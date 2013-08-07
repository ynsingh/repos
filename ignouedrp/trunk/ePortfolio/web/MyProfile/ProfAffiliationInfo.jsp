<%-- 
    Document   : ProfAffiliationInfo
    Created on : Oct 14, 2011, 11:08:40 AM
Author     : IGNOU Team
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Professional Affiliation</title>
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
                            <div class="my_account_bg">Professional Affiliation</div>
                            <div class="v_gallery">
                                <div class="bradcum"><a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Professional Affiliation</div>
                                <div class="w100 fl-l">
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="wau fl-r mart10"><a href="ProfAffiliationInfoAdd.jsp"><img src=" <s:url value="/icons/add.gif"/>" title="Add Qualification"/></a>
                                    </div>
                                </div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l">
                                        <table width="100%" class="fl-l" cellpadding="4" border="1" cellspacing="0"> 
                                            <tr>
                                                <th>S.No</th>
                                                <th>Role</th>
                                                <th>Organization/Body</th>
                                                <th >Duration</th>
                                                <th>Place</th>
                                                <th>Country</th>
                                                <th >Summary</th>
                                                <th>Edit</th>
                                                <th>Delete</th>
                                            </tr>
                                            <s:iterator value="AffiliationList" var="ProfAffili" status="stat">
                                                <tr>  
                                                    <td align="center"><s:property value="%{#stat.count}"/></td>
                                                    <td>
                                                        <s:property value="role"/>
                                                    </td><td>
                                                        <s:property value="orgBody"/>
                                                    </td><td>
                                                        <s:property value="vfrom"/>&nbsp;-&nbsp;<s:property value="vupto"/>
                                                    </td><td>
                                                        <s:property value="place"/>
                                                    </td><td>
                                                        <s:property value="country"/>
                                                    </td><td>
                                                        <s:generator separator=" " val="%{summary}" count="8">
                                                            <s:iterator >
                                                                <s:property/>
                                                            </s:iterator>
                                                        </s:generator>
                                                        <sj:dialog  id="%{#stat.count}" 
                                                                    autoOpen="false" 
                                                                    modal="true" 
                                                                    width="300"
                                                                    height="175"
                                                                    >
                                                            <s:property value="summary" escape="false"/>
                                                        </sj:dialog>
                                                        <sj:a 
                                                            openDialog="%{#stat.count}" 
                                                            button="false"
                                                            cssClass="cursor"
                                                            >
                                                            more...
                                                        </sj:a>
                                                    </td>
                                                    <td valign="middle" style="vertical-align:middle;" align="center">
                                                        <a href="editAffiliationInfo?proAffiliationId=<s:property value="proAffiliationId"/>">
                                                            <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/>
                                                        </a></td>
                                                    <td><a href="deleteAffiliationInfo?proAffiliationId=<s:property value="proAffiliationId"/>" onclick="return confirm('Are you sure you want to delete this record')">
                                                            <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/>
                                                        </a></td>                                                        
                                                </tr>
                                            </s:iterator>
                                        </table>
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
