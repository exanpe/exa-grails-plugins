package fr.exanpe.grails.datatables.util

import spock.lang.Specification
import spock.lang.Unroll

class TagLibUtilsSpec extends Specification {

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
}
