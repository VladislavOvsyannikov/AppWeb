<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ShopHistory</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>История заказов: </h3>
<script>
    var show;

    function hidetxt(type) {
        param = document.getElementById(type);
        if (param.style.display == "none") {
            if (show) show.style.display = "none";
            param.style.display = "block";
            show = param;
        } else param.style.display = "none"
    }
</script>
<style type="text/css">
    TABLE {
        border-collapse: collapse;
    }

    TD, TH {
        padding: 3px;
        border: 1px solid black;
    }

    TH {
        background: #b0e0e6;
    }
</style>

<c:forEach items="${baskets}" var="basket">
<c:if test="${basket.status=='1'}">
<p>
<div>
    <a onclick="hidetxt('${basket.id}'); return false;" href="" rel="nofollow"><h4>Заказ номер ${basket.id}</h4></a>
    <div id="${basket.id}" style="display:none">

        <table>
            <tr>
                <th>&ensp;Название&ensp;</th>
                <th>&ensp;Цена&ensp;</th>
                <th>&ensp;Тип</th>
            </tr>
            <c:forEach items="${basket.productInOrder}" var="productInOrder">
                <tr>
                    <td>${productInOrder.product.name}</td>
                    <td>${productInOrder.product.price}</td>
                    <td>${productInOrder.product.type.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<p>
    </c:if>
    </c:forEach>
    <br>
    <a href="http://localhost:8080/shop/product">Список продуктов</a>
    <br>
</body>
</html>
