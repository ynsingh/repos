<%--
    Document   : DeprecitionAction
    Created on : Apr 13, 2012, 11:55:54 AM
    Author     : Faraz & Ehtesham

   I18n By    : Mohd. Manauwar Alam
               : Jan 2014

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
                <jsp:include page="jobBar.jsp" flush="true"></jsp:include>
                
                <!-- *********************************End Menu****************************** -->
                <div id ="mainContent">
                <br><br>
                <div style ="background-color: #215dc6;">
<%--                    <p align="center" class="pageHeading" style="color: #ffffff">ITEM CATEGORY MANAGEMENT</p> --%>
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.ItemCatMgmt')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <s:form name="frmdepreciation">
                        <s:hidden name="erpmItemCategoryMaster.erpmicmItemId" />

                        <table border="0" cellpadding="4" cellspacing="0" align="center">
                            <tbody>
                                <tr>
                                    <td valign="middle" class="FormContent">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
<%--				<s:select requiredposition="left" required="true" label="Institution" name="erpmItemCategoryMaster.institutionmaster.imId" headerKey="0" headerValue="-- Please Select --" list="tosImIdList" listKey="imId" listValue="imName"
					  onchange="getSubinstitutionList('ErpmTOSAction_esr_institutionmaster_imId', 'ErpmTOSAction_esr_subinstitutionmaster_simId');" cssClass="textInput" />

                                        <s:textfield requiredposition="left" required="true" maxLength="100" size="50"
                                                     label="Category Name" name="erpmItemCategoryMaster.erpmicmCatDesc" headerKey="0" title="Enter Capital Item Category Name"  cssClass="textInput"/>

                                        <s:select id="15" required="true" requiredposition="left" label="Category Level" name="erpmItemCategoryMaster.erpmicmItemLevel" headerKey="0" headerValue="-- Please Select --" list="{'1','2','3'}"  cssClass="queryInput" 
                                                  onchange="getParentCategoryList('15', '16')">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:select>

                                        <s:select id="16" requiredposition="left" required="true" label="Parent Category" name="erpmItemCategoryMaster.erpmItemCategoryMaster.erpmicmItemId" headerKey="0" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"/>

                                        <s:select requiredposition="left" required="true" label="Depreciation Method" name="erpmItemCategoryMaster.erpmicmDepreciationMethod" headerKey="0" headerValue="-- Please Select --" list="#{'S':'Straight Line','W':'Written Down Value'}"  cssClass="queryInput" value="depvalue" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:select>

                                        <s:textfield required="true" requiredposition="left" maxLength="5" size="7"
                                                     label="Depreciation Percentage(0-100)" name="erpmItemCategoryMaster.erpmicmDepreciationPercentage" headerKey="-1" title="Enter Depre %"  cssClass="textInput"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td> 
                                        <s:submit  theme="simple" value="Save Data" action="SaveItemCategoryAction" cssClass="textInput" >
                                            <s:param name="colspan" value="%{3}" />
                                            <s:param name="align" value="%{'center'}" />
                                        </s:submit></td>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" value="Browse Data" action="BrowseItemCategory" cssClass="textInput">
                                            <s:param name="colspan" value="%{2}" />
                                            <s:param name="align" value="%{'center'}" />
                                        </s:submit> </td>
                                    <td>  <s:submit theme="simple" name="btnSubmit" value="Clear" action="ClearItemCategory" cssClass="textInput"> --%>
                                        <s:select requiredposition="left" required="true" key="Administration.InstitutionName" name="erpmItemCategoryMaster.institutionmaster.imId" headerKey="0" headerValue="-- Please Select --" list="tosImIdList" listKey="imId" listValue="imName"
                                                  onchange="getSubinstitutionList('ErpmTOSAction_esr_institutionmaster_imId', 'ErpmTOSAction_esr_subinstitutionmaster_simId');" cssClass="textInput" />

                                        <s:textfield requiredposition="left" required="true" maxLength="100" size="50"
                                                     key="Administration.CategoryName" name="erpmItemCategoryMaster.erpmicmCatDesc" headerKey="0" title="Enter Capital Item Category Name"  cssClass="textInput"/>

                                        <s:select id="15" required="true" requiredposition="left" key="Administration.CategoryLevel" name="erpmItemCategoryMaster.erpmicmItemLevel" headerKey="0" headerValue="-- Please Select --" list="{'1','2','3'}"  cssClass="textInput" 
                                                  onchange="getParentCategoryList('15', '16')">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:select>

                                        <s:select id="16" requiredposition="left" required="true" key="Administration.ParentCategory" name="erpmItemCategoryMaster.erpmItemCategoryMaster.erpmicmItemId" headerKey="0" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc"/>

                                        <s:select requiredposition="left" required="true" key="Administration.DepriciationMethod" name="erpmItemCategoryMaster.erpmicmDepreciationMethod" headerKey="0" headerValue="-- Please Select --" list="#{'S':'Straight Line','W':'Written Down Value'}"  cssClass="textInput" value="depvalue" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:select>

                                        <s:textfield required="true" requiredposition="left" maxLength="5" size="7"
                                                     key="Administration.DepriciationPercnt" name="erpmItemCategoryMaster.erpmicmDepreciationPercentage" headerKey="-1" title="Enter Depre %"  cssClass="textInput"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <s:submit  theme="simple" key="Administration.Save" action="SaveItemCategoryAction" cssClass="textInput" >
                                            <s:param name="colspan" value="%{3}" />
                                            <s:param name="align" value="%{'center'}" />
                                        </s:submit></td>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" key="Administration.Browse" action="BrowseItemCategory" cssClass="textInput">
                                            <s:param name="colspan" value="%{2}" />
                                            <s:param name="align" value="%{'center'}" />
                                        </s:submit> </td>
                                    <td>  <s:submit theme="simple" name="btnSubmit" key="Administration.Clear" action="ClearItemCategory" cssClass="textInput">
                                            <s:param name="colspan" value="%{2}" />
                                            <s:param name="align" value="%{'center'}" />
                                        </s:submit> </td>
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
                <br>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
