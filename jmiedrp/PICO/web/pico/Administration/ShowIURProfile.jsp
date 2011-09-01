<%--
    Document   : ManageCapitalCategory
    Created on : 25 Jan, 2011, 23:00:20 PM
    Author     : sknaqvi
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>      
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <link href="/BookShop/css/stylesheet.css" type="text/css" rel="stylesheet">
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container" >
            <div id="header">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>           
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->

            <div id ="mainContent" align="center">
            <p align="center" class="pageHeading" ><s:label value="INSTITUTION USER ROLE PRIVILEGES MANAGEMENT"/></p>
                <p align="center" class="pageMessage"><s:property value="message" /></p>
                <p align="left" class="pageMessage"><s:property value="message2"/></p>

                 <s:form name="frmIURBrowse" method="POST">
                     
                     <s:hidden name="iur.iurId"/>                     
                    <table width="60%" border="0" cellspacing="0" cellpadding="0" align="center" >
                        <tr><s:submit theme="simple" name="btnSubmit" value="Add More Programs & Privileges"  align="right" action="AddIURP"/></tr>
                     <display:table name="irpList" pagesize="15"  uid="row"
                                    excludedParams="" export="false" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Administration/BrowseIURP.action" decorator="Administration.ManageInstitutionUserRoleDecorator">
                       <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                         <display:column property="erpmmodule.erpmmShortName" title="Module Name"
                                    maxLength="50" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                    style="width:15%" sortable="false"/>
                         <display:column property="erpmprogram.erpmpDisplayName" title="Program Name"
                                    maxLength="70" headerClass="gridheader"
                                    class="griddata" style="width:20%" sortable="false"/>
                         <display:column property="iupCanAdd" title="Can Add"
                                    maxLength="15" headerClass="gridheader"
                                    class="griddata" style="width:5%" sortable="false"/>
                        <display:column property="iupCanEdit" title="Can Edit"
                                    maxLength="15" headerClass="gridheader"
                                    class="griddata" style="width:5%" sortable="false"/>
                        <display:column property="iupCanDelete" title="Can Delete"
                                    maxLength="15" headerClass="gridheader"
                                    class="griddata" style="width:8%" sortable="false"/>
                        <display:column property="iupCanView" title="Can View"
                                    maxLength="15" headerClass="gridheader"
                                    class="griddata" style="width:5%" sortable="false"/>
                        <display:column paramId="iupId" paramProperty="iupId" title="Edit Privilege" style="width:10%"
                                        href="/pico/Administration/EditIURP.action"
                                    headerClass="gridheader" class="griddata" media="html">
                                    Edit
                         </display:column>
                                    
                         <display:column paramId="iupId" paramProperty="iupId" title="Revoke Privilege" style="width:10%"
                                           href="/pico/Administration/DeleteIURP.action?iur.iurId=${param['iur.iurId']}"
                                    headerClass="gridheader" class="griddata" media="html">                             
                                    Revoke
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