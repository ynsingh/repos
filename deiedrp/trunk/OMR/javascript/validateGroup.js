
/**
* This function is to validate the time period 
*/
function validateTimePeriod(){
		var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
		 
		if(!(from=="--Select--" || to=="--Select--" || from=="0" || to=="0")){
			
			ComboBoxOptions.checkTimePeriod(from, to, function(data)
			{
		 		if(!data){
			 		ShowMessage.getMessage("msg.invalidTime",function(value){alert(value);});
			 		
			     	dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
			     	var selectTestName = dwr.util.byId("testName");
			     	selectTestName.options[0] = new Option('--Select--', 0);
			     	populateDate();
			     	reset();
			     
				}
				else{
					populateName();
					reset();
					}
			}
			);
		}
		else{
			dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
	    	var selectTestName = dwr.util.byId("testName");
	    	selectTestName.options[0] = new Option('--Select--', 0);
	    	reset();
			}
}


/**
* This function is to retrieve the names of the test for the selected from and to dates. 
*/
function populateName() {
		var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
	
		ComboBoxOptions.populateNameGroupSetUp(from, to, function(data) {
				dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
				dwr.util.addOptions(document.getElementsByName("testName")[0], data);
		}
		);

}

/**
* This function is to populate the fromDate and toDate in the combobox 
*/
function populateDate() {

		ComboBoxOptions.selectDateGroupSetup(function(data) {
		dwr.util.removeAllOptions(document.getElementsByName("fromDate")[0]);
		dwr.util.addOptions(document.getElementsByName("fromDate")[0], data);

		dwr.util.removeAllOptions(document.getElementsByName("toDate")[0]);
		dwr.util.addOptions(document.getElementsByName("toDate")[0], data);

	});

}


	/**
	* This function is to insert the group code and group name input by the user in the database.
	@param tableId
	@param deleteButton
	*/
function addGroup(tableId, deleteButton) {
	var groupCode = dwr.util.getValue("groupCodeId");
	groupCode=groupCode.split(' ').join('');
	var groupName = dwr.util.getValue("groupNameId");
	var testName = dwr.util.getValue("testName");
	
	ShowMessage.getMessage("msg.Confirm",function(value){
		
		var i=confirm(value);
	
		if(i==true){
			GroupOP.insertGroup(testName, groupCode, groupName, function(bool) {
				if (bool == true) {
					getDataAndSetInTable(tableId, deleteButton);
					
					ShowMessage.getMessage("msg.success",function(value){alert(value);});
		
				} 
				});
				}
	});
		
}

	/**
	* This function is to check input values for groupCode is integer or not
	@param inputvalue, the input value given by user
	@return boolean value
	*/
function isInteger(inputValue){
	var i;
	for(i=0;i<inputValue.length; i++){
	var c = inputValue.charAt(i);
	if(((c<"0") || (c>"9"))) return false;
	}
	return true;
}

	/**
	* This function is to check validation on the input fields.
	@param tableId
	@param deleteButton
	@return boolean value false if validation fails
	*/
function validateAddGroup(tableId, deleteButton){
	
	validateGroupCode();
	var groupCode = dwr.util.getValue("groupCodeId");
	groupCode=groupCode.split(' ').join('');
	var groupName = dwr.util.getValue("groupNameId");
	var testName = dwr.util.getValue("testName");
	var existedgroup = dwr.util.getValue("existGroupCode");
	var fromDate = dwr.util.getValue("fromDate");
	var toDate = dwr.util.getValue("toDate");
	
	if(fromDate =="--Select--"){
					ShowMessage.getMessage("msg.selectTestDate",function(value){alert(value);});
					return false;
					}
	if(toDate =="--Select--"){
					ShowMessage.getMessage("msg.selectTestDate",function(value){alert(value);});
					return false;
					}
	
	if(testName=="--Select--"){
					ShowMessage.getMessage("msg.selectTestName",function(value){alert(value);});
					return false;
					}
	if((groupCode.length<=0) && (groupName.length<=0) ){
					ShowMessage.getMessage("msg.selectCodeName",function(value){alert(value);});
				 	return false;
					}
	if((groupName.length<=0)&& (groupCode.length>0)){
					ShowMessage.getMessage("msg.EnterGroupName",function(value){alert(value);});
					return false;
					}
	if((groupName.length>0)&& (groupCode.length<=0)){
					ShowMessage.getMessage("msg.EnterGroupCode",function(value){alert(value);});
					return false;
					}
	if((groupCode.length>0)||(groupName.length>0)){
	
				if(isNaN(groupCode)){
					ShowMessage.getMessage("msg.ValidateGroupCode01",function(value){alert(value);});
               		 return false;
               		 }
		            		 
				else if(!(isInteger(groupCode))){
					ShowMessage.getMessage("msg.ValidateGroupCode02",function(value){alert(value);});
					 return false;
               		 }
        		else if(groupCode.length!=2){
        			ShowMessage.getMessage("msg.ValidateGroupCode03",function(value){alert(value);});
					return false;
               		 
        			}
				else if((existedgroup==groupCode)&& existedgroup!=""){
					ShowMessage.getMessage("msg.ValidateGroupCode04",function(value){alert(value);});
					return false;
					}
				else if(groupName.length>45){
					ShowMessage.getMessage("msg.ValidateGroupCode05",function(value){alert(value);});
					return false;
               		}
				/*else if(groupCode==00){
					ShowMessage.getMessage("msg.ValidateGroupCode06",function(value){alert(value);});
					return false;
					}*/
				else if (groupName.split(' ').join('').length==0){
					ShowMessage.getMessage("msg.ValidateGroupCode07",function(value){alert(value);});
					return false;
               		}
				else addGroup(tableId, deleteButton);
		}
		
}


	/**
	* This function is to add data dynamically in tables (on the UI) from the database
	@param tableId
	@param deleteButton
	*/
