<%-- 
    Document   : LeaveValue
    Created on : Jan 4, 2011, 2:08:33 AM
    Author     : Algox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <rich:panel header="Existing Leave Values">
                <h:panelGrid columns="2">
                 <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                 <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                 </h:panelGrid>
                <h:form>
                <rich:dataTable value="#{LeaveValueBean.values}" binding="#{LeaveValueBean.dataGrid}" var="lv">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Name"/>
                        </f:facet>
                        <rich:inplaceInput value="#{lv.name}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Value(Days)"/>
                        </f:facet>
                        <rich:inplaceInput value="#{lv.value}"/>
                    </h:column>                    
                </rich:dataTable>
                    <h:commandButton value="Update" action="#{LeaveValueBean.update}"/>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="pnl">
                <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Name"/>
                    <h:inputText value="#{LeaveValueBean.name}"/>
                </h:panelGrid>
                <h:panelGrid columns="2">
                    <h:outputText value="Value(Days)"/>
                    <h:inputText value="#{LeaveValueBean.value}"/>
                </h:panelGrid>
                 <h:panelGrid columns="2">
                     <h:commandButton value="Save" action="#{LeaveValueBean.save}"/>
                     <h:commandButton onclick="Richfaces.hideModalPanel('pnl');" value="Close"/>
                </h:panelGrid>
                </h:form>
            </rich:modalPanel>



        </f:view>
    </body>
</html>
