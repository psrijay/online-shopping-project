

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
        <style>
            .header{
                background-color: orange;
                width: 100%;
                height: 100px;
            }
            .footer{
                background-color: orange;
                width: 100%;
                height: 50px;
                bottom:0px;
                position: fixed;
            }
            nav{
                width: 100%;
                background-color: chocolate;
            }
            nav a{
                text-decoration: none;
                color: wheat;
                font-size: 20px;
                padding-left: 50px;
                padding-right: 50px;
                padding-top: 10px;
                padding-bottom: 10px;
            }
            .img-br{
                padding-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class='header'>
            <h1 style="text-align:center;color: blueviolet;font-size: 50px;">Online Shopping</h1>
            <nav>
                <a href='#'>Home</a> | 
                <a href='#'>About Us</a> | 
                <a href='#'>Contact Us</a> | 
                <a href='#'>Help</a> | 
                <a href='#'>feedback</a>
                <c:if test="${not empty user}">
                    <a href="logout.jsp">Logout</a>
                    <a href='#'>Hi ${user.firstName} !</a>
                </c:if>
                <c:if test="${empty user}">
                    <a href="#">Login</a>
                    <a href='#'>Sign Up</a>
                </c:if>
                <c:if test="${roles[1].roleName=='Admin'}">
                    <a href="Manage.jsp">Manage Site</a>
                </c:if>
                <c:if test="${roles[1].roleName=='Seller'}">
                    <a href="seller.jsp">Manage Store</a>
                </c:if>
            </nav>
        </div>
    </body>
</html>
