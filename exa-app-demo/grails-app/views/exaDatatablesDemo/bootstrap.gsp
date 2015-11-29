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
        <exa:datatable id="table" items="${persons}" include="firstName, lastname, age" add="company" class="table-striped table-bordered">
            <exa:customColumn name="company">
                <b>${it?.company?.name}</b>
            </exa:customColumn>
        </exa:datatable>
    </div>
</body>
</html>