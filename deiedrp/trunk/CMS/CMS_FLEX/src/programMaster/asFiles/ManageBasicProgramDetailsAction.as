
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import programMaster.EditBasicProgramDetails;
import programMaster.ProgramMaster;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

private var pm:ProgramMaster=new ProgramMaster();
public var editWindow:EditBasicProgramDetails;
public var abc:Function;

[Bindable]
public var codeXML:XML; 
[Bindable]
public var detailsXML:XML; 

/**
 * Function for getting program code list
 */
public function httpProgramCodeList():void{

	var params:Object=new Object();
	params["userId"]=pm.userId;
		params["time"]=new Date().getTime();
	httpProgramCode.send(params);
}
private function faultHandler_ProgramCode(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_ProgramCode(event:ResultEvent):void{
	 
   codeXML=event.result as XML;
    
     if(codeXML.sessionConfirm == true)
             {
            Alert.show("session is inactive");
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
programNameComboBox.dataProvider=codeXML.branch.branchName;
	
//	pm.httpType();
	
//pm.httpProgramType.send(params);
   }
   
  
   
   private function displayCode():void{
   	programCodeText.text=codeXML.branch.(branchName==programNameComboBox.selectedLabel).branchCode;
   }
   
   

public function showDetails():void
{
	httpProgramDetails();
 
	programDetailPanel.visible=true;
}

public function editDetails():void
{
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(programDetailsGrid);
	editWindow=EditBasicProgramDetails(PopUpManager.createPopUp(this,EditBasicProgramDetails,true));
	pm.programTypeCb=editWindow.programTypeCombo;
	pm.programModeCb=editWindow.programModeCombo;
	pm.codeEditXML=codeXML;
	pm.httpProgramType.url=commonFunction.getConstants('url')+ "/programmaster/methodUniversityProgramType.htm";
	pm.httpProgramMode.url=commonFunction.getConstants('url')+ "/programmaster/methodUniversityProgramMode.htm";
	pm.httpProgramCode.url=commonFunction.getConstants('url')+ "/programmaster/methodprogList.htm";
	pm.httpType();
	editWindow.programCodeText.text=selectedValues[0].programCode;
	editWindow.programNameText.text=selectedValues[0].programName;
	editWindow.creditsRequiredText.text=selectedValues[0].creditRequired;
	editWindow.dgpaText.text=selectedValues[0].dgpa;
	editWindow.maxAttemptAllowedText.value=selectedValues[0].numberOfAttemptAllowed;
	editWindow.maxFailSubjectText.value=selectedValues[0].maxNumberOfFailSubjects;
	editWindow.maxRegSemText.value=selectedValues[0].maxRegSemester;
	editWindow.noOfTermsText.value=selectedValues[0].numberOfTerms;
	pm.selectedType=selectedValues[0].programType;
	pm.selectedMode=selectedValues[0].programMode;
	editWindow.ugOrPg=selectedValues[0].ugOrPg;
	editWindow.tenCodes=selectedValues[0].tencodes;
	editWindow.printAggregate=selectedValues[0].printAggregate;
	editWindow.fixOrVariableGroup.selectedValue=selectedValues[0].fixedOrVariableCredit
	editWindow.programDescriptionText.text=selectedValues[0].programDescription;

	editWindow.updateButtonFunction=updateDetails;
	PopUpManager.centerPopUp(editWindow);	
}



public function updateDetails():void
{
	editButton.enabled=false;
	deleteButton.enabled=false;
	if(editWindow.validateBasicProgramDetails())
	{
		 var progDetails:Object=new Object();
			   progDetails["userId"]=pm.userId;
	           progDetails["program_id"]=codeXML.branch.(branchName==programNameComboBox.selectedLabel).programId;
               progDetails["program_name"]=editWindow.programNameText.text;
               progDetails["program_type"]=pm.typeXML.type.(typeName==editWindow.programTypeCombo.selectedLabel).typeCode
               progDetails["program_mode"]=pm.modeXML.mode.(modeName==editWindow.programModeCombo.selectedLabel).modeCode
               progDetails["no_of_terms"]=editWindow.noOfTermsText.value;  
		       progDetails["number_of_attempt_allowed"]=editWindow.maxAttemptAllowedText.value;
		       progDetails["max_number_of_fail_subjects"]=editWindow.maxFailSubjectText.value;
		       progDetails["ug_pg"]=editWindow.ugpgXML.option.(name==editWindow.ugOrPgCombo.selectedLabel).code;
		       progDetails["ten_codes"]=editWindow.tenCodesXML.option.(name==editWindow.tenCodesCombo.selectedLabel).code;
		       progDetails["print_aggregate"]=editWindow.aggregateXML.option.(name==editWindow.printAggregateCombo.selectedLabel).code;
               progDetails["max_reg_semester"]=editWindow.maxRegSemText.value;
               progDetails["dgpa"]=editWindow.dgpaText.text;
               progDetails["credit_required"]=editWindow.creditsRequiredText.text;
               progDetails["fixed_or_variable_credit"]=editWindow.fixOrVariableGroup.selectedValue;
               progDetails["programDescription"]=editWindow.programDescriptionText.text;
               httpUpdateDetails(progDetails);
               
	}
	else
	{
		Alert.show(resourceManager.getString('Messages','pleasecheckEntriesinRed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	}	
	
	          
	
}

private function deleteConfirm(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(programDetailsGrid);
		var s:String="";
		for each(var obj:Object in selectedValues)
		{
			s+=obj.programId+"|";
		}
		var progDetails:Object=new Object();
		progDetails["progId"]=s;
		deleteAllDetails(progDetails);		
	}	
}

public function deleteDetails():void
{
	Alert.show(commonFunction.getMessages('deleteConfirmMessage'), commonFunction.getMessages('confirm'), 3, this, deleteConfirm,questionIcon);
}


/**
 * Function for getting program details
 */
public function httpProgramDetails():void{
	var params:Object=new Object();
	params["userId"]=pm.userId;
	if(programNameComboBox.selectedIndex>0){
	params["programId"]=codeXML.branch.(branchName==programNameComboBox.selectedLabel).programId;
	}
//	if(programCodeText.text != ""){
//		programNameComboBox.selectedItem=codeXML.branch.(branchCode==programCodeText.text).branchName.toString();
//		params["criteria"]="code";
//	params["programCode"]=programCodeText.text
//	}else{
		params["criteria"]="not code";
//	}
		params["time"]=new Date().getTime();
	httpProgramDetail.send(params);
}
private function faultHandler_ProgramDetails(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_ProgramDetails(event:ResultEvent):void{
	 
   detailsXML=event.result as XML; 
   
   if(detailsXML.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
   var detailsAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in detailsXML.program){
   		detailsAC.addItem({select:false,programId:object.programId,programCode:object.programCode,programName:object.programName,
   		programType:object.programType,programMode:object.programMode,numberOfTerms:object.numberOfTerms,
   		totalCredits:object.totalCredits,numberOfAttemptAllowed:object.numberOfAttemptAllowed,
   		maxNumberOfFailSubjects:object.maxNumberOfFailSubjects,printAggregate:object.printAggregate,
   		ugOrPg:object.ugOrPg,tencodes:object.tencodes,maxRegSemester:object.maxRegSemester,
   		dgpa:object.dgpa,creditRequired:object.creditRequired,fixedOrVariableCredit:object.fixedOrVariableCredit,programDescription:object.programDescription});
   	}
   	
   programDetailsGrid.dataProvider=detailsAC;
   }


/**
 * Function for getting program details
 */
public function httpUpdateDetails(params:Object):void{
		params["time"]=new Date().getTime();
	httpUpdateDetail.send(params);
}
private function faultHandler_UpdateDetails(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_UpdateDetails(event:ResultEvent):void{
	var resultXml:XML=event.result as XML;
	if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	
	
Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),4,this,closeUpdate,successIcon);

}

private function closeUpdate(event:CloseEvent):void{
	if(event.detail==Alert.OK){
	editWindow.closePopUp();
	httpProgramDetails();
	 }
}

/**
 * Function for deleting program details
 */
public function deleteAllDetails(params:Object):void{
		params["time"]=new Date().getTime();
	httpDeleteAllDetail.send(params);
}
private function faultHandler_DeleteDetails(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_DeleteDetails(event:ResultEvent):void{
	var resultXml:XML=event.result as XML;
	if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	
	if(resultXml.exception.exceptionstring=="ie"){
		Alert.show(resourceManager.getString('Messages','dependencyDeleteError'),resourceManager.getString('Messages','error'),4,this,closeDelete,errorIcon);
	}else{
Alert.show(resourceManager.getString('Messages','success'),resourceManager.getString('Messages','success'),4,this,closeDelete,successIcon);
	}
}

private function closeDelete(event:CloseEvent):void{
	if(event.detail==Alert.OK){
		programCodeText.text="";
	httpProgramCodeList();
	programDetailPanel.visible=false;
	 }
}


