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

This plugin provides easy integration with DataTables.net (Table plug-in for jQuery).

A live demo of the taglib is available [here](http://grails-exanpe.rhcloud.com/exaDatatablesDemo/index).

<p align="right"><a href="#Top">Top</a></p>
<a name="features"></a>
##FEATURES

This plugin is pre-configured to ease integration with DataTables.
So, most of the important features are enabled by default like:
* Instant search,
* Pagination,
* Column ordering,
* ...

Of course, this plugin provides a way to use all of existing features of DataTables.net.

> The full documentation of the DataTables jQuery plugin is available here: [DataTables.net](https://www.datatables.net/).

<p align="right"><a href="#Top">Top</a></p>
<a name="configuration"></a>
##Plugin configuration

### Grails 2.4.x

The plugin depends on both jQuery and asset-pipeline, so include them as dependencies in your `BuildConfig.groovy` file :

```groovy
    plugins {
        compile ":asset-pipeline:1.9.9"
        runtime ":jquery:1.11.1"
        compile ":exa-datatables:1.0.2"
    }
```

### Grails 3.1.8 +

The plugin is now available for Grails 3, just include the following dependency in your `build.gradle` file:

```
dependencies {
    // Exa Datatables plugin
    runtime "fr.exanpe.grails:exa-datatables:2.0.0"
}
```

An example Grails 3 project with exa-datatables taglib integration is available here: [Grails 3 sample project](https://github.com/lguerin/exa-datatables-grails3-sample).
Just clone the project, set Grails 3.1.8 version in your favorite shell and execute: `grails run-app` to see the plugin in action.

### Assets conf

Then, thanks to the Asset Pipeline plugin, you just have to reference the following files to load the static
resources needed by DataTables:

Javascript `grails-app/assets/javascripts/application.js`:
```javascript
//= require jquery
//= require exa-datatables
```

Stylesheet `grails-app/assets/stylesheets/application.css`:
```
/*
*= require exa-datatables
*= require main
*/
```

In case you would integrate this plugin with your Twitter Bootstrap 3 look and feel, you must use the below configuration instead:

Javascript `grails-app/assets/javascripts/application.js`:
```javascript
//= require jquery
//= require bootstrap
//= require exa-datatables-bootstrap3
```

Stylesheet `grails-app/assets/stylesheets/application.css`:
```
/*
*= require bootstrap
*= require exa-datatables-bootstrap3
*= require main
*/
```

<p align="right"><a href="#Top">Top</a></p>
<a name="taglib"></a>
##Taglib

All the tags must be used through the namespace `exa`.

### **datatable** Tag

```gsp
 <exa:datatable id="table1" items="${items}" />
```

Tag attributes:

| Attribute | Description                                                                                                                                                                                               | Default value |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------:|
| id        | **Required** - Unique client-side ID of the datatable.                                                                                                                                                    |               |
| items     | **Required** - The collection of objects to iterate over.                                                                                                                                                 |               |
| include   | List of property names (not case-sensitive) to be retained from each item.                                                                                                                                |               |
| exclude   | List of property names (not case-sensitive) to be removed from each item.                                                                                                                                 |               |
| add       | Extra columns to display for each item. Cells for added columns will be blank by default, unless you provide a custom **customColumn** nested tag for each of them.                                       |               |
| hidden    | List of property names (not case-sensitive) to include in the data model but not to display directly. Useful when you want to have access to a given property inside a **customColumn** nested tag.       |               |
| reorder   | List of property names (not case-sensitive) indicating the order in which the columns should be presented. Could be the beginning or the full list of columns.                                            |               |
| class     | CSS classes to apply to the table.                                                                                                                                                                        |               |
| filtering | Enable or disable table filtering.                                                                                                                                                                        |      true     |
| ordering  | Enable or disable column ordering.                                                                                                                                                                        |      true     |
| paging    | Enable or disable paging.                                                                                                                                                                                 |      true     |
| infos     | Enable or disable table information display field.                                                                                                                                                        |      true     |
| stateSave | Enable or disable saving the state of a table (paging position, ordering state, etc) so that is can be restored when the user reloads a page.                                                             |      true     |
| auto      | **EXPERIMENTAL** Enable or disable auto rendering of the datatable.  Used to take control over Datatable settings or customization.  If false, you have to call render(options) yourself on client-side.  |      true     |

### **customHeader** nesteg tag

```gsp
 <exa:datatable id="table1" items="${persons}">
    <exa:customHeader name="fullName" value="${message(code: 'demo.fullName.label')}" />
 </exa:datatable>
```

Tag attributes:

| Attribute | Description                                                                                                                                                                                               | Default value |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------:|
| name      | **Required** - The name of the property to customize.                                                                                                                                                     |               |
| value     | **Required** - The label value to set (could be a literal or a i18n message).                                                                                                                             |               |

### **customColumn** nesteg tag

```gsp
 <exa:datatable id="table1" items="${persons}">
   <exa:customColumn name="fullName">
     ${it.firstName} ${it.lastName}
   </exa:customColumn>
 </exa:datatable>
```

Tag attribute:

| Attribute | Description                                                                                                                                                                                               | Default value |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------:|
| name      | **Required** - The name of the property column to customize.                                                                                                                                              |               |

To notice: you have access to the current iterator value through `it` variable within the body of the tag.

<p align="right"><a href="#Top">Top</a></p>
<a name="usage"></a>
##USAGE

#### Simple case

In the following sample, we show the simplest way to use `datatable` tag.
DataTable is displayed with default settings: filtering, ordering, paging and table infos.

`DemoController.groovy`
```groovy
    def index() {
        List<Person> persons = ...
        [items: persons]
    }

    class Person {
        String firstName
        String lastName
        String email
        String username
        Person.Sex sex
        Integer age
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

    <exa:datatable id="table1" items="${persons}" />

</body>
</html>
```

### Customize the rendering

Sometimes, you need more than just the data to display, for example, include or exclude only some columns, add extra columns,
or simply customize a column header label.
Below, an example and some hints to achieve such customization:

`index.gsp`
```gsp
<exa:datatable id="table2" items="${persons}" exclude="age" hidden="firstName,lastName,sex" add="fullName, gender" reorder="fullName username">
    <exa:customHeader name="fullName" value="My Full Name" />
    <exa:customColumn name="fullName">
        ${it.firstName} ${it.lastName}
    </exa:customColumn>
    <exa:customColumn name="gender">
        <button name='alert' class='btn-alert'>gender</button>
    </exa:customColumn>
</exa:datatable>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        var datatable = Exa.Datatable.getDatatable('table2');
        $('#table tbody').on('click', '.btn-alert', function () {
            var data = datatable.getInstance().row($(this).parents('tr')).data();
            alert("Gender: " + data['sex']);
        });
    });
</script>
```

Some tips about the code above:
* Collect all the *Person* class properties, minus age property (`exclude` parameter).
* Do not display directly *firstName*, *lastName*, and *sex* properties (`hidden` parameter). Although not directly displayed, these properties fill the data model of the datatable and can thus be used within the `customColumn` nested tag.
* Add extra columns *fullName* and *gender* (`add` parameter).
* Change the default order of columns (`reorder` parameter).
* Last, but not least, look at the usage of `customHeader` and `customColumn` nested tags that allow to change the label and the content of some columns.

Finally, notice the usage of helper function `Exa.Datatable.getDatatable` to get the JS instance of the datatable linked to the client ID 'table2' and the usage of `getInstance()` that allow to get the real DataTables.net JS component, wrapped by our taglib.

Basically, this function gives you a full access to all the native and powerfull features of DataTables jQuery plug-in.

<p align="right"><a href="#Top">Top</a></p>
<a name="changelog"></a>
##CHANGE LOG
* v2.0.0 :
** Same features as v1.0.2 but full compatible and dedicated to Grails 3.1.8+

* v1.0.2 :
** Add stateSave parameter : Enable or disable saving the state of a table (paging position, ordering state, etc) so that is can be restored when the user reloads a page.
** Accepts JSON format as input data
** Upgrade to DataTables 1.10.12 version

* v1.0.1 :
** Merged https://github.com/exanpe/exa-grails-plugins/pull/3[#3]: cleanup

* v1.0.0 : Initial Release

<p align="right"><a href="#Top">Top</a></p>
<a name="roadmap"></a>
##ROADMAP

Better integration with ajax data loading, and some other features of DataTables extensions and plugins.

If you need another features, please fill an issue on Github!

<p align="right"><a href="#Top">Top</a></p>
<a name="License"></a>
##LICENSE

Copyright 2015 EXANPE <exanpe@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
