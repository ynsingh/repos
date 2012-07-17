/**
 * @(#) UpdatePrestagingPopupAction.as
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
import mx.collections.ArrayCollection;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Bindable]public var urlPrefix1:String;
[Bindable]public var urlPrefix2:String;			
import mx.controls.Alert;
import mx.validators.Validator;
import mx.messaging.management.Attribute;
import common.commonFunction;
import mx.events.CloseEvent;
import common.Mask;			
public var infoObject:Object={};
public var buttonFunction:Function;
public var gender:String;
public var permanentCity:String;
public var permanentState:String;
public var corspondenceCity:String;
public var corspondenceState:String;
public var arr:ArrayCollection;
public var o:Object;

[Bindable]public var params:Object={};
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;
[Bindable]public var semesterXml:XML;
[Bindable]public var genderr:String="";
[Bindable]
public var categoryDetails: XML;
public var categorylist:ArrayCollection;
[Bindable]public var category:String="";
[Bindable]public var catId:String="";
[Bindable]public var regNo:String="";
[Bindable]public var enrNo:String="";
[Bindable]public var entId:String="";
[Bindable]public var prgId:String="";
[Bindable]public var brnId:String="";
[Bindable]public var spcId:String="";
[Bindable]public var semId:String="";
[Bindable]public var atmNo:String="";
[Bindable]public var admMode:String=""; 
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
public var param:Object={};

/**
 * This method is for getting the details
 * requered when the UI is loaded
 * And set values in some fields
 **/
protected function titlewindowInitializeHandler():void{
	
		urlPrefix1=resourceManager.getString('Constants','url')+"/prestagingData/";
	    urlPrefix2=resourceManager.getString('Constants','url')+"/updatePrestaging/";
				
		permanentAddressField.city=permanentCity;
		permanentAddressField.stateField.selectedItem=permanentState;				
				
		corspondenceAddressField.city = corspondenceCity;
		corspondenceAddressField.stateField.selectedItem = corspondenceState;
					
		infoObject["userId"] = new Date;
	//	Mask.show(commonFunction.getMessages('pleaseWait'));
		getCategoryDetails.send(infoObject);
		params['time']=new Date;
		getEntityList.send(params);
		genderCombo.selectedItem=genders.gender.(@code==genderr).@name+"";
		}
			
			
/**
* method executed on request failure 
* */
public function onFailure(event:FaultEvent):void{
	Mask.close();			
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
	}
			
/**
 * This method closes the popup window
 **/			
public function closepopup():void{
				
		PopUpManager.removePopUp(this);
	}
				
/**
 * This method fires on success of fetching of Entities
 * set values in combo box
 **/		
public function getEntitySuccess(event:ResultEvent):void
{
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	entityXml=event.result as XML;
	
	newEntityCombo.dataProvider=entityXml.Details.name;
	//Alert.show(entId+"rohit"+entityXml.Details);
	//Alert.show(entityXml.Details.(id==entId).name+"rohit");
	newEntityCombo.selectedItem=entityXml.Details.(id==entId).name+"";
	//Mask.close();
}

/**
 * This method fire on change of entity
 * send request to fetch programs
 **/
public function onEntityChange(event:Event):void
{
	
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==newEntityCombo.selectedLabel).id;
	//Mask.show(commonFunction.getMessages('pleaseWait'));
	getProgramList.send(params);
}

/**
 * This method fires on success of fetching of Programs
 * set values in combo box
 **/		
public function getProgramSuccess(event:ResultEvent):void
{
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	programXml=event.result as XML;
	
	newProgramCombo.dataProvider=programXml.Details.name;
	
	newProgramCombo.selectedItem=programXml.Details.(id==prgId).name+"";
	Mask.close();
}

/**
 * This method fire on change of program
 * send request to fetch branches
 **/
public function onProgramChange(event:Event):void
{
	
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==newEntityCombo.selectedLabel).id;
	params['programId']=programXml.Details.(name==newProgramCombo.selectedLabel).id;
	//Mask.show(commonFunction.getMessages('pleaseWait'));
	getBranchList.send(params);
	}

/**
 * This method fires on success of fetching of Branch
 * set values in combo box
 **/		
public function getBranchSuccess(event:ResultEvent):void
{
	
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	branchXml=event.result as XML;
	
	newBranchCombo.dataProvider=branchXml.Details.name;
	newBranchCombo.selectedItem=branchXml.Details.(id==brnId).name+"";
    Mask.close();
}

/**
 * This method fire on change of branches
 * send request to fetch specialzations
 **/
public function onBranchChange(event:Event):void
{
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==newEntityCombo.selectedLabel).id;
	params['programId']=programXml.Details.(name==newProgramCombo.selectedLabel).id;
	params['branchId']=branchXml.Details.(name==newBranchCombo.selectedLabel).id;
	//Mask.show(commonFunction.getMessages('pleaseWait'));
	getSpecializationList.send(params);
}

/**
 * This method fires on success of fetching of Specializations
 * set values in combo box
 **/		
public function getSpecializationSuccess(event:ResultEvent):void
{
	
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	specializationXml=event.result as XML;

	newSpecializationCombo.dataProvider=specializationXml.Details.name;
	
	
	newSpecializationCombo.selectedItem=specializationXml.Details.(id==spcId).name+"";
    Mask.close();
}

/**
 * This method fire on change of 
 * send request to fetch programs
 **/
