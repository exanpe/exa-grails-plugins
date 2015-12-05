class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        for (controllerClass in grailsApplication.controllerClasses) {
            controllerClass.metaClass.csrfHandler = {controller, request, params ->
                log.warn("Oops, CSRF !")
                throw new IllegalStateException("Wrong CSRF token catch !")
            }
        }
    }
    def destroy = {
    }
}
