<%-- 
    Document   : TenderSubmissionBrowse
    Created on : Mar 24, 2013, 11:34:36 AM
    Author     : ehtesham, arpan
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
         <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Budgettypemaster.js"></script> 
          <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
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
                        <p align="center" class="pageHeading" style="color: #ffffff">Tender Submission list</p>
                        <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                     <s:form name="frmTenderSubmissionBrowse">
                        <table width="200%" border="0" cellspacing="0" cellpadding="0" align="left">
                            <display:table name="erpmtsbList" pagesize="15"
                                           excludedParams="*"  cellpadding="0"
                                           cellspacing="0"  id="doc"
                                           requestURI="/PrePurchase/BrowseTenderSubmission.action">
                                        <display:column  class="griddata" title="S.No" sortable="true" headerClass="gridheader" style="width:2%" >
                                        <c:out> ${doc_rowNum}
                                        </display:column>
                                    
                                        <display:column property="institutionmaster.imShortName" title="Institute"
                                                    headerClass="gridheader"
                                                    class="griddata"  style="width:5%" sortable="true"/>
                                                                     
                                       <display:column property="subinstitutionmaster.simShortName" title="SubInstitute"
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:8%" sortable="true"/>
                                       
                                       <display:column property="departmentmaster.dmShortName" title="Department"
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:8%" sortable="true"/>
                                      
                                        <display:column property="erpmTenderMaster.tmTenderNo" title="Tender No."
                                                    maxLength="50" headerClass="gridheader" 
                                                   class="griddata"  style="width:15%" sortable="true"/>
                                        
                                        <display:column property="tsbCompanyName" title="Company Name."
                                                    maxLength="30" headerClass="gridheader" 
                                                   class="griddata"  style="width:10%" sortable="true"/>
                                         <display:column property="erpmTenderMaster.tmName" title="Tender Name."
                                                    maxLength="30" headerClass="gridheader" 
                                                   class="griddata"  style="width:10%" sortable="true"/>
                                        
                                        <display:column property="tsbCompanyEmail" title="EMAIL"
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:10%" sortable="true"/>
                  
                                       <display:column paramId="tsbTsbId" paramProperty="tsbTsbId"
                                                    href="/pico/PrePurchase/EditTenderSubmissionAction.action"
                                                    headerClass="gridheader" class="griddata" media="html" style="width:5%"  title="Edit">
                                        Edit
                                        
                                    </display:column>     
                                                     <display:column paramId="tsbTsbId" paramProperty="tsbTsbId"
                                                    href="/pico/PrePurchase/DeleteTenderSubmissionAction.action"
                                                    headerClass="gridheader" class="griddata" media="html" style="width:5%"  title="Delete">
                                        Delete
                                        
                                    </display:column>     
                                    <tr><td></td></tr>


                                </display:table>
                        </table>
                                     
                    </s:form>
                    <br>
                </div>
              

            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
