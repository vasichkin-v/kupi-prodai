<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Products</title>
    <style>
        .ctx {
            max-width: 1080px;
            margin-left: auto;
            margin-right: auto;
        }
        .product__item {
            margin: 12px 0;
            max-width: 480px;
            border: 1px solid #9a9a9a;
            padding: 16px;
        }
        .product__item + .product__item {
            margin-top: 32px;
        }
        .product__item-text {
            color: #494949;
        }
        form {
            max-width: 480px;
            padding: 16px;
            margin: 48px 0;
            background: #f3eeee;
        }
        [type] {
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
        <h1>Правим продукт</h1>
        <form action="/product/update" method="post">
            <input type="hidden" name="id" value="${product.id}">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <p>Название: <input type="text" name="title" value="${product.title}"></p>
            <p>Описание: <input type="text" name="description" value="${product.description}"></p>
            <p>Цена: <input type="text" name="price" value="#{product.price}"></p>
            <p>Город: <input type="text" name="city" value="${product.city}"></p>
            <input type="submit" value="Сохранить">
        </form>
    </div>
</body>
</html>