package fr.exanpe.grails.datatables

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
     * @attr data REQUIRED Datas to display
     * @attr columns Name of data columns to display
     * @attr class CSS classes to apply
     * @attr filtering Enable or disable table filtering (default true)
     * @attr ordering  Enable or disable column ordering (default true)
     * @attr paging  Enable or disable paging (default true)
     * @attr infos  Enable or disable table information display field (default true)
     * @attr auto  Enable or disable auto rendering of the datatable (default true).
     *             Used to take control over Datatable settings or customization.
     *             In this case, you have to call render() yourself on client-side.
     */
    def datatable = { attrs, body ->
        def filtering = attrs.filtering ?: "true"
        def ordering = attrs.ordering ?: "true"
        def paging = attrs.paging ?: "true"
        def infos = attrs.infos ?: "true"
        def auto = attrs.auto ?: "true"
        out << render(template: "/tpl/datatable", plugin: "exa-datatables",
                model: [id: attrs.id,
                        data: attrs.data,
                        columns: attrs.columns,
                        cssClass: attrs.class,
                        filtering: filtering,
                        ordering: ordering,
                        paging: paging,
                        infos: infos,
                        auto: auto,
                        body: body()]
        )
    }

}
