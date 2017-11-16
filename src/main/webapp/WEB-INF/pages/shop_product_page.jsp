<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"  %>
<html>
<head>
    <title>ShopProduct</title>
</head>
<body>
    <h2>Интернет-магазин для животных</h2>
    <h3>Список доступных товаров: </h3>
    <style type="text/css">
        TABLE {  border-collapse: collapse; }
        TD, TH {
            padding: 3px;
            border: 1px solid black;
        }
        TH {  background: #b0e0e6;  }
    </style>
    <table>
        <tr>
            <th>&ensp;Название&ensp;</th>
            <th>&ensp;Цена&ensp;</th>
            <th>&ensp;Вид животного&ensp;</th>
            <th>&ensp;Наличие&ensp;</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.kind}</td>
                <td>${product.availability}</td>
                <td>
                    <spring:form modelAttribute="productFromServer" method="post" action="/shop/product">
                        <spring:hidden path="name" value="${product.name}"/>
                        <spring:hidden path="price" value="${product.price}"/>
                        <spring:hidden path="kind" value="${product.kind}"/>
                        <spring:hidden path="availability" value="${product.availability}"/>
                        <spring:button>Добавить в корзину</spring:button>
                    </spring:form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="http://localhost:8080/shop/order">Корзина</a> <br>
    <a href="http://localhost:8080/shop/search">Поиск</a>
</body>
</html>
