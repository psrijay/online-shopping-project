
//JSTL
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="User" method="post">
            <table>
                <tr>
                    <td>
                       <input type="text" name="firstName" value="${param.firstName}" placeholder="First Name" required="true" pattern="[a-zA-Z]{3,}"/>
                    </td>
                    <td>
                        <input type="text" name="lastName" value="${param.lastName}" placeholder="Last Name" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="email" id="mailId" name="mailId" value="${param.mailId}" placeholder="Email Id" required="true" onkeyup="isNewMail()"/>
                    </td>
                    <td><div id="message"></div></td>
                </tr>
                <tr>
                    <td>
                        <input type="tel" name="phoneNumber" value="${param.phoneNumber}" placeholder="Mobile Number" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td> <label>Gender</label> </td>
                    <td>
                        <c:if test="${param.gender=='Male' or empty param.gender}" >
                        <input type="radio" name="gender" value='Male' checked/> Male
                        <input type="radio" name="gender" value='Female'/> Female
                        </c:if>
                        <c:if test="${param.gender=='Female'}" >
                        <input type="radio" name="gender" value='Male' /> Male
                        <input type="radio" name="gender" value='Female' checked/> Female
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:if test="${not empty param.pass}">
                            <input type="password" name="password" placeholder="Password" required="true" autofocus="true"/>
                        </c:if>
                            <c:if test="${empty param.pass}">
                                <input type="password" name="password" placeholder="Password" required="true" />
                            </c:if>
                            
                    </td>
                    <td>
                        <input type="password" name="confirmpassword" placeholder="Confirm Password" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="reset" name="cancelBtn" placeholder="Cancel" />
                    </td>
                    <td>
                        <input type="submit" name="submitBtn" placeholder="Sign Up" />
                    </td>
                </tr>
            </table>
        </form>
                    <script>
                        var dis=document.getElementById("message");
                        function isNewUser(){
                            var req;
                            var idFld=document.getElementById("mailId");
                            var url="ValidateMail?mailid="+encodeURIComponent(idFld);
                            if(window.XMLHttpRequest){
                                req=new XMLHttpRequest();
                            }else if(window.ActiveXObject){
                                req=new ActiveXObject("Microsoft.XMLHTTP");
                            }else{
                                
                            }
                            req.open("GET",url,true);
                            req.send(null);
                            req.onreadystatechange = callback;
                            
                            function callback(){
                                if(req.readyState == 4 && req.status == 200){
                                    dis.innerHTML=req.responseText;
                                }
                            }
                        }
                    </script>
    </body>
</html>
