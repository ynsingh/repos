<%--
    Document   : ManageGeneralTermsBrowse
    Created on : 25 May, 2011, 2:54:29 PM
    Author     : Tanvir Ahmed
--%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
--%>

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
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <br>
             <s:form name="frmManageGeneralTermsBrowse" >
                   <p align="center"><s:label value="GENERAL TERMS LIST" /></p>
                 <s:property value="message" />
                 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                    <display:table name="GTermsList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Administration/BrowseGeneralTerms.action">
                    <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="institutionmaster.imName" title="Institution Name"
                                    maxLength="35" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:20%" sortable="true"/>
                    <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Terms Type"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:15%" sortable="true"/>

                        <display:column property="gtTermsDescription" title="Terms & Condition Description"
                                    maxLength="255" headerClass="gridheader"
                                    class="griddata" style="width:65%" sortable="true"/>
                    <display:column paramId="GTGTID" paramProperty="gtGtid" href="/pico/Administration/EditGeneralTerms"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit" >
                                    Edit
                    </display:column>
                    <display:column paramId="GTGTID" paramProperty="gtGtid" href="/pico/Administration/DeleteGeneralTerms.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete" style="width:20%" >
                                    Delete
                    </display:column>
                </display:table>
                </table>
             </s:form>
             <br>
            </div>
             <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
