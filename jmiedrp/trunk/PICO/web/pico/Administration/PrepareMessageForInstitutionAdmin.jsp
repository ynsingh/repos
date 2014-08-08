<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Budgettypemaster.js"></script>
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
<%--    <body class="twoColElsLtHdr"> --%>
     <body class="oneColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <!-- *********************************End Menu****************************** -->
         <%--   <div id ="mainContent" align="center">
                <br><br>
                <p align="center">SEND MESSAGE TO INSTITUTION ADMINISTRATOR</p> --%>
            <div id ="mainContent">
                <br>
                <div align="right" style="margin-right: 10px">
                    <a href="Index" >HOME</a>
                </div>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">SEND MESSAGE TO INSTITUTION ADMINISTRATOR</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <div style="border: solid 1px #000000; background: gainsboro">

            <s:form name="frmInstitutionsBrowse">                                                     
                 <s:property value="message" />
                 <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td colspan="2" align="left">
                            </tr>
<%--                            <tr>
                                <td valign="middle" class="FormContent">
                                    <s:label value="USER REGISTRATION" />
                                    <s:property  value="message" />
                                </td>
                            </tr>
--%>                            <tr>
                                <td>
                 
                 <s:textfield requiredposition="left" maxLength="100" size="50" disabled="false" readonly="true"
                              label="Message To " name="emailTo" title="EMail Id of the Institution Administrator"  cssClass="textInputRO"/>
                 <s:textfield requiredposition="left" maxLength="100" size="50" 
                              label="Subject" name="subject" title="Subject of the Mail"  cssClass="textInput"/>
                 <s:textfield requiredposition="left" maxLength="100" size="50" 
                              label="Your EMail Id" name="senderEmailId" title="Sender's EMail Id"  cssClass="textInput"/>
                 <s:textfield requiredposition="left" maxLength="100" size="50"
                              label="Your Contact No" name="senderContactNo" title="Sender's Contact No"  cssClass="textInput"/>
                 <s:textarea requiredposition="left" cols = "100" rows = "10"
                              label="Message" name="message" title="Mail Text"  cssClass="textInput"/>
                 <s:submit name="btnSubmit" value="Send Message" action="SendMessageToInstitutionAdmin" cssClass="textInput"/>
                                </td>
</tr></tbody>
                    </table>
             </s:form>
     <%--        <br><br><br>  --%>
             <br>
                </div>
            </div>
                <br>
             <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
