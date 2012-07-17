/**
 * @(#) programCourseTypeAction.as
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

private var courseDetailGridDataProvider:ArrayCollection = new ArrayCollection();

/**This function Set Focus on 1st Field Entity combobox
 * and send request on load for input details
 * */
public function setFirstFocous(object:UIComponent):void {
	
	var infoObject:Object = {};
	
	infoObject["userId"] = new Date;	
	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
	getInputDetails.send(infoObject);
    object.setFocus();
    object.drawFocus(true);
   }
 
/**
 * The function retrives the list of programs for the 
 * concerned university idSS
 * */
[Bindable]
var details: XML;
public function onSuccess(event:ResultEvent):void{
	
	details=event.result as XML;
	
	if(details.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            } 
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in details.role){
		
		detailslist.addItem({id:o.id,description:o.description});
	}
	
	if(detailslist.length==0){
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('alert'));
	}
	else{
		programCombo.dataProvider = details.role.description;
	}
	
	Mask.close();	
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();	
}

/**
 * This Function Refresh All fields as on load
 * */
public function refresh():void
 {
    courseCombo.selectedIndex=-1;
    minCreditCombo.value=0;
    maxCreditCombo.value=0;       	
 }

/**
 * method executed on program change
 * and enable branch combo field and send request fro sending branch input values
 * */
 public function programChange(event:ListEvent):void
			{
			 branchCombo.enabled=true;
			 
			 var infoObject:Object = {};
			 
			 infoObject["userId"] = new Date;
			 infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
			 branchCombo.selectedIndex=-1;
			 
			 specilizationCombo.selectedIndex=-1;
			 semesterCombo.selectedIndex=-1;
			 refresh();
			 
			 Mask.show(commonFunction.getMessages('pleaseWait'));
			 
			 getBranchDetails.send(infoObject);
			}


/** This Method Retrieves branch values and set as input
 * */
[Bindable]
var branchDetails: XML;
public function programChangeSuccess(event:ResultEvent):void{
	
	branchDetails=event.result as XML;
	
	if(branchDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            } 
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in branchDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
	}
	
	if(detailslist.length==0){
		
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('alert'));
	}else{
		
		branchCombo.dataProvider = branchDetails.role.description;
	}
	
	Mask.close();	
}

/**
 * method executed on branch change
 * and enable specialization combo field and send request for fetching specialization input values
 * */
protected function branchChange(event:ListEvent):void
			{
	specilizationCombo.enabled=true;
	
	var infoObject:Object = {};
	
	infoObject["userId"] = new Date;
	infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
	infoObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
	specilizationCombo.selectedIndex=-1;
	
	semesterCombo.selectedIndex=-1;
	refresh();
	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
	getspecializationDetails.send(infoObject);
	
			}

/** This Method Retrieves specialiation values and set as input
 * */
[Bindable]
var specialiationDetails: XML;
public function branchChangeSuccess(event:ResultEvent):void{
	
	specialiationDetails=event.result as XML;
	
	if(specialiationDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in specialiationDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}
	
	if(detailslist.length==0){
		
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('alert'));
	}else{
		
		specilizationCombo.dataProvider = specialiationDetails.role.description;
	}	
	
	Mask.close();
}

/**
 * method executed on specialization change
 * and enable semester combo field and send request for fetching semester input values
 * */			
protected function specializationChange(event:ListEvent):void
			{
				semesterCombo.enabled=true;
				var infoObject:Object = {};
				
				infoObject["userId"] = new Date;
				infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
				infoObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
				infoObject["specializationId"] =  specialiationDetails.role.(description==specilizationCombo.selectedLabel).id;
				semesterCombo.selectedIndex=-1;
								
				refresh();
				
				Mask.show(commonFunction.getMessages('pleaseWait'));
				
				getsemesterDetails.send(infoObject);
			}	

/** This Method Retrieves semester values and set as input
 * */			
[Bindable]
var semesterDetails: XML;
public function specializationChangeSuccess(event:ResultEvent):void{
	
	semesterDetails=event.result as XML;
	
	if(semesterDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }	
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in semesterDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}
	
	if(detailslist.length==0){
		
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('alert'));
	}else{
		
		semesterCombo.dataProvider = semesterDetails.role.description;
	}
	
	Mask.close();	
}

/**
 * method executed on semester change
 * and send request for fetching course input values
 * and enable course,max credit,min credit fields
 * */
