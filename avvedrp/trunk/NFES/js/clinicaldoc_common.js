


function setCdocPageAction ( actionstring ) {

       	document.forms[0].action.value = actionstring;
		
}


function disableHtmlButtons () {	
	if(document.getElementById("btsave")){
		document.getElementById("btsave").disabled = true;
	}
}

function docHtmlSave () {	
	disableHtmlButtons ();
	setCdocPageAction ( "CDOC-SAVE_THE_DOCUMENT" );
	document.forms[0].submit();
}


function docHtmlSaveAndApprove () {
	
	var tfmsg = confirm ( "Are you sure to approve this document ?" );
	if ( tfmsg == true ) {
		disableHtmlButtons ();
		setCdocPageAction ( "CDOC-FINAL_APPROVE" );
		document.forms[0].submit();
	}
	
}

function docHtmlShowPrintable () {
	
	disableHtmlButtons ();
	setCdocPageAction ( "CDOC-PRINT_READY_DOCUMENT" );
	document.forms[0].submit();
	
}

//========================= Created By Rajitha.k , 18-01-2011 ====================================

function docHtmlRecordwiseApprove () {
	
	var tfmsg = confirm ( "Would you like to approve this Record ?" );
	if ( tfmsg == true ) {
		disableHtmlButtons ();
		setCdocPageAction ( "CDOC-RECORD_APPROVE" );
		document.forms[0].submit();
	}
	
}

function generateIds(tabname)
{	
	//alert("2....");	
	var insertIds="";
	var deletedids=" ";	
	var doc_id="";
	
	var ctrlName=tabname;
	ctrlName=ctrlName.replace("Tab_staff_profile_","");
	ctrlName=ctrlName.replace("_v0","") +"_ID";   	
	//alert(ctrlName);

    	var mytable     = document.getElementById(tabname); 
	var rowCount = mytable.rows.length;               		
	//alert(rowCount);
	for(var i=1; i<rowCount; i++) {   
		doc_id=document.getElementById(tabname).rows[i].cells;     
		doc_id=doc_id[0].innerHTML;
				
		//alert(deletedids+","+doc_id);		
		if(doc_id.indexOf("#deleted")>0){			
			if (deletedids==" "){					
				deletedids="Deleted:"+doc_id.replace("#deleted","");
				
			}
			else{			
				deletedids=deletedids + "," +doc_id.replace("#deleted","");
			}
		}
		else{			
			if (insertIds==""){
				insertIds=insertIds+doc_id;				
			}
			else{
				insertIds=insertIds+","+doc_id;				
			}			
			
		}
		
		
	}	
	if (deletedids!=" "){
	     	document.getElementById(ctrlName).value=insertIds+","+deletedids;
	     	}
	else{
		document.getElementById(ctrlName).value=insertIds
	    }		
	//alert(deletedids);
	
}


	
function addRow(documentId,formname,entitytype,tabledata){
	
	
	/*var editRow=isDocumentIdExists(documentId,formname);
	//alert("Edit Row:"+editRow);	
	
	if (editRow<=0){
		//alert(1);
		var editRow=getEditedrow(formname);
		//alert(editRow);
	}
	
	*/	
	
	var editRow=getEditedrow(formname);	
	tabname="Tab_"+formname;	
	var mytable     = document.getElementById(tabname); 
	var rowCount = mytable.rows.length;
	//alert(tabledata);		
	var TabElements = tabledata.split("||,");		
	var colCount = (TabElements.length)
	//alert(rowCount);

	var i=0;		
	if (editRow>0){	
		//alert("Edit :1");
		var editRowcells=document.getElementById(tabname).rows[editRow].cells;		
		//alert("Edit :2");
		for (i=0;i<colCount;i++){
		//alert(TabElements[i]);
		  editRowcells[i].innerHTML=TabElements[i];
		}
		//alert("Edit :3");
	}	
	else
	{	//alert("New Row");
		var x=document.getElementById(tabname).insertRow(rowCount);		
		x.id="TR_"+TabElements[0];		
		//alert(x.id);
		for (i=0;i<colCount;i++){				
			 var y=x.insertCell(i);					 
			 y.innerHTML=TabElements[i];
			 y.id="TD_"+TabElements[i];
			 y.className="dataTableRows";			 
		}
                
		var editbutton='<input type="BUTTON" value="" id="EDIT'+ documentId + '" name="EDIT'+ documentId + '" ONCLICK="showchildform('+"'"+formname+ "'" + ',' + "'" + '' + documentId + "'" +','+ "'" + '' + entitytype + "'" + ');" />';		
		//alert(editbutton);
		ajaxFunction("edit",documentId);
		var y=x.insertCell(i);		
		y.innerHTML=editbutton;
                y.className="dataTableRows";
                y.style.textAlign="center";
                
		var deletebutton='<input type="BUTTON" value="" id="DELETE'+ documentId + '" name="DELETE'+ documentId + '" ONCLICK="deleteRow(this.parentNode.parentNode.rowIndex,' + "'" +tabname+ "'"+');" />';	
		//alert(deletebutton);
	        ajaxFunction("delete",documentId);
		var y=x.insertCell(i+1);		
		y.innerHTML=deletebutton;
		y.className="dataTableRows";
		y.style.textAlign="center";
	}	
	generateIds(tabname);
	hideIdColumn(tabname);
	//alert("end");
	
	
}

