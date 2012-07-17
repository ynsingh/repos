/**
 * @(#) PrestagingAction.as
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
 import common.*;
 
 import flash.events.DataEvent;
 import flash.events.Event;
 import flash.events.IOErrorEvent;
 import flash.net.FileReference;
 import flash.net.URLStream;
 
 import mx.controls.Alert;
 import mx.rpc.events.FaultEvent;
 import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

private var uploadUrl:String;
private var downloadUrl:String;
public var uploadFileref:FileReference;
public var downloadFileRef:FileReference;

[Bindable]public var fileName:String="";
[Bindable]public var urlStream:URLStream;
[Bindable]public var filePath:String;
[Bindable]public var filePath2:String;

[Bindable]public var urlPrefix:String;
[Bindable]public var params:Object={};
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;

//On creation complete of page
public function onPageCreate():void
{
	urlPrefix=commonFunction.getConstants('url')+"/prestagingData/";
	uploadUrl=commonFunction.getConstants('url')+"/fileUpload/upload.jsp?fileName=";
	downloadUrl=commonFunction.getConstants('url')+commonFunction.getConstants('prestagingTemplateUrl')+"/PrestagingDataSheetFor";
	
	filePath=commonFunction.getConstants('url')+"/PrestagingDataDocuments/";
	filePath2=commonFunction.getConstants('url')+"/UploadedPrestagingDataDocuments/";
}

//on change of student type for downloading sheet
public function onStudentTypeChange(event:Event):void
{
	downloadButton.enabled=false;
    urlStream=new URLStream;
	urlStream.addEventListener(Event.COMPLETE, onAvailable);
	urlStream.addEventListener(IOErrorEvent.IO_ERROR, onIOError);
	urlStream.load(new URLRequest(downloadUrl+studentType.selectedLabel+".xls"));
}

//Called if data template is available
public function onAvailable(e:Event):void
{
    urlStream.close();
    downloadButton.enabled=true;
}

//Called if data template is not available
public function onIOError(ev:IOErrorEvent):void
{
	urlStream.close();
    Alert.show(commonFunction.getMessages('fileMissingForGivenStudent'),commonFunction.getMessages('error'),0,null,null,errorIcon);
}

//on click of download button
public function onDownload():void
{
	downloadFileRef=new FileReference;
	downloadFileRef.addEventListener(Event.CANCEL,onCancel);
	downloadFileRef.addEventListener(Event.CLOSE,onCancel);
	downloadFileRef.addEventListener(Event.COMPLETE,onCancel);
	downloadFileRef.download(new URLRequest(downloadUrl+studentType.selectedLabel+".xls"),"PrestagingDataTemplateFor"+studentType.selectedLabel+".xls");
}

//on click of cancel
public function onCancel(event:Event):void
{
	studentType.selectedIndex=-1;
	downloadButton.enabled=false;
}

//on change of student type combo for uploading sheet
public function onUploadStudentTypeChange(event:Event):void
{
	params['time']=new Date;
	getEntityList.send(params);
	onReset(event);
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	
	programCombo.enabled=false;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
	
	uploadPanel.enabled=false;
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
	onReset(event);
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
	uploadPanel.enabled=false;
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
	onReset(event);
	specilizationCombo.selectedIndex=-1;
	specilizationCombo.enabled=false;
	uploadPanel.enabled=false;
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
	onReset(event);
	uploadPanel.enabled=false;
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
	fileName=entityXml.Details.(name==entityCombo.selectedLabel).id;
	fileName+="_"+programXml.Details.(name==programCombo.selectedLabel).id;
	fileName+="_"+branchXml.Details.(name==branchCombo.selectedLabel).id;
	fileName+="_"+specializationXml.Details.(name==specilizationCombo.selectedLabel).id+"_prestagingData.xls";
	
	urlStream=new URLStream;
	urlStream.addEventListener(Event.COMPLETE, onFound);
	urlStream.addEventListener(IOErrorEvent.IO_ERROR, onNotFound);
	urlStream.load(new URLRequest(filePath+uploadStudentType.selectedLabel+"/"+fileName));
}

//fault handler
public function onFailure(event:FaultEvent):void
{
	Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'),0,null,null,errorIcon);  	
}

//Called if file already uploaded for selected combination
public function onFound(e:Event):void
{
    urlStream.close();
    uploadPanel.enabled=false;
    Alert.show(commonFunction.getMessages('fileAlreadyUploaded'));
	onReset(e);
}

//Called if data already uploaded for selected combination
public function onFinalFound(e:Event):void
{
    urlStream.close();
    uploadPanel.enabled=false;
    Alert.show(commonFunction.getMessages('fileAndDataAlreadyUploaded'));
	onReset(e);
}

//Called if niether file nor data already uploaded for selected combination
public function onNotFound(event:IOErrorEvent):void
{
	urlStream=new URLStream;
	urlStream.addEventListener(Event.COMPLETE, onFinalFound);
	urlStream.addEventListener(IOErrorEvent.IO_ERROR, onFinalNotFound);
	urlStream.load(new URLRequest(filePath2+uploadStudentType.selectedLabel+"/"+fileName));
}

public function onFinalNotFound(event:IOErrorEvent):void
{
	urlStream.close();
    uploadPanel.enabled=true;
    var e:Event;
	onReset(e);
}

//on click of browse button
public function onBrowse(event:Event):void
{
	uploadFileref=new FileReference;
	uploadFileref.addEventListener(Event.SELECT,onFileSelect);
	uploadFileref.addEventListener(ProgressEvent.PROGRESS,showProgress);
	uploadFileref.browse([new FileFilter("Excel File(.xls,.xlsx)","*.xls;*.xlsx")]);
}

//file select handler
public function onFileSelect(event:Event):void
{
	uploadFileref.addEventListener(Event.COMPLETE,onLoadComplete);
	uploadFileref.addEventListener(Event.CANCEL,onReset);
	uploadFileref.load();
}

//File load complete handler
public function onLoadComplete(event:Event):void
{
	resetButton.enabled=true;
	uploadButton.enabled=true;
	browseButton.enabled=false;
}

//Show progress on progress bar
public function showProgress(event:ProgressEvent):void
{
	if(event.bytesLoaded==event.bytesTotal){
		fileLabel.text="Loading Done.";
	}else{
		fileLabel.text="Loading File Please Wait...";
	}
	uploadFileName.setProgress(event.bytesLoaded, event.bytesTotal);
}

//on click of reset button
public function onReset(event:Event):void
{
	resetButton.enabled=false;
	uploadButton.enabled=false;
	browseButton.enabled=true;
	uploadFileName.setProgress(0,100);
	uploadFileref=new FileReference();
	fileLabel.text=commonFunction.getConstants('selectExcelToUpload');
}

//on click of upload button
public function onUpload(event:Event):void
{
	uploadFileref.addEventListener(DataEvent.UPLOAD_COMPLETE_DATA,onUploadComplete);
	uploadFileref.upload(new URLRequest(uploadUrl+fileName+"&folder=PrestagingDataDocuments/"+uploadStudentType.selectedLabel+"/"));
}

public function onUploadComplete(event:DataEvent):void
{
	Alert.show(commonFunction.getMessages('successOnUpload'),commonFunction.getMessages('success'),0,null,null,successIcon);
	onReset(event);
	uploadPanel.enabled=false;
	uploadStudentType.selectedIndex=-1;
	entityCombo.selectedIndex=-1;
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
}