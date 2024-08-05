<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>AccAppCommon</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
              rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">
        <link href="css/custom.css" rel="stylesheet">
    </head>
    <body>
    <#nested>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>
<#macro navigationMenu>
    <div class="navigation-box">
        <div class="navigation-link">
            <a href="http://192.168.0.101:8080/" style="color: #fcf6f6">Главная</a>
        </div>
        <div class="navigation-link">
            <a href="http://192.168.0.101:8080/employee" style="color: #fcf6f6">Сотрудники</a>
        </div>
        <div class="navigation-link">
            <a href="http://192.168.0.101:8080/itstaff" style="color: #fcf6f6">Программисты</a>
        </div>
        <div class="navigation-link">
            <a href="http://192.168.0.101:8080/workarea" style="color: #fcf6f6">Филиалы</a>
        </div>
        <div class="navigation-link">
            <a href="http://192.168.0.101:8080/room" style="color: #fcf6f6">Кабинеты</a>
        </div>
        <div class="navigation-link">
            <a href="http://192.168.0.101:8080/events" style="color: #fcf6f6">События</a>
        </div>
        <div class="navigation-link">
            <a href="http://192.168.0.101:8080/devices" style="color: #fcf6f6">Оборудование</a>
        </div>
        <div class="navigation-link">
            <a href="http://192.168.0.101:8080/documents" style="color: #fcf6f6">Документы</a>
        </div>
    </div>

</#macro>