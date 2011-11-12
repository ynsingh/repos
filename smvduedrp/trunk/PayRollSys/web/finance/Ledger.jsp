<%-- 
    Document   : Ledger
    Created on : May 26, 2011, 1:08:02 PM
    Author     :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <rich:panel header="Ledgers">
        <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
        <rich:messages  >
            <f:facet name="infoMarker">
                <h:graphicImage url="/img/success.png"/>
            </f:facet>
            <f:facet name="errorMarker">
                <h:graphicImage url="/img/err.png"/>
            </f:facet>
        </rich:messages>

        <rich:dataTable id="tbl" value="#{LedgerController.ledgers}" binding="#{LedgerController.dataGrid}" var="ledger">
            <rich:column>
                <f:facet name="header">
                    <h:outputText value="Ledger Name"/>
                </f:facet>
                <h:outputText value="#{ledger.name}"/>
            </rich:column>
            <rich:column>
                <f:facet name="header">
                    <h:outputText value="Account Group"/>
                </f:facet>
                <h:outputText value="#{ledger.agName}"/>
            </rich:column>
            <rich:column>
                <f:facet name="header">
                    <h:outputText value="Ledger Type"/>
                </f:facet>
                <h:outputText value="#{ledger.typeName}"/>
            </rich:column>
        </rich:dataTable>
    </rich:panel>

    <rich:modalPanel autosized="true" id="pnl">
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Ledger Name"/>
                <h:inputText value="#{Ledger.name}"/>
                <h:outputText value="Under"/>
                <h:selectOneMenu value="#{Ledger.agId}">
                    <f:selectItems value="#{LedgerController.groupAsItem}"/>
                </h:selectOneMenu>
                <h:outputText value="Ledger Type"/>
                <h:selectOneMenu value="#{Ledger.type}">
                    <f:selectItem itemLabel="Income" itemValue="true"/>
                    <f:selectItem itemLabel="Expenses" itemValue="false"/>
                </h:selectOneMenu>
                <a4j:commandButton reRender="tbl" value="Save" action="#{Ledger.save}"/>
                <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                
            </h:panelGrid>
        </h:form>
    </rich:modalPanel>



</f:view>
