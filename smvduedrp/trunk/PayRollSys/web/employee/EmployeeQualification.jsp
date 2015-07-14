<%-- 
    Document   : EmployeeQualification
    Created on : May 29, 2014, 3:44:46 PM
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
*  Modified Date: 4AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*  GUI Modification : 20 June 2015, Om Prakash<omprakashkgp@gmail.com>

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
                var x = document.getElementById("qualForm:empCode").value;
                if(x!=="")
                {
                    document.getElementById("qualForm:ldbtn").disabled=false;
                    document.getElementById("qualForm:ldbtn1").disabled=false;
                                        
                    document.getElementById("addeducation:btnSave").disabled=false;
                    document.getElementById("addeducation:btnReset").disabled=false;
                }
                else
                {
                    document.getElementById("qualForm:ldbtn").disabled=true;
                    document.getElementById("qualForm:ldbtn1").disabled=false;
                    document.getElementById("addeducation:btnSave").disabled=false;
                    document.getElementById("addeducation:btnReset").disabled=false;
                }
            }
            
            function enatble_text()
            {
                var x = document.getElementById("qualForm:board").value;
                alert("board===="+x);
                if(x=="2"){
                    
                    document.getElementById("pnl:univboard").disabled=false;
                }
                else{
                    document.getElementById("pnl:univboard").disabled=true;
                }
           }
            
            
                             
     </script>
    </head>
    
