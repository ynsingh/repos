<%-- 
    Document   : ManageGeneralTerms
    Created on : 20 May, 2011, 5:57:47 PM
    Author     : Tanvir Ahmed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
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
            <s:property value="message" />
           <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">
               <s:form name="frmGeneralTerms" action="SaveGeneralTermsAction"  validate="true">
                    <s:hidden name="GTerms.gtGtid" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td colspan="2" align="left">
                            </tr>
                            <tr>
                                <td valign="middle" class="FormContent">
                                    <s:label value="GENERAL TERMS & CONDITIONS"  cssClass="pageHeading"/>
                                </td>
                            </tr>
                            <tr>
                                <td> <br><br>
                                <s:url action="BrowseGeneralTerms" id="NavigatetoURL"></s:url>
                                    <a href='<s:property value="NavigatetoURL"/>'>Browse General Terms</a>
                                    <br>

                                        <s:select label="Institution" required="true" name="GTerms.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="termsImIdList" listKey="imId" listValue="imName" cssClass="textInput"/>

                                        <s:select label="Terms & Conditions Type" required="true" name="GTerms.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="termsTypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>

                                        <s:textarea required="true" requiredposition="topleft" rows="5" cols="50" label="Terms Description" name="GTerms.gtTermsDescription" title=""  />
                               
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save General Terms"   cssClass="textInput"/>  <%-- action="SaveGeneralTermsAction"/> --%>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear"  cssClass="textInput" action="ClearGeneralTermsAction"/>
                                <td>
                            </tr>
                            <tr>
                    <td> <br><br> </td>
                </tr>
                 <tr>
                    <td> <br><br> </td>
                </tr>

                        </tbody>
                    </table>
                </s:form>
            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>