protected function semesterChange():void
			{
	
	var courseTypeObject:Object = {};
	
	courseTypeObject["userId"] = new Date;
	courseTypeObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
	courseTypeObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
	courseTypeObject["specializationId"] =  specialiationDetails.role.(description==specilizationCombo.selectedLabel).id;
	courseTypeObject["semesterCode"] = semesterDetails.role.(description==semesterCombo.selectedLabel).id;
	
	getCourseTypeDetails.send(courseTypeObject);	
	courseCombo.enabled=true;
	minCreditCombo.enabled=true;
	maxCreditCombo.enabled=true;
				
	var infoObject:Object = {};
				
	infoObject["userId"] = new Date;
	infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
	infoObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
	infoObject["specializationId"] =  specialiationDetails.role.(description==specilizationCombo.selectedLabel).id;
	infoObject["semesterCode"] = semesterDetails.role.(description==semesterCombo.selectedLabel).id;
				
	getRecords.send(infoObject);
				
	refresh();
				
	}	

/** This Method Retrieves course values and set as input
 * */
[Bindable]
var courseTypeDetails: XML;
public function semesterChangeSuccess(event:ResultEvent):void{
	
	courseTypeDetails=event.result as XML;	
	
	if(courseTypeDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	
    var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in courseTypeDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}		
		courseCombo.dataProvider = courseTypeDetails.role.description;
		courseCombo.dataProvider.refresh();	
}

/** This Method executed on course change and set errorstring to null
 * */
public function courseChange(event:ListEvent):void
{
	courseCombo.errorString="";
}

/**
 * method executed on click of Save for saving details 
 * this validate required fields 
 * and send request for sending details
 * */
public function checkValidations():void
{
	var invalidentry:Boolean=false;
	if((programCombo.selectedIndex!=-1)&&(branchCombo.selectedIndex!=-1)&&(specilizationCombo.selectedIndex!=-1)&&(semesterCombo.selectedIndex!=-1)&&(courseCombo.selectedIndex!=-1)&&(minCreditCombo.value!=0)&&(maxCreditCombo.value!=0))
     { 
     	if(maxCreditCombo.value>=minCreditCombo.value)
       	 {
       	 	
       	 	Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onSuccessfulRecordEnter,questionIcon);

       	 }
       	 else
       	 {
       	 Alert.show((commonFunction.getMessages('maximumCreditsCantBeLessThenMinimumCredits')),(commonFunction.getMessages('error')),0,null,null,errorIcon);	
       	 }
     }
     else
     {
       Alert.show((commonFunction.getMessages('firstSelectallMandatoryFields')),(commonFunction.getMessages('error')),0,null,null,errorIcon);     	
     }
}

public function onSuccessfulRecordEnter(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		
		var infoObject:Object = {};
			
			infoObject["userId"] = new Date;
			infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
			infoObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
			infoObject["specializationId"] =  specialiationDetails.role.(description==specilizationCombo.selectedLabel).id;
			infoObject["semesterCode"] = semesterDetails.role.(description==semesterCombo.selectedLabel).id;
			infoObject["courseType"] = courseTypeDetails.role.(description==courseCombo.selectedLabel).id;
			infoObject["minCredits"] = minCreditCombo.value;
			infoObject["maxCredits"] = maxCreditCombo.value;
			infoObject["activityFlag"] = "insert";
			
			Mask.show(commonFunction.getMessages('pleaseWait'));
			
			setProgramCoursetypeSummary.send(infoObject);	
	}			
	
}

/** Executed on successful submission of values in table
 * gives alert of successfull submission
 * */
public function onSuccessfulEntry(event:ResultEvent):void{
	
	Alert.show((commonFunction.getMessages('savedSuccessfully')),
		(commonFunction.getMessages('saved')),
		4,this,onOK,successIcon);
		
		Mask.close();
		
	refresh();	
}

/** This function ask for conformation for continue to delete records
 * and calls appropriate function for handling choice
 * */
public function deleteOrNot():void
{   		
 Alert.show((commonFunction.getMessages('conformForContinue')),(commonFunction.getMessages('confirm'))
	 ,(Alert.YES|Alert.NO),null,deleteRecords,questionIcon);	
}

/**This function works according conformation results
 * and send request for delete records for yes
 * and back to previous for no
 * */
