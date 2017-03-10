<%-- 
    Document   : EmpIndividualGrossSalary
    Created on : Feb 21, 2017, 12:28:54 PM
    Author     : Copyright (c) 2010 - 2011 SMVDU, Katra.
                 Copyright (c) 2014 - 2017 IIT, Kanpur. 
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*  @author : Manorama Pal (palseema30@gmail.com), IITK  
  
*
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
     <%--<link rel="stylesheet" type="text/css" href="../css/printstyle.css" media="print"/>--%>
     <style media="print">
    
        /*@media print {
            body {transform: scale(.5);}
            table {page-break-inside: avoid;}
        }*/
        
        #mainpnl{
          visibility:hidden;
        }
     
    </style>
    <script type="text/javascript">
        $("#btnPrint").live("click", function () {
          //  $('link[title="printLandscape"]').attr('disabled', 'disabled').remove();
            var divContents = $("#dvContainer").html();
            var printWindow = window.open('', '', 'height=250,width=300');
            //printWindow.document.write('</head><body><table border="1" cellspacing="0"><tr><td>');
            printWindow.document.write(divContents);
            //printWindow.document.write('</td></tr></table></body></html>');
            printWindow.document.close();
            printWindow.print();
       
        });
        
     
    </script>
</head>     

<body>
<f:view>
        
    <rich:panel  id="mainpnl" header="Employee Annual Gross Salary" >
        <a4j:commandLink  onclick="javascript:window.print();">
            <rich:toolTip value="Click for Print" for="btnPrint"/>  
        <h:graphicImage value="/img/Printer-icon.png" alt="Print"  /> 
        </a4j:commandLink>
                    
    </rich:panel>    
        <div id="Container2">
            <rich:panel id="panl2">
           
            <h:column>
                <h:outputText styleClass="out2" style="font-size:1.3em;font-weight:bold;margin-;margin-left:200px;margin-top:300px;"  value="#{OrgController.currentOrg.name}"/>
            </h:column>
            <br/>
            <h:column>
                <h:outputText style="font-size:1.2em;font-weight:bold;bold;margin-;margin-left:210px;"  value="Salary Statement For Financial Year  "/>
                <h:outputText style="font-size:1.2em;font-weight:bold;bold;"  value="#{EmpGrossSalaryController.financialYear}"/>
                <%--<h:outputText style="font-size:1.2em;font-weight:bold;align:center;"  value="#{EmpGrossSalaryController.dateTo} #{EmpGrossSalaryController.dateFrom}"/>--%>
            </h:column>
            <h:panelGrid columns="2">
                <h:column>
                    <h:outputText style="font-weight:bold"  value="Employee Code - "/>
                    <h:outputText style="" value="#{EmpGrossSalaryController.employee.code}"/>  
                </h:column>
                <h:column>
                    <h:outputText style="font-weight:bold;margin-;margin-left:300px;" value="Name - "/>
                    <h:outputText style=""  value="#{EmpGrossSalaryController.employee.name}"/>
                </h:column>
                <h:column>  
                    <h:outputText style="font-weight:bold" styleClass="Label" value="Department - "/>
                    <h:outputText style="margin-;margin-right:20px;" styleClass="Label" value="#{EmpGrossSalaryController.employee.deptName}"/>
                </h:column>
                <h:column>  
                    <h:outputText style="font-weight:bold;margin-;margin-left:300px;" styleClass="Label" value="Designation - "/>
                    <h:outputText style="margin-;margin-right:20px;" styleClass="Label" value="#{EmpGrossSalaryController.employee.desigName}"/>
               </h:column>
                
            </h:panelGrid>
            </rich:panel>
            <rich:dataTable id="dataTL" width="100%" value="#{EmpGrossSalaryController.salaryMatrix}" var="matrix">
        
            <h:column >
                <f:facet name="header">
                    <h:outputText value="Salary Head"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryHead}"/>
            </h:column>

             <h:column>
                <f:facet name="header">
                    <h:outputText value="April"/>
                </f:facet>
                 <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[0]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="May"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[1]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="June"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[2]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="July"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[3]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="August"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[4]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="September"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[5]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="October"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[6]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="November"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[7]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="December"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[8]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="January"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[9]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="February"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[10]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="March"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[11]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Total"/>
                </f:facet>
                <h:outputText style="font-weight:bold" value="#{matrix.salaryData[12]}" >
                </h:outputText>
            </h:column>
            
        </rich:dataTable>
       
         </div>  
</f:view>
</body>    
 </html>           
