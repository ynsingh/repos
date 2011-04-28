<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-03-22
     This jsp page is meant for recieving book which is checked out.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <jsp:include page="/admin/header.jsp" flush="true" /> 

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check in process</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<body>

    <html:form method="post" action="/circheckin">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

   <table width="400px" valign="top" align="center" class="table">

  <tr><td  class="headerStyle" bgcolor="#E0E8F5" height="25px;" align="center">



          <b>Book Return Checkin Process</b>





        </td></tr>
   <tr><td></td></tr>

        <tr><td   width="400px" valign="top" style="" align="center">
                <br><br>
                <table cellspacing="10px">

                    <tr><td rowspan="5" class="txt2">Enter Accession No.<br><br>
                            <input type="text"  name="TXTACCESSION"/><br/>
                          <html:messages id="err_name" property="TXTACCESSION">
				<bean:write name="err_name" />

			</html:messages>

 </td><td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Submit" /></td></tr>

 <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td></tr>



                </table>












</td></tr>
        <tr><td >&nbsp;&nbsp;
                  <%
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("success");
if(msg!=null)
{%>
<p class="err">
    <%=msg%>

</p>
<%}
else if(msg1!=null)
{%>
<p class="mess">
   <%=msg1%>
</p>
<%}%>


            </td></tr>

   </table>
        </div>
    
</html:form>

</body>
<script language="javascript" type="text/javascript">

  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



    </script>


</html>
