    <%-- 
    Document   : LeaveTypes.jsp
    Created on : jun 11, 2014, 12:08:41 PM
    Author     : Seema Pal, Kishore Kumar Shukla  
    
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITK.
*
--%>
    


<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     </head>
    <f:view>
        <body class="subpage" id="">
        <a4j:keepAlive beanName="LeaveTypeBean" ajaxOnly="true"/> 
        <div class="container_form">
        <rich:panel header="Existing Leaves Types">
            <h:panelGrid columns="2">
                <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                <rich:messages>
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                    <f:facet name="errorMarker">
                        <h:graphicImage url="/img/err.png"/>
                    </f:facet>
                </rich:messages>
            </h:panelGrid>
            <h:form id="leaveForm">
            <h:panelGrid columns="3" id="leavetypeDetail">
            <a4j:status onstart="#{rich:component('statPane')}.show()" onstop="#{rich:component('statPane')}.hide()" />
            <rich:dataTable id="si" value="#{LeaveTypeBean.leaveValues}" binding="#{LeaveTypeBean.dataGrid}" var="ltt" rowKeyVar="row"  rows="8" style="width:100%;height:600px;" >

                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Leave Type"/>
                    </f:facet>
                    <%--<rich:inplaceInput value="#{ltt.name}" />--%>
                    <h:outputText value="#{ltt.name}" />
                </rich:column>

                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="No of Days"/>
                    </f:facet>
                    <%--<rich:inplaceInput value="#{ltt.value}" />--%>
                    <h:outputText value="#{ltt.value}" />
                </rich:column>

                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Checked/Unchecked"/>
                    </f:facet>
                    <h:selectBooleanCheckbox value="#{ltt.checked}"/>
                </rich:column>
                <%--<h:column>
                   <f:facet name="header">
                       <h:outputText value="Actions"/>
                   </f:facet>
                   <a4j:commandLink  styleClass="no-decor" ajaxSingle="true" oncomplete="#{rich:component('confirmPane')}.show()">
                       <h:graphicImage value="/img/delete.gif" alt="delete" />
                       <f:setPropertyActionListener value="#{row}" target="#{LeaveTypeBean.currentRecordindex}" />
                   </a4j:commandLink>
                   <a4j:commandLink styleClass="no-decor" ajaxSingle="true" reRender="editGrid" oncomplete="#{rich:component('editPnl')}.show()">
                   <h:graphicImage value="/img/edit.gif" alt="edit" />
                   <f:setPropertyActionListener value="#{row}" target="#{LeaveTypeBean.currentRecordindex}" />
                   <f:setPropertyActionListener value="#{ltt}" target="#{LeaveTypeBean.editedRecord}" />
                   </a4j:commandLink>
               </h:column>--%>

                <f:facet name="footer">
                    <rich:datascroller for="si" page="5" />
                </f:facet>
            </rich:dataTable>
                <rich:panel style="width:100%;height:600px;" header="Important Points related to Leave Types">
                   <h:panelGrid columns="1">
                       <h:outputText value="1. Leave Types Checked  its means allowed for this Institute"/>
                       <h:outputText value="2. Leave Types Unchecked its means not allowed for this Institute"/>
                   </h:panelGrid>
                </rich:panel>
            </h:panelGrid>
        
            <rich:modalPanel id="confirmPane" autosized="true"  width="250">
                    Are you sure you want to delete the row?
                    <a4j:commandButton value="Cancel" onclick="#{rich:component('confirmPane')}.hide(); return false;" />

                    <a4j:commandButton ajaxSingle="true" reRender="si,leavetypeDetail" value="Delete" action="#{LeaveTypeBean.deleteLType}" oncomplete="#{rich:component('confirmPane')}.hide();"/>

            </rich:modalPanel>
            <a4j:commandButton value="Apply" action="#{LeaveTypeBean.print}" reRender="si,leavetypeDetail"/>  
        </h:form>

        <rich:modalPanel   id="editPnl" autosized="true"  domElementAttachment="parent" width="400" height="170">
            <h:form>
                <rich:panel header="Edit Employee Service Record ">
                    <h:panelGrid id="editGrid">
                        <h:outputText value="Leave Type"/>
                        <h:inputText value="#{LeaveTypeBean.editedRecord.name}" />
                        <h:outputText value="Max Limit(In Days)"/>
                        <h:inputText value="#{LeaveTypeBean.editedRecord.value}" />

                        <h:outputText value="Checked/Unchecked"/>
                        <h:selectBooleanCheckbox value="#{LeaveTypeBean.editedRecord.checked}"/>
                        </h:panelGrid>
                        <a4j:commandButton  value="Store" action="#{LeaveTypeBean.updateRecord}" reRender="leaveForm" oncomplete="#{rich:component('editPnl')}.hide();" />
                    <a4j:commandButton value="Cancel" onclick="#{rich:component('editPnl')}.hide(); return false;" />
                 </rich:panel>
            </h:form>
        </rich:modalPanel>      
        <rich:modalPanel width="300" height="240" autosized="true" id="pnl">
            <f:facet name="header">
               <h:panelGroup>
                   <h:outputText value="Add New Leave Type"></h:outputText>
               </h:panelGroup>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                    <rich:componentControl for="pnl" attachTo="hidelink" operation="hide" event="onclick"/>
                </h:panelGroup>
            </f:facet>
            <h:form>
                    <h:panelGrid columns="2">
                    <h:outputText value="Leave Type"/>
                    <h:inputText value="#{LeaveTypeBean.name}" required="true" requiredMessage="Please Enter Leave Type" />
                    <h:outputText value="Max Limit(In Days)" />
                    <h:inputText value="#{LeaveTypeBean.value}" required="true" requiredMessage="Please Enter Max Limit " />
                     <%--                           
                    <h:outputText value="Checked/Unchecked"/>
                    <h:selectBooleanCheckbox value="#{LeaveTypeBean.checked}"/>--%>
                    </h:panelGrid>
                    <a4j:commandButton styleClass="panel" value="Save"  action="#{LeaveTypeBean.save}" reRender="leaveForm"  oncomplete="#{rich:component('pnl')}.hide();"/>
                    <a4j:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                </h:form>
            </rich:modalPanel>
        </rich:panel>
                </div>
        </body>
       </f:view>
   </html>
