/**
* This function is to validate the time period 
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
	 				ShowMessage.getMessage("msg.invalidTime",function(value){alert(value);});
					dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
	     			var selectTestName = dwr.util.byId("testName");
	     			selectTestName.options[0] = new Option('--Select--', 0);
	     
	 			}
	 			else{
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
* This function is to retrieve the names of the test for the selected from and to dates. 
*/
function populateName(){
		var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
		//alert(from);
		//alert(to);
	
		ComboBoxOptions.populateCorrectAnsName(from, to, function(data)
  		{
    		dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
  			dwr.util.addOptions(document.getElementsByName("testName")[0],data);
  		}
  		);
	
}
	
/**
* This function is to populate the fromDate and toDate in the combobox 
*/
function populateDate(){
	
	//alert("inside Test ");
	
	//alert(testDate);
		ComboBoxOptions.selectDateCorrectAns(function(data)
  		{
    		dwr.util.removeAllOptions(document.getElementsByName("fromDate")[0]);
  			dwr.util.addOptions(document.getElementsByName("fromDate")[0],data);
  	
  			dwr.util.removeAllOptions(document.getElementsByName("toDate")[0]);
  			dwr.util.addOptions(document.getElementsByName("toDate")[0],data);
  	
  	
  		}
  		);
  
}
	

/**
	* This function return boolean value true if the String end with the given extension.
	@return boolean value false if validation fails
	*/
function endsWith(str, suffix) {
	  	
    	return str.indexOf(suffix, str.length - suffix.length) !== -1;
    	
}

/**
	* This function is to check validation on the input fields.
	@return boolean value false if validation fails
	*/
function checkComboBoxValueGroup(){
			
			var from = dwr.util.getValue("fromDate");
			var filevalue=dwr.util.getValue("correctPath").value;
			
							
			var where=true;
            var to = dwr.util.getValue("toDate");
            var testName = dwr.util.getValue("testName");
            
			if(from== '0' || to== '0' || testName== '0' || from== '--Select--' || to=='--Select--' || testName=='--Select--'||!(filevalue.length>0))
			{
            	ShowMessage.getMessage("msg.RequiredFields",function(value){alert(value);});
            	where=false;
                
            }
            
	   		if(filevalue.length>0){
	   			if(!((endsWith(filevalue, ".bmp"))||(endsWith(filevalue, ".BMP"))))
	   			{
	   				ShowMessage.getMessage("msg.Format",function(value){alert(value);});
	   				where=false;
	   			}
	   			//alert("a"+where);	
	   		}
	   		
           
			return where;

}

	/**
	* This function is to add data dynamically in tables (on the UI) from the database
	@param tableId
	*/
function getDataAndSetInTable1(tableId) {
	deleteAll(tableId);
	var testName = dwr.util.getValue("testName");
	GroupOP.getExistingGroupList(testName, function(data) {
		if (data.length > 0) {
			var table = document.getElementById(tableId);
			for ( var i = 0; i < data.length - 1; i = i + 2) {
				var row = table.insertRow(1);
				
				var cell2 = row.insertCell(0);
				cell2.innerHTML = data[i];
				cell2.align = "center";

				var cell3 = row.insertCell(1);
				cell3.innerHTML = data[i + 1];
				cell3.align = "center";
			}
			document.getElementById(tableId).style.display = "block";

		} else {
			document.getElementById(tableId).style.display = "none";
		}
	});
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
