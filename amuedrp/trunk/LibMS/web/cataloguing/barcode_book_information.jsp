<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<% String msg=(String)request.getAttribute("msg");
   String msg1=(String)request.getAttribute("msg1");
%>
<script type="text/javascript">

function fun()
{
  
document.getElementById("Form1").action="/LibMS/bar_book_info.do";

document.getElementById("Form1").method="post";

document.getElementById("Form1").submit();
}
function show(){
  var f=  document.getElementById("acc1_no");
  f.focus();
}

function showdiv(){

        var ele = document.getElementById("acc1_no");


	if(ele.style.display == "block") {
    		ele.style.display = "none";

  	}
	else {
		ele.style.display = "block";

	}
        ele.style.cursor="true";
     //   document.getElementById('acc1_no').focus();

    }


function quit()
  {


         window.location="<%=request.getContextPath()%>/admin/main.jsp";

      return true;
  }
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="common" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body onload="showdiv();">




   <div
   style="  top:150px;
left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

       <div id="acc1_no"
   style=" background: cyan;
   height:150px;left:35%;
   overflow: hidden;
      position: absolute;
      display: none; "

      >
           <html:form styleId="Form2"  method="post" action="/cataloguing/bar_book_info"><br><br>
               <table border="1" class="table" width="400px" align="center">
                  <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Book Information Correspondind Barcode</b></td></tr>
                  <tr><td>
                 <table width="400px" border="0" cellspacing="4" cellpadding="1" align="left">
                  <tr><td align="left" class="txtStyle"><strong>Accession No.</strong></td>
                      <td><html:text  property="accession_no" styleId="acc1_no" styleClass="textBoxWidth"  />
                          
                      </td>
                  </tr>
                  <tr><td colspan="2"><html:submit value="Submit" styleId="button" styleClass="textBoxWidth"  />
                          <input type="reset" value="Reset" />
                          <input type="button" value="Cancel" onclick="quit();"  />
                      </td></tr>
                 </table>
                  </td></tr>
                  <tr><td class="mess">

        <%
          if (msg!=null)
          {

        %>

        <p class="err">  <%=msg%></p>

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

               </table>

           </html:form>

      </div>
      <%-- <html:form styleId="Form1"  method="post" action="/bar_book_info">
       <table border="1" class="table" align="center">
          <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Book Information Correspondind Barcode</b></td></tr>
          <tr><td>
                 <table width="400px" border="0" cellspacing="4" cellpadding="1" align="left">
                  <tr></tr>
                  <tr><td colspan="5" height="10px"></td></tr>
                  <tr><td colspan="5" height="10px"></td></tr>
                  <tr><td align="left" class="txtStyle"><strong>Accession No.</strong></td>
                      <td><html:text  property="accession_no" name="BarBookInformationActionForm" styleId="acc_no" styleClass="textBoxWidth" onblur="fun()" /></td>
                  </tr>
                  <tr><td colspan="5" height="5px"></td></tr>
                  <tr><td width="150" align="left" class="txtStyle"><strong>Title</strong> </td>
                      <td><html:text   property="title" styleClass="textBoxWidth" styleId="language_name" name="BarBookInformationActionForm" /></td>
                  </tr>
                  <tr><td colspan="5" height="5px"></td></tr>
                  <tr><td width="150" align="left" class="txtStyle"><strong>Call No.</strong> </td>
                      <td><html:text   property="call_no" styleClass="textBoxWidth" styleId="language_name" name="BarBookInformationActionForm" /></td>
                  </tr>
                  <tr><td colspan="5" height="5px"></td></tr>
                  <tr><td colspan="5" height="5px"></td></tr>
                  <tr><td></td><td></td></tr>
                  <tr><td colspan="5" height="10px"></td></tr>
                </table>
         </td></tr>
       </table>

  </html:form>--%>

  </div>
  </body>
</html>
