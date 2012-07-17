/**
 * @(#) studentInfoMainAction.as
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

import flash.net.navigateToURL;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import studentInformation.studentCourseList;
import studentInformation.studentSemesterSummary;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;

[Bindable]public var urlPrefix:String;
[Bindable]public var appliedSemesterId:String;
[Bindable]public var appliedStartDate:String;
[Bindable]public var appliedEndDate:String;

/** Method fires on load of screen and set url to variable
 * and send request to fetch details  
 * */
public function init():void{

	urlPrefix=commonFunction.getConstants('url')+"/studentMarksSummary/";
	Mask.show(commonFunction.getMessages('pleaseWait'));
	var infoObject:Object = {};
	infoObject["time"]=new Date;
	getRollNumber.send(infoObject);
}

public var rollNumberDetail: XML;
var rollNumberCollection:ArrayCollection=new ArrayCollection();
/** Method fires on success of fetching values for roll number
 * and sends another request for fetching courses details
 * */ 
public function getRollNumberSuccess(event:ResultEvent):void{
	rollNumberDetail=event.result as XML;
	
	for each(var obj:Object in rollNumberDetail.rollNumber){
		rollNumberCollection.addItem({rollNo:obj.rollNo,rollDetail:obj.rollNo+"-"+obj.programName+"-"+obj.branchName+"-"+obj.specialization,
		entityId:obj.entityId,programId:obj.programId,programName:obj.programName,branchId:obj.branchId,
		branchName:obj.branchName,specializationId:obj.specializationId,specialization:obj.specialization});
	}
	rollNumberCB.dataProvider = rollNumberCollection;
	
//	rollNumberCB.dataProvider = rollNumberDetail.rollNumber.rollNo;
	if(rollNumberCollection.length==1){
		rollNumberCB.selectedIndex=0;
		onRollNoCombo();
	}
	 Mask.close();
}

var semesterCollection:ArrayCollection=new ArrayCollection();
public var semesterDetail: XML;

/** Method fires on success of fetching values for all semester list
 * and sets value to data grid
 * */ 
public function getAllSemesterSuccess(event:ResultEvent):void{
	belowCanvas.visible="true";
	semesterCollection.removeAll();
	programLbl.text=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).programName;
	//programLbl.text=String(rollNumberDetail.rollNumber.(rollNo==rollNumberCB.selectedItem).programName);
	branchLbl.text=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).branchName;
//	branchLbl.text=String(rollNumberDetail.rollNumber.(rollNo==rollNumberCB.selectedItem).branchName);
specializationLbl.text=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).specialization;
//	specializationLbl.text=String(rollNumberDetail.rollNumber.(rollNo==rollNumberCB.selectedItem).specialization);
	semesterDetail = event.result as XML;
	for each(var obj:Object in semesterDetail.semester){
		semesterCollection.addItem({programCourseKey:obj.programCourseKey,semesterCode:obj.semesterCode,semesterName:obj.semesterName,
		semesterStartDate:obj.semesterStartDate,semesterEndDate:obj.semesterEndDate,name:obj.name});			
    }  
	semesterDislpay.dataProvider=semesterCollection;
	Mask.close();
}


public var courseDetail: XML;
/** Method fires on success of fetching values for courses
 * and sets values in another window with all the available courses
 * */ 
