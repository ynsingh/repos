<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/>
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<%
String welcome="";String logout="";String lc="";String home="";
try{
	lc=(String) request.getSession().getAttribute("language");	
	ml.init(lc);    
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	Locale locale = new Locale(lc, "");
	welcome=ml.getValue("welcome");
	logout=ml.getValue("logout");
	home=ml.getValue("home");
}catch(Exception e){
     e.printStackTrace();
}
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
        conn1 = db.getMysqlConnection();
	PreparedStatement pst=conn1.prepareStatement("SELECT users.id,email,TRIM(CONCAT(IFNULL(title,''),user_full_name,' ',IFNULL(LAST_NAME,''))) user_full_name FROM users where username=?");
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

<div style="background-color: #FFFFFF; margin: 10px -6px;">
<table width=100% style="background-color: #FFFFFF; border=1; margin: 0px;">
<tr>
<td width=5% rowspan="2"><img src="../images/loginheader_logo.PNG" height="80px"  ></td>
</tr>	

<tr>
<td colspan=2 width=95%  align="right" valign="bottom"><img src="../images/loginheader_NFES.PNG" ></td>	
<td width=10%></td>
</tr>
</table>
<div style="background-image: url('../images/innerpageheaderhr.jpg');repeat-x;scroll 0 0 #ef9e00;">&nbsp;</div>
<div class="loginLink_header">
<span><font color="#174664"><%=welcome%>&nbsp;&nbsp;<b><%=userFullName%>&nbsp;&nbsp;|</b></font></span>
<span><font color="#174664">&nbsp;&nbsp;<img alt="Help" title="Help" onclick="window.open('../UserGuides/UserGuides.html','mywindow','width=1000,height=700,left=0,top=100,screenX=0,screenY=100')" src="/nfes/images/help.gif"><b>&nbsp;&nbsp;|</b></font></span>
<span><font color="#174664">&nbsp;&nbsp;<img alt="About Us" title="About Us" onclick="window.open('../images/AboutUs_NFES.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')" src="/nfes/images/aboutUs.jpg"><b>&nbsp;&nbsp;|</b></font></span>
<span class="classLink">&nbsp;<a href="Myaccount.jsp" target="body" title="Change Password"><img src="../images/changepassword.jpg" border=0 ></a>&nbsp;<b>|</b></span>
<span class="classLink">&nbsp;<a href="body.jsp" target="body" title="Home"><font color="#174664"><b><%=home%></a>&nbsp;|</b></font></span>
<span class="classLink">&nbsp;<a href="../j_spring_security_logout" title="Logout" target="_top"><font color="#174664"><b><%=logout%></a>&nbsp;|</b></font></span>
</div>
</div>
</div>
</BODY></HTML>
