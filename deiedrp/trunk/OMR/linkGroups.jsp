<!--
* @(#) linkGroups.jsp
* @Author : Dheeraj Singh
* @Date : 17 Aug, 2011
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

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'linkGroups.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<script src='dwr/util.js'></script>
		<script src='dwr/engine.js'></script>
		<script src='dwr/interface/GroupSection.js'></script>
		<script src='dwr/interface/ShowMessage.js'></script>
		<script src='dwr/interface/ComboBoxOptions.js'></script>
		<script type="text/javascript">

/**
*  This method retrieves the section-wise groups and populates in respective table
*  @param tableId (id of table)
*/
function showSectionGroup(tableId) {
	deleteAll(tableId);
	var testName = dwr.util.getValue("testName");
	var sectionNo = dwr.util.getValue("totalSection");
	document.getElementById("cc").checked = false;
	GroupSection.getGroups(testName,sectionNo,function(data) {

						if (data == "Select") {
							ShowMessage.getMessage("msg.selectAlert", function(value) {alert(value);});
						}
						if (data.length == 0) {
							document.getElementById("addbutton").disabled = true;
							populateSec();
						}else{
							document.getElementById("addbutton").disabled = false;
						}
						
						var table = document.getElementById(tableId);
						
						for ( var i = 0; i < data.length - 1; i = i + 2) {
							var row = table.insertRow(1);
							var cell1 = row.insertCell(0);
							var element1 = document.createElement("input");
							element1.type = "checkbox";
							cell1.appendChild(element1);
							cell1.align = "center";

							var cell2 = row.insertCell(1);
							cell2.innerHTML = data[i];
							cell2.align = "center";

							var cell3 = row.insertCell(2);
							cell3.innerHTML = data[i + 1];
							cell3.align = "center";
						}
					});
	document.getElementById(tableId).style.display = "block";
}


/**
*  This method is used to reset the values 
*/
function reset() {

	document.getElementById("addbutton").disabled = true;
	//document.forms[0].elements[3].value = "";
	//document.forms[0].elements[4].value = "";
}

/**
*  This method delete the all group codes selected by user 
*  @param tableId (id of table)
*/
function deleteAll(tableId) {
	var table = document.getElementById(tableId);
	var rowCount = table.rows.length;

	while (table.rows.length > 1) {
		for ( var i = 1; i < table.rows.length; i++) {
			table.deleteRow(i);
		}
	}
}

/**
*  This method select or deselect all checkboxes on selecting the main one 
*  @param tableId (id of table)
*  @param cc (id of checkbox)
*/
function selectDeselectAll(tableId, cc) {
	try {
		var table = document.getElementById(tableId);
		var bool = document.getElementById(cc).checked;

		var rowCount = table.rows.length;
		for ( var i = 1; i < rowCount; i++) {
			table.rows[i].cells[0].childNodes[0].checked = bool;
		}
	} catch (e) {
		alert(e);
	}
}

/**
*  This method retrieves already added group codes and populates in respective table 
*  @param tableId (id of table)
*/
function showAddedGroups(tableid) {

	deleteAll(tableid);
	var testName = dwr.util.getValue("testName");
	var sectionNo = dwr.util.getValue("totalSection");
	document.getElementById("cd").checked = false;
	GroupSection.getAddedGroups(testName, sectionNo, function(data) {

		if (data.length == 0) {
		document.getElementById("delbutton").disabled = true;
		
		}else{
		document.getElementById("delbutton").disabled = false;
		}

		var table = document.getElementById(tableid);
		
		for ( var i = 0; i < data.length - 2; i = i + 3) {
			var row = table.insertRow(1);
			var cell1 = row.insertCell(0);
			var element1 = document.createElement("input");
			element1.type = "checkbox";
			cell1.appendChild(element1);
			cell1.align = "center";

			var cell2 = row.insertCell(1);
			cell2.innerHTML = data[i];
			cell2.align = "center";

			var cell3 = row.insertCell(2);
			cell3.innerHTML = data[i + 1];
			cell3.align = "center";

			var cell4 = row.insertCell(3);
			cell4.innerHTML = data[i + 2];
			cell4.align = "center";

		}
	});
	document.getElementById(tableid).style.display = "block";
}

/**
*  This method deletes the group code selected by user 
*  @param tableId (id of table)
*/
function deleteGroup(tableid) {
	
	try {

		var table = document.getElementById(tableid);

		var rowCount = table.rows.length;

		var selectedCodes = '';
		var selectedSection = '';

		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (chkbox.checked == true) {
				selectedSection = selectedSection + row.cells[1].innerHTML
						+ '|';
				selectedCodes = selectedCodes + row.cells[2].innerHTML + '|';
			}
		}
		if (selectedCodes == '') {
			populateSec();
			ShowMessage.getMessage("msg.noGrpCodes", function(value) {alert(value);});
		} else {

			var testName = dwr.util.getValue("testName");
			ShowMessage.getMessage("msg.confirm",function(value) {
			var i = confirm(value);
			if (i == true) {
				GroupSection.deleteGroup(testName, selectedSection,
						selectedCodes, function(count) {
							populateSec();
							showAddedGroups(tableid);
							ShowMessage.getMessage("msg.delSuccess",function(value) {alert(count + " " + "" + value);});
						});
				}
			});
		}
	} catch (e) {
		alert(e);
	}
}

