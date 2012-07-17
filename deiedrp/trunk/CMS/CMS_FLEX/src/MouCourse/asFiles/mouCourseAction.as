/**
 * @(#) Quiz_Action.java
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
import MouCourse.editPopupWindow;

import common.commonFunction;

import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;
   
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

private var courseDetailGridDataProvider:ArrayCollection = new ArrayCollection();

public var infoObject:Object={};

/**
 *This function Set Focus on 1st Field Program combobox
 **/
public function setFirstFocous(object:UIComponent):void
{
	object.setFocus();
    object.drawFocus(true);
    onLoad();
}

/**
 * The function send request on load for getting input values
 * */
public function onLoad():void{
	
	courseCombo.selectedIndex=-1;
	mouUniversityCombo.selectedIndex=-1;
	savebutton.enabled=false;
	courseMouGrid.dataProvider=null;
	
	infoObject["userId"] = new Date;
	
	getMouDetails.send(infoObject);
}

/**
 * The function retrives the list of Mou universities for the 
 * concerned university idSS
 * */
public var MOUDetails:XML;
public function onInputSuccess(event:ResultEvent):void{		
		MOUDetails = event.result as XML;		
		mouUniversityCombo.dataProvider=MOUDetails.role.description;		
	}
	
	public function onFailure(event:FaultEvent):void{
		
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
}
	
/**
 * The function fires on mou iniversity change
 * enabled course combo and calls function for sending requests 
 * */
public function mouUniversityChange(event:ListEvent):void
{
	mouUniversity();	
	courseCombo.enabled=true;
	courseCombo.errorString="";
}

/**
 * The function send request for getting input values of courses and values for grid for
 * concerned university idSS
 * */
public function mouUniversity():void{
	
	infoObject["mouUniversityId"] = MOUDetails.role.(description==mouUniversityCombo.selectedLabel).id;
	
	getMouCoursesDetails.send(infoObject);
	
	getSetRecords.send(infoObject);
}

/**
 * The function retrives the list of courses for the 
 * concerned Mou university and set it to course combobox
 * */
public var MOUCourseDetails:XML;
public var MOUCourseList:ArrayCollection;
public function onCourseSuccess(event:ResultEvent):void{
		
		MOUCourseDetails = event.result as XML;
		
		MOUCourseList = new ArrayCollection();
		
		for each (var obj:Object in MOUCourseDetails.role){
			
			MOUCourseList.addItem({id:obj.id,description:obj.description});			
		}		
		
		courseCombo.dataProvider = MOUCourseList;
		courseCombo.labelField = "description";
		}

/**
 * The function fires on course change
 * enabled save button
 * */
public function courseChange(event:ListEvent):void
{
	savebutton.enabled=true;
}

/**
 * The function get retrived values for grid  
 * and set it to grid data provider
 * */
public var getCoursesDetails:XML;
public var getCourseList:ArrayCollection;
public function onGetRecordsSuccess(event:ResultEvent):void{
	
	getCoursesDetails = event.result as XML;
	getCourseList = new ArrayCollection();	
	
	for each(var o:Object in getCoursesDetails.Details){
		
		getCourseList.addItem({select:false,mouUniversityId:o.mouUniversityId,
		mouUniversityName:o.mouUniversityName,courseCode:o.courseCode,
			courseName:o.courseName,courseStatus:o.courseStatus});
		
	}		
		courseMouGrid.dataProvider = getCourseList;	
	
}


/**
 * Method to fire on the click event of the save button
 **/
public function saveDetails():void {
	
	if(Validator.validateAll([courseComboValidator]).length==0){
			
	infoObject["mouCourse"]=courseCombo.selectedItem.id;
	infoObject["activity"]="insert";
	
	setDetails.send(infoObject);
		}
		else{
			 Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);

		}
	}

/**
 * Method to fire on the click event of the delete button
 **/
public function deleteRecords():void
{	
	
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteOrNot,questionIcon);
}      

/**
 * Method to confirm deletion of record
 **/
public function deleteOrNot(event:CloseEvent):void
{   		
 	if(event.detail==1)
	{
		var recordTypeList:ArrayCollection =new ArrayCollection();	
		
		for each (var obj:Object in courseMouGrid.dataProvider){
			if(obj.select==true){
				recordTypeList.addItem(obj.courseCode);		
			}
		}		
		
		infoObject["MOURecords"] = recordTypeList;
		
		deleteMOURecords.send(infoObject);
				
	   
	}
 if(event.detail==2)
	{
		        var gridDataItems:ArrayCollection=courseMouGrid.dataProvider as ArrayCollection;
            	for(var e:int=0;e<gridDataItems.length;e++)
            	{
            	  var gridItem:Object=gridDataItems.getItemAt(e);
            	if(gridItem.select==true)
            	   {
            		gridItem.select=false;
            		gridDataItems.setItemAt(gridItem,e);
            	    courseMouGrid.dataProvider=gridDataItems;
            	   }
            	}
	 deletebutton.enabled=false;
	 editbutton.enabled=false;
	
	 }
}

/**
 * on Delete Success Handler 
 **/
public var deleteSuccess: XML; 
public function onDeleteSuccess(event:ResultEvent):void
{	
	deleteSuccess=event.result as XML;
	
	if(deleteSuccess.Details.list_values=="success"){
		
		Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),
		commonFunction.getMessages('success'),0,null,null,successIcon);
		
		editbutton.enabled=false;
		deletebutton.enabled=false;
		mouUniversity();
		
	}else{
		Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	

	}			
	
			
}

/**
 * Method to fire the click event of the edit button
 * opens edit window
 **/
private function openEditWindow():void {
	var selectedRecord:ArrayCollection=commonFunction.getSelectedRowData(courseMouGrid);
    
	if(selectedRecord.length==1){
       var editWindow:editPopupWindow =editPopupWindow(PopUpManager.createPopUp(this,editPopupWindow,true))
	  
       editWindow.courseLabel.text=selectedRecord.getItemAt(0).courseName;
       editWindow.courseId = selectedRecord.getItemAt(0).courseCode;
       editWindow.mouUniversityId = selectedRecord.getItemAt(0).mouUniversityId;
   	   editWindow.courseStatus=selectedRecord.getItemAt(0).courseStatus;
	   editWindow.edit=editbutton;
	   editWindow.delet=deletebutton;
	   editWindow.buttonFunction=mouUniversity;
	 
	   editWindow.setFocus();
	   PopUpManager.centerPopUp(editWindow);
	}
}

/**
 * method executed on processing of the updation request
 * */
[Bindable]
public var successDetails: XML;
public function onSetSuccess(event:ResultEvent):void{
	
	successDetails=event.result as XML;
	
	if(successDetails.Details.list_values=="success"){
		
		Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),
		commonFunction.getMessages('success'),0,null,null,successIcon);
		
		mouUniversity();
		
	}else{
		Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	

	}			
}

/**
 * Method to fire the click event of the cancel button,remove UI from main window
 **/
public function cancelFunction():void
{
	this.parentDocument.loaderCanvas.removeAllChildren();
}			
