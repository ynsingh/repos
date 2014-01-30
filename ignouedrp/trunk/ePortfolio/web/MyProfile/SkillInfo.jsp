<%-- 
    Document   : SkillInfo
    Created on : Sep 13, 2011, 5:16:32 PM
Author     : IGNOU Team
Version      : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Skills Set</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
        <script type="text/javascript">
            function validatePlanForm()
            {
                var x = document.forms["SkillForm"]["skills"].value;
                if (x == null || x == "")
                {
                    alert("You can't add Null or Blank Value. Please Enter your Skill.");
                    return false;
                }
            }
        </script>
    </head>
    <body><%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
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
                            <div class="my_account_bg">My Skills Set</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Skills Set </div>
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="w100 fl-l">
                                    <s:form action="AddSkill" method="post" namespace="/MyProfile" name="SkillForm" onsubmit="return validatePlanForm()" theme="simple">
                                        <fieldset class="w400p mar0a">
                                            <legend class="fbld"> Skills Set</legend>
                                            <table width="70%" class="mar0a" cellpadding="4" border="0" cellspacing="0" theme="simple">
                                                <tr>
                                                    <td width="30%">Skills Set Type</td>
                                                    <td width="40%"><s:select name="type"  list="{'Personal','Technical','Others'}" headerKey="-1" headerValue="-- Select Skill Type --"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Skill</td>
                                                    <td><s:textfield name="skills"  /></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td><s:submit value="Add Skill" />
                                                        <s:reset value="Cancel" onClick="history.go(-1);" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </s:form>
                                    <div class="w50 mar0a tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l">
                                        <!--     <tr>
                                                <th colspan="3" align="left">Academic Skills Set</th>
                                            </tr>
                                        <s:iterator value="AcademicList" var="Skil" status="stat">
                                            <tr><td valign="top"><s:property value="#stat.count"/></td>
                                                <td><s:property value="skills"/></td>
                                                <td><a href="deleteSkill?skillId=<s:property value="skillId"/>" onclick="return confirm('Are you sure you want to delete this record')"> <img class="fl-l marl10" src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/></a></td>
                                            </tr>
                                        </s:iterator>
                                        -->  
                                        <fieldset class="w400p mar0a">
                                            <legend class="fbld">Personal Skills Set</legend> 
                                            <table width="70%" class="mar0a" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td colspan="2">
                                                        <div class="w50 mar0a tc fbld fcgreen">
                                                            <s:property value="permsg"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <s:iterator value="PersonalList" var="Skil" status="stat">
                                                    <tr><td valign="top"><s:property value="#stat.count"/></td>
                                                        <td><s:property value="skills"/></td>
                                                        <td><a href="deleteSkill?skillId=<s:property value="skillId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                        </fieldset>
                                        <fieldset class="w400p mar0a">
                                            <legend class="fbld">Technical Skills Set</legend> 
                                            <table width="70%" class="mar0a" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td colspan="2"> 
                                                        <div class="w50 mar0a tc fbld fcgreen">
                                                            <s:property value="techmsg"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <s:iterator value="TechnicalList" var="Skil" status="stat">
                                                    <tr>
                                                        <td valign="top"><s:property value="#stat.count"/></td>
                                                        <td><s:property value="skills"/></td>
                                                        <td><a href="deleteSkill?skillId=<s:property value="skillId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/></a></td>
                                                    </tr>
                                                </s:iterator></table>
                                        </fieldset>
                                        <fieldset class="w400p mar0a">
                                            <legend class="fbld">Other Skills Set</legend> 
                                            <table width="70%" class="mar0a" cellpadding="0" cellspacing="0" border="0">
                                                <tr>
                                                    <td colspan="2">
                                                        <div class="w50 mar0a tc fbld fcgreen">
                                                            <s:property value="othmsg"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <s:iterator value="OtherList" var="Skil" status="stat">
                                                    <tr>
                                                        <td valign="top"><s:property value="#stat.count"/></td>
                                                        <td><s:property value="skills"/></td>
                                                        <td><a href="deleteSkill?skillId=<s:property value="skillId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a>
                                                        </td>
                                                    </tr>               
                                                </s:iterator>
                                            </table>    </fieldset>

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
            var frmvalidator = new Validator("SkillForm");
            frmvalidator.addValidation("type", "dontselect=-1", "Please enter your Skills Set Type");

            frmvalidator.addValidation("skills", "req", "Please enter Skill");
            frmvalidator.addValidation("skills", "maxlen=50", "Max length is 50");
            frmvalidator.addValidation("skills", "alpha_s", "Alphabetic chars only");

        </script>
    </body>
</html>
