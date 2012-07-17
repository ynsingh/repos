/**
 * @(#) studentIssueAction.as
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
import mx.controls.*;
import mx.events.*;
import mx.managers.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import studentIssue.addIssueWindow;
import studentIssue.editIssueWindow;
public var btn:LinkButton=new LinkButton();

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]public var urlPrefix1:String;
public var infoObject:Object;

/**
 *This function Set Focus on roll Number field
 **/
public function setFirstFocous():void
{
	
	urlPrefix1=commonFunction.getConstants('url')+"/studentIssue/";
	
	
	getRollNo.send(new Date);
//	object.setFocus();
//    object.drawFocus(true);
}

public function showIssue():void{
	
	var s:String=(rollNoField.dataProvider as ArrayCollection).length+"";
	
	if(s=="0"){
		Alert.show("Enter Correct Roll No.");
		manageCanvas.visible=false;
		studentIssueGrid.dataProvider=null;
		
	}else{
		manageCanvas.visible=true;
		addButton.enabled=true;
		infoObject=new Object;
		infoObject["rollNo"]=rollNoField.text;
	    getIssueDetails.send(infoObject);
		
		rollNoField.editable=false;
		
	}
		
	
		
		
	
	
		
	
	
}	

public function checkValid():void{
	
	var s:String=(rollNoField.dataProvider as ArrayCollection).length+"";
	
	if(s=="0"){
		Alert.show("Enter Correct Roll No.");
	}
}	

public function getRollNoSuccess(event:ResultEvent):void{
	
	
	var dd:XML=event.result as XML;
	var arr:ArrayCollection=new ArrayCollection;
	
	for each(var o:Object in dd.Details){
		arr.addItem({rollNo:o.rollNo});
	}
	rollNoField.dataProvider=arr;
	rollNoField.labelField="rollNo";
	rollNoField.selectedIndex=-1;
}

public function reset():void{
	rollNoField.editable=true;
	rollNoField.text="";
	rollNoField.errorString="";
	manageCanvas.visible=false;
	studentIssueGrid.dataProvider=null;
	
	
}

/** This method validates that roll number only
 * either have cga=
 * */
