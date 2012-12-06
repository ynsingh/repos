<%-- 
    Document   : Resumes
    Created on : Mar 15, 2012, 12:12:23 PM
    Author     : IGNOU Team
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>My Resume</title>
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
          <div class="my_account_bg">My Resume</div>
          <div class="v_gallery">
            <div class="w98 mar0a">
              <div class="w100 fl-l mart10">
                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyBuilder.jsp"/>">My Builder</a>&nbsp;>&nbsp;<a href="<s:url value="/Builder/indexResume.jsp"/>">My Resume</a> > My Resumes </div>
                <a href="BuildResume.jsp"> <img src=" <s:url value="/icons/add.gif"/>" align="right" title="Build Resume"/> </a>
                <ol>
                  <s:iterator value="" status="groupStatus">
                    <li title="<s:property value=""/>
                    "><span style="float:right; width:40px;"> <a href="EditResume?=<s:property value=""/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a> <a href="DeleteResume?=<s:property value=""/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a> </span>
                    </li>
                  </s:iterator>
                </ol>
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
