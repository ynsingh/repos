<%-- 
    Document   : sendMessage
    Created on : Oct 20, 2011, 12:24:23 PM
    Author     : edrp01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="javax.servlet.http.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script language="javascript" type="text/javascript">
            function atTop(msg)
            {
<%

if((String)session.getAttribute("chatter")==null)
{%>
var x=confirm("Candidate Close Chat Room. You Need to logout from chat");
if(x!=true)
{location.href="/EMS/chatlogout.do";
}else{
location.href="/EMS/chatlogout.do";
}

<%}%>

        
  
                top.location.href="/EMS/catchroom.do?msg='"+msg+"'";
            }
function logout1(){

   top.location.href="/EMS/chatlogout.do";

return true;

}

  document.onkeyup = keyHit
function keyHit(event) {

  if (event.keyCode == 13) {
  atTop(f1.msg.value);

    event.stopPropagation()
    event.preventDefault()
  }
}
            </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      <form name="f1"  method="post">
          <hr>
           <INPUT type=text name="msg" value="" >
           <input type="button" name="button" value="Send" onclick="return atTop(f1.msg.value);"><br>
              <input type="button"  value="Logout" onclick="return logout1();"/>
            <%-- <TR>
		<TD width="100%" align="center"><a href="<%=request.getContextPath()%>/logout.do">Logout</a> </TD>
	</TR>--%>
    </form>

    </body>
</html>
