<%-- 
    Document   : TestimonialDraftEdit
    Created on : Oct 12, 2012, 3:15:39 PM
    Author     : Vinay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Edit Testimonials</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%  if (session.getAttribute("user_id") == null) {
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
                            <div class="my_account_bg"><s:property value="title"/></div>
                            <div class="w100 fl-l mart10">
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > My Testimonial > Draft > Edit Testimonials
                                </div>
                                <div class="marr15 fl-r mart10">
                                    || <s:a action="StdTestiReq">Inbox</s:a> || <s:a action="MailedTestimonial">Sent</s:a> || <s:a action="FacultyDraftTesti">Draft</s:a> ||
                                </div>
                                <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                <div class="w100 fl-l mart5">
                                    <s:form action="UpdateTestimonial" method="post" theme="simple" namespace="/MyProfile">   
                                        <fieldset class="w550p mar0a">
                                            <legend class="fbld">Edit Testimonial</legend>
                                            <table width="100%" class="fl-l" cellpadding="5" border="0" cellspacing="5">
                                                <s:iterator value="ReqsentList" status="stat" var="testiModel">
                                                    <s:hidden name="testiReqId"/>
                                                    <tr>
                                                        <th class="tl">Requestor</th>
                                                        <td width="70%" valign="top" align="left">
                                                            <s:hidden name="requestorId" value="%{userByTestiRequestor.emailId}"/>
                                                            <s:property value="userByTestiRequestor.fname"/> <s:property value="userByTestiRequestor.lname"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="tl" valign="top">Sent To</th>
                                                        <td valign="top" align="left">
                                                            <strong><s:property value="testiForName"/></strong><br/>
                                                            <s:property value="testiForAddress"/><br/>
                                                            <s:hidden name="testiForEmail"/>
                                                            <s:hidden name="testiReqCc"/>
                                                            <s:hidden name="testiReqBcc"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="tl" valign="top">Created Date</th>
                                                        <td valign="top"><s:property value="createDate"/>
                                                            <s:hidden name="createDate" value="createDate"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th class="tl" valign="top">Testimonial</th>
                                                        <td>  
                                                            <sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="report"
                                                                rows="25"
                                                                cols="15"
                                                                value="%{report}"
                                                                editorLocal="en"
                                                                editorTheme="advanced"
                                                                editorSkin="o2k7"
                                                                toolbarAlign="left"
                                                                toolbarLocation="top"
                                                                toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                toolbarButtonsRow3=" "
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4" align="center">
                                                            <button type="submit" name="ButtonName" value="Email">Email</button>
                                                            <button type="submit" name="ButtonName" value="Export">Export</button>
                                                            <button type="submit" name="ButtonName" value="Save">Draft</button>
                                                            <button type="button" onClick="history.go(-1);" >Back</button>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                        </fieldset>
                                    </s:form>
                                </div> 
                            </div>
                        </div>
                        <!--Right box End Here-->
                    </div>
                </div>
                <!--Middle Section Ends Here-->
            </div>
        </div>
        <s:include value="/Footer.jsp"/>  
    </body>
</html>