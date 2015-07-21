import fr.exanpe.grails.security.CSRFTokenException

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        for (controllerClass in grailsApplication.controllerClasses) {
            controllerClass.metaClass.csrfHandler = {controller, request, params ->
                log.warn("Oops, CSRF !")
                controller.redirect(url : "http://www.google.fr")
            }
        }
    }
    def destroy = {
    }
}