public function onSpecializationChange(event:Event):void
{
	
	params['time']=new Date;
	params['programId']=programXml.Details.(name==newProgramCombo.selectedLabel).id;
	params['branchId']=branchXml.Details.(name==newBranchCombo.selectedLabel).id;
	params['specializationId']=specializationXml.Details.(name==newSpecializationCombo.selectedLabel).id;
	//Mask.show(commonFunction.getMessages('pleaseWait'));
	getSemesterList.send(params);

}

/**
 * This method fires on success of fetching of Semester's list
 * set values in combo box
 **/		
public function getSemesterSuccess(event:ResultEvent):void
{
	
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	semesterXml=event.result as XML;

    newSemesterCombo.dataProvider=semesterXml.role.description;
    
    newSemesterCombo.selectedItem=semesterXml.role.(id==semId).description+"";
    Mask.close();
}

/**
 * The function retrives the list of categories for the 
 * concerned university id
 * */

public function onCategoryDetailsSuccess(event:ResultEvent):void{
	
	categoryDetails=event.result as XML;
	
	categorylist =new ArrayCollection();
	
	for each (var o:Object in categoryDetails.role){
		
		categorylist.addItem({id:o.id,description:o.description});
		
	}
	
	if(categorylist.length==0){
		
		Alert.show(resourceManager.getString('Messages','noRecordFound'),
			resourceManager.getString('Messages','alert'));
	}else{
		
		categoryCombo.dataProvider = categoryDetails.role.description;
		categoryCombo.selectedItem = categoryDetails.role.(id==category).description+"";
	}	
}
		
/**
 * This method fires on click of update button
 * it validates all mandatory fields
 * send request to update record
 **/			
public function updateValues():void{
		
		if(Validator.validateAll([studentFirstNameValid,fatherFirstNameValid,motherFirstNameValid,newEntityComboValid,
		newProgramComboValid,newBranchComboValid,newSpecializationComboValid,newSemesterComboValid,
		registrationDueDateValid,semStartDateValid,semEndDateValid]).length==0){
			
		param["regRollNo"] =regrollNo.text;
		param["enrollNo"] =enrollNo.text;
		param["studentfname"] =studentFirstName.text;
		param["studentmname"] =studentmiddleName.text;
		param["studentlname"] =studentLastName.text;
		param["fatherfname"] =fatherFirstName.text;
		param["fathermname"] =fatherMiddleName.text;
		param["fatherlname"] =fatherLastName.text;
		param["motherfname"] =motherFirstName.text;
		param["mothermname"] =motherMiddleName.text;
		param["motherlname"] =motherLastName.text;
		param["primaryMail"] =primarymailtext.text;
		param["dob"] =dateofBirthField.text;
		param["gender"] =genders.gender.(@name==genderCombo.selectedLabel).@code;
		param["category"] =categoryDetails.role.(description==categoryCombo.selectedLabel).id;
		param["newEntityId"] =entityXml.Details.(name==newEntityCombo.selectedLabel).id;
		param["newProgramId"] =programXml.Details.(name==newProgramCombo.selectedLabel).id;
		param["newBranchId"] =branchXml.Details.(name==newBranchCombo.selectedLabel).id;
		param["newSpecializationId"] =specializationXml.Details.(name==newSpecializationCombo.selectedLabel).id;
		param["newSemesterId"] =semesterXml.role.(description==newSemesterCombo.selectedLabel).id;
		param["attemptNumber"] =atemptNo.text;
		param["admissionMode"]=admMode;
		param["registrationDueDate"] =registrationDueDate.text;
		param["semesterStartDate"] =semStartDate.text;
		param["semesterEndDate"] =semEndDate.text;
		param["attemptNumber"] =atemptNo.text;
		param["perAddress"] =permanentAddressField.addressLine1.text;
		param["perCity"] =permanentAddressField.cityField.selectedLabel;
		param["perState"] =permanentAddressField.stateField.selectedLabel;
		param["perPincode"] =permanentAddressField.pinField.text;
		param["corAddress"] =corspondenceAddressField.addressLine1.text;
		param["corCity"] =corspondenceAddressField.cityField.selectedLabel;
		param["corState"] =corspondenceAddressField.stateField.selectedLabel;
		param["corPincode"] =corspondenceAddressField.pinField.text;
		param["officePhone"] =phones.officePhoneField.text;
		param["extraPhone"] =phones.homePhoneField.text;
		param["otherPhone"] =phones.otherPhoneField.text;
		param["fax"] =phones.faxPhoneField.text;
		param["updRegRollNo"] =regNo;
		param["updEnrollNo"] =enrNo;
		param["updNewEntity"] =entId;
		param["updNewProgram"] =prgId;
		param["updNewBranch"] =brnId;
		param["updNewSpecialization"] =spcId;
		param["updNewSemester"] =semId;
		param["updAttemptNumber"] =atmNo;
		param["updAdmissionMode"] =admMode;
		Mask.show(commonFunction.getMessages('pleaseWait'));
		updateRecords.send(param);			
	}
	else{
			
	Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);	
	}
	
}

/**
 * This method fires on update success
 * it shows appropriate Alert
 **/	
public var updateDetails:XML;
public function onUpdateSuccess(event:ResultEvent):void{
		

	updateDetails=event.result as XML;
	
	if(updateDetails.Details.list_values=="success"){
	
	Alert.show((resourceManager.getString('Messages','recordUpdatedSuccessfully')),commonFunction.getMessages('success'),
		4,null,onUpdate,successIcon);
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}		
	Mask.close();
}

/**
 * This method fires on click of ok button
 * of update Success Alert
 **/			
public function onUpdate(event:CloseEvent):void{
				
		PopUpManager.removePopUp(this);
		buttonFunction.call();
	}