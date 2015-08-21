<%-- 
    Document   : AdminEmailConfig
    Created on : Dec 18, 2012, 6:31:38 AM
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
            <rich:panel header="List Of Email Configuration" style="font-size:17px;font-weight:bold; height:320px; width:1290px " >

                <h:panelGrid columns="1" id="adminemail">
                    <a4j:commandButton onclick="Richfaces.showModalPanel('adnew');" value="Add New Admin EmailId Configration"/>
                    <rich:panel style="margin-left:150px;margin-right:150px;border:none;">
                        <rich:messages>
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                        </rich:messages>
                        <h:form>
                            <rich:dataTable style="font-size:14px;font-weight:bold;width:900px;" rows="9" value="#{OrgProfileBean.adminEmailIdList}" binding="#{OrgProfileBean.dataGrid2}"  var="ins">
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Email ID"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.email}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Check/Uncheck"/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{ins.status}"/> 
                                </h:column>
                            </rich:dataTable>
                            <rich:panel>
                                <a4j:commandButton value="Update" action="#{OrgProfileBean.updateAdminEmail}"/>
                            </rich:panel>
                        </h:form>
                    </rich:panel>
                </h:panelGrid>
                <rich:modalPanel label="Add New Administrator" id="adnew" >
                     <f:facet name="header">
                            <h:panelGroup>
                                <h:outputText value="Add New Admin EmailId Configration"></h:outputText>
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
                                    <h:inputText value="#{OrgProfileBean.email}"/>
                                    <h:outputText value="Password"/>
                                    <h:inputSecret value="#{OrgProfileBean.adPassword}"/>
                                    <h:outputText value="Re Password"/>
                                    <h:inputSecret value="#{OrgProfileBean.adRePassword}"/>
                                    <a4j:commandButton reRender="adminemail" value="Submit"  action="#{OrgProfileBean.saveAdminEmailConfig}"/>
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