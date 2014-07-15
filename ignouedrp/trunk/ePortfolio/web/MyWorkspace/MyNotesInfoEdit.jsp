<%-- 
    Document   : MyNotesInfoEdit
    Created on : Dec 8, 2011, 11:18:56 AM
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
        <title>Edit Note</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Edit Note</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum">
                                            <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;><a href="ShowNotesInfo">My Notes Show </a> &nbsp;> Edit Note
                                        </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w550p mar0a">
                                                <legend><strong>Edit Note</strong></legend>
                                                <s:form action="UpdateNotesInfo" method="post" name="myform" theme="simple">
                                                    <table width="80%" border="0" class="mar0a" cellpadding="2" cellspacing="0">
                                                        <s:iterator value="editNoteList" var="mynoteslist">
                                                            <s:hidden name="notesId"/>
                                                            <tr>
                                                                <td width="20%">Note Topic</td>
                                                                <td width="60%"><s:textfield name="topic" required="true"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2"><br/></td>
                                                            </tr>
                                                            <tr>
                                                                <td valign="top">Note Description</td>
                                                                <td> <sjr:tinymce
                                                                        id="richtextTinymceAdvancedEditor"
                                                                        name="note"
                                                                        label="Description"
                                                                        rows="20"
                                                                        cols="10"
                                                                        value="%{note}"
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
                                                                <td>&nbsp;</td>
                                                                <td>
                                                                    <s:submit value="Save Changes" />
                                                                    <s:reset value="Cancel" onClick="history.go(-1);" />
                                                                </td>
                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </fieldset>
                                            </s:form>
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
            frmvalidator.addValidation("topic","req","Please enter Note Topic");
            frmvalidator.addValidation("topic","maxlen=20","Max length is 20");
            frmvalidator.addValidation("topic","alpha_s","Alphabetic chars only");        
            
            frmvalidator.addValidation("note","req","Please enter Note Description");
            frmvalidator.addValidation("note","maxlen=200","Max length is 200");
            frmvalidator.addValidation("note","alpha_s","Alphabetic chars only");        
  
        </script>
    </body>
</html>