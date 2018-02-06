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
<c:if test="${userName!='Гость'}">
<p>
    Вы вошли как
    <c>${userName}</c>
    .&emsp;&emsp;
    <a href="http://localhost:8080/logout">Выход</a>&emsp;&emsp;
    <a href="http://localhost:8080/shop/history">История заказов</a>
<p></c:if>
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
    <p></c:if>
<p>
    </c:if>
    <c:if test="${!empty basket.productInOrder}">
    <c:if test="${basket.status!='1'}">
    <br> Общая стоимость:
    <c>${basket.cost}</c>
    </c:if>
    </c:if>
</body>
</html>
