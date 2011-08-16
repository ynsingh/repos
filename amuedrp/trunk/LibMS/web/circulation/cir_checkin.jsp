<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-03-22
     This jsp page is meant for recieving book which is checked out.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.util.*;"%>
 <jsp:include page="/admin/header.jsp" flush="true" /> 

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check in process</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
  <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
</head>
<body>

    <html:form method="post" action="/circheckin">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

   <table dir="<%=rtl%>" width="400px" valign="top" align="center" class="table">

  <tr><td dir="<%=rtl%>"  class="headerStyle" bgcolor="#E0E8F5" height="25px;" align="center">



          <b><%=resource.getString("circulation.cir_checkin.bookreturn")%></b>





        </td></tr>
   <tr><td></td></tr>

        <tr><td dir="<%=rtl%>"  width="400px" valign="top" style="" align="center">
                <br><br>
                <table dir="<%=rtl%>" cellspacing="10px">

                    <tr><td dir="<%=rtl%>" rowspan="5" class="txt2"><%=resource.getString("circulation.cir_checkin.enteraccessno")%><br><br>
                            <input type="text"  name="TXTACCESSION"/><br/>
                          <html:messages id="err_name" property="TXTACCESSION">
				<bean:write name="err_name" />

			</html:messages>

                        </td><td dir="<%=rtl%>" width="150px" align="center"> <input type="submit" class="btn"  onclick="return Submit();"  value="<%=resource.getString("circulation.cir_newmember.submit")%>" /></td></tr>

 <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="Button5"  value="<%=resource.getString("circulation.cir_newmember.back")%>" class="btn" onclick="return quit()"/></td></tr>



                </table>



 <input type="hidden" id="button" name="button" />








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

  function Submit()
{
    var buttonvalue="Submit";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



    </script>


</html>
