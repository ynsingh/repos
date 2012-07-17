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

[Bindable]public var urlPrefix1:String=commonFunction.getConstants('url');
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;
[Bindable]public var semesterXml:XML;
[Bindable]public var sessionXml:XML;

public var params:Object={};

public function creationComplete():void{
	try{
		getOwnerEntity.send([new Date]);
	}
	catch(e:Error){}
}
/**
 * Method to fire the change event of the entityName Combo
 * This Method send request for getting program Name and Id
 **/
public function onEntityChange():void{
	try{
		params["date"]=new Date();
		params["entityId"]=entityName.selectedItem;
		getProgramName.send(params);
	}
	catch(e:Error){}
	branch.selectedIndex=-1;
	specialization.selectedIndex=-1;
	semester.selectedIndex=-1;
	session.selectedIndex=-1;
	branch.enabled=false;
	specialization.enabled=false;
	semester.enabled=false;
	session.enabled=false;
	UIComponent(entityName).errorString="";
}

/**
 * Method to fire the change event of the Program Name Combo
 * This Method send request for getting Branch Name and Id
 **/
public function onProgramChange():void{
	programName.toolTip=programName.selectedLabel;
	try{
		params["date"]=new Date();
		params["programId"]=programName.selectedItem;
		getBranch.send(params);
	}
	catch(e:Error){}
	specialization.selectedIndex=-1;
	semester.selectedIndex=-1;
	session.selectedIndex=-1;
	specialization.enabled=false;
	semester.enabled=false;
	session.enabled=false;
	UIComponent(programName).errorString="";
}

/**
 * Method to fire the change event of the Branch Combo
 * This Method send request for getting Specialization Name and Id
 **/
public function onBranchChange():void{
	try{
		params["date"]=new Date();
		params["programId"]=programName.selectedItem;
		params["branchId"]=branch.selectedItem;
		getSpecialization.send(params);
	}
	catch(e:Error){}
		semester.selectedIndex=-1;
		session.selectedIndex=-1;
		semester.enabled=false;
		session.enabled=false;
		UIComponent(branch).errorString="";
}

/**
 * Method to fire the change event of the Specialization Combo
 * This Method send request for getting semester Name and Id
 **/
public function onSpecializationChange():void{
	try{
		params["date"]=new Date();
		params["programId"]=programName.selectedItem.toString();
		params["branchId"]=branch.selectedItem.toString();
		params["specializationId"]=specialization.selectedItem.toString();
		getSemester.send(params);
	}
	catch(e:Error){}
	session.selectedIndex=-1;
	session.enabled=false;
	UIComponent(specialization).errorString="";
}

/**
 * Method to fire the change event of the Semester Combo
 * This Method send request for getting Session
 **/
 
public function onSemesterChange():void{
	getSession.send(new Date());
	UIComponent(semester).errorString="";
}
/**
 * Failure Handler
 * @Param Event(FaultEvent)
 **/
public function onFailure(event:FaultEvent):void{
	 Alert.show("Error in Manual Process: "+event);
     rollNoTxt.text="";
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
 * getProgram Success Handler
 * @Param Event(ResultEvent)
 **/
public function getProgramSuccess(event:ResultEvent):void{
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
	programXml=event.result as XML;
	programName.dataProvider=programXml.programs.programId;
}

/**
 * getBranch Success Handler
 * @Param Event(ResultEvent)
 **/
public function getBranchSuccess(event:ResultEvent):void{
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
	branch.dataProvider=branchXml.branch;
}

/**
 * getSpecialization Success Handler
 * @Param Event(ResultEvent)
 **/
public function getSpecializationSuccess(event:ResultEvent):void{
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
	specializationXml=event.result as XML;
	specialization.dataProvider=specializationXml.specialization;
}

/**
 * getSemester Success Handler
 * @Param Event(ResultEvent)
 **/
public function getSemesterSuccess(event:ResultEvent):void{
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
	semesterXml=event.result as XML;
	semester.dataProvider=semesterXml.semester;
}

/**
 * getSession Success Handler
 * @Param Event(ResultEvent)
 **/
public function getSessionSuccess(event:ResultEvent):void{
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
 	sessionXml=event.result as XML;
 	session.dataProvider=sessionXml.session;
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
		params["programId"]=programName.selectedItem;
         params["programCode"]=programName.selectedItem.@code;
		//params["programCode"] = programXml.programs.(programId=programName.selectedItem).programCode;
		params["branchId"]=branch.selectedItem;
		params["specializationId"]=specialization.selectedItem.toString();
		params["semesterCode"] = semester.selectedItem;
		params["session"] = session.selectedLabel;
        params["rollNo"] = rollNoTxt.text;
		setManualEntry.send(params);
	}
	catch(e:Error){}
	}
}


/**
 * setManualEntry Success Handler
 * @Param Event(ResultEvent)
 **/
public function setManualEntrySuccess(event:ResultEvent):void{
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
 	if(status=="true"){
			Alert.show(commonFunction.getMessages('fileUploadSuccess'),commonFunction.getMessages('success'),0,null,null,successIcon);
            rollNoTxt.text="";
	}
	else{
		 Alert.show("Error: "+event.result.Exception,null,0,null,null,errorIcon);
         rollNoTxt.text="";
 	}

}


/**
 * Method to Validate The Fields
 * @return bol(True/False)
 **/
 
public function validateForm():Boolean{
	var bol:Boolean=true;
	if(Validator.validateAll([fileNameVal,entityNameVal,programNameVal,branchVal,specializationVal,semesterVal,sessionVal]).length>0){
		bol=false;
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),null,null,null,errorIcon);
	}
	return bol;
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
 * Method to fire click event of the Cancel Button
 **/
public function exit():void{
	parent.removeChild(this);
}