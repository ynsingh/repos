<%--
    Document   : Add Department
    Created on : Sept 28, 2010, 4:26:31 PM
    Author     : Saurabh
--%>


<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <title>Add Employee Type</title>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <h:form >
                    <table>
                        <thead>
                            <tr ><th colspan="2">Add Employee Type</th></tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td width="28%">Existing Types</td>
                                <td width="28%">
                                    <html:select property="allTypes">
                                    <html:options property="allTypes"/>
                                    </html:select>
                                </td>
                            </tr>


                        <tr>
                            <td>New Type</td>
                            <td width="72%"><html:text property="name" /></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td><html:submit value="Save" /></td>
                        </tr>
                        </tbody>
                    </table>
                </h:form>
            </f:view>
        </div>
    </body>
</html>
