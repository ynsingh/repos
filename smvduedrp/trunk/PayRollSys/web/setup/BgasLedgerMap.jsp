<%-- 
    Document   : BgasLedgerMap
    Created on : Jun 9, 2016, 1:40:42 PM
    Author     : Manorama Pal (palseema30@gmail.com)
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <f:view>
        <rich:panel header="Map SalaryHead with  BGAS Ledger">
            <h:form>
                <rich:panel id="pnlload">
                <h:panelGrid columns="3">
                    <h:outputText value="Chart of Account"/>
                    <h:selectOneMenu required="true" value="#{LedgerMapControllerBean.coaFormat}" valueChangeListener="#{LedgerMapControllerBean.coaformatChanged}">
                        <f:selectItem itemValue="MHRD" itemLabel="MHRD Format 2015"/>
                        <f:selectItem itemValue="Minimal" itemLabel="Minimal"/>
                        <f:selectItem itemValue="Corporate" itemLabel="Corporate Format"/>
                    </h:selectOneMenu>
                    <a4j:commandButton reRender="pnl" value="Load " action="#{LedgerMapControllerBean.populate}"/>
                </h:panelGrid>
            </rich:panel> 
            </h:form>
            <h:panelGrid columns="2">
                <rich:messages>
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                </rich:messages>
            </h:panelGrid>
            <rich:separator  style="width:100%;" /><br/>
            <h:form>
                <rich:panel id="pnl">
                    <h:inputHidden value="#{LedgerMapControllerBean.coaFormat}"/>   
                   <%-- ${LedgerMapControllerBean.coaFormat}  ............... --%>
                    <h:panelGrid columns="2">
                        <h:column>
                            <h:commandButton action="#{LedgerMapControllerBean.mapLedgers}" value="Update"/>&nbsp;&nbsp;
                        </h:column>
                        <br/>  
                        <rich:dataTable id="headTable" binding="#{LedgerMapControllerBean.dataGrid}" style="width:70%;"
                                    value="#{LedgerMapControllerBean.heads}" var="heads" ajaxKeys="#{LedgerMapControllerBean.ajaxKeys}" >
                            
                            
                         
                           <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Head Code"/>
                                </f:facet>
                                <rich:inplaceInput value="#{heads.shcode}" />
                            </h:column>
                            
                            <h:column>
                                                          
                                <f:facet name="header">
                                    <h:outputText value="Head Name"/>
                                </f:facet>
                                <rich:inplaceInput value="#{heads.name}" />
                            </h:column>

                            <h:column>
                                <f:facet name="header">
                                <h:outputText value="Short Name"/>
                                </f:facet>
                                <rich:inplaceInput value="#{heads.alias}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                <h:outputText value="Ledger Code"/>
                                </f:facet>
                                <h:outputText value="#{heads.ledgerCode}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText style="width:100px;" value="Income"/>
                                </f:facet>
                                <h:selectBooleanCheckbox value="#{heads.under}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Formula"/>
                                </f:facet>
                                <h:selectBooleanCheckbox value="#{heads.calculationType}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Taxable"/>
                                </f:facet>
                                <h:selectBooleanCheckbox value="#{heads.type}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                <h:outputText value="Linked"/>
                                </f:facet>
                                <h:selectBooleanCheckbox disabled="true" readonly="true" value="#{heads.special}"/>
                            </h:column>
                        
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Map Heads with BGAS"/>
                                </f:facet>
                                <h:selectOneMenu value="#{heads.bgasLedger}">
                                <f:selectItem itemLabel="Select Ledger"/>
                                <f:selectItems  value="#{LedgerMapControllerBean.arrayAsItem}"/>
                                </h:selectOneMenu>
                                <h:message for="headmsg" tooltip=" Select From Choices*"/>
                            </h:column>
                      
                        </rich:dataTable>

                </h:panelGrid>
                <h:column> 
                <h:commandButton action="#{LedgerMapControllerBean.update}" value="Update"/>&nbsp;&nbsp;    
                <%--<c:choose>  
                 <c:when test="${not empty LedgerMapControllerBean.coaFormat}">
                <h:commandButton action="#{LedgerMapControllerBean.update}" value="Update"/>&nbsp;&nbsp;
                <h:commandButton action="#{LedgerMapControllerBean.mapLedgers}" value="MapLedger"/>
                </c:when>
                <c:otherwise>   
                <h:commandButton action="#{LedgerMapControllerBean.mapLedgers}" value="MapLedger"/>
                
                </c:otherwise>
                </c:choose>--%>
                </h:column>
            </rich:panel>    
            </h:form>
        </rich:panel>
       </f:view>           
    </body>
</html>
