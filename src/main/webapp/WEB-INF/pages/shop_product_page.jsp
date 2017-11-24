<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ShopProduct</title>
</head>
<body>
<h2>Интернет-магазин товаров для животных</h2>
<h3>Список доступных товаров: </h3>
<script>
    var show;
    function hidetxt(type){
        param=document.getElementById(type);
        if(param.style.display == "none") {
            if(show) show.style.display = "none";
            param.style.display = "block";
            show = param;
        }else param.style.display = "none"
    }
</script>
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

<c:forEach items="${types}" var="type">
    <div>
        <a onclick="hidetxt('${type.name}'); return false;" href="" rel="nofollow"><h4>${type.name}</h4></a>
    <div id="${type.name}" style="display:none">

    <table>
        <tr>
            <th>&ensp;Название&ensp;</th>
            <th>&ensp;Цена&ensp;</th>
            <th>&ensp;Количество</th>
        </tr>
        <c:forEach items="${type.product}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <c:if test="${product.quantity!='0'}"><p>
                    <td>${product.quantity}</td>
                    <td>
                        <spring:form modelAttribute="productFromServer" method="post" action="/shop/product">
                            <spring:hidden path="id" value="${product.id}"/>
                            <spring:button>Добавить в корзину</spring:button>
                        </spring:form>
                    </td>
                <p></c:if>
                <c:if test="${product.quantity=='0'}"><p>
                    <td>Нет в наличии</td>
                <p></c:if>
            </tr>
            </c:forEach>
    </table>
    </div>
    </div>
</c:forEach>

<br>
<a href="http://localhost:8080/shop/basket">Корзина</a> <br>
<a href="http://localhost:8080/shop/search">Поиск</a> <br>
<a href="http://localhost:8080/shop/add">Добавление товара в список товаров</a> <br>
<a href="http://localhost:8080/shop/history">История заказов</a>
</body>
</html>
