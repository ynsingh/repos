<%-- 
    Document   : EmpSalaryLiability
    Created on : Aug 8, 2014, 3:20:09 PM
    Author     : Seema Pal, Kishore Kumar shukla
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
                    
    </head>
    <body>
    <f:view>
        <a4j:keepAlive beanName="SalaryProcessingBean"/>
         <rich:panel id="addemplqualdetail" header="Employee Salary Liability and Expenses Detail" styleClass="form" style="width:auto;">
          <h:form>
          <a4j:commandButton action="#{EmpSalaryLiabilityBean.callpage}" value="Show All"/><br/>
            </h:form>
            <rich:messages>
                <f:facet name="infoMarker">
                    <h:graphicImage url="/img/success.png"/>
                </f:facet>
                <f:facet name="errorMarker">
                    <h:graphicImage url="/img/err.png"/>
                </f:facet>
            </rich:messages><br/>
            
            <rich:separator/> <br/> 
            <%--
            <rich:panel style="height:40px;">
                <h:outputText value="#{salaryMessage.message}"/>
             </rich:panel><br/>
            <rich:separator/> <br/>--%>
           <h:panelGrid columns="3">
              <h:form>
              <rich:panel id="lpnl" header="Liability Ledger" style="width:300px;height:475px;">
                 <h:panelGrid columns="1">
                     
                            <f:facet name="header">
                             <h:outputText style="font-size:12px;align:right;" value="Total Liability : #{SalaryProcessingBean.totalIncome}"/>
                            </f:facet>
                          
                         <f:facet name="footer">
                        <h:outputText style="font-size:16px;align:right;" value=" Total :  #{SalaryProcessingBean.totalIncome}"/>
                        </f:facet>
                        
                   </h:panelGrid>
                   
            </rich:panel>
            <%--  <a4j:commandButton reRender="inpnl,dpnl,salpnl,ppanel"  id="sa5" value="Create Salary" action="#{SalaryProcessingBean.updateDatawithbudgets}"/>
              <h:commandButton value="Reset" onclick="this.form.reset()"/>--%>
            </h:form>
                  
              <rich:panel style="width:500px;height:475px;" header="Expenses" id="mypnl">
                  <h:form>
                                 <h:panelGrid columns="2">
                                        <rich:panel  id="inpnl" style="width:200px;height:400px;">
                                            <rich:dataTable  width="180px;" binding="#{SalaryProcessingBean.incomeGrid}"
                                                             value="#{SalaryProcessingBean.incomeHeads}" var="income">
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Income Head"/>
                                                    </f:facet>
                                                    <h:outputText rendered="#{!income.display}" value="#{income.name}"/>
                                                </h:column>
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Amount"/>
                                                    </f:facet>
                                                    <h:inputText rendered="#{!income.display}" requiredMessage="Input Numbers Only" style="width:60px;"  value="#{income.defaultValue}">
                                                        <f:convertNumber />
                                                    </h:inputText>
                                                </h:column>
                                               <f:facet name="footer">
                                                    <h:outputText value="Total : #{SalaryProcessingBean.totalIncome}"/>
                                                </f:facet>
                                            </rich:dataTable>
                                        </rich:panel>
                                        <rich:panel id="dpnl" style="width:200px;height:400px;">
                                            <rich:dataTable width="180px;" binding="#{SalaryProcessingBean.deductGrid}"
                                                            value="#{SalaryProcessingBean.deductHeads}" var="deduct">
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Deduction Head"/>
                                                    </f:facet>
                                                    <h:outputText rendered="#{!deduct.display}" value="#{deduct.name}"/>
                                                </h:column>
                                                <h:column>
                                                    <f:facet name="header">
                                                        <h:outputText value="Amount"/>
                                                    </f:facet>
                                                    <h:inputText rendered="#{!deduct.display}" style="width:60px;" value="#{deduct.defaultValue}"/>
                                                </h:column>
                                               <f:facet name="footer">
                                                    <h:outputText value="Total  : #{SalaryProcessingBean.totalDeduct}"/>
                                                </f:facet>
                                    </rich:dataTable>
                                    </rich:panel>
                                </h:panelGrid>
                                  </h:form>  
                  
                                 </rich:panel> 
                                    <rich:panel style="width:300px;height:475px;" header="Salary Tools">
                                    <h:form>
                                    <rich:panel style="width:280px;border:1;">
                                        <h:panelGrid columns="4">
                                            <h:outputText value="Employee Code"/>
                                            <h:inputText id="empcCode" value="#{SalaryProcessingBean.empCode}" />
                                            <rich:suggestionbox for="empcCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                                <h:column>
                                                    <h:outputText value="#{abc.name}"/>
                                                </h:column>
                                                <h:column>
                                                    <h:outputText value="#{abc.code}"/>
                                                </h:column>
                                            </rich:suggestionbox>
                                            <a4j:commandButton status="topstat" action="#{SalaryProcessingBean.loadProfile}" reRender="empDetail,mypnl,salpnl,lpnl" value="Load"/>
                                            <h:outputText value="For month"/>
                                            <h:outputText style="font-size:12px;foreground-color:red;" value="#{UserBean.currentMonthName}"/>
                                        </h:panelGrid>
                                    </rich:panel>
                                </h:form>
                                
                                    <rich:panel header="Employee Details">
                                    <h:panelGrid id="empDetail" columns="2">
                                        <h:outputText value="Code"/>
                                        <h:inputText readonly="true"  value="#{SalaryProcessingBean.employee.code}"/>
                                        <h:outputText value="Name"/>
                                        <h:inputText readonly="true" value="#{SalaryProcessingBean.employee.name}"/>
                                        <h:outputText value="Dept"/>
                                        <h:inputText readonly="true" value="#{SalaryProcessingBean.employee.deptName}"/>
                                        <h:outputText value="Desig."/>
                                        <h:inputText readonly="true" value="#{SalaryProcessingBean.employee.desigName}"/>
                                        <h:outputText value="Bank AC No"/>
                                        <h:inputText readonly="true"  value="#{SalaryProcessingBean.employee.bankAccNo}"/>
                                        <h:outputText value="Employee Type"/>
                                        <h:inputText readonly="true" value="#{SalaryProcessingBean.employee.typeName}"/>
                                    </h:panelGrid>
                                </rich:panel>
                                <rich:panel id="salpnl" header="Salary Summery">
                                    <h:panelGrid columns="2">
                                        <h:outputText style="font-size:14px;align:right;" value="Total Income"/>
                                        <h:outputText style="font-size:14px;align:right;" value="#{SalaryProcessingBean.totalIncome}"/>
                                        <h:outputText style="font-size:14px;align:right;" value="Total Deduction"/>
                                        <h:outputText style="font-size:14px;align:right;" value="#{SalaryProcessingBean.totalDeduct}"/>
                                        <h:outputText style="font-size:16px;align:right;" value="Net Salary"/>
                                        <h:outputText style="font-size:16px;align:right;" value="#{SalaryProcessingBean.gorssTotal}"/>
                                    </h:panelGrid>
                                </rich:panel>
                            </rich:panel>
                           
                </h:panelGrid>
           <%--</h:form>--%>
         </rich:panel>
          </f:view>
         </body>
</html>
