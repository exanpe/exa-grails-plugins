<a name="Top"></a>

# EXA-DATATABLES

* [Introduction](#intro)
* [Features](#features)
* [Plugin configuration](#configuration)
* [Usage](#usage)
* [Changelog](#changelog)
* [Roadmap](#roadmap)
* [License](#license)

<a name="intro"></a>
##INTRODUCTION

This plugin provides integration with DataTables (Table plug-in for jQuery).

<p align="right"><a href="#Top">Top</a></p>
<a name="features"></a>
##FEATURES

This plugin is pre-configured to ease integration with DataTables.
So, most of the important features are enabled by default like:
* Instant search,
* Pagination,
* Column ordering.

Of course, this plugin provides a way to customize all of existing features of DataTables.net.

> The full DataTables jQuery plugin documentation is available here: [DataTables.net](https://www.datatables.net/).

<p align="right"><a href="#Top">Top</a></p>
<a name="configuration"></a>
##Plugin configuration

The plugin depends on both jQuery and asset-pipeline, so include them as dependencies in your BuildConfig.groovy file :

```
    plugins {
        compile ":asset-pipeline:1.9.9"
        runtime ":jquery:1.11.1"
        compile ":exa-datatables:X.Y.Z"
    }
```

Then, thanks to the Asset Pipeline plugin, you just have to reference the following resources to load the static
resources needed by DataTables :

Javascript `grails-app/assets/javascripts/application.js`:
```javascript
//= require jquery
//= require exa-datatables
...
```

Stylesheet `grails-app/assets/stylesheets/application.css`:
```
*= require exa-datatables
*= *= require main
...
```

<p align="right"><a href="#Top">Top</a></p>
<a name="usage"></a>
##USAGE


<p align="right"><a href="#Top">Top</a></p>
<a name="changelog"></a>
##CHANGE LOG

* v1.0.0 : Initial Release

<p align="right"><a href="#Top">Top</a></p>
<a name="roadmap"></a>
##ROADMAP

Tell us !

<p align="right"><a href="#Top">Top</a></p>
<a name="License"></a>
##LICENSE

Copyright 2015 EXANPE <exanpe@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
