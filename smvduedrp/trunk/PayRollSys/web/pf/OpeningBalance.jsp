<%-- 
    Document   : PFConfig
    Created on : Apr 15, 2011, 11:09:59 AM
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
<rich:panel header="PF Configuration">
    <rich:messages  >
        <f:facet name="infoMarker">
            <h:graphicImage url="/img/success.png"/>
        </f:facet>
        <f:facet name="errorMarker">
            <h:graphicImage url="/img/err.png"/>
        </f:facet>
    </rich:messages>
    <h:form>
        <rich:dataTable value="#{PFAccountController.data}" binding="#{PFAccountController.dataGrid}" var="pa">
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Employee Code"/>
                </f:facet>
                <h:outputText value="#{pa.empCode}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Employee Name"/>
                </f:facet>
                <h:outputText value="#{pa.empName}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Employee's Opening Balance"/>
                </f:facet>
                <h:inputText value="#{pa.opBal}"/>
            </h:column>
            <%--  <h:column>      // employers opening balance not to be shown separately but included in OpBal
                <f:facet name="header">
                    <h:outputText value="Employer's Opening Balance"/>
                </f:facet>
                <h:inputText value="#{pa.employerBalance}"/>
            </h:column>  --%>
        </rich:dataTable>
        <a4j:commandButton value="Update" action="#{PFAccountController.update}"/>
    </h:form>

</rich:panel>
</f:view>
