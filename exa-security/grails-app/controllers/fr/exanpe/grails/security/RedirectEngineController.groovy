package fr.exanpe.grails.security

/**
 * Offer entry point to send redirect with target defined securely
 * - From "flash" value
 * - From static configuration mapping
 * @author jmaupoux
 */
class RedirectEngineController {

    static allowedMethods = [flash : "GET"]

    /**
     * Redirect according to the flash.redirect variable
     */
    def flash(){
        if(!flash.redirect){
            log.error("flash.redirect not supplied")
            throw new IllegalStateException("flash.redirect not supplied")
        }

        def target = flash.redirect

        log.debug("Flash redirection to : ${target}")

        if(!flash.keepSession){
            log.debug("Session invalidate")
            session.invalidate()
        }

        redirect(url : target)
    }

    /**
     * Redirect according to the params.key value
     * This value is mapped in grails.plugin.exa.sec.redirects.map configuration
     */
    def map(){
        if(!params.key){
            log.error("Redirect map require params.key")
            throw new IllegalStateException("params.key not supplied")
        }

        def mapping = grailsApplication.mergedConfig.grails.plugin.exa.sec.redirects.map[params.key]

        if(!mapping){
            log.error("Redirect '${params.key}' is not mapped to a target")
            throw new IllegalStateException("params.key not supplied")
        }

        def target
        boolean keepSession = false

        if(mapping instanceof String){
            target = mapping
        }else{
            if(!mapping.url){
                log.error("Mapping for '${params.key}' must defined 'url' key")
                throw new IllegalStateException("url not supplied")
            }
            target = mapping.url
            keepSession = mapping.keepSession
        }

        log.debug("Mapped redirection to : ${target}")

        if(!keepSession){
            log.debug("Session invalidate")
            session.invalidate()
        }

        redirect(url : target)
    }

}
