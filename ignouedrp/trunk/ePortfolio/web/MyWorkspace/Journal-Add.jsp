<%-- 
    Document   : Journal-Add
    Created on : Dec 15, 2011, 12:33:29 PM
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Journal</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />
        <link href="<s:url value="/css/MonthYearPicker.css"/>" rel="stylesheet" type="text/css"/>
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
                            <div class="my_account_bg">Add Journal</div>
                            <div class="w100 fl-l mart10">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href=" ShowJournal"> Journals</a> &nbsp;> Add Journal </div>
                                <div class="w100 fl-l mart10">
                                    <fieldset class="w550p mar0a">
                                        <legend><strong>Add Journal</strong></legend>
                                        <s:form action="AddJournal" method="post" namespace="/MyWorkspace" name="">
                                            <table width="90%" border="0" class="mar0a" cellpadding="0" cellspacing="2">
                                                <s:radio name="confType" label="Conference Type" list="{'National','International'}"/>
                                                <s:textfield name="researchArea" label="Research Expertise Area"/>
                                                <s:radio name="asssProject" label="Is the paper associated with any project" list="{'Yes','No'}"/>
                                                <s:textfield name="projectName" label="if yes, Name of the Project"/>
                                                <s:textfield name="paperTitle" label="Title of the Paper"/>
                                                <s:select id="mymenu" label="No of Author" name="noCoauthor" onchange="updatefields()" list="{'1','2','3','4','5','6','7','8','9','10'}" headerKey="0" headerValue="Select No. of Author"/>
                                                <script type="text/javascript">
                                                    var selectmenu=document.getElementById("mymenu")
                                                    var i;
                                                    var fieldcont="";
                                                    selectmenu.onchange=function(){ //run some code when "onchange" event fires
                                                        var chosenoption=this.options[this.selectedIndex] //this refers to "selectmenu"
                                                        for(i=0;i<chosenoption.value;i++){
                                                            fieldcont+='<table border="0"><tr><td valign="top" width="220">Co-Author '+(i+1)+' First Name </td><td><input type="text" name="fname['+i+']"/></td></tr>';
                                                            fieldcont+='<tr><td valign="top">Co-Author '+(i+1)+' Last Name</td><td><input type="text" name="lname['+i+']"/></td></tr></table>';
                                                        }  
                                                        if(fieldcont){
                                                            document.getElementById("formfields").innerHTML=fieldcont;
                                                            fieldcont="";
                                                        }
                                                    }
                                                </script>
                                                <tr>
                                                    <td colspan="2"><s:div id="formfields"/></td>
                                                </tr>
                                                <s:textfield name="journalName" label="Name of the Journal"/>
                                                <s:textfield name="volumeNo" label="Volume No"/>
                                                <s:textfield name="serialNo" label="Serial No"/>
                                                <s:textfield name="issnNo" label="Issue No (ISSN)"/>
                                                <tr>
                                                    <td>Pages:</td>
                                                    <td>From&nbsp;&nbsp;&nbsp;
                                                        <s:textfield  name="pfrom" cssStyle="width:50px;" theme="simple"/>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;To&nbsp;&nbsp;&nbsp;
                                                        <s:textfield  name="pto" cssStyle="width:50px;" theme="simple"/>
                                                    </td>
                                                </tr>
                                                <sj:datepicker id="date0" label="Month & Year" 
                                                               name="date" value="today" 
                                                               displayFormat="MM, yy"                                                            
                                                               changeMonth="true" changeYear="true"
                                                               onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                               />
                                                <s:textfield name="impactFactor" label="Impact Factor"/>
                                                <s:textfield name="avgCitagtionIndex" label="Average Citation Index"/>
                                                <s:radio name="scopus" label="Whether Indexed in Scopus?(Yes/No)" list="{'Yes','No'}"/>
                                                <s:textfield name="language" label="Language"/>
                                                <s:textfield name="affiliation" label="Affiliation"/>
                                                <s:textfield name="url" label="URL, if any"/>
                                                <s:textarea name="summary" label="Summary/Abstract"/>
                                                <tr>
                                                    <td>Keywords:</td>
                                                    <td><s:textfield name="Key1" cssStyle="width:79px;" theme="simple"/>
                                                        <s:textfield name="Key2" cssStyle="width:79px;" theme="simple"/>
                                                        <s:textfield name="Key3" cssStyle="width:79px;" theme="simple"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td><s:textfield name="Key4" cssStyle="width:79px;" theme="simple"/>
                                                        <s:textfield name="Key5" cssStyle="width:79px;" theme="simple"/>
                                                        <s:textfield name="Key6" cssStyle="width:79px;" theme="simple"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" align="center"><s:submit theme="simple" value="Save" />
                                                        <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                    </fieldset>
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
