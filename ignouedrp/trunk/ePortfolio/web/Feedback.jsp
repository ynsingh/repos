<%-- 
    Document   : feedback
    Created on : Oct 3, 2011, 11:30:57 AM
Author     : IGNOU Team
Version      : 1
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib  prefix="sx"  uri="/struts-dojo-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Welcome to ePortfolio</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sx:head/>
        <sj:head />  
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
    </head>
    <body>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <div class="w100 fl-l">
                    <div class="header">
                        <div class="w100 fl-l"><img src="<s:url value="/images/header.png"/>" alt="" width="980" height="100" /></div>
                    </div>
                    <div class="menu_bg">
                        <div class="wau fl-l"><img src="images/blank.gif" alt="" width="20" height="10" /></div>
                        <div class="eportfolio_txt">ePORTFOLIO</div>
                        <div class="menu">
                            <a href="<s:url value="/Login.jsp"/>">Home</a>
                        </div>
                        <div class="menu_arrow_img">&nbsp;</div>
                        <div class="img_panel">
                            <div class="my_profile">
                                &nbsp;
                            </div>
                            <div class="profile_img">&nbsp;</div>
                        </div>
                    </div>
                    <!--Header Ends Here-->
                </div>
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Right box Starts Here-->
                        <div class="middle_cont">
                            <div class="my_account_bg">Feedback</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l tc fbld fcgreen"><s:property value="feedbackmsg"/></div>
                                <fieldset class="w550p mar0a mart15">
                                    <legend class="fbld">Feedback</legend>
                                    <s:form action="feedback" method="post">
                                        <table width="90%" class="mar0a mart10" cellpadding="2" cellspacing="2" border="0" align="center">
                                            <s:textfield name="name" cssClass="tdLabel" label="Name"/>
                                            <s:textfield name="occupation" cssClass="tdLabel" label="Occupation"/>
                                            <s:textfield name="organization" cssClass="tdLabel" label="Organization" />
                                            <s:textfield size="25" name="emailId" cssClass="tdLabel" label="Email" />
                                            <s:textfield size="10" name="phone" cssClass="tdLabel" label="Phone" />
                                            <s:textfield name="FSubject" cssClass="tdLabel" label="Subject"/>
                                            <sjr:tinymce
                                                id="richtextTinymceAdvancedEditor"
                                                name="comment"
                                                label="Comments"
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
                                            <tr>
                                                <td colspan="2" align="center">
                                                    <s:submit value="Submit" theme="simple" />
                                                    <s:reset value="Cancel"  theme="simple" onClick="history.go(-1);" />
                                                </td>
                                            </tr>
                                        </table>
                                    </s:form>
                                </fieldset>
                            </div>
                            <!--Right box End Here-->
                        </div>
                        <div class="w100 fl-l"><img src="images/blank.gif" alt="" width="600" height="30" /></div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <!--Footer Section Starts Here-->
        <div class="footer">
            <div class="f_menu"> 
                <s:a action="ShowAboutUs" namespace="/Administrator">About Us</s:a> | <a href="<s:url value="/Feedback.jsp"/>" target="_Blank">Feedback</a> | <a href="<s:url value="/Help.jsp"/>" target="_Blank">Help</a> | <a href="#">Sitemap</a> | <s:a action="ShowContactUs" namespace="/Administrator">Contact Us</s:a>
            </div>
        </div>
        <div class="footer_panel">
            <div class="footer_txt">
                <div class="wau fl-l tl">ePortfolio &COPY; 2011-13, MHRD. All Rights Reserved</div>
                <div class="wau fl-r tr">Designed and Developed by eGyanKosh,Indira Gandhi National Open University</div>
            </div>
            <!--Footer Section Ends Here-->
        </div>
    </body>
</html>
