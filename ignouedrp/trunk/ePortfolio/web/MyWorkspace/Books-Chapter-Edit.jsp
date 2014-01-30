<%-- 
    Document   : Books-Chapter-Edit
    Created on : Feb 23, 2012, 3:03:15 PM
    Author     : IGNOU Team
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
        <title>Edit Book / Chapter</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
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
                            <div class="my_account_bg">Edit Book / Chapter</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum">
                                            <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="ShowBookChapter">Books Chapter</a> &nbsp;>Edit Books Chapter 
                                        </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w400p mar0a">
                                                <legend><strong>Edit Book / Chapter</strong></legend>
                                                <s:form action="UpdateBookChapter" method="post" namespace="/MyWorkspace" name="">
                                                    <table width="90%" class="mar0a" border="0" cellpadding="4" cellspacing="0">
                                                        <s:iterator value="BCListList"> 
                                                            <s:hidden name="bookChapterId"/><s:hidden name="userId"/>
                                                            <s:radio name="bcType" label="Book/Chapter" list="{'Book','Chapter'}"/>
                                                            <s:select  style="width:155px;" name="role" label="Role"  headerKey="1" headerValue="-- Please Select Your Role--" list="{'Author','Co-Author','Editor','Reviewer','Others'}"/>
                                                            <s:textfield name="title" label="Title"/>
                                                            <s:select id="mymenu" label="No of Author" name="noCoauthor" style="width:155px;" onchange="updatefields()" list="{'1','2','3','4','5','6','7','8','9','10'}" headerKey="0" headerValue="Select No. of Author" disabled="true"/>
                                                            <s:hidden name="noCoauthor"/>
                                                            <s:iterator value="bookChapterAuthors">
                                                                <s:textfield name="fname" label="Co-Author's Fist Name" disabled="true"/>
                                                                <s:textfield name="lname" label="Co-Author's Last Name" disabled="true"/>
                                                            </s:iterator>
                                                            <s:textfield name="publisher" label="Publisher"/>
                                                            <s:textfield name="isbn" label="ISBN"/>
                                                            <sj:datepicker readonly="true"  id="date0" label="Published on" value="%{publishedOn}" name="publishedOn" changeMonth="true" changeYear="true"/>
                                                            <tr>
                                                                <td>Pages</td>
                                                                <td>From&nbsp;&nbsp;&nbsp;
                                                                    <s:textfield name="PFrom" cssStyle="width:35px;" theme="simple"/>
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;To&nbsp;&nbsp;&nbsp;
                                                                    <s:textfield name="PTo" cssStyle="width:35px;" theme="simple"/>
                                                                </td>
                                                            </tr>
                                                            <s:textfield name="language" label="Language"/>
                                                            <s:textfield name="affiliation" label="Affiliation"/>
                                                            <s:textfield name="url" label="URL, if any"/>
                                                            <s:textarea name="summary" label="Summary"/>
                                                            <tr>
                                                                <td>&nbsp;</td>
                                                                <td>
                                                                    <s:submit theme="simple" value="Save Changes" />
                                                                    <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                                </td>
                                                            </tr>
                                                        </s:iterator>
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