public function deleteRecords(event:CloseEvent):void
{
	if(event.detail==1)
	{

var courseTypeList:ArrayCollection =new ArrayCollection();	

for each (var obj:Object in courseDetailGrid.dataProvider){
	if(obj.select==true){
		courseTypeList.addItem(obj.courseType);		
	}
}
    var infoObject:Object = {};
	
	infoObject["userId"] = new Date;
	infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
	infoObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
	infoObject["specializationId"] =  specialiationDetails.role.(description==specilizationCombo.selectedLabel).id;
	infoObject["semesterCode"] = semesterDetails.role.(description==semesterCombo.selectedLabel).id;
	infoObject["courseType"] = courseTypeList;
	
	deleteCoursetypeSummaryRecords.send(infoObject);
                 
        Alert.show((commonFunction.getMessages('recordsDeletedSuccessfully')),
			(commonFunction.getMessages('success')),4,this,onDeleteSuccess,successIcon);
		
		editbutton.enabled=false,deletebutton.enabled=false;
 }
 
 if(event.detail==2)
	{
		var gridRecords:ArrayCollection=courseDetailGrid.dataProvider as ArrayCollection;
 
            	for(var e:int=0;e<gridRecords.length;e++)
            	{
            	  var gridItem:Object=gridRecords.getItemAt(e);
            	if(gridItem.select==true)
            	   {
            		gridItem.select=false;
            		gridRecords.setItemAt(gridItem,e);
            	    courseDetailGrid.dataProvider=gridRecords;
            	   }
            	}
	 editbutton.enabled=false,deletebutton.enabled=false;
	 }
}					




/**
 *Method to fire the click event of the button
 * ie. to reflect the changes made in the record
 * onto the application 
 **/
public function onOK(event:CloseEvent):void{
	
		var infoObject:Object = {};
		
		infoObject["userId"] = new Date;
		infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
		infoObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
		infoObject["specializationId"] =  specialiationDetails.role.(description==specilizationCombo.selectedLabel).id;
		infoObject["semesterCode"] = semesterDetails.role.(description==semesterCombo.selectedLabel).id;
		
		getRecords.send(infoObject);
		
		semesterChange();
}

/** method executed on succesfull deletion of records 
 * and refreshes course combo 
 * and reload grid Data
 * */
public function onDeleteSuccess(event:CloseEvent):void{	
	semesterChange();
	courseCombo.dataProvider.refresh();
}

/** this method executed on fetching data from tables 
 * this set fetched data in grid 
 * */
[Bindable]
var recordsXml:XML;
public var recordList:ArrayCollection;
public function onRecordsSuccess(event:ResultEvent):void{
	
	recordsXml=event.result as XML;	
	
	 recordList = new ArrayCollection();
	
	
	for each (var obj:Object in recordsXml.Details){
		
		recordList.addItem({select:false,courseType:obj.courseType,courseTypeDescription:obj.courseTypeDescription,
			minCredits:obj.minCredits,maxCredits:obj.maxCredits});
		
	}

	courseDetailGrid.dataProvider=recordList;
	
	recordList.refresh();
	
}

/** This function opens an popup window with details for editing records
 * */
private function openEditWindow():void
{
	var s3:ArrayCollection=commonFunction.getSelectedRowData(courseDetailGrid);
	var colc:int=s3.length;
	var z:int=0;
	
	var s6:Object=s3.getItemAt(0);
	if(s6.select==true)
	{
		var editWindow:editPopupWindow =editPopupWindow(PopUpManager.createPopUp(this,editPopupWindow,true))
		PopUpManager.centerPopUp(editWindow);                  	
		editWindow.changemaxcombo.value=s6.maxCredits;
		editWindow.changemincombo.value=s6.minCredits;
		editWindow.coursetypeLabel.text=s6.courseTypeDescription;
		
		editWindow.programLabel.text=programCombo.text;
		editWindow.branchLabel.text=branchCombo.text;
		editWindow.specilizationLabel.text=specilizationCombo.text;
		editWindow.semesterLabel.text=semesterCombo.text;
		
		editWindow.programId = details.role.(description==programCombo.selectedLabel).id;
		editWindow.branchId = branchDetails.role.(description==branchCombo.selectedLabel).id;
		editWindow.specializationId = specialiationDetails.role.(description==specilizationCombo.selectedLabel).id;
		editWindow.semesterCode = semesterDetails.role.(description==semesterCombo.selectedLabel).id;
		editWindow.courseTypeCode = s6.courseType;
		
		editWindow.buttonFunction = onOK;
		editWindow.editButton=editbutton;
		editWindow.deleteButton=deletebutton;
		editWindow.setFocus();
	}
	
}

/**
 * This function removes this UI Pannel from main window
 * */		
public function cancelFunction():void{
this.parentDocument.loaderCanvas.removeAllChildren();
}								