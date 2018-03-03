<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Confirm</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>Навигация: </h3>
<a href="http://localhost:8080/shop/product">Главная</a>&emsp;&emsp;
<a href="http://localhost:8080/shop/admin">Администрирование</a>&emsp;&emsp;
<h3>Список пользователей:</h3>

<style type="text/css">
    form{
        display:inline-block;
    }
</style>

<c:forEach items="${users}" var="us">
    <c:if test="${us.role=='ROLE_USER'}">
    <c>${us.name}</c>&emsp;&emsp;
    <spring:form modelAttribute="user" method="post" action="/shop/admin/users">
        <spring:hidden path="id" value="${us.id}"/>
        <spring:button>Удалить</spring:button>
    </spring:form>
    <br><br>
    </c:if>
</c:forEach>

</body>
</html>