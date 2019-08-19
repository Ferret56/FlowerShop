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
    <title>UnpaidOrders</title>
</head>
<h1><a href="/FlowerShop/back">Back</a> </h1>
<body>
<div class="orderBox">
    <p><h3>${errorMsg}</h3></p>
    <c:forEach var="order" items="${ordersList}">
        <table border="2">
           <tr>
               <th>Order №</th>
               <th>Items</th>
               <th>Price</th>
               <th>Buy</th>
               <th>Clear</th>
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
                <td><a href="userPage/buy/order/${order.id}">Buy</a></td>
                </td>
                <td><a href="remove/order/${order.id}">Delete</a> </td>
            </tr>
        </table>
    </c:forEach>








</body>
</html>