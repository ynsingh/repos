<!--
* @(#) correctSheetGroup.jsp
* @Author :UPASANA KULSHRESTHA 
* @Date :9-12-2011
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
* Redistribution in binary form must reproduce the above copyright
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
		<script src='dwr/interface/GroupOP.js'></script>
		<script src='dwr/interface/ShowMessage.js'></script>
		<script type="text/javascript" src="javascript/validateCorrectAnswer.js"></script>
		<script>
		function changeBody()
		{
			var blurDiv = document.createElement("div");
			blurDiv.id = "blurDiv";
			blurDiv.style.cssText = "position:absolute; top:0; right:0; width:" + screen.width + "px; height:" + screen.height + "px; background-color:#3B6AA0; opacity:0.4; filter:alpha(opacity=40);";
			document.getElementsByTagName("body")[0].appendChild(blurDiv);	
		}
		</script>
		
	</head>
	<body onload="populateDate();">
		<table width="100%">
  			<tr><td>  <jsp:include page="header.jsp"></jsp:include></td></tr>
  			<tr><td>	<hr width="100%"> </td></tr>
 			<tr><td> <jsp:include page="Menu.jsp"></jsp:include></td></tr>
		</table>	
		<%
		HttpSession hs = request.getSession();
		%>
		
		<html:form action="/selectCorrectGroup" method="post" enctype="multipart/form-data" onsubmit="changeBody();">
		 								  	
				
			<blockquote><font face="Arial" color="#000040"> 
				<strong><bean:message key="msg.correct"/><br/>
				</strong></font>
			</blockquote>
				
			<center> 
					<font color="red" size="2">
					<%
					try{
						System.out.println("Exception " + hs.getAttribute("correctGroupSheetMsg"));
					if(hs.getAttribute("correctGroupSheetMsg")==null){
						hs.setAttribute("correctGroupSheetMsg","NA");
					}	
						
					if(!((String)hs.getAttribute("correctGroupSheetMsg")).equals("NA")){
					 %>	
					 <%=(String) hs.getAttribute("correctGroupSheetMsg") %>	
					 <%}
						hs.setAttribute("correctGroupSheetMsg","NA");
					 }catch(Exception e){
					 System.out.println("Exception in corr Group : " + e);
					 }%>
				    <html:errors property="correctPath" /> 
					</font>
			<table align="center" > 
				<tr>
					<td>
						<font face="Arial" color="#000040"><bean:message
								key="label.testDate" /> </font>
					</td>
					<td>
						<font face="Arial" color="#000040"> <bean:message
								key="label.from" /> 
						</font> 
						<font
								face="Arial" color="#000040"><strong>:</strong>
						</font>
						<font color="red" size="2"><bean:message
								key="required.symbol" />
						</font>
					</td>
					<td>
						<html:select indexed="fromDate" property="fromDate" style="width:150px"
							onchange="validateTimePeriod();">
							<html:option value="0">
								<bean:message key="msg.select" />
							</html:option>
						</html:select>
						
						
					</td>
					<td >
						<font face="Arial" color="#000040"><bean:message
								key="label.to" /> </font> 
						<font
								face="Arial" color="#000040"><strong>:</strong>
						</font>
						<font color="red" size="2"><bean:message
								key="required.symbol" />
						</font>
					</td>
					<td>
						<html:select indexed="toDate" property="toDate" style= "width:150px"
							onchange="validateTimePeriod();">
							<html:option value="0">
								<bean:message key="msg.select" />
							</html:option>
						</html:select>
						
						
					</td>
				</tr>
				<tr>
					<td colspan="2" >
						<font face="Arial" color="#000040"> <bean:message
								key="label.testname" />
						</font>
						<font color="red" size="2"><bean:message
								key="required.symbol" />
						</font>
					
					</td>
					<td colspan="3">
						<select id="testName" name="testName" style="width: 150px" onChange="getDataAndSetInTable1('dataTable');">
							<option value="0">
								<bean:message key="msg.select" />
							</option>
						</select>
						
						
					</td>
					
				</tr>
			
				<tr> 
					<td colspan="2"> 
						<font face="Arial" color="#000040"><bean:message key="label.corrrectSheet" /></font>
						<font color="red" size="2"><bean:message
								key="required.symbol" />
						</font> 
					</td> 
						
					<td colspan="3"> 
						<html:file property="correctPath"></html:file>
						
					</td> 
									
				</tr> 
 
			</table> 
			<br/>
 			
			<html:submit onclick="return checkComboBoxValueGroup();"></html:submit> &nbsp; <html:cancel value="Cancel"></html:cancel>
			</center> 
			<br></br>

			<table id="dataTable" align="center" style="display: none"
				width="40%" bordercolor="Black" border="1" >
				
				<TR height="0%">
					<Th width="10%"><font face="Arial" color="#000040">
						<bean:message key="label.GroupCodeExisting" /></font>
					</Th>
					<Th width="30%"><font face="Arial" color="#000040">
						<bean:message key="label.GroupName" /></font>
					</Th>
				</TR>


			</table>
			<br></br>
			
		</html:form> 
	</body>
</html>

				

				
