<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ShopOrder</title>
</head>
<body>
<h2>Интернет-магазин для животных</h2>
<h3>Корзина: </h3>
<style type="text/css">
    TABLE {  border-collapse: collapse; }
    TD, TH {
        padding: 3px;
        border: 1px solid black;
    }
    TH {  background: #b0e0e6;  }
</style>
    <c:if test="${empty orders}">
        <p><h3>Нет товаров в корзине</h3><p>
    </c:if>
    <c:if test="${!empty orders}">
        <p>
<table>
    <tr>
        <th>&ensp;Название&ensp;</th>
        <th>&ensp;Цена&ensp;</th>
        <th>&ensp;Вид животного&ensp;</th>
        <th>&ensp;Наличие&ensp;</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.name}</td>
            <td>${order.price}</td>
            <td>${order.kind}</td>
            <td>${order.availability}</td>
            <td>
                <spring:form modelAttribute="orderFromServer" method="post" action="/shop/order">
                    <spring:hidden path="id" value="${order.id}"/>
                    <spring:hidden path="name" value="${order.name}"/>
                    <spring:hidden path="price" value="${order.price}"/>
                    <spring:hidden path="kind" value="${order.kind}"/>
                    <spring:hidden path="availability" value="${order.availability}"/>
                    <spring:button>Удалить из корзины</spring:button>
                </spring:form>
            </td>
        </tr>
    </c:forEach>
</table>
    <p>
    </c:if>
        <br>
        <a href="http://localhost:8080/shop/product">Список продуктов</a><br>
        <a href="http://localhost:8080/shop/search">Поиск</a>
</body>
</html>
