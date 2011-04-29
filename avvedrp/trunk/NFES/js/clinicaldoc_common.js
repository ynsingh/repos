/*
 * Copyright (c) 2002-2006 Amrita Technologies
 * Amritapuri, Kerala, India
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Amrita
 * Technologies. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Amrita Technologies.
 */

/*
 * Revision History
 *
 *  Date		Author		Comments
 *  11/11/2005		Manoj S		Added function for perio chart and dental chart controls.
 *
 */


//	************************************************************
//	
//	File containing most of the JavaScript functions used for Clinical Docs templates.
//	Created on 23-03-2004.
//	Create by Manoj.
//
//	************************************************************


//Modified By Sankaranarayana on 27  Jan 2008
//For Clinical form refreshing through ajax while closing pop-up window



var PrescriptionUpdater = Class.create();
PrescriptionUpdater.prototype = {
	initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {
  	 var browserName=navigator.appName; 
  	 if (browserName=="Netscape"){ 
    	var obj = ajaxResponse.getElementsByTagName("*");
		this.setPrescriptionDetails(obj[0]);
   
   	 }else if(browserName=="Microsoft Internet Explorer"){
   
      	this.setPrescriptionDetails(ajaxResponse.childNodes[0]);
  	 }
   	

   },

   setPrescriptionDetails: function(prescResp) {
         var name = prescResp.getAttribute("itemName");
         var value = prescResp.getAttribute("itemValue");
         value = value.replace(/    /g,"\r\n");//Added to solve bug#16269:misalignment in service and prescription controls
         eval("document.forms[0]."+name+".value =  ''");
         eval("document.forms[0]."+name+".value =  value");

   }

};



var DiagnosisUpdater = Class.create();
DiagnosisUpdater.prototype = {
	initialize: function() {
   },
  
   ajaxUpdate: function(ajaxResponse) {
   
   	var browserName=navigator.appName; 
  	 if (browserName=="Netscape"){ 
    	var obj = ajaxResponse.getElementsByTagName("*");
		this.setDiagnosisDetails(obj[0]);
   
   	 }else if(browserName=="Microsoft Internet Explorer"){
   
      	this.setDiagnosisDetails(ajaxResponse.childNodes[0]);
  	 }
   
   },

   setDiagnosisDetails: function(diagResp) {
         
         var name = diagResp.getAttribute("itemName");
         var value = diagResp.getAttribute("itemValue");

         addIcdToTable(value, name);

   }

};

var ServiceUpdater = Class.create();
ServiceUpdater.prototype = {
	initialize: function() {
   },
   ajaxUpdate: function(ajaxResponse) {

   	 var browserName=navigator.appName; 
  	 if (browserName=="Netscape"){ 
    	var obj = ajaxResponse.getElementsByTagName("*");
		this.setServiceDetails(obj[0]);
   
   	 }else if(browserName=="Microsoft Internet Explorer"){
   
      	this.setServiceDetails(ajaxResponse.childNodes[0]);
  	 }
   	
 
   },

   setServiceDetails: function(serviceResp) {
         var name = serviceResp.getAttribute("itemName");
         var value =serviceResp.getAttribute("itemValue");
		 value = value.replace(/    /g,"\r\n");//Added to solve bug#16269:misalignment in service and prescription controls
		 
         eval("document.forms[0]."+name+".value =  ''");
         eval("document.forms[0]."+name+".value =  value");

   }

};


var MibgSensitivityUpdater = Class.create();
MibgSensitivityUpdater.prototype = {
	initialize: function() {
   },
  
   ajaxUpdate: function(ajaxResponse) {
   
   	var browserName=navigator.appName; 
  	 if (browserName=="Netscape"){ 
    	var obj = ajaxResponse.getElementsByTagName("*");
		this.setMibgSensitivityResultDetails(obj[0]);
   
   	 }else if(browserName=="Microsoft Internet Explorer"){
   
      	this.setMibgSensitivityResultDetails(ajaxResponse.childNodes[0]);
  	 }
   
   },

   setMibgSensitivityResultDetails: function(diagResp) {
         
         var name = diagResp.getAttribute("itemName");
         var value = diagResp.getAttribute("itemValue");
      
         value = value.replace(/@/g,"\r\n");
         
         var tableValue=eval("document.forms[0]."+name+".value ");
         
         tableValue=tableValue+value;
        eval("document.forms[0]."+name+".value =  ''");
         eval("document.forms[0]."+name+".value =  tableValue");

   }

};


function initAjaxReqst(){

	diagnosisUpdater = new DiagnosisUpdater();
	serviceUpdater = new ServiceUpdater();
	prescriptionUpdater = new PrescriptionUpdater();
	mibgSensitivityUpdater = new MibgSensitivityUpdater();
	var contextPath = document.forms[0].contextPath.value;
	
	var servletUrl = contextPath+'/StaffProfileServlet'
	ajaxEngine.registerRequest( "CDOC-GET_AJAX_REQUEST",servletUrl);
	
	ajaxEngine.registerAjaxObject( 'diagnosisUpdater', diagnosisUpdater );
	ajaxEngine.registerAjaxObject( 'serviceUpdater', serviceUpdater );
	ajaxEngine.registerAjaxObject( 'prescriptionUpdater', prescriptionUpdater );
	ajaxEngine.registerAjaxObject( 'mibgSensitivityUpdater', mibgSensitivityUpdater );
	

	
}



var currentlySelectedControl; // can use this variable to set the currently selected control to this variable
var imageUploadControls;
var richTextNames;
var detailFormSave = false;
function getZScoreValue(absolute,indexed)
{
//	************************************************************
//	This function is to calculate the z-score with the two values, 
//	Absolute and Indexed to dest.
//	Here the z-score is:
//	z = (Absolute - Indexed) / Sqrt(Indexed).
//	************************************************************

	if( absolute != null && indexed != null )
	{
	
		if(absolute != '' || indexed != '')
		{
			
			var score = ( absolute - indexed ) / Math.sqrt( indexed );
			score = Math.round( score * 100 ) / 100;
			return score;
			
		} else {
		
			return '';
			
		}
		
	}else{
		return '';
	}
}


function getBSAwithHeightWeight(height,weight)
{
//	************************************************************
//	This function is to calculate the BSA (Body Surface Area), 
//	if Height(m) and Weight(kg) are given.
//	By DuBois and DuBois formula:
//	BSA (m2) = 0.20247 * (Height(m))0.725 * (Weight(kg))0.425
//	************************************************************

	if( height != null && weight != null )
	{
	
		if( height != '' || weight != '' )
		{
		
			//conversion of cm to m.
			height = height / 100;
			var bsa = 0.20247 * Math.pow( height,0.725 ) * Math.pow( weight,0.425 );
			bsa = Math.round( bsa * 100 ) / 100;
			return bsa;
			
		}else{
		
			return '';
		}
	}else{
	
		return '';
	}
}


/**
  * Function for getting the audio interface from velocity form pages...
  *
  **/
function openWindow(sectionName,labelName,isDictate) {
	
	document.forms[0].curSection.value=sectionName;
	document.forms[0].curLabel.value=labelName;
	document.forms[0].curControl.value=isDictate;
	
	//Modified by Sankaranarayanan on 27/12/2007,  for checking client machine's  have read ,wrte and audio permission
	if ( document.recordapplet ) {
		var requiredPermAvailable = document.recordapplet.checkRequiredPermissions();
		if (requiredPermAvailable)	{
			//Commented for Firefox integration, on 24-05-2005.
			var browserName=navigator.appName; 
			if (browserName=="Netscape"){ 
				hisShowModal("./Jsp/discharge/Style_Sheets/audioclip.htm",550,350,commonSelectionFunction,window);
			} else if (browserName=="Microsoft Internet Explorer"){
				window.showModalDialog("./Jsp/discharge/Style_Sheets/audioclip.htm",window,"dialogHeight:215px;dialogWidth:500px;dialogTop:300px;dialogLeft:300px;status:no;scroll:no;help:no");
			}
		}else{
			alert("You do not have permission for Audio facility. Please contact AHIS Support team...");
		}
	} 
}
      






commonSelectionFunction = function () {

}


function getDSControlWindow(action,patientId,visitId,caller,anch){
	//Commented for Firefox integration, on 24-05-2005.
	var browserName=navigator.appName; 
	if (browserName=="Netscape"){ 
		hisShowModal("./Jsp/discharge/dsControl.jsp?action=" + action +"&patientId="+ patientId + "&visitId=" + visitId + "&caller=" + caller + "#" + anch, 
			800,420,commonSelectionFunction,window);
	} else if (browserName=="Microsoft Internet Explorer"){ 
		mywindow = window.showModalDialog("./Jsp/discharge/dsControl.jsp?action=" + action +"&patientId="+ patientId + "&visitId=" + visitId + "&caller=" + caller + "#" + anch, 
			window, "dialogHeight:420px;dialogWidth:800px;location:no;menubar:no;scrollbars:yes;status:no;dialogTop:200px;dialogLeft:100");
	}
	
}

//Added by Prajeesh for child forms
//For Opening the selected child form from the combobox of 'childforms.vm'
function getChildFormWindow(action,patientId,visitId,docType,encounterId,ctrlName){
	currentlySelectedControl = ctrlName;
	
	var selectedFormIndex = eval("document.forms[0].comboChildForm" + ctrlName + ".selectedIndex;");
	var selectedForm = eval("document.forms[0].comboChildForm" + ctrlName + ".options[selectedFormIndex].value;");
	var docid = document.forms[0].documentId.value;
	if(selectedForm == ""){
		alert("select a form..");
		return;
	}
	if (browserName=="Netscape"){ 
		var urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
						"?action=" + action +"&patientId="+ patientId + "&visitId=" + visitId + "&formName="+ selectedForm + "&docType=" + docType+ "&encounterId=" + encounterId;
		hisShowModal (urlstring,950,550,setDocumentId,window);
	} else if (browserName=="Microsoft Internet Explorer"){ 
	
		var urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
						"?action=" + action +"&patientId="+ patientId + "&visitId=" + visitId + "&formName="+ selectedForm + "&docType=" + docType+ "&encounterId=" + encounterId;
		hisShowModal (urlstring,950,550,setDocumentId,window);
	}
	
}

//Added by Prajeesh for detail form on 09-09-06
//For Opening the detail form from the combobox of 'detailform.vm'
function getDetailFormWindow(action,patientId,visitId,docType,encounterId,ctrlName,formName){
	currentlySelectedControl = ctrlName;
	
	//var selectedFormIndex = eval("document.forms[0].comboDetailForm" + ctrlName + ".selectedIndex;");
	//var selectedForm = eval("document.forms[0].comboDetailForm" + ctrlName + ".options[selectedFormIndex].value;");
	var selectedForm = formName;
	var docid = document.forms[0].documentId.value;
	if(selectedForm == ""){
		alert("select a form..");
		return;
	}
	if (browserName=="Netscape"){ 
		var urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
						"?action=" + action +"&patientId="+ patientId + "&visitId=" + visitId + "&formName="+ selectedForm + "&docType=" + docType+ "&encounterId=" + encounterId;
		hisShowModal (urlstring,950,550,setDocumentIdOfDetailForm,window);
	} else if (browserName=="Microsoft Internet Explorer"){ 
	
		var urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
						"?action=" + action +"&patientId="+ patientId + "&visitId=" + visitId + "&formName="+ selectedForm + "&docType=" + docType+ "&encounterId=" + encounterId;
		hisShowModal (urlstring,950,550,setDocumentIdOfDetailForm,window);
	}
	
}
//Added by Prajeesh on 09-09-06 for saving the document Id of opened file from detailform.vm control
setDocumentIdOfDetailForm = function(lineItems) {
	if(dialogWin.returnedValue){	
		lineItems = dialogWin.returnedValue;
		var tableName = 'tblDetailForm'+currentlySelectedControl;
		addDetailFormResultToTable(lineItems,tableName,currentlySelectedControl);
	}
}
//added by Prajeesh on 09-09-06 for adding the detailForm details to the parent table 
function addDetailFormResultToTable (lineItems,tableName,currentlySelectedControl) {
	var docId = lineItems.docId;
	var formName = lineItems.formName;
	var createdBy = lineItems.createdBy;
	//alert(docId+"***"+formName+"****"+createdBy+"*****"+tableName);
	
	var tbl= document.getElementById(tableName); 
	//var txtIndex = tbl.rows.length;
	//var rowIndex = tbl.rows.length;
	if(docId != "" && docId != " " && docId != null){
		//try { 
			//var newRow = tbl.insertRow(rowIndex); 

			//var newCell = newRow.insertCell(0); 
			//newCell.innerHTML = lineItems.formName; 

			//var newCell3 = newRow.insertCell(1);
			//var onClickFun = "editChildForm('"+docId+"');"
			//newCell3.innerHTML = '<input type="button" value=" Edit " name= btEdit'+lineItems.formName+ ' onClick='+onClickFun+'>';
			
			if(eval('document.forms[0].'+currentlySelectedControl+'.value == ""' ))
				eval("document.forms[0]."+currentlySelectedControl+".value =  lineItems.docId");
			else
				eval("document.forms[0]."+currentlySelectedControl+".value =  document.forms[0]."+currentlySelectedControl+".value+','+lineItems.docId");
		//} 
		//catch(ex) { 
		//	//document.getElementById(txtError); 
		//}
	}
	detailFormSave = true;
	saveAudio();
}

