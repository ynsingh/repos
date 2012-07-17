/**
 * @(#) CourseGroupAction.as
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

import courseGroup.editPopupWindow;

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
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
[Bindable]public var infoObject:Object = {};
[Bindable]public var details: XML;
[Bindable]public var branchDetails: XML;
[Bindable]public var specialiationDetails: XML;
[Bindable]public var semesterDetails: XML;
[Bindable]public var courseGroupList: XML;
[Bindable]public var courseGroupDetails: XML;
[Bindable]public var urlPrefix1:String;
[Bindable]public var urlPrefix2:String;
public var resultXml:XML;
private var courseDetailGridDataProvider:ArrayCollection = new ArrayCollection();

/**
 *This function Set Focus on 1st Field Program combobox
 **/
public function setFirstFocous(object:UIComponent):void
{
	 object.setFocus();
    object.drawFocus(true);
    
	urlPrefix1=commonFunction.getConstants('url')+"/programCourseType/";
	urlPrefix2=commonFunction.getConstants('url')+"/courseGroup/";
	
	getProgramList.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));

   
}

/**
 * get Program List Success Handler
 **/
public function getProgramSuccess(event:ResultEvent):void{
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
    details=event.result as XML;
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in details.role){	
		detailslist.addItem({id:o.id,description:o.description});	
	}
	programCombo.dataProvider = detailslist;
	programCombo.labelField="description";	
    programCombo.setFocus();
}

/**
 * Failure Handlure
 **/
public function onFailure(event:FaultEvent):void{
	Mask.close();
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
}

/**
 * Method to refresh fields 
 **/
public function refresh():void
{
	
	courseGroupCombo.selectedIndex=-1;
	courseGroupCombo.errorString="";
	courseGroupValidator.source=null;      	
}

/**
 *  Program change Handler
 **/
public function programChange(event:ListEvent):void
{
	 branchCombo.enabled=true;
     infoObject["programId"] = programCombo.selectedItem.id;
	 getBranchList.send(infoObject);
	 Mask.show(commonFunction.getMessages('pleaseWait'));
	 specilizationCombo.selectedIndex=-1;
	 specilizationCombo.enabled=false;
	 semesterCombo.selectedIndex=-1;
	 semesterCombo.enabled=false;
	 refresh();
}

/**
 *  get Branch List Success Handler
 **/
public function getBranchSuccess(event:ResultEvent):void{
	
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
    branchDetails=event.result as XML;
	
    var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in branchDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}
	branchCombo.dataProvider = detailslist;
	branchCombo.labelField="description";
	branchCombo.selectedIndex=-1;
	branchCombo.enabled=true;
}

/**
 * Branch change Handler 
 **/
protected function branchChange(event:ListEvent):void
{
	specilizationCombo.enabled=true;
	specilizationCombo.selectedIndex=-1;
	
	var infoObject:Object = {};
	
	infoObject["programId"] = programCombo.selectedItem.id;
	infoObject["branchId"] = branchCombo.selectedItem.id;
	
	getspecializationList.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	semesterCombo.selectedIndex=-1;
	semesterCombo.enabled=false;
	
	refresh();
}

/**
 *  get Specialization List Success Handler
 **/
public function getspecializationSuccess(event:ResultEvent):void
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
    specialiationDetails=event.result as XML;
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in specialiationDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}
	specilizationCombo.dataProvider = detailslist;
	specilizationCombo.labelField="description";
	specilizationCombo.selectedIndex=-1;
	specilizationCombo.enabled=true;    
}
			
/**
 * Specialization change Handler 
 **/
protected function specializationChange(event:ListEvent):void
{
	semesterCombo.enabled=true;
	semesterCombo.selectedIndex=-1;
				
	infoObject["programId"] = programCombo.selectedItem.id;
	infoObject["branchId"] = branchCombo.selectedItem.id;
	infoObject["specializationId"] = specilizationCombo.selectedItem.id;
				
	getSemesterList.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));				
	refresh();
}	
			
/**
 *  get Semester List Success Handler
 **/
public function getSemesterSuccess(event:ResultEvent):void{
	
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
    semesterDetails=event.result as XML;	
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in semesterDetails.role)
	{
		detailslist.addItem({id:o.id,description:o.description});	
	}
	semesterCombo.dataProvider = detailslist;
	semesterCombo.labelField="description";
	semesterCombo.selectedIndex=-1;
	semesterCombo.enabled=true;
}

