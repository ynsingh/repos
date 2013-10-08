/**
 * @(#) AddProgramSwitchAction.as
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

import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.events.ListEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

public var parentFunction:Function;
public var switchType:String;
public var switchRule:String;
public var oldSemester:String;
public var newEntity:String;
public var newProgram:String;
public var newBranch:String;
public var newSpecialization:String;
public var newSemester:String;
public var selectedValues:ArrayCollection;
public var infoObject:Object={};
public var buttonFunction:Function;

/** this method initialize screen elements**/
public function initFormElements():void
{	
//	entityCombo.selectedItem=newEntity;
//	newBranchCombo.selectedItem=newBranch;
//	newProgramCombo.selectedItem=newProgram;
//	newSpecializationCombo.selectedItem=newSpecialization;
//	newSemesterCombo.selectedItem=newSemester;
	urlPrefix=resourceManager.getString('Constants','url')+"/programswitch/";
	urlPrefix1=resourceManager.getString('Constants','url')+"/switchRule/";
	urlPrefix2=resourceManager.getString('Constants','url')+"/programCourseType/";		
	infoObject["userId"]=new Date;	
	var infoSemester:Object={};	
	infoSemester["userId"] = new Date;
	infoSemester["programId"] = selectedValues.getItemAt(0).programId;
	infoSemester["branchId"] = selectedValues.getItemAt(0).branchId;
	infoSemester["specializationId"] = selectedValues.getItemAt(0).specializationId; 
	
	getSwitchType.send(infoObject);
	getSwitchRule.send(infoObject);
	getEntityDetails.send(infoObject);
	getOldSemesters.send(infoSemester);
}

/**
 * This method is for getting the details
 * requered when the UI is loaded
 **/
[Bindable]public var urlPrefix:String;
[Bindable]public var urlPrefix1:String;
[Bindable]public var urlPrefix2:String;
public function onIntialize():void{
	urlPrefix=resourceManager.getString('Constants','url')+"/programswitch/";
	urlPrefix1=resourceManager.getString('Constants','url')+"/switchRule/";
	urlPrefix2=resourceManager.getString('Constants','url')+"/programCourseType/";	
	infoObject["userId"]=new Date;
	getSwitchType.send(infoObject);
	getSwitchRule.send(infoObject);
	getEntityDetails.send(infoObject);
	getProgramDetails.send(infoObject);	
}

/**
 * Method for getting the list Switch types 
 * defined for the concerned university
 **/ 
 public var switchTypeXml:XML;
 public var switchTypeList:ArrayCollection;
 public function onSwitchTypeSuccess(event:ResultEvent):void{ 	
 	switchTypeXml=event.result as XML; 	
 	switchTypeList=new ArrayCollection(); 	
 	for each (var o:Object in switchTypeXml.role){			
		switchTypeList.addItem({id:o.id,description:o.description});			
	} 	 	
 	switchTypeCombo.dataProvider=switchTypeXml.role.description;
 	switchTypeCombo.selectedItem=switchType; 	
 }
 
/**
 * Method retrives the switch rules 
 * defined for the university
 **/  
[Bindable]
public var details: XML;
public var switchRuleList:ArrayCollection;
public function onSuccess(event:ResultEvent):void{	
	details=event.result as XML;	
	switchRuleList =new ArrayCollection();	
	for each (var o:Object in details.Details){		
		switchRuleList.addItem({select:false,ruleId:o.ruleId,ruleCodeOne:o.ruleCodeOne,ruleCodeTwo:o.ruleCodeTwo,
			ruleCodeThree:o.ruleCodeThree,ruleCodeFour:o.ruleCodeFour,ruleCodeFive:o.ruleCodeFive,
			ruleCodeSix:o.ruleCodeSix,ruleFormula:o.ruleFormula});		
	}		
	switchRuleCombo.dataProvider = switchRuleList;
	
	if(switchRule!=null){
		for (var i:int=0;switchRuleCombo.dataProvider.length;i++){
			// Get this item's data
            var item:String = switchRuleCombo.dataProvider[i].ruleId;    
            // Check if is selectedValue
            if(item == switchRule){
                // Yes, set selectedIndex
                switchRuleCombo.selectedIndex = i;
                break;
            }
		}
	}
//		switchRuleCombo.dataProvider = details.Details.ruleId;
//		switchRuleCombo.selectedItem=switchRule;		
}

/**
 * Method retrieves the list of entity
 * for the concerned university
 **/ 
[Bindable]
public var entityDetailsXml: XML;
public function onEntitySuccess(event:ResultEvent):void{	
	entityDetailsXml=event.result as XML;		
	var detailslist:ArrayCollection =new ArrayCollection();	
	for each (var o:Object in entityDetailsXml.role){		
		detailslist.addItem({id:o.id,description:o.description});		
	}		
		entityCombo.dataProvider = entityDetailsXml.role.description;
		if(newEntity!=null){
			entityCombo.selectedItem=newEntity;
			entityChange();
		}				
}
/**
 * Method retrieves the list of programs
 * for the concerned university
 **/ 
