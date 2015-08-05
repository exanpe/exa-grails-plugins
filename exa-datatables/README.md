<a name="Top"></a>

# EXA-DATATABLES

* [Introduction](#intro)
* [Features](#features)
* [Plugin configuration](#configuration)
* [Taglib](#taglib)
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

```groovy
    plugins {
        compile ":asset-pipeline:1.9.9"
        runtime ":jquery:1.11.1"
        compile ":exa-datatables:X.Y.Z"
    }
```

Then, thanks to the Asset Pipeline plugin, you just have to reference the following files to load the static
resources needed by DataTables :

Javascript `grails-app/assets/javascripts/application.js`:
```javascript
//= require jquery
//= require exa-datatables
...
```

Stylesheet `grails-app/assets/stylesheets/application.css`:
```
/*
...
*= require exa-datatables
*= require main
...
*/
```

<p align="right"><a href="#Top">Top</a></p>
<a name="taglib"></a>
##Taglib

The tag must be used through the namespace `exa` as follow:

```gsp
 <exa:datatable id="table1" data="..." columns="...">
     <thead>
     <tr>
        ...
     </tr>
     </thead>
 </exa:datatable>
```

Tag attributes:

| Attribute | Description                                                                                                                                                                             | Default value |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------:|
| id        | **Required** - Unique client-side ID of the datatable                                                                                                                                   |               |
| data      | **Required** - JSON datas to display                                                                                                                                                    |               |
| columns   | Name of data columns to display                                                                                                                                                         |               |
| class     | CSS classes to apply to the table                                                                                                                                                       |               |
| filtering | Enable or disable table filtering                                                                                                                                                       |      true     |
| ordering  | Enable or disable column ordering                                                                                                                                                       |      true     |
| paging    | Enable or disable paging                                                                                                                                                                |      true     |
| infos     | Enable or disable table information display field                                                                                                                                       |      true     |
| auto      | Enable or disable auto rendering of the datatable.  Used to take control over Datatable settings or customization.  If false, you have to call render(options) yourself on client-side. |      true     |

<p align="right"><a href="#Top">Top</a></p>
<a name="usage"></a>
##USAGE

#### Simple case

In the following case, we show the simplest way to use Datatable tag.

Only columns col1, col2 and col3 from data, provided as JSON by a controller, are used.

DataTable is displayed with default settings: filtering, ordering, paging and table infos.

`DemoController.groovy`
```groovy
    def index() {
        [data: getMyData() as JSON]
    }
```

`index.gsp`
```gsp
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Simple case</title>
</head>
<body>

    <exa:datatable id="table1" data="${data}" columns="col1 col2 col3">
        <thead>
        <tr>
            <th><g:message code="col1.label" default="Col 1" /></th>
            <th><g:message code="col2.label" default="Col 2" /></th>
            <th><g:message code="col3.label" default="Col 3" /></th>
        </tr>
        </thead>
    </exa:datatable>

</body>
</html>
```

### Custom table

Sometimes, you need more than simple the data to display, for example, an extra column to display custom actions.
Below, we give you some hints to achieve a such customization:

`DemoController.groovy`
```groovy
    def custom() {
        [data: getMyData() as JSON]
    }
```

`custom.gsp`
```gsp
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Custom table</title>
</head>
<body>

    <exa:datatable id="table2" data="${data}" columns="arrondissement adresse nom_du_cafe" auto="false">
        <thead>
        <tr>
            <th><g:message code="demo.arrondissement.label" /></th>
            <th><g:message code="demo.adresse.label" /></th>
            <th><g:message code="demo.nom_du_cafe.label" /></th>
            <th><g:message code="demo.actions.label" default="Actions" /></th>
        </tr>
        </thead>
    </exa:datatable>

    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            var datatable = Exa.Datatable.getDatatable('table2');
            var optionColumn = { "data": null,
                "defaultContent": "<button name='alert' class='btn-alert'>Price</button>"
            };
            datatable.addColumn(optionColumn);
            datatable.render();

            $('#table2 tbody').on('click', '.btn-alert', function () {
                var data = datatable.getInstance().row($(this).parents('tr')).data();
                alert("Coffee price: " + data['prix_comptoir']);
            });
        });
    </script>

</body>
</html>
```

Some tips about the code above :
* Disable the auto-rendering with attribute `auto="false"`
* Add an extra row into the thead of the table for the 'Action' column
* Use the helper function `Exa.Datatable.getDatatable` to get the JS instance of the datatable linked to the client ID 'table2'
* Call the `render()` function explicitly
* Notice the usage `getInstance()` that allow to get the real DataTables.net component, wrapped by our taglib.  Basically, this function gives you acces to the power of all the DataTables native features.

<p align="right"><a href="#Top">Top</a></p>
<a name="changelog"></a>
##CHANGE LOG

* v1.0.0 : Initial Release

<p align="right"><a href="#Top">Top</a></p>
<a name="roadmap"></a>
##ROADMAP

Better integration with ajax data loading, and some of DataTables extensions and plugins: Internationalisation, Scroller, ...
If you need another features, please fill an issue on Github!

<p align="right"><a href="#Top">Top</a></p>
<a name="License"></a>
##LICENSE

Copyright 2015 EXANPE <exanpe@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
