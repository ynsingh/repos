
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>

<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 




<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String lc="";
try{
     lc=request.getParameter("language");
     if(lc==null ||lc.equals("")){
          lc=(String) session.getAttribute("language");
          if(lc==null ||lc.equals("")){lc="en";}
     }
     session.setAttribute("language",lc);    
     
     conn = db.getMysqlConnection();
     theStatement=conn.createStatement();       
     
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
     
      ml.init("login.jsp", lc);  
      
}catch(Exception e){
     e.printStackTrace();
}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>NFES - Login</TITLE>



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

<style type="text/css" media='screen'>

.class1 A:link {font-family:verdana,Helvetica,sans-serif;font-weight: normal;font-size: 12px;text-decoration: none;color: #7D053F;}
.class1 A:visited {text-decoration: none;}
.class1 A:active {text-decoration: none;}
.class1 A:hover {text-decoration: underline; }



body {
text-align: center;
font-family:verdana,Helvetica,sans-serif;
font-size: 12px;
background-image:url(./images/bg.jpg);
background-repeat: repeat-x;
background-color: #D5E5ED;
}


/* Login Bar Start */
.loginLink{
	margin: 0 auto; 
	width:847px;
	height:30px;
	background-color: #b9dcef;
	font-size:14px;
	text-align:right;
	font-weight:bold;
	color:#007500;
	}
.loginLink span { 
  text-align:right;
  padding-right: 15px;
  }
	
.loginLink span A:link { color:#01518e; font-weight:bold; text-decoration: none}
.loginLink span A:visited { color:#01518e; font-weight:bold; text-decoration: none}
.loginLink span A:active { color:#01518e; font-weight:bold; text-decoration: none}
.loginLink span A:hover { color:#01518e; font-weight:bold; text-decoration: none}
/* Login Bar Start */

/* Banner Start */
.banner {
	margin: 0 auto; 
	width:847px;
	height:158px;
	background-color: #b9dcef;
	background-image: url(./images/indexnew_02.jpg);
	background-repeat: no-repeat;  
	}
/* Banner end */

/* login Start */
.login {
	margin: 0 auto; 
	width:847px;
	height:260px;
	text-align: right;
	background-color: #b9dcef;
	background-image: url(./images/login_03.jpg);
	background-repeat: no-repeat;  
	}
.loginUserPass{
     	position: relative;
	float: right;
	right:80px;
	top: 35px;
	width:310px;
	height:164px;
	color: #01518e;
	font-size:12px;
	font-weight: bold;
	letter-spacing: 1px;
	text-align:left;
	font-family: verdana,Helvetica,sans-serif;
	
	}
/* login end */
/* dashboardBar Start */
.dashboardBar {
	margin: 0 auto; 
	width:847px;
	height:48px;
	background-color: #b9dcef;
	background-image: url(./images/indexnew_04.jpg);
	background-repeat: no-repeat;  
	}
.dashboardBar span { 
  text-align:right;
  
  }
/* dashboardBar end */

</style>

</HEAD>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="document.f.j_username.focus();" >

<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST" target="_top">
<%
if ((request.getParameter("login_error") != "")&&(request.getParameter("login_error") != null)) {%>
<div id="validation-summary">	
	 <div class="loginLink">			
		<div class='login_message'  align="center"><br><%=ml.getValue("error_msg2")%></div>
	</div>
</div>		
<%} 		 
%>

<!-- Banner Start-->
<div class="banner"></div>
<!-- Banner end-->
<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">

<!-- Login Start-->
<div class="login">
<div class="loginUserPass">


<table width="100%" border="0" cellspacing="0" cellpadding="0">

<tr align="left">
		<th height="40"> <%=ml.getValue("language")%></th>
		<th align="left">
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
		</th>
</tr>		

<tr align="left">
      <th width="50%" height="40"><%=ml.getValue("username")%> </th>
      <th width="50%" align="left">	      
	 <input type="text" style="width: 150px;" name='j_username' id='j_username' onkeypress="keypress(this.name,event)" />
     </th>
</tr>
<tr align="left">
      <th width="50%" height="40"><%=ml.getValue("password")%> </th>
      <th width="50%" align="left">
       <input type='password' style="width: 150px;" name='j_password' id='j_password' onkeypress="keypress(this.name,event)" />
    </th>
</tr>
    <tr align="left">
      <th >&nbsp;</th>
      <th align="left"><input type=button name="button" onclick="doLogin()" value='<%=ml.getValue("login")%>' ></th>
      </tr>
<tr align="left">
<td ><a onmouseout="this.style.textDecoration='none';" onmouseover="this.style.textDecoration ='underline';" style="font-size: 12px; font-weight: normal; text-decoration: none; color: rgb(125, 5, 63);" href="registerUser.jsp?action=REGISTER_UNIVERSITY"><%=ml.getValue("register")%></a></td>
<td ><a onmouseout="this.style.textDecoration='none';" onmouseover="this.style.textDecoration ='underline';" style="font-size: 12px; font-weight: normal; text-decoration: none; color: rgb(125, 5, 63);" href="ForgotPassword.jsp"><%=ml.getValue("lost_password")%> </a></td>
</tr>
<tr align="left">
<th>&nbsp;</th>
<td align="right"><font size="1" face="Times"/>     
</td>
</tr>
      
  </table>
 
 </div>
 	
 	
 </div>
  </form>
 <!-- Login end-->
 
 <!-- dashboardBar Start-->
 <div class="dashboardBar">
 <br>
 <center>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;line-height: 2.5;">Developed by Amrita University under ERP, NME ICT, MHRD</label>
 </div>
<!-- dashboardBar end-->

</body>
</HTML>