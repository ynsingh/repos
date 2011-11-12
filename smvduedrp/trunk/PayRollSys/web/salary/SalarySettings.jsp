<%-- 
    Document   : SalaryOption
    Created on : Jul 5, 2010, 1:54:00 AM
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


<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <h:form>
                    <rich:panel style="width:600px;" header="Configure Applicable Salary Heads">
                        <h:panelGrid columns="4">
                        <h:outputText value="Employee Type"/>
                        <h:selectOneMenu  value="#{EmployeeSalaryHeadControllerBean.empType}">
                            <f:selectItems value="#{EmployeeTypeBean.items}"/>
                        </h:selectOneMenu>
                        <a4j:commandButton reRender="plist" action="#{EmployeeSalaryHeadControllerBean.populate}" value="Load"/>
                        <rich:messages layout="table" style="border:1;" >
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>
                        </h:panelGrid>
                        <rich:dataTable id="plist" value="#{EmployeeSalaryHeadControllerBean.allHeads}"
                                        binding="#{EmployeeSalaryHeadControllerBean.datagrid}"  var="head">
                            <h:column>
                            <f:facet name="header">
                                <h:outputText value="Salary Head"/>
                            </f:facet>
                                <h:outputText value="#{head.name}"/>
                            </h:column>
                            
                             <h:column>
                            <f:facet name="header">
                                <h:outputText value="Applicable"/>
                            </f:facet>
                                 <h:selectBooleanCheckbox value="#{head.selected}"/>
                            </h:column>
                        </rich:dataTable>
                        <rich:separator/>
                        <h:commandButton value="Update" action="#{EmployeeSalaryHeadControllerBean.print}"  />
                        <h:commandButton value="Help" action="#{ETHBean.print}"  />
                    </rich:panel>
                </h:form>
            </f:view>
        </div>
    </body>
</html>
