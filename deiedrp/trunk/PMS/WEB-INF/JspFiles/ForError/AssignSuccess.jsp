<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ page import="in.ac.dei.edrp.pms.myMail.*;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
  <body> 
<%!boolean bool=false;
String s4=null;
%>
<%
String s2=(String)request.getAttribute("uidinfo");
//out.println(s2);
s4="Welcome to Project Management System,\n Your account has been created successfully.\n"+
 "click on the following link,"+"http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+
 " and proceed by your login.\n Project assigned: "+(String)request.getAttribute("AssignProjName")+
 "\n User Id: "+request.getAttribute("uidinfo")+"\n Password: "+request.getAttribute("passinfo")+
 "\n Thanks !\n The PMS Team\n DEI";
//new MailSend().mail(s2.trim(),s4);
//MailSend.Mail();
bool=SendingMail.sendMail(s4,"pms.dei2010@gmail.com",s2,"PMS Info");
//out.println(new MailSend().sendMail("deepak2rok@gmail.com","aniltiwari08@gmail.com","deepak2rok@gmail.com","hi",s4,"html"));
 %>  
 
 <font size="3" color="#0000ff"><%=request.getAttribute("msginfo") %></font> <br>
 
 <h2>Your Project Name=<font size="2" color="#0000ff"><%=request.getAttribute("AssignProjName") %></font></h2><br>
 <h2>Your Role Name=<font size="2" color="#0000ff"><%=request.getAttribute("AssignRoleName") %></font></h2><br>
  <h2>Your user id=<font size="2" color="#0000ff"><%=request.getAttribute("uidinfo") %></font></h2><br>
  <h2>Password=<font size="2" color="#0000ff"><%=request.getAttribute("passinfo") %></font></h2><br> 
  <h2><%=s4%></h2>
  <br>
  
  <%
  if(bool)
  {
   %>
  <h2><font size="2" color="#0055ff">Your mail has been send!</font></h2><br>
  <%
  }
  else
  {
  %>
  <h2><font size="2" color="#0055ff">Your mail has not been send!</font></h2><br>
  <%} %>
  
  <br><html:button property="back" value="Back to Previous Page" onclick="history.back();" />
  </body>
</html>
