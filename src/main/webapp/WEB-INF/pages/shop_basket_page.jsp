<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ShopOrder</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>Корзина: </h3>
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

<c:if test="${empty basket.productInOrder}"><p>
<h3>Нет товаров в корзине</h3>
<p></c:if>

<c:if test="${!empty basket.productInOrder}"><p>
<c:if test="${basket.status=='1'}"><p>
<h3>Нет товаров в корзине</h3>
<p></c:if>

<c:if test="${basket.status=='0'}"><p>

    <form:form method="post" action="/shop/history">
        <input type="submit" value="Подтвердить заказ"/>
    </form:form>

    <table>
        <tr>
            <th>&ensp;Название&ensp;</th>
            <th>&ensp;Цена&ensp;</th>
            <th>&ensp;Тип&ensp;</th>
        </tr>
        <c:forEach items="${basket.productInOrder}" var="productInOrder">
            <tr>
                <td>${productInOrder.product.name}</td>
                <td>${productInOrder.product.price}</td>
                <td>${productInOrder.product.type.name}</td>
                <td>
                    <spring:form modelAttribute="productInOrderFromServer" method="post" action="/shop/basket">
                        <spring:hidden path="id" value="${productInOrder.id}"/>
                        <%--<spring:hidden path="basket" value="${productInOrder.basket}"/>--%>
                        <%--<spring:hidden path="product" value="${productInOrder.product}"/>--%>
                        <spring:button>Удалить из корзины</spring:button>
                    </spring:form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p></c:if><p>
    </c:if>
    <br>
    <a href="http://localhost:8080/shop/product">Список продуктов</a><br>
    <a href="http://localhost:8080/shop/search">Поиск</a>
</body>
</html>
