<%-- 
    Document   : notices_view
    Created on : Apr 1, 2011, 11:45:45 PM
    Author     : edrp02
--%>
<%@ page import="java.util.*,com.myapp.struts.hbm.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
Notices notice=(Notices)session.getAttribute("notice");
%>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%

try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<%if(notice!=null){ %>
<html>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body onload="parent.setIframeHeight();">
        <table class="datagrid" width="100%" style="border: solid 1px black;">

            <tr><td  align="<%=align%>" dir="<%=rtl%>" ><b>Notice Details</b> <br/><hr/><%=notice.getDetail() %></td></tr>
         </table>
    </body>
</html>
<%}%>