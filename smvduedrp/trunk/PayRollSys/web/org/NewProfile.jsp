<%-- 
    Document   : NewProfile
    Created on : Dec 10, 2010, 8:26:42 PM
    Author     : Algox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <rich:panel header="Institution Profile">

        <h:panelGrid columns="2">
            <h:outputText value="Institute Name"/>
            <rich:inplaceInput value="#{OrgProfileBean.name}"/>
        </h:panelGrid>
        <h:panelGrid columns="2">
            <h:outputText value="Tag Line"/>
            <rich:inplaceInput value="#{OrgProfileBean.tagLine}"/>
        </h:panelGrid>
        <h:panelGrid columns="2">
            <h:outputText value="Institute Website"/>
            <rich:inplaceInput value="#{OrgProfileBean.website}"/>
        </h:panelGrid>
        <h:panelGrid columns="2">
            <h:outputText value="Institute Logo"/>
            <rich:fileUpload ajaxSingle="true" status="true" immediateUpload="true">
                <f:facet name="label">
                    <h:outputText value="{_KB}KB from {KB}KB uploaded --- {mm}:{ss}" />
                </f:facet>
            </rich:fileUpload>
        </h:panelGrid>

    </rich:panel>


</f:view>