<%-- 
    Document   : Student-Exchange-Add
    Created on : Dec 28, 2011, 10:59:32 AM
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Student Exchange Programme</title>
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
                            <div class="my_account_bg">Student Exchange Programme</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="ShowExchangePro">Student Exchange Programme</a> &nbsp;> Add Student Exchange Programme </div>
                                    <div class="w100 fl-l mart10">
                                        <fieldset class="w500p mar0a">
                                            <legend><strong>Add Student Exchange Programme</strong></legend>
                                            <s:form action="AddExchangeInfo" method="post" namespace="/MyWorkspace" name="" theme="simple">
                                                <table width="95%" border="0" class="mar0a" cellpadding="0" cellspacing="2">
                                                    <tr>
                                                        <td width="30%">Exchange Programme Type: </td>
                                                        <td width="65%"><s:radio name="programmeType" list="{'National','International'}" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Name of the University/Institute: </td>
                                                        <td><s:textfield name="nameUniversity" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Venue: </td>
                                                        <td><s:textfield name="venue" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>State: </td>
                                                        <td><s:textfield name="state" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Country: </td>
                                                        <td><s:textfield name="country" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Exchange Programme Theme/Topic: </td>
                                                        <td><s:textarea name="programmeTheme" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Role: </td>
                                                        <td><s:select name="role"  list="{'Participant','Organizer','Volunteer','Others'}" headerKey="0" headerValue="-------As a Participant-------"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>If others, mention: </td>
                                                        <td><s:textfield name="ifOther" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Duration:</td>
                                                        <td>From&nbsp;
                                                            <sj:datepicker id="date0" value="%{durationFrom}" name="durationFrom" cssClass="w80p" changeMonth="true" changeYear="true"/>
                                                            &nbsp;To&nbsp;
                                                            <sj:datepicker id="date1" value="%{durationTo}" name="durationTo" cssClass="w80p" changeMonth="true" changeYear="true"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Degree Level: </td>
                                                        <td><s:radio name="degreeLevel"  list="{'UG','PG','M.Phil','PhD','Other'}" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Name of the Degree: </td>
                                                        <td><s:textfield name="degraeeName" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Research Collaboration, if any: </td>
                                                        <td><s:textarea name="researchColl" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>URL, if any: </td>
                                                        <td><s:textfield name="url" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Description: </td>
                                                        <td><s:textarea name="description" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:submit theme="simple" value="Save" />
                                                            <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </fieldset>
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
