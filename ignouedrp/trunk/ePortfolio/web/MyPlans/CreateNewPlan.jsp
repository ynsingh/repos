<%-- 
    Document   : CreateNewPlan
    Created on : Aug 2, 2011, 4:43:37 PM
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
        <title>Edit Plan</title>
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
                            <div class="my_account_bg">Plans</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;><a href="<s:url value="/MyPlans/fetch"/>">My Plans</a> &nbsp;> Create New Plan </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w300p mar0a">
                                                <legend><strong>Create Plan</strong></legend>
                                                <s:form action="newplan" method="post" theme="simple" name="newPlanForm" onsubmit="return validatePlanForm()">
                                                    <table width="80%" class="mar0a" cellpadding="5" cellspacing="0" >
                                                        <tr>
                                                            <td width="30%">Title</td>
                                                            <td width="50%"><s:textfield name="PTitle"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td valign="top">Description</td>
                                                            <td><s:textarea name="PDescription"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save Plan" />
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
            var frmvalidator  = new Validator("newPlanForm");
            frmvalidator.addValidation("p_title","req","Please enter Plan Title");
            frmvalidator.addValidation("p_title","maxlen=200","Max length is 200");
            frmvalidator.addValidation("p_title","alpha_s","Alphabetic chars only");        
            
            frmvalidator.addValidation("p_description","req","Please enter Plan Description");
            frmvalidator.addValidation("p_description","maxlen=200","Max length is 200");
            frmvalidator.addValidation("p_description","alpha_s","Alphabetic chars only");        
  
        </script>
    </body>
</html>
