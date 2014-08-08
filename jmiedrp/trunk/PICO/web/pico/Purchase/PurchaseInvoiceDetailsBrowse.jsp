<%-- 
    Document   : PurchaseInvoiceDetailsBrowse
    Created on : 6 Aug, 2012, 1:13:28 PM
    Author     : Tanvir Ahmed & Saeed-uz-Zama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Budgettypemaster.js"></script>
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <s:form name="frmBrowsePurchaseInvoice.action">
                    <p align="center"><s:label value="PURCHASE INVOICE DETAILS" /></p>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                        <tr><td> <s:property value="message" /> </td></tr>
                        <display:table name="erpmpidList" pagesize="15"
                                       excludedParams="*" export="false" cellpadding="0"
                                       cellspacing="0" id="doc"
                                       requestURI="/Administration/AddDetailsAction.action">
                                <display:column  class="griddata" title="S.No" sortable="true" maxLength="50" headerClass="gridheader">
                                <c:out> ${doc_rowNum}
                                </display:column>

                                <display:column property="erpmPurchaseinvoiceMaster.erpmPoMaster.pomPoNo" title="PURCHASE ORDER NO"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:20%" sortable="true"/>

                                <display:column property="erpmItemMaster.erpmimDetailedDesc" title="Item Name"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:20%" sortable="true"/>
                                <display:column property="pidQuantity" title="Quantity"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:20%" sortable="true"/>
                                <display:column property="pidRate" title="Rate"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:20%" sortable="true"/>
                                <display:column property="pidDiscount" title="Discount"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:20%" sortable="true"/>
                                <display:column property="erpmPurchaseinvoiceTaxeses" title="Taxes"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:20%" sortable="true"/>
                                <display:column paramId="pidPidId" paramProperty="pidPidId"
                                                href="/pico/Administration/EditPurchaseInvoiceMasterAction.action" title="Edit"
                                                headerClass="gridheader" class="griddata" media="html" >
                                    Edit
                                </display:column>
                                <display:column paramId="pidPidId" paramProperty="pidPidId" title="Delete"
                                                href="/pico/Administration/DeletePurchaseInvoiceMasterAction.action"
                                                headerClass="gridheader" class="griddata" media="html" style="width:30%">
                                    Delete
                                </display:column>

                            </display:table>
                    </table>
                </s:form>

                <div id="footer">
                    <jsp:include page="footer.jsp" flush="true"></jsp:include>
                </div>
            </div>
    </body>
</html>

