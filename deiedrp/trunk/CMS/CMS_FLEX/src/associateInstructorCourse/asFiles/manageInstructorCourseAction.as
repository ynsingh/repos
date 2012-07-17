/**
 * @(#) manageInstructorCourseAction.as
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
 
import associateInstructorCourse.editpopup;

import common.commonFunction;

import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

//import windows.SimplePopupWindow;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

protected var entityList:XML; 
protected var programList:XML;
protected var branchList:XML;
protected var specializationList:XML;
protected var semesterList:XML;
protected var semesterDate:XML;
protected var courseInstructorDetails:XML;
protected var courseInstructorsArrCol:ArrayCollection; 
[Bindable] protected var urlPrefix:String;
private var mygrid:ArrayCollection = new ArrayCollection();

/**
 * @protected
 * function will called on the form creation 
 */
protected function applicationCreationHandler():void{
	urlPrefix=commonFunction.getConstants('url')+"/associatecoursewithinstructor/";
	entityListHttpService.send();
}
			
/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function deleteCourseInstructorHttpServiceResultHandler(event:ResultEvent):void{
	var deleteResult:XML=event.result as XML;
	if(deleteResult.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	if(deleteResult.result.message=="success"){
		Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),(commonFunction.getMessages('success')),4,null,null,successIcon);	
	}
	else{
		Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
	}
	semesterChange();		
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function deleteCourseInstructorHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);		
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function courseInstructorDetailsHttpServiceResultHandler(event:ResultEvent):void{
	courseInstructorDetails= event.result as XML;
	if(courseInstructorDetails.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	courseInstructorsArrCol= new ArrayCollection();
	for each (var o:Object in courseInstructorDetails.courseInstructors){
		courseInstructorsArrCol.addItem({select:false,programCourseKey:o.programCourseKey, courseCode:o.courseCode, courseName:o.courseName,
		employeeCode:o.employeeCode, employeeName:o.employeeName,status:o.status});		
	}
	manageInstructorGrid.dataProvider=courseInstructorsArrCol;
}
			
/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function courseInstructorDetailsHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
}
			
