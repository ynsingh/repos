/**
 * @(#) ResultVerificationReportAction.as
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
import RequestVerification.ResultVerification;

import common.Mask;

import mx.collections.*;
import mx.controls.*;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Bindable]public var urlPrefix:String="";

public var params:Object={};
public var requestTypeXml:XML;
[Bindable]public var sanctionList:ArrayCollection=new ArrayCollection();
[Bindable]public var recordList:ArrayCollection=new ArrayCollection(); 

public function onCreationComplete():void{
	urlPrefix=resourceManager.getString('Constants','url')+"/resultVerification/";
	detail1.visible=false;	
	params["time"] = new Date;
	getRequestType.send(params);
	Mask.show();
	//to populate the request type combo
}

/** this method calls request completes**/
public var requestTypeCollection:ArrayCollection=new ArrayCollection();
public function getRequestTypeSuccess(event:ResultEvent):void{
	try{
		if(event.result.sessionConfirm == true){
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
	requestTypeXml=event.result as XML;
	for each(var obj:Object in requestTypeXml.requestType){
		requestTypeCollection.addItem({requestTypeCode:obj.requestTypeCode,requestTypeName:obj.requestTypeName});
	}
	requestTypeCombo.dataProvider = requestTypeCollection;
	if(requestTypeCollection.length==1){
		requestTypeCombo.selectedIndex=0;
	}
	 Mask.close();
}


/** for ok button **/
public function ok():void{
	if(Validator.validateAll([requestTypevalidator]).length==0){
		Mask.show();
    	recordList.removeAll();
		params["requestType"] = requestTypeCollection.getItemAt(requestTypeCombo.selectedIndex).requestTypeCode;
		params["companyName"] = '';
		params["receiveDate"]='';
		params["time"] = new Date;
		getRequestSearchService.send(params);
    }
 	else{
 		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),null,0,null,null,errorIcon);
 	}
} 	

/** 
 * this method get all request details from database
 **/
 
public var recordsDetails:XML;

