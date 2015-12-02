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
  &lt;exa:customHeader name="fullName" value="Fullname" />
  &lt;exa:customColumn name="fullName">
    &dollar;{it.firstName} &dollar;{it.lastName}
  &lt;/exa:customColumn>
&lt;/exa:datatable>
</code></pre>

    <exa:datatable id="table6" items="${persons}" exclude="age" hidden="firstName,lastName" add="fullName" reorder="fullName username">
        <exa:customHeader name="fullName" value="Fullname" />
        <exa:customColumn name="fullName">
            ${it.firstName} ${it.lastName}
        </exa:customColumn>
    </exa:datatable>
    <br />

    <h1>Full control</h1>
    <p>To complete</p>
</body>
</html>