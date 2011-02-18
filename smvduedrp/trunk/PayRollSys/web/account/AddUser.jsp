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
                            <h:outputText value="User Name"/>
                        </f:facet>
                        <h:outputText value="#{user.userName}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText  value="User Group"/>
                        </f:facet>
                        <h:outputText value="#{user.userGroup.name}" />
                    </h:column>

                </rich:dataTable>
                </rich:panel>


                
                <h:outputText value="#{UserBean.message}"/>

                <rich:modalPanel id="pnl">
                    <rich:panel header="Add New User">
                <h:form id="users">
                    <h:panelGroup layout="table" >
                        <h:panelGrid
                            columns="2"
                            styleClass="myTable"
                            columnClasses="label,field,label,field">
                            <f:facet name="header">
                                <h:outputText value="Add new user"/>
                            </f:facet>
                            <h:outputText value="User Name: "/>
                            <h:inputText value="#{UserBean.userName}"/>
                            <h:outputText value="Password: "/>
                            <h:inputSecret value="#{UserBean.password}"/>
                            <h:outputText value="Verify Password: "/>
                            <h:inputSecret value="#{UserBean.pass2}"/>
                            <h:outputText value="Administrator ?"/>
                            <h:selectBooleanCheckbox label="YES" value="#{UserBean.admin}"/>
                            <h:commandButton styleClass="panel" value="Save" action="#{UserBean.save}" />
                            <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                        </h:panelGrid>
                        
                    </h:panelGroup>
                </h:form>
                </rich:panel>
                </rich:modalPanel>
            </f:view>

    </body>
</html>
