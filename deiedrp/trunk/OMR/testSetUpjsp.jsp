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
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
	<head>
		<title>Online OMR Evaluation System</title>
		<script src='dwr/util.js'></script>
	    <script src='dwr/engine.js'></script>
	   <script src='dwr/interface/OMRValidation.js'></script>
	    
	    
		<script type="text/javascript" src="javascript/datetimepicker.js" /></script>
		<script type="text/javascript" src="javascript/testSetUp.js"></script>

	<script type="text/javascript">
		function setSection(){
		var setObj = document.getElementById("section"); 
         setObj.options[setObj.selectedIndex].value="0";
         setObj.options[setObj.selectedIndex].text="--Select--";
         setObj.options[1] = new Option('1', 1);
                  setObj.options[2] = new Option('2', 2);
                  setObj.options[3] = new Option('3', 3);
                  setObj.options[4] = new Option('4', 4);
         
		}
		</script>
		
	</head>
	
	<%--<body onload="setSection()">

	 --%>
	 <body>
	 
	 <table width="100%">
  <tr><td>  <jsp:include page="header.jsp"></jsp:include></td></tr>
  <tr><td>	<hr width="100%"> </td></tr>
 <tr><td> <jsp:include page="Menu.jsp"></jsp:include></td></tr>
</table>
		<div style="height: 72%">
		<html:javascript formName="testSetUpForm"/>
		<html:form action="/test" onsubmit="return validateTestSetUpForm(this);">
						<br />
			
			<br/><blockquote><font face="Arial" color="#000040"><strong><bean:message key="link.testsetup"/></strong></font></blockquote>
			
			<br />
			<input type="hidden" name="existTestName" id="existTestName" />
				<table align="left">

					<tr>
						<td width="175px">
						<font face="Arial" color="#000040">	<bean:message key="label.testname"/></font>
												</td>
						<td>
						<font face="Arial" color="#000040">
							<html:text property="testName"  onchange="validateTestName();"/></font>
							<font color="red" size="2">*</font>
							<html:errors property="testName" />
						</td>
					</tr>
					<tr>
						<td>
						<font face="Arial" color="#000040">	<bean:message key="label.testID"  /></font>
												</td>
						<td>
						<font face="Arial" color="#000040">
							<html:text property="testNo" onfocus="validateTestName();" /></font>
							<font color="red" size="2">*</font>
							<html:errors property="testNo" />
						</td>
					</tr>
					
					<tr>
						<td>
						<font face="Arial" color="#000040">	<bean:message key="label.totalQues" /></font>
						</td>
						<td>
							<font face="Arial" color="#000040"><html:text property="totalQues" /></font>
							<font color="red" size="2">*</font>
							<html:errors property="totalQues" />
						</td>
					</tr>

					<tr>
						<td>
						<font face="Arial" color="#000040">	<bean:message key="label.ConductDate"/> </font>
						</td>
						<td>
							<input type="text" name="sdate" id="sdate" />
							<a href="javascript:NewCssCal('sdate','yyyymmdd')"><img
									src="img/cal.gif" width="16" height="16" border="0"
									alt="Pick a date"> </a> <bean:message key="format.date"/>
							<font color="red" size="2">*</font>
							<html:errors property="sdate" />
						</td></tr>
						
					<tr>
						<td>
							<font face="Arial" color="#000040">	<bean:message key="label.SheetFormat"/> </font>
						</td>
						<td>
							<html:select styleId="sheetFormat" property="sheet" onchange="askGroup();">
								<html:option value="0">--Select--</html:option>
								<html:option value="NGC">8 Digits Roll Number</html:option>
								<html:option value="GRC">6 Digits Roll Number</html:option>
							</html:select> 
						</td>
					</tr>
					
					<tr>
						<td>
							<font face="Arial" color="#000040">	<bean:message key="label.groupExists"/> </font>
						</td>
						<td>
							<html:select styleId="groupExists" property="groupExists" disabled="true" onchange="addGrp();" >
								<html:option value="0">--Select--</html:option>
								<html:option value="Y">Yes</html:option>
								<html:option value="N">No</html:option>
							</html:select> 
						</td>
					</tr>	
			
			
					<tr><td><font face="Arial" color="#000040">
<bean:message key="label.section"/></font> </td><td><html:select styleId="section" property="totalSec" onchange="display();">
					<%--<html:option value="0">--select--</html:option>
			<html:option value="1">1</html:option>
			<html:option value="2">2</html:option>
			<html:option value="3">3</html:option>
			<html:option value="4">4</html:option>
					--%></html:select>	<html:errors property="totalSec" /></td></tr>
						
					<tr><td colspan="2"><div id="divId">
						
			         </div></td></tr>
					
			
			<tr><td></td> <td><html:submit onclick="return verify();"></html:submit><html:cancel/></td></tr>
			</table>
		</html:form>
		</div>

	</body>
</html>

