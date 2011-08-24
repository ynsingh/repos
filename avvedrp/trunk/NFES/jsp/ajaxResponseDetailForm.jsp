<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String lc="";String cn="";String ls="";
try{
     lc=(String) session.getAttribute("language");
     cn=request.getParameter("cn");
     if(lc==null ||lc.equals("")){lc="en";}
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
/*   Properties properties = new Properties();
     properties.load(new FileInputStream("../conf/db.properties"));
     String dbname = properties.getProperty("dbname");
     String username = properties.getProperty("username");
     String password = properties.getProperty("password");
     Class.forName("org.gjt.mm.mysql.Driver");
     conn=DriverManager.getConnection("jdbc:mysql:"+dbname+"?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes",username,password);
*/
     conn = db.getMysqlConnection();
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and file_code=30 and language_code=\'"+lc+"\' and control_name=\'"+cn+"\'");
     theResult.first();
     ls=theResult.getString("language_string");
     out.println(ls);
}catch(Exception e){
     e.printStackTrace();
}
theResult.close();theStatement.close();conn.close();	
%>