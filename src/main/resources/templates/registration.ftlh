<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Страница регистрации</title>
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
        .login__error-msg {
            color: red;
        }
    </style>
</head>
<body>
    <div class="ctx">
        <h1>Регистрация</h1>
        <form action="/registration" method="post">
            <p>Имя: <input class="field" type="text" name="name"></p>
            <p>Эл. почта: <input class="field" type="text" name="email"></p>
            <p>Телефон: <input class="field" type="text" name="phoneNumber"></p>
            <p>Пароль: <input class="field" type="password" name="password"></p>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Зарегистрироваться">
        </form>
        <div class="">
            <a href="/login">Уже зарегистрирован (войти)</a>
        </div>
        <#if errorMsg??>
            <h4 class="login__error-msg">${errorMsg}</h4>
        </#if>
    </div>
</body>
</html>