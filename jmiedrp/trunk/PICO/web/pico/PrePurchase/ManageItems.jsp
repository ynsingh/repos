<%--  
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014
--%>


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

                <jsp:include page="../Administration//jobBar.jsp" flush="true"></jsp:include>
                
                <!-- *********************************End Menu****************************** -->

                <div id ="mainContent">

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('PrePurchase.ItemRecMgmt')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>


                <div style="border: solid 1px #000000; background:  gainsboro">
                    <s:form name="frmItemMaster" action="SaveItemMaster" theme="qxhtml">
                        <br>
                        <s:hidden name="erpmim.erpmimId" />

                        <s:label value="..." cssClass="tdSpace"/>
                        <s:select key = "PrePurchase.Institution" required="true" requiredposition="" name="erpmim.institutionmaster.imId"
                                  headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                  onchange="getCapitalCategoryList('SaveItemMaster_erpmim_institutionmaster_imId', 'SaveItemMaster_erpmim_erpmCapitalCategory_erpmccId')">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:select>

                        <s:label/>
                        <s:textarea required="true" requiredposition="left" rows="3" cols="115" maxLength="500"
                                    key = "PrePurchase.ItemBriefDesc" name="erpmim.erpmimItemBriefDesc" title="Enter Item Brief Description">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textarea>

                        <s:label/>
                        <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     key="PrePurchase.ItemMake" name="erpmim.erpmimMake" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:textfield required = "false" requiredposition="left" maxLength="20" size="20"
                                     key="PrePurchase.ItemModel" name="erpmim.erpmimModel" title="Enter Item Model">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:label/>
                        <s:select  requiredposition="left"  key = "PrePurchase.ItemIssuePolicy" name="erpmim.erpmimIssuePolicy" headerKey=""  headerValue="-- Please Select --" list="#{'1':'FIFO','2':'LIFO','3':'User Choice'}" >
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:select key="PrePurchase.AreSerialNoApplicable"   requiredposition="right"  name="erpmim.erpmimSerialNoApplicable" list="#{'Y':'Yes','N':'No'}" >
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:label/>
                        <s:select key="PrePurchase.ItemType" required="true" requiredposition="" name="erpmim.erpmItemCategoryMasterByErpmimItemCat1.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                  onchange="getSubCategoryList('SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId', 'SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId')" ondblclick="getSubCategoryList('SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat1_erpmicmItemId');">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:select key="PrePurchase.ItemCategory" required="true" requiredposition="" name="erpmim.erpmItemCategoryMasterByErpmimItemCat2.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList2" listKey="erpmicmItemId" listValue="erpmicmCatDesc"
                                  onchange="getSubCategoryList('SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId', 'SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId')" ondblclick="getSubCategoryList('SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat2_erpmicmItemId');">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:label/>
                        <s:select key="PrePurchase.ItemSubCategory" required="true" requiredposition="" name="erpmim.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId" headerKey="" headerValue="-- Please Select --" list="erpmIcmList3" listKey="erpmicmItemId" listValue="erpmicmCatDesc" 
                                  onchange="getDepreciationMethodandPercentageList('SaveItemMaster_erpmim_erpmItemCategoryMasterByErpmimItemCat3_erpmicmItemId','SaveItemMaster_erpmim_erpmimDepreciationMethod','SaveItemMaster_erpmim_erpmimDepreciationPercentage')">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:select key="PrePurchase.UnitOfPurchase" required="true" requiredposition="" name="erpmim.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="erpmGmIdList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:label/>
                        <s:select key = "PrePurchase.CapitalCategory" name="erpmim.erpmCapitalCategory.erpmccId" headerKey="" headerValue="-- None --" list="erpmCapitalCategoryList" listKey="erpmccId" listValue="ermccDesc">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>
                          <s:label/>
                        <s:select key = "PrePurchase.DepriciationMethod" name="erpmim.erpmimDepreciationMethod" requiredposition="right"  headerKey=""  headerValue="" list="#{'S':'Straight Line','W':'Written Down Value'}">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>


                        <s:label/>
                          <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     key="PrePurchase.DepriciationPercent" name="erpmim.erpmimDepreciationPercentage" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                          <s:textfield required="false" requiredposition="right" maxLength="20" size="20"
                                     key="PrePurchase.ResidualVal" name="erpmim.erpmimResidualValue" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                           <s:label/>
                          
                        <s:textarea required="true" requiredposition="left" rows="5" cols="115" maxLength="2000"
                                    key="PrePurchase.DetailDesc" name="erpmim.erpmimDetailedDesc" title="Enter Detailed Description">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textarea>

                        <s:label/>
                        <s:textfield required="false" requiredposition="left" maxLength="100" size="100"
                                     key="PrePurchase.Remarks" name="erpmim.erpmimRemarks" title="Enter Remarks, if any">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:label/>
                        
                        <s:submit name="btnSubmit" key="PrePurchase.Save">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
                        <s:submit name="btnClear" key="PrePurchase.Clear" action="ManageItems">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
                        <s:submit name="btnBrowse" key="PrePurchase.Browse"  action="BrowseItems">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
                         
                        <s:submit name="btnPrint" key="PrePurchase.Print"  action="PrintItems">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
                        <tr><td> 
                        <s:submit theme="simple" name="showGFRreport"  key="PrePurchase.ShowGFR" action="showGFRreportManageItems" disabled="varShowGFR" >
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
                        </td></tr>
                    </s:form>
                    <br>
                </div>
            </div>
            <br>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div> 
    </body>
</html>