class ExaDatatablesGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.4 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/domain/**",
        "grails-app/controllers/**",
        "grails-app/services/**",
        "grails-app/views/error.gsp"
    ]

    def title = "Exa Datatables Plugin" // Headline display name of the plugin
    def author = "Exanpe"
    def authorEmail = "exanpe@gmail.com"
    def description = '''\
This plugin provides integration with DataTables (Table plug-in for jQuery).
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/exanpe/exa-grails-plugins"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Exanpe", url: "https://github.com/exanpe" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Laurent Guérin", email: "lguerin.fr@gmail.com" ], [ name: "Julien Maupoux", email: "jmaupoux@gmail.com" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "github", url: "https://github.com/exanpe/exa-grails-plugins/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-datatables" ]

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { ctx ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }
}
