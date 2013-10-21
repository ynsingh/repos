
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <br><br>
                <p align="center">ISSUE ITEMS LIST</p>

                <br><p align="center"><s:property value="message" /></p>
                <s:form name="frmIssueItemsBrowse" >

                    <table width="100%" border="0" cellspacing="1" cellpadding="5" align="center">
                        <display:table name="eimList" pagesize="" decorator="Inventory.InventoryDecorator"
                                       excludedParams="*" export="false" cellpadding="2"
                                       cellspacing="0" id="doc"
                                       requestURI="/Inventory/BrowseIssueItems.action" >
                            <display:column  class="griddata" title="Record" sortable="true"  headerClass="gridheader" style="width:10%">
                                <c:out> ${doc_rowNum}
                                </display:column>

                                  <display:column property="ismIssueNo" title="Issue No:"
                                                maxLength="10" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:10%" sortable="true"/>
                                <display:column property="formattedismIssueDate" title="Issue Date"
                                                maxLength="10" headerClass="gridheader" style="width:10%"
                                                class="griddata" sortable="true"/>
                                <display:column property="ismIssueDesc" title="Issue Desc:"
                                                maxLength="45" headerClass="gridheader" style="width:20%"
                                                class="griddata" sortable="true"/>

                               <display:column property="erpmIndentMaster.indtTitle" title="Against Indent Title"
                                                maxLength="30" headerClass="gridheader"
                                                class="griddata" sortable="true" style="width:20%"/>


                                <display:column paramId="EIMID" paramProperty="ismId" sortable="true"
                                                href="/pico/Inventory/EditIssueItems" style="width:10%"
                                                headerClass="gridheader" class="griddata" media="html"  title="Edit" >
                                    Edit
                                </display:column>
                                <display:column paramId="EIMID" paramProperty="ismId" style="width:10%"
                                                href="/pico/Inventory/DeleteIssueItems" sortable="true"
                                                headerClass="gridheader" class="griddata" media="html" title="Delete" >
                                    Delete
                                </display:column>
                                    <display:column paramId="EIMID" paramProperty="ismId" style="width:10%"
                                                    href="/pico/Inventory/EmailIssueItems" sortable="true"
                                                headerClass="gridheader" class="griddata" media="html" title="SendEmail" >
                                    SendEmail
                                </display:column>
                            </display:table>
                    </table>
                </s:form>
                <br><br><br>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
