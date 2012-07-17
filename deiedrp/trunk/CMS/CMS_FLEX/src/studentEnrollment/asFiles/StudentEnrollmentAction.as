/**
 * @(#) StudentEnrollmentAction.as
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
import common.hindiKeyBoard;

import loginForm.EnrollmentLogin;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.Image;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")] private var errorIcon:Class;
[Embed(source="/images/success.png")] private var successIcon:Class;
[Embed(source="/images/reset.png")] private var resetIcon:Class;
[Embed(source="/images/question.png")] private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")] private var infoIcon:Class;

/** declaring public variables **/
public var params:Object={};
[Bindable]public var urlPrefix:String="";
public var personalXML:XML;
public var categoryXML:XML;
public var contactXML:XML;
public var academicXML:XML;
public var enrollmentNo:String="";
public var studentId:String;
public var entityCode:String="";
public var programCode:String="";
public var branchCode:String="";
public var specializationCode:String="";
public var photoPath:String="";
public var registrationNo:String;
public var qualificationArray:ArrayCollection;
[Bindable]public var academicData:ArrayCollection=new ArrayCollection();
public var uploadUrl:String;
public var uploadFileref:FileReference;

/** on creation of form **/
public function onCreationComplete():void
{
	uploadUrl=commonFunction.getConstants('url')+"/fileUpload/upload.jsp?fileName=";
	genderCombo.dataProvider=genderXml.gender.@name;
	urlPrefix=commonFunction.getConstants('url')+"/enrollmentForm/";
	params["time"]=new Date().getTime();
	getCategoryInfo.send(params);
	Mask.show();
}

/** get category success handler **/
public function getCategorySuccess(event:ResultEvent):void
{
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    categoryXML=event.result as XML;
	category.dataProvider=categoryXML.category.name;
	params["time"]=new Date().getTime();
	params["registrationNo"]=registrationNo;
	getPersonalInfo.send(params);
}

/** get personal info success handler **/
public function getPersonalInfoSuccess(event:ResultEvent):void
{
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){Mask.close();}
    personalXML=event.result as XML;
	
	for each(var obj:Object in personalXML.personalInfo){
		if(String(obj.status+"").toUpperCase()!='SUB')
		{
		sessionStartDate.text=obj.sessionStartDate;
		sessionEndDate.text=obj.sessionEndDate;
		facRegNo.text=registrationNo.toUpperCase();
		
		studentId=obj.studentId;
		enrollmentNo=obj.enrollmentNo;
		studentHindiName.text=decodeURI(obj.studentHindiName+"");
		fatherHindiName.text=decodeURI(obj.fatherHindiName+"");
		motherHindiName.text=decodeURI(obj.motherHindiName+"");
		firstName.text=obj.studentFirstName;
		middleName.text=obj.studentMiddleName;
		lastName.text=obj.studentLastName;
		fatherFirstName.text=obj.fatherFirstName;
		fatherMiddleName.text=obj.fatherMiddleName;
		fatherLastName.text=obj.fatherLastName;
		motherFirstName.text=obj.motherFirstName;
		motherMiddleName.text=obj.motherMiddleName;
		motherLastName.text=obj.motherLastName;
		category.selectedItem=categoryXML.category.(id==obj.categoryCode+"").name+"";
		dobField.text=obj.dob;
		primaryEmail.text=obj.primaryMail;
		secondaryEmail.text=obj.secondaryMail;
		guardianName.text=obj.guardianName;
		nationalityCB.selectedItem=obj.nationality+"";
		religionCB.selectedItem=obj.religion+"";
		genderCombo.selectedItem=genderXml.gender.(@code==obj.gender+"").@name+"";
		photoPath=obj.photoPath+"";
		if((photoPath!=null)&&(photoPath.length!=0))
		{
			var img:Image=new Image();
			img.height=188;
			img.width=183;
			img.load(commonFunction.getConstants('url')+"/"+photoPath);
			photoUpload.removeAllChildren();
			photoUpload.addChild(img);
			photoUpload.isOld=true;
			photoUpload.hasImage=true;
		}
		entity.text=obj.entity;
		program.text=obj.program;
		branch.text=obj.branch;
		specialization.text=obj.specialization;
		
		entityCode=obj.entityCode;
		programCode=obj.programCode;
		branchCode=obj.branchCode;
		specializationCode=obj.specializationCode;
		}
		else
		{
			Alert.show(commonFunction.getMessages('enrollmentFormAlreadySubmitted'),commonFunction.getMessages('error'),0,null,cancelForm,errorIcon);
		}
	}
	params['studentId']=studentId;
	getContactInfo.send(params);
}

