<%-- 
    Document   : EmployeeInfo
    Created on : Sep 13, 2011, 11:52:03 AM
Author     : IGNOU Team
Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Employment Information</title>
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
                            <div class="my_account_bg">Employment Information</div>
                            <div class="v_gallery">
            <div class="w98 mar0a">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Employment </div>
                                    <div class="w100 fl-l mart10">
                                        <div class="w98 mar0a tr"> <a href="EmployeeInfoAdd.jsp"> <img src=" <s:url value="/icons/add.gif" />" title="Add Employment"/> </a> </div>
                                        <div class="w100 fl-l tc fbld fcgreen">
                                            <s:property value="msg"/>
                                        </div>
                                        <div class="w100 fl-l mart5">
                                            <table width="98%" class="mar0a" cellpadding="4" border="1" cellspacing="0">
                                                <tr>
                                                    <th width="81">S. No</th>
                                                    <th width="79">Job Title</th>
                                                    <th width="134">Organization Name</th>
                                                    <th width="216">Address</th>
                                                    <th width="86">Joining Date</th>
                                                    <th width="156">Relieved On</th>
                                                  <th>Role/Description</th>
                                                    <th width="53">Edit</th>
                                                    <th width="76">Delete</th>
                                                </tr>
                                                <s:iterator value="employmentListList" var="ProfileEmployment" status="groupStatus">
                                                    <tr>
                      									<td align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                                                        <td valign="top"><s:property value="jtitle"/></td>
                                                        <td valign="top"><s:property value="orgName"/></td>
                                                        <td valign="top"><s:property value="oaddress"/>
                                                            <s:property value="ocity"/>
                                                            <s:property value="ostate"/>
                                                            <s:property value="ocountry"/>
                                                        </td>
                                                      <td valign="top"><s:property value="jdate"/></td>
                                                        <td valign="top"><s:property value="ldate"/>
                                                            &nbsp;</td>
                                                        <td valign="top" width="223"><s:property value="description"/></td>
                                                      <td align="center" valign="top"><a href="EditEmpInfo?employmentInfoId=<s:property value="employmentInfoId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a> </td>
                                                        <td align="center" valign="top"><a href="DeleteEmpInfo?employmentInfoId=<s:property value="employmentInfoId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a> </td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
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
    </body>
</html>
