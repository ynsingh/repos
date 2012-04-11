<%-- 
    Document   : ManageGeneralMaster
    Created on : 6 Jan, 2011, 9:46:01 PM
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
        <!--<script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>-->
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">        
        <div id="container" >
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent"> <br><br>
                <p align="center"><s:label value="GENERAL MASTER MANAGEMENT" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>
                <s:form name="frmGeneralMasterAddEdit" action="SaveGeneralMasterAction"  validate="true">
                    <s:hidden name="erpmgm.erpmgmEgmId" />
                    
                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td><br>
                                    <s:select label="Control Parameter" name="erpmgm.erpmGenCtrl.erpmgcGenType" headerKey="" headerValue="-- Please Select --" list="erpmgctrlList" listKey="erpmgcGenType" listValue="erpmgcGenDesc" cssClass="textInput"/>
                                    <s:textfield label="General Master Entry" name="erpmgm.erpmgmEgmDesc" title="Enter General Master Entry" 
                                                 required="true" requiredposition="left" maxLength="50" size="50"  cssClass="textInput"/>
                                    <s:textfield label="Remarks" name="erpmgm.erpmgmerpmgmRemarks" title="Remarks about General Master Entry"
                                                 required="true" requiredposition="left" maxLength="50" size="50" cssClass="textInput" />
                                    <s:textfield label="testingRemarks" name="erpmgm.erpgmEgmRemarks" title="Remarks about General Master Entry"
                                                 required="true" requiredposition="left" maxLength="50" size="50" cssClass="textInput" />
                                    <s:textfield label="checksRemarks" name="erpmgm.erpgmEgmChecks" title="Remarks about General Master Entry"
                                                 required="true" requiredposition="left" maxLength="50" size="50" cssClass="textInput" />
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save General Master Entry"  cssClass="textInput" />
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Fetch GM Entries" action="FetchGMEntries"  cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearGeneralMaster" cssClass="textInput"/>
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                        </tbody>
                    </table>
                </s:form>
            </div>


             <div id ="mainContent" align="center">
             <s:form name="frmGeneralMasterBrowse"> 
                 <table width="60%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                     <display:table name="erpmgmList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Administration/ManageExportEntries.action">
                         <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                         <display:column property="erpmGenCtrl.erpmgcGenDesc" title="Control Parameter"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:40%" sortable="false" />

                         <display:column property="erpmgmEgmDesc" title="Description"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="false"/>

                         <display:column paramId="ErpmgmEgmId" paramProperty="erpmgmEgmId"
                                    href="/pico/Administration/EditGeneralMasterAction.action"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                    </display:column>
                    <display:column paramId="ErpmgmEgmId" paramProperty="erpmgmEgmId"
                                    href="/pico/Administration/DeleteGeneralMasterAction.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                    </display:column>
                </display:table>
                </td></tr>
                </table>
                 <br>
             </s:form>
            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>