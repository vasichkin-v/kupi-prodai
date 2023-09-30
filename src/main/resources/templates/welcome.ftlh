<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Index</title>
    <style>
        .ctx {
            max-width: 1080px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
    <div class="ctx">
        <#if user.name??>
            <h1>${user.name}, добро пожаловать в "Купи-Продай".</h1>
            <div><a href="/user/${user.id}">Пользователь</a></div>
        <#else>
            <h1>Добро пожаловать в "Купи-Продай".</h1>
        </#if>
        <div><a href="/products">Список объявлений</a></div>
        <div><a href="/page">Page</a></div>
        <#if user.isAdmin()><div><a href="/admin/users">Админпанель</a></div></#if>
        <#if user.name??>
            <div><a href="/logout">Выход</a></div>
        <#else>
            <div><a href="/login">Вход</a></div>
            <div><a href="/registration">Зарегистрироваться</a></div>
        </#if>
    </div>
</body>
</html>