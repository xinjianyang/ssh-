<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改商品信息</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form method="post" action="/product/edit">
        <div class="form-group">
            <%--<input type="hidden" name="id" value="${product.id}">--%>
            <label>商品名称</label>
            <input type="text" name="productName" class="form-control" value="${product.productName}">
        </div>
        <div class="form-group">
            <label>价格</label>
            <input type="text" name="price" class="form-control" value="${product.price}">
        </div>
        <div class="form-group">
            <label>市场价格</label>
            <input type="text" name="marketPrice" class="form-control" value="${product.marketPrice}">
        </div>
        <div class="form-group">
            <label>产地</label>
            <input type="text" name="place" class="form-control" value="${product.place}">
        </div>
        <div class="form-group">
            <label>评论数量</label>
            <input type="text" name="commentNum" class="form-control" value="${product.commentNum}">
        </div>
        <div class="form-group">
            <button class="btn btn-primary">保存</button>
        </div>
    </form>
</div>
</body>
</html>