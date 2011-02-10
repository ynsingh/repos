<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Additional Search...</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<style type="text/css">
a:active
{
   color: #0000FF;
}
.rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}

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
    <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<SCRIPT language="JAVASCRIPT">
    function f()
    {
       if(document.F1.CMBYR.value=="upto" || document.F1.CMBYR.value=="after")
         {
            document.F1.TXTYR1.disabled=false;
            document.F1.TXTYR1.style.backgroundColor = "#ffffff";
            document.F1.TXTYR2.disabled=true;
            document.F1.TXTYR2.style.backgroundColor = "#eeeeee";

	   }
	 if(document.F1.CMBYR.value=="all")
         {
            document.F1.TXTYR1.disabled=true;
            document.F1.TXTYR1.style.backgroundColor = "#eeeeee";
            document.F1.TXTYR2.disabled=true;
            document.F1.TXTYR2.style.backgroundColor = "#eeeeee";

	   }
        if(document.F1.CMBYR.value=="between")
         {
            document.F1.TXTYR1.disabled=false;
            document.F1.TXTYR1.style.backgroundColor = "#ffffff";
            document.F1.TXTYR2.disabled=false;
            document.F1.TXTYR2.style.backgroundColor = "#ffffff";

	   }

    }
    function validate()
    {
        if(document.getElementById('CMBYR').value=="between")
            {
                if((document.getElementById('TXTYR1').value=="") || (document.getElementById('TXTYR2').value==""))
                {
                    alert("Please specify year");
                    return false;
                }

            }
          if((document.getElementById('CMBYR').value=="upto")||(document.getElementById('CMBYR').value=="after"))
          {
              if(document.getElementById('TXTYR1').value==""){
                  alert("Please specify year");
                  return false;}
          }
               return true;
    }
</SCRIPT>

