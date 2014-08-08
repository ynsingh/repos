<%-- 
    Document   : BrowseTenderSchedule
    Created on : 8 Apr, 2013, 11:15:40 AM
    Author     : Saeed
--%>


<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

            <!-- *********************************End Menu****************************** erpmGenCtrl.erpmgcGenType-->
            <div id ="mainContent" align="center">
             <s:form name="frmBrowseTenderSchedule">
                   <s:hidden name="tenschdl.tscTscId"/> 
                 <s:property value="message"/>
                 <s:label value="TENDER SCHEDULE" />
                 <table width="70%" border="0" cellspacing="0" cellpadding="0" align="center">
                     <display:table name="tenschdlList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" id="doc" decorator="PrePurchase.PrePurchaseDecorator"
                               requestURI="/PrePurchase/BrowseTenderSchedule.action">
                            <display:column  class="griddata" title="S.No" maxLength="100" sortable="true" style="width:5%" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                    </display:column>
                    <display:column property="institutionmaster.imShortName" title="Institute Name"
                                    maxLength="10" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:8%" sortable="true"/>

                    <display:column property="subinstitutionmaster.simShortName" title="Subinstitution Name"
                                    maxLength="10" headerClass="gridheader"
                                    class="griddata" style="width:8%" sortable="true"/>

                    <display:column property="departmentmaster.dmShortName" title="Department Name"
                                    maxLength="10" headerClass="gridheader"
                                    class="griddata" style="width:8%" sortable="true"/>

                    <display:column property="erpmTenderMaster.tmTenderNo" title="Tender No"
                                    maxLength="40" headerClass="gridheader"
                                    class="griddata" style="width:15%" sortable="true"/>

                    <display:column paramId="tenschlId" paramProperty="tscTscId"
                                    href="/pico/PrePurchase/EditTenderSchedule"
                                    headerClass="gridheader" class="griddata" media="html" style="width:5%">
                        Edit
                    </display:column>

                    <display:column paramId="tenschlId" paramProperty="tscTscId"
                                    href="/pico/PrePurchase/DeleteTenderSchedule"
                                    headerClass="gridheader" class="griddata" media="html"   style="width:5%">
                        Delete
                    </display:column>
                   

                    
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

