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
            <hr>
            <br><br><br>
            <script type='text/javascript'>

                    function getCurrentLanguage(lang)    {

                        var msg = $.ajax({
                            url:"/pico/ajax/setLanguage.action?searchValue=" + lang,
                            async:false
                        }).responseText;
                        alert(msg);
                    }

            </script>

	         <div style=" height:400px; margin-top:50px; margin-left:250px">
            <table border="0" width="80%" >
                <tbody>
                    <tr>
                        <td width="5%"></td>
                        <td width="30%" valign="top" height="80%" class="textInput">
                            <b>Important Links</b>
                        </td>

                        <td width="30%" valign="top" height="80%" class="textInput">
                            <b>Portal News</b>
                        </td>

                        <td>
                        </td>
                </tr>
                    <tr>
                        <td width="1%"></td>
                        <td width="35%" valign="top" height="80%" class="textInput">
                            <br><br>
                            <a href="http://saksat.as.in">About ERP Mission Project</a><br>                                                      
                            <a href="http://202.141.40.218/">EdRP Services (IIT Kanpur)</a><br>
                            <a href="http://cserp1.amu.ac.in:8080/LibMS/">Library Management System (LibMS) (AMU)</a><br>
                            <a href="http://cserp1.amu.ac.in:8080/EMS/">Election Management System (AMU)</a><br>
                            <a href="http://erp.amrita.ac.in:8080/gms">Grants Management System (AVV)</a><br>
                            <a href="http://erp.amrita.ac.in:8080/StudentActivityVisualization">Display Information and Visualtization System (AVV)</a><br>
                            <a href="http://erp.amrita.ac.in:8080/nfes/">National Faculty Expert System (AVV)</a><br>
                            <a href="http://erp.amrita.ac.in:9090/aell/">English Language Lab (AVV)</a><br>
                            <a href="http://180.149.53.46:8084/CMS">Online Admissions System (DEI)</a><br>
                            <a href="http://14.139.40.226:8084/ePortfolio">E-Portfolio Management System (IGNOU)</a>
                            <br><br>
                        </td>


                        <td width="30%" valign="top">
                            <br><br>
                            <marquee  behavior="scroll" direction="up" style="color:#C11111;" scrollamount="2" onmouseover="this.stop();" onmouseout="this.start();">
                                <s:iterator value="showingNewsinPageList" status="record">
                                    <s:if test="%{#record.index%2 == 0}">
                                        <p style="font-size:8pt; font-weight:bolder; color: #0000FF;" > <s:property value="newsText"/>
                                    </s:if>
                                    <s:else>
                                        <p style="font-size:8pt; font-weight:bolder; color:#C11111;" > <s:property value="newsText"/></p>
                                    </s:else>
                                </s:iterator>
                            </marquee>
                        <br><br>
                     </td>
                <td width="20%">
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
                                        <s:textfield required="true" requiredposition="left" maxLength="50" label="Username"
                                                     name="erpmuser.erpmuName" title="Username" key="global.username"/>

                                        <s:password  required="true" requiredposition="left" maxLength="25" label="Password"
                                                    name="erpmuser.erpmuPassword" title="Enter Password" key="global.password" />
                                        <s:submit name="login" value="Login" key="global.login" />
                                        <s:submit name="rmtLoin" value="Brihaspati Open ID" action="brihaspatiLogin"/>
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
                                <tr>
                                    <td colspan="2" class="textInput">
                                    <s:url action="ViewRegisteredInstitutions.action" id="NavigateToURL"></s:url>
                                        <a href='<s:property value="NavigateToURL"/>'>Registered Institutions </a></td>
                                </tr>                                
                            </tbody>
                        </table>
                    </s:form>
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
                    &nbsp &nbsp &nbsp&nbsp <s:a href="%{localeEN}" >English</s:a>
                    <s:a href="%{localezhCN}" >Chinese</s:a>
                    <s:a href="%{localeDE}" >German</s:a>
                    <s:a href="%{localeFR}" >France</s:a>
                </td>
               </tr>               
           </tbody>                
            </table>
		</div>
            <br><br>
            <br>
            <hr>
        <div id="footer">
            <jsp:include page="footer.jsp" flush="true"></jsp:include>
        </div>
                </div>
    </body>
</html>
