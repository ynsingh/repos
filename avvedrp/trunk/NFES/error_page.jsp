<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD 
profile=http://gmpg.org/xfn/11><TITLE>NFES: Error Page</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

	

<body class="bodystyle">
   <%
   String errmsg=request.getParameter("errmsg");   
   %>        
      <script language="javascript">
      //window.top.location.href="./error_msg.jsp?errmsg=<%=errmsg%>";
      window.top.location.href="./login.jsp;
      </script>
</BODY>


