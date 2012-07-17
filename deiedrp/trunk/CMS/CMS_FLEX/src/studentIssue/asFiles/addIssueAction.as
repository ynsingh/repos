/**
 * @(#) editIssueAction.as
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
import common.commonFunction;

import mx.collections.*;
import mx.controls.Alert;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Bindable]public var urlPrefix2:String;
public var infoObject:Object;
[Bindable]public var penalities:XML;
public var setPenalities:ArrayCollection;
public var setCourses:ArrayCollection; 
[Bindable]public var entityId:String;

public var courses2:ArrayCollection;
public var courses3:ArrayCollection;

public var buttonFunction:Function;



public function getValues():void{
	urlPrefix2=commonFunction.getConstants('url')+"/studentIssue/";
	infoObject=new Object;
	infoObject["rollNo"]=rollNoLabel.text;
	getEntity.send(infoObject);
	getPrograms.send(infoObject);
	getIssues.send();
	getPenalityCodes.send();
}


public var entityxml:XML;
public function onEntitySuccess(event:ResultEvent){
	
	entityxml=new XML;
	entityxml=event.result as XML;
	entityLabel.text=entityxml.Details.entity;
	entityId=entityxml.Details.entityId;
}

public var progXml:XML;
public function onProgramSuccess(event:ResultEvent):void{
progXml=new XML;
progXml=event.result as XML;
var prog:ArrayCollection=new ArrayCollection;
for each(var obj:Object in progXml.Details){
	prog.addItem({name:obj.programName,id:obj.programId});
}
programCombo.dataProvider=prog;
programCombo.labelField="name";	
}


/**
 *  Program change Handler
 **/
public function programChange(event:ListEvent):void
{	
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
	semesterCombo.enabled=false;
	issueCodeCombo.enabled=false;
	penalityCodeCombo.enabled=false;
	penalityCourse1Combo.enabled=false;
	penalityCourse2Combo.enabled=false;
	penalityCourse3Combo.enabled=false;
	penalityCourse4Combo.enabled=false;
	semesterStartDate.text="";
	semesterEndDate.text="";
	infoObject=new Object;
	infoObject["rollNo"]=rollNoLabel.text;
	infoObject["programId"]=programCombo.selectedItem.id;
	getBranch.send(infoObject);

	 branchCombo.enabled=true;
     //infoObject["programId"] = programCombo.selectedItem.id;
	 branchCombo.selectedIndex=-1;
	 
}

public function onBranchSuccess(event:ResultEvent):void{
	var branchxml:XML=new XML;
	branchxml=event.result as XML;
	var branchs:ArrayCollection=new ArrayCollection;
    for each(var obj:Object in branchxml.Details){
	branchs.addItem({name:obj.branchName,id:obj.branchId});
}
branchCombo.dataProvider=branchs;
branchCombo.labelField="name";	
}

/**
 * Branch change Handler 
 **/
protected function branchChange(event:ListEvent):void
{
	specilizationCombo.enabled=false;
	semesterCombo.enabled=false;
	issueCodeCombo.enabled=false;
	penalityCodeCombo.enabled=false;
	penalityCourse1Combo.enabled=false;
	penalityCourse2Combo.enabled=false;
	penalityCourse3Combo.enabled=false;
	penalityCourse4Combo.enabled=false;
	semesterStartDate.text="";
	semesterEndDate.text="";
	infoObject=new Object;
	infoObject["rollNo"]=rollNoLabel.text;
	infoObject["programId"]=programCombo.selectedItem.id;
	infoObject["branchId"]=branchCombo.selectedItem.id;
	getSpecialization.send(infoObject);
	specilizationCombo.enabled=true;
	specilizationCombo.selectedIndex=-1;
	
	var infoObject:Object = {};
	
	//infoObject["programId"] = programCombo.selectedItem.id;
	//infoObject["branchId"] = branchCombo.selectedItem.id;
}

public function onSpecializationSuccess(event:ResultEvent):void{
	var specializationxml:XML=new XML;
	specializationxml=event.result as XML;
	var specializations:ArrayCollection=new ArrayCollection;
    for each(var obj:Object in specializationxml.Details){
	specializations.addItem({name:obj.specializationName,id:obj.specializationId});
}
specilizationCombo.dataProvider=specializations;
specilizationCombo.labelField="name";	
}

/**
 * Specialization change Handler 
 **/
protected function specializationChange(event:ListEvent):void
{ 
	semesterCombo.enabled=false;
	issueCodeCombo.enabled=false;
	penalityCodeCombo.enabled=false;
	penalityCourse1Combo.enabled=false;
	penalityCourse2Combo.enabled=false;
	penalityCourse3Combo.enabled=false;
	penalityCourse4Combo.enabled=false;
	semesterStartDate.text="";
	semesterEndDate.text="";
	infoObject=new Object;
	infoObject["rollNo"]=rollNoLabel.text;
	infoObject["programId"]=programCombo.selectedItem.id;
	infoObject["branchId"]=branchCombo.selectedItem.id;
	infoObject["specializationId"]=specilizationCombo.selectedItem.id;
	getSemester.send(infoObject);
	semesterCombo.enabled=true;
	semesterCombo.selectedIndex=-1;
				
//	infoObject["programId"] = programCombo.selectedItem.id;
//	infoObject["branchId"] = branchCombo.selectedItem.id;
//	infoObject["specializationId"] = specilizationCombo.selectedItem.id;
}	
	
