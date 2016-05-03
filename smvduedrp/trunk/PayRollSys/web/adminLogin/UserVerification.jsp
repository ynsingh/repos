<%-- 
    Document   : UserVerification
    Created on : Apr 19, 2016, 3:18:34 PM
    Author     : Om Prakash <omprakashkgp@gmail.com> iitk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${param.vcode == null}">
            <jsp:forward page="error.jsp" />
        </c:if>

        <h:form>
            <h:panelGrid columns="1">
                <rich:panel style="height:170px; width:1340px;">
                    <h:panelGrid columns="2">
                        <h:panelGroup>
                            <h:graphicImage value="/img/#{UserVerificationBean.imageUrl}"/>
                            <h:outputText value="#{UserVerificationBean.msg}" style="font-style:Arial;font-size:18px;font-weight:bold;"/>
                        </h:panelGroup>
                        <%--  ${param.vcode}
                        ${param.email} --%>
                        <div> <a href="../Login.jsf"><h2> Click here for Login </h2> </a> </div>
                    </h:panelGrid>
                </rich:panel>
                <rich:panel style="height:430px;">
                    <h:outputLink value="#{UserVerificationBean.userEvent}"/>
                </rich:panel>
            </h:panelGrid>
        </h:form>
      
    </body>
</html>
</f:view>
