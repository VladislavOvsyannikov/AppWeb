<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ShopSearch</title>
</head>
<body>
    <h2>Интернет-магазин для животных</h2>
    <h3>Поиск товара по параметрам:</h3>
    <spring:form modelAttribute="searchFromServer" method="post" action="/shop/search">
        <spring:input path="name" /> Название <br>
        <spring:input path="price" /> Цена <br>
        <spring:input path="kind" /> Вид животного <br>
        <spring:input path="availability"/> Наличие <br>
        <spring:button>Поиск</spring:button>
    </spring:form>

    <style type="text/css">
        TABLE {  border-collapse: collapse; }
        TD, TH {
            padding: 3px;
            border: 1px solid black;
        }
        TH {  background: #b0e0e6;  }
    </style>
    <c:if test="${empty res}">
    <p><h3>Нет таких товаров</h3><p>
        </c:if>
        <c:if test="${!empty res}">
    <p>
    <table>
        <h3>Найденые товары:</h3>
        <tr>
            <th>&ensp;Название&ensp;</th>
            <th>&ensp;Цена&ensp;</th>
            <th>&ensp;Вид животного&ensp;</th>
            <th>&ensp;Наличие&ensp;</th>
        </tr>
        <c:forEach items="${res}" var="order">
            <tr>
                <td>${order.name}</td>
                <td>${order.price}</td>
                <td>${order.kind}</td>
                <td>${order.availability}</td>
            </tr>
        </c:forEach>
    </table>
    <p>
    </c:if>

    <br><a href="http://localhost:8080/shop/product">Список продуктов</a>
        <br><a href="http://localhost:8080/shop/order">Корзина</a>

</body>
</html>