</head>
<body>
    <%if(page.equals(true)){%>
    <form name="F1" method="post" action="additional.do" onsubmit="return validate();">
       <table  align="left" width="1200x" class="datagrid" style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px"  height="28px" align="center" colspan="2">


	   <%=resource.getString("opac.additional.additionalsearchtext")%>




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px"  >
          <table>
              <tr><td ><%=resource.getString("opac.additional.author")%></td><td><input type="text" id="TXTAUTHOR"  name="TXTAUTHOR"></td></tr>
              <tr><td ><%=resource.getString("opac.additional.title")%></td><td><input type="text" id="TXTTITLE" name="TXTTITLE"></td></tr>
              <tr><td ><%=resource.getString("opac.additional.subject")%></td><td><input type="text" id="TXTSUBJECT"  name="TXTSUBJECT"></td></tr>
              <tr><td ><%=resource.getString("opac.additional.otherfield")%></td><td><input type="text" id="TXTOTHER"  name="TXTOTHER"></td></tr>


    

          </table>
      </td>
      <td    align="left">
          <table >
              <tr><td> <%=resource.getString("opac.additional.connectas1")%> </td><td  valign="top">

         <select name="CMBCONN1" size="1" id="CMBCONN1" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
     </td>

              </tr>
               <tr><td><%=resource.getString("opac.additional.connectas2")%> </td><td  valign="top">

         <select name="CMBCONN2" size="1" id="CMBCONN2" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
     </td>

              </tr>
           <tr><td><%=resource.getString("opac.additional.connectas3")%></td><td  valign="top">

          <select name="CMBCONN3" size="1" id="CMBCONN3">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
     </td>

              </tr>
           <tr><td><%=resource.getString("opac.additional.connectas4")%> </td><td  valign="top">

        <select name="CMBCONN4" size="1" id="CMBCONN4" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
     </td>

              </tr>




          </table></td></tr>
  <tr class="header"><td width="1000px"   align="left" >Restricted By</td><td align="left">Sort By</td></tr>
   <tr style="background-color:#e0e8f5;"><td width="800px"   align="left">
           <table  width="800px" ><tr><td align="500px">
          <table>
              <tr><td ><%=resource.getString("opac.additional.database")%></td><td>
                      <select name="CMBDB" size="1" id="CMBDB">
<option selected value="combined">COMBINED</option>
    <option value="book">BOOKS</option>
    <option value="cd">CDs</option>
   </select>
                  </td></tr>
              <tr>   <td ><%=resource.getString("opac.simplesearch.library")%></td><td>
                     <select name="CMBLib" size="1" id="CMBLib">
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
                   </td><td align="left"><%=resource.getString("opac.additional.publicationyear")%>
                                   <br/>
                       <table>
                           <tr><td rowspan="4"></td><td>
                                  <select name="CMBYR" onChange="f()" size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="all">Select Year</option>
<option value="all">ALL YEARS</option>
<option value="between">BETWEEN</option>
<option value="upto">UPTO</option>
<option value="after">AFTER</option>
</select></td><td>

<input type="text" id="TXTYR1"  name="TXTYR1" style="width:50px"></td><td>
<input type="text" id="TXTYR2"  name="TXTYR2" style="width:50px">
</td></tr>



                       </table>




                   </td></tr></table>
      </td>
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

  </tr>
  <tr><td>
<input type="submit" id="Button1" class="btn" name="" value="FIND">


<input type="reset" id="Button2" class="btn" name="" value="CLEAR">


      </td></tr>
      <tr class="header">
                               <td colspan="2">


                            <a name="tips">&nbsp;Search Tips</a>




                            <table class="datagrid" style="background-color:#e0e8f5;color:black" halign="right" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="15%"></colgroup><colgroup width="1%"></colgroup><colgroup width="90%"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext">
    	The user can make a Additional Search using this option. The fields to be entered are:
    </th>

    </tr>

    <tr>
        <td class="txt2">
    		Database
    </td>
    <td class="tipsheading">:</td>
    <td class="tipstext">
    		 Select from the combo box the database on which the search is to be made.
    </td>

    </tr>
<tr valign="top">
    	<td class="txt2" nowrap1="">
    		Library
    	</td>
    	<td class="tipsheading">:</td>
    	<td class="tipstext">
    		 Give the Library within which a search has to be made.
    	</td>

    </tr>
    <tr valign="top">
    	<td class="txt2">
    		Author
   	</td>
   	<td class="tipsheading">:</td>
   	<td class="tipstext">
    		Give the word(s) or phrase on the basis of which the search is to be made on Author Field.
    	</td>

    </tr>
    <tr valign="top">
    	<td class="txt2">
    		Title
   	</td>
   	<td class="tipsheading">:</td>
   	<td class="tipstext">
    		Give the word(s) or phrase on the basis of which the search is to be made on Title Field.
    	</td>

    </tr>
    <tr valign="top">
    	<td class="txt2">
    		Subject
   	</td>
   	<td class="tipsheading">:</td>
   	<td class="tipstext">
    		Give the word(s) or phrase on the basis of which the search is to be made on Subject Field.
    	</td>

    </tr>

    <tr valign="top">

    	<td class="txt2">
    		other fields
    	</td>
    	<td class="tipsheading">:</td>
    	<td class="tipstext">
    		 Give the word(s) or phrase on the basis of which the search is to be made on Given Field.
    	</td>

    </tr>

    <tr valign="top">
    	<td class="txt2">
    		Connect As
    	</td>
    	<td class="tipsheading">:</td>
    	<td class="tipstext">
    		 Select from the combo box the connector required between the search words.
    	</td>

    </tr>
    <tr valign="top">
    	<td class="txt2" nowrap1="">
    		Publication Year
    	</td>
    	<td class="tipsheading">:</td>
    	<td class="tipstext">
    		 Give the publishing year(s) within which a search has to be made.
    	</td>

    </tr>
<tr valign="top">
    	<td class="txt2">
    		Field
   	</td>
   	<td class="tipsheading">:</td>
   	<td class="tipstext">
    		Select from the combo box the field on which the sorting has to be made.
    	</td>

    </tr>
   <tr valign="top">
   	<td class="txt2" align="right">
   		Click Find
    	</td>
    	<td colspan="2" class="txt2">
    		and the result is displayed. Thus , a additional search can be made on any field, title-wise, author-wise or subject-wise.
    	</td>

   </tr></tbody></table>



                               </td></tr>

       </table>



</form>

<%}else{%>
    <form name="F1" method="post" action="additional.do" onsubmit="return validate();">
       <table  align="left" width="1200x" class="datagrid"  style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px"  height="28px" align="center" colspan="2">


	   <%=resource.getString("opac.additional.additionalsearchtext")%>




        </td></tr>
  <tr style="background-color:#e0e8f5;">
      <td    align="right">
          <table >
              <tr><td> <%=resource.getString("opac.additional.connectas1")%> </td><td  valign="top">

         <select name="CMBCONN1" size="1" id="CMBCONN1" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
     </td>

              </tr>
               <tr><td><%=resource.getString("opac.additional.connectas2")%> </td><td  valign="top">

         <select name="CMBCONN2" size="1" id="CMBCONN2" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
     </td>

              </tr>
           <tr><td><%=resource.getString("opac.additional.connectas3")%></td><td  valign="top">

          <select name="CMBCONN3" size="1" id="CMBCONN3">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
     </td>

              </tr>
           <tr><td><%=resource.getString("opac.additional.connectas4")%> </td><td  valign="top">

        <select name="CMBCONN4" size="1" id="CMBCONN4" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
     </td>

              </tr>




          </table></td>
  <td width="800px" align="right" >
          <table>
              <tr><td ><%=resource.getString("opac.additional.author")%></td><td><input type="text" id="TXTAUTHOR"  name="TXTAUTHOR"></td></tr>
              <tr><td ><%=resource.getString("opac.additional.title")%></td><td><input type="text" id="TXTTITLE" name="TXTTITLE"></td></tr>
              <tr><td ><%=resource.getString("opac.additional.subject")%></td><td><input type="text" id="TXTSUBJECT"  name="TXTSUBJECT"></td></tr>
              <tr><td ><%=resource.getString("opac.additional.otherfield")%></td><td><input type="text" id="TXTOTHER"  name="TXTOTHER"></td></tr>




          </table>
      </td>
  </tr>

 <tr class="header">
      <td align="right">Sort By</td><td width="1000px"   align="right" >&nbsp;Restricted By</td></tr>
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
  <td width="800px"   align="right">
           <table  width="800px" ><tr>
                  <td align="left"><%=resource.getString("opac.additional.publicationyear")%>
                                   <br/>
                       <table >
                           <tr><td rowspan="4"></td><td><select name="CMBYR" onChange=f() size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="all">Select Year</option>
<option value="all">ALL YEARS</option>
<option value="between">BETWEEN</option>
<option value="upto">UPTO</option>
<option value="after">AFTER</option>
</select></td><td>
<input type="text" id="TXTYR1"  name="TXTYR1" style="width:50px"></td><td>
<input type="text" id="TXTYR2"  name="TXTYR2" style="width:50px">
</td></tr>



                       </table>




                   </td>
                <td align="500px" align="right">
          <table>
              <tr><td ><%=resource.getString("opac.simplesearch.database")%></td><td>
                      <select name="CMBDB" size="1" id="CMBDB">
<option value="combined" selected>COMBINED</option>
    <option value="book">BOOKS</option>
    <option value="cd">CDs</option>

</select>
                  </td></tr>
                   <tr>   <td ><%=resource.getString("opac.simplesearch.library")%></td><td>
                  <select name="CMBLib" size="1" id="CMBLib">
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
      </td>
  </tr>
  
  <tr><td colspan="2" align="right">



<input type="reset" class="btn" id="Button2" name="" value="CLEAR">
<input type="submit" class="btn" id="Button1" name="" value="FIND">

      </td></tr>
       <tr class="header">
       <td colspan="2" align="right">


                            <a name="tips">&nbsp;Search Tips</a>




                            <table class="datagrid" style="background-color:#e0e8f5;color:black" halign="left" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="79%"></colgroup><colgroup width="1%"></colgroup><colgroup width="20%"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext">
    	The user can make a additonal search using this option. The fields to be entered are:
    </th>

    </tr>

    <tr>
        <td class="tipstext" align="right">
    		 Select from the combo box the database on which the search is to be made.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		Database
    </td>
    </tr>
    <tr>
        <td class="tipstext" align="right">
    		 Select from the combo box the library on which the search is to be made.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		Library
    </td>
    </tr>
    <tr>
        <td class="tipstext" align="right">
    		 Give the word(s) or phrase on the basis of which the search is to be made on Author Field.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		Author
    </td>
    </tr>
    <tr>
        <td class="tipstext" align="right">
    		Give the word(s) or phrase on the basis of which the search is to be made on Title Field.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		Title
    </td>
    </tr>
<tr>
        <td class="tipstext" align="right">
    		Give the word(s) or phrase on the basis of which the search is to be made on Subject Field.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		Subject
    </td>
    </tr>
    <tr>
        <td class="tipstext" align="right">
    		 Give the word(s) or phrase on the basis of which the search is to be made on Given Field.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		other fields
    </td>
    </tr>
    <tr>
        <td class="tipstext" align="right">
    		Select from the combo box the connector required between the search words.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		contact as
    </td>
    </tr>
    <tr valign="top">


    	<td class="tipstext" align="right">
    		Select from the combo box the field on which the sorting has to be made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2"  align="right">
    		Field
    	</td>
    </tr>

    

    
    <tr valign="top">


    	<td class="tipstext" align="right">
    		 Give the publishing year(s) within which a search has to be made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2"  align="right">
    		Publication Year
    	</td>
    </tr>

   <tr valign="top">

    	<td colspan="2" class="txt2" align="right">
    	Click on Find and the result is displayed. Thus , a additional search can be made on any field, title-wise, author-wise or subject-wise.
    	</td>

   </tr></tbody></table>
       

       </table>


    </form>


<%}%>
</body>
</html>
