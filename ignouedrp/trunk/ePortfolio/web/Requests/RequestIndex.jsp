<%-- 
    Document   : RequestIndex
    Created on : Sep 7, 2012, 5:34:04 PM
    Author     : Vinay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>User Request</title>
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
        <%
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
                            <div class="my_account_bg"><s:property value="Headtitle"/></div>
                            <div class="w100 fl-l mart10">
                                <%
                                    if (role.equals("admin")) {
                                %>
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;Requests
                                </div>
                                <div class="marr15 fl-r mart10">
                                    || <s:a action="UserReqList">New Request</s:a> || <s:a action="UserReqUpdated">Processed Requests</s:a> ||
                                    </div>
                                    <div class="w100 fl-l mart15">
                                        <table width="95%" class="mar0a" cellpadding="4" cellspacing="0" border="1">
                                            <tr>
                                                <th width="5%" align="left">S.No.</th>
                                                <th width="18%">Requestor</th>
                                                <th width="18%">Type</th>
                                                <th width="20%">Purpose</th>
                                                <th width="22%">New Record</th>
                                                <th width="15%">Request Date</th>
                                            </tr>
                                        <s:iterator value="UsrReqList" status="stat">
                                            <s:if test="status==0">
                                                <tr class="fbld">
                                                    <td><s:property value="%{#stat.count}"/></td>
                                                    <td>
                                                        <a href="EditUserPerInfo?requestId=<s:property value="requestId"/>&amp;requestorId=<s:property value="user.emailId"/>">
                                                            <s:property value="user.fname"/>&nbsp;<s:property value="user.lname"/>
                                                        </a>
                                                    </td>
                                                    <td><a href="EditUserPerInfo?requestId=<s:property value="requestId"/>&amp;requestorId=<s:property value="requestorId"/>">
                                                            <s:property value="requestType"/>
                                                        </a>
                                                    </td>
                                                    <td><a href="EditUserPerInfo?requestId=<s:property value="requestId"/>&amp;requestorId=<s:property value="requestorId"/>">
                                                            <s:property value="reason"/>
                                                        </a>
                                                    </td>
                                                    <td><a href="EditUserPerInfo?requestId=<s:property value="requestId"/>&amp;requestorId=<s:property value="requestorId"/>">
                                                            <s:property value="newRecord"/>
                                                        </a>
                                                    </td>
                                                    <td><s:date name="requestDate" format="MMM dd, yyyy"/>
                                                    </td>
                                                </tr>
                                            </s:if>
                                            <s:if test="status==1">
                                                <sj:dialog 
                                                    id="%{#stat.count}" 
                                                    autoOpen="false" 
                                                    modal="true" 
                                                    width="550"
                                                    height="550"
                                                    title="Request Details"
                                                    >
                                                    <p><span class="fbld">Requestor Name: </span><s:property value="user.fname"/>&nbsp;<s:property value="user.lname"/></p>
                                                    <p><span class="fbld">Type: </span><s:property value="requestType"/></p>
                                                    <p><span class="fbld">Reason: </span><s:property value="reason"/></p>
                                                    <p><span class="fbld">New Record: </span><s:property value="newRecord"/></p>
                                                    <p><span class="fbld">Request Date: </span><s:date name="requestDate" format="MMM dd, yyyy"/></p>
                                                    <p><span class="fbld">Archived Record: </span><s:property value="recordArchive" escape="false"/></p>
                                                    <p><span class="fbld">Updated Date: </span><s:date name="updatedDate" format="MMM dd, yyyy"/></p>
                                                </sj:dialog>
                                                <tr>
                                                    <td><s:property value="%{#stat.count}"/></td>
                                                    <td><s:property value="user.fname"/>&nbsp;<s:property value="user.lname"/><br/>
                                                        <sj:a 
                                                            openDialog="%{#stat.count}" 
                                                            button="false"
                                                            cssClass="cursor"
                                                            >
                                                            View
                                                        </sj:a>
                                                    </td>
                                                    <td><s:property value="requestType"/></td>
                                                    <td><s:property value="reason"/></td>
                                                    <td><s:property value="newRecord"/></td>
                                                    <td><s:date name="requestDate"  format="MMM dd, yyyy"/>
                                                    </td>
                                                </tr>   
                                            </s:if>
                                        </s:iterator>
                                    </table>
                                </div>
                                <%} else {%>

                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;Requests
                                </div>
                                <div class="w100 fl-l">
                                    <ul>
                                        <li><a href="ChangePersonalId.jsp">To Change Personal Information.</a></li>
                                    </ul>
                                </div>
                                <%}%> 
                                <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
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