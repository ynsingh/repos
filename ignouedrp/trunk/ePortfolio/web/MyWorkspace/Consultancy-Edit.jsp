<%-- 
    Document   : Consultancy-Edit
    Created on : 25 Dec, 2011, 5:17:58 PM
    Author     : IGNOU Team
    Version    : 1
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
        <title>Edit Consultancy</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
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
                            <div class="my_account_bg">Add Consultancy</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum">
                                            <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="ShowConsultancy">Consultancy Offered</a> &nbsp;> Edit Consultancy Offered
                                        </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w550p mar0a">
                                                <legend class="fbld">Edit Consultancy</legend>
                                                <s:form action="updateConsultancy" method="post" namespace="/MyWorkspace" theme="simple">
                                                    <table border="0" class="mar0a" cellpadding="2" cellspacing="0" width="95%">  
                                                        <s:iterator value="ConsultList" var="Consult">
                                                            <s:hidden name="consultancyId"/>
                                                            <s:hidden name="userId"/>
                                                            <tr>
                                                                <td>Name of the Client</td>
                                                                <td><s:textfield name="clientName"/> </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Duration:</td>
                                                                <td>From&nbsp;
                                                                    <sj:datepicker readonly="true"  id="date0" cssClass="w70p" value="%{DFrom}" name="DFrom" changeMonth="true" changeYear="true"/>
                                                                    &nbsp;To&nbsp;
                                                                    <sj:datepicker readonly="true"  id="date1" cssClass="w70p" value="%{DTo}" name="DTo" changeMonth="true" changeYear="true"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>No of Co-Consultancy involved, if any</td>
                                                                <td><s:select id="mymenu" name="noOfConsultancy" nchange="updatefields()" list="{'1','2','3','4','5','6','7','8','9','10'}" headerKey="0" headerValue="Select No. of Co-Consultancy" disabled="true"/></td>
                                                            </tr>
                                                            <s:iterator value="consultancyNatures">
                                                                <tr>
                                                                    <td>Name of the Consultancy</td>
                                                                    <td><s:textfield name="nameConsultancy" label=" Name of the Consultancy" disabled="true"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Nature of the Work</td>
                                                                    <td><s:textfield name="natureWork" label="Nature of the Work" disabled="true"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                            <tr>
                                                                <td>Revenue Generated</td>
                                                                <td><s:textfield name="revenue"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Service's Offered</td>
                                                                <td><s:textarea name="service"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>URL, if any</td>
                                                                <td><s:textfield name="url"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Summary</td>
                                                                <td><s:textarea name="summary"/></td>
                                                            </tr>
                                                        </s:iterator><tr>
                                                            <td>&nbsp;</td>
                                                            <td>
                                                                <s:submit theme="simple" value="Save Changes" />
                                                                <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                        </div>
                                    </div>
                                    <!--Right box End Here-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>  
    </body>
</html>