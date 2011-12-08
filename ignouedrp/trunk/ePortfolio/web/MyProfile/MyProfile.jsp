<%-- 
    Document   : index
    Created on : Aug 9, 2011, 12:43:49 PM
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

                    <h3>My Profile</h3>

                    <br/><br/><br/>
                    <table border="0" class="default" cellpadding="0" cellspacing="10">
                        <s:url id="PBID" action="ShowBasicInfo" namespace="/MyProfile"/>
                        <s:url id="PCID" action="ShowContactInfo" namespace="/MyProfile"/>
                        <s:url id="PPID" action="ShowPersonalInfo" namespace="/MyProfile"/>
                        <s:url id="PAID" action="ShowAcademic_Info" namespace="/MyProfile"/>
                        <s:url id="PEID" action="ShowEmployeeInfo" namespace="/MyProfile"/>
                        <s:url id="PSKID" action="ShowSkillInfo" namespace="/MyProfile"/>
                        <s:url id="PMCID" action="ShowCertificationInfo" namespace="/MyProfile"/>
                        <s:url id="PTSID" action="ShowTestInfo" namespace="/MyProfile"/>
                        <s:url id="PHAID" action="ShowHonorInfo" namespace="/MyProfile"/>
                        <s:url id="CertificateID" action="ShowCertificateInfo" namespace="/MyProfile"/>
                        <s:url id="TSID" action="ShowScore" namespace="/MyProfile"/>
                        <s:url id="HAID" action="ShowHonor" namespace="/MyProfile"/>
                        <s:url id="ProAID" action="ShowAffiliation" namespace="/MyProfile"/>
                        <tr>
                            <td width="33%"><s:a href="%{PBID}"><img src="<s:url value="/icons/basic.gif"/>" width="60" height="60" /><span>Basic Information</span></s:a></td>
                            <td width="33%"><s:a href="%{PCID}"><img src="<s:url value="/icons/contact.gif"/>" width="60" height="60" /><span>Contact Information</span></s:a></td>
                            <td width="33%"><s:a href="%{PPID}"><img src="<s:url value="/icons/personal.gif"/>" width="60" height="60" /><span>Personal Information</span></s:a></td>
                            </tr>
                            <tr>
                                <td width="33%"><s:a href="%{PAID}"><img src="<s:url value="/icons/academic.gif"/>" width="60" height="60" /><span>Academic Information</span></s:a></td>
                            <td width="33%"><s:a href="%{PEID}"><img src="<s:url value="/icons/employeement-info.gif"/>" width="60" height="60" /><span>Employment Information</span></s:a></td>
                            <td width="33%"><s:a href="%{ProAID}"><img src="<s:url value="/icons/prof-membership.gif"/>" width="60" height="60" /><span>My Professional Affiliation</span></s:a></td>
                            </tr>
                            <tr>
                                <td width="33%"><s:a href="%{PSKID}"><img src="<s:url value="/icons/skillset.gif"/>" width="60" height="60" /><span>My Skills Set</span></s:a></td>
                            <td width="33%"><s:a href="%{TSID}"><img src="<s:url value="/icons/test-score.gif"/>" width="60" height="60" /><span>Test Scores</span></s:a></td>
                            <td width="33%"><s:a href="%{CertificateID}"><img src="<s:url value="/icons/achievement.gif"/>" width="60" height="60" /><span>My Certifications</span></s:a></td>
                            </tr>
                            <tr>                                
                                <td width="33%"><s:a href="%{HAID}"><img src="<s:url value="/icons/goals.gif"/>" width="60" height="60" /><span>Honor or Award</span></s:a></td>
                            <td width="33%"><s:a href="%{}"><img src="<s:url value="/icons/interest.gif"/>" width="60" height="60" /><span>My Interests</span></s:a></td>

                            </tr>
                        </table>          
                        <br/><br/><br/><br/>
                    </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>