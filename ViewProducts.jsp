<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <table>
            <c:forEach var="p" items="${vproducts}">
            <tr>
                <td>Product Image</td>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>${p.manufacturename}</td>
                <td><a href="Cart?m=a&&p=${p.id}">Add to Cart</a></td>                                
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
