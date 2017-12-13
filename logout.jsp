

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            session.setAttribute("user", null);
            session.setAttribute("roles", null);
        %>
        <p>You are logged out successfully click <a href="Login.jsp">here</a> to Login</p>
    </body>
</html>
