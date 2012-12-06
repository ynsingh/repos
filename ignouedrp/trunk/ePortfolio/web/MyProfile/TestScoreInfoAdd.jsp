<%-- 
    Document   : TestScoreInfoAdd
    Created on : Oct 12, 2011, 12:42:51 PM
    Version    : 1
Author     : IGNOU Team
Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Test Score</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />        
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/gen_validatorv4.js"/>"></script>
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
                            <div class="my_account_bg">Add Test Score</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > <a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > <a href="ShowScore">Test Score</a> > Add Test Score </div>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <div class="w100 fl-l tc fbld fcgreen">
                                            <s:property value="msg"/>
                                        </div>
                                        <div class="w100 fl-l mart5">
                                            <fieldset class="w600p mar0a">
                                                <legend class="fbld">Add Test Score</legend>
                                                <s:form action="AddTestInfo" method="post" namespace="/MyProfile" name="myform">
                                                    <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                        <s:textfield name="tname" label="Test Name"/>
                                                        <s:textfield name="score" label="Score"/>
                                                        <sj:datepicker id="date0" label="Date" maxDate="-1d" name="tdate" changeMonth="true" changeYear="true"/>
                                                        <sjr:tinymce
                                                            id="richtextTinymceAdvancedEditor"
                                                            name="tdescription"
                                                            label="Description"
                                                            rows="10"
                                                            cols="10"
                                                            value="%{tdescription}"
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
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save" theme="simple" />
                                                                <s:reset value="Cancel" theme="simple" onClick="history.go(-1);" /></td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                        </div>
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
        <s:include value="/Footer.jsp"/>
        <script type="text/javascript">
            var frmvalidator  = new Validator("myform");
            frmvalidator.addValidation("tname","req","Please enter Test Name");
            frmvalidator.addValidation("tname","maxlen=20","Max length is 20");
            frmvalidator.addValidation("tname","alpha_s","Alphabetic chars only");
    
            frmvalidator.addValidation("score","req","Please enter your Score");
            frmvalidator.addValidation("score","maxlen=20", "Max length is 20");
            frmvalidator.addValidation("score","numeric","Numeric only");
  
            frmvalidator.addValidation("tdate","req","Please enter Date");
  
        </script>
    </body>
</html>
