<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=yes, initial-scale=1.0">
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
        .product__img {
            display: block;
            margin-top: 4px;
            margin-bottom: 8px;
            max-width: 100%;
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
        <h1>Список продуктов</h1>
        <div class="product">
            <#list products as product>
                <div class="product__item">
                    <p>Название: ${product.title}</p>
                    <p class="product__item-text">Цена: ${product.price} ₽</p>
                    <#if product.previewImageId??>
                        <img class="product__img" src="/images/${product.previewImageId}">
                    </#if>
                    <div><a href="/product/view/${product.id}" class="">Подробнее</a></div>
                </div>
                <#else>
                <h2>Товаров нет!</h2>
            </#list>
        </div>
        <form action="/product/create" method="post" enctype="multipart/form-data">
            <p>Название: <input class="field" type="text" name="title"></p>
            <p>Описание: <input class="field" type="text" name="description"></p>
            <p>Цена: <input class="field" type="text" name="price"></p>
            <p>Город: <input class="field" type="text" name="city"></p>
            <p>Автор: <input class="field" type="text" name="author"></p>
            <p>Фото: <input type="file" name="images"></p>
            <input type="submit" value="Добавить">
        </form>
    </div>
</body>
</html>