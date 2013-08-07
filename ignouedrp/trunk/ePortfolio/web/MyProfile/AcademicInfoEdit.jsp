<%-- 
   Document   : AcademicInfoEdit
   Created on : Sep 4, 2011, 2:33:49 PM
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
        <title>Edit  Academic Information</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />        
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
        <script type="text/javascript">
            if (window.history.forward(1) != null)
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
                            <div class="my_account_bg">Edit Academic Information</div>
                            <div class="v_gallery">
                                <div class="bradcum"><a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > <a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > <a href="ShowAcademic_Info">Academic Information</a> > Edit Academic Information</div> 
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" class="mar0a" cellpadding="2" border="1" cellspacing="0">
                                            <tr>
                                                <th>Degree/Programme</th>
                                                <th>Specialization</th>
                                                <th>Board/University/Institute</th>
                                                <th>Passing Year</th>
                                                <th>Percentage</th>
                                                <th>Grade</th>
                                                <th>Division</th>
                                            </tr>
                                            <s:form action="updateAcademicInformation" method="post"  theme="simple">
                                                <s:iterator value="editAcademicList" var="ProfileAcademic">
                                                    <s:hidden name="academicInfoId"/> <s:hidden name="userId"/>
                                                    <tr>
                                                        <td><s:textfield name="degree" style="width: 115px;"/></td>
                                                        <td><s:textfield name="fstudy" style="width: 81px;"/></td>
                                                        <td><s:textfield name="university" style="width: 163px;"/></td>   
                                                        <td><s:textfield name="pyear" style="width: 66px;"/></td>
                                                        <td><s:textfield name="percent" style="width: 68px;"/></td>
                                                        <td><s:textfield name="location" style="width: 51px;"/></td>
                                                        <td><s:textfield name="division" style="width: 46px;"/></td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                            <table width="30%" class="mar0a mart10" cellpadding="0" border="0" cellspacing="0">
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td><s:submit value="Save Changes" />
                                                        <s:reset value="Cancel" onClick="history.go(-1);" /></td></tr>
                                            </table>
                                        </s:form>
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
