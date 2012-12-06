<%-- 
    Document   : GradeValueEdit
    Created on : Jun 8, 2012, 3:52:59 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Edit Existing Course Grade Setup</title>
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
                            <div class="my_account_bg">Edit Existing Course Grade Setup</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> > <a href="GetGradeSetupList">Existing Grade Setup</a> > <a href="EditGradeSetup?gradeValId=<s:property value="gradeValId"/>">Edit Grade Setup</a> > Edit Grade Value
                                    </div>
                                    <fieldset class="w500p mar0a">
                                        <legend class="fbld">Edit Grade Value</legend>

                                        <table width="50%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                            <tr>
                                                <td class="fbld" width="180px;">Course</td>
                                                <td><s:property value="Course"/><s:hidden name="Course"/></td>
                                            </tr>
                                            <tr><td colspan="3"><hr/></td></tr>
                                            <s:form action="UpdateGradeValue" method="post" theme="simple" namespace="Evidence">
                                                <s:hidden name="gradeValId"/>
                                                <s:hidden name="instituteId"/>
                                                <s:hidden name="programmeId"/>
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
                                                <tr><td colspan="3" align="center"><s:submit value="Save Changes"/></td></tr>
                                            </s:form>
                                        </table>
                                    </fieldset>  
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