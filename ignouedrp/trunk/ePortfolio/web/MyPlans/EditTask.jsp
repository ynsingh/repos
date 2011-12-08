<%-- 
    Document   : EditTask
    Created on : Aug 23, 2011, 4:32:24 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Home</title>
        <script type="text/javascript" src="<s:url value="/JS/jquery-latest.js"/>"></script>
        <script type="text/javascript">
            function validateTaskForm()
            {
                var x=document.forms["NewTaskForm"]["t_title"].value;
                var y=document.forms["NewTaskForm"]["t_description"].value;
                if(x==null||x=="")
                {
                    alert("Task title is either blank or null. Please enter Task Title.");
                    return false;
                }
                if(y==null||y=="")
                {
                    alert("Task Description is either null or blank. Please enter Task Description.");
                    return false;
                }
            }
        </script>

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
                    <h3>Edit Task</h3>

                    <br/>

                    ||<s:a href="../index.jsp">Index</s:a>||<s:a href="%{MyPlanID}">My Plans</s:a>||<s:a href="MyPlanTask.jsp">Tasks</s:a>
                    <hr/>

                    <s:form action="updatetask" method="post" name="NewTaskForm" onsubmit="return validateTaskForm()">
                        <table width="60%" cellpadding="4" cellspacing="0" align="center">
                            <s:iterator value="EditTask" var="myplantasklist">

                                <tr><td></td><td><s:hidden value="plan_id"/><input type="hidden" name="task_id" value="<s:property value="task_id"/>"/></td></tr>
                                <tr><td>
                                        <s:textfield name="t_title" label="Task Title"/>
                                    </td></tr>
                                <tr><td>
                                        <s:textarea name="t_description" label="Description" cols="20" rows="5"/>
                                    </td></tr>
                                <tr><td>Status</td><td>
                                        <table border="0">
                                            <tr>
                                                <td align="center"><input type="radio" name="status" value="0" checked="true"/></td>
                                                <td align="center"><input type="radio" name="status" value="20"/></td>
                                                <td align="center"><input type="radio" name="status" value="40"/></td>
                                                <td align="center"><input type="radio" name="status" value="60"/></td>
                                                <td align="center"><input type="radio" name="status" value="80"/></td>
                                                <td align="center"><input type="radio" name="status" value="100"/></td>
                                            </tr>
                                            <tr>
                                                <td align="center">0&#37;</td>
                                                <td align="center">20&#37;</td>
                                                <td align="center">40&#37;</td>
                                                <td align="center">60&#37;</td>
                                                <td align="center">80&#37;</td>
                                                <td align="center">100&#37;</td>
                                            </tr>
                                        </table>                    
                                    </td></tr>
                                </s:iterator>
                        </table> 
                        <s:submit cssClass="floatL buttonsMiddle" value="Save Task" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />
                    </s:form>
                    <br/><br/><br/>                    
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
