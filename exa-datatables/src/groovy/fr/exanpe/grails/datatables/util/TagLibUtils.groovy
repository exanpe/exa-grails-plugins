package fr.exanpe.grails.datatables.util

import fr.exanpe.grails.datatables.builder.ColumnsBuilder
import fr.exanpe.grails.datatables.model.DatatableModelRow

class TagLibUtils {

    /**
     * Compute columns to display into the datatable
     *
     * @param item              Current iterator item
     * @param toInclude         Columns to include
     * @param toExclude         Columns to exclude
     * @param toHide            Columns to hide
     * @param extraColumns      Extra columns to add
     * @param order             The order of the columns
     * @return
     */
    static List<String> computeColumns(Object item, String toInclude, String toExclude, String toHide, String extraColumns, String order) {
        ColumnsBuilder builder = new ColumnsBuilder()
        builder.from(item).include(toInclude).exclude(toExclude).hidden(toHide).add(extraColumns).reorder(order)
        return builder.build()
    }

    /**
     * Convert provided item and columns to display as a DatatableModelRow instance
     *
     * @param item              Current iterator item
     * @param columns           List of columns to display
     * @param hiddenColumns     List of columns to add the list of rows, but to not display
     * @return
     */
    static DatatableModelRow addDatatableModelRow(Object item, List<String> columns, List<String> hiddenColumns = []) {
        DatatableModelRow row = new DatatableModelRow()
        def all = columns + hiddenColumns
        all.each { column ->
            def value = item.hasProperty(column) ? item."$column" : ""
            row.addCell(column, value?.toString(), hiddenColumns.contains(column))
        }
        return row
    }

    /**
     * Convert the given columns from string to list of string
     * @param input
     * @return
     */
    static List<String> asList(String columns) {
        if (!columns) return []
        def list = columns.contains(",") ? columns.tokenize(",") : columns.tokenize()
        list = list*.trim()
        return list
    }
}
