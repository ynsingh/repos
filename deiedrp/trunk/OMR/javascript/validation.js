function isInteger(s){
	var i;
	for(i=0;i<s.length; i++){
	var c = s.charAt(i);
	if(((c<"0") || (c>"9"))) return false;
	}
	return true;
	}	
function addRow()
{
  var secTable = document.getElementById('sectionUpdate');
  var lastElement=secTable.rows.length;
  var index=lastElement;
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
	
