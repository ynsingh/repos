/**
 * @(#) CourseMasterAction.as
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

import flash.events.KeyboardEvent;
import flash.ui.Keyboard;

import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

public var ownerEntity:String;
public var ownerProgram:String;
public var ownerBranch:String;
public var ownerSpecialization:String;
public var courseClassification:String;
public var courseType:String;
public var courseGroup:String;
public var sinceSession:String;
public var referenceFunction:Function;
public var e:CloseEvent;

[Bindable]public var urlPrefix:String;
[Bindable]public var params:Object={};
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;
[Bindable]public var courseTypeXml:XML;
[Bindable]public var courseGroupXml:XML;
[Bindable]public var resultSystemXML:XML;
[Bindable]public var courseClassificationXml:XML;
[Bindable]public var resultSystem:String;


public var enbl:Boolean;

//after key depression
public function onKeyUp(event:KeyboardEvent):void
{
	if(event.keyCode==Keyboard.ESCAPE)
	{
		if(!ownerEntityComboBox.enabled)
		{
			onOk(e);
		}
	}
}

/** creation of form **/
public function createForm():void
{
	enbl=tutorialsField.editable;
	urlPrefix=commonFunction.getConstants('url')+"/courseMaster/";
	loadYears();
	sinceSessionComboBox.selectedItem=sinceSession;
	
	params['time']=new Date();
	
	getEntityList.send(params);
	
	params['groupCode']='CRSTYP';
	
	getCourseTypeList.send(params);
	
	params['groupCode']='SBWGRP';
	
	getCourseGroupList.send(params);
	
	params['groupCode']='CRCLSF';
	
	getCourseClassificationList.send(params);
	
	params['groupCode']='CRSOPT';
	
	getComponentList.send(params);
	
	ownerEntityComboBox.setFocus();
	
	if(InternalExternalActive.selectedValue=='1')
	{
		internalMarksField.editable=true&&enbl;
		externalMarksField.editable=true&&enbl;
		totalMarksField.editable=false&&enbl;
	}
	else
	{
		internalMarksField.editable=false&&enbl;
		externalMarksField.editable=false&&enbl;
		totalMarksField.editable=true&&enbl;
	}
}

/** get course type success handler **/
public function getComponentSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    resultSystemXML=event.result as XML;
	resultSystemCombo.dataProvider=resultSystemXML.Details.name;
	resultSystemCombo.selectedItem=resultSystemXML.Details.(id==resultSystem).name+"";
}

/** get course type success handler **/
public function getCourseTypeSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    courseTypeXml=event.result as XML;
	courseTypeComboBox.dataProvider=courseTypeXml.Details.name;
	courseTypeComboBox.selectedItem=courseType;
}

/** get course classification success handler **/
public function getCourseClassificationSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    courseClassificationXml=event.result as XML;
	courseClassificationComboBox.dataProvider=courseClassificationXml.Details.name;
	courseClassificationComboBox.selectedItem=courseClassification;
}

/** get course group success handler **/
public function getCourseGroupSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    courseGroupXml=event.result as XML;
	courseGroupComboBox.dataProvider=courseGroupXml.Details.name;
	courseGroupComboBox.selectedItem=courseGroup;
}

/** get entity success handler **/
public function getEntitySuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    entityXml=event.result as XML;
	ownerEntityComboBox.dataProvider=entityXml.Details.name;
	ownerEntityComboBox.selectedItem=ownerEntity;
}

//on change of entity
public function onEntityChange():void
{
	try{
	params['entityId']=entityXml.Details.(name==ownerEntityComboBox.selectedLabel).code;
	getProgramList.send(params);
	}catch(e:Error){}
}

/** get programs success handler **/
public function getProgramSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    programXml=event.result as XML;
	ownerProgramComboBox.dataProvider=programXml.Details.name;
	ownerProgramComboBox.selectedItem=ownerProgram;
}

/** on change of program **/
public function onProgramChange():void
{
	try{
		params['programId']=programXml.Details.(name==ownerProgramComboBox.selectedLabel).id;
		params['time']=new Date();
	
		getBranchList.send(params);
	}
	catch(e:Error){}	
}

/** get branches success handler **/
public function getBranchSuccess(event:ResultEvent):void
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
    branchXml=event.result as XML;
	ownerBranchComboBox.dataProvider=branchXml.Details.name;
	ownerBranchComboBox.selectedItem=ownerBranch;
}

