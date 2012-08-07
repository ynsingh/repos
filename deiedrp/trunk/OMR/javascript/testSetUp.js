	function validateTestName(){
	var testName=dwr.util.getValue("testName");
	//alert("testName: " + testName);
	var infoStatus=false;
	OMRValidation.validateTestNameJs(testName, function(data){
	dwr.util.setValue("existTestName", data);
	});
	
	}
	
	function isInteger(s){
	var i;
	for(i=0;i<s.length; i++){
	var c = s.charAt(i);
	if(((c<"0") || (c>"9"))) return false;
	}
	return true;
	}
	
	function verify()
			{
			validateTestName();
				var testname=dwr.util.getValue("testName");
			
			var i=1;
			var totalSec=dwr.util.getValue("totalSec");
			var totalQues=0;
			var totQues=0;
			var cellval=0;
			totQues=dwr.util.getValue("totalQues");			
			var flag=true;
			var a = dwr.util.getValue("existTestName");
			
  
			a=a+"";
if(totalSec > 0){
	for (r=1; r < (document.getElementById('secTable')).rows.length; r=r+1)
	 {
	 	for(c=1;c<4;c=c+1)
	 	{
   		cellval =(document.getElementById('secTable')).rows[r].cells[c].childNodes[0].value; //errors out with trow.cells being undefined
            if (cellval.length < 1) {
                alert("Please complete the entry for Section :"+r);
                flag=false;
                return flag;
            }
               if(c==1)
               		{
               		 if(cellval==""){
               		 alert("Please enter the number of questions in section "+r);
               		 return false;
               		 }
               		 
               		 if(!isInteger(cellval)){
               		 alert('Question number must be an integer in section ' +r);
               		 return false;
               		 }
               		 if(parseInt(cellval)<1){
               		 alert("Invalid question number in section "+r);
               		 return false;
               		 }
               		totalQues=parseInt(totalQues)+parseInt(cellval);
               		}
               if(c==2)
               	{
               	if(cellval==""){
               	alert("Please Enter the marks for section : " + r);
               	return false;
               	}
              
               	if(isNaN(cellval)){
               	alert('Marks should be numeric in section ' + r);
               	return false;
               	}
               	if(parseFloat(cellval)>10 || parseFloat(cellval)<0.25)
               		{
               		alert("Marks of each question cannot be greater than 10 and less than 0.25 for section : "+r);
               		return false;
               		}
               		
               	}
               	if(c==3)
               		{
               		if(cellval==""){
               	alert("Please Enter the Negative marks for section : " + r);
               	return false;
               	}
               	if(isNaN(cellval)){
               	alert('Negative Marks should be numeric in section ' + r);
               	return false;
               	}
               	if(parseFloat(cellval)>10 || parseFloat(cellval)<(-10))
               			{
               			alert("Negative marks can't be greater than 10 for Section No:"+r);
               			
               			return false;
               			}
               		}		
      	}//end col							
 	}//end row
 			if(totQues!=totalQues)
 				{
 				alert("Sum of no. of questions in each section should be equal to the total number of questions ");
 				return false;
 				}
 				
 	       a = dwr.util.getValue("existTestName");
            a = dwr.util.getValue("existTestName");
  
//	alert("a " +a);
			
 	if(a==testname){
		alert("Test Name already exist.");
	return false;
	}			
 }//end if total section
  else{
  alert('Please fill all the mandatory fields.');
  return false;
  }
  	
}//func end

function removeRow(lastElement, tbody){
  if (lastElement > 1){ 
    if (lastElement > 2){ 
    tbody.deleteRow(lastElement - 1);
  }else{
  alert('Please select the number of sections.');
  }
}
}	
	function addRow(index, tbody){
	
	// creates a <tr> element
            mycurrent_row = document.createElement("tr");
            sec_num = document.createElement("td");
            sec_numTxt = document.createTextNode(index);
            sec_num.appendChild(sec_numTxt);
            mycurrent_row.appendChild(sec_num);
            for(var i = 1; i < 4; i++) {
                // creates a <td> element
                mycurrent_cell = document.createElement("td");
                // creates a text node
	  mycurrent_txtbox = document.createElement("input");
	  mycurrent_txtbox.setAttribute('type',"text");
	   mycurrent_txtbox.setAttribute('name',"sectionDetail"+i);
	   mycurrent_txtbox.setAttribute('id',"sectionDetail"+i);
                //currenttext = document.createTextNode("cell is row "+j+", column "+i);
                // appends the text node we created into the cell <td>
                //mycurrent_cell.appendChild(currenttext);
	mycurrent_cell.appendChild(mycurrent_txtbox);
                // appends the cell <td> into the row <tr>
                mycurrent_row.appendChild(mycurrent_cell);
            }
            // appends the row <tr> into <tbody>
            tbody.appendChild(mycurrent_row);
	
	}	

