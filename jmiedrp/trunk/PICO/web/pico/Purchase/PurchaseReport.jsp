<%--
    Document   : ManageChallan
    Created on : 3 Jun, 2011, 11:32:07 AM
    Author     : Tanvir Ahmed and Sajid
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
                <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/ItemMasterScript.js"></script>

        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Tanvir Ahmed, Jamia Millia Islamia">
        <meta name="email" content="tanvirahmed74@gmail.com">
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
            <div id ="mainContent" >
               
                 <div style ="background-color: #215dc6;">
                      <p align="center" class="pageHeading" style="color: #ffffff">MANAGE PURCHASE REPORTS</p>
           
            <p align="center"><s:property value="message" /></p>
                 </div>
            <s:actionerror />
 <div style="border: solid 1px #000000; background: gainsboro">
            <s:form name="FrmPurchaseChallan" action="PurchaseReportAction" >
                   
                 


 <table border="0" cellpadding="4" cellspacing="0" align="center" >
                    <tbody>
                    <tr>
                    <td>
                        <s:select label="Institution" name="institutionId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" cssClass="textInput" value="DefaultInsitute"
                                onchange="getSubinstitutionAndgetSupplierList('PurchaseReportAction_institutionId','PurchaseReportAction_subinstitutionId','PurchaseReportAction_supplierId')" />
                       <s:select label="College/Faculty/School" name="subinstitutionId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
                                onchange="getDepartmentList('PurchaseReportAction_subinstitutionId','PurchaseReportAction_departmentId')"  cssClass="textInput"/>
                       <s:select label="Department" name="departmentId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"  cssClass="textInput" value="DefaultDepartment"/>

                

                <s:select cssClass="queryInput" label="Supplier Name" name="supplierId"  title="Select Supplier from the List"
                           headerKey="" headerValue="-- Please Select --" list="suppList" listKey="smId" listValue="smName"
                           ondblclick="getsupplieraftervalidation('PurchaseReportAction_supplierId_smId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>
                        <s:select cssClass="queryInput" label="Item Type" name="ItemTypeId"  title="Select Supplier from the List"
                           headerKey="" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                           onchange="getSubCategoryList('PurchaseReportAction_ItemTypeId', 'PurchaseReportAction_CategoryId')"
                           ondblclick="getSubCategoryList('PurchaseReportAction_ItemTypeId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>
                        <s:select cssClass="queryInput" label="item Category" name="CategoryId"  title="Select Supplier from the List"
                           headerKey="" headerValue="-- Please Select --" list="erpmIcmList2"  listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                           onchange="getSubCategoryList('PurchaseReportAction_CategoryId','PurchaseReportAction_SubCategoryId')"
                           ondblclick="getSubCategoryList('PurchaseReportAction_CategoryId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                        <s:select cssClass="queryInput" label="item SubCategory" name="SubCategoryId"  title="Select Supplier from the List"
                           headerKey="" headerValue="-- Please Select --" list="erpmIcmList3"  listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                           onchange="getItemListfromsubcategory('PurchaseReportAction_SubCategoryId','PurchaseReportAction_ItemNameId')" ondblclick="getItemListfromsubcategory('PurchaseReportsAction_SubCategoryId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>
                      <s:select cssClass="queryInput" label="Item Name" name="ItemNameId" headerKey="" headerValue="-- Please Select --"
                           list="itemlist" listKey="erpmimId" listValue="erpmimItemBriefDesc" title="Select Item from the List"
                           ondblclick="getitemList('PurchaseReportAction_ItemNameId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{2}" />
                 </s:select>




                      <s:textfield cssClass="textInput" required="true" requiredposition="left" maxLength="10" size="15" 
                              label="From Date(dd-mm-yyyy)" name="fromDate"  onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>
                        <s:textfield cssClass="textInput" required="true" requiredposition="left" maxLength="10" size="15" 
                              label="To Date(dd-mm-yyyy)" name="toDate"   onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>
                      

              <tr>
                <td align="right">
                     <s:submit theme="simple" name="btnSubmit" value="Pending Bills" action="PrintPendingBills"/>
                    </td>
                    <td>
                     <s:submit theme="simple" name="btnClear" value="Pending PO"   action = "ExportPendingPO"/>
 </td>
 <td>
                     <s:submit theme="simple" name="btnClear" value="Unchecked & Unverified Items"   action = "UncheckedAndUnverifiedItems"/>
 </td>

                <td>
                     <s:submit theme="simple" name="btnClear" value="Clear"   action="PurchaseReportAction"/>
 </td>
 <td>
                     <s:submit theme="simple" name="btnClear" value="Invoices Received"   action = "ListOfInvoicesReceived"/>
 </td>
                </tr>
                </tbody>
 </table>

            </s:form>
     <br>
     </div>
  &nbsp;
            </div>
                <div id="footer" >
                    <jsp:include page="../Administration/footer.jsp" flush="true" ></jsp:include>
                </div>
            </div>
       </body>
</html>