[Bindable]
public var detailsXml: XML;
public function onProgramSuccess(event:ResultEvent):void{	
	detailsXml=event.result as XML;		
	var detailslist:ArrayCollection =new ArrayCollection();	
	for each (var o:Object in detailsXml.role){		
		detailslist.addItem({id:o.id,description:o.description});		
	}		
		newProgramCombo.dataProvider = detailsXml.role.description;
		if(newProgram!=null){
			newProgramCombo.selectedItem=newProgram;
			programChange();
		}	
			
}

	public function programChange():void{
		 newBranchCombo.enabled=true;		 
		 var infoObject:Object = {};		 
		 infoObject["userId"] = new Date;
		 infoObject["entityId"] = entityDetailsXml.role.(description==entityCombo.selectedLabel).id;
		 infoObject["programId"] = detailsXml.role.(description==newProgramCombo.selectedLabel).id;
		 infoObject["counter"] = "two";		 
		 getBranchDetails.send(infoObject);		 
	}
			
	public function entityChange():void{ 
		 newProgramCombo.enabled=true;			 
		 var infoObject:Object = {};			 
		 infoObject["userId"] = new Date;
		 infoObject["entityId"] = entityDetailsXml.role.(description==entityCombo.selectedLabel).id;
		 infoObject["counter"] = "one";			 
		 getProgramDetails.send(infoObject);			 
	}
/**
 * Method retrieves the list of branches for the selected program
 * of the concerned university
 **/
[Bindable]
public var branchDetails: XML;
public function programChangeSuccess(event:ResultEvent):void{	
	branchDetails=event.result as XML;	
	var detailslist:ArrayCollection =new ArrayCollection();	
	for each (var o:Object in branchDetails.role){		
		detailslist.addItem({id:o.id,description:o.description});		
	}		
		newBranchCombo.dataProvider = branchDetails.role.description;
		if(newBranch!=null){
			newBranchCombo.selectedItem=newBranch;
			branchChange();	
		}				
}

protected function branchChange():void
			{
	newSpecializationCombo.enabled=true;
	
	var infoObject:Object = {};
	
	infoObject["userId"] = new Date;
	infoObject["entityId"] = entityDetailsXml.role.(description==entityCombo.selectedLabel).id;
	infoObject["programId"] = detailsXml.role.(description==newProgramCombo.selectedLabel).id;
	infoObject["branchId"] = branchDetails.role.(description==newBranchCombo.selectedLabel).id;
	 infoObject["counter"] = "three";
	getspecializationDetails.send(infoObject);	
			}
/**
 * Method retrieves the list of specializations defined
 * for the selected program-branch
 * of the concerned university
 **/
[Bindable]
public var specialiationDetails: XML;
public function branchChangeSuccess(event:ResultEvent):void{
	
	specialiationDetails=event.result as XML;
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in specialiationDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}		
		newSpecializationCombo.dataProvider = specialiationDetails.role.description;
		if(newSpecialization!=null){
			newSpecializationCombo.selectedItem=newSpecialization;
			specializationChange();
		}	
}
			
protected function specializationChange():void{
	newSemesterCombo.enabled=true;
	var infoObject:Object = {};				
	infoObject["userId"] = new Date;
	infoObject["programId"] = detailsXml.role.(description==newProgramCombo.selectedLabel).id;
	infoObject["branchId"] = branchDetails.role.(description==newBranchCombo.selectedLabel).id;
	infoObject["specializationId"] =  specialiationDetails.role.(description==newSpecializationCombo.selectedLabel).id;
	getsemesterDetails.send(infoObject);					
}	

/**
 * Method retrieves the list of semestes defined
 * for the selected program-branch-specialization
 * of the concerned university
 **/			
[Bindable]
public var semesterDetails: XML;
public var minSemSeq :int=0;
public var semDetailList:ArrayCollection =new ArrayCollection();
public function specializationChangeSuccess(event:ResultEvent):void{		
	semesterDetails=event.result as XML;					
	for each (var o:Object in semesterDetails.semesterDetail){		
		semDetailList.addItem({semesterCode:o.semesterCode,semesterName:o.semesterName,semesterSequence:o.semesterSequence});
		var semSeq :int = Number(o.semesterSequence);
		if(minSemSeq>semSeq || minSemSeq==0){
			minSemSeq=semSeq;
		}
	}	
	//	newSemesterCombo.dataProvider = semesterDetails.role.description;
	newSemesterCombo.dataProvider = semDetailList;	
	if(newSemester!=null){
		for (var i:int=0;newSemesterCombo.dataProvider.length;i++){
			// Get this item's data
            var item:String = newSemesterCombo.dataProvider[i].semesterName;    
            // Check if is selectedValue
            if(item == newSemester){
                // Yes, set selectedIndex
                newSemesterCombo.selectedIndex = i;
                break;
            }
		}
	}	
}
  /**
 	* Mehtod to be called on request failure
 **/
 public function onFailure(event:FaultEvent):void{ 	
 	Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon); 	
 }

