<!-- 
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 * Author: Anshul Agarwal

 -->
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@page import="in.ac.dei.mhrd.omr.testSetUp.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>Online OMR Evaluation System</title>

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
	
	<script src='dwr/util.js'></script>
	<script src='dwr/engine.js'></script>
	
		<script type="text/javascript">
		
		function change()
			{	
				var val=dwr.util.getValue("totalSection");
				 var secTable = document.getElementById('sectionUpdate');
  				 var lastElement=secTable.rows.length;  				 
  				 var index=lastElement-1;  			
				if(val>index)
					{
					for(var i=1;i<=(val-index);i++)
						{
						addRow();
						}
					}
				else
					{
					for(i=1;i<=(index-val);i++)
					{
					removeRow();
					}
					}
			}
		
			function checkDisable(disable,section)
				{
				var questions=dwr.util.byId("noq"+section);
				if(disable == 'false')
					{
					questions.removeAttribute('readOnly');
					return false;
					}
				}
				
		function verifyQuestion()
			{
			var totalQuestion=0;
			var toq=0;
			var noOfQues = dwr.util.getValue("totalQuestion");
			var totalSection=dwr.util.getValue("totalSection");
			var markEach;
			var markNeg;
				for(var i=1; i<=totalSection; i=i+1)
				{
				toq=(document.getElementById("noq"+i)).value;
				
				markEach=(document.getElementById("meq"+i)).value;
				
				markNeg=(document.getElementById("nm"+i)).value;
				
				if(toq==""){
               		 alert("Please enter the number of questions in section "+ i);
               		 return false;
               		 }
               		 if(!isInteger(toq)){
               		 alert('Question number must be an integer in section ' +i);
               		 return false;
               		 }
               		 if(parseInt(toq)<1){
               		 alert("Invalid question number in section "+i);
               		 return false;
               		 }
				if(markEach==""){
				alert('Please enter the marks for section : ' + i);
				return false;
				}
				
               	if(isNaN(markEach)){
               	alert('Marks should be numeric in section ' + i);
               	return false;
               	}
						if(markEach>10 || markEach<0.25)
						{
						alert("Marks of each question cannot be greater than 10 and less than 0.25 \n for section : "+i);
						return false;
						}
					if(markNeg==""){
					alert('Please enter the Negative marks for section : ' + i);
				    return false;
					}
					if(isNaN(markNeg)){
               	alert('Negative Marks should be numeric in section ' + i);
               	return false;
               	}
					   if(parseFloat(markNeg)>10 && parseFloat(markNeg)<(-10))
							{
							alert("Negative marks can't be greater than 10 for Section No:"+i);
							return false;
							}
			
				totalQuestion=parseInt(totalQuestion)+parseInt(toq);
				}
				
			if(parseInt(noOfQues)!= parseInt(totalQuestion))
				{ 
				alert("Sum of no. of questions in each section should be equal to the total number of questions!");
				return false;
				}
		 	}
		</script>
		

  </head>
  
  <body>
  <table width="100%">
  <tr><td>  <jsp:include page="header.jsp"></jsp:include></td></tr>
  <tr><td>	<hr width="100%"> </td></tr>
 <tr><td> <jsp:include page="Menu.jsp"></jsp:include></td></tr>
</table>

  <%!
  		 int sec;
  		//no.of sections
  		
  	   String testName;
  	    	   
  	   	
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
    	 String condition=cca.isCorrectAnsUploaded();

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
							<html:text property="testNo" size="6" readonly="<%=disable %>" indexed="testId" style="width=145px;"/></font>
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
						<bean:define id="date1" name="manageForm" property="date"></bean:define>
                         <input type="text" name="date" id="date" value="<%=date1 %>"/>
							<a href="javascript:NewCssCal('date','yyyymmdd')"><img
									src="img/cal.gif" width="16" height="16" border="0"
									alt="Pick a date"> </a> (YYYY-MM-DD)
							<font color="red" size="2">*</font>
						</td>
					</tr>
					<tr><td><font face="Arial" color="#000040"><bean:message key="label.totalSec"/></font> </td>
	        <td>
	        <html:select styleId="section" property="totalSection" indexed="totalSection" onchange="change();" disabled="<%=disable %>">
			<html:option value="1">1</html:option>
			<html:option value="2">2</html:option>
			<html:option value="3">3</html:option>
			<html:option value="4">4</html:option>
					</html:select>	</td></tr>
		<tr><td></td><td>				
		<table id="sectionUpdate" align=left>					
	        
					<%
						if(sec>=1)
							{
							
							%>
						
							<tr><td><bean:message key="label.secNo"/></td><td><bean:message key="label.noOfQues"/></td><td><bean:message key="label.MarksEachQues"/> </td><td><bean:message key="label.negMarks"/> </td></tr>
				<logic:iterate id="sectionDetailId" property="sectionDetail" name="manageForm">
					<bean:define id="sectionNumber" name="sectionDetailId" property="sectionNo"></bean:define>
					<bean:define id="noOfQues" name="sectionDetailId" property="noOfQuestion"></bean:define>
					<bean:define id="marksEachQuestion" name="sectionDetailId" property="marksEachQuestion"></bean:define>
					<bean:define id="negMarks" name="sectionDetailId" property="negMarks"></bean:define>
			<tr><td ><%=sectionNumber %></td><td><input type="text" id=noq<%=sectionNumber%> readonly name=noq<%=sectionNumber%> value=<%=noOfQues%> onfocus="return checkDisable('<%=disable%>','<%=sectionNumber%>');" onmouseover="checkDisable('<%=disable%>','<%=sectionNumber%>');"></td>
			<td><input type="text" id=meq<%=sectionNumber %> name=meq<%=sectionNumber %>  value=<%=marksEachQuestion %> ></td>
			<td><input type="text" id=nm<%=sectionNumber %> name=nm<%=sectionNumber %> value=<%=negMarks %> ></td></tr>
							
				</logic:iterate>
				<%} %>		 
			    	</table>
			    	</td></tr>
			    	<tr><td></td><td><html:submit onclick="return verifyQuestion();"></html:submit><html:reset value="Reset" onclick="history.go(0)"></html:reset>
			    	<html:button value="Cancel" property="btn" onclick="history.back();"/></td></tr>
			    	</table>
			    	<html:hidden property="totalSection"/>
			    	</html:form>
  </body>
</html:html>
