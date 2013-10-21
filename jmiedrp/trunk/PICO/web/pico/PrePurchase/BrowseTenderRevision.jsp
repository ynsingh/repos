<%-- 
    Document   : BrowseTenderRevision
    Created on : 8 May, 2013, 1:08:53 PM
    Author     : wml4
--%>

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
                <jsp:include page="../Administration/menu.jsp"   flush="true" ></jsp:include >
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center"><br><br>
                <div style ="background-color: #215dc6;" align="center">
                    <p align="center" class="pageHeading" style="color: #ffffff">TENDER REVISION</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                
                <s:form name="frmBrowseTenderRevision">
                    
                    <s:label value="TENDER REVISION" />
                    
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                        <display:table name="etrList" pagesize="15"
                                       excludedParams="*" export="false" cellpadding="0"
                                       cellspacing="0" id="doc" decorator="PrePurchase.PrePurchaseDecorator"
                                       requestURI="/PrePurchase/BrowseTenderRevision.action">
                            <display:column  class="griddata" title="S.No" maxLength="100" sortable="true" style="width:10%" headerClass="gridheader">
                                <c:out> ${doc_rowNum}
                                </display:column>
                                
                                <display:column property="subinstitutionmaster.simShortName" title="SubInst. Name"
                                                maxLength="20" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>

                                <display:column property="departmentmaster.dmShortName" title="Dept. Name"
                                                maxLength="20" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                style="width:10%" sortable="true"/>

                                <display:column property="erpmTenderMaster.tmTenderNo" title="Tender No"
                                                maxLength="30" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                style="width:20%" sortable="true"/>

                                <display:column property="trRevisionNo" title="Revision No"
                                                maxLength="20" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                style="width:10%" sortable="true"/>

                                <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Revision Type"
                                                maxLength="20" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                style="width:10%" sortable="true"/>

                                <display:column property="trRevisionDate" title="Revsision Date"
                                                maxLength="20" headerClass="gridheader"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                style="width:10%" sortable="true"/>
                                
                                <display:column paramId="ETRID" paramProperty="trTrId"
                                                href="/pico/PrePurchase/EditTenderRevision"
                                                headerClass="gridheader" class="griddata" media="html"   style="width:10%" title="Edit">
                                      Edit
                                </display:column>

                                  <display:column paramId="ETRID" paramProperty="trTrId"
                                    href="/pico/PrePurchase/DeleteTenderRevision"
                                    headerClass="gridheader" class="griddata" media="html"   style="width:10%" title="Delete">
                                    Delete
                                </display:column>



                            </display:table>


                    </table>

                </s:form>
            </div>
            <br>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