/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function semesterDateHttpServiceResultHandler(event:ResultEvent):void{
	semesterDate= event.result as XML;
	if(semesterDate.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	var startDate:String=semesterDate.semester.semesterStartDate;
	var endDate:String=semesterDate.semester.semesterEndDate;
	
	if(startDate=="" && endDate==""){
		Alert.show(commonFunction.getMessages('semesterDatesMissing'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	else{
		semesterStartDateField.text=startDate;
		semesterEndDateField.text=endDate;
	}	
	
	var param:Object=new Object();
	param["selectedEntityId"]=entityList.entity.(entityName==entityCombo.selectedLabel).entityId;
	param["programId"]=programList.program.(programName==programCombo.selectedLabel).programId;
	param["branchId"]=branchList.branch.(branchName==branchCombo.selectedLabel).branchId;
	param["specializationId"]=specializationList.specialization.(specializationName==specializationCombo.selectedLabel).specializationId;
	param["semesterCode"]=semesterList.semester.(semesterName==semesterCombo.selectedLabel).semesterCode;
	param["semesterStartDate"]=semesterStartDateField.text;
	param["semesterEndDate"]=semesterEndDateField.text;
	courseInstructorDetailsHttpService.send(param);
	
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */			
protected function semesterDateHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function semesterListHttpServiceResultHandler(event:ResultEvent):void{
	semesterList = event.result as XML;
	if(semesterList.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	semesterCombo.dataProvider=semesterList.semester.semesterName;
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */	
protected function semesterListHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);				
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function specializationListHttpServiceResultHandler(event:ResultEvent):void{
	specializationList = event.result as XML;
	if(specializationList.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	specializationCombo.dataProvider=specializationList.specialization.specializationName;
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function specializationListHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function branchListHttpServiceResultHandler(event:ResultEvent):void{
	branchList = event.result as XML;
	if(branchList.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	branchCombo.dataProvider=branchList.branch.branchName;
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function branchListHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function programListHttpServiceResultHandler(event:ResultEvent):void{
	programList = event.result as XML;
	if(programList.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	programCombo.dataProvider=programList.program.programName;
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function programListHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function entityListHttpServiceResultHandler(event:ResultEvent):void{
	entityList = event.result as XML;
	if(entityList.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	entityCombo.dataProvider=entityList.entity.entityName;
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function entityListHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);	
}

/**
 * @public
 * This function Set Focus on 1st Field Entity combobox 
 * @param object a UIComponent object.
 */
public function setFirstFocous(object:UIComponent):void{
    object.setFocus();
    object.drawFocus(true);
}

/**
 * @protected 
 * This function enables programcombo and make grid null on entity change 
 * @param event a ListEvent object.
 */
protected function entityChange(event:ListEvent):void{
	programCombo.enabled=true;
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specializationCombo.selectedIndex=-1;
	semesterCombo.selectedIndex=-1;
	makeNullOnChange();
	var param:Object=new Object();
	param["entityId"]=entityList.entity.(entityName==entityCombo.selectedLabel).entityId;
	programListHttpService.send(param);
}

/**
 * @protected 
 * This function enables program combo box and make grid null on program change 
 * @param event a ListEvent object.
 */
protected function programChange(event:ListEvent):void{
	branchCombo.enabled=true;
	branchCombo.selectedIndex=-1;
	specializationCombo.selectedIndex=-1;
	semesterCombo.selectedIndex=-1;
	makeNullOnChange();
	var param:Object=new Object();
	param["programId"]=programList.program.(programName==programCombo.selectedLabel).programId;
	param["entityId"]=entityList.entity.(entityName==entityCombo.selectedLabel).entityId;
	branchListHttpService.send(param);
}


/**
 * @protected 
 * This function enables branch combo and make grid null on branch change 
 * @param event a ListEvent object.
 */
protected function branchChange(event:ListEvent):void{
	specializationCombo.enabled=true;
	specializationCombo.selectedIndex=-1;
	semesterCombo.selectedIndex=-1;
	makeNullOnChange();
	var param:Object=new Object();
	param["programId"]=programList.program.(programName==programCombo.selectedLabel).programId;
	param["entityId"]=entityList.entity.(entityName==entityCombo.selectedLabel).entityId;
	param["branchId"]=branchList.branch.(branchName==branchCombo.selectedLabel).branchId;
	specializationListHttpService.send(param);	
}


/**
 * @protected 
 * This function enables specilizationcombo and make grid null on branch change 
 * @param event a ListEvent object.
 */
protected function specializationChange(event:ListEvent):void{
	semesterCombo.enabled=true;
	semesterCombo.selectedIndex=-1;
	makeNullOnChange();
	var param:Object=new Object();
	param["programId"]=programList.program.(programName==programCombo.selectedLabel).programId;
	semesterListHttpService.send(param);
}	

/**
 * @public 
 * this set everything to null state as it was on loading page 
 */
public function makeNullOnChange():void{
	manageInstructorGrid.dataProvider=null;
	semesterStartDateField.text=null;
	semesterEndDateField.text=null;
}	

/**
 * @protected 
 * This function enables semestercombo and make grid null on  specilizationchange
 */		
protected function semesterChange():void{
	editbutton.enabled=false;
	deleteButton.enabled=false;
	resetbutton.enabled=true;
	manageInstructorGrid.dataProvider=null;
	var param:Object=new Object();
	param["programId"]=programList.program.(programName==programCombo.selectedLabel).programId;
	param["branchId"]=branchList.branch.(branchName==branchCombo.selectedLabel).branchId;
	param["specializationId"]=specializationList.specialization.(specializationName==specializationCombo.selectedLabel).specializationId;
	param["semesterCode"]=semesterList.semester.(semesterName==semesterCombo.selectedLabel).semesterCode;
	semesterDateHttpService.send(param);				
	//	showDataInGrid();
}



/**
 * @public 
 * this set everything to null state as it was on loading page 
 */
public function refresh():void{
	entityCombo.selectedIndex=-1;
	programCombo.selectedItem=-1;
	branchCombo.selectedIndex=-1;
	specializationCombo.selectedIndex=-1;
	semesterCombo.selectedIndex=-1;
	semesterStartDateField.text=null;
	semesterEndDateField.text=null;
	
	manageInstructorGrid.dataProvider=null;
 }	

/**
 * @private 
 * this method is called on the edit button click 
 */
private function openEditpopup():void{
	var gridData:ArrayCollection=commonFunction.getSelectedRowData(manageInstructorGrid);
	var editWindow:editpopup=editpopup(PopUpManager.createPopUp(this,editpopup,true));
	
	editWindow.entityId=entityList.entity.(entityName==entityCombo.selectedLabel).entityId.toString();
	editWindow.programCoursekey=gridData.getItemAt(0).programCourseKey;
	editWindow.courseNameLabel.text=gridData.getItemAt(0).courseName;
	editWindow.courseCode=gridData.getItemAt(0).courseCode;
	editWindow.employeeName=gridData.getItemAt(0).employeeName;
	editWindow.employeeCode=gridData.getItemAt(0).employeeCode;
	editWindow.statusValue=gridData.getItemAt(0).status.toString();
	editWindow.semesterStartDate=semesterStartDateField.text;
	editWindow.semesterEndDate=semesterEndDateField.text;	
	editWindow.refreshGrid=semesterChange;
	editWindow.setFocus();
	PopUpManager.centerPopUp(editWindow);
}


/**
 * @public 
 * this function allows user to select multiple records for deletion and ask for conformation 
 */
public function deleteOrNot():void{
	Alert.show(commonFunction.getMessages('deleteConfirmMessage'),(commonFunction.getMessages('confirm')),3,this,deleteRecords,questionIcon);
}


/**
 * @public 
 * this function works according to user answer of conformation
 * if user chose yes then continue to delete else for no go back to previous state 
 */
public function deleteRecords(event:CloseEvent):void
{
	if(event.detail == Alert.YES){
		var gridData:ArrayCollection=commonFunction.getSelectedRowData(manageInstructorGrid);
		var selectedRecordArrColl:ArrayCollection=new ArrayCollection();
		var entityId:String=entityList.entity.(entityName==entityCombo.selectedLabel).entityId;
		
		for each(var o:Object in gridData){
			selectedRecordArrColl.addItem([o.programCourseKey, o.courseCode, o.employeeCode, entityId,
				semesterStartDateField.text, semesterEndDateField.text]);
		}
		
		var param:Object=new Object();
		param["selectedData"]=selectedRecordArrColl;
		deleteCourseInstructorHttpService.send(param);	
	}
}


/**
 * @public 
 * set focous on press tab key 
 */
public function entityTabChange():void{
	programCombo.setFocus();
}


/**
 * @public 
 * set focous on press tab ke 
 */
public function cancelFunction():void{
	this.parentDocument.loaderCanvas.removeAllChildren();
}				