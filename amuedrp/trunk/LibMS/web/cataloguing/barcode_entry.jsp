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
  String msg1=(String)request.getAttribute("msg1");
  String msg=(String)request.getAttribute("msg");
  String list=(String)request.getAttribute("list");
  String accession_no=(String)request.getAttribute("accession_no");
%>

<script type="text/javascript">
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
      <form name="Form" action="barcodeentry.do" method="post">
       <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

       <table width="50%" class="table"   border="1" align="center">

          <tr><td align="center" class="headerStyle"   bgcolor="#E0E8F5" height="25px;">Accession No. Entry For Barcode</td></tr>
          <tr><td valign="top"  align="center" style=" padding-left: 5px;width: 60px;height: 100px;">
              <table align="left">
                  <%--<tr><td>Enter List No.</td><td><input type="text" name="list" id="list"  /></td></tr>--%>
                  <tr><td>Enter AccNo. List</td><td><textarea name="accession_no" id="accession_no"  style="width: 400px;height: 300px"></textarea></td></tr>
                  <tr>  <td>  <%--<td> <input type="submit" id="Button1" name="" value="View" >
                       <input type="reset" id="Button2" name="submit" value="Update" ></td>
                      <td> <input type="button" id="Button3" name="" value="Delete">--%>
                       <input type="submit" id="Button3" name="" value="Submit">
                       <input type="button" id="Button3" name="" value="Back"></td>
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

                    </td></tr>
                  <tr><td align="justify" colspan="2"><font color="blue" size="-1"><b>Note1:</b> Enter list of accession no. seperated by commas for which barcodes to be generated</font></td></tr>
                  
              </table>
          </td></tr>

       </table>
         </div>
     </form>
    </body>
</html>
