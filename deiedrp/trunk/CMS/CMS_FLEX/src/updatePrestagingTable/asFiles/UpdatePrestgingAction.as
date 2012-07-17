/**
 * @(#) UpdatePrestagingAction.as
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
import common.Mask;
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import updatePrestagingTable.updateprestagepopup;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
public var infoObject:Object={};
[Bindable]public var urlPrefix:String;

/**
 * This method is for getting the details
 * requered when the UI is loaded
 **/
public function onCreationComplete():void{
	
	editButton.enabled=false;
	deleteButton.enabled=false;
	urlPrefix=resourceManager.getString('Constants','url')+"/updatePrestaging/";
	infoObject["userId"]=new Date;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getSetRecords.send(infoObject);
	
}

/** 
 * this method fires on success of fetching grid records
 * it set records to grid via ArrayCollection
 **/
 [Bindable] var recordsDetails:XML;
public var recordList:ArrayCollection; 
public function getSetRecordsDetails(event:ResultEvent):void{	
	
	recordsDetails=event.result as XML;	
 	
 	recordList=new ArrayCollection();
 	
 	for each(var o:Object in recordsDetails.Details){
			
			var sn:String=o.studentfname+" "+o.studentmname+" "+o.studentlname;
			var gen:String=o.gender;
			if(gen=="M"){
				gen="Male";
			}else{
				gen="Female";
			}
			recordList.addItem({select:false,studentId:o.studentId,regRollNo:o.regRollNo,enrollNo:o.enrollNo,
				dob:o.dob,category:o.category,categoryId:o.categoryId,genderName:gen,genderId:o.gender,
				studentfname:o.studentfname,studentmname:o.studentmname,
				studentlname:o.studentlname,fatherfname:o.fatherfname,fathermname:o.fathermname,
				fatherlname:o.fatherlname,motherfname:o.motherfname,mothermname:o.mothermname,
				motherlname:o.motherlname,newEntity:o.newEntity,newProgram:o.newProgram,
				newBranch:o.newBranch,newSpecialization:o.newSpecialization,newSemester:o.newSemester,
				newEntityId:o.newEntityId,newProgramId:o.newProgramId,
				newBranchId:o.newBranchId,newSpecializationId:o.newSpecializationId,newSemesterId:o.newSemesterId,
				attemptNumber:o.attemptNumber,processsedFlag:o.processsedFlag,
				registrationDueDate:o.registrationDueDate,semesterStartDate:o.semesterStartDate,
				semesterEndDate:o.semesterEndDate,primaryMail:o.primaryMail,processStatus:o.processStatus,reasoncode:o.reasoncode,
				description:o.description,
				perAddress:o.perAddress,perCity:o.perCity,perState:o.perState,perPincode:o.perPincode,
				corAddress:o.corAddress,corCity:o.corCity,corState:o.corState,corPincode:o.corPincode,officePhone:o.officePhone,
				extraPhone:o.extraPhone,otherPhone:o.otherPhone,fax:o.fax,admissionModee:o.admissionMode,studentName:sn});

		} 	

 	prestagingDetailsGrid.dataProvider=recordList;
	Mask.close();
}
  /**
 	* Mehtod to be called on request failure
 	**/
 public function onFailure(event:FaultEvent):void{
 	
 	Mask.close();
 	Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
 	
 }
 
 
 
