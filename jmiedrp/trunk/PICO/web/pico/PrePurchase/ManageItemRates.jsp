<%--
    Document   : IndentForm
    Created on : Feb 1, 2011, 12:28:32 PM
    Modified on; Feb 29, 2012, by SK Naqvi
    Author     : Sajid Aziz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="html" uri="/struts-tags"%>
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
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">

            <br><br>

                <p align="center"><s:label value="MANAGE ITEM RATES" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

        <s:form name="FrmItemRate" action="SaveItemRate" theme="qxhtml">
                <s:hidden name ="itemrate.irItemRateId" />
                <s:hidden name="itemratedet.irdItemRateDetailsId" />
                <s:hidden name="itemratetax.irtItemRateTaxesId" />


                 <s:select label="Institution"  name="itemrate.institutionmaster.imId" cssClass="queryInput" value="DefaultInsitute1"
                           headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" title = "Select Institution"
                           onchange="getItemforInsituteList('SaveItemRate_itemrate_institutionmaster_imId', 'SaveItemRate_itemrate_erpmItemMaster_erpmimId');" ondblclick="getInsituteaftervalidation('SaveIndentRate_itemrate_institutionmaster_imId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{7}" />
                 </s:select>

                 <s:select cssClass="queryInput" label="Item Name" name="itemrate.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --"
                           list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" title="Select Item from the List"
                           onchange="getsupplierforInsituteList('SaveItemRate_itemrate_institutionmaster_imId', 'SaveItemRate_itemrate_suppliermaster_smId');"
                           ondblclick="getitemList('SaveIndentRate_itemrate_erpmItemMaster_erpmimId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>

                 <s:label value="...." cssClass="tdSpace"/>

                 <s:select cssClass="queryInput" label="Supplier Name" name="itemrate.suppliermaster.smId"  title="Select Supplier from the List"
                           headerKey="" headerValue="-- Please Select --" list="suppList" listKey="smId" listValue="smName"
                           ondblclick="getsupplieraftervalidation('SaveIndentRate_itemrate_suppliermaster_smId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                 <s:select cssClass="queryInput" label="Currency of Purchase" name="itemrate.erpmGenMasterByIrCurrencyId.erpmgmEgmId" title="Enter Currency of Purchase"
                           headerKey="" headerValue="-- Please Select --" list="currencyList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                           ondblclick="getCurrencyAfterValidation('SaveItemRate_itemrate_erpmGenMasterByIrCurrencyId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                 <s:textfield cssClass="textInput" requiredposition="left" maxLength="11" size="25"
                              label="Unit Rate" name="itemrate.irdRate" required="yes" title="Enter Unit Rate" >
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:textfield cssClass="queryInput" requiredposition="left" maxLength="10" size="10" title="Enter Approval Effective From Date [MM-DD-YYYY]"
                              label="Approval Effective From Date" name="effDate" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:textfield cssClass="queryInput" requiredposition="left" maxLength="10" size="10" title="Enter last date of approval validity [MM-DD-YYYY]"
                              label="Approval Valid Upto Date" name="validUptoDate" >
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>

                 <s:textfield cssClass="textInput" required="true" requiredposition="left" maxLength="2" size="10" title="Enter warranty duration in months"
                              label="Warranty Duration(Months)" name="itemrate.irWarrantyMonths" value="12"  onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>


                 <s:select cssClass="textInput" label="Warranty Starts From" name="itemrate.erpmGenMasterByIrWarrantyStartsFromId.erpmgmEgmId"
                           headerKey="" headerValue="-- Please Select --" list="wsfList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                           ondblclick="getWarrantyaftervalidation('SaveIndentRate_itemrate_erpmGenMasterByIrWarrantyStartsFromId_erpmgmEgmId');"
                           title="Select warranty start point">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                 <s:textarea cssClass="textInput"  rows="2" cols="90" label="Warranty Description"
                             name="itemrate.irWarrantyClause" title="Enter warranty description">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{7}" />
                 </s:textarea>

                <tr><td> &nbsp; </td></tr>
                <s:label />

                <s:submit name="btnSubmit" value="Save Item Rate" cssClass="inputButton" >
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="btnFetch" value="Fetch Item Rate Details" action="FetchItemRates"  cssClass="inputButton">
                        <s:param name="colspan" value="%{2}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="bthReset" value="Clear Item Rates"  action="ClearItemRates" cssClass="inputButton" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <s:submit name="bthReset" value="Export Item Rates"  action="ExportItemRates" cssClass="inputButton" >
                        <s:param name="colspan" value="%{1}" />
                        <s:param name="align" value="right" />
                </s:submit>

                <tr><td> &nbsp; </td></tr>
                <s:label />

    </s:form>
    </div>
    <div id ="mainContent" align="center">
        <s:form name="frmItemRateBrowse">
            <table border="1" cellspacing="0" cellpadding="0" align="center" >
                <tr><td>
                    <display:table name="itemRateList" pagesize="15"
                        excludedParams="*" export="true" cellpadding="0"
                        cellspacing="0" id="doc"
                        requestURI="/pico/PrePurchase/ManageItemRates.action">
                    <display:column  class="griddata" title="SNo" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                    </display:column>
                    <display:column property="institutionmaster.imName" title="Institution"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false" />
                    <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    <display:column property="suppliermaster.smName" title="Supplier"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    <display:column property="irdWefDate" title="WEF Date"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    <display:column property="irdWetDate" title="WET Date"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    <display:column property="irdRate" title="Rate"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    <display:column property="erpmGenMasterByIrCurrencyId.erpmgmEgmDesc" title="Currency"
                                    headerClass="gridheader" class="griddata" sortable="false"/>
                    <display:column paramId="ItemRateId" paramProperty="irItemRateId"
                                    href="/pico/PrePurchase/EditItemRate.action"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                    </display:column>
                    <display:column paramId="ItemRateId" paramProperty="irItemRateId"
                                    href="/pico/PrePurchase/DeleteItemRate.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                    </display:column>
                   <display:column paramId="ItemRateId" paramProperty="irItemRateId"
                                    href="/pico/PrePurchase/ItemTax.action?itemrate.irItemRateId=${param['itemrate.irItemRateId']}"
                                    headerClass="gridheader" class="griddata" media="html" title="Add/Edit Taxes">
                                    Add/Edit Taxes
                    </display:column>



                </display:table>
                </td></tr>
                </table>
                <br>
        </s:form>
        </div>

    <div id="footer">
        <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
    </div>
    </div>
    </body>
</html>


