<%--
    Document        : Designation.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 4:03 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Designation</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
    </head>
    <body class="subpage" id="">
        <h1>Designations</h1>
        <div class="container_form">
            <f:view>

                <h:form id="org">
                    <h:dataTable  id="designation"  border="1">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Organization Profile"/>
                            </f:facet>                            
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Organization Name"/>
                            </f:facet>
                            <h:outputText value="#{OrgProfileBean.name}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText  value="Tag Line"/>
                            </f:facet>
                            <h:outputText value="#{OrgProfileBean.tagLine}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText  value="Logo Image"/>
                            </f:facet>
                            <h:graphicImage value="#{OrgProfileBean.theFile}"/>
                        </h:column>

                    </h:dataTable>               
                    
                    <h:commandButton value="Save" action="#{DesignationBean.save}"  />
                </h:form>

            </f:view>
        </div>
    </body>
</html>
