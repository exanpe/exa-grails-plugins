<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
        <h1>Taglib</h1>
        <h2>exas:link</h2>
		<exas:link action="link">exa:link Success</exas:link>
        <exas:link action="link" params="['p':'t']">exa:link Success - 2nd token</exas:link>
        <g:link action="link">Standard link - without token (redirect google)</g:link>

        <h1>Redirect</h1>

        ${session.id}<br/>

        <g:link action="redirect">Redirect flash - clean session</g:link>
        <g:link action="redirectkeep">Redirect flash - keep session</g:link><br/>

        <g:link controller="redirectEngine" action="map" params="[key : 'yahoo']">Redirect Map - clean session</g:link>
        <g:link controller="redirectEngine" action="map" params="[key : 'yahoo_keepsession']">Redirect Map - keep session</g:link>

        <h1>Headers</h1>
        <table>
        <%
            for(String i : new HashSet<String>(response.headerNames)){
        %>
            <tr>
                <td><%= i %></td>
                <td><%= response.getHeaders(i).join(",") %></td>
            </tr>
        <%
            }
        %>
        </table>

        Test de rendu iFrame : Affichage attendu :: <%= grailsApplication.mergedConfig.grails.plugin.exa.sec.headers.xframe %>
        <iframe width="100%" src="<%= request.servletContext.contextPath %>"></iframe>
	</body>
</html>
