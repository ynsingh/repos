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
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head>
		<link rel="stylesheet" type="text/css" href="style/jquery.datepick.css" />
	
	<script src='dwr/util.js'></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/interface/ComboBoxOptions.js'></script>
	<script src='dwr/interface/ResultDate.js'></script>
	<script type="text/javascript" src="javascript/datetimepicker.js" /></script>
	<script type="text/javascript" src="javascript/validatecomboBox.js"></script>
	<script type="text/javascript" src="javascript/ManageResult.js"></script>
	
	<script type="text/javascript" src="javascript/jQueryDatePicker/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="javascript/jQueryDatePicker/jquery.datepick.js"></script>
	<script type="text/javascript" src="javascript/jQueryDatePicker/jquery-ui-1.8.6.custom.min.js"></script>

	<script type="text/javascript">
	jQuery(document).ready(function(){
 	
 	//for datapicker
	jQuery('#resultFrom,#resultTo').datepick({ 
	dateFormat: 'yyyy-mm-dd',
	minDate:0,
	monthsToShow: 1,
	onSelect: customRange, showTrigger: '<img src="img/cal.gif" alt="Popup" class="trigger">'});
     
	function customRange(dates) {
	 
	 //var endDate =    document.getElementsByName("resultTo");
    if (this.id == 'resultFrom') { 
        jQuery('#resultTo').datepick('option', 'minDate', dates[0] || null); 
    } 
    else { 
        jQuery('#resultFrom').datepick('option', 'maxDate', dates[0] || null); 
    } 
	}
});
 </script>

	</head>
	<body onload="populateDate();">
<div>
    <jsp:include page="header.jsp"></jsp:include>
	</div>
	<hr width="100%">
	<jsp:include page="Menu.jsp"></jsp:include>
	
			<html:javascript formName="resultSchedule"/>
	
				<html:form action="/manageResult" onsubmit="validateResultSchedule(this)">	
				
				                             <input type="hidden" name="firstResultDate" id = "firstResultDate"/>
				                              <input type="hidden" name="lastResultDate" id="lastResultDate"/>
  				
				<font face="Arial" color="#000040"><STRONG><bean:message key="link.manageresult"/></STRONG></font>			
					<center> 
					<font color="red" size="2"> <html:errors property="resultTo"/>
					                            <html:errors property="fromDate"/>  <br/>
					                            <html:errors property="resultFrom"/> <br/>
					</font>
						<table> 
						
						  <tr><td><font face="Arial" color="#000040"><bean:message key="label.ConductDate"/> </font></td><td><font face="Arial" color="#000040"> <bean:message key="label.FromConductDate"/> </font><html:select indexed="fromDate" property="fromDate"   onchange="validateTimePeriod();"/></td><td><font face="Arial" color="#000040">  <bean:message key="label.ToConductDate"/> </font><html:select indexed="toDate" property="toDate"  onchange="validateTimePeriod();"/><html:errors property="toDate" /></td></tr>
 
							<tr> 
								<td> 
								<font face="Arial" color="#000040"><bean:message key="label.testname"/>	</font> 
								</td> 
								<td> 
									<select id="testName" name="testName" onchange="getResultDuration();"> 
                                         <option value="0"><bean:message key="msg.select"/> 
                                         </select>
								</td> 
							</tr> 
							<tr><td><font face="Arial" color="#000040"><bean:message key="label.resultFrom"/> </font></td><td><Input type="text" name="resultFrom" id="resultFrom"/>
							<font color="red" size="2">*</font></td></tr>
							   <tr> <td><font face="Arial" color="#000040"><bean:message key="label.resultTo"/> </font></td><td><Input type="text" name="resultTo" id="resultTo"/><font color="red" size="2">*</font></td></tr>
	
	<tr><td><html:submit onclick="return validateResultPeriod();"></html:submit></td><td><html:cancel></html:cancel></td></tr>
		</table></center>
		</html:form>	
	</body>
</html>