public function getCoursesSuccess(event:ResultEvent):void{
	courseDetail=event.result as XML;	
	var courseCollection:ArrayCollection=new ArrayCollection();	
	for each(var obj:Object in courseDetail.course){
		courseCollection.addItem({courseCode:obj.courseCode,courseName:obj.courseName,semesterStartDate:obj.semesterStartDate,
		semesterEndDate:obj.semesterEndDate,courseStatus:obj.courseStatus,studentStatus:obj.studentStatus,attemptNumber:obj.attemptNumber});
	}	
				
	var courseListWindow:studentCourseList = new studentCourseList();
	this.visible=false;
	this.parentDocument.loaderCanvas.addChild(courseListWindow);
	if(courseCollection.length != 0){	
	try{
		courseListWindow.programLbl.text=this.programLbl.text;
		//String(rollNumberDetail.rollNumber.(rollNo==rollNumberCB.selectedItem).programName);
//		courseListWindow.branchLbl.text=String(rollNumberDetail.rollNumber.(rollNo==rollNumberCB.selectedItem).branchName);
//		courseListWindow.specializationLbl.text=String(rollNumberDetail.rollNumber.(rollNo==rollNumberCB.selectedItem).specialization);
		courseListWindow.branchLbl.text=this.branchLbl.text;
		courseListWindow.specializationLbl.text=this.specializationLbl.text;		
		courseListWindow.semesterLbl.text=semesterDislpay.selectedItem.semesterName;
		courseListWindow.programCourseKey=semesterDislpay.selectedItem.programCourseKey;
		courseListWindow.courseDislpay.dataProvider=courseCollection;
		courseListWindow.semesterStartDateLbl.text=courseCollection.getItemAt(0).semesterStartDate;
		courseListWindow.semesterEndDateLbl.text=courseCollection.getItemAt(0).semesterEndDate;
		courseListWindow.rollNumber=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).rollNo;
	}
	catch(e:Error){
		Alert.show(e+" Nupur");
	}
	}
	this.parentDocument.loaderCanvas.addChild(courseListWindow);
}


public var marksDetail: XML;


/** Method fires on success of fetching marks detail for selected course
 * and sents value in a arraycollection
 * */ 
public function getMarksDetailSuccess(event:ResultEvent):void{
	marksDetail=event.result as XML;
	var componentCollection:ArrayCollection=new ArrayCollection();
	var restCollection:ArrayCollection=new ArrayCollection(); 	
	for each(var obj:Object in marksDetail.marks){
		componentCollection.addItem({evaluationId:obj.evaluationId,mark:obj.mark});
		restCollection.addItem({totalInternal:obj.totalInternal,totalExternal:obj.totalExternal,totalMarks:obj.totalMarks,
								internalGrade:obj.internalGrade,externalGrade:obj.externalGrade,finalGradePoint:obj.finalGradePoint,
								semesterName:obj.semesterName,semesterStartDate:obj.semesterStartDate,semesterEndDate:obj.semesterEndDate});
    }   
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
 * This function open another window with list of courses
 * */
public function listCourses():void{
	var programCourseKey:String=semesterDislpay.selectedItem.programCourseKey;	
	var params:Object=new Object();
	params["rollNumber"] = rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).rollNo;
	params["programCourseKey"]=programCourseKey;
	params["time"] = new Date();
	getCourses.send(params);
}
/**
 * This function fetches semester summary information
 * */
public function semesterSummary():void{
	var programCourseKey:String=semesterDislpay.selectedItem.programCourseKey;	
	var params:Object=new Object();
	params["rollNumber"] =rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).rollNo;
	params["programCourseKey"]=programCourseKey;
	params["time"] = new Date();
	getSemesterSummary.send(params);
}

public var semesterSummaryDetail: XML;
/** Method fires on success of fetching values for semester summary
 * and sets values in another window showing semester summary information
 * */ 
