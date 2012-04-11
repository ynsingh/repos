
<%@ page contentType="text/html;charset=UTF-8" %>

<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
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
            <table border="0" width="100%">
                <tbody>
                    <tr>
                        <td width="5%"></td>
                        <td width="30%" valign="top" height="80%" class="textInput">
                            <br><br>
                            <a href="http://saksat.as.in">About ERP Mission Project</a><br>
                            <a href="http://saksat.as.in">Partner Institutions</a><br>
                            Other Modules of Project<br>
                            <a href="http://saksat.as.in">Online Admissions System</a><br>
                            <a href="http://saksat.as.in">Project Management System</a><br>
                            <a href="http://saksat.as.in">Library Management System</a><br>
                            <a href="http://saksat.as.in">Timetable Management System</a>
                            <br><br>
                        </td>
                        <td>
                            <p align="center"><img align="center" src="../images/MHRD.JPG" border="0" style="cursor:pointer;"/></p>
                        </td>
                <script type='text/javascript'>

                    function getCurrentLanguage(lang)    {

                        var msg = $.ajax({
                            url:"/pico/ajax/setLanguage.action?searchValue=" + lang,
                            async:false
                        }).responseText;
                        alert(msg);
                    }

                </script>

                <td width="30%">
                    <s:form id="frmLogin" name="frmLogin" method="post" action="Login"  >
                        <table border="0" cellpadding="4" cellspacing="0" align="center">
                            <tbody>
                                <tr>
                                    <td colspan="2" align="right" class="textInput">
                                        <br>
                                        <s:property value="message"/>
                                </tr>
                                <tr>
                                    <td valign="middle" class="FormContent">
                                        <%--<s:select label="Language" name="language" headerKey="" headerValue="-- Please Select --" list="LangList" listKey="langId" listValue="langName" cssClass="textInput"  onchange="getCurrentLanguage(this.options[this.selectedIndex].text)"/>--%>
                                        <s:textfield required="true" requiredposition="left" maxLength="50"
                                                     name="erpmuser.erpmuName" title="Enter Username"   key="global.username"/>

                                        <s:password  required="true" requiredposition="left" maxLength="25"
                                                    name="erpmuser.erpmuPassword" title="Enter Password" key="global.password" />
                                        <s:submit name="login" key="global.submit" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="textInput"><s:url action="ForgotPassword.action" id="NavigateToURL" ></s:url>
                                        <a href='<s:property value="NavigateToURL"/>'>Forgot Password</a></td>
                                    <td class="textInput"><s:url action="AddUser" id="NavigateToURL"></s:url>
                                        <a href='<s:property value="NavigateToURL"/>'>New Account</a></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="textInput">
                                    <s:url action="AdminRegistration.action" id="NavigateToURL"></s:url>
                                        <a href='<s:property value="NavigateToURL"/>'>Institution Administrator Registration</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </s:form>

                    <%-- <s:url action="BrowseInstitutions" id="NavigatetoURL" ></s:url>
                            <a href='<s:property value="NavigatetoURL"/>'>Browse Institutions</a>--%>

                    <s:url id="localeEN" namespace="/Administration" action="locale" >
                        <s:param name="request_locale" >en</s:param>
                    </s:url>
                    <s:url id="localezhCN" namespace="/Administration" action="locale" >
                        <s:param name="request_locale"   >zh_CN</s:param>
                    </s:url>
                    <s:url id="localeDE" namespace="/Administration" action="locale" >
                        <s:param name="request_locale"  >de</s:param>
                    </s:url>
                    <s:url id="localeFR" namespace="/Administration" action="locale"  >
                        <s:param name="request_locale" >fr</s:param>
                    </s:url>

                    <s:a href="%{localeEN}" >English</s:a>
                    <s:a href="%{localezhCN}" >Chinese</s:a>
                    <s:a href="%{localeDE}" >German</s:a>
                    <s:a href="%{localeFR}" >France</s:a>

                </td>
                </tbody>
                <br><br><br>
            </table>
        <div id="footer">
            <jsp:include page="footer.jsp" flush="true"></jsp:include>
        </div>
                </div>
    </body>
</html>
