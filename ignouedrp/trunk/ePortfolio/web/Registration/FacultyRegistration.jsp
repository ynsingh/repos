<%-- 
    Document   : FacultyRegistration
    Created on : Sep 22, 2011, 10:03:02 AM
Author     : IGNOU Team
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Faculty Registration</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript">
            $().ready(function() {
                // validate signup form on keyup and submit
                $("#FacultyReg").validate({
                    rules: {
                        password: {
                            required: true,
                            minlength: 5
                        },
                        rePassword: {
                            required: true,
                            minlength: 5,
                            equalTo: "#FacultyReg_password"
                        },
                        agree: "required"
                    },
                    messages: {
                        password: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 5 characters long"
                        },
                        rePassword: {
                            required: "Please provide a password",
                            minlength: "Your password must be at least 5 characters long",
                            equalTo: "Please enter the same password as above"
                        }
                    }
                });
                // newsletter topics are optional, hide at first
                var inital = newsletter.is(":checked");
                var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
                var topicInputs = topics.find("input").attr("disabled", !inital);
                // show when newsletter is checked
                newsletter.click(function() {
                    topics[this.checked ? "removeClass" : "addClass"]("gray");
                    topicInputs.attr("disabled", !this.checked);
                });
            });
        </script>      
        <sj:head/>
        <sx:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/jquery.validate.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        <!-- user name availability script -->
        <style type="text/css">
            .flable{
                color:gray;
            }
            .status{
                font-family:verdana;
                font-size:12px;
            }
            .emailId{
                color:blue;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function(){
                $(".emailId").change(function(){
                    var emailId = $(this).val();
                    if(emailId.length > 3){
                        $(".status").html("<img src='../images/loading.gif'/><font color='gray'> Checking availability...</font>");
                        $.ajax({
                            type: "GET",
                            url: "CheckAvail",
                            data: "emailId=" + emailId,
                            success: function(msg){
                                $(".status").ajaxComplete (function(event , request, settings){
                                    $(".status").html(msg);

                                });
                            }
                        }); 
                    }
                    else{
                         
                        $(".status").html("<font color='red'>Username should be greater than<b>5</b> characters</font>");
                    }
                    
                });
            });
        </script>
    </head>
    <body>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <div class="w100 fl-l">
                    <div class="header">
                        <div class="w100 fl-l"><img src="<s:url value="/images/header.png"/>" alt="" width="980" height="100" /></div>
                    </div>
                    <div class="menu_bg">
                        <div class="wau fl-l"><img src="<s:url value="/images/blank.gif"/>" alt="" width="20" height="10" /></div>
                        <div class="eportfolio_txt">ePORTFOLIO</div>
                        <div class="menu"> 
                            <a href="<s:url value="/Login.jsp"/>">Home</a>
                        </div>
                        <div class="menu_arrow_img">&nbsp;</div>
                        <div class="img_panel">
                            <div class="my_profile">
                                &nbsp;
                            </div>
                            <div class="profile_img">&nbsp;</div>
                        </div>
                    </div>
                    <!--Header Ends Here-->
                </div>
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Right box Starts Here-->
                        <div class="middle_cont">
                            <div class="my_account_bg">Faculty Registration</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <fieldset class="w450p mar0a">
                                        <legend class="fbld">Faculty Registration</legend>
                                        <s:url id="Univer" action="UniversityAct" namespace="/Dropdown"/> 
                                        <s:url id="program" action="ProgramAct" namespace="/Dropdown"/> 
                                        <s:form action="FacultyReg" method="post" name="Student">
                                            <table width="90%" class="mar0a" cellpadding="2" cellspacing="2" border="0" align="center">
                                                <td width="30%">&nbsp;</td><td width="60%"><span class="status"></span></td>
                                                <s:textfield cssClass="width220 emailId" name="emailId" label="Email Id"/>
                                                <s:password cssClass="width220" name="password" label="Create a Password"/>
                                                <s:password cssClass="width220" name="rePassword" label="Confirm Password" />
                                                <s:hidden name="role" value="faculty"/>
                                                <s:textfield cssClass="width220" name="fname" label="First Name"/>
                                                <s:textfield cssClass="width220" name="mname" label="Middle Name"/>
                                                <s:textfield cssClass="width220" name="lname" label="Last Name" />
                                                <s:hidden value="occupation"/>
                                                <sj:select 
                                                    href="%{Univer}" 
                                                    id="univCode" 
                                                    onChangeTopics="reloadprogrammlist" 
                                                    name="instituteId" 
                                                    list="univList" 
                                                    emptyOption="false" 
                                                    headerKey="-1" 
                                                    headerValue="Please Select University"
                                                    label="University"
                                                    sortable="false"
                                                    />
                                                <sj:select 
                                                    href="%{program}" 
                                                    id="programe" 
                                                    formIds="FacultyReg" 
                                                    reloadTopics="reloadprogrammlist" 
                                                    name="programmeId" 
                                                    list="programmeL" 
                                                    emptyOption="false" 
                                                    headerKey="-1" 
                                                    headerValue="Please Select a Programme"
                                                    label="Programme"
                                                    />
                                                <s:textfield cssClass="width220" name="univRegNo" label="Employee Id" />
                                                <tr>
                                                    <td colspan="2" align="center">
                                                        <s:submit theme="simple" value="Submit" />
                                                        <s:reset theme="simple" value="Reset"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        <br/>
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
        <!--Footer Section Starts Here-->
        <div class="footer">
            <div class="f_menu"> <a href="<s:url value="/About.jsp"/>" target="_Blank">About Us</a> | <a href="#">Sitemap</a> | <a href="<s:url value="/Feedback.jsp"/>" target="_Blank">Feedback</a> | <a href="<s:url value="/Help.jsp"/>" target="_Blank">Help</a> | <a href="<s:url value="/Contact.jsp"/>" target="_Blank">Contact Us</a> </div>
        </div>
        <div class="footer_panel">
            <div class="footer_txt">
                <div class="wau fl-l tl">Copyright 2008 IGNOU, MHRD. All right reserved</div>
                <div class="wau fl-r tr">Developed &amp; Maintained By: eGyanKosh Team, IGNOU</div>
            </div>
            <!--Footer Section Ends Here-->
        </div>
    </body>
</html>
