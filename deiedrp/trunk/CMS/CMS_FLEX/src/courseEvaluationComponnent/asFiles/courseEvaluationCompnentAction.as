/**
 * @(#) courseEvaluationComponentAction.as
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

import courseEvaluationComponnent.evaluationComponentWindow;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;

[Bindable]protected var urlPrefix:String;
public var pclist:XML;
public var ceclist:XML;
public var ceclistarray:ArrayCollection=new ArrayCollection();
public var programCourseListArray:ArrayCollection=new ArrayCollection();

[Bindable]public var parms:Object={};

/** getting all course evaluation details **/
public function getAllEvaluationDetails():void
{
	var gridrecords:ArrayCollection=commonFunction.getSelectedRowData(programCourseGrid);
	if(gridrecords.length>0)
	{
		parms["coursecode"]=gridrecords.getItemAt(0).coursecode;
	    parms["programid"]=gridrecords.getItemAt(0).programid;
	    parms["semestercode"]=gridrecords.getItemAt(0).semestercode;
	 		
		getCecListService.send(parms);
		Mask.show("Please Wait");
	}
	else
	{
		componentsGrid.dataProvider=null;
	}
	editButton.enabled=false;
	deleteButton.enabled=false;
}
		    	
/** get course evaluation details success handler **/
public function getceclistSuccess(event:ResultEvent):void
{
	Mask.close();
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
    ceclist=event.result as XML;
	componentsGrid.dataProvider=null;
	ceclistarray.removeAll();
	for each(var obj:Object in ceclist.rec )
	{
	ceclistarray.addItem({select:false,evaluationId:obj.evaluationid,iddescription:obj.iddescription,
		groupName:obj.groupName,group:obj.groupid,rule:obj.rule,ruleName:obj.ruleName,
			orderInMarksheet:obj.orderinmarksheet,daysToDisplayMarks:obj.datetodisplay,daysFromDisplayMarks:obj.datefromdisplay,
				maximumMarks:obj.maximummark ,examDate:obj.examdate});
	}
	
	componentsGrid.dataProvider = ceclistarray ;
}          

/** on creation complete of page **/
protected function applicationCreationHandler():void{
	urlPrefix=commonFunction.getConstants('url')+"/CourseEvaluationComponent/";
	
	parms["date"]=new Date();
	getProgramCourseService.send();
	Mask.show("Please Wait");
}

/** getting program course details success handler **/
public function getprogramcourseSuccess(event:ResultEvent):void
{
	Mask.close();
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
    pclist=event.result as XML;
	
	for each(var obj:Object in pclist.rec )
	{
		programCourseListArray.addItem({select:false,coursecode:obj.coursecode, semesterName:obj.componentdescription,programName:obj.programname,
		programid:obj.programid,semestercode:obj.semestercode,programcode:obj.programcode });
	}
	
	Mask.close();
 	programCourseGrid.dataProvider=programCourseListArray;
}

/** fault handler **/
public function onFailure(event:FaultEvent):void{
	Mask.close();
 	Alert.show(commonFunction.getMessages('getProgramCourseFail'),commonFunction.getMessages('error'),0,null,null,errorIcon);//Change by Devendra
}

/** this method open add program switch popupwindow **/
public function addComponent():void
{
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(programCourseGrid);
	
	var addWindow:evaluationComponentWindow=evaluationComponentWindow(PopUpManager.createPopUp(this,evaluationComponentWindow,true));
	
	addWindow.programLabel.text=selectedValues[0].programName;
	addWindow.programid=selectedValues[0].programid;
	addWindow.semestercode=selectedValues[0].semestercode;
    addWindow.semesterLabel.text=selectedValues[0].semesterName;
	addWindow.courseLabel.text=selectedValues[0].coursecode;
	addWindow.updateButton.visible=false;
	addWindow.saveButton.visible=true;
	addWindow.referenceFunction=getAllEvaluationDetails;
	
	PopUpManager.centerPopUp(addWindow);
	
}
 
