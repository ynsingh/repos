<%-- 
    Document   : CreateNewTask
    Created on : Aug 12, 2011, 3:20:45 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="javax.servlet.http.*"%>

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
                    <h3>New Task</h3>
                    <%
                        String plan_id = request.getParameter("plan_id");
                    %>      
                    ||<s:a href="./index.jsp">Index</s:a>||<s:a href="fetch">My Plan</s:a>||<s:a href="./MyPlanTask.jsp">Tasks</s:a>
                        <hr/>
                        <br/>
                    <s:actionerror/>
                    <s:form action="newtask" method="post" name="NewTaskForm" onsubmit="return validateTaskForm()">
                        <table align="center" width="60%" cellpadding="4" border="0" cellspacing="0">                          
                            <input type="hidden" name="plan_id" value="<%out.println(plan_id);%>"/>

                            <s:textfield name="t_title" label="Task Title"/>
                            <s:textarea name="t_description" label="Task Description" cols="20" rows="4"/>
                        </table>
                        <br/>
                        <s:submit cssClass="floatL buttonsMiddle" value="Save New Task" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />

                    </s:form>

                    <br/><br/><br/><br/><br/><br/>

                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
