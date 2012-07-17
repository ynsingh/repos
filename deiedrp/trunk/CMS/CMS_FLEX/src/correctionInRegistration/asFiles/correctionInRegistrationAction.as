/**
 * @(#) correctionInRegistrationAction.as
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
import common.commonFunction;
import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Bindable]public var urlPrefix1:String;
import updatePrestagingTable.updateprestagepopup;
import mx.events.ListEvent;
import correctionInRegistration.snewsswtpopup;
import correctionInRegistration.misspopup;
import correctionInRegistration.dupsenrrpopup;
[Bindable]public var entityXml:XML;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
public var param:Object={};
[Bindable]public var programXml:XML;
public var infoObject:Object={};

/**
 * This method is for getting the details
 * requered when the UI is loaded
 **/
[Bindable]public var urlPrefix:String;

public function onCreationComplete():void{
	
	entityCombo.setFocus();
	editButton.enabled=false;
	urlPrefix=resourceManager.getString('Constants','url')+"/correctionInRegistration/";
	urlPrefix1=resourceManager.getString('Constants','url')+"/prestagingData/";
	
	infoObject["userId"]=new Date;
	param['time']=new Date;
	getEntityList.send(param);
	
}

/**
 * The function fires on change of entity
 * it sends request for fetching programs
 * */
public function entChange(event:ListEvent):void{
	
	param['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
	
	getProgramList.send(param);
}

/**
 * The function fires on success of fetching program 
 * it set program values to program combo
 * */
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
	
}

/**
 * The function validates required field and send request for fetching records 
 * */
