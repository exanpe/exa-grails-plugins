<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Exa Datatable demo</title>
</head>
<body>
    <h1>Custom Datatable</h1>

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
                "defaultContent": "<button name='alert' class='btn-alert'>alert</button>"
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