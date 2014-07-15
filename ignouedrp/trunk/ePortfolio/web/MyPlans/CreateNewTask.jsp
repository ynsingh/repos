<%-- 
    Document   : CreateNewTask
    Created on : Aug 12, 2011, 3:20:45 PM
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="javax.servlet.http.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Create Plan's Task</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
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
            String planId = request.getParameter("planId");
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
                            <div class="my_account_bg">Task</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="w98 mar0a">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;><a href="<s:url value="/MyPlans/fetch"/>">My Plans</a> &nbsp;><a href="task?planId=<%=planId%>">My Plan Task</a> &nbsp;> Create Task </div>
                                        <s:actionerror/>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w300p mar0a">
                                                <legend><strong>Create Task</strong></legend>
                                                <s:form action="newtask" method="post" name="NewTaskForm" onsubmit="return validateTaskForm()" theme="simple">
                                                    <table width="80%" class="mar0a" cellpadding="5" cellspacing="0" >
                                                        <input type="hidden" name="planId" value="<%=planId%>"/>
                                                        <tr>
                                                            <td width="30%">Task Title</td>
                                                            <td width="50%"><s:textfield name="TTitle"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td valign="top">Description</td>
                                                            <td><s:textarea name="TDescription" ols="20" rows="5"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save" />
                                                                <s:reset value="Cancel" onClick="history.go(-1);" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
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
        <script type="text/javascript">
            var frmvalidator  = new Validator("NewTaskForm");
            frmvalidator.addValidation("t_title","req","Please enter Task Title");
            frmvalidator.addValidation("t_title","maxlen=40","Max length is 40");
            frmvalidator.addValidation("t_title","alpha_s","Alphabetic chars only");        
            
            frmvalidator.addValidation("t_description","req","Please enter Task Description");
            frmvalidator.addValidation("t_description","maxlen=200","Max length is 200");
            frmvalidator.addValidation("t_description","alpha_s","Alphabetic chars only");        
  
        </script>
    </body>
</html>