//added by Prajeesh on 13-09-06 for adding the detailForm  
//details to the parent table while initializing
function addDetailFormsDetailsToTable(tblName, itemName, codeValue,itemValue) {
	//alert("Inside addDetailFormsDetailsToTable()");
	var tblObj = document.getElementById (tblName);
	var lastRow = tblObj.rows.length;
	var rowCnt = lastRow - 1;
	var strObjOne = new String();
	strObjOne = itemValue;
	var arrString = new Array();
	arrString = strObjOne.split("@");
	
	if (arrString.length > 0) {
		for (cnt =0; cnt < arrString.length; cnt++, lastRow++)
		{
			var newRow = tblObj.insertRow(lastRow);
			var strObjTwo = new String();
			strObjTwo = arrString[cnt];
			var arrTmpStr = new Array();
			arrTmpStr = strObjTwo.split("#");
			
			var documentId = arrTmpStr[0];
			var onClickFun = "editChildForm('"+documentId+"');"
			if(documentId == ""){
				documentId = 0;
			}
			var onClickFun1 = "deleteChildForm('"+documentId+"','"+itemName+"','"+tblName+"',this);"
			if(arrTmpStr.length  > 0)
			{
				try { 
					for(var count=0;count<arrTmpStr.length -1;count++){
						var newCell = newRow.insertCell(count);
						if(cnt != 0){
							newCell.innerHTML = arrTmpStr[count+1] =="" ?"&nbsp;":arrTmpStr[count+1];
						}else{
							newCell.innerHTML = "<b>"+arrTmpStr[count+1]+"</b>";
						}
					}
					if(arrTmpStr.length > 1 && cnt != 0 ){
						var newCell3 = newRow.insertCell(0);
						newCell3.innerHTML = '<input type="button" value=" Delete " name=btDelete'+cnt+ ' onClick='+onClickFun1+' class="clinical_button" onmouseover="this.className='+"'"+'clinical_button clinical_buttonhov'+"'"+'" onmouseout="this.className='+"'"+'clinical_button'+"'"+'" >';
						var newCell2 = newRow.insertCell(0);
						newCell2.innerHTML = '<input type="button" value=" Edit " name=btEdit'+arrTmpStr[1]+ ' onClick='+onClickFun+' class="clinical_button" onmouseover="this.className='+"'"+'clinical_button clinical_buttonhov'+"'"+'" onmouseout="this.className='+"'"+'clinical_button'+"'"+'" >';

					}else if(arrTmpStr.length > 1 && cnt == 0 ){
						var newCell3 = newRow.insertCell(0);
						newCell3.innerHTML =  "<b>Delete</b>";
						var newCell2 = newRow.insertCell(0);
						newCell2.innerHTML =  "<b>Edit</b>";
					}
				}catch(ex) { 
					//document.getElementById(txtError); 
				}
			}
			
			if(eval('document.forms[0].'+itemName+'.value == ""' )){
				eval("document.forms[0]."+itemName+".value =  arrTmpStr[0]");
			}
			else{
				eval("document.forms[0]."+itemName+".value =  document.forms[0]."+itemName+".value+','+arrTmpStr[0]");
			}
		}
	}
	
}

//Added by Saju
//For deleting the document id from values tables, that are already saved, when we click on delete button in the 'detailform.vm'
function deleteChildForm(docId,itemName,tblName,thisObj){
	
	//alert(thisObj.parentNode.parentNode.rowIndex);
	var oRow = thisObj.parentNode.parentNode;  
	var rowNo = oRow.rowIndex;
	var valueString = eval("document.forms[0]."+itemName+".value")+',';
	var tblObj = document.getElementById (tblName);
	// delete this row
	tblObj.deleteRow(rowNo);
	var docIds = valueString.replace( docId+",","" );
	if(docIds.length == 0)
	{
		eval("document.forms[0]."+itemName+".value = ''");
	}else
	{	
		docIdString = docIds.substring(0,docIds.length - 1);
		eval("document.forms[0]."+itemName+".value = docIdString");
	}
}


//Added by manoj s to open the latest form control...
//On 25/01/2006...
function openLatestForm(documentId){
	var urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
						"?action=EMR-CDOC_SHOW_DOCUMENT_IN_NEW_WINDOW" +"&documentId="+ documentId;
}


setDocumentId = function(lineItems) {
	if(dialogWin.returnedValue){	
		lineItems = dialogWin.returnedValue;
		var tableName = 'tblChildForm'+currentlySelectedControl;
		addChildFormResultToTable(lineItems,tableName,currentlySelectedControl);
	}
}

reloadBrowser = function() {

}


function showTemplates() {

	mywindow = window.showModalDialog("./Jsp/discharge/dsControl.jsp?action=DS-COMMON_CONTROL_INITIALIZE", window, 
				"dialogHeight:400px;dialogWidth:800px;location:no;menubar:no;scrollbars:yes;status:no;dialogTop:200px;dialogLeft:100");

}


function removeResultsFromList(objName, tblName) {
	var tbl = document.getElementById(tblName);
	var rowLength = tbl.rows.length;
	//var selObjName = tblName;
	var selObjName = 'selObj' + tblName
	
	//var elements = document.getElementsByName(tblName);
	var elements = document.getElementsByName(selObjName);
	var chkedCnt = 0;
	
	for(j = 0; j < elements.length; j++){
	  			
	 	if (elements[j].checked == true) { 
			chkedCnt = chkedCnt + 1;
		 } 

	}
	if(chkedCnt==0){
		alert('Select the results to remove...');
	}
	
	for (i = 0; i < chkedCnt; i++) {
		for (k = 0; k < elements.length; k++) {
			if (elements[k].checked == true) { 
				tbl.deleteRow(k+1);
			}
		}
	}

}

function removeDiagnosisFromList(objName, tblName) {
	var tbl = document.getElementById(tblName);
	var rowLength = tbl.rows.length;
	var selObjName = 'selObj' + tblName
	
	var ctrlDiagIds="diagIds"+objName;
	var arrDiagCntlids = new Array();
	if(document.getElementById(ctrlDiagIds)){
		var selDiagIds=document.getElementById(ctrlDiagIds);
	     arrDiagCntlids=selDiagIds.value.split(",");
	}
	var elements = document.getElementsByName(selObjName);
	var chkedCnt = 0;
	
	for(j = 0; j < elements.length; j++){
	  			
	 	if (elements[j].checked == true) { 
			chkedCnt = chkedCnt + 1;
		 } 
	}
	if(chkedCnt==0){
		alert('Select the rows to be removed...');
	}
	var count=0; 
	for (i = 0; i < chkedCnt; i++) {
		for (k = 0; k <  elements.length; k++) {
			if (elements[k].checked == true) { 
				tbl.deleteRow(k+1);
				
				if(arrDiagCntlids.length > 0){
				   arrDiagCntlids[k]="";	   
				  var ids="";
				  var isFirst=true;
					for (h = 0; h < arrDiagCntlids.length; h++) {		   
					   if(arrDiagCntlids[h]!="" && isFirst==true){
					     ids=arrDiagCntlids[h];
					     isFirst=false;
					   }else if(arrDiagCntlids[h]!=""){
					      ids=ids+","+arrDiagCntlids[h];
					   }
					}
					selDiagIds.value=ids;
					arrDiagCntlids=selDiagIds.value.split(",");
				}
			}
		}
	}
	
}


function addLabResultToTable (tbl, hid, theValue) {
	var tblObj = document.getElementById (tbl);
	var lastRow = tblObj.rows.length;
	var rowCnt = lastRow - 1;
	var strForHid = "";
	var strForRadioButton = "";
	
	var strObjOne = new String();
	strObjOne = theValue;
	var arrString = new Array();
	var selObjName = 'selObj' + tbl;
	
	arrString = strObjOne.split("@");
	
	if (arrString.length > 0) {
		for (cnt =0; cnt < arrString.length; cnt++, lastRow++)
		{
			var strObjTwo = new String();
			strObjTwo = arrString[cnt];
			var arrTmpStr = new Array();
			arrTmpStr = strObjTwo.split("!");

			if (cnt == arrString.length - 1) {
				var chkVal = new String();
				chkVal = arrString[cnt];
				chkVal = chkVal.replace (" ", "");
				if ( chkVal == '3' ){
					strForRadioButton = chkVal;
				}
				 else if (chkVal == '2') {
					strForRadioButton = chkVal;
				}
				else if (chkVal == '1') {
					strForRadioButton = chkVal;
				}
				
				break;
			}
			
			if (arrTmpStr.length > 0) {
				
				var row = tblObj.insertRow(lastRow);
				var j = 0;
				for (i = 0,j=0; i < arrTmpStr.length; i++)
				{
					if (i != 3 && i != 8) { 
						var ch;
						var chid;
						if ( i == 0 ) { 
							strForHid = strForHid + arrTmpStr[i] + ",";
							ch = document.createElement('input');
							ch.setAttribute('type','checkbox');
							ch.setAttribute('name',selObjName);
							ch.setAttribute('id',selObjName);

							chid = document.createElement('input');
							chid.setAttribute('type','hidden');
							chid.setAttribute('name',hid);
							chid.setAttribute('value', arrTmpStr[i]);
						} else { 
							ch = document.createTextNode(arrTmpStr[i]);
						}

						var cellNext = row.insertCell(j);
						j = j + 1;
						cellNext.appendChild(ch);

						if ( i == 0 ) {
							cellNext.appendChild(chid);
						}
					}
				}
			}
		}
	}
	
	if (strForRadioButton == '3') {
		document.forms[0].checkRadio.value = '3';
	}else if (strForRadioButton == '2'){
		document.forms[0].checkRadio.value = '2';
	}else if (strForRadioButton == '1') {
		document.forms[0].checkRadio.value = '1';
	}
}

//added by Prajeesh for Child Forms
//For adding already existing child forms to the table in the 'childforms.vm'
function addChildFormsExistsToTable(tblName, itemName, itemValue) {
	//alert("Inside addChildFormsExistsToTable()");
	var tblObj = document.getElementById (tblName);
	var lastRow = tblObj.rows.length;
	var rowCnt = lastRow - 1;

	var strObjOne = new String();
	strObjOne = itemValue;
	var arrString = new Array();
	arrString = strObjOne.split("@");
	
	//alert("arrString.length:"+arrString.length);
	if (arrString.length > 0) {
		for (cnt =0; cnt < arrString.length; cnt++, lastRow++)
		{
			var strObjTwo = new String();
			strObjTwo = arrString[cnt];
			//alert("strObjTwo:"+strObjTwo+":");
			var arrTmpStr = new Array();
			arrTmpStr = strObjTwo.split("#");
			//alert("arrTmpStr.length:"+arrTmpStr.length);
			if(arrTmpStr.length > 0)
			{
				try { 
					var documentId = arrTmpStr[0];
					//alert("documentId :"+documentId);
					
					if(documentId != "" && documentId != " " && documentId != null)
					{
						var onClickFun = "editChildForm('"+documentId+"');"
						var newRow = tblObj.insertRow(lastRow); 

						var newCell0 = newRow.insertCell(0);
						var newCell1 = newRow.insertCell(1); 
						//var newCell2 = newRow.insertCell(2);
						
						newCell0.innerHTML = arrTmpStr[1]; 
						//newCell2.innerHTML = arrTmpStr[2];
						newCell1.innerHTML = '<input type="button" value=" Edit " name= btEdit'+arrTmpStr[1]+ ' onClick='+onClickFun+'>';

						if(eval('document.forms[0].'+itemName+'.value == ""' )){
							eval("document.forms[0]."+itemName+".value =  arrTmpStr[0]");
						}
						else{
							eval("document.forms[0]."+itemName+".value =  document.forms[0]."+itemName+".value+','+arrTmpStr[0]");
						}	
						
					}
				} 
				catch(ex) { 
					//document.getElementById(txtError); 
				}
			}
		}
	}
	
}

