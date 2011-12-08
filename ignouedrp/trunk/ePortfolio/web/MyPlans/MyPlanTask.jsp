<%-- 
    Document   : MyPlanTask
    Created on : Aug 11, 2011, 3:32:39 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Home</title>
        <script type="text/javascript" src="<s:url value="/JS/jquery-latest.js"/>"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#accordion > li > div").click(function(){
 
                    if(false == $(this).next().is(':visible')) {
                        $('#accordion ul').slideUp(300);
                    }
                    $(this).next().slideToggle(300);
                });
 
                $('#accordion ul:eq(0)').show();

            });
        </script>
       <script type="text/javascript">
            $(document).ready(function() {
            $('fieldset.jcalendar').jcalendar();
            });
        </script>
        <script src="<s:url value="/JS/jquery-1.6.4.min.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/JS/jcalendar-source.js"/>" type="text/javascript"></script>
        <link href="<s:url value="/JS/jcalendar.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/theme1/style.css"/>" rel="stylesheet" type="text/css" />
    </head>
    <body><%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }
                   
        %>
        <jsp:include page="../Header.jsp"/>

        <div id="container">
            <div class="wrapper">
                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>Plan Task</h3>

                    <br/>

                    ||<s:a href="../index.jsp">Index</s:a>||<s:a href="%{MyPlanID}">My Plans</s:a>||<s:a href="#">Tasks</s:a>
                    <hr/>

                    <a href="CreateNewTask.jsp?plan_id=<s:property value="plan_id"/>">
                        <img src="../icons/add.gif" align="right" title="New Task"/>
                    </a>

                    <table width="100%" class="defaulttab1" cellpadding="4" cellspacing="0" border="4">

                        <tr>
                            <th>Task Title</th>
                            <th>Task Description</th>
                            <th>Date</th>
                            <th>Status(&#37;)</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>

                        <s:iterator value="tasklist" var="myplantasklist">
                            <tr>
                                <td>
                                    <s:property value="t_title"/>
                                </td><td>
                                    <s:property value="t_description"/>
                                </td><td>
                                    <s:property value="t_date"/>
                                </td><td align="center">
                                    <s:property value="status"/>&#37;
                                </td><td align="center">
                                    <a href="editTask?task_id=<s:property value="task_id"/>">
                                        <img src="../icons/edit.gif" align="middle" title="Edit Task"/>
                                    </a>    
                                </td><td align="center">
                                    <a href="deleteTask?task_id=<s:property value="task_id"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                        <img src="../icons/delete.gif" align="middle" title="Delete Task"/>
                                    </a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                    <br/><br/>                    
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
