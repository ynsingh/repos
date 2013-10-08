// ActionScript file
/**
 * @(#) insertInPrestagingAction.as
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
 * Author:Ashish Mohan
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
 
import ProgramCourse.GridDataExporterService.*;

import common.commonFunction;

import flash.events.Event;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.DataGrid;
import mx.controls.Label;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.core.ClassFactory;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
public var columnNameArr:Array;
public var tableData:Array;
public var newStList:XMLList=new XMLList;
public var oldStList:XMLList=new XMLList;
public var gridData:Array=new Array;

private static const DEL_LAST_ROW:String = "Remove Last Student";
private static const ADD_ROW:String = "Add More Student";
private static const DEL_INDEX_ROW:String="Remove Selected Student";
[Bindable]public var urlPrefix:String;
[Bindable]public var params:Object={};
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;
[Bindable]private var tasks:ArrayCollection;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;


  	
//used to give tooltip to the data grid
private function showHeaderTooltip(dg:DataGrid):void {
	var cols:Array = mainGrid.columns;
   	for each(var dataColumn:DataGridColumn in cols){
		dataColumn.showDataTips=true;
		dataColumn.dataTipField=dataColumn.dataField;
		dataColumn.headerRenderer=new ClassFactory(mx.controls.Label);
	}
	mainGrid.columns=cols;
}


//to get serial no in data grid
private function myLabelFun(item:Object, col:DataGridColumn):String
{
	var itemIndex:int = tasks.getItemIndex(item);
	return (itemIndex+1).toString();
}
 
 



//on uploading interface
public function onInterfaceLoad():void
{	urlPrefix=commonFunction.getConstants('url')+"/prestagingData/";
	params['time']=new Date;
	params['mode']="all";
	getEntityList.send(params);
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	programCombo.enabled=false;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
    submitButton.enabled=false;
    headerCanvas.enabled=true;
    studentCanvas.visible=false;
    tasks=new ArrayCollection;
    
}




//get entity list success handler
public function getEntitySuccess(event:ResultEvent):void
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
	
	entityXml=event.result as XML;
	entityCombo.dataProvider=entityXml.Details.name;
	entityCombo.selectedIndex=-1;
	entityCombo.enabled=true;
}


//on change of entity
public function onEntityChange(event:Event):void
{
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
	getProgramList.send(params);
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
    submitButton.enabled=false;
    studentCanvas.visible=false;
}



//get program list success handler
public function getProgramSuccess(event:ResultEvent):void
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
	programXml=event.result as XML;
	
	programCombo.dataProvider=programXml.Details.name;
	programCombo.selectedIndex=-1;
	programCombo.enabled=true;
}



//on change of program
public function onProgramChange(event:Event):void
{
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
	params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
	getBranchList.send(params);
	specilizationCombo.selectedIndex=-1;
	specilizationCombo.enabled=false;
    submitButton.enabled=false;
    studentCanvas.visible=false;

}



//get branch list success handler
public function getBranchSuccess(event:ResultEvent):void
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
	branchXml=event.result as XML;
	branchCombo.dataProvider=branchXml.Details.name;
	branchCombo.selectedIndex=-1;
	branchCombo.enabled=true;
}



//on change of branch
public function onBranchChange(event:Event):void
{
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
	params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
	params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
	getSpecializationList.send(params);
    submitButton.enabled=false;
    studentCanvas.visible=false;
}



//get specialization success handler
public function getSpecializationSuccess(event:ResultEvent):void
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
	specializationXml=event.result as XML;
	specilizationCombo.dataProvider=specializationXml.Details.name;
	specilizationCombo.selectedIndex=-1;
	specilizationCombo.enabled=true;
}



//On change of specialization
public function onSpecializationChange(event:Event):void
{
	submitButton.enabled=true;
	studentCanvas.visible=false;
}



//On clik of ok 
public function visibleStudentGrid(event:Event):void
{	
	getStudentListMethod();
}




public function getStudentListMethod():void{
		params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
		params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
		params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
		params['specializationId']=specializationXml.Details.(name==specilizationCombo.selectedLabel).id;
		params['time']=new Date;
		getStudentList.send(params);
}

//setting already saved data
public function getStudentListSuccess(event:ResultEvent):void{

	var s:XML=event.result as XML;
	if(s.children().length()>0){
		studentCanvas.visible=true;
		headerCanvas.enabled=false;
	}
	else{
		Alert.show("No saved Student Found for this Combination",commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	tasks=new ArrayCollection;
	for each(var ob:Object in s.personalInfo){
		var enrollmentNumber:String=ob.enrollmentNumber;
		if(enrollmentNumber=="NA"){
			enrollmentNumber="";
		}
		var dob:String=ob.dateOfBirth;
		var yy:String=dob.substr(0,4);
		var mm:String=dob.substr(5,2);
		var dd:String=dob.substr(8,2);
		dob=dd+mm+yy;

		var categoryName:String=ob.categoryName;
		var hindiName:String=decodeURI(ob.hindiName);
		
		tasks.addItem({select:false,EnrollmentNumber:enrollmentNumber,StudentName:ob.studentFirstName,
		FatherName:ob.fatherFirstName,DateofBirth:dob,
			Category:ob.categoryName,EmailId:ob.primaryEmailId,Gender:ob.gender,
			Address:ob.addressLineOne,RollNumberGroupCode:ob.path,StudentHindiName:hindiName});
	}

	mainGrid.dataProvider=tasks;

}




public function updateSuccess(event:ResultEvent):void{
	getStudentListMethod();
}


public function openEditWindow():void{
	var selectedRecord:ArrayCollection=commonFunction.getSelectedRowData(mainGrid);
    var editWindow:editPopupWindow =editPopupWindow(PopUpManager.createPopUp(this,editPopupWindow,true))
   	editWindow.oldEntity=entityXml.Details.(name==entityCombo.selectedLabel).id;
	editWindow.oldProgram=programXml.Details.(name==programCombo.selectedLabel).id;
	editWindow.oldBranch=branchXml.Details.(name==branchCombo.selectedLabel).id;
	editWindow.oldSpecialization=specializationXml.Details.(name==specilizationCombo.selectedLabel).id;
    editWindow.refresh=onInterfaceLoad;
    editWindow.selStudent=selectedRecord;
	PopUpManager.centerPopUp(editWindow);	  
}








//fault handler
public function onFailure(event:FaultEvent):void
{
	Alert.show(event.fault+"",commonFunction.getMessages('error'),0,null,null,errorIcon);  	
}




// ActionScript file
