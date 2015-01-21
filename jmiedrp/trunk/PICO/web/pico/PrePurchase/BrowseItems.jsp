<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Budgettypemaster.js"></script>
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>

            <jsp:include page="../Administration//jobBar.jsp" flush="true"></jsp:include>            
            
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">ITEM LIST</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>                                
                                    <%--   cellspacing="0" id="doc" decorator="PrePurchase.PrePurchaseDecorator"  --%>
                
               <table style="height:20em;" width="100%"><tr><td valign="top">
                <s:form name="frmItemsBrowse">
                        <display:table name="erpmimList" pagesize="15"
                                       excludedParams="*" export="true" cellpadding="0"
                                       cellspacing="0" id="doc" 
                                       requestURI="/PrePurchase/BrowseItems.action">
                                
                                <display:column  class="griddata" title="S.No" sortable="true" maxLength="100" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                </display:column>
                                <display:column property="institutionmaster.imShortName" title="Institution"
                                                maxLength="10" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="true"/>
                                <display:column property="erpmimItemBriefDesc" title="Brief Description"
                                                maxLength="35" headerClass="gridheader"
                                                class="griddata" style="width:20%" sortable="true"/>
                                
                                <display:column property="erpmimMake" title="Item Make"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:15%" sortable="true"/>
                                
                                <display:column property="erpmimModel" title="Item Model"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:15%" sortable="true"/>
                                
                                <display:column property="erpmItemCategoryMasterByErpmimItemCat1.erpmicmCatDesc" title="Item Type"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:15%" sortable="true"/>
                                
                                <display:column property="erpmItemCategoryMasterByErpmimItemCat2.erpmicmCatDesc" title="Item Category"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                                
                                <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Unit of Purchase"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                                
                                <display:column property="erpmCapitalCategory.ermccDesc" title="Capital Category"
                                                maxLength="35" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                               <%-- 
                                
                                <display:column property="serializableStatus" title="Serial No. Applicable"
                                                maxLength="10" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                                <display:column property="formatederpmimDepreciationMethod" title="Depreciation Method"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:15%" sortable="true"/>
                                 
                               --%>  
                              
                                
                                <display:column paramId="ErpmimId" paramProperty="erpmimId"
                                                href="/pico/PrePurchase/EditItem.action"
                                                headerClass="gridheader" class="griddata" media="html"  value="Edit">
                                </display:column>
                                
                                <display:column paramId="ErpmimId" paramProperty="erpmimId"
                                                href="/pico/PrePurchase/DeleteItem.action"
                                                headerClass="gridheader" class="griddata" media="html" value="Delete">
                                </display:column>

                            </display:table>
                </s:form>
               </td></tr></table>                                        
            </div>
            <br>    
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
