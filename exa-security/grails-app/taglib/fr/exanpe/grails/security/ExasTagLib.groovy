package fr.exanpe.grails.security

import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder

class ExasTagLib {
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [link: 'raw']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = 'exas'

    /**
     * Enrich a link with a CSRF token to protect the action.
     * Use this tag with the withForm{ } closure in Controller to enable server side token verification
     * @see <g:link> params
     * @since 1.0.0
     */
    def link = { attrs, body ->
        def params = attrs.params?attrs.params:[:]
        attrs.params = params

        SynchronizerTokensHolder holder = SynchronizerTokensHolder.store(session)

        params[SynchronizerTokensHolder.TOKEN_KEY] = holder.generateToken(request.forwardURI)
        params[SynchronizerTokensHolder.TOKEN_URI] = request.forwardURI

        out << g.link(attrs, body)
    }
}
