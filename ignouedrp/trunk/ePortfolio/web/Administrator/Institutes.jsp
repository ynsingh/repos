<%-- 
    Document   : Institutes
    Created on : Nov 5, 2012, 1:37:31 PM
    Author     : vinay
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Institutes</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");
               
            }
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
             logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
        %>

        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include value="/Header.jsp"/>
                <!--Header Ends Here-->
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Institutes</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> 
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;Registered Institutes
                                    </div>
                                     <% if (role.contains("admin")) {%>
                                    <div class="w98 maroa tr">
                                       
                                        <a href="InstituteRegistration.jsp">Register New Institute</a>
                                        
                                    </div>
                                     <% } %>
                                    <div class="w100 fl-l tc fbld fcred">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <table width="95%" class="tablepaging" id="tablepaging" cellspacing="0" cellpadding="5" border="1">
                                            <thead>
                                                <tr>
                                                    <td>S.No.</td>
                                                    <td>Institute Name</td>
                                                    <td>Short Name</td>
                                                    <td>Location</td>
                                                    <td>Contact</td>
                                                </tr>
                                            </thead>
                                            <s:iterator value="InsList" status="stat">
                                                <tr>
                                                    <td><s:property value="#stat.count"/></td>
                                                    <td><s:property value="instituteName"/></td>
                                                    <td><s:property value="shortName"/></td>
                                                    <td><s:property value="cityPlace"/>,&nbsp;<s:property value="country"/></td>
                                                    <td>
                                                        <span class="fbld">Phone No:</span> <s:property value="phone1"/>,&nbsp;<s:property value="phone2"/><br/>
                                                        <span class="fbld">Email Id:</span> <s:property value="emailId"/><br/>
                                                        <span class="fbld">URL:</span> <s:property value="website"/>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
