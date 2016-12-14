package fr.exanpe.grails.datatables

import fr.exanpe.grails.datatables.marshaller.DatatableModelRowJsonMarshaller
import grails.plugins.Plugin

class ExaDatatablesGrailsPlugin extends Plugin {

    def grailsVersion = "3.1.8 > *"
    def pluginExcludes = [
        "domain/**",
        "controllers/**",
        "services/**",
        "src/groovy/fr/exanpe/grails/datatables/test/**"
    ]
    def title = "Exa Datatables Plugin"
    def author = "Exanpe"
    def authorEmail = "exanpe@gmail.com"
    def description = 'Provides easy integration with DataTables.net (Table plug-in for jQuery).'
    def profiles = ['web']
    def documentation = "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-datatables"
    def license = "APACHE"
    def organization = [ name: "Exanpe", url: "https://github.com/exanpe" ]
    def developers = [ [ name: "Laurent Guérin", email: "lguerin.fr@gmail.com" ], [ name: "Julien Maupoux", email: "jmaupoux@gmail.com" ]]
    def issueManagement = [ system: "github", url: "https://github.com/exanpe/exa-grails-plugins/issues" ]
    def scm = [ url: "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-datatables" ]

    void doWithApplicationContext() {
        DatatableModelRowJsonMarshaller.register()
    }
}
