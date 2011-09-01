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
            <div id ="mainContent">
                <p align="center" class="pageHeading" ><s:label value="INSTITUTION USER ROLE PRIVILEGES MANAGEMENT"/></p>
                <p align="center" class="pageMessage" ><s:property value="message" /></p>
                <p align="left" class="pageText">You are now setting up privileges for '<s:property value="iur.iurName" />'
                    role in '<s:property value="iur.institutionmaster.imName" />' institution.</p>
                <p align="left" class="pageText">Please Choose generic role to initialize '<s:property value="iur.iurName" />' </p>
                <s:form name="frmIURPCreate" action="CreateIURP">
                    <s:hidden name="iur.iurId"/>
                    <s:hidden name="ImId" />
                <%--    <s:hidden name="InstitutionRole"/> --%>
                <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tr>
                            <td>
                                <s:select cssClass="textInput" label="Generic Role" name="gurId" headerKey="" headerValue="-- Please Select --" list="gurList" listKey="gurId" listValue="gurRoleName"
                                    onchange="showRoleRemarks('CreateIURP_gurId','CreateIURP_gurDescription');"/>
                                <s:textfield cssClass="textInputRO" label="Role Description"  name="gurDescription" maxLength="100" size="100"  readonly="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:submit theme="simple" name="btnSubmit" value="Initialize Role" action="CreateIURP"/>
                            </td>
                        </tr>

                </table>
                </s:form>
            </div>

            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
    </body>
</html>