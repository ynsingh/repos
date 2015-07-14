<%-- 
    Document   : LeaveDetail
    Created on : july 7, 2014, 2:09:17 AM
    Author     :  *  Copyright (c) 2014  IIT Kanpur.
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IIT Kanpur
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
        
        <script type="text/javascript">
            function activate()
            {
                
                 var x = document.getElementById("historyForm:empCode").value;
                if(x!=="")
                {
                    document.getElementById("historyForm:ldbtn").disabled=false;
                    
                }
                else
                {
                    document.getElementById("historyForm:ldbtn").disabled=true;
                    
                }
                
            }
     </script>    
    </head>
    <body>
        <f:view>
            <a4j:keepAlive beanName="LeaveQuotaBean" ajaxOnly="true"/> 
            <rich:panel header="Employee's Leave Details">
            <h:panelGrid columns="2">
             <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
            </h:panelGrid>
            <h:form id="historyForm">
            <h:panelGrid columns="1">
             <h:column>
                  <rich:toolTip for="empName" value="Standard Charecter(e.g @,#,$,...etc) Will Not Be Accepted, Length Should Be Three Charecter"/>
                                <rich:toolTip for="seci" value="Check , If Employee Is Senior Citizen Otherwise Uncheck"/>
                                <h:panelGroup>
                                  <h:panelGrid columns="2"  style="width:100%;">
                                            <h:column>
                                                <h:panelGrid columns="8">
                                                    <h:outputText  styleClass="Label" value="Employee Code"/>
                                                    <h:inputText  onblur="activate()" id="empCode" value="#{LeaveQuotaBean.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                                    <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                                        <h:column>
                                                            <h:outputText value="#{abc.name}"/>
                                                        </h:column>
                                                        <h:column>
                                                            <h:outputText value="#{abc.code}"/>
                                                        </h:column>
                                                    </rich:suggestionbox>
                                                    <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>
                                                    <a4j:commandButton id="ldbtn" styleClass="panel" action="#{LeaveQuotaBean.loadleaveDetail}" value="Load"  reRender="si,leavetypeDetail" />
                                             </h:panelGrid>
                                            </h:column>
                                        </h:panelGrid>
                                      </h:panelGroup>
                                </h:column>
                                </h:panelGrid>
                <h:panelGrid columns="3" id="leavetypeDetail">
                <rich:dataTable  id= "si" value="#{LeaveQuotaBean.allotedQuota}" binding="#{LeaveQuotaBean.dataGrid}" var="leave" rowKeyVar="row"  rows="10"  style="width:1015px;">
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="LeaveType"/>
                    </f:facet>
                    <h:outputText value="#{leave.leaveTypeName}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Alloted Leave"/>
                    </f:facet>
                    <h:outputText value="#{leave.count}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Balance Leave"/>
                    </f:facet>
                    <h:outputText value="#{leave.balanceCount}"/>
                </rich:column>
                <f:facet name="footer">
                   <rich:datascroller for="si" page="5" />
                   </f:facet>
               </rich:dataTable>
                </h:panelGrid>
             </h:form >
            </rich:panel>
           </f:view>
    </body>
</html>
