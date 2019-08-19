<%--
  Created by IntelliJ IDEA.
  User: alexsey.sultanaev
  Date: 24.07.2019
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <style>
        <%@include file="../css/UserPage.css"%>
    </style>
    <title>${sessionScope.currentUser.username}</title>
</head>
<body>
<div class="headline">
    <div class="menu">
        <ul>
            <li><a href="logout">LogOut</a></li>
            <li><a href="viewOrders">Orders</a></li>
            <li><a href="viewUnpaidOrders">Unpaid orders</a> </li>
        </ul>
    </div>
</div>
<div class="UserWelcome">
    <h2>Welcome, ${sessionScope.currentUser.username}</h2>
    <h2>You have : ${sessionScope.currentUser.money}$</h2>
    <h2>Your discount : ${sessionScope.currentUser.discount}%</h2>
</div>
<div class="Catalog">
    <h2>Catalog:</h2>
    <form:form method="post" action="userPage/filterByName/">
    <input type ="text" id="fndInput" name="flowerName" placeholder="Find by the flower name" />
    <input type="submit" id="fndByName" value="Find"/>
    </form:form>
    <form:form method="post" action="filterByRange">
        <input type=number id="fndByNum" name="From" placeholder = "From"/>
        <input type=number id="fndByNum" name="To" placeholder = "To"/>
        <input type="submit" id="fndByNumBtn" value="Find"/>
        <br>
    </form:form>
    <a href="/FlowerShop/userPage/resetFilter">Reset filter</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="flower" items="${flowerList}">
            <tr>
                <td><c:out value="${flower.id}"/></td>
                <td><c:out value="${flower.name}"/></td>
                <td><c:out value="${flower.price}$"/></td>
                <td><c:out value="${flower.amount}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<c:if test="${not empty sortedFlowerList}">
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="flower" items="${sortedFlowerList}">
            <tr>
                <td><c:out value="${flower.id}"/></td>
                <td><c:out value="${flower.name}"/></td>
                <td><c:out value="${flower.price}$"/></td>
                <td><c:out value="${flower.amount}"/></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<div class="OrderChoose">
    <form:form method="post">
        <select name="FlowerId">
            <c:forEach var="flower" items="${flowerList}">
                <option value="${flower.id}"><c:out value="${flower.name}"/></option>
            </c:forEach>
        </select>
        <input type="number" id="amountFld" name="Amount" placeholder="Amount" min="1">
        <input type="submit" id="addBtn" value="Add">
    </form:form>
</div>
<div class="Basket">
    <h2>Your basket:</h2>
    <c:if test="${ not empty sessionScope.currentBasket.orderItemList}">
        <table border="1">
            <tbody>
            <tr>
                <td>Name</td>
                <td>Count</td>
            </tr>
            <c:forEach var="orderItem" items="${sessionScope.currentBasket.orderItemList}">
                <tr>
                    <td> <c:out value="${orderItem.flower.name}"/></td>
                    <td> <c:out value="${orderItem.amount}"/></td>
                    <td><a href="userPage/basket/remove/${orderItem.flower.id}" >&#10008;</a></td>
                </tr>
            </c:forEach>
            </tbody>
            <h3><c:out value="${informationMessage}"/></h3>
        </table>
        <h4 id = "Price">Price: ${sessionScope.currentBasket.price}$</h4>
        <c:url value="userPage/createOrder" var="createOrderLink"/>
        <a href="${createOrderLink}">Create order</a>
        <c:url value="userPage/clearOrder" var="clearOrderLink"/>
        <a href="${clearOrderLink}">Delete</a>
    </c:if>
</div>
</body>
</html>