/** this method open update Prestaging popup window **/
public function editRecord():void
{
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(prestagingDetailsGrid);
	var s:String=selectedValues[0].admissionModee;
	if(s.toUpperCase()=="NEW"){
	
	var editWindow:updateprestagepopup=updateprestagepopup(PopUpManager.createPopUp(this,updateprestagepopup,true));
	editWindow.height=this.parentDocument.loaderCanvas.height;
	
	editWindow.admMode=selectedValues[0].admissionModee;
	editWindow.regNo=selectedValues[0].regRollNo;
	editWindow.enrNo=selectedValues[0].enrollNo;
	editWindow.atmNo=selectedValues[0].attemptNumber;

	editWindow.regrollNo.text=selectedValues[0].regRollNo;
	editWindow.enrollNo.text=selectedValues[0].enrollNo;
	editWindow.dateofBirthField.text=selectedValues[0].dob;
	editWindow.category=selectedValues[0].categoryId;
	editWindow.catId=selectedValues[0].categoryId;
	editWindow.genderr=selectedValues[0].genderId;
	editWindow.studentFirstName.text=selectedValues[0].studentfname;
	editWindow.studentmiddleName.text=selectedValues[0].studentmname;
	editWindow.studentLastName.text=selectedValues[0].studentlname;
	editWindow.fatherFirstName.text=selectedValues[0].fatherfname;
	editWindow.fatherMiddleName.text=selectedValues[0].fathermname;
	editWindow.fatherLastName.text=selectedValues[0].fatherlname;
	editWindow.motherFirstName.text=selectedValues[0].motherfname;
	editWindow.motherMiddleName.text=selectedValues[0].mothermname;
	editWindow.motherLastName.text=selectedValues[0].motherlname;
	editWindow.entId=selectedValues[0].newEntityId;
	editWindow.prgId=selectedValues[0].newProgramId;
	editWindow.brnId=selectedValues[0].newBranchId;
	editWindow.spcId=selectedValues[0].newSpecializationId;
	editWindow.semId=selectedValues[0].newSemesterId;
	editWindow.atemptNo.text=selectedValues[0].attemptNumber;

	
	editWindow.registrationDueDate.text=selectedValues[0].registrationDueDate;
	editWindow.semStartDate.text=selectedValues[0].semesterStartDate;
	editWindow.semEndDate.text=selectedValues[0].semesterEndDate;
	editWindow.primarymailtext.text=selectedValues[0].primaryMail;
	
	editWindow.permanentAddressField.addressLine1.text=selectedValues[0].perAddress;
	editWindow.permanentCity=selectedValues[0].perCity;
	editWindow.permanentState=selectedValues[0].perState;
	editWindow.permanentAddressField.pinField.text=selectedValues[0].perPincode;
	editWindow.corspondenceAddressField.addressLine1.text=selectedValues[0].corAddress;
	editWindow.corspondenceCity=selectedValues[0].corCity;
	editWindow.corspondenceState=selectedValues[0].corState;
	editWindow.corspondenceAddressField.pinField.text=selectedValues[0].corPincode;
	editWindow.phones.officePhoneField.text=selectedValues[0].officePhone;
	editWindow.phones.homePhoneField.text=selectedValues[0].extraPhone;
	editWindow.phones.otherPhoneField.text=selectedValues[0].otherPhone;
	editWindow.phones.faxPhoneField.text=selectedValues[0].fax;
	editWindow.buttonFunction=onCreationComplete;
	PopUpManager.centerPopUp(editWindow);
	}
	else{
		Alert.show(commonFunction.getMessages('thisRecordIsAvilableOnlyForDeletion'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
}	
	

/**This function asks for conformation of delete records or not
 * then pass to delete records
 * */
public function deleteOrNot():void
	{	 
		 Alert.show(commonFunction.getMessages('conformForContinue'),
		 commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteRecord,questionIcon);	
                
	}

/** this method delete program switch details **/
public function deleteRecord(event:CloseEvent):void
{
	if(event.detail==1){
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(prestagingDetailsGrid);
	
		infoObject["regRollNo"]="";
		infoObject["newEntityId"] ="";
		infoObject["newProgramId"] ="";
		infoObject["newBranchId"] ="";
		infoObject["newSpecializationId"] ="";
		infoObject["newSemesterId"] ="";
		infoObject["attemptNumber"] ="";
		infoObject["admissionMode"] ="";
		infoObject["enrollNo"] ="";
		
	for each(var obj:Object in selectedValues){
		
		infoObject["regRollNo"]+=obj.regRollNo+"|";
		infoObject["newEntityId"]+=obj.newEntityId+"|";
		infoObject["newProgramId"]+=obj.newProgramId+"|";
		infoObject["newBranchId"]+=obj.newBranchId+"|";
		infoObject["newSpecializationId"]+=obj.newSpecializationId+"|";
		infoObject["newSemesterId"]+=obj.newSemesterId+"|";
		infoObject["attemptNumber"]+=obj.attemptNumber+"|";
		infoObject["admissionMode"]+=obj.admissionModee+"|";
		infoObject["enrollNo"]+=obj.enrollNo+"|";
	}
	    Mask.show(commonFunction.getMessages('pleaseWait'));
		deleteRecords.send(infoObject);	
	}
}

/**
 * Method to be executed on successful deletion of selected records
 **/ 
public var successDetails:XML;
public function onSuccessfulEntry(event:ResultEvent):void{
	
	successDetails=event.result as XML;
	
	if(successDetails.Details.list_values=="success"){
	
	Alert.show((resourceManager.getString('Messages','recordsDeletedSuccessfully')),"Success",
		4,null,null,successIcon);
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}		
	    Mask.close();	
		onCreationComplete();	
}