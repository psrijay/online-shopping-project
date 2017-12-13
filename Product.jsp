<%-- 
    Document   : Product
    Created on : Jun 13, 2017, 6:44:07 PM
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
        <form method="POST">
            <table>
                <tr>
                    <td>
                        <input type="text" name="productName" placeholder="Product Name" value="${product.name}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <textarea name="description" rows="4" cols="20" placeholder="Description">${product.description}
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="price" placeholder="MRP" value="${product.price}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <select name="categoryId">
                            <c:forEach var='c' items="${categories}">
                            <option value="${c.id}">${c.name}</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="pid" value="${product.id}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="manufactureName" placeholder="Manufacture Name" value="${product.manufacturename}" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${empty product}">
                            <input type="submit" value="Add Product" formaction="Product?m=c"/>
                        </c:if>
                        <c:if test="${not empty product}">
                            <input type="hidden" value="${Product.id}" name="id"/>
                            <input type="submit" value="Update Product" formaction="Product?m=u" />
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
        
        <table border='1'>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Manufacture</th>
                <th>Category</th>
                <th colspan="2">Manage</th>
            </tr>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td>${p.name}</td>
                    <td>${p.description}</td>
                    <td>${p.price}</td>
                    <td>${p.manufacturename}</td>
                    <td>${p.categoryId}</td>
                    <td><a href="Product?m=e&&id=${p.id}">Edit</a></td>
                    <td><a href="Product?m=d&&id=${p.id}">delete</a></td>
                    <td><a href="ProductDetails?m=r&&id=${p.id}">View Sellers</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
