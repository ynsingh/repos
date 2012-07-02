<%--
    Document   : barcode_entry
    Created on : Dec 28, 2011, 2:44:19 PM
    Author     : edrp02
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/admin/header.jsp" flush="true" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

<%
  String msgg=(String)request.getAttribute("msgg");
  String msg1=(String)request.getAttribute("msg1");
  String msg=(String)request.getAttribute("msg");
  String list=(String)request.getAttribute("list");
  String accession_no=(String)request.getAttribute("accession_no");
%>

<script type="text/javascript">
function send()
{
    top.location="<%=request.getContextPath()%>/admin/main.jsp";
    return false;
}


function fun()
{

 var x='<%=accession_no%>';

 if(x=="null")
  { document.Form.elements['accession_no'].value= "";
   }
 else
 {  document.Form.elements['accession_no'].value= x;

 }

 var x1='<%=list%>';
 if(x1=="null")
   document.Form.elements['list'].value= "";
 else
   document.Form.elements['list'].value= x1;


}

</script>
 <body onload="fun()">
      <form name="Form" action="cataloguecardentry.do" method="post">
       <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

       <table width="50%" class="table"   border="1" align="center">

          <tr><td  align="center" class="headerStyle"   bgcolor="#E0E8F5" height="25px;">Accession No. Entry For Catalogue Card</td></tr>
          <tr><td valign="top"  align="center" style=" padding-left: 5px;width: 60px;height: 100px;">
              <table align="center" class="txt2">
                  <tr><td colspan="3" align="right">Select Card Type</td>
                  <tr><td colspan="3" align="right">Main Card</td><td align="right"><input type="radio" value="maincard" name="card"> </td></tr>
                  <tr><td colspan="3" align="right">Title Card</td><td align="right"><input type="radio" value="titlecard" name="card"> </td></tr>
                  <tr><td colspan="3" align="right">Subject Card</td><td align="right"><input type="radio" value="subjectcard" name="card"> </td></tr>
                  <tr><td colspan="3" align="right">Statement Responsibility Card</td><td align="right"><input type="radio" value="scard" name="card"> </td></tr>
                  <tr><td colspan="3" align="right">All Type of Card</td><td align="right"><input type="radio" value="allcard" name="card"> </td></tr>
                  <tr><td colspan="2">Enter AccNo. List&nbsp;&nbsp;<textarea name="accession_no" id="accession_no"   style="width: 100%;height: 100px"></textarea></td></tr>
                  
                  <tr>  <td>
                       <input type="submit" id="Button1" name="" value="Submit">
                       <input type="button" id="Button2" name="" onclick="return send()" value="Back"></td>
                  </tr>
                  <tr><td class="mess" colspan="3">

        <%
          if (msg!=null)
          {

        %>

        <p class="err">AccNo. not exist:<%=msg%></p>

        <%
        }
        %>

        
         <%if (msg1!=null)
          {
        %>


        <p class="mess">  <%=msg1%></p>

        <%
        }
        %>

         <%
          if (msgg!=null)
          {

        %>

        <p class="err"><%=msgg%></p>

        <%
        }
        %>

       


                    </td></tr>
                  <tr><td align="justify" colspan="2"><font color="blue" size="-1"><b>Note1:</b> Enter list of accession no. seperated by commas for which barcodes to be generated</font></td></tr>
                  <tr><td align="justify" colspan="2"><font color="blue" size="-1"><b>Note2:</b> Enter list no. corresponding to list of accesion no.</font></td></tr>
              </table>
          </td></tr>

       </table>
         </div>
     </form>
    </body>
</html>
