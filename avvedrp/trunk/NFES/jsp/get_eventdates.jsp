<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*,java.util.Date,java.util.ArrayList,java.util.HashSet,java.text.SimpleDateFormat,java.sql.*,javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,com.erp.nfes.ConnectDB" %>
<%
int userId=Integer.parseInt(request.getParameter("userId"));
Connection conn=null;
ConnectDB conObj=new ConnectDB(); 
conn = conObj.getMysqlConnection();
String str1=request.getParameter("month");
String str2=request.getParameter("year");
String fetch_date=null;
ArrayList dates = new ArrayList();       
String qry=null;
String final_dates = "";
String delim = ",";


if(str1==null && str2==null){ 
	Date today = new Date();
	String strDateFormat = "yyyy-MM";
	SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
	fetch_date=sdf.format(today);
 } else { 
	 fetch_date=str2+"-0"+str1;
 }
try
{	
	qry="select date_format(event_date,'%d') as event_date,event_description from event_scheduler where date_format(event_date,'%Y-%m')='"+fetch_date+"' and idf='"+userId+"'";
	PreparedStatement pst1=conn.prepareStatement(qry);
	ResultSet rs1=pst1.executeQuery();
	while(rs1.next())
	{
		dates.add(rs1.getInt("event_date"));
	}
	//Removing Duplicates
	HashSet h = new HashSet(dates);
	dates.clear();
	dates.addAll(h);
	//Imploding date values with commas
    for(int i=0; i<dates.size(); i++) {
        if(i!=0) { final_dates += delim; }
        final_dates += dates.get(i);
    }
    
}catch(Exception e)
{
	e.printStackTrace();
}
conn.close();
%>
<%=final_dates%>