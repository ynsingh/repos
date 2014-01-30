<%-- 
    Document   : ValueSetup
    Created on : May 12, 2012, 1:51:10 PM
    Author     : IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Course Grade Setup</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
    </head>
    <body>
        <%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
             logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
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
                            <div class="my_account_bg">Course Grade Setup</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> &nbsp; >  <a href="ActivSubList?evidenceId=<s:property value="evidenceId"/>&amp;instituteId=<s:property value="instituteId"/>&amp;programmeId=<s:property value="programmeId"/>&amp;courseId=<s:property value="courseId"/>">Task / Activities Grade</a> &nbsp; > Task / Activity Grading 
                                    </div>
                                    <div class="fl-l mart20 w100">
                                        <fieldset class="w500p mar0a">
                                            <legend class="fbld">Value Setup</legend>
                                            <table width="50%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <s:iterator value="CourseListList">
                                                    <tr>
                                                        <th align="left">Institute</th>
                                                        <td colspan="2">
                                                            <s:iterator value="programme.institute">
                                                                <s:property value="instituteName"/>
                                                            </s:iterator>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Programme</th>
                                                        <td colspan="2">
                                                            <s:iterator value="programme">
                                                                <s:property value="programmeName"/>
                                                            </s:iterator>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Course</th>
                                                        <td colspan="2">
                                                            <s:property value="courseName"/>&nbsp;(<s:property value="courseCode"/>)
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                                <tr><td colspan="3"><hr/></td></tr>

                                                <s:form action="AddGradeValue" method="post" theme="simple" namespace="Evidence">
                                                    <s:hidden name="courseId"/>
                                                    <tr><th align="left">Grade</th>
                                                            <s:if test="splitDetails2[0]=='Points'">
                                                            <th>Maximum Value</th>
                                                            </s:if>       
                                                            <s:elseif test="splitDetails2[0]!='Points'">
                                                            <th>Min Value</th><th>Max Value</th>
                                                            </s:elseif>
                                                    </tr>
                                                    <s:iterator value="GTDList">
                                                        <s:hidden name="gtdId"/>
                                                        <s:hidden name="details"/>
                                                        <s:iterator value="splitDetails2" status="stat">
                                                            <tr>     
                                                                <th align="left">
                                                                    <s:property value="splitDetails2[#stat.index]"/>
                                                                </th>
                                                                <s:if test="splitDetails2[0]=='Points'">
                                                                    <td><s:textfield name="gradeValue"/></td>
                                                                </s:if>       
                                                                <s:elseif test="splitDetails2[0]!='Points'">
                                                                    <td><s:textfield name="gradeValue"/></td>
                                                                    <td><s:textfield name="gradeValue1"/></td> 
                                                                </s:elseif>
                                                            </tr>
                                                        </s:iterator>                                
                                                    </s:iterator>
                                                    <tr>
                                                        <td colspan="3" align="center">
                                                            <s:submit value="Save"/>&nbsp;
                                                            <s:reset value="Reset"/>&nbsp;
                                                            <s:reset value="Back" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </s:form>

                                            </table>                                        
                                        </fieldset>
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