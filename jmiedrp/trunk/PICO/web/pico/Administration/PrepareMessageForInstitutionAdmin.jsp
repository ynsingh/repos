<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <br><br>
                <p align="center">SEND MESSAGE TO INSTITUTION ADMINISTRATOR</p>
                <br>
            <s:form name="frmInstitutionsBrowse">                                                     
                 <s:property value="message" />
                 
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
             </s:form>
             <br><br><br>
            </div>
             <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>