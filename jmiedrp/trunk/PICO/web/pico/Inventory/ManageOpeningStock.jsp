<%-- 
    Document   : ErpmTOS
    Created on : Apr 13, 2012, 11:55:54 AM
    Author     : farah
    Updated By : Tanvir Ahmed
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/ItemMasterScript.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <s:head />
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
            <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">OPENING STOCK</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">


                    <s:form name="frmManageOpeningStock" action="ManageOpeningStockAction" theme="qxhtml">
                        <s:hidden name="tos.tosId" />
                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                     label="Batch Id" name="tos.tosBatchId" disabled="true" cssClass="textInputRO">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{7}" />
                        </s:textfield>

                        <s:select required="true"  requiredposition="" label="Institution" name="tos.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="tosImIdList" listKey="imId" listValue="imName"
                                  onchange="getSubinstitutionList('ManageOpeningStockAction_tos_institutionmaster_imId', 'ManageOpeningStockAction_tos_subinstitutionmaster_simId');" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select required="true"  requiredposition="" label="SubInstitution" name="tos.subinstitutionmaster.simId" headerKey="" headerValue="-- Please select --" list="tosSimImIdList" listKey="simId" listValue="simName"
                                  onchange="getDepartmentList('ManageOpeningStockAction_tos_subinstitutionmaster_simId','ManageOpeningStockAction_tos_departmentmaster_dmId')" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select required="true"  requiredposition="" label="Department" name="tos.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="tosDmList" listKey="dmId" listValue="dmName" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select required="true"  requiredposition="" label="Supplier" name="tos.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="tosSmList" listKey="smId" listValue="smName" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select  label="Item Type" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat1.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                  onchange="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId', 'ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId')"
                                  ondblclick="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId');" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select  label="Item Category" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat2.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList2" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                  onchange="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId', 'ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId')"
                                  ondblclick="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select  label="Item Sub Category" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList3" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                  onchange="getItemListTOS('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId', 'ManageOpeningStockAction_tos_erpmItemMaster_erpmimId')"
                                  ondblclick="getItemListTOS('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId');" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select required="true"  requiredposition="" label="Item Name" name="tos.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="tosINList" listKey="erpmimId" listValue="erpmimDetailedDesc" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:textfield  requiredposition="centre" maxLength="40" size="40"
                                      label="Quantity" name="quantity" title="Enter the quantity "  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="centre" maxLength="40" size="40" label="In Stock since [DD-MM-YYYY]" name="tosInStockSince" title="Enter the date "  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      label="PurchaseOrder No" name="tos.tosPoNo" title="Enter purchase order no."  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="centre" maxLength="40" size="40"
                                      label="Purchase Order Date [DD-MM-YYYY]" name="tosPoDate" title="Enter Purchase order date"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      label="Challan No." name="tos.tosChallanNo" title="Enter Challan Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield requiredposition="centre" maxLength="40" size="40"
                                     label="Challan Date [DD-MM-YYYY]" name="tosChallanDate" title="Enter Challan Date"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      label="Invoice No" name="tos.tosInvoiceNo" title="Enter invoice no"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="centre" maxLength="40" size="40"
                                      label="Invoice Date [DD-MM-YYYY]" name="tosInvoiceDate" title="Enter Invoice date"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     label="Unit rate" name="tos.tosUnitRate" title="Enter Unit rate"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      label="Tax Value" name="tos.tosTaxValue" title="Enter tax value"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     label="Central Stock Register No." name="tos.tosCsrNo" title="Enter Central Stock Register Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     label="Central Stock Register Page No." name="tos.tosCsrPgNo" title="Enter Central Stock Register Page  Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     label="Department Stock Register No." name="tos.tosDeptSrNo" title="Enter Department Stock Register Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     label="Department Stock Register Page No" name="tos.tosDeptSrPgNo" title="Enter Department Stock Register Page Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      label="Product No." name="tos.tosProductNo" title="Enter Product No."  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      label="Stock Serial No." name="tos.tosStockSerialNo" title="Enter Stock Serial No."  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40" label="Warranty Expiry Date [DD-MM-YYYY]" name="tosWarrantyExpiryDate" title="Enter Warranty Expiry date"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select  label="Warranty Type" required="true" name="tos.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="tosWTList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:textarea  requiredposition="left" rows="5" cols="100" maxLength="500"
                                     label="Remarks" name="remark" title="Enter remark"  >
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{7}" />
                        </s:textarea>

                        <tr><td> &nbsp; </td></tr>
                        <tr><td align="left">
                        <s:submit theme="simple" name="btnSubmit" value="Save" action="SaveTOSAction"  cssClass="textInput">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        </td><td align="left">
                        <s:submit theme="simple" name="bthReset" value="Clear" action="ClearTOSAction"  cssClass="textInput">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit theme="simple" name="btnSubmit" value="Browse" action="BrowseTOS"  cssClass="textInput">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
<%--                        
                        <s:submit theme="simple" name="btnSubmit" value="Print Opening Stock" action="PrintOpeningStock"  cssClass="textInput">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
--%>
                        <s:submit theme="simple" name="showGFRreport"  value="Show GFR" action="showGFRreportStock" disabled="varShowGFR" />
                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>
            <div id="footer" >
                <jsp:include page="footer.jsp" flush="true" ></jsp:include>
            </div>
        </div>
    </body>
</html>
