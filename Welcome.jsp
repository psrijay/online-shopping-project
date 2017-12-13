

<%@page import="com.onlineshopping.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%
            Cookie[] cookies=request.getCookies();
            for(Cookie c:cookies){
                if(c.getName().equals("phone") || c.getName().equals("pass")){
                    out.println(c.getValue());
                }
            }
            
            User u=(User)session.getAttribute("user");
            if(u==null){
                response.sendRedirect("Login.jsp");
            }
            out.println(u.getMailId());
        %>
        <a href="logout.jsp">Logout</a>
    </body>
</html>
