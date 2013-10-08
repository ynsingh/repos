/**
 * @(#) InstructorCourseAction.as
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

import awardBlankSheet.marksCorrection;

import cancelRegistration.CancelRegistration;

import common.Mask;
import common.commonFunction;

import coursegradelimit.ExternalGradeLimit;
import coursegradelimit.InternalGradeLimit;

import flash.net.FileReference;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
   
	public var userId:String;
	public var _InternalGradeLimit:InternalGradeLimit;
	public var _marksCorrection:marksCorrection;
	public var _ExternalGradeLimit:ExternalGradeLimit;
	public var displayType:String;//="I";
	public var buttonPressed:String=null;
	public var employeeCode:String=null;
	public var programCourseKey:String=null;
	public var currentApprovalOrder:String;
	[Bindable]public var gradelimitdetail:Boolean  ; 
	[Bindable]public var courseapprstatus:Boolean  ; 
	
	[Bindable]public var gradelimit:String;
	
	[Bindable] private var entityTypeXml:XML;
	[Bindable] private var entityXml:XML;
	[Bindable] private var programXml:XML;
	[Bindable] private var branchXml:XML;
	[Bindable] private var specXml:XML;
	[Bindable] private var semXml:XML;
	[Bindable] private var courseXml:XML;
	[Bindable] private var componentXml:XML;
	[Bindable] private var studentXml:XML;
	[Bindable] private var marksXml:XML;
	[Bindable] private var ruleXml:XML;
	[Bindable] private var employeeXml:XML;
	[Bindable] private var keyXml:XML;
	public var gradeXML:XML
	private var componentAC:ArrayCollection=new ArrayCollection();
	private var studentListAC:ArrayCollection=new ArrayCollection();  
	private var studentMarksListAC:ArrayCollection=new ArrayCollection(); 
	public var employeeCourseArrCol:ArrayCollection;
	[Bindable] public var urlPrefix:String;
	
	[Bindable]private var columnNameList:Array; 
	[Bindable]private var columList:Array;
	[Bindable]private var columnNameListfordownload:Array; 
	[Bindable]private var columListfordownload:Array;
	[Bindable]private var dataProviderList:ArrayCollection;
	[Bindable]private var dataProviderListfordownload:ArrayCollection;
	[Bindable]private var selectedData:String;
	[Bindable]private var fileRef:FileReference;
	[Bindable]private var CTGroupName:String;
	[Bindable]private var gradelimitarraycoll :ArrayCollection = new ArrayCollection ;
	[Bindable]private var courseapprstatusarraycoll :ArrayCollection = new ArrayCollection ;
	
	
	public var cid:String="";
	
	public var s:String ;
	

	var param:Object =new Object();
	private function moduleCreationCompleteHandler():void{		
		urlPrefix=commonFunction.getConstants('url')+'/instructCourse/';
		param['time']=new Date();
		Mask.show(commonFunction.getMessages('pleaseWait')); 
		employeeCourseHttpService.send(param);
	}

	private function employeeCourseHttpServiceResultHandler(event:ResultEvent):void{
		Mask.close();		
		var employeeCourse:XML = event.result as XML;
		if(employeeCourse.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}
				
	 	param["time"]=new Date();
		employeeCourseArrCol=new ArrayCollection();
		for each(var obj:Object in employeeCourse.root){
			employeeCourseArrCol.addItem({select:false,entityId:obj.entityId, programId:obj.programId, programName:obj.programName, 
			branchId:obj.branchId, branchName:obj.branchName, specializationId:obj.specializationId, 
			specializationName:obj.specializationName, semesterCode:obj.semesterCode, semesterName:obj.semesterName, 
			semesterStartDate:obj.semesterStartDate, semesterEndDate:obj.semesterEndDate, courseCode:obj.courseCode, 
			courseName:obj.courseName, programCourseKey:obj.programCourseKey, resultSystem:obj.resultSystem, 
			employeeCode:obj.employeeCode, entityType:obj.entityType, entityName:obj.entityName, 
			startDate:obj.startDate, endDate:obj.endDate, employeeName:obj.employeeName, gradelimit:obj.gradelimit});
		}			
		courseListGrid.dataProvider=null;
		courseListGrid.dataProvider=employeeCourseArrCol;
		employeeCode=employeeCourseArrCol.getItemAt(0).employeeCode;			
		Mask.close();
	}
	
	/** 
	 * This function fires on failure of any request to give failure alert
	 */	    	
	private function faultHandler(event:FaultEvent):void{
		Mask.close();
		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	
		
	private function httpServiceFaultHandler(event: FaultEvent):void{
		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	
	public function furtherExecution(gridData:ArrayCollection):void{
		param['programId']=gridData.getItemAt(0).programId;
		param['branchId']=gridData.getItemAt(0).branchId;
		param['specializationId']=gridData.getItemAt(0).specializationId;
		param['courseCode']=gridData.getItemAt(0).courseCode;
		param['semesterCode']=gridData.getItemAt(0).semesterCode;
		param['semesterStartDate']=gridData.getItemAt(0).semesterStartDate;
		param['semesterEndDate']=gridData.getItemAt(0).semesterEndDate;
		param['time']=new Date();
		courseStudentListGrid.dataProvider=null;
		studentArrCol.removeAll();
		studentListHttpService.send(param);
		Mask.show();
	}
	
	var studentArrCol:ArrayCollection=new ArrayCollection();
	private function studentListHttpServiceResultHandler(event:ResultEvent):void{
		studentArrCol.removeAll();
		Mask.close();		
		var studentList:XML = event.result as XML;
		if(studentList.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}		
		for each(var obj:Object in studentList.student){
			studentArrCol.addItem({studentName:obj.studentName, studentId:obj.studentId, registrationNo:obj.registrationNo,rollNumber:obj.rollNumber,
			enrollmentNumber:obj.enrollmentNumber,dateOfBirth:obj.dateOfBirth,fatherName:obj.fatherName});
		}			
		if(studentArrCol.length>0){
			awardSheetCanvas.visible=true;
			courseStudentListGrid.dataProvider=null;
			courseStudentListGrid.dataProvider=studentArrCol;	
		}
		else{
			Alert.show(commonFunction.getMessages('noStudentInCourse'),(commonFunction.getMessages('info')),4,null,null,infoIcon);			
		}				
		Mask.close();
	}
	
	public function cancelRegistration():void{
		var cancelReg:CancelRegistration = new CancelRegistration();
//		var cancelReg:CancelRegistration=  CancelRegistration(PopUpManager.createPopUp(this,CancelRegistration,true));
		this.visible=false;
		this.parentDocument.loaderCanvas.addChild(cancelReg);
		try{
			cancelReg.regRollText.text=courseStudentListGrid.selectedItem.registrationNo;
			cancelReg.populateData();
//			PopUpManager.centerPopUp(cancelReg);
		}catch(e:Error){
			Alert.show(e+" Nupur");
		}
//		Alert.show('student id'+courseStudentListGrid.selectedItem.studentId);
	}
	
