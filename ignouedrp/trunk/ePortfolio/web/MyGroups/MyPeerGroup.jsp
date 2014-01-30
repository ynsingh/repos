<%--
Document : MyPeerGroup
Created on : Oct 18, 2012, 4:25:59 PM
Author : Vinay
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:property value="title"/></title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/jquery.jcarousel.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/pagination.js"/>"></script>
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
                        <s:include value="/Left-Nevigation.jsp"/>
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg"><s:property value="title"/></div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<s:property value="title"/>
                                    </div>
                                    <div class="w100 fl-l mart15">
                                        <div class="faculty">
                                            <table width="95%" class="tablepaging" id="tablepaging" cellspacing="0" cellpadding="5" border="1">
                                                <thead>
                                                    <tr>
                                                        <td width="5%">S. No.</td>
                                                        <td width="20%">Name</td>
                                                        <!--   <td width="25%">Office</td>
                                                           <td width="20%">Phone</td>-->
                                                        <td width="25%">Email</td>
                                                    </tr>
                                                </thead>
                                                <s:iterator value="PeerGroupListList" status="stat">
                                                    <tr>
                                                        <td class="fbld tc" width="5%" valign="top"><s:property value="#stat.count"/></td>
                                                        <td width="20%" class="fbld" valign="top">
                                                            <a href="ProfileDetails?emailId=<s:property value="emailId"/>">
                                                                <span class="w20p fl-l"><img src="<s:url action='ImageAction'/>?userId=<s:property value="emailId"/>" width="20px" height="20px"/></span>
                                                                <span class="w100p fl-l marl5"><s:property value="fname"/>&nbsp;<s:property value="lname"/></span>
                                                            </a>
                                                        </td>
                                                        <!--     <td width="25%" valign="top">
                                                        <s:iterator value="profileContacts">
                                                            <s:property value="address1"/>, <s:property value="address2"/><br/>
                                                            <s:property value="city"/>, <s:property value="state"/><br/>
                                                            <s:property value="country"/>, <s:property value="pin"/>
                                                        </s:iterator>
                                                    </td>
                                                     <td width="20%" valign="top">
                                                        <s:iterator value="profileContacts">
                                                            Res No.: <s:property value="HTelephone"/><br/>
                                                            Off No.: <s:property value="OTelephone"/><br/>
                                                            Mob No.: <s:property value="mobileNo"/><br/>
                                                            Fax No.: <s:property value="faxNo"/>
                                                        </s:iterator>
                                                    </td>
                                                        -->
                                                        <td width="25%" class="fbld" valign="top">
                                                            <a href="mailto:<s:property value="emailId"/>, <s:property value="email1"/>">
                                                                <s:property value="emailId"/><br/>
                                                                <s:iterator value="profileContacts">
                                                                    <s:property value="email1"/><br/>
                                                                    <s:property value="email2"/><br/>
                                                                    <s:property value="email3" />
                                                                </s:iterator>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                                <s:iterator value="MyFacultyList" status="stat">
                                                    <tr>
                                                        <td class="fbld tc" width="5%" valign="top"><s:property value="#stat.count"/></td>
                                                        <td width="20%" class="fbld" valign="top">
                                                            <a href="ProfileDetails.jsp">
                                                                <span class="wau fl-l"><img src="<s:url action='ImageAction'/>?userId=<s:property value="emailId"/>" width="20px" height="20px"/></span>
                                                                <span class="wau fl-l marl5"><s:property value="fname"/>&nbsp;<s:property value="lname"/></span>
                                                            </a>
                                                        </td>
                                                        <!--       <td width="25%" valign="top">
                                                        <s:iterator value="profileContacts">
                                                            <s:property value="address1"/>, <s:property value="address2"/><br/>
                                                            <s:property value="city"/>, <s:property value="state"/><br/>
                                                            <s:property value="country"/>, <s:property value="pin"/>
                                                        </s:iterator>
                                                    </td>
                                                    <td width="20%" valign="top">
                                                        <s:iterator value="profileContacts">
                                                            Res No.: <s:property value="HTelephone"/><br/>
                                                            Off No.: <s:property value="OTelephone"/><br/>
                                                            Mob No.: <s:property value="mobileNo"/><br/>
                                                            Fax No.: <s:property value="faxNo"/>
                                                        </s:iterator>
                                                    </td> -->
                                                        <td width="25%" class="fbld" valign="top">
                                                            <a href="mailto:<s:property value="emailId"/>, <s:property value="email1"/>">
                                                                <s:property value="emailId"/><br/>
                                                                <s:iterator value="profileContacts">
                                                                    <s:property value="email1"/><br/>
                                                                    <s:property value="email2"/><br/>
                                                                    <s:property value="email3" />
                                                                </s:iterator></a>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                            <div id="pageNavPosition" style="padding-top: 20px" align="center"> </div>
                                        </div>
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
        <script type="text/javascript">
            var pager = new Pager('tablepaging', 10);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
        </script>
    </body>
</html>
