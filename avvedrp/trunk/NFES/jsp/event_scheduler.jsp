<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11><TITLE>Change Password</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

<link type="text/css" href="../css/redmond/jquery-ui-1.8.14.custom.css" rel="stylesheet" />	
<script type="text/javascript" src="../js/timepicker/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="../js/timepicker/jquery-ui-1.8.14.custom.min.js"></script>
<script language="JavaScript" type="text/javascript" ></script>
<script type="text/javascript">
var userId = <%=request.getParameter("userId")%>;
var eventDates = new Array();			
$(document).ready(function() {
   $('#datepicker').datepicker({dateFormat: 'yy-mm-dd',onSelect:showPopup,beforeShowDay: showEventDates,onChangeMonthYear: loadEventDates});
   loadEventDates();
 });
 
 function showPopup(date)
 {
 window.open("add_events.jsp?userId="+userId+"&sel_date="+date,"DetailForm","height=600, width=800, screenX=50,left=200,screenY=50,top=50,scrollbars=yes,directories=no,location=no,menubar=no,resizable=no,status=yes,toolbar=no,modal=yes"); 
 }
 function loadEventDates(year, month)
 {
	 $.get('get_eventdates.jsp?userId='+userId, {month: month,year: year},
				function(data) {		
					  eventDates=data.split(',');
					  $('#datepicker').datepicker('refresh');
				});
 }
 function showEventDates(date) 
 {
	for (var i = 0; i < eventDates.length; i++) {
	if (date.getDate()== eventDates[i])
	 {
		return [true,'eventDay'];
	  }
	}
	return [true, 'busyDay'];
 }
</script>

<style type="text/css">
.ui-datepicker.ui-widget-content .ui-state-default { background:#F79825 }
.ui-widget-content .eventDay a.ui-state-default { background-color: #6EACC9; }
.ui-widget-content .busyDay a.ui-state-default { background-color:#E0EFFC; }
</style>
	
</HEAD>
<BODY class="bodystyle">
<form id="user" action="../ChangePasswordServlet" method="post" name="user" onSubmit="return checkval()">
<div class="listdiv_eventscheduler">
<h4 align="center">Event Scheduler</h4> 
<table style="background: none repeat scroll 0 0 #CCE6F3;" align="center" width="98%">  	

<tr>
<td colspan=2>
</td>
</tr>

<tr>	 
 <td colspan=2><div id="datepicker"></div></td>
 <td colspan=2></td>
</tr>

</table>
<br>
</div>
</form>
</BODY></HTML>