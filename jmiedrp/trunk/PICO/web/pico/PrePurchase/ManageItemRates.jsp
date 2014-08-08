
<%--  
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
        <meta name="author" content="sajidaziz00000, Jamia Millia Islamia">
        <meta name="email" content="sajidaziz00@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
         <s:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
                        
            <jsp:include page="../Administration//jobBar.jsp" flush="true"></jsp:include>            
            

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">            

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">MANAGE ITEM RATES</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
       

         <div style="border: solid 1px #000000; background:  gainsboro">
         <table style="height:19em;" width="80%"><tr><td valign="top">
         <s:form name="FrmItemRate" action="SaveItemRate" theme="qxhtml">
                <s:hidden name ="itemrate.irItemRateId" />
                <s:hidden name="itemratedet.irdItemRateDetailsId" />
                <s:hidden name="itemratetax.irtItemRateTaxesId" />

                 <s:select key="PrePurchase.Institution" required="true" requiredposition="" name="itemrate.institutionmaster.imId" cssClass="textInput" value="DefaultInsitute1"
                           headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" title = "Select Institution"
                           onchange="getItemforInsituteList('SaveItemRate_itemrate_institutionmaster_imId', 'SaveItemRate_itemrate_erpmItemMaster_erpmimId');" ondblclick="getInsituteaftervalidation('SaveIndentRate_itemrate_institutionmaster_imId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{7}" />
                 </s:select>

                 <s:select cssClass="textInput" key="PrePurchase.ItemName" required="true" requiredposition="" name="itemrate.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                           list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" title="Select Item from the List"
                           onchange="getsupplierforInsituteList('SaveItemRate_itemrate_institutionmaster_imId', 'SaveItemRate_itemrate_suppliermaster_smId');"
                           ondblclick="getitemList_2('SaveIndentRate_itemrate_erpmItemMaster_erpmimId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{4}" />
                 </s:select>

                 <s:label value="...." cssClass="tdSpace"/>

                 <s:select cssClass="textInput" key="PrePurchase.SupplierName" required="true" requiredposition="" name="itemrate.suppliermaster.smId"  title="Select Supplier from the List"
                           headerKey="" headerValue="-- Please Select --" list="suppList" listKey="smId" listValue="smName"
                           ondblclick="getsupplieraftervalidation('SaveIndentRate_itemrate_suppliermaster_smId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{4}" />
                 </s:select>

                 <s:select cssClass="textInput" key="PrePurchase.CurrencyOfPurchase" required="true" requiredposition="" name="itemrate.erpmGenMasterByIrCurrencyId.erpmgmEgmId" title="Enter Currency of Purchase"
                           headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                           ondblclick="getCurrencyAfterValidation('SaveItemRate_itemrate_erpmGenMasterByIrCurrencyId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{4}" />
                 </s:select>
<s:label value="..." cssClass="tdSpace"/>
                 <s:textfield cssClass="textInput" required="true" requiredposition="left" maxLength="11" size="25"
                              key="PrePurchase.UnitRate" name="itemrate.irdRate"  title="Enter Unit Rate" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{4}" />
                 </s:textfield>

                 <s:textfield cssClass="queryInput" required="true" requiredposition="left" maxLength="10" size="10" title="Enter Approval Effective From Date [MM-DD-YYYY]"
                              key="PrePurchase.ApprovalEffectiveFromDate" name="effDate" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{4}" />
                 </s:textfield>
<s:label value="..." cssClass="tdSpace"/>
                 <s:textfield cssClass="queryInput" required="true" requiredposition="left" maxLength="10" size="10" title="Enter last date of approval validity [MM-DD-YYYY]"
                              key="PrePurchase.ApprovalValidUptoDate" name="validUptoDate" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{4}" />
                 </s:textfield>

                 <s:textfield cssClass="queryInput" required="true" requiredposition="left" maxLength="10" size="10" title="Enter Minimum Quantity for which the rate is valid"
                              key = "PrePurchase.MinimumQuantity" name="itemrate.irMinQty">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{4}" />
                 </s:textfield>
<s:label value="..." cssClass="tdSpace"/>
                 <s:textfield cssClass="queryInput" required="true" requiredposition="left" maxLength="10" size="10" title="Enter Maximum Quantity for which the rate is valid"
                              key="PrePurchase.MaximumQuantity" name="itemrate.irMaxQty" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{4}" />
                 </s:textfield>



                 <s:textfield cssClass="textInput" required="true" requiredposition="left" maxLength="2" size="10" title="Enter warranty duration in months"
                              key="PrePurchase.WarrantyDuration" name="itemrate.irWarrantyMonths" value="12"  onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{4}" />
                 </s:textfield>
