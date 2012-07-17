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
import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;
public var infoObject:Object;
[Bindable] public var issueId:String = new String;
[Bindable] public var programCourseKey:String = new String;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Bindable]public var penalities:XML;
[Bindable]public var urlPrefix3:String;
public var setPenalities:ArrayCollection;
public var setCourses:ArrayCollection; 
public var courses2:ArrayCollection;
public var courses3:ArrayCollection;

[Bindable]public var entId:String;
[Bindable]public var prgId:String;
[Bindable]public var brnId:String;
[Bindable]public var spcId:String;
[Bindable]public var semId:String;
[Bindable]public var isuCod:String;
public var buttonFunction2:Function;


public function getDetails():void{
		urlPrefix3=commonFunction.getConstants('url')+"/studentIssue/";
	infoObject=new Object;
	
	getPenalityCodes.send();
	infoObject["rollNo"]=rollNoLabel.text;
	infoObject["programCourseKey"]=programCourseKey;
	infoObject["flag"]="Edit";
	getPenalityCourseCodes.send(infoObject);
	
}

public function ongetPenalitySuccess(event:ResultEvent):void{
	penalities=new XML;
	penalities=event.result as XML;
	setPenalities=new ArrayCollection();
	for each(var obj:Object in penalities.Details){
		setPenalities.addItem({penalityId:obj.penalityId,penalityCode:obj.penalityCode,
		penalityFlag:obj.penalityFlag})
	}
	editpenalityCodeCombo.dataProvider=setPenalities;
	editpenalityCodeCombo.labelField="penalityCode";
}

public function ongetPenalityCourseSuccess(event:ResultEvent):void{
	var courses:XML=new XML;
	courses=event.result as XML;
	setCourses=new ArrayCollection();
	
	for each(var obj:Object in courses.Details){
		setCourses.addItem({courseCode:obj.courseCode});
	}
	editpenalityCourse1Combo.dataProvider=setCourses;
	editpenalityCourse1Combo.labelField="courseCode";
	
}



/** this method fires on click of update button**/
public function updateDetails():void
{
	if(Validator.validateAll([editpenalityCodeComboValidator]).length==0)
	{
		if(checkCourse1()){
			editpenalityCourse1Combo.errorString=commonFunction.getMessages('pleaseSelectAPenalityCourse');
			Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);

			
		}else{
		
		infoObject=new Object;
		
		infoObject["entityId"]=entId
		infoObject["programId"]=prgId;
	    infoObject["branchId"]=brnId;
	    infoObject["specializationId"]=spcId;
	    infoObject["semesterId"]=semId;
	    infoObject["semesterStartDate"]=semesterStartDateLabel.text;
	    infoObject["semesterEndDate"]=semesterEndDateLabel.text;
	    infoObject["issueId"]=issueId;	
		infoObject["issueCode"]=isuCod;
	    infoObject["rollNo"]=rollNoLabel.text;
	    infoObject["programCourseKey"]=programCourseKey;
		infoObject["penalityId"]=editpenalityCodeCombo.selectedItem.penalityId;
		infoObject["penalityCourse1"]=editpenalityCourse1Combo.selectedLabel;
		infoObject["penalityCourse2"]=editpenalityCourse2Combo.selectedLabel;
		infoObject["penalityCourse3"]=editpenalityCourse3Combo.selectedLabel;
		infoObject["penalityCourse4"]=editpenalityCourse4Combo.selectedLabel;
		infoObject["remarks"]=editRemarks.text;
		updateIssues.send(infoObject);
		}
	}
	else
	{
		Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}


/** this validates 1 required course in some cases of penality code
 *  @return bool(True/False)
 * */
public function checkCourse1():Boolean{
	var bool:Boolean=false;
	if(editpenalityCourse1Combo.enabled==true){
	    	if(editpenalityCourse1Combo.selectedIndex==-1){
	    	     bool=true;	
	    	}
	    }
	return bool;
}
public function setNextCourses1(event:ListEvent):void{
	courses2=new ArrayCollection;
	for each(var ob:Object in setCourses)
	{
		courses2.addItem(ob);
	}
	courses2.removeItemAt(editpenalityCourse1Combo.selectedIndex);
	editpenalityCourse2Combo.dataProvider=courses2;
	editpenalityCourse2Combo.labelField="courseCode";
	editpenalityCourse2Combo.selectedIndex=-1;
	editpenalityCourse3Combo.selectedIndex=-1;
	editpenalityCourse4Combo.selectedIndex=-1;
}

public function setNextCourses2(event:ListEvent):void{
	courses3=new ArrayCollection;
	for each(var ob:Object in courses2)
	{
		courses3.addItem(ob);
	}
	courses3.removeItemAt(editpenalityCourse2Combo.selectedIndex);
	editpenalityCourse3Combo.dataProvider=courses3;
	editpenalityCourse3Combo.labelField="courseCode";
	editpenalityCourse3Combo.selectedIndex=-1;
	editpenalityCourse4Combo.selectedIndex=-1;
}

public function setNextCourses3(event:ListEvent):void{
	var courses4:ArrayCollection=new ArrayCollection;
	for each(var ob:Object in courses3)
	{
		courses4.addItem(ob);
	}
	courses4.removeItemAt(editpenalityCourse3Combo.selectedIndex);
	editpenalityCourse4Combo.dataProvider=courses4;
	editpenalityCourse4Combo.labelField="courseCode";
	editpenalityCourse4Combo.selectedIndex=-1;
}

public function onUpdateSuccess(event:ResultEvent):void
{
	var updateDetails:XML=event.result as XML;
	
	if(updateDetails.Details.list_values=="success"){
	
	Alert.show((resourceManager.getString('Messages','recordUpdatedSuccessfully')),commonFunction.getMessages('success'),
		4,null,null,successIcon);
		closeScreen();
		buttonFunction2();
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
 	closeScreen();
	}		
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
}

public function penalityCodeChange(event:ListEvent){
	
	var s:String=editpenalityCodeCombo.selectedItem.penalityFlag;
	
	if(s=="1")
	{
		editpenalityCourse1Combo.enabled=true;
		editpenalityCourse2Combo.enabled=true;
		editpenalityCourse3Combo.enabled=true;
		editpenalityCourse4Combo.enabled=true;
//		infoObject["rollNo"]=rollNoLabel.text;
//		infoObject["flag"]="add";
//	    infoObject["programId"]=programCombo.selectedItem.id;
//	    infoObject["branchId"]=branchCombo.selectedItem.id;
//	    infoObject["specializationId"]=specilizationCombo.selectedItem.id;
//	    infoObject["semesterId"]=semesterCombo.selectedItem.id;
//		getPenalityCourseCodes.send(infoObject);
		
	}
	else
	{
		editpenalityCourse1Combo.enabled=false;
		editpenalityCourse2Combo.enabled=false;
		editpenalityCourse3Combo.enabled=false;
		editpenalityCourse4Combo.enabled=false;
	}
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

public function cancelWindow():void{
	closeScreen();
}

/** this method close popup screen**/
public function closeScreen():void
{
	PopUpManager.removePopUp(this);
}