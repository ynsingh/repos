<%-- 
    Document   : Departments
    Created on : Nov 06, 2012, 04:50:05 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Departments/School</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
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
            String role = session.getAttribute("role").toString();
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
                            <div class="my_account_bg">Departments/School</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> 
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<s:a action="ShowRegisteredInstitute">Registered Institutes</s:a>&nbsp;>&nbsp; Departments/School
                                        </div>
                                         <% if (role.contains("admin")) {%>
                                        <div class="w98 maroa tr">
                                            <a href="DepartmentAdd.jsp">Add Department/School</a>
                                        </div>
                                         <% } %>
                                        <div class="w100 fl-l tc fbld fcred">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <s:url id="Univer" action="UniversityAct" namespace="/Dropdown"/> 
                                        <s:url id="dept" action="DeptAct" namespace="/Dropdown"/> 
                                        <s:form method="post" id="FormId" theme="simple" namespace="/Dropdown">
                                            <table class="tablepaging" id="tablepaging" width="95%" cellspacing="0" cellpadding="5" border="1">
                                                <thead>
                                                    <tr><td width="180px;">University/Institute</td>
                                                        <td>
                                                            <sj:select 
                                                                href="%{Univer}" 
                                                                id="univCode" 
                                                                onChangeTopics="reloaddepartmentlist" 
                                                                name="instituteId" 
                                                                list="univList" 
                                                                emptyOption="false" 
                                                                headerKey="-1" 
                                                                headerValue="Please Select University"
                                                                label="Select University/Institute"
                                                                sortable="false"
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                </thead>
                                                <tr>
                                                    <th width="180px;" align="left" valign="top">Department/School</th>
                                                    <td>
                                                        <sj:radio
                                                            href="%{dept}" 
                                                            id="department" 
                                                            formIds="FormId" 
                                                            reloadTopics="reloaddepartmentlist" 
                                                            name="departmentId" 
                                                            list="departmentL" 
                                                            emptyOption="false" 
                                                            headerKey="-1" 
                                                            headerValue="Please Select a Programme"
                                                            label="Programme"
                                                            onChangeTopics=""
                                                            />
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                    </div>
                                </div>
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
