<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <asset:stylesheet src="highlight/styles/default.css" />
    <asset:stylesheet src="highlight/styles/mono-blue.css" />
    <asset:stylesheet src="theme.less"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="highlight/highlight.pack.js" />
    <g:layoutHead/>
    %{--<ga:trackPageview />--}%
</head>
<body>

<g:render template="/tpl/menu/header" />

<div class="container-fluid">

    <!-- Header title -->
    <div class="section-header">
        <div class="row">
            <g:if test="${controllerName}">
                <div class="page-header">
                    <h1><g:layoutTitle default="My Title"/></h1>
                </div>
            </g:if>
        </div>
    </div>

    <div class="main">

        <div class="row">
            <!-- Main content -->
            <div class="content col-lg-12">
                <g:if test='${flash.message}'>
                    <div class='alert alert-${flash.status ?: "info"}'>
                        <g:message code="${flash.message}" />
                    </div>
                </g:if>
                <g:layoutBody/>
            </div>
        </div>

    </div> <!-- /.main -->

<g:render template="/tpl/menu/footer" />

</div> <!-- /.container-fluid -->

<g:javascript>
    hljs.initHighlighting();
</g:javascript>
</body>
</html>
