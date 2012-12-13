<%-- 
    Document   : TaskActivityScore
    Created on : Jul 4, 2012, 5:21:17 PM
    Author     : IGNOU Team
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Task/Activities Score</title>
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
                            <div class="my_account_bg">Task/Activities Score</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;>&nbsp;<a href="ExamEvaluationIndex.jsp">Exam &amp; Evaluation</a> > Task/Activity Score
                                    </div>
                                    <% if (role.contains("faculty")) {%>

                                    <% } else if (role.contains("student")) {%>
                                    <div class="w100 fl-l">
                                        <table width="100%" class="fl-l mart10" cellpadding="4" border="1" cellspacing="0">   
                                            <tr>
                                                <th align="center">S.No.</th>
                                                <th align="center">Course ID</th>
                                                <th align="center">Task/Activity ID</th>
                                                <th align="center">Obtained Score</th>
                                                <th align="center">Special Comments</th>
                                            </tr>
                                            <s:iterator value="StdScrList" status="stat">
                                                <tr>
                                                    <td align="center">
                                                        <s:property value="#stat.count" />
                                                    </td>
                                                    <td align="center">
                                                        <s:property value="CourseCodeList[#stat.index]" />
                                                    </td>
                                                    <td align="left">
                                                        <s:property value="EvidenceTitleList[#stat.index]" />
                                                    </td>
                                                    <td align="center">  
                                                        <s:property value="gradesObtained"/>
                                                    </td>
                                                    <td align="left"> <s:property value="facultyComment" escape="false"/><br/>
                                                        <s:property value="facultyAttachment"/>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                        </table>
                                        <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                        <% }%>
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