//added by Prajeesh for Child Forms
//For adding the child forms opened and saved in the 'childforms.vm'
function addChildFormResultToTable (lineItems,tableName,currentlySelectedControl) {
	var docId = lineItems.docId;
	var formName = lineItems.formName;
	var createdBy = lineItems.createdBy;
	//alert(docId+"***"+formName+"****"+createdBy+"*****"+tableName);
	
	var tbl= document.getElementById(tableName); 
	//var txtIndex = tbl.rows.length;
	var rowIndex = tbl.rows.length;
	if(docId != "" && docId != " " && docId != null){
		try { 
			var newRow = tbl.insertRow(rowIndex); 

			var newCell = newRow.insertCell(0); 
			newCell.innerHTML = lineItems.formName; 

			//var newCell2 = newRow.insertCell(1); 
			//newCell2.innerHTML = lineItems.createdBy; 

			var newCell3 = newRow.insertCell(1);
			var onClickFun = "editChildForm('"+docId+"');"
			newCell3.innerHTML = '<input type="button" value=" Edit " name= btEdit'+lineItems.formName+ ' onClick='+onClickFun+'>';
			//alert("currentlySelectedControl is:"+currentlySelectedControl);
			if(eval('document.forms[0].'+currentlySelectedControl+'.value == ""' ))
				eval("document.forms[0]."+currentlySelectedControl+".value =  lineItems.docId");
			else
				eval("document.forms[0]."+currentlySelectedControl+".value =  document.forms[0]."+currentlySelectedControl+".value+','+lineItems.docId");
			//alert(eval("document.forms[0]."+currentlySelectedControl+".value"));
		} 
		catch(ex) { 
			//document.getElementById(txtError); 
		}
	}
}
//Added by prajeesh for child forms
//For editing the forms, that are already saved, when we click on edit button in the 'childforms.vm'
function editChildForm(docId){
	//alert("Inside editChildForm:"+docId);
	//Changed the action for mdifying the button name from 'Set As Request Form' to 'close'
	var action = 'EMR-CDOC_SHOW_DOCUMENT_IN_NEWWINDOW_4_CHILDWINDOWS';
	if (browserName=="Netscape"){ 
		var urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
				"?action=" + action +"&documentId="+ docId;
		hisShowModal (urlstring,950,550,doNothing1,window);
	} else if (browserName=="Microsoft Internet Explorer"){ 
		var urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
				"?action=" + action +"&documentId=" + docId;
		//hisShowModal (urlstring,950,550,doNothing1,window);
		//window.open(urlstring);
		//alert("doNothing");
		hisShowModal(urlstring,950,550,doNothing1,window);
	}
}
//added by prajeesh for child forms
doNothing1 = function(lineItems){
	if(dialogWin.returnedValue){		
		lineItems = dialogWin.returnedValue;
		detailFormSave = true;
		saveAudio();	
	}
}

function openPrescriptionWindow( doctorId, 
				doctorname,
				patientId,
				visitId,
				mrdno,
				patientName,
				appointmentId ) {
	var urlStr = "./Jsp/ordercom/NewPrescription.jsp?doctorId="+doctorId+"&doctorname="+doctorname+"&patientId="+patientId+"&visitId="+visitId+"&mrdNo="+mrdno+"&patientName="+patientName+"&appointmentId="+appointmentId+"&action=PRESCRIPTION-INITIALISE_NEW_DS_PRESCRIPTION";
	hisShowModal( urlStr,650,600,reloadPrescriptions,"" );
}


function openPrescriptionDetailsWindow ( prescriptionId,
					 doctor,
					 patientName,
					 mrdNo,
					 rowNum,
					 patientCategory,
					 patientSex,
					 visitType ) { 
	var urlStr = "./Jsp/ordercom/PrescriptionDetails.jsp?Id=" + prescriptionId + "&doctor="+doctor+"&patientName="+patientName+"&mrdNo="+mrdNo+"&rowno="+rowNum+"&patientCategory="+patientCategory+"&sex="+patientSex+"&visitType="+visitType+"&action=PRESCRIPTION-INITIALIZE_PRESCRIPTION_DETAILS";
	hisShowModal( urlStr,750,630,modifyPrescriptionHeaderDetails,"" );
}


function openProcReportWindow ( patientId, visitId ) {
	var action = "DS-PATIENT_VISIT_REPORTS";
	mywindow = window.open("./Jsp/discharge/dsControl.jsp?action=" + action + "&patientId=" + patientId + "&visitId=" + visitId, 
			"reportwindow", "resizable=1,status=1,scrollbars=1,width=810,height=420,top=100,left=100");
}
//added by Neena to open referral viewer window
function openReferralsWindow ( mrdNumber, visibility ) {
	referralWindow = window.open("./Jsp/mrdviewer/ReferralsViewer.jsp?action=EMR-REFFERALSVIEWER_INITIALIZE_TAB&mrdNumber="+mrdNumber+"&visibility="+visibility,
	                              "reportwindow","resizable=1,status=1,scrollbars=1,width=870,height=420,top=100,left=100");
}


//Function to open the annotation applet window
//Created by Suryasri
function openAppletWindow ( img,imgname ) {

	var ptid = document.forms[0].patientId.value;
	var vid = document.forms[0].visitId.value;
	var fname = document.forms[0].formName.value;
	var docid = document.forms[0].documentId.value;
	//var imgPath = "http://192.168.111.102//annotation//images//" + img;
	var urlstring = "./Jsp/mrdviewer/AmritaAnnotation.jsp?patientId=" + ptid + 
								"&visitId=" + vid + 
								"&formName=" + fname +
								"&documentId=" + docid +
								"&imgPath=" + img + 
								"&fieldname=" + imgname;
	
	window.open( urlstring,'name','height=1300,width=1000,left = 0,right = 10');
	//hisShowModal(urlstring,1200,1200,testingAnnotation,"");
}

testingAnnotation = function() {

}

function setCdocPageAction ( actionstring ) {

       	document.forms[0].action.value = actionstring;
		
}


function disableSubmitButtons() {
	if ( document.forms[0].savebutton )
		document.forms[0].savebutton.disabled = true;

	if ( document.forms[0].rft ) 
		document.forms[0].rft.disabled = true;

	if ( document.forms[0].rfa ) 
		document.forms[0].rfa.disabled = true;

	if ( document.forms[0].fa ) 
		document.forms[0].fa.disabled = true;
     //Start Merge:This Segment taken from 4.0.3 BARC branch--Anitha
	/*if ( document.forms[0].printable ) 
		document.forms[0].printable.disabled = true;*/ //End Merge

	if ( document.forms[0].rfd ) 
		document.forms[0].rfd.disabled = true;

}

function enableSubmitButtons() {
	if ( document.forms[0].savebutton )
		document.forms[0].savebutton.disabled = false;

	if ( document.forms[0].rft ) 
		document.forms[0].rft.disabled = false;

	if ( document.forms[0].rfa ) 
		document.forms[0].rfa.disabled = false;

	if ( document.forms[0].fa ) 
		document.forms[0].fa.disabled = false;

	if ( document.forms[0].printable ) 
		document.forms[0].printable.disabled = false;

	if ( document.forms[0].rfd ) 
		document.forms[0].rfd.disabled = false;

}



//this function is used to gettingthe image name when browsing
function saveImage(image){
	
	/*if(eval("document.forms[0]."+image+".value") != ""){
	 alert(eval("document.forms[0]."+image+".value"));
		return;
	}*/
	
	if(eval("document.forms[0]." +" imageName "+ ".value") == ""){
		eval("document.forms[0].imageName.value = document.forms[0].imgN"+image+".value");
		document.forms[0].imgCntrl.value = image;
		
		
	}else{
		eval("document.forms[0].imageName.value=document.forms[0]." + "imageName" + ".value+','+ document.forms[0].imgN"+image+".value");
		
		document.forms[0].imgCntrl.value = document.forms[0].imgCntrl.value + "," + image;
		
	}
}


//this function is used to upload file at the time of saving
function saveAudio(){
	document.forms[0].target = "";
	
	
	//Added sankaranarayanan on 23/09/2006 for checking mandatory controles
	if(document.forms[0].mandatoryControl){
		var choiceElementStr = document.forms[0].mandatoryControl.value;
		if(choiceElementStr != null || choiceElementStr != "" || choiceElementStr != " "){
			var flag = checkMandatoryControles();
			if(flag == false ){
				return;
			}


		}
	}

	
	disableSubmitButtons();	
	var status = document.forms[0].curStatus.value;
	
	if( status > 2){
		//setCdocPageAction ( "CDOC-SAVE_THE_DOCUMENT" );
		document.forms[0].target = "frmSaveHidden";
		setCdocPageAction ( "CDOC-SAVE_WITHOUT_REFRESH_THE_DOCUMENT" );
		document.forms[0].submit();
		document.forms[0].normalSave.value="No";
		enableSubmitButtons();
	} else {
		var mrdNumber = document.forms[0].mrdNumber.value;
		var visitCode = document.forms[0].visitCode.value;
		var imgEditStatus = false;
		var retValue = false;
		//added by saju for uploading image
		/*
		if ( document.imgapplet ) {
			var imgName = eval("document.forms[0]." + "imageName "+ ".value");
			if(imgName != "")
			{
				var imageNames = new Array();
				var strObjTwo = new String();

				var strObjTwo = document.imgapplet.upLoadAllFiles ( mrdNumber, visitCode, imgName);

				imageNames = strObjTwo.split(",");

				var strObjOne = new String();
				strObjOne = document.forms[0].imgCntrl.value;
				var imageControls = new Array();
				imageControls = strObjOne.split(",");
				
				for(i=0;i<imageNames.length;i++){
					eval("document.forms[0]."+imageControls[i]+".value =imageNames[i]+'@'");
				}
			}

		}
		*/
		/**		Added by Hani M		**/
		if ( document.forms[0].imageUploadYesNo) {
				var obj = document.imgapplet;
				var result = "";
				var newResults = "";
				var thisImageName="";
				var retVal = "";
				//var mrdNo = document.forms[0].mrdNumber.value;
				var patientId = document.forms[0].patientId.value;
				var imageUploadCntrls = "";
				if(imageUploadControls && imageUploadControls.length>0)
					imageUploadCntrls = imageUploadControls.substring(0,imageUploadControls.length-1);
				var arrImgUploadCntrls = imageUploadCntrls.split(",");
								
				if(arrImgUploadCntrls && arrImgUploadCntrls.length >0){
				    for(var p=0; p<arrImgUploadCntrls.length; p++){
					tbln = document.getElementById("myTable"+arrImgUploadCntrls[p]);
					var len = 0;
					if(tbln){
						len = tbln.rows.length;
					}
					var newDirYesNo = "YES";
					for(i=1;i<len;i++){				
						thisImageName = tbln.rows[i].childNodes[0].childNodes[0].data;
						if(i>1){
							newDirYesNo = "NO";
						}	

						//result = obj.loadPathologyImages(mrdNo,thisImageName,newDirYesNo);
						var paramNames=new Array("fileName","mrdNo","CreateDir") ;
						//mrdNo parameter in the "paramValues" array is used for creating a folder
						//structure and store it in the server. But in case of BARC, we can't use
						//mrdNo for directory name since it may contain "/", so instead of mrdNo, we
						//are using patientId
						//var paramValues = new Array(thisImageName,mrdNo,newDirYesNo);	
						//alert(thisImageName);
						var paramValues = new Array(thisImageName,patientId,newDirYesNo);	
						result = uploadFile(obj,"ScannedImageServlet?action=UPLOAD-PATHOLOGY-IMAGES",paramNames,paramValues);	
						//name = thisImageName.split("\\");
						name = thisImageName.split("/");
						if (navigator.appName == 'Netscape') {
							setVal = name[name.length-1];
						}else{
							//setVal = name.substring(name.lastIndexOf("\\") + 1,name.indexOf("\""));
							setVal = name.substring(name.lastIndexOf(",") + 1,name.length);
						}
						
						/*
						var intIndexOfMatch = setVal.indexOf( " " );
						while (intIndexOfMatch != -1){
							// Relace out the current instance.
							setVal = setVal.replace( " ", "" );

							// Get the index of any next matching substring.
							intIndexOfMatch = setVal.indexOf(" ");
						}
						//retVal = setVal.replaceAll(" ", "+");	
						*/
						if(i<len-1 ){						
							newResults = newResults + setVal + ",";						
						}else{
							newResults = newResults + setVal;
							imgEditStatus=true;
						}
						newResults = replaceAlll(newResults);
						if(i == len-1){
							newResults = result + "#" +newResults;
						}
						if(len>1 && result != null){
							eval("document.forms[0]."+arrImgUploadCntrls[p]+".value = '" + newResults + "';");
						}
					}
					newResults = "";
				    }
				}
		}
		
		//Modified by Sankaranarayanan on 27/12/2007,  for checking client machine's  have read ,wrte and audio permission
				if ( document.recordapplet ) {
					var requiredPermAvailable = document.recordapplet.checkRequiredPermissions();
					if (requiredPermAvailable)	{
						retValue = document.recordapplet.upLoadAllFiles ( mrdNumber, visitCode );
					}
				}
		


		//document.forms[0].Submit1.value = "Save";
		if(document.forms[0].documentId.value && !imgEditStatus && !detailFormSave)
		{
			getRichTextValues();
			document.forms[0].target = "frmSaveHidden";
			setCdocPageAction ( "CDOC-SAVE_WITHOUT_REFRESH_THE_DOCUMENT" );
			document.forms[0].submit();
			document.forms[0].normalSave.value="No";
			if(document.forms[0].docIdsToCancel){
				document.forms[0].docIdsToCancel.value="";
			}
			enableSubmitButtons();
		}else{
			getRichTextValues();
			setCdocPageAction ( "CDOC-SAVE_THE_DOCUMENT" );
			document.forms[0].submit();
			document.forms[0].normalSave.value="Yes";
			if(!detailFormSave){
				refreshMrdTree();
			}
			detailFormSave = false;
		}
		if(document.forms[0].printable){			
			document.forms[0].printable.focus();
		}else{
			
			document.getElementById('myAnchor').focus();
		}			
	}
}



