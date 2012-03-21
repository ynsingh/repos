<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="org.codehaus.groovy.grails.commons.GrailsApplication" %>
<%@ page import="grails.util.GrailsUtil" %>
 <%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
 <%@ page import="mondrian.rolap.*;" %>
 <% mondrian.rolap.RolapSchema.clearCache(); %>
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
            String propertiesFileName = tomcat_path+"/conf/gmsDb.properties";
            java.io.FileInputStream fis = new java.io.FileInputStream( propertiesFileName );
            java.util.Properties props = new java.util.Properties();
            props.load( fis );
            // getting the properties
            db_url = props.getProperty("gmsDbUrl");
            db_usr = props.getProperty("gmsDbUSer");
            db_pswd = props.getProperty("gmsDbPassword");
            jdbc_url=db_url+"?user="+db_usr+"&password="+db_pswd;
    }
    else
    {
             String propertiesFileName = request.getRealPath( "/" ) +
                                                System.getProperty( "file.separator" ) +
                                                "images" +
                                                System.getProperty( "file.separator" ) +
                                                "masterDb.properties";
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
catalogUri="/WEB-INF/queries/projects.xml">

WITH MEMBER [Measures].[Balance] AS '[Measures].[Receipt Amount]-
([Measures].[Expense Amount]+[Measures].[Amount Transferred])',
FORMAT_STRING = '#,###'   
SELECT {[Measures].[Amount Allocated],[Measures].[Receipt Amount],
[Measures].[Expense Amount],[Measures].[Amount Transferred],[Measures].[Balance]} ON COLUMNS,
  NON EMPTY {[ProjectName]} ON ROWS
FROM [Projects]
WHERE [Party].[<%=party%>]
 </jp:mondrianQuery>
 <c:set var="title01" scope="session">Project details using Mondrian OLAP</c:set>
