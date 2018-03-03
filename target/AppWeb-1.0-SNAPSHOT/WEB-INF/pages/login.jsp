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
<form method="post" action="<c:url value='login'/>">
    Имя пользователя: <br>
    <input type="text" name="username"/> <br>
    Пароль: <br>
    <input type="password" name="password"/> <br><br>

    <input type="submit" value="Вход"/>

    <%--<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>"--%>
           <%--value="<c:out value="${_csrf.token}"/>"/>--%>
</form>
<br><a href="http://localhost:8080/registration">Регистрация</a>

</body>
</html>