function refreshMrdTree(){
	if(parent.frames){
		if( parent.frames['tabFrame'] && parent.frames['treeFrame'] ){	
		    //modified as part of PMT#T5575:replacing the existing Patient Info Business Logic
			var mrdNumber = parent.frames['tabFrame'].getJadeControl("mrdNumber").value;
			var deptId = parent.frames['tabFrame'].getJadeControl("selDepartment").value
			parent.frames['treeFrame'].location.href= "./Jsp/mrdviewer/general.jsp?action=MRD_TREE&deptId="+deptId+"&mrdNumber="+mrdNumber;
		} else if(parent.frames['treeFrame']){
			parent.frames['treeFrame'].location.href= "./Jsp/mrdviewer/ClinicalNotesTree.jsp?action=VISITS";	
		}
		
	}


}

function replaceAlll(newResults){
	var i = 0;
	var results = "";
	for(i=0;i<newResults.length;i++){
		results = newResults.replace(" ","_");
		newResults = results;
	}
	return newResults;
}
//new function to save the cpath forms...
function cPathSaveAudio(){

	document.forms[0].target = "";
	disableSubmitButtons();	
	var status = document.forms[0].curStatus.value;
	if( status > 2){
		//document.forms[0].Submit1.value = "Save";
		setCdocPageAction ( "DS-SAVE_CPATH_DOCUMENT" );
		document.forms[0].submit();
	} else {
		var mrdNumber = document.forms[0].mrdNumber.value;
		var visitCode = document.forms[0].visitCode.value;
		
		
		var retValue = false;
		if ( document.recordapplet ) {
			retValue = document.recordapplet.upLoadAllFiles ( mrdNumber, visitCode );
		}

		//document.forms[0].Submit1.value = "Save";
		setCdocPageAction ( "DS-SAVE_CPATH_DOCUMENT" );
		document.forms[0].submit();
	}
	document.forms[0].normalSave.value="Yes";
}


//this function is to create a folder in c:\audio\save directory for the patient
//Commented this method by sankaranarayanan on 22-01-2010 , this logic is directly calling from SoundApplet
/*function createAudioFolder ( arg1, arg2 ) {
	if ( document.recordapplet ) {
		var isDir = document.recordapplet.directory( arg1,arg2 );
		return isDir;
	} else {
		return false;
	}
}*/





function readyForTranscription () {
	document.forms[0].normalSave.value="Yes";
	document.forms[0].target = "";
	var ptId = document.forms[0].mrdNumber.value;
	var vId = document.forms[0].visitCode.value;
	var docId = document.forms[0].documentId.value;
	var curStatus = document.forms[0].curStatus.value;

	if ( docId > 0 && curStatus > 1 ) {
		alert ( 'This document is already ready for transcription' );
	} else {

		var retValue = false;
		
		if ( document.recordapplet ) { 
			var requiredPermAvailable = document.recordapplet.checkRequiredPermissions();
			if(requiredPermAvailable){
				retValue = document.recordapplet.upLoadAllFiles ( ptId, vId );
			}
		}
		
		
		if(retValue == true ){
			disableSubmitButtons();
			//document.forms[0].Submit5.value = "Ready for Transcription";
			setCdocPageAction ( "CDOC-OPEN_FOR_TRANSCRIPTION" );
			document.forms[0].submit();
		}else{
			alert( 'Cannot make ready for transcription. No dictations done...');
		}
	}
}


function backToDictated () {
	document.forms[0].normalSave.value="Yes";
	document.forms[0].target = "";
	disableSubmitButtons();
	setCdocPageAction ( "CDOC-SAVE_BACK_TO_DICTATED" );
	document.forms[0].submit();
}


function readyForApproval () {
	document.forms[0].normalSave.value="Yes";
	document.forms[0].target = "";
	disableSubmitButtons();
	//document.forms[0].Submit4.value = "Ready for Approve";
	setCdocPageAction ( "CDOC-OPEN_FOR_APPROVAL" );
	document.forms[0].submit();
}


function finalApprove () {

	var tfmsg = confirm ( "Are you sure to approve this document ?" );
	if ( tfmsg == true ) {
		document.forms[0].normalSave.value="Yes";
		document.forms[0].target = "";
		disableSubmitButtons();
		//document.forms[0].Submit2.value = "Final Approve";
		if(document.forms[0].docType.value == "DS")
		{
			document.forms[0].target = "_newWindow";
		}
		getRichTextValues();
		setCdocPageAction ( "CDOC-FINAL_APPROVE" );
		document.forms[0].submit();
	}
	
}