/** this method close popup screen**/
public function closeScreen():void{
	PopUpManager.removePopUp(this);	
}

/** this method add program switch details **/
public function submitDetails():void{
	if(validateAddProgramSwitchScreen()){		
		Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);					
	}
	else{
		Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

public function onOK(event:CloseEvent):void{	
	if(event.detail==Alert.YES){	
		var switchType:String=switchTypeXml.role.(description==switchTypeCombo.selectedLabel).id;
		var switchRule:String=switchRuleList.getItemAt(switchRuleCombo.selectedIndex).ruleCodeTwo;	
		var semSequence :int = semDetailList.getItemAt(newSemesterCombo.selectedIndex).semesterSequence;
		infoObject["swtichType"]=switchTypeXml.role.(description==switchTypeCombo.selectedLabel).id;
		infoObject["switchRuleId"]=switchRuleCombo.selectedLabel;
		infoObject["entityId"] = selectedValues[0].entityId;
		infoObject["oldProgramId"]=selectedValues[0].programId;
		infoObject["oldBranchId"]=selectedValues[0].branchId;
		infoObject["oldSpecializationId"]=selectedValues[0].specializationId;
		infoObject["oldSemesterId"]=oldSemesterDetails.role.(description==oldSemesterCombo.selectedLabel).id;
		infoObject["newEntityId"]=entityDetailsXml.role.(description==entityCombo.selectedLabel).id;
		infoObject["newProgramId"] = detailsXml.role.(description==newProgramCombo.selectedLabel).id;
		infoObject["newBranchId"] = branchDetails.role.(description==newBranchCombo.selectedLabel).id;
		infoObject["newSpecializationId"] =  specialiationDetails.role.(description==newSpecializationCombo.selectedLabel).id;
//		infoObject["newSemesterId"] =  semesterDetails.role.(description==newSemesterCombo.selectedLabel).id;
		infoObject["newSemesterId"] =  semDetailList.getItemAt(newSemesterCombo.selectedIndex).semesterCode;
		infoObject["activity"]="insert";				
		
		if(switchType=='LAT' && switchRule=='N'){
			if(semSequence>minSemSeq){
				var semName:String=semesterDetails.semesterDetail.(semesterSequence==minSemSeq).semesterName;
				Alert.show(resourceManager.getString("Messages","programSwitchSemesterRule",[semName]),commonFunction.getMessages('error'),0,null,null,errorIcon);
//				Alert.show("for this switch type and rule only first semester can be selected");
				newSemesterCombo.selectedIndex=-1;
				newSemesterCombo.setFocus();
			}
			else{
				setProgramSwitchDetails.send(infoObject);
			}
		}
		else{
			setProgramSwitchDetails.send(infoObject);
		}
	}	
}

public var oldSemesterDetails:XML;
public function onOldSemester(event:ResultEvent):void{
	
	oldSemesterDetails = event.result as XML;
	
	oldSemesterCombo.dataProvider = oldSemesterDetails.role.description;
	
}

/**
 * Method to be executed on successful activity(insert/update) execution
 **/ 
public var successDetails:XML;
//Changes By Mandeep
public function onSuccessfulEntry(event:ResultEvent):void{
	successDetails=event.result as XML;
	var value:int=parseInt(successDetails.Details.list_values);
	if(successDetails.Details.list_values=="success"){
	
	Alert.show((resourceManager.getString('Messages','savedSuccessfully')),
		(resourceManager.getString('Messages','saved')),
		4,null,null,successIcon);
		
	}
    else if (successDetails.Details.list_values=="failure"){
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	else if(String(successDetails.Details.list_values).search("DuplicateEntry")>-1){
		Alert.show(successDetails.Details.list_values,commonFunction.getMessages('DuplicateError'),4,null,null,infoIcon);
	}
	else if (successDetails.Details.list_values=="failure"){
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	else if(value==1){
	Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),4,null,null,successIcon);
	}
	else if(value==0){
	Alert.show(commonFunction.getMessages('noRecordUpdated'),commonFunction.getMessages('error'),4,null,null,infoIcon);	
	}

		
		closeScreen();	
		
		buttonFunction();	
}

/** this method update program switch details**/
public function updateDetails():void
{
	if(validateUpdateProgramSwitchScreen())
	{
		Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onUpdate,questionIcon);	
	}
	else
	{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
}

public function onUpdate(event:CloseEvent):void{	
	if(event.detail==Alert.YES){
		var switchType:String=switchTypeXml.role.(description==switchTypeCombo.selectedLabel).id;
		var switchRule:String=switchRuleList.getItemAt(switchRuleCombo.selectedIndex).ruleCodeTwo;	
		var semSequence :int = semDetailList.getItemAt(newSemesterCombo.selectedIndex).semesterSequence;		
		infoObject["swtichType"]= selectedValues[0].componentId;
		infoObject["oldSwitchRuleId"]=selectedValues[0].switchRuleId;
		infoObject["switchRuleId"]= switchRuleCombo.selectedLabel;
		infoObject["entityId"] = selectedValues[0].fromEntityId;
		infoObject["oldProgramId"]= selectedValues[0].oldProgramId;
		infoObject["oldBranchId"]= selectedValues[0].oldBranchId;
		infoObject["oldSpecializationId"]= selectedValues[0].oldSpecializationId;
		infoObject["oldSemesterId"]= selectedValues[0].oldSemesterCode;
		infoObject["newEntityId"]=entityDetailsXml.role.(description==entityCombo.selectedLabel).id;
		infoObject["newProgramId"] = detailsXml.role.(description==newProgramCombo.selectedLabel).id;
		infoObject["newBranchId"] = branchDetails.role.(description==newBranchCombo.selectedLabel).id;
		infoObject["newSpecializationId"] =  specialiationDetails.role.(description==newSpecializationCombo.selectedLabel).id;
//		infoObject["newSemesterId"] =  semesterDetails.role.(description==newSemesterCombo.selectedLabel).id;
		infoObject["newSemesterId"] =  semDetailList.getItemAt(newSemesterCombo.selectedIndex).semesterCode;
		infoObject["activity"]="update";			
		if(switchType=='LAT' && switchRule=='N'){
			if(semSequence>minSemSeq){
				var semName:String=semesterDetails.semesterDetail.(semesterSequence==minSemSeq).semesterName;
				Alert.show(resourceManager.getString("Messages","programSwitchSemesterRule",[semName]),commonFunction.getMessages('error'),0,null,null,errorIcon);
//				Alert.show("for this switch type and rule only first semester can be selected");
				newSemesterCombo.selectedIndex=-1;
				newSemesterCombo.setFocus();
			}
			else{
				setProgramSwitchDetails.send(infoObject);
			}
		}//end if switch==lat
		else{
			setProgramSwitchDetails.send(infoObject);
		}
	}//end if==yes			
}	//end function
	
/** this method reset program switch screen **/
public function resetDetails():void
{
	switchTypeCombo.selectedIndex=-1
	switchRuleCombo.selectedIndex=-1
	oldSemesterCombo.selectedIndex=-1
	newProgramCombo.selectedIndex=-1
	newBranchCombo.selectedIndex=-1
	newBranchCombo.dataProvider='';
	newSpecializationCombo.selectedIndex=-1
	newSpecializationCombo.dataProvider=null;
	newSemesterCombo.selectedIndex=-1
	newSemesterCombo.dataProvider=null;

	switchTypeCombo.errorString="";
	switchRuleCombo.errorString="";
	oldSemesterCombo.errorString="";
	newProgramCombo.errorString="";
	newBranchCombo.errorString="";
	newSpecializationCombo.errorString="";
	newSemesterCombo.errorString="";
	
}

/** this method validate program switch screen **/
public function validateAddProgramSwitchScreen():Boolean
{
	switchTypeValidator.source=switchTypeCombo
	switchRuleValidator.source=switchRuleCombo;
	newProgramValidator.source=newProgramCombo;
	newBranchValidator.source=newBranchCombo;
	newSpecializationValidator.source=newSpecializationCombo;
	oldSemesterValidator.source=oldSemesterCombo;
	newSemesterValidator.source=newSemesterCombo;
	
	if(Validator.validateAll([switchRuleValidator, switchTypeValidator, newProgramValidator, newBranchValidator,
	                          newSpecializationValidator, newSemesterValidator,oldSemesterValidator]).length==0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

/** this method validate program switch screen **/
public function validateUpdateProgramSwitchScreen():Boolean
{
	switchTypeValidator.source=switchTypeCombo
	switchRuleValidator.source=switchRuleCombo;
	newProgramValidator.source=newProgramCombo;
	newBranchValidator.source=newBranchCombo;
	newSpecializationValidator.source=newSpecializationCombo;
//	oldSemesterValidator.source=oldSemesterCombo;
	newSemesterValidator.source=newSemesterCombo;
	
	if(Validator.validateAll([switchRuleValidator, switchTypeValidator, newProgramValidator, newBranchValidator,
	                          newSpecializationValidator, newSemesterValidator]).length==0)
	{
		return true;
	}
	else
	{
		return false;
	}
}