public function getSemesterSummarySuccess(event:ResultEvent):void{
	semesterSummaryDetail=event.result as XML;
	//Alert.show(semesterSummaryDetail+'');
	var semesterSummaryWindow:studentSemesterSummary = new studentSemesterSummary();
	this.visible=false;
	this.parentDocument.loaderCanvas.addChild(semesterSummaryWindow);
	try{	
		semesterSummaryWindow.rollNoLbl.text=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).rollNo;
		semesterSummaryWindow.programLbl.text=this.programLbl.text;
		semesterSummaryWindow.branchLbl.text=this.branchLbl.text;	
		semesterSummaryWindow.specializationLbl.text=this.specializationLbl.text;
		semesterSummaryWindow.semesterLbl.text=semesterDislpay.selectedItem.semesterName;	
		semesterSummaryWindow.semesterStartDateLbl.text=semesterSummaryDetail.semesterSummary.semesterStartDate;
		semesterSummaryWindow.semesterEndDateLbl.text=semesterSummaryDetail.semesterSummary.semesterEndDate;
		semesterSummaryWindow.cgpaLbl.text=semesterSummaryDetail.semesterSummary.cgpa;
		semesterSummaryWindow.theoryCgpaLbl.text=semesterSummaryDetail.semesterSummary.theoryCgpa;	
		semesterSummaryWindow.practicalCgpaLbl.text=semesterSummaryDetail.semesterSummary.practicalCgpa;	
		semesterSummaryWindow.sgpaLbl.text=semesterSummaryDetail.semesterSummary.sgpa;
		semesterSummaryWindow.theorySgpaLbl.text=semesterSummaryDetail.semesterSummary.theorySgpa;
		semesterSummaryWindow.practicalSgpaLbl.text=semesterSummaryDetail.semesterSummary.practicalSgpa;
	}
	catch(e:Error){
		Alert.show(e+" Nupur");
	}					
	this.parentDocument.loaderCanvas.addChild(semesterSummaryWindow);
}
/**
 * This function open another window with progress card
 * */
public function progressCard():void{
	var programCourseKey:String=semesterDislpay.selectedItem.programCourseKey;
	var semesterStartDate:String=semesterDislpay.selectedItem.semesterStartDate;
	var semesterEndDate:String=semesterDislpay.selectedItem.semesterEndDate;
	var semesterCode:String=semesterDislpay.selectedItem.semesterCode;	
	 var params:Object=new Object();
	 params["semesterStartDate"]=semesterStartDate;
	 params["semesterEndDate"]=semesterEndDate;
	 params["time"]=new Date();
	 params["rollNumber"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).rollNo;
	 params["programCourseKey"]=programCourseKey;
	 params["entityId"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).entityId;
	 params["programId"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).programId;
	 params["branchId"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).branchId;
	 params["specializationId"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).specializationId;
	 params["reportType"]="PSS";
	 params["reportCode"]=8;
	 params["semesterCode"]=semesterCode;
	 getPathProgressCard.send(params);	   		
}

/** Method fires on success of fetching values for path for progress card
 * */ 
public function getPathProgressCardSuccess(event:ResultEvent):void{
	var pathDetail:XML=event.result as XML;	
	var path:String=pathDetail.Path;
//Alert.show(path);
	if(path.substr(0,5)=="false"){
		if(path.length>5){
	 		Alert.show(path.substr(6,path.length-6),commonFunction.getMessages('error'),0,null,null,errorIcon);
	 	}
	 	else{
			Alert.show(commonFunction.getMessages('failToGenerate'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	 	}		       
	 }		 
	 else{
	 	try{	
			navigateToURL(new URLRequest(path),'Progress_Card');
		}catch(e:Error){
			Alert.show("Progress card is not available !");
		}
	 }
}

//This function removes whole page
public function cancelFunction():void
{
   this.parentDocument.loaderCanvas.removeAllChildren();
}

/** Method fires when roll number combo box changes
 * and sends request to fetch value for all semester list
 * */ 
public function onRollNoCombo():void{
	var infoObject:Object = {};
	infoObject["rollNumber"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).rollNo;
	infoObject["programId"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).programId;
	infoObject["branchId"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).branchId;
	infoObject["specializationId"]=rollNumberCollection.getItemAt(rollNumberCB.selectedIndex).specializationId;
	//Mask.show(commonFunction.getMessages('pleaseWait'));
	belowCanvas.visible="true";
	getAllSemester.send(infoObject);
	 Mask.close();
	}
