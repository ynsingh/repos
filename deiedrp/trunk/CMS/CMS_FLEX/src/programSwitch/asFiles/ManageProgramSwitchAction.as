/**
 * @(#) ManageProgramSwitchAction.as
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted prertovided that the following
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
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import programSwitch.AddProgramSwitch;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;


public var infoObject:Object={};

/**
 * This method is for getting the details
 * requered when the UI is loaded
 **/
[Bindable]public var urlPrefix:String;
[Bindable]public var urlPrefix1:String;
[Bindable]public var urlPrefix2:String;
public function onCreationComplete():void{
	
	editButton.enabled=false;
	deleteButton.enabled=false;
	
	urlPrefix=resourceManager.getString('Constants','url')+"/programswitch/";
	urlPrefix1=resourceManager.getString('Constants','url')+"/switchRule/";
	urlPrefix2=resourceManager.getString('Constants','url')+"/programCourseType/";	
	
	infoObject["userId"]=new Date;
	
	getSetRecords.send(infoObject);
	
}

/** 
 * this method get program 
 * switch details from database
 **/
 
public var recordsDetails:XML;
public var recordList:ArrayCollection; 
public function getSwitchDetails(event:ResultEvent):void{	
	
	recordsDetails=event.result as XML;	
 	
 	recordList=new ArrayCollection();
 	
 	for each (var o:Object in recordsDetails.Details){
			
			recordList.addItem({select:false,fromEntityId:o.fromEntityId,fromEntityName:o.fromEntityName,oldProgramId:o.oldProgramId,oldProgramName:o.oldProgramName,oldBranchId:o.oldBranchId,
				oldBranchName:o.oldBranchName,oldSpecializationId:o.oldSpecializationId,oldSpecializationName:o.oldSpecializationName,
				oldSemesterCode:o.oldSemesterCode,oldSemesterName:o.oldSemesterName,
				programId:o.programId,programName:o.programName,branchId:o.branchId,
				branchName:o.branchName,specializationId:o.specializationId,specializationName:o.specializationName,
				semesterCode:o.semesterCode,semesterName:o.semesterName,componentId:o.componentId,
				componentDescription:o.componentDescription,switchRuleId:o.switchRuleId,entityId:o.entityId,entityName:o.entityName});
			
		} 	
 	
 	programSwitchDetailsGrid.dataProvider=recordList;
	
}
  /**
 	* Mehtod to be called on request failure
 	**/
 public function onFailure(event:FaultEvent):void{
 	
 	Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
 	
 }
 


/** this method open edit program switch window **/
public function editSwitch():void
{
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(programSwitchDetailsGrid);
	
	var editWindow:AddProgramSwitch=AddProgramSwitch(PopUpManager.createPopUp(this,AddProgramSwitch,true));
	
	editWindow.oldEntityLabel.text = selectedValues[0].fromEntityName;
	editWindow.oldProgramLabel.text=selectedValues[0].oldProgramName;
	editWindow.oldBranchLabel.text=selectedValues[0].oldBranchName;
	editWindow.oldSpecializationLabel.text=selectedValues[0].oldSpecializationName;
	editWindow.oldSemester=selectedValues[0].oldSemesterName;
	editWindow.newProgram=selectedValues[0].programName;
	editWindow.newBranch=selectedValues[0].branchName;
	editWindow.newSpecialization=selectedValues[0].specializationName;
	editWindow.newSemester=selectedValues[0].semesterName;
	editWindow.switchType=selectedValues[0].componentDescription;
	editWindow.switchTypeCombo.enabled=false;
	editWindow.switchRule=selectedValues[0].switchRuleId;
	editWindow.selectedValues=selectedValues;
	editWindow.buttonFunction=onCreationComplete;
	
	editWindow.updateButton.visible=true;
	
	editWindow.oldSemesterCombo.visible = false;
	editWindow.oldSemesterLabel.text = selectedValues[0].oldSemesterName;
	editWindow.oldSemesterLabel.visible = true;
	
	PopUpManager.centerPopUp(editWindow);
}

/** this method delete program switch details **/
public function deleteSwitch():void
{
	
	Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onDelete,questionIcon);	
	
}

public function onDelete(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		
		var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(programSwitchDetailsGrid);
		var deleteRecords:ArrayCollection=new ArrayCollection();
		for each(var obj:Object in selectedValues){
		
		deleteRecords.addItem(obj.fromEntityId);
		deleteRecords.addItem(obj.entityId);
		deleteRecords.addItem(obj.oldProgramId);
		deleteRecords.addItem(obj.programId);
		deleteRecords.addItem(obj.oldBranchId);
		deleteRecords.addItem(obj.branchId);
		deleteRecords.addItem(obj.oldSpecializationId);
		deleteRecords.addItem(obj.specializationId);
		deleteRecords.addItem(obj.semesterCode);
		deleteRecords.addItem(obj.oldSemesterCode);
		deleteRecords.addItem(obj.componentId);
		deleteRecords.addItem(obj.switchRuleId);
	}
	
	infoObject["deleteRecords"] = deleteRecords;
		
		deleteProgramSwitchRecords.send(infoObject);	
		
	}
	
}

/**
 * Method to be executed on successful deletion of selected records
 **/ 
 
 //changes by Mandeep
public var successDetails:XML;
public function onSuccessfulEntry(event:ResultEvent):void{
	successDetails=event.result as XML;
	var value:int=parseInt(successDetails.Details.list_values);
	
	if(value>0){
	Alert.show((resourceManager.getString('Messages','recordsDeletedSuccessfully')),
		(resourceManager.getString('Messages','saved')),
		4,null,null,successIcon);
		
	}
	else if(value==0){
      Alert.show(value+" record Deleted");
	}
	else if(String(successDetails.Details.list_values).search("ForiegnKeyConstraint")>-1){
		Alert.show(successDetails.Details.list_values,commonFunction.getMessages('ForiengnKeyError'),0,null,null,errorIcon);
	}
	else{	
			Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);	
	}	
		
		onCreationComplete();	
}