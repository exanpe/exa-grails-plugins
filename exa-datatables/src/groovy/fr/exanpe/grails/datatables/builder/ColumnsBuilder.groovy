package fr.exanpe.grails.datatables.builder

import fr.exanpe.grails.datatables.util.TagLibUtils as Utils

/**
 * Builder used to compute list of columns to display into the datatable.
 */
class ColumnsBuilder {

    List<String> columns

    ColumnsBuilder() {
        columns = []
    }

    ColumnsBuilder from(Object item) {
        columns = filterItemProperties(item)
        return this
    }

    ColumnsBuilder include(String columns) {
        if (!columns) return this
        def toInclude = Utils.asList(columns)
        def filtered = filter(toInclude)
        this.columns = filtered
        return this
    }

    ColumnsBuilder exclude(String columns) {
        if (!columns) return this
        def toExclude = Utils.asList(columns)
        def filtered = filter(toExclude)
        this.columns = (this.columns - filtered)
        return this
    }

    ColumnsBuilder add(String columns) {
        if (!columns) return this
        def extraColumns = Utils.asList(columns)
        this.columns.addAll(extraColumns)
        return this
    }

    List<String> build() {
        return columns
    }

    private List<String> filter(List<String> filteredColumns) {
        def filtered = []
        filteredColumns.each {
            def index = columns*.toLowerCase().indexOf(it.toLowerCase())
            if (index < 0) {
                throw new RuntimeException("Unknow column: " + it)
            }
            filtered << columns.get(index)
        }
        return filtered
    }

    private List<String> filterItemProperties(Object item) {
        // GORM & Grails auto-generated properties to filter by default
        def filtered = ['class', 'constraints', 'errors', 'constraints', 'namedQueries',
                        'instanceControllersDomainBindingApi', '$defaultDatabindingWhiteList', 'instanceDatabindingApi',
                        'instanceGormValidationApi', 'errors', '$changedProperties', 'version', 'instanceGormInstanceApi',
                        'staticGormStaticApi', 'log', 'instanceConvertersApi', 'mimeTypesApi']
        return item.class.declaredFields.findAll { !it.synthetic && !(it.name in filtered) }.collect { it.name }
    }
}
