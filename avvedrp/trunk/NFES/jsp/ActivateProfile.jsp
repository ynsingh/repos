<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD 
profile=http://gmpg.org/xfn/11><TITLE>Staff Registration Activation</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8"><LINK 
media=screen href="css/style1.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
</HEAD>
<BODY class="bodystyle">
<form id="actForm" >
<div class="listdiv">
	<input type="hidden" name="hidUserkey" value="<%= request.getParameter("userKey") %>">
	<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="0" >
	<tr>
	<td rowspan="2"><img src="../images/information_16x16.gif"></img> </td>		
	</tr>
	<tr>	 		
		<td> 
		<form  method="post" >
			<% 
			if( request.getParameter("successVal") != null) {
			%>
			<h3 style="color:#000099">
			   <%= request.getParameter("successVal") %>       
			</h3>
		</form>	
		</td>     
	</tr>
	<!--<tr>
		<td>&nbsp;</td>
		<td>
		<p style="color:black">
		   click on Login     
		</p>
		<img src="images/arrowlback.gif" width="22" height="25"><a href="login.jsp" class="req"> Login </a>       		
		<%}%>		
		</td>
	</tr>		-->
	</table>  
</div>	
</form>
</BODY>
</HTML>
