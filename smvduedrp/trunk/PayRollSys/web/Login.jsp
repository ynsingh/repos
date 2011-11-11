<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
    Author          :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link rel="stylesheet" type="text/css" href="loginpage.css"/>
        <title>Payroll System | Login</title>
    </head>
        <f:view>
            <h:panelGrid style="margin-left:150px;margin-top:20px;" columns="2">
                <h:graphicImage alt="Payroll System" url="img/pls.png"/>
                <rich:panel style="height:480px;" header="">
                    
                    <h:form>
                        <rich:messages>
                            <f:facet name="errorMarker">
                                <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                        <rich:panel>
                            <h:graphicImage width="210px;" url="icons/lg.jpg"/>
                            <h:outputText value="Login"  style="align:left;font-size:20px;"/>
                            <h:panelGrid bgcolor="grey"  columns="2">
                            <h:outputText value="Organization Name"/>
                            <h:selectOneMenu value="#{UserBean.userOrgCode}">
                                <f:selectItems value="#{OrgController.items}"/>
                            </h:selectOneMenu>
                            <h:outputText value="Username"/>
                            <h:inputText label="User Name" value="#{UserBean.userName}" />
                            <h:outputText value=" Password:"/>
                            <h:inputSecret label="Password" value="#{UserBean.password}"/>
                            <h:outputText value=" >> "/>
                            <h:commandButton   action="#{UserBean.validate}" value="Login" />
                        </h:panelGrid>
                        </rich:panel>
                        <rich:separator/>
                        <rich:separator/>
                        <h:outputText value=""/>
                    </h:form>
                </rich:panel>
            </h:panelGrid>
              <rich:panel style="margin-left:150px;width:80%;background-color:red;">
                <h:panelGrid columns="4">
                <h:graphicImage width="50px;" height="50px;" />
                <h:outputText value="Developed by SMVD University"/>
                </h:panelGrid>
            </rich:panel>
             </f:view>
</html>
