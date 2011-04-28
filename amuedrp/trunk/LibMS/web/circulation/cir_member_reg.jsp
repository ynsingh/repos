<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for Register,View,Deleate,Update newMember (Buttons will be visible) and Accept MemberId.
     This jsp page is first page During Process of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
// message from circulationmemberaction
String msg1=(String)request.getAttribute("msg1");
String msg=(String)request.getAttribute("msg");
// message from circulationnewmemberregaction
String msg2=(String)request.getAttribute("msg2");
%>
<%if(msg!=null){%><script>alert("<%=msg%>")</script><%}%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<body>
 
    <html:form method="post" action="/memberreg">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table width="400px" height="200px"  valign="top" align="center" class="table">

        <tr><td   class="headerStyle" align="center">
          
             

          <b>Member Management</b>



          
          
        </td></tr>
   <tr><td></td></tr>

        <tr><td   width="400px" height="200px" valign="top" style="" align="center">
                <br><br>
                <table cellspacing="10px">

                    <tr><td rowspan="5" class="txt2">Enter Member ID<br><br>
                            <html:text property="TXTMEMID" value="" /><br/>
                          <html:messages id="err_name" property="TXTMEMID">
				<bean:write name="err_name" />

			</html:messages>

                        </td><td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Register" /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>
 
 <tr><td width="150px" align="center"><input type="submit" id="Button4" name="button" value="Delete" class="btn" /></td></tr>
 <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td></tr>
 
 

                </table>
       


    <input type="hidden" name="library_id" value="<%=library_id%>">
   








</td></tr>

        <tr><td class="mess">

    <%     if (msg1!=null){
 %>

<p class="err"><%=msg1%></p>

 <%
}

%>

 <%     if (msg!=null){
 %>
 <p class="mess"> <%=msg%></p>

 <%
}

%>

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