/** get contact info success handler **/
public function getContactInfoSuccess(event:ResultEvent):void
{
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){Mask.close();}
    contactXML=event.result as XML;
	for each(var obj:Object in contactXML.addressInfo){
		if(obj.addressKey=='PER')
		{
			permanentAddress.addressLine1.text=obj.address;
			permanentAddress.stateField.selectedItem=obj.state.toString();
			permanentAddress.city=obj.city.toString();
			permanentAddress.pinField.text=obj.pinCode;
			
			phone.officePhoneField.text=obj.officePhone;
			phone.homePhoneField.text=obj.homePhone;
			phone.otherPhoneField.text=obj.otherPhone;
			phone.faxPhoneField.text=obj.fax;
		}
		else
		{
			correspondenceAddress.addressLine1.text=obj.address;
			correspondenceAddress.stateField.selectedItem=obj.state.toString();
			correspondenceAddress.city=obj.city.toString();
			correspondenceAddress.pinField.text=obj.pinCode;
		}
	}
	getAcademicInfo.send(params);
}

/** get academic info success handler **/
public function getAcademicInfoSuccess(event:ResultEvent):void
{
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'));
			var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){Mask.close();}
    academicXML=event.result as XML;
	for each(var obj:Object in academicXML.academicInfo){
		var examName:String=qualificationXml.qualification.(@value==obj.exam+"").@label;
		academicData.addItem({exam:examName, board:obj.board,cgpa:obj.cgpa,marksObtained:obj.marksObtained, totalMarks:obj.totalMarks,
		college:obj.college,year:obj.year});
	}
	
	marksGrid.dataProvider=academicData;
	Mask.close();
}

/** fault handler **/
public function faultHandler(event:FaultEvent):void
{
	Mask.close();
	Alert.show(event.messageId,commonFunction.getMessages('requestFailed'));
}

/** coying permanent address to correspondense address **/
public function copyAddress():void
{
	var b:Boolean=sameAsAbove.selected;
	if(b)
	{
		correspondenceAddress.addressLine1.text=permanentAddress.addressLine1.text;
		correspondenceAddress.city=permanentAddress.cityField.selectedLabel;
		correspondenceAddress.stateField.selectedIndex=permanentAddress.stateField.selectedIndex;
		correspondenceAddress.pinField.text=permanentAddress.pinField.text;
		
	}
	else
	{
		correspondenceAddress.addressLine1.text="";
		correspondenceAddress.stateField.selectedIndex=-1;
		correspondenceAddress.cityField.selectedIndex=-1;
		correspondenceAddress.pinField.text="";
		
		correspondenceAddress.addressLine1.errorString="";
		correspondenceAddress.stateField.errorString="";
		correspondenceAddress.cityField.errorString="";
		correspondenceAddress.pinField.errorString="";
	}
    correspondenceAddress.enabled=(!b);
    permanentAddress.enabled=(!b);
}

/** on click of submit button **/
public function submitForm():void
{
	if(Validator.validateAll([genderValidator,firstNameValidator,fatherFirstNameValidator,motherFirstNameValidator,religionValidator,categoryValidator,
			primaryEmailValidator,secondaryEmailValidator,nationalityValidator,officePhoneValidator,homePhoneValidator,otherPhoneValidator,
			hindiNameValidator1,hindiNameValidator2,hindiNameValidator2,addressValidator,cityValidator,stateValidator,permanentAddress.pinValidator,correspondenceAddress.pinValidator]).length==0){
		if(validateQualificationGrid()){
			params['status']='SUB';
			Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
		}
		else
		{
		}
	}
	else{
		Alert.show(commonFunction.getMessages("pleasecheckEntriesinRed"),commonFunction.getMessages("error"),0,null,null,errorIcon);
	}
}

