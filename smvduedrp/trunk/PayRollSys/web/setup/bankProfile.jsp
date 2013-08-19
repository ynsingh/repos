<%--
    Document   : bankProfile
    Created on : 13 Feb, 2012, 12:48:25 PM
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
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body>
            <rich:panel header="Add New Bank Profile">
                <h:panelGrid columns="1" id="bank">
                    <h:column>
                        <h:commandButton value="Add New Bank Profile" onclick="Richfaces.showModalPanel('bankd');"/>
                        <rich:messages  >
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                                <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                    </h:column>
                    <h:column>
                        <h:panelGrid columns="3">
                            <h:form>
                                <rich:dataTable id="bp" headerClass="headerStyle" rowClasses="rowStyle1" value="#{bankConttroler.bankprofiledetails}" binding="#{bankConttroler.dataGrid}" var="bad">
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Bank IFSC Code"/>
                                        </f:facet>
                                        <h:outputText value="#{bad.bankIFSCCode}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Bank Name"/>
                                        </f:facet>
                                        <rich:inplaceInput value="#{bad.bankName}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Bank Branch"/>
                                        </f:facet>
                                        <rich:inplaceInput value="#{bad.bankBranch}"/>
                                    </h:column>
                                </rich:dataTable>
                                <rich:panel>
                                    <a4j:commandButton value="Update" action="#{bankConttroler.updateBankRecords}"/>
                                </rich:panel>
                            </h:form>
                        </h:panelGrid>
                    </h:column>
                </h:panelGrid>
            </rich:panel>
            <rich:spacer height="10"/>
            <rich:modalPanel  style="height:300px;bgcolor:blue;" id="bankd" height="300" width="400">
                <rich:panel header="Add New Bank Profile">
                    <h:form>
                        <h:panelGrid columns="3">
                            <h:outputText value="Bank Name"/>
                            <h:inputText id="bn" value="#{bankProfile.bankName}" style="width:160px;" styleClass="fields"/>
                            <h:message for="bn"/>
                            <h:outputText value="Bank Address"/>
                            <h:inputTextarea id="ba" value="#{bankProfile.bankAddress}" style="width:160px;" styleClass="fields"/>
                            <h:message for="ba"/>
                            <h:outputText value="Bank IFSC Code"/>
                            <h:inputText id="bifsc" value="#{bankProfile.bankIFSCCode}" style="width:160px;" styleClass="fields"/>
                            <h:message for="bifsc"/>
                            <h:outputText value="Branch Name"/>
                            <h:inputText id="bna" value="#{bankProfile.bankBranch}" style="width:160px;" styleClass="fields"/>
                            <h:message for="bna"/>
                        </h:panelGrid>
                        <rich:panel>
                            <a4j:commandButton  value="Submit" action="#{bankProfile.saveData}" onclick="Richfaces.hideModalPanel('bankd');" reRender="bank"/>
                            <a4j:commandButton onclick="Richfaces.hideModalPanel('bank');" value="Close"/>
                        </rich:panel>
                    </h:form>
                </rich:panel>
            </rich:modalPanel>
        </body>
    </html>
</f:view>