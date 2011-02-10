
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<title>Browsing.... </title>
<%!
    static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
  .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}

</style>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
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

document.Form1.action="browse.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}

</script>
</head>
<body onload="fun()">
    <%if(page.equals(true)){%>
    <form action="/browse.do"  name="Form1">
   <table  align="left" width="1200x" class="datagrid" style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px"  height="28px" align="center" colspan="2">


		Browse Search




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px"  >
          <table>
              <tr><td ><%=resource.getString("opac.browse.enterstartingkeyword")%></td><td><input  name="TXTTITLE" type="text" onkeyup="fun()"></td></tr>
             

          </table>
      </td>
      <td    align="left" valign="top">
          <table >
              <tr><td>in <%=resource.getString("opac.browse.field")%> </td><td rowspan="2" valign="top">


<select name="CMBFIELD" onChange="fun()" size="1">
<option value="any field" selected>ANY FIELD</option>
<option value="author_main">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="publisher_name">PUBLISHER</option>

</select>
     </td>

              </tr></table></td></tr>
  <tr class="header"><td width="1000px"   align="left" >Restricted By</td><td align="left">Sort By</td></tr>
   <tr style="background-color:#e0e8f5;"><td width="800px"   align="left">
           <table  width="800px" ><tr><td align="500px">
          <table>
              <tr><td ><%=resource.getString("opac.browse.database")%></td><td>
                    <select name="CMBDB" onChange="fun()" size="1">
<option selected value="combined">COMBINED</option>
    <option value="book">BOOKS</option>
     <option value="cd">CDs</option>
  
  
</select>
                  </td><td ><%=resource.getString("opac.simplesearch.library")%></td><td><select name="CMBLib" onChange="fun()"  size="1" id="CMBLib">
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
</select></td>
</tr>
            
          </table>
                   </td><td align="left">
                      




                   </td></tr></table>
      </td>
      <td align="left">
           <table>
                           <tr><td><%=resource.getString("opac.browse.title")%></td><td> <select name="CMBSORT" size="1" onChange="fun()" id="CMBSORT">
<option value="title">TITLE</option>
<option  value="author_main">AUTHOR</option>
<option value="isbn10">ISBN</option>
<option value="publisher_name">PUBLISHER</option>
</select>
</td>
                           </tr></table>


      </td>

  </tr>
  <tr><td>


<input type="submit" id="Button1" name="" class="btn" value="FIND">
<input type="reset" id="Button2" name="" class="btn" value="CLEAR">


      </td></tr>
 <tr><td  height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1"  src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>
      

       </table>
   

        

    


 </form>


 <%}else{%>
  <form action="/browse.do"  name="Form1">

        <table  align="left" width="1200x" class="datagrid" style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px"   align="center" colspan="2">


		Browse Search




        </td></tr>
  <tr style="background-color:#e0e8f5;">
      <td    align="left" width="200px" valign="top">
          <table width="200px">
              <tr><td>in Field </td><td rowspan="2" valign="top">
<select name="CMBFIELD" onChange="fun()" size="1">
<option value="any field" selected>ANY FIELD</option>
<option value="author_main">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="publisher_name">PUBLISHER</option>

</select>
        
     </td>

              </tr></table></td>
  <td align="right">
          <table>
              <tr><td >Keyword</td><td><input  name="TXTTITLE" type="text" onkeyup="fun()"></td></tr>
             

          </table>
      </td>
  </tr>
  <tr class="header"><td align="right">Sort By</td><td width="1000px"   align="right" >&nbsp;Restricted By</td></tr>
    <tr style="background-color:#e0e8f5;">

      <td align="left">
           <table>
                           <tr><td>Field</td><td> <select name="CMBSORT" size="1" id="CMBSORT">
<option  value="author_main">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="publisher_name">PUBLISHER</option>
</select></td>
                           </tr></table>


      </td>
  
                <td align="500px" align="right">
          <table>
              <tr><td ><%=resource.getString("opac.simplesearch.database")%></td><td>
                      <select name="CMBDB" size="1" id="CMBDB">
<option value="combined" selected>COMBINED</option>
    <option value="book">BOOKS</option>
    <option value="cd">CDs</option>

</select>
                  </td><td ><%=resource.getString("opac.simplesearch.library")%></td><td>
                  <select name="CMBLib" size="1" id="CMBLib" onChange="fun()">
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
</tr>
                   
          </table>
                   </td>
               </tr></table>
     

  <tr><td colspan="2" align="right">



<input type="reset" id="Button2" class="btn" name="" value="CLEAR">
<input type="submit" id="Button1" class="btn" name="" value="FIND">

      </td></tr>

 
  
<tr><td  height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1"  src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>

       </table>







 </form>
 <%}%>
 
</body>
</html>