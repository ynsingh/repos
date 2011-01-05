function confirmMsg()
			{
			var where=false;
			var testName=new String((document.getElementById("testName")).value);
			var zippedFolder = String((document.getElementById("zippedFolder")).value);
			var result = zippedFolder.search(/(.ZIP)+/ig);
			//alert('result : '+result);
			
			
			where = checkComboBoxValue();
			//alert(where);
			  if(result== -1){
			where=false;
			alert("Please upload the response Sheets in a zipped folder!!");
			}else{
			 where=confirm('Are you sure you want to Upload the Response Sheets for the Test : '+testName);
			 }
				return where;	
}

function checkComboBoxValue(){

var from = (document.getElementById("fromDate")).value;
			var where=true;
            var to = (document.getElementById("toDate")).value;
           // alert('to :'+to);
            var testName = (document.getElementById("testName")).value;
			//alert('testName : ' + testName);
			if(from==0 || to==0 || testName==0){
              alert('Please select the required fields.');
              where=false;
            }
           
			return where;

}
