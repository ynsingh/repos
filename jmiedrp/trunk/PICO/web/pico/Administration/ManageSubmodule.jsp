<%-- 
    Document   : ManageSubmodule
    Created on : May 8, 2012, 3:10:18 PM
    Author     : mkhan
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
                <br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">Sub-Module Records Management</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                <s:form name="frmErpmProgram" action="ManageSubmoduleAction"  theme="qxhtml">
                    <s:hidden name="erpmsm.erpmSubModuleId" />
                    <br>
                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Sub Module Name" name="erpmsm.esmName" title="Enter Sub Module Name"  cssClass="textInput">
                                    <s:param name="labelcolspan" value="%{2}"/>
                                    <s:param name="inputcolspan" value="%{6}"/>
                         </s:textfield>
                         
                        <s:select label="Module Name" required="true" requiredposition="" name="erpmsm.erpmmodule.erpmmId" headerKey="" headerValue="-- Please Select --" list="erpmmList" listKey="erpmmId" listValue="erpmmName" cssClass="textInput" >
                                    <s:param name="labelcolspan" value="%{2}"/>
                                    <s:param name="inputcolspan" value="%{6}"/>
                        </s:select>
                        
                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Order" name="erpmsm.esmOrder" title="Enter Order"  cssClass="textInput">
                                    <s:param name="labelcolspan" value="%{2}"/>
                                    <s:param name="inputcolspan" value="%{6}"/>
                        </s:textfield>            
                        
                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Hyperlink Reference" name="erpmsm.esmHref" title="Enter Path"  cssClass="textInput">
                                    <s:param name="labelcolspan" value="%{2}"/>
                                    <s:param name="inputcolspan" value="%{6}"/>
                        </s:textfield>
                        
                        <s:label value="" />
                        <s:label value="" />
                    
                        <s:submit name="btnSubmit" value="Save" action="SaveManageSubmoduleAction" >
                                  <s:param name="colspan" value="%{1}" />
                        </s:submit>
                        <s:submit name="btnSubmit" value="Browse Sub-Modules" action="Browsemodule"> 
                                  <s:param name="colspan" value="%{1}" />
                        </s:submit>
                        <s:submit name="bthReset" value="Clear"  action="ClearModule">
                                  <s:param name="colspan" value="%{1}" />
                        </s:submit>                        
                </s:form> 
                    <br>
                </div>
                <br>
            </div>  


            
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
