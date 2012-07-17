/**
 * @(#) ConsolidatedChartAction.as
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

import flash.events.Event;
import flash.net.FileReference;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;


public var downloadFileRef:FileReference;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

[Bindable]public var fileName:String="";
[Bindable]public var urlStream:URLStream;
[Bindable]public var urlPrefix:String;
[Bindable]public var urlPrefix1:String;
[Bindable]public var params:Object={};
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;
[Bindable]public var semesterXml:XML;
[Bindable]public var sessionXml:XML;
[Bindable]public var startDate:String;
[Bindable]public var endDate:String;
[Bindable]public var selDateIndex:int=-1;
[Bindable]public var datesArray:ArrayCollection;
		
//On creation complete of page
public function OnCreatPage():void
{
	urlPrefix=commonFunction.getConstants('url')+"/prestagingData/";
	urlPrefix1=commonFunction.getConstants('url')+"/consolidatedChart/";
	
	params['time']=new Date;
	params['mode']="all";
	getEntityList.send(params);
	getSessionList.send(params);
}

//get session list success handler
public function getSessionSuccess(event:ResultEvent):void
{
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	sessionXml=event.result as XML;
	datesArray=new ArrayCollection;
	var i:int=0;
	for each(var obj:Object in sessionXml.role)
	{
		if((obj.id+""==startDate)&&(obj.description+""==endDate))
		{
			selDateIndex=i;
		}
		i++;
		datesArray.addItem({startDate:String(obj.id+"").substring(0,4),endDate:String(obj.description+"").substring(0,4),sd:obj.id,ed:obj.description});
	}
	startDateField.dataProvider=datesArray;
	startDateField.labelField="startDate";
	startDateField.selectedIndex=selDateIndex;
}

//get entity list success handler
public function getEntitySuccess(event:ResultEvent):void
{
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	entityXml=event.result as XML;
	entityCombo.dataProvider=entityXml.Details.name;
	entityCombo.selectedIndex=-1;
}

//on change of entity
public function onEntityChange(event:Event):void
{
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
	getProgramList.send(params);
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	semesterCombo.selectedIndex=-1;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
	semesterCombo.enabled=false;
	startDateField.enabled=false;
	startDateField.selectedIndex=-1;
	endDateField.text="";
    generateButton.enabled=false;
}

//get program list success handler
public function getProgramSuccess(event:ResultEvent):void
{
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
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
	semesterCombo.selectedIndex=-1;
	specilizationCombo.enabled=false;
	semesterCombo.enabled=false;
	startDateField.enabled=false;
    startDateField.selectedIndex=-1;
	endDateField.text="";
    generateButton.enabled=false;
}

//get branch list success handler
public function getBranchSuccess(event:ResultEvent):void
{	
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
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
	semesterCombo.selectedIndex=-1;
	semesterCombo.enabled=false;
	startDateField.enabled=false;
    startDateField.selectedIndex=-1;
	endDateField.text="";
    generateButton.enabled=false;
}

//get specialization success handler
public function getSpecializationSuccess(event:ResultEvent):void
{
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	specializationXml=event.result as XML;
	specilizationCombo.dataProvider=specializationXml.Details.name;
	specilizationCombo.selectedIndex=-1;
	specilizationCombo.enabled=true;
}

//On change of specialization
public function onSpecializationChange(event:Event):void
{
	params['time']=new Date;
	params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
	params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
	params['specializationId']=specializationXml.Details.(name==specilizationCombo.selectedLabel).id;
	getSemesterList.send(params);
	startDateField.enabled=false;
    startDateField.selectedIndex=-1;
	endDateField.text="";
    generateButton.enabled=false;
}

//get semester list success handler
public function getSemesterSuccess(event:ResultEvent):void
{
	
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	   	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	semesterXml=event.result as XML;
	semesterCombo.dataProvider=semesterXml.role.description;
	semesterCombo.selectedIndex=-1;
	semesterCombo.enabled=true;
}

//fault handler
public function onFailure(event:FaultEvent):void
{
	Mask.close();
	Alert.show(event.message+"",commonFunction.getMessages('error'),0,null,null,errorIcon);
}

//fault handler
public function onFailureGenerate(event:FaultEvent):void
{
	Mask.close();
	Alert.show("Consolidated Chart Generated Successfully.",commonFunction.getMessages('success'),0,null,null,successIcon);
	resetForm();
}

//on change of semester
public function onSemesterChange(event:Event):void
{
    generateButton.enabled=false;
    startDateField.enabled=true;
	startDateField.selectedIndex=-1;
	endDateField.text="";
}

//on change of semester start date
public function onStartDateChange():void
{
	endDateField.text=String(int(startDateField.selectedLabel)+1);
    generateButton.enabled=true;
}

public function generateChart():void
{
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,generateOrNot,questionIcon);
}

public function generateOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		params['time']=new Date;
		params['entity']=entityCombo.selectedLabel;
		params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
		params['entityCode']=entityXml.Details.(name==entityCombo.selectedLabel).code;
		params['program']=programCombo.selectedLabel;
		params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
		params['branch']=branchCombo.selectedLabel;
		params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
		params['specialization']=specilizationCombo.selectedLabel;
		params['specializationId']=specializationXml.Details.(name==specilizationCombo.selectedLabel).id;
		params['semester']=semesterCombo.selectedLabel;
		params['semesterId']=semesterXml.role.(description==semesterCombo.selectedLabel).id;
		params['startDate']="";
		params['endDate']="";
		
		for each(var obj:Object in datesArray)
		{
			if((obj.startDate+""==startDateField.selectedLabel)&&(obj.endDate+""==endDateField.text))
			{
				params['startDate']=obj.sd;
				params['endDate']=obj.ed;
			}
		}
		
		Mask.show();
		generateConsolidatedChart.send(params);
	}	
}

//get semester list success handler
public function generateConsolidatedChartSuccess(event:ResultEvent):void
{
	Mask.close();
	if(event.result.resultdata=="NoData"){
		Alert.show(commonFunction.getMessages('noDataForConsolidatedChart'),commonFunction.getMessages('info'),0,null,null,infoIcon);
	}
	else{
		Alert.show(commonFunction.getMessages('consolidatedChartSuccess'),commonFunction.getMessages('success'),0,null,null,successIcon);
	}
	resetForm();
}

public function resetForm():void
{
	entityCombo.selectedIndex=-1;
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	semesterCombo.selectedIndex=-1;
	startDateField.selectedIndex=-1;
	endDateField.text="";
    programCombo.enabled=false;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
	semesterCombo.enabled=false;
	startDateField.enabled=false;
	generateButton.enabled=false;
}