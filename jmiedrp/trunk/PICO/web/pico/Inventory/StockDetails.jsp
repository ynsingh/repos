<%--
    Document   : StockDetails
    Created on : Apr 13, 2012, 11:55:54 AM
    Author     : farah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
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
                <br><br>
                <p align="center"><s:label value="STOCK DETAILS"  cssClass="pageHeading"/>
            <p align="center"><s:property value="message" /></p>

            <div id ="mainContent">
                <s:form name="frmErpmTOS" action="ManageOpeningStockAction">
                    <s:hidden name="tos.tosId" />


                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td valign="middle" class="FormContent">
                                </td>
                            </tr>
                            <tr>
                                <td>

                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Batch Id" name="tos.tosBatchId" disabled="true" cssClass="textInputRO"/>

                                    <s:select required="true" label="Institution" name="tos.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="tosImIdList" listKey="imId" listValue="imName"
                                              onchange="getSubinstitutionList('ManageOpeningStockAction_tos_institutionmaster_imId', 'ManageOpeningStockAction_tos_subinstitutionmaster_simId');" cssClass="textInput" />
                                    <s:select required="true" label="SubInstitution" name="tos.subinstitutionmaster.simId" headerKey="" headerValue="-- Please select --" list="tosSimImIdList" listKey="simId" listValue="simName"
                                              onchange="getDepartmentList('ManageOpeningStockAction_tos_subinstitutionmaster_simId','ManageOpeningStockAction_tos_departmentmaster_dmId')" cssClass="textInput"/>
                                    <s:select required="true" label="Department" name="tos.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="tosDmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>
                                    <s:select required="true" label="Supplier" name="tos.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="tosSmList" listKey="smId" listValue="smName" cssClass="textInput"/>
                                    <s:select required="true" label="Item Type" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat1.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                              onchange="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId', 'ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId')"
                                              ondblclick="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId');"cssClass="textInput"/>
                                    <s:select required="true" label="Item Category" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat2.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList2" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                              onchange="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId', 'ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId')"
                                              ondblclick="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId');"/>
                                    <s:select required="true" label="Item Sub Category" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList3" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                              onchange="getItemListTOS('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId', 'ManageOpeningStockAction_tos_erpmItemMaster_erpmimId')"
                                              ondblclick="getItemListTOS('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId');"cssClass="textInput"/>
                                    <s:select required="true" label="Item Name" name="tos.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="tosINList" listKey="erpmimId" listValue="erpmimDetailedDesc" cssClass="textInput"/>
                                    <s:textfield  requiredposition="centre" maxLength="50" size="50"
                                                  label="In Stock since" name="tos.tosInStockSince" title="Enter the date "  cssClass="textInput"/>
                                    <s:textfield  requiredposition="left" maxLength="50" size="50"
                                                  label="PurchaseOrder No" name="tos.tosPoNo" title="Enter purchase order no."  cssClass="textInput"/>
                                    <s:textfield  requiredposition="centre" maxLength="50" size="50"
                                                  label="PurchaseOrder Date " name="tos.tosPoDate" title="Enter purchase order date"  cssClass="textInput"/>
                                    <s:textfield  requiredposition="left" maxLength="50" size="50"
                                                  label="Challan No." name="tos.tosChallanNo" title="Enter Challan Number"  cssClass="textInput"/>
                                    <s:textfield requiredposition="centre" maxLength="50" size="50"
                                                 label="Challan Date" name="tos.tosChallanDate" title="Enter Challan Date"  cssClass="textInput"/>

                                    <s:textfield  requiredposition="left" maxLength="50" size="50"
                                                  label="Invoice No" name="tos.tosInvoiceNo" title="Enter invoice no"  cssClass="textInput"/>
                                    <s:textfield  requiredposition="centre" maxLength="50" size="50"
                                                  label="Invoice Date" name="tos.tosInvoiceDate" title="Enter Invoice date"  cssClass="textInput"/>

                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Unit rate" name="tos.tosUnitRate" title="Enter Unit rate"  cssClass="textInput"/>
                                    <s:textfield  requiredposition="left" maxLength="50" size="50"
                                                  label="Tax Value" name="tos.tosTaxValue" title="Enter tax value"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Central Stock Register No." name="tos.tosCsrNo" title="Enter Central Stock Register Number"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Central Stock Register Page No." name="tos.tosCsrPgNo" title="Enter Central Stock Register Page  Number"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Department Stock Register No." name="tos.tosDeptSrNo" title="Enter Department Stock Register Number"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Department Stock Register Page No" name="tos.tosDeptSrPgNo" title="Enter Department Stock Register Page Number"  cssClass="textInput"/>
                                    <s:textfield  requiredposition="left" maxLength="50" size="50"
                                                  label="Product No." name="tos.tosProductNo" title="Enter Product No."  cssClass="textInput"/>
                                    <s:textfield  requiredposition="left" maxLength="50" size="50"
                                                  label="Stock Serial No." name="tos.tosStockSerialNo" title="Enter Stock Serial No."  cssClass="textInput"/>
                                    <s:textfield  requiredposition="left" maxLength="50" size="50"
                                                  label="Warranty Expiry Date" name="tos.tosWarrantyExpiryDate" title="Enter Warranty Expiry date"  cssClass="textInput"/>
                                    <s:select  label="Warranty Type" name="tos.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="tosWTList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>
                                    <s:textarea  requiredposition="left" rows="5" cols="100" maxLength="500"
                                                 label="Remarks" name="remark" title="Enter remark"  />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save" action="SaveTOSAction"  cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear" action="ClearTransferStockDetails"  cssClass="textInput" />

                                    <%-- <s:submit theme="simple" name="showGFRreport"  value="Show GFR" action="showGFRreport" disabled="varShowGFR"/>
                                    --%>
                                </td>
                            </tr>
                            <tr>
                                <td> <br><br> </td>
                            </tr>
                            <tr>

                            </tr>

                        </tbody>
                    </table>
                </s:form>
            </div>
            <div id="footer" >
                <jsp:include page="footer.jsp" flush="true" ></jsp:include>
            </div>
        </div>
    </body>
</html>