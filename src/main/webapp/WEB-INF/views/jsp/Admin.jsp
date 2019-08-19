<%--
  Created by IntelliJ IDEA.
  User: alexsey.sultanaev
  Date: 24.07.2019
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <style>
        <%@include file="../css/Admin.css"%>
    </style>
    <title>Admin</title>
</head>
<body>
<div class="headline">
    <div class="menu">
        <ul>
            <li><a href="logout">LogOut</a></li>
        </ul>
    </div>
</div>
<div class="UserWelcome">
    <h2>You are login as 'Admin'</h2>

    <table border="2">
        <tr>
            <th>User_Id</th>
            <th>UserName</th>
            <th>UserMoney</th>
            <th>UserDiscount</th>
            <th>Order_id</th>
            <th>Price</th>
            <th>Create_Date</th>
            <th>Close_Date</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach var="order" items="${ordersList}">
        <tr>
            <td>${order.user.id}</td>
            <td>${order.user.username}</td>
            <td>${order.user.money}</td>
            <td>${order.user.discount}</td>
            <td>${order.id}</td>
            <td>${order.cost}</td>
            <td>${order.orderCreateDate}</td>
            <td>${order.orderCloseDate}</td>
            <td>${order.status}</td>
            <td><a href="admin/close/${order.id}">Close</a></td>
        </tr>
        </c:forEach>
    </table>


</body>
</html>