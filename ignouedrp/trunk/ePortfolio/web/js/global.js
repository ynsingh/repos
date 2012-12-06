// JavaScript Document
function populateYearSelect() 
  {
    d = new Date();  
    curr_year = d.getFullYear();  
	totalyearfield=document.getElementsByClassName('year').length;
	for(x=0;x<totalyearfield;x++){
    	for(i = 0; i < 20; i++)
        	document.getElementsByClassName('year')[x].options[i] = new Option(curr_year-i,curr_year-i); 
	}
   } 
  function populateMonthSelect() 
  {
    d = new Date();
    var monthslist=new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	curr_month= d.getMonth();
	//alert(monthslist[curr_month]);
	totalmonthsfield=document.getElementsByClassName('month').length;
	for(x=0;x<totalmonthsfield;x++){
    	for(i = 0; i < 12; i++)
        	document.getElementsByClassName('month')[x].options[i] = new Option(monthslist[i], i+1);
	}
   } 
	function col3switch(){
                if(document.getElementById("col3").style.display=="block")
                    document.getElementById("col3").style.display="none";
                else
                    document.getElementById("col3").style.display="block";
    }   
	function fillmonthyear(){
   	populateYearSelect();
	populateMonthSelect();
   }
   function addmorefield(){				
		var newFields = document.getElementById('tablerootvalue').cloneNode(true);
		newFields.id = '';
		newFields.style.display = 'block';
		
		var insertHere = document.getElementById('qualificationtable');
		insertHere.parentNode.insertBefore(newFields,insertHere);
		
	}
	function setallonload(){
		populateYearSelect();
		addmorefield();
	}
