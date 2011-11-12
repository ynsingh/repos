<%-- 
    Document   : Project
    Created on : Apr 22, 2011, 4:17:13 PM
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
    <rich:panel header="Edit Project Details" style="width:100%;">

        <rich:messages  >
            <f:facet name="infoMarker">
                <h:graphicImage url="/img/success.png"/>
            </f:facet>
            <f:facet name="errorMarker">
                <h:graphicImage url="/img/err.png"/>
            </f:facet>
        </rich:messages>
        <h:form>
            <h:panelGrid columns="3">
                <h:outputText value="Project ID"/>
                <h:outputText id="prjid" value="#{Project.editId}"/>
                <h:message styleClass="error" for="prjid" tooltip="Employee Type"/>
                <h:outputText value="Project Name"/>
                <h:inputText id="prj" required="true" requiredMessage="Please Enter Project Name"
                             value="#{Project.name}"/>
                <h:message styleClass="error" for="prj" tooltip="Employee Type"/>
                <h:outputText value="Partner"/>
                <h:selectOneMenu id="partner" value="#{Project.partnerId}">
                    <f:selectItems value="#{ProjectController.partners}"/>
                </h:selectOneMenu>
                <h:message styleClass="error" for="partner" tooltip="Choose Partner name from Menu"/>
                <h:outputText value="Project Associate"/>
                <h:inputText   id="prja"value="#{Project.prjAssociate}" requiredMessage="Enter project Associate" immediate="true"/>
                <rich:suggestionbox for="prja" var="abc" fetchValue="#{abc.empId}"  suggestionAction="#{SearchBean.getSuggestion}">
                    <h:column>
                        <h:outputText value="#{abc.name}"/>
                    </h:column>
                    <h:column>
                        <h:outputText value="#{abc.empId}"/>
                    </h:column>
                </rich:suggestionbox>
                <h:outputText value="Project Architect"/>
                <h:inputText id="arch"value="#{Project.archName}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                <rich:suggestionbox for="arch" var="abc" fetchValue="#{abc.name}"  suggestionAction="#{SearchBean.getSuggestion}">
                    <h:column>
                        <h:outputText value="#{abc.name}"/>
                    </h:column>
                    <h:column>
                        <h:outputText value="#{abc.name}"/>
                    </h:column>
                </rich:suggestionbox>
                <h:outputText value="Team Leader"/>
                <h:inputText id="tl"value="#{Project.prjTl}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                <rich:suggestionbox for="tl" var="abc" fetchValue="#{abc.empId}"  suggestionAction="#{SearchBean.getSuggestion}">
                    <h:column>
                        <h:outputText value="#{abc.name}"/>
                    </h:column>
                    <h:column>
                        <h:outputText value="#{abc.empId}"/>
                    </h:column>
                </rich:suggestionbox>
                <h:outputText value="Project Budget"/>
                <h:inputText id="prjname" required="true"  value="#{Project.budget}"/>
                <h:message styleClass="error" for="prjname" tooltip="Project Budget"/>

                <h:outputText value="Start Date"/>
                <rich:calendar id="sdate" converter="dateConverter" showWeekDaysBar="false"
                               showFooter="false" styleClass="special"
                               datePattern="yyyy-MM-dd" popup="true"
                               required="true" value="#{Project.startDate}">

                </rich:calendar>
                <h:message for="sdate"/>
                <h:outputText value="Completion Date"/>
                <rich:calendar id="edate" converter="dateConverter" showWeekDaysBar="false"
                               showFooter="false" styleClass="special"
                               datePattern="yyyy-MM-dd" popup="true"
                               required="true" value="#{Project.endDate}">

                </rich:calendar>
                <h:message for="edate"/>

                <h:outputText id="prjd" value="Project Details"/>
                <h:inputText value="#{Project.detail}"/>
                <h:message styleClass="error" for="prjd" tooltip="Employee Type"/>
                <a4j:commandButton value="Save" reRender="tbl" action="#{Project.save}"  />
            </h:panelGrid>
        </h:form>
    </rich:panel>




</f:view>
