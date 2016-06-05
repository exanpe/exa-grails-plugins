package fr.exanpe.grails.datatables.builder

import fr.exanpe.grails.datatables.util.TagLibUtils as Utils
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException

/**
 * Computes list of columns to display into the datatable.
 */
class ColumnsBuilder {

    List<String> columns = []

    ColumnsBuilder from(item) {
        columns = item instanceof JSONObject ? item.keySet() as List<String> : filterItemProperties(item)
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

    ColumnsBuilder hidden(String columns) {
        if (!columns) return this
        def toHide = Utils.asList(columns)
        def filtered = filter(toHide)
        this.columns = (this.columns - filtered)
        return this
    }

    ColumnsBuilder reorder(String columns) {
        if (!columns) return this
        def toReorder = Utils.asList(columns)
        def filtered = filter(toReorder)
        this.columns = filtered + (this.columns - filtered)
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
                throw new GrailsTagException("Tag [datatable] error - Unknown column: " + it)
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
        return item.class?.declaredFields.findAll { !it.synthetic && !(it.name in filtered) }.collect { it.name }
    }
}
