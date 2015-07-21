<a name="Top"></a>

# EXA-SECURITY

* [Introduction](#intro)
* [Features](#features)
* [Changelog](#changelog)
* [Roadmap](#roadmap)
* [License](#license)

<a name="intro"></a>
##INTRODUCTION

This plugin provides features to enhance Grails 2.4.x security, as well as few other useful stuff.

The feature implementations mainly rely on [Top 10 OWASP](https://www.owasp.org/index.php/Top_10_2013-Top_10) recommandation.

> This plugin is NOT an alternative to [Grails Security recommandation](http://grails.github.io/grails-doc/2.4.4/guide/security.html) or [Spring Security](https://github.com/grails-plugins/grails-spring-security-core). Check these documentations to maximize your application security.

<p align="right"><a href="#Top">Top</a></p>

<a name="features"></a>
##FEATURES

> Please note that all features are **disabled** by default. Enable features by configuration according to your requirements.

Features available
* HTTP response **headers**
* Secured **XML** parser
* **Link** handling CSRF token
* Enhanced **scaffolding**
* Advanced **cookie** configuration
* **Redirect** engine

### HEADERS

#### NOCACHE

Add response headers to disable browser caching

To enable, declare in Config.groovy
```
grails.plugin.exa.sec.headers.nocache=true
```

#### SERVER

Set the "Server" response header, overriding the real server name

> Please note that the application server might override this value, depending on the vendor.

To enable, declare in Config.groovy
```
grails.plugin.exa.sec.headers.server="secret"
```

#### X-FRAME-OPTIONS

Set the "X-Frame-Options" response header

To enable, declare in Config.groovy with the desired value (see [https://www.owasp.org/index.php/Clickjacking_Defense_Cheat_Sheet](OWASP description) for more information
```
grails.plugin.exa.sec.headers.xframe="DENY"
```

### XML

Use `XMLFactory.createDocumentBuilder()` to instanciate a secured DocumentBuilder class - disabling entities evaluation.
Otherwise, you can use groovy.util.XmlParser that skip processing instructions

### TAGLIB

A taglib provides some security enhancement

#### <exas:link>

This tag add the Grails CSRF feature to link. Use it in link that do perform write operation on database (delete link for example).
This tag is a wrapper aroung <g:link>, then all <g:link> attributes are compatible with <exas:link>

```xml
<exas:link action="myaction">Click me !</exas:link>
```

```groovy
    def myaction(){
        withForm {
            redirect(action: "index")
        }.invalidToken {
            //TODO
        }
    }
```

### SCAFFOLDING

Use the `create-sec-controller` script to scaffold a basic controller with some security measures :
* withForm on save method
* Command pattern
* CSRF handling

> This script does not generate authorization restriction !

```
    usage: grails create-sec-controller package.Controller
```

Additionnally to this scaffolding, a base view can be configured to setup the controller view (index.gsp).

```
grails.plugin.exa.sec.scaff.baseView = "tpl/_base.gsp"
```

#### CSRF HANDLING

To handle CSRF error, a `csrfHandler` closure is injected in every Controller.
The default implementation throws a `CSRFTokenException`.

Set your own implementation by applying a custom `csrfHandler` to all controller. For example :

BootStrap.groovy :
```groovy
    for (controllerClass in grailsApplication.controllerClasses) {
        controllerClass.metaClass.csrfHandler = {controller, request, params ->
            log.warn("Oops, CSRF !")
            controller.redirect(url : "http://grails.org")
        }
    }
```

To check if a token is valid, the `CSRFHelper` provide a utility method.
Often useful when a form submit generates a file download, without resetting the screen.

```groovy
boolean CSRFHelper.isValidToken(def session, def params)
```

### COOKIES

#### SECURE FLAG
Set the cookie as secured, only transmitted through secured channel [https://www.owasp.org/index.php/SecureFlag]

> This feature is only enabled for web.xml configured with Servlet 3.0 API
> Check value of configuration `grails.servlet.version = "3.0"`

To enable, declare in Config.groovy
```
grails.plugin.exa.sec.cookie.secure.enabled=true
```

By default, cookie security is set only for production build. You can turn the restriction off with the following configuration :
```
grails.plugin.exa.sec.cookie.secure.productionOnly=false
```

> If development is done in HTTP (not HTTPS), turning feature on and productionOnly to false will create a new session for every request, breaking session feature...

#### NAME

To change cookie name, declare in Config.groovy
```
grails.plugin.exa.sec.cookie.name="HELLO_COOKIE"
```

### REDIRECTS

Offer a generic feature to redirect to an external URL without setting the target URL in GET parameters. A very simple feature :)

2 redirect strategies :
* Dynamic, based on a flash property
* Static, based on the application configuration

#### Dynamic

Usage :
```groovy
    flash.redirect = "http://www.google.fr"
    redirect(controller: "redirectEngine", action: "flash")
```

> Session is invalidated by default. Set flash.keepSession to skip invalidation

#### Static

Usage :

View
```xml
    <g:link controller="redirectEngine" action="map" params="[key : 'google']">Failure</g:link>
```

Config.groovy
```groovy
grails.plugin.exa.sec.redirects.map = [google: [url : 'http://www.yahoo.Fr', keepSession : false]]
or
grails.plugin.exa.sec.redirects.map = ['http://www.yahoo.Fr']
```

> Session is invalidated by default. Set flash.keepSession to true to bypass invalidation

<p align="right"><a href="#Top">Top</a></p>
<a name="changelog"></a>
##CHANGE LOG

* v1.0.0 : Release

<p align="right"><a href="#Top">Top</a></p>
<a name="roadmap"></a>
##ROADMAP
---------------

Tell us !

<p align="right"><a href="#Top">Top</a></p>
<a name="License"></a>
##LICENSE
---------------

Copyright 2015 EXANPE <exanpe@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.