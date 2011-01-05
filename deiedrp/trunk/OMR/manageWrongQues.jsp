<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
	<head>
		<title>Online OMR Evaluation System</title>
		
		<script src='dwr/util.js'></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/interface/DisplayTestName.js'></script>
	<script type="text/javascript" src="javascript/validatecomboBox.js"></script>
	
	<script type="text/javascript">
	
	
	function validateTimePeriod(){
		//alert("inside time period");
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
		 
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
	
	DisplayTestName.populateWrongQuesName(from, to, function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
  	dwr.util.addOptions(document.getElementsByName("testName")[0],data);
  }
  );
}
	
	
	function populateDate(){
	
	//alert("inside Test ");
	
	//alert(testDate);
	DisplayTestName.selectDateWrongQues(function(data)
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
	<body  onload="populateDate();">
				<jsp:include page="Menu.jsp"></jsp:include>
				<html:form action="/manageWrongQues">	
				
				<font face="Arial" color="#000040"><STRONG><bean:message key="msg.WrongQues"/></STRONG></font>			
					<center> 
						<table> 
						
						<tr><td>  <font face="Arial" color="#000040"><bean:message key="label.testDate"/> </font></td><td> <font face="Arial" color="#000040"> <bean:message key="label.from"/> </font><html:select indexed="fromDate" property="fromDate"  onchange="validateTimePeriod();"/><html:errors property="fromDate"/></td><td> <font face="Arial" color="#000040">  <bean:message key="label.to"/> </font><td><html:select indexed="toDate" property="toDate" onchange="populateName();"/><html:errors property="toDate" /></td></tr>
			
 
							<tr> 
								<td> 
								<font face="Arial" color="#000040"><bean:message key="label.testname"/>	</font> 
								</td> 
								<td> 
									<html:select indexed="testName" property="testName"> 
                                      <html:option value="0"><bean:message key="msg.select"/> </html:option>
										</html:select> 
								</td> 
							</tr> 
							
 
						</table> 
 
						<html:submit onclick="return checkComboBoxValue();" ></html:submit> 
						&nbsp; 
<html:button value="cancel" property="btn" onclick="return checkComboBoxValue();"></html:button> 
					</center> 
				</html:form> 
	</body>
</html>

