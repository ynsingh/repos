function confirmMsg()
			{
			var where = false;
			var testName= new String(dwr.util.getValue("testName"));
			
			where = checkComboBoxValue();
			if(where == true){
			 where=confirm('Are you sure you want to Upload the Response Sheets for the Test : '+testName +'?');
			 }
				return where;	
}

function checkComboBoxValue(){
var from = dwr.util.getValue("fromDate");
			var where=true;
            var to = dwr.util.getValue("toDate");
            var testName = dwr.util.getValue("testName");
			if(from== '0' || to== '0' || testName== '0' || from== '--Select--' || to=='--Select--' || testName=='--Select--'){
              alert('Please select the required fields.');
              where=false;
            }
			return where;

}
