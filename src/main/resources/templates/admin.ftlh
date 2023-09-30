<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Страница авторизации</title>
    <style>
        .admin-users form {
            margin: 0;
            padding: 0;
        }
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
        .admin-users table {
            border-spacing: 0;
            margin: 0;
            width: 100%;
        }
        tr th, tr td {
            padding: 5px;
        }
        .user-status-banned {
            color: red;
        }
    </style>
</head>
<body>
    <div class="ctx">
        <h1>Панель администратора</h1>
        <div class="admin-users">
            <table border="1" align="center">
                <tr>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Roles</th>
                    <th>Date of created</th>
                    <th>Бан</th>
                    <th>Actions</th>
                    <th>Ссылки</th> <#-- todo Добавить редактирование пользователся -->
                </tr>
                <#list users as user>
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.phoneNumber}</td>
                        <td><#list user.roles as role> ${role}<#sep>, </#list></td>
                        <td>${user.dateOfCreated}</td>
                        <td><#if !user.active><div class="user-status-banned">true</div><#else>false</#if></td>
                        <td>
                            <form action="/admin/user/ban/${user.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="submit" value="Бан">
                            </form>
                        </td>
                        <td>
                            <div><a href="/user/${user.id}">Детальная стр</a></div>
                        </td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
</body>
</html>