<%--
       I18n By    : Mohd. Manauwar Alam
                  : Jan 2014
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- Modified by Tanvir Ahmed on 11-09-2013 for adding 'List of Tenders' Link -->

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
        <meta name="co-author" content="Tanvir Ahmed, Jamia Millia Islamia, tanvirahmed74@gmail.com">
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
		<div id ="mainContent" align="center">   <%-- by Jaivir   --%>
                <table border="0" width="100%">
                    <tbody>
                        <tr>
                          <%--  <td width="5%"></td>  --%>
                          <%--  <td width="30%" valign="top" height="80%" class="textInput">  --%>
                             <%--   <b><s:property value="getText('Administration.ImpLinks')"/></b>  
                                <b>What is Purchase and Inventory Control System</b>
                            </td>
--%>
                            <td width="30%" valign="top" height="80%" class="textInput">  
                                <b><s:property value="getText('Administration.PortalNews')"/></b>
                            </td>

                            <td>
                            </td>
                       <%--     <td width="30%" bgcolor="gainsboro"> </td> --%>
                        </tr>
                        <tr>
                         <%--   <td width="1%"></td> --%>
                       <%--     <td width="35%" valign="top" height="80%" class="textInput">  
                            <td width="30%" valign="top" height="80%" class="textInput">
                                <br><br>       --%>
                        <%--        <a href="http://saksat.as.in"><s:property value="getText('Administration.aboutEDRP')"/></a><br>
                                <a href="http://202.141.40.218/"><s:property value="getText('Administration.EDRPKanpurServices')"/></a><br>
                                <a href="http://cserp1.amu.ac.in:8080/LibMS/"><s:property value="getText('Administration.LibManageSystemAMU')"/></a><br>
                                <a href="http://cserp1.amu.ac.in:8080/EMS/"><s:property value="getText('Administration.ElectionMgmtSysAMU')"/></a><br>
                                <a href="http://erp.amrita.ac.in:8080/gms"><s:property value="getText('Administration.GrantMgmtSys')"/></a><br>
                                <a href="http://erp.amrita.ac.in:8080/StudentActivityVisualization"><s:property value="getText('Administration.DispInfVisualiSys')"/></a><br>
                                <a href="http://erp.amrita.ac.in:8080/nfes/"><s:property value="getText('Administration.NationalFcltyExpertSys')"/></a><br>
                                <a href="http://erp.amrita.ac.in:9090/aell/"><s:property value="getText('Administration.EngLanLb')"/></a><br>
                                <a href="http://180.149.53.46:8084/CMS"><s:property value="getText('Administration.OnlineAddSys')"/></a><br>
                                <a href="http://14.139.40.226:8084/ePortfolio"><s:property value="getText('Administration.EPortfolio')"/></a>    --%>
			<%--	The Purchase and Inventory Control system offers comprehensive 
				reporting capabilities to keep you on top of inventory status. 
				It can help you bring about the creation of new or improved purchasing 
				policies, sales policies, pricing methods and even enhanced customer service.
				By leveraging Sage BusinessWorks, you have the tools to create an inventory system
				with the depth to meet needs of your company for years to come. 
                                <br><br> 
                            </td> --%>


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
<%--
                <br><br><br>
                <s:url action="ListOfTendersAction.action" id="ListOfTenders"></s:url>
                <a href='<s:property value="ListOfTenders"/>'><s:property value="getText('Administration.TenderList')"/> </a>
               </td> --%>
              <%--  <td>
                    <p align="center"><img align="center" src="../images/mhrd.jpg" border="0" style="cursor:pointer;"/></p>
                </td> --%>
                <td width="20%">
                    <s:url id="localeEN" namespace="/Administration" action="locale" >
                    <s:param name="request_locale" >en</s:param>
                    </s:url>
                     <s:url id="localeHI" namespace="/Administration" action="locale">
                    <s:param name="request_locale">hi</s:param>
                    </s:url>
                    <s:a href="%{localeEN}" >English</s:a>
                    <s:a href="%{localeHI}" >Hindi</s:a>
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
                                        <s:textfield required="true" requiredposition="left" maxLength="50" 
                                                     name="erpmuser.erpmuName" title="Email" key="Administration.email"/>

                                        <s:password  required="true" requiredposition="left" maxLength="25" 
                                                     name="erpmuser.erpmuPassword" title="Enter Password" key="Administration.password" />
                                        <s:submit name="login"  key="Administration.LoginBt" />
                               <%--         <s:submit name="rmtLoin"  action="brihaspatiLogin" key="Administration.LoginBrahaspatiBt"/>  --%>
                                    </td>
                                </tr>
                                <tr>
                                 <td class="textInput"><s:url action="ForgotPassword.action" id="NavigateToURL" ></s:url>
                                  <a href='<s:property value="NavigateToURL"/>'><s:property value="getText('Administration.ForgotPassword')"/></a></td>
                                </tr>
				<tr>
                                   <td class="textInput"><s:url action="AddUser" id="NavigateToURL"></s:url>
                                  <a href='<s:property value="NavigateToURL"/>'><s:property value="getText('Administration.NewUser')"/></a></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="textInput">
                                        <s:url action="AdminRegistration.action" id="NavigateToURL"></s:url>
                                        <a href='<s:property value="NavigateToURL"/>'><s:property value="getText('Administration.InstAdminRegistration')"/></a></td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="textInput">
                                        <s:url action="ViewRegisteredInstitutions.action" id="NavigateToURL"></s:url>
                                        <a href='<s:property value="NavigateToURL"/>'><s:property value="getText('Administration.RegisteredInstitution')"/></a></td>
                                </tr>
				<tr><td colspan="2" class="textInput">
					<s:url action="ListOfTendersAction.action" id="ListOfTenders"></s:url>
                			<a href='<s:property value="ListOfTenders"/>'><s:property value="getText('Administration.TenderList')"/> </a>
				</td></tr>
                            </tbody>
                        </table>
                    </s:form>

                <%--    <s:url id="localeEN" namespace="/Administration" action="locale" >
                    <s:param name="request_locale" >en</s:param>
                    </s:url>
                     <s:url id="localeHI" namespace="/Administration" action="locale">
                    <s:param name="request_locale">hi</s:param>
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
                     --%>


                <%--    <s:a href="%{localeEN}" >English</s:a>
                    <s:a href="%{localeHI}" >Hindi</s:a>
                    <s:a href="%{localezhCN}" >Chinese</s:a>
                    <s:a href="%{localeDE}" >German</s:a>
                    <s:a href="%{localeFR}" >France</s:a>
                    --%>
                    </td>
                    </tr>
                    </tbody>
                </table>
            </div> <%-- by Jaivir   --%>
                <br><br>
        <%--    <s:label label="Application Last Updated On 02-06-2014" labelSeparator=""></s:label>  --%>
                <br>
                <hr>
                <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