function display() {
	     validateTestName();
		// get the reference for the body
        var mybody = document.getElementById("divId");
        var tableid = document.getElementById("secTableid");
        
         var setObj = document.getElementById("section"); 
         var val = setObj.options[setObj.selectedIndex].value; 
        if(tableid!= null)
        {
         var tbody = document.getElementById("secTable");          
        
        //get no. of rows in the table.
  		 var lastElement=secTable.rows.length;
  				 var index=lastElement-2;
  				 	if(val>index)
					{
					for(var i=lastElement;i<=val;i=i+1)
						{
						addRow(i, tbody);
						}
					}
				else
					{
					val= parseInt(val)+1;
					for(i=lastElement; i>val ; i=i-1)
					{
					removeRow(i, tbody);
					}
					}
         
        }
       else{
        // creates a <table> element and a <tbody> element
        mytable = document.createElement("table");
        mytable.setAttribute('id', "secTableid");
        mytablebody = document.createElement("tbody");
        mytablebody.setAttribute('id',"secTable");
        //create Header
        my_row = document.createElement("tr");
        my_col = document.createElement("td");
        my_col.setAttribute('align',"center");
        my_text = document.createTextNode("Section Number");
        my_col.appendChild(my_text);
        my_row.appendChild(my_col);
        my_col3 = document.createElement("td");
        my_text3 = document.createTextNode("No. of Questions");
        my_col3.appendChild(my_text3);
         my_col3.setAttribute('align',"center");
        my_row.appendChild(my_col3);
        my_col2 = document.createElement("td");
        my_text2 = document.createTextNode("Marks of each Question");
         my_col2.setAttribute('align',"center");
        my_col2.appendChild(my_text2);
        my_row.appendChild(my_col2);
        my_col4 = document.createElement("td");
        my_text4 = document.createTextNode("Negative Marks");
        my_col4.appendChild(my_text4);
         my_col4.setAttribute('align',"center");
        my_row.appendChild(my_col4);
        mytablebody.appendChild(my_row);
        // creating all cells
        for(var j = 1; j <= val; j++) {
       
            // creates a <tr> element
            mycurrent_row = document.createElement("tr");
            sec_num = document.createElement("td");
            sec_numTxt = document.createTextNode(j);
            sec_num.appendChild(sec_numTxt);
            mycurrent_row.appendChild(sec_num);
            for(var i = 1; i < 4; i++) {
                // creates a <td> element
                mycurrent_cell = document.createElement("td");
                // creates a text node
	  mycurrent_txtbox = document.createElement("input");
	  mycurrent_txtbox.setAttribute('type',"text");
	   mycurrent_txtbox.setAttribute('name',"sectionDetail"+i);
	   mycurrent_txtbox.setAttribute('id',"sectionDetail"+i);
                //currenttext = document.createTextNode("cell is row "+j+", column "+i);
                // appends the text node we created into the cell <td>
                //mycurrent_cell.appendChild(currenttext);
	mycurrent_cell.appendChild(mycurrent_txtbox);
                // appends the cell <td> into the row <tr>
                mycurrent_row.appendChild(mycurrent_cell);
            }
            // appends the row <tr> into <tbody>
            mytablebody.appendChild(mycurrent_row);
        }
        // appends <tbody> into <table>
        mytable.appendChild(mytablebody);
        
        
        // appends <table> into <body>
        
        mybody.appendChild(mytable);
        // sets the border attribute of mytable to 2;
      //  mytable.setAttribute("border", "2");
    }
}
function askGroup(){
	var setObj = document.getElementById('sheetFormat'); 
    var val = setObj.options[setObj.selectedIndex].value;
    
    var a=document.getElementById('groupExists');
    var tbody = document.getElementById("secTable");
    var sec = document.getElementById("section");
    if(val.toString()=="GRC"){
    	
    	a.disabled=false;
    	sec.selectedIndex=0;
    	for(var i = tbody.rows.length - 1; i > 0; i--)
		{
    		tbody.deleteRow(i);
		}
    }
    else {
    	a.selectedIndex=0;
    	sec.selectedIndex=0;
    	
    	a.disabled=true;
    	
    	var setObj1 = document.getElementById("section");
    	for (i = setObj1.length - 1; i>=0; i--) {
    		
      			setObj1.remove(i);
    		
  		}
    	option0=document.createElement("option");
        option0.value="0";
        option0.innerHTML="---Select---";
        	
		setObj1.appendChild(option0);
		setObj1.options[1] = new Option('1', 1);
        setObj1.options[2] = new Option('2', 2);
        setObj1.options[3] = new Option('3', 3);
        setObj1.options[4] = new Option('4', 4);
    
    }
}

function addGrp(){
	var setObj = document.getElementById('groupExists'); 
    var val = setObj.options[setObj.selectedIndex].value;
	
		var setObj1 = document.getElementById("section");
		for (i = setObj1.length - 1; i>=0; i--) {
    		
      			setObj1.remove(i);
    		
  		}
		option0=document.createElement("option");
        option0.value="0";
        option0.innerHTML="---Select---";
        	
		setObj1.appendChild(option0);
        
        switch(val)
		{ 
		case 'N':
		
		setObj1.options[1] =new Option('1',1);
		
		
		break;
		case 'Y':
		
        setObj1.options[1] = new Option('1', 1);
        setObj1.options[2] = new Option('2', 2);
        setObj1.options[3] = new Option('3', 3);
        setObj1.options[4] = new Option('4', 4);
		break;
		
		}
                  
	
}