public function onSemesterSuccess(event:ResultEvent):void{
	
	var semesterxml:XML=new XML;
	semesterxml=event.result as XML;
	
	var semesters:ArrayCollection=new ArrayCollection;
    for each(var obj:Object in semesterxml.Details){
	semesters.addItem({name:obj.semesterName,id:obj.semesterId});
}
semesterCombo.dataProvider=semesters;
semesterCombo.labelField="name";	
}

/**
 * This method fires on change of semester
 * it send request for fetching semester Start date & end date  
 **/
protected function onSemesterChange():void
{
	penalityCodeCombo.enabled=false;
	penalityCourse1Combo.enabled=false;
	penalityCourse2Combo.enabled=false;
	penalityCourse3Combo.enabled=false;
	penalityCourse4Combo.enabled=false;
	semesterStartDate.text="";
	semesterEndDate.text="";
	infoObject=new Object;
	infoObject["rollNo"]=rollNoLabel.text;
	infoObject["programId"]=programCombo.selectedItem.id;
	infoObject["branchId"]=branchCombo.selectedItem.id;
	infoObject["specializationId"]=specilizationCombo.selectedItem.id;
	infoObject["semesterId"]=semesterCombo.selectedItem.id;
	getSemesterDates.send(infoObject);
	issueCodeCombo.enabled=true;
//	infoObject["programId"] = programCombo.selectedItem.id;
//	infoObject["branchId"] = branchCombo.selectedItem.id;
//	infoObject["specializationId"] = specilizationCombo.selectedItem.id;
//	infoObject["semesterId"]=semesterCombo.selectedItem.id;
}

public function onSemesterDatesSuccess(event:ResultEvent):void{
	
	var datesxml:XML=new XML;
	datesxml=event.result as XML;
	semesterStartDate.text=datesxml.Details.semesterStartDate;
	semesterEndDate.text=datesxml.Details.semesterEndDate;
}

public function onIssuesSuccess(event:ResultEvent):void{
	var issuexml:XML=new XML;
	issuexml=event.result as XML;
	
	var issueList:ArrayCollection=new ArrayCollection;
	for each(var obj:Object in issuexml.Details){
		issueList.addItem({issue:obj.issue,issueCode:obj.issueCode});
	}
	issueCodeCombo.dataProvider=issueList;
	issueCodeCombo.labelField="issue";
}

public function issueCodeChange(event:ListEvent):void{
	
	penalityCodeCombo.enabled=true;
}

public function ongetPenalitySuccess(event:ResultEvent):void{
	penalities=new XML;
	penalities=event.result as XML;
	setPenalities=new ArrayCollection();
	for each(var obj:Object in penalities.Details){
		setPenalities.addItem({penalityId:obj.penalityId,penalityCode:obj.penalityCode,
		penalityFlag:obj.penalityFlag})
	}
	penalityCodeCombo.dataProvider=setPenalities;
	penalityCodeCombo.labelField="penalityCode";
}

/** this method fires on change of penality code
 * this enables/disables penality course comboboxes according to flag value of penality code
 * */
public function penalityCodeChange(event:ListEvent){
	
	if(Validator.validateAll([programComboValidator,branchComboValidator,specilizationComboValidator,semesterComboValidator,issueCodeComboValidator]).length==0){
	var s:String=penalityCodeCombo.selectedItem.penalityFlag;
	if(s=="1")
	{	
		penalityCourse1Combo.enabled=true;
		penalityCourse2Combo.enabled=true;
		penalityCourse3Combo.enabled=true;
		penalityCourse4Combo.enabled=true;
		infoObject["rollNo"]=rollNoLabel.text;
		infoObject["flag"]="add";
	    infoObject["programId"]=programCombo.selectedItem.id;
	    infoObject["branchId"]=branchCombo.selectedItem.id;
	    infoObject["specializationId"]=specilizationCombo.selectedItem.id;
	    infoObject["semesterId"]=semesterCombo.selectedItem.id;
		getPenalityCourseCodes.send(infoObject);
		//Alert.show("sent"+penalityCodeCombo.selectedItem.penalityId);
		}else{
			
		penalityCourse1Combo.enabled=false;
		penalityCourse2Combo.enabled=false;
		penalityCourse3Combo.enabled=false;
		penalityCourse4Combo.enabled=false;
		}
    }else{
    		
    		Alert.show("first select all prev values");
    }
 }

public function onPenalityCourseSuccess(event:ResultEvent):void{
	
	var courses:XML=new XML;
	courses=event.result as XML;
	setCourses=new ArrayCollection();
	
	for each(var obj:Object in courses.Details){
		setCourses.addItem({courseCode:obj.courseCode});
	}
	penalityCourse1Combo.dataProvider=setCourses;
	penalityCourse1Combo.labelField="courseCode";
	
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
}

