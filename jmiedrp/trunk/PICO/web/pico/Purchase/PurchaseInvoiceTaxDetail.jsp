
<%--
    Document   : PurchaseInvoiceDetails
    Created on : 6 Aug, 2012, 1:13:28 PM
    Author     : Tanvir Ahmed & Saeed & mkhan
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container" >
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent"> <br><br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br>
                <br>
                <div style ="background-color: #215dc6;" align="center">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Purchase.PurchaseInvoiceTaxDetail')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <div style="border: solid 1px #000000; background: gainsboro">
                <%-- <p align="center"><s:label value="PURCHASE INVOICE TAX DETAIL" cssClass="pageHeading"/></p>
                     <p align="center"><s:property value="message" /></p>--%>
                <s:form name="frmPurchaceInvoiceBill"   theme="qxhtml">
                    <s:hidden name="pibm.pimPimId" />
                    <s:hidden name="pid.pidPidId" />
                  

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>

                           
                            <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="20"
                                          key="Purchase.SupplierInvoiceNo" name="pibm.pimSupplierInvoiceNo" readonly="True" >
                                  <s:param name="labelcolspan" value="%{1}" />
                                  <s:param name="inputcolspan" value="%{7}" />
                            </s:textfield>

                            <s:textfield key="Purchase.ItemName" required="" name="pid.erpmItemMaster.erpmimItemBriefDesc" headerKey="" cssClass="textInput">
                                 <s:param name="labelcolspan" value="%{1}" />
                                 <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:textfield key="Purchase.Quantity" required="" name="pid.pidQuantity" headerKey="" cssClass="textInput">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>


                                <tr><td align="left">
                                    
                                    <s:submit  theme="simple" key="Purchase.PreviousPage"  action="BackToPurchaseInvoiceDetail"  >
                                    </s:submit>
                                        </td></tr>
                                <tr><td> &nbsp; </td></tr>
                            </tbody>
                    </table>
                </s:form>
            </div>
                <div id ="mainContent">
            <s:form name="frmPurchaseInvoiceDetail" align="left">
             
              <table width="65%" border="1" cellspacing="0" cellpadding="0">
<tr><td>
                  <display:table name="pitaxList" pagesize="10"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Purchase/PurchaseInvoiceAction.Action">

                        <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                            <display:column property="pitTaxName" title="Tax Name"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                        <display:column property="pitTaxPercent" title="Tax Percent"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                            <display:column property="pitTaxOnValuePercent" title="Tax On Value Percent"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                            <display:column property="pitSurchargePercent" title="Surcharge Percent"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>

                  </display:table>
                    <br></td></tr>
                </table>

             </s:form>
                    </div>
                &nbsp;
            </div>


            <div id="footer">
                 <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
