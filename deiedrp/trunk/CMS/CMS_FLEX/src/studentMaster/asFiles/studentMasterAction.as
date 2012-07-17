/**
 * @(#) studentmasterAction.as
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
import common.ImageUpload;
import mx.controls.Alert;
import mx.controls.DateField;
import mx.controls.Image;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]public var urlPrefix:String;
[Bindable]public var params:Object={};
[Bindable]public var categoryXML:XML=new XML;
[Bindable]public var entityXML:XML=new XML;
[Bindable]public var studentId:String;
[Bindable]public var uploadUrl:String;
[Bindable]public var uploadFileref:FileReference;

//this function sets focus to first field session on load of page
public function setFirstFocus():void
{
	uploadUrl=commonFunction.getConstants('url')+"/fileUpload/upload.jsp?fileName=";
	loadYears();
	params["time"]=new Date();
	urlPrefix=commonFunction.getConstants('url')+"/studentMaster/";
	getCategoryService.send(params);
	getEntityList.send(params);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	enrolllmentNoField.setFocus();
	enrolllmentNoField.errorString="";
	var endMonth:Date=new Date();
	dateOfBirthField.selectableRange={rangeEnd :endMonth};
}

// submit button handler
public function submitEnrollmentInfo():void
{
	resetForm();
	if(Validator.validateAll([enrollmentValidator]).length==0)
	{
		params["enrollmentNumber"]=enrolllmentNoField.text;
		checkEnrollmentInfo.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));	
	}
	else
	{
		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
}

// check enrollment number result handler
public function checkEnrollmentResultHandler(event:ResultEvent):void
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
    masterForm.visible=true;
	var bool:Boolean=(event.result.personalInfo.studentId.toString()!="");
	if(bool)
	{
		studentId=event.result.personalInfo.studentId;
		parentEntityCombo.selectedItem=entityXML.entity.(entityId==event.result.personalInfo.parentEntity.toString()).entityName+"";
		studentFirstNameText.text=event.result.personalInfo.studentFirstName;
		studentMiddleNameText.text=event.result.personalInfo.studentMiddleName;
		studentLastNameText.text=event.result.personalInfo.studentLastName;
		fatherFirstNameText.text=event.result.personalInfo.fatherFirstName;
		fatherMiddleNameText.text=event.result.personalInfo.fatherMiddleName;
		fatherLastNameText.text=event.result.personalInfo.fatherLastName;
		motherFirstNameText.text=event.result.personalInfo.motherFirstName;
		motherMiddleNameText.text=event.result.personalInfo.motherMiddleName;
		motherLastNameText.text=event.result.personalInfo.motherLastName;
		studentHindiName.text=decodeURI(String(event.result.personalInfo.hindiName+""));
		fatherHindiName.text=decodeURI(String(event.result.personalInfo.fatherHindiName+""));
		motherHindiName.text=decodeURI(String(event.result.personalInfo.motherHindiName+""));
		dateOfBirthField.selectedDate=DateField.stringToDate(event.result.personalInfo.dateOfBirth+'','YYYY-MM-DD');
		genderCombo.selectedItem=genderxmlId.gender.(@code==event.result.personalInfo.gender+"").@name+"";
		categoryCombo.selectedItem=event.result.personalInfo.categoryName+"";
		primarymailtext.text=event.result.personalInfo.primaryMail;
		secondarymailtext.text=event.result.personalInfo.secondaryMail;
		statusCombo.selectedItem=statusxmlId.status.(@code==event.result.personalInfo.status+"").@name+"";
		
		religionCombo.selectedItem=String(event.result.personalInfo.religion+"");
		nationalityCombo.selectedItem=String(event.result.personalInfo.nationality+"");
		guardianNameText.text=String(event.result.personalInfo.guardian+"");
		var photoPath:String=event.result.personalInfo.path+"";
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
		
		var session:String=event.result.personalInfo.registeredInSession;
		sessionComboBox.selectedItem=session.substring(0,4).toString();
		
		permanentAddressComponent.addressLine1.text=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').address;
		permanentAddressComponent.stateField.selectedItem=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').state.toString();
		permanentAddressComponent.city=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').city.toString();
		permanentAddressComponent.pinField.text=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').pinCode;
		
		phones.homePhoneField.text=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').homePhone;
		phones.officePhoneField.text=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').officePhone;
		phones.otherPhoneField.text=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').otherPhone;
		phones.faxPhoneField.text=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').fax;
		
		correspondenceAddressComponent.addressLine1.text=event.result.personalInfo.addressInfo.addresses.(addressKey=='COR').address;
		correspondenceAddressComponent.stateField.selectedItem=event.result.personalInfo.addressInfo.addresses.(addressKey=='COR').state.toString();
		correspondenceAddressComponent.city=event.result.personalInfo.addressInfo.addresses.(addressKey=='COR').city.toString();
		correspondenceAddressComponent.pinField.text=event.result.personalInfo.addressInfo.addresses.(addressKey=='COR').pinCode;
	}
	else
	{
		Alert.show(commonFunction.getMessages('enrollmentNumberNotFound'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	savebutton.visible=(!bool);
	updatebutton.visible=(bool);
	resetbutton.visible=(!bool);
	submitEnrollmentButton.enabled=false;
	
	parentEntityCombo.enabled=(!bool);
	dateOfBirthField.enabled=(!bool);
	categoryCombo.enabled=(!bool);
	genderCombo.enabled=(!bool);
	enrolllmentNoField.editable=(!bool);
	sessionComboBox.enabled=(!bool);
	nationalityCombo.enabled=(!bool);
	religionCombo.enabled=(!bool);
	statusCombo.enabled=bool;
}

// get entity result handler
public function getEntityResultHandler(event:ResultEvent):void
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
    entityXML=event.result as XML;
	parentEntityCombo.dataProvider=entityXML.entity.entityName;
	parentEntityCombo.selectedIndex=-1;
}

// get category result handler
public function getCategoryResultHandler(event:ResultEvent):void
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
    categoryXML=event.result as XML;
	categoryCombo.dataProvider=categoryXML.category.name;
	categoryCombo.selectedIndex=-1;
}

// fault handler
public function faultHandler(event:FaultEvent):void
{
	Mask.close();
	Alert.show(commonFunction.getMessages('error'));
}

// save button handler
public function submitForm():void
{
	if(validateForm())
	{
		params['time']=new Date;
		params['enrollmentNumber']=enrolllmentNoField.text;
				
		checkDuplicateInfo.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
	else
	{
		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
}

// duplicate check details handler
public function checkDuplicateResultHandler(event:ResultEvent):void
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
    if(int(event.result.count)==0)
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);	
	}
	else
	{
		Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),0,null,null,questionIcon);
	}
}

// setting values in param object
public function setParamsValue(obj:Object):Object
{
	obj["time"]=new Date;
	
	if(photoUpload.hasImage)
	{
		obj["path"]="StudentPhotos/"+enrolllmentNoField.text+".jpg";
	}
	
		
	obj["parentEntity"]=entityXML.entity.(entityName==parentEntityCombo.selectedLabel).entityId;
	obj["hindiName"]=encodeURI(studentHindiName.text);
	obj["firstName"]=studentFirstNameText.text;
	obj["middleName"]=studentMiddleNameText.text;
	obj["lastName"]=studentLastNameText.text;
	obj["fatherHindiName"]=encodeURI(fatherHindiName.text);
	obj["fatherFirstName"]=fatherFirstNameText.text;
	obj["fatherMiddleName"]=fatherMiddleNameText.text;
	obj["fatherLastName"]=fatherLastNameText.text;
	obj["motherHindiName"]=encodeURI(motherHindiName.text);
	obj["motherFirstName"]=motherFirstNameText.text;
	obj["motherMiddleName"]=motherMiddleNameText.text;
	obj["motherLastName"]=motherLastNameText.text;
	obj["category"]=categoryXML.category.(name==categoryCombo.selectedLabel).id;
	obj["dob"]=dateOfBirthField.text;
	obj["enrollmentNo"]=enrolllmentNoField.text;
	obj["registeredSession"]=sessionComboBox.selectedLabel+"-"+session.text;
	obj["gender"]=genderxmlId.gender.(@name==genderCombo.selectedLabel).@code;
	obj["status"]=statusxmlId.status.(@name==statusCombo.selectedLabel).@code;
	obj["primaryEmailId"]=primarymailtext.text;
	obj["secondaryEmailId"]=secondarymailtext.text;
	obj["address"]=permanentAddressComponent.addressLine1.text+"|"+correspondenceAddressComponent.addressLine1.text+"|";
	obj["city"]=permanentAddressComponent.cityField.selectedLabel+"|"+correspondenceAddressComponent.cityField.selectedLabel;
	obj["state"]=permanentAddressComponent.stateField.selectedLabel+"|"+correspondenceAddressComponent.stateField.selectedLabel;
	obj["pin"]=permanentAddressComponent.pinField.text+"|"+correspondenceAddressComponent.pinField.text+"|";
	obj["officePhone"]=phones.officePhoneField.text;
	obj["homePhone"]=phones.homePhoneField.text;
	obj["otherPhone"]=phones.otherPhoneField.text;
	obj["fax"]=phones.faxPhoneField.text;
	obj["nationality"]=nationalityCombo.selectedLabel;
	obj["religion"]=religionCombo.selectedLabel;
	obj["guardian"]=guardianNameText.text;
	return obj;
}

// insert detail confirmation handler
public function insertOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		params=setParamsValue(params);
		params['action']="insert";
		setStudentDetailsService.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

// set and update student detail result handler
public function setStudentDetailsResultHandler(event:ResultEvent):void
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
    if(event.result.info+""=="true")
	{
		if((!photoUpload.isOld)&&(photoUpload.hasImage))
		{
			uploadFileref=photoUpload.loadFileRef;
			uploadFileref.upload(new URLRequest(uploadUrl+enrolllmentNoField.text+".jpg"));
		}
		if(params['action']=="insert")
		{
			Alert.show(commonFunction.getMessages('successOnInsert'),commonFunction.getMessages('success'),0,null,null,successIcon);
		}
		else
		{
			Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
		}
		cancelForm();
	}
}

//this function update details 
public function updateForm():void
{
   if(validateForm())
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,updateOrNot,questionIcon);	
	}
	else
	{
		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
}

// update detail confirmation handler
public function updateOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		params=setParamsValue(params);
		params["studentId"]=studentId;
		params['action']="update";
		setStudentDetailsService.send(params);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

//this function update details 
public function cancelForm():void
{
   masterForm.visible=false;
   enrolllmentNoField.editable=true;
   enrolllmentNoField.text="";
   resetForm();
   enrolllmentNoField.errorString="";
   submitEnrollmentButton.enabled=true;
}

protected var flag:Boolean=false;
//This Function Checks All basic validations required of all form fields
public function validateForm():Boolean
{
	sessionValidator.source=sessionComboBox;
    if(Validator.validateAll([enrollmentValidator,sessionValidator,gendervalid,categoryvalid,
    studentFirstname,studentLastname,fatherFirstname,fatherLastname,motherFirstname,motherLastname,
	primaryemailVal,secondaryemailVal,birthdate,permanentAddressComponent.addressValidator
	,permanentAddressComponent.stateValidator,permanentAddressComponent.cityValidator,
	permanentAddressComponent.pinValidator,correspondenceAddressComponent.addressValidator,
	correspondenceAddressComponent.pinValidator,correspondenceAddressComponent.stateValidator,
	correspondenceAddressComponent.cityValidator,oficephonevalid,homephonevalid,otherphonavalid,
	faxphonevalid,parentEntityvalid,studentHindiValid,fatherHindiValid,motherHindiValid,
	religionvalid,nationalityvalid,guardianvalid]).length!=0)
		{
		flag=false;		
	    }
	
	else{
		flag=true;		
	    }
	
	return flag;
}

//This function copies values of permanent address to correspondence fields on selecting
// and on deselecting refresh correspondence address fields
public function copyToCorrespondenceAddress():void
{
	var b:Boolean=checkBoxField.selected;
	if(b)
	{
		correspondenceAddressComponent.addressLine1.text=permanentAddressComponent.addressLine1.text;
		correspondenceAddressComponent.city=permanentAddressComponent.cityField.selectedLabel;
		correspondenceAddressComponent.stateField.selectedIndex=permanentAddressComponent.stateField.selectedIndex;
		correspondenceAddressComponent.pinField.text=permanentAddressComponent.pinField.text;
	}
	else
	{
	    correspondenceAddressComponent.addressLine1.text="";
		correspondenceAddressComponent.stateField.selectedIndex=-1;
		correspondenceAddressComponent.cityField.selectedIndex=-1;
		correspondenceAddressComponent.pinField.text="";	
	}  
	correspondenceAddressComponent.enabled=(!b);
	permanentAddressComponent.enabled=(!b);
}

//This function reset whole form			
public function resetForm():void
{
	sessionValidator.source=null;
	
	permanentAddressComponent.enabled=true;
	correspondenceAddressComponent.enabled=true;
	studentFirstNameText.text="";
	studentMiddleNameText.text="";
	studentLastNameText.text="";
	fatherFirstNameText.text="";
	fatherMiddleNameText.text="";
	fatherLastNameText.text="";
	motherFirstNameText.text="";
	motherMiddleNameText.text="";
	motherLastNameText.text="";
	primarymailtext.text="";
	secondarymailtext.text="";
	dateOfBirthField.text="";
	genderCombo.selectedIndex=-1;
	categoryCombo.selectedIndex=-1;
	religionCombo.selectedIndex=-1;
	nationalityCombo.selectedIndex=-1;
	studentHindiName.text="";
	fatherHindiName.text="";
	motherHindiName.text="";
	guardianNameText.text="";

	parentEntityCombo.selectedIndex=-1;
	correspondenceAddressComponent.addressLine1.text="";
	permanentAddressComponent.addressLine1.text="";
	correspondenceAddressComponent.stateField.selectedIndex=-1;
	if(permanentAddressComponent.stateField.selectedIndex>=0)
	{
		permanentAddressComponent.stateField.selectedIndex=-1;
		permanentAddressComponent.cityField.selectedIndex=-1;
	}
	correspondenceAddressComponent.cityField.selectedIndex=-1;
	correspondenceAddressComponent.pinField.text="";
	permanentAddressComponent.pinField.text="";
	
	statusCombo.selectedIndex=0;
	sessionComboBox.selectedIndex=-1;
	session.text="";
	phones.officePhoneField.text="";
	phones.homePhoneField.text="";
	phones.otherPhoneField.text="";
	checkBoxField.selected=false;
	studentFirstNameText.errorString="";
	studentMiddleNameText.errorString="";
	studentLastNameText.errorString="";
	fatherFirstNameText.errorString="";
	fatherMiddleNameText.errorString="";
	fatherLastNameText.errorString="";
	motherFirstNameText.errorString="";
	motherMiddleNameText.errorString="";
	motherLastNameText.errorString="";
	primarymailtext.errorString="";
	secondarymailtext.errorString="";
	dateOfBirthField.errorString="";
	genderCombo.errorString="";
	categoryCombo.errorString="";
	studentHindiName.errorString="";
	fatherHindiName.errorString="";
	motherHindiName.errorString="";
	religionCombo.errorString="";
	nationalityCombo.errorString="";
	guardianNameText.errorString="";
	correspondenceAddressComponent.addressLine1.errorString="";
	permanentAddressComponent.addressLine1.errorString="";
	correspondenceAddressComponent.stateField.errorString="";
	permanentAddressComponent.stateField.errorString="";
	correspondenceAddressComponent.cityField.errorString="";
	permanentAddressComponent.cityField.errorString="";
	correspondenceAddressComponent.pinField.errorString="";
	permanentAddressComponent.pinField.errorString="";	
	session.errorString="";
	phones.officePhoneField.errorString="";
	parentEntityCombo.errorString="";
}

/** load list of years in sinceSession combobox **/
public function loadYears():void
{
	var years:Array=new Array();
    var fromYear:int = 1980;
    var currentYear:int = new Date().fullYear;

    for (var i:int=fromYear;i<=currentYear;i++)
    {
        years.push(i);
    }
    sessionComboBox.dataProvider=years;
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