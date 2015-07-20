package fr.exa.datatables

/**
 * Taglib that ease use of Datatables.net
 */
class ExaDatatablesTagLib {

    static namespace = "exa"

    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [datatable: [taglib:'raw']]

    /**
     * Datatable component
     *
     * @attr id REQUIRED Client ID of the datatable
     * @attr data REQUIRED Datatable data to display
     * @attr columns Name of data columns to display
     * @attr class CSS classes to apply
     */
    def datatable = { attrs, body ->
        out << render(template: "/tpl/datatable", plugin: "exa-datatables",
                model: [id: attrs.id,
                        data: attrs.data,
                        columns: attrs.columns,
                        cssClass: attrs.class,
                        body: body()]
        )
    }

}