function disableHtmlButtons () {
	
	if(document.forms[0].btsave){
		document.forms[0].btsave.disabled = true;
	}
	
	/*if(document.forms[0].btsapprove){
		document.forms[0].btsapprove.disabled = true;
	}
	
	if(document.forms[0].btprintable){
		document.forms[0].btprintable.disabled = true;
	}
	
	if(document.forms[0].btprovisional){
		document.forms[0].btprovisional.disabled = true;
	}*/
	
	
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

function SaveProvisional(){

	var docid = document.forms[0].documentId.value;
	if(docid==""||docid==null)
	{
		alert("Please save the document before making it Provisional");
		return;
	}//Start Merge:This Segment taken from 4.0.7 AIMS branch:---Anitha
	document.forms[0].target = "_self";//End Merge
	setCdocPageAction ( "CDOC-SAVE_PROVISIONAL" );
	document.forms[0].submit();
	
}

function docHtmlShowPrintable () {
	
	disableHtmlButtons ();
	setCdocPageAction ( "CDOC-PRINT_READY_DOCUMENT" );
	document.forms[0].submit();
	
}


function disableBackToDictatedButton() {
	if ( document.forms[0].rfd && document.forms[0].curStatus.value != '2' ) {
		document.forms[0].rfd.value = "";
		document.forms[0].rfd.style.visibility = 'hidden';
	}
}


function autoFillSelectedKeyWords(event) {
	var the_key = 0;
	if (event.keyCode) {
		the_key=event.keyCode;
	} else if (event.which) {
		the_key=event.which;
	}

	if(the_key == 32 || the_key == 13) {
		var changedYesNo = false;
		var thisControl = event.srcElement.name;
		
		if(eval("document.forms[0]." + thisControl + ".createTextRange")){
			eval("document.forms[0]." + thisControl + ".caretPos = document.selection.createRange().duplicate();");
		}
		
		var txt_1_value = eval("document.forms[0]." + thisControl + ".value");
		
		for(i=0;i < allTemplateNames.length;i++){
			var thisArrayName = allTemplateNames[i];
			var thisArrayValue = allTemplateValues[i];
			var stringArray = txt_1_value.split(thisArrayName);
			if(stringArray.length > 1){
				txt_1_value = txt_1_value.replace(thisArrayName,thisArrayValue);
				changedYesNo = true;
			}
		}
		
		if(changedYesNo) {
			var evalString = txt_1_value.replace(/\r\n/g,"\\r\\n");
			eval("document.forms[0]." + thisControl + ".value = '" + evalString + "';");
			//var caretPos = eval("document.forms[0]." + thisControl + ".caretPos");
			//alert(caretPos.text);
		}
	}
}


function openTemplateBrowser(thisControl) {
	document.forms[0].htmlPageControl_Event_Source_For_Autofill.value = thisControl;
	var controlObj = eval("document.forms[0]." + thisControl);
	if(controlObj.createTextRange){
		controlObj.caretPos = document.selection.createRange().duplicate();
		var caretPos = controlObj.caretPos;
	}
	var urlStr = "./Jsp/discharge/Templates.jsp?action=CDOC-TEMPLATES_INITIALIZE";
	hisShowModal( urlStr,300,330,updateTemplateValueToControl,"" );
}


updateTemplateValueToControl = function (returnedValue) {
	var returnedValue = dialogWin.returnedValue;
	returnedValue = replaceAbstractNamesWithValues(returnedValue);
	var getAutoFillControl = document.forms[0].htmlPageControl_Event_Source_For_Autofill.value;
	var control = eval("document.forms[0]." + getAutoFillControl);
	if (control.createTextRange && control.caretPos) {
		var caretPos = control.caretPos;
		if(caretPos.text.length < 1) {
			eval("document.forms[0]." + getAutoFillControl + ".value = document.forms[0]." + getAutoFillControl + ".value + '" + returnedValue + "';");
		} else {
			caretPos.text = caretPos.text.charAt(caretPos.text.length - 1) == ' ' ? returnedValue : returnedValue;
		}
	} else {
		eval("document.forms[0]." + getAutoFillControl + ".value = document.forms[0]." + getAutoFillControl + ".value + '" + returnedValue + "';");
	}
}


function replaceAbstractNamesWithValues(valueString) {
	for(i=0;i < allTemplateNames.length;i++){
		var thisArrayName = allTemplateNames[i];
		var thisArrayValue = allTemplateValues[i];
		valueString = valueString.replace(thisArrayName,thisArrayValue);
	}
	return valueString;
}

function here(control) {
	var t = eval("document.forms[0]." + control + ".value;");
	alert(t);
}

//This function is to open the child forms of child form controls in a new window
function openChildForm(url,itemname) {
	
	window.open(url,"reportwindow","resizable=1,status=1,scrollbars=1,width=870,height=420,top=100,left=100");
	
}


//Set the name of periochart control currently opened in a public variable.
//This is to set the chart id back to the control...
var currentPerioChartControl = "";

//This function will open the dental perio chart jsp page for the user to edit/add perio chart,
//according to whether the chart id is present or not. This function will accept the chart id if exists, and
//the control name of the opening control of clinical form.
function openPerioChartWindow(mrdNumber,chartId,controlName){
	//Set the control name.
	currentPerioChartControl = controlName;
	
	//Now create the url string to open the perio chart jsp page
	var urlStr = "./Jsp/dental/PerioChart.jsp?action=DENTAL-INITIALISE_PERIO_CHART&id=" + mrdNumber;
	
	if(chartId.length>0){
		urlStr = urlStr + "&chartId=" + chartId;
	}
	
	//Show the jsp page in a new model dialog window
	hisShowModal( urlStr,900,350,setPerioChartId,"" );	
}


//This is the returning function of model dialog box opened for perio chart
//The returnedValue will be the chart id
setPerioChartId = function(returnedValue){
	returnedValue = dialogWin.returnedValue;
	eval("document.forms[0]." + currentPerioChartControl + ".value='" + returnedValue + "';");
}


//This function will open the dental chart jsp page for edit/add a dental chart according to whether
//the chart id is present or not.
function openDentalChartWindow(mrdNumber){
	//Create the url string of the dental jsp page
	var urlStr = "./Jsp/dental/DentalChart.jsp?action=DENTAL-INITIALISE&id=" + mrdNumber;
	
	//Open the jsp page in a new model dialog box.
	hisShowModal( urlStr,920,500,updateDentalChartIdToControl,"" );	
	//The function which will get the return value will be updateDentalChartIdToControl.
}

//This function will be called when the dental chart jsp page will return something, ie chart id
updateDentalChartIdToControl = function(returnedValue){
}


//Added by Prajeesh
//For evaluating Expression automatically in the inputbox.vm
function evaluateMathematicalExpression(expressionToBeSolved,itemName){
	if(expressionToBeSolved == "" || expressionToBeSolved == " " || expressionToBeSolved == null)
		return;

	var oprtrsExistArr = new Array("+","-","*","/","%","(",")");
	var indexes = "-1";

	for(var i=0;i<oprtrsExistArr.length;i++){
		var index = 0;
		do{
			var temp = expressionToBeSolved.substring(index,expressionToBeSolved.length);
			var newIndex = temp.indexOf(oprtrsExistArr[i]);
			index = index + newIndex;
			if(newIndex != -1)
				if(indexes == ""){
					indexes = index;
				}else{
					indexes = indexes + "," + index;
				}
			index = index + 1;

		}while(newIndex != -1);
	}
	var operators = "";
	expressionArr = expressionToBeSolved.split(''); 
	for(var i=0; i<expressionArr.length ; i++){
		for(var j=0; j<oprtrsExistArr.length ; j++){
			if(expressionArr[i] == oprtrsExistArr[j]){
				if(operators == "")
					operators = expressionArr[i];
				else 
					operators = operators + "," + expressionArr[i];
			}
		}

	}
	var arrOperators = operators.split(",");
	indexes = indexes + "," + expressionToBeSolved.length;
	var indexesArr = indexes.split(",");
	var sortedIndexesArr = indexesArr.sort(sortNumber);
	var operands = "";
	for(var cnt=0 ; cnt<sortedIndexesArr.length - 1 ; cnt++){
		var firstInd  = sortedIndexesArr[cnt];
		var secondInd = sortedIndexesArr[parseInt(cnt)+parseInt(1)];
		var startInd = parseInt(firstInd) + parseInt(1);
		if(cnt == 0)
			operands = expressionToBeSolved.substring(startInd,secondInd);
		else
			operands = operands + "," + expressionToBeSolved.substring(startInd,secondInd);
	}
	//alert("|"+operands+"|");
	var operandsArr = operands.split(",");

	var operatorVals = "";
	var result = expressionToBeSolved.replace(' ','');
	
	var validOperand = "false";
	for(var i=0;i<operandsArr.length;i++){
		var operand = operandsArr[i];
		if(operand != "" && operand != " " && operand != null && isNaN(operand)){
			var tempVal= eval("document.forms[0]."+operand+".value");
			if(isNaN(tempVal)){
				return;
			}
			var trimmedOperand = trimString(tempVal);
			if(trimmedOperand == ""){
				return;
			}
			validOperand = "true";
		}else{
			continue;
		}
		//trimString(tempVal)
		do{
			result = result.replace(operandsArr[i],tempVal)
			var index = result.indexOf(operand);
		}while(index != -1);		
	}
	if(validOperand == "true"){
		var resultValue = eval(result);
		eval("document.forms[0]."+itemName+".value = "+resultValue); 
		
	}else{
		return;
	}
}
//Added by Prajeesh for Trimming a String
function trimString (str) {
  while (str.charAt(0) == ' ')
    str = str.substring(1);
  while (str.charAt(str.length - 1) == ' ')
    str = str.substring(0, str.length - 1);
  return str;
}

//Added by Prajeesh for sort an Array
function sortNumber(a, b){
	return a - b
}

//Added by Prajeesh for dividing the large form in to pages
//function to set the properties of each division in the form
function setDivProperties(tableNo){
	var the_div = document.getElementById('divAdd'+tableNo);
	if(tableNo == "1"){
		the_div.style.display='block';
	}else{
		the_div.style.display='none';
	}
}

//Added by Prajeesh for dividing the large form in to pages
//function to add 'page' buttons to the form
var reg_names;
function addPageButtonsToTable (tableCount,regionNames){
	var browserName=navigator.appName; 
	if (browserName=="Netscape"){ 
		document.forms[0].noOfPages.value = tableCount;
	}else if (browserName=="Microsoft Internet Explorer"){
		document.getElementById('noOfPages').value = tableCount;
	}
	reg_names=regionNames;
	var regions = regionNames.split(",");
	var div1Obj = document.getElementById('pagingDiv');
	if(div1Obj){
		div1Obj.style.display='block';
	}
	
	var j=0;
	var tempRow;
	for(var i=0;i<parseInt(tableCount);i++){
		var onClickFun = "goToPage('"+(parseInt(i)+parseInt(1))+"','"+tableCount+"');"
		//for arranging 6 buttons in a row
		if(i%6 == 0){
			//alert("if");
			var tblObj = document.getElementById ('tblPaging');
			var lastRow = tblObj.rows.length;
			var newRow = tblObj.insertRow(lastRow);
			tempRow = newRow;
			
			j = 0;
			var newCell = newRow.insertCell(j);
			newCell.align='left';
		}else{
			//alert("else");
			j=j+1;
			var newCell = tempRow.insertCell(j);
			newCell.align='left';
		}
		//newCell.innerHTML = '<table width="125" height="10" align="left" border="0" background="/images/cdoc_page.gif" onClick='+onClickFun+'><tr align="left" ><td align="center"><b id=Text'+(parseInt(i)+parseInt(1))+'>Page'+(parseInt(i)+parseInt(1))+'</b></td></tr></table>';
		newCell.innerHTML = '<table width="125" height="10" align="left" border="0" background="/images/cdoc_page.gif" onClick='+onClickFun+'><tr align="left" ><td align="center"><b id=Text'+(parseInt(i)+parseInt(1))+'>'+regions[i]+'</b></td></tr></table>';
		
	}
	//only for making the alignment correct
		if(j<6){
			for(var k=j+1; k<=6 ; k++){
				var newCell = tempRow.insertCell(k);
				newCell.align='left';
				newCell.width='125';
			}
	}
	var theAnchorCnt = document.forms[0].form_anchor.value;
	if(theAnchorCnt.length > 0 && parseInt(theAnchorCnt) > 0 ){
		goToPage(parseInt(theAnchorCnt),tableCount);
	} else {
		chageFontColor(1,tableCount);
	}
	
}

//Added by Prajeesh for dividing the large form in to pages
//function to go to a particular page while clicking on a "Page" button
function goToPage(pageNo,tableCount){
	for(var i=0 ; i<tableCount ; i++){
		var the_div = document.getElementById('divAdd'+(parseInt(i)+parseInt(1)));
		if((parseInt(i)+parseInt(1)) == pageNo){
			the_div.style.display='block';
		}else{
			the_div.style.display='none';
		}
	}
	//for chnging the font color
	chageFontColor(pageNo,tableCount);
	document.forms[0].form_anchor.value=pageNo;
}
//Added by Prajeesh for dividing the large form in to pages
//function to change the button font color
function chageFontColor(pageNo,tableCount){
	for(var i=0 ; i<tableCount ; i++){
		var textId  = parseInt(i)+parseInt(1);
		var textObj = document.getElementById ('Text'+textId);
		textObj.style.color = '#000000';
	}
	var textObj = document.getElementById ('Text'+pageNo);
	textObj.style.color = '#cc0000';
}


//Added by Prajeesh for checking whether user wants to display all the pages of
//EMRForms or Paging is neede
function showAllPages(){
	var browserName=navigator.appName; 
	if (browserName=="Netscape"){ 
		var noOfPages = document.forms[0].noOfPages.value;
		var checkedOrNot = document.forms[0].noPaging.checked;
	}else if (browserName=="Microsoft Internet Explorer"){
		var noOfPages = document.getElementById('noOfPages').value;
		var checkedOrNot = document.getElementById('noPaging').checked;
	}
	if (checkedOrNot){
		var div1Obj = document.getElementById('pagingDiv');
		if(div1Obj){
			div1Obj.style.display='none';
		}
	
		for(var i=0 ; i<noOfPages ; i++){
			var the_div = document.getElementById('divAdd'+(parseInt(i)+parseInt(1)));
			the_div.style.display='block';
		}
	}else{
		var div1Obj = document.getElementById('pagingDiv');
		if(div1Obj){
			div1Obj.style.display='block';
		}
		for(var i=0 ; i<noOfPages ; i++){
			var the_div = document.getElementById('divAdd'+(parseInt(i)+parseInt(1)));
			if((parseInt(i)+parseInt(1)) == 1){
				the_div.style.display='block';
			}else{
				the_div.style.display='none';
			}
		}
		//for chnging the font color
		chageFontColor(1,noOfPages);
	}
}

//Added by Saju for using Ctrl + key to toggle between regions
var prevInd = 1;
var prevKeycode = -1;;
function ctrlTabPage(keyCode){
	var pageNo=0;
	if(document.forms[0].noOfPages)
	{
		var noOfPages = document.forms[0].noOfPages.value;
		var regions = reg_names.split(",");
		if (!(document.forms[0].noPaging.checked)){
			if(keyCode == 9){
				for(var i=0 ; i<= noOfPages ; i++){
					var the_div = document.getElementById('divAdd'+(parseInt(i)+parseInt(1)));
					if(the_div.style.display != "none"){
						pageNo = (parseInt(i)+parseInt(1)+parseInt(1));
						if(pageNo > noOfPages){
							pageNo=1;
						}
						goToPage(pageNo,noOfPages);
						break;
					}
				}

			}else{ 

				for(var i=0;i<parseInt(noOfPages);i++){
					if( keyCode == regions[i].substring(0,1).toString().charCodeAt(0)){
						var ind=i;
						prevKeycode = keyCode;
					}
					var temp_div = document.getElementById('divAdd'+(parseInt(i)+parseInt(1)));
						if(temp_div.style.display != "none"){
							var diplay_div = (parseInt(i)+parseInt(1));									
						}


				}
				if(diplay_div != prevInd){
					prevInd=0;
				}
				if(keyCode == prevKeycode){
					for(var j=prevInd;j<parseInt(noOfPages);j++){

						if( keyCode == regions[j].substring(0,1).toString().charCodeAt(0)){
							prevInd = parseInt(j)+parseInt(1);
							goToPage(parseInt(j)+parseInt(1),noOfPages);
							if(prevInd == ind+1 ){
								prevInd=0;
							}
							break;
						}

					}
				}		

			}
		}
	}
}

//Added by Manoj S on 28/01/2006...
//For latestform.vm
function getLatestFormWindow(patientId,documentId){
	if(documentId=='0'){
		alert("No document available now !!");
	} else {
		var action = 'EMR-CDOC_SHOW_DOCUMENT_IN_NEW_WINDOW_FOR_CHILDWIN';

		var urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
			"?action=" + action +"&patientId="+ patientId + "&documentId=" + documentId;

		if (browserName=="Netscape"){ 
			urlstring = "./Jsp/discharge/PopupClinicalWindow.jsp" + 
				"?action=" + action +"&patientId="+ patientId + "&documentId=" + documentId;
		} 

		hisShowModal (urlstring,950,550,doNothing,window);
	}
}


//Set the currently opened oral hygiene control name in a public variable.
//For setting the chart id back to the main page.
var currentOralHygieneControlName = "";

//Added by Manoj S on 29/01/2006...
//For oralhygienechart.vm
function oralHygieneChartWindow(mrdNumber,chartId,controlName){
	//Set the control name in the public variable.
	currentOralHygieneControlName = controlName;
	
	//Now create the url string to open the perio chart jsp page
	var urlStr = "./Jsp/dental/OralHygieneIndex.jsp?action=DENTAL-INITIALISE_ORAL_HYGIENE&id=" + mrdNumber;
	
	if(chartId.length>1){
		urlStr = urlStr + "&chartHeaderId=" + chartId;
	}
	
	//Show the jsp page in a new model dialog window
	hisShowModal( urlStr,900,700,setOralHygieneChartId,"" );
}


//Return function for Oral Hygiene chart modal window...
setOralHygieneChartId = function(returnedValue){
	returnedValue = dialogWin.returnedValue;
	eval("document.forms[0]." + currentOralHygieneControlName + ".value='" + returnedValue + "';");
}

//Added by Saju on 14/03/2006
//For creating diagnosis.vm

//Global variable for appending the diagnosis Ids for saving
var currentDiagnosisViewerControlName = "";
var checkEdit="";
function openAddEditDiagnosis(patientMrd,patientId,visitId,visitCode,encounterId,patientname,patientAge,sex,controlName){
	checkEdit = "new";
	//Set the control name in the public variable.
	currentDiagnosisViewerControlName = controlName;
	//Now create the url string to open the perio chart jsp page
	hisShowModal ("./Jsp/emr/DiagnosisMaster.jsp?action=EMR-DIAGNOSIS_INITILIZE&patientMrd=" +patientMrd + "&patientId="+ patientId +"&visitId=" +visitId+"&visitCode=" +visitCode+ "&encounterId=" +encounterId +"&patientname=" +patientname+ "&patientAge=" +patientAge+ "&sex="+sex,700,300,refreshPage,window);

}


refreshPage =function (lineItems) {
		
	if(checkEdit == "new"){
 		if(dialogWin.returnedValue){
 			lineItems = dialogWin.returnedValue;
 			}
 		if (!lineItems) {
 			return;
 		}
		if(eval("document.forms[0]." + currentDiagnosisViewerControlName + ".value") == ""){
			eval("document.forms[0]." + currentDiagnosisViewerControlName + ".value='" + lineItems + "';");
		
 		}else{

 			eval("document.forms[0]." + currentDiagnosisViewerControlName + ".value=document.forms[0]." + currentDiagnosisViewerControlName + ".value+','+lineItems");
 		}
 		//alert(eval("document.forms[0]."+currentDiagnosisViewerControlName+".value"));
 	}else if(checkEdit == "edit"){
 		return;
 	}
 }
 
function addDiagnosisToTable (  theValue, controlId, patientMrd,patientId,visitId,visitCode,encounterId,patientname,patientAge,sex ) {
		
		if ( theValue == "" ) return;
	
		var controlName = 'tbl' + controlId;
		var selObjName = 'selObj' + controlName;
		var tblObject = document.getElementById ( controlName );
	
		var strForHid = "";
	
		var strObjOne = new String();
		strObjOne = theValue;
		var arrString = new Array();
		arrString = strObjOne.split("|");
	
		for ( cnt =0; cnt < arrString.length; cnt++ ){
	
			var strObjTwo = new String();
			strObjTwo = arrString[cnt];
			var arrTmpStr = new Array();
			arrTmpStr = strObjTwo.split("@");
		
			var Id = arrTmpStr[0];
			var code = arrTmpStr[1];
			var desc = arrTmpStr[2];
			var create = arrTmpStr[3];
			var status = arrTmpStr[4];
		
			var cellNext;
			var row = tblObject.insertRow ( cnt + 1 );

		
			var cod = document.createTextNode ( code );
			cellNext = row.insertCell ( 0 );
			cellNext.setAttribute('align','left');
			cellNext.appendChild ( cod );

			var des = document.createTextNode ( desc );
			cellNext = row.insertCell ( 1 );
			cellNext.setAttribute('align','left');
			cellNext.appendChild ( des );
		
			var created = document.createTextNode ( create );
			cellNext = row.insertCell ( 2 );
			cellNext.setAttribute('align','left');
			cellNext.appendChild ( created );
		
			var stat = document.createTextNode ( status );
			cellNext = row.insertCell ( 3 );
			cellNext.setAttribute('align','left');
			cellNext.appendChild ( stat );
		
			var newCell4 = row.insertCell(4);
			var action = 'EMR-DIAGNOSIS_INITILIZE_MODIFY';
			var urlstring = "./Jsp/emr/DiagnosisMaster.jsp" + 
						"?Id=" + Id+"&action=" + action+
						"&patientMrd=" +patientMrd+ "&patientId="+ patientId +
						"&visitId=" +visitId+"&visitCode=" +visitCode+ 
						"&encounterId=" +encounterId +"&encounterCode=" +encounterId +
						"&patientname=" +patientname+ 
						"&patientAge=" +patientAge+ "&sex="+sex;
				
			var onClickFun = "editDiagnosis('"+urlstring+"');"
			newCell4.innerHTML = '<input type="button" value=" Edit " name= "btEdit" onClick="'+onClickFun+'" >';
		}
	
	 	
}

function closeMe(){
    	if ( window.opener && window.opener.refreshClinicalForm ) {
		window.opener.refreshClinicalForm();
	}
	window.close ();
}

function editDiagnosis(urlstring){
	
	checkEdit= "Edit";
	if (browserName=="Netscape"){ 
		hisShowModal(urlstring,700,300,doNothing,window);
	} else if (browserName=="Microsoft Internet Explorer"){ 
	
		//window.open(urlstring);
		
		hisShowModal(urlstring,700,300,doNothing,window);
		
	}
}


doNothing =function (lineItems) {
}



function addvalue(src,dest,control)
{
 	/* var destlen=dest.options.length;
	if(src.selectedIndex!=-1)
	{
		var ref=src.options[src.selectedIndex];
		var exists=false;
		for( var i=0;i<destlen;i++)
		{
			if(ref.value==dest.options[i].value)
			{
				exists=true;
				break;
			}

		}*/
	/*if(!exists)
		dest.options[destlen]=new Option(ref.text,ref.value);
	}*/
	for (var i=0; i<src.options.length; i++) 
	{
		var o = src.options[i];
		var exists=false;
		if(o.selected)
		{
			for( var j=0;j<dest.options.length;j++)
			{
				if(o.value==dest.options[j].value)
				{
					exists=true;
					break;
				}
			}
			if ( !exists) 
			{
				dest.options[dest.options.length] = new Option( o.text, o.value,false,false);
				src.options[i].selected = false;
				
			}
		}
	}
	
	control.value="";
	for(var i=0;i<dest.options.length;i++)
	{
		control.value=control.value + dest.options[i].value + ",";
	}
	control.value=control.value.substring(0,control.value.length-1);
	
}

function removevalue(src,control)
{	
	var count =src.options.length;
	for(var i=0; i<count; i++)
	{
		count =src.options.length;
		var o = src.options[i];
		if (o != null && o.selected) 
		{
			src.options[i] = null;
			i = 0;
		}
			
	}
	if(src.selectedIndex!=-1)
		src.options[src.selectedIndex]=null;
	control.value="";
	for(var i=0;i<src.options.length;i++)
	{
		control.value=control.value + src.options[i].value + ",";
	}
	control.value=control.value.substring(0,control.value.length-1);
	
}



function replaceTags()

{	
	var browserName=navigator.appName;
	if (browserName=="Netscape") 
	{
		
		var str = document.body.innerHTML;

		str = str.replace(/&amp;lt;/gi,"&lt;");
		str = str.replace(/&amp;gt;/gi,"&gt;");
		str = str.replace(/&lt;/gi,"<");
		str = str.replace(/&gt;/gi,">");
		str = str.replace(/&amp;nbsp;/gi,"&nbsp;");

		document.body.innerHTML = str;
		
	}


}

function openAllregies(patientMrd,patientId,visitId,visitCode,encounterId,patientname,patientAge,sex,controlName){
	checkEdit = "new";
	//Set the control name in the public variable.
	currentDiagnosisViewerControlName = controlName;
	//Now create the url string to open the perio chart jsp page
	hisShowModal ("./Jsp/mrdviewer/PatientAllergyViewer.jsp?action=EMR-MRDVIEWER_ALLERGY_VIEWER_SHOWALL&mrdNumber=" +patientMrd,700,300,refreshPage,window);

}


var curTableName = "";
var curAction = "";


function openProblemList(patientMrd,tableName,action){	
	curTableName = tableName;
	curAction = action;
	hisShowModal("./Jsp/emr/DiagnosisViewer.jsp?action=EMR-DIAGNOSISVIEWER_INITILIZE_TAB&mrdNumber=" +patientMrd+ "&closeBtnVisibility=TRUE&refreshStatus=TRUE",980,565,refreshDiagnosisWithTimeOut,window);

}
function selectProblemList(patientMrd,tableName,action){	
	curTableName = tableName;
	curAction = action;
	hisShowModal("./Jsp/emr/DiagnosisViewer.jsp?action=EMR-DIAGNOSISVIEWER_INITILIZE_TAB&mrdNumber=" +patientMrd+ "&closeBtnVisibility=TRUE&refreshStatus=TRUE&category=clinicalDiagnosis&caller="+tableName+"&selectBtnVisibility=TRUE",980,565,"",window);

}

function AddEditProblemList(patientMrd,patientId,visitId,visitCode,encounterId,patientname,patientAge,patientDob,sex,controlName,tableName,action){
	checkEdit = "new";
	curTableName = tableName;
	curAction = action;
	//Set the control name in the public variable.
	currentDiagnosisViewerControlName = controlName;
	//Now create the url string to open the perio chart jsp page
	hisShowModal ("./Jsp/emr/DiagnosisMaster.jsp?action=EMR-DIAGNOSIS_INITILIZE&mrdNumber=" +patientMrd + "&patientId="+ patientId +"&visitId=" +visitId+"&visitCode=" +visitCode+ "&encounterId=" +encounterId +"&patientname=" +patientname+ "&patientAge=" +patientAge+ "&patientDob=" +patientDob+ "&sex="+sex,640,330,refreshDiagnosisWithTimeOut,window);

}

/* Don't call ajax request function directly, as it will be terminated when the popup is closed in  
 * Firefox.Instead call the ajax function with settimeout.The settimeout function will be scheduled 
 * before the popup is closed and will be executed after the popup was closed. Without that 
 * settimeout in the parent window, the ajax request will be terminated when the popup is closed
 * and the ajax callback function (http_request.onreadystatechange)  will fail 
 */
refreshDiagnosisWithTimeOut = function (id){  
	
	window.setTimeout("refreshDiagnosis()", 0);
}



  function refreshDiagnosis(diagnosisId) {
	if(!dialogWin.returnedValue || dialogWin.returnedValue == "-9999"){
		return;
	}
	var tableId = curTableName;
	var action = curAction;
	curTableName= "";
	curAction = "";
	//For removing diagnosis rows from diagnosis control 
	//Passing table id for removing the rows
	removeElement(tableId);
	var className = "diagnosisUpdater";
	getChildWindowData(action,className,"");

}

function removeElement(tableId) {
	var tblObj = document.getElementById (tableId);
	var rows = tblObj.getElementsByTagName('tr');
	// delete table rows
    while(rows.length > 0){
		tblObj.deleteRow(rows.length-1);
	}
}



/* Don't call ajax request function directly, as it will be terminated when the popup is closed in  
 * Firefox.Instead call the ajax function with settimeout.The settimeout function will be scheduled 
 * before the popup is closed and will be executed after the popup was closed. Without that 
 * settimeout in the parent window, the ajax request will be terminated when the popup is closed
 * and the ajax callback function (http_request.onreadystatechange)  will fail 
 */
refreshServiceWithTimeOut = function (id){  
	window.setTimeout("getServiceDetails()",100);
}


getServiceDetails = function (lineitems) {

	var retVal = "";
	if(lineitems != null){
		retVal = lineitems;
	}else{
		retVal = dialogWin.returnedValue;
	}

	if(retVal != 1){
		return;
	}

	var action = "services";
	var className = "serviceUpdater";
	getChildWindowData(action,className,"");
	//var methodStr = "getChildWindowData('" + action + "','" + className + "','')";
	//window.setTimeout(methodStr,100); //timeout is set so that it works for tabbed pages - for PMT # 6870
}



/* Don't call ajax request function directly, as it will be terminated when the popup is closed in  
 * Firefox.Instead call the ajax function with settimeout.The settimeout function will be scheduled 
 * before the popup is closed and will be executed after the popup was closed. Without that 
 * settimeout in the parent window, the ajax request will be terminated when the popup is closed
 * and the ajax callback function (http_request.onreadystatechange)  will fail 
 */
refreshPrescriptionWithTimeOut = function (id){  		
    var str=id;
	window.setTimeout("getprescriptionDetails('"+str+"')", 0);
}


getprescriptionDetails = function (lineitems) {
	if(lineitems.medicationId == -999){
		return;
	}
	var action = "prescription";
	var className = "prescriptionUpdater";
	
	getChildWindowData(action,className,"");
}



refreshSensitivityResultWithTimeOut = function (org){  		
    var str=dialogWin.returnedValue;
    if(str =="" && org !=""){
       str=org;
    }
	window.setTimeout("getMibgSensitivityResultDetails('"+str+"')", 0);
}


getMibgSensitivityResultDetails = function (lineitems) {
	if(lineitems.id == -999){
		return;
	}
	var action = "sensitivity_comments";
	var className = "mibgSensitivityUpdater";
	getChildWindowData(action,className,lineitems);
}





function getChildWindowData(vmAction,className,resValue)
{	
	var docType = document.forms[0].docType.value;
	var formName = document.forms[0].formName.value;
	var patientId = document.forms[0].patientId.value;
	var visitId = document.forms[0].visitId.value;
	var encounterId = document.forms[0].encounterId.value;
	var orderId = document.forms[0].orderId.value;

	ajaxEngine.sendRequest( "CDOC-GET_AJAX_REQUEST","patientId=" + patientId ,"visitId=" + visitId,
							"encounterId="+encounterId,"formName="+formName,"orderId="+orderId,"resultValue="+resValue,
							"vmAction="+vmAction,"className="+className,	
							"action=CDOC-GET_AJAX_REQUEST" ); 
  
    
}


function ifOrderingTemplateDoSomething () {
	//If the opening is ordering template, disable the final approve at first time ...
	var docId = document.forms[0].documentId.value;
	var docType = document.forms[0].docType.value;
	if ( (docId=="" || docId==null || docId=="null") && (docType=="ORD" || parent.window.opener)){
		if(parent.document.forms[0]&& parent.document.forms[0].htmlPageTopContainer_childDocumentId){
			document.forms[0].fa.disabled = true;
		}
	}
	
	//If the document is having a document id then set in the parent form's hidden variable.
	if( (docId!="" && docId!=null && docId!="null") && (docType=="ORD" || parent.window.opener)) {
		if(parent.document.forms[0]&& parent.document.forms[0].htmlPageTopContainer_childDocumentId){
			parent.document.forms[0].htmlPageTopContainer_childDocumentId.value=docId;
			parent.document.forms[0].htmlPageTopContainer_childFormName.value=document.forms[0].formName.value;
		}
	}
}

function fileChooser(itemName){
	obj = document.imgapplet;
	currentlySelectedControl = itemName;
	if(imageUploadControls && imageUploadControls.length>0)
		imageUploadControls = imageUploadControls+currentlySelectedControl+",";
	else
		imageUploadControls = currentlySelectedControl+",";
	var result = obj.uploadFileSelector(true,".jpg",true);	
	//alert(result);
	if(result == "Restricted File Extension"){
		alert("Restricted File Extension");
		return;	
	} 
	if(result == null){
	   result = "Unknown Error";
	}
	
	if(result){
		addImageUrlsToTable("myTable"+itemName,result+"");		
		//deleteAllRows('myTable');
		//addToTable(mytool_array);
	}
}

function addImageUrlsToTable(tblName, itemValue) {
	//alert("Inside addChildFormsExistsToTable()");
	var tblObj = document.getElementById (tblName);
	var lastRow = tblObj.rows.length;
	var rowCnt = lastRow - 1;
	var arrString = itemValue.split("@");
	if (arrString.length > 0) {
		for (cnt =0; cnt < arrString.length; cnt++, lastRow++)
		{
			var strFname = arrString[cnt];
			var arrTmpStr = strFname.split("*");
			if(arrTmpStr.length > 0)
			{
				try { 
					var documentId = arrTmpStr[0];	
					if(documentId != "" && documentId != " " && documentId != null)
					{
						//alert("lastRow: "+lastRow);
						btn = '_' + lastRow;
						//var onClickFun = "editChildForm('"+documentId+"');"
						var onClickFun = "removeRowfromTable('"+lastRow+"');"
						//var onClickFun = "removeRowfromTable(this);"
						var newRow = tblObj.insertRow(lastRow); 

						var newCell0 = newRow.insertCell(0);
						var newCell1 = newRow.insertCell(1); 
						newCell0.innerHTML = arrTmpStr[0]; 
						//newCell1.innerHTML = '<input type="button" value=" Remove " name= btEdit'+arrTmpStr[1]+ ' onClick='+onClickFun+'>';
						newCell1.innerHTML = '<input type="button" value=" Remove " name= btEdit'+btn+ ' onClick='+onClickFun+'>';
						//newCell1.innerHTML = '<input type="button" value=" Remove " name= btEdit'+btn+ ' onClick="removeRowFromTable(this)">';
						
						if (document.forms[0].imageUploadTempFiles){
							document.forms[0].imageUploadTempFiles.value = document.forms[0].imageUploadTempFiles.value + "," + arrTmpStr[0];
						}
					}
				} 
				catch(ex) { 
					//document.getElementById(txtError); 
				}
			}
		}
	}
}


function removeRowfromTable(rowNo) {
	var tbl = document.getElementById("myTable"+currentlySelectedControl);	
	len = tbl.rows.length;
	for(i=1;i<len;i++){
		if(tbl.rows[i].childNodes[1].childNodes[0].name == 'btEdit_'+rowNo){
			tbl.deleteRow(i);
			break;
		}
	}
}

function loadImage(imgNames){
	var tbl = document.getElementById("myTable"+currentlySelectedControl);		
	imgObj = document.getElementById("imageTable");	
	if(imgNames.length > 0){
	for (cnt =0; cnt < imgNames.length; cnt++){	
		var newRow = imgObj.insertRow(cnt+1);
		var newCell0 = newRow.insertCell(0);
		newCell0.innerHTML = '<img name= pth '+cnt+ ' src="/images/'+imgNames[cnt]+'>';
	}
	}
}



/*********************

The following functions will make a clinical form dirty for prompting the user to save before moving to 
another page. These functions will call whenever the user press the key, or select combos etc.

*********************/

var _common_control_value_var = "";
var _common_control_obj;

function checkPageModification(evt) {
	//This function is to set the page dirty whenever any purposeful key press is happend.
	var keyCode = evt.keyCode ? evt.keyCode : evt.charCode ? evt.charCode : evt.which;
	if(keyCode > 0){
		document.forms[0].pageDirty.value="true";
	}
}

function checkPageModificationWithClick(evt){
	//If already the page is dirty, then do nothing.
	if(document.forms[0].pageDirty.value=="true"){
		return;
	}
	
	//Take the control type to a variable, as below, which will work both in IE and FireFox.
	//In the below code, the value "undefined" represents any unwanted string, to be neglected.
	var controltype = (evt ? 
		( evt.srcElement ? 
			( evt.srcElement.type ? evt.srcElement.type : "undefined" ) : 
			( evt.target ? 
				( evt.target.type ? evt.target.type : "undefined" ) : "undefined" ) ) : "undefined");
	
	//Set the page dirty field to true accordingly, like if the control's previous value and new 
	//	value are different in the case of text and textarea.
	//But in the case of controls like checkbox, select box, buttons etc, even a click can change the 
	//	value, hence we are making the page dirty on click.
	if(controltype != "undefined"){
		//Take the control to a variable, as below, which will work both in IE and FireFox.
		var controlField = (evt ? ( evt.srcElement ? evt.srcElement : ( evt.target ? evt.target : "undefined" ) ) : "undefined");
		if(controlField != "undefined"){
			if(controlField.name == "savebutton" || controlField.name == "btprovisional" || controlField.name == "printable" ||controlField.name == "htmlFinalApprove" || controlField.name == "closebutton" ){
				return;
			}
		}
		if(controltype == "text" || controltype == "textarea" || 
			controltype == "select-one"){
			//var obj = evt.srcElement;//Start Merge:This Segment taken from 4.0.3 BARC branch
			var obj = controlField;
			obj = evt ? evt : window.event

			if( obj != null ){
				_common_control_value_var = obj.value;
			}//End Merge
			_common_control_obj = obj;
			obj.onblur = function() {
				var newvalue = _common_control_obj.value;
				if(newvalue != _common_control_value_var){
					document.forms[0].pageDirty.value="true";
				}
			}
			obj.onchange = function() {
				var newvalue = _common_control_obj.value;
				if(newvalue != _common_control_value_var){
					document.forms[0].pageDirty.value="true";
				}
			}
		} else if(controltype == "checkbox"){
			//var obj = evt.srcElement;
			var obj = controlField;
			_common_control_value_var = obj.checked;
			obj.onchange = function() {
				document.forms[0].pageDirty.value="true";
			}
		} else if(controltype == "button"){
			document.forms[0].pageDirty.value="true";
		}
	}
}


/*********************

The following function are used for putting formatting in the textarea like, numbering the lines,
bulletting with 'o' and '*'. Added by Manoj on 28/10/2006. Please add relevant documentation if these 
are modifying.

*********************/

function runCommandForFormat (control,idx) {
	var reChar = "o";
	if(idx=='1'){
		reChar = "n";
	} else if(idx=='2'){
		reChar = "o";
	} else if(idx=='3'){
		reChar = "*";
	}

	var txtObject = "";
	eval("txtObject=document.forms[0]." + control + ";");

	var txt = txtObject.value;
	var selectedTxt = txt.substring(txtObject.selectionStart,txtObject.selectionEnd);

	if(selectedTxt.length < 1){
	} else {
		txt = selectedTxt;
	}

	txt = removeFormattings(txt);

	var allLines = new Array();
	var allTextTogether = "";
	allLines = txt.split("\n");
	for(i=0;i<allLines.length;i++){
		var thisTxt = allLines[i];
		j=i+1;
		if(thisTxt.length > 0){
			var appendChar = reChar + "\t";
			if(reChar=="n"){
				appendChar = "" + j + ")\t";
			}
			thisTxt = appendChar + thisTxt + "\n";
			allTextTogether = allTextTogether + thisTxt;
		}
	}

	if(selectedTxt.length < 1){
	} else {
		var allTxt = txtObject.value;
		allTxt = allTxt.replace(selectedTxt,allTextTogether);
		allTextTogether = allTxt;
	}

	txtObject.value = allTextTogether;
}

function deleteAllFormattings(control){
	var txtObject = "";
	eval("txtObject=document.forms[0]." + control + ";");
	var txt = "";
	var selectedTxt = txt.substring(txtObject.selectionStart,txtObject.selectionEnd);
	
	eval("txt = txtObject.value;");
	if(selectedTxt.length < 1){
	} else {
		txt = selectedTxt;
	}
	
	txt = removeFormattings(txt);
	
	if(selectedTxt.length < 1){
	} else {
		var allTxt = txtObject.value;
		allTxt = allTxt.replace(selectedTxt,txt);
		txt = allTxt;
	}
	txtObject.value = txt;
}

function removeFormattings(txt) {
	var allLines = new Array();
	var allTextTogether = "";
	allLines = txt.split("\n");
	for(i=0;i<allLines.length;i++){
		var thisTxt = allLines[i];
		j=i+1;
		if(thisTxt.length > 0){
		// bug fix # 15094 : added expr: [0-9][0-9][\)] in search condition to take care of 2 digit numbers also
			thisTxt = thisTxt.replace(/(\*|o|[0-9][\)]|[0-9][0-9][\)])[\t]/g,"") + "\n"; 		
			allTextTogether = allTextTogether + thisTxt;
		}
	}
	return allTextTogether;
}











