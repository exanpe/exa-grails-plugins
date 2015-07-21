package fr.exanpe.grails.security.csrf

import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

/**
 * This class hold common CSRF related methods
 * Created by jmaupoux on 17/07/15.
 */
class CSRFHelper {

    /**
     * Validate a token submitted without consumming it
     * @session the user session
     * @param the request params
     * @return true if valid, false otherwise
     */
    static boolean isValidToken(def session, def params) {
        SynchronizerTokensHolder token = session.store(session)
        try {
            return token.isValid(params[SynchronizerTokensHolder.TOKEN_URI], params[SynchronizerTokensHolder.TOKEN_KEY])
        }
        catch(Exception e) {
            return false
        }
    }
}