/**
*  This method add the group codes for particular section 
*  @param tableId (id of table)
*/
function addGroup(tableid) {

	try {
		var table = document.getElementById(tableid);
		var rowCount = table.rows.length;

		var selectedCodes = '';
		var selectedGroups = '';

		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (chkbox.checked == true) {
				selectedCodes = selectedCodes + row.cells[1].innerHTML + '|';
				selectedGroups = selectedGroups + row.cells[2].innerHTML + '|';
			}
		}
		if (selectedCodes == '') {
			ShowMessage.getMessage("msg.noGrpCodes", function(value) {alert(value);});
		} else {
			var testName = dwr.util.getValue("testName");
			var sectionNo = dwr.util.getValue("totalSection");
			ShowMessage.getMessage("msg.confirm", function(value) {
			var j = confirm(value);
			if(j==true){
			GroupSection.addGroup(testName, sectionNo, selectedCodes,
					selectedGroups, function(count) {
					showSectionGroup(tableid);
					showAddedGroups('showTable');
					ShowMessage.getMessage("msg.insertSuccess", function(value) {alert(count + " " + "" + value);});
					});
				}
			});
			
		}
	} catch (e) {
		alert(e);
	}
}

/**
*  This method populate the sections of concerned test in combobox 
*/
function populateSec() {
	var testName = dwr.util.getValue("testName");

	ComboBoxOptions.populateSection(testName,
			function(data) {
				dwr.util.removeAllOptions(document
						.getElementsByName("totalSection")[0]);
				dwr.util.addOptions(
						document.getElementsByName("totalSection")[0], data);
			});
}
</script>

	</head>

	<body onload="showAddedGroups('showTable'),populateSec(),reset()">
		<table width="100%">
			<tr>
				<td><jsp:include page="header.jsp"></jsp:include></td>
			</tr>
			<tr>
				<td><hr width="100%"></td>
			</tr>
			<tr>
				<td><jsp:include page="Menu.jsp"></jsp:include></td>
			</tr>
		</table>

		<%!HttpSession hs = null;%>
		<%try {hs = request.getSession();} catch (Exception e) {}%>
		<%int totalSection = (Integer) hs.getAttribute("totalSection");%>

		<font size="4" face="Arial" color="#000040"> <b><bean:message
					key="label.testname" />: </b><I> <%=(String) hs.getAttribute("testName")%></I>
		</font>

		<html:form action="/cancel">
			<html:hidden property="testName" value='<%=(String)hs.getAttribute("testName")%>' />
			<br/>
			<table>
				<tr>
					<td>
						<font size="4" face="Arial" color="#000040"> <bean:message key="label.groupsection" /> </font>
					</td>
					<td>
						<select id="totalSection" name="totalSection" style="width: 100px" onchange="showSectionGroup('dataTable')">
							
						</select>
					</td>
				</tr>
			</table>
			<br/>
			<table>
				<tr>
					<td>
						<html:cancel></html:cancel>
					</td>
				</tr>
			</table>
			<br />
			<br/>
			
<table width="78%" align="left">
	<tr>
		<td align="left" >
			<font size="2" face="Arial" color="#000040"><B><i><bean:message key="label.toAdd" /></i></B></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
    			
    		<input type="button" value=' Add ' id="addbutton" onclick="addGroup('dataTable')"/>
		
			<table id="dataTable" width="60%" align="left" border="1">
				<TR>
					<Th width="3%">
						<input type="checkbox" id="cc" onclick="selectDeselectAll('dataTable','cc')" />
					</Th>
					<Th width="22%">
						Group Code
					</Th>
					<Th width="35%">
						Group Name
					</Th>
				</TR>
			</table>
		</td>
		
		<td align="right">
			<font size="2" face="Arial" color="#000040"><b><i><bean:message key="label.alreadyAdd" /></i></b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="delbutton" value='Delete' onclick="deleteGroup('showTable'),deleteAll('dataTable')" />
		
			<table id="showTable" width="82%" align="right" border="1">
				<TR>
					<Th width="3%">
						<input type="checkbox" id="cd" onclick="selectDeselectAll('showTable','cd')" />
					</Th>
					<Th width="22%">
						Section No.
					</Th>
					<Th width="22%">
						Group Code
					</Th>
					<Th width="35%">
						Group Name
					</Th>
				</TR>
			</table>
		</td>
	</tr>
</table>
		</html:form>
	</body>
</html>