/** on click of save form **/
public function saveForm():void
{
	if(Validator.validateAll([genderValidator,firstNameValidator,fatherFirstNameValidator,motherFirstNameValidator,religionValidator,categoryValidator,
			primaryEmailValidator,secondaryEmailValidator,nationalityValidator,officePhoneValidator,homePhoneValidator,otherPhoneValidator,
			hindiNameValidator1,hindiNameValidator2,hindiNameValidator2,addressValidator,cityValidator,stateValidator,permanentAddress.pinValidator,correspondenceAddress.pinValidator]).length==0){
		if(validateQualificationGrid()){
			params['status']='SAV';
			Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
		}
		else{
		}
	}
	else{
		Alert.show(commonFunction.getMessages("pleasecheckEntriesinRed"),commonFunction.getMessages("error"),0,null,null,errorIcon);
	}
}

/** validating acdemic grid data **/
public function validateQualificationGrid():Boolean
{
	var bool:Boolean=true;
	var tempArr:Array=new Array();
	for each(var obj:Object in academicData)
	{
		if((obj.exam+""=="")||(obj.board+""=="")||(obj.marksObtained+""=="")||(obj.totalMarks+""=="")||(obj.college+""=="")||(obj.year+""==""))
		{
			Alert.show(commonFunction.getMessages("enterYearAndCollege"),commonFunction.getMessages("error"),0,null,null,errorIcon);
			bool=false;
			break;
		}
		// this else if  is added by Ashish Mohan
		else if((int(obj.totalMarks+"")-int(obj.marksObtained+""))<0)
		{
			Alert.show(commonFunction.getMessages('totalGreaterObtained'),commonFunction.getMessages("error"),0,null,null,errorIcon);
			bool=false;
			break;
		}else if(tempArr.indexOf(String(obj.year+""+obj.exam))<0){
			tempArr.push(String(obj.year+""+obj.exam));
		}else{
			Alert.show("duplicate entry for same qualiction",commonFunction.getMessages("error"),0,null,null,errorIcon);
			bool=false;
			break;
		}
	}
	return bool;
}

/** on click of insert confirmation **/
public function insertOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES){
		params['time']=new Date;
		params["registrationNo"]=registrationNo;
		params["studentId"]=studentId;
		params['studentHindiName']=encodeURI(studentHindiName.text);
		params['fatherHindiName']=encodeURI(fatherHindiName.text);
		params['motherHindiName']=encodeURI(motherHindiName.text);
		params["firstName"]=firstName.text;
		params["middleName"]=middleName.text;
		params["lastName"]=lastName.text;
		params["fatherFirstName"]=fatherFirstName.text;
		params["fatherMiddleName"]=fatherMiddleName.text;
		params["fatherLastName"]=fatherLastName.text;
		params["motherFirstName"]=motherFirstName.text;
		params["motherMiddleName"]=motherMiddleName.text;
		params["motherLastName"]=motherLastName.text;
		params["category"]=categoryXML.category.(name==category.selectedLabel).id;
		params["religion"]=religionCB.selectedLabel;
		params["nationality"]=nationalityCB.selectedLabel;
		params["guardian"]=guardianName.text;
		params["gender"]=genderXml.gender.(@name==genderCombo.selectedLabel+"").@code;
		params["dob"]=dobField.text;
		params["entity"]=entityCode
		params["program"]=programCode;
		params["branch"]=branchCode;
		params["specialization"]=specializationCode;
		params["enrollmentNo"]=enrollmentNo;
		params["primaryEmailId"]=primaryEmail.text;
		params["secondaryEmailId"]=secondaryEmail.text;
		
		if(photoUpload.hasImage)
		{
			params['path']="StudentPhotos/"+registrationNo+".jpg";
		}		
		
		params["address"]=permanentAddress.addressLine1.text+"|"+correspondenceAddress.addressLine1.text+"|";
		params["city"]=permanentAddress.cityField.selectedLabel+"|"+correspondenceAddress.cityField.selectedLabel;
		params["state"]=permanentAddress.stateField.selectedLabel+"|"+correspondenceAddress.stateField.selectedLabel;
		params["pin"]=permanentAddress.pinField.text+"|"+correspondenceAddress.pinField.text+"|";
		
		params["officePhone"]=phone.officePhoneField.text;
		params["homePhone"]=phone.homePhoneField.text;
		params["otherPhone"]=phone.otherPhoneField.text;
		params["faxPhone"]=phone.faxPhoneField.text;
		
		params["examCode"]="";
		params["year"]="";
		params["boardCode"]="";
		params["schoolCollege"]="";
		params["totalMarks"]="";
		params["marksObtained"]="";
		params["cgpa"]="";//Add by Devendra
		var flag:int=0;
		for each(var obj:Object in marksGrid.dataProvider as ArrayCollection){
			params["examCode"]+=qualificationXml.qualification.(@label==obj.exam+"").@value+"|";
			params["year"]+=obj.year+"|";
			params["boardCode"]+=obj.board+"|";
			params["schoolCollege"]+=obj.college+"|";
			params["totalMarks"]+=obj.totalMarks+"|";
			params["marksObtained"]+=obj.marksObtained+"|";
			params["cgpa"]+=obj.cgpa+"|";
			if(Number(obj.cgpa)>10){
				flag++;
			}
		}

		params['time']=new Date();
		if(flag==0){
			setStudentInfo.send(params);
			Mask.show(commonFunction.getMessages('pleaseWait'));
		}
		else{
			Alert.show(commonFunction.getMessages('cgpaCantGreater'),commonFunction.getMessages('info'),0,null,null,infoIcon);
		}
		
	}
}