/*******************

The following function is to check whether two dates are compared with the operator provided.
Eg. If the operator is ">", then it will check the date of object obj1 is ">" object obj2.
Modified by Sankaranarayanan on 25/09/2008, for implementing the condition in date fields.

*******************/

function checkDates(operator,obj1,obj1Caption,obj2,obj2Caption,splitOperator,dtFormat) {

	if(obj1 && obj2){}
	else{return true;}
	var dateOne = obj1.value;
	var dateTwo = obj2.value;
	
	var dateOneFlag = checkDateFormat(dateOne,dtFormat);
	
	var dateTwoFlag = checkDateFormat(dateTwo,dtFormat);
	
	
	if(dateOneFlag==false){
		alert("Please enter the date in " + '$dateFormat' + "format" );
		return false;
	}
	
	
	if(dateTwoFlag==false){
		alert("Please enter the date in " + '$dateFormat' + "format" );
		return false;
	}

	
	var compare = compareDatesfunc(dateOne, dateTwo);	
	return compare;
}











/*******************

The following function is to check whether two dates are compared with the operator provided.
Eg. If the operator is ">", then it will check the date of object obj1 is ">" object obj2.
Added by Manoj on 28/10/2006, for implementing the condition in date fields.

*******************/

function checkDates(operator,obj1,obj1Caption,obj2,obj2Caption,splitOperator) {
	if(obj1 && obj2){}
	else{return true;}
	var dateOne = obj1.value;
	var dateTwo = obj2.value;
	
	var dateOneArr = new Array();
	var dateTwoArr = new Array();
	dateOneArr = dateOne.split(splitOperator);
	if(dateOneArr.length == 0 || dateOneArr.length == 1 || dateOneArr.length == 2){
		alert("The from date should be of the form dd/MM/yyyy");
		return false;
	}
	dateTwoArr = dateTwo.split("/");
	if(dateTwoArr.length == 0 || dateTwoArr.length == 1 || dateTwoArr.length == 2){
		alert("The to date should be of the form dd/MM/yyyy");
		return false;
	}
	if(dateOneArr[0].length > 2 || dateOneArr[1].length > 2 || dateOneArr[2].length != 4 ||
		dateTwoArr[0].length > 2 || dateTwoArr[1].length > 2 || dateTwoArr[2].length != 4){
		alert("The dates are not of the format dd/MM/yyyy");
		return false;
	}
	
	var compare = compareDatesfunc(dateOne, dateTwo);	
	
     return compare;
     
	/*if(eval("parseInt(dateTwoArr[2]) " + operator + " parseInt(dateOneArr[2])")){
		return true;
		
	}else if(eval("parseInt(dateTwoArr[1]) " + operator + " parseInt(dateOneArr[1])")){
		return true;
	}
	if(eval("parseInt(dateTwoArr[0]) " + operator + " parseInt(dateOneArr[0])")){
		
		return true;
	}
	alert("The date " + obj2Caption + " should be greater than " + obj1Caption )*/
	return true;
}




