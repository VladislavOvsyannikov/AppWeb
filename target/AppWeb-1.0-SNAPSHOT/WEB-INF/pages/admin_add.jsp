<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
    <h2>Интернет-магазин товаров для животных</h2>
    <h3>Навигация: </h3>
    <a href="http://localhost:8080/shop/product">Главная</a>&emsp;&emsp;
    <a href="http://localhost:8080/shop/admin">Администрирование</a>&emsp;&emsp;
    <h3>Добавление товара в список товаров:</h3>
    <spring:form modelAttribute="product" method="post" action="/shop/admin/add">
        Название<br>
        <spring:input path="name" /> <br>
        Цена<br>
        <spring:input path="price" />  <br>
        Количество товара<br>
        <spring:input path="quantity" />  <br>
        Тип товара<br>
        <spring:input path="type.name" />  <br>
        Название склада<br>
        <spring:input path="stock.address"/>  <br>
        <br>
        <spring:button>Добавить</spring:button>
    </spring:form>

    <%--<spring:form method="post" action="/shop/generate">--%>
        <%--<input type="submit" value="Generate"/>--%>
    <%--</spring:form>--%>

</body>
</html>