// Added on 13-05-2011 for Multi Language support

//Browser Support Code
function ajaxFunction(cn,documentId){
	var ajaxRequest;  // The variable that makes Ajax possible!

	try{
		// Opera 8.0+, Firefox, Safari
		ajaxRequest = new XMLHttpRequest();
	} catch (e){
		// Internet Explorer Browsers
		try{
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try{
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e){
				// Something went wrong
				alert("Your browser broke!");
				return false;
			}
		}
	}
	// Create a function that will receive data sent from the server
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4){
		     if(cn=="edit"){ 
		        var str=ajaxRequest.responseText.split('\n');
			document.getElementById("EDIT"+documentId).value=str[1];
		     }else if(cn=="delete"){
		        var str=ajaxRequest.responseText.split('\n');
		        document.getElementById("DELETE"+documentId).value=str[1];
		     }

			
		}
	}
	ajaxRequest.open("POST", "./jsp/ajaxResponseDetailForm.jsp", true);
	ajaxRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        ajaxRequest.send("cn="+cn);
		
}


// End of added on 13-05-2011 for Multi Language support 

function isDocumentIdExists(documentId,formname){
	//alert("Document Exists-Start");
	tabname="Tab_"+formname;		
	var mytable = document.getElementById(tabname); 
	var rowCount= mytable.rows.length;
	//alert("2:"+rowCount);
	
	var i=0;	
	for (i=1;i<rowCount;i++){
		var x=document.getElementById(tabname).rows[i].cells; 		
		//alert("DocumentID:"+x[0].innerHTML);		
		if (x[0].innerHTML==documentId){			
			//alert("Document Exists-Found");
			return i;	
			break;
		}
	}
	//alert("Document Exists-end");
	return 0;
}
 


function hideIdColumn(tabname){		
	var tbl  = document.getElementById(tabname);	
	var rows = tbl.getElementsByTagName('TR');		
	for (var row=0; row<rows.length;row++) {	      		      
	      var cels = rows[row].getElementsByTagName('TD');
	      cels[0].style.display='none';	      
    }	
	
}



function isMandatoryDataExists(frm) {

mandatory_fields=document.forms[0].mandatory_fields.value;
var fields= mandatory_fields.split("|");
var msg="";
for (var i=0;i<frm.length;i++){  	
	if(frm.elements[i].type!="hidden"){	
		for(var j=0;j<fields.length;j++){		
		  if(fields[j]==frm.elements[i].name){ 		  	
			if(frm.elements[i].value==""){ 					
				alert("Please Enter Mandatory Fields!!!");
				msg="1";
				break;
			}
		 } 	
		}
		if(msg=="1"){
			break;  	
		}
	}	
  }  
  if(msg=="1") {
  	return 0;
  }
  else {
  	return 1;
  }
  	
}


