

 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Page</title>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<body>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<table align="center">
      <%if(page.equals(true)){%>
      <tr><td colspan="2" align="center" height="50px" valign="bottom">

                    <b><span style="font-family: Tahoma;font-size:14px">&copy; <%=resource.getString("login.message.footer")%></span></b>
                </td></tr>


        <%}else{%>
        <tr><td colspan="2" align="center" height="50px" valign="bottom">

                    <b><span style="font-family: Tahoma;font-size:14px">&copy; <%=resource.getString("login.message.footer")%></span></b>
                </td></tr>


        <%}%>
</table>

</body></html>
