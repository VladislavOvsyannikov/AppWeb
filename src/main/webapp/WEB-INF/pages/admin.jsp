<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>Навигация: </h3>
<a href="http://localhost:8080/shop/product">Главная</a>&emsp;&emsp;

<h3>Администрирование:</h3>
<a href="http://localhost:8080/shop/admin/change">Изменить название товара</a>&emsp;&emsp;
<a href="http://localhost:8080/shop/admin/confirm">Обработать заказ(<c>${number}</c>)</a>&emsp;&emsp;
<a href="http://localhost:8080/shop/admin/users">Пользователи</a>&emsp;&emsp;

</body>
</html>