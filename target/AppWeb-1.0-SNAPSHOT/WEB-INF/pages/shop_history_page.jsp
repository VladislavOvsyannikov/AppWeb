<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ShopHistory</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>Навигация: </h3>
<a href="http://localhost:8080/shop/product">Главная</a>&emsp;&emsp;
<a href="http://localhost:8080/shop/search">Поиск товара</a>&emsp;&emsp;
<a href="http://localhost:8080/shop/basket">Корзина</a>

<h3>Авторизация:</h3>
<c:if test="${userName=='Гость'}"><p>
    Вы вошли как <c>${userName}</c>.&emsp;&emsp;
    <a href="http://localhost:8080/login">Вход</a>&emsp;&emsp;
    <a href="http://localhost:8080/registration">Регистрация</a>
<p></c:if>
<c:if test="${userName!='Гость'}"><p>
    Вы вошли как <c>${userName}</c>.&emsp;&emsp;
    <a href="http://localhost:8080/logout">Выход</a>
<p></c:if>
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
    <a onclick="hidetxt('${basket.id}'); return false;" href="" rel="nofollow">
        <h4>Заказ номер ${basket.id}</h4>
    </a>
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
        Общая стоимость: ${basket.cost}
        <br><br>
    </div>
</div>

&emsp;Статус заказа:
<c:if test="${basket.status2=='0'}"><font color="blue"> В обработке.</font></c:if>
<c:if test="${basket.status2=='1'}"><font color="red">Отменён.</font></c:if>
<c:if test="${basket.status2=='2'}"><font color="green">Подтверждён.</font></c:if>

<p>
    </c:if>
    </c:forEach>
</body>
</html>
