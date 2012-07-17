/**
 * @(#) ActivityMaster.as
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

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
			
			
		protected var params:Object={};
		protected var programCourseHeader_XmlResult:XML;
		protected var activityMasterDetails_XMLResult:XML;
		protected var branchName_XmlResult:XML;
		protected var semesterName_XmlResult:XML;
		protected var specializationName_XmlResult:XML;
		
		protected var selectedProgram_id:String="";
		protected var selectedBranch_id:String="";
		protected var selectedSemester_id:String="";
		protected var programCourseKey:String="";
		protected var programId:String=""; 
		protected var semesterCode:String="";
		[Bindable]protected var sessionStartDate:String="";
		[Bindable]protected var sessionEndDate:String="";
		[Bindable]public var urlPrefix:String;
		[Bindable]public var activityMasterDetials_ArrCol:ArrayCollection;
		
		private var entityArrayColl:ArrayCollection;
		private var selectedEntityId:String;

		[Embed(source="/images/error.png")]public var errorIcon:Class;
		[Embed(source="/images/success.png")]private var successIcon:Class;
		[Embed(source="/images/question.png")]private var questionIcon:Class;
		[Embed(source="/images/warning.png")]private var warningIcon:Class;
											
		/**
		 * @protected
		 * this method is called on the form initilization
		 * @param event a Flex event object.
		 */			 	
		 protected function formIntializeHandler():void
		 {	 
		 	urlPrefix=commonFunction.getConstants('url')+"/activitymaster/";
//		 	Mask.show(commonFunction.getMessages('pleaseWait'));
			var param:Object=new Object();
			param["time"]=new Date();
			getEntityHttpService.send(param);
			addRecordButton.enabled=false;
			Mask.close();
		 }
		 
		 private function entityCBChangeHandler():void{
			for each(var obj:Object in entityArrayColl){
				if(obj.entityName==entityCB.selectedLabel){
					selectedEntityId=obj.entityId;
					break;
				}
			}
			
			activityMasterGrid.dataProvider=null;
			var param:Object=new Object();
			param["time"]=new Date();
			param["selectedEntityId"]=selectedEntityId;
			programCourseHeaderListHttpService.send(param);
		 }
		 
		 private function getEntityHttpServiceResultHandler(event:ResultEvent):void{
			var resultData:XML = event.result as XML;
			entityArrayColl=new ArrayCollection();
			for each(var obj:Object in resultData.entity){
				entityArrayColl.addItem({entityId:obj.entityId, entityName:obj.entityName});
			}
			
			entityCB.labelField="entityName";
			entityCB.dataProvider=entityArrayColl;
		 }
		
		protected function sessionDetailsHttpServiceResultHandler(event:ResultEvent):void{
			var data:XML=event.result as XML;
			if(data.sessionConfirm == true){
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        	}
        		
			sessionStartDate=data.SessionStartDate;
			sessionEndDate=data.SessionEndDate;
			this.title=commonFunction.getConstants('activityMasterPanelTitle')+" Session: "+ sessionStartDate+ "/" +sessionEndDate;
			var param:Object=new Object();
			param["sessionStartDate"]=sessionStartDate;
			param["sessionEndDate"]=sessionEndDate;
			param["selectedEntityId"]=selectedEntityId;
			param["time"]=new Date();
			activityMasterDetailsHttpService.send(param);
			Mask.close();
		}
		
		/**
		 * @protected
		 * this method is called when a result come from the server. 
		 * @param event a Result event object.
		 */
		protected function activityMasterDetailsResultHandler(event:ResultEvent):void{
			Mask.close();
			activityMasterDetails_XMLResult=event.result as XML;
			if(activityMasterDetails_XMLResult.sessionConfirm == true){
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        	}
						
			activityMasterDetials_ArrCol= new ArrayCollection();
			for each (var o:Object in activityMasterDetails_XMLResult.activityMaster)
			{
				activityMasterDetials_ArrCol.addItem({programCourseKey:o.programCourseKey, programName:o.programName, branchName:o.branchName,
				specializationName:o.specializationName, semesterCode:o.semesterCode, semesterName:o.semesterName, semesterStartDate:o.semesterStartDate,
				semesterEndDate:o.semesterEndDate, processCode:o.processCode, processName:o.processName, 
				activityCode:o.activityCode, activityName:o.activityName, activitySequence:o.activitySequence, status:o.status});
			}
			
			Mask.close();
			//activityMasterGrid.dataProvider=activityMasterDetials_ArrCol;
		}
		
		/**
		 * @protected
		 * this method is called when a result come from the server. 
		 * @param event a Result event object.
		 */			
		protected function programCourseHeaderResultHandler(event:ResultEvent):void{
			Mask.close();
			programCourseHeader_XmlResult=event.result as XML;
			if(programCourseHeader_XmlResult.sessionConfirm == true){
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        	}
        	
        	if(programCourseHeader_XmlResult==""){
        		Alert.show(commonFunction.getMessages('recordsNotFound'), commonFunction.getMessages(""),4,null,null,warningIcon);
        	}
        	else{
				var programCourseHeader_ArrCol:ArrayCollection= new ArrayCollection();
				for each (var o:Object in programCourseHeader_XmlResult.programCourse)
				{
					programCourseHeader_ArrCol.addItem({select:false,programCourseKey:o.programCourseKey,
					programName:o.programName, branchName:o.branchName, specializationName:o.specializationName,
					 semesterCode:o.semesterCode, semesterName:o.semesterName});
				}
        	}
        	programCourseGrid.dataProvider=programCourseHeader_ArrCol;
			var param:Object=new Object();
			param["time"]=new Date();
			param["selectedEntityId"]=selectedEntityId;
			sessionDetailsHttpService.send(param);
			Mask.close();
		}
		
		/**
		 * @protected
		 * this method is called when a fault occur from the server. 
		 * @param event a Result event object.
		 */	
		protected function httpServiceFaultHandler(event:FaultEvent):void{
			Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
			Mask.close();
		}
		
			
		/**
		 * @protected
		 * this method is called on add Record button click event. 
		 * @param event a mouse event object.
		 */	
		protected function addRecordButtonClickHandler(event:MouseEvent):void
		{
			var myAC4:ArrayCollection = new ArrayCollection();
	    	var gridData:ArrayCollection=commonFunction.getSelectedRowData(programCourseGrid);
	    	var popUpWin:EnterDetailWindow=EnterDetailWindow(PopUpManager.createPopUp(this, EnterDetailWindow, true));
	    	
	        popUpWin.programNameLabel.text=gridData[0].programName;
			popUpWin.branchNameLabel.text=gridData[0].branchName;
			popUpWin.specializationLabel.text=gridData[0].specializationName;
			popUpWin.semesterCodeLabel.text=gridData[0].semesterName;
			popUpWin.programCourseKey=gridData[0].programCourseKey;
			popUpWin.programId=gridData[0].programCourseKey.toString().substr(0,7);	
			popUpWin.semesterCode=gridData[0].semesterCode;
			popUpWin.sessionStartDate=sessionStartDate;
			popUpWin.sessionEndDate=sessionEndDate;
			popUpWin.refreshActivityMasterGrid=refreshGrids;
			popUpWin.selectedEntityId=selectedEntityId;
			popUpWin.setFocus();
			PopUpManager.centerPopUp(popUpWin);
			 
		}
		
		protected function refreshGrids():void{
			addRecordButton.enabled=false;
			var param:Object=new Object();
			param["time"]=new Date();
			param["selectedEntityId"]=selectedEntityId;
			programCourseHeaderListHttpService.send(param);
		}

