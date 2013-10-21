<%-- 
    Document   : PurchaseInvoice
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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
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
                <jsp:include page="../Administration/header.jsp"  flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" > <br><br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <div style ="background-color: #215dc6;" align="center">
                    <p align="center" class="pageHeading" style="color: #ffffff">PURCHASE INVOICE/BILL</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

             <div style="border: solid 1px #000000; background: gainsboro">
                    <br>
                   
               <s:form name="frmPurchaceInvoiceBill" action="PurchaseInvoiceAction"  theme="qxhtml">
                    <s:hidden name="pibm.pimPimId" />
                         
                 <table border="0" cellpadding="4" cellspacing="0" align="center" >
                 <tbody>

                            <s:select label="Institute" required="true" requiredposition="" name="pibm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                      cssClass="textInput"
                                      onchange="getSubinstitutionList('PurchaseInvoiceAction_pibm_institutionmaster_imId', 'PurchaseInvoiceAction_pibm_subinstitutionmaster_simId');">
                                 <s:param name="labelcolspan" value="%{1}" />
                                 <s:param name="inputcolspan" value="%{3}" />
                            </s:select>
                           
                            <s:select label="SubInstitute" required="true" requiredposition="" name="pibm.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName"
                                      cssClass="textInput"
                                      onchange="getDepartmentList('PurchaseInvoiceAction_pibm_subinstitutionmaster_simId', 'PurchaseInvoiceAction_pibm_departmentmaster_dmId');">
                                 <s:param name="labelcolspan" value="%{3}" />
                                 <s:param name="inputcolspan" value="%{2}" />
                            </s:select>

                            <s:select label="Department" required="true" requiredposition=""  name="pibm.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"  cssClass="textInput">
                                 <s:param name="labelcolspan" value="%{1}" />
                                 <s:param name="inputcolspan" value="%{3}" />
                            </s:select>
                            
                     <%--   <s:select label="Invoice Type" required="true" name="pibm.pimInvoiceType" headerKey="" headerValue="-- Please Select --" list="{'Only Invoice','Invoice Cum Challan'}"  cssClass="queryInput"
                                      onchange="EnableDisablePO_Challan('PurchaseInvoiceAction_pibm_pimInvoiceType','PurchaseInvoiceAction_pibm_erpmPoMaster_pomPoMasterId','PurchaseInvoiceAction_pibm_erpmPurchasechallanMaster_pcmPcmId','PurchaseInvoiceAction_showPOreport');">
                                 <s:param name="labelcolspan" value="%{1}" />
                                 <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                     --%>
                            <s:select label="Invoice Type" required="true" requiredposition="" name="pibm.pimInvoiceType" headerKey="" headerValue="-- Please Select --" list="{'Only Invoice','Invoice Cum Challan'}"  cssClass="queryInput"
                                      onchange="EnableDisablePO_Challan('PurchaseInvoiceAction_pibm_pimInvoiceType','PurchaseInvoiceAction_pibm_erpmPoMaster_pomPoMasterId','PurchaseInvoiceAction_pibm_erpmPurchasechallanMaster_pcmPcmId','PurchaseInvoiceAction_showPOreportInInvoice');">
                                 <s:param name="labelcolspan" value="%{1}" />
                                 <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:textfield label="Exchange Rate" required="" requiredposition="left" maxLength="40" size="20"
                                          name="pibm.pimImportExchangeRate" title="Enter Order"  cssClass="textInput">
                                 <s:param name="labelcolspan" value="%{1}" />
                                 <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>
                       

                            <s:textfield label="Invoice Recieved Date" required="true" requiredposition="" maxLength="40" size="20"
                                          name="invoicerecvDate" title="Enter Order"  cssClass="textInput">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:textfield label="Supplier Invoice No" required="true" requiredposition="" maxLength="40" size="20"
                                          name="pibm.pimSupplierInvoiceNo" title="Enter Order"  cssClass="textInput">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:textfield required="true"  requiredposition="" maxLength="100" size="20"
                                         label="Supplier Invoice Date" name="suplierinvoiceDate" title="Enter Order"  cssClass="textInput">
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>
               
                            <s:select label="Supplier Name" required="true"  requiredposition="" name="pibm.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="smList" listKey="smId" listValue="smName"  cssClass="textInput"    
                                onchange="getChallanList('PurchaseInvoiceAction_pibm_suppliermaster_smId', 'PurchaseInvoiceAction_pibm_erpmPurchasechallanMaster_pcmPcmId');
                                getPOList('PurchaseInvoiceAction_pibm_suppliermaster_smId', 'PurchaseInvoiceAction_pibm_erpmPoMaster_pomPoMasterId');">
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{7}" />
                            </s:select>

                     <s:select label="Purchase order no" required="true" requiredposition=""  name="pibm.erpmPoMaster.pomPoMasterId"  headerKey="" headerValue="-- Please Select --" list="pomList"  listKey="poid" listValue="pono" cssClass="textInput">
                                 <s:param name="labelcolspan" value="%{1}" />
                                 <s:param name="inputcolspan" value="%{1}" />
                            </s:select>
                            <s:submit name="showPOreportInInvoice"  value="Show PO" action="showPOreportInInvoice"/>

                            <s:select label="Challan no" required="true" requiredposition="" name="pibm.erpmPurchasechallanMaster.pcmPcmId"  headerKey="" headerValue="-- Please Select --" list="pcmList" listKey="pcmPcmId" listValue="pcmChallanNo"  cssClass="textInput">
                                 <s:param name="labelcolspan" value="%{1}" />
                                 <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

         
                            <tr><td> &nbsp; </td></tr>

                            <tr><td align="left">
                            <s:submit theme="simple" name="btnSubmit" value="SAVE"   action="SavePurchaseInvoice" />

                            </td><td align="left">

                            <s:submit theme="simple" name="btnSubmit" value="BROWSE"    action="BrowsePurchaseInvoice"/>

                            <s:submit theme="simple" name="bthReset" value="CLEAR"  action="ClearPurchaseInvoice"  />

                            <s:submit theme="simple" name="showGFRreport"  value="Show GFR" action="showGFRreportInvoice" disabled="varShowGFR" />
                            </td></tr>
                            <tr><td> &nbsp; </td></tr>

                        </tbody>
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
