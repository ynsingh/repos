
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>                
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">RECOVER PASSWORD</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">


                    <s:form name="frmForgotPassword" action="RecoverPassword">
                        <s:hidden name="erpmuId" />
                        <table border="0" cellpadding="4" cellspacing="0" align="center">
                            <tbody>
                                <tr>
                                    <s:textfield requiredposition="left" maxLength="50" size="50"
                                                 label="User Name (E-Mail Address)" name="erpmusers.erpmuName" title="Enter User Name (E-Mail Address)" onchange="RetrieveSQ('RecoverPassword_erpmusers_erpmuName','RecoverPassword_erpmusersdob', 'RecoverPassword_erpmusers_erpmuSecretQuestion');" />
                                    <s:textfield requiredposition="left" maxLength="11" size="11" name="erpmusersdob" label="Your Date of Birth [Format:yyyy-mm-dd]"
                                                 onchange="RetrieveSQ('RecoverPassword_erpmusers_erpmuName','RecoverPassword_erpmusersdob', 'RecoverPassword_erpmusers_erpmuSecretQuestion');" />
                                    <s:textfield name="erpmusers.erpmuSecretQuestion" requiredposition="left" maxLength="100" size="100"
                                                 label="Your 'Secret' Question is "  readonly="true"/>
                                    <s:textfield requiredposition="left" maxLength="100" size="100"
                                                 label="Answer to Secret Question" name="erpmusers.erpmuSecretAnswer" title="Enter your answer to secret question" />
                                </tr>
                                <tr>
                                    <td>
                                        <p align="right"> <s:submit theme="simple" align="right" name="btnSubmit" value="Recover password" /></p>
                                    </td>
                                    <td>
                                        <s:reset theme="simple" name="bthReset" id="btnReset" value="Clear" action = "ClearForgotPassword"/>
                                        <s:submit theme="simple" action="Index"  name="login" value="Go to Login Page"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                    </s:form>
                    <s:actionerror />
                </div>
                <div id="footer">
                    <jsp:include page="footer.jsp" flush="true"></jsp:include>
                </div>
            </div>
    </body>
</html>