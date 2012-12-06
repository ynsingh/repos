<%-- 
    Document   : AllowComment
    Created on : May 29, 2012, 10:17:54 AM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Allow Comment</title>
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
        <script type="text/javascript">
            function chkform(){
                count=document.getElementById("AllowedStudent_listStudent").options.length-1;
                for(i=0; i<=count; i++)
                {
                    document.getElementById("AllowedStudent_listStudent").options[i].selected=1;
                }
            }
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
                            <div class="my_account_bg">Allow Comment</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a>  &nbsp; >  <a href="ActivSubList?evidenceId=<s:property value="evidenceId"/>&amp;instituteId=<s:property value="instituteId"/>&amp;programmeId=<s:property value="programmeId"/>&amp;courseId=<s:property value="courseId"/>">Task/Activity Details</a> &nbsp;> Allow Comments
                                    </div>
                                    <div class="w100 fl-l">
                                         <fieldset class="w500p mar0a">
                                        <legend class="fbld">Allow Comment</legend>

                                        <table width="70%" class="mart10" align="center" cellpadding="4" border="0" cellspacing="0">
                                            <s:iterator value="evdInfo">
                                                <tr><th align="left">Title</th><td><s:property value="evTitle"/></td></tr>
                                             <!--
                                                <tr><th align="left">Start Date (MM/DD/YY)</th>
                                                    <td><s:property value="openDate"/></td></tr>
                                                <tr><th align="left">Closing Date (MM/DD/YY)</th>
                                                    <td><s:property value="closeDate"/></td></tr>
                                                <tr>
                                                    <th align="left">Instruction</th>
                                                    <td><s:property value="instruction" escape="false"/></td>
                                                </tr>
                                                <tr>
                                                    <th align="left">File</th>
                                                    <td>
                                                        <s:if test="assAttach!='null'">
                                                            <a href="downnloadAttach?facultyId=<s:property value="facultyId"/>&amp;assAttach=<s:property value="assAttach"/>" target="_blank"><s:property value="assAttach"/></a>
                                                        </s:if>
                                                        <s:elseif test="assAttach=='null'">

                                                        </s:elseif>
                                                    </td>
                                                </tr>
                                             -->
                                            </s:iterator>
                                            <s:form action="AllowedStudent"  namespace="/Evidence" theme="simple" method="post" onsubmit="chkform();">
                                                <s:hidden name="submissionId"/>
                                                <s:hidden name="evidenceId"/>
                                                <s:hidden name="courseId"/>
                                                <s:hidden name="instituteId"/>
                                                <s:hidden name="programmeId"/>
                                                <tr> 
                                                    <td colspan="2"> <s:optiontransferselect 
                                                            label="Student List"
                                                            name="stName"
                                                            leftTitle="Student List"
                                                            rightTitle="Selected Student"
                                                            list="stList"
                                                            headerKey="headerKey"
                                                            headerValue="--- Please Select---"
                                                            doubleName="listStudent"
                                                            doubleList="stSelList"
                                                            doubleHeaderKey="doubleHeaderKey"
                                                            doubleHeaderValue="--- Please Select---"
                                                            allowAddToRight="true" 
                                                            addToRightLabel=">"
                                                            allowAddAllToRight="true"
                                                            addAllToRightLabel=">>"
                                                            allowAddToLeft="true"
                                                            addToLeftLabel="<"
                                                            allowAddAllToLeft="true"
                                                            addAllToLeftLabel="<<"
                                                            allowUpDownOnLeft="false"
                                                            allowUpDownOnRight="false"
                                                            allowSelectAll="false"
                                                            />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <s:hidden name="canComment" value="true"/>
                                                </tr>         
                                                <tr>  
                                                    <td colspan="2" align="center">
                                                        <s:submit value="Submit" />&nbsp;
                                                        <s:reset value="Cancel" onClick="history.go(-1);" />
                                                    </td>
                                                </tr>
                                            </s:form>
                                        </table>
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
        <s:include value="/Footer.jsp"/>  
    </body>
</html>