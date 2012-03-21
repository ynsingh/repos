<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 
<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String lc="";String cn="";String ls="";
try{
     lc=(String) session.getAttribute("language");
     if(lc==null ||lc.equals("")){lc="en";}
     ml.init(lc);      
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
     
     cn=request.getParameter("cn");     
     ls=ml.getValue(cn);
     
     out.println(ls);
}catch(Exception e){
     e.printStackTrace();
}
%>