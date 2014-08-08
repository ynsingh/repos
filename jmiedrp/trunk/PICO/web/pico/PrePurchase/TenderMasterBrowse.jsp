<%-- 
    Document   : TenderMasterBrowse
    Created on : Mar 20, 2013, 11:28:12 AM
    Author     : FarazAhmad
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
                        <p align="center" class="pageHeading" style="color: #ffffff">Tender Master list</p>
                        <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                     <s:form name="frmTenderBrowse">
                         <table width="100%" border="0" cellspacing="0" cellpadding="0" align="right">
                            <display:table name="erpmTenderMasterList" pagesize="15" decorator="PrePurchase.PrePurchaseDecorator"
                                           excludedParams="*"  cellpadding="0"
                                           cellspacing="0"  id="doc"
                                           requestURI="/PrePurchase/BrowseTender.action" >
                                <display:column  class="griddata" title="S.No" sortable="true" headerClass="gridheader" style="width:3%" >
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <%--
                                         <display:column property="tmTmId" title="ID"
                                                    headerClass="gridheader"
                                                    class="griddata"  style="width:5%" sortable="true"/>
                                      --%>
                                         
                                         
                                         <display:column property="departmentmaster.dmShortName" title="Deptartment"
                                                    maxLength="25" headerClass="gridheader"
                                                    class="griddata" style="width:7%" sortable="true"/>
                                                                               
                                         <display:column property="subinstitutionmaster.simShortName" title="Sub-Institute"
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:7%" sortable="true"/>
                                        
                                         <display:column property="tmTenderNo" title="Tender No."
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:12%" sortable="true"/>
                                         
                                         <display:column property="tenderDate" title="Tender Date" 
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:5%" sortable="true"/>
                                         
                                         <display:column property="tmName" title="Tender Name"
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:12%" sortable="true"/>
                                        
                                         <display:column property="erpmGenMasterByTmTypeId.erpmgmEgmDesc" title="Tender Type"
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:6%" sortable="true"/>
                                        
                                        <display:column property="erpmGenMasterByTmStatusId.erpmgmEgmDesc" title="Status"
                                                    maxLength="25" headerClass="gridheader"
                                                   class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:4%" sortable="true"/>
                                        
                              
                                     
                                       <display:column paramId="STUDID" paramProperty="tmTmId" href="/pico/PrePurchase/DeleteTender.action"
                                                    headerClass="gridheader"  class="griddata" media="html"  style="width:2%" title="Delete">
                                        Delete

                                       </display:column> 
                                      
                                     
                                       <display:column paramId="tmTmId" paramProperty="tmTmId"
                                                    href="/pico/PrePurchase/EditTender.action"
                                                    headerClass="gridheader" class="griddata" media="html" style="width:2%"  title="Edit">
                                        Edit
                                      </display:column>     
                                         <display:column paramId="tmTmId" paramProperty="tmTmId"
                                                    href="/pico/PrePurchase/SubmittedTendersReportAction"
                                                    headerClass="gridheader" class="griddata" media="html" style="width:2%"  title="Edit">
                                        Print Submitted
                                        Report
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
                                      <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
                                      </div>
            </div>
    </body>
</html>
