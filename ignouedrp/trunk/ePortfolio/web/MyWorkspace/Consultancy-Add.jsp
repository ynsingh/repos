<%-- 
    Document   : Consultancy-Add
    Created on : Dec 19, 2011, 2:45:58 PM
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
        <title>Add Consultancy</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        <script type="text/javascript">
            if(window.history.forward(1) != null)
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
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="ShowConsultancy">Consultancy Offered</a> &nbsp;> Add Consultancy Offered </div>
                                    <div class="w100 fl-l mart10">
                                        <s:form action="AddConsultancy" method="post" namespace="/MyWorkspace" name="" theme="simple">
                                            <fieldset class="w550p mar0a">
                                                <legend class="fbld">Consultancy Add</legend>
                                                <table border="0" class="mar0a" cellpadding="2" cellspacing="0" width="90%">
                                                    <tr>
                                                        <td width="30%">Name of the Client</td>
                                                        <td width="50%"><s:textfield name="clientName"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Duration:</td>
                                                        <td>From&nbsp;
                                                            <sj:datepicker readonly="true"  id="date0" name="DFrom" cssClass="w70p" changeMonth="true" changeYear="true"/>
                                                            &nbsp;To&nbsp;
                                                            <sj:datepicker readonly="true"  id="date1" name="DTo" cssClass="w70p" changeMonth="true" changeYear="true"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>No of Co-Consultancy involved, if any</td>
                                                        <td><s:select id="mymenu" name="noOfConsultancy" nchange="updatefields()" list="{'1','2','3','4','5','6','7','8','9','10'}" headerKey="0" headerValue="Select No. of Co-Consultancy"/></td>
                                                    </tr>
                                                    <script type="text/javascript">
                                                        var selectmenu=document.getElementById("mymenu")
                                                        var i;
                                                        var fieldcont="";
                                                        selectmenu.onchange=function(){ //run some code when "onchange" event fires
                                                            var chosenoption=this.options[this.selectedIndex] //this refers to "selectmenu"
                                                            for(i=0;i<chosenoption.value;i++){
                                                                fieldcont+='<table border="0"><tr><td valign="top" width="205">Name of the Consultancy '+(i+1)+'</td><td><input type="text" name="nameConsultancy['+i+']"/></td></tr>';
                                                                fieldcont+='<tr><td valign="top" width="205">Nature of the Work '+(i+1)+'</td><td><input type="text" name="natureWork['+i+']" /></td></tr>';
                                                            }  
                                                            if(fieldcont){
                                                                document.getElementById("formfields").innerHTML=fieldcont;
                                                                fieldcont="";
                                                            }
                                                        }
                                                    </script>
                                                    <tr>
                                                        <td colspan="2"><div id="formfields"></div></td>
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
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit theme="simple" value="Save" />
                                                                <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                            </td>
                                                        </tr>
                                                </table>
                                            </s:form>
                                            </tr>
                                        </fieldset>
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
