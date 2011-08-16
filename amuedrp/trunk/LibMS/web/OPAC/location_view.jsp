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
     List<Location> loc=( List<Location>)request.getAttribute("loc");


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
<%if(loc!=null){ %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
   
        <table dir="<%=rtl%>" align="<%=align%>">
        <%if(loc.size()==0){%> <%=resource.getString("global.norecordfound")%><%}else{%>

        <tr bgcolor="#CCDD66" dir="<%=rtl%>"><td dir="<%=rtl%>"><%=resource.getString("opac.locationview.locid")%></td><td><%=resource.getString("opac.locationview.locname")%></td></tr>

         <% for(int i=0;i<loc.size();i++ ){%>
        
        <tr bgcolor="#AADD88" dir="<%=rtl%>">
                <td  align="<%=align%>" dir="<%=rtl%>" class="heading"><p dir="<%=rtl%>" align="justify"><%=loc.get(i).getId().getLocationId()%></p></td>
                <td  align="<%=align%>" dir="<%=rtl%>" class="heading"><%=loc.get(i).getLocationName()%></td>
            </tr>
         <%}%>
         <%}%>
         </table>
     
    </body>
</html>
<%}%>
