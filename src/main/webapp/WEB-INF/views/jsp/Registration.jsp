<%--
  Created by IntelliJ IDEA.
  User: alexsey.sultanaev
  Date: 24.07.2019
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<html>
<head>
    <meta charset="utf-8">
    <style>
        <%@include file="../css/Registration.css"%>
    </style>
    <title>Registration</title>
</head>
<body>
<div class="registrationForm">
    <form:form method="post" modelAttribute="userDTO">
        <div class="inputs">
            <h3 class="Account">Create new account</h3>
            <input type="text" id="usr"  name="username" placeholder="username" onchange="isExist()">
            <input type="text" name="phone" placeholder="phone number">
            <input type="email" name="email" placeholder="email">
            <input type="password" name="password" placeholder="password">
            <input type="password" name="confirm_password" placeholder="confirm password">
            <br/>
            <input class="submit" type="submit" value="Sign up">
            <br/>
            <c:url value="/login" var="loginLink"/>
            <a href="${loginLink}">Sign in</a>
            <p class="errorMessage"><form:errors path="*"/> </p>
            <p class="errorMessage">${errorMessage} </p>
            <p class="successMessage">${successMessage}</p>
        </div>
    </form:form>
</div>
<script>
    function isExist() {
        $.ajax({
            type: "GET",
            url : "http://localhost:8080/FlowerShop/rest/find/" + document.getElementById("usr").value,
            dataType:"text",
            success : function (ans) {
                if(ans === "true"){
                    document.getElementById("usr").style.color="Red";
                }
                else
                    document.getElementById("usr").style.color = "Black";

            }

        });
    }
</script>
</body>

</html>