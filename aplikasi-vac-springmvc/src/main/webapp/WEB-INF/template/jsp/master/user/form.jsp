<%-- 
    Document   : form
    Created on : Feb 19, 2013, 3:37:27 PM
    Author     : endy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entri User</title>
    </head>
    <body>
        <h1>Entri User</h1>

        <spring:form modelAttribute="user">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td>
                            <spring:input path="username" />
                        </td>
                        <td>
                            <font color="red">
                                <spring:errors path="username" />
                            </font>
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <spring:password path="password" />
                        </td>
                        <td>
                            <font color="red">
                                <spring:errors path="password" />
                            </font>
                        </td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td>
                            <spring:select path="role.id" >
                                <spring:options items="${daftarRole}" itemValue="id" itemLabel="nama"/>
                            </spring:select>
                        </td>
                        <td>
                            <font color="red">
                                <spring:errors path="role" />
                            </font>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <input type="submit"/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </tbody>
            </table>
        </spring:form>


    </body>
</html>
