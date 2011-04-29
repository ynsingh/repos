
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>
<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String login="";String u="";String p="";String lp="";String login_msg="";String l=""; String lc="";
String em1="";String em2="";String em3="";
try{
     lc=request.getParameter("language");
     if(lc==null ||lc.equals("")){
          lc=(String) session.getAttribute("language");
          if(lc==null ||lc.equals("")){lc="en";}
     }
     session.setAttribute("language",lc);
     Properties properties = new Properties();
     properties.load(new FileInputStream("../conf/db.properties"));
     String dbname = properties.getProperty("dbname");
     String username = properties.getProperty("username");
     String password = properties.getProperty("password");
     Class.forName("org.gjt.mm.mysql.Driver");
     conn=DriverManager.getConnection("jdbc:mysql:"+dbname+"?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes",username,password);
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and language_code=\'"+lc+"\'");
     theResult.last();int len=theResult.getRow();String cn[]=new String[len];String ls[]=new String[len];
     int i=0;theResult.beforeFirst();
     while(theResult.next()){
          cn[i]=theResult.getString("control_name");
          ls[i]=theResult.getString("language_string");
          i++;
     }
     
     for(i=0;i<len;i++){
     	if(cn[i].equals("login")){
     		login=ls[i];
     	}else if(cn[i].equals("username")){
     		u=ls[i];
     	}else if(cn[i].equals("password")){
     		p=ls[i];
     	}else if(cn[i].equals("lost_password")){
     		lp=ls[i];
     	}else if(cn[i].equals("login_msg")){
     		login_msg=ls[i];
     	}else if(cn[i].equals("language")){
     		l=ls[i];
     	}else if(cn[i].equals("error_msg1")){
     		em1=ls[i];
     	}else if(cn[i].equals("error_msg2")){
     		em2=ls[i];
     	}else if(cn[i].equals("error_msg3")){
     		em3=ls[i];
     	}
     	
     }
     
     
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Profile Login</TITLE>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<META content="MSHTML 6.00.2900.5694" name=GENERATOR>


<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script language="javascript">

function doLogin(){
	document.forms[0].submit();
}

function keypress(c,e){
     var unicode=e.keyCode? e.keyCode : e.charCode;
     if(unicode==13){
     	if(c=="j_username" && document.forms[0].j_username.value!=""){
     	     document.forms[0].j_password.focus();	
     	}else if(c=="j_password" && document.forms[0].j_password.value!=""){
     	     doLogin(); 
     	}
      }	
      if(unicode==40){   //down arrow 
       if(c=="j_username"){
     	document.forms[0].j_username.value="";
       }	
     }
}

function combo_change(){
	window.location="login.jsp?language="+document.f.language.value;
}

</script>
<title>Login NFES</title>
<link href="./css/main.css" rel="stylesheet" type="text/css" />
<link href="./css/dark_blue.css" rel="stylesheet" type="text/css" />
<!--<script src="./js/jquery-1.4.2.min.js" type="text/javascript"></script>-->
<link href="./css/oiostyles.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
.class1 A:link {text-decoration: none;color: white;}
.class1 A:visited {text-decoration: none;color: white;}
.class1 A:active {text-decoration: none}
.class1 A:hover {text-decoration: underline; color: yellow;}
</style>

</HEAD>

<BODY onload="document.f.j_username.focus();" >

<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
<%
if ((request.getParameter("login_error") != "")&&(request.getParameter("login_error") != null)) {%>
<div id="validation-summary">	
	 <div class="notification errors closable">	
		<!--<h3><%=em1%></h3>		-->
		<ul>
			<li><%=em2%></li>
			<!--<li><%=em3%></li>-->
		</ul>
		
	</div>
</div>		
<%} 		 
%>

<!--<div class="logindiv">-->


<table WIDTH=70% align="center"><tr>
<td colspan=2 class="logindiv"><!--<img  src="./images/loginheader.png"  >--></td>

<!--<tr><td>
<div align="center" style="margin:6% auto 20px;">
</div>
</tr></td>-->

<tr>
<td><p>A nation wide,web based,searchable electronic respository that acquires, maintains, and provides information regarding the research interests and  publications of faculty members within and across various educational institutions under MHRD</p></td>

<td>
<div id="login">	
	<!--<h2 class="head-alt"><%=login%></h2>
	<ul class="tabs">
		<li><a tabindex="4" href="ForgotPassword.jsp"><%=lp%></a></li>
	</ul>
	-->
	
	<!--<div class="panes">	-->
		<div>			
			<form action="index.html" method="post">							
				<fieldset>
				<label for="language"> <%=l%></label>
				<select name="language" onchange="combo_change();" ><%
				theResult=theStatement.executeQuery("select name,code from language_master where active_yes_no=1");
				String c="";
				while(theResult.next()){
				     c=theResult.getString("code");
				     if(c.equals(lc)){%>
					<option value=<%=c%> selected="selected" ><%=theResult.getString("name")%></option>
				     <%}else{%>
					<option value=<%=c%> ><%=theResult.getString("name")%></option>
				     <%}
				}		
				theResult.close();
				theStatement.close();
				conn.close();%>
				</select>
				<br><br>
				<!--<legend><%=login_msg%></legend>-->
				
				<label for="username"><%=u%></label><input id="j_username" tabindex="1" value="" name="j_username" type="text" onkeypress="keypress(this.name,event)" />
				<label for="password"><%=p%></label><input id="j_password" tabindex="2"  name="j_password" type="password" onkeypress="keypress(this.name,event)" />
				<br><a name="button" href="#" tabindex="3" onclick="doLogin()" class="button" ><%=login%></a>				
				<span class="class1"><a tabindex="4" href="ForgotPassword.jsp"><%=lp%></a>
				</span>
				</fieldset>
			</form>
		</div>
	<!--</div>-->
</div>
</td></tr>
<tr><td colspan=2 class="footerdivlogin">
<!--<div class="footerdivlogin" > -->
Developed by Amrita University under ERP, NME ICT, MHRD
<!--</div>-->
</td></tr>
</table>


<!--<%
if ((request.getParameter("login_error") != "")&&(request.getParameter("login_error") != null)) {%>
<div id="validation-summary">	
	<div class="notification errors closable">	
		<h3><%=em1%></h3>
		<ul>
			<li><%=em2%></li>
			<li><%=em3%></li>
		</ul>
	</div>
</div>		
<%} 		 
%>	-->
<!--</div>-->



</form>
	
</BODY>
</HTML>


<!--NFES is a nation wide, web based, searchable electronic respository that acquires, maintains, and provides information regarding the research interests and publications of faculty members within and across various educational institutions under MHRD-->



	
