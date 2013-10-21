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
            <div id ="mainContent"> <br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">GENERAL FINANCIAL RULE</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <div style="border: solid 1px #000000; background: gainsboro">
                   <s:form name="frmGFR" action="SaveManageGFRAction"  theme="qxhtml">
                   <s:hidden name="grfMaster.gfrGfrId" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                             <tr>
                                <td>
                                     <s:textfield label="Section" name="grfMaster.gfrSection"
                                                 required="true" requiredposition="left" maxLength="5" size="20"  cssClass="textInput"/>
                                    <s:textfield label="Chapter No" name="grfMaster.gfrChapterNo"
                                                 required="true" requiredposition="left" maxLength="2" size="20" cssClass="textInput" />
                                     <s:textfield label="Chapter Name" name="grfMaster.gfrChapterName"
                                                 required="true" requiredposition="left" maxLength="45" size="100" cssClass="textInput" />
                                      <s:textfield label="Rule No" name="grfMaster.gfrRuleNo" 
                                                 required="true" requiredposition="left" maxLength="2000" size="20" cssClass="textInput" />
                                       
                                         <s:textarea rows="15" cols="100" label="Description" name="grfMaster.gfrDescription" title="">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                                                  </s:textarea>
                                </td>
                             </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save"  cssClass="textInput" />
                                   <s:submit theme="simple" name="bthReset" value="Browse" action="BrowseManageGFRAction"  cssClass="textInput"/>

                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearManageGFRAction" cssClass="textInput"/>
                                </td>
                            </tr>
                            
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