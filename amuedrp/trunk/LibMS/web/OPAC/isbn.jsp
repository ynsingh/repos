<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@page  import="java.util.*,java.io.*,java.net.*"%>
<%@page import="java.sql.ResultSet"%>
<html><head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Search by ISBN...</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<script language="javascript">
function fun()
{
/*document.Form1.action="number.jsp";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();*/
}
function funcSearch()
{
    document.Form1.action="SearchByIsbn.do";
   document.Form1.method="post";
    document.Form1.submit();
}
</script>
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


</head><body>
    <%if(page.equals(true)){%>
<div id="wb_Form1" style="position: absolute; background-color: rgb(255, 255, 255); left: 65px; top: 0px; width: 1050px; height: 40px; z-index: 4;">
    <form method="post" action="SearchByIsbn.do" target="f1" name="Form1">
<div id="wb_Text1" style="position: absolute; left: 5px; top: 50px; width: 170px; height: 16px; z-index: 0;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.isbn.enterisbn")%></b></font></div>
<input id="TXTKEY" style="border: 1px solid rgb(192, 192, 192); position: absolute; left: 135px; top: 50px; width: 224px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 1;" name="TXTKEY" type="text">
<input id="TXTPAGE" value="isbn" style="border: 1px solid rgb(192, 192, 192); position: absolute; left: 135px; top: 13px; width: 224px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 1;" name="TXTPAGE" type="hidden">

<input type="submit" id="Button1" name="go" onclick="fun()" value="<%=resource.getString("opac.isbn.go")%>" style="position: absolute; left: 135px; top: 90px; width: 55px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 2;">
<div id="wb_Text6" style="position:absolute;left:52px;top:10px;width:69px;height:16px;z-index:0;" align="left">
    <font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.isbn.library")%>&nbsp;:</b></font></div>
<div style="position:absolute;left:135px;top:10px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
    <select name="CMBLib" size="1" onchange="funcSearch()" id="CMBLib" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");
        rs.beforeFirst();
    %>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="all">ALL</option>
    <%

    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>
</select>
</div>

    </form>
</div>
<IFRAME  name="f1" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;left:24px;height:400px;width:850px;visibility:true;" id="f1" />
<IFRAME  name="f2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:120px;left:670px;height:370px;width:450px;visibility:true;" id="f2"></IFRAME>
<%}else{%>

<div id="wb_Form1" style="position: absolute; background-color: rgb(255, 255, 255); right: 100px; top: 0px; width: 1050px; height: 40px; z-index: 4;">
    <form method="post" action="SearchByIsbn.do" target="f1" name="Form1">
        <div id="wb_Text1" style="position: absolute; right: -1px; top: 50px; width: 130px; height: 16px; z-index: 0;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b>:<%=resource.getString("opac.isbn.enterisbn")%></b></font></div>
<input id="TXTKEY" style="border: 1px solid rgb(192, 192, 192); position: absolute; right: 115px; top: 50px; width: 224px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 1;" name="TXTKEY" type="text">
<input id="TXTPAGE" value="isbn" style="border: 1px solid rgb(192, 192, 192); position: absolute; right: 135px; top: 13px; width: 224px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 1;" name="TXTPAGE" type="hidden">

<input type="submit" id="Button1" name="go" onclick="fun()" value="<%=resource.getString("opac.isbn.go")%>" style="position: absolute; right:  115px; top: 90px; width: 55px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 2;">
<div id="wb_Text6" style="position:absolute;right:70px;top:10px;width:69px;height:16px;z-index:0;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b>:<%=resource.getString("opac.isbn.library")%></b></font></div>
<div style="position:absolute;right:115px;top:10px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
    <select name="CMBLib" size="1" onchange="funcSearch()" id="CMBLib" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");
        rs.beforeFirst();
    %>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="all">ALL</option>
    <%

    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>
</select>
</div>

    </form>
</div>
<IFRAME  name="f1" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;right:24px;height:400px;width:650px;visibility:true;" id="f1" />
<IFRAME  name="f2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:90px;left:0px;height:400px;width:350px;visibility:true;" id="f2"></IFRAME>



<%}%>

    </body>

</html>