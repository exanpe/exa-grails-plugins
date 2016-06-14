package fr.exanpe.grails.datatables.util

import fr.exanpe.grails.datatables.test.Person
import grails.converters.JSON
import spock.lang.Specification
import spock.lang.Unroll

class TagLibUtilsSpec extends Specification {

    Person person1 = new Person(name: "Laurent", address: "10 rue de Paris", zipCode: "75001", city: "Paris", age: 35)
    String jsonPerson = "{name: \"Laurent\", address: \"10 rue de Paris\", zipCode: \"75001\", city: \"Paris\", age: 35}"

    @Unroll
    def 'Convert "#input" string column names as List'() {
        expect:
        result == TagLibUtils.asList(input)

        where:
        result                      | input
        ["name", "age"]             | "name,age"
        ["name", "age"]             | "name age"
        ["name", "zipCode"]         | " name,  zipCode  "
        []                          | ""
    }

    def 'Build Datatable model row from input data'() {
        when: 'Build from an object instance'
        def datatableModelRow = TagLibUtils.addDatatableModelRow(person1, ['name', 'zipCode'])

        then:
        datatableModelRow.cells.get('name').value == "Laurent"
        !datatableModelRow.cells.containsKey('age')

        when: 'Build from JSON object'
        datatableModelRow = TagLibUtils.addDatatableModelRow(JSON.parse(jsonPerson), ['name', 'zipCode'])

        then:
        datatableModelRow.cells.get('name').value == "Laurent"
        !datatableModelRow.cells.containsKey('age')
    }
}
