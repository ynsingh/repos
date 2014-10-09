<%--
    Document   : Purchase Order Master form
    Created on : May  2011, 12:28:32 PM
    Author     : Sajid Aziz
    Revised By : S. Kazim NAqvi
               : August 05, 2012
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
                   <div id ="mainContent" align="left">
         <br><br>         
         
         <s:bean name="java.util.HashMap" id="qTableLayout">
            <s:param name="tablecolspan" value="%{8}" />
         </s:bean>

         <div style="background-color: #215dc6;">
                     <p align="left" class="pageHeading" style="color:  #ffffff"> &nbsp<s:property value="getText('PrePurchase.Step1of5CreatePurchaseOrder')" /></p>
                     <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"/> </p>
         </div>
         <div style="border: solid 1px #000000; background:  gainsboro">


         <script type='text/javascript'>
            function isNumberKey(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57) )
                    return false;
                return true;
            }
        </script>

        <s:form name="Frmpomaster" action="SavePOMaster" theme="qxhtml">
            <p align="left" class="pageMessage"><s:property value="message" /></p>
            <s:hidden name ="pomaster.pomPoMasterId" />

            <s:iterator value="{1,1,1,1,1,1,1,1}">
                <s:label value="..." cssClass="tdSpace"/>
            </s:iterator>

            <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" key="PrePurchase.PurchaseOrderNo" name="poNumber" readonly="true">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:textfield>

            <s:textfield  required="true" requiredposition="left" maxLength="10" size="10" title="PO Date [DD-MM-YYYY]"
                      key="PrePurchase.PODate" name="poDate" >
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textfield>

            <s:select  cssClass="textInput" key="PrePurchase.Institution"  required="true" requiredposition="" name="pomaster.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="defaultInsitute"
                       onchange="getSubinstitutionList('SavePOMaster_pomaster_institutionmaster_imId', 'SavePOMaster_pomaster_subinstitutionmaster_simId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:select>
 <s:select cssClass="textInput" key="PrePurchase.SubInstitution"  required="true" requiredposition="" name="pomaster.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="defaultSubInsitute"
                      onchange="getDepartmentList('SavePOMaster_pomaster_subinstitutionmaster_simId', 'SavePOMaster_pomaster_departmentmaster_dmId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:select>
            <s:select cssClass="textInput" key="PrePurchase.Department"  required="true" requiredposition="" name="pomaster.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="defaultDepartment"
                      onchange="getsupplierforInsituteList('SavePOMaster_pomaster_institutionmaster_imId', 'SavePOMaster_pomaster_suppliermaster_smId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:select>

           
    
            <s:select cssClass="textInput" key="PrePurchase.SupplierName"  required="true" requiredposition="" name="pomaster.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="suppList" listKey="smId" listValue="smName"
                      onchange="getAddressForSupplier('SavePOMaster_pomaster_suppliermaster_smId', 'SavePOMaster_pomaster_supplierAddress_supAdId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" key="PrePurchase.SupplierAddress" required="true" requiredposition=""  name="pomaster.supplierAddress.supAdId" headerKey="" headerValue="-- Please Select --" list="saList" listKey="supAdId"  listValue="adLine1 + ' ' + adLine2 + ' ' + adCity">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" key="PrePurchase.CurrencyOfPurchase"  required="true" requiredposition="" name="pomaster.erpmGenMasterByPomCurrencyId.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId"  value="defaultCurrency" listValue="erpmgmEgmDesc">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select key="PrePurchase.PurchaseMode"  name="pomaster.pomPurchaseMode" list="{'Import','Domestic '}">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" key="PrePurchase.PaymentMode"  required="true" requiredposition=""  name="pomaster.erpmGenMasterByPomPaymentModeId.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="paymodelist" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc">
                  <s:param name="labelcolspan" value="%{2}" />
                  <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" key="PrePurchase.ApprovedBY"  required="true" requiredposition="" name="pomaster.erpmusersByPomApprovedById.erpmuId" headerKey="" headerValue="-- Please Select --" list="erpmuserlist" listKey="erpmuId" listValue="erpmuFullName">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:textfield  required="true"  requiredposition="left" maxLength="10" size="10" title="Delivery Date [DD-MM-YYYY]"
                      key="PrePurchase.DeliveryDate" name="deliveryDate" >
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textfield>

            <s:textfield cssClass="textInput" requiredposition="left" maxLength="50" size="50"
                         key="PrePurchase.Tender_QuoteReference" name="pomaster.pomAgainstReferenceId" title="" onkeypress="return isNumberKey(event)">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textfield>

            <s:select key="PrePurchase.POClosed"  name="pomaster.pomAccomplished"  list="{'No','Yes'}">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select  key="PrePurchase.POCanceled" name="pomaster.pomCancelled"   list="{'No','Yes'}">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:textarea cssClass="textArea"  rows="2" cols="50" key="PrePurchase.Remarks" name="pomaster.pomRemarks" title="">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textarea>

            <s:submit name="btnSubmit" key="PrePurchase.SaveOrNextStep">
                      <s:param name="colspan" value="%{1}" />
                      <s:param name="align" value="left" />
            </s:submit>

            <s:submit name="btnClear" key="PrePurchase.Clear" action="ClearPOMaster">
                      <s:param name="colspan" value="%{1}" />
                      <s:param name="align" value="left" />
            </s:submit>

            <s:submit name="btnBrowsePO" key="PrePurchase.Browse" action="browsePOs" >
                      <s:param name="colspan" value="%{1}" />
                      <s:param name="align" value="left" />
            </s:submit>

            <s:if test="pomaster.pomPoMasterId != null">
                <s:submit name="btnPrintPO" key="PrePurchase.IndentedItems" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>
                <s:submit name="btnPrintPO" key="PrePurchase.NonIndentedItems" action="prepareNonIndentedItemsForPO" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>

                <s:submit name="btnPrintPO" key="PrePurchase.TermsConditions" action="prepareTermsForPO" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>

                <s:submit name="btnPrintPO" key="PrePurchase.ItemsDistribution" action="prepareItemDeliveryLocationsForPO" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>
            </s:if>
            <s:else>
                <s:submit name="btnPrintPO" key="PrePurchase.IndentedItems" disabled="true">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>
                
                <s:submit name="btnPrintPO" key="PrePurchase.NonIndentedItems" disabled="true">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>

                <s:submit name="btnPrintPO" key="PrePurchase.TermsConditions" disabled="true">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>

                <s:submit name="btnPrintPO" key="PrePurchase.ItemsDistribution" disabled="true">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>
                <tr><td>
                <s:submit theme="simple" name="showGFRreport"  key="PrePurchase.ShowGFR" action="showGFRreportPO" disabled="varShowGFR" >
                         <s:param name="colspan" value="%{3}" />
                         <s:param name="align" value="%{'center'}" />
                </s:submit>
                
            </s:else>
<tr><td>
             <s:submit name="btnPrintPO" key="PrePurchase.Print" action="PrintPO">
                      <s:param name="colspan" value="%{1}" />
                      <s:param name="align" value="left" />
            </s:submit>
</td></tr>
      </s:form>
            <br>
         </div>
       <br><br>
       </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
