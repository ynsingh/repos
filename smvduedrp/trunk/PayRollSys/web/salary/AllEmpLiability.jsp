<%-- 
    Document   : AllEmpLiability
    Created on : Aug 18, 2014, 12:14:08 PM
    Author     : guest
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
        <title>JSP Page</title>
        <script>
            function myFunction() {
                var str = "salary on hold";
                var result = str.fontcolor("green");
                document.getElementById("c1").innerHTML = result;
            }
        </script>    
    </head>
    <body>
        
        
        <f:view>
          <%--<a4j:keepAlive beanName="EmpSalaryLiabilityBean"/>--%>
            
            <rich:panel id="addemplqualdetail" header="Employee Salary Liability Detail" styleClass="form" style="width:auto;">
                <h:form>
                    <h:panelGrid columns="4">
                        <h:outputText style="font-weight:bold" value="Select Month"/>
                        <h:selectOneMenu  value="#{EmpSalaryLiabilityBean.current}" >
                        <f:selectItem itemLabel="Select Month"/>
                        <f:selectItems value="#{EmpSalaryLiabilityBean.copyProvider}"/>
                        </h:selectOneMenu>
                        <h:outputText value=" "/>
                        <a4j:commandButton styleClass="panel" action="#{EmpSalaryLiabilityBean.loadliablityDetail}" value="Load" reRender="empliabDetail,addemplqualdetail"/>
                        <h:outputText style="font-weight:bold" value=" Month "/>
                        <h:outputText style="font-weight:bold" value="#{EmpSalaryLiabilityBean.currentMonthYear}"/>
                        
                    </h:panelGrid>
                    <br/><br/>
                    <rich:separator/> <br/>
                   <%-- <rich:panel id="liability">--%>
                    <h:panelGrid  id="empliabDetail" columns="6"  columnClasses="label,field">
                       <%--<h:inputHidden value="#{EmpSalaryLiabilityBean.current}"/>--%>
                        <rich:dataTable id="si"  value="#{EmpSalaryLiabilityBean.empliabrecord}" binding="#{EmpSalaryLiabilityBean.dataGrid}" var="empliab" rowKeyVar="row"  rows="20"
                                        onRowMouseOver="this.style.backgroundColor='#B5F3FB'" onRowMouseOut="this.style.backgroundColor='#{a4jSkin.rowBackgroundColor}'" style="width:1200px;">
                         <rich:column>
                            <f:facet name="header" >
                            <h:outputText value="Sr.No"/>
                            </f:facet>
                            <h:outputText value="#{empliab.srNo}" />
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                            <h:outputText styleClass="Label" value="Code"/>
                            </f:facet>
                            <h:outputText value="#{empliab.code}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                            <h:outputText styleClass="Label" value="Name"/>
                            </f:facet>
                            <h:outputText value="#{empliab.employee.name}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                            <h:outputText styleClass="Label" value="Department"/>
                            </f:facet>
                            <h:outputText value="#{empliab.employee.typeName}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                            <h:outputText styleClass="Label" value="Department"/>
                            </f:facet>
                            <h:outputText value="#{empliab.employee.deptName}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                            <h:outputText styleClass="Label" value="Designation"/>
                            </f:facet>
                            <h:outputText value="#{empliab.employee.desigName}"/>
                        </rich:column>
                        <rich:column>
                            <f:facet name="header">
                            <h:outputText styleClass="Label" value="Total Liability"/>
                            </f:facet>
                            <h:outputText value="#{empliab.liabiltyAmt}"/>
                        </rich:column>
                        <rich:column >
                            <f:facet name="header">
                            <h:outputText styleClass="Label" value="Status" />
                            </f:facet>
                            <%-- <h:outputText rendered="#{(EmpSalaryLiabilityBean.status == 'salary on hold!') ?'red':'none'};" value="#{empliab.status}" />--%>
                            
                           <h:outputText id="stat" style="color: #{empliab.status == 'salary processed' ? 'red' : 'green'};font-weight:bold;" value="#{empliab.status}" />
                           <o:ajax execute="stat" event="change" render="empfmlyDetail" />
                        </rich:column>
                        <f:facet name="footer">
                            <rich:datascroller for="si" page="20" />  
                        </f:facet>  
                       </rich:dataTable>  
                    </h:panelGrid>   
                   <%-- </rich:panel> --%>
                    </h:form>
                </rich:panel>   
                      
        </f:view>    
        
    </body>
</html>
