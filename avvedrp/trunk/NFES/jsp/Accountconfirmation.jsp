<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<%
String msg1="";String msg2="";String lc="";
try{
     lc=(String) session.getAttribute("language");
     if(lc==null||lc==""){
            lc="en";
     }
     ml.init(lc);
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
     msg1=ml.getValue("msg1");
     msg2=ml.getValue("msg2");
    }catch(Exception e){
         e.printStackTrace();
}
    
%>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>NFES</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">


<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>



</HEAD>
<BODY class="bodystyle">


  <div  class="listdiv">
	<h1></h1>
	<div style= "margin:10px;background-color:#386890;width:98%;height:25px">&nbsp;<br><br><br>		
	</div>  	
	<br><br>
  <table width="65%" border="0"  align="center" cellpadding="0" cellspacing="0" class="confirmtable">
  <tr>
  <td width="48%">
  <form  method="post" >
  <h2><%=msg1%></h2> 
  <%=msg2%>  
  </form>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  <tr><td>&nbsp;</td></tr>
  </table>  
  </div>

</BODY></HTML>
