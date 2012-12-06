<%-- 
    Document   : MySocialInfo
    Created on : Oct 13, 2011, 11:28:03 AM
Author     : IGNOU Team
Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Social Networking</title>
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
          <div class="my_account_bg">My Social Connections</div>
          <div class="v_gallery">
            <div class="w98 mar0a">
              <div class="w100 fl-l mart10">
                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyConnection.jsp"/>">My Connections</a>&nbsp;>
                  <s:a href="ShowSocialInfo">Social Networking</s:a> Add Social Information </div>
                <div class="w100 fl-l mart10">
                  <table width="50%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                    <s:iterator value="socialListList" var="ProfileSocial">
                      <tr>
                        <th align="left">Gtalk :</th>
                        <td><s:property value="gtalk"/>
                        </td>
                        <td><s:url id="PSID" action="EditSocialInfo" namespace="/MyConnection"/>
                          <s:a href="%{PSID}"><img src="<s:url value="/icons/edit.gif"/>" align="right" title="Edit Information"/></s:a></td>
                      </tr>
                      <tr>
                        <th align="left">Skype :</th>
                        <td ><s:property value="skype"/>
                        </td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <th align="left">MSN :</th>
                        <td ><s:property value="msn"/>
                        </td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <th align="left">AIM :</th>
                        <td ><s:property value="aim"/>
                        </td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <th align="left">Yahoo :</th>
                        <td ><s:property value="yahoo"/>
                        </td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <th align="left">Facebook :</th>
                        <td ><a href="<s:property value="facebook"/>" target="_blank">
                          <s:property value="facebook"/>
                          </a> </td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <th align="left">Orkut :</th>
                        <td ><a href="<s:property value="orkut"/>" target="_blank">
                          <s:property value="orkut"/>
                          </a> </td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <th align="left">Twitter :</th>
                        <td ><a href="<s:property value="twitter"/>" target="_blank">
                          <s:property value="twitter"/>
                          </a> </td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <th align="left">Blog :</th>
                        <td ><a href="<s:property value="blog"/>" target="_blank">
                          <s:property value="blog"/>
                          </a> </td>
                        <td>&nbsp;</td>
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
