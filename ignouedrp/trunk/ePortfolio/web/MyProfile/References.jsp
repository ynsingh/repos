<%-- 
    Document   : References
    Created on : Oct 11, 2011, 1:20:34 PM
    Author     : IGNOU Team
    Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>References</title>
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
                            <div class="my_account_bg">My References</div>
                            <div class="v_gallery">
                                <div class="bradcum"><a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > References</div>
                                <div class="w100 fl-l">
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="wau fl-r mart10"><a href="<s:url value="ReferencesInfoAdd.jsp"/>"><img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Reference"/></a></div>
                                </div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" class="fl-l" cellpadding="4" border="1" cellspacing="0">   
                                            <tr>
                                                <th width="10%">S. No</th>
                                                <th width="20%">Name &amp; Designation</th>
                                                <th width="50%">Address, Email ID, Phone No.</th>
                                                <th width="10%">Edit</th>
                                                <th width="10%">Delete</th>                                                        
                                            </tr>
                                            <s:iterator value="RefList" var="ProRef" status="stat">
                                                <tr>
                                                    <td align="center" valign="top"><s:property value="%{#stat.count}"/></td>
                                                    <td align="left" valign="top"><strong>
                                                            <s:property value="name"/>
                                                        </strong><br/>
                                                        <s:property value="designation"/></td>
                                                    <td>
                                                        <s:property value="department"/><br/>
                                                        <s:property value="orgUniv"/><br/>
                                                        <s:property value="place"/>
                                                        <s:property value="city"/>
                                                        <s:property value="state"/>
                                                        <s:property value="country"/><br/>
                                                        <s:property value="phoneno"/>
                                                        <s:property value="mobileno"/><br/>
                                                        <s:property value="emailId"/><br/>
                                                        <s:property value="website"/>
                                                    </td>
                                                    <td valign="top" align="center">
                                                        <a href="editReference?referencesId=<s:property value="referencesId"/>">
                                                            <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/>
                                                        </a></td>
                                                    <td valign="top" align="center">
                                                        <a href="deleteReference?referencesId=<s:property value="referencesId"/>" onclick="return confirm('Are you sure you want to delete this record')">
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
