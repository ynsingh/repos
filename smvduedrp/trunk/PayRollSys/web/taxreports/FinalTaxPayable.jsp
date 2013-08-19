<%-- 
    Document   : FinalTaxPayable
    Created on : 14 May, 2012, 2:49:22 PM
    Author     : smvdu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <rich:panel header="Report Exporter" style="height:70px;">
            <rich:panel>
                <h:form>
                    <a4j:commandButton value="ExportAsHtml" action="#{formSixteen.formsixteentaxReportAsHtml}"/>
                </h:form>
            </rich:panel>

             <rich:panel id="pdf" style="width:230px;float:left;text-align:center">
                        <h:form>
                            <h:commandButton value="ExportAsPDF" action="#{formSixteen.formsixteentaxReportAsPdf}"/>
                </h:form>
                </rich:panel>
            </rich:panel>




        </body>
    </html>
</f:view>