<%--Document   : 
    Created on : Apr 13, 2012, 11:55:54 AM
    Author     : 
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
                <jsp:include page="../Administration/menu.jsp"   flush="true" ></jsp:include >
                </div>

                <!-- *********************************End Menu****************************** -->
                <div id ="mainContent" align="center">

                    <br><br>
                    <div style ="background-color: #215dc6;">
                        <p align="center" class="pageHeading" style="color: #ffffff">Item Category list</p>
                        <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmItemCategoryBrowse">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <display:table name="erpmItemCategoryMasterList" pagesize="30"
                                           excludedParams="*"  cellpadding="0"
                                           cellspacing="0"  id="doc" decorator="Administration.ActorDecorator"
                                           requestURI="/Administration/SaveItemCategory.action">
                                <display:column  class="griddata" title="S.No" sortable="true" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <display:column property="institutionmaster.imName" title="Institution"
                                                    headerClass="gridheader"
                                                    class="griddata"  style="width:25%" sortable="true"/>

                                    <display:column property="erpmicmItemLevel" title="Level"
                                                    maxLength="25" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:10%" sortable="true"/>
                                    <display:column property="erpmicmCatDesc" title="Catogry"
                                                    maxLength="25" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:25%" sortable="true"/>

                                    <display:column property="erpmicmDepreciationPercentage" title="Percentage"
                                                    maxLength="25" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:12%" sortable="true"/>
                                    <display:column property="categoryDepreciationMethod" title="Method"
                                                    maxLength="25" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:15%" sortable="true"/>

                                    <display:column paramId="STUDID" paramProperty="erpmicmItemId"
                                                    href="/pico/Administration/EditItemCategory.action"
                                                    headerClass="gridheader" class="griddata" media="html" style="width:10%"  title="Edit">
                                        Edit
                                    </display:column> 

                                    <display:column paramId="STUDID" paramProperty="erpmicmItemId" href="/pico/Administration/DeleteItemCategory.action"
                                                    headerClass="gridheader"  class="griddata" media="html"  style="width:10%" title="Delete">
                                        Delete

                                    </display:column>

                                    <tr><td></td></tr>


                                </display:table>
                        </table>
                    </s:form>
                    <br>
                </div>
                &nbsp;

            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>