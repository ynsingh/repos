<%-- 
    Document   : PurchaseInvoiceDetails
    Created on : 6 Aug, 2012, 1:13:28 PM
    Author     : Tanvir Ahmed , Saeed-uz-Zama & mkhan
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
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Purchase.PurchaseInvoiceDetailforPO')" /></p>
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
                                          key="Purchase.PONo" name="pibm.erpmPoMaster.pomPoNo" readonly="True" >
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:label value="........." cssClass="tdSpace"></s:label>

                            <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="20"
                                          key="Purchase.SupplierInvoiceNo" name="pibm.pimSupplierInvoiceNo" readonly="True" >
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                            <s:textfield cssClass="textInputRO"  requiredposition="left" maxLength="50" size="20"
                                         key="Purchase.InvoiceType" name="pibm.pimInvoiceType" readonly="True" >
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{7}" />
                            </s:textfield>

                            <s:select cssClass="textInput" key="Purchase.ItemName" name="pid.erpmItemMaster.erpmimId" headerKey="" disabled="PidDisable"
                                        headerValue="-- Please Select --" list="podList" listKey="erpmItemMaster.erpmimId"
                                        listValue="erpmItemMaster.erpmimItemBriefDesc" >
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:label value="........." cssClass="tdSpace"></s:label>

                            <s:textfield key="Purchase.Quantity" required="" name="pid.pidQuantity" headerKey="" tabindex="0" disabled="PidDisable" value="0"
                                         cssClass="textInput">
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>
                            <s:checkbox key="Purchase.HaveYouCheckedTheQuantity" name="checked" />
                            <s:checkbox key="Purchase.HaveTheItemsBeenVerified" name="verified" />

                            <s:textarea cssClass="textArea"  rows="3" cols="50" key="Purchase.CheckedAndVerifiedRemarks" name="CheckAndVerifiedRemarks" maxLength="100">
                               <s:param name="labelcolspan" value="%{1}" />
                               <s:param name="inputcolspan" value="%{1}" />
                            </s:textarea>
                            
                                <tr><td> &nbsp; </td></tr>

                                <tr><td align="left">

                                    <s:submit theme="simple"  key="Purchase.Save"   action="SavePurchaseInvoiceDetail" disabled="PidDisable">
                                    </s:submit>

                                    <s:submit theme="simple"  key="Purchase.PreviousPage"   action="BackToPurchaseInvoiceMaster" >
                                    </s:submit>

                                    <s:submit  cssClass="savebutton"  name="btnSubmit" key="Purchase.SaveCheckVerify" action="SaveCheckAndVerifyforPO"
                                               disabled="btnCheckAndVerifySave"/>
                                </td><td align="left">
                                   
                               </td></tr>

                                <tr><td> &nbsp; </td></tr>
                            </tbody>
                    </table>
                </s:form>
            <s:if test="erpmpidList.size() > 0">
                        <hr>
                    <s:label value="%{getText('')}" cssClass= "pageSubHeading">
                        <s:param name="labelcolspan" value="%{0}" />
                        <s:param name="inputcolspan" value="%{11}" />
                    </s:label>
                    <hr>

       
            <s:form name="frmPurchaseInvoiceDetail" align="left">
               <s:hidden name="pid.pidPidId" />
             
               
                  <display:table name="erpmpidList" pagesize="10" decorator="Purchase.PurchaseDecorator"
                               excludedParams="*" export="false" cellpadding="8"
                               cellspacing="2" id="doc"
                               requestURI="/Purchase/PurchaseInvoiceAction.Action">

                        <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader" style="width:5%"> 
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name"
                                    maxLength="35" headerClass="gridheader" style="width:15%"
                                    class="griddata" sortable="true"/>
                        <display:column property="pidQuantity" title="Quantity"
                                    maxLength="35" headerClass="gridheader" style="width:5%"
                                    class="griddata" sortable="true"/>
                        <display:column property="pidRate" title="Rate" style="width:5%"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
   
                        <display:column paramId="pidPidId" paramProperty="pidPidId" property="editviewinvoice"
                                    href="/pico/Purchase/EditPurchaseInvoiceDetailforPO.action" style="width:10%"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit Item/View Serial Detail"/>

                        <display:column property="checkedinvoice" paramId="pidPidId" title="Checked" paramProperty="pidPidId"
                                    href="/pico/Purchase/checkedforPO.action" style="width:5%"
                                    headerClass="gridheader" class="griddata" media="html" sortable="true"/>

                        <display:column property="verifiedinvoice" paramId="pidPidId" title="Verified" paramProperty="pidPidId"
                                    href="/pico/Purchase/verifiedforPO.action" style="width:5%"
                                    headerClass="gridheader" class="griddata" media="html" sortable="true"/>

                        <%-- <display:column paramId="pidPidId" paramProperty="pidPidId"
                                    href="/pico/Purchase/ViewSerialInfo.action"
                                    headerClass="gridheader" class="griddata" media="html"  value="ViewSerialInfo" title="ViewSerialInfo"/>
                        --%>
                       <display:column paramId="pidPidId" paramProperty="pidPidId" style="width:10%"
                                    href="/pico/Purchase/ViewTaxInfoPO.action"
                                    headerClass="gridheader" class="griddata" media="html"  value="ViewTaxInfo" title="ViewTaxInfo"/>
                  </display:table>
                    <br>
               

             </s:form>
               </s:if>
               </div>
                        </div>
                <br>
                <br>

            <div id="footer">
                 <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