/** on chnage of branch **/
public function onBranchChange():void
{
	try{
		params['branchId']=branchXml.Details.(name==ownerBranchComboBox.selectedLabel).id;
		params['time']=new Date();
	
		getSpecializationList.send(params);
	}
	catch(e:Error){}
}

/** get specialization success handler **/
public function getSpecializationSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    specializationXml=event.result as XML;
	ownerSpecializationComboBox.dataProvider=specializationXml.Details.name;
	ownerSpecializationComboBox.selectedItem=ownerSpecialization;
}

/** get duplicate success handler **/
public function getDuplicateSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");;
		}
	}
 	catch(e:Error){}
    if(int(event.result.count)==0)
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** on click of insert confirmation **/
public function insertOrNot(event:CloseEvent):void
{
	var intMarks:String="0";
	var extMarks:String="0";
	var totMarks:String="0";
	
	if(InternalExternalActive.selectedValue=='1')
	{
		intMarks=internalMarksField.text;
		extMarks=externalMarksField.text;
		totMarks=(int(intMarks)+int(extMarks)).toString();
	}
	else
	{
		totMarks=totalMarksField.text;
	}
	
	if(event.detail==1)
	{
		params['dummyFlag']=InternalExternalActive.selectedValue;
		params['programId']=programXml.Details.(name==ownerProgramComboBox.selectedLabel).id;
		params['entityId']=entityXml.Details.(name==ownerEntityComboBox.selectedLabel).code;
		params['branchId']=branchXml.Details.(name==ownerBranchComboBox.selectedLabel).id;
		params['specializationId']=specializationXml.Details.(name==ownerSpecializationComboBox.selectedLabel).id;
		params['courseCode']=courseCodeTextInput.text;
		params['courseName']=courseNameTextInput.text;
		params['courseType']=courseTypeXml.Details.(name==courseTypeComboBox.selectedLabel).id;
		params['courseGroup']=courseGroupXml.Details.(name==courseGroupComboBox.selectedLabel).id;
		params['courseClassification']=courseClassificationXml.Details.(name==courseClassificationComboBox.selectedLabel).id;
		params['internalMarks']=intMarks;
		params['externalMarks']=extMarks;
		params['totalMarks']=totMarks;
		params['units']=unitsField.text;
		params['credits']=creditsField.text;
		params['lectures']=lecturesField.text;
		params['resultSystem']=resultSystemXML.Details.(name==resultSystemCombo.selectedLabel.toString()).id;
		params['practicals']=practicalsField.text;
		params['tutorials']=tutorialsField.text;
		params['sinceSession']=sinceSessionComboBox.selectedLabel+"-"+toSessionTextInput.text;
		params['time']=new Date();
			/*gradeLimit parameter added by Mandeep*/
		params['gradeLimit']=gradeLimitActive.selectedValue;
		params["edeiStatus"]=edeiStatusGroup.selectedValue

		Mask.show(commonFunction.getMessages('pleaseWait'));
		setCourseDetails.send(params);
	}
}

/** set course details success handler **/
public function setCourseDetailsSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
 	//Mandeep
 	var result:XML=event.result as XML;
 		if(String(result.exception.exceptionstring).search("InsertError")>-1){
				var result:XML=event.result as XML;
		Alert.show(result.exception.exceptionstring,commonFunction.getMessages('error'),0,null,null,infoIcon);
	}
	else{
    Alert.show(commonFunction.getMessages('successOnInsert'),commonFunction.getMessages('success'),0,null,null,successIcon);
	resetForm();
	}
	
}

/** fault handler **/
public function onFailure(event:FaultEvent):void
{
	Mask.close();
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
}

/**
 * validation function for course master screeen
 * @return boolean(True/False)
 */
