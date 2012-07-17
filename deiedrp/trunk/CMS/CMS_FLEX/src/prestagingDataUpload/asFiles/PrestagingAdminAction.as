/**
 * @(#) PrestagingAdminAction.as
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
import flash.events.IOErrorEvent;
import flash.net.FileReference;
import flash.net.URLRequest;

import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import prestagingDataUpload.SuccessAlert;


public var downloadFileRef:FileReference;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]public var fileName:String="";
[Bindable]public var urlStream:URLStream;
[Bindable]public var urlPrefix:String;
[Bindable]public var filePath:String;
[Bindable]public var filePath2:String;
[Bindable]public var params:Object={};
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;

// Change Done By Dheeraj For By-Passing The Logic of Enrollment Form
// Starts
public var getSuccessList:SuccessAlert=new SuccessAlert();
// Ends
//On creation complete of page
public function OnCreatPage():void
{
	urlPrefix=commonFunction.getConstants('url')+"/prestagingData/";
	filePath=commonFunction.getConstants('url')+"/PrestagingDataDocuments/";
	filePath2=commonFunction.getConstants('url')+"/UploadedPrestagingDataDocuments/";
}

//on change of student type combo for uploading sheet
public function onUploadStudentTypeChange(event:Event):void
{
	params['time']=new Date;
	params['mode']="all";
	getEntityList.send(params);
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	programCombo.enabled=false;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
    downloadButton.enabled=false;
    submitButton.enabled=false;
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
    downloadButton.enabled=false;
    submitButton.enabled=false;
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
	specilizationCombo.enabled=false;
    downloadButton.enabled=false;
    submitButton.enabled=false;
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
	downloadButton.enabled=false;
    submitButton.enabled=false;
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
	downloadButton.enabled=false;
    submitButton.enabled=false;
	
	fileName=entityXml.Details.(name==entityCombo.selectedLabel).id;
	fileName+="_"+programXml.Details.(name==programCombo.selectedLabel).id;
	fileName+="_"+branchXml.Details.(name==branchCombo.selectedLabel).id;
	fileName+="_"+specializationXml.Details.(name==specilizationCombo.selectedLabel).id+"_prestagingData.xls";
	
	urlStream=new URLStream();
	urlStream.addEventListener(Event.COMPLETE, streamHandler);
	urlStream.addEventListener(IOErrorEvent.IO_ERROR, onIOError);
	urlStream.load(new URLRequest(filePath+uploadStudentType.selectedLabel+"/"+fileName));
}

//on click of download file
public function downloadFile(event:Event):void
{
	downloadFileRef=new FileReference;
	downloadFileRef.download(new URLRequest(filePath+uploadStudentType.selectedLabel+"/"+fileName));
}

//checking availability of file
public function streamHandler(e:Event):void
{
    urlStream.close();
    downloadButton.enabled=true;
    submitButton.enabled=true;
}

//called if file is available
public function onFound(e:Event):void
{
    urlStream.close();
    Alert.show(commonFunction.getMessages('fileAndDataAlreadyUploaded'));
	downloadButton.enabled=false;
    submitButton.enabled=false;
}

//called if file is not available
public function onNotFound(event:IOErrorEvent):void
{
    urlStream.close();
    Alert.show(commonFunction.getMessages('fileNotUploaded'));
    downloadButton.enabled=false;
    submitButton.enabled=false;
}

//on IO Error
public function onIOError(event:IOErrorEvent):void
{
	urlStream=new URLStream();
	urlStream.addEventListener(Event.COMPLETE, onFound);
	urlStream.addEventListener(IOErrorEvent.IO_ERROR, onNotFound);
	urlStream.load(new URLRequest(filePath2+uploadStudentType.selectedLabel+"/"+fileName));
}

//On clik of submit 
public function submitData(event:Event):void
{	
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
}

//Insert data confirmation handler
public function insertOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		params['fileName']=fileName;
		params['studentType']=uploadStudentType.selectedLabel+"/";
		params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
		params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
		params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
		params['specializationId']=specializationXml.Details.(name==specilizationCombo.selectedLabel).id;
		params['time']=new Date;
		if(uploadStudentType.selectedLabel.toLowerCase()=='new')
		{
			setPrestagingData.send(params);
		}
		else if(uploadStudentType.selectedLabel.toLowerCase()=='existing')
		{
			setPrestagingDataForExisting.send(params);
		}
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

//Set data success handler
public function setPrestagingDataSuccess(event:ResultEvent):void
{
	// Change Done By Dheeraj For By-Passing The Logic of Enrollment Form
	// Starts
	Mask.close();
	var successList:SuccessAlert=SuccessAlert(PopUpManager.createPopUp(this,SuccessAlert,false));
	getSuccessList=successList;
	getSuccessList.dataArray=event.result.student;
	transferInPrestaging.send([new Date]);
   	// Ends
	
}

//Set data success handler
public function setPrestagingDataForOldSuccess(event:ResultEvent):void
{
	Mask.close();
	if(String(event.result.count)=="true")
	{
		Alert.show("Record uploaded succesfully",commonFunction.getMessages('success'),0,null,null,successIcon);  
	}else
	{
		Alert.show("Some records rejected by upload process",commonFunction.getMessages('error'),0,null,null,errorIcon);  
	}
	
	uploadStudentType.selectedIndex=-1;
	entityCombo.selectedIndex=-1;
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	programCombo.enabled=false;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
    downloadButton.enabled=false;
    submitButton.enabled=false;
}
	// Function Added By Dheeraj For By-Passing The Logic of Enrollment Form
	// Starts
	public function transferInPrestagingResult(event:ResultEvent):void{
		
		PopUpManager.centerPopUp(getSuccessList);
		
		entityCombo.selectedIndex=-1;
		programCombo.selectedIndex=-1;
		branchCombo.selectedIndex=-1;
		specilizationCombo.selectedIndex=-1;
		programCombo.enabled=false;
		branchCombo.enabled=false;
		specilizationCombo.enabled=false;
	    downloadButton.enabled=false;
	    submitButton.enabled=false;
	}
	// Ends

//fault handler
public function onFailure(event:FaultEvent):void
{
	Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'),0,null,null,errorIcon);  	
}