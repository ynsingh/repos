<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>

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
     ml.init("StaffReport.jsp", lc);          
}catch(Exception e){
     e.printStackTrace();
}
%>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEADprofile=http://gmpg.org/xfn/11>
	<TITLE>Institution Transfer List</TITLE>
	<META http-equiv=Content-Type content="text/html; charset=UTF-8">
	<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
	<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
</HEAD>
<BODY class="bodystyle">

<FORM action="../ReportsServlet" method="POST" name="reports" target="_blank" >
<div align="center" class="listdiv"> 
<div style="text-align: center; font-size:14px;font-weight: bold;height:8px;"><%=ml.getValue("institution_transfer_list")%></div>
	
	<br>
	<table class="search_field_div" width=98% align="center">
		<tr><td>
		<table width="100%">
			<tr>
				<td width="13%" class="labeltext"><%=ml.getValue("university")%></td>
				<%if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){%>		
					<td><Input width="35%" Type="text" SIZE="35" name="university"  id="university" disabled value="<%=getUserDetails.getUniversity(request.getUserPrincipal().getName())%>"/></td>
				<%}else{%>
				<td ><Input width="35%" Type="text" SIZE="35"  name="university"  id="university" /></td>
				<%}%>	
			
				<td width="13%" class="labeltext"><%=ml.getValue("institution")%></td>
				<td width="35%"><Input Type="text" SIZE="35"  name="institution"  id="institution" /></td>
			</tr>
			<tr>
				<td width="13%" class="labeltext"><%=ml.getValue("department")%></td>
				<td width="35%"><Input Type="text" SIZE="35"  name="department"  id="department" />
				<Input Type="HIDDEN" name="action"  id="action" value="InstitutionTransferReport" />
				</td>
				<td width="13%" class="labeltext"><%=ml.getValue("title")%></td>
				<td><Select name="nametitle"  id="nametitle">
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
				<td width="13%" class="labeltext"><%=ml.getValue("first_name")%></td>				
				<td width="35%"><Input Type="text" SIZE="35"  name="firstname"  id="firstname" />
				<td width="13%" class="labeltext"><%=ml.getValue("last_name")%></td>
				<td width="35%"><Input Type="text" SIZE="35"  name="lastname"  id="lastname" />
				<Input Type="HIDDEN" name="action"  id="action" value="InstitutionTransferReport" />
				</td>
			</tr>
			<tr colspan="2">
				<td>&nbsp;</td>
				<td class="labeltext">
				<input type="Submit" name="showReport" value="<%=ml.getValue("print")%>" />
				<!--  Reports Format Selection Block -->
				<table class="ReportFormatTable">
				<tr>
					<script language="javascript">	
					function setReporFormat(value){
						document.getElementById("ReportFormat").value=value;
					}
					</script>
					<td class="labeltext">
					<input type="HIDDEN" NAME ="ReportFormat" ID="ReportFormat" value="pdf">
					Format :
					<input id="Report_Format" name="Report_Format" type="radio" value="pdf" checked="checked" onclick="setReporFormat(this.value)";/>PDF
					<input type="radio" id="Report_Format" name="Report_Format" value="csv" onclick="setReporFormat(this.value)" /> CSV
					</td>  
				</tr>
				</table>
				<!--  End of Report Format Selection Block -->			
				</td>
		 </tr>
	 </table>
		</td></tr>
	</table>
	<br>
</div>
</form>
</body>
</html>