//Created By  sankaranarayanan on 23/09/2006 for checking mandatory controles
function checkMandatoryControles(){

	var choiceElementStr = document.forms[0].mandatoryControl.value;
	var choiceElementArray = new Array();
	var choiceElementSubStrArray = new Array();
	choiceElementArray = choiceElementStr.split(",");
	var len = choiceElementArray.length;
	var choiceElement = "";
	var choiceElementValue ="";
	var flag = false;
	for(var i=0;i<len;i++ ){

		choiceElement = choiceElementArray[i];
		choiceElementSubStrArray = choiceElement.split("|");
		var lenSubStr = choiceElementSubStrArray.length;
		if(lenSubStr > 1 ){

			  for(var j=0; j<choiceElementSubStrArray.length;j++){

			    var choiceElementSubStr = choiceElementSubStrArray[j];
			    var choiceElementSubStrValue = eval("document.forms[0]." + choiceElementSubStr +".value");
			     if( choiceElementSubStrValue.length > 0 ){
				flag = true;
				break;
			     }else {
				flag = false;
			    }
			 }//inner for loop

			 if(flag == false){
				alert("Please fill up mandatory columns...");
				return flag;

			 }



		 }else{
			choiceElementValue = eval("document.forms[0]." + choiceElement +".value");
			if(choiceElementValue == null || choiceElementValue == "" || choiceElementValue == " "){
				alert("Please fill up mandatory columns...");
				flag = false;
				return flag;
			}

		}

	}//outer for loop
			

}// end function checkMandatoryControles



function getRichTextValues(){

	if(richTextNames && richTextNames.length>0){
		var arrrichTextNames = richTextNames.split(",");

		if(arrrichTextNames && arrrichTextNames.length >0){
			for(var p=0; p<arrrichTextNames.length; p++){

				var richTextName = arrrichTextNames[p];
				var richEditValue = document.getElementById("edit_"+richTextName).contentWindow.document.body.innerHTML;      									
				eval("document.forms[0]." + richTextName + ".value = richEditValue;");
				

			}
		}
	}	

}




