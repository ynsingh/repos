<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<f:view>
    <rich:dataTable value="#{PortalAccessController.acl}" var="acl">
        <h:column>
            <f:facet name="header">
                <h:outputText value="Employee Code"/>
            </f:facet>
            <h:outputText style="#{acl.style}" value="#{acl.code}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Employee Name"/>
            </f:facet>
            <h:outputText value="#{acl.name}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Department"/>
            </f:facet>
            <h:outputText value="#{acl.department}"/>
        </h:column>
        <h:column>
            <f:facet name="header">
                <h:outputText value="Login ID"/>
            </f:facet>
            <h:outputText value="#{acl.loginId}"/>
        </h:column>
         <h:column>
            <f:facet name="header">
                <h:outputText value="Access Allowed"/>
            </f:facet>
             <h:selectBooleanCheckbox value="#{acl.allowed}"/>
        </h:column>
    </rich:dataTable>
</f:view>