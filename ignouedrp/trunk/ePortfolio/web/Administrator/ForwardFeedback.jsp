<%-- 
    Document   : ForwardFeedback
    Created on : Apr 11, 2012, 3:43:41 PM
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
        <title>Forward FeedBack</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
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
                <s:include value="/Header.jsp"/>
                <!--Header Ends Here-->
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Forward FeedBack</div>
                            <div class="w98 mar0a">
                                <div class="w100 fl-l mart10">
                                    <fieldset class="w500p mar0a">
                                        <legend class="fbld">Forward Feedback</legend>

                                        <s:form action="SendFeedback" method="post" theme="simple">
                                            <table align="center" width="75%" cellpadding="4" border="0" cellspacing="0">
                                                <tr>
                                                    <td colspan="2" align="left"><s:submit value="Send" />
                                                        &nbsp;&nbsp;&nbsp;
                                                        <s:reset value="Discard" onClick="history.go(-1);" />
                                                    </td>
                                                </tr>
                                                <s:iterator value="fbList" var="fback">
                                                    <s:hidden name="feedbackId"/>
                                                    <s:hidden  name="name" />
                                                    <tr>
                                                        <td><s:label value="To"/></td>
                                                        <td><s:textfield name="emailId" value=" "/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Subject:</td>
                                                        <td><input type="text"  name="FSubject" value="Fwd: <s:property value="FSubject"/>
                                                                   "/> </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Message:</td>
                                                        <td><sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="comment"
                                                                rows="10"
                                                                cols="10"
                                                                value="%{comment}"
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
                                                </s:iterator>
                                            </table>
                                        </s:form>
                                    </fieldset>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>

        <s:include value="/Footer.jsp"/>
    </body>
</html>
