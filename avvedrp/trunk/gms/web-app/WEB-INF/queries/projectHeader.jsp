<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="org.codehaus.groovy.grails.commons.GrailsApplication" %>
<%@ page import="grails.util.GrailsUtil" %>
 <%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
 <%
HttpSession sess=request.getSession();
int party=Integer.parseInt(sess.getAttribute("Party").toString());

    String jdbc_url=new String("");
    String db_url=new String("");
    String db_usr=new String("");
    String db_pswd=new String("");
    if(GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION))
    {
          String tomcat_path=System.getProperty("catalina.base");
	             String propertiesFileName = tomcat_path+"/conf/BIDb.properties";
	             java.io.FileInputStream fis = new java.io.FileInputStream( propertiesFileName );
	             java.util.Properties props = new java.util.Properties();
	             props.load( fis );
	             // getting the properties
	                          db_url = props.getProperty("BIdbUrl");
	             db_usr = props.getProperty("BIdbUSer");
	             db_pswd = props.getProperty("BIdbPassword");
            jdbc_url=db_url+"?user="+db_usr+"&password="+db_pswd; 
    }
    else
    {
             String propertiesFileName = request.getRealPath( "/" ) +
                                                System.getProperty( "file.separator" ) +
                                                "images" +
                                                System.getProperty( "file.separator" ) +
                                                "gmsDb.properties";
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
 <jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver"
jdbcUrl="<%=jdbc_url%>"
catalogUri="/WEB-INF/queries/projectHeader.xml">

SELECT {[Measures].[Amount Allocated],[Measures].[Amount Recieved],[Measures].[Amount On Hand],[Measures].[Amount Pending]} ON COLUMNS,
  NON EMPTY {([Parent Project],[Project],[Fund Agency],[Project Institute],[PIName],[Duration],[Start Date],[End Date])} ON ROWS
FROM [ProjectHeader]
 </jp:mondrianQuery>
 <c:set var="title01" scope="session">Project Header details using Mondrian OLAP</c:set>