/** set student success handler **/
public function setStudentInfoSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
 	
 	var status:String=(event.result.info+"").substring(0,3);
 	var result:String=(event.result.info+"").substring(3,(event.result.info+"").length);
 	
 	if(result=="true")
	{
		if((!photoUpload.isOld)&&(photoUpload.hasImage))
		{
			uploadFileref=photoUpload.loadFileRef;
			uploadFileref.upload(new URLRequest(uploadUrl+registrationNo+".jpg"));
		}
		if(status=="SUB")
		{
			Alert.show(commonFunction.getMessages('enrollmentFormSubmittedSuccessfully'),commonFunction.getMessages('success'),0,null,cancelForm,successIcon);
		}
		else
		{
			Alert.show(commonFunction.getMessages('savedSuccessfully'),commonFunction.getMessages('success'),0,null,cancelForm,successIcon);
		}
	}else{
		Alert.show(result,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** after enrollment is done **/
public function afterDone(msg:String):void
{
	this.removeAllChildren();
	this.height=100;
	this.title=msg;
}

/** adding a row in grid **/
public function addRow():void
{
	if(validateQualificationGrid())
	{
		academicData.addItem({exam:"", board:"", marksObtained:"", totalMarks:"",college:"",year:""});
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleaseFillAcademicDetail'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** on click of cancel **/
public function cancelForm(event:CloseEvent):void
{
	params['time']=new Date;
	setLogoutService.send(params);
}

/** Showing hindi keyboard **/
public function showHindiKeyboardForStudent():void
{
	var hindiScreen:hindiKeyBoard=hindiKeyBoard(PopUpManager.createPopUp(this,hindiKeyBoard,true));
	hindiScreen.refFunction=setHindiNameForStudent;
	hindiScreen.textValue.text=studentHindiName.text;
	PopUpManager.centerPopUp(hindiScreen);
}

/** Setting hindi name from keyboard to form **/
public function setHindiNameForStudent(hName:String):void
{
	studentHindiName.text=hName;
}

/** Showing hindi keyboard **/
public function showHindiKeyboardForFather():void
{
	var hindiScreen:hindiKeyBoard=hindiKeyBoard(PopUpManager.createPopUp(this,hindiKeyBoard,true));
	hindiScreen.refFunction=setHindiNameForFather;
	hindiScreen.textValue.text=fatherHindiName.text;
	PopUpManager.centerPopUp(hindiScreen);
}

/** Setting hindi name from keyboard to form **/
public function setHindiNameForFather(hName:String):void
{
	fatherHindiName.text=hName;
}

/** Showing hindi keyboard **/
public function showHindiKeyboardForMother():void
{
	var hindiScreen:hindiKeyBoard=hindiKeyBoard(PopUpManager.createPopUp(this,hindiKeyBoard,true));
	hindiScreen.refFunction=setHindiNameForMother;
	hindiScreen.textValue.text=motherHindiName.text;
	PopUpManager.centerPopUp(hindiScreen);
}

/** Setting hindi name from keyboard to form **/
public function setHindiNameForMother(hName:String):void
{
	motherHindiName.text=hName;
}

/** On click of logout button **/
public function logoutResultHandler(event:Event):void
{
	this.parentDocument.loaderCanvas2.addChild(new EnrollmentLogin());
	this.parentDocument.loaderCanvas2.removeChildAt(0);
}