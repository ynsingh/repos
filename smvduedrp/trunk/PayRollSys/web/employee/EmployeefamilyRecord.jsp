<%-- 
    Document   : EmployeefamilyRecord
    Created on : Mar 27, 2014, 12:25:01 PM
    Author     : guest

*  All Rights Reserved.
*  Redistribution and use in source and binary forms, with or
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
*  Modified Date: 4 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Family Record</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/components.css"/>
        <link rel="stylesheet" type="text/css" href="css/Form.css"/>
        <link rel="stylesheet" type="text/css" href="../css/richpanel.css"/>
        <script type="text/javascript">
            function activate()
            {
                var x = document.getElementById("profileForm:empCode").value;
                if(x!=="")
                {
                    document.getElementById("profileForm:ldbtn").disabled=false;
                    document.getElementById("profileForm:ldbtn1").disabled=false;
                    document.getElementById("profileForm:ldbtn2").disabled=false;
                    
                    document.getElementById("addfamilyrecord:btnSave").disabled=false;
                    document.getElementById("addfamilyrecord:btnReset").disabled=false;
                }
                else
                {
                    document.getElementById("profileForm:ldbtn").disabled=true;
                    document.getElementById("profileForm:ldbtn1").disabled=false;
                    document.getElementById("profileForm:ldbtn2").disabled=false;
                    document.getElementById("addfamilyrecord:btnSave").disabled=false;
                    document.getElementById("addfamilyrecord:btnReset").disabled=false;
                }
            }
            
                             
     </script>
    </head>
    <f:view>
        <body class="subpage" id="">
            <a4j:keepAlive beanName="EmployeeBean" ajaxOnly="true"/>
            <div class="container_form">
                <rich:panel id="addemplfamilydetail" header="Employee Family Details" styleClass="form" style="width:auto;">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                    <rich:messages  >
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    <h:form id="profileForm">
                       <h:panelGrid columns="1">
                        
                        <h:column>
                            
                                <rich:toolTip for="empName" value="Standard Charecter(e.g @,#,$,...etc) Will Not Be Accepted, Length Should Be Three Charecter"/>
                                <rich:toolTip for="seci" value="Check , If Employee Is Senior Citizen Otherwise Uncheck"/>
                                <h:panelGroup>
                                    <%--<rich:panel style="width:100%;">--%>
                                        <h:panelGrid columns="2"  style="width:100%;">
                                            <h:column>
                                                <h:panelGrid columns="8">
                                                    <h:outputText  styleClass="Label" value="Employee Code"/>
                                                    <h:inputText  onblur="activate()" id="empCode" value="#{EmployeeBean.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                                    <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                                        <h:column>
                                                            <h:outputText value="#{abc.name}"/>
                                                        </h:column>
                                                        <h:column>
                                                            <h:outputText value="#{abc.code}"/>
                                                        </h:column>
                                                    </rich:suggestionbox>
                                                    <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>
                                                    <a4j:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeBean.loadfamilyDetail}" value="Load Record"  reRender="si,addemplfamilydetail,empfmlyDetail" />
                                             </h:panelGrid>
                                            </h:column>
                                        </h:panelGrid>
                                      </h:panelGroup>
                                </h:column>
                                </h:panelGrid>
                                
                                <h:panelGrid  id="empfmlyDetail" columns="6"  columnClasses="label,field">
                                  <a4j:status onstart="#{rich:component('statPane')}.show()" onstop="#{rich:component('statPane')}.hide()" />
                                  <rich:dataTable id="si"  value="#{EmployeeBean.allFamilyRecord}" binding="#{EmployeeBean.dataGrid}" var="empfr" rowKeyVar="row" rows="5" style="width:1200px;">
                                  <rich:column>
                                    <f:facet name="header" >
                                    <h:outputText value="Sr.No"/>
                                    </f:facet>
                                    <h:outputText value="#{empfr.srNo}" />
                                    </rich:column>   
                                   <rich:column  width="20%">
                                <f:facet name="header">
                                    <h:outputText styleClass="Label" value="Family Member Name"/>
                                </f:facet>
                                  <%--  <rich:inplaceInput value="#{empfr.memberName}" />--%>
                                  <h:outputText value="#{empfr.memberName}"/>
                                </rich:column>
                                 <rich:column width="15%">
                                <f:facet name="header">
                                <h:outputText value="Relation"/>
                                </f:facet>
                                     <h:selectOneMenu value="#{empfr.relation}">
                                    <f:selectItem itemLabel="Father" itemValue="Father"/>
                                    <f:selectItem itemLabel="Mother" itemValue="Mother"/>
                                    <f:selectItem itemLabel="Wife" itemValue="Wife"/>
                                    <f:selectItem itemLabel="Husband" itemValue="Husband"/>
                                    <f:selectItem itemLabel="Son" itemValue="Son"/>
                                    <f:selectItem itemLabel="Daughter" itemValue="Daughter"/>
                                </h:selectOneMenu>
                                </rich:column>
                                <rich:column width="15%" >
                                <f:facet name="header">
                               <h:outputText styleClass="Label" value="Date of Birth"/>
                               </f:facet>
                                <h:outputText  value="#{empfr.dob}" />
                                    
                                   </rich:column> 
                                  <rich:column width="10%" >
                                <f:facet name="header">
                                <h:outputText value="Dependent"/>
                                </f:facet>
                                      <h:selectOneMenu value="#{empfr.dependent}">
                                    <f:selectItem itemLabel="Yes" itemValue="Yes"/>
                                    <f:selectItem itemLabel="No" itemValue="No"/>
                                </h:selectOneMenu>
                                </rich:column>
                                  <rich:column width="15%">
                                <f:facet name="header">
                                <h:outputText value="Whether Employed"/>
                                </f:facet>
                                      <h:selectOneMenu value="#{empfr.whetherEmployed}">
                                    <f:selectItem itemLabel="State" itemValue="State"/>
                                    <f:selectItem itemLabel="Center" itemValue="Center"/>
                                    <f:selectItem itemLabel="Other" itemValue="Other"/>
                                    <f:selectItem itemLabel="Unemployed" itemValue="Unemployed"/>
                                </h:selectOneMenu>
                                </rich:column>
                                <rich:column width="15%">
                                <f:facet name="header">
                                <h:outputText value="Name of the Department"/>
                                </f:facet>
                                 <h:outputText value="#{empfr.deptName}" />
                                 </rich:column>
                                 <rich:column>
                                    <f:facet name="header">
                                     <h:outputText value="Actions"/>
                                    </f:facet>
                                    <a4j:commandLink  styleClass="no-decor" ajaxSingle="true" oncomplete="#{rich:component('confirmPane')}.show()">
                                        <h:graphicImage value="/img/delete.gif" alt="delete" />
                                        <f:setPropertyActionListener value="#{row}" target="#{EmployeeBean.currentRecordindex}" />
                                    </a4j:commandLink>
                                    <a4j:commandLink styleClass="no-decor" ajaxSingle="true" reRender="editGrid" oncomplete="#{rich:component('editPane')}.show()">
                                        <h:graphicImage value="/img/edit.gif" alt="edit" />
                                        <f:setPropertyActionListener value="#{row}" target="#{EmployeeBean.currentRecordindex}" />
                                        <f:setPropertyActionListener value="#{empfr}" target="#{EmployeeBean.editedRecord}" />
                            
                                    </a4j:commandLink>
                                 </rich:column>
                                 <f:facet name="footer">
                                <rich:datascroller for="si" page="5" />  
                                </f:facet>
                               </rich:dataTable>                          
                                </h:panelGrid>
                       
                                 <rich:modalPanel id="confirmPane" autosized="true"  width="250">
                                Are you sure you want to delete the row?
                                <a4j:commandButton value="Cancel" onclick="#{rich:component('confirmPane')}.hide(); return false;" />
                                
                                <a4j:commandButton id="ldbtn1" ajaxSingle="true" reRender="si,empfmlyDetail" value="Delete" action="#{EmployeeBean.deleteRecord}" oncomplete="#{rich:component('confirmPane')}.hide();"/>
                                </rich:modalPanel>
                            <%--
                           <h:panelGroup>
                               <a4j:commandButton  id="ldbtn2" value="Update" action="#{EmployeeBean.updateRecord}"/>
                            </h:panelGroup>
                            <h:panelGroup>
                               <a4j:commandButton  id="ldbtn1" value="Delete" action="#{EmployeeBean.deleteRecord}"/>
                               
                            </h:panelGroup>--%>
                             </h:form>
                                
                             
                            <rich:modalPanel id="editPane" autosized="true"  domElementAttachment="parent" width="400" height="170">
                            <h:form>
                                <rich:panel header="Edit Family Record ">
                            <h:panelGrid id="editGrid">
                                
                            <h:outputText styleClass="Label" value="Family Memeber Name"/>
                                    <h:inputText id="empfrName" required="true"
                                                 requiredMessage="Enter Member Name."
                                                 styleClass="fields" value="#{EmployeeBean.editedRecord.memberName}" validator="#{allValidator.validateUserName}">
                                        <rich:ajaxValidator event="onblur"></rich:ajaxValidator>
                                    </h:inputText>
                                    <h:message styleClass="error" for="empfrName" tooltip="Enter Family Member Name."/>
                                    <h:outputText value="Relation"/>
                                    <h:selectOneMenu  id="relation" value="#{EmployeeBean.editedRecord.relation}">
                                        <f:selectItem itemLabel="Father" itemValue="Father"/>
                                        <f:selectItem itemLabel="Mother" itemValue="Mother"/>
                                        <f:selectItem itemValue="Husband" itemLabel="Husband"/>
                                        <f:selectItem itemValue="Wife" itemLabel="Wife"/>
                                        <f:selectItem itemValue="Daughter" itemLabel="Daughter"/>
                                        <f:selectItem itemValue="Son" itemLabel="Son"/>
                                    </h:selectOneMenu>
                                    <h:message for="relation" tooltip="Employee's Relation."/>
                    
                                <h:outputText styleClass="Label" value="Date of Birth"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empDob" popup="true"
                                                   required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                                   value="#{EmployeeBean.editedRecord.dob}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="empDob" tooltip="*"/>
                                    
                                   <h:outputText value="Dependent"/>
                                   <h:selectOneMenu  id="dependent" value="#{EmployeeBean.editedRecord.dependent}">
                                        <f:selectItem itemLabel="Yes" itemValue="Yes"/>
                                        <f:selectItem itemLabel="No" itemValue="No"/>
                                    </h:selectOneMenu>
                                    <h:message for="dependent" tooltip="Employee's dependent."/> 
                                    
                                    
                                    <h:outputText value="Whether Employed"/>
                                    <h:selectOneMenu  id="whetheremployed" value="#{EmployeeBean.editedRecord.whetherEmployed}">
                                        <f:selectItem itemLabel="Center" itemValue="Center"/>
                                        <f:selectItem itemLabel="State" itemValue="State"/>
                                        <f:selectItem itemLabel="Other" itemValue="Other"/>
                                        <f:selectItem itemLabel="Unemployed" itemValue="Unemployed"/>
                                     </h:selectOneMenu>
                                    <h:message for="whetheremployed" tooltip="Whether Employed."/> 
                                    
                                    <h:outputText value="Name of the Department"/>
                                    <h:inputText id="dept" styleClass="fields" value="#{EmployeeBean.editedRecord.deptName}"/>
                                    <h:message for="dept" tooltip="Department Name"/>
                                 </h:panelGrid> 
                            
                               <a4j:commandButton value="Store"   action="#{EmployeeBean.updateRecord}" reRender="profileForm" oncomplete="#{rich:component('editPane')}.hide();"/>
                               <a4j:commandButton value="Cancel" onclick="#{rich:component('editPane')}.hide(); return false;" />
                               
                               </rich:panel>
                              </h:form>
                            </rich:modalPanel>
                    
                
                <rich:modalPanel id="pnl">
                            <h:form id="addfamilyrecord">
                                <rich:panel header="Add Family Record ">
                                    <h:column>
                            
                                <rich:toolTip for="empName" value="Standard Charecter(e.g @,#,$,...etc) Will Not Be Accepted, Length Should Be Three Charecter"/>
                                <rich:toolTip for="seci" value="Check , If Employee Is Senior Citizen Otherwise Uncheck"/>
                                <h:panelGroup>
                                   
                                        <h:panelGrid columns="2"  style="width:100%;">
                                            <h:column>
                                                <h:panelGrid columns="8">
                                                    <h:outputText  styleClass="Label" value="Employee Code"/>
                                                    <h:inputText  onblur="activate()" id="empCode" value="#{EmployeeBean.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                                    <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                                        <h:column>
                                                            <h:outputText value="#{abc.name}"/>
                                                        </h:column>
                                                        <h:column>
                                                            <h:outputText value="#{abc.code}"/>
                                                        </h:column>
                                                    </rich:suggestionbox>
                                                    <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>
                                                    <%--<a4j:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeBean.loadfamilyDetail}" reRender="dTable" />--%>
                                             </h:panelGrid>
                                            </h:column>
                                        </h:panelGrid>
                                      </h:panelGroup>
                                </h:column>
                            <h:panelGrid   columnClasses="label,field">   
                            <h:outputText styleClass="Label" value="Family Memeber Name"/>
                                    <h:inputText id="empfrName" required="true"
                                                 requiredMessage="Enter Member Name."
                                                 styleClass="fields" value="#{EmployeeBean.memberName}" validator="#{allValidator.validateUserName}">
                                        <rich:ajaxValidator event="onblur"></rich:ajaxValidator>
                                    </h:inputText>
                                    <h:message styleClass="error" for="empfrName" tooltip="Enter Family Member Name."/>
                                    <h:outputText value="Relation"/>
                                    <h:selectOneMenu  id="relation" value="#{EmployeeBean.relation}">
                                        <f:selectItem itemLabel="Father" itemValue="Father"/>
                                        <f:selectItem itemLabel="Mother" itemValue="Mother"/>
                                        <f:selectItem itemValue="Husband" itemLabel="Husband"/>
                                        <f:selectItem itemValue="Wife" itemLabel="Wife"/>
                                        <f:selectItem itemValue="Daughter" itemLabel="Daughter"/>
                                        <f:selectItem itemValue="Son" itemLabel="Son"/>
                                    </h:selectOneMenu>
                                    <h:message for="relation" tooltip="Employee's Relation."/>
                    
                                <h:outputText styleClass="Label" value="Date of Birth"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empDob" popup="true"
                                                   required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                                   value="#{EmployeeBean.dob}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="empDob" tooltip="*"/>
                                    
                                   <h:outputText value="Dependent"/>
                                   <h:selectOneMenu  id="dependent" value="#{EmployeeBean.dependent}">
                                        <f:selectItem itemLabel="Yes" itemValue="Yes"/>
                                        <f:selectItem itemLabel="No" itemValue="No"/>
                                    </h:selectOneMenu>
                                    <h:message for="dependent" tooltip="Employee's dependent."/> 
                                    
                                    
                                    <h:outputText value="Whether Employed"/>
                                    <h:selectOneMenu  id="whetheremployed" value="#{EmployeeBean.whetherEmployed}">
                                        <f:selectItem itemLabel="Center" itemValue="Center"/>
                                        <f:selectItem itemLabel="State" itemValue="State"/>
                                        <f:selectItem itemLabel="Other" itemValue="Other"/>
                                        <f:selectItem itemLabel="Unemployed" itemValue="Unemployed"/>
                                     </h:selectOneMenu>
                                    <h:message for="whetheremployed" tooltip="Whether Employed."/> 
                                    
                                    <h:outputText value="Name of the Department"/>
                                    <h:inputText id="dept" styleClass="fields" value="#{EmployeeBean.deptName}"/>
                                    <h:message for="dept" tooltip="Department Name"/>
                                    </h:panelGrid>
                              
                               <a4j:commandButton id="btnSave" styleClass="panel"   value="Save" action="#{EmployeeBean.saveFamilyRecord}" reRender="profileForm" oncomplete="#{rich:component('pnl')}.hide();"/>
                               <a4j:commandButton value="Cancel" onclick="#{rich:component('pnl')}.hide(); return false;" />
                                                             
                               </rich:panel>
                              </h:form>
                            </rich:modalPanel>
                    
                     </rich:panel>
                
                </f:view>   
            </div>
        </body>
     
</html>
