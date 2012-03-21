<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 
<jsp:useBean id="getUserDetails" class="com.erp.nfes.GetRecordValue" scope="session"/> 

<%
String lc="";
try{
     lc=(String) session.getAttribute("language");         
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");            
     ml.init(lc);    
           
}catch(Exception e){
     e.printStackTrace();
}
SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
Date date = new Date();
String value = sdf1.format(date);
%>



<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">

<HEADprofile=http://gmpg.org/xfn/11>
<TITLE>Approval List</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<link href="../css/oiostyles.css" type="text/css" rel="stylesheet">
<link href="../css/jquery.datepick.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="../js/jquery-1.4.2.min.js"></script>
<script language="JavaScript" src="../js/jquery.datepick.js"></script>
<script language="javascript">
$(document).ready(function(){
$('#datefrom').datepick({maxDate:'',dateFormat: 'dd-mm-yy',closeAtTop: false, showStatus: true,  showOn: 'both', buttonImageOnly: true, buttonImage: '../images/calendar.gif'});
$('#dateto').datepick({maxDate:'',dateFormat: 'dd-mm-yy',closeAtTop: false, showStatus: true,  showOn: 'both', buttonImageOnly: true, buttonImage: '../images/calendar.gif'});
});
</script>

</HEAD>
<BODY class="bodystyle">

<FORM action="../ReportsServlet" method="POST" name="reports" target="_blank" >
<div align="center" class="listdiv"> 
<div style="text-align: center; font-size:14px;font-weight: bold;height:8px;"><%=ml.getValue("approval_list")%></div>

<br>
<table class="search_field_div" width=98% align="center">
<tr><td>
<table >
<tr>
<td class="labeltext" width="12%"><%=ml.getValue("report_date")%></td>
<td class="labeltext" width="43%"><input type="text" id="datefrom" name="datefrom" value="<%=value%>" readonly="readonly" size="15" />
&nbsp;&nbsp;<%=ml.getValue("to")%>&nbsp;&nbsp;<input type="text" id="dateto" name="dateto" value="<%=value%>" readonly="readonly" size="15" />
</td>
</tr>
<tr>
<td class="labeltext" width="12%"><%=ml.getValue("university")%></td>
<%if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){%>		
	<td width="43%"><Input Type="text" SIZE="35" name="searchbyUniversity"  id="searchbyUniversity" disabled value="<%=getUserDetails.getUniversity(request.getUserPrincipal().getName())%>"/></td>
<%}else{%>
<td width="43%"><Input Type="text" SIZE="35"  name="searchbyUniversity"  id="searchbyUniversity" /></td>
<%}%>	

<td class="labeltext" width="12%"><%=ml.getValue("institution")%></td>
<td width="43%"><Input Type="text" SIZE="35"  name="searchbyInstitution"  id="searchbyInstitution" /></td>
</tr>
<tr>
<td class="labeltext" width="12%"><%=ml.getValue("department")%></td>
<td width="43%"><Input Type="text" SIZE="35"  name="searchbyDepartment"  id="searchbyDepartment" /></td>
<td class="labeltext" width="12%"><%=ml.getValue("title")%></td>
<td width="43%">
	<Select name="searchbyTitle"  id="searchbyTitle">
	<option value="" SELECTED>-Select-</option>
	<%
		try
		{	
			Connection conn1=null;	
	        conn1 = db.getMysqlConnection();
	        PreparedStatement pst=null;
			pst=conn1.prepareStatement("SELECT fld_value FROM `general_master` WHERE category='Title' AND  active_yes_no=1 ORDER BY fld_value");						
			ResultSet rs=pst.executeQuery();		
			while(rs.next())		
			{%>
			<option value=<%=rs.getString("fld_value")%>><%=rs.getString("fld_value")%></option>;		 			
			<%}
		 }catch(Exception e){}		
		%>
	</select>
	</td>
</tr>
<tr>
<td class="labeltext" width="12%"><%=ml.getValue("first_name")%></td>
<td width="43%"><Input Type="text" SIZE="35"  name="searchbyFirstname"  id="searchbyFirstname" /></td>
<td class="labeltext" width="12%"><%=ml.getValue("last_name")%></td>
<td width="43%"><Input Type="text" SIZE="35"  name="searchbyLastname"  id="searchbyLastname" /></td>
</td>
</tr>

<tr>
<td class="labeltext" width="12%"><%=ml.getValue("category")%></td>
<td width="43%"><select name="category" id="category">
<option value="">All</option>
<option value="personal_details">Personal Details</option>
<option value="qualification">Qualification</option>
<option value="awards">Award</option>
<option value="journal_papers">Publications</option>
<option value="conference_papers">Conference Papers</option>
<option value="books_chapters">Books or Chapters</option>
<option value="talks_lectures">Invited Talks</option>
<option value="training">Seminars</option>
<option value="projects">Projects</option>
<option value="patents">Patents</option>
<option value="media_publication">Media Publication</option>
<option value="thesis">Student Project</option>
<option value="faculty_exchange">Faculty Exchange</option>
<option value="professional_body">Professional Bodies</option>
<option value="consultancy_offered">Consultancy Offered</option>
<option value="governance">Governance</option>
<option value="review_committees">Review Committees</option>
<option value="community_service">Community Service</option>
</select>
<Input Type="HIDDEN" name="action"  id="action" value="ApprovalReport" />
</td>
</tr>

<tr>
<td>&nbsp;</td>
<td class="labeltext">
<input type="Submit" name="showReport" value="<%=ml.getValue("print")%>" />
<!--  Reports Format Selection Block -->
<table class="ReportFormatTable"><tr>
<script language="javascript">	
function setReporFormat(value){
	document.getElementById("ReportFormat").value=value;
}
</script>
<td class="labeltext">
<input type="HIDDEN" NAME ="ReportFormat" ID="ReportFormat" value="pdf">
<%=ml.getValue("format")%> :
<input id="Report_Format" name="Report_Format" type="radio" value="pdf" checked="checked" onClick="setReporFormat(this.value)";/>PDF
<input type="radio" id="Report_Format" name="Report_Format" value="csv" onClick="setReporFormat(this.value)" /> CSV
</td>  
</tr>
</table>
</td>
</tr></table>
</td></tr></table>
<br>
</form>
</body>
</html>