<%-- 
    Document   : AnnualAttendanceReport
    Created on : Feb 13, 2015, 10:51:56 AM
    Author     : Om Prakash<omprakashkgp@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <f:view>

<%--

            <%

                        String forward = request.getParameter("fwdLink");
                        String path = application.getRealPath("/");

            %> --%>
            <rich:panel header="Annual Report Exporter" style="height:85px;">
 
              <h:form>
                    <h:panelGrid columns="3">
                        <h:outputText value="Employee Code   " style="font-weight:bold" />
                        <h:inputText id="code" required="true" requiredMessage="Please Enter Employee Code" value="#{AttendanceControllerBean.code}"/>
                        <rich:suggestionbox for="code" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                        <h:column>
                            <h:outputText value="#{abc.name}"/>
                            </h:column>
                            <h:column>
                            <h:outputText value="#{abc.code}"/>
                            </h:column>
                        </rich:suggestionbox>
                        <rich:toolTip value="Enter few characters of name and choose from List" for="code"/>
                        <h:message styleClass="error" for="code" tooltip="Employee Type"/>
                    </h:panelGrid >
                  
                          <h:outputText value="View Attendance Report  " style="font-weight:bold" />
                          &nbsp;
                          <h:selectOneMenu value="#{AttendanceControllerBean.year}" >
                             <f:selectItems value="#{AttendanceControllerBean.itemsYear}"/>
                          </h:selectOneMenu>&nbsp;
                          <h:commandButton value="ExportAsPDF" action="#{AttendanceControllerBean.annualAttendanceReportPDF}" disabled="false"/>&nbsp;
                          <h:commandButton value="ExportAsHTML" action="#{AttendanceControllerBean.annualAttendanceReportHTML}" disabled="false"/> 
                       </h:form>

        </rich:panel>
     </f:view>
    </body>
</html>
