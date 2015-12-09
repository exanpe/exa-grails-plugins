<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>exa-datatable Demo</title>
</head>
<body>

    <p>This page shows how to use <code>exa-datatable</code> tag and the many ways to customize the rendering of a datatable.</p>

    <h1>Basic settings</h1>
    <p>Suppose we have a collection of <code>Person</code> to display. Each Person instance has the following attributes: <br /><p>
        <pre><code class="java">class Person {
    String firstName
    String lastName
    String email
    String username
    Person.Sex sex
    Integer age
}</code></pre>

    <blockquote>
        <p>The easiest way is to only set the <code>items</code> parameter:</p>
    </blockquote>

    <pre><code class="html">&lt;exa:datatable id="table1" items="&dollar;{persons}" /></code></pre>

    <exa:datatable id="table1" items="${persons}" />
    <br />

    <blockquote>
        <p>You can also enable or disable some native Datatables.js settings, like <code>filtering</code>, <code>paging</code>, <code>ordering</code> or <code>infos</code>...</p>
    </blockquote>

    <pre><code class="html">&lt;exa:datatable id="table2" items="&dollar;{persons}" filtering="true" infos="false" paging="true" ordering="false" /></code></pre>

    <exa:datatable id="table2" items="${persons}" filtering="true" infos="false" paging="true" ordering="false" />
    <br />

    <blockquote>
        <p>You can choose to <code>include</code> or <code>exclude</code> only some columns: column names are not case-sensitive ; separator field could be ' ' or ','.</p>
    </blockquote>

    <pre><code class="html">&lt;exa:datatable id="table3" items="&dollar;{persons}" exclude="age" /></code></pre>

    <exa:datatable id="table3" items="${persons}" exclude="age" />
    <br />

    <pre><code class="html">&lt;exa:datatable id="table4" items="&dollar;{persons}" include="firstName, lastname, age" /></code></pre>

    <exa:datatable id="table4" items="${persons}" include="firstName, lastname, age" />
    <br />

    <h1>Advanced settings</h1>

    <p>Suppose now that we have to display a more complex data structure with some object as part as <code>Person</code> properties: <br /><p>
    <pre><code class="java">class Person {
    String firstName
    String lastName
    String email
    String username
    Person.Sex sex
    Integer age
    Company company
    }

class Company {
    String name
    String domain
}
</code></pre>

    <blockquote>
        <p>Rendering of <code>company</code> column with <code>customColumn</code> nested tag: </p>
    </blockquote>

    <pre><code class="html">&lt;exa:datatable id="table5" items="&dollar;{persons}" include="firstName lastname age company">
  &lt;exa:customColumn name="company">
    &lt;b>&dollar;{it?.company?.name}&lt;/b>
  &lt;/exa:customColumn>
&lt;/exa:datatable>
</code></pre>

    <exa:datatable id="table5" items="${persons2}" include="firstName lastname age company">
        <exa:customColumn name="company">
            <b>${it?.company?.name}</b>
        </exa:customColumn>
    </exa:datatable>

    <blockquote>
        <p>You often need to <code>add</code> extra column, sometimes with properties that you don't want to display elsewhere (<code>hidden</code> parameter) and also <code>reorder</code> the beginning or the entire list of properties.<br />
        Look at the nested tag <code>customHeader</code> too, that allow to change the label of the column header.
        </p>
    </blockquote>

    <pre><code class="html">&lt;exa:datatable id="table6" items="&dollar;{persons}" exclude="age company" hidden="firstName,lastName" add="fullName" reorder="fullName username">
  &lt;exa:customHeader name="fullName" value="&dollar;{message(code: 'demo.fullName.label')}" />
  &lt;exa:customColumn name="fullName">
    &dollar;{it.firstName} &dollar;{it.lastName}
  &lt;/exa:customColumn>
&lt;/exa:datatable>
</code></pre>

    <exa:datatable id="table6" items="${persons}" exclude="age" hidden="firstName,lastName" add="fullName" reorder="fullName username">
        <exa:customHeader name="fullName" value="${message(code: 'demo.fullName.label')}" />
        <exa:customColumn name="fullName">
            ${it.firstName} ${it.lastName}
        </exa:customColumn>
    </exa:datatable>
    <br />

    <h1>Full control</h1>

    <p>Sometimes, you'll want to get a full control over the Datatables.js instance in order use some features you can't have access server-side or simply achieve some DOM manipulations client-side.<br />
       The two following examples shows you how to do that.</p>

    <blockquote>
        <p>Notice the usage of the helper function <code>Exa.Datatable.getDatatable</code> to get the JS instance of the datatable linked to the client ID 'table7' and the usage of <code>getInstance()</code> that allow to get the real JS DataTables.net component, wrapped by our taglib.<br />
        Basically, this function gives you a full access to all the native and powerfull features of DataTables jQuery plug-in.</p>
    </blockquote>

<pre><code class="html">&lt;exa:datatable id="table7" items="&dollar;{persons}" include="firstname lastname age sex" hidden="sex" add="gender">
  &lt;exa:customColumn name="gender">
    &lt;button name='alert' class='btn-alert'>gender</button>
  &lt;/exa:customColumn>
&lt;/exa:datatable>

&lt;script type="text/javascript" charset="utf-8">
  &dollar;(document).ready(function() {
    var datatable = Exa.Datatable.getDatatable('table7');
    &dollar;('#table7 tbody').on('click', '.btn-alert', function () {
      var data = datatable.getInstance().row($(this).parents('tr')).data();
      alert("Gender: " + data['sex']);
  });
});
&lt;/script>

</code></pre>

<exa:datatable id="table7" items="${persons2}" include="firstname lastname age sex" hidden="sex" add="gender">
    <exa:customColumn name="gender">
        <button name='alert' class='btn-alert'>gender</button>
    </exa:customColumn>
</exa:datatable>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        var datatable = Exa.Datatable.getDatatable('table7');
        $('#table7 tbody').on('click', '.btn-alert', function () {
            var data = datatable.getInstance().row($(this).parents('tr')).data();
            alert("Gender: " + data['sex']);
        });
    });
</script>

<blockquote>
    <p>Finally, this last example show the usage of <code>auto</code> parameter that allow to enable or disable auto rendering of the datatable by the taglib.<br />
    When set to false, this give you a way to take the control (client-side) over all the Datatables.js native settings. In this case, you have to call render(options) yourself on client-side.<br />
    In the simple example below, we play with the <code>rowCallback</code> feature before rendering the component:
    </p>
</blockquote>

<pre><code class="html">&lt;exa:datatable id="table8" items="&dollar;{persons}" include="firstname lastname age sex" auto="false"/>

&lt;script type="text/javascript" charset="utf-8">
    &dollar;(document).ready(function() {
            var datatable = Exa.Datatable.getDatatable('table8');
            var options = {
                "rowCallback": function(row, data, index) {
                    if (data['sex'] === "MALE") {
                    &dollar;(row).addClass('blue');
                    }
                    else {
                    &dollar;(row).addClass('pink');
                    }
                }
            }
            datatable.render(options);
        });
&lt;/script>
</code></pre>

<exa:datatable id="table8" items="${persons2}" include="firstname lastname age sex" auto="false"/>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        var datatable = Exa.Datatable.getDatatable('table8');
        var options = {
            "rowCallback": function(row, data, index) {
                if (data['sex'] === "MALE") {
                    $(row).addClass('blue');
                }
                else {
                    $(row).addClass('pink');
                }
            }
        }
        datatable.render(options);
    });
</script>

</body>
</html>