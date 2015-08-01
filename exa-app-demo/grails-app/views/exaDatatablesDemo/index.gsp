<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Exa Datatable demo</title>
</head>
<body>
    <h1>Datatables 1</h1>

    <exa:datatable id="table1" data="${data}" columns="arrondissement adresse nom_du_cafe">
        <thead>
        <tr>
            <th><g:message code="demo.arrondissement.label" /></th>
            <th><g:message code="demo.adresse.label" /></th>
            <th><g:message code="demo.nom_du_cafe.label" /></th>
        </tr>
        </thead>
    </exa:datatable>
</body>
</html>