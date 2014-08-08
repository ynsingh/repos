<%--
    Document   : ErpmTOS
    Created on : Apr 13, 2012, 11:55:54 AM
    Author     : farah, Tanvir Ahmed
    I18n By    : Mohd. Manauwar Alam
               : March 2014
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
            <br><br>
             <div style ="background-color: #215dc6;">
                 <p align="center" class="pageHeading" style="color:  #ffffff"><s:property value="getText('Inventory.InventoryReports')" />"  </p>
                    
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
         
          
                 <div style="border: solid 1px #000000; background: gainsboro">
                <s:form name="frmErpmTOS" action="ErpmTOSAction" target = "_blank">
                   <s:hidden name="esr.stId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td valign="middle" class="FormContent">
                                </td>
                            </tr>
                            <tr>
                                <td>

                                    <s:select required="true" requiredposition="" key="Inventory.Institution" name="esr.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="tosImIdList" listKey="imId" listValue="imName"
                                               onchange="getSubinstitutionList('ErpmTOSAction_esr_institutionmaster_imId', 'ErpmTOSAction_esr_subinstitutionmaster_simId');" cssClass="textInput" />

                                    <s:select required="true"  requiredposition="" key="Inventory.SubInstitution" name="esr.subinstitutionmaster.simId" headerKey="" headerValue="-- Please select --" list="tosSimImIdList" listKey="simId" listValue="simName"
                                    onchange="getDepartmentList('ErpmTOSAction_esr_subinstitutionmaster_simId','ErpmTOSAction_esr_departmentmaster_dmId')" cssClass="textInput"/>

                                    <s:select required="true"  requiredposition="" key="Inventory.Department" name="esr.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="tosDmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>

                                    <s:select required="true" requiredposition="" key="Inventory.SupplierName" name="esr.suppliermaster.smId" headerKey="" headerValue="-- Please Select --" list="tosSmList" listKey="smId" listValue="smName" cssClass="textInput"/>

                                    <s:select required="true" requiredposition="" key="Inventory.ItemType" name="esr.ErpmItemMaster.erpmItemCategoryMasterByErpmimItemCat1.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                              onchange="getSubCategoryList('ErpmTOSAction_esr_ErpmItemMaster_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId', 'ErpmTOSAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId')"
                                              ondblclick="getSubCategoryList('ErpmTOSAction_esr_ErpmItemMaster_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId');" cssClass="textInput"/>

                                    <s:select required="true"  requiredposition="" key="Inventory.ItemCategory" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat2.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList2" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                              onchange="getSubCategoryList('ErpmTOSAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId', 'ErpmTOSAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId')"
                                              ondblclick="getSubCategoryList('ErpmTOSAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId');"/>

                                    <s:select required="true" requiredposition="" key="Inventory.ItemSubCategory" name="erpmItemCategoryMaster.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList3" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                              onchange="getItemListfromsubcategory('ErpmTOSAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId', 'ErpmTOSAction_esr_erpmItemMaster_erpmimId')"
                                              ondblclick="getItemListfromsubcategory('ErpmTOSAction_erpmItemCategoryMaster_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId');" cssClass="textInput"/>

                                    <s:select required="true"  requiredposition="" key="Inventory.ItemName" name="esr.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="tosINList" listKey="erpmimId" listValue="erpmimItemBriefDesc" cssClass="textInput"/>

                      <s:textfield cssClass="textInput" required="true" requiredposition="left" maxLength="10" size="15"
                              key="Inventory.FromDate" name="fromDate"  onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>
                        <s:textfield cssClass="textInput" required="true" requiredposition="left" maxLength="10" size="15"
                              key="Inventory.ToDate" name="toDate"   onclick="checkdate">
                              <s:param name="labelcolspan" value="%{1}" />
                              <s:param name="inputcolspan" value="%{3}" />
                 </s:textfield>
                      

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:submit theme="simple" name="bthReset" key="Inventory.StockReceivedSummary" action="PrintStockSummary"  cssClass="textInput" />
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" key="Inventory.StockReceivedItemDetails" action="Printstockdetail"  cssClass="textInput"/>
                                 </td>
                                 <td>
                                 <s:submit theme="simple" name="bthReset" key="Inventory.ItemsPendingToBeReceived" action="IssuesPendingReport"  cssClass="textInput" />
                                </td>
                                  <td>
                                    <s:submit theme="simple" name="bthReset" key="Inventory.StockInHand" action="PrintStockInHand"  cssClass="textInput"/>
                                </td>
                    <td> <br><br> </td>                                
                                
                                  <td>

                                    <s:submit theme="simple" name="bthReset" key="Inventory.GFRReport40" action="ManageGFRReport40"  cssClass="textInput"/>
                                </td>
                                  <td>

                                    <s:submit theme="simple" name="bthReset" key="Inventory.GFRReport41" action="ManageGFRReport41"  cssClass="textInput"/>
                                </td>
                                  <td>
                                    
                			<s:submit theme="simple" name="bthReset" key="Inventory.StockRegisterReport" action="StockRegister"  cssClass="textInput"/>

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
            </div>
              <div id="footer">
            <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
        </div>
        </div>
    </body>
</html>