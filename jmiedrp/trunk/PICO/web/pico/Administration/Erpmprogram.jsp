<%-- 
    Document   : Trainee
    Created on : Mar 26, 2012, 12:12:54 PM
    Author     : mkhan
    Modified by SKNaqvi on Dec. 09, 2012 to use qxhtml theme
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
        <s:head />
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
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">PROGRAM MANAGEMENT</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro" >

                    <s:form name="frmErpmProgram" action="SaveErpmprogramAction"  theme="qxhtml">
                        <s:hidden name="erpmprogram.erpmpId" />
                        <br>
                                <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                             label="Program Name" name="erpmprogram.erpmpDisplayName" title="Enter Program Name"  cssClass="textInput">
                                             <s:param name="labelcolspan" value="%{2}" />
                                             <s:param name="inputcolspan" value="%{6}" />
                                </s:textfield>

                                <s:select cssClass="textInput" label="Submodule"  required="true" name="erpmprogram.erpmsubmodule.erpmSubModuleId" headerKey="" headerValue="-- Please Select --" list="erpmsmList" listKey="erpmSubModuleId" listValue="esmName"
                                          onchange="getProgramBySubmoduleList('SaveErpmprogramAction_erpmprogram_erpmsubmodule_erpmSubModuleId', 'SaveErpmprogramAction_erpmprogram_erpmprogram_erpmpId');">
                                             <s:param name="labelcolspan" value="%{2}" />
                                             <s:param name="inputcolspan" value="%{6}" />
                                </s:select>

                                <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                             label="Program Action" name="erpmprogram.erpmpHref" title="Enter Program Path"  cssClass="textInput">
                                             <s:param name="labelcolspan" value="%{2}" />
                                             <s:param name="inputcolspan" value="%{6}" />
                                </s:textfield>

                                <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                             label="Order" name="erpmprogram.erpmpOrder" title="Enter Order"  cssClass="textInput">
                                             <s:param name="labelcolspan" value="%{2}" />
                                             <s:param name="inputcolspan" value="%{6}" />
                                </s:textfield>

                                <s:select label="Subprogram of " required="false" name="erpmprogram.erpmprogram.erpmpId" headerKey="0" headerValue="None" list="erpmList" listKey="erpmpId" listValue="erpmpDisplayName"  cssClass="textInput">
                                             <s:param name="labelcolspan" value="%{2}" />
                                             <s:param name="inputcolspan" value="%{6}" />
                                </s:select>

                                <s:label value="Choose Generic Privileges Group(s) for the Program" cssClass="label">
                                             <s:param name="labelcolspan" value="%{2}" />
                                             <s:param name="inputcolspan" value="%{6}" />
                                </s:label>
                                    
                                
                                
                        <s:checkbox cssClass="checkboxLabel"  label="Institution Administrator" name="institutionAdministrator"  value="1" labelposition="left"/>
                                <s:checkbox cssClass="checkboxLabel"  label="Purchase Manager" name="purchaseManager" value="0" labelposition="left"/>
                                <s:checkbox cssClass="checkboxLabel"  label="Purchase Staff" name="purchaseStaff" value="0" labelposition="left"/>
                        
                                <s:label/>
                                <s:label/>
                                <s:label/>
                                <s:label/>
                                <s:label/>  
                                <s:submit name="btnSubmit" value="Save"   action="SaveErpmprogramAction">
                                            <s:param name="colspan" value="%{1}" />
                                            <s:param name="align" value="%{'center'}" />
                                </s:submit>

                                <s:submit name="btnSubmit" value="Browse" action="BrowsePrograms">
                                            <s:param name="colspan" value="%{1}" />
                                            <s:param name="align" value="%{'center'}" />
                                </s:submit>

                                <s:submit name="bthReset" value="Clear" action="ClearProgram">
                                            <s:param name="colspan" value="%{1}" />
                                            <s:param name="align" value="%{'center'}" />
                                </s:submit>
                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>

            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>