
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
        <%--  <tr colspan="2" align="left" style="top:2%; font-family:Tahoma;font-size:15px;"><%=resource.getString("login.message.selectlanguage")%><select name="button" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></tr>--%>

<table style="position:absolute;left:100px;top:50px;width:604px;height:282px;z-index:4;border:2px #FFFFFF solid;" cellpadding="0" cellspacing="1" id="Table1">
 <%if(!page.equals(true)){%>
    <tr>
    <td align="right" valign="top"  style="border:1px #FFFFFF solid;height:49px;">
<font style="font-size:27px"  color="#0099CC" face="Arial"><b><u><%=resource.getString("opacmainframe.opachome.text1")%></u></b></font><font style="font-size:11px" color="#000000" face="Arial"><br>
</font></td>
</tr>
<tr>
    <td align="right" valign="top"  style="border:1px #FFFFFF solid;height:25px;">
<font style="font-size:13px" color="#FF0066" face="Arial"><b><%=resource.getString("opacmainframe.opachome.text2")%>&lt;&lt;</b></font></td>
</tr>

<tr>
<td align="right" valign="top" style="border:1px #FFFFFF solid;height:23px;">
<font style="font-size:13px" color="#990033" face="Arial"><b><%=resource.getString("opacmainframe.opachome.text3")%>&lt;&lt;</b></font></td>
</tr>
<tr>
<td align="right" valign="top" style="border:1px #FFFFFF solid;height:26px;">
<font style="font-size:13px" color="#FF00FF" face="Arial"><b><%=resource.getString("opacmainframe.opachome.text4")%>&lt;&lt;</b></font></td>
</tr>
<tr>
<td align="right" valign="top" style="border:1px #FFFFFF solid;height:26px;">
<font style="font-size:13px" color="#FFCC00" face="Arial"><b><%=resource.getString("opacmainframe.opachome.text5")%>&lt;&lt;</b></font></td>

</tr>
<tr>
    <td align="right" valign="top" style="border:1px #FFFFFF solid;height:19px;">
<font style="font-size:13px" color="#006600" face="Arial"><b><%=resource.getString("opacmainframe.opachome.text6")%>&lt;&lt;</b></font></td>
</tr>
<tr>
<td align="right" valign="top" style="border:1px #FFFFFF solid;height:37px;">
<font style="font-size:13px" color="#FF3300" face="Arial"><b><%=resource.getString("opacmainframe.opachome.text7")%>&lt;&lt</b></font></td>
</tr>

<tr>
<td align="right" valign="top" style="border:1px #FFFFFF solid;height:26px;">
<font style="font-size:13px" color="#66CCFF" face="Arial"><b><%=resource.getString("opacmainframe.opachome.text8")%>&lt;&lt;</b></font></td>
</tr>
<tr>
<td align="right" valign="top" style="border:1px #FFFFFF solid;height:19px;">
<font style="font-size:13px" color="#CC0066" face="Arial"><b><%=resource.getString("opacmainframe.opachome.text9")%>&lt;&lt;</b></font></td>
</tr>

 <%}else{%>

 <tr>
    <td align="left" valign="top"  style="border:1px #FFFFFF solid;height:49px;">
<font style="font-size:27px"  color="#0099CC" face="Arial"><b><u><%=resource.getString("opacmainframe.opachome.text1")%></u></b></font><font style="font-size:11px" color="#000000" face="Arial"><br>
</font></td>
</tr>
<tr>
    <td align="left" valign="top"  style="border:1px #FFFFFF solid;height:25px;">
<font style="font-size:13px" color="#FF0066" face="Arial"><b>&gt;&gt;<%=resource.getString("opacmainframe.opachome.text2")%></b></font></td>
</tr>

<tr>
<td align="left" valign="top" style="border:1px #FFFFFF solid;height:23px;">
<font style="font-size:13px" color="#990033" face="Arial"><b>&gt;&gt;<%=resource.getString("opacmainframe.opachome.text3")%></b></font></td>
</tr>
<tr>
<td align="left" valign="top" style="border:1px #FFFFFF solid;height:26px;">
<font style="font-size:13px" color="#FF00FF" face="Arial"><b>&gt;&gt;<%=resource.getString("opacmainframe.opachome.text4")%></b></font></td>
</tr>
<tr>
<td align="left" valign="top" style="border:1px #FFFFFF solid;height:26px;">
<font style="font-size:13px" color="#FFCC00" face="Arial"><b>&gt;&gt;<%=resource.getString("opacmainframe.opachome.text5")%></b></font></td>

</tr>
<tr>
    <td align="left" valign="top" style="border:1px #FFFFFF solid;height:19px;">
<font style="font-size:13px" color="#006600" face="Arial"><b>&gt;&gt;<%=resource.getString("opacmainframe.opachome.text6")%></b></font></td>
</tr>
<tr>
<td align="left" valign="top" style="border:1px #FFFFFF solid;height:37px;">
<font style="font-size:13px" color="#FF3300" face="Arial"><b>&gt;&gt;<%=resource.getString("opacmainframe.opachome.text7")%></b></font></td>
</tr>

<tr>
<td align="left" valign="top" style="border:1px #FFFFFF solid;height:26px;">
<font style="font-size:13px" color="#66CCFF" face="Arial"><b>&gt;&gt;<%=resource.getString("opacmainframe.opachome.text8")%></b></font></td>
</tr>
<tr>
<td align="left" valign="top" style="border:1px #FFFFFF solid;height:19px;">
<font style="font-size:13px" color="#CC0066" face="Arial"><b>&gt;&gt;<%=resource.getString("opacmainframe.opachome.text9")%></b></font></td>
</tr>

 <%}%>
</table>
    </form>
</body>
</html>
