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
    </style>
</head>
<body>
    <div class="ctx">
        <h1>Страница пользователя</h1>
        <h2>Пользователь: <#if user??>${user.name}<#else>неавторизован!</#if></h2>
        <#if user??>
            <div>Тел.: ${user.phoneNumber}</div>
            <div>Email: ${user.email}</div>
            <div>Роль(и): <#list user.roles as role><span>${role}<#sep>, </span></#list></div>
            <div>Дата регистрации: ${user.toFormatDate()}</div>
        </#if>
        <h3>Объявления пользователся:</h3>
        <#if products??>
            <table border="1px">
                <#list products as product>
                    <tr>
                        <td>${product.title}</td>
                        <td>${product.price} ₽</td>
                        <td>${product.dateOfCreated}</td>
                        <td><a href="/product/view/${product.id}">Подробнее</a></td>
                    </tr>
                </#list>
            </table>
            <#else>
            <div class="">Список пуст. Перейдите в каталог и <a href="/products">создайте свое первое объявление о продаже товара</a></div>
        </#if>

    </div>
</body>
</html>