<f:view>
    <body class="subpage" id="">
        <a4j:keepAlive beanName="EmployeeQualification" ajaxOnly="true"/>
        <div class="container_form">
        <rich:panel id="addemplqualdetail" header="Employee Education / Training Details" styleClass="form" style="width:auto;">
            <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add Education Detail"/> &nbsp;
            <h:commandButton onclick="Richfaces.showModalPanel('tnl');" value="Add Training Detail"/>
                <rich:messages>
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                    <f:facet name="errorMarker">
                        <h:graphicImage url="/img/err.png"/>
                    </f:facet>
                </rich:messages>
                 <h:form id="qualForm">
                     
                    <h:panelGrid columns="1">
                        <h:column>
                            <rich:toolTip for="empName" value="Standard Charecter(e.g @,#,$,...etc) Will Not Be Accepted, Length Should Be Three Charecter"/>
                            <rich:toolTip for="seci" value="Check , If Employee Is Senior Citizen Otherwise Uncheck"/>
                            <h:panelGroup>
                                
                                <h:panelGrid columns="2"  style="width:100%;">
                                    <h:column>
                                         <h:panelGrid columns="8">
                                            <h:outputText  styleClass="Label" value="Employee Code"/>
                                            <h:inputText  onblur="activate()" id="empCode" value="#{EmployeeQualification.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                            <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                                <h:column>
                                                    <h:outputText value="#{abc.name}"/>
                                                </h:column>
                                                <h:column>
                                                    <h:outputText value="#{abc.code}"/>
                                                </h:column>
                                            </rich:suggestionbox>
                                            <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>
                                            <a4j:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeQualification.loadQualDetail}" value="Load Record"  reRender="si,addemplqualdetail,empqualDetail,td,emptringDetail" />
                                         </h:panelGrid>
                                    </h:column>
                                </h:panelGrid>
                            </h:panelGroup>
                        </h:column>
                    </h:panelGrid>
              <rich:panel header="Employee Education Details" > 
                    <h:panelGrid  id="empqualDetail" columns="7"  columnClasses="label,field">
                        <a4j:status onstart="#{rich:component('statPane')}.show()" onstop="#{rich:component('statPane')}.hide()" />
                        <rich:dataTable id="si"  value="#{EmployeeQualification.allQualRecord}"  binding="#{EmployeeQualification.dataGrid}"  var="empqual"  rowKeyVar="row"  rows="5" style="width:1000px;">
                            <rich:column width="5%" >
                                <f:facet name="header" >
                                    <h:outputText value="Sr.No" style="font-size:10px;" />
                                </f:facet>
                                    <h:outputText value="#{empqual.srNo}" />
                            </rich:column>   
                            <rich:column  width="20%">
                                <f:facet name="header">
                                    <h:outputText styleClass="Label" value="Exam Passed" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText styleClass="Label" value="#{empqual.examPassed}"/>
                            </rich:column>
                            <rich:column  width="25%">
                                <f:facet name="header">
                                    <h:outputText styleClass="Label" value="Name of Board/University" style="font-size:10px;" />
                                </f:facet>
                               <h:outputText styleClass="Label" value="#{empqual.board}"/>
                            </rich:column>
                            <rich:column width="15%" >
                                <f:facet name="header">
                                    <h:outputText styleClass="Label" value="Marks Obtained(in %)" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText  value="#{empqual.marksObtained}" />
                            </rich:column> 
                            <rich:column width="10%">
                                <f:facet name="header">
                                    <h:outputText value="Passing Year" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText value="#{empqual.yearOfPassing}"/>
                            </rich:column>
                            <rich:column width="10%">
                                <f:facet name="header">
                                    <h:outputText value="Division/Grade" style="font-size:10px;" />
                                </f:facet>
                               <h:outputText value="#{empqual.divGrade}" />
                            </rich:column>
                            <rich:column width="5%">
                                    <f:facet name="header">
                                     <h:outputText value="Actions" style="font-size:10px;" />
                                    </f:facet>
                                    <a4j:commandLink  styleClass="no-decor" ajaxSingle="true" oncomplete="#{rich:component('confirmPane')}.show()">
                                        <h:graphicImage value="/img/delete.gif" alt="delete" />
                                        <f:setPropertyActionListener value="#{row}" target="#{EmployeeQualification.currentRecordindex}" />
                                    </a4j:commandLink>
                                    <a4j:commandLink styleClass="no-decor" ajaxSingle="true" reRender="editGrid" oncomplete="#{rich:component('editPane')}.show()">
                                        <h:graphicImage value="/img/edit.gif" alt="edit" />
                                        <f:setPropertyActionListener value="#{row}" target="#{EmployeeQualification.currentRecordindex}" />
                                        <f:setPropertyActionListener value="#{empqual}" target="#{EmployeeQualification.editedRecord}" />
                            
                                    </a4j:commandLink>
                                 </rich:column>
                             <f:facet name="footer">
                                <rich:datascroller for="si" page="5" />  
                            </f:facet>
                            
                             </rich:dataTable> 
                        </h:panelGrid>
                     
                         <rich:modalPanel id="confirmPane" autosized="true"  width="250"> 
                              <f:facet name="controls">
                                    <h:panelGroup>
                                    <h:graphicImage value="/img/close1.png" styleClass="hidelink3" id="hidelink3"/>
                                    <rich:componentControl for="confirmPane" attachTo="hidelink3" operation="hide" event="onclick"/>
                                    </h:panelGroup>
                               </f:facet> 
                                Are you sure you want to delete the row?
                                <a4j:commandButton value="Cancel" onclick="#{rich:component('confirmPane')}.hide(); return false;" />
                                
                                <a4j:commandButton id="ldbtn1" ajaxSingle="true" reRender="si,empqualDetail" value="Delete" action="#{EmployeeQualification.deleteRecord}" oncomplete="#{rich:component('confirmPane')}.hide();"/>
                         </rich:modalPanel>
                               </rich:panel> 
                     </h:form>
                           
                    <h:form>
                 <rich:panel header="Employee training Details"  >   
                    <h:panelGrid  id="emptringDetail" columns="6"  columnClasses="label,field">
                        <rich:dataTable id="td"   value="#{EmployeeQualification.allTrainingRecord}"  binding="#{EmployeeQualification.dataGrid1}"  var="emptd" rowKeyVar="rowtd" rows="5" style="width:1000px;">
                            <rich:column width="5%">
                                <f:facet name="header" >
                                    <h:outputText value="Sr.No" style="font-size:10px;" />
                                </f:facet>
                               <h:outputText value="#{emptd.srNo}" />
                            </rich:column>   
                            <rich:column  width="20%">
                                <f:facet name="header">
                                    <h:outputText styleClass="Label" value="Training Type" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText styleClass="Label" value="#{emptd.trainingType}"/>
                            </rich:column>
                            <rich:column  width="20%">
                                <f:facet name="header">
                                    <h:outputText styleClass="Label" value="Topic Name" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText styleClass="Label" value="#{emptd.topicName}"/>
                            </rich:column>
                            <rich:column width="15%" >
                                <f:facet name="header">
                                    <h:outputText styleClass="Label" value="Name of the Institute" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText  value="#{emptd.instituteName}" />
                            </rich:column> 
                            <rich:column width="15%">
                                <f:facet name="header">
                                    <h:outputText value="Sponsored by" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText value="#{emptd.sponsoredBy}"/>
                            </rich:column>
                            <rich:column width="10%">
                                <f:facet name="header">
                                    <h:outputText value="Date From" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText value="#{emptd.dateFrom}" />
                            </rich:column>
                            <rich:column width="10%">
                                <f:facet name="header">
                                    <h:outputText value="Date To" style="font-size:10px;" />
                                </f:facet>
                                <h:outputText value="#{emptd.dateTo}" />
                            </rich:column>
                            <rich:column width="5%">
                                    <f:facet name="header">
                                     <h:outputText value="Actions" style="font-size:10px;" />
                                    </f:facet>
                                    <a4j:commandLink  styleClass="no-decor" ajaxSingle="true" oncomplete="#{rich:component('confirmPnl')}.show()">
                                        <h:graphicImage value="/img/delete.gif" alt="delete" />
                                        <f:setPropertyActionListener value="#{rowtd}" target="#{EmployeeQualification.currentRecordindex}" />
                                    </a4j:commandLink>
                                    <a4j:commandLink styleClass="no-decor" ajaxSingle="true" reRender="editGrid1" oncomplete="#{rich:component('editPnl')}.show()">
                                        <h:graphicImage value="/img/edit.gif" alt="edit" />
                                        <f:setPropertyActionListener value="#{rowtd}" target="#{EmployeeQualification.currentRecordindex}" />
                                        <f:setPropertyActionListener value="#{emptd}" target="#{EmployeeQualification.editedRecord}" />
                            
                                    </a4j:commandLink>
                                 </rich:column>
                                 <f:facet name="footer">
                                <rich:datascroller for="td" page="5" />  
                            </f:facet>
                          </rich:dataTable> 
                       </h:panelGrid>
                    
                         <rich:modalPanel id="confirmPnl" autosized="true"  width="250"> 
                                <f:facet name="controls">
                                    <h:panelGroup>
                                    <h:graphicImage value="/img/close1.png" styleClass="hidelink4" id="hidelink4"/>
                                    <rich:componentControl for="confirmPnl" attachTo="hidelink4" operation="hide" event="onclick"/>
                                    </h:panelGroup>
                                </f:facet> 
                                Are you sure you want to delete the row?
                                <a4j:commandButton value="Cancel" onclick="#{rich:component('confirmPnl')}.hide(); return false;" />
                                
                                <a4j:commandButton  ajaxSingle="true" reRender="td,emptringDetail" value="Delete" action="#{EmployeeQualification.deleteTrainingRecord}" oncomplete="#{rich:component('confirmPnl')}.hide();"/>
                         </rich:modalPanel>
                                
                       </rich:panel> 
                     </h:form>     
                            
                                                          
                                      
                <rich:modalPanel id="editPane" autosized="true"  domElementAttachment="parent" width="400" height="170"> 
                      <f:facet name="controls">
                                <h:panelGroup>
                                <h:graphicImage value="/img/close1.png" styleClass="hidelink3" id="hidelink3"/>
                                <rich:componentControl for="editPane" attachTo="hidelink3" operation="hide" event="onclick"/>
                                </h:panelGroup>
                      </f:facet>
                    <h:form>
                        <rich:panel header="Edit Education Detail">
                             <h:panelGrid id="editGrid">
                               
                                <h:outputText value="Exam Passed"/>
                                <h:selectOneMenu  id="exam" value="#{EmployeeQualification.editedRecord.examPassed}">
                                        <f:selectItem itemLabel="HighSchool" itemValue="HighSchool"/>
                                        <f:selectItem itemLabel="Intermediate" itemValue="Intermediate"/>
                                        <f:selectItem itemValue="Graduation" itemLabel="Graduatuion"/>
                                        <f:selectItem itemValue="Post Graduation" itemLabel="Post Graduation"/>
                                        
                                    </h:selectOneMenu>
                                    <h:message for="exam" tooltip=" Select exam passed ."/>
                                 
                                <h:outputText value="Name of Board/University"/>
                                <h:selectOneMenu  id="board" value="#{EmployeeQualification.editedRecord.board}">
                                   <f:selectItem itemLabel="CBSE board" itemValue="CBSE board"/>
                                   <f:selectItem itemLabel="ICSE board" itemValue="ICSE board"/>
                                   <f:selectItem itemValue="State board" itemLabel="State board"/>
                                   <f:selectItem itemValue="Other board" itemLabel="Other board"/>
                                   </h:selectOneMenu>
                                <h:inputText id="univboard" maxlength="30" value="#{EmployeeQualification.editedRecord.boardName}" onclick="enatble_text()" immediate="true"/>
                                <h:message for="board" tooltip="Select name of board/university ."/>
                                
                                <h:outputText value="Marks Obtained (in  %)"/>
                                <h:inputText id="marks" styleClass="fields" maxlength="15" value="#{EmployeeQualification.editedRecord.marksObtained}"/>
                                <h:message for="marks" tooltip="Marks obtained"/> 
                                <h:outputText  value="Passing year (YYYY Ex- 1996)"/>
                                <h:inputText id="pyear" styleClass="fields" required="true" maxlength="15" requiredMessage="Enter year of passing" value="#{EmployeeQualification.editedRecord.yearOfPassing}"/>
                                <%--<h:outputText styleClass="Label" value="Passing year"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                        showFooter="false" styleClass="special"
                                        datePattern="yyyy-MM-dd" id="pyear" popup="true"
                                        required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                        value="#{EmployeeQualification.editedRecord.dob}">
                                    </rich:calendar> --%>
                                    <h:message styleClass="error" for="pyear" tooltip="Enter passing year"/>
                                                                    
                                <h:outputText value="Division / Grade"/>
                                <h:inputText id="grade" styleClass="fields" maxlength="20" value="#{EmployeeQualification.editedRecord.divGrade}"/>
                                <h:message for="grade" tooltip="Division or GradeS"/>
                             </h:panelGrid> 
                             <a4j:commandButton value="Store"   action="#{EmployeeQualification.updateRecord}" reRender="qualForm" oncomplete="#{rich:component('editPane')}.hide();"/>
                             <a4j:commandButton value="Cancel" onclick="#{rich:component('editPane')}.hide(); return false;" />
                           </rich:panel>
                    </h:form>
                    </rich:modalPanel>
                
                                
                <rich:modalPanel id="pnl">
                    <f:facet name="header">
                        <h:panelGroup>
                            <h:outputText value="Add Education Detail"></h:outputText>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                            <rich:componentControl for="pnl" attachTo="hidelink" operation="hide" event="onclick"/>
                         </h:panelGroup>
                    </f:facet>
                    <h:form>
                     <rich:panel>
                        <h:column>
                            <rich:toolTip for="empName" value="Standard Charecter(e.g @,#,$,...etc) Will Not Be Accepted, Length Should Be Three Charecter"/>
                            <rich:toolTip for="seci" value="Check , If Employee Is Senior Citizen Otherwise Uncheck"/>
                            <h:panelGroup>
                                    <h:panelGrid columns="2"  style="width:100%;">
                                        <h:column>
                                            <h:panelGrid columns="8">
                                                <h:outputText  styleClass="Label" value="Employee Code"/>
                                                <h:inputText  onblur="activate()" id="empCode" value="#{EmployeeQualification.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                                <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                                    <h:column>
                                                        <h:outputText value="#{abc.name}"/>
                                                    </h:column>
                                                    <h:column>
                                                        <h:outputText value="#{abc.code}"/>
                                                    </h:column>
                                                </rich:suggestionbox>
                                                <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>
                                                 
                                            </h:panelGrid>
                                        </h:column>
                                    </h:panelGrid>
                                </h:panelGroup>
                            </h:column>
                            <h:panelGrid  columnClasses="label,field">   
                                <h:outputText value="Exam Passed"/>
                                <h:selectOneMenu  id="exam" value="#{EmployeeQualification.examPassed}">
                                        <f:selectItem itemLabel="HighSchool" itemValue="HighSchool"/>
                                        <f:selectItem itemLabel="Intermediate" itemValue="Intermediate"/>
                                        <f:selectItem itemValue="Graduation" itemLabel="Graduatuion"/>
                                        <f:selectItem itemValue="Post Graduation" itemLabel="Post Graduation"/>
                                        <f:selectItem itemValue="Others" itemLabel="Others"/>
                                        
                                    </h:selectOneMenu>
                                    <h:message for="exam" tooltip=" Select exam passed ."/>
                                    
                                    <h:outputText value="Name of Board/University"/>
                                    <h:selectOneMenu  id="board" value="#{EmployeeQualification.board}">
                                        <f:selectItem itemLabel="CBSE board" itemValue="CBSE board"/>
                                        <f:selectItem itemLabel="ICSE board" itemValue="ICSE board"/>
                                        <f:selectItem itemValue="State board" itemLabel="State board"/>
                                        <f:selectItem itemValue="Other board" itemLabel="Other board"/>
                                    </h:selectOneMenu>
                                    
                                    <h:inputText id="univboard"  maxlength="35" value="#{EmployeeQualification.boardName}" onclick="enatble_text()" immediate="true"/>
                                    <h:message for="board" tooltip="Select name of board/university ."/>
                                    
                                <h:outputText value="Marks Obtained (in  %)"/>
                                <h:inputText id="marks" styleClass="fields"  required="true" maxlength="15" requiredMessage=" Enter Marks obtained" value="#{EmployeeQualification.marksObtained}"/>
                                <h:message for="marks" tooltip="true"/> 
                    
                                <h:outputText  value="Passing year (YYYY Ex- 1996)"/>
                                <h:inputText id="pyear" styleClass="fields" required="true" maxlength="15" requiredMessage="Enter year of passing" value="#{EmployeeQualification.yearOfPassing}"/>
                                <%--<h:selectOneMenu  id="pyear" value="#{EmployeeQualification.year}">
                                    <f:selectItem value="#{EmployeeQualification.yearList}"/>
                                </h:selectOneMenu>--%>  
                                <%-- <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                        showFooter="false" styleClass="special"
                                        datePattern="yyyy" id="pyear" popup="true"
                                        required="true"   requiredMessage="*Enter year of passing"
                                        value="">
                                    </rich:calendar>--%>
                                    <h:message styleClass="error" for="pyear" tooltip="true"/>
                                                                    
                                <h:outputText value="Division / Grade"/>
                                <h:inputText id="grade" styleClass="fields" maxlength="20" required="true" requiredMessage="Enter Division or Grade" value="#{EmployeeQualification.divGrade}"/>
                                <h:message for="grade" tooltip="true"/>
                    
                              </h:panelGrid>
                              
                         <a4j:commandButton id="btnSave" styleClass="panel"   value="Save" action="#{EmployeeQualification.saveQualification}" reRender="qualForm" oncomplete="#{rich:component('pnl')}.hide();"/>
                            <a4j:commandButton value="Cancel" onclick="#{rich:component('pnl')}.hide(); return false;" />
                        </rich:panel>
                    </h:form>
                </rich:modalPanel>
                
                                
                   <rich:modalPanel id="editPnl" autosized="true"  domElementAttachment="parent" width="400" height="170"> 
                      <f:facet name="controls">
                                <h:panelGroup>
                                <h:graphicImage value="/img/close1.png" styleClass="hidelink2" id="hidelink2"/>
                                <rich:componentControl for="editPnl" attachTo="hidelink2" operation="hide" event="onclick"/>
                                </h:panelGroup>
                      </f:facet>
                    <h:form>
                        <rich:panel header="Edit Training Detail">
                             <h:panelGrid id="editGrid1">
                               
                                <h:outputText value="Training Type"/>
                                <h:selectOneMenu  id="ttype" value="#{EmployeeQualification.editedRecord.trainingType}">
                                        <f:selectItem itemLabel="Skills training" itemValue="Skills training"/>
                                        <f:selectItem itemLabel="Technical Skills training" itemValue="Technical Skills training"/>
                                        <f:selectItem itemValue="Refresher training" itemLabel="Refresher Training"/>
                                        <f:selectItem itemValue="Cross-fuctional training" itemLabel="Cross-fuctional training"/>
                                        <f:selectItem itemValue="Team training" itemLabel="Team training"/>
                                        <f:selectItem itemValue="Creativity training" itemLabel="Creativity training"/>
                                        <f:selectItem itemValue="Diversity training" itemLabel="Diversity training"/>
                                        <f:selectItem itemValue="Literacy training" itemLabel="Literacy training"/>
                                        <f:selectItem itemValue="Ethics training" itemLabel="Ethics training"/>
                                        <f:selectItem itemValue="Others" itemLabel="Others"/>
                                </h:selectOneMenu>
                                <h:message for="ttype" tooltip="Select training type"/>                
                                <h:outputText value="Topic Name"/>
                                <h:inputText id="topic" styleClass="fields" maxlength="25" required="true" requiredMessage="Enter topic name" value="#{EmployeeQualification.editedRecord.topicName}"/>
                                <h:message for="topic" tooltip="true"/> 
                                
                                <h:outputText value="Name of the Institute"/>
                                <h:inputText id="insName" styleClass="fields" maxlength="30" required="true" requiredMessage= "Enter name od the institute" value="#{EmployeeQualification.editedRecord.instituteName}"/>
                                <h:message for="insName" tooltip="true"/>
                                
                                <h:outputText value="Sponsored by"/>
                                <h:inputText id="sponsby" styleClass="fields" maxlength="25" value="#{EmployeeQualification.editedRecord.sponsoredBy}"/>
                                <h:message for="sponsby" tooltip="Enter Sponsored by"/>
                                                    
                                <h:outputText styleClass="Label" value="Date From"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                        showFooter="false" styleClass="special"
                                        datePattern="yyyy-MM-dd" id="datef" popup="true"
                                        required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                        value="#{EmployeeQualification.editedRecord.dateFrom}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="datef" tooltip=" Insert Date From "/>
                                    
                                    <h:outputText styleClass="Label" value="Date To"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                        showFooter="false" styleClass="special"
                                        datePattern="yyyy-MM-dd" id="datet" popup="true"
                                        required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                        value="#{EmployeeQualification.editedRecord.dateTo}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="datet" tooltip=" Insert Date To"/>
                                  </h:panelGrid>
                                    <a4j:commandButton value="Store"   action="#{EmployeeQualification.updateTrainingRecord}" reRender="emptringDetail" oncomplete="#{rich:component('editPnl')}.hide();"/>
                             <a4j:commandButton value="Cancel" onclick="#{rich:component('editPnl')}.hide(); return false;" />
                             </rich:panel>
                            </h:form>
                    </rich:modalPanel>
                                
                   <rich:modalPanel id="tnl">
                       <f:facet name="header">
                        <h:panelGroup>
                            <h:outputText value="Add Training Detail"></h:outputText>
                        </h:panelGroup>
                    </f:facet>
                    <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/img/close1.png" styleClass="hidelink1" id="hidelink1"/>
                            <rich:componentControl for="tnl" attachTo="hidelink1" operation="hide" event="onclick"/>
                         </h:panelGroup>
                    </f:facet>
                       
                    <h:form>
                        <rich:panel>
                            <h:column>
                                <rich:toolTip for="empName" value="Standard Charecter(e.g @,#,$,...etc) Will Not Be Accepted, Length Should Be Three Charecter"/>
                                <rich:toolTip for="seci" value="Check , If Employee Is Senior Citizen Otherwise Uncheck"/>
                                <h:panelGroup>
                                    <h:panelGrid columns="2"  style="width:100%;">
                                        <h:column>
                                            <h:panelGrid columns="8">
                                                <h:outputText  styleClass="Label" value="Employee Code"/>
                                                <h:inputText  onblur="activate()" id="empCode" value="#{EmployeeQualification.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                                <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                                    <h:column>
                                                        <h:outputText value="#{abc.name}"/>
                                                    </h:column>
                                                    <h:column>
                                                        <h:outputText value="#{abc.code}"/>
                                                    </h:column>
                                                </rich:suggestionbox>
                                                <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>
                                                
                                            </h:panelGrid>
                                        </h:column>
                                    </h:panelGrid>
                                </h:panelGroup>
                            </h:column>
                            <h:panelGrid columns="3"  columnClasses="label,field">   
                                <h:outputText value="Training Type"/>
                                <h:selectOneMenu  id="ttype" value="#{EmployeeQualification.trainingType}">
                                        <f:selectItem itemLabel="Skills training" itemValue="Skills training"/>
                                        <f:selectItem itemLabel="Technical Skills training" itemValue="Technical Skills training"/>
                                        <f:selectItem itemValue="Refresher training" itemLabel="Refresher Training"/>
                                        <f:selectItem itemValue="Cross-fuctional training" itemLabel="Cross-fuctional training"/>
                                        <f:selectItem itemValue="Team training" itemLabel="Team training"/>
                                        <f:selectItem itemValue="Creativity training" itemLabel="Creativity training"/>
                                        <f:selectItem itemValue="Diversity training" itemLabel="Diversity training"/>
                                        <f:selectItem itemValue="Literacy training" itemLabel="Literacy training"/>
                                        <f:selectItem itemValue="Ethics training" itemLabel="Ethics training"/>
                                        <f:selectItem itemValue="Others" itemLabel="Others"/>
                                </h:selectOneMenu>
                                <h:message for="ttype" tooltip="Select training type"/>                
                                <h:outputText value="Topic Name"/>
                                <h:inputText id="topic" styleClass="fields" maxlength="30" required="true" requiredMessage="Enter topic name" value="#{EmployeeQualification.topicName}"/>
                                <h:message for="topic" tooltip="true"/> 
                                
                                <h:outputText value="Name of the Institute"/>
                                <h:inputText id="insName" styleClass="fields" maxlength="30" required="true" requiredMessage= "Enter name od the institute" value="#{EmployeeQualification.instituteName}"/>
                                <h:message for="insName" tooltip="true"/>
                                
                                <h:outputText value="Sponsored by"/>
                                <h:inputText id="sponsby" styleClass="fields" maxlength="25" value="#{EmployeeQualification.sponsoredBy}"/>
                                <h:message for="sponsby" tooltip="Enter Sponsored by"/>
                                                    
                                <h:outputText styleClass="Label" value="Date From"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                        showFooter="false" styleClass="special"
                                        datePattern="yyyy-MM-dd" id="datef" popup="true"
                                        required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                        value="#{EmployeeQualification.dateFrom}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="datef" tooltip=" Insert Date From "/>
                                    
                                    <h:outputText styleClass="Label" value="Date To"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                        showFooter="false" styleClass="special"
                                        datePattern="yyyy-MM-dd" id="datet" popup="true"
                                        required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                        value="#{EmployeeQualification.dateTo}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="datet" tooltip=" Insert Date To"/>
                                 </h:panelGrid>
                              
                            <a4j:commandButton styleClass="panel"   value="Save" action="#{EmployeeQualification.saveTrainingDetail}"  reRender="emptringDetail" oncomplete="#{rich:component('tnl')}.hide();"/>
                            <a4j:commandButton value="Cancel" onclick="#{rich:component('tnl')}.hide(); return false;" />
                        </rich:panel>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>
        </div>
    </body>
    </f:view>
    
</html>
