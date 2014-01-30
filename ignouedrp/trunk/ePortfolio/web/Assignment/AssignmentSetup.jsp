<%--
    Document   : AssignmentSetup
    Created on : 23 Oct, 2013, 12:09:10 PM
    Author     : Vinay
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
        <title>Assignment Setup</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Assignment Setup</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="../MyWorkspace/MyWorkspace.jsp">My Workspace</a> > Assignment
                                    </div>
                                    <div align="right" class="tab_btn">
                                        <div class="tab_btn_1"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                        <div class="fl-r">
                                            <a href="AssignmentSetup.jsp">Assignment Setup</a>
                                        </div>
                                        <div class="w100 fl-l mart15">
                                            <fieldset class="w550p mar0a ">
                                                <legend class="fbld">Assignment Setup</legend>
                                                <s:form name="form1" action="QuestionSet" namespace="/Assignment" theme="simple">
                                                    <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                                        <tr>
                                                            <td>MCQ</td><td><s:checkbox name="mcq"  label="MCQ"/></td><td>No. of Question</td>
                                                            <td><s:textfield name="mcqNo" value="0" label="No. of Question"/>No of Option<s:textfield name="noofOpt" label="No of Option" cssClass="w70p"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Fill in the Blanks</td><td><s:checkbox name="fill"  label="Fill in the Blanks"/></td>
                                                            <td>No. of Question</td><td><s:textfield name="fillNo" value="0" label="No. of Question"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>True & False</td><td><s:checkbox name="tf"  label="True & False"/></td>
                                                            <td>No. of Question</td><td><s:textfield name="tfNo" value="0" label="No. of Question"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Matching</td><td><s:checkbox name="match"  label="Matching"/></td>
                                                            <td>No. of Question</td><td><s:textfield name="matchNo" value="0" label="No. of Question"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Short Answer</td><td><s:checkbox name="shortans"  label="Short Answer"/></td>
                                                            <td>No. of Question</td><td> <s:textfield name="shortansNo" value="0" label="No. of Question"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Long Answer</td><td><s:checkbox name="longans" label="Long Answer"/></td>
                                                            <td>No. of Question</td><td><s:textfield name="longansNo" value="0" label="No. of Question"/></td>
                                                        </tr>
                                                        <tr><td colspan="5" class="tc"><s:submit/><s:reset/></td></tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
