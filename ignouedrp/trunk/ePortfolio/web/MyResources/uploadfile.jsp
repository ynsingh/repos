<%-- 
   Document   : index
   Created on : Jul 22, 2011, 2:12:14 PM 
Author     : IGNOU Team
Version    : 1
--%>
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
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>">
        </script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>">
        </script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
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
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;>My Resources </div>
                                        <s:actionerror/>
                                        <div class="w100 fl-l">
                                            <fieldset class="w500p mar0a mart10">
                                                <legend><strong>Upload File</strong></legend>
                                                <s:form action="addInfo" method="post" enctype="multipart/form-data" name="myform">
                                                    <table width="70%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                        <tr>
                                                            <td><s:textfield name="filename" label="File Name"/>
                                                            </td>
                                                            <td><s:file name="userImage" label="File"/>
                                                            </td>
                                                            <td align="center"><s:submit value="Upload" align="center"/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                            <fieldset class="w500p mar0a mart10">
                                                <legend><strong>Upload Folder</strong></legend>
                                                <s:form action="CreateFolder" method="post" name="myform_1">
                                                    <table width="50%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                        <s:textfield name="name" label="Create Folder"/>
                                                        <s:submit value="Create" align="center"/>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                            <div align="center"><strong><font color="#0B610B">
                                                        <s:property value="msg"/>
                                                    </font></strong></div>
                                            <table width="100%" class="fl-l mart10" cellpadding="4" border="1" cellspacing="0">
                                                <tr>
                                                    <th width="64" align="center">S. No</th>
                                                    <th width="293" align="center">File Name</th>
                                                    <th width="49" align="center">Size</th>
                                                    <th width="55" align="center">Type</th>
                                                    <th width="352" align="center">Description</th>
                                                    <th width="119" align="center">Date</th>
                                                    <th width="121" align="center">Edit</th>
                                                    <th width="96" align="center">Delete</th>
                                                </tr>
                                                <s:iterator value="imagelistlist" var="imagelist" status="groupStatus">
                                                    <tr>
                                                        <td align="center"><s:property value="%{#groupStatus.count}"/></td>
                                                        <td align="center" height="30" width="293"><s:if test="filetype!=null"> <a href="download?fileid=<s:property value="fileid"/>" target="_blank">
                                                                    <s:property value="filename"/>
                                                                </a> </s:if>
                                                            <s:else>
                                                                <s:property value="filename"/>
                                                            </s:else>
                                                        </td>
                                                        <td align="center"><s:property value="size"/>
                                                        </td>
                                                        <td align="center"><s:if test="filetype==null"> <img src="../images/folder.gif"/> </s:if>
                                                            <s:elseif test="filetype!=null"> <img src="../images/file.gif"/> </s:elseif>
                                                        </td>
                                                        <td align="center"><s:property value="description"/>
                                                        </td>
                                                        <td align="center"><s:property value="filedate"/>
                                                        </td>
                                                        <td align="center"><a href="edit?fileid=<s:property value="fileid"/>"><img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/></a></td>
                                                        <td align="center"><a href="delete?fileid=<s:property value="fileid"/>" onclick="return confirm('Are you sure you want to delete this record ?')"><img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/></a></td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
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
