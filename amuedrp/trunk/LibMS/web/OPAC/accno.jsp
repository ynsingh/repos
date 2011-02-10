<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<html><head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>Search by Accession Number...</title>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}
</style>
<script language="javascript">
function fun()
{
document.Form1.action="/accession.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}
function funcSearch()
{
    document.Form1.action="accession.do";
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
session.setAttribute("page_name", "accessionno");
    %>



</head><body>
    <%if(page.equals(true)){%>


    <form  method="post" action="accession.do" target="f1" name="Form1" >
          <table align="left" width="1200x" class="datagrid" height="400px"  style="border:solid 1px #e0e8f5;" >


  <tr class="header"><td  width="800px"  height="28px" align="center" colspan="2">


		Accession No Search




        </td></tr>
   <tr style="background-color:#e0e8f5;"><td width="800px" rowspan="2" >
          <table>
              <tr><td><%=resource.getString("opac.accessionno.enteraccessionno")%></td><td>
                     <input id="TXTKEY" name="TXTKEY" type="text">
<input id="TXTPAGE" value="accessionno" name="TXTPAGE" type="hidden">




                  </td></tr>




          </table>
       </td><td class="header">
           Restricted By

       </td>

    </tr>
    <tr style="background-color:#e0e8f5;" >
          <td    align="left">
          <table>
              <tr><td><%=resource.getString("opac.accessionno.library")%></td><td  valign="top">
<select name="CMBLib" size="1" onchange="funcSearch()" id="CMBLib">
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


     </td>

              </tr></table></td>

    </tr>

    <tr><td>


<input type="submit" id="Button1" name="go"  value="<%=resource.getString("opac.accessionno.go")%>" class="btn" />

<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.simplesearch.clear")%>" class="btn">


<script>
    function back()
    {
        window.location="/LibMS-Struts/OPAC/OPACmain.jsp";

    }
    </script>
      </td></tr>
 <tr style="background-color:#e0e8f5;"><td  height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>


        </table>
    </form>


<%}else{%>

    <form  method="post" action="accession.do" target="f1" name="Form1" >
          <table align="left" width="1200x" class="datagrid" height="400px" class="datagrid"  style="border:solid 1px #e0e8f5;">


  <tr class="header"><td  width="800px"  height="28px" align="center" colspan="2">


		Accession No Search




        </td></tr>
   <tr style="background-color:#e0e8f5;"><td class="header">
           Restricted By

       </td><td width="800px" rowspan="2" align="right">
          <table class="datagrid">
              <tr><td>
                     <input id="TXTKEY" name="TXTKEY" type="text">
<input id="TXTPAGE" value="accessionno" name="TXTPAGE" type="hidden">




                  </td><td><%=resource.getString("opac.accessionno.enteraccessionno")%></td></tr>




          </table>
       </td>

    </tr>
    <tr style="background-color:#e0e8f5;" >
          <td    align="left">
          <table class="datagrid">
              <tr><td><%=resource.getString("opac.accessionno.library")%></td><td  valign="top">
<select name="CMBLib" size="1" onchange="funcSearch()" id="CMBLib">
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


     </td>

              </tr></table></td>

    </tr>

    <tr><td>


<input type="submit" id="Button1" name="go"  value="<%=resource.getString("opac.accessionno.go")%>" />

<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.simplesearch.clear")%>">


<script>
    function back()
    {
        window.location="/LibMS-Struts/OPAC/OPACmain.jsp";

    }
    </script>
      </td></tr>
 <tr style="background-color:#e0e8f5;"><td  height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>

        </table>
    </form>



<%}%>

    </body></html>