function getDataAndSetInTable(tableId, deleteButton) {
	deleteAll(tableId);
	var testName = dwr.util.getValue("testName");
	document.getElementById("selectcheckbox").checked = false;
	GroupOP.getGroupList(testName, function(data) {
		if (data.length > 0) {
		
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
			document.getElementById(tableId).style.display = "block";
			document.getElementById(deleteButton).style.display = "block";

		} else {
			document.getElementById(tableId).style.display = "none";
			document.getElementById(deleteButton).style.display = "none";

		}
	}

	);
	
	reset();
}

	/**
	* This function is to reset the values.
	*/
function reset() {
	
	document.getElementById("dataTable").style.display = "none";
	document.getElementById("deleteButtonTable").style.display = "none";
	document.forms[0].elements[3].value = "";
	document.forms[0].elements[4].value = "";
	
}

	/**
	* This function is to remove all the row of the table
	@param tableId 
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
	* This function is to select or deselect the checkboxes of the table.
	@param tableId
	@param selectedcheckbox
	*/
function selectDeselectAll(tableID, selectcheckbox) {
	try {
		var table = document.getElementById(tableID);
		var bool = document.getElementById(selectcheckbox).checked;

		var rowCount = table.rows.length;
		for ( var i = 1; i < rowCount; i++) {
			table.rows[i].cells[0].childNodes[0].checked = bool;
		}
	} catch (e) {
		alert(e);
	}
}


/**
	* This function is to delete the selected rows from the database.
	@param tableId
	@param deleteButton
	*/
function deleteGroup(tableId, deleteButton) {

	try {
		
		var table = document.getElementById(tableId);
		var rowCount = table.rows.length;
		var selectedCodes = '';
		
		for ( var i = 1; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (chkbox.checked == true) {
				selectedCodes = selectedCodes + row.cells[1].innerHTML + '|';
			}
		}
		if (selectedCodes == '') {
			ShowMessage.getMessage("msg.NoGroupSelected",function(value){alert(value);});

		} else {
				
			ShowMessage.getMessage("msg.Confirm",function(value){
		
				var i=confirm(value);
				if(i==true){
					var testName = dwr.util.getValue("testName");
					GroupOP.deleteGroup(testName, selectedCodes, function(count) {
					
						
					getDataAndSetInTable(tableId, deleteButton);
						if(count!=0) {
								
								ShowMessage.getMessage("msg.RecordsDeleted",function(value){alert(count+" "+value);});
					
						}
						else{
							
							ShowMessage.getMessage("msg.GroupInUse",function(value){alert(value);});
														
						}
											
					
					});
				}
			});

		}
	} catch (e) {
		alert(e);
	}
}

/**
	* This function to check the group code input by the user exists in the database
	*/
function validateGroupCode(){
	var groupCode=dwr.util.getValue("groupCodeId");
	var testName = dwr.util.getValue("testName");
	var infoStatus=false;
	GroupOP.validateGroupCodeJs(groupCode,testName, function(data){
	dwr.util.setValue("existGroupCode", data);
	});
	
	}

