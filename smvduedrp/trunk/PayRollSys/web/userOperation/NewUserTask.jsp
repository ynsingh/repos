<%-- 
    Document   : NewUserTask
    Created on : Aug 2, 2012, 3:34:12 PM
    Author     : ERP
--%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body>
            <rich:panel>
                <h:form>
                    <h:panelGrid columns="1">
                        <h:column>
                            <h:outputText value="Employee ID"/>
                            <h:inputText onblur="activate()"  id="empCode" value="#{EmployeeBean.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                            <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                <h:column>
                                    <h:outputText value="#{abc.name}"/>
                                </h:column>
                                <h:column>
                                    <h:outputText value="#{abc.code}"/>
                                </h:column>
                            </rich:suggestionbox>
                            <a4j:commandButton value="Assign Task" action="#{userTaskBeans1.saveTaskList}"/>
                        </h:column>
                        <h:column>
                            <h:dataTable headerClass="headerStyle" rowClasses="rowStyle1" value="#{userTaskBeans1.taskList}" var="task" binding="#{userTaskBeans1.dataGrid}">
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="CheckBox"/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{task.check}">
                                        
                                    </h:selectBooleanCheckbox>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >

                                    </f:facet>
                                    <h:inputHidden value="#{task.taskId}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Task List"/>
                                    </f:facet>
                                    <h:outputText value="#{task.taskName}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=""/>
                                    </f:facet>
                                    <h:inputHidden value="#{task.taskValue}"/>
                                </h:column>
                            </h:dataTable>
                        </h:column>
                        <h:column>
                            <rich:separator/>
                        </h:column>
                    </h:panelGrid>
                </h:form>
            </rich:panel>
        </body>
    </html>
</f:view>