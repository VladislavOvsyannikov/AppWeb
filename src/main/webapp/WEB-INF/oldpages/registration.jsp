<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>Навигация: </h3>
<a href="http://localhost:8080/shop/product">Главная</a>&emsp;&emsp;
<h3>Регистрация:</h3>
<spring:form modelAttribute="user" method="post" action="/registration">
    Имя пользователя: <br>
    <spring:input path="name" /> <br>
    Пароль: <br>
    <spring:input path="password" /> <br>
    <br>
    <spring:button>Зарегистрироваться</spring:button>
</spring:form>

</body>
</html>