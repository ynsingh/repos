<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head><%--
		<link rel="stylesheet" type="text/css" href="style/jquery.datepick.css" />
	--%>
	<script src='dwr/util.js'></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/interface/DisplayTestName.js'></script>
	<script src='dwr/interface/ResultDate.js'></script>
	<script type="text/javascript" src="javascript/datetimepicker.js" /></script>
		<script type="text/javascript" src="javascript/validatecomboBox.js"></script>
	
	<script type="text/javascript">
	
	function validateResultPeriod(){
	var lastDate = dwr.util.getValue("lastResultDate");
	var from = dwr.util.getValue("resultFrom");
    var to = dwr.util.getValue("resultTo");	
   // alert("Last Date : "+lastDate);	
    var flag=true;
    if(lastDate!="" || from!="" || to!=""){
	DisplayTestName.checkTimePeriod(from, to, function(data)
	{
	 //alert(data);
	 if(!data){
	 alert("invalid Result Display Period");
	 flag=false;
	 }
	 });
	 
	 
	 DisplayTestName.checkTimePeriod(from, lastDate, function(data)
	{
	 //alert(data);
	 if(!data){
	 alert("Result can be displayed Maximum for one month after processing of the sheets. Date cannot be greater than "+ lastDate);
	 flag=false;
	 }
	 });
	DisplayTestName.checkTimePeriod(to, lastDate, function(data)
	{
	 //alert(data);
	 if(!data){
	 alert("Result can be displayed Maximum for one month after processing of the sheets. Date cannot be greater than "+ lastDate);
	 flag=false;
	 }
	 });
	if(flag){
	flag=checkComboBoxValue();
	}
	return flag;
	}
	}
	
	function validateTimePeriod(){
		//alert("inside time period");
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
		//alert("inside time from : " + from); 
				//alert("inside time to : " + to); 
		if(!(from=="--Select--" || to=="--Select--")){
	DisplayTestName.checkTimePeriod(from, to, function(data)
	{
	 //alert(data);
	 if(!data){
	 alert("invalid Time period");
	     dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
	     var selectTestName = document.getElementById("testName");
	     selectTestName.options[0] = new Option('--Select--', 0);
	     
	     
	}else{
	populateName();
	}
	}
	);
	}
	}	
			
	function getResultDuration(){
	
	var testname = dwr.util.getValue("testname");
	if(!(testname=="--Select--")){ 
	ResultDate.getResultDate(testname, function(data)
	{
	  dwr.util.setValue("lastResultDate", data[3]);
	  dwr.util.setValue("resultFrom", data[1]);
	  dwr.util.setValue("resultTo", data[2]);
	  dwr.util.setValue("firstResultDate", data[0]);
	}
	);
	}
	}
	
	function populateName(){
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
	//alert(from);
    //alert(to);
	
	DisplayTestName.populateNameListManageResult(from, to, function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("testname")[0]);
  	dwr.util.addOptions(document.getElementsByName("testname")[0],data);
  }
  );
	
	}

	
	function populateDate(){
	
 DisplayTestName.selectDateManageResult(function(data)
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

	<jsp:include page="Menu.jsp"></jsp:include>
	
			<html:javascript formName="resultSchedule"/>
	
				<html:form action="/manageResult" onsubmit="validateResultSchedule(this)">	
											<input type="hidden" name="lastResultDate" id="lastResultDate"/>
				                             <input type="hidden" name="firstResultDate" id = "firstResultDate"/>
  				
				<font face="Arial" color="#000040"><STRONG><bean:message key="link.manageresult"/></STRONG></font>			
					<center> 
						<table> 
						
						  <tr><td><font face="Arial" color="#000040">Test Date </font></td><td><font face="Arial" color="#000040"> From </font><html:select indexed="fromDate" property="fromDate"   onchange="validateTimePeriod();"/><html:errors property="fromDate"/></td><td><font face="Arial" color="#000040">  To: </font><html:select indexed="toDate" property="toDate"  onchange="validateTimePeriod();"/><html:errors property="toDate" /></td></tr>
			
 
							<tr> 
								<td> 
								<font face="Arial" color="#000040"><bean:message key="label.testname"/>	</font> 
								</td> 
								<td> 
									<html:select indexed="testname" property="testname" onchange="getResultDuration();"> 
                                         <html:option value="0"><bean:message key="msg.select"/> </html:option>
										</html:select> 
								</td> 
							</tr> 
							<tr><td><font face="Arial" color="#000040"><bean:message key="label.resultFrom"/> </font></td><td><html:text property="resultFrom" indexed="resultFrom"></html:text><a href="javascript:NewCssCal('resultFrom','yyyymmdd')"><img
									src="img/cal.gif" width="16" height="16" border="0"
									alt="Pick a date"> </a> <bean:message key="format.date"/>
							<font color="red" size="2">*</font><html:errors property="resultFrom"/></td></tr>
							   <tr> <td><font face="Arial" color="#000040"><bean:message key="label.resultTo"/> </font></td><td><html:text property="resultTo" indexed="resultTo"></html:text><a href="javascript:NewCssCal('resultTo','yyyymmdd')"><img
									src="img/cal.gif" width="16" height="16" border="0"
									alt="Pick a date"> </a> <bean:message key="format.date"/>
							<font color="red" size="2"></font> <html:errors property="resultTo"/></td></tr>
	<tr><td><html:submit onclick="return validateResultPeriod();"></html:submit></td><td><html:cancel></html:cancel></td></tr>
		</table></center>
			
			
			
			
			
			
			
		</html:form>	
	</body>
</html>

