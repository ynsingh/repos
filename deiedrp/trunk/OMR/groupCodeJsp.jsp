<!--
* @(#) groupCodeJsp.jsp
* @Author :UPASANA KULSHRESTHA 
* @Date :21-08-2011
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
		<script src='dwr/interface/GroupOP.js'></script>
		<script src='dwr/interface/ShowMessage.js'></script>
		<script type="text/javascript" src="javascript/validatecomboBox.js"></script>
		<script type="text/javascript" src="javascript/validateGroup.js"></script>


	</head>
	<body onload="populateDate();">
	<input type="hidden" name="existGroupCode" id="existGroupCode" />
		<table width="100%">
			<tr>
				<td>
					<jsp:include page="header.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td>
					<hr width="100%">
				</td>
			</tr>
			<tr>
				<td>
					<jsp:include page="Menu.jsp"></jsp:include></td>
			</tr>
		</table>

		<font face="Arial" color="#000040">
				<STRONG><bean:message key="link.groupcodesetup" /></STRONG>
		</font>
		
		<html:form action="/groupCode">
			<table align="center">


				<tr>
					<td>
						<font face="Arial" color="#000040">
								<bean:message key="label.testDate" />
						</font>
					</td>
					<td>
						<font face="Arial" color="#000040">
								<bean:message key="label.from" />
						</font> &nbsp;&nbsp;
						<font face="Arial" color="#000040"><strong>:</strong> </font>
					</td>
					<td>
						<html:select indexed="fromDate" property="fromDate"
							style="width:150px" onchange="validateTimePeriod();">
							<html:option value="0">
								<bean:message key="msg.select" />
							</html:option>
						</html:select>
						<font color="red" size="2"><bean:message
								key="required.symbol" /> </font>
						<html:errors property="fromDate" />
					</td>

					<td>
						<font face="Arial" color="#000040"><bean:message
								key="label.to" /> </font> &nbsp;&nbsp;
						<font face="Arial" color="#000040"><strong>:</strong> </font>
					</td>
					<td>
						<html:select indexed="toDate" property="toDate"
							style="width:150px" onchange="validateTimePeriod();">
							<html:option value="0">
								<bean:message key="msg.select" />
							</html:option>
						</html:select>
						<font color="red" size="2"><bean:message
								key="required.symbol" /> </font>
						<html:errors property="toDate" />
					</td>
				</tr>
				<tr>
					<td>
						<font face="Arial" color="#000040">
								<bean:message key="label.testname" />
						</font>
								
					</td>
					<td>
						<font color="#ffffff">
								<bean:message key="label.from" />&nbsp;&nbsp;&nbsp; 
						</font>
						<font face="Arial" color="#000040"><strong>:</strong></font>
					</td>
					<td>
						<select id="testName" name="testName" style="width: 150px" onchange="getDataAndSetInTable('dataTable','deleteButtonTable');">
							<option value="0">
								<bean:message key="msg.select" />
							</option>
						</select>
						<font color="red" size="2">
								<bean:message key="required.symbol" />
						</font>
						<html:errors property="testName" />
					</td>
				</tr>
				

			</table>
			<br></br>
			<table align="center">
				<tr>
					<TD align="center">
						<font face="Arial" color="#000040"> 
								<bean:message key="label.GroupCode" /><b>:</b>
						</font>
					</td>
					<td>
						<html:text property="groupCodeId" style="width:130px" onchange="validateGroupCode();" />
						&nbsp;&nbsp;&nbsp;
					</td>
					<TD align="center">
						<font face="Arial" color="#000040"> <bean:message
								key="label.GroupName" /><b>:</b> </font>

					</td>
					<td>
						<html:text property="groupNameId" style="width:130px" />
					</td>
					<td>
						<html:button property="addbutton" value='Add'
							onclick="validateAddGroup('dataTable','deleteButtonTable')" />
					</td>

				</tr>

			</table>

			
			<br></br>

			<table id="dataTable" align="center" style="display: none"
				width="40%" bordercolor="Black" border="1" >
				
				<TR height="0%">
				
					<Th width="2.5%">
						<input type="checkbox" id="selectcheckbox"
							onclick="selectDeselectAll('dataTable','selectcheckbox')" />
					</Th>
					<Th width="10%"><font face="Arial" color="#000040">
						<bean:message key="label.GroupCode" /></font>
					</Th>
					<Th width="27.5%"><font face="Arial" color="#000040">
						<bean:message key="label.GroupName" /></font>
					</Th>
				</TR>


			</table>
			<br></br>
			
			<table width="20%" align="center" id="deleteButtonTable"
				style="display: none">
				<tr>
					
					<td align="center"> 
						<input type="Button" id="deleteButton" name="deleteButton"
							value='Delete'
							onclick="deleteGroup('dataTable','deleteButtonTable')" />
						<html:cancel/>
					</td>
					
				</tr>
			</table>
			
		</html:form>
	</body>
</html>