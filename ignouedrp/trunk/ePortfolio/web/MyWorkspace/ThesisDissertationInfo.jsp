<%-- 
    Document   : ThesisDissertationInfo
    Created on : Feb 21, 2012, 5:01:37 PM
    Author     : IGNOU Team
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Thesis / Dissertation</title>
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
                            <div class="my_account_bg">Thesis / Dissertation</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> Thesis / Dissertation</div>
                                    <div class="w100 fl-l">
                                        <a href="<s:url value="/MyWorkspace/ThesisDissertation-Add.jsp"/>"> <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Patent"/> </a> 
                                    </div>
                                    <br/>
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" class="fl-l" cellpadding="0" cellspacing="0" border="1">
                                            <s:iterator  value="TDListList" status="groupStatus">
                                                <tr>
                                                    <td width="5%" align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                                                    <td width="85%"><s:iterator value="basicListList">
                                                            <s:property value="lname"/>
                                                            &nbsp;
                                                            <s:property value="fname"/>
                                                            &nbsp;
                                                            <s:property value="mname"/>
                                                            , </s:iterator>
                                                        <a href="<s:property value="url"/>" target="_blank"> <strong>"
                                                                <s:property value="thesisTitle"/>
                                                                "</strong> </a>,
                                                                <s:if test="others!=''">
                                                                    <s:property value="other"/>
                                                                </s:if>
                                                                <s:if test="programme!='Others'">
                                                                    <s:property value="programme"/>
                                                                </s:if>
                                                        &nbsp;
                                                        <s:property value="reportType"/>
                                                        ,
                                                        <s:property value="department"/>
                                                        , &nbsp;
                                                        <s:property value="nameUniversity"/>
                                                        ,
                                                        <s:property value="startDate"/>
                                                        &nbsp;to&nbsp;
                                                        <s:property value="endDate"/>
                                                        .</td>
                                                    <td width="5%" align="center" valign="top"><a href="EditTD?thesisDissertationId=<s:property value="thesisDissertationId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a></td>
                                                    <td width="5%" align="center" valign="top"><a href="DeleteTD?thesisDissertationId=<s:property value="thesisDissertationId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a></td>
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
