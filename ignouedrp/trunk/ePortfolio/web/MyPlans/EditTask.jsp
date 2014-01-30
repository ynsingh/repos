<%-- 
    Document   : EditTask
    Created on : Aug 23, 2011, 4:32:24 PM
Author     : IGNOU Team
Version      : 1
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
        <title>Edit Plan's Task</title>
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
    <body><%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
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
                            <div class="my_account_bg">Edit Task</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="w98 mar0a">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;><a href="<s:url value="/MyPlans/fetch"/>">My Plans</a> &nbsp;><a href="task?planId=<s:property value="planId"/>">My Plan Task</a> &nbsp;> Edit Task </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w300p mar0a">
                                                <legend><strong>Edit Task</strong></legend>
                                                <s:form action="updatetask" method="post" name="NewTaskForm" onsubmit="return validateTaskForm()" theme="simple">
                                                    <table width="80%" class="mar0a" cellpadding="5" cellspacing="0" >
                                                        <s:iterator value="EditTask" var="myplantasklist">
                                                            <s:hidden name="planId"/>
                                                            <s:hidden name="taskId"/>
                                                            <tr>
                                                                <td width="30%">Task Title</td>
                                                                <td width="50%"><s:textfield name="TTitle"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Description</td>
                                                                <td><s:textarea name="TDescription" ols="20" rows="5"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Status</td>
                                                                <td><table border="0">
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
                                                                    </table></td>
                                                            </tr>
                                                        </s:iterator>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save Changes" />
                                                                <s:reset value="Cancel" onClick="history.go(-1);" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                        </div>
                                    </div>
                                </div>
                                <!--Right box End Here-->
                            </div>
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