<s:label value="..." cssClass="tdSpace"/>

                 <s:select cssClass="textInput" key="PrePurchase.WarrantyStartsFrom" required="true" requiredposition="" name="itemrate.erpmGenMasterByIrWarrantyStartsFromId.erpmgmEgmId"
                           headerKey="" headerValue="-- Please Select --" list="wsfList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                           ondblclick="getWarrantyaftervalidation('SaveIndentRate_itemrate_erpmGenMasterByIrWarrantyStartsFromId_erpmgmEgmId');"
                           title="Select warranty start point">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{4}" />
                 </s:select>

                 <s:textarea cssClass="textInput"  rows="2" cols="90" key="PrePurchase.WarrantyDescription"
                             name="itemrate.irWarrantyClause" title="Enter warranty description">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{7}" />
                 </s:textarea>

                <tr><td> &nbsp; </td></tr>
                <s:label />

                <s:submit name="btnSubmit" key="PrePurchase.Save" cssClass="inputButton" >
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="btnFetch" key="PrePurchase.Browse" action="FetchItemRates"  cssClass="inputButton">
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="bthReset" key="PrePurchase.Clear"  action="ClearItemRates" cssClass="inputButton" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="bthReset" value="Export Item Rates"  action="ExportItemRates" cssClass="inputButton" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="bthReset" value="Export Item Rates History"  action="ExportItemRatesHistory" cssClass="inputButton" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="right" />
                </s:submit>
                <tr><td>
                <s:submit theme="simple" name="showGFRreport"  value="Show GFR" action="showGFRreportItemsRates" disabled="varShowGFR" >
                        <s:param name="colspan" value="%{3}" />
                        <s:param name="align" value="%{'center'}" />
                </s:submit>
                </td></tr>
                <tr><td> &nbsp; </td></tr>
                <s:label />

    </s:form>
    

    <s:if test="itemRateList.size()>0">
        <hr>
        <s:form name="frmItemRateBrowse">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr><td>
                <display:table  name="itemRateList" pagesize="15" style="width:100%"
                                    excludedParams="*" export="true" cellpadding="0"
                                    cellspacing="0" id="doc"
                                    requestURI="/pico/PrePurchase/ManageItemRates.action">

                    <display:column  class="griddata" title="SNo" sortable="true" 
                                     maxLength="100" headerClass="gridheader" style="width:5%">
                        <c:out> ${doc_rowNum}
                    </display:column>
                                       
                    <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item" style="width:15%"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    
                    <display:column property="suppliermaster.smName" title="Supplier" style="width:15%"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    
                    <display:column property="irdWefDate" title="WEF Date" style="width:9%"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    
                    <display:column property="irdWetDate" title="WET Date" style="width:9%"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    
                    <display:column property="irMinQty" title="Min Qty" style="width:5%"                                    
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    
                    <display:column property="irMaxQty" title="Max Qty" style="width:5%"                                    
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    
                    <display:column property="irdRate" title="Rate" style="width:9%"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    
                    <display:column property="erpmGenMasterByIrCurrencyId.erpmgmEgmDesc" title="Currency"
                                    headerClass="gridheader" class="griddata" sortable="false" style="width:5%"/>
                    
                    <display:column paramId="ItemRateId" paramProperty="irItemRateId" style="width:5%"
                                    href="/pico/PrePurchase/EditItemRate.action"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                    </display:column>
                    
                    <display:column paramId="ItemRateId" paramProperty="irItemRateId" style="width:5%"
                                    href="/pico/PrePurchase/DeleteItemRate.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                    </display:column>
                   
                    <display:column paramId="ItemRateId" paramProperty="irItemRateId" style="width:15%"
                                    href="/pico/PrePurchase/ItemTax.action?itemrate.irItemRateId=${param['itemrate.irItemRateId']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Add/Edit Taxes">
                                    Add/Edit Taxes
                    </display:column>

                </display:table>
                </td></tr>
                </table>
                <br>
        </s:form>
    </s:if>
                </td></tr>
         </table>
            
    </div>
    
    <br>
    </div>
    </div>
    <div id="footer">
        <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
    </div>
    
    </body>
</html>

