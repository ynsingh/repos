<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="getUserDetails" class="com.erp.nfes.GetRecordValue" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 



<%
String lc = "";String action = "";
try{     
     lc=(String) session.getAttribute("language");
     action = request.getParameter("action");
     ml.init(lc);  
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD 
profile=http://gmpg.org/xfn/11><TITLE>Staff Registration Activation</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
<style type="text/css" media="screen"> 
#footer {position: absolute; bottom: 0; left: 0px; right:0px; width: 100%; } 
</style>
</HEAD>
<div style="height:545px; background-color:#d5e5ed;">
<BODY class="bodystyle">


<div  class="listdiv" style="margin:2px">	

	<h1></h1>
	<div style= "margin:10px;background-color:#386890;width:98%;height:25px">&nbsp;<br><br><br>		
	</div>  	
	<br><br>	
	<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="0" >

	<tr>	 		
		<td>
		<form  method="post" >
			<% 
			if( request.getParameter("successVal") != null) {
			%>
			<h2>
			   <%= ml.getValue(request.getParameter("successVal"))%>       
			</h2>
		</form>	
		</td>     
	</tr>	
		<%}%>				
	</table>  	
	<br><br>
</div>	
 
</BODY>

</HTML>