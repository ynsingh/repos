<%-- 
    Document   : TestimonialIndex
    Created on : Sep 14, 2012, 3:50:34 PM
    Author     : Vinay
--%>

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
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%  String role = session.getAttribute("role").toString();
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
                            <div class="my_account_bg"><s:property value="title"/></div>
                            <div class="w100 fl-l mart10">
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > My Testimonial 
                                </div>
                                <% if (role.contains("faculty")) {%> 
                                <div class="marr15 fl-r mart10">
                                    || <s:a action="StdTestiReq">Inbox</s:a> || <s:a action="MailedTestimonial">Sent</s:a> || <s:a action="FacultyDraftTesti">Draft</s:a> ||
                                </div>
                                <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                <div class="w100 fl-l mart5">
                                    <s:if test="%{!ReqsentList.isEmpty()}">
                                        <table width="97%" class="mar0a" cellpadding="4" border="1" cellspacing="0">
                                            <tr>
                                                <th width="20px;">S.No</th>
                                                <th width="200px;">Request From</th>
                                                <th width="64px;">Purpose</th>
                                                <th>Message</th>
                                                <th width="57px;">Request Date</th>
                                            </tr>                                                    
                                            <s:iterator value="ReqsentList" status="stat">
                                                <s:if test="readStatus==0">
                                                    <tr class="fbld">
                                                        <td><s:property value="#stat.count"/></td>
                                                        <td><a href="readReq?testiReqId=<s:property value="testiReqId"/>">
                                                                <s:property value="userByTestiRequestor.fname"/> <s:property value="userByTestiRequestor.lname"/></a>
                                                        </td>
                                                        <td><a href="readReq?testiReqId=<s:property value="testiReqId"/>"><s:property value="testiType"/></a></td>
                                                        <td><a href="readReq?testiReqId=<s:property value="testiReqId"/>"><s:property value="testiReqMessage"/></a></td>
                                                        <td><a href="readReq?testiReqId=<s:property value="testiReqId"/>"><s:property value="testiReqDate"/></a></td>
                                                    </tr>
                                                </s:if>
                                                <s:if test="readStatus==1">
                                                    <tr>  <td><s:property value="#stat.count"/></td>
                                                        <td><a href="readReq?testiReqId=<s:property value="testiReqId"/>">
                                                                <s:property value="userByTestiRequestor.fname"/> <s:property value="userByTestiRequestor.lname"/></a>
                                                        </td>
                                                        <td><a href="readReq?testiReqId=<s:property value="testiReqId"/>"><s:property value="testiType"/></a></td>
                                                        <td><a href="readReq?testiReqId=<s:property value="testiReqId"/>"><s:property value="testiReqMessage"/></a></td>
                                                        <td><a href="readReq?testiReqId=<s:property value="testiReqId"/>"><s:property value="testiReqDate"/></a></td>
                                                    </tr>
                                                </s:if>
                                            </s:iterator>
                                        </table>
                                    </s:if>
                                    <s:else>

                                    </s:else>
                                </div>
                                <% } else if (role.contains("student")) {%>
                                <div class="marr15 fl-r mart10">
                                    || <a href="TestimonialRequestForm.jsp">New Request</a> || <s:a action="TestimonialSent"> Inbox</s:a> || <s:a action="sentReq">Sent Request</s:a> ||<s:a action="DraftReq">Draft</s:a> ||
                                </div>
                                <div class="w100 fl-l mart10">
                                    <table width="97%" class="mar0a" cellpadding="4" border="1" cellspacing="0">
                                        <tr>
                                            <th width="20px;">S.No</th>
                                            <th width="115px;">Request To</th>
                                            <th width="64px;">Purpose</th>
                                            <th>Message</th>
                                            <th width="80px;">Request Date</th>
                                        </tr>                                                    
                                        <s:if test="%{!ReqsentList.isEmpty()}">
                                            <s:iterator value="ReqsentList" status="stat">
                                                <tr>
                                                    <td><s:property value="#stat.count"/></td>
                                                    <td>
                                                        <s:property value="userByTestiRequestor.fname"/> <s:property value="userByTestiRequestor.lname"/><br/>
                                                    </td>
                                                    <td><s:property value="testiType"/></td>
                                                    <td><s:property value="testiReqMessage"/></td>
                                                    <td><s:property value="testiReqDate"/></td>
                                                </tr>
                                            </s:iterator>
                                        </s:if>
                                        <s:else>
                                        </s:else>
                                    </table>
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
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