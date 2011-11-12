<%-- 
    Document   : Context
    Created on : Jun 1, 2011, 1:49:38 AM
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
    <rich:panel header="Task Contexts">
        <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
        <h:form>
            <rich:dataTable id="tbl" binding="#{TContextController.dataGrid}" value="#{TContextController.contexts}" var="ctx">
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Context Display Text"/>
                </f:facet>
                <rich:inplaceInput value="#{ctx.name}"/>
            </h:column>
        </rich:dataTable>
            <a4j:commandButton value="Update" action="#{TContextController.update}" reRender="tbl"/>
        </h:form>
    </rich:panel>
     <rich:modalPanel id="pnl">
                    <rich:panel header="Add New Context">
                    <h:form>
                        <h:inputText id="deptName" required="true" requiredMessage="Please Enter Department Name" value="#{TContext.name}"/>
                    <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                    <a4j:commandButton value="Save" reRender="tbl" action="#{TContext.save}"  />
                    <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                </h:form>
                </rich:panel>
                </rich:modalPanel>
</f:view>
