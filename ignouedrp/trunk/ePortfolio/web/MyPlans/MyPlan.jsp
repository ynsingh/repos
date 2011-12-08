<%-- 
    Document   : My Plan
    Created on : Aug 2, 2011, 4:01:57 PM
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
                    <h3>My Plans</h3>
                    <a href="<s:url value="CreateNewPlan.jsp"/>">
                        <img src="../icons/add.gif" align="right" title="New Plan"/>
                    </a>
                    <table width="99%" class="defaulttab1" cellpadding="4" cellspacing="0" border="4">                        <tr><th>Plan Title</th>
                            <th>Plan Description</th>
                            <th>Task</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>

                        <s:iterator value="planlist" var="myplanlist">
                            <tr><td>
                                    <s:property value="p_title"/>
                                </td>
                                <td>
                                    <s:property value="p_description"/>
                                </td>
                                <td align="center">
                                    <a href="task?Plan_id=<s:property value="plan_id"/>">
                                        <img src="../icons/task.gif" title="Task"/>
                                    </a>
                                </td>
                                <td align="center">
                                    <a href="editPlan?plan_id=<s:property value="plan_id"/>">
                                        <img src="../icons/edit.gif" title="Edit Plan"/>
                                    </a> 
                                </td>
                                <td align="center">
                                    <a href="deletePlan?plan_id=<s:property value="plan_id"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                        <img src="../icons/delete.gif" title="Delete Plan"/>
                                    </a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                    <br/><br/><br/>                   
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
