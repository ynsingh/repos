<%-- 
    Document   : index
    Created on : Jul 22, 2011, 2:12:14 PM 
Author     : IGNOU Team
Version    : 1
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
        <title>Profile</title>
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
            if(window.history.forward(1) != null)
                window.history.forward(1);
        </script>
    </head>
    <body>
        <%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
             logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
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
                            <div class="my_account_bg">My Profile</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="w98 mar0a">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyResources/show_files"/>">My Resources</a> &nbsp;> Edit File/Folder </div>
                                        <s:actionerror/>
                                        <div class="w100 fl-l mart10">

                                            <fieldset class="w500p mar0a mart10">
                                                <legend><strong>Upload File</strong></legend>
                                                <s:form action="UpdateFile" method="post" name="myform" enctype="multipart/form-data" theme="simple">
                                                    <table width="50%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                        <s:iterator value="imagelistlist">
                                                            <input type="hidden" name="fileid" value="<s:property value="fileid"/>
                                                                   "/>
                                                            <tr>
                                                                <td>File Name</td>
                                                                <td><s:textfield name="filename" /></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Description</td>
                                                                <td><s:textarea name="description" /></td>
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
        <script type="text/javascript">
            var frmvalidator  = new Validator("myform");
            
            frmvalidator.addValidation("userImage","req","Please Choose a file");    
  
        </script>
        <script type="text/javascript">
            var frmvalidator  = new Validator("myform_1");
            
            frmvalidator.addValidation("name","req","Please Enter a Name");     
  
        </script>
    </body>
</html>

