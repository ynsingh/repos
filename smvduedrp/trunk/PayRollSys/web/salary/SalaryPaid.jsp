<%-- 
    Document   : SalaryPaid
    Created on : jan 1, 2016, 11:14:35 AM
    Author     : guest
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script language="javascript" src='/JQuery/jquery-selectAll.js'></script>

    </head>
    
    <f:view>
            
        <rich:panel id="addemplqualdetail" header="Salary Detail" styleClass="form" style="width:auto;">
            <h:form id="myForm">
               
                <h:panelGrid columns="2">
                        <h:outputText value="Current Month :" style="font-size:1.2em;"/>
                        <h:outputText value="#{SalaryTool.copyTo}" style="font-size:1.2em;"/>
             
               </h:panelGrid>
                <div align="right" >
                <h:outputText style="font-size:1.2em;color:blue;" value="If Salary Amount is negative then Salary will not be paid to the employee ,please correct the Deduction Amount of the employee." />
                </div>
                <rich:messages layout="table" style="border:1;" >
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                    <f:facet name="errorMarker">
                        <h:graphicImage url="/img/err.png"/>
                    </f:facet>
                </rich:messages> <br/>
               
                <a4j:commandButton  reRender="#{EmpSalaryLiabilityBean.salaryPayableRecord}, si, myForm, empDetail" action="#{EmpSalaryLiabilityBean.Updatesalaryprocess}" value="Submit" />
                
                <h:panelGrid  id="empDetail" columns="6"  columnClasses="label,field">
                    <rich:dataTable id="si"  value="#{EmpSalaryLiabilityBean.salaryPayableRecord}" binding="#{EmpSalaryLiabilityBean.dataGrid1}" var="empliab" rowKeyVar="row" rows="20" 
                        onRowMouseOver="this.style.backgroundColor='#B5F3FB'" onRowMouseOut="this.style.backgroundColor='#{a4jSkin.rowBackgroundColor}'" style="width:1200px;">
                        <a4j:keepAlive beanName="EmpSalaryLiabilityBean" ajaxOnly="true"/>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Sr.No"/>
                            </f:facet>
                            <h:outputText value="#{empliab.srNo}" />
                        </h:column> 
                        <h:column>
                            <f:facet name="header">
                                <h:outputText styleClass="Label" value="Code"/>
                            </f:facet>
                            <h:outputText value="#{empliab.code}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText styleClass="Label" value="Name"/>
                            </f:facet>
                            <h:outputText value="#{empliab.employee.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText styleClass="Label" value="Department"/>
                            </f:facet>
                            <h:outputText value="#{empliab.employee.typeName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText styleClass="Label" value="Department"/>
                            </f:facet>
                            <h:outputText value="#{empliab.employee.deptName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText styleClass="Label" value="Designation"/>
                            </f:facet>
                            <h:outputText value="#{empliab.employee.desigName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText styleClass="Label" value="Total Salary"/>
                            </f:facet>
                                <h:outputText value="#{empliab.liabiltyAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText styleClass="Label" value="Status"/>
                            </f:facet>
                            <h:outputText id="stat" style="color: #{empliab.status == 'salary processed' ? 'red' : 'green'};font-weight:bold;" value="#{empliab.status}" />
                                <%--<o:ajax execute="stat" event="change" render="empfmlyDetail" />--%>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:panelGroup layout="block">
                                    <script type="text/javascript">
                           
                                        function checkAllCheckboxesInTable( inputId, state ){
                                            //var rows = document.getElementById('myForm:si').rows;
                                            //alert("rows==="+rows.length);
                                            var commonIdPart = inputId.substr(0, inputId.lastIndexOf(':'));
                                            var tableId = commonIdPart;
                                            var tableElement = document.getElementById( tableId );
                                            var inputs = tableElement.getElementsByTagName('input');
                                            //alert("inputs===="+inputs);
                                            for (var i = 0; i <= inputs.length; i++){
                                                var input = inputs[i];
                                                if (input != "undefined") {
                                                    if( input.getAttribute('type') == "checkbox" && state){
                                                        input.setAttribute("checked", state);
                                                        // alert("input=if=="+input);
                                            
                                                    }
                                                    else{
                                                        input.setAttribute('checked', false);
                                                        input.removeAttribute('checked');
                                                        //alert("input=else=="+input);
                                                    }
                                                }
                                            }
                                        }
    
    
                                    </script>
                                    <h:selectBooleanCheckbox id="t0" onclick="checkAllCheckboxesInTable( this.id, this.checked );" >
                                        <a4j:support event="onchange" reRender="si" />
                                    </h:selectBooleanCheckbox>
                                </h:panelGroup>
                            </f:facet>
                            <h:selectBooleanCheckbox id="t1" value="#{empliab.checked}" disabled="#{empliab.btnDisabled}"/>
                                <o:ajax execute="t1" event="change" render="empDetail" />
                        </h:column>
                        <f:facet name="footer">
                            <rich:datascroller for="si" page="20" />  
                        </f:facet>  
                    </rich:dataTable>  
                </h:panelGrid>   
                <a4j:commandButton  id ="b1" reRender="#{EmpSalaryLiabilityBean.salaryPayableRecord}, si, myForm, empDetail" action="#{EmpSalaryLiabilityBean.Updatesalaryprocess}" value="Submit" />
                <a4j:support event="onchange" reRender="si" />
            </h:form>
        </rich:panel>   
    </f:view>
    
  