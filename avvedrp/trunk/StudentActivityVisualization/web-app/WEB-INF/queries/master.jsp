<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
    String environment=session.getAttribute("env").toString();     
	String role=session.getAttribute("ROLE").toString();  
	System.out.println(role);
    String jdbc_url=new String("");
    String db_url=new String("");
    String db_usr=new String("");
    String db_pswd=new String("");
    if(environment.equals("PRODUCTION"))
    {
            String tomcat_path=System.getProperty("catalina.base");
            String propertiesFileName = tomcat_path+"/conf/masterDb.properties";
            java.io.FileInputStream fis = new java.io.FileInputStream( propertiesFileName );
            java.util.Properties props = new java.util.Properties();
            props.load( fis );
            // getting the properties
            db_url = props.getProperty("moodleDbUrl");
            db_usr = props.getProperty("moodleDbUSer");
            db_pswd = props.getProperty("moodleDbPassword");
            jdbc_url=db_url+"?user="+db_usr+"&password="+db_pswd;
    }
    else
    {
             String propertiesFileName = request.getRealPath( "/" ) +
                                                "images" +
                                                System.getProperty( "file.separator" ) +
                                                "masterDb.properties";
												 //System.out.println("asd"+propertiesFileName);
             java.io.FileInputStream fis = new java.io.FileInputStream( propertiesFileName );
             java.util.Properties props = new java.util.Properties();
             props.load(fis);
            // getting the properties
             db_url = props.getProperty("masterDbUrl");
             db_usr = props.getProperty("masterDbUSer");
             db_pswd = props.getProperty("masterDbPassword");
             jdbc_url=db_url+"?user="+db_usr+"&password="+db_pswd;            
    }
 %>
 <%
   if(role.equals("ROLE_SUPERADMIN") || role.equals("ROLE_UNIVERSITY"))
    {	  
	   %>
	
		<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="<%=jdbc_url%>"
		catalogUri="/WEB-INF/queries/master.xml"> 		
		
		
		SELECT  NON EMPTY {([Measures].[Activity Count],[Course].[All Courses])} ON COLUMNS,
		NON EMPTY {[User].[All Users]}  ON ROWS
		FROM [master]
		
		</jp:mondrianQuery> <c:set var="title01" scope="session">Course Analysis</c:set> 
<%	}  %>
 
<%
   if(role.equals("ROLE_STAFF"))
    { 
	   String username=session.getAttribute("currUsername").toString(); 
	  // String course=session.getAttribute("sel_course").toString();  
	   String course=session.getAttribute("crsval").toString();  
	 // String course="Physics";
	System.out.println("asd"+course);
	%>
	
		<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="<%=jdbc_url%>"
		catalogUri="/WEB-INF/queries/staff.xml"> 
		
		
		
		SELECT  NON EMPTY {([Measures].[Activity Count],[Course].[Course])} ON COLUMNS,
		NON EMPTY {[User].[User]}  ON ROWS
		FROM [staff] WHERE ([SelCourse].[<%=course%>] )
		
		</jp:mondrianQuery> <c:set var="title01" scope="session">Course Analysis</c:set> 

<%	}  %>















