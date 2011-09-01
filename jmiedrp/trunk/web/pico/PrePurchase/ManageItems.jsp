<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html  >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/ItemMasterScript.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
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
                <jsp:include page="../Administration/header.jsp"  flush="true"></jsp:include>
            </div>
            
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            
            <!-- *********************************End Menu****************************** -->
            
            <div id ="mainContent">
                <p align="center"><s:label value="ITEM MASTER" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>

                <s:form name="frmItemMaster" action="SaveItemMaster" theme="xhtml">
                    <s:hidden name="erpmim.erpmimId" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td><br><s:submit theme="simple" name="bthReset" value="Browse Items"  action="BrowseItems"/>
                                    <s:select label="Institution" name="erpmim.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                              onchange="getCapitalCategoryList('SaveItemMaster_erpmim_institutionmaster_imId', 'SaveItemMaster_erpmim_erpmCapitalCategory_erpmccId')"/>
                                    <s:textarea required="true" requiredposition="left" rows="3" cols="100" maxLength="500"
                                                 label="Item Brief Description" name="erpmim.erpmimItemBriefDesc" title="Enter Item Brief Description" />
                                    <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                                 label="Item Make" name="erpmim.erpmimMake" title="Enter Item Make" />
                                    <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                                 label="Item Model" name="erpmim.erpmimModel" title="Enter Item Model" />
                                    <s:select label="Item Type" name="erpmim.erpmItemCategoryMasterByErpmimItemCat1.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                              onchange="getSubCategoryList('SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId', 'SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId')"/>
                                    <s:select label="Item Category" name="erpmim.erpmItemCategoryMasterByErpmimItemCat2.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList2" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                              onchange="getSubCategoryList('SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId', 'SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId')"/>
                                    <s:select label="Item Sub Category" name="erpmim.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList3" listKey="erpmicmItemId" listValue="erpmicmCatDesc"  />
                                    <s:select label="Unit of Purchase" name="erpmim.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="erpmGmIdList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"/>
                                    <s:select label="Capital Category" name="erpmim.erpmCapitalCategory.erpmccId" headerKey="" headerValue="-- None --" list="erpmCapitalCategoryList" listKey="erpmccId" listValue="ermccDesc"/>
                                    <s:textarea required="true" requiredposition="left" rows="5" cols="100" maxLength="2000"
                                                 label="Detailed Description" name="erpmim.erpmimDetailedDesc" title="Enter Detailed Description" />
                                    <s:textfield required="false" requiredposition="left" maxLength="100" size="50"
                                                 label="Remarks" name="erpmim.erpmimRemarks" title="Enter Remarks, if any" />                                    
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Item"  />
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear" action="ManageItems"/>
                                    <s:submit theme="simple" name="bthReset" value="Browse Items"  action="BrowseItems"/>
                                </td>
                            </tr>
                            <tr>
                    <td> <br><br> </td>
                </tr>

                        </tbody>
                    </table>
                </s:form>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div> 
    </body>
</html>