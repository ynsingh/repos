<%-- 
    Document   : ProjectInfoEdit
    Created on : Oct 19, 2011, 3:26:21 PM
    Author     : IGNOU Team
    Version      : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Edit Project</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />
        <link href="<s:url value="/css/MonthYearPicker.css"/>" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
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
                            <div class="my_account_bg">Edit Project</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;><a href="ProjectInfo">My Projects</a> &nbsp;> Edit Project </div>
                                    <div class="w100 fl-l mart10">
                                        <fieldset class="w600p mar0a">
                                            <legend><strong>Edit Project</strong></legend>
                                            <s:form action="UpdateProjInfo" method="post" theme="simple" namespace="/MyProfile" name="AcademicForm" onsubmit="return validatePlanForm()">
                                                <table width="95%" border="0" class="mar0a" cellpadding="0" cellspacing="2">
                                                    <s:iterator value="ProList" var="pro">
                                                        <s:hidden name="projectId"/>
                                                        <s:hidden name="userId"/>
                                                        <tr>
                                                            <td width="25%">Project Name: </td>
                                                            <td width="70%"><s:textfield name="proName"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Team Size: </td>
                                                            <td><s:textfield name="teamSize" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Role: </td>
                                                            <td><s:textfield name="role" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Funding Agency (if any): </td>
                                                            <td><s:textfield name="agency" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Budget (if any): </td>
                                                            <td><s:textfield name="budget" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Project URL: </td>
                                                            <td><s:textfield name="proUrl" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Start Date: </td>
                                                            <td><sj:datepicker readonly="true"  id="date0" label="Month and Year of Start" 
                                                                           name="startDate" value="%{startDate}"
                                                                           displayFormat="MM, yy"                                                            
                                                                           changeMonth="true" changeYear="true"
                                                                           onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                           />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>End Date: </td>
                                                            <td><sj:datepicker readonly="true"  id="date1" name="endDate" value="%{endDate}" 
                                                                           displayFormat="MM, yy"                                                            
                                                                           changeMonth="true" changeYear="true"
                                                                           onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                           />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td valign="top">Description: </td>
                                                            <td><sjr:tinymce
                                                                    id="richtextTinymceAdvancedEditor"
                                                                    name="description"
                                                                    label="Description"
                                                                    rows="10"
                                                                    cols="10"
                                                                    value="%{description}"
                                                                    editorLocal="en"
                                                                    editorTheme="advanced"
                                                                    editorSkin="o2k7"
                                                                    toolbarAlign="left"
                                                                    toolbarLocation="top"
                                                                    toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                    toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                    toolbarButtonsRow3=" "
                                                                    />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save Changes" />
                                                                <s:reset value="Cancel" onClick="history.go(-1);" />
                                                            </td>
                                                        </tr>
                                                    </s:iterator>
                                                </table>
                                            </s:form>
                                        </fieldset>
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
            var frmvalidator  = new Validator("AcademicForm");
            frmvalidator.addValidation("proName","req","Please enter Project Name");
            frmvalidator.addValidation("proName","maxlen=20","Max length is 20");
            frmvalidator.addValidation("proName","alpha_s","Alphabetic chars only");        
            
            frmvalidator.addValidation("teamSize","req","Please enter Team Size");
            frmvalidator.addValidation("teamSize","maxlen=20","Max length is 20");
            frmvalidator.addValidation("teamSize","numeric","Numeric only");     
            
            frmvalidator.addValidation("role","req","Please enter Role");
            frmvalidator.addValidation("role","maxlen=20","Max length is 20");
            frmvalidator.addValidation("role","alpha_s","Alphabetic chars only");      
                        
            frmvalidator.addValidation("budget","req","Please enter Budget");
            frmvalidator.addValidation("budget","maxlen=20","Max length is 20");
            frmvalidator.addValidation("budget","numeric","Numeric chars only");      
  
            frmvalidator.addValidation("startDate","req","Please enter Start Date");
            
            frmvalidator.addValidation("endDate","req","Please enter End Date");
            
            frmvalidator.addValidation("description","req","Please enter Description");
            frmvalidator.addValidation("description","maxlen=200","Max length is 200");
            frmvalidator.addValidation("description","alpha_s","Alphabetic chars only");      
  
        </script>
    </body>
</html>
