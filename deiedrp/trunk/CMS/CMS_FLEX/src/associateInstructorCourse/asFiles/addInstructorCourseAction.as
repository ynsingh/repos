/**
 * @(#) addInstructorCourseAction.as
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

import associateInstructorCourse.assignPopupWindow;

import common.commonFunction;

import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;


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

/**
 * @protected
 * function will called on the popup window creation 
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
protected function courseInstructorDetailsHttpServiceResultHandler(event:ResultEvent):void{
	courseInstructorDetails= event.result as XML;
	if(courseInstructorDetails.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	courseInstructorsArrCol= new ArrayCollection();
	for each (var o:Object in courseInstructorDetails.courseInstructors)
	{
		courseInstructorsArrCol.addItem({select:false,programCourseKey:o.programCourseKey, courseCode:o.courseCode, courseName:o.courseName,
		employeeCode:o.employeeCode, employeeName:o.employeeName, status:o.status});
	}
	
	addInstructorGrid.dataProvider=courseInstructorsArrCol;
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
	entityList = event.result as XML
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
 * this method is called when a fault occur from the server.
 * This function Set Focus on 1st Field Entity combobox 
 * @param object a UICompent object.
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
 * This function enables programcombo and make grid null on program change 
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
 * this Function Called in above all change functons and this make null both grid and both semester date values 
 */
public function makeNullOnChange():void{
	addInstructorGrid.dataProvider=null;
	semesterStartDateField.text=null;
	semesterEndDateField.text=null;
}	

/**
 * @protected 
 * This function enables semestercombo and make grid null on  specilizationchange 
 * @param event a ListEvent object.
 */
protected function semesterChange():void{			    
    resetbutton.enabled=true;
	assignButton.enabled=false;
	addInstructorGrid.dataProvider=null;
	var param:Object=new Object();
	param["programId"]=programList.program.(programName==programCombo.selectedLabel).programId;
	param["branchId"]=branchList.branch.(branchName==branchCombo.selectedLabel).branchId;
	param["specializationId"]=specializationList.specialization.(specializationName==specializationCombo.selectedLabel).specializationId;
	param["semesterCode"]=semesterList.semester.(semesterName==semesterCombo.selectedLabel).semesterCode;
	semesterDateHttpService.send(param);				
	//showDataInGrid();
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
	addInstructorGrid.dataProvider=null;
 }	

/**
 * @protected 
 * This function allow user to only select that records for which instructor has not assigned,
 * and call function for assignPopup window 
 */
public function assignFunction():void{
	
	var assignGridData:ArrayCollection=addInstructorGrid.dataProvider as ArrayCollection;
    var instructorAssigned:int=0;
    for(var d:int=0;d<assignGridData.length;d++)
     {
         var gridItem:Object=assignGridData.getItemAt(d);
           if(gridItem.select==true)
            {
            	var compare1:String=gridItem.employeeName;
        	  	var compare2:String="Not Assigned";
           	  	if(compare1.toUpperCase()!=compare2.toUpperCase())
            	{
            	   instructorAssigned=instructorAssigned+1;
            	}
            }
     }
          if(instructorAssigned==0)
           {
              openAssignWindow();	
           }
           else
           {
                 	Alert.show(resourceManager.getString("Messages","courseAssignedAlert",[compare1]),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO)
           	,null,assignConfirm,questionIcon);
           // Alert.show((commonFunction.getMessages('selectOnlyThatCoursesForWhichInstructorHasNotAssigned')),(commonFunction.getMessages('error')),0,null,null,errorIcon);	
           } 
  }
public function assignConfirm(event:CloseEvent):void{	
	if(event.detail==Alert.YES){
		openAssignWindow();
	}
}

/**
 * @private
 * this method is called on Assign button click event.
 * this function open popup window for assigning intructor to course 
 */	
private function openAssignWindow():void{
 	var gridData:ArrayCollection=commonFunction.getSelectedRowData(addInstructorGrid);
    var assignWindow:assignPopupWindow =assignPopupWindow(PopUpManager.createPopUp(this,assignPopupWindow,true))
	assignWindow.entityId=entityList.entity.(entityName==entityCombo.selectedLabel).entityId;
	assignWindow.semesterStartDate=semesterStartDateField.text;
	assignWindow.semesterEndDate=semesterEndDateField.text;  
	assignWindow.changecourse.dataProvider=gridData;
	assignWindow.selectedGridData=gridData;
	assignWindow.refreshGrid=semesterChange;
	assignWindow.setFocus();
    PopUpManager.centerPopUp(assignWindow);
    
}


/**
 * @public
 * this function calls on click of cancel button 
 */
public function cancelFunction():void{
	this.parentDocument.loaderCanvas.removeAllChildren();
}				