public function showGridRecord():void{
	
	entityComboValid.source=entityCombo;
	if(Validator.validateAll([entityComboValid]).length!=0)
		{
			Alert.show((commonFunction.getMessages('firstSelectallMandatoryFields')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
		}
		else
		{
			infoObject["entityId"] = entityXml.Details.(name==entityCombo.selectedLabel).id;
		
		if(programCombo.selectedIndex==-1){
			
			infoObject["programId"] = "a";						
		}else{	
			
			var s:String= programXml.Details.(name==programCombo.selectedLabel).id;
			infoObject["programId"] = s+"%";			
		}
		getGridRecords.send(infoObject);
		}				
}

  /**
 	* Mehtod to be called on request failure
 	**/
 public function onFailure(event:FaultEvent):void{
 	
 	Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
 	
 }
 
 /**
 * The function fires on success of fecthing records for grid  
 * it sets the value in grid
 * */
[Bindable] public var gridXml:XML; 
public function onGridRecordSuccess(event:ResultEvent):void{
	
	gridXml=event.result as XML;
	var gridData:ArrayCollection = new ArrayCollection();
	for each(var obj:Object in gridXml.Details){
		
		var rol:String=obj.rollNo;
		var regrol:String="";
		if(rol==""){
			regrol=obj.regNo;
		}else{
			regrol=obj.rollNo;
		}
		gridData.addItem({select:false,regNo:regrol,regsNo:obj.regNo,rollNo:obj.rollNo,enrollNo:obj.enrollNo,admissionMode:obj.admissionMode,
		programName:obj.programName,branchName:obj.branchName,specializationName:obj.specializationName,semesterName:obj.semesterName,
		programId:obj.programId,branchId:obj.branchId,specializationId:obj.specializationId,semesterId:obj.semesterId,
		studentId:obj.studentId,sequenceNo:obj.sequenceNo,reasoncode:obj.reasoncode,description:obj.description,studentfname:obj.studentfname,
		studentmname:obj.studentmname,studentlname:obj.studentlname,fatherfname:obj.fatherfname,fathermname:obj.fathermname,
		fatherlname:obj.fatherlname,gender:obj.gender,category:obj.category,dob:obj.dob,categoryId:obj.categoryId,oldEntity:obj.oldEntity,
		oldProgram:obj.oldProgram,oldBranch:obj.oldBranch,oldSpecialization:obj.oldSpecialization,oldSemester:obj.oldSemester});
	
	}
	
	errorRecordGrid.dataProvider=gridData;
	recordGridCanvas.visible=true;
}
 
/**
 * The function fires on success of fetching entities  
 * it set values ot entity combo
 * */
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
}


/**
 * The function opens appropriate pop up with values
 * */
public function openPopup():void
{
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(errorRecordGrid);
	var s:String=selectedValues[0].reasoncode;
	if((s.toUpperCase()=="SNEW")||(s.toUpperCase()=="SSWT")){
	
	var seqeditWindow:snewsswtpopup=snewsswtpopup(PopUpManager.createPopUp(this,snewsswtpopup,true));
	seqeditWindow.entityLabel.text=entityCombo.selectedLabel;
	seqeditWindow.prgId=selectedValues[0].programId;
	seqeditWindow.programLabel.text=selectedValues[0].programName;
	seqeditWindow.branchLabel.text=selectedValues[0].branchName;
	seqeditWindow.specilizationLabel.text=selectedValues[0].specializationName;
	seqeditWindow.semesterLabel.text=selectedValues[0].semesterName;
	seqeditWindow.admModeLabel.text=selectedValues[0].admissionMode;
	seqeditWindow.regrolLabel.text=selectedValues[0].regNo;
	seqeditWindow.reasoncodeLabel.text=selectedValues[0].reasoncode;
	seqeditWindow.descriptionLabel.text=selectedValues[0].description;
	seqeditWindow.sequenceNum=selectedValues[0].sequenceNo;
	seqeditWindow.reason=selectedValues[0].reasoncode;
	seqeditWindow.reg=selectedValues[0].regsNo;
	seqeditWindow.roll=selectedValues[0].rollNo;
	seqeditWindow.buttonFunction=showGridRecord;
	PopUpManager.centerPopUp(seqeditWindow);
	}
	else if(s.toUpperCase()=="MISS"){
	
	var misseditWindow:misspopup=misspopup(PopUpManager.createPopUp(this,misspopup,true));
	misseditWindow.height=this.parentDocument.loaderCanvas.height;
	misseditWindow.entityLabel.text=entityCombo.selectedLabel;
	//misseditWindow.prgId=selectedValues[0].programId;
	misseditWindow.programLabel.text=selectedValues[0].programName;
	misseditWindow.branchLabel.text=selectedValues[0].branchName;
	misseditWindow.specilizationLabel.text=selectedValues[0].specializationName;
	misseditWindow.semesterLabel.text=selectedValues[0].semesterName;
	misseditWindow.admModeLabel.text=selectedValues[0].admissionMode;
	misseditWindow.regrolLabel.text=selectedValues[0].regNo;
	misseditWindow.reasoncodeLabel.text=selectedValues[0].reasoncode;
	misseditWindow.descriptionLabel.text=selectedValues[0].description;
	misseditWindow.reason=selectedValues[0].reasoncode;
	misseditWindow.reg=selectedValues[0].regsNo;
	misseditWindow.roll=selectedValues[0].rollNo;
	misseditWindow.enrNo=selectedValues[0].enrollNo;
	misseditWindow.stuId=selectedValues[0].studentId;
	misseditWindow.buttonFunction=showGridRecord;
	PopUpManager.centerPopUp(misseditWindow);
	
	}else if((s.toUpperCase()=="DUPS")||(s.toUpperCase()=="ENRR")){
		
	var enreditWindow:dupsenrrpopup=dupsenrrpopup(PopUpManager.createPopUp(this,dupsenrrpopup,true));
	
	enreditWindow.sf=selectedValues[0].studentfname;
	enreditWindow.sm=selectedValues[0].studentmname;
	enreditWindow.sl=selectedValues[0].studentlname;
	enreditWindow.ff=selectedValues[0].fatherfname;
	enreditWindow.fm=selectedValues[0].fathermname;
	enreditWindow.fl=selectedValues[0].fatherlname;
	enreditWindow.cateId=selectedValues[0].categoryId;
	enreditWindow.gen=selectedValues[0].gender;
	enreditWindow.oldentId=selectedValues[0].oldEntity;
	enreditWindow.oldprgId=selectedValues[0].oldProgram;
	enreditWindow.oldbrnId=selectedValues[0].oldBranch;
	enreditWindow.oldspcId=selectedValues[0].oldSpecialization;
	enreditWindow.oldsemId=selectedValues[0].oldSemester;
	enreditWindow.entityLabel.text=entityCombo.selectedLabel;
	enreditWindow.prgId=selectedValues[0].programId;
	enreditWindow.programLabel.text=selectedValues[0].programName;
	enreditWindow.branchLabel.text=selectedValues[0].branchName;
	enreditWindow.specilizationLabel.text=selectedValues[0].specializationName;
	enreditWindow.semesterLabel.text=selectedValues[0].semesterName;
	enreditWindow.admModeLabel.text=selectedValues[0].admissionMode;
	enreditWindow.regrolLabel.text=selectedValues[0].regNo;
	enreditWindow.reasoncodeLabel.text=selectedValues[0].reasoncode;
	enreditWindow.descriptionLabel.text=selectedValues[0].description;
	
	enreditWindow.studentIdLabel.text=selectedValues[0].studentId;
	enreditWindow.studentNameLabel.text=selectedValues[0].studentfname+" "+selectedValues[0].studentmname+" "+selectedValues[0].studentlname;
	enreditWindow.fatherNameLabel.text=selectedValues[0].fatherfname+" "+selectedValues[0].fathermname+" "+selectedValues[0].fatherlname;
	enreditWindow.genderNameLabel.text=selectedValues[0].gender;
	enreditWindow.dobLabel.text=selectedValues[0].dob;
	enreditWindow.categoryLabel.text=selectedValues[0].category;
	enreditWindow.rolNo=selectedValues[0].rollNo;
	enreditWindow.enrNo=selectedValues[0].enrollNo;
	enreditWindow.buttonFunction=showGridRecord;
	PopUpManager.centerPopUp(seqeditWindow);
	}
}	
	
/**
 * Method to be executed on successful deletion of selected records
 **/ 
public var successDetails:XML;
public function onSuccessfulEntry(event:ResultEvent):void{
	
	successDetails=event.result as XML;
	
	if(successDetails.Details.list_values=="success"){
	
	Alert.show((resourceManager.getString('Messages','deletedSuccessfully')),"Success",
		4,null,null,successIcon);
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}		
		
		onCreationComplete();	
}

public function checkActive():void{
	
	 var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(errorRecordGrid);
	   var infoObjects:Object={};
	
		var s:String=selectedValues[0].reasoncode;
		
	if((s.toUpperCase()=="DUPS")||(s.toUpperCase()=="ENRR")){
		
		
	infoObjects["studentfname"]=selectedValues[0].studentfname;
	infoObjects["studentmname"]=selectedValues[0].studentmname;
	infoObjects["studentlname"]=selectedValues[0].studentlname;
	infoObjects["fatherfname"]=selectedValues[0].fatherfname;
	infoObjects["fathermname"]=selectedValues[0].fathermname;
	infoObjects["fatherlname"]=selectedValues[0].fatherlname;
	infoObjects["gender"]=selectedValues[0].gender;
	infoObjects["categoryId"]=selectedValues[0].categoryId;
	infoObjects["dob"]=selectedValues[0].dob;
	
	                            	
	getEnrollNo.send(infoObjects);
	
	}else if((s.toUpperCase()=="SNEW")||(s.toUpperCase()=="SSWT")){
		
		openPopup();
	}  
		
}

public function onSuccessfulCheck(event:ResultEvent):void{
	
	successDetails=event.result as XML;
	//Alert.show(successDetails);
	
	if(successDetails.Details.list_values=="1"){
		
		var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(errorRecordGrid);
	var infoObjects:Object={};
	
	infoObjects["entityId"]=entityXml.Details.(name==entityCombo.selectedLabel).id;
	infoObjects["programId"]=selectedValues[0].programId;
	infoObjects["branchId"]=selectedValues[0].branchId;
	infoObjects["specializationId"]=selectedValues[0].specializationId;
	infoObjects["registrationNo"]=selectedValues[0].regsNo;
	infoObjects["studentId"]=selectedValues[0].studentId;
	infoObjects["reasonCode"]=selectedValues[0].reasoncode;
		
	rejectRecords.send(infoObjects);
	}else if(successDetails.Details.list_values=="0"){
		openPopup();
	}else{
		Alert.show("fail to check in student program");
	}
			
}

/**
 * The function fires on success of getting enrollment number
 * */
public function ongetEnrollNoSuccess(event:ResultEvent):void{
		
		var enr:XML=event.result as XML;
		
		var s:String=enr.Details.enrollNo;
		
		var infoObjects:Object={};
		
		infoObjects["enrollNo"]=s;
		
		checkRecord.send(infoObjects);
		
		
	}
	
	/**
 * The function fires on success of getting enrollment number
 * */
public function onSuccessfulReject(event:ResultEvent):void{
		
		var enr:XML=event.result as XML;
		
	
	if(enr.Details.list_values=="success"){
	
	Alert.show((resourceManager.getString('Messages','StudentRejected')),"Success",
		4,null,null,successIcon);
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}		
		showGridRecord();
	}
	
