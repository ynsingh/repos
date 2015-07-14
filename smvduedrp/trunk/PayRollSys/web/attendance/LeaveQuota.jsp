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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  Modified Date: 4 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
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
                <%--<a4j:keepAlive beanName="LeaveQuotaBean"/>--%>
                <h:form>
                    <rich:panel header="Configure Applicable Leaves Types and Leave Quota">
                        <h:panelGrid columns="3">
                        <h:outputText value="Employee Type"/>
                        <h:selectOneMenu  value="#{LeaveQuotaBean.empType}">
                            <f:selectItems value="#{EmployeeTypeBean.items}"/>
                        </h:selectOneMenu>
                        <a4j:commandButton reRender="plist" action="#{LeaveQuotaBean.populate}" value="Load"/>
                        <rich:messages layout="table" style="border:1;" >
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <rich:dataTable id="plist" value="#{LeaveQuotaBean.quotas}" binding="#{LeaveQuotaBean.dataGrid}" rowKeyVar="row"  var="head"  rows="10" style="width:1015px;">
                            <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Leave Type"/>
                            </f:facet>
                                <h:outputText value="#{head.leaveTypeName}"/>
                            </rich:column>
                            <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Quota"/>
                            </f:facet>
                                <rich:inplaceInput value="#{head.count}"/>
                            </rich:column>  
                            <rich:column>
                            <f:facet name="header">
                                <h:outputText value="Applicable "/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{head.selected}"/>
                            </rich:column>
                            <f:facet name="footer">
                            <rich:datascroller for="plist" page="5" />  
                            </f:facet>
                        </rich:dataTable>
                        </h:panelGrid>
                        <rich:separator/>
                        <a4j:commandButton value="Update" reRender="plist"  action="#{LeaveQuotaBean.update}"  />
                        <%--<h:commandButton value="Help" action="#{ETHBean.print}"  />--%>
                    </rich:panel>
                </h:form>
            </f:view>
        </div>
    </body>
</html>
