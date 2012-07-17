/**
 * @(#) CreateProgramSwitchAction.as
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
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import programSwitch.AddProgramSwitch;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
public var infoObject:Object={};

/**
 * This method is for getting the details
 * requered when the UI is loaded
 **/
 public function onCreationComplete():void{
 	
 	addButton.enabled=false;
 	
 	infoObject["userId"]=new Date;
 	
 	getInputRecords.send(infoObject); 	
 	
 }
 
 /**
 * Method for getting the list of
 * programs-combinations for the concerned university
 **/
 public var programCombinations:XML;
 public var programCombinationList:ArrayCollection;
 public function onRecordsSuccess(event:ResultEvent):void{
 	
 	programCombinations=event.result as XML;
 	
 	programCombinationList=new ArrayCollection();
 	
 	for each (var o:Object in programCombinations.Details){
			
			programCombinationList.addItem({select:false,entityId:o.entityId,entityName:o.entityName,programId:o.programId,programName:o.programName,branchId:o.branchId,
				branchName:o.branchName,specializationId:o.specializationId,specializationName:o.specializationName,
				semesterCode:o.semesterCode,semesterName:o.semesterName});
			
		} 	
 	
 	programBranchSpecializationGrid.dataProvider=programCombinationList;
 	
 	getSetRecords.send(infoObject);
 	
 }
 	/**
 	* Mehtod to be called on request failure
 	**/
 public function onFailure(event:FaultEvent):void{
 	
 	Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
 	
 }
 
 

/** 
 * this method opens add program switch popupwindow
 **/
public function addASwitch():void
{
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(programBranchSpecializationGrid);
	var editWindow:AddProgramSwitch=AddProgramSwitch(PopUpManager.createPopUp(this,AddProgramSwitch,true));
	
	editWindow.oldEntityLabel.text = selectedValues[0].entityName;
	editWindow.oldProgramLabel.text=selectedValues[0].programName;
	editWindow.oldBranchLabel.text=selectedValues[0].branchName;
	editWindow.oldSpecializationLabel.text=selectedValues[0].specializationName;
	editWindow.selectedValues=selectedValues;
	editWindow.parentFunction=getSwitchDetails;
	editWindow.buttonFunction=onCreationComplete;
	
	editWindow.submitButton.visible=true;
	editWindow.resetButton.visible=true;
	
	
	
	PopUpManager.centerPopUp(editWindow);
	
}

/** 
 * Method for getting the list of
 * defined records(to be displayed in the grid)
 * set for the university
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
 	
 	recordsDisplayDataGrid.dataProvider=recordList;
	
}