<%-- 
    Document   : HeaderChange
    Created on : Sep 13, 2012, 2:47:38 PM
    Author     : Amit
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
        <title>Header Image Change</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
    </head>
    <body><%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include value="/Header.jsp"/>
                <!--Header Ends Here-->
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Header Change</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart15">
                                        <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                        <fieldset class="w500p mar0a">
                                            <legend class="fbld">Change Header Image</legend>
                                            <s:form action="UpdateUnivLogo" method="POST" theme="simple" enctype="multipart/form-data" namespace="/Administrator" >
                                                <table width="100%" class="mar0a" cellpadding="2" cellspacing="0" border="0">
                                                    <tr>
                                                        <th class="w300p">
                                                            <span class="fl-l w100 mar0a tl">Select Image&nbsp;<br/>(Make Sure file name should be header and extention should be .png)</span>
                                                        </th>
                                                        <td>
                                                            <s:file name="uniLogo"/>
                                                        </td>
                                                    </tr>
                                                    <tr><td class="tc" colspan="2"><s:submit value="submit" align="center"/></td></tr>
                                                </table>
                                            </s:form>
                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
