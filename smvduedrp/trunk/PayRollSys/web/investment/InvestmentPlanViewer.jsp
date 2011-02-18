<%-- 
    Document   : SearchEmployee
    Created on : Jul 8, 2010, 3:23:09 PM
    Author     : Algox
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <f:view>
            <h:panelGrid columns="2">
            <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
            <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
            </h:panelGrid>
            <rich:panel header="Employee's Annual Investment Plan">
                <h:form>
                    <rich:dataTable id="table" width="100%"  border="1"  value="#{InvestmentPlanBean.allPlanes}" var="emp">
                    <h:column>
		    <f:facet name="header" >
                        <h:outputText value="Plan ID"/>
		    </f:facet>
		    <h:outputText value="#{emp.planId}" />
		  </h:column>
                  <rich:column sortable="true">
		    <f:facet name="header">
                        <h:outputText value="Investment Head"/>
		    </f:facet>
		    <h:outputText value="#{emp.headId.name}" />
		  </rich:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Amount"/>
		    </f:facet>
		    <h:outputText value="#{emp.amount}" />
		  </h:column>
                  <rich:column sortable="true">
		    <f:facet name="header">
                        <h:outputText value="Employee Name"/>
		    </f:facet>
		    <h:outputText value="#{emp.empname}" />
		  </rich:column>
                         <h:column>
		    <f:facet name="header">
                        <h:outputText value="Employee Code"/>
		    </f:facet>
		    <h:outputText value="#{emp.empCode}" />
		  </h:column>
                    <h:column>
		    <f:facet name="header">
                        <h:outputText value="Remove "/>
		    </f:facet>
                        <a4j:commandButton data="#{emp.planId}"  value="Remove" reRender="table" action="#{InvestmentPlanBean.delete}" >
                            <f:param name="pid" value="#{emp.planId}"/>
                        </a4j:commandButton>
		  </h:column>
                    <h:column>
		    <f:facet name="header">
                        <h:outputText value="Edit "/>
		    </f:facet>
                        <a4j:commandButton data="#{emp.planId}" value="Edit" />
		  </h:column>
                  
                </rich:dataTable>
            </h:form>
            </rich:panel>


            <rich:modalPanel id="pnl">               
                <rich:panel header="Add New Investment Plan">
                     <h:form>
                    <h:panelGrid columns="2">
                        <h:outputText value="Plan Head"/>
                        <h:selectOneMenu  id="empGender" value="#{InvestmentPlanBean.planCode}">
                            <f:selectItems value="#{InvestmentHeadBean.selectedItems}"/>
                         </h:selectOneMenu>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <h:outputText value="Employee"/>
                        <h:selectOneMenu  id="empCode" value="#{InvestmentPlanBean.empId}">
                            <f:selectItems value="#{EmployeeBean.empIdentity}"/>
                         </h:selectOneMenu>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <h:outputText value="Amount(Rs.)"/>
                        <h:inputText value="#{InvestmentPlanBean.amount}"/>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <h:commandButton action="#{InvestmentPlanBean.save}" value="Save"/>
                        <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                    </h:panelGrid>
                    </h:form>
                </rich:panel>
                
            </rich:modalPanel>
        </f:view>

    </body>
</html>
