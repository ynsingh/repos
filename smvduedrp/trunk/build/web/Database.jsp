<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>login</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <link rel="stylesheet" type="text/css" href="css/layout.css"/>
        <link rel="stylesheet" type="text/css" href="css/loginpage.css"/>

    </head>

    <body id="" class="loginpage">
        <div class="container_16">
            <div class="grid_16 header">
                <div class="logo"><img src="img/logo.GIF" width="158" height="88" alt="[ logo ]" /></div>

                <div class="grid_10 title">
                    <p class="xerror">Database Configuration</p>
                </div>

            </div>

            <div class="grid_16 body alpha omega">

                
                <div class="form grid_7" >
                   
                        <div class="error">
                        </div>
                        <f:view>
                            <h:outputText id="msg" styleClass="Label" value="#{DBBean.message}"/>
                            <h:form id="connForm" onsubmit="return hidesome()">
                                <fieldset>  
                                    <table cellspacing="0" border="0">
                                        <tr>
                                            <td><label for="username">Host Name</label> </td>
                                            <td><h:inputText required="true" requiredMessage="Please Enter database Host name" value="#{DBBean.host}" />
                                                <!--<input name="username" class="v-text" type="text" autofocus /> --></td>
                                        </tr>
                                        <tr>
                                            <td><label for="password">Port </label> </td>
                                            <td><h:inputText value="#{DBBean.port}"/>
                                                <!--<input name="password" class="v-text" type="password" /> -->
                                            </td>
                                        </tr>

                                                 <tr>
                                            <td><label for="password">Database Name </label> </td>
                                            <td><h:inputText value="#{DBBean.dbName}"/>
                                                <!--<input name="password" class="v-text" type="password" /> -->
                                            </td>
                                        </tr>

                                                 <tr>
                                            <td><label for="password">User Name </label> </td>
                                            <td><h:inputText value="#{DBBean.username}"/>
                                                <!--<input name="password" class="v-text" type="password" /> -->
                                            </td>
                                        </tr>
                                                 <tr>
                                            <td><label for="password">Password </label> </td>
                                            <td><h:inputSecret value="#{DBBean.password}"/>
                                                <!--<input name="password" class="v-text" type="password" /> -->
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td><h:commandButton action="#{DBBean.testConnection}" value="Save" />
                                                <h:commandLink  action="Login.faces" value="Login"/>
                                            </td>
                                        </tr>
                                    </table>
                                </fieldset>
                            </h:form>
                        </f:view>
                </div>




            </div>



            <div class="grid_16 footer"> <a href="#">Home</a> | <a href="#">Copyright Status</a> | <a href="#">Help</a> | <a href="#">Disclaimer</a> | <a href="#">Developers</a></div>
        </div>

    </body>
</html>
