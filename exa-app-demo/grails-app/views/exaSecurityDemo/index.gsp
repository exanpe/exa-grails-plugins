<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>exa-security Demo</title>
</head>
<body>
        <h1>Taglib CSRF link</h1>

		<h4>exas:link propagate a CSRF token</h4>

		<pre><code class="html">&lt;exas:link action="link">Click me&lt;/exas:link></code></pre>

		<p>Render as a standard link :</p>
		<exas:link action="link">Click me</exas:link>
		<br/><br/>
		<blockquote>
			Do not forget to handle the token in the controller action
		</blockquote>
<pre><code class="java">def link(){
	withForm {
		redirect(action: "index")
	}.invalidToken {
		//what your want here
	}
}</code></pre>

		<h4>exas:link works with additional parameters</h4>
		<pre><code class="html">&lt;exas:link action="link" params="['p':'t']">With additional params&lt;/exas:link></code></pre>

		<p>Render as a standard link :</p>
		<exas:link action="link" params="['p':'t']">exa:link Success - 2nd token</exas:link>

<br/><br/><br/>

		<h4>Clicking link without the token fail</h4>
		In this example, an Exception is thrown
		<pre><code class="html">&lt;g:link action="link">Link without token fail and generates Exception&lt;/exas:link></code></pre>

		<p>Render as :</p>
		<g:link action="link">Link without token fail and generates an Exception</g:link>

		<br/><br/>
		<blockquote>It's up to you can handle that exception or any other behavior globally</blockquote>

        <h1>Redirect engine</h1>

	Hi, this is your session ID :
	<pre><code>SESSIONID :: ${session.id}</code></pre><br/>

	<p>The redirect engine let you manage redirection safely server side.<br/>
		You can interact with the redirect in several ways</p>

	<h4>Using flash scope, invalidating session</h4>

<pre><code class="html">&lt;g:link action="doredirect">Redirect with a flash scope URL, and keep session alive&lt;/g:link></code>
	<code class="java">def doredirect(){
	flash.redirect = "http://www.google.fr"
	redirect(controller: "redirectEngine", action: "flash")
	}</code></pre>

	Try it :<br/>
        <g:link action="doredirect">Redirect with a flash scope URL, and clean session</g:link>

	<br/><hr/>
	<h4>Using flash scope, keeping session</h4>

<pre><code class="html">&lt;g:link action="doredirectkeep">Redirect with a flash scope URL, and keep session alive&lt;/g:link></code>
<code class="java">def doredirectkeep(){
flash.redirect = "http://www.google.fr"
flash.keepSession = true
redirect(controller: "redirectEngine", action: "flash")
}</code></pre>

	Try it :<br/>
	<g:link action="doredirectkeep">Redirect with a flash scope URL, and keep session alive</g:link>

	<br/><hr/>
	<h4>Redirect configuration in Config.groovy</h4>

	<pre><code class="html">&lt;g:link controller="redirectEngine" action="map" params="[key : 'yahoo']">Redirect to an URL configured in Config.groovy, and clean session&lt;/g:link></code></pre>
	Try it :<br/>
	<g:link controller="redirectEngine" action="map" params="[key : 'yahoo']">Redirect to an URL configured in Config.groovy, and clean session</g:link>

	<br/><hr/>

	<pre><code class="html">&lt;g:link controller="redirectEngine" action="map" params="[key : 'yahoo_keepsession']">Redirect to an URL configured in Config.groovy, and keep session&lt;/g:link></code></pre>
	Try it :<br/>
	<g:link controller="redirectEngine" action="map" params="[key : 'yahoo_keepsession']">Redirect to an URL configured in Config.groovy, and keep session</g:link>

	<br/>

        <h1>Headers</h1>
		<p>Check for nocache headers in the table underneath</p><br/>

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

        <p>This Iframe displays a page of the application according to the <b>X-Frame-Options</b> Header</p>
		<blockquote><%= grailsApplication.mergedConfig.grails.plugin.exa.sec.headers.xframe %> is configured</blockquote>
        <iframe width="100%" src="<%= request.servletContext.contextPath %>"></iframe>
	</body>
</html>
