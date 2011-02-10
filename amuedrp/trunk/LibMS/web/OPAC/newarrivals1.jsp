<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>New Arrivals.....</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
.rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}
</style>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
    <script language="javascript">
function fun()
{
document.form1.action = "NewArrival.do"
document.form1.method="post";
document.form1.target="f1";
document.form1.submit();
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


</head>

<body onload="fun()">
    <%if(page.equals(true)){%>


    <form name ="form1" method="post" action="/NewArrival">
          <table  align="left" width="1200x" class="datagrid"  style="border:solid 1px #e0e8f5;" class="txt">



  <tr class="header"><td  width="1000px" height="25px"  align="center">


		New Arrivals




        </td></tr>
  <tr><td align="center" width="1200px" height="15px">
          <table class="datagrid">
              <tr><td width="150px">
<%=resource.getString("opac.newarrivals.library")%>

    <select name="CMBLib" size="1" onchange="fun()" id="CMBLib">

    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");

        rs.beforeFirst();

    if(lib_id==null)
    {%>

    <option selected value="all">ALL</option>
    <%}
    else
    {%>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="all">ALL</option>

    <%
    }
    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>




</select>

                  </td><td width="500px" align="center">

                      <input type="radio" id="RadioButton1" onclick="fun()" checked name="r" value="book" > <%=resource.getString("opac.newarrivals.books")%>
<input type="radio" id="RadioButton2" name="r" onclick="fun()" value="journal"><%=resource.getString("opac.newarrivals.journals")%>
<input type="radio" id="RadioButton3" name="r" onclick="fun()" value="others"><%=resource.getString("opac.newarrivals.other")%>
                  </td><td width="300px">
     <%=resource.getString("opac.newarrivals.selectperiod")%><select name="CMBPERIOD" onChange="fun()" size="1">
<option value="2">within 2 months</option>
<option value="6">within 6 months</option>
<option value="12">within 1 year</option>
</select>
                  </td></tr></table>


      </td></tr>

<tr style="background-color:#e0e8f5;"><td  height="400px" valign="top"  >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>
  

      </td>
      

</tr>
      
          </table>




















    </form>


<%}else{%>
 
 <form name ="form1" method="post" action="/NewArrival">
          <table  align="left" width="1200x" class="datagrid"   style="border:solid 1px #e0e8f5;" class="txt">



  <tr class="header"><td  width="1000px" height="25px"  align="center">


		New Arrivals




        </td></tr>
  <tr><td align="center" width="1200px" height="15px">
          <table class="datagrid">
              <tr><td width="300px">
     <%=resource.getString("opac.newarrivals.selectperiod")%><select name="CMBPERIOD" onChange="fun()" size="1">
<option value="2">within 2 months</option>
<option value="6">within 6 months</option>
<option value="12">within 1 year</option>
</select>
                  </td><td width="500px" align="center">

 <input type="radio" id="RadioButton1" onclick="fun()" name="r" value="book" > <%=resource.getString("opac.newarrivals.books")%>
<input type="radio" id="RadioButton2" name="r" onclick="fun()" value="journal"><%=resource.getString("opac.newarrivals.journals")%>
<input type="radio" id="RadioButton3" name="r" onclick="fun()" value="others"><%=resource.getString("opac.newarrivals.other")%>
                  </td><td width="150px">
<%=resource.getString("opac.newarrivals.library")%>

    <select name="CMBLib" size="1" onchange="fun()" id="CMBLib">

    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");

        rs.beforeFirst();

    if(lib_id==null)
    {%>

    <option selected value="all">ALL</option>
    <%}
    else
    {%>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="all">ALL</option>

    <%
    }
    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>




</select>

                  </td></tr></table>


      </td></tr>

<tr style="background-color:#e0e8f5;"><td  height="400px" valign="top"  >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td>


</tr>

          </table>




















    </form>
  <%}%>

</body>
</html>
