<%-- 
    Document   : journalinput
    Created on : Oct 22, 2004, 8:10:43 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.myapp.struts.opac.OpacDoc"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
<%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
 <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="Iqubal Ahmad" content="MCA,VBU">
        <title>Journal Search.....</title>

        <style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
  <style>
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }
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
    document.Form1.action="JournalAction.do";
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



    </head>

    <body onload="funcSearch()">
         <%if(page.equals(true)){%>

        <form method="post" action="JournalAction.do" target="f1" name="Form1">
             <table  align="left" width="1200x" class="datagrid"   style="border:solid 1px #e0e8f5;" class="txt">



  <tr class="header"><td  width="1000px" height="25px"  align="center">


		Journal Search




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td>library  <select name="CMBLib" size="1" onchange="funcSearch()" id="CMBLib">
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
      </td></tr>
    <tr style="background-color:#e0e8f5;"><td colspan="2">
          <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>
        </td>
    </tr></table>


        


  
        </form>

 


<%}else{%>

  <form method="post" action="JournalAction.do" target="f1" name="Form1">
             <table  align="left" width="1200x" class="datagrid"  style="border:solid 1px #e0e8f5;" class="txt">



  <tr class="header"><td  width="1000px" height="25px"  align="center">


		<%=resource.getString("opac.journals.library")%>




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td align="left">  <select name="CMBLib" size="1" onchange="funcSearch()" id="CMBLib">
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

</select>library
      </td></tr>
    <tr style="background-color:#e0e8f5;"><td colspan="2">
          <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>
        </td>
    </tr></table>






        </form>
   
   
   
   <%}%>

    </body>
</html>
