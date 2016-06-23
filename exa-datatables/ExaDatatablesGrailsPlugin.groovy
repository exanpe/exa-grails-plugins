import fr.exanpe.grails.datatables.marshaller.DatatableModelRowJsonMarshaller

class ExaDatatablesGrailsPlugin {
    def version = "1.0.2"
    def grailsVersion = "2.4 > *"
    def pluginExcludes = [
        "grails-app/domain/**",
        "grails-app/controllers/**",
        "grails-app/services/**",
        "src/groovy/fr/exanpe/grails/datatables/test/**"
    ]
    def title = "Exa Datatables Plugin"
    def author = "Exanpe"
    def authorEmail = "exanpe@gmail.com"
    def description = 'Provides easy integration with DataTables.net (Table plug-in for jQuery).'
    def documentation = "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-datatables"
    def license = "APACHE"
    def organization = [ name: "Exanpe", url: "https://github.com/exanpe" ]
    def developers = [ [ name: "Laurent Guérin", email: "lguerin.fr@gmail.com" ], [ name: "Julien Maupoux", email: "jmaupoux@gmail.com" ]]
    def issueManagement = [ system: "github", url: "https://github.com/exanpe/exa-grails-plugins/issues" ]
    def scm = [ url: "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-datatables" ]

    def doWithApplicationContext = {
        DatatableModelRowJsonMarshaller.register()
    }
}
