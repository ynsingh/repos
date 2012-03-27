<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <title>Home</title>

  <script language="javascript">
	alert("You are suceessfully logged out");
</script>
  
    </head>
<%
session.setAttribute("username",'');
//println (" hi now session is " + referal_url);
response.sendRedirect(referal_url);
%>



</html>
