/**
 * @(#) manageExternalExaminarDetailAction.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/warning.png")]private var  warningIcon:Class;
import common.commonFunction;

import mx.controls.dataGridClasses.DataGridColumn;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

public var infoObject:Object={};
public var s6:Object={};

var param:Object={};
/**
 * This function calls on Creation complete of the interface
 */ 
public function setFirstFocous():void
{
		
	enterEmployeeCode.text="";
	enterEmployeeName.text="";
	editButton.enabled=false;
	deleteButton.enabled=false;
	employeeDetailsgrid.dataProvider=null;
	employeeDetailsgrid.dataProvider.refresh();
	
	enterEmployeeCode.setFocus();
	
	
}
/**
 * function for showing Examinar Detail in the grid
 */ 
public function showdataingrid():void
{		
		infoObject["userId"] = new Date;
		
		if(enterEmployeeCode!=null){			
			infoObject["extCode"] = enterEmployeeCode.text+"%";			
		}else{			
			infoObject["extCode"] = "%";			
		}
						
			if((enterEmployeeName!=null)){			
			
			infoObject["extName"] = enterEmployeeName.text+"%";			
		}else{
			
			infoObject["extName"] = "%";	
		}
			
				
		getEmpDetails.send(infoObject);	

}
/**
 * Function for checking entries in the fields
 */ 
