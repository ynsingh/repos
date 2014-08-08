<%-- 
    Document   : ManageGeneralTerms
    Created on : 20 May, 2011, 5:57:47 PM
    Author     : Tanvir Ahmed 
    I18n By    : Mohd. Manauwar Alam
               : Jan 2014
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
            
           <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />    
                </s:bean>

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.GenTermsCond')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

               <s:form name="frmGeneralTerms" action="SaveGeneralTermsAction"  validate="true">
             <%--       <p align="left" class="pageMessage"><s:property value="message" /></p> --%>

                    <s:hidden name="GTerms.gtGtid" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>


                            <tr>
<%--                                <td colspan="2" align="left">
                            </tr>
                            <tr>
                                <td valign="middle" class="FormContent">
                                    <s:label value="GENERAL TERMS & CONDITIONS"  cssClass="pageHeading"/>
                                </td>
                            </tr>
                            <tr>
                                <td> <br><br>
                                     <s:select label="Institution" required="true" requiredposition="" name="GTerms.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="termsImIdList" listKey="imId" listValue="imName" cssClass="textInput"/>

                                        <s:select label="Terms & Conditions Type" required="true" requiredposition="" name="GTerms.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="termsTypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>

                                        <s:textarea  requiredposition="topleft" rows="5" cols="50" label="Terms Description" name="GTerms.gtTermsDescription" title=""  />
                               
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save General Terms" />  <%-- action="SaveGeneralTermsAction"/> --%>
        <%--                        </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear"   action="ClearGeneralTermsAction"/>
                                <td>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Browse General Terms"    action="BrowseGeneralTerms"/> --%>
                                <td>
                                     <s:select key="Administration.InstitutionName" required="true" requiredposition="" name="GTerms.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="termsImIdList" listKey="imId" listValue="imName" cssClass="textInput"/>

                                        <s:select key="Administration.TermsAndCondType" required="true" requiredposition="" name="GTerms.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="termsTypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>

                                        <s:textarea  requiredposition="topleft" rows="5" cols="50" key="Administration.TermsDescription" name="GTerms.gtTermsDescription" title=""  />

                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" key="Administration.Save" />  <%-- action="SaveGeneralTermsAction"/> --%>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" key="Administration.Clear"   action="ClearGeneralTermsAction"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" key="Administration.Browse"    action="BrowseGeneralTerms"/>
                                </td>
                            </tr>
                          <%--<tr>
                    <td> <br><br> </td>
                </tr>
                 <tr>
                    <td> <br><br> </td>
                </tr> --%>

                        </tbody>
                    </table>
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
