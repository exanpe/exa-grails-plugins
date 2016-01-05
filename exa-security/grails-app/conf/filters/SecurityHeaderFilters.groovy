package filters

/**
 * Sets some http headers enforcing Security
 * @see http://stackoverflow.com/questions/1353156/how-to-prevent-grails-from-caching-old-versions-of-gsp-file
 */
class SecurityHeaderFilters {

    private static final String HEADER_PRAGMA = "Pragma"
    private static final String HEADER_EXPIRES = "Expires"
    private static final String HEADER_CACHE_CONTROL = "Cache-Control"
    private static final String HEADER_SERVER = "Server"
    private static final String HEADER_X_FRAME = "X-Frame-Options"

    def filters = {
        /**
         * For all filters
         */
        noCacheHeaderFilters {
            after = {
                def headers = grailsApplication.mergedConfig.grails.plugin.exa.sec.headers
                if(headers.nocache){
                    response.setHeader(HEADER_PRAGMA, "no-cache")
                    response.setDateHeader(HEADER_EXPIRES, 0L)
                    response.setHeader(HEADER_CACHE_CONTROL, "no-cache")
                    response.addHeader(HEADER_CACHE_CONTROL, "no-store")
                    response.addHeader(HEADER_CACHE_CONTROL, "must-revalidate")
                }
                if(headers.server){
                    response.setHeader(HEADER_SERVER, headers.server)
                }
                if(headers.xframe){
                    response.setHeader(HEADER_X_FRAME, headers.xframe)
                }
            }
        }
    }
}
