<%-- 
    Document   : DepartmentAdd
    Created on : Apr 23, 2012, 1:25:19 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Departments/School</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
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
                            <div class="my_account_bg">Departments/School</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> 
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<s:a action="ShowRegisteredInstitute">Registered Institutes</s:a>&nbsp;>&nbsp; Departments/School
                                    </div>
                                    <div class="w100 fl-l tc fbld fcred">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <s:form method="post" action="AddDepartment" theme="simple" namespace="/Administrator">
                                            <s:url id="Univer" action="UniversityAct" namespace="/Dropdown"/> 
                                            <fieldset class="w550p mar0a">
                                                <legend class="fbld">Add Department/School</legend>
                                                <table align="center">
                                                    <tr><th>Institute/University</th><td>
                                                            <sj:select 
                                                                href="%{Univer}" 
                                                                id="univCode" 
                                                                onChangeTopics="reloadprogrammlist" 
                                                                name="instituteId" 
                                                                list="univList" 
                                                                emptyOption="false" 
                                                                headerKey="-1" 
                                                                headerValue="Please Select University"
                                                                label="Select University/Institute"
                                                                sortable="false"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Name:</th>
                                                        <td><s:textfield name="departmentName"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Shot Name:</th>
                                                        <td><s:textfield name="departmentCode"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left" valign="top">Introduction:</th>
                                                        <td>
                                                            <sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="introduction"
                                                                rows="10"
                                                                cols="10"
                                                                value="%{introduction}"
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
                                                        <th align="left" valign="top">Postal Address:</th>
                                                        <td><s:textarea cssClass="w255p" rows="6" name="postalAddress"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left" valign="top">Phone No.:</th>
                                                        <td>Region Code: <s:textfield name="phoneCode"/>Ex.(011)&nbsp;<br/>
                                                            Number:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="phoneNo"/>Ex. (29571923)</td>
                                                    </tr>                                                    
                                                    <tr>
                                                        <th align="left">Mobile:</th>
                                                        <td><s:textfield name="mobileNo"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Fax:</th>
                                                        <td><s:textfield name="fax"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Email:</th>
                                                        <td><s:textfield name="deptEmailId"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th colspan="2" align="center">
                                                            <s:submit value="Save"/>&nbsp;&nbsp;<s:reset value="Reset"/>&nbsp;&nbsp;<s:reset value="Back" onClick="history.go(-1);" />
                                                        </th>
                                                    </tr>
                                                </table>
                                            </fieldset>
                                        </s:form>
                                    </div>
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