/**
 * Semester change Handler 
 **/
protected function onSemesterChange():void
{
	minCreditCombo.enabled=true;
	maxCreditCombo.enabled=true;
	courseGroupCombo.enabled=true;
	savebutton.enabled=true;
	detailCanvas.visible=true;
	
	infoObject["programId"] = programCombo.selectedItem.id;
	infoObject["branchId"] = branchCombo.selectedItem.id;
	infoObject["specializationId"] = specilizationCombo.selectedItem.id;
	infoObject["semesterId"]=semesterCombo.selectedItem.id;
	
	getCourseGroupList.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	refresh();
}

/**
 *  get Course Group List Success Handler
 **/
public function getCourseGroupSuccess(event:ResultEvent):void
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
    courseGroupList=event.result as XML;
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in courseGroupList.Details)
	{
		detailslist.addItem({id:o.id,description:o.description});	
	}
	courseGroupCombo.dataProvider=detailslist;
	courseGroupCombo.labelField="description";
	courseGroupCombo.errorString="";
	courseGroupCombo.selectedIndex=-1;
	getDetails();
    
}

/**
 * Method to get the list of all records in CourseGroup Table
 **/
protected function getDetails():void
{
	editbutton.enabled=false;
	deletebutton.enabled=false;
	getCourseGroupDetails.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	refresh();
}	

/**
 * get Course Group Details Success Handler
 **/
public function getDetailsSuccess(event:ResultEvent):void
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
    courseGroupDetails=event.result as XML;
	var courseGroupData:ArrayCollection=new ArrayCollection();
	for each(var obj:Object in courseGroupDetails.Details)
	{
		courseGroupData.addItem({select:false,elective:obj.elective,programCourseKey:obj.programCourseKey,courseGroupName:obj.courseGroupName,
				courseGroupCode:obj.courseGroupCode,minCredits:obj.minCredits,maxCredits:obj.maxCredits,orderInMarksheet:obj.orderInMarksheet});
	}
	courseGroupDetailGrid.dataProvider=courseGroupData;
    
}

/**
 * Method to fire on the click event of the save button
 **/
public function saveDetails():void
{
	if(checkValidations())
	{
		infoObject["programId"] = programCombo.selectedItem.id;
		infoObject["branchId"] = branchCombo.selectedItem.id;
		infoObject["specializationId"] = specilizationCombo.selectedItem.id;
		infoObject["semesterId"]=semesterCombo.selectedItem.id;
		infoObject["courseGroupCode"]=courseGroupCombo.selectedItem.id;
		infoObject["elective"]=isElectiveGroup.selectedValue;
/** order In Marksheet Added By Mandeep**/
		infoObject["orderInMarksheet"]=orderInMarksheet.value;
		getDuplicateCount.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/**
 * Method to validate Course Group Screen
 * @return bool(True/False)
 **/
public function checkValidations():Boolean
{
	var bool:Boolean=false;
	courseGroupValidator.source=courseGroupCombo;
	minCreditsValidator.source=minCreditCombo;
	maxCreditsValidator.source=maxCreditCombo;
	if(Validator.validateAll([courseGroupValidator,minCreditsValidator,maxCreditsValidator]).length==0)
	{
	    bool=true;
		if(Number(minCreditCombo.text)>Number(maxCreditCombo.text))
		{
			bool=false;
			maxCreditCombo.errorString=commonFunction.getMessages('maximumCreditsCantBeLessThenMinimumCredits');
		}
		else
		{
			bool=true;
			maxCreditCombo.errorString="";
		}
	}
	
	return bool;
}

/**
 * set Course Group Success Handler 
 **/
public function setCourseGroupSuccess(event:ResultEvent):void
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
 	 resultXml=event.result as XML;
 	 if(String(resultXml.exception.exceptionstring).search("ErrorInInsert")>-1){
		callAlert("errorInsert");
     }
     else{
    	Alert.show(commonFunction.getMessages('successOnInsert'),commonFunction.getMessages('success'),0,null,onOk,successIcon);
    	}
}

  // function added by ashish mohan to call  custom alert 
   private function callAlert(msg:String):void
 {
 var myAlert:Alert;
 mx.controls.Alert.yesLabel = "Details"
  mx.controls.Alert.buttonWidth=80;
 mx.controls.Alert.show(resourceManager.getString('Messages',msg),resourceManager.getString('Messages','error'),(Alert.YES|Alert.CANCEL), this,showDetailError, errorIcon);
 }
 
 // function added by ashish mohan to show details of error
   public function showDetailError(event:CloseEvent):void{
   	if(event.detail==Alert.YES){
  	Alert.show(String(resultXml.exception.exceptionstring),commonFunction.getMessages('errorDetail'),0,null,null,infoIcon);
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
		var selectedRecord:ArrayCollection=commonFunction.getSelectedRowData(courseGroupDetailGrid);
		infoObject["programCourseKey"]=selectedRecord.getItemAt(0).programCourseKey;
		var courseGroupCodes:String="";
		for each(var obj:Object in selectedRecord)
		{
			courseGroupCodes+=obj.courseGroupCode+"|";
		}
		infoObject["courseGroupCodes"]=courseGroupCodes;
		deleteGroupService.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	}
	else
	{
		getDetails();
	}
}

/**
 * on Delete Success Handler 
 **/
public function onDeleteSuccess(event:ResultEvent):void
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
 	resultXml=event.result as XML;
 	 if(String(resultXml.exception.exceptionstring).search("ErrorInDelete")>-1){
		callAlert("cannotDelete");
     }
     else if(int(resultXml.count)==0){
     Alert.show(resourceManager.getString('Messages','recordNotDelete'),resourceManager.getString('Messages','error'),4,null,null,errorIcon);
     }
     else{
    Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,onOk,successIcon);
     }
}

