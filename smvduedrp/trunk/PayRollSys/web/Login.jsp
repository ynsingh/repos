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
        <link rel="stylesheet" type="text/css" href="css/layout.css"/>
        <link rel="stylesheet" type="text/css" href="css/loginpage.css"/>

        <!--set the autofocus on the login form-->
        <script type="text/javascript" type="text/javascript">
            function setFocus(){
                document.UserInfoForm.userName.select();
                document.UserInfoForm.userName.focus();
            }
        </script>
    </head>

    <body id="" class="loginpage">
        <div class="container_16">
            <div class="grid_16 header">
                <div class="logo"><img src="img/logo.GIF" width="158" height="88" alt="[ logo ]" /></div>

                <div class="grid_10 title">
                    <h1>Payroll System</h1>
                    <p>Shri Mata Vaishno Devi university.</p>
                </div>

            </div>

            <div class="grid_16 body alpha omega">

                <div class="product grid_8" >
                    <img src="img/ps.png" width="282" height="258" alt="Payroll System" />
                </div>
                <div class="form grid_7" >
                    <html:form action="/userlogin">
                        <div class="error">
                        </div>
                        <f:view>
                            <h:form>
                                <fieldset>
                                    
                                    <legend>Login Here</legend>
                                    <table cellspacing="0" border="0" width="60%">
                                        <tr>
                                            <td><label for="username">Organization Name</label> </td>
                                            <td><h:inputText value="#{UserBean.orgName}" />
                                                <!--<input name="username" class="v-text" type="text" autofocus /> --></td>
                                        </tr>
                                        <tr>
                                            <td><label for="username">Username:</label> </td>
                                            <td><h:inputText label="User Name" value="#{UserBean.userName}" />
                                                <!--<input name="username" class="v-text" type="text" autofocus /> --></td>
                                        </tr>
                                        <tr>
                                            <td><label for="password">Password: </label> </td>
                                            <td><h:inputSecret label="Password" value="#{UserBean.password}"/>
                                                <!--<input name="password" class="v-text" type="password" /> -->
                                            </td>
                                        </tr>
                                        <tr>
                                            
                                            <h:outputText rendered="#{CommonDBBean.connected}" value="Database Connected"/>
                                            <h:outputText rendered="#{!CommonDBBean.connected}" value="Database Not Connected"/>
                                            <td><h:commandButton action="#{UserBean.validate}" value="Login" /><!--<input name="submit" type="button" value="Submit" /> --></td>


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
