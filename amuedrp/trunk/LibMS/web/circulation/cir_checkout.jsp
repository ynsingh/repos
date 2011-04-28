<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->
<%@page  import="java.util.List" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="/admin/header.jsp" flush="true" />

<%
  
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
 <script language="javascript" type="text/javascript">
  function back()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";

   }
   function validation()
    {
    var sublib_name=document.getElementById('memid');
var str="Enter Following Values:-";
    if(sublib_name.value=="")
        {str+="\n Enter Member Id ";
             alert(str);
             document.getElementById('memid').focus();
            return false;
        }else{
            return true;

        }
    }
 </script>
</head>
<body>
 <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

     <html:form  action="/cir_chk" method="post">
 <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Checkout</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">

                   
  <tr><td height="5px" colspan="4" ></td></tr>

  <tr>

    <td align="right"><strong>Member Id<a class="star">*</a></strong></td>
    <td><html:text property="memid" styleId="memid"  value="" styleClass="textBoxWidth"/>

    </td>
  </tr>
  <tr><td height="5px" colspan="4" ></td></tr>
 
  <tr>
    <td colspan="4" align="center"><input type="submit"  value="Submit"  onClick="return validation();"/>

        <input type="button"  value="Back" onclick="back();" />
 </td>
</tr>
  <tr><td height="15px" colspan="4" ></td></tr>
</table>
                    </td></tr>
     <tr><td>



  <%
 String msg=(String)request.getAttribute("msg");
if(msg!=null){
%>
   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg%></span>
 <%
}

%>
  <%
 String msg1=(String)request.getAttribute("msg1");
if(msg1!=null){
%>
 <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>
 <%
}
%>                               </td></tr>
 <tr border="1" class="table"  align="center"><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Checkout Request from OPAC</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">
                    <tr><td>
                            <iframe align="center" height="300px" width="600px" src="<%=request.getContextPath()%>/showcir_request_opac.do" frameborder="0"/>
                        </td></tr>
            </table>
                    </td></tr>
 </table>
</html:form>
</div>
</body>


</html>

