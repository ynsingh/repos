<%-- 
    Document   : AnnualTax
    Created on : 12 Apr, 2012, 11:10:52 AM
    Author     : smvdu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
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
            <rich:panel header="Report Exporter" style="height:70px;">
                <rich:panel style="width:230px;float:left;text-align:center">
                <h:form>
                    <a4j:commandButton value="ExportAsHTML" action="#{annualTaxReportGen.annualtaxReportAsHtml}"/>
              </h:form>
                </rich:panel>


                <rich:panel id="pdf" style="width:230px;float:left;text-align:center">
                        <h:form>
                            <h:commandButton value="ExportAsPDF" action="#{annualTaxReportGen.annualtaxReportAsPDF}"/>

                </h:form>
                </rich:panel>

                <%--        <rich:panel id="excel" style="width:230px;float:left;text-align:center">
                        <h:form>
                            <h:commandButton value="ExportAsExcel" action="#{taxReportGen.employeeWiseExcel}"/>
                   </h:form>
                    </rich:panel>   --%>

              </rich:panel>

        </f:view>
    </body>
</html>