/**
 * Method to fire the click event of the edit button
 **/
private function openEditWindow():void
{
	var selectedRecord:ArrayCollection=commonFunction.getSelectedRowData(courseGroupDetailGrid);
    
	if(selectedRecord.length==1)
    {
           var editWindow:editPopupWindow =editPopupWindow(PopUpManager.createPopUp(this,editPopupWindow,true))
		   
		   editWindow.programLabel.text=programCombo.selectedLabel;
		   editWindow.branchLabel.text=branchCombo.selectedLabel;
		   editWindow.specilizationLabel.text=specilizationCombo.selectedLabel;
		   editWindow.semesterLabel.text=semesterCombo.selectedLabel;
		   editWindow.maxCreditField.text=selectedRecord.getItemAt(0).maxCredits;
   		   editWindow.minCreditField.text=selectedRecord.getItemAt(0).minCredits;
		   editWindow.courseGroupLabel.text=selectedRecord.getItemAt(0).courseGroupName;
		   editWindow.courseGroupCode=selectedRecord.getItemAt(0).courseGroupCode;
		   editWindow.programCourseKey=selectedRecord.getItemAt(0).programCourseKey;
		   editWindow.isElectiveGroup.selectedValue=int(selectedRecord.getItemAt(0).elective.toString());
		   editWindow.order.value=selectedRecord.getItemAt(0).orderInMarksheet
		   editWindow.buttonFunction = getDetails;
		   editWindow.minCreditField.setFocus();
	   
		   PopUpManager.centerPopUp(editWindow);	  
    }
}		
		
/**
 * Method to fire the click event of the cancel button
 **/
public function cancelFunction():void
{
	this.parentDocument.loaderCanvas.removeAllChildren();
}			



/**
 * Method to fire the click event of the ok button
 **/
public function onOk(event:CloseEvent):void
{
	courseGroupCombo.errorString="";
	getDetails();
}

/**
 * get Duplicate Count Success Handler 
 **/
public function getDuplicateCountSuccess(event:ResultEvent):void
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
    if(int(event.result.count)==0)
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,saveOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		courseGroupCombo.selectedIndex=-1;
		courseGroupCombo.errorString=commonFunction.getMessages('duplicateEntry');
		courseGroupCombo.setFocus();
	}
}

/**
 * Method to confirm insertion of record
 **/
public function saveOrNot(event:CloseEvent):void
{
	if(event.detail==1)
	{
		infoObject["minCredits"]=minCreditCombo.text;
		infoObject["maxCredits"]=maxCreditCombo.text;
		setCourseGroupDetails.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}