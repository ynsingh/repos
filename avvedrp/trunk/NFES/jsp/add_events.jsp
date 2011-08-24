<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream,com.erp.nfes.ConnectDB" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD
profile=http://gmpg.org/xfn/11><TITLE>EVENT SCHEDULER</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<script language=javascript>window.opener.location.reload(true);</script>
<link rel="stylesheet" media="all" type="text/css" href="../css/redmond/jquery-ui-1.8.14.custom.css" />
        <script type="text/javascript" src="../js/timepicker/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="../js/timepicker/jquery-ui-1.8.14.custom.min.js"></script>
		<script type="text/javascript" src="../js/timepicker/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript">
		$(function(){
		$('#event_start_time').timepicker({ ampm: true });
		$('#event_end_time').timepicker({ ampm: true });
		});
		</script>
		<style type="text/css"> 			
			
			#ui-datepicker-div{ font-size: 80%; }			
			/* css for timepicker */
			.ui-timepicker-div .ui-widget-header{ margin-bottom: 8px; }
			.ui-timepicker-div dl{ text-align: left; }
			.ui-timepicker-div dl dt{ height: 25px; }
			.ui-timepicker-div dl dd{ margin: -25px 10px 10px 65px; }
			.ui-timepicker-div td { font-size: 90%; }			
		</style>
		<script language="javascript">
		function closeWindow()
 		{
 		javascript:window.close();
 		}
 		function validateData()
 		  {
 		   if(document.getElementById("event_description").value == "")
 		    {
 		     alert("Please Enter Mandatory Field!!!");
 		     return false;
 		    }
 		    return true;
 		   }
		</script>
	  
</HEAD>
<BODY class="bodystyle">		
<%
if(request.getParameter("val")==null){
}else if(request.getParameter("val").equals("0")) {%>
<script language="javascript">
alert("Event already exists.");
</script>
<% }
int userId = Integer.parseInt(request.getParameter("userId"));
int eventId = 0;
String sel_date = null;
Connection conn=null;
String event_date = null;
String event_start_time = null;
String event_end_time = null;
String event_description = null;


String start_time_val = "";
String end_time_val = "";
String desc_val = "";
String edit = "";
String edit_val = "";
String delete = "";
String del_val = "";

ConnectDB conObj = new ConnectDB(); 
conn = conObj.getMysqlConnection();
int staffCount=0;
sel_date = request.getParameter("sel_date");	
edit = request.getParameter("edit");	
if(edit!=null)
{
	edit_val=edit;
	PreparedStatement query=conn.prepareStatement("select * from event_scheduler where idf="+userId+" and id='"+edit_val+"'");		
	ResultSet rs1=query.executeQuery();
	while(rs1.next())
	{
		sel_date=rs1.getString("event_date");		
		start_time_val=rs1.getString("event_start_time");		
		end_time_val=rs1.getString("event_end_time");		
		desc_val=rs1.getString("event_description");	
	}
}
delete = request.getParameter("delete");	
if(delete!=null)
{
	del_val = request.getParameter("delete");				
	PreparedStatement st = conn.prepareStatement("delete from event_scheduler where id='"+del_val+"'");							
	st.executeUpdate();
	response.sendRedirect("add_events.jsp?userId="+userId+"&sel_date="+sel_date+"&d=2");
}
%>
<div class="listdiv">
<h4>&nbsp;&nbsp;&nbsp;Event Scheduler</h4> 
<table style="background: none repeat scroll 0 0 #CCE6F3;" align="center" width="98%">  	
<tr>
<td>
<form id="add_events" action="../AddEventsServlet" method="post" name="add_events">
<table align="left" width="100%">
  <tr>
    <td width="174">Date</td>
    <td width="350"><input type="text" name="event_date" id="event_date"  value="<%=sel_date%>" size="10" readonly="readonly"/></td>
  </tr>
  <tr>
    <td>Start Time</td>
    <td><input type="text" name="event_start_time"  id="event_start_time" value="<%=start_time_val%>" size="10"/>&nbsp;&nbsp;</td>
  </tr>
   <tr>
    <td>End Time</td>
    <td><input type="text" name="event_end_time" id="event_end_time"  value="<%=end_time_val%>" size="10"/></td>
  </tr>
  <tr>
    <td>Event Description<span style="color: red;">*</span></td>
    <td><textarea name="event_description" id="event_description"><%=desc_val%></textarea></td>
  </tr>
  <tr>
    <td colspan="2" class="thead" align="center">
    <% if(edit!=null) { %><input type="submit" value="Edit"/><input type="hidden" name="mode" value="edit"/><input type="hidden" name="edit_id" value="<%=edit_val%>"/><% } %>
    <% if(edit==null) { %><input type="submit" value="Add" onclick="return validateData()" /><input type="hidden" name="mode" value="add"/><% } %>
    <input type="button" name="close" value="Close" onclick="closeWindow()" /> </td> 
  </tr>
</table>
<input type="hidden" name="userId" value="<%=userId%>"/>

</form>
</td>
<tr>
<td>
<br >
<table align="left">
<tr><td><h5>EVENTS SCHEDULED FOR <%=sel_date%></h5></td></tr>
<%
PreparedStatement query=conn.prepareStatement("select * from event_scheduler where idf='"+userId+"'and event_date='"+sel_date+"'");		
ResultSet rs2=query.executeQuery();
while(rs2.next())
{
	eventId=rs2.getInt("id");		
	event_date=rs2.getString("event_date");		
	event_start_time=rs2.getString("event_start_time");		
	event_end_time=rs2.getString("event_end_time");		
	event_description=rs2.getString("event_description");	
%>
  <tr>
    <td width="300">
    <form id="view_events" action="../AddEventsServlet" method="post" name="view_events">
		<table>		
		<tr><td><strong><%=event_start_time%>-<%=event_end_time%></strong></td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href='#' onclick="return editEvents('<%=userId%>','<%=sel_date%>','<%=eventId%>')">Edit<img src="../images/edit.png"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href='#' onclick="return deleteEvents('<%=userId%>','<%=sel_date%>','<%=eventId%>')">Delete<img src="../images/delete.png"/></a>
		</td></tr>
		<tr><td><%=event_description%></td><td><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr>
		</table>
		<input type="hidden" name="mode" value="edit"/>
	</form>
   </td>
  </tr>  
  <%
  }
  conn.close();
%>
</table>
</td>
</tr>
</table>
<br>
</div>
<script type="text/javascript">
function editEvents(userId,seldate,val)
{
	//window.opener.location.reload(true);
	window.location.href="add_events.jsp?userId="+userId+"&sel_date="+seldate+"&edit="+val;	
}
function deleteEvents(userId,seldate,val)
{
	var con=confirm("Do you want to delete this event?");
	if(con==true)
	window.location.href="add_events.jsp?userId="+userId+"&sel_date="+seldate+"&delete="+val;
	else
	return false;
}
</script>
</BODY>
</HTML>