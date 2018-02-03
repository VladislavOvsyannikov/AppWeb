<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ShopAdd</title>
</head>
<body>
    <h2>Интернет-магазин товаров для животных</h2>
    <h3>Добавление товара в список товаров:</h3>
    <spring:form modelAttribute="product" method="post" action="/shop/add">
        <spring:input path="name" /> Название <br>
        <spring:input path="price" /> Цена <br>
        <spring:input path="quantity" /> Количество товара <br>
        <spring:input path="type.name" /> Тип товара <br>
        <spring:input path="stock.address"/> Название склада <br>
        <spring:button>Добавить</spring:button>
    </spring:form>

    <br>
    <br>

    <spring:form method="post" action="/shop/generate">
        <input type="submit" value="Generate"/>
    </spring:form>



</body>
</html>
