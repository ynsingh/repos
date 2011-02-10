

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
.rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;text-decoration: none;padding-left: 10px; }
  .header1        { font-family:Tahoma;font-size: 12px;text-decoration: none;padding-left: 10px; }
      .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}
</style>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
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
    <form method="post" action="SearchByIsbn.do" target="f1" name="Form1">
        <table align="left" width="1200x" height="400px" class="datagrid"  style="border:solid 1px #e0e8f5;">


  <tr class="header"><td  width="800px"  height="28px" align="center" colspan="2">


		ISBN Search




        </td></tr>
   <tr style="background-color:#e0e8f5;">
       <td width="800px" rowspan="2" >
          <table class="datagrid">
              <tr><td ><%=resource.getString("opac.isbn.enterisbn")%></td><td>
                      <input id="TXTKEY"  name="TXTKEY" type="text">
<input id="TXTPAGE" value="isbn"  name="TXTPAGE" type="hidden">


                  </td></tr>




          </table>
       </td>
       <td class="header">
           Restricted By

       </td>
        
    </tr>
    <tr style="background-color:#e0e8f5;">
        <td    align="left" colspan="2">
          <table class="datagrid">
              <tr><td>in Library ID </td><td  valign="top">
 
<select name="CMBLib" onchange="funcSearch()" size="1" id="CMBLib">
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


<input type="submit" id="Button1" class="btn" name="" value="<%=resource.getString("opac.simplesearch.find")%>">
<input type="reset" id="Button2" name="" class="btn" value="<%=resource.getString("opac.simplesearch.clear")%>">



      </td></tr>
    <tr style="background-color:#e0e8f5;"><td  height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>
      



        </table>



    </form>
<%}else{%>
 <form method="post" action="SearchByIsbn.do" target="f1" name="Form1">
   <table align="left" width="1200x" class="datagrid" height="400px"  style="border:solid 1px #e0e8f5;">


  <tr class="header"><td  width="800px"  height="28px" align="center" colspan="2">


		ISBN Search




        </td></tr>
   <tr style="background-color:#e0e8f5;">
       <td class="header">
           Restricted By

       </td>
       <td width="800px" rowspan="2" align="right">
          <table class="datagrid">
              <tr><td>
                    <input id="TXTKEY"  name="TXTKEY" type="text">
<input id="TXTPAGE" value="isbn"  name="TXTPAGE" type="hidden">



                  </td><td><%=resource.getString("opac.isbn.enterisbn")%></td></tr>




          </table>
       </td>

    </tr>
    <tr style="background-color:#e0e8f5;" >
          <td    align="right">
          <table class="datagrid">
              <tr><td  valign="top">
        <select name="CMBLib" onchange="funcSearch()" size="1" id="CMBLib">
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

           <td>Library</td>   </tr></table></td>

    </tr>

    <tr><td align="right" colspan="2">

<input type="reset" id="Button2" class="btn" name="" value="<%=resource.getString("opac.simplesearch.clear")%>">
<input type="submit" id="Button1" class="btn" name="" value="<%=resource.getString("opac.simplesearch.find")%>">



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

    </body>

</html>