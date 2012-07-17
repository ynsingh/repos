/**
 * @(#) studentRemedialAction.as
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
import common.Mask;
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import studentRemedial.sheduleExam;

[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;

[Bindable]public var urlPrefix:String;
[Bindable]public var appliedSemesterId:String;
[Bindable]public var appliedStartDate:String;
[Bindable]public var appliedEndDate:String;

/** Method fires on load of screen and set url to variable
 * and send request to fetch details  
 * */
public function setLabels():void{

	urlPrefix=commonFunction.getConstants('url')+"/studentRemedial/";
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getStudentDetails.send();
}

[Bindable]
public var details: XML;
/** Method fires on success of fetching values
 *  and set text to 5 labels on screen 
 * and sends another request for fetching remedials details
 * */ 
public function onDetailSuccess(event:ResultEvent):void{
	details=event.result as XML;
	
	programLabel.text=details.Details.programName;
	branchLabel.text=details.Details.branchName;
	specialisationLabel.text=details.Details..specializationName;
	semesterLabel.text=details.Details.semesterName;
	rollNoLabel.text=details.Details.rollNo;
	appliedStartDate=details.Details.appliedStartDate;
	appliedEndDate=details.Details.appliedEndDate;
	appliedSemesterId=details.Details.semesterId;
	
	var infoObject:Object = {};
	infoObject["programId"]=details.Details.programId;
	getRemedialDetails.send(infoObject);	
}

[Bindable]
public var remedials: XML;
/** Method fires on success of fetching remedial details
 * and sets data to grid
 * */
public function onRemedialSuccess(event:ResultEvent):void{
	remedials=event.result as XML;
	var gridRecords:ArrayCollection=new ArrayCollection();
    	
    	for each(var obj:Object in remedials.Details)
	    {
		gridRecords.addItem({select:false,programId:obj.programId,program:obj.programName,
		branchId:obj.branchId,branch:obj.branchName,specializationId:obj.specializationId,
		specialisation:obj.specializationName,semesterId:obj.semesterId,attemptNumber:obj.attemptNumber,
		semester:obj.semesterName,courseCode:obj.courseCode,courseName:obj.courseName,
		semesterStartDate:obj.semesterStartDate,semesterEndDate:obj.semesterEndDate,
		courseStatus:obj.courseStatus,applied:obj.applied,confirm:obj.confirm,programCourseKey:obj.programCourseKey});
	    }
	    remedialsGrid.dataProvider=gridRecords;
	    Mask.close();
	}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	Mask.close();
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
}

/**
 * This function first validates to apply only for that courses for which not applied yet
 * and opens apply window for apply
 * */ 
public function openApplyWindow():void{

	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(remedialsGrid);
    for each(var obj:Object in selectedValues)
    {   	var s1:String="YES";
    	if((obj.applied==s1.toLowerCase())||(obj.applied==s1.toUpperCase()))
    	{
    	Alert.show(commonFunction.getMessages('pleaseSelectOnlyThoseCoursesForWhichNotApplied'),commonFunction.getMessages('error'),0,null,clearSelected,errorIcon);   	
    	}     
    else{
	var applyWindow:sheduleExam=sheduleExam(PopUpManager.createPopUp(this,sheduleExam,true));
    
    applyWindow.currentProgramLabel.text=programLabel.text;
    applyWindow.currentBranchLabel.text=branchLabel.text;
    applyWindow.currentSpecialisationLabel.text=specialisationLabel.text;
    applyWindow.currentSemesterLabel.text=semesterLabel.text;
    applyWindow.currentRollNoLabel.text=rollNoLabel.text;
    applyWindow.remedialProgramLabel.text=selectedValues[0].program;
	applyWindow.remedialBranchLabel.text=selectedValues[0].branch;
	applyWindow.remedialSpecialisationLabel.text=selectedValues[0].specialisation;
	applyWindow.remedialSemesterLabel.text=selectedValues.getItemAt(0).semester;
	applyWindow.remedialCourseLabel.text=selectedValues[0].courseCode;
	applyWindow.remedialStartDateLabel.text=selectedValues[0].semesterStartDate;
	applyWindow.remedialEndDateLabel.text=selectedValues[0].semesterEndDate;
	applyWindow.remedialCourseStatusLabel.text=selectedValues[0].courseStatus;
	applyWindow.ApplyingSemesterLabel.text=semesterLabel.text;
	applyWindow.applyingSemesterStartLabel.text=appliedStartDate;
	applyWindow.applyingSemesterEndLabel.text=appliedEndDate;
	var i:int=int(selectedValues[0].attemptNumber);
	applyWindow.attemptNumberLabel.text=(i+1).toString();
	applyWindow.programCourseKey=selectedValues[0].programCourseKey;
	applyWindow.appliedSemesterId=appliedSemesterId;
	applyWindow.buttonFunction=setLabels;
	applyWindow.btn=applyButton;
	PopUpManager.centerPopUp(applyWindow);
    applyWindow.setFocus();
    }
    }
  }

/** this function clear selected checkboxes on click of alert of error of selection of already
 * applied courses
 * */
public function clearSelected(event:CloseEvent):void{
	
	var gridData:ArrayCollection=remedialsGrid.dataProvider as ArrayCollection;
            	
    for(var e:int=0;e<gridData.length;e++)
    {
        var gridItem:Object=gridData.getItemAt(e);
        if(gridItem.select==true){
        	
            gridItem.select=false;
            gridData.setItemAt(gridItem,e);
            remedialsGrid.dataProvider=gridData;
        }
     }
	applyButton.enabled=false;
}

//This function removes whole page
public function cancelFunction():void
{
   this.parentDocument.loaderCanvas.removeAllChildren();
}