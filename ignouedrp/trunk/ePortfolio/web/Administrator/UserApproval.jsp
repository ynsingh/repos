<%-- 
    Document   : UserApproval
    Created on : Dec 2, 2011, 3:31:50 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Events</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
         <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body><%
        if (session.getAttribute("user_id") == null) {
            response.sendRedirect("../Login.jsp");
        }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include value="/Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">User Approval</div>
                            <div class="">
                                <table width="99%" class="defaulttab1" cellpadding="4" cellspacing="0" border="4"> 
                                    <tr><th> </th>
                                        <th> </th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </tr>

                                    <s:iterator value="" var="">
                                        <tr><td>
                                                <s:property value=""/>
                                            </td>
                                            <td>
                                                <s:property value=""/>
                                            </td>

                                            <td align="center">
                                                <a href="?id=<s:property value=""/>">
                                                    <img src="<s:url value="/icons/edit.gif"/>" title=" "/>
                                                </a> 
                                            </td>
                                            <td align="center">
                                                <a href="?id=<s:property value=""/>" onclick="return confirm('Are you sure you want to delete this record ?')">
                                                    <img src="<s:url value="/icons/delete.gif"/>" title=" "/>
                                                </a>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </table>
                            </div>
                            <!--Right box Starts Here-->
                        </div>

                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>  
    </body>
</html>