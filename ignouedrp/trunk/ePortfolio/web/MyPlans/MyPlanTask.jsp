<%-- 
    Document   : MyPlanTask
    Created on : Aug 11, 2011, 3:32:39 PM
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Plan's Tasks</title>
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
              <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;><a href="<s:url value="/MyPlans/fetch"/>">My Plans</a> &nbsp;> My Plan Task </div>
              <div class="w100 fl-l tr"> <a href="CreateNewTask.jsp?planId=<s:property value="planId"/>"> <img src="<s:url value="/icons/add.gif"/>" title="New Plan"/> </a></div>
              <div class="w100 fl-l tc fbld fcgreen">
                <s:property value="msg"/>
              </div>
              <div class="w100 fl-l mart10">
              <table width="100%" class="fl-l" border="1" cellpadding="0" cellspacing="0" >
                <tr>
                  <th width="6%" align="center">S. No</th>
                  <th width="14%" align="center">Task Title</th>
                  <th width="42%" align="center">Task Description</th>
                  <th width="17%" align="center">Date</th>
                  <th width="6%" align="center">Status(&#37;)</th>
                  <th width="7%" align="center">Edit</th>
                  <th width="8%" align="center">Delete</th>
                </tr>
                <s:iterator value="tasklist" status="stat">
                  <tr>
                    <td align="center"><s:property value="%{#stat.count}"/></td>
                    <td><s:property value="TTitle"/>
                    </td>
                    <td><s:property value="TDescription"/>
                    </td>
                    <td><s:property value="TDate"/>
                    </td>
                    <td align="center"><s:property value="status"/>
                      &#37; </td>
                    <td align="center"><a href="editTask?taskId=<s:property value="taskId"/>&amp;planId=<s:property value="planId"/>"> <img src="<s:url value="/icons/edit.gif"/>" align="middle" title="Edit Task"/> </a> </td>
                    <td align="center"><a href="deleteTask?taskId=<s:property value="taskId"/>&amp;planId=<s:property value="planId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" align="middle" title="Delete Task"/> </a> </td>
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
