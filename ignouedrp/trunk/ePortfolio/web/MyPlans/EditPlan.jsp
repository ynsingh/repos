<%-- 
    Document   : EditPlan
    Created on : Aug 23, 2011, 10:56:08 AM
Author     : IGNOU Team
Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Edit Plan</title>
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
        <script type="text/javascript">
            function validatePlanForm()
            {
                var x=document.forms["newPlanForm"]["p_title"].value;
                var y=document.forms["newPlanForm"]["p_description"].value;
                if (x==null || x=="")
                {
                    alert("Plan Tile is either null or blank. Please Enter Plan Title.");
                    return false;
                }
                if(y==null||y=="")
                {
                    alert("Plan Description is either null or blank. Please Enter Plan Description.");
                    return false;
                }
            }
        </script>
        <script type="text/javascript">
            if(window.history.forward(1) != null)
                window.history.forward(1);
        </script>
    </head>
    <body>
        <%
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
                            <div class="my_account_bg">Edit Plans</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;><a href="<s:url value="/MyPlans/fetch"/>">My Plans</a> &nbsp;> Edit Plan </div>
                                        <div class="w100 fl-l tc fbld fcgreen">
                                            <s:property value="msg"/>
                                        </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w300p mar0a">
                                                <legend><strong>Edit Plan</strong></legend>
                                                <s:form action="updateplan" theme="simple" method="post" name="newPlanForm" onsubmit="return validatePlanForm()">
                                                    <table width="80%" class="mar0a" cellpadding="5" cellspacing="0" >
                                                        <s:iterator value="editPlanList" var="myplanlist">
                                                            <s:hidden name="planId"/>
                                                            <tr>
                                                                <td width="30%">Title</td>
                                                                <td width="50%"><s:textfield name="PTitle"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td valign="top">Description</td>
                                                                <td><s:textarea name="PDescription"/></td>
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