function setEditmark(documentId,formname){		
	var tabname="Tab_"+formname;		
	var mytable = document.getElementById(tabname); 
	var rowCount= mytable.rows.length;
	var i=0;	
	for (i=1;i<rowCount;i++){
		var editRow=document.getElementById(tabname).rows[i].cells; 				
		if (editRow[0].innerHTML.replace("#Edit","")==documentId){		
			var cont= editRow[0].innerHTML;
			cont=cont.replace("#Edit","");
			editRow[0].innerHTML=cont+"#Edit";										
		}
		else {
		var cont= editRow[0].innerHTML;
		cont=cont.replace("#Edit","");
		editRow[0].innerHTML=cont;
		}
	}
	
	
}


function getEditedrow(formname){
		
	var tabname="Tab_"+formname;		
	var mytable = document.getElementById(tabname); 
	var rowCount= mytable.rows.length;
	var i=0;	

	for (i=1;i<rowCount;i++){
		var editRow=document.getElementById(tabname).rows[i].cells; 	
		var cont= editRow[0].innerHTML;
		if(cont.indexOf("#Edit")>0){
		return i;
		break;
		//alert("2");
		}
		}
		//alert("No Edit Row");
	return 0;	
}

/*============================ Added on 21-02-2011 ==========================================*/
/*
function submitform_staff_profile_report_v0()
{
	//alert("forms[0]: "+document.forms[0].name+"\nforms[1]: "+document.forms[1].name);
	var frmelement0=document.getElementById("staff_profile_report_v0_0");
	var frmelement1=document.getElementById("staff_profile_report_v0");
	if(validateForm(frmelement0) && validateForm(frmelement1) && isMandatoryDataExists(frmelement1)=="1") {    		
	
		if(resume && photo)
		{       //alert("Resume: "+resume+"\nPhoto: "+photo);
			if(document.forms[1].upload_resumefiles.value==""){
				document.forms[0].upload_resume.value=document.forms[1].upload_resume.value; 
			}	
			else{
				document.forms[0].upload_resume.value=document.forms[1].upload_resume.value + "|"+ document.forms[1].upload_resumefiles.value; 
			}
		 
		       document.forms[0].upload_photo.value=document.forms[1].upload_photo.value;
		       document.forms[1].submit();
		}
		else if(resume && (!photo))
		{	//alert("Resume: "+resume+"\nPhoto: "+photo);
			if(document.forms[1].upload_resumefiles.value==""){
				document.forms[0].upload_resume.value=document.forms[1].upload_resume.value; 
			}	
			else{
				document.forms[0].upload_resume.value=document.forms[1].upload_resume.value + "|"+ document.forms[1].upload_resumefiles.value; 
			}
			 
		       document.forms[0].upload_photo.value=document.forms[1].upload_photofiles.value;
		       document.forms[1].submit();
		}
		else if((!resume) && photo)
		{
			//alert("Resume: "+resume+"\nPhoto: "+photo);
			document.forms[0].upload_resume.value=document.forms[1].upload_resumefiles.value;
			document.forms[0].upload_photo.value=document.forms[1].upload_photo.value;
		        document.forms[1].submit();
		}
		else
		{
			//alert("Resume: "+resume+"\nPhoto: "+photo);
			document.forms[0].upload_resume.value=document.forms[1].upload_resumefiles.value;
			document.forms[0].upload_photo.value=document.forms[1].upload_photofiles.value;
			
		}
		return 1;
	}
	else {
		return 0;
	}
}
*/

