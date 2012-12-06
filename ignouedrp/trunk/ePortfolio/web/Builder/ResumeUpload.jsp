<%-- 
    Document   : ResumeUpload
    Created on : Feb 21, 2012, 4:33:32 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Resume Index</title>
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
                            <div class="my_account_bg">Upload Resume</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyBuilder.jsp"/>">My Builder</a>&nbsp;>&nbsp;<a href="<s:url value="/Builder/indexResume.jsp"/>">My Resume</a> > Upload Resume
                                    </div>
                                    <div class="w100 fl-l">
                                        <fieldset class="w550p mar0a mart10">
                                            <legend><strong>Upload Resume</strong></legend>
                                            <s:form action="UploadResume" method="post" enctype="multipart/form-data" namespace="/Builder">
                                                <table width="80%" border="0" class="mar0a" cellpadding="0" cellspacing="10">                                    
                                                    <s:file name="userResume" label="Choose Resume"/>
                                                    <s:submit value="Upload" align="center"/>
                                                </table>
                                            </s:form>
                                        </fieldset>
                                    </div>
                                    <div class="w100 fl-l">
                                        <table width="100%" border="0" class="mar0a" cellpadding="0" cellspacing="10">                                    
                                            <tr>
                                                <th align="center" height="30" width="250">Resume Tittle</th>
                                                <th align="center" height="30" width="250">Size</th>
                                                <th align="center" height="30" width="250">Date of Uploading</th>
                                                <th align="center" height="30" width="250">Download</th>
                                                <th align="center" height="30" width="250">Delete</th>
                                            </tr>
                                            <s:iterator value="resumelist" var="rslist">
                                                <tr>
                                                    <td align="center" height="30" width="250">
                                                        <a href="downloadResume?idResume=<s:property value="idResume"/>" target="_blank">
                                                            <s:property value="resumeName"/>
                                                        </a>
                                                    </td>
                                                    <td align="center" height="30" width="250">
                                                        <s:property value="resumeSize"/>
                                                    </td>
                                                    <td align="center" height="30" width="250">
                                                        <s:property value="uploadDate"/>
                                                    </td>
                                                    <td align="center" height="30" width="250"><a href="downloadResume?idResume=<s:property value="idResume"/>"><img src="<s:url value="/icons/download.png"/>" title="Download Resume"/></a></td>
                                                    <td align="center" height="30" width="250"><a href="deleteResume?idResume=<s:property value="idResume"/>" onclick="return confirm('Are you sure you want to delete this record ?')"><img src="<s:url value="/icons/delete.gif"/>" title="Delete Resume"/></a></td>
                                                </tr>
                                            </s:iterator>
                                        </table> 
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