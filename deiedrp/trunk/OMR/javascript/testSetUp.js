	function verify()
			{
			var i=1;
			var totalSec=document.getElementById("totalSec");
			var totalQues=0;
			var totQues=0;
			
			totQues=(document.getElementById("totalQues")).value;			
			
			
	for (r=1; r < (document.getElementById('secTable')).rows.length; r=r+1)
	 {
	 	for(c=1;c<4;c=c+1)
	 	{
   		cellval =(document.getElementById('secTable')).rows[r].cells[c].childNodes[0].value; //errors out with trow.cells being undefined
       	  
            if (cellval.length < 1) {
                alert("Please complete the entry for Section :"+r);
                return false;
                 					}
               if(c==1)
               		{
               		totalQues=parseInt(totalQues)+parseInt(cellval);
               		}
               if(c==2)
               	{
               	if(parseFloat(cellval)>10 || parseFloat(cellval)<1)
               		{
               		alert("Marks of each question cannot be greater than 10 and less than 1 for section : "+r);
               		return false;
               		}
               		
               	}
               	if(c==3)
               		{
               	if(parseFloat(cellval)>10 || parseFloat(cellval)<(-10))
               			{
               			alert("Negative marks can't be greater than 10 for Section No:"+r);
               			
               			return false;
               			}
               		}		
      	}							
 	}
 			if(totQues!=totalQues)
 				{
 				alert("Sum of no. of questions in each section should be equal to the total number of questions ");
 				return false;
 				}
 			
}

		function remove(mytableid){
		var mybody = document.getElementById("divId");
		mybody.removeChild(mytableid);
		}

function display() {

           
		// get the reference for the body
        var mybody = document.getElementById("divId");
        var tableid = document.getElementById("secTableid");
        if(tableid!=null)
        {
         remove(tableid);
        }
       
         var setObj = document.getElementById("section"); 
         var val = setObj.options[setObj.selectedIndex].value; 
        
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
