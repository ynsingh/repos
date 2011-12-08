<%-- 
    Document   : SkillInfo
    Created on : Sep 13, 2011, 5:16:32 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Home</title>
        <script type="text/javascript" src="<s:url value="/JS/jquery-latest.js"/>"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#accordion > li > div").click(function(){
 
                    if(false == $(this).next().is(':visible')) {
                        $('#accordion ul').slideUp(300);
                    }
                    $(this).next().slideToggle(300);
                });
 
                $('#accordion ul:eq(0)').show();

            });
        </script>
        <script type="text/javascript">
            function validatePlanForm()
            {
                var x=document.forms["SkillForm"]["skills"].value;
                if (x==null || x=="")
                {
                    alert("You can't add Null or Blank Value. Please Enter your Skill.");
                    return false;
                }
               
            }
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('fieldset.jcalendar').jcalendar();
            });
        </script>
        <script src="<s:url value="/JS/jquery-1.6.4.min.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/JS/jcalendar-source.js"/>" type="text/javascript"></script>
        <link href="<s:url value="/JS/jcalendar.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/theme1/style.css"/>" rel="stylesheet" type="text/css" />
    </head>
    <body><%
        if (session.getAttribute("user_id") == null) {
            pageContext.forward("../login.jsp");
        }

        %>
        <jsp:include page="../Header.jsp"/>

        <div id="container">
            <div class="wrapper">

                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>My Skills Set</h3>

                    <br/><br/>
                    <s:form action="AddSkill" method="post" namespace="/MyProfile" name="SkillForm" onsubmit="return validatePlanForm()">
                        <table align="center" width="75%" cellpadding="4" border="0" cellspacing="0">
                            <tr><td>Skill Type:</td>
                                <td>
                                    <select name="type">
                                        <option value="#">Select Skill Type</option>
                                        <option value="Personal">Personal Skills Set</option>
                                        <option value="Academic">Academic  Skills Set</option>
                                        <option value="Technical">Technical  Skills Set</option>
                                        <option value="Others">Others  Skills Set</option>
                                    </select>
                                </td>
                            </tr>
                            <s:textfield name="skills" cssClass="width250" label="Skill" />      
                        </table>
                        <s:submit cssClass="floatL buttonsMiddle" value="Add Skill" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />
                    </s:form>
                    <br/><br/><br/>
                    <div>
                        <p><strong>Academic Skills Set</strong></p>
                        <ol class="default1">
                            <s:iterator value="AcademicList" var="Skil">  
                                <li>
                                    <span style="float:right; width:40px;"> 
                                        <a href="deleteSkill?skillId=<s:property value="skillId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                            <img src="../icons/delete.gif" title="Delete Record"/>
                                        </a>
                                    </span>

                                    <s:property value="skills"/>
                                </li>
                            </s:iterator>
                        </ol>
                        <p><strong>Technical Skills Set</strong></p>
                        <ol class="default1">
                            <s:iterator value="TechnicalList" var="Skil">  
                                <li>
                                    <span style="float:right; width:40px;"> 
                                        <a href="deleteSkill?skillId=<s:property value="skillId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                            <img src="../icons/delete.gif" title="Delete Record"/>
                                        </a>
                                    </span>

                                    <s:property value="skills"/>
                                </li>
                            </s:iterator>
                        </ol>
                        <p><strong>Personal Skills Set</strong></p>
                        <ol class="default1">
                            <s:iterator value="PersonalList" var="Skil">  
                                <li>
                                    <span style="float:right; width:40px;"> 
                                        <a href="deleteSkill?skillId=<s:property value="skillId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                            <img src="../icons/delete.gif" title="Delete Record"/>
                                        </a>
                                    </span>

                                    <s:property value="skills"/>
                                </li>
                            </s:iterator>
                        </ol>
                        <p><strong>Other Skills Set</strong></p>
                        <ol class="default1">
                            <s:iterator value="OtherList" var="Skil">  
                                <li>
                                    <span style="float:right; width:40px;"> 
                                        <a href="deleteSkill?skillId=<s:property value="skillId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                            <img src="../icons/delete.gif" title="Delete Record"/>
                                        </a>
                                    </span>

                                    <s:property value="skills"/>
                                </li>
                            </s:iterator>
                        </ol>
                    </div>

                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
