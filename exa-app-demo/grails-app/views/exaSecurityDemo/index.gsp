<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>exa-security Demo</title>
</head>
<body>
        <h1>Taglib CSRF link</h1>
		<blockquote>
			<p>exas:link propagate a CSRF token</p>
		</blockquote>

		<exas:link action="link">Click me</exas:link>
		<pre>
			<code class="html">&lt;exas:link action="link">Click me&lt;/exas:link></code>
		</pre>
		<br/>

		<exas:link action="link" params="['p':'t']">exa:link Success - 2nd token</exas:link>
		<pre>
			<code class="html">&lt;exas:link action="link" params="['p':'t']">With additional params&lt;/exas:link></code>
		</pre>
		<br/>

		<g:link action="link">Link without token fail and generates IllegalStateException</g:link>
		<pre>
			<code class="html">&lt;g:link action="link">Link without token fail and generates IllegalStateException&lt;/exas:link></code>
		</pre>


        <h1>Redirect engine</h1>

	<pre><code>SESSIONID :: ${session.id}</code></pre><br/>

        <g:link action="doredirect">Redirect with a flash scope URL, and clean session</g:link>
		<pre>
			<code class="html">&lt;g:link action="doredirect">Redirect with a flash scope URL, and keep session alive&lt;/g:link></code>
			<code class="java">
def doredirect(){
	flash.redirect = "http://www.google.fr"
	redirect(controller: "redirectEngine", action: "flash")
}
			</code>
		</pre>
	<br/>

		<g:link action="doredirectkeep">Redirect with a flash scope URL, and keep session alive</g:link>
		<pre>
			<code class="html">&lt;g:link action="doredirectkeep">Redirect with a flash scope URL, and keep session alive&lt;/g:link></code>
		<code class="java">
def doredirectkeep(){
	flash.redirect = "http://www.google.fr"
	flash.keepSession = true
	redirect(controller: "redirectEngine", action: "flash")
}
		</code>
		</pre>
	<br/>

	<g:link controller="redirectEngine" action="map" params="[key : 'yahoo']">Redirect to an URL configured in Config.groovy, and clean session</g:link>
	<pre>
		<code class="html">&lt;g:link controller="redirectEngine" action="map" params="[key : 'yahoo']">Redirect to an URL configured in Config.groovy, and clean session&lt;/g:link></code>
	</pre>
	<br/>

	<g:link controller="redirectEngine" action="map" params="[key : 'yahoo_keepsession']">Redirect to an URL configured in Config.groovy, and keep session</g:link>
	<pre>
		<code class="html">&lt;g:link controller="redirectEngine" action="map" params="[key : 'yahoo_keepsession']">Redirect to an URL configured in Config.groovy, and keep session&lt;/g:link></code>
	</pre>
	<br/>

        <h1>Headers</h1>
        <table class="table">
        <%
            for(String i : new HashSet<String>(response.headerNames)){
        %>
            <tr>
                <td><b><%= i %></b></td>
                <td><%= response.getHeaders(i).join(",") %></td>
            </tr>
        <%
            }
        %>
        </table>

        <b><pre>Iframe Rendering : Expected : <%= grailsApplication.mergedConfig.grails.plugin.exa.sec.headers.xframe %></pre></b>
        <iframe width="100%" src="<%= request.servletContext.contextPath %>"></iframe>
	</body>
</html>
