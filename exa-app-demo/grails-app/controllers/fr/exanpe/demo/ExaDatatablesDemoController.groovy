package fr.exanpe.demo

import org.jfairy.Fairy
import org.jfairy.producer.company.Company
import org.jfairy.producer.person.Person
import org.jfairy.producer.person.PersonProperties

class ExaDatatablesDemoController {

    def index() {
        [persons: getSimplePersons(), persons2: getPersons()]
    }

    def custom() {
        [persons: getPersons()]
    }

    private def getSimplePersons() {
        Fairy fairy = Fairy.create();
        List<SimplePerson> persons = []
        15.times {
            Person p = fairy.person()
            persons << new SimplePerson(firstName: p.firstName(), lastName: p.lastName(), email: p.email(),
                    username: p.username(), sex: p.sex(), age: p.age())
        }
        return persons
    }

    private def getPersons() {
        Fairy fairy = Fairy.create();
        Company company = fairy.company();
        Company company2 = fairy.company();
        List<Person> persons = []
        20.times {
            Company c = it % 2 == 0 ? company2 : company
            persons << fairy.person(PersonProperties.withCompany(c))
        }
        return persons
    }
}

class SimplePerson {
    String firstName
    String lastName
    String email
    String username
    Person.Sex sex
    Integer age
}
