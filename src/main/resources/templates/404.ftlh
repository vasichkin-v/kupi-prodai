<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Страница авторизации</title>
    <style>
        .ctx {
            max-width: 1080px;
            margin-left: auto;
            margin-right: auto;
        }
        form {
            max-width: 480px;
            padding: 16px;
            margin: 48px 0;
            background: #f3eeee;
        }
        .field {
            display: block;
            height: 32px;
            margin-top: 4px;
            width: 100%;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="ctx">
        <h1>404</h1>
        <#list .data_model?keys as key>
            <div>${key}</div>
        </#list>
<#--        <#if errorMsg??>-->
<#--            <p>Неверное имя пользователя и пароль (перем errorMsg)</p>-->
<#--        </#if>-->
<#--        <#if param.error??>-->
<#--            <p>Неверное имя пользователя и пароль.</p>-->
<#--        </#if>-->
<#--        <#if param.logout??>-->
<#--            <p>Вы вышли из системы.</p>-->
<#--        </#if>-->
    </div>
</body>
</html>