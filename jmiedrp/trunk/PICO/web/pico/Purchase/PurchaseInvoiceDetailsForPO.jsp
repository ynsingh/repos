<%-- 
    Document   : PurchaseInvoiceDetails
    Created on : 6 Aug, 2012, 1:13:28 PM
    Author     : Tanvir Ahmed , Saeed-uz-Zama & mkhan
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
                <div style ="background-color: #215dc6;" align="center">
                    <p align="center" class="pageHeading" style="color: #ffffff">PURCHASE INVOICE DETAIL FOR PO</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <div style="border: solid 1px #000000; background: gainsboro">
                    <%--  <p align="center"><s:label value="PURCHASE INVOICE DETAIL" cssClass="pageHeading"/></p>
                        <p align="center"><s:property value="message" /></p>--%>
                <s:form name="frmPurchaceInvoiceBill"   theme="qxhtml">
                    <s:hidden name="pibm.pimPimId" />
                    <s:hidden name="pid.pidPidId" />
                    <s:hidden name="pibm.erpmPoMaster.pomPoMasterId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="20"
                                          label="Purchase order no" name="pibm.erpmPoMaster.pomPoNo" readonly="True" >
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:label value="........." cssClass="tdSpace"></s:label>

                            <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="20"
                                          label="Supplier Invoice No" name="pibm.pimSupplierInvoiceNo" readonly="True" >
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="20"
                                         label="Invoice Type" name="pibm.pimInvoiceType" readonly="True" >
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{7}" />
                            </s:textfield>

                            <s:select cssClass="textInput" label="Item Name" name="pid.erpmItemMaster.erpmimId" headerKey="" disabled="PidDisable"
                                        headerValue="-- Please Select --" list="podList" listKey="erpmItemMaster.erpmimId"
                                        listValue="erpmItemMaster.erpmimItemBriefDesc" >
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:label value="........." cssClass="tdSpace"></s:label>

                            <s:textfield label="Quantity" required="" name="pid.pidQuantity" headerKey="" tabindex="0" disabled="PidDisable"
                                         cssClass="textInput">
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>
                            <s:checkbox label="Have you checked the quantity" name="checked" />
                            <s:checkbox label="Have the items been verified" name="verified" />

                            <s:textarea cssClass="textArea"  rows="3" cols="50" label="Checked and verified Remarks" name="CheckAndVerifiedRemarks" maxLength="100">
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{1}" />
                            </s:textarea>
                            
                                <tr><td> &nbsp; </td></tr>

                                <tr><td align="left">

                                    <s:submit theme="simple"  value="Save"   action="SavePurchaseInvoiceDetail" disabled="PidDisable">
                                    </s:submit>

                                    <s:submit theme="simple"  value="Back"   action="BackToPurchaseInvoiceMaster" >
                                    </s:submit>

                                    <s:submit  cssClass="savebutton"  name="btnSubmit" value="Save Check & Verify" action="SaveCheckAndVerifyforPO"
                                               disabled="btnCheckAndVerifySave"/>
                                </td><td align="left">
                                   
                               </td></tr>

                                <tr><td> &nbsp; </td></tr>
                            </tbody>
                    </table>
                </s:form>
            </div>

        <div id ="mainContent">
            <s:form name="frmPurchaseInvoiceDetail" align="left">
               <s:hidden name="pid.pidPidId" />
             
               <table align="center" border="1" cellspacing="5" cellpadding="4">
                <tr><td>
                  <display:table name="erpmpidList" pagesize="10" decorator="Purchase.PurchaseDecorator"
                               excludedParams="*" export="true" cellpadding="5"
                               cellspacing="5" id="doc"
                               requestURI="/Purchase/PurchaseInvoiceAction.Action">

                        <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                        <display:column property="pidQuantity" title="Quantity"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                        <display:column property="pidRate" title="Rate"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
   
                        <display:column paramId="pidPidId" paramProperty="pidPidId" property="editviewinvoice"
                                    href="/pico/Purchase/EditPurchaseInvoiceDetailforPO.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit Item/View Serial Detail"/>

                        <display:column property="checkedinvoice" paramId="pidPidId" title="Checked" paramProperty="pidPidId"
                                    href="/pico/Purchase/checkedforPO.action" style="width:10%"
                                    headerClass="gridheader" class="griddata" media="html" sortable="true"/>

                        <display:column property="verifiedinvoice" paramId="pidPidId" title="Verified" paramProperty="pidPidId"
                                    href="/pico/Purchase/verifiedforPO.action" style="width:10%"
                                    headerClass="gridheader" class="griddata" media="html" sortable="true"/>

                        <%-- <display:column paramId="pidPidId" paramProperty="pidPidId"
                                    href="/pico/Purchase/ViewSerialInfo.action"
                                    headerClass="gridheader" class="griddata" media="html"  value="ViewSerialInfo" title="ViewSerialInfo"/>
                        --%>
                       <display:column paramId="pidPidId" paramProperty="pidPidId"
                                    href="/pico/Purchase/ViewTaxInfoPO.action"
                                    headerClass="gridheader" class="griddata" media="html"  value="ViewTaxInfo" title="ViewTaxInfo"/>
                  </display:table>
                    <br>
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
