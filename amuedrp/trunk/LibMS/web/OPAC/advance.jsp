<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@page import="java.sql.ResultSet"%>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Advance Search...</title>

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
<SCRIPT language="JAVASCRIPT">
     function f()
    {
        if(document.getElementById('CMBYR').value=="upto" || document.getElementById('CMBYR').value=="after")
         {
            document.getElementById('TXTYR1').disabled=false;
            document.getElementById('TXTYR1').style.backgroundColor = "#ffffff";
            document.getElementById('TXTYR2').disabled=true;
            document.getElementById('TXTYR2').style.backgroundColor = "#eeeeee";

	   }
           if(document.getElementById('CMBYR').value=="all")
         {
             document.getElementById('TXTYR1').disabled=true;
            document.getElementById('TXTYR1').style.backgroundColor = "#eeeeee";
            document.getElementById('TXTYR2').disabled=true;
            document.getElementById('TXTYR2').style.backgroundColor = "#eeeeee";

	   }
        if(document.getElementById('CMBYR').value=="between")
         {
            document.getElementById('TXTYR1').disabled=false;
            document.getElementById('TXTYR1').style.backgroundColor = "#ffffff";
            document.getElementById('TXTYR2').disabled=false;
            document.getElementById('TXTYR2').style.backgroundColor = "#ffffff";

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

<body>
     <%if(page.equals(true)){%>

     <html:form  method="post" action="/OPAC/advance_search" onsubmit="return validate();">



            <table  align="left" width="1200x" class="datagrid"  style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px"  height="28px" align="center" colspan="2">


		  <%=resource.getString("opac.advance.advancesearchtext")%>




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px">
          <table width="800px">
              <tr><td width="130px">Search Keyword as</td><td><input name="TXTPHRASE1" type="text">
</td></tr>
              <tr>   <td>Connected Word As</td><td> <select name="CMB1" size="1">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>

               

                  </td><td align="left">  <select name="CMBF1" size="1">
<option value="or">OR</option>
<option value="and">AND</option>
</select></td>

      </tr>

          </table>
      </td>
      <td    align="left" valign="top">
          <table >
              <tr><td>in Field </td><td rowspan="2" valign="top">

          <select name="CMBFIELD1" size="1" >
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>

</select>

     </td>

              </tr></table></td></tr>
   <tr style="background-color:#e0e8f5;"><td width="800px"  >
          <table width="800px">
              <tr><td width="130px">Search Keyword as</td><td><input name="TXTPHRASE2"  type="text">
</td></tr>
              <tr>   <td >Connected Word As</td><td> <select name="CMB2" size="1" >
    <option value="or">OR</option>
    <option value="and">AND</option>
                      </select></td><td align="left">      <select name="CMBF2" size="1">
<option value="or">OR</option>
<option value="and">AND</option>
</select>
</td>

      </tr>

          </table>
      </td>
      <td    align="left" valign="top">
          <table >
              <tr><td>in Field </td><td rowspan="2" valign="top">

         <select name="CMBFIELD2" size="1"  >
<option selected value="author">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>

</select>


     </td>

              </tr></table></td></tr>
    <tr style="background-color:#e0e8f5;"><td width="800px"  >
          <table width="800px">
              <tr><td width="130px">Search Keyword as</td><td><input name="TXTPHRASE3" type="text">

</td></tr>
              <tr>   <td >Connected Word As</td><td> <select name="CMB3" size="1">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select></td><td>     <select name="CMBF3" size="1" >
<option value="or">OR</option>
<option value="and">AND</option>
</select>
</td>

      </tr>

          </table>
      </td>
      <td    align="left" valign="top">
          <table >
              <tr><td>in Field </td><td rowspan="2" valign="top">

        <select name="CMBFIELD3" size="1" >
<option selected value="subject">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>

</select>


     </td>

              </tr></table></td></tr>
  <tr class="header"><td width="1000px"   align="left" >Restricted By</td><td align="left">Sort By</td></tr>
   <tr style="background-color:#e0e8f5;"><td width="800px"   align="left">
           <table  width="800px" ><tr><td align="500px">
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
</select></td>

      </tr>

          </table>
                   </td><td align="left"><%=resource.getString("opac.additional.publicationyear")%>
                                   <br/>
                       <table>
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

<input id="Button2" class="btn" name="" value="<%=resource.getString("opac.advance.find")%>"  type="submit">
<input id="Button1" name="" class="btn" value="<%=resource.getString("opac.advance.clear")%>" type="reset">


      </td></tr>
           <tr class="header">
                               <td colspan="2">


                            <a name="tips">&nbsp;Search Tips</a>




                            <table class="datagrid" style="background-color:#e0e8f5;color:black;" halign="right" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="15%"></colgroup><colgroup width="1%"></colgroup><colgroup width="90%"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext">
    	The user can make a advanced search using this option. The fields to be entered are:
    </th>

    </tr>

    <tr>
        <td class="txt2">
    		Library
    </td>
    <td class="tipsheading">:</td>
    <td class="tipstext">
    		 Select from the combo box the Library on which the search is to be made.
    </td>

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
    	<td class="txt2">
    		Field
   	</td>
   	<td class="tipsheading">:</td>
   	<td class="tipstext">
    		Select from the combo box the field on which the search is to be made.
    	</td>

    </tr>

    <tr valign="top">

    	<td class="txt2">
    		Search Keyword As
    	</td>
    	<td class="tipsheading">:</td>
    	<td class="tipstext">
    		 Give the word(s) or phrase on the basis of which the search is to be made.
    	</td>

    </tr>

    <tr valign="top">
    	<td class="txt2">
    		Connected Word As
    	</td>
    	<td class="tipsheading">:</td>
    	<td class="tipstext">
    		 Select from the combo box the connector required between the search words.
    	</td>

    </tr>
     <tr valign="top">
    	<td class="txt2">
    		In Field
    	</td>
    	<td class="tipsheading">:</td>
    	<td class="tipstext">
    		 Select from the combo box the Field in which searching has to made.
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
   	<td class="txt2" align="right">
   		Click Find
    	</td>
    	<td colspan="2" class="txt2">
    		and the result is displayed. Thus , a advanced search can be made on any field, title-wise, author-wise or subject-wise.
    	</td>

   </tr></tbody></table>



                               </td></tr>

       </table>


   </html:form>
  




     <%}else{%>

   
<html:form  method="post" action="/OPAC/advance_search" onsubmit="return validate();">
    
    
     <table  align="left" width="1200x" class="datagrid"  style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px"  height="28px" align="center" colspan="2">


		  <%=resource.getString("opac.advance.advancesearchtext")%>




        </td></tr>
  <tr style="background-color:#e0e8f5;">

      <td    align="left" width="200px" valign="top">
          <table >
              <tr>
                  <td>in Field </td>
                  <td rowspan="2" valign="top">

          <select name="CMBFIELD1" size="1" >
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>


</select>

     </td>

              </tr></table></td>
              <td width="800px" align="right">
          <table width="800px" align="right">
              <tr>
                  <td align="right" colspan="2"><input name="TXTPHRASE1" type="text">
</td><td align="left">Search Keyword as&nbsp;</td></tr>
              <tr> <td align="center" width="500px"><select name="CMBF1" size="1">
<option value="or">OR</option>
<option value="and">AND</option>
</select></td>
<td align="right"> <select name="CMB1" size="1">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>



                  </td> <td align="left">Connected Word As</td>
      </tr>

          </table>
      </td>
  </tr>
  <tr style="background-color:#e0e8f5;">

      <td    align="left" width="200px" valign="top">
          <table >
              <tr>
                  <td>in Field </td>
                  <td rowspan="2" valign="top">

          <select name="CMBFIELD2" size="1" >
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>


</select>

     </td>

              </tr></table></td>
              <td width="800px" align="right">
          <table width="800px" align="right">
              <tr>
                  <td align="right" colspan="2"><input name="TXTPHRASE2" type="text">
</td><td align="left">Search Keyword as&nbsp;</td></tr>
              <tr> <td align="center" width="500px"><select name="CMBF2" size="1">
<option value="or">OR</option>
<option value="and">AND</option>
</select></td>
<td align="right"> <select name="CMB2" size="1">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>



                  </td> <td align="left">Connected Word As</td>
      </tr>

          </table>
      </td>
  </tr>
  <tr style="background-color:#e0e8f5;">

      <td    align="left" width="200px" valign="top">
          <table >
              <tr>
                  <td>in Field </td>
                  <td rowspan="2" valign="top">

          <select name="CMBFIELD3" size="1" >
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>

</select>

     </td>

              </tr></table></td>
              <td width="800px" align="right">
          <table width="800px" align="right">
              <tr>
                  <td align="right" colspan="2"><input name="TXTPHRASE3" type="text">
</td><td align="left">Search Keyword as&nbsp;</td></tr>
              <tr> <td align="center" width="500px"><select name="CMBF3" size="1">
<option value="or">OR</option>
<option value="and">AND</option>
</select></td>
<td align="right"> <select name="CMB3" size="1">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>



                  </td> <td align="left">Connected Word As</td>
      </tr>

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
                                   </select></td><td align="right">
<input type="text" id="TXTYR1"  name="TXTYR1" style="width:50px"></td><td align="right">
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




 
  <tr><td  colspan="2" align="right">

<input id="Button2" name="" class="btn" value="<%=resource.getString("opac.advance.find")%>"  type="submit">
<input id="Button1" name="" class="btn" value="<%=resource.getString("opac.advance.clear")%>" type="reset">


      </td></tr>
    <tr class="header">
       <td colspan="2" align="right">


                            <a name="tips">&nbsp;Search Tips</a>




                            <table class="datagrid" style="background-color:#e0e8f5;color:black" halign="left" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="79%"></colgroup><colgroup width="1%"></colgroup><colgroup width="20%"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext">
    	The user can make a advanced search using this option. The fields to be entered are:
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
    		 Select from the combo box the Library on which the search is to be made.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		Library
    </td>
    </tr>

    <tr valign="top">


   	<td class="tipstext" align="right">
    		Select from the combo box the field on which the Sorting is to be made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2" align="right">
    		Field
   	</td>
    </tr>

    <tr valign="top">



    	<td class="tipstext" align="right">
    		 Give the word(s) or phrase on the basis of which the search is to be made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2" align="right">
    		Search Keyword As
    	</td>
    </tr>

    <tr valign="top">


    	<td class="tipstext" align="right">
    		 Select from the combo box the connector required between the search words.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2" align="right">
    		Connected Word As
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


    	<td class="tipstext" align="right">
    		  Select from the combo box the Field required on which searching has to made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2"  align="right">
    		In Field
    	</td>
    </tr>

   <tr valign="top">

    	<td colspan="2" class="txt2" align="right">
    		Click on Find and the result is displayed. Thus , a advanced search can be made on  title-wise, author-wise or subject-wise etc.
    	</td>

   </tr></tbody></table>


       

       </table>



</html:form>


     <%}%>
</body>
</html>
