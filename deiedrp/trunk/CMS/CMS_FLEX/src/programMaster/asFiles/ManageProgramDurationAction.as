
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
var pm:ProgramMaster=new ProgramMaster();
var progId:String=new String();

public function changeProgram():void
{
	programCodeText.text="";
	programCodeText.errorString="";
	minDurationText.errorString="";
	maxDurationText.errorString="";
	startDateField.errorString="";
	programCodeText.text=pm.codeXML.branch.(branchName==programNameComboBox.selectedLabel).branchCode;
}

public function showDetails():void
{
	minDurationText.errorString="";
	maxDurationText.errorString="";
	startDateField.errorString="";
//	programNameComboBox.selectedIndex=-1;
	
	if(!(programNameComboBox.selectedIndex==-1))
	{
			programNameComboBox.errorString="";	
	    httpDurationDetailsForManage();
	}
	else
	{
		programNameComboBox.errorString=resourceManager.getString('Messages','noRecordSelected');
		Alert.show(resourceManager.getString('Messages','pleasecheckEntriesinRed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
    }
}

public function updateDetails():void
{
	startDateField.errorString="";
	var validationError:Boolean=false;
	var minDuration:int=0;
	var maxDuration:int=0;
	if(fixedDurationRadio.selectedValue=="N")
	{
		if(minDurationText.value>=maxDurationText.value)
		{
			minDurationText.errorString=resourceManager.getString('Messages','invalidEntry');
		    maxDurationText.errorString=resourceManager.getString('Messages','invalidEntry');
		    validationError=true;
		}
		else
		{
			minDuration=int(minDurationText.value);
			maxDuration=int(maxDurationText.value);
		}
	}
	else
	{
		minDuration=int(minDurationText.value);
		maxDuration=minDuration;
	}
	if(!validationError)
	{
		var progDetails:Object=new Object();
			   progDetails["fixed_duration"]=fixedDurationRadio.selectedValue;
               progDetails["min_duration"]=minDurationText.value;
               progDetails["max_duration"]=maxDurationText.value;
               progDetails["progId"]=progId;
               progDetails["userId"]=pm.userId;
		
		minDurationText.errorString="";
	    maxDurationText.errorString="";
	    
	    UpdateDuration(progDetails);
	    
	    
	}
	else
	{
		minDurationText.setFocus();
		Alert.show(resourceManager.getString('Messages','invalidEntry'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	}
}


public function deleteDetails():void
{
	minDurationText.errorString="";
	maxDurationText.errorString="";
	startDateField.errorString="";
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(programStartDateGrid);
	
	var s:String="";
	var programIds:String="";
	for each(var obj:Object in selectedValues)
	{
		s+=obj.startdate+"|";
		programIds+=obj.programId+"|";
	}
	
	var dateInput:Object=new Object();
	dateInput["startDate"]=s;
	dateInput["progId"]=programIds;
	DeleteDuration(dateInput);
}

public function showDurationBox():void
{
	if(fixedDurationRadio.selectedValue=="Y")
	{
		maxDurationText.value=1;
		maxDurationText.enabled=false;
	}
	else
	{
		maxDurationText.enabled=true;
	}
}

public function addStartDate():void
{
	minDurationText.errorString="";
	maxDurationText.errorString="";
	if(startDateField.selectedValue!="")
	{
		var dateInput:Object=new Object();
		dateInput["startDate"]=startDateField.selectedValue;
		dateInput["progId"]=progId;
		dateInput["min_duration"]=minDurationText.value;
        dateInput["max_duration"]=maxDurationText.value;
          dateInput["userId"]=pm.userId;
		startDateField.errorString="";
		startDateField.dateCombo.selectedIndex=-1;
		startDateField.monthsCombo.selectedIndex=-1;
		
		AddDuration(dateInput);
	
	}
	else
	{
		Alert.show(resourceManager.getString('Messages','selectADate'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
		startDateField.errorString=resourceManager.getString('Messages','selectADate');
	}
}

private function loadCombo():void{
	pm.httpProgramCode.url=commonFunction.getConstants('url')+ "/programmaster/methodprogList.htm";
	pm.httpProgramCodeList();
	pm.codeListCb=programNameComboBox;
	
}

[Bindable]
var durationXML:XML;


public function httpDurationDetailsForManage():void{
		var params:Object=new Object();
	params["userId"]=pm.userId;
//	if(programNameComboBox.selectedIndex>0){
	params["programId"]=pm.codeXML.branch.(branchName==programNameComboBox.selectedLabel).programId;
//	}
//	if(programCodeText.text != ""){
//		params["criteria"]="code";
//	params["programCode"]=programCodeText.text
//	}else{
		params["criteria"]="not code";
//	}
		params["time"]=new Date().getTime();
	httpDurationDetails.send(params);
}
private function faultHandler_DurationDetails(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_DurationDetails(event:ResultEvent):void{
	
   durationXML=event.result as XML;  
   if(durationXML.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
   var resultXML:XML=event.result as XML;
	if(resultXML.exception.exceptionstring=="fail"){
		 mx.controls.Alert.show(resourceManager.getString('Messages','error'),resourceManager.getString('Messages','error'));
	}else{
		 var bsAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in durationXML.duration){
   	bsAC.addItem({select:false,programId:object.programId,programCode:object.programCode,programName:object.programName,
   	minimunDuration:object.minimunDuration,maximumDuration:object.maximumDuration,
   	fixedDuration:object.fixedDuration,startdate:object.startdate});
   	
    fixedDurationRadio.selectedValue=object.fixedDuration;
    progId=object.programId;
    if(object.fixedDuration=="N"){
    	maxDurationText.enabled=true;
    }else{
    	maxDurationText.enabled=false;
    }
   	maxDurationText.value=object.maximumDuration;
	minDurationText.value=object.minimunDuration;
   	
   	}
   
programStartDateGrid.dataProvider=bsAC;
if(bsAC.length==0){
	
		   Alert.show(resourceManager.getString('Messages','noRecordFound'),resourceManager.getString('Messages','success'),0,null,null,successIcon);  

	
	programDurationDetailPanel.visible=false;
}else{
	programDurationDetailPanel.visible=true;
}
	
	}
   }

/* */
 private function UpdateDuration(params:Object):void{
 	httpUpdateDurationDetails.send(params);
 }
 
 private function DeleteDuration(params:Object):void{
 	httpDeleteDurationDetails.send(params);
 }
 
  private function AddDuration(params:Object):void{
 	httpAddDurationDetails.send(params);
 } 
 
 
private function faultHandler(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler(event:ResultEvent):void{
	var resultXml:XML=event.result as XML;
	if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	   Alert.show(resourceManager.getString('Messages','successOnInsert'),resourceManager.getString('Messages','success'),0,null,null,successIcon);  
showDetails();



}

private function resultHandler_delete(event:ResultEvent):void{
	var resultXml:XML=event.result as XML;
	if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	   Alert.show(resourceManager.getString('Messages','recordsDeletedSuccessfully'),resourceManager.getString('Messages','success'),0,null,null,successIcon);  
showDetails();
}

private function resultHandler_update(event:ResultEvent):void{
	var resultXml:XML=event.result as XML;
	if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	   Alert.show(resourceManager.getString('Messages','recordUpdatedSuccessfully'),resourceManager.getString('Messages','success'),0,null,null,successIcon);  
showDetails();
}

  