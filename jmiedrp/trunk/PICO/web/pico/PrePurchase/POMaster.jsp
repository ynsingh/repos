<%--
    Document   : Purchase Order Master form
    Created on : May  2011, 12:28:32 PM
    Author     : Sajid Aziz
    Revised By : S. Kazim NAqvi
               : August 05, 2012
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
                     <p align="left" class="pageHeading" style="color:  #ffffff"> &nbsp;Step 1 of 5 (Create Purchase Order)</p>
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

            <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" label="Purchase Order No" name="poNumber" readonly="true">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:textfield>

            <s:textfield  required="true" requiredposition="left" maxLength="10" size="10" title="PO Date [DD-MM-YYYY]"
                      label="PO Date" name="poDate" >
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textfield>

            <s:select  cssClass="textInput" label="Institution"  required="true" requiredposition="" name="pomaster.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="defaultInsitute"
                       onchange="getSubinstitutionList('SavePOMaster_pomaster_institutionmaster_imId', 'SavePOMaster_pomaster_subinstitutionmaster_simId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" label="Department Name"  required="true" requiredposition="" name="pomaster.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="defaultDepartment"
                      onchange="getsupplierforInsituteList('SavePOMaster_pomaster_institutionmaster_imId', 'SavePOMaster_pomaster_suppliermaster_smId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" label="College/Faculty/School"  required="true" requiredposition="" name="pomaster.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="defaultSubInsitute"
                      onchange="getDepartmentList('SavePOMaster_pomaster_subinstitutionmaster_simId', 'SavePOMaster_pomaster_departmentmaster_dmId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:select>
    
            <s:select cssClass="textInput" label="Supplier Name"  required="true" requiredposition="" name="pomaster.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="suppList" listKey="smId" listValue="smName"
                      onchange="getAddressForSupplier('SavePOMaster_pomaster_suppliermaster_smId', 'SavePOMaster_pomaster_supplierAddress_supAdId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" label="Supplier Address" required="true" requiredposition=""  name="pomaster.supplierAddress.supAdId" headerKey="" headerValue="-- Please Select --" list="saList" listKey="supAdId"  listValue="adLine1 + ' ' + adLine2 + ' ' + adCity">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" label="Currency of Purchase"  required="true" requiredposition="" name="pomaster.erpmGenMasterByPomCurrencyId.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId"  value="defaultCurrency" listValue="erpmgmEgmDesc">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select label="Purchase Mode "  name="pomaster.pomPurchaseMode" list="{'Import','Domestic '}">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" label="Payment Mode "  required="true" requiredposition=""  name="pomaster.erpmGenMasterByPomPaymentModeId.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="paymodelist" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc">
                  <s:param name="labelcolspan" value="%{2}" />
                  <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select cssClass="textInput" label="Approved BY"  required="true" requiredposition="" name="pomaster.erpmusersByPomApprovedById.erpmuId" headerKey="" headerValue="-- Please Select --" list="erpmuserlist" listKey="erpmuId" listValue="erpmuFullName">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:textfield  required="true"  requiredposition="left" maxLength="10" size="10" title="Delivery Date [DD-MM-YYYY]"
                      label="Delivery Date [DD-MM-YYYY]" name="deliveryDate" >
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textfield>

            <s:textfield cssClass="textInput" requiredposition="left" maxLength="50" size="50"
                         label="Tender/Quote Reference" name="pomaster.pomAgainstReferenceId" title="" onkeypress="return isNumberKey(event)">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textfield>

            <s:select label="PO Closed"  name="pomaster.pomAccomplished"  list="{'No','Yes'}">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:select  label="PO Canceled" name="pomaster.pomCancelled"   list="{'No','Yes'}">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:select>

            <s:textarea cssClass="textArea"  rows="2" cols="50" label="Remarks" name="pomaster.pomRemarks" title="">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
            </s:textarea>

            <s:submit name="btnSubmit" value="Save/Next Step">
                      <s:param name="colspan" value="%{1}" />
                      <s:param name="align" value="left" />
            </s:submit>

            <s:submit name="btnClear" value="Clear" action="ClearPOMaster">
                      <s:param name="colspan" value="%{1}" />
                      <s:param name="align" value="left" />
            </s:submit>

            <s:submit name="btnBrowsePO" value="Browse POs" action="browsePOs" >
                      <s:param name="colspan" value="%{1}" />
                      <s:param name="align" value="left" />
            </s:submit>

            <s:if test="pomaster.pomPoMasterId != null">
                <s:submit name="btnPrintPO" value="Indented Items" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>
                <s:submit name="btnPrintPO" value="Non Indented Items" action="prepareNonIndentedItemsForPO" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>

                <s:submit name="btnPrintPO" value="Terms & Conditions" action="prepareTermsForPO" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>

                <s:submit name="btnPrintPO" value="Items Distribution" action="prepareItemDeliveryLocationsForPO" disabled="false">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>
            </s:if>
            <s:else>
                <s:submit name="btnPrintPO" value="Indented Items" disabled="true">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>
                
                <s:submit name="btnPrintPO" value="Non Indented Items" disabled="true">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>

                <s:submit name="btnPrintPO" value="Terms & Conditions" disabled="true">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>

                <s:submit name="btnPrintPO" value="Item Distribution" disabled="true">
                          <s:param name="colspan" value="%{1}" />
                          <s:param name="align" value="left" />
                </s:submit>
                <tr><td>
                <s:submit theme="simple" name="showGFRreport"  value="Show GFR" action="showGFRreportPO" disabled="varShowGFR" >
                         <s:param name="colspan" value="%{3}" />
                         <s:param name="align" value="%{'center'}" />
                </s:submit>
                </td></tr>
            </s:else>
             <s:submit name="btnPrintPO" value="Print PO" action="PrintPO">
                      <s:param name="colspan" value="%{1}" />
                      <s:param name="align" value="left" />
            </s:submit>

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