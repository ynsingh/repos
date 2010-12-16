<%-- 
    Document   : SalaryOption
    Created on : Jul 5, 2010, 1:54:00 AM
    Author     : Algox
--%>


<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <h:form>
                    <h:panelGroup layout="table">
                        <h:panelGrid
                            columns="2"
                            styleClass="data_entry_form table_full_fixed"
                            columnClasses="label,field,label,field">
                            <f:facet name="header">
                                <h:outputText value="Salary Heading Settings"/>
                            </f:facet>

                            <h:outputText value="Select Employee Type"/>
                            <h:selectOneMenu value="#{ETHBean.typeCode}"
                                             immediate="true" onchange="this.form.submit( )" >
                                <f:valueChangeListener type="org.smvdu.payroll.beans.SalaryMap"/>
                                <f:selectItems value="#{EmployeeTypeBean.items}"/>
                            </h:selectOneMenu>
                        
                            <h:outputText value="Select Applicable Salary Heads"/>
                        
                                <h:selectManyCheckbox  id="marked" layout="pageDirection" value="#{ETHBean.salaryCode}">
                                    <f:selectItems value="#{SalaryHeadBean.items}" />
                                </h:selectManyCheckbox>
                            </h:panelGrid>
                        
                        
                            <h:commandButton value="Save" action="#{ETHBean.print}"  />
                        
                    </h:panelGroup>
                </h:form>
            </f:view>
        </div>
    </body>
</html>
