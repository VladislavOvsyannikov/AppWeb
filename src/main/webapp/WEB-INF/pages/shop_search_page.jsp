<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ShopSearch</title>
</head>
<body>
<style type="text/css">
    TABLE {  border-collapse: collapse; }
    TD, TH {
        padding: 3px;
        border: 1px solid black;
    }
    TH {  background: #b0e0e6;  }
</style>
    <h2>Интернет-магазин товаров для животных</h2>
    <h3>Поиск товара по параметрам:</h3>
    <spring:form modelAttribute="searchFromServer" method="post" action="/shop/search">
        <spring:select path="type.name">
                <option>-</option>
            <c:forEach items="${types}" var="t">
                <option>${t.name}</option>
            </c:forEach>
        </spring:select> Тип товара <br>
        <spring:input path="name" /> Название <br>
        <spring:input path="price" /> Цена (<=)<br>
        <spring:input path="quantity" /> Количество (>=) <br>
        <spring:button>Поиск</spring:button>
    </spring:form>

    <c:if test="${empty res}">
    <p><h3>Нет таких товаров</h3><p>
        </c:if>
        <c:if test="${!empty res}"><p>
    <table>
        <h3>Найденые товары:</h3>
        <tr>
            <th>&ensp;Название&ensp;</th>
            <th>&ensp;Цена&ensp;</th>
            <th>&ensp;Тип&ensp;</th>
            <th>&ensp;Количество&ensp;</th>
            <th> Склад </th>
        </tr>
        <c:forEach items="${res}" var="prod">
            <tr>
                <td>${prod.name}</td>
                <td>${prod.price}</td>
                <td>${prod.type.name}</td>
                <c:if test="${prod.quantity!='0'}"><p>
                    <td>${prod.quantity}</td>
                    <td>${prod.stock.address}</td>
                    <td>
                        <spring:form modelAttribute="productFromServer" method="post" action="/shop/product">
                        <spring:hidden path="id" value="${prod.id}"/>
                        <spring:button>Добавить в корзину</spring:button>
                        </spring:form>
                    </td>
                <p></c:if>

                <c:if test="${prod.quantity=='0'}"><p>
                    <td>Нет в наличии</td>
                </p></c:if>
            </tr>
        </c:forEach>
    </table>
    <p>
    </c:if>

    <br><a href="http://localhost:8080/shop/product">Список продуктов</a>
        <br><a href="http://localhost:8080/shop/basket">Корзина</a>

</body>
</html>
