<%-- 
    Document   : SearchEmployee
    Created on : Jul 8, 2010, 3:23:09 PM
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

    <body>

        <f:view>

            <rich:modalPanel style="height:300px;bgcolor:blue;" id="simplesearch">
                <h:form id="ssearch">
                    <rich:panel header="Search Options">
                        <h:panelGrid columns="2">
                            <h:outputText value="Employee Name"/>
                            <h:inputText value="#{SearchBean.name}"/>
                            <h:outputText value="Department"/>
                            <h:selectOneMenu styleClass="combo" value="#{SearchBean.deptCode}">
                                <f:selectItem itemLabel="All" itemValue="0"/>
                                <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Designation"/>
                            <h:selectOneMenu styleClass="combo" value="#{SearchBean.desigCode}">
                                <f:selectItem itemLabel="All" itemValue="0"/>
                                <f:selectItems value="#{DesignationBean.arrayAsItem}"/>
                            </h:selectOneMenu>

                            <h:outputText value="Employee Type"/>
                            <h:selectOneMenu styleClass="combo" value="#{SearchBean.typeCode}">
                                <f:selectItem itemLabel="All" itemValue="0"/>
                                <f:selectItems value="#{EmployeeTypeBean.items}"/>
                            </h:selectOneMenu>
                        </h:panelGrid>
                    </rich:panel>
                    <h:panelGrid columns="2">
                        <a4j:commandButton reRender="result"  onclick="submit();" value="Search"/>
                        <h:commandButton onclick="Richfaces.hideModalPanel('simplesearch');" value="Cancel"/>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>

            <rich:panel  header="Existing Employees">
                <h:panelGrid columns="3">
                    <h:commandButton onclick="Richfaces.showModalPanel('simplesearch');" value="Filter"/>
                    <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                    </rich:messages>
                </h:panelGrid>
                <h:form id="myform">
                    <rich:dataTable id="result" value="#{SearchBean.all}" var="emp">
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Sr.No"/>
                            </f:facet>
                            <h:outputText value="#{emp.srNo}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Code"/>
                            </f:facet>
                            <h:outputText value="#{emp.code}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value="#{emp.name}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Department"/>
                            </f:facet>
                            <h:outputText value="#{emp.deptName}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Designation "/>
                            </f:facet>
                            <h:outputText value="#{emp.desigName}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Employee Type"/>
                            </f:facet>
                            <h:outputText value="#{emp.typeName}" />
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Phone"/>
                            </f:facet>
                            <h:outputText value="#{emp.phone}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="E mail"/>
                            </f:facet>
                            <h:outputText value="#{emp.email}" />
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Basic"/>
                            </f:facet>
                            <h:outputText value="#{emp.currentBasic}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Joining Date"/>
                            </f:facet>
                            <h:outputText value="#{emp.doj}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Status"></h:outputText>
                            </f:facet>
                            <h:graphicImage value="#{emp.statusI}"></h:graphicImage>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Delete"></h:outputText>
                            </f:facet>
                            <a4j:commandButton  disabled="#{emp.event}" value="#{emp.buttonValue}"></a4j:commandButton>
                        </h:column>
                    </rich:dataTable>
                </h:form>
            </rich:panel>
        </f:view>

    </body>
</html>
