
		function verifyQuestion()
			{
			var totalQuestion=0;
			var toq=0;
			var noOfQues=document.getElementById("totalQuestion");
			var totalSection=document.getElementById("totalSection").value;
			var markEach;
			var markNeg;
				for(var i=1;i<=totalSection;i++)
				{
				toq=document.getElementById("noq"+i).value;
				markEach=document.getElementById("meq"+i).value;
				markNeg=document.getElementById("nm"+i).value;
						if(markEach>10 || markEach<1)
						{
						alert("Marks of each question cannot be greater than 10 and less than 1 \n for section : "+i);
						return false;
						}
					
					   if(parseFloat(markNeg)>10 && parseFloat(markEach)<(-10))
							{
							alert("Negative marks can't be greater than 10 for \n Section No:"+i);
							return false;
							}
			
				totalQuestion=parseInt(totalQuestion)+parseInt(toq);
				}
			if(parseInt(noOfQues.value)!=parseInt(totalQuestion))
				{
				alert("Sum of no. of questions in each section should be equal to the total number of questions!");
				return false;
				}
		 	}
	function change(update)
			{	
				var val=document.getElementById("totalSection").value;
				 var secTable = document.getElementById('sectionUpdate');
  				 var lastElement=secTable.rows.length;
  				 var index=lastElement-2;
				if(val>index)
					{
					for(var i=1;i<=(val-index);i++)
						{
						addRow();
						}
					}
				else
					{
					for(i=1;i<=(index-val);i++)
					{
					removeRow();
					}
					}
			}
function addRow()
{
  var secTable = document.getElementById('sectionUpdate');
  var lastElement=secTable.rows.length;
  var index=lastElement-1;
  var row=secTable.insertRow(lastElement);
  var cellLeft=row.insertCell(0);
  var textNode=document.createTextNode(index);
  cellLeft.appendChild(textNode);
  
  var cellText=row.insertCell(1);
  var element=document.createElement('input');
  element.type='text';
  element.name='noq'+index;
  element.id='noq'+index;
  cellText.appendChild(element);
  
  var cellText=row.insertCell(2);
  var element=document.createElement('input');
  element.type='text';
  element.name='meq'+index;
  element.id='meq'+index;
  
  cellText.appendChild(element);
  var cellText=row.insertCell(3);
  var element=document.createElement('input');
  element.type='text';
  element.name='nm'+index;
  element.id='nm'+index;
  cellText.appendChild(element);
  }

	function removeRow()
{
  var ptable = document.getElementById('sectionUpdate');
  var lastElement = ptable.rows.length;
  if (lastElement > 1){ 
  ptable.deleteRow(lastElement - 1);
  }
  else
  {
  alert("you have to keep atleast one section");
  }
  }
	
