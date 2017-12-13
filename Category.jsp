<%-- 
    Document   : Category
    Created on : Jun 12, 2017, 6:44:58 PM
    Author     : iamsu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${empty param.r}">
            <c:redirect url="Category?m=r"/>
        </c:if>
        <form method="POST">
            <table>
                <tr>
                    <td>
                        <input type="text" name="categoryName" placeholder="Category Name" value="${Category.name}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <textarea name="description" rows="4" cols="20" placeholder="Description">${Category.description}
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${empty Category}">
                            <input type="submit" value="Add Category" formaction="Category?m=c"/>
                        </c:if>
                        <c:if test="${not empty Category}">
                            <input type="hidden" value="${Category.id}" name="id"/>
                            <input type="submit" value="Update Category" formaction="Category?m=u" />
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
        
        <table border='1'>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th colspan="2">Manage</th>
            </tr>
            <c:forEach var="c" items="${categories}">
                <tr>
                    <td>${c.name}</td>
                    <td>${c.description}</td>
                    <td><a href="Category?m=e&&id=${c.id}">Edit</a></td>
                    <td><a href="Category?m=d&&id=${c.id}">delete</a></td>
                    <td><a href="Product?m=r&&id=${c.id}">View Products</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
