<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>

<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD
profile=http://gmpg.org/xfn/11><TITLE>Staff Profile</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">

<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

</HEAD>
<BODY>
<form name="user" method="post" >
<DIV class=main>
<DIV class=main-bg>
<DIV class=main-top>
<DIV class=main-width>
<DIV class=header>
<DIV class=logo>
<DIV class=indent>
<img src="../images/NFES_header.jpg" >
</DIV></DIV>
<DIV class=signup>

<%
String str=null;
String userName = null;
int userId = 0;
String email = null;
String userFullName = null;
Connection conn1=null;
Connection conn2=null;
int documentId = 0;
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
	PreparedStatement pst=conn1.prepareStatement("select id,email,user_full_name from users where username=?");
	pst.setString(1,userName);
	ResultSet rs=pst.executeQuery();
	while(rs.next())
	{
		userId=rs.getInt(1);
		email=rs.getString(2); 
		userFullName=rs.getString(3); 
	}
	conn1.close();
	conn2=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);
	PreparedStatement pst1=conn2.prepareStatement("select document_id from entity_document_master where entity_id="+userId);
	ResultSet rs1=pst1.executeQuery();
	while(rs1.next())
	{
		documentId=rs1.getInt("document_id");
		
	}
	conn2.close();
}catch(Exception e)
{
}
str="../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT&entityId="+userId+"&documentId="+documentId;
%>

<FORM id="searchform" action="../j_spring_security_logout"  method="get" >

     <br />     	
	<div style="float: left;" width="40%">   
	<font color="#C68B13"><b>UserName:</b></font>&nbsp;&nbsp;<font color="#174664"><strong><%=userFullName%></strong></font>
	</div>

	<div style="float: right;" width="40%">
	<a href="../j_spring_security_logout"><INPUT  type="image" src="../images/logout.jpg" ></a>
	</div>


</FORM></DIV></DIV>


<iframe src =<%=str%> width="100%" height="700px" frameborder="0">

  
</iframe>


<DIV class=main-menu>
<DIV class=menu-bg>
<DIV class=corner-left>
<DIV class=corner-right>
<DIV class=menu>
	<input type="hidden" name="status" value=""/>
	<input type="hidden" name="url" value=""/>
	<input type="hidden" id="canAccess" name="canAccess" value="" />	
<script language="JavaScript" type="text/javascript" src="../js/mymenu.js"></script>
</DIV></DIV></DIV></DIV></DIV>
</DIV>
<DIV class=content>
<DIV class=content-bg>
<DIV class=content-indent>
<DIV class=banner>
<P><IMG src="../images/myheader.jpg" alt="" border="0" usemap="#Map" href="#">
</P>
<P>

</DIV>
<!-- enter contents-->
<DIV class=bg01>
</DIV>

<!-- enter contents-->
</DIV></DIV></DIV></DIV></DIV></DIV></DIV>
<DIV class=footer>
<!--<font color="#CC0000" size="+1">If you have previously visited AIMS and already have an MRD Card, do not register online again.</font>-->
<DIV class=width>
  <DIV class=corner-left>
<DIV class=corner-right>
<DIV class=indent>
<!-- external page is given in the href attribute (as it should be) 
<a href="terms.jsp">
	Terms and Conditions |</a> -->

<!-- another link. uses the same overlay 
<a href="policy.jsp">
	Privacy & Disclaimer policy</a>
<br>


 2010 AIMS. All rights reserved, Inc. reserved-->
<!--{%FOOTER_LINK} -->
</DIV>
</DIV></DIV></DIV></DIV>

</form>
</BODY></HTML>
