<%-- 
    Document   : ErpmTOS
    Created on : Apr 13, 2012, 11:55:54 AM
    Author     : farah
    Updated By : Tanvir Ahmed
    I18n By    : Mohd. Manauwar Alam
               : March 2014

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
        <script language="JavaScript">
        function popupWin(url,popupName)
        {
                Win=window.open(url,popupName,"resizable=0,scrollbars=1,height=400,width=600");
        }
        </script>
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
		<div align="right" style="margin-right: 10px">
		<a href=javascript:popupWin("/pico/Inventory/HelpForOpeningStock.action","HelpForOpeningStock"); style=text-decoration:none><font size=3>HELP</font></a>
                </div>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Inventory.OpeningStock')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">


                    <s:form name="frmManageOpeningStock" action="ManageOpeningStockAction" theme="qxhtml">
                        <s:hidden name="tos.tosId" />
                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                     key="Inventory.BatchId" name="tos.tosBatchId" disabled="true" cssClass="textInputRO">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{7}" />
                        </s:textfield>

                        <s:select required="true"  requiredposition="" key="Inventory.Institution" name="tos.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="tosImIdList" listKey="imId" listValue="imName"
                                  onchange="getSubinstitutionList('ManageOpeningStockAction_tos_institutionmaster_imId', 'ManageOpeningStockAction_tos_subinstitutionmaster_simId');" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select required="true"  requiredposition="" key="Inventory.SubInstitution" name="tos.subinstitutionmaster.simId" headerKey="" headerValue="-- Please select --" list="tosSimImIdList" listKey="simId" listValue="simName"
                                  onchange="getDepartmentList('ManageOpeningStockAction_tos_subinstitutionmaster_simId','ManageOpeningStockAction_tos_departmentmaster_dmId')" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select required="true"  requiredposition="" key="Inventory.Department" name="tos.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="tosDmList" listKey="dmId" listValue="dmName" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select required="true"  requiredposition="" key="Inventory.SupplierName" name="tos.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="tosSmList" listKey="smId" listValue="smName" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select  key="Inventory.ItemType" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat1.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                  onchange="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId', 'ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId')"
                                  ondblclick="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId');" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select  key="Inventory.ItemCategory" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat2.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList2" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                  onchange="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId', 'ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId')"
                                  ondblclick="getSubCategoryList('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:select  key="Inventory.ItemSubCategory" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList3" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                  onchange="getItemListTOS('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId', 'ManageOpeningStockAction_tos_erpmItemMaster_erpmimId')"
                                  ondblclick="getItemListTOS('ManageOpeningStockAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId');" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select required="true"  requiredposition="" key="Inventory.ItemName" name="tos.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="tosINList" listKey="erpmimId" listValue="erpmimDetailedDesc" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:textfield  requiredposition="centre" maxLength="40" size="40"
                                      key="Inventory.Quantity" name="quantity" title="Enter the quantity "  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="centre" maxLength="40" size="40" key="Inventory.InStockSince" name="tosInStockSince" title="Enter the date "  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      key="Inventory.PurchaseOrderNo" name="tos.tosPoNo" title="Enter purchase order no."  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="centre" maxLength="40" size="40"
                                      key="Inventory.PurchaseOrderDate" name="tosPoDate" title="Enter Purchase order date"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      key="Inventory.ChallanNo" name="tos.tosChallanNo" title="Enter Challan Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield requiredposition="centre" maxLength="40" size="40"
                                     key="Inventory.ChallanDate" name="tosChallanDate" title="Enter Challan Date"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      key="Inventory.InvoiceNo" name="tos.tosInvoiceNo" title="Enter invoice no"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="centre" maxLength="40" size="40"
                                      key="Inventory.InvoiceDate" name="tosInvoiceDate" title="Enter Invoice date"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     key="Inventory.UnitRate" name="tos.tosUnitRate" title="Enter Unit rate"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      key="Inventory.TaxValue" name="tos.tosTaxValue" title="Enter tax value"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     key="Inventory.CentralStockRegisterNo" name="tos.tosCsrNo" title="Enter Central Stock Register Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     key="Inventory.CentralStockRegisterPageNo" name="tos.tosCsrPgNo" title="Enter Central Stock Register Page  Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     key = "Inventory.DepartmentStockRegisterNo" name="tos.tosDeptSrNo" title="Enter Department Stock Register Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield required="true" requiredposition="left" maxLength="40" size="40"
                                     key="Inventory.DepartmentStockRegisterPageNo" name="tos.tosDeptSrPgNo" title="Enter Department Stock Register Page Number"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      key="Inventory.ProductNo" name="tos.tosProductNo" title="Enter Product No."  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:textfield  requiredposition="left" maxLength="40" size="40"
                                      key="Inventory.StockSerialNo" name="tos.tosStockSerialNo" title="Enter Stock Serial No."  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield  requiredposition="left" maxLength="40" size="40" key="Inventory.WarrantyExpiryDate" name="tosWarrantyExpiryDate" title="Enter Warranty Expiry date"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace"/>

                        <s:select  key="Inventory.WarrantyType" required="true" name="tos.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="tosWTList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:select>

                        <s:textarea  requiredposition="left" rows="5" cols="100" maxLength="500"
                                     key="Inventory.Remarks" name="remark" title="Enter remark"  >
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{7}" />
                        </s:textarea>

                        <tr><td> &nbsp;
                        <tr><td align="left">
                        <s:submit theme="simple" name="btnSubmit" key="Inventory.Save" action="SaveTOSAction"  cssClass="textInput">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <td align="left">
                        <s:submit theme="simple" name="bthReset" key="Inventory.Clear" action="ClearTOSAction"  cssClass="textInput">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit theme="simple" name="btnSubmit" key="Inventory.Browse" action="BrowseTOS"  cssClass="textInput">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
<%--                        
                        <s:submit theme="simple" name="btnSubmit" value="Print Opening Stock" action="PrintOpeningStock"  cssClass="textInput">
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
--%>
                        <s:submit theme="simple" name="showGFRreport"  key="Inventory.ShowGFR" action="showGFRreportStock" disabled="varShowGFR" />
                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>
            <div id="footer" >
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
