package fr.exanpe.grails.datatables

import fr.exanpe.grails.datatables.model.DatatableModel
import fr.exanpe.grails.datatables.model.DatatableModelRow
import fr.exanpe.grails.datatables.util.TagLibUtils
import fr.exanpe.grails.datatables.util.TagLibUtils as Utils
import grails.converters.JSON

/**
 * Taglib that ease use of Datatables.net
 */
class ExaDatatablesTagLib {

    static namespace = "exa"

    static defaultEncodeAs = [taglib: 'html']
    static encodeAsForTags = [datatable: [taglib:'raw']]

    private static DATATABLE_MODEL = 'datatableModel'
    private static DATATABLE_ROW_MODEL = 'datatableRowModel'
    private static DATATABLE_ROW_INDEX = 'datatableRowIndex'
    private static DATATABLE_ROW_ITEM = 'datatableRowItem'

    /**
     * Datatable component
     *
     * @attr id REQUIRED Client ID of the datatable
     * @attr items REQUIRED List of items to display
     * @attr include List of property names (case-insensitive) to be retained from each item
     * @attr exclude List of property names (case-insensitive) to be removed from each item
     * @attr add Extra columns to display for each item. Cells for added columns will be blank by default,
     *           unless you provide a custom rowCell for each.
     * @attr hidden List of property names (case-insensitive) to include to the data model but not to display.
     *              Useful when you want to have access to a given property but not display this property ('id' for
     *              example)
     * @attr class CSS classes to apply
     * @attr filtering Enable or disable table filtering (default true)
     * @attr ordering  Enable or disable column ordering (default true)
     * @attr paging  Enable or disable paging (default true)
     * @attr infos  Enable or disable table information display field (default true)
     * @attr auto  Enable or disable auto rendering of the datatable (default true).
     *             Used to take control over Datatable settings or customization.
     *             If false, you have to call render(options) yourself on client-side.
     */
    def datatable = { attrs, body ->
        def filtering = attrs.filtering ?: "true"
        def ordering = attrs.ordering ?: "true"
        def paging = attrs.paging ?: "true"
        def infos = attrs.infos ?: "true"
        def auto = attrs.auto ?: "true"

        def items = attrs.remove('items') ?: []
        if (items.size() == 0) throwTagError("Tag [datatable] must have a valid [items] attribute")

        def include = attrs.remove('include')
        def exclude = attrs.remove('exclude')
        if (include && exclude) throwTagError("Tag [datatable] attributes [include] and [exclude] can't be used together")
        def columns = Utils.computeColumns(items[0], include, exclude, attrs.remove('add'))
        def hiddenColumns = Utils.asList(attrs.remove('hidden'))

        DatatableModel model = beforeRender(columns)
        items.eachWithIndex { item, index ->
            def row = TagLibUtils.addDatatableModelRow(item, columns, hiddenColumns)
            beforeRenderRow(row, item, index)
            body()
            afterRenderRow(model, row)
        }
        afterRender()

        out << render(template: "/tpl/datatable", plugin: "exa-datatables",
                model: [id: attrs.id,
                        data: model?.rows as JSON,
                        headers: model.headers,
                        columns: columns as JSON,
                        cssClass: attrs.class,
                        filtering: filtering,
                        ordering: ordering,
                        paging: paging,
                        infos: infos,
                        auto: auto]
        )
    }

    /**
     * Customize the rendering of a column of the datatable
     *
     * @attr name REQUIRED Name of the column
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
     * Customize the rendering of a header of the datatable
     *
     * @attr name REQUIRED Name of the header column
     * @attr value REQUIRED Custom value to use
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
     * Before rendering datatable
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
     * After rendering datatable
     */
    private void afterRender() {
        pageScope.setVariable(DATATABLE_MODEL, null)
    }
}
