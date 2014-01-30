<%--
    Document   : QuestionSet
    Created on : 17 Oct, 2013, 11:51:38 AM
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
        <title>Question Setup</title>
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
                            <div class="my_account_bg">Question Setup</div>
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
                                            <s:form theme="simple">
                                                <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                                    <tr><td colspan="2"><legend class="fbld fl-l">MCQ</legend></td></tr>
                                                    <s:iterator value="mcqQuestion" status="mcqQuestionStat">
                                                        <tr>
                                                            <td>Question <s:property value="%{#mcqQuestionStat.count}"/></td>
                                                            <td><s:textfield name="Question" label="Question %{#mcqQuestionStat.count}"/></td>
                                                        </tr>
                                                        <s:iterator value="noofOptList" status="noofOptListStat">
                                                            <tr>
                                                                <td>Option <s:property value="%{#noofOptListStat.count}"/></td>
                                                                <td><s:textfield name="Opt1" label="Option %{#noofOptListStat.count}"/></td>
                                                            </tr>
                                                        </s:iterator>
                                                        <tr>
                                                            <td>Currect Answer</td>
                                                            <td><s:textfield name="CurrectAns" label="Currect Answer"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                    <tr><td colspan="2"><legend class="fbld fl-l">Fill in the Blank</legend></td></tr>
                                                    <s:iterator value="fillQuestion" status="fillQuestionStat">
                                                        <tr>
                                                            <td>Question <s:property value="%{#fillQuestionStat.count}"/></td>
                                                            <td><s:textfield name="question" label="Question %{#fillQuestionStat.count}"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Currect Answer</td>
                                                            <td><s:textfield name="CurrectAns" label="Currect Answer"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                    <tr><td colspan="2"><legend class="fbld">True & False</legend></td></tr>
                                                    <s:iterator value="tfQuestion" status="tfQuestionStat">
                                                        <tr>
                                                            <td>Question <s:property value="%{#tfQuestionStat.count}"/></td>
                                                            <td><s:textfield name="question" label="Question %{#tfQuestionStat.count}"/></td>
                                                        </tr>
                                                        <tr><td>Currect Answer</td>
                                                            <td><s:radio name="" list="{'True','False'}" label="Currect Answer"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                    <tr><td colspan="2">  <legend class="fbld">Matching</legend></td></tr>
                                                    <s:iterator value="matchQuestion" status="matchQuestionStat">
                                                        <tr><td>Question <s:property value="%{#matchQuestionStat.count}"/></td><td><s:textfield name=""/></td></tr>
                                                        <tr><td>Answer <s:property value="%{#matchQuestionStat.count}"/></td><td><s:textfield name=""/></td></tr>
                                                        <tr><td>Correct Answer</td>
                                                            <td>
                                                                <select name="">
                                                                    <s:iterator value="matchQuestion" status="matchQuestionStat">
                                                                        <option value="<s:property value="%{#matchQuestionStat.count}"/>"><s:property value="%{#matchQuestionStat.count}"/></option>
                                                                    </s:iterator>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                    </s:iterator>
                                                    <tr><td colspan="2"> <legend class="fbld">Short Answer</legend></td></tr>
                                                    <s:iterator value="shortansQuestion" status="shortansQuestionStat">
                                                        <tr><td>Question <s:property value="%{#shortansQuestionStat.count}"/></td>
                                                            <td><s:textarea name="" label="Question %{#shortansQuestionStat.count}"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                    <tr><td colspan="2"> <legend class="fbld">Long Answer</legend></td></tr>
                                                    <s:iterator value="longansQuestion" status="longansQuestionStat">
                                                        <tr><td>Question <s:property value="%{#longansQuestionStat.count}"/></td>
                                                            <td><s:textarea name="" label="Question %{#longansQuestionStat.count}"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                </table>
                                            </s:form>
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
