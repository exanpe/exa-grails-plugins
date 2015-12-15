import fr.exanpe.grails.security.CSRFTokenException
import grails.util.Environment

class ExaSecurityGrailsPlugin {
    // the plugin version
    def version = "1.0.0"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.4 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/**",
            "grails-app/domain/**",
            "grails-app/controllers/fr/exanpe/test/**"
    ]

    def title = "Exa Security Plugin" // Headline display name of the plugin
    def author = "Julien Maupoux"
    def authorEmail = ""
    def description = '''\
Enhance your application security with some tools - script, scaffolding, filters and more.
This plugin is only compatible with Grails 2
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-security"

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    def organization = [ name: "Exanpe", url: "https://github.com/exanpe" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Julien Maupoux", email: "jmaupoux@gmail.com" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "github", url: "https://github.com/exanpe/exa-grails-plugins/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-security" ]

    def doWithWebDescriptor = { xml ->

        /********** cookie-config **********/
        if (application.mergedConfig.grails.plugin.exa.sec.cookie.secure.enabled) {
            log.info('Secure Cookie enabled')
            if(application.mergedConfig.grails.plugin.exa.sec.cookie.secure.productionOnly && Environment.current != Environment.PRODUCTION){
                log.info('Secure Cookie skipped : productionOnly declared and not a production build')
            }else{
                def sessionConfig = xml.'session-config';

                if(!xml.'session-config'.'cookie-config'[0]){
                    sessionConfig[0].appendNode('cookie-config', [:])
                }

                if(!xml.'session-config'.'cookie-config'.'secure'[0]){
                    xml.'session-config'.'cookie-config'[0].appendNode('secure', 'true')
                }else{
                    xml.'session-config'.'cookie-config'.'secure'[0].replaceBody('true')
                }
                log.info('Secure Cookie added to web.xml')
            }
        }else{
            log.info('Secure Cookie skipped : not enabled')
        }

        if (application.mergedConfig.grails.plugin.exa.sec.cookie.name) {
            def sessionConfig = xml.'session-config';

            if(!xml.'session-config'.'cookie-config'[0]){
                sessionConfig[0].appendNode('cookie-config', [:])
            }

            if(!xml.'session-config'.'cookie-config'.'name'[0]){
                xml.'session-config'.'cookie-config'[0].appendNode('name', application.mergedConfig.grails.plugin.exa.sec.cookie.name)
            }else{
                xml.'session-config'.'cookie-config'.'name'[0].replaceBody(application.mergedConfig.grails.plugin.exa.sec.cookie.name)
            }
            log.info('Secure Name added to web.xml')
        }
    }

    def doWithSpring = {

    }

    def doWithDynamicMethods = { ctx ->

        for (controllerClass in application.controllerClasses) {
            controllerClass.metaClass.csrfHandler = {controller, request, params ->
                throw new CSRFTokenException()
            }
        }

    }

    def doWithApplicationContext = { ctx ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
