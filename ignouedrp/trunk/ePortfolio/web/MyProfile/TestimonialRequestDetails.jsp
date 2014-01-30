<%--
    Document   : TestimonialRequestDetails
    Created on : Sep 17, 2012, 5:11:36 PM
    Author     : Vinay
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

        <title>My Testimonials</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
    </head>
    <body>
        <%  final Logger logger = Logger.getLogger(this.getClass());
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
                            <div class="my_account_bg">My Testimonials</div>
                            <div class="w100 fl-l mart10">
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > My Testimonial
                                </div>
                                <% if (role.contains("faculty")) {%>
                                <s:iterator value="ReqsentList" status="stat">
                                    <div class="marr15 fl-r mart10">
                                        || <s:a action="StdTestiReq">Inbox</s:a> || <a href="createTestifor?testiReqId=<s:property value="testiReqId"/>">Create Testimonial</a> || Sent Testimonials ||
                                        </div>
                                        <div class="tab_btn_1 mart5"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l mart15">
                                        <fieldset class="w450p mar0a">
                                            <table width="100%" align="center" class="mar0a" cellpadding="4" cellspacing="0">
                                                <legend><strong>Student Request Details for Testimonial</strong></legend>
                                                <tr>
                                                    <th align="left">Requestor :</th>
                                                    <td><s:property value="userByTestiRequestor.fname"/> <s:property value="userByTestiRequestor.lname"/></td>
                                                </tr>
                                                <tr>
                                                    <th align="left">Request Date :</th>
                                                    <td><s:property value="testiReqDate"/></td>
                                                </tr>
                                                <tr>
                                                    <th align="left">Type of Testimonial :</th>
                                                    <td><s:property value="testiType"/></td>
                                                </tr>
                                                <tr>
                                                    <th align="left" class="italic">Supported Documents Received (Optional) :</th>
                                                    <td><s:property value="testiReqFile"/></td>
                                                </tr>
                                                <tr>
                                                    <th align="left" valign="top">Message/Purpose/Description :</th>
                                                    <td><s:property value="testiReqMessage"/></td>
                                                </tr>
                                            </table>
                                            <fieldset>
                                                <table width="80%" align="center" class="mar0a" cellpadding="4" cellspacing="0">
                                                    <legend><strong>To Whom the Testimonial to be sent</strong></legend>

                                                    <tr>
                                                        <th align="left">Name :</th>
                                                        <td><s:property value="testiForName"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Email :</th>
                                                        <td><s:property value="testiForEmail"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Address :</th>
                                                        <td><s:property value="testiForAddress"/></td>
                                                    </tr>
                                                </table>
                                            </fieldset> </fieldset>
                                    </div>
                                </s:iterator>
                                <% } else if (role.contains("student")) {%>
                                || <a href="TestimonialRequestForm.jsp">New Request || <s:a action="TestimonialSent"> Inbox</s:a> || <s:a action="sentReq">Sent Request</s:a> ||<s:a action="DraftReq">Draft</s:a> ||
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l mart10">
                                        <s:if test="%{!ReqsentList.isEmpty()}">
                                        </s:if>
                                        <s:else>
                                        </s:else>
                                    </div>
                                    <% }%>
                            </div>
                        </div>
                        <!--Right box End Here-->
                    </div>
                </div>
                <!--Middle Section Ends Here-->
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>