public function validateRollNo():Boolean{
	var bool:Boolean=true;
   var roll:String=rollNoField.text;
   for(var g:int=0;g<roll.length;g++)
   {
     if (!((roll.charCodeAt(g) > 64) && (roll.charCodeAt(g) < 91))) //not between A to Z
     {
      if (!((roll.charCodeAt(g) > 96) && (roll.charCodeAt(g) < 123))) //not between a to z
      {
       if ((roll.charCodeAt(g) != 32) && (roll.charCodeAt(g) != 46)) //not between 0 to 9
        {                
		  rollNoField.errorString=commonFunction.getMessages('eNTERQualificationinValidLetters');
          Alert.show((commonFunction.getMessages('pleaseChecktheFieldsinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
          bool= false;
        }
      }
     }
   }
return bool;
}


 
public function onGridSuccess(event:ResultEvent){
var dd:XML= event.result as XML;

	var ff:ArrayCollection=new ArrayCollection();
	var ss:String="";
	for each(var obj:Object in dd.Details)
	    {
	    	if(obj.issueStatus=="OPN"){
	    		ss="Open";
	    	}
	    	else if(obj.issueStatus=="CLS"){
	    		ss="Closed";
	    	}
	    	
		ff.addItem({select:false,entityId:obj.entityId,entity:obj.entity,issueId:obj.issueId,issue:obj.issue,
		programId:obj.programId,program:obj.programName,penalityId:obj.penalityId,issueCode:obj.issueCode,
		branchId:obj.branchId,branch:obj.branchName,specializationId:obj.specializationId,
		specialization:obj.specializationName,semesterId:obj.semesterId,attemptNumber:obj.attemptNumber,
		semester:obj.semesterName,courseCode:obj.courseCode,courseName:obj.courseName,issueCode:obj.issueCode,
		semesterStartDate:obj.semesterStartDate,remarks:obj.remarks,penalityCourse1:obj.penalityCourse1,
		semesterEndDate:obj.semesterEndDate,penalityCourse2:obj.penalityCourse2,penalityCourse3:obj.penalityCourse3,
		penalityCourse4:obj.penalityCourse4,rollNo:obj.rollNo,
		issueStatus:ss,flag:obj.penalityFlag,programCourseKey:obj.programCourseKey});
	    }
	    
	    studentIssueGrid.dataProvider=ff;

	
}


/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
}

/**
 * This function opens edit window for Changing issue details
 * */ 
public function openAddWindow():void{

	var addWindow:addIssueWindow=addIssueWindow(PopUpManager.createPopUp(this,addIssueWindow,true));
		addWindow.rollNoLabel.text=rollNoField.text;
		PopUpManager.centerPopUp(addWindow);
		addWindow.setFocus();
		addWindow.buttonFunction=showIssue;
	}
  



/**
 * This function opens edit window for Changing issue details
 * */ 
public function openEditWindow():void{

	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(studentIssueGrid);
	
	var entity:String=selectedValues[0].entity;
	var program:String=selectedValues[0].program;
	var branch:String=selectedValues[0].branch;
	var specialization:String=selectedValues[0].specialization;
	var semester:String=selectedValues[0].semester;
	var semesterStartDate:String=selectedValues[0].semesterStartDate;
	var semesterEndDate:String=selectedValues[0].semesterEndDate;
	var isu:String=selectedValues[0].issue;
	var issueCode:String=selectedValues[0].issueCode;
	var status:String=selectedValues[0].issueStatus;
	var penalityCode:String=selectedValues[0].penalityCode;
	var penalityCourse1:String=selectedValues[0].penalityCourse1;
	var penalityCourse2:String=selectedValues[0].penalityCourse2;
	var penalityCourse3:String=selectedValues[0].penalityCourse3;
	var penalityCourse4:String=selectedValues[0].penalityCourse4;
	var remarks:String=selectedValues[0].remarks;
	var issueId:String=selectedValues[0].issueId;
	var programCourseKey:String=selectedValues[0].programCourseKey;
	var entityd:String=selectedValues[0].entityId;
	var programd:String=selectedValues[0].programId;
	var branchd:String=selectedValues[0].branchId;
	var specialziationd:String=selectedValues[0].specializationId;
	var semesterd:String=selectedValues[0].semesterId;
	
	
	if(status=="Closed"){
	    Alert.show("This Issue is Already closed",commonFunction.getMessages('error'),0,null,null,errorIcon);	
	}else
	{
		var editWindow:editIssueWindow=editIssueWindow(PopUpManager.createPopUp(this,editIssueWindow,true));
		editWindow.entityLabel.text=entity;
		editWindow.rollNoLabel.text=rollNoField.text;
		editWindow.programLabel.text=program;
		editWindow.branchLabel.text=branch;
		editWindow.specializationLabel.text=specialization;
		editWindow.semesterLabel.text=semester;
		editWindow.semesterStartDateLabel.text=semesterStartDate;
		editWindow.semesterEndDateLabel.text=semesterEndDate;
		editWindow.issueCodeLabel.text=isu;
		editWindow.editpenalityCodeCombo.text=penalityCode;
		editWindow.editpenalityCourse1Combo.text=penalityCourse1;
		editWindow.editpenalityCourse2Combo.text=penalityCourse2;
		editWindow.editpenalityCourse3Combo.text=penalityCourse3;
		editWindow.editpenalityCourse4Combo.text=penalityCourse4;
		editWindow.editRemarks.text=remarks;
		editWindow.issueId=issueId;
		editWindow.programCourseKey=programCourseKey;
		editWindow.entId=entityd;
		editWindow.prgId=programd;
		editWindow.brnId=branchd;
		editWindow.spcId=specialziationd;
		editWindow.semId=semesterd;
		editWindow.isuCod=issueCode;
		editWindow.buttonFunction2=showIssue;
		PopUpManager.centerPopUp(editWindow);
		editWindow.setFocus();
	}
  }


/**This function ask for conformation to delete records or not
 * */
public function deleteOrNot():void
{   		
 Alert.show((resourceManager.getString('Messages','conformForContinue')),(resourceManager.getString('Messages','conformation'))
	 ,(Alert.YES|Alert.NO),null,deleteRecords,questionIcon);	
}

/** This function works according conformation results and send request for yes and come back for no
 * */
public function deleteRecords(event:CloseEvent):void
{
	infoObject=new Object;
	if(event.detail==1){

   var issueIdList:ArrayCollection =new ArrayCollection();	
    for each(var obj:Object in studentIssueGrid.dataProvider)
	{
		if(obj.select==true){
			issueIdList.addItem(obj.issueId);
			infoObject["rollNo"]=obj.rollNo;
		}
	}

	infoObject["issueId"]=issueIdList;
	
	deleteIssues.send(infoObject);
	
//	Alert.show((resourceManager.getString('Messages','recordsDeletedSuccessfully')),
//		(resourceManager.getString('Messages','success')),0,null,null,successIcon);
//		editButton.enabled=false,deleteButton.enabled=false;
 }
 
 if(event.detail==2)
	{
		var gridRecords:ArrayCollection=studentIssueGrid.dataProvider as ArrayCollection;
            	
         for(var e:int=0;e<gridRecords.length;e++)
         {
            var gridItem:Object=gridRecords.getItemAt(e);
            if(gridItem.select==true){
            	
            	gridItem.select=false;
            	gridRecords.setItemAt(gridItem,e);
            	studentIssueGrid.dataProvider=gridRecords;
            }
        }
	    editButton.enabled=false,deleteButton.enabled=false;
   }
 }      

public function onDeleteSuccess(event:ResultEvent):void
{
	Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
}


/**
 * This function removes this UI Pannel from main window
 * */
public function cancel():void
{
this.parentDocument.loaderCanvas.removeAllChildren();
}		