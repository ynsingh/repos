/**
 * @(#) manageEmployeeMasterAction.java
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

import common.commonFunction;

import flash.net.sendToURL;

import mx.controls.dataGridClasses.DataGridColumn;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

public var infoObject:Object={};
public var s6:Object={};



public function setFirstFocous():void
{
		
	enterEmployeeCode.text="";
	enterEmployeeName.text="";
	editButton.enabled=false;
	
	employeeDetailsgrid.dataProvider=null;
	employeeDetailsgrid.dataProvider.refresh();
	
	enterEmployeeCode.setFocus();
	
	
}

public function showdataingrid():void
{		
		infoObject["userId"] = new Date;
		
		if(enterEmployeeCode!=null){			
			infoObject["empCode"] = enterEmployeeCode.text+"%";			
		}else{			
			infoObject["empCode"] = "%";			
		}
						
			if((enterEmployeeName!=null)){			
			
			infoObject["empName"] = enterEmployeeName.text+"%";			
		}else{
			
			infoObject["empName"] = "%";	
		}
			
				
		getEmpDetails.send(infoObject);	

}

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
 * The function retrives the list of programs for the 
 * concerned university idSS
 * */
[Bindable]
public var details: XML;
public var detailslist:ArrayCollection;
public function EmployeeName(row:Object,col:DataGridColumn):String{
	
	return row.employeeFirstName+" "+row.employeeMiddleName+" "+row.employeeLastName;
}
public function onSuccess(event:ResultEvent):void{
	
	details=event.result as XML;
	
	
	detailslist =new ArrayCollection();
	
	for each (var o:Object in details.Details){
		
		detailslist.addItem({select:false,parentEntity:o.parentEntity,employeeFirstName:o.employeeFirstName,
			employeeMiddleName:o.employeeMiddleName,employeeLastName:o.employeeLastName,
			primaryEmailId:o.primaryEmailId,secondryEmailId:o.secondryEmailId,
			qualification:o.qualification,designation:o.designation,
			dateOfBirth:o.dateOfBirth,dateOfJoining:o.dateOfJoining,
			gender:o.gender,category:o.category,
			employeeCode:o.employeeCode,employeeStatus:o.employeeStatus,postGraduate:o.postGraduate,netQualified:o.netQualified,
			handicapped:o.handicapped,minority:o.minority,pensionCode:o.pensionCode,minorityFlag:o.minorityFlag});
		
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

private function editEmployeeDetailPopup():void {
	
	
	
var s3:ArrayCollection=commonFunction.getSelectedRowData(employeeDetailsgrid);
            	s6=s3.getItemAt(0);
    
					
	infoObject["employeeCode"] =s6.employeeCode; 
	
	getEmpAddressDetails.send(infoObject);	
	
}

public var addressDetails:XML;
public var addressDetailsList:ArrayCollection ;
public function onEditSuccess(event:ResultEvent):void{
	
	addressDetails=event.result as XML;	
	
	addressDetailsList =new ArrayCollection();
	
	
	for each (var o:Object in addressDetails.Details){
		
		addressDetailsList.addItem({addressKey:o.addressKey,permanentAddress:o.permanentAddress,
			permanentCity:o.permanentCity,permanentState:o.permanentState,
			permanentPinCode:o.permanentPinCode,officePhoneNumber:o.officePhoneNumber,
			otherPhoneNumber:o.otherPhoneNumber,faxNumber:o.faxNumber,
			homePhoneNumber:o.homePhoneNumber});
		
		
	}
	
	var popUpWindow:employeePopUp=employeePopUp(PopUpManager.createPopUp(this,employeePopUp,true));
	
	setFields(addressDetailsList,s6,popUpWindow);
	popUpWindow.height=this.parentDocument.loaderCanvas.height;
	popUpWindow.arr=addressDetailsList;
	popUpWindow.o=s6;
	popUpWindow.pop=popUpWindow;
	popUpWindow.resetFunction=setFields;
	popUpWindow.parentObject=addressDetailsList.getItemAt(0);
	PopUpManager.centerPopUp(popUpWindow);
	popUpWindow.setFocus();	
}

public function setFields(addressInfo:ArrayCollection,basicInfo:Object,popUpWindow:employeePopUp):void
{		
	
popUpWindow.employeeCodeField.text=basicInfo.employeeCode;	
popUpWindow.employeeStatus = basicInfo.employeeStatus;
popUpWindow.parentEntity = basicInfo.parentEntity;
popUpWindow.designation = basicInfo.designation;
popUpWindow.gender = basicInfo.gender;
popUpWindow.category = basicInfo.category;
popUpWindow.pg = basicInfo.postGraduate;
popUpWindow.net = basicInfo.netQualified;
popUpWindow.physicalHandicapped = basicInfo.handicapped;
popUpWindow.minority = basicInfo.minority;
popUpWindow.pensionCode = basicInfo.pensionCode;
popUpWindow.minorityFlag = basicInfo.minorityFlag;


popUpWindow.buttonFunction = setFirstFocous;
popUpWindow.statusCombo.selectedItem=basicInfo.employeeStatus;
popUpWindow.dateOfJoiningField.data=DateField.stringToDate(basicInfo.dateOfJoining,"YYYY/MM/DD");
popUpWindow.studentfirstnametext.text=basicInfo.employeeFirstName;
popUpWindow.studentmiddlenametext.text=basicInfo.employeeMiddleName;
popUpWindow.studentlastnametext.text=basicInfo.employeeLastName;
popUpWindow.primarymailtext.text=basicInfo.primaryEmailId;
popUpWindow.secondarymailtext.text=basicInfo.secondryEmailId;
popUpWindow.dateofBirthField.data=DateField.stringToDate(basicInfo.dateOfBirth,"YYYY/MM/DD");
popUpWindow.qualificationtext.text=basicInfo.qualification;

for each(var i:Object in addressInfo){
	
	
	if(i.addressKey=="PER"){
		
		
		popUpWindow.permanentAddressField.addressLine1.text=i.permanentAddress;
		popUpWindow.permanentCity = i.permanentCity;
		popUpWindow.permanentState = i.permanentState;
		popUpWindow.permanentAddressField.pinField.text=i.permanentPinCode;
		
		popUpWindow.phones.officePhoneField.text=i.officePhoneNumber;
		popUpWindow.phones.homePhoneField.text=i.homePhoneNumber;
		popUpWindow.phones.otherPhoneField.text=i.otherPhoneNumber;
		popUpWindow.phones.faxPhoneField.text=i.faxNumber;
		
	}else if(i.addressKey=="COR"){
		
		popUpWindow.corspondenceAddressField.addressLine1.text=i.permanentAddress;
		popUpWindow.corspondenceCity=i.permanentCity;
		popUpWindow.corspondenceState=i.permanentState;
		popUpWindow.corspondenceAddressField.pinField.text=i.permanentPinCode;
		
	}
	
	
	
}
}

public function cancelFunction():void
{
	this.parentDocument.loaderCanvas.removeAllChildren();
}
