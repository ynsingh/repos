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
*  GUI Modification : 20 June 2015, Om Prakash<omprakashkgp@gmail.com>

--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="org.smvdu.payroll.beans.setup.SalaryGrade" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.smvdu.payroll.beans.Employee" %>
<%@page import="javax.faces.application.FacesMessage"%>
<%@page import="javax.faces.application.FacesMessage.Severity"%>
<%@page import="javax.faces.component.UIData"%>
<%@page import="javax.faces.context.FacesContext"%>--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Service History</title>
        <%--<link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/components.css"/>
        <link rel="stylesheet" type="text/css" href="css/Form.css"/>
        <link rel="stylesheet" type="text/css" href="../css/richpanel.css"/>--%>
        <script type="text/javascript">
            function activate()
            {
                
                 var x = document.getElementById("historyForm:empCode").value;
                if(x!=="")
                {
                    document.getElementById("historyForm:ldbtn").disabled=false;
                    document.getElementById("historyForm:ldbtn1").disabled=false;
                    document.getElementById("editform:ldbtn2").disabled=false;
                    
                    document.getElementById("addservicerecord:btnSave").disabled=false;
                    document.getElementById("addservicerecord:btnReset").disabled=false;
                }
                else
                {
                    document.getElementById("historyForm:ldbtn").disabled=true;
                    document.getElementById("historyForm:ldbtn1").disabled=false;
                    document.getElementById("editform:ldbtn2").disabled=false;
                    document.getElementById("addservicerecord:btnSave").disabled=false;
                    document.getElementById("addservicerecord:btnReset").disabled=false;
                }
                
            }
           /* function addOnclickToDatatableRows() {
    var trs = document.getElementById('form:dataTable').getElementsByTagName('tbody')[0]
        .getElementsByTagName('tr');
    for (var i = 0; i < trs.length; i++) {
        trs[i].onclick = new Function("highlightAndSelectRow(this)");
    }
}

function highlightAndSelectRow(tr) {
    var trs = document.getElementById('form:dataTable').getElementsByTagName('tbody')[0]
        .getElementsByTagName('tr');
    for (var i = 0; i < trs.length; i++) {
        if (trs[i] == tr) {
            trs[i].bgColor = '#ff0000';
            document.form.rowIndex.value = trs[i].rowIndex;
        } else {
            trs[i].bgColor = '#ffffff';
        }
    }
}*/
            
            
            
                  
     </script>
    <%-- <script>
        window.onload = addOnclickToDatatableRows;
    </script>--%>
    </head>
    <f:view>
        <body class="subpage" id="">
           <a4j:keepAlive beanName="EmployeeBean" ajaxOnly="true"/>
            <div class="container_form">
                <rich:panel id="addempservicerecord" header="Employee Service History" styleClass="form" >
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                    <rich:messages  >
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
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
                                                    <a4j:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeBean.loadserviceHistory}" value="Load Record"  reRender="si,addempservicerecord,empserviceDetail,editGrid" />
                                             </h:panelGrid>
                                            </h:column>
                                        </h:panelGrid>
                                      </h:panelGroup>
                                </h:column>
                                </h:panelGrid>
                    
                                <h:panelGrid id="empserviceDetail" >
                                <%--<rich:datascroller for="si" maxPages="5" />--%>
                                <a4j:status onstart="#{rich:component('statPane')}.show()" onstop="#{rich:component('statPane')}.hide()" />
                                <rich:dataTable  id="si" value="#{EmployeeBean.allServiceRecord}" binding="#{EmployeeBean.dataGrid}" var="empshr" rowKeyVar="row" rows="10" style="width:1010px;" >
                                <rich:column>
                                    <f:facet name="header" >
                                    <h:outputText value="Sr.No" style="font-size:9px;" />
                                    </f:facet>
                                    <h:outputText value="#{empshr.srNo}" style="font-size:9px;" />
                                    </rich:column>   
                                <rich:column >
                                    <f:facet name="header">
                                        <h:outputText styleClass="Label" value="Transaction Type" style="font-size:9px;" />
                                    </f:facet>
                                   
                                    <h:outputText styleClass="Label" value="#{empshr.transactiontype}" style="font-size:9px;" />
                                    </rich:column>
                                <rich:column >
                                    <f:facet name="header">
                                        <h:outputText value="To office" style="font-size:9px;" />
                                    </f:facet>
                                    <h:outputText styleClass="Label" value="#{empshr.tooffice}" style="font-size:9px;" />
                                 </rich:column>
                                <rich:column >
                                    <f:facet name="header">
                                        <h:outputText  value="To which Post" style="font-size:9px;" />
                                    </f:facet>
                                     <h:outputText  value="#{empshr.towhichpost}" style="font-size:9px;" />                 
                                   
                                </rich:column> 
                                <rich:column >
                                    <f:facet name="header">
                                        <h:outputText value="Class" style="font-size:9px;" />
                                    </f:facet>
                                    <h:outputText styleClass="Label" value="#{empshr.empservclass}" style="font-size:9px;" />
                                    
                                </rich:column>
                                <rich:column >
                                    <f:facet name="header">
                                        <h:outputText value="Order Number" style="font-size:9px;" />
                                    </f:facet>
                                    
                                    <h:outputText styleClass="Label" value="#{empshr.ordernum}" />
                                </rich:column>
                                <rich:column >
                                    <f:facet name="header">
                                       <h:outputText value="Order Date" style="font-size:9px;" />
                                    </f:facet>
                              
                                         <h:outputText styleClass="Label" value="#{empshr.orderdate}" />
                                </rich:column>
                                <rich:column>
                                    <f:facet name="header">
                                        <h:outputText value="Increment Date" style="font-size:9px;" />
                                    </f:facet>
                        
                                    <h:outputText styleClass="Label" value="#{empshr.dateofincrement}" />
                                </rich:column>
                                <rich:column >
                                    <f:facet name="header" >
                                        <h:outputText value="Pay Scale" style="font-size:9px;" />
                                    </f:facet>
                                    <h:outputText value="#{empshr.payscale}" style="font-size:9px;" />
                    
                                </rich:column> 
                                <rich:column>
                                    <f:facet name="header" >
                                     
                                        <h:outputText value="Department for Deputation" style="font-size:9px;" />
                                        
                                    </f:facet>
                                    <h:outputText value="#{empshr.deputationdept}" style="font-size:9px;" />
                                 
                                </rich:column> 
                                <rich:column >
                                    <f:facet name="header" >
                                    <h:outputText value="Area Type" style="font-size:9px;" />
                                    </f:facet>
                              
                                    <h:outputText styleClass="Label" value="#{empshr.areatype}" />
                                </rich:column> 
                                <rich:column>
                                       <f:facet name="header">
                                           <h:outputText value="Actions" style="font-size:9px;" />
                                       </f:facet>
                                <a4j:commandLink  styleClass="no-decor" ajaxSingle="true" oncomplete="#{rich:component('confirmPane')}.show()">
                                <h:graphicImage value="/img/delete.gif" alt="delete" />
                                 <f:setPropertyActionListener value="#{row}" target="#{EmployeeBean.currentRecordindex}" />
                               <%-- <a4j:actionparam value="#{empshr.recordId}" assignTo="#{EmployeeBean.currentRecordindex}"/>--%>
                              </a4j:commandLink>
                            <a4j:commandLink styleClass="no-decor" ajaxSingle="true" reRender="editGrid" oncomplete="#{rich:component('editPane')}.show()">
                            <h:graphicImage value="/img/edit.gif" alt="edit" />
                            <f:setPropertyActionListener value="#{row}" target="#{EmployeeBean.currentRecordindex}" />
                            <f:setPropertyActionListener value="#{empshr}" target="#{EmployeeBean.editedRecord}" />
                            <%--<a4j:actionparam value="#{EmployeeBean.dataGrid.rowIndex}" assignTo="#{empshr.currentRecordindex}"/>--%>
                            </a4j:commandLink>
                            </rich:column>
                                <f:facet name="footer">
                                <rich:datascroller for="si" page="5" />
                                </f:facet>
                             </rich:dataTable>
                            </h:panelGrid>
                     
                            <%--<a4j:jsFunction  name="remove" action="#{EmployeeBean.deleteServiceHistory}" reRender="si"  oncomplete="#{rich:component('confirmPane')}.hide();" />
                               <rich:modalPanel id="statPane" autosized="true">
                                <h:graphicImage value="/img/edit.gif" alt="ai" />
                                 Please wait...
                                </rich:modalPanel>--%>
 
                                <rich:modalPanel id="confirmPane" autosized="true"  width="250">
                                <f:facet name="controls">
                                    <h:panelGroup>
                                    <h:graphicImage value="/img/close1.png" styleClass="hidelink3" id="hidelink3"/>
                                    <rich:componentControl for="confirmPane" attachTo="hidelink3" operation="hide" event="onclick"/>
                                    </h:panelGroup>
                                </f:facet> 
                             
                                Are you sure you want to delete the row?
                                <a4j:commandButton value="Cancel" onclick="#{rich:component('confirmPane')}.hide(); return false;" />
                                <%--<a4j:commandButton value="Delete"  onclick="remove(); return false;" />--%>
                                <a4j:commandButton id="ldbtn1" ajaxSingle="true" reRender="si,empserviceDetail" value="Delete" action="#{EmployeeBean.deleteServiceHistory}" oncomplete="#{rich:component('confirmPane')}.hide();"/>
                                </rich:modalPanel>
                                                           
                             <%-- <h:panelGroup>
                                  <a4j:commandButton  id="ldbtn2" value="update" action="#{EmployeeBean.updateServiceRecord}"/>
                            </h:panelGroup>
                           <h:panelGroup>
                               <a4j:commandButton  id="ldbtn1" value="Delete" action="#{EmployeeBean.deleteServiceHistory}"/>
                            </h:panelGroup>--%>
                          </h:form>

                         <rich:modalPanel   id="editPane" autosized="true"  domElementAttachment="parent" width="400" height="170"> 
                            <f:facet name="controls">
                                <h:panelGroup>
                                <h:graphicImage value="/img/close1.png" styleClass="hidelink1" id="hidelink1"/>
                                <rich:componentControl for="editPane" attachTo="hidelink1" operation="hide" event="onclick"/>
                                </h:panelGroup>
                            </f:facet>            
                          <h:form >
                              <rich:panel header="Edit Employee Service Record ">
                           <h:panelGrid id="editGrid">
                                               
                                    <h:outputText  value="Transaction Type"/>
                                    <h:inputText id="transType" required="true" maxlength="25"
                                                 requiredMessage="Enter Transaction Type."
                                                 styleClass="fields" value="#{EmployeeBean.editedRecord.transactiontype}" >
                                    </h:inputText>
                                    <h:message styleClass="error" for="transType" tooltip="Enter Transaction Type ."/>
                                    <h:outputText styleClass="Label" value="To office"/>
                                    <h:inputText id="tooffice" required="true" maxlength="25" requiredMessage="Enter Office Name." styleClass="fields" value="#{EmployeeBean.editedRecord.tooffice}">
                                    </h:inputText>
                                    <h:message styleClass="error" for="tooffice" tooltip="Enter Office Name ."/>
                                    
                                    <h:outputText styleClass="Label" value="To Which Post"/>
                                    <h:selectOneMenu  styleClass="combo"  id="whichPost" required="true" requiredMessage="Enter Post Name." value="#{EmployeeBean.editedRecord.towhichpost}">
                                    <f:selectItems value="#{DesignationBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message styleClass="error" for="whichPost" tooltip="Employee Designation. Select From Choices ."/>
                                    
                                    <h:outputText styleClass="Label" value="Class"/>
                                    <h:inputText id="class" required="true" maxlength="15" requiredMessage="Enter Class." styleClass="fields" value="#{EmployeeBean.editedRecord.empservclass}">
                                    </h:inputText>
                                    <h:message styleClass="error" for="class" tooltip="Enter Class ."/>
                                    
                                    <h:outputText styleClass="Label" value="Order Number"/>
                                    <h:inputText id="orderno" required="true" maxlength="11" requiredMessage="Enter Order Number." styleClass="fields" value="#{EmployeeBean.editedRecord.ordernum}">
                                    </h:inputText>
                                    <h:message styleClass="error" for="orderno" tooltip="Enter Order Number ."/>
                                                                      
                                    <h:outputText styleClass="Label" value=" Order Date"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empod" popup="true"
                                                   required="true"   requiredMessage="*Enter Order Date as yyyy-mm-dd"
                                                   value="#{EmployeeBean.editedRecord.orderdate}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="orderdate" tooltip="Enter Order Date ."/>
                                                                        
                                    <h:outputText styleClass="Label" value="Date of Increment "/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empDoI" popup="true"
                                                   required="true"   requiredMessage="*Enter Date of Increment as yyyy-mm-dd"
                                                   value="#{EmployeeBean.editedRecord.dateofincrement}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="incrementdate" tooltip="Enter Date of Increment ."/>
                                    
                                    <h:outputText styleClass="Label" value="Pay Scale"/>
                                    <h:selectOneMenu styleClass="combo"  id="payscale"  required="true" requiredMessage="Enter Pay Scale." value="#{EmployeeBean.editedRecord.payscale}">
                                        <f:selectItems value="#{SalaryGradeBean.grades}"/>
                                    </h:selectOneMenu>
                                    <h:message styleClass="error" for="payscale" tooltip="Enter Pay Scale ."/>
                                    
                                    <h:outputText styleClass="Label" value="Name of the other Department in case of Deputation"/>
                                    <h:selectOneMenu styleClass="combo" id="deputedDept"   value="#{EmployeeBean.editedRecord.deputationdept}">
                                        <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message styleClass="error" for="deputedDept" tooltip="Select Department in case of Deputation."/>
                                                                                                                                                                      
                                    <h:outputText value="Area Type"/>
                                    <h:selectOneMenu  id="areaType" value="#{EmployeeBean.editedRecord.areatype}">
                                        <f:selectItem itemLabel="Hard" itemValue="Hard"/>
                                        <f:selectItem itemLabel="Tribal" itemValue="Tribal"/>
                                        <f:selectItem itemLabel="Sub-Cader" itemValue="Sub-Cader"/>
                                        <f:selectItem itemLabel="None" itemValue="None"/>
                                     </h:selectOneMenu>
                                    <h:message for="areaType" tooltip="Enter Area Type."/> 
                                 </h:panelGrid>
                       
                        <a4j:commandButton  value="Store" action="#{EmployeeBean.updateServiceRecord}" reRender="historyForm" oncomplete="#{rich:component('editPane')}.hide();" />
                        <a4j:commandButton value="Cancel" onclick="#{rich:component('editPane')}.hide(); return false;" />
                        </rich:panel>
                        </h:form>
                        </rich:modalPanel>
                        <br />
                <hr/>
                <rich:modalPanel id="pnl"> 
                            <f:facet name="controls">
                                <h:panelGroup>
                                <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                                <rich:componentControl for="pnl" attachTo="hidelink" operation="hide" event="onclick"/>
                                </h:panelGroup>
                            </f:facet>
                            <h:form id="addservicerecord">
                                <rich:panel header="Add Employee Service History ">
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
                                                   <%-- <a4j:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeBean.loadserviceHistory}" value="Load Profile" reRender="dTable" />--%>
                                             </h:panelGrid>
                                            </h:column>
                                        </h:panelGrid>
                                      </h:panelGroup>
                                </h:column>
                            <h:panelGrid columns="3"  columnClasses="label,field">   
                            <h:outputText  value="Transaction Type"/>
                            <h:inputText id="transType" required="true" maxlength="25"
                                                 requiredMessage="Enter Transaction Type."
                                                 styleClass="fields" value="#{EmployeeBean.transactiontype}"  >
                                    </h:inputText>
                                    <h:message styleClass="error" for="transType" tooltip="Enter Transaction Type ."/>
                                    <h:outputText styleClass="Label" value="To office"/>
                                    <h:inputText id="tooffice" required="true" maxlength="25" requiredMessage="Enter Office Name." styleClass="fields" value="#{EmployeeBean.tooffice}">
                                    </h:inputText>
                                    <h:message styleClass="error" for="tooffice" tooltip="Enter Office Name ."/>
                                    
                                    <h:outputText styleClass="Label" value="To Which Post"/>
                                    <%--<h:inputText id="whichPost" required="true" requiredMessage="Enter Post Name." styleClass="fields" value="#{EmployeeBean.towhichpost}">
                                    </h:inputText>--%>
                                    <h:selectOneMenu  styleClass="combo"  id="whichPost" required="true" requiredMessage="Enter Post Name." value="#{EmployeeBean.desig}">
                                        <f:selectItems value="#{DesignationBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message styleClass="error" for="whichPost" tooltip="Employee Designation. Select From Choices ."/>
                                    
                                    <h:outputText styleClass="Label" value="Class"/>
                                    <h:inputText id="class" required="true"  maxlength="15" requiredMessage="Enter Class." styleClass="fields" value="#{EmployeeBean.empservclass}">
                                    </h:inputText>
                                    <h:message styleClass="error" for="class" tooltip="Enter Class ."/>
                                    
                                    <h:outputText styleClass="Label" value="Order Number"/>
                                    <h:inputText id="orderno" required="true" maxlength="15" requiredMessage="Enter Order Number." styleClass="fields" value="#{EmployeeBean.ordernum}">
                                    </h:inputText>
                                    <h:message styleClass="error" for="orderno" tooltip="Enter Order Number ."/>
                                                                       
                                    <h:outputText styleClass="Label" value=" Order Date"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empod" popup="true"
                                                   required="true"   requiredMessage="*Enter Order Date as yyyy-mm-dd"
                                                   value="#{EmployeeBean.orderdate}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="orderdate" tooltip="Enter Order Date ."/>
                                                                        
                                    <h:outputText styleClass="Label" value="Date of Increment "/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empDoI" popup="true"
                                                   required="true"   requiredMessage="*Enter Date of Increment as yyyy-mm-dd"
                                                   value="#{EmployeeBean.dateofincrement}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="incrementdate" tooltip="Enter Enter Date of Increment ."/>
                                    
                                    <h:outputText styleClass="Label" value="Pay Scale"/>
                                    <h:selectOneMenu styleClass="combo"  id="payscale"  required="true" requiredMessage="Enter Pay Scale." value="#{EmployeeBean.grade}">
                                        <f:selectItems value="#{SalaryGradeBean.grades}"/>
                                    </h:selectOneMenu>
                                    <%--<h:inputText id="payscale" required="true" requiredMessage="Enter Pay Scale." styleClass="fields" value="#{EmployeeBean.payscale}">
                                    </h:inputText>--%>
                                    <h:message styleClass="error" for="payscale" tooltip="Enter Pay Scale ."/>
                                    
                                    <h:outputText styleClass="Label" value="Name of the other Department in case of Deputation"/>
                                    <%--<h:inputText id="deputedDept" required="true" requiredMessage="Enter Department in case of Deputation." styleClass="fields" value="#{EmployeeBean.deputationdept}">
                                    </h:inputText>--%>
                                    <h:selectOneMenu styleClass="combo" id="deputedDept"   value="#{EmployeeBean.dept}">
                                        <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message styleClass="error" for="deputedDept" tooltip="Select Department in case of Deputation."/>
                                                                                                                                                                      
                                    <h:outputText value="Area Type"/>
                                    <h:selectOneMenu  id="areaType" value="#{EmployeeBean.areatype}">
                                        <f:selectItem itemLabel="Hard" itemValue="Hard"/>
                                        <f:selectItem itemLabel="Tribal" itemValue="Tribal"/>
                                        <f:selectItem itemLabel="Sub-Cader" itemValue="Sub-Cader"/>
                                        <f:selectItem itemLabel="None" itemValue="None"/>
                                     </h:selectOneMenu>
                                    <h:message for="areaType" tooltip="Enter Area Type."/> 
                               <h:panelGroup>
                               <a4j:commandButton id="btnSave" styleClass="panel"   value="Save" action="#{EmployeeBean.saveServiceHistory}" reRender="historyForm" oncomplete="#{rich:component('pnl')}.hide();"/>
                               <a4j:commandButton value="Cancel" onclick="#{rich:component('pnl')}.hide(); return false;" />
                               </h:panelGroup>
                               </h:panelGrid> 
                               </rich:panel>
                                </h:form>
                            </rich:modalPanel>
                    
                     </rich:panel>
                
                </f:view>   
            </div>
        </body>
     
</html>




