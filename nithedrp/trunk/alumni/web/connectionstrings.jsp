<%@ page language="java" import="java.sql.*" %>
<%@ page import = 'org.gjt.mm.mysql.*' %>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.*"%>
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<%

	String filename=getServletContext().getRealPath("config.properties");
	InputStream instream = new FileInputStream(filename);
	Properties prop = new Properties();
	prop.load(instream);
    String dbname=prop.getProperty("dbname");
	String dbserver=prop.getProperty("dbserver");
	String dbuser=prop.getProperty("dbuser");
	String dbpasswd=prop.getProperty("dbpassword");
	String dbport = prop.getProperty("dbport");
	if(instream!=null)
	instream.close();
String quer="";

	String driver = "org.gjt.mm.mysql.Driver";
	//Load Database Driver
	try {
	Class.forName(driver).newInstance(); 
}
catch(Exception x){
%>	
<script type="text/javascript">
alert("unable to load Database Class")
</script>
<%	
}
	/*Class.forName(driver).newInstance();
*/
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;	

		
try{
	
	//String url="jdbc:mysql://localhost:3306/alumni?user=root&password=calendar";	
	String url="jdbc:mysql://"+dbserver+":"+dbport+"/"+dbname+"?user="+dbuser+"&password="+dbpasswd;
	con=DriverManager.getConnection(url);
		stmt=con.createStatement();
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
%>