public function getRequestSearchServiceSuccess(event:ResultEvent):void{		
	Mask.close();
	try{
		if(event.result.sessionConfirm == true){
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
	recordsDetails=event.result as XML;	 	
 	recordList=new ArrayCollection(); 	
 	for each (var o:Object in recordsDetails.detail){			
			recordList.addItem({select:false,requestNo:o.requestNo,requestType:o.requestType,requester:o.requester,receiveDate:o.receiveDate,
			processDate:o.processDate,processStatus:o.processStatus,refNo:o.refNo,refDate:o.refDate,compName:o.compName,compAdd:o.compAdd});			
	} 
	if(recordList.length>0){
		detail1.visible=true;
	}	 	
 	DetailGrid1.dataProvider=recordList;	
}

public function resetFeild():void{
	requestTypeCombo.selectedIndex=-1;
	recordList.removeAll();
	detail1.visible=false;
}

public function faultHandler(event:FaultEvent):void{
	Mask.close();
	 Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'));
}

/** this method open edit manage result verification window **/
public function editSwitch():void {
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(DetailGrid1);
	if(selectedValues.length>1){
		Alert.show(resourceManager.getString('Messages','selectOneRecord'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else{
		if(selectedValues[0].processStatus=='P'|| selectedValues[0].processStatus=='p'){
			Alert.show(resourceManager.getString('Messages','alreadyProcessed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
		else{
			params["requestNo"] = selectedValues[0].requestNo;
			params["time"]=new Date();
			getRequestDetail.send(params);
		}		
	}	
//	editWindow.buttonFunction=onCreationComplete;
}

/** 
 * this method get all request details from database
 **/
 
public var requestRecordsDetails:XML;
public var rollNumberList:ArrayCollection=new ArrayCollection();
public function getRequestDetailSuccess(event:ResultEvent):void{		
	Mask.close();
	try{
		if(event.result.sessionConfirm == true){
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
	requestRecordsDetails=event.result as XML;	 	
 	rollNumberList=new ArrayCollection(); 	
 	for each (var o:Object in requestRecordsDetails.rollNo){			
			rollNumberList.addItem({rollNumber:o.rollNumber});			
	} 
	createPop(rollNumberList.length,rollNumberList);	
}

public function createPop(arrSize:int, rollNumberList:ArrayCollection):void{
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(DetailGrid1);
	var editWindow:ResultVerification=ResultVerification(PopUpManager.createPopUp(this,ResultVerification,true));
	editWindow.requestNumber=selectedValues[0].requestNo;
	editWindow.requester.text=selectedValues[0].requester;
	editWindow.compName.text=selectedValues[0].compName;
	editWindow.compAdd.text=selectedValues[0].compAdd;
	editWindow.receiveDate.text=selectedValues[0].receiveDate;
	editWindow.refNo.text=selectedValues[0].refNo;
	editWindow.refDate.text=selectedValues[0].refDate;
	editWindow.selectedValues=selectedValues;
	editWindow.detail.visible=true;
	editWindow.vbox.visible=true;
	editWindow.genButton.visible=false;
	editWindow.updateButton.visible=true;
	editWindow.requestTypeCode=selectedValues[0].requestType;	
	if(arrSize>0){
		editWindow.arrSize=arrSize;
		editWindow.selRollNoArray=rollNumberList;
	}	
	PopUpManager.centerPopUp(editWindow);
} 
/** this method delete result verification requests **/
public function deleteSwitch():void {	
	Alert.show(commonFunction.getMessages('deleteConfirmMessage'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onDelete,questionIcon);		
}

public function onDelete(event:CloseEvent):void{	
	if(event.detail==Alert.YES){		
		var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(DetailGrid1);
		var deleteRecords:Array=new Array();
		var delFlag:String='false';
		for each(var obj:Object in selectedValues){	
			if(obj.processStatus=='P' || obj.processStatus=='p'){				
				delFlag='true';
				break;
			}
			else{
				deleteRecords.push(obj.requestNo);
			}				
//			deleteRecords.addItem(obj.requestNo);			
		}	
		if(delFlag=='true'){
			Alert.show(resourceManager.getString('Messages','alreadyProcessed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
		else{
			params["deleteRecords"] = deleteRecords;		
			deleteRequestRecords.send(params);
			Mask.show();
		}		
	}	 
}

/**
 * Method to be executed on successful deletion of selected records
 **/ 
public var successDetails:XML;
public function deleteRequestRecordsSuccess(event:ResultEvent):void{
	Mask.close();
	try{
		if(event.result.sessionConfirm == true){
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){} 	
 	var result:String=(event.result.info.message+"") 	
 	var lengthResult:Number = result.length;
 	if(result=="true"){
 		Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
 		ok();
 	}
 	else{
 		Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
 	}
}

  	
/** this function remove manage sanction detail panel **/ 
public function cancel():void{
	parent.removeChild(this);
} 

/**
  * This method calls on the click of any button(generate, download, print)
 **/
 		
public function onButtonClick(event:Event):void{
	params["requestNo"] = DetailGrid1.selectedItem.requestNo;
	params["requestType"] = DetailGrid1.selectedItem.requestType;
	params["requester"] = DetailGrid1.selectedItem.requester;
	params["compName"] = DetailGrid1.selectedItem.compName;
	params["compAdd"] = DetailGrid1.selectedItem.compAdd;
	params["receiveDate"] = DetailGrid1.selectedItem.receiveDate;
	params["refNo"] = DetailGrid1.selectedItem.refNo;
	params["refDate"] = DetailGrid1.selectedItem.refDate;
	if(event.currentTarget.toolTip==commonFunction.getConstants('generateReport')){
		generateReport.send(params);
	}	
}

/**
 * Method to be executed on successful generation of report
 **/ 
public function generateReportSuccess(event:ResultEvent):void{
	Mask.close();
	try{
		if(event.result.sessionConfirm == true){
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){} 	
 	var result:String=(event.result.info.message+"") 	
// 	var lengthResult:Number = result.length;
 	if(result=="true"){
 		Alert.show(commonFunction.getMessages('reportGeneratedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
 		//ok();
 	}
 	else{
 		Alert.show(commonFunction.getMessages('failToGenerate'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
 	}
}
