<%-- 
    Document   : My Plan
    Created on : Aug 2, 2011, 4:01:57 PM
Author     : IGNOU Team
Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>My Plans</title>
<link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
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
          <div class="my_account_bg">My Plans</div>
          <div class="v_gallery">
          <div class="w100 fl-l mart10">
            <div class="w98 mar0a">
              <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> My Plans </div>
              <div class="w100 fl-l tr"><a href="<s:url value="CreateNewPlan.jsp"/>"> <img src="<s:url value="/icons/add.gif"/>" title="New Plan"/> </a></div>
              <div class="w100 fl-l tc fbld fcgreen">
                <s:property value="msg"/>
              </div>
              <div class="w100 fl-l mart10">
                <table width="100%" class="fl-l" border="1" cellpadding="0" cellspacing="0" >
                  <tr>
                    <th width="5%" align="center">S. No</th>
                    <th width="16%" align="center">Plan Title</th>
                    <th width="58%" align="center">Plan Description</th>
                    <th width="8%" align="center">Task</th>
                    <th width="5%" align="center">Edit</th>
                    <th width="8%" align="center">Delete</th>
                  </tr>
                  <s:iterator value="planlist" status="stat">
                    <tr>
                        <td align="center"><s:property value="%{#stat.count}"/></td>
                      <td><s:property value="PTitle"/></td>
                      <td><s:property value="PDescription"/></td>
                      <td align="center"><a href="task?planId=<s:property value="planId"/>"> <img src="<s:url value="/icons/task.gif"/>" title="Tasks"/> </a> </td>
                      <td align="center"><a href="editPlan?planId=<s:property value="planId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Plan"/> </a> </td>
                      <td align="center"><a href="deletePlan?planId=<s:property value="planId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Plan"/> </a> </td>
                    </tr>
                  </s:iterator>
                </table>
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
