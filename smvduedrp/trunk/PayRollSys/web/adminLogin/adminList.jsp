<%-- 
    Document   : adminList
    Created on : Dec 15, 2012, 7:49:39 PM
    Author     : KESU
GUI Modified date 21 July 2015, IITK , Om Prakash (omprakashkgp@gmail.com)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body>
            <rich:panel header="List Of Administrator" style="font-size:17px;font-weight:bold;height:320px; width:1290px">
                <h:form>
                    <h:panelGrid columns="1">
                        <%--   <a4j:commandButton value="Add New Administrator" onclick="Richfaces.showModalPanel('adnew');"/>--%>
                        <rich:panel style="margin-left:200px;margin-right:200px;border:none;" >
                            <rich:messages>
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>
                            <rich:dataTable style="font-size:14px;font-weight:bold;width:800px;" id="emp" rowKeyVar="row" value="#{OrgProfileBean.adminList}" binding="#{OrgProfileBean.dataGrid}"  var="ins">
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="User ID"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.adUserId}"/>
                                </h:column>
                                <%-- <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Adding Date"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.date}"/> 
                                </h:column>--%>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Check/UnCheck"/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{ins.status}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Status"/>
                                    </f:facet>
                                    <h:graphicImage value="/img/#{ins.imgUrl}"/>
                                </h:column>
                            </rich:dataTable> 
                            <rich:panel>
                             <a4j:commandButton value="Update" action="#{OrgProfileBean.updateAdmin}"/>
                            </rich:panel>
                        </rich:panel>
                        <%-- <rich:panel style="margin-left:100px;margin-right:100px;">
                            <a4j:commandButton value="Update" action="#{OrgProfileBean.updateAdmin}"/>
                        </rich:panel> --%>
                    </h:panelGrid>
                </h:form>
                <rich:modalPanel label="Add New Administrator" id="adnew"> 
                      <f:facet name="header">
                           <h:panelGroup>
                                <h:outputText value="Add New Administrator"></h:outputText>
                            </h:panelGroup>
                            </f:facet>
                      <f:facet name="controls">
                            <h:panelGroup>
                             <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                             <rich:componentControl for="adnew" attachTo="hidelink" operation="hide" event="onclick"/>
                            </h:panelGroup>
                      </f:facet>
                    <h:panelGrid columns="1" id="op">
                        <h:form>
                            <rich:panel style="border:none">
                                <rich:messages>
                                    <f:facet name="infoMarker">
                                        <h:graphicImage url="/img/success.png"/>
                                    </f:facet>
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/img/err.png"/>
                                    </f:facet>
                                </rich:messages>
                            </rich:panel>
                            <rich:panel>
                                <h:panelGrid columns="2">
                                    <h:outputText value="User ID"/>
                                    <h:inputText value="#{OrgProfileBean.adUserId}"/>
                                    <h:outputText value="Password"/>
                                    <h:inputSecret value="#{OrgProfileBean.adPassword}"/>
                                    <h:outputText value="Re Password"/>
                                    <h:inputSecret value="#{OrgProfileBean.adRePassword}"/>
                                    <a4j:commandButton value="Submit"  action="#{OrgProfileBean.saveAdmin}"/>
                                    <a4j:commandButton onclick="Richfaces.hideModalPanel('adnew');" value="Close"/>
                                </h:panelGrid>
                            </rich:panel>
                        </h:form>
                    </h:panelGrid>
                </rich:modalPanel>
            </rich:panel>
        </body>
    </html>
</f:view>