<%-- 
    Document   : GovernanceInfo
    Created on : 25 Dec, 2011, 7:45:51 PM
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Books / Chapters</title>
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
          <div class="my_account_bg">Books / Chapters</div>
          <div class="w100 fl-l mart10">
            <div class="w98 mar0a">
              <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> Corporate Life </div>
              <div class="w100 fl-l">
                <s:a href="Governance-Add.jsp" namespace="/MyWorkspace"> <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Consultancy Offered"/> </s:a>
              </div>
              <div class="w100 fl-l tc fbld fcgreen">
                <s:property value="msg"/>
              </div>
              <div class="w100 fl-l mart10">
                <table width="100%" class="fl-l" cellpadding="0" cellspacing="0" border="0">
                  <s:iterator value="GovernanceListList" var="GovernanceModel" status="groupStatus">
                    <tr>
                      <td width="5%" align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                      <td width="85%"><a href="http://<s:property value="url"/>" target="_blank"> <strong>
                        <s:property value="nameCommittee"/>
                        </strong> </a> <br/>
                        <strong> Name of the University/Institute:&nbsp; </strong>
                        <s:property value="nameUniversity"/>
                        <br/>
                        <strong>Service Duration:&nbsp;</strong>&nbsp;
                        <s:property value="durationFrom"/>
                        -
                        <s:property value="durationTo"/>
                        <br/>
                        <s:if test="responsibility!=null"> <strong> Responsibilities:&nbsp; </strong>&nbsp;
                          <s:property value="responsibility"/>
                          ,&nbsp; </s:if>
                        <s:if test="summary!=null"> <strong> <br/>
                          Summary:&nbsp; </strong>
                          <s:property value="summary" escape="false"/>
                        </s:if></td>
                      <td width="5%" align="right" valign="top"><a href="editGovernance?governanceId=<s:property value="governanceId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a></td>
                      <td width="5%" align="right" valign="top"><a href="deleteGovernance?governanceId=<s:property value="governanceId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a></td>
                    </tr>
                  </s:iterator>
                </table>
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
