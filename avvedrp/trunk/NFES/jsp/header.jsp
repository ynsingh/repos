<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>
<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String welcome="";String logout="";String lc="";String home="";
try{

	lc=(String) request.getSession().getAttribute("language");	
	Properties properties = new Properties();
	properties.load(new FileInputStream("../conf/db.properties"));
	String dbname = properties.getProperty("dbname");
	String username = properties.getProperty("username");
	String password = properties.getProperty("password");
	Class.forName("org.gjt.mm.mysql.Driver");
	conn=DriverManager.getConnection("jdbc:mysql:"+dbname+"?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes",username,password);
	theStatement=conn.createStatement();
	theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and file_code=21 and language_code=\'"+lc+"\'");
	theResult.last();int len=theResult.getRow();String cn[]=new String[len];String ls[]=new String[len];
	int i=0;theResult.beforeFirst();
	while(theResult.next()){
	     cn[i]=theResult.getString("control_name");
	     ls[i]=theResult.getString("language_string");
	     i++;
	}
	
	for(i=0;i<len;i++){
	     if(cn[i].equals("welcome")){
		welcome=ls[i];
	     }else if(cn[i].equals("logout")){
	     	logout=ls[i];
	     }else if(cn[i].equals("home")){
	     	home=ls[i];
	     }
	     	
        }
	
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	Locale locale = new Locale(lc, "");

}catch(Exception e){
     e.printStackTrace();
}
theResult.close();theStatement.close();conn.close();	
%>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Profile</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<link href="../css/oiostyles.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.toolbar {
  position: relative;
  display: inline;  
  padding: 0 .5em;
  top: -9.5em;    
}

.classLink A:link {text-decoration: none;}

</style>
</HEAD>
<BODY class="bodystyle">
<%
String str=null;
String userName = null;
int userId = 0;
String userFullName = null;
Connection conn1=null;
int documentId = 0;
String Roll="";
try
{
	userName = request.getUserPrincipal().getName();
	
	Properties properties = new Properties();
	properties.load(new FileInputStream("../conf/db.properties"));
	String dbname = properties.getProperty("dbname");
	String username = properties.getProperty("username");
	String password = properties.getProperty("password");
	
	Class.forName("org.gjt.mm.mysql.Driver");
	conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);	
	PreparedStatement pst=conn1.prepareStatement("SELECT users.id,email,user_full_name FROM users where username=?");
	pst.setString(1,userName);
	ResultSet rs=pst.executeQuery();
	while(rs.next())
	{
		userFullName=rs.getString(3); 
	}	
	conn1.close();

}catch(Exception e)
{
}
%>

<table width=105% style="background-color: #FFFFFF; border=1; margin: 0px -10px 0px;">
<tr>
<td width=5% rowspan="2"><img src="../images/loginheader_logo.PNG" ></td>
</tr>	

<tr>
<td colspan=2 width=85%  align="right" valign="bottom"><img src="../images/loginheader_NFES.PNG" ></td>	
<td width=10%></td>
</tr>
</table>

<div style="background-image: url('../images/innerpageheaderhr.jpg');repeat-x;scroll 0 0 #ef9e00;margin-left: -8px; margin-right: -8px;">&nbsp;</div>
<div class="loginLink_header">
<span><font color="#174664"><%=welcome%>&nbsp;&nbsp;<b><%=userFullName%>&nbsp;&nbsp;|</b></font></span>
<span><font color="#174664">&nbsp;&nbsp;<img alt="About Us" title="About Us" onclick="window.open('../images/AboutUs_NFES.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')" src="/nfes/images/aboutUs.jpg"><b>&nbsp;&nbsp;|</b></font></span>
<span class="classLink">&nbsp;<a href="Myaccount.jsp" target="body" title="Change Password"><img src="../images/changepassword.jpg" border=0 ></a>&nbsp;<b>|</b></span>
<span class="classLink">&nbsp;<a href="body.jsp" target="body" title="Home"><font color="#174664"><b><%=home%></a>&nbsp;|</b></font></span>
<span class="classLink">&nbsp;<a href="../j_spring_security_logout" title="Logout" target="_top"><font color="#174664"><b><%=logout%></a>&nbsp;|</b></font></span>
</div>
</div>
</BODY></HTML>
	
