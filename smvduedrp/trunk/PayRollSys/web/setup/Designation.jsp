<%--
    Document        : Designation.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 4:03 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Designation</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <rich:panel header="Existing Designations">
                    <h:panelGrid columns="2">
                        <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                        <rich:messages  >
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                                <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                    </h:panelGrid>
                    <h:form >
                        <h:panelGrid  columns="2">
                            <rich:dataTable  binding="#{DesignationControllerBean.dataGrid}" 
                                             id="designation" value="#{DesignationControllerBean.designations}" 
                                             var="desig" border="1">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText  value="Name"/>
                                    </f:facet>
                                    <rich:inplaceInput value="#{desig.name}" />
                                </h:column>
                            </rich:dataTable>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:commandButton value="Update" action="#{DesignationControllerBean.update}"/>
                        </h:panelGrid>
                    </h:form>
                </rich:panel>
                <rich:modalPanel id="pnl">
                    <f:facet name="header">
                        <h:outputText value=""/>
                    </f:facet>
                    <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/img/close.png" id="hidelink"/>
                            <rich:componentControl for="pnl" attachTo="hidelink" operation="hide" event="onclick"/>
                        </h:panelGroup>
                    </f:facet>
                    <rich:panel>
                        <h:panelGrid columns="1">
                            <rich:panel>
                                <rich:messages  >
                                    <f:facet name="infoMarker">
                                        <h:graphicImage url="/img/success.png"/>
                                    </f:facet>
                                    <f:facet name="errorMarker">
                                        <h:graphicImage url="/img/err.png"/>
                                    </f:facet>
                                </rich:messages>
                            </rich:panel>
                            <rich:panel>
                                <h:form>
                                    <h:inputText id="desigName" required="true" requiredMessage="Please Enter New Designation Name" value="#{DesignationBean.name}"/>
                                    <h:message for="desigName" styleClass="error"/>
                                    <a4j:commandButton value="Save" reRender="designation" action="#{DesignationBean.save}"  />
                                    <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                                </h:form>
                            </rich:panel>
                        </h:panelGrid>
                    </rich:panel>
                </rich:modalPanel>
            </f:view>
        </div>
    </body>
</html>
