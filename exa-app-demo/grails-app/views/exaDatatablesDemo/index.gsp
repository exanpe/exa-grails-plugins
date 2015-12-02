<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>exa-datatables Demo</title>
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
        <p>You can choose to <code>include</code> or <code>exclude</code> only some columns. Column names are not case-sensitive and you can use " " or "," as separator field:</p>
    </blockquote>

    <pre><code class="html">&lt;exa:datatable id="table3" items="&dollar;{persons}" exclude="age" /></code></pre>

    <exa:datatable id="table3" items="${persons}" exclude="age" />
    <br />

    <pre><code class="html">&lt;exa:datatable id="table4" items="&dollar;{persons}" include="firstName, lastname, age" /></code></pre>

    <exa:datatable id="table4" items="${persons}" include="firstName, lastname, age" />
    <br />

    <h2>Datatables 4</h2>
    <exa:datatable id="table5" items="${persons}" exclude="age" hidden="firstName,lastName" add="fullName" reorder="fullName username">
        <exa:customHeader name="fullName" value="Fullname" />
        <exa:customColumn name="fullName">
            ${it.firstName} ${it.lastName}
        </exa:customColumn>
    </exa:datatable>
    <br />
</body>
</html>