<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>Навигация: </h3>
<a href="http://localhost:8080/shop/product">Главная</a>&emsp;&emsp;
<h3>Авторизация:</h3>
<form method="post" action="/j_spring_security_check" role="form">
    Имя пользователя: <br>
    <input type="text" name="j_username"/> <br>
    Пароль: <br>
    <input type="password" name="j_password"/> <br><br>
    <%--<input type="checkbox" name="j_spring_security_remember_me"/>--%>
    <input type="submit" value="Вход"/>
</form>
<br><a href="http://localhost:8080/registration">Регистрация</a>

</body>
</html>
