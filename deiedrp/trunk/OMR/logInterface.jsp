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
	<script src='dwr/interface/ComboBoxOptions.js'></script>
	<script type="text/javascript" src="javascript/validatecomboBox.js"></script>
	
	<script type="text/javascript">
	
	function validateTimePeriod(){
		//alert("inside time period");
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
		//alert("inside time from : " + from); 
				//alert("inside time to : " + to); 
		if(!(from=="--Select--" || to=="--Select--")){
	ComboBoxOptions.checkTimePeriod(from, to, function(data)
	{
	 //alert(data);
	 if(!data){
	 alert("invalid Time period");
	     dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
	     var selectTestName = dwr.util.byId("testName");
	     selectTestName.options[0] = new Option('--Select--', 0);
	     
	     
	}else{
	populateName();
	}
	}
	);
	
	}	
	}
	
	function populateName(){
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
	//alert(to);
	
	ComboBoxOptions.populateLogNameList(from, to, function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
  	dwr.util.addOptions(document.getElementsByName("testName")[0],data);
  }
  );
	
	}
	
	
	function populateDate(){
	
	//alert("inside Test ");
	
	//alert(testDate);
	ComboBoxOptions.selectDateLog(function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("fromDate")[0]);
  	dwr.util.addOptions(document.getElementsByName("fromDate")[0],data);
  	
  	dwr.util.removeAllOptions(document.getElementsByName("toDate")[0]);
  	dwr.util.addOptions(document.getElementsByName("toDate")[0],data);
  	
  	
  }
  );
  //populateName();
	}
	
	</script>
	
		
	</head>
	<body onload="populateDate();">
	<div>
    <jsp:include page="header.jsp"></jsp:include>
	</div>
	<hr width="100%">
	<jsp:include page="Menu.jsp"></jsp:include>
	
		<font face="Arial" color="#000040"><STRONG><bean:message key="msg.log"/></STRONG></font>
		<html:form action="/logInter">
			<table align="center">
			<tr><td><font face="Arial" color="#000040"><bean:message key="label.testDate"/> </font></td><td><font face="Arial" color="#000040"> <bean:message key="label.from"/> </font><html:select indexed="fromDate" property="fromDate"  onchange="validateTimePeriod();"><html:option value="0"><bean:message key="msg.select"/> </html:option></html:select><font color="red" size="2"><bean:message key="required.symbol"/></font> <html:errors property="fromDate" /></td>
		
		<td><font face="Arial" color="#000040"><bean:message key="label.to"/>: </font><html:select indexed="toDate" property="toDate" onchange="validateTimePeriod();"><html:option value="0"><bean:message key="msg.select"/> </html:option></html:select><font color="red" size="2"><bean:message key="required.symbol"/></font>   <html:errors property="toDate" /></td></tr>
			<tr>
			<td><font face="Arial" color="#000040"> <bean:message key="label.testname"/>  </font></td><td><select id="testName" name="testName">
			<option value="0"><bean:message key="msg.select"/> </option></select><font color="red" size="2"><bean:message key="required.symbol"/></font> 
			<html:errors property="testName"/></td></tr>		
	
			<tr><td>Log:</td>
				<td> <html:select property="log">
					<html:option value="E"><bean:message key="log.viewerrlog"/> </html:option>
					<html:option value="T"><bean:message key="log.viewtestlog"/> </html:option>
			</html:select> <html:errors property="log"/>
			</td>
			</tr>
			<tr><td><html:submit  onclick="return checkComboBoxValue();" /></td><td><html:cancel/></td></tr>
			</table>
		</html:form>
	</body>
</html>