function submitform_staff_profile_report_v0()
{
	var frmelement1=document.getElementById("staff_profile_report_v0");
	if(validateForm(frmelement1) && isMandatoryDataExists(frmelement1)=="1") {    		
	     document.myForm.upload_resume.value=document.myForm01.upload_resumefiles.value;
	     document.myForm.upload_photo.value=document.myForm02.upload_photofiles.value;
	     return 1;
	}
	else {
		return 0;
	}
}

/*================================ End of added on 21-02-2011 ===================================*/

/*============================ Added on 28-02-2011 ==========================================*/

function isEmail(field) {
	var check = true;
	var str=field.value;
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if(! str.match(re)) {
	    check = false;
        }
	if(!check)
	{
	    field.style.backgroundColor ="#fca9ae";// "red";
	}
	else
	{
	    field.style.backgroundColor = "white";
	}
        return check;
}
	

function isNumber(field)
     {
          var check = true;
          var value = field.value;
          for(var i=0;i < field.value.length; ++i)
          {
               var new_key = value.charAt(i);
               if(((new_key < "0") || (new_key > "9")) && !(new_key == ""))
               {
                    check = false;
                    break;
               }
          }
          if(!check)
          {
               field.style.backgroundColor = "#fca9ae";//"red";
          }
          else
          {
               field.style.backgroundColor = "white";
          }
          return check;
     }

/*================================ End of added on 28-02-2011 ===================================*/

/*============================ Added on 01-03-2011 ==========================================*/

function validateForm(frm){		
	var str=frm.validation_fields.value;	
	var fields=str.split("|");
	var opt="";
	var f=true;
	for(var i=0;i<fields.length;i++){	     
	     opt=fields[i].split("-");
	     for(var j=0;j<frm.length;j++){	     		     	  
	          if(opt[0]==frm.elements[j].name){	               	          		          	
	               if(frm.elements[j].value!=""){	               	                   
	                    if(opt[1]=="E"){
		                 if(!(isEmail(frm.elements[j]))){
		                      alert("Not a valid e-mail address");f=false;
		       		      break;
		                 }
		            }else if(opt[1]=="N"){
		                 if(!(isNumber(frm.elements[j]))){
		                      alert("invalid character in number fields");f=false;	
		                      break;
		                 }
		            }else if(opt[1]=="Y"){		            	 
		            	 if(!(isYear(frm.elements[j]))){
		                      alert("invalid character in Year field");f=false;	
		                      break;
		                 } 
		            }else if(opt[1]=="P"){
		            	 if(!(isPhoto(frm.elements[j]))){
		                      alert("Invalid image file type in Photo field");f=false;	
		                      break;
		                 } 
		            }else if(opt[1]=="D"){
		            	 if(!(isDate(frm.elements[j]))){
		                      alert("Invalid date format");f=false;	
		                      break;
		                 } 
		            }else if(opt[1]=="A"){
		            	 if(!(validateAmount(frm.elements[j]))){
		                      f=false;	
		                      break;
		                 } 
		            }else if(opt[1]=="Per"){
		            	 if(!(validatePercentage(frm.elements[j]))){
		                      f=false;	
		                      break;
		                 } 
		            }
		       }     
	          }
	     }
	}
	return f;
}

function isYear(field){
	var check = true;
	var str=field.value;
	var re=/[^0-9]/;
	if(str.length>4 || str.length<4){
		check=false;
	}else if(str.match(re)){
		check=false;
	}
	if(!check)
	{
	     field.style.backgroundColor = "#fca9ae";
	}
	else
	{
	     field.style.backgroundColor = "white";
        }
	return check;
}

function submitform_staff_profile_qualification_v0(){
     var frmelement=document.getElementById("staff_profile_qualification_v0");     
     if(isMandatoryDataExists(frmelement)=="1" && validateForm(frmelement)){
          docHtmlSave();
     }	
}

function approveform_staff_profile_qualification_v0(){
     var frmelement=document.getElementById("staff_profile_qualification_v0");
     if(isMandatoryDataExists(frmelement)=="1" && validateForm(frmelement)){
          docHtmlRecordwiseApprove();
     }	
}

