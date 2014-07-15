<%-- 
    Document   : SocialInfoEdit
    Created on : Sep 16, 2011, 10:42:51 AM
Author     : IGNOU Team
Version      : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Social Networking</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
             logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include  value="/Header.jsp"/>
                <!--Header Ends Here-->
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include value="/Left-Nevigation.jsp"/>
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Edit Social Networks</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyConnection.jsp"/>">My Connections</a>&nbsp;>
                                            <s:a href="ShowSocialInfo">Social Networking</s:a>
                                            > Edit Social Information </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w300p mar0a">
                                                <legend><strong>Edit Social Information</strong></legend>
                                                <s:form action="updateSocialInfo" method="post" theme="simple">
                                                    <table width="95%" class="mar0a" cellpadding="0" border="0" cellspacing="2">
                                                        <s:iterator value="socialListList" var="ProfileSocial">
                                                            <s:hidden name="socialInfoId"/>
                                                            <tr>
                                                                <td width="35%">GTalk</td>
                                                                <td width="60%"><s:textfield name="gtalk" label="GTalk"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Skype</td>
                                                                <td><s:textfield name="skype" label="Skype"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>MSN</td>
                                                                <td><s:textfield name="msn" label="MSN"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>AIM</td>
                                                                <td><s:textfield name="aim" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Yahoo</td>
                                                                <td><s:textfield name="yahoo" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Facebook</td>
                                                                <td><s:textfield name="facebook" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Orkut</td>
                                                                <td><s:textfield name="orkut" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Twitter</td>
                                                                <td><s:textfield name="twitter" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Blog</td>
                                                                <td><s:textfield name="blog" /></td>
                                                            </tr>
                                                        </s:iterator>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save Changes" />
                                                                <s:reset value="Cancel" onClick="history.go(-1);" /></td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--Right box End Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
