<%--
  Created by IntelliJ IDEA.
  User: alexsey.sultanaev
  Date: 24.07.2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.Ferret56.FlowerShopEE.be.entity.Order.OrderStatus" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <style>
        <%@include file="../css/UnpaidOrders.css"%>
    </style>
    <title>Orders</title>
</head>
<h1><a href="/FlowerShop/back">Back</a> </h1>
<body>
<div class="orderBox">
    <c:forEach var="order" items="${ordersList}">
    <table border="2">
        <tr>
            <th>Order №</th>
            <th>Items</th>
            <th>Price</th>
           <th>Status</th>
        </tr>
        <tr>
            <td align="center">${order.id}</td>
            <td>
                <table border="2">
                    <tr>
                        <th>FLower</th>
                        <th>Amount</th>
                    </tr>
                    <c:forEach var="item" items="${order.orderItemList}">
                        <tr>
                            <td>${item.flower.name}</td>
                            <td>${item.amount}</td>
                        </tr>
                    </c:forEach>
                </table>
            <td>${order.cost}</td>
           <td>${order.status}</td>
        </tr>
    </table>
    </c:forEach>



<!--
<div class="orderBox">
    <c:forEach var="order" items="${ordersList}">
        <div class="order">
            <h4>Order № ${order.id}</h4>
            <h4>Price: ${order.cost}$</h4>
            <table border="1">
                <tr>
                    <th>Flower</th>
                    <th>Count</th>
                </tr>
                <c:forEach var="item" items="${order.orderItemList}">
                    <tr>
                        <td><c:out value="${item.flower.name}"/></td>
                        <td><c:out value="${item.amount}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <p><h2>Status: ${order.status}</h2></p>

            <c:if test="${sessionScope.currentUser.role eq Roles.ADMIN}">
                <c:url value="/web/admin/remove/order/${order.id}" var="deleteOrderLink"/>
                <a href="${deleteOrderLink}">Delete order</a>
            </c:if>
        </div>
    </c:forEach>
</div>
-->
</body>
</html>