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
        <title>Add New Company / Institute</title>        
    </head>
    <body>
    <body class="subpage" id="">
            <f:view>
                <rich:panel header="Create Company/Institute Profile">
                    <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                    <h:form>
                        <h:panelGrid id="pnl" columns="3">
                        <h:outputText value="Institute Name"/>
                        <h:inputText id="instName" requiredMessage="Name cannot be empty" required="true" value="#{OrgProfileBean.name}"/>
                        <h:message for="instName" />
                        <h:outputText value="Master Password"/>
                        <h:inputSecret id="instpass" requiredMessage="Password Cant be Empty" required="true" value="#{OrgProfileBean.masterPassword}"/>
                        <h:message for="instpass" />
                        <h:outputText value="Re type Master Password"/>
                        <h:inputSecret id="instpass2" requiredMessage="Password Cant be Empty" required="true" value="#{OrgProfileBean.vpass}"/>
                        <h:message for="instpass2" />
                         <h:outputText value="Recovery E-Mail ID"/>
                         <h:inputText id="instrec" requiredMessage="Recovery E mail cannot be empty" required="true" value="#{OrgProfileBean.recoveryEMailId}"/>
                        <h:message for="instrec" />
                        <h:outputText value="Tag Line"/>
                        <h:inputText id="insttag" value="#{OrgProfileBean.tagLine}"/>
                        <h:message for="insttag" />
                        <h:outputText value="Address(Line 1)"/>
                        <h:inputText id="insta1" value="#{OrgProfileBean.address1}"/>
                        <h:message for="insta1" />
                        <h:outputText value="Address(Line 2)"/>
                        <h:inputText id="insta2" value="#{OrgProfileBean.address2}"/>
                        <h:message for="insta2" />
                        <h:outputText value="Website"/>
                        <h:inputText id="instweb" value="#{OrgProfileBean.web}"/>
                        <h:message for="instweb" />
                        <h:outputText value="Phone"/>
                        <h:inputText id="instph" value="#{OrgProfileBean.phone}"/>
                        <h:message for="instph" />
                        <h:outputText value="E-Mail"/>
                        <h:inputText id="instmail" value="#{OrgProfileBean.email}"/>
                        <h:message for="instmail" />
                        <a4j:commandButton value="Save" action="#{OrgProfileBean.save}"/>
                    </h:panelGrid>
                   </h:form>
                </rich:panel>


               
            </f:view>

    </body>
</html>
