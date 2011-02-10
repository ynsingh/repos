
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>OPAC</title>
<style type="text/css">

body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>
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
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
    <script language="javascript">
        function fun()
        {

            document.form1.submit();
        }
    </script>


</head>

<body>


    <form method="post" action="opachomeRTL.jsp" name="form1">

      


 <%if(!page.equals(true)){%>
 <table width="100%"><tr><td align="right">

<font style="font-size:20px"  color="#0099CC" face="Arial"><b>Please Login from LibMS Homepage for this Facility</b></font></td></tr></table>

 <%}else{%>
<table width="100%"><tr><td align="left">
 
<font style="font-size:20px"  color="#0099CC" face="Arial"><b>Please Login from LibMS Homepage for this Facility</b></font>
        </td></tr></table>
<%}%>
    </form>
</body>
</html>