public function checkAll3FieldsToEntered():Boolean
{
	if(checkenterForOnlyCharAndNumber(enterEmployeeName.text))
       {
		if(checkenterForOnlyCharAndNumber(enterEmployeeCode.text))
        {
        	
		  return true;
 	    }
       
       else
       {
       	enterEmployeeCode.errorString=commonFunction.getMessages('enterOnlyNo.Orchar');
       }
       }
       else
       {
       	enterEmployeeName.errorString=commonFunction.getMessages('enterOnlyNo.Orchar');
       }
Alert.show((commonFunction.getMessages('pleaseCheckFieldsinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
return false
}


public function checkenterForOnlyCharAndNumber(s:String):Boolean
{
    for (var i:int=0;i<s.length;i++)
    {
   	if(!((s.charCodeAt(i) > 64) && (s.charCodeAt(i) < 91)))
    {
   	 if (!((s.charCodeAt(i) > 96) && (s.charCodeAt(i) < 123)))
   	 {
       if (!((s.charCodeAt(i) > 47) && (s.charCodeAt(i) < 58)))
       {
       	return false;
       } 
     }
    }
   }
return true;
}

/**
 * The function retrives the list of Examinar Names  
 * 
 * */
[Bindable]
public var details: XML;
public var detailslist:ArrayCollection;
public function EmployeeName(row:Object,col:DataGridColumn):String{
	
	return row.employeeFirstName+" "+row.employeeMiddleName+" "+row.employeeLastName;
}

/**
 * this function retrieves the list of the Examinar Basic Details
 */ 
public function onSuccess(event:ResultEvent):void{
   details=event.result as XML;
		if(details.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
           	    var url:String="";
           	    url=commonFunction.getConstants('navigateHome');
	   var urlRequest:URLRequest=new URLRequest(url);
	   urlRequest.method=URLRequestMethod.POST;
	    navigateToURL(urlRequest,"_self");
            }
	detailslist =new ArrayCollection();
	for each (var o:Object in details.Details){

	detailslist.addItem({select:false,employeeCode:o.ExaminarId,prefix:o.Prefix,employeeFirstName:o.ExaminarFirstName,
			employeeMiddleName:o.ExaminarMiddleName,employeeLastName:o.ExaminarLastName,
			designation:o.designation,gender:o.gender,department:o.Department,collegeName:o.CollegeName,
            collegeAddress:o.CollegeAddress});
		
	}		
		employeeDetailsgrid.dataProvider = detailslist;
		
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
}


import mx.collections.ArrayCollection;
import mx.controls.*;
import mx.events.*;
import mx.managers.PopUpManager;
import employeeMaster.employeePopUp;
import mx.formatters.DateFormatter;
import externalExaminarCourse.popUpExaminarDetail;

/**
 *This function is called on the click of the edit button
 */  
private function editEmployeeDetailPopup():void {
	
	
	
var s3:ArrayCollection=commonFunction.getSelectedRowData(employeeDetailsgrid);
            	s6=s3.getItemAt(0);
    
					
	infoObject["examinarCode"] =s6.employeeCode; 
	
	getEmpAddressDetails.send(infoObject);	
	
}

public var addressDetails:XML;
public var addressDetailsList:ArrayCollection ;
/**
 * This function retrieves the list of the Exminar Personal Details
 */ 
public function onEditSuccess(event:ResultEvent):void{
	addressDetails=event.result as XML;	
//		if(addressDetails.sessionConfirm == true)
//             {
//            Alert.show(resourceManager.getString('Messages','sessionInactive'));
//           	    var url:String="";
//           	    url=commonFunction.getConstants('navigateHome');
//	   var urlRequest:URLRequest=new URLRequest(url);
//	   urlRequest.method=URLRequestMethod.POST;
//	    navigateToURL(urlRequest,"_self");
//            }	
	addressDetailsList =new ArrayCollection();
	
	
	for each (var o:Object in addressDetails.Details){
		
		addressDetailsList.addItem({addressKey:o.addressKey,permanentAddress:o.permanentAddress,
			permanentCity:o.permanentCity,permanentState:o.permanentState,
			permanentPinCode:o.permanentPinCode,officePhoneNumber:o.officePhoneNumber,
			faxNumber:o.faxNumber,homePhoneNumber:o.homePhoneNumber});
		
		
	}
	
	var popUpWindow:popUpExaminarDetail=popUpExaminarDetail(PopUpManager.createPopUp(this,popUpExaminarDetail,true));
	
	var selectedExaminar:ArrayCollection=commonFunction.getSelectedRowData(employeeDetailsgrid);
var tempGen:String="";		
popUpWindow.examinarIdPop=selectedExaminar.getItemAt(0).employeeCode;
popUpWindow.prefixPop=selectedExaminar.getItemAt(0).prefix;
popUpWindow.studentfirstnametext.text=selectedExaminar.getItemAt(0).employeeFirstName;
popUpWindow.studentmiddlenametext.text=selectedExaminar.getItemAt(0).employeeMiddleName;
popUpWindow.studentlastnametext.text=selectedExaminar.getItemAt(0).employeeLastName;
if(selectedExaminar.getItemAt(0).gender=='M'){
	tempGen="Male";
}
else{
	tempGen="Female";
	
}
popUpWindow.genderPop=tempGen;
popUpWindow.designation.text =selectedExaminar.getItemAt(0).designation;
popUpWindow.department.text=selectedExaminar.getItemAt(0).department;
popUpWindow.colgName.text=selectedExaminar.getItemAt(0).collegeName;
popUpWindow.colgAdd.text=selectedExaminar.getItemAt(0).collegeAddress;
popUpWindow.editPop=editButton;
popUpWindow.deletePop=deleteButton;
popUpWindow.refresh=showdataingrid;



//popUpWindow.buttonFunction = setFirstFocous;

	for each(var i:Object in addressDetailsList){
	
	if(i.addressKey=="PER"){
		
		
		popUpWindow.permanentAddressField.addressLine1.text=i.permanentAddress;
		popUpWindow.permCity = i.permanentCity;
		popUpWindow.permState = i.permanentState;
		popUpWindow.permanentAddressField.pinField.text=i.permanentPinCode;
		
		popUpWindow.officePhoneField.text=i.officePhoneNumber;
		popUpWindow.mobilePhoneField.text=i.homePhoneNumber;
		popUpWindow.faxPhoneField.text=i.faxNumber;
		
	}else if(i.addressKey=="COR"){
		
		popUpWindow.corspondenceAddressField.addressLine1.text=i.permanentAddress;
		popUpWindow.corsCity=i.permanentCity;
		popUpWindow.corsState=i.permanentState;
		popUpWindow.corspondenceAddressField.pinField.text=i.permanentPinCode;
		
	}
	}
	//setFields(addressDetailsList,s6,popUpWindow);
	popUpWindow.height=this.parentDocument.loaderCanvas.height;
	//popUpWindow.arr=addressDetailsList;
	//popUpWindow.o=s6;
	//popUpWindow.pop=popUpWindow;
	//popUpWindow.resetFunction=setFields;
	//popUpWindow.parentObject=addressDetailsList.getItemAt(0);
	PopUpManager.centerPopUp(popUpWindow);
	popUpWindow.setFocus();	
}


/**
 * This function called on the click of the cancel button 
 */ 

public function cancelFunction():void
{
	this.parentDocument.loaderCanvas.removeAllChildren();
}

public function onDelete():void{
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteOrNot,questionIcon);
}

/**
 * Method to confirm deletion of record
 **/
public function deleteOrNot(event:CloseEvent):void
{   	
	if(event.detail==1){
	var arry=employeeDetailsgrid.dataProvider as ArrayCollection;
	
   param["recordArrayCol"]="";

	
	for each(var o:Object in arry){
		if(o.select==true){
			 param["recordArrayCol"]+=o.employeeCode +"|";
	     
		}
	}
deleteExaminarRecord.send(param);

	}
}

/**
 * Calls on the success of the deleteExaminarRecord Http Service
 */ 
public function ondeleteSuccess(event:ResultEvent):void{
	var deletexml:XML=event.result as XML;
			if(deletexml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
           	    var url:String="";
           	    url=commonFunction.getConstants('navigateHome');
	   var urlRequest:URLRequest=new URLRequest(url);
	   urlRequest.method=URLRequestMethod.POST;
	    navigateToURL(urlRequest,"_self");
            }
	if(deletexml.info==true){
	Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
	showdataingrid();
}
else if(deletexml.info==false){
	Alert.show(commonFunction.getMessages('courseIsAssignedToExaminer'),commonFunction.getMessages('warning'),0,null,null,warningIcon);
	showdataingrid();	
}
}