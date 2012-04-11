<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    Document   : ManageCapitalCategory
    Created on : 5 Jan, 2011, 9:53:25 PM
    Author     : sknaqvi
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
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
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <br><br>
            <p align="center"><s:label value="CAPITAL ITEMS CATEGORIES MANAGEMENT" cssClass="pageHeading"/></p>
            <p align="center"><s:property value="message" /></p>
            <div id ="mainContent">
                <s:form name="frmCapitalItemCategoryAddEdit" action="SaveCapitalCategoryAction"  validate="true">
                    <s:hidden name="erpmcc.erpmccId" />                    
                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>                        
                            <tr>
                                <td><br>
                                    <s:select label="Institution" name="erpmcc.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="50"
                                                 label="Capital Item Category Name" name="erpmcc.ermccDesc" title="Enter Capital Item Category Name"  cssClass="textInput"/>
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Capital Item Category"   cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Fetch Capital Item Entries" action="FetchCCEntries" cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearCapitalCategory" cssClass="textInput"/>
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                        </tbody>
                    </table>
                </s:form>
            </div>


             <div id ="mainContent" align="center">
             <s:form name="frmCapitalItemsCategoryBrowse">                 
                <table width="60%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="erpmccList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/Administration/ManageCapitalExportAction.action">
                         <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="institutionmaster.imName" title="Institution"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:30%" sortable="true"/>
                        <display:column property="ermccDesc" title="Description"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>
                        <display:column paramId="ErpmccId" paramProperty="erpmccId"
                                    href="/pico/Administration/EditCapitalCategoryAction.action" 
                                    headerClass="gridheader" class="griddata" media="html" title="Edit" >
                                    Edit
                        </display:column>
                        <display:column paramId="ErpmccId" paramProperty="erpmccId"
                                    href="/pico/Administration/DeleteCapitalCategoryAction.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete" style="width:30%">
                                    Delete
                        </display:column>
                    </display:table>
                <br></td></tr>
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