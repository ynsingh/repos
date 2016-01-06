<%-- 
    Document   : ManageSubmodule
    Created on : December 17, 2015
    Author     : Jaivir Singh
    email      : jaivirpal@gmail.com
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
        <%-- <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script> --%>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Jaivir Singh, IIT Kanpur">
        <meta name="email" content="jaivirpal@gmail.com">
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
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Change Password')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <div style ="background-color: #215dc6;">
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                <s:form name="frmChpsw" action="ListUserAction"  theme="qxhtml">
		 <s:hidden name="erpmusers.erpmuId" />  
		 <s:hidden name="erpmusers.erpmuActive" />  
		 <s:hidden name="erpmusers.erpmuVerifiedBy" />  
		 <s:hidden name="erpmusers.erpmuFullName" />  
                    <br>
                        <s:textfield requiredposition="left" maxLength="50" size="50"
                                                 key="Change Password For" name="erpmusers.erpmuName" title="Change Password For"  cssClass="textInput" readonly="true">
                                    <s:param name="labelcolspan" value="%{2}"/>
                                    <s:param name="inputcolspan" value="%{6}"/>  
                         </s:textfield>
                        <s:password requiredposition="left" maxLength="50" size="50"
                                                 key="New Password" name="erpmusers.erpmuPassword" title="New Password"  cssClass="textInput">
                                    <s:param name="labelcolspan" value="%{2}"/>
                                    <s:param name="inputcolspan" value="%{6}"/>  
                        </s:password>

                        <s:label value="" />
                        <s:label value="" /> 

                        <s:submit name="btnSubmit" key="Administration.Save" action="UpdatePsword" >
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
