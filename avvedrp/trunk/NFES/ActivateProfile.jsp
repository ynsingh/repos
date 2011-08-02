<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD 
profile=http://gmpg.org/xfn/11><TITLE>Staff Registration Activation</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

<LINK media=screen href="./css/oiostyles.css" type=text/css rel=stylesheet>
<style type="text/css" media="screen"> 
#footer {position: absolute; bottom: 0; left: 0px; right:0px; width: 100%; } 
</style>
</HEAD>
<div style="height:545px; background-color:#d5e5ed;">
<BODY class="bodystyle">



<form id="actForm" >
<table width="100%" style="background-color: #FFFFFF;">
	<tbody><tr>

		<td rowspan="2" width="5%"><img src="./images/loginheader_logo.PNG"></td>
		<td width="58%"></td>	
		<td align="right" width="30%"><font color="#174664"><b><a href="./login.jsp" STYLE="TEXT-DECORATION: NONE">Login</a></b></font></td>
		<td width="7%"></td>	

	</tr>	
	<tr>
		<td colspan="4" width="95%" align="right"><img src="./images/loginheader_NFES.PNG"></td>	
	</tr>
	<tr>	
	<td colspan="6" style="background-color: #9dc6d9;"><hr></td></tr>
	</tbody>
</table>
<div  class="listdiv" style="margin:2px">	

	<h1></h1>
	<div style= "margin:10px;background-color:#386890;width:98%;height:25px">&nbsp;<br><br><br>		
	</div>  	
	<br><br>
	
	<input type="hidden" name="hidUserkey" value="<%= request.getParameter("userKey") %>">
	<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="0" >
	<tr>
	<td rowspan="2">&nbsp;<img src="./images/information_16x16.gif"></img> </td>		
	</tr>
	<tr>	 		
		<td> 
		<form  method="post" >
			<% 
			if( request.getParameter("successVal") != null) {
			%>
			<h2>
			   <%= request.getParameter("successVal") %>       
			</h2>
		</form>	
		</td>     
	</tr>	
		<%}%>				
	</table>  	
	<br><br>
</div>	
<div id="footer">
<center>
<div class="footerdiv" > 
Developed by Amrita University under ERP, NME ICT, MHRD
</div>
</center>
</div> 
</form>
</BODY>

</HTML>
