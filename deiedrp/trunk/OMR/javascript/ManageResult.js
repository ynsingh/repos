var status=false;

 function validateResultPeriod()
{
	var lastDate = dwr.util.getValue("lastResultDate");
	var from = dwr.util.getValue("resultFrom");
    var to = dwr.util.getValue("resultTo");
    var temp=false;
    this.status = checkComboBoxValue();
    if(this.status==true){
    if(lastDate!="" && from!="" && to!=""){
    status=checkResultPeriod();
     
         ComboBoxOptions.checkTimePeriod(from, lastDate, function(data)
         {
        if(data == false){
           alert("Result can be displayed Maximum for one month after processing of the sheets. Date cannot be greater than "+ lastDate);
           status=false;
          }else{
                ComboBoxOptions.checkTimePeriod(to, lastDate, function(data)
                {
                  if(data == false){
                  alert("Result can be displayed Maximum for one month after processing of the sheets. Date cannot be greater than "+ lastDate);
                  status=false;
                  }
                }
                );
               }     
          }
          );
     
   temp= !(status);
    return status;
    }else{
    
    return false;
    }
    }else{
    return this.status;
    }
 
 }
 
 function checkResultPeriod(){
var from = dwr.util.getValue("resultFrom");
			var where=true;
            var to = dwr.util.getValue("resultTo");
            var testName = dwr.util.getValue("testName");
			if(from > to){
              alert('Invalid Result Display Period');
               dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
	     var selectTestName = dwr.util.byId("testName");
	     selectTestName.options[0] = new Option('--Select--', 0);
	    
              where=false;
            }
           
			return where;

}
 
 
//this finction validates the test conduct period selected 
	function validateTimePeriod(){
		//alert("inside time period");
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
		//alert("inside time from : " + from); 
				//alert("inside time to : " + to); 
		if(!(from=="--Select--" || to=="--Select--")){
	ComboBoxOptions.checkTimePeriod(from, to, function(data)
	{
	 //alert(data);
	 if(!data){
	 alert("invalid Time period");
	     dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
	     var selectTestName = dwr.util.byId("testName");
	     selectTestName.options[0] = new Option('--Select--', 0);
	     
	     
	}else{
	populateName();
	}
	}
	);
	}
	}	
			
	function getResultDuration(){
	
	var testname = dwr.util.getValue("testName");
	if(!(testname=="--Select--")){ 
	ResultDate.getResultDate(testname, function(data)
	{
	  dwr.util.setValue("lastResultDate", data[3]);
	  dwr.util.setValue("resultFrom", data[1]);
	  dwr.util.setValue("resultTo", data[2]);
	  dwr.util.setValue("firstResultDate", data[0]);
	}
	);
	}else{
	dwr.util.setValue("lastResultDate", "");
	  dwr.util.setValue("resultFrom", "");
	  dwr.util.setValue("resultTo", "");
	  dwr.util.setValue("firstResultDate", "");
	}
	}
	
	function populateName(){
	var from = dwr.util.getValue("fromDate");
		var to = dwr.util.getValue("toDate");
	//alert(from);
    //alert(to);
	
	ComboBoxOptions.populateNameListManageResult(from, to, function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("testName")[0]);
  	dwr.util.addOptions(document.getElementsByName("testName")[0],data);
  }
  );
	
	}

	
function populateDate(){
	
 ComboBoxOptions.selectDateManageResult(function(data)
  {
    dwr.util.removeAllOptions(document.getElementsByName("fromDate")[0]);
  	dwr.util.addOptions(document.getElementsByName("fromDate")[0],data);
  	
  	dwr.util.removeAllOptions(document.getElementsByName("toDate")[0]);
  	dwr.util.addOptions(document.getElementsByName("toDate")[0],data);
  		
  }
  );
 }
	