public function setNextCourses1(event:ListEvent):void{
	courses2=new ArrayCollection;
	for each(var ob:Object in setCourses)
	{
		courses2.addItem(ob);
	}
	courses2.removeItemAt(penalityCourse1Combo.selectedIndex);
	penalityCourse2Combo.dataProvider=courses2;
	penalityCourse2Combo.labelField="courseCode";
	penalityCourse2Combo.selectedIndex=-1;
	penalityCourse3Combo.selectedIndex=-1;
	penalityCourse4Combo.selectedIndex=-1;
}

public function setNextCourses2(event:ListEvent):void{
	courses3=new ArrayCollection;
	for each(var ob:Object in courses2)
	{
		courses3.addItem(ob);
	}
	courses3.removeItemAt(penalityCourse2Combo.selectedIndex);
	penalityCourse3Combo.dataProvider=courses3;
	penalityCourse3Combo.labelField="courseCode";
	penalityCourse3Combo.selectedIndex=-1;
	penalityCourse4Combo.selectedIndex=-1;
}

public function setNextCourses3(event:ListEvent):void{
	var courses4:ArrayCollection=new ArrayCollection;
	for each(var ob:Object in courses3)
	{
		courses4.addItem(ob);
	}
	courses4.removeItemAt(penalityCourse3Combo.selectedIndex);
	penalityCourse4Combo.dataProvider=courses4;
	penalityCourse4Combo.labelField="courseCode";
	penalityCourse4Combo.selectedIndex=-1;
}


/**
 * Method to fire on the click event of the submit button this send request for saving details after validations
 **/
public function saveDetails():void
{
	if(checkValidations())
	{
		if(semesterStartDate.text>=semesterEndDate.text)
		{
			Alert.show(commonFunction.getMessages('startLessEndDate'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
		else if(checkCourse()){
			penalityCourse1Combo.errorString=commonFunction.getMessages('pleaseSelectAPenalityCourse');
			Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
		else
		{
			infoObject=new Object;
			penalityCourse1Combo.errorString="";
			infoObject["entityId"]=entityId;
			infoObject["rollNo"]=rollNoLabel.text;
			infoObject["programId"]=programCombo.selectedItem.id;
	    	infoObject["branchId"]=branchCombo.selectedItem.id;
	    	infoObject["specializationId"]=specilizationCombo.selectedItem.id;
	    	infoObject["semesterId"]=semesterCombo.selectedItem.id;
	    	infoObject["semesterStartDate"]=semesterStartDate.text;
	    	infoObject["semesterEndDate"]=semesterEndDate.text;
	    	infoObject["issue"]=issueCodeCombo.selectedItem.issueCode;
	    	infoObject["remarks"]=remarks.text;
	    	
	   		if(penalityCodeCombo.selectedIndex==-1){
	   			infoObject["penalityId"]=null;
	   		}else{   
	   			infoObject["penalityId"]=penalityCodeCombo.selectedItem.penalityId;
	   			//Alert.show(penalityCodeCombo.selectedItem.penalityId);
	    	    infoObject["flagforClose"]="addClose";
	    	}
		   	infoObject["penalityCourse1"]=penalityCourse1Combo.selectedLabel;
	    	infoObject["penalityCourse2"]=penalityCourse2Combo.selectedLabel;
	    	infoObject["penalityCourse3"]=penalityCourse3Combo.selectedLabel;
	    	infoObject["penalityCourse4"]=penalityCourse4Combo.selectedLabel;
	    	insertIssueDetails.send(infoObject);
		}

	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

public function oninsertIssueSuccess(event:ResultEvent):void{
	
	var insertDetails:XML=event.result as XML;
	
	if(insertDetails.Details.list_values=="Success"){
	
	Alert.show((resourceManager.getString('Messages','submittedSuccessfully')),commonFunction.getMessages('success'),
		4,null,null,successIcon);
		closeScreen();
		buttonFunction();
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
 	closeScreen();
	}		
	
	
	
		    
}



/**
 * Method to validate required fields to add an issue
 * @return bool(True/False)
 **/
public function checkValidations():Boolean
{   
    var bool:Boolean=false;
	if(Validator.validateAll([semesterComboValidator,issueCodeComboValidator]).length==0)
	{
	 bool=true;
    }
	return bool;
}

/** this validates 1 required course in some cases of penality code
 *  @return bool(True/False)
 * */
public function checkCourse():Boolean{
	var bool:Boolean=false;
	if(penalityCourse1Combo.enabled==true){
	    	if(penalityCourse1Combo.selectedIndex==-1){
	    	     bool=true;	
	    	}
	    }
	return bool;
}

/**
 * This fire on pressing down ESC key 
 * and closes popup
 * */
private function initializeForEsc(event:KeyboardEvent):void
{
    if (event.keyCode == Keyboard.ESCAPE)
    {
		closeScreen();
    }
}

public function onUpdateSuccess(event:ResultEvent):void
{
	Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
}


public function cancelWindow():void{
	closeScreen();
}

/** this method close popup screen**/
public function closeScreen():void
{
	PopUpManager.removePopUp(this);
}