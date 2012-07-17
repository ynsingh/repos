/**
 * @(#) AdvanceParentProgramAction.as
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
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

private var advanceCourseGridDataProvider:ArrayCollection = new ArrayCollection();
public var infoObject:Object = {};

/**
 * This function Set Focus on 1st Field Entity combobox
 * and send request for bringing details for input in comboboxes
 *   */
public function setFirstFocous(object:UIComponent):void {
	infoObject["userId"] = new Date();
	
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
var detailslist:ArrayCollection;
public function onSuccess(event:ResultEvent):void{
	
	details=event.result as XML;
	
	if(details.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	
	detailslist =new ArrayCollection();
	
	for each (var o:Object in details.role){
		
		detailslist.addItem({id:o.id,description:o.description});
	}		
		programCombo.dataProvider = details.role.description;		
	
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
 * method executed on program change,this method retrives the 
 * programs list excluding the selected program
 * */
protected function programChange(event:ListEvent):void
			{
	
	var advanceprogramlist:ArrayCollection = new ArrayCollection();
	
	for each(var obj:Object in detailslist)
	{
		if(obj.description!=programCombo.selectedLabel)
		{
		    advanceprogramlist.addItem({id:obj.id,description:obj.description});
		}
	}	
			advanceProgramCombo.enabled=true;
			advanceProgramCombo.selectedIndex=-1;
			advanceProgramCombo.dataProvider = advanceprogramlist;
			advanceProgramCombo.labelField = "description";
			programtext.text="";
			advanceprogramcoursegrid.dataProvider=null;
			
			programtext.text=programCombo.text;
			infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
			infoObject["advanceProgramId"] = "";
		
			getsetRecords.send(infoObject);
}

/**
 * This method retrives the course codes associated to the 
 * programs selected
 * */			
protected function advanceProgramChange():void {
	
	programtext.text=programCombo.text;
	infoObject["programId"] = details.role.(description==programCombo.selectedLabel).id;
	infoObject["advanceProgramId"] = details.role.(description==advanceProgramCombo.selectedLabel).id;
	
	
	getcoursesDetails.send(infoObject);
	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getsetRecords.send(infoObject);
	
	}

/**
 * Method executed on retriving the course list 
 * */
[Bindable]
var courseDetails: XML;
var courseList:ArrayCollection;
public function onCoursesSuccess(event:ResultEvent):void{
	
	courseDetails=event.result as XML;
	
	if(courseDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	
	courseList =new ArrayCollection();
	
	for each (var o:Object in courseDetails.role){
		
		courseList.addItem({id:o.id,description:o.description});
		
	}		
		courseCombo.dataProvider = courseList;
		courseCombo.labelField="description";	
		courseCombo.enabled=true;
		courseCombo.selectedIndex=-1;
		
		Mask.close();
}

public function showCourseCode():void{
	courseCode.text=courseCombo.selectedItem.id;
}


/** This function for Adding details In Grid
 * */
public function saveDetails():void {
     if((programCombo.selectedIndex!=-1)&&(advanceProgramCombo.selectedIndex!=-1)&&(courseCombo.selectedIndex!=-1)){
     	
     	Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onValidationSuccess,questionIcon);     	 
         
	}
    else{
		Alert.show(commonFunction.getMessages('firstSelectallMandatoryFields'),
		commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

public function onValidationSuccess(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		
		 infoObject["courseCode"] = courseCombo.selectedItem.id;          
			 
		  setDetails.send(infoObject);			 

          Alert.show(commonFunction.getMessages('savedSuccessfully'),
		  commonFunction.getMessages('saved'),0,null,null,successIcon);
		  
		  courseCode.text="";		  
			  
		  getsetRecords.send(infoObject);
			  
		  advanceProgramChange();
		
	}
	
}	

/**This function asks for conformation of delete records or not
 * then pass to delete records
 * */
public function deleteOrNot():void
	{	 
		 Alert.show(commonFunction.getMessages('conformForContinue'),
		 commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteRecords,questionIcon);	
                
	}

/** This function works on result of conformation,
 * for yes give information of records to be deleted,
 * for no back to previous state
 * */
public function deleteRecords(event:CloseEvent):void {
	if(event.detail==1){
		var courseTypeList:ArrayCollection =new ArrayCollection();	
		
		for each (var obj:Object in advanceprogramcoursegrid.dataProvider){
			if(obj.select==true){
				courseTypeList.addItem(obj.courseCode);		
			}
		}		
	
		infoObject["courseCode"] = courseTypeList;
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		deleteAdvanceProgramRecords.send(infoObject);			
		
		Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),
			commonFunction.getMessages('success'),4,this,onDeleteSuccess,successIcon);
		
		deletebutton.enabled=false;
	}
	if(event.detail==2){
		
		var gridRecords:ArrayCollection=advanceprogramcoursegrid.dataProvider as ArrayCollection;
            
        for(var e:int=0;e<gridRecords.length;e++){
            	  var gridItem:Object=gridRecords.getItemAt(e);
            	if(gridItem.select==true){
            			gridItem.select=false;
            			gridRecords.setItemAt(gridItem,e);
            	advanceprogramcoursegrid.dataProvider=gridRecords;
            	}}
	}
	
	Mask.close();
}
/**
 * This method refreshes the grid on the basis of records 
 * set in the database
 * */
public function onDeleteSuccess(event:CloseEvent):void
{	
	advanceProgramChange();
	courseCombo.dataProvider.refresh();
}

/**
 * This method retrives the records already set for the
 * program combination
 * */
[Bindable]
var recordsXml:XML;
public var recordList:ArrayCollection;
public function onRecordsSuccess(event:ResultEvent):void{	
	recordsXml=event.result as XML;	
	
	if(recordsXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	
	recordList = new ArrayCollection();	
	for each (var obj:Object in recordsXml.Details){		
		recordList.addItem({select:false,programId:obj.programId,advanceProgram:obj.advanceProgram,
			courseCode:obj.courseCode,coursename:obj.coursename});		
	}	
	advanceprogramcoursegrid.dataProvider=recordList;	
	recordList.refresh();
	
	Mask.close();
	
}

/**
 * This function removes this UI Pannel from main window
 * */
public function cancel():void
{
this.parentDocument.loaderCanvas.removeAllChildren();
}