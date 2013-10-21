<%--
    Document   : PendingPO
    Created on : 25 Oct, 2012, 4:33:37 PM
    Author     : manauwar
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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/ItemMasterScript.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Tanvir Ahmed, Jamia Millia Islamia">
        <meta name="email" content="manauwar.mca@gmail.com">
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
                 <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
            <p align="center"><s:label cssClass="pageHeading" value="PENDING PO REPORTS" style="color: #ffffff"  /></p>
            <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
             </div>
                 <%--------------------this is a Purchase Challan Form --------------------%>

            <s:actionerror />
<div style="border: solid 1px #000000; background: gainsboro">
            <s:form name="FrmPendingPO" action="ExportPendingPO" theme="qxhtml">
   <%--                 <p align="left" class="pageMessage"><s:property value="message" /></p>


use where only number is required for input other than number it does not accept value
                          <script type='text/javascript'>
              function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
   </script>
    --%>
 <table border="0" cellpadding="4" cellspacing="0" align="center" >
                    <tbody>
                    <tr>
                    <td>
                        <s:select name="institutionId" label="Institution"  list="imList" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                  onchange="getSubinstitutionAndgetSupplierList('ExportPendingPO_institutionId','ExportPendingPO_subInstitutionId','ExportPendingPO_suppliermasterId')">
                             <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>
                     <s:select label="College/Faculty/School" name="subInstitutionId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName"
                                onchange="getDepartmentList('ExportPendingPO_subInstitutionId','ExportPendingPO_departmentId')" >
                          <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>
                       <s:select label="Department" name="departmentId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName">
                            <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>



                  <s:select  label = "Supplier Name" name="supplierId"  title="Select Supplier from the List"
                           headerKey="" headerValue="-- Please Select --" list="supList" listKey = "smId" listValue="smName"
                           ondblclick="getsupplieraftervalidation('ExportPendingPO_supplierId');">
                       <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                        <s:select label="Item Type" name="ItemTypeId"  title="Select item Type from the list"
                           headerKey="" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                           onchange="getSubCategoryList('ExportPendingPO_ItemTypeId', 'ExportPendingPO_CategoryId')"
                           ondblclick="getSubCategoryList('PendingPOInput_ItemTypeId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>
                        <s:select label="item Category" name="CategoryId"  title="Select item category from the list"
                           headerKey="" headerValue="-- Please Select --" list="erpmIcmList2"  listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                           onchange="getSubCategoryList('ExportPendingPO_CategoryId','ExportPendingPO_SubCategoryId')"
                           ondblclick="getSubCategoryList('ExportPendingPO_CategoryId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>

                        <s:select  label="item SubCategory" name="SubCategoryId"  title="Select item subcategory from the List"
                           headerKey="" headerValue="-- Please Select --" list="erpmIcmList3"  listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                           onchange="getItemListTOS('ExportPendingPO_SubCategoryId', 'ExportPendingPO_ItemNameId')"
                                      ondblclick="getItemListTOS('ExportPendingPO_SubCategoryId', 'ExportPendingPO_ItemNameId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>
                      <s:select label="Item Name" name="ItemNameId" headerKey="" headerValue="-- Please Select --"
                           list="itemList" listKey="erpmimId" listValue="erpmimItemBriefDesc" title="Select Item from the List"
                           ondblclick="getitemList('ExportPendingPO_ItemNameId');">
                           <s:param name="labelcolspan" value="%{1}" />
                           <s:param name="inputcolspan" value="%{3}" />
                 </s:select>




                      <s:textfield  required="true" requiredposition="left" maxLength="2" size="15" title="Enter warranty duration in months"
                              label="From Date(dd-mm-yyyy)" name="fromDate"   onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>
                        <s:textfield  required="true" requiredposition="left" maxLength="2" size="15" title="Enter warranty duration in months"
                              label="To Date(dd-mm-yyyy)" name="toDate"   onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>


              <tr>
                <td align="right">
                    <s:submit  name="btnSubmit" value="Pending PO" action="ExportPendingPO"/>
                    </td>

                </tr>
                </tbody>
 </table>

            </s:form>
                    </div>
            </div>
<br>
                <div id="footer" >
                    <jsp:include page="../Administration/footer.jsp" flush="true" ></jsp:include>
                </div>
            </div>
       </body>
</html>
