import fr.exanpe.grails.security.CSRFTokenException
import grails.util.Environment

class ExaSecurityGrailsPlugin {
    def version = "1.0.0"
    def grailsVersion = "2.4 > *"
    def pluginExcludes = [
            "grails-app/views/**",
            "grails-app/controllers/fr/exanpe/test/**"
    ]

    def title = "Exa Security Plugin"
    def author = "Julien Maupoux"
    def description = '''\
Enhance your application security with some tools - script, scaffolding, filters and more.
This plugin is only compatible with Grails 2
'''

    def documentation = "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-security"
    def license = "APACHE"
    def organization = [ name: "Exanpe", url: "https://github.com/exanpe" ]
    def developers = [ [ name: "Julien Maupoux", email: "jmaupoux@gmail.com" ]]
    def issueManagement = [ system: "github", url: "https://github.com/exanpe/exa-grails-plugins/issues" ]
    def scm = [ url: "https://github.com/exanpe/exa-grails-plugins/tree/master/exa-security" ]

    def doWithWebDescriptor = { xml ->

        /********** cookie-config **********/
        def cookie = application.mergedConfig.grails.plugin.exa.sec.cookie
        if (cookie.secure.enabled) {
            log.info('Secure Cookie enabled')
            if(cookie.secure.productionOnly && Environment.current != Environment.PRODUCTION){
                log.info('Secure Cookie skipped : productionOnly declared and not a production build')
            }else{
                def sessionConfig = xml.'session-config'

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

        if (cookie.name) {
            def sessionConfig = xml.'session-config'

            if(!xml.'session-config'.'cookie-config'[0]){
                sessionConfig[0].appendNode('cookie-config', [:])
            }

            if(!xml.'session-config'.'cookie-config'.'name'[0]){
                xml.'session-config'.'cookie-config'[0].appendNode('name', cookie.name)
            }else{
                xml.'session-config'.'cookie-config'.'name'[0].replaceBody(cookie.name)
            }
            log.info('Secure Name added to web.xml')
        }
    }

    def doWithDynamicMethods = { ctx ->

        for (controllerClass in application.controllerClasses) {
            controllerClass.metaClass.csrfHandler = {controller, request, params ->
                throw new CSRFTokenException()
            }
        }

    }
}
