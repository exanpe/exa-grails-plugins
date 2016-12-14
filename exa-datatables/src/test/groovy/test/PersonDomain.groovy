package test

import grails.gorm.annotation.Entity

@Entity
class PersonDomain {

    String name
    String address
    String zipCode
    String city
    Integer age

    static constraints = {
        address nullable: true
        zipCode nullable: true
        city nullable: true
        age nullable: true
    }

    static namedQueries = {
        namedMyself {
            eq('name', 'Laurent')
        }
    }
}
