<!--
* @(#) selectGroup.JSP
* @Author : Dheeraj Singh
* @Date : 10 Aug, 2011
* Version 1.0
* Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
* All Rights Reserved.
*
* Redistribution and use in source and binary forms, with or
* without modification, are permitted provided that the following
* conditions are met:
*
* Redistributions of source code must retain the above copyright
* notice, this list of conditions and the following disclaimer.
*
* Redistribution in binary form must reproducuce the above copyright
* notice, this list of conditions and the following disclaimer in
* the documentation and/or other materials provided with the
* distribution.
*
*
* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
* FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
* OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
* BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
* OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
* EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* Contributors: Members of EdRP, Dayalbagh Educational Institute
*/
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
		<script src='dwr/interface/ShowMessage.js'></script>
		<script type="text/javascript" src="javascript/validatecomboBox.js"></script>
		<script type="text/javascript">
/**
*  This method compares the test from and to dates 
*/
	function validateTimePeriod(){
		//alert("inside time period");
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
		 
		if(!(from=="--Select--" || to=="--Select--" || from=="0" || to=="0")){
		
	ComboBoxOptions.checkTimePeriod(from, to, function(data)
	{
	 //alert(data);
	 if(!data){
	 ShowMessage.getMessage("msg.dateValidation", function(value) {alert(value);});
	     dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
	     var selectTestName = dwr.util.byId("testName");
	     selectTestName.options[0] = new Option('--Select--', 0);
	     
	     
	}else{
	populateName();
	}
	}
	);
	}
	else{
	dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
	     var selectTestName = dwr.util.byId("testName");
	     selectTestName.options[0] = new Option('--Select--', 0);
	}
	}	
	
/**
*  This method populates the test names in TestName combobox 
*/
	function populateName(){
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
	
	
	ComboBoxOptions.populateSectionGroupsName(from, to, function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
  	dwr.util.addOptions(document.getElementsByName("testName")[0],data);
  }
  );
	
	}
	
/**
*  This method populates the from and to test dates in their comboboxes 
*/
	function populateDate(){
	
	ComboBoxOptions.selectDateGroupSection(function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("fromDate")[0]);
  	dwr.util.addOptions(document.getElementsByName("fromDate")[0],data);
  	
  	dwr.util.removeAllOptions(document.getElementsByName("toDate")[0]);
  	dwr.util.addOptions(document.getElementsByName("toDate")[0],data);
  	
  	
  }
  );
  
	}
	
	</script>



	</head>
	<body onload="populateDate();">
		 <table width="100%">
  <tr><td>  <jsp:include page="header.jsp"></jsp:include></td></tr>
  <tr><td>	<hr width="100%"> </td></tr>
 <tr><td> <jsp:include page="Menu.jsp"></jsp:include></td></tr>
</table>
	<font face="Arial" color="#000040"><STRONG><bean:message key="label.group" /></STRONG></font>
	<html:form action="/selectGroup">
		<table align="center">
			<tr>
				<td>
					<font face="Arial" color="#000040"><bean:message key="label.testDate" /></font>
				</td>
				<td>
					<font face="Arial" color="#000040"> <bean:message key="label.from" /></font> &nbsp;&nbsp;
					<font face="Arial" color="#000040"><strong>:</strong></font>
				</td>
				<td>
					<html:select indexed="fromDate" property="fromDate" style="width:150px" onchange="validateTimePeriod();">
						<html:option value="0">
							<bean:message key="msg.select" />
						</html:option>
					</html:select>
					<font color="red" size="2"><bean:message key="required.symbol" /></font>
					<html:errors property="fromDate" />
				</td>
				<td>
					<font face="Arial" color="#000040"><bean:message key="label.to" /> </font> &nbsp;&nbsp;
					<font face="Arial" color="#000040"><strong>:</strong></font>
				</td>
				<td>
					<html:select indexed="toDate" property="toDate" style= "width:150px" onchange="validateTimePeriod();">
						<html:option value="0">
							<bean:message key="msg.select" />
						</html:option>
					</html:select>
					<font color="red" size="2"><bean:message key="required.symbol" /></font>
					<html:errors property="toDate" />
				</td>
			</tr>
			<tr>
				<td>
					<font face="Arial" color="#000040"> <bean:message key="label.testname" /> </font>
				</td>
				<td>
					<font color="#ffffff"><bean:message key="label.from" /></font>&nbsp;&nbsp;&nbsp;
					<font face="Arial" color="#000040"><strong>:</strong></font>
				</td>
				<td>
					<select id="testName" name="testName" style="width: 150px">
						<option value="0">
							<bean:message key="msg.select" />
						</option>
					</select>
					<font color="red" size="2"><bean:message key="required.symbol" /></font>
					<html:errors property="testName" />
				</td>
			</tr>
			<tr>
				<td>
					<font color="#ffffff"><bean:message key="label.from" /></font>
				</td>
				<td>
					<font color="#ffffff"><bean:message key="label.from" /></font>
				</td>
				<td>
					<html:submit onclick="return checkComboBoxValue();" />
					<html:cancel />
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html>
