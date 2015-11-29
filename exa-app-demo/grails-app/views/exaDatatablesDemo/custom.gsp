<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Exa Datatable demo</title>
</head>
<body>
    <h1>Custom Datatable</h1>

    <exa:datatable id="table" items="${persons}" include="firstName, lastname, age, sex" hidden="sex" add="gender">
        <exa:customColumn name="gender">
            <button name='alert' class='btn-alert'>gender</button>
        </exa:customColumn>
    </exa:datatable>

    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            var datatable = Exa.Datatable.getDatatable('table');
            $('#table tbody').on('click', '.btn-alert', function () {
                var data = datatable.getInstance().row($(this).parents('tr')).data();
                alert("Gender: " + data['sex']);
            });
        });
    </script>
</body>
</html>