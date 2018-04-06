<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Confirm</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>Навигация: </h3>
<a href="http://localhost:8080/shop/product">Главная</a>&emsp;&emsp;
<a href="http://localhost:8080/shop/admin">Администрирование</a>&emsp;&emsp;
<h3>Обработка заказов:</h3>

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
    form{
        display:inline-block;
    }
</style>

<c:forEach items="${baskets}" var="bask">
<c:if test="${bask.status=='1'}">
<c:if test="${bask.status2=='0'}">
<p>
<div>
    <a onclick="hidetxt('${bask.id}'); return false;" href="" rel="nofollow">
        <h4>Заказ номер ${bask.id}</h4>
    </a>
    <div id="${bask.id}" style="display:none">
        <table>
            <tr>
                <th>&ensp;Название&ensp;</th>
                <th>&ensp;Цена&ensp;</th>
                <th>&ensp;Тип</th>
                <th>&ensp;Количество</th>
            </tr>
            <c:forEach items="${bask.basketProductLinks}" var="basketProductLink">
                <c:if test="${basketProductLink.quantity!=0}">
                <tr>
                    <td>${basketProductLink.product.name}</td>
                    <td>${basketProductLink.product.price}</td>
                    <td>${basketProductLink.product.type.name}</td>
                    <td>${basketProductLink.quantity}</td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
        Общая стоимость: ${bask.cost}<br>
    </div>
</div>
&emsp;Логин заказчика: <c>${bask.user.name}</c><br>
&emsp;Статус заказа:
    <c:if test="${bask.status2=='0'}">
        <spring:form modelAttribute="basket" method="post" action="/shop/admin/confirm">
            <spring:hidden path="id" value="${bask.id}" />
            <spring:button>Отменить</spring:button>
        </spring:form>
        <spring:form modelAttribute="basket" method="post" action="/shop/admin/confirm2">
            <spring:hidden path="id" value="${bask.id}" />
            <spring:button>Подтвердить</spring:button>
        </spring:form>
    </c:if>

<c:if test="${bask.status2=='1'}"><font color="red">Отменён.</font></c:if>
<c:if test="${bask.status2=='2'}"><font color="green">Подтверждён.</font></c:if>

<p></c:if></c:if></c:forEach>

</body>
</html>