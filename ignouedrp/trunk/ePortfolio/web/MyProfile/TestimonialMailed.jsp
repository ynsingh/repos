<%-- 
    Document   : TestimonialMailed
    Created on : 28 Sep, 2012, 5:36:39 PM
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
                                    <div class="w100 fl-l mart5">
                                        <table width="97%" class="mar0a" cellpadding="4" border="1" cellspacing="0">
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Requestor</th>
                                                <th>Sent To</th>
                                                <th>Created Date</th>
                                            <s:if test="sent==1">
                                                <th>Sent Date</th>
                                            </s:if>
                                        </tr>
                                        <s:iterator value="ReqsentList" status="stat" var="testiModel">
                                            <tr>
                                                <td valign="top"><s:property value="#stat.count"/></td>
                                                <td valign="top" align="left"><s:property value="userByTestiRequestor.fname"/> <s:property value="userByTestiRequestor.lname"/>
                                                    <s:if test="sent==1"> 
                                                        <br/><br/>
                                                        <sj:dialog 
                                                            id="%{#stat.count}" 
                                                            autoOpen="false" 
                                                            modal="true" 
                                                            width="550"
                                                            height="550"
                                                            >
                                                            <s:property value="report" escape="false"/>
                                                        </sj:dialog>
                                                        <sj:a 
                                                            openDialog="%{#stat.count}" 
                                                            button="false"
                                                            cssClass="cursor"
                                                            >
                                                            Testimonial
                                                        </sj:a>
                                                    </s:if>
                                                    <s:if test="sent==0">
                                                        <span class="tr dis_b">
                                                            <a href="EditDraftTesti?testiReqId=<s:property value="testiReqId"/>">Edit</a>
                                                        </span>
                                                    </s:if>
                                                </td>
                                                <td valign="top" align="left">
                                                    <strong><s:property value="testiForName"/></strong><br/>
                                                    <s:property value="testiForAddress"/><br/>
                                                    <s:property value="testiForEmail"/>
                                                </td>
                                                <td valign="top"><s:property value="createDate"/></td>
                                                <s:if test="sent==1">
                                                    <td valign="top"><s:date name="sentDate" format="MMM dd, yyyy"/></td>   
                                                </s:if>
                                            </tr>
                                        </s:iterator>
                                    </table>
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                </div>   
                                <% } else if (role.contains("student")) {%>
                                <div class="marr15 fl-r mart10">
                                    || <a href="TestimonialRequestForm.jsp">New Request</a> || <s:a action="TestimonialSent"> Inbox</s:a> || <s:a action="sentReq">Sent Request</s:a> ||<s:a action="DraftReq">Draft</s:a> ||
                                    </div>
                                    <div class="w100 fl-l mart5">
                                        <table width="97%" class="mar0a" cellpadding="4" border="1" cellspacing="0">
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Sender</th>
                                                <th>Sent To</th>
                                                <th>Created Date</th>
                                                <th>Sent Date</th>
                                            </tr>
                                        <s:iterator value="ReqsentList" status="stat" var="testiModel">
                                            <tr>
                                                <td valign="top"><s:property value="#stat.count"/></td>
                                                <td valign="top" align="left">
                                                    <s:property value="userByTestiRequestor.fname"/> <s:property value="userByTestiRequestor.lname"/><br/>
                                                    <s:if test="report!=null">
                                                        <sj:dialog 
                                                            id="%{#stat.count}" 
                                                            autoOpen="false" 
                                                            modal="true" 
                                                            width="550"
                                                            height="550"
                                                            >
                                                            <s:property value="report" escape="false"/>
                                                        </sj:dialog>
                                                        <sj:a 
                                                            openDialog="%{#stat.count}" 
                                                            button="false"
                                                            cssClass="cursor"
                                                            >
                                                            Testimonial
                                                        </sj:a>
                                                    </s:if>    
                                                </td>
                                                <td valign="top" align="left">
                                                    <strong><s:property value="testiForName"/></strong><br/>
                                                    <s:property value="testiForAddress"/><br/>
                                                    <s:property value="testiForEmail"/>
                                                </td>
                                                <td valign="top"><s:property value="createDate"/></td>
                                                <td valign="top"><s:property value="sentDate"/></td>   
                                            </tr>
                                        </s:iterator>
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