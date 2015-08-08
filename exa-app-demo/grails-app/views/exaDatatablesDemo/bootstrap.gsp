<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="bootstrap" />
    <title>Exa Datatable demo</title>
</head>
<body>
    <h1>Datatables with Twitter Bootstrap 3</h1>

    <div class="dataTables_wrapper">
        <exa:datatable id="table" data="${data}" columns="arrondissement adresse nom_du_cafe" class="table-striped table-bordered">
            <thead>
            <tr>
                <th><g:message code="demo.arrondissement.label" /></th>
                <th><g:message code="demo.adresse.label" /></th>
                <th><g:message code="demo.nom_du_cafe.label" /></th>
            </tr>
            </thead>
        </exa:datatable>
    </div>
</body>
</html>