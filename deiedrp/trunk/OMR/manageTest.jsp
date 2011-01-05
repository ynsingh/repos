
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@page import="in.ac.dei.mhrd.omr.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>manageTest.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="javascript/datetimepicker.js" /></script>
		<script type="text/javascript" src="javascript/validation.js"></script>
	
		<script type="text/javascript">
			function checkDisable(disable,section)
				{
				var questions=document.getElementById("noq"+section);
				if(disable == 'false')
					{
					questions.removeAttribute('readOnly');
					return false;
					}
				}
		</script>
		

  </head>
  
  <body>
  <jsp:include page="Menu.jsp"></jsp:include>
  <%!
  		 int sec;
  		//no.of sections
  		
  	   String testName;
  	  
  	   int sectionNo;
  	   //for sequencing the section no.
  	   	
  	   int testId;
  	   int totalQuestion;
  	   boolean disable;
  	  	   
   %>
  <%
  	sec=(Integer)session.getAttribute("sec");
     	 //getting no. of sections
     	 
    	 testName=(String)session.getAttribute("testName");
    	 testId=(Integer)session.getAttribute("testId");
    	 CheckCorrectAns cca=new CheckCorrectAns(testId);
    	 String condition=cca.returnCorrectRs();

    	 	if(condition.equalsIgnoreCase("Exist"))
    	 		{
    	 		disable=true;
    	 		}
    	 		else
    	 		 {
    	 		 disable=false;
    	 		 }
  %>
  <html:javascript formName="manageForm"/>
    <html:form action="/updateTestSetup" onsubmit="return validateManageForm(this);">
		<br />
			
			<br/><blockquote><font face="Arial" color="#000040"><strong><bean:message key="link.managetestsetup"/> </strong></font></blockquote>
			
			<br />
			
				<table align="left">

					<tr>
						<td>
						<font face="Arial" color="#000040"><bean:message key="label.testname"/>	</font>
												<br></td>
						<td>
						<font face="Arial" color="#000040">
							<html:text property="testName" readonly="true"></html:text>
						</font>
						
						<br></td>
					</tr>
					<tr>
						<td>
						<font face="Arial" color="#000040"><bean:message key="label.testid"/>	</font>
						</td>
						<td>
						<font face="Arial" color="#000040">
							<html:text property="testNo" size="20" readonly="<%=disable %>" indexed="testId"/></font>
							<font color="red" size="2">*</font>
						</td>
					</tr>
					
					<tr>
						<td>
						<font face="Arial" color="#000040"><bean:message key="label.totalques"/>	</font>
						</td>
						<td>
							<font face="Arial" color="#000040"><html:text property="totalQuestion" indexed="totalQuestion" readonly="<%=disable %>"/></font>
							<font color="red" size="2">*</font>
						</td>
					</tr>

					<tr>
						<td>
						<font face="Arial" color="#000040"><bean:message key="label.ConductDate"/>	 </font>
						<br></td>
						<td>
							<html:text property="date" indexed="date"></html:text>
							<a href="javascript:NewCssCal('date','yyyymmdd')"><img
									src="img/cal.gif" width="16" height="16" border="0"
									alt="Pick a date"> </a> (YYYY-MM-DD)
							<font color="red" size="2">*</font>
						</td>
					</tr>
		<tr><td></td><td>				
		<table id="sectionUpdate" align=left>					
	        <tr><td><font face="Arial" color="#000040"><bean:message key="label.totalSec"/></font> </td>
	        <td>
	        <html:select styleId="section" property="totalSection" indexed="totalSection" onchange="change('update');" disabled="<%=disable %>">
			<html:option value="1">1</html:option>
			<html:option value="2">2</html:option>
			<html:option value="3">3</html:option>
			<html:option value="4">4</html:option>
					</html:select>	</td></tr>
					<%
						if(sec>=1)
							{
							
							%>
						
							<tr><td><bean:message key="label.secNo"/></td><td><bean:message key="label.noOfQues"/></td><td><bean:message key="label.MarksEachQues"/> </td><td><bean:message key="label.negMarks"/> </td></tr>
			
						<%
							SectionResultSet srs=new SectionResultSet();
							
							ResultSet rs=srs.returnRs(testId);
								try{
									while(rs.next())
										{
										 sectionNo=rs.getInt("Section_number");
						%>
			<tr><td ><%=sectionNo %></td><td><input type="text" id="noq" readonly name=noq<%=sectionNo%> value=<%=rs.getInt("No_of_question")%> onfocus="return checkDisable('<%=disable%>','<%=sectionNo%>');" onmouseover="checkDisable('<%=disable%>','<%=sectionNo%>');"></td><td><input type="text" name=meq<%=sectionNo %>  value=<%=rs.getInt("Marks_each_question") %> ></td><td><input type="text" name=nm<%=sectionNo %> value=<%=rs.getInt("Neg_Marks") %> ></td></tr>
								
							<%
								}
							}catch(Exception e)
								{
								out.print("Manage Jsp Exception:"+e.getMessage());
								}
							}
						 %>		
						 
			    	</table>
			    	</td></tr>
			    	<tr><td></td><td><html:submit onclick="return verifyQuestion();"></html:submit><html:reset value="reset" onclick="history.go(0)"></html:reset>
			    	<html:button value="Cancel" property="btn" onclick="history.back();"/></td></tr>
			    	</table>
			    	<html:hidden property="totalSection"/>
			    	</html:form>
  </body>
</html:html>
