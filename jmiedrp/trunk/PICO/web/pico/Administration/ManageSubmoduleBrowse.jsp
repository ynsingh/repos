<%-- 
    Document   : ManageSubmoduleBrowse
    Created on : May 10, 2012, 5:46:08 PM
    Author     : mkhan
--%>
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
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">SUB-MODULE LIST</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                
                <div style="border: solid 1px #000000; background: gainsboro">
                        
                <s:form name="frmErpmprogramBrowse">                 
                    <display:table name="erpmsmList" pagesize="15"
                        excludedParams="*" export="true" cellpadding="0"
                        cellspacing="0" id="doc"
                        requestURI="/Administration/BrowseModules.action">

                       <display:column  class="griddata" title="S.No"  style="width:5%" 
                                        sortable="false" headerClass="gridheader">
                            <c:out> ${doc_rowNum}
                       </display:column>
                    
                       <display:column property="esmName" title="Submodule Name"
                                    headerClass="gridheader" style="width:20%" 
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="true"/>
                       
                       <display:column property="erpmmodule.erpmmName" title="Module Name"
                                    headerClass="gridheader" style="width:20%" 
                                    class="griddata" sortable="true"/>
                       
                       <display:column property="esmOrder" title="Order"
                                    headerClass="gridheader" style="width:10%" 
                                    class="griddata" sortable="true"/>
                    
                       <display:column property="esmHref" title="Href name"
                                    headerClass="gridheader" style="width:30%" 
                                    class="griddata" sortable="true"/>
                       
                       <display:column paramId="erpmSubModuleid" paramProperty="erpmSubModuleId" style="width:5%" 
                                    href="/pico/Administration/Editmodule.action" title="Edit"
                                    headerClass="gridheader" class="griddata" media="html" >
                                    Edit
                       </display:column>
                       
                        <display:column paramId="erpmSubModuleid" paramProperty="erpmSubModuleId" title="Delete" style="width:5%" 
                                    href="/pico/Administration/Deletemodule.action"
                                    headerClass="gridheader" class="griddata" media="html">
                                    Delete
                        </display:column>
                </display:table>                
                </s:form> 
            </div>
            </div>             
            <br>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>

