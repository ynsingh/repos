<%--
    Document        : AddUser.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 6:00 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New User</title>
        <link rel="stylesheet" type="text/css" href="../css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="../css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="../css/table.css"/>
    </head>
    <body>
    <body class="subpage" id="">

        
            
            <f:view>
                <rich:panel header="Existing Users">
                    <h:commandButton value="Add New" onclick="Richfaces.showModalPanel('pnl');"/>
                <rich:dataTable  value="#{UserBean.allUser}" var="user" border="1">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Login ID"/>
                        </f:facet>
                        <h:outputText value="#{user.userName}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText  value="User Group"/>
                        </f:facet>
                        <h:outputText value="#{user.userGroup.name}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText  value="Task Permission"/>
                        </f:facet>
                        <h:outputText rendered="#{!user.profileActive}" value="Deactive" />
                        <h:outputText rendered="#{user.profileActive}" value="Active" />
                    </h:column>

                </rich:dataTable>
                </rich:panel>


                
                <h:outputText value="#{UserBean.message}"/>

                <rich:modalPanel autosized="true" id="pnl">
                    <rich:panel  header="Add New User" >
                <h:form id="users">
                    
                        <h:panelGrid  width="300"
                            columns="2">
                            <h:outputText value="User Type"/>
                            <h:selectOneMenu value="#{UserBean.groupCode}">
                                <f:selectItems value="#{UserBean.groups}"/>
                            </h:selectOneMenu>
                            <h:outputText value="User Name: "/>
                            <h:inputText value="#{UserBean.userName}"/>
                            <h:outputText value="Password: "/>
                            <h:inputSecret value="#{UserBean.password}"/>
                            <h:outputText value="Verify Password: "/>
                            <h:inputSecret value="#{UserBean.pass2}"/>
                            <h:outputText value="Profile ID "/>
                            <h:inputText onblur="activate()"  id="empCode"value="#{UserBean.profileId}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                            <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.empId}"  suggestionAction="#{SearchBean.getSuggestion}">
                                    <h:column>
                                        <h:outputText value="#{abc.name}"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{abc.empId}"/>
                                    </h:column>
                                </rich:suggestionbox>
                                <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>


                            <h:outputText value="Administrator ?"/>
                            <h:selectBooleanCheckbox label="YES" value="#{UserBean.admin}"/>
                            <h:commandButton styleClass="panel" value="Save" action="#{UserBean.save}" />
                            <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                        </h:panelGrid>
                        
                    
                </h:form>
                </rich:panel>
                </rich:modalPanel>
            </f:view>

    </body>
</html>
