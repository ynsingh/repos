// ActionScript file
import common.Mask;
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

[Bindable]public var urlPrefix1:String=commonFunction.getConstants('url')+"/stagingTableReport/";
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
		Mask.show();
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
		Mask.show();
	}
	catch(e:Error){}
	branch.selectedIndex=-1;
	specialization.selectedIndex=-1;
	semester.selectedIndex=-1;
	branch.enabled=false;
	specialization.enabled=false;
	semester.enabled=false;
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
		Mask.show();
	}
	catch(e:Error){}
	specialization.selectedIndex=-1;
	semester.selectedIndex=-1;
	specialization.enabled=false;
	semester.enabled=false;
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
		Mask.show();
	}
	catch(e:Error){}
		semester.selectedIndex=-1;
		semester.enabled=false;
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
		Mask.show();
	}
	catch(e:Error){}
	UIComponent(specialization).errorString="";
}

/**
 * Method to fire the change event of the Semester Combo
 * This Method send request for getting Session
 **/
 
public function onSemesterChange():void{
	UIComponent(semester).errorString="";
}
/**
 * Failure Handler
 * @Param Event(FaultEvent)
 **/
public function onFailure(event:FaultEvent):void{
	Mask.close();
	 Alert.show("Error in Staging table report Generation: "+event);
}

/**
 * getOwnerEntity Success Handler
 * @Param Event(ResultEvent)
 **/
public function getOwnerEntitySuccess(event:ResultEvent):void{
	try{
		Mask.close();
		if(event.result.sessionConfirm == true){
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
		Mask.close();
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
		Mask.close();
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
		Mask.close();
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
		Mask.close();
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
 * Method to confirm insertion of record
 * This Method send request for insert Records on server
 * @param Event(Close Event)
 **/
 
public function submitConfirm(ev:CloseEvent):void{
	if(ev.detail==Alert.YES){
		try{
		params["date"]=new Date();
		params["entityId"]=entityName.selectedItem;
		params["entityName"] = entityName.selectedLabel;
		params["programId"]=programName.selectedItem;
		params["programName"]=programName.selectedLabel;
         params["programCode"]=programName.selectedItem.@code;
		//params["programCode"] = programXml.programs.(programId=programName.selectedItem).programCode;
		params["branchId"]=branch.selectedItem;
		params["branchName"]=branch.selectedLabel;
		params["specializationId"]=specialization.selectedItem.toString();
		params["specialization"]=specialization.selectedLabel;
		params["semesterCode"] = semester.selectedItem;
		params["semesterName"]=semester.selectedLabel;
		params["processFlagName"]=processFlagCombo.selectedItem.label;
		params["processFlag"]=processFlagCombo.selectedItem.data;
		generateReport.send(params);
		Mask.show();
	}
	catch(e:Error){}
	}
}


/**
 * generateReport Success Handler
 * @Param Event(ResultEvent)
 **/
public function setGenerateReportSuccess(event:ResultEvent):void{
	try
	{
		Mask.close();
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
			Alert.show(commonFunction.getMessages('reportGeneratedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);     
			resetForm();      
	}
	else{
		 Alert.show("Error: "+event.result.Exception,null,0,null,null,errorIcon);         
 	}

}


/**
 * Method to Validate The Fields
 * @return bol(True/False)
 **/
 
public function validateForm():Boolean{
	var bol:Boolean=true;
	if(Validator.validateAll([entityNameVal,programNameVal,branchVal,specializationVal,semesterVal]).length>0){
		bol=false;
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),null,null,null,errorIcon);
	}
	return bol;
}

/**
 * Method to fire click event of the Submit Button
 **/
public function submitData():void{
//	Alert.show(processFlagCombo.selectedItem.label+" data "+processFlagCombo.selectedItem.data);
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

/** reset form data **/
public function resetForm():void {
	entityName.selectedIndex=-1;
	programName.selectedIndex=-1;
	branch.selectedIndex=-1;
	specialization.selectedIndex=-1;
	semester.selectedIndex=-1;
	processFlagCombo.selectedIndex=-1;	
	programName.enabled=false;
	branch.enabled=false;
	specialization.enabled=false;
	semester.enabled=false;
	generateReportButton.enabled=false;
	downloadId.enabled=false;
	printId.enabled=false;
	processFlagCombo.enabled=false;	
    entityName.errorString="";
    programName.errorString="";
    branch.errorString="";
    specialization.errorString="";
    semester.errorString="";
}

/**
  * This method calls on the click of any button(generate, download, print)
 **/
 		
	public var downloadUrl:URLRequest = new URLRequest();	
	public var fileRequest:FileReference = new FileReference();
	public function onButtonClick(event:Event):void{
		var entiName:String=String(entityName.selectedItem);
		var entiName:String = entityName.selectedLabel;
		var progName:String=programName.selectedLabel;
		var brName:String=branch.selectedLabel;
		var speName:String=specialization.selectedLabel;
		var semCode:String = String(semester.selectedItem);
		var procFlagName:String=processFlagCombo.selectedItem.label;
		var toolTipAction:String = event.currentTarget.toolTip;
		var reportName = entiName+"_"+progName+"_"+brName+"_"+speName+"_"+semCode+"_"+procFlagName;
		var path:String=commonFunction.getConstants('url')+"/REPORTS/StagingTable/"+reportName+".pdf";
		downloadUrl.url = path;
		configureListeners(fileRequest);
		if(event.currentTarget.toolTip==commonFunction.getConstants('downloadreport')){		
			downloadUrl.url = path;
			Alert.show(commonFunction.getMessages('downloadConfirmation'),
			commonFunction.getMessages('success'),(Alert.YES|Alert.NO),null,downloadFile,successIcon,1);	
		}
	
		if(toolTipAction==commonFunction.getConstants('printReport')){			
			Alert.show("Do You want to Print the Report ?",
			commonFunction.getMessages('success'),(Alert.YES|Alert.NO),null,printFile,successIcon,1);	
		}
	}

	public function downloadFile(event:CloseEvent):void{
		if(event.detail==Alert.YES){
			fileRequest.download(downloadUrl);		
		}	
	}

	public function printFile(event:CloseEvent):void{
		if(event.detail==Alert.YES){
			Mask.show();
	//		printReport.send(object);
	//		navigateToURL(downloadUrl);		
		}	
	}

 	private function configureListeners(dispatcher:IEventDispatcher):void {
        dispatcher.addEventListener(Event.CANCEL, cancelHandler);
        dispatcher.addEventListener(Event.COMPLETE, completeHandler);
        dispatcher.addEventListener(IOErrorEvent.IO_ERROR, ioErrorHandler);
        dispatcher.addEventListener(SecurityErrorEvent.SECURITY_ERROR, securityErrorHandler);
    }
        
    private function cancelHandler(event:Event):void {
        Alert.show("cancelHandler: " + event);
    }

    private function completeHandler(event:Event):void {
       // Alert.show("completeHandler: " + event);
    }

    private function ioErrorHandler(event:IOErrorEvent):void {
        Alert.show("Please Check...the report is not yet generated...");
    }

    private function securityErrorHandler(event:SecurityErrorEvent):void {
        Alert.show("securityErrorHandler: " + event);
    }
