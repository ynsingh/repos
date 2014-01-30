<%--
    Document   : RubricSetup
    Created on : 31 Oct, 2013, 12:46:07 PM
    Author     : Vinay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <s:form action="RubricSetupAction" namespace="/Assignment" theme="simple">
            <table border="1">
                <tr><td>Category<s:textarea name="category"/></td>
                    <td>Level 1<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 2<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 3<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 4<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                </tr>
                <tr><td>Category<s:textarea name="category"/></td>
                    <td>Level 1<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 2<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 3<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 4<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                </tr>
                <tr><td>Category<s:textarea name="category"/></td>
                    <td>Level 1<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 2<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 3<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                    <td>Level 4<s:textarea name="level"/><br/>Point<s:textfield name="point"/></td>
                </tr>
            </table>
            <s:submit/>
        </s:form>
    </body>
</html>