public function validateCourseMasterScreen():Boolean
{
	resultSystemValidator.source=resultSystemCombo;
	ownerEntityValidator.source=ownerEntityComboBox;
	ownerProgramValidator.source=ownerProgramComboBox;
	ownerBranchValidator.source=ownerBranchComboBox;
	ownerSpecValidator.source=ownerSpecializationComboBox;
	courseTypeValidator.source=courseTypeComboBox;
	courseClassValidator.source=courseClassificationComboBox;
	courseGroupValidator.source=courseGroupComboBox;
	sinceSessionValidator.source=sinceSessionComboBox;
	
	var i:int=Validator.validateAll([totalMarksValidator,resultSystemValidator,ownerEntityValidator,ownerProgramValidator,ownerBranchValidator,ownerSpecValidator,courseTypeValidator
	    ,courseClassValidator,courseGroupValidator,courseCodeValidator,courseNameValidator,internalMarksValidator,externalMarksValidator
	        ,creditsValidator,unitsValidator,lecturesValidator,tutorialsValidator,practicalsValidator,sinceSessionValidator]).length;
	
    if(i==0)
	{
		if(commonFunction.checkForSpeacialCharAtStart(courseNameTextInput.text))
		{
			return true;
		}
		else
		{
			courseNameTextInput.errorString=commonFunction.getMessages('invalidCharInData');
			return false;
		}
	}
	else
	{
		return false;
	}
}

/** on submit of form data **/
public function submitForm():void
{
	if(validateCourseMasterScreen())
	{
		params['courseCode']=courseCodeTextInput.text;
		Mask.show(commonFunction.getMessages('pleaseWait'));
		getDuplicateCourseCount.send(params);
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** reset form data **/
public function resetForm():void
{
	resultSystemValidator.source=null;
	ownerEntityValidator.source=null;
	ownerProgramValidator.source=null;
	ownerBranchValidator.source=null;
	ownerSpecValidator.source=null;
	courseTypeValidator.source=null;
	courseClassValidator.source=null;
	courseGroupValidator.source=null;
	sinceSessionValidator.source=null;
	
	ownerEntityComboBox.selectedIndex=-1;
	ownerProgramComboBox.selectedIndex=-1;
	ownerBranchComboBox.selectedIndex=-1;
	ownerSpecializationComboBox.selectedIndex=-1;
	courseCodeTextInput.text="";
    courseNameTextInput.text="";
	courseTypeComboBox.selectedIndex=-1;
	courseGroupComboBox.selectedIndex=-1;
	courseClassificationComboBox.selectedIndex=-1;
    lecturesField.text="";
    tutorialsField.text="";
    practicalsField.text="";
    creditsField.text="";
    unitsField.text="";
    internalMarksField.text="0";
    externalMarksField.text="0";
    totalMarksField.text="0";
    sinceSessionComboBox.selectedIndex=-1;
    resultSystemCombo.selectedIndex=-1;
    
    ownerEntityComboBox.errorString="";
    ownerProgramComboBox.errorString="";
    ownerBranchComboBox.errorString="";
    ownerSpecializationComboBox.errorString="";
    courseCodeTextInput.errorString="";
    courseNameTextInput.errorString="";
    courseTypeComboBox.errorString="";
	courseGroupComboBox.errorString="";
	courseClassificationComboBox.errorString="";
    lecturesField.errorString="";
    tutorialsField.errorString="";
    practicalsField.errorString="";
    creditsField.errorString="";
    unitsField.errorString="";
    internalMarksField.errorString="";
    externalMarksField.errorString="";
    totalMarksField.errorString="";
    sinceSessionComboBox.errorString="";
	resultSystemCombo.errorString="";
	InternalExternalActive.selectedValue=1;
		/*gradeLimit parameter added by Mandeep*/
	gradeLimitActive.selectedValue=1;
	edeiStatusGroup.selectedValue=0;
    internalMarksField.editable=true&&enbl;
	externalMarksField.editable=true&&enbl;
	totalMarksField.editable=false&&enbl;
	
	ownerProgramComboBox.enabled=false;
	ownerBranchComboBox.enabled=false;
	ownerSpecializationComboBox.enabled=false;
}

/** Update form details **/
public function updateForm():void
{
	if(validateCourseMasterScreen())
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,updateOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** on click of update confirmation **/
public function updateOrNot(event:CloseEvent):void
{
	var intMarks:String="0";
	var extMarks:String="0";
	var totMarks:String="0";
	
	if(InternalExternalActive.selectedValue=='1')
	{
		intMarks=internalMarksField.text;
		extMarks=externalMarksField.text;
		totMarks=(int(intMarks)+int(extMarks)).toString();
	}
	else
	{
		totMarks=totalMarksField.text;
	}

	
	if(event.detail==1)
	{
		params['dummyFlag']=InternalExternalActive.selectedValue;
		params['courseCode']=courseCodeTextInput.text;
		params['courseName']=courseNameTextInput.text;
		params['courseType']=courseTypeXml.Details.(name==courseTypeComboBox.selectedLabel).id;
		params['courseGroup']=courseGroupXml.Details.(name==courseGroupComboBox.selectedLabel).id;
		params['courseClassification']=courseClassificationXml.Details.(name==courseClassificationComboBox.selectedLabel).id;
		params['internalMarks']=intMarks;
		params['externalMarks']=extMarks;
		params['totalMarks']=totMarks;
		params['resultSystem']=resultSystemXML.Details.(name==resultSystemCombo.selectedLabel.toString()).id;
		params['units']=unitsField.text;
		params['credits']=creditsField.text;
		params['lectures']=lecturesField.text;
		params['practicals']=practicalsField.text;
		params['tutorials']=tutorialsField.text;
		params['sinceSession']=sinceSessionComboBox.selectedLabel+"-"+toSessionTextInput.text;
	/*gradeLimit parameter added by Mandeep*/	
		params['gradeLimit']=gradeLimitActive.selectedValue;
		params["edeiStatus"]=edeiStatusGroup.selectedValue;
		Mask.show(commonFunction.getMessages('pleaseWait'));
		updateCourseDetails.send(params);
	}
}

/** update course details success handler **/
public function updateCourseDetailsSuccess(event:ResultEvent):void
{
	 	var result:XML=event.result as XML;
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
    if(int(event.result.count)==1)
	{
		Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,onOk,successIcon);
	}
	//Mandeep
	else if(int(event.result.count)==0){
	Alert.show(commonFunction.getMessages('noRecordUpdated'),commonFunction.getMessages('error'),0,null,onOk,infoIcon);
	}
	
 		else if(String(result.exception.exceptionstring).search("updateError")>-1){
				var result:XML=event.result as XML;
		Alert.show(result.exception.exceptionstring,commonFunction.getMessages('updateError'),0,null,null,infoIcon);
	}
	
	Mask.close();
}
	
/** Delete form details **/
public function deleteDetails():void
{
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.CANCEL),null,deleteOrNot,questionIcon);
}

