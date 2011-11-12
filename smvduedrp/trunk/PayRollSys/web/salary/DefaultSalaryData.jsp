<%-- 
    Document   : DefaultSalaryData
    Created on : Dec 24, 2010, 11:37:17 AM
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
            <rich:panel header="Default Salary Data">
                <h:form>
                    <h:panelGrid columns="3">
                        <h:outputText value="Salary Grade"/>
                        <h:selectOneMenu value="#{DefaultSalaryDataController.typecode}">
                            <f:selectItems value="#{SalaryGradeBean.grades}"/>
                        </h:selectOneMenu>
                        <a4j:commandButton reRender="pnl" value="Load Defaults" action="#{DefaultSalaryDataController.populate}"/>
                    </h:panelGrid>
                </h:form>
                <h:form>
                    <rich:panel id="pnl">
                        <h:inputHidden value="#{DefaultSalaryDataController.typecode}"/>
                        <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                        <rich:dataTable binding="#{DefaultSalaryDataController.grid}" value="#{DefaultSalaryDataController.heads}" var="sal">
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Salary Head"/>
                            </f:facet>
                            <h:outputText value="#{sal.name}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Under"/>
                            </f:facet>
                            <h:outputText value="#{sal.underString}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Default Value"/>
                            </f:facet>
                            <rich:inplaceInput value="#{sal.defaultValue}" />
                        </h:column>
                    </rich:dataTable>
                    </rich:panel>
                    <h:panelGrid columns="2">
                        <h:commandButton value="Update" action="#{DefaultSalaryDataController.update}"/>
                    
                    </h:panelGrid>
                </h:form>
            </rich:panel>
        </f:view>
    </body>
</html>