/**
 * This function opens edit window for Changing Evaluationcomponent details
 * */ 
public function openEditWindow():void{
	var aboveselectedValues:ArrayCollection=commonFunction.getSelectedRowData(programCourseGrid);
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(componentsGrid);

	var editWindow:evaluationComponentWindow=evaluationComponentWindow(PopUpManager.createPopUp(this,evaluationComponentWindow,true));
	editWindow.programLabel.text=aboveselectedValues[0].programName.toString();
	editWindow.semesterLabel.text=aboveselectedValues[0].semesterName.toString();
	editWindow.courseLabel.text=aboveselectedValues[0].coursecode.toString();
	editWindow.evaluation=selectedValues.getItemAt(0).iddescription.toString();
	editWindow.evaluationIdCombo.enabled=false;
	editWindow.group=selectedValues.getItemAt(0).groupName.toString();
	editWindow.groupCombo.enabled=false;
	editWindow.rule=selectedValues.getItemAt(0).ruleName.toString();
	var examdate:String=selectedValues.getItemAt(0).examDate.toString();
	editWindow.examDateField.selectedDate=int(examdate.substr(3,2));
	editWindow.examDateField.monthsCombo.selectedIndex=int(examdate.substr(0,2))-1;
	editWindow.maximumMarks.value=int(selectedValues[0].maximumMarks.toString());

	var dateTo:String=selectedValues.getItemAt(0).daysToDisplayMarks.toString();
	editWindow.datetoDispalyMarks.selectedDate=int(dateTo.substr(3,2));
	editWindow.datetoDispalyMarks.monthsCombo.selectedIndex=int(dateTo.substr(0,2))-1;
	
	var dateFrom:String=selectedValues.getItemAt(0).daysFromDisplayMarks.toString();
	editWindow.datefromDispalyMarks.selectedDate=int(dateFrom.substr(3,2));
	editWindow.datefromDispalyMarks.monthsCombo.selectedIndex=int(dateFrom.substr(0,2))-1;
	editWindow.orderinMarkshet.value=int(selectedValues[0].orderInMarksheet.toString());
	editWindow.updateButton.visible=true;
	editWindow.saveButton.visible=false;
	editWindow.programid=aboveselectedValues[0].programid;
	editWindow.referenceFunction=getAllEvaluationDetails;
	
	PopUpManager.centerPopUp(editWindow);
  }


/** delete course evaluation details **/
public function deleteRecords():void
{   		
 Alert.show((resourceManager.getString('Messages','conformForContinue')),(resourceManager.getString('Messages','conformation'))
	 ,(Alert.YES|Alert.NO),null,deleteOrNot,questionIcon);	
}

/** delete confirmation handler **/
public function deleteOrNot(event:CloseEvent):void
{
	if(event.detail==1)
	{

	    var aboveselectedValues:ArrayCollection=commonFunction.getSelectedRowData(programCourseGrid);
		var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(componentsGrid);
	
		parms["course"] = aboveselectedValues[0].coursecode.toString();
		parms["programid"]	= aboveselectedValues[0].programid.toString();
		parms["evalid"] = "";
		parms["idgrp"]  = "";
			
		for each(var obj:Object in selectedValues)
		{
			parms["evalid"]=parms["evalid"]+obj.evaluationId+"|";
			parms["idgrp"]=parms["idgrp"]+obj.group+"|";
		}

		deleteCecService.send(parms);
		Mask.show("Please Wait");
	}
}

/** delete course evaluation details success handler **/
public function deleteCecSuccess(event:ResultEvent):void
{
	Mask.close();
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
    Alert.show((resourceManager.getString('Messages','recordsDeletedSuccessfully')),
			(resourceManager.getString('Messages','success')),0,null,null,successIcon);
			getAllEvaluationDetails();
} 

/**
 * This function removes this UI Pannel from main window
 * */
public function cancel():void
{
this.parentDocument.loaderCanvas.removeAllChildren();
}	  