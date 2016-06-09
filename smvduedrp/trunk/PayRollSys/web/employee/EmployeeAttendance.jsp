<%-- 
    Document   : EmployeeAttendance
    Created on : Sep 29, 2014, 5:22:17 PM
    Author     : 
*  Copyright (c) 2010 - 2011 SMVDU, Katra.
* Copyright (c) 2014 - 2016 ETRG, IITK.                
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
   guest
   @author : Om Prakash(omprakashkgp@gmail.com), Manorama Pal (palseema30@gmail.com)
--%>

<%@page import="org.smvdu.payroll.beans.upload.FileUploadBean" %>
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
        <style>
.top {
    vertical-align: top;
    
}
.info {
    height: 202px;
    overflow: auto;
}
</style>
        
    </head>
    <body>
            
        <f:view>
            <rich:panel header="Existing Attendance">
                <div align="right" >                                            
                <a4j:commandLink ajaxSingle="true" reRender="helppnl" onclick="Richfaces.showModalPanel('hnl');" >
                <h:graphicImage value="/img/help-icon.png" alt="Help" /> 
                </a4j:commandLink>
                </div>  
                <div>
                    <div>
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add Attendane"/> 
                    <h:commandButton onclick="Richfaces.showModalPanel('dnl');" value="Upload Attendance"/>
                    </div>
                 <h:form>
                        <h:commandButton action="#{AttendanceBean.callPage}" value="Check Attendance"/>
                 </h:form>
                 </div>
                 <rich:separator  style="width:100%;" /><br/> 
                  <h:form>
                  <h:panelGrid columns="5">
                         <h:outputText value="View Attendance"/>
                           <h:selectOneMenu value="#{AttendanceControllerBean.month}" >
                                        <f:selectItem itemValue="0" itemLabel="January"/>
                                        <f:selectItem itemValue="1" itemLabel="February"/>
                                        <f:selectItem itemValue="2" itemLabel="March"/>
                                        <f:selectItem itemValue="3" itemLabel="April"/>
                                        <f:selectItem itemValue="4" itemLabel="May"/>
                                        <f:selectItem itemValue="5" itemLabel="June"/>
                                        <f:selectItem itemValue="6" itemLabel="July"/>
                                        <f:selectItem itemValue="7" itemLabel="August"/>
                                        <f:selectItem itemValue="8" itemLabel="September"/>
                                        <f:selectItem itemValue="9" itemLabel="October"/>
                                        <f:selectItem itemValue="10" itemLabel="November"/>
                                        <f:selectItem itemValue="11" itemLabel="December"/>
                         </h:selectOneMenu>
                         <h:selectOneMenu value="#{AttendanceControllerBean.year}" >
                             <f:selectItems value="#{AttendanceControllerBean.itemsYear}"/>
                         </h:selectOneMenu>
                         <a4j:commandButton reRender="tbl" action="#{AttendanceControllerBean.populate}" value="Load"/>
                    </h:panelGrid>
                  </h:form>
                  <h:panelGrid columns="2">
                  <rich:messages  >
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                 </h:panelGrid>
                <h:form id="attForm"> 
                    
                  <%--  <rich:panel>--%>
                    <h:panelGrid id="attlist" style="width:100%;">
                                  
                       <rich:dataTable id="tbl" value="#{AttendanceControllerBean.allAttendanceData}"  binding="#{AttendanceControllerBean.dataGrid}" var="emp" rowKeyVar="row"  rows="15" style="width:100%;">
                      
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="S.No."/>
                            </f:facet>
                            <h:outputText value="#{emp.srNo}" />
                        </h:column>
                         
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Employee Code"/>
                            </f:facet>
                            <h:outputText value="#{emp.code}" />
                        </h:column>  
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value=" Employee Name "/>
                            </f:facet>
                            <h:outputText value="#{emp.name}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Present" />
                            </f:facet> 
                            <rich:inplaceInput value="#{emp.present}" />
                        </h:column>  
                         <h:column >
                            <f:facet name="header">
                                <h:outputText value=" Absent "/>
                            </f:facet>
                            <rich:inplaceInput value="#{emp.absent}" />
                        </h:column> 
                         <h:column >
                            <f:facet name="header">
                                <h:outputText value=" Leave "/>
                            </f:facet>
                            <rich:inplaceInput value="#{emp.leave}" />
                         </h:column> 
                            <f:facet name="footer">
                                <rich:datascroller for="tbl" page="15"/>  
                            </f:facet>
                    </rich:dataTable>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <a4j:commandButton value="Update" reRender="tbl" action="#{AttendanceControllerBean.update}"/>                    
                    </h:panelGrid>
                   <%-- </rich:panel>--%>
                </h:form>
                  
            </rich:panel>
             <rich:modalPanel  width="500" height="240" autosized="true" id="pnl">
                <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('pnl')" />
                </f:facet>
                           
                   
               <h:form>
                    <rich:panel header="Add Attendance">
                    <h:panelGrid columns="4">
                    <h:outputText value="Employee Code   "/>
                    <h:inputText id="code" required="true" requiredMessage="Please Enter Employee Code" value="#{AttendanceBean.code}"/>
                    <rich:suggestionbox for="code" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                    <h:column>
                           <h:outputText value="#{abc.name}"/>
                           </h:column>
                           <h:column>
                           <h:outputText value="#{abc.code}"/>
                           </h:column>
                    </rich:suggestionbox>
                    <rich:toolTip value="Enter few characters of name and choose from List" for="code"/>
                    <h:message styleClass="error" for="code" tooltip="Employee Type"/>
                    </h:panelGrid >
                    <h:panelGrid columns="3">   
                    <h:outputText value=" Present"/>
                    <h:inputText id="present" required="true" requiredMessage="Please Enter Present"  value="#{AttendanceBean.present}"/>
                
                    <%--<rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="dd" id="present" popup="true"
                                                   required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                                   value="#{AttendanceBean.present}">
                    </rich:calendar>--%>
                    
                    <h:message styleClass="error" for="present" tooltip="Example: 15 "/>
                    <h:outputText value=" Absent"/>
                    <h:inputText id="absent" required="true" requiredMessage="Please Enter Absent" value="#{AttendanceBean.absent}"/>
                    <h:message styleClass="error" for="absent" tooltip="*" />
                    <h:outputText value=" Leave"/>
                    <h:inputText id="leave" required="true" requiredMessage="Please Enter Leave" value="#{AttendanceBean.leave}"/>
                    <h:message styleClass="error" for="leave" tooltip="*"/>
                    <h:outputText value="Attendance Month"/>
                    <h:selectOneMenu  styleClass="combo" id="month" value="#{AttendanceBean.month}">
                                        <f:selectItem itemValue="0" itemLabel="January"/>
                                        <f:selectItem itemValue="1" itemLabel="February"/>
                                        <f:selectItem itemValue="2" itemLabel="March"/>
                                        <f:selectItem itemValue="3" itemLabel="April"/>
                                        <f:selectItem itemValue="4" itemLabel="May"/>
                                        <f:selectItem itemValue="5" itemLabel="June"/>
                                        <f:selectItem itemValue="6" itemLabel="July"/>
                                        <f:selectItem itemValue="7" itemLabel="August"/>
                                        <f:selectItem itemValue="8" itemLabel="September"/>
                                        <f:selectItem itemValue="9" itemLabel="October"/>
                                        <f:selectItem itemValue="10" itemLabel="November"/>
                                        <f:selectItem itemValue="11" itemLabel="December"/>
                                      
                    </h:selectOneMenu>
                    <h:message styleClass="error" for="month" tooltip="*"/>
                    <h:outputText value="Attendance Year"/>
                    <h:selectOneMenu  styleClass="combo" id="year" value="#{AttendanceBean.year}">
                        <%-- <f:selectItem itemValue="2014" itemLabel="2014"/>
                                        <f:selectItem itemValue="2015" itemLabel="2015"/>--%>
                        <f:selectItems value="#{AttendanceControllerBean.itemsYear}"/>                                    
                    </h:selectOneMenu>
                    <h:message styleClass="error" for="year" tooltip="*"/>
                    </h:panelGrid>
                    </rich:panel>
                    <a4j:commandButton value="Save" action="#{AttendanceBean.save}" reRender="attForm,tbl" oncomplete="#{rich:component('pnl')}.hide();" />
                    <a4j:support event="oncomplete" reRender="tbl"/>
                    <h:commandButton value="Close" onclick="#{rich:component('pnl')}.hide(); return false;" />
                </h:form> 
            
                </rich:modalPanel>    
   
                <rich:modalPanel  width="500" height="240" autosized="true" id="dnl">
                
                <%--file upload for attendance---------------------- --%>
                <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('dnl')" />
                </f:facet>
                <h:form>
                <h:panelGrid columns="1" columnClasses="top,top">
                <rich:fileUpload fileUploadListener="#{AttendanceBean.listener}"
                    maxFilesQuantity="#{FileUploadBean.uploadsAvailable}"
                    id="upload"
                    immediateUpload="#{FileUploadBean.autoUpload}"
                    acceptedTypes="csv" ontyperejected="alert('Only CSV files are accepted');" allowFlash="#{FileUploadBean.useFlash}">
                   <a4j:support event="onuploadcomplete" reRender="dnl"/>
                </rich:fileUpload>
                
               </h:panelGrid>
                
               </h:form>   
                </rich:modalPanel>
           <%---file upload END================================= --%>     
                
               <rich:modalPanel id="hnl" autosized="true" domElementAttachment="parent" width="750" height="450">
                  <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('hnl')" />
                  </f:facet>
                
                <h:form>
                    <rich:panel header="Help">
                    <h:panelGrid  id="helppnl">
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value="Instruction for add attendance"/>
                    <h:outputText style="font-size:1.2em;"  value="1. The Code (Employee code) should be existing in the database."/>
                    <h:outputText style="font-size:1.2em;"  value="2. Please insert the value of Present in number."/>
                    <h:outputText style="font-size:1.2em;"  value="2. Please insert the value of Absent in number."/>
                    <h:outputText style="font-size:1.2em;"  value="3. Please insert the value of Leave in number."/>
                    <h:outputText style="font-size:1.2em;"  value="4. Please select the month."/>
                    <h:outputText style="font-size:1.2em;"  value="5. Please select the year. "/>
                    <h:outputText style="font-size:1.2em;"  value="6. Now press the Save Button for save attendance in database. "/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value="Instruction for Check attendance"/>
                    <h:outputText style="font-size:1.2em;"  value=" When you click on Check Attendance Button it will show the list of employee which attendance is not uploaded. "/>
                    <h:outputText style="font-size:1.2em;"  value=" Please select the checkbox and fill the value of Year, Month, Present, Absent and Leave. now press the Save button. "/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value="Instruction for View attendance"/>
                    <h:outputText style="font-size:1.2em;"  value=" you can view the attendance by select month and year from drop down list and press the load button."/>
                    <h:outputText style="font-size:1.2em;font-weight:bold;" value="Instruction for upload a csv file."/>
                    <h:outputText style="font-size:1.2em;" value=" 1. Open LibreOffice Calc in ubuntu and Excel in windows."/>
                    <h:outputText style="font-size:1.3em;" value=" 2. The file should contain six field i.e"/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value="Example: "/>
                    <h:outputText style="font-size:1.2em;" value=" Code = 127 or EE127"/>
                    <h:outputText style="font-size:1.2em;" value=" Present = 21"/>
                    <h:outputText style="font-size:1.2em;" value=" Absent= 5 "/>
                    <h:outputText style="font-size:1.2em;" value=" Leave = 4 "/>
                    <h:outputText style="font-size:1.2em;" value=" month = 0 for january ---- 11 for December "/>
                    <h:outputText style="font-size:1.2em;" value=" year = 2014 "/>
                    <h:outputText style="font-size:1.2em;"  value="3. Save as csv format."/>
                    <%--<h:outputText style="font-size:1.5em;"  value="4. The list of attendance which is not uploaded is :  ~/PayRollSys/build/webcsvfile-UploadError.txt"/>
                     --%>        
                    </h:panelGrid>
                    </rich:panel>
                    <h:commandButton value="Close" onclick="#{rich:component('hnl')}.hide(); return false;" />
                </h:form>
                </rich:modalPanel>
                 
          </f:view>
    </body>
</html>


