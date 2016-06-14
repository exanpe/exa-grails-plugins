package fr.exanpe.grails.datatables

import fr.exanpe.grails.datatables.model.DatatableModel
import fr.exanpe.grails.datatables.model.DatatableModelRow
import fr.exanpe.grails.datatables.util.TagLibUtils as Utils
import grails.converters.JSON
import org.grails.web.converters.exceptions.ConverterException

/**
 * Eases use of Datatables.net
 */
class ExaDatatablesTagLib {

    static namespace = "exa"

    static defaultEncodeAs = [taglib: 'html']
    static encodeAsForTags = [datatable: [taglib:'none']]

    private static final String DATATABLE_MODEL = 'datatableModel'
    private static final String DATATABLE_ROW_MODEL = 'datatableRowModel'
    private static final String DATATABLE_ROW_INDEX = 'datatableRowIndex'
    private static final String DATATABLE_ROW_ITEM = 'datatableRowItem'

    /**
     * Datatable component
     *
     * @attr id REQUIRED Client ID of the datatable
     * @attr items REQUIRED The collection of objects to iterate over
     * @attr include List of property names (case-insensitive) to be retained from each item
     * @attr exclude List of property names (case-insensitive) to be removed from each item
     * @attr add Extra columns to display for each item. Cells for added columns will be blank by default,
     *           unless you provide a custom rowCell for each.
     * @attr hidden List of property names (case-insensitive) to include to the data model but not to display.
     *              Useful when you want to have access to a given property but not display this property ('id' for
     *              example)
     * @attr reorder  List of property names (case-insensitive) indicating the order in which the columns should be
     *                presented. Could be the beginning or the full list of columns.
     * @attr class CSS classes to apply
     * @attr filtering Enable or disable table filtering (default true)
     * @attr ordering  Enable or disable column ordering (default true)
     * @attr paging  Enable or disable paging (default true)
     * @attr infos  Enable or disable table information display field (default true)
     * @attr stateSave  Enable or disable saving the state of a table (paging position, ordering state, etc) so that is can be restored when the user reloads a page (default true)
     * @attr auto  Enable or disable auto rendering of the datatable (default true).
     *             Used to take control over Datatable settings or customization.
     *             If false, you have to call render(options) yourself on client-side.
     */
    def datatable = { attrs, body ->
        def filtering = attrs.filtering ?: "true"
        def ordering = attrs.ordering ?: "true"
        def paging = attrs.paging ?: "true"
        def infos = attrs.infos ?: "true"
        def stateSave = attrs.stateSave ?: "true"
        def auto = attrs.auto ?: "true"

        def items = getListOfItems(attrs.remove('items') ?: [])
        if (items.size() == 0) throwTagError("Tag [datatable] must have a valid and not empty collection of [items]")

        def include = attrs.remove('include')
        def exclude = attrs.remove('exclude')
        if (include && exclude) throwTagError("Tag [datatable] attributes [include] and [exclude] can't be used together")

        def hidden = attrs.remove('hidden')
        def hiddenList = Utils.asList(hidden)
        def columns = Utils.computeColumns(items[0], include, exclude, hidden, attrs.remove('add'), attrs.remove('reorder'))

        DatatableModel model = beforeRender(columns)
        items.eachWithIndex { item, index ->
            def row = Utils.addDatatableModelRow(item, columns, hiddenList)
            beforeRenderRow(row, item, index)
            body()
            afterRenderRow(model, row)
        }
        afterRender()

        out << render(template: "/tpl/datatable",
                model: [id: attrs.id,
                        data: model?.rows as JSON,
                        headers: model.headers,
                        columns: columns as JSON,
                        cssClass: attrs.class,
                        filtering: filtering,
                        ordering: ordering,
                        paging: paging,
                        infos: infos,
                        stateSave: stateSave,
                        auto: auto]
        )
    }

    /**
     * Customize the rendering of a column of the datatable
     *
     * @attr name REQUIRED The name of the property column to customize
     */
    def customColumn = { attrs, body ->
        def name = attrs.name
        DatatableModelRow row = pageScope.getVariable(DATATABLE_ROW_MODEL)
        def item = pageScope.getVariable(DATATABLE_ROW_ITEM)
        def value = body(it: item)
        if (value) {
            row.updateCell(name, value.toString())
        }
    }

    /**
     * Customize the rendering of a header column of the datatable
     *
     * @attr name REQUIRED The name of the property to customize
     * @attr value REQUIRED The label value to set (could be a literal or a i18n message)
     */
    def customHeader = { attrs, body ->
        def index = pageScope.getVariable(DATATABLE_ROW_INDEX)
        // Custom headers are processed only once
        if (index > 0) return

        def name = attrs.remove('name')
        def value = attrs.remove('value')
        DatatableModel model = pageScope.getVariable(DATATABLE_MODEL)
        if (value) {
            model.updateHeader(name, value)
        }
    }

    /**
     * Before rendering the datatable
     *
     * @param columns The datatable columns
     */
    private DatatableModel beforeRender(List<String> columns) {
        DatatableModel model = new DatatableModel()
        model.addHeaders(columns)
        pageScope.setVariable(DATATABLE_MODEL, model)
        return model
    }

    /**
     * Before rendering each row that corresponds to the current iterator item
     *
     * @param row       Row of the DatatableModel to feed
     * @param current   Current iterator item
     * @param index     Current index
     */
    private void beforeRenderRow(DatatableModelRow row, Object current, int index) {
        pageScope.setVariable(DATATABLE_ROW_MODEL, row)
        pageScope.setVariable(DATATABLE_ROW_ITEM, current)
        pageScope.setVariable(DATATABLE_ROW_INDEX, index)
    }

    /**
     * After rendering each row
     *
     * @param model     DatatableModel to display into the datatable
     * @param row       Row of the DatatableModel
     */
    private void afterRenderRow(DatatableModel model, DatatableModelRow row) {
        pageScope.setVariable(DATATABLE_ROW_MODEL, null)
        pageScope.setVariable(DATATABLE_ROW_ITEM, null)
        pageScope.setVariable(DATATABLE_ROW_INDEX, null)
        model.rows << row
    }

    /**
     * After rendering the datatable
     */
    private void afterRender() {
        pageScope.setVariable(DATATABLE_MODEL, null)
    }

    /**
     * Get items as list of objects, particularly for JSON contents
     *
     * @param items     Items to display
     */
    private def getListOfItems(def items) {
        // If items are sent in JSON format
        if (items instanceof String || items instanceof GString) {
            try {
                return JSON.parse(items)
            }
            catch (ConverterException e) {
                throwTagError("Tag [datatable] attributes [items] does not contains valid JSON content")
            }
        }
        return items
    }
}