/*================================ End of added on 01-03-2011 ===================================*/

/*============================ Added on 03-03-2011 ==========================================*/

function isPhoto(field) {
	var check = true;
	var str=field.value;
	if(!/(\.bmp|\.gif|\.jpg|\.png|\.jpeg)$/i.test(str)) {
	     check = false;
	}
	if(!check)
	{
	     field.style.backgroundColor = "red";
	}
	else
	{
	     field.style.backgroundColor = "white";
	}
	return check;
}

function isDate(field){
	var check = true;
	var str=field.value;
	var s=str.split("-");
	var re=/[^0-9-]/;
	if(str.length>10 || str.length<6 || s[0]=="" || s[1]=="" || s[2]==""|| s[0]=="0" || s[0]=="00" || s[1]=="0" || s[1]=="00"){
	     check = false;
	}else if(str.match(re)){
	     check = false;
	}
	if(!check)
	{
	     field.style.backgroundColor = "#fca9ae";//"red";
	}
	else
	{
	     field.style.backgroundColor = "white";
	}
	return check;
}

/*================================ End of added on 03-03-2011 ===================================*/

/*============================ Added on 16-06-2011[Date comparison] ==========================================*/


/**********************************************************************
  function compareDates
   parameters: String1,String2 :format dd/mm/yyyy
   String2 can be set to "today" to default to current sysdate for convenience
   returns: integer, -1,0,1
   date1<date2 returns -1
   date1=date2 returns 0
   date1>date2) returns 1
   Can check for other cases like  date1 <=date2- e.g: if(!(date1,date2)==1)
/**********************************************************************/
function compareDatesfunc(date1,date2)
{

var datea ;
var dateb ;
var now = new Date();


splitString = date1.split("-");
datea = new Date(splitString[2],splitString[1]-1,splitString[0]);


if(date2.toLowerCase().valueOf() == "today")
{

 dateb = new Date(now.getFullYear(),now.getMonth(),now.getDate());

}
else
{
	splitString1 = date2.split("-");
	dateb = new Date(splitString1[2],splitString1[1]-1,splitString1[0]);
	
}
if (datea < dateb)
{
    return -1 ;
}
else if (datea > dateb)
{
    return 1 ;
}
else
{
     return 0 ;
}
}

/*================================ End of added on 16-06-2011 ===================================*/
/*============================ Added on 21-06-2011[Amount Validation] ==========================================*/
function validateAmount(field)
	{
		field.style.backgroundColor = "white";
		if(field.value == 0)
		{
		alert("Please Enter amount");
	  	field.focus();
	  	field.style.backgroundColor = "#fca9ae";
		return false;
		}
		if(field.value <= 0)
		{
		alert("Please Enter Proper amount");
		field.style.backgroundColor = "#fca9ae";
		field.focus();
		return false;
		}
		if(isNaN(field.value))
		{
		alert("Invalid Amount  ");
		field.style.backgroundColor = "#fca9ae";
		field.focus();
		return false;
	    	}
	    	return true;
	}
	

/*================================ End of added on 21-06-2011 ===================================*/

/*============================ Added on 21-06-2011[Percentage Validation] ==========================================*/
function validatePercentage(field)
	{
		field.style.backgroundColor = "white";
		if(isNaN(field.value) )
		{
		alert("Please Enter Numeric Value For Percentage Of Completion");
		field.style.backgroundColor = "#fca9ae";
		field.focus();
		return false;
		}
		if(eval(field.value)<=0)
	    	{
		alert("Please enter Proper Percentage");
		field.style.backgroundColor = "#fca9ae";
		field.focus(); 
		return false;
	    	}
	    	if(eval(field.value)>100)
	    	{
		alert("Please enter Proper Percentage");
		field.style.backgroundColor = "#fca9ae";
		field.focus(); 
		return false;
    		}
    		return true;
	}
/*================================ End of added on 21-06-2011 ===================================*/