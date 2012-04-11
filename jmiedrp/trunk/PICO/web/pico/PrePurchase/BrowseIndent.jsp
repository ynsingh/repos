<%--
    Document   : IndentForm
    Created on : Feb 1, 2011, 12:28:32 PM
    Author     : Sajid Aziz
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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="sajidaziz00000, Jamia Millia Islamia">
        <meta name="email" content="sajidaziz00@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
              <div id ="mainContent" align="center">
             <s:form name="frmindentBrowse">
                 <p align="left" class="pageMessage"><s:property value="message" /></p>
              <s:hidden name="erpmindtmast.indtIndentId" />              
                 <table width="65%" border="2" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="IndentList" pagesize="20"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/BrowseIndent.action">
                     <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="indtIndentDate" title="Indent_Date"
                                    maxLength="10" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                    style="width:5%" sortable="true"/>
                    <display:column property="institutionmaster.imShortName" title="Institute."
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>
                    <display:column property="subinstitutionmaster.simShortName" title="Coll./Flty./School"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>
                    <display:column property="departmentmaster.dmName" title="Department"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>
                    <display:column property="budgetheadmaster.bhmName" title="Budget_Head "
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata"  sortable="true"/>       
                    <display:column paramId="indtindentid" paramProperty="indtIndentId"
                                    href="/pico/PrePurchase/DeleteIndentMasterDetails.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                    </display:column>
                    <display:column paramId="indtindentid" paramProperty="indtIndentId"
                                    href="/pico/PrePurchase/EditIndentMasterDetails.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit">
                                    Edit
                    </display:column>                                   
                <%--    <display:column paramId="indtindentid" paramProperty="indtIndentId"
                                    href="/pico/PrePurchase/BrowseIndentDetail.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Browse Indent Details">
                                    Go_To_Indent_Details
                    </display:column>--%>
                </display:table>
                </table>
             </s:form>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
