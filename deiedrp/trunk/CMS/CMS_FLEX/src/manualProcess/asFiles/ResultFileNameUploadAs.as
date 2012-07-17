// ActionScript file
import common.commonFunction;

import mx.controls.Alert;
import mx.core.UIComponent;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/question.png")] var questionIcon:Class;
[Embed(source="/images/error.png")] var errorIcon:Class;
[Embed(source="/images/success.png")] var successIcon:Class;

[Bindable]public var urlPrefix:String=commonFunction.getConstants('url')+"/manualProcess/";

[Bindable]public var urlPrefix1:String=commonFunction.getConstants('url')+"/UploadStudentNameInfo/";
[Bindable]public var entityXml:XML;


public var params:Object={};

public function creationComplete():void{
	try{
		getOwnerEntity.send([new Date]);
	}
	catch(e:Error){}
}

/**
 * getOwnerEntity Success Handler
 * @Param Event(ResultEvent)
 **/
public function getOwnerEntitySuccess(event:ResultEvent):void{
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
	entityXml=event.result as XML;
	entityName.dataProvider=entityXml.entity;
}


/**
 * Method to fire click event of the Submit Button
 **/
public function submitData():void{
	if(validateForm()){
		Alert.show(commonFunction.getMessages('doYouWantContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,submitConfirm,questionIcon);
	}
}

/**
 * Method to Validate The Fields
 * @return bol(True/False)
 **/
 
public function validateForm():Boolean{
	var bol:Boolean=true;
	if(Validator.validateAll([fileNameVal,entityNameVal]).length>0){
		bol=false;
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),null,null,null,errorIcon);
	}
	return bol;
}

/**
 * Method to confirm insertion of record
 * This Method send request for insert Records on server
 * @param Event(Close Event)
 **/
 
public function submitConfirm(ev:CloseEvent):void{
	if(ev.detail==Alert.YES){
		try{
		params["date"]=new Date();
		params["fileName"] = fileNameText.text;
		params["entityId"]=entityName.selectedItem;
		setStudentNameEntry.send(params);
	}
	catch(e:Error){}
	}
}

/**
 * setManualEntry Success Handler
 * @Param Event(ResultEvent)
 **/
public function setStudentNameEntrySuccess(event:ResultEvent):void{
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
 	var status:String=(event.result.Exception+"");
// 	Alert.show(status);
// 	if(status=="true"){
//			Alert.show(commonFunction.getMessages('fileUploadSuccess'),commonFunction.getMessages('success'),0,null,null,successIcon);
//	}
//	else{
		 Alert.show(event.result.Exception,commonFunction.getMessages('success'),0,null,null,successIcon);
// 	}
}

/**
 * Failure Handler
 * @Param Event(FaultEvent)
 **/
public function onFailure(event:FaultEvent):void{
	 Alert.show("Error in student Name Info Upload Process: "+event);
}

/**
 * Method to fire click event of the Cancel Button
 **/
public function exit():void{
	parent.removeChild(this);
}