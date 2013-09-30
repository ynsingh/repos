<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
    Modified        : 27 Sep 2013, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
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
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/layout.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
              

    </head>
    <body class="subpage" id="">
        
        <div class="container_form">
            <f:view>
                <rich:panel header="Exising Heads">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                <h:form id="investmentheads">
                    <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                    <h:panelGrid columns="3">
                        <rich:dataTable  style="width:800px;" binding="#{InvestmentHeadBean.dataGrid}"
                                         value="#{InvestmentHeadBean.allHeads}" var="dept">
                            <rich:column width="5%">
                                <f:facet name="header">
                                    <h:outputText value="Code"/>
                                </f:facet>
                                <h:outputText value="#{dept.code}" />
                            </rich:column>
                            <rich:column width="20%">
                                <f:facet name="header">
                                    <h:outputText  value="Name"/>
                                </f:facet>
                                <rich:inplaceInput value="#{dept.name}" />
                            </rich:column>
                            <rich:column width="25%" sortable="true" sortBy="#{dept.underGroupName}"  id="group">
                                <f:facet name="header">
                                    <h:outputText  value="Under"/>
                                </f:facet>
                                <h:outputText value="#{dept.underGroupName}" />
                            </rich:column>
                            <rich:column width="50%">
                                <f:facet name="header">
                                    <h:outputText  value="Details"/>
                                </f:facet>
                                 <rich:inplaceInput value="#{dept.details}" />
                            </rich:column>
                        </rich:dataTable>
                        </h:panelGrid>
                    <a4j:commandButton action="#{InvestmentHeadBean.update}" value="Update"/>
                    
                </h:form>
                </rich:panel>
                <br />
                <hr/>
                <rich:modalPanel id="pnl">
                <h:form>
                    <rich:panel header="Add New Investment Head">

                        <h:panelGrid
                            columns="5"
                            styleClass="data_entry_form"
                            columnClasses="label,field">                            
                            <h:outputText styleClass="Label" value="New Head"/>
                            <h:inputText id="headName" required="true" requiredMessage="Enter New Investment Head Name" value="#{InvestmentHeadBean.name}"/>
                            <h:message for="headName" styleClass="error"/>
                    <h:outputText styleClass="Label" value="Under"/>
                    <h:selectOneMenu value="#{InvestmentHeadBean.underGroupCode}">
                        <f:selectItems value="#{InvestmentTypeController.asItems}"/>
                    </h:selectOneMenu>
                    <h:outputText styleClass="Label"  value="Tax Benefit"/>
                    <h:selectBooleanCheckbox id="txtBenefit" value="#{InvestmentHeadBean.benefit}">
                    </h:selectBooleanCheckbox>
                    <h:message for="txtBenefit" styleClass="error"/>
                    <h:outputText styleClass="Label"  value="Details"/>
                    <h:inputText  value="#{InvestmentHeadBean.details}"/>
                    <h:message for="detail" styleClass="error"/>
                    <h:commandButton value="Save" action="#{InvestmentHeadBean.save}" />
                    <h:commandButton onclick="Richfaces.hideModalPanel('pnl');" value="Close"/>
                    </h:panelGrid>
                    </rich:panel>
                </h:form>
                </rich:modalPanel>
            </f:view>
        </div>
    </body>
</html>
