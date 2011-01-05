<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
	<head>
		<title>JSP for ResultForm form</title>
		<script src='dwr/util.js'></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/interface/DisplayTestName.js'></script>
	<script type="text/javascript" src="javascript/validatecomboBox.js"></script>
	<script type="text/javascript">
	
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
	
	function populateName(){
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
	//alert(from);
	//alert(to);
	
	DisplayTestName.populateResultNameList(from, to, function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
  	dwr.util.addOptions(document.getElementsByName("testName")[0],data);
  }
  );
	
	}
	
	
	function populateDate(){
	
	//alert("inside Test ");
	
	//alert(testDate);
	DisplayTestName.selectDateResult(function(data)
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
		 <font face="Arial" color="#000040"><STRONG><bean:message key="msg.Result"/></STRONG></font>	
		<html:form action="/result">
        <table align="center">
       
				
		<tr><td><font face="Arial" color="#000040"><bean:message key="label.testDate"/> </font></td><td><font face="Arial" color="#000040"> <bean:message key="label.from"/> </font><html:select indexed="fromDate" property="fromDate"  onchange="validateTimePeriod();"><html:option value="0"><bean:message key="msg.select"/> </html:option></html:select><font color="red" size="2"><bean:message key="required.symbol"/></font> <html:errors property="fromDate" /></td>
		
		<td><font face="Arial" color="#000040"><bean:message key="label.to"/>: </font><html:select indexed="toDate" property="toDate" onchange="validateTimePeriod();"><html:option value="0"><bean:message key="msg.select"/> </html:option></html:select><font color="red" size="2"><bean:message key="required.symbol"/></font>   <html:errors property="toDate" /></td></tr>
			<tr>
			<td><font face="Arial" color="#000040"> <bean:message key="label.testname"/>  </font></td><td><html:select indexed="testName" property="testName">
			<html:option value="0"><bean:message key="msg.select"/> </html:option></html:select><font color="red" size="2"><bean:message key="required.symbol"/></font> 
			<html:errors property="testName"/></td></tr>		
	
			
			<tr><TD><html:submit onclick="return checkComboBoxValue();" /></TD>
<td><html:button value="cancel" property="btn" onclick="history.back();"/></td></tr></table>
</html:form>
	</body>
</html>

