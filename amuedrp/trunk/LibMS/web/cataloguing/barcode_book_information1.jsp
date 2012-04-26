<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>

<script type="text/javascript">

function fun()
{

document.getElementById("Form1").action="/LibMS/bar_book_info.do";

document.getElementById("Form1").method="post";

document.getElementById("Form1").submit();
}
function show(){
    document.getElementById("acc_no").focus();

}


function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/barcode_book_information.jsp";
    return false;
}

function back()
    {

        location.href="<%=request.getContextPath()%>/circulation/cir_member_reg.jsp";
    }
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="common" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>




   <div
   style="  top:150px;
left:5px;
   right:5px;
      position: absolute;

      visibility: show;">


       <html:form styleId="Form1"  method="post" action="/cataloguing/bar_book_info">
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
                  <tr><td align="center" colspan="2"><input type="button" value="Cancel" onclick="send();"  />
                      </td></tr>
                </table>
         </td></tr>
       </table>

  </html:form>

  </div>
  </body>
</html>
