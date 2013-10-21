<%-- 
    Document   : PurchaseInvoiceDetails
    Created on : 6 Aug, 2012, 1:13:28 PM
    Author     : Tanvir Ahmed & Saeed-uz-Zama & mkhan
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
        <!--<script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>-->
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
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent"> <br><br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <div style ="background-color: #215dc6;" align="center">
                    <p align="center" class="pageHeading" style="color: #ffffff">PURCHASE INVOICE/BILL</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

             <div style="border: solid 1px #000000; background: gainsboro">
                    <br>
                <%--<p align="center"><s:label value="PURCHASE INVOICE DETAIL" cssClass="pageHeading"/></p>
                    <p align="center"><s:property value="message" /></p>--%>
                <s:form name="frmPurchaceInvoiceBill" action="SavePurchaseInvoiceDetailAction"  theme="qxhtml">
                    <s:hidden name="pid.pidPidId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>



                                   <s:select label="Item Master" required="true" name="pid.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="itemmList" listKey="erpmimId" listValue="erpmimItemBriefDesc"
                                             cssClass="textInput" >
                                    <s:param name="tablecolspan" value="%{2}"/>
                                    <s:param name="tablecolspan" value="%{6}"/>
                                   </s:select>

                                   <s:select label="Purchase Invoice Master" required="true" name="pid.erpmPurchaseinvoiceMaster.pimPimId" headerKey="" headerValue="None" list="pibmList" listKey="pimPimId" listValue="pimSupplierInvoiceNo"  cssClass="textInput">
                                   <s:param name="tablecolspan" value="%{2}"/>
                                   <s:param name="tablecolspan" value="%{6}"/>
                                   </s:select>

                                   <s:textfield  requiredposition="left" maxLength="100" size="100"
                                             label="Quantity" name="pid.pidQuantity" title="Enter Quantity"  cssClass="textInput"/>
                                    <s:param name="tablecolspan" value="%{2}"/>
                                    <s:param name="tablecolspan" value="%{6}"/>

                                    <s:textfield  requiredposition="left" maxLength="100" size="100" label="Discount" name="pid.pidDiscount" title="Enter Discount" cssClass="textInput"/>
                                    <s:param name="tablecolspan" value="%{2}"/>
                                    <s:param name="tablecolspan" value="%{6}"/>

                                    <s:textfield  requiredposition="left" maxLength="100" size="100" label="Rate" name="pid.pidRate" title="Enter Rate" cssClass="textInput"/>
                                    <s:param name="tablecolspan" value="%{2}"/>
                                    <s:param name="tablecolspan" value="%{6}"/>





                                    <s:submit theme="simple"  value="SAVE"   action="SavePurchaseInvoiceDetail" >
                                    </s:submit>
                                    <s:submit  theme="simple" value="BROWSE"    action="BrowsePurchaseInvoiceDetail" >
                                    </s:submit>
                                    <s:submit  theme="simple" value="CLEAR"  action="ClearPurchaseInvoiceDetail"  >
                                    </s:submit>



                            </tbody>
                    </table>
                </s:form>
            </div>



            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
