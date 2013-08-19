<%-- 
    Document   : brihaspatiMainLogin
    Created on : Jun 26, 2012, 3:14:45 PM
    Author     : ERP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <h:form>
                <h:panelGrid columns="1">
                    <rich:panel style="height:170px; width:1340px;">
                        <%
                                out.println("&context="+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
                        %>

                    </rich:panel>
                    <rich:panel style="height:430px;">
                        <f:facet name="header">
                            <h:outputText value="Confirm Your Registration From Brihaspati Server" style="font-size:18px;font-weight:bold;"/>
                        </f:facet>
                        <rich:panel style="margin-left:35%;margin-right:35%;width:30%;margin-top:100px;height:100px;">
                            <h:panelGrid columns="3">
                                <h:outputText value="Email ID"/>
                                <h:inputText id="pass" value="#{brihaspatiBean.emailId}"/>
                                <h:commandButton value="Proceed" action="#{brihaspatiBean.brihaspatiAuth}"/>
                                <h:messages/>
                            </h:panelGrid>
                        </rich:panel>
                    </rich:panel>
                </h:panelGrid>
            </h:form>
        </body>
    </html>
</f:view>