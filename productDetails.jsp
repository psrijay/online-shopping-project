<%-- 
    Document   : productDetails
    Created on : Jun 19, 2017, 6:18:12 PM
    Author     : iamsu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include  file="header.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ProductDetails" method="post">
            <table>
                <tr>
                    <td>
                        <input type="text" name="serialNumber" Placeholder="Serial Number" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <select name="productId">
                            <option value="0">Select</option>
                            <c:forEach var='p' items="${products}">
                                <option value="${p.id}">${p.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="date" name="manufactureDate" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="date" name="expieryDate" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="size" Placeholder="Size" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="color" Placeholder="Color" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="sellerId" value="${user.id}" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Add Product Details" />
                    </td>
                </tr>
        </form>
        
    </body>
</html>
