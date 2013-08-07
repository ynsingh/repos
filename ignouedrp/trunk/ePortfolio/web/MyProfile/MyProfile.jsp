<%-- 
    Document   : index
    Created on : Aug 9, 2011, 12:43:49 PM
Author     : IGNOU Team
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Profile</title>
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
                            <div class="my_account_bg">My Profile</div>
                            <div class="v_gallery">
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;My Profile
                                </div>
                                <div class="gallery">
                                    <s:url id="PBID" action="ShowBasicInfo" namespace="/MyProfile"/>
                                    <s:url id="PCID" action="ShowContactInfo" namespace="/MyProfile"/>
                                    <s:url id="PPID" action="ShowPersonalInfo" namespace="/MyProfile"/>
                                    <s:url id="DEIPPID" action="GetRemotePersonalInfo" namespace="/MyProfile"/>
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
                                    <s:url id="InterestID" action="ShowInterestInfo" namespace="/MyProfile"/>
                                    <s:url id="PPS" action="ShowProfilePicture" namespace="/MyProfile"/>
                                    <ul class="jcarousel-skin-tango">
                                        <li><s:a href="%{PPID}"><img src="<s:url value="/icons/personal.gif"/>" width="60" height="60" /><span>Personal Information</span></s:a></li>
                                      <!--   <li><s:a href="%{DEIPPID}"><img src="<s:url value="/icons/personal.gif"/>" width="60" height="60" /><span>DEI Personal Information</span></s:a></li> -->
                                        <li><s:a href="%{PCID}"><img src="<s:url value="/icons/contact.gif"/>" width="60" height="60" /><span>Contact Information</span></s:a></li>
                                        <!-- <li><s:a action="GetRemoteContactInfo"><img src="<s:url value="/icons/contact.gif"/>" width="60" height="60" /><span>DEI Contact Information</span></s:a></li> -->
                                        <li><s:a href="%{PAID}"><img src="<s:url value="/icons/academic.gif"/>" width="60" height="60" /><span>Academic Information</span></s:a></li>
                                        <li><s:a href="%{PEID}"><img src="<s:url value="/icons/employeement-info.gif"/>" width="60" height="60" /><span>Employment Information</span></s:a></li>
                                        <li><s:a href="%{ProAID}"><img src="<s:url value="/icons/prof-membership.gif"/>" width="60" height="60" /><span>My Professional Affiliation</span></s:a></li>
                                        <li><s:a href="%{PSKID}"><img src="<s:url value="/icons/skillset.gif"/>" width="60" height="60" /><span>My Skills Set</span></s:a></li>
                                        <li><s:a href="%{TSID}"><img src="<s:url value="/icons/test-score.gif"/>" width="60" height="60" /><span>Test Scores</span></s:a></li>
                                        <li><s:a href="%{CertificateID}"><img src="<s:url value="/icons/achievement.gif"/>" width="60" height="60" /><span>My Certifications</span></s:a></li>
                                        <li><s:a href="%{HAID}"><img src="<s:url value="/icons/goals.gif"/>" width="60" height="60" /><span>Honors or Awards</span></s:a></li>
                                      <!--  <li><s:a href="%{PPS}"><img src="<s:url value="/icons/profile.jpg"/>" width="60" height="60" /><span>Profile Picture</span></s:a></li>
                                            -->
                                            <li><s:a href="%{InterestID}"><img src="<s:url value="/icons/interest.gif"/>" width="60" height="60" /><span>My Interests</span></s:a></li>

                                        </ul>
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