/** on click of delete confirmation **/
public function deleteOrNot(event:CloseEvent):void
{
	if(event.detail==1)
	{
		params["courseCode"]=courseCodeTextInput.text;
		params['time']=new Date();
	
		Mask.show(commonFunction.getMessages('pleaseWait'));
		deleteCourseDetails.send(params);
	}
	else
	{
		onOk(e);
	}
}

/** delete course details success handler **/
public function deleteCourseDetailsSuccess(event:ResultEvent):void
{
	var result:XML=event.result as XML;
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
    
    if(int(event.result.count)==1)
	{
		Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,onOk,successIcon);
	}
	//Mandeep
	else if((event.result.count)==0){
		Alert.show(commonFunction.getMessages('noRecordDeleted'),commonFunction.getMessages('error'),0,null,onOk,infoIcon);
	}
	else if(String(result.exception.exceptionstring).search("ForiegnKeyConstraint")>-1){
		Alert.show(result.exception.exceptionstring,commonFunction.getMessages('ForiengnKeyError'),0,null,onOk,infoIcon);
	}
	//Mandeep
}
	
/** on click of ok **/
public function onOk(event:CloseEvent):void
{
	if(updateButton.visible||deleteButton.visible)
   	{
		referenceFunction("dontShowAlert");
		PopUpManager.removePopUp(this);
   	}
   	else
   	{
   		this.parentDocument.loaderCanvas.removeAllChildren();
   	}
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
    sinceSessionComboBox.dataProvider=years;
}

//on click of internal external radio
public function setIntExtActive():void
{
	if(InternalExternalActive.selectedValue=='1')
	{
		totalMarksField.text="0";
		internalMarksField.editable=true&&enbl;
		externalMarksField.editable=true&&enbl;
		totalMarksField.editable=false&&enbl;
	}
	else
	{
		internalMarksField.text="0";
		externalMarksField.text="0";
		internalMarksField.editable=false&&enbl;
		externalMarksField.editable=false&&enbl;
		totalMarksField.editable=true&&enbl;
	}
}

//setting total marks field value
public function setTotal():void
{
	try	{
		if(!totalMarksField.editable)
		{
			totalMarksField.text=(Number(internalMarksField.text)+Number(externalMarksField.text)).toString();
		}
	}catch(E:Error){}
}