<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Exa Datatable demo</title>
</head>
<body>
    <h1>Datatables 1</h1>
    <exa:datatable id="table1" items="${persons}" />
    <br />

    <h1>Datatables 2</h1>
    <exa:datatable id="table2" items="${persons}" exclude="age" />
    <br />

    <h1>Datatables 3</h1>
    <exa:datatable id="table3" items="${persons}" include="firstName, lastname, age" />
    <br />

    <h1>Datatables 4</h1>
    <exa:datatable id="table4" items="${persons}" exclude="age" hidden="firstName,lastName" add="fullName" reorder="fullName username">
        <exa:customHeader name="fullName" value="Fullname" />
        <exa:customColumn name="fullName">
            ${it.firstName} ${it.lastName}
        </exa:customColumn>
    </exa:datatable>
</body>
</html>