package fr.exanpe.grails.datatables.marshaller

import fr.exanpe.grails.datatables.model.DatatableModelCell
import fr.exanpe.grails.datatables.model.DatatableModelRow
import grails.converters.JSON

class DatatableModelRowJsonMarshaller {

    static void register() {
        JSON.registerObjectMarshaller(DatatableModelRow) { DatatableModelRow row ->
            def result = [:]
            row?.cells?.values()?.each { DatatableModelCell cell ->
                result[cell.name] = cell.value
            }
            result
        }
    }
}
