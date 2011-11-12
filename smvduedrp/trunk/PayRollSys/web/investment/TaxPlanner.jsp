<%-- 
    Document   : TaxPlanner
    Created on : Mar 15, 2011, 10:13:20 AM
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
<!DOCTYPE html>


<f:view>

    <h:form>
        <a4j
            <h:panelGrid columns="4">
                <h:outputText value="Employee Code"/>
                <h:inputText id="empcCode" value="#{TaxPlanController.empCode}"/>
                <rich:suggestionbox for="empcCode" var="abc" fetchValue="#{abc.code}"  
                                    suggestionAction="#{SearchBean.getSuggestion}">
                    <h:column>
                        <h:outputText value="#{abc.name}"/>
                    </h:column>
                    <h:column>
                        <h:outputText value="#{abc.code}"/>
                    </h:column>
                </rich:suggestionbox>
                <a4j:commandButton value="Submit"  action="#{TaxPlanController.init}" reRender="empMonth,tp,monthTable"/>
                <rich:panel header="Tax Installment Details">
                      
                    <h:panelGrid id="tp" columns="6">
                         <rich:messages layout="table" style="border:1;" >
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                        <h:outputText style="font-size:14px;" value="Net Tax"/>
                        <h:outputText style="font-size:14px;" value="#{TaxPlanController.netTax}"/>
                    </h:panelGrid>
                </rich:panel>


            </h:panelGrid>   

            <rich:panel id="empMonth" header="Select Months">
                <rich:dataTable id="monthTable" binding="#{TaxPlanController.datagrid}" value="#{TaxPlanController.extendedMonths}" var="month">
                    <rich:column width="45%">
                        <f:facet name="header">
                            <h:outputText value="Month"/>
                        </f:facet>
                        <h:outputText value="#{month.name}" />
                    </rich:column>
                    <rich:column >
                        <f:facet name="header">
                            <h:outputText value="Tax Amount"/>
                        </f:facet>
                        <h:inputText value="#{month.amount}" />
                    </rich:column>
                     <f:facet name="footer">
                         <h:outputText value="Total Planned Amount #{TaxPlanController.totalPlanned} "/>
                        </f:facet>
                        

                </rich:dataTable>
                <a4j:commandButton value="Update" action="#{TaxPlanController.update}" reRender="tp,monthTable"/>
            </rich:panel>
            
        </h:form>

    </f:view>