/**********************************************************************/
/*Added By sankaranarayanan on 7/12/2006
/*Function name :isDatefunc(s) */
/*Usage of this function :To check s is a valid format */
/*Input parameter required:s=input string */
/*Return value :if is a valid date return 1 */
/* else return 0 */
/*Function required :isPositiveIntegerfunc() */
/**********************************************************************/
function checkDateFormat(s)
{	
	var a1=s.split("/");
	var e=true;
	if ((a1.length!=3) )
	{
	e=false;
	}
	else
	{if (a1.length==3)
	var na=a1;
	if (isPositiveIntegerfunc(na[0]) && isPositiveIntegerfunc(na[1]) && isPositiveIntegerfunc(na[2]))
	{
	var d=na[0],m=na[1];
	var y=na[2];
	if (((e) && (y<1000)||y.length>4))
	e=false
	if (e)
	{
	v=new Date(m+"/"+d+"/"+y);
	if (v.getMonthClient()!=m-1)
	e=false;
	}
	}
	else
	{
	e=false;
	}
	}
	return e;
}

function checkDateFormat(s,f)
{	
	var a1=s.split("/");
	var e=true;
	if ((a1.length!=3) )
	{
		e=false;
	}
	else
	{
		if (a1.length==3)
			var na=a1;
		if (isPositiveIntegerfunc(na[0]) && isPositiveIntegerfunc(na[1]) && isPositiveIntegerfunc(na[2]))
		{
			if(f=="MM/dd/yyyy"){
				var d=na[1],m=na[0];
				var y=na[2];
			} else {
				var d=na[0],m=na[1];
				var y=na[2];
			}
			if (((e) && (y<1000)||y.length>4))
				e=false
			if (e)
			{
				v=new Date(m+"/"+d+"/"+y);
				if (v.getMonthClient()!=m-1)
					e=false;
			}
		}
		else
		{
			e=false;
		}
	}
	return e;
}



/*************************************************************************/
/**Added by sankaranarayanan on 7/12/2006
/*Function name :isPositiveInteger(theString) */
/*Usage of this function :test for an +ve integer */
/*Input parameter required:thedata=string for test whether is +ve integer*/
/*Return value :if is +ve integer,return true */
/* else return false */
/*function require :isDigit */
/*************************************************************************/
function isPositiveIntegerfunc(theString)
{
	var theData = new String(theString)

	if (!isDigitfunc(theData.charAt(0)))
	if (!(theData.charAt(0)== '+'))
	return false

	for (var i = 1; i < theData.length; i++)
	if (!isDigitfunc(theData.charAt(i)))
	return false
	return true
}




/**********************************************************************/
/*Function name :isDigit(theDigit) */
/*Usage of this function :test for an digit */
/*Input parameter required:thedata=string for test whether is digit */
/*Return value :if is digit,return true */
/* else return false */
/**********************************************************************/
function isDigitfunc(theDigit)
{
	var digitArray = new Array('0','1','2','3','4','5','6','7','8','9'),j;

	for (j = 0; j < digitArray.length; j++)
	{if (theDigit == digitArray[j])
	return true
	}
	return false

}



/**********************************************************************/
/*Added by Sankaranarayanan on 8/12/2006
/*Function name :openRichEditReport() */
/**********************************************************************/

function openRichEditReport(){
	setCdocPageAction ( "CDOC-PRINT_READY_RICH_EDIT_DOCUMENT" );
	document.forms[0].target = "printWindow";
	document.forms[0].submit();


}



function openRichEditReportAfterApprove(){

	var tfmsg = confirm ( "Are you sure to approve this document ?" );
	if ( tfmsg == true ) {//Start Merge:This Segment taken from 4.0.3 BARC branch
		getRichTextValues();//End Merge
		document.forms[0].normalSave.value="Yes";
		document.forms[0].target = "";
		disableSubmitButtons();
		//document.forms[0].Submit2.value = "Final Approve";
		if(document.forms[0].docType.value == "DS")
		{
			document.forms[0].target = "_newWindow";
		}
		setCdocPageAction ( "CDOC-FINAL_APPROVE_RICH_EDIT_DOCUMENT" );
		document.forms[0].submit();
	}


}

//Start Merge:This Segment taken from 4.0.3 BARC branch
function isNegativeNumber(theString){
	
	var theData = new String(theString);
	
	if (theData.charAt(0)== '-'){
		return true;
	}
	return false;

}
//End Merge
/*************************************************************************/
/**Added by sankaranarayanan on 26/12/2006
/*Function name :createTable(heade,detailInfo) */
/*Usage of this function :create dynamic tables */
/*************************************************************************/

//function createTable(headerInfo,controlId){

function createTable(tblInfo,controlId){
	/*var headerLen = tblInfo.indexOf('|');
	
	var headerStr = tblInfo.substring(0,headerLen);
	
	var headrArr = headerStr.split(",");
	var col = headrArr.length;
	var allRecords = tblInfo.substring(headerLen + 1,tblInfo.length);*/
	

	var arrOne = new Array();
	arrOne = tblInfo.split("~");

	var rowLen = arrOne.length;
	
	var arrTwo = new Array();
	
	
	
	var controlName = 'tbl' + controlId;
	var tblObject = document.getElementById ( controlName );
	
	for( cnt=0; cnt<arrOne.length; cnt++ ){
		
		var data = arrOne[cnt];
		arrTwo = data.split("@");
		var k=0;
		var cellNext;
		var row = tblObject.insertRow(cnt);
		
		
		for(j=0;j<arrTwo.length;j++ ){
			var txt1 = document.createTextNode ( arrTwo[k] );
			cellNext = row.insertCell ( k );
			cellNext.setAttribute('align','left');
			cellNext.appendChild ( txt1 );
			k++;
		
	
		}
	}

		



}



// Tis function called from ClinicalHtmlReportServelet
  function showPrintableReport(docId,docType,formPrivacy){
	urlString = ".../../StaffProfileServlet?action=CDOC-PRINT_PDF_DOCUMENT&documentId=" +docId+"&docType="+docType+"&formPrivacy="+formPrivacy;
	window.open(urlString,null,"scrollbars =1,height=650,width=850");


}
  
  
  
  function printReport(){
	  
	    document.forms[0].target = "_blank";
		setCdocPageAction ( "CDOC-PRINT_PDF_DOCUMENT" );
		document.forms[0].submit();

	  
  }
  
 
 /*
	function viewReport() {

	document.forms[0].target = "_blank";
	setCdocPageAction ( "CDOC-PRINT_READY_DOCUMENT" );
	document.forms[0].submit();

	}*/





function viewReport(docId){
	urlString = ".../../StaffProfileServlet?action=CDOC-PRINT_READY_DOCUMENT&documentId=" +docId;
	window.open(urlString,"_newWindow","scrollbars =1,height=650,width=850");


}
 
 
 

//PMT No: 2505. Added by saju for displaying user details of clinical form saved / approved
 function addAccessLogDetailsToTable(  theValue, controlId ){
 	
 	var controlName = 'tbl' + controlId;
	var selObjName = 'selObj' + controlName;
	var tblObject = document.getElementById ( controlName );
 	var cellNext;
	var row = tblObject.insertRow ( 0 );
	var txt1 = document.createTextNode ( "User Login" );
	cellNext = row.insertCell ( 0 );
	cellNext.setAttribute('align','left');
	cellNext.style.cssText = 'font-weight:bold;';
	cellNext.setAttribute('style','font-weight:bold;');
	cellNext.appendChild ( txt1 );

	var txt2 = document.createTextNode ( "User Name" );
	cellNext = row.insertCell ( 1 );
	cellNext.setAttribute('align','left');
	cellNext.style.cssText = 'font-weight:bold;';
	cellNext.setAttribute('style','font-weight:bold;');
	cellNext.appendChild ( txt2 );

	var txt3 = document.createTextNode ( "Date of Save/Approve" );
	cellNext = row.insertCell ( 2 );
	cellNext.setAttribute('align','left');
	cellNext.style.cssText = 'font-weight:bold;';
	cellNext.setAttribute('style','font-weight:bold;');
	cellNext.appendChild ( txt3 );
	
	if ( theValue == "" ) return;

	var strObjOne = new String();
	strObjOne = theValue;
	var arrString = new Array();
	arrString = strObjOne.split("~");
	
        for ( cnt =0; cnt < arrString.length; cnt++ ){
		 	var strObjTwo = new String();
			strObjTwo = arrString[cnt];
			var arrTmpStr = new Array();
		 	arrTmpStr = strObjTwo.split("@");
		
			var userLogin = arrTmpStr[0];
		   	var userName=arrTmpStr[1];
			var dateTime=arrTmpStr[2];    
			
			var cellNext;
			var row = tblObject.insertRow ( cnt + 1 );
	
			var txt1 = document.createTextNode ( userLogin );
			cellNext = row.insertCell ( 0 );
			cellNext.setAttribute('align','left');
			cellNext.appendChild ( txt1 );
		
			var txt2 = document.createTextNode ( userName );
			cellNext = row.insertCell ( 1 );
			cellNext.setAttribute('align','left');
			cellNext.appendChild ( txt2 );
	
			var txt3 = document.createTextNode ( dateTime );
			cellNext = row.insertCell ( 2 );
			cellNext.setAttribute('align','left');
			cellNext.appendChild ( txt3 );

	}     
	        
 }

function checkSubFormMandatoryControles(){

	var choiceElementStr = window.frames['tabImages'].document.forms[0].mandatoryControl.value;
	var choiceElementArray = new Array();
	var choiceElementSubStrArray = new Array();
	choiceElementArray = choiceElementStr.split(",");
	var len = choiceElementArray.length;
	var choiceElement = "";
	var choiceElementValue ="";
	var flag = false;
	for(var i=0;i<len;i++ ){

		choiceElement = choiceElementArray[i];
		choiceElementSubStrArray = choiceElement.split("|");
		var lenSubStr = choiceElementSubStrArray.length;
		if(lenSubStr > 1 ){

			  for(var j=0; j<choiceElementSubStrArray.length;j++){

			    var choiceElementSubStr = choiceElementSubStrArray[j];
			    var choiceElementSubStrValue = eval("window.frames['tabImages'].document.forms[0]." + choiceElementSubStr +".value");
			     if( choiceElementSubStrValue.length > 0 ){
				flag = true;
				break;
			     }else {
				flag = false;
			    }
			 }//inner for loop

			 if(flag == false){
				alert("Please fill up mandatory columns...");
				return flag;

			 }



		 }else{
			choiceElementValue = eval("window.frames['tabImages'].document.forms[0]." + choiceElement +".value");
			if(choiceElementValue == null || choiceElementValue == "" || choiceElementValue == " "){
				alert("Please fill up mandatory columns...");
				flag = false;
				return flag;
			}

		}

	}//outer for loop
			

}

//========================= Created By Rajitha.k , 18-01-2011 ====================================
    
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
	//alert(rowCount);		
	var TabElements = tabledata.split(",||");	
	var colCount = (TabElements.length)
	//alert(rowCount);

	var i=0;		
	if (editRow>0){	
		//alert("Edit :1");
		var editRowcells=document.getElementById(tabname).rows[editRow].cells;		
		//alert("Edit :2");
		for (i=0;i<colCount;i++){
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
		}

		var editbutton='<input type="BUTTON" value="Edit" name="EDIT'+ documentId + '" ONCLICK="showchildform('+"'"+formname+ "'" + ',' + "'" + '' + documentId + "'" +','+ "'" + '' + entitytype + "'" + ');" />';		
		//alert(editbutton);
		var y=x.insertCell(i);		
		y.innerHTML=editbutton;

		var deletebutton='<input type="BUTTON" value="Delete" name="DELETE'+ documentId + '" ONCLICK="deleteRow(this.parentNode.parentNode.rowIndex,' + "'" +tabname+ "'"+');" />';	
		//alert(deletebutton);
		var y=x.insertCell(i+1);		
		y.innerHTML=deletebutton;
	}	
	generateIds(tabname);
	hideIdColumn(tabname);
	//alert("end");
	
}

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
				alert("Please Fill Mandatory Values!!!");
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
		if (editRow[0].innerHTML==documentId){		
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
	    field.style.backgroundColor = "red";
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
               field.style.backgroundColor = "red";
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
	     field.style.backgroundColor = "red";
	}
	else
	{
	     field.style.backgroundColor = "white";
        }
	return check;
}

function submitform_staff_profile_qualification_v0(){
     var frmelement=document.getElementById("staff_profile_qualification_v0");
     if(validateForm(frmelement) && isMandatoryDataExists(frmelement)=="1"){
          docHtmlSave();
     }	
}

/*================================ End of added on 01-03-2011 ===================================*/

/*============================ Added on 03-03-2011 ==========================================*/

function isPhoto(field) {
	var check = true;
	var str=field.value;
	if(!/(\.bmp|\.gif|\.jpg|\.jpeg)$/i.test(str)) {
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
	     field.style.backgroundColor = "red";
	}
	else
	{
	     field.style.backgroundColor = "white";
	}
	return check;
}

/*================================ End of added on 03-03-2011 ===================================*/