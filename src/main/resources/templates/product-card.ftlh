<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=yes, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Product card</title>
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
        .product__img {
            display: block;
            margin-top: 4px;
            margin-bottom: 8px;
            max-width: 100%;
        }
    </style>
</head>
<body>
    <div class="ctx">
        <h1>Краточка продукта</h1>
        <div class="product">
            <div class="product__item">
                <b>${product.title}</b>
                <p class="product__item-text">${product.description}</p>
                <p class="product__item-text">Цена: ${product.price} ₽</p>
                <p class="product__item-text">Город: ${product.city}</p>
                <p class="product__item-text">Автор: ${product.user.name}</p>
                <p class="product__item-text">Телефон: ${product.user.phoneNumber}</p>
                <#list product.images as img>
                    <img class="product__img" src="/images/${img.getId()}" alt="${img.getOriginalFileName()}">
                </#list>
                <div><a href="/product/remove/${product.id}" class="">Удалить</a></div>
                <div><a href="/product/edit/${product.id}" class="">Редактировать</a></div>
            </div>
        </div>
    </div>
</body>
</html>