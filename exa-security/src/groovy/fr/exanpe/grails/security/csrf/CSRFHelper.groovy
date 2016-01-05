package fr.exanpe.grails.security.csrf

import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

/**
 * Holds common CSRF related methods.
 * @author jmaupoux
 */
class CSRFHelper {

    /**
     * Validate a token submitted without consumming it.
     * @session the user session
     * @param the request params
     * @return true if valid
     */
    static boolean isValidToken(session, params) {
        SynchronizerTokensHolder token = session.store(session)
        try {
            return token.isValid(params[SynchronizerTokensHolder.TOKEN_URI], params[SynchronizerTokensHolder.TOKEN_KEY])
        }
        catch(e) {
            return false
        }
    }
}
