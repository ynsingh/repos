/**
 * @(#) ManageActivityMaster.as
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
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
			
	protected var activityMasterDetailsXMLResult:XML;
			
	protected var programCourseKey:String="";
	protected var semesterStartDate:String="";
	protected var semesterEndDate:String="";
	protected var processCode:String="";
	protected var processName:String="";
	protected var activityCode:String="";
	protected var activityName:String="";
	protected var activitySequence:String="";
	protected var selectedRecordArrColl:ArrayCollection;
	[Bindable]protected var sessionStartDate:String="";
	[Bindable]protected var sessionEndDate:String="";
	[Bindable]public var urlPrefix:String;
	private var selectedEntityId:String;
	private var entityArrayColl:ArrayCollection;
	
	[Embed(source="/images/error.png")]private var errorIcon:Class;
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
//			Mask.show(commonFunction.getMessages('pleaseWait'));
			var param:Object=new Object();
			param["time"]=new Date();
			getEntityHttpService.send(param);
		}

		 private function entityCBChangeHandler():void{
			for each(var obj:Object in entityArrayColl){
				if(obj.entityName==entityCB.selectedLabel){
					selectedEntityId=obj.entityId;
					break;
				}
			}
			
			var param:Object=new Object();
			param["time"]=new Date();
			param["selectedEntityId"]=selectedEntityId;
			sessionDetailsHttpService.send(param);
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
			this.title=title=commonFunction.getConstants('manageActivityMasterPanelTitle')+" Session: "+sessionStartDate+ " / "+ sessionEndDate;
			var param:Object=new Object();
			param["time"]=new Date();
			param["sessionStartDate"]=sessionStartDate;
			param["sessionEndDate"]=sessionEndDate;
			param["selectedEntityId"]=selectedEntityId;
			activityMasterDetailsHttpService.send(param);
			Mask.close();
		}
		
		private function refreshGrid():void{
			var param:Object=new Object();
			param["time"]=new Date();
			param["sessionStartDate"]=sessionStartDate;
			param["sessionEndDate"]=sessionEndDate;
			param["selectedEntityId"]=selectedEntityId;
			activityMasterDetailsHttpService.send(param);
		}
		
		/**
		 * @protected
		 * this method is called when a result come from the server. 
		 * @param event a Result event object.
		 */
				protected function activityMasterDetailsResultHandler(event:ResultEvent):void{
			activityMasterDetailsXMLResult=event.result as XML;
			if(activityMasterDetailsXMLResult.sessionConfirm == true){
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        	}
			
			var activityMasterDetialsArrCol:ArrayCollection= new ArrayCollection();
			for each (var o:Object in activityMasterDetailsXMLResult.activityMaster)
			{
				activityMasterDetialsArrCol.addItem({select:false,programCourseKey:o.programCourseKey,programName:o.programName, branchName:o.branchName,
					specializationName:o.specializationName, semesterCode:o.semesterCode, semesterName:o.semesterName, semesterStartDate:o.semesterStartDate,
					semesterEndDate:o.semesterEndDate, processCode:o.processCode, processName:o.processName, 
					activityCode:o.activityCode, activityName:o.activityName, activitySequence:o.activitySequence, status:o.status});
			}
			
			activityMasterDetailGrid.dataProvider=activityMasterDetialsArrCol;
			editButton.enabled=false;
			statusButton.enabled=false;
			deleteButton.enabled=false;
			
		//---------------------Add by Devendra for enabled/disabled search field ----------------------
			if(activityMasterDetialsArrCol.length!=0){
				semSearch.enabled=true;
				programSearch.enabled=true;
			}
			else{
				semSearch.enabled=false;
				programSearch.enabled=false;
			}
			semSearch.text="";
			programSearch.text="";
		//-----------------------------Devendra Ends----------------------------------
			Mask.close();
		}
		
		/**
		 * @protected
		 * this method is called when a result come from the server. 
		 * @param event a Result event object.
		 */
		protected function deleteActivityMasterDetailsResultHandler(event:ResultEvent):void{
			var data:XML=event.result as XML;
			if(data.sessionConfirm == true){
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        	}
        	
			refreshGrid();
			Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),4,null,null,successIcon);
			Mask.close();
		}
		
		/**
		 * @protected
		 * this method is called when a result come from the server. 
		 * @param event a Result event object.
		 */
		protected function changeActivityStatusResultHandler(event:ResultEvent):void{
			var data:XML=event.result as XML;
			if(data.sessionConfirm == true){
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        	}
        	
			refreshGrid();
			Alert.show(commonFunction.getMessages('statusChangeMessage'), commonFunction.getMessages('success'),4,null,null,successIcon);
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
		 * this method is called on add edit button click event. 
		 * @param event a mouse event object.
		 */	
		protected function editButtonClickHandler(event:MouseEvent):void
		{
			var myAC4:ArrayCollection = new ArrayCollection();
			var gridData:ArrayCollection=commonFunction.getSelectedRowData(activityMasterDetailGrid);
			
			var popUpWin:ManageEditWindow=ManageEditWindow(PopUpManager.createPopUp(this, ManageEditWindow, true));
			
			popUpWin.selectedEntityId=selectedEntityId;
			popUpWin.programNameLabel.text=gridData[0].programName;
			popUpWin.branchNameLabel.text=gridData[0].branchName;
			popUpWin.specializationLabel.text=gridData[0].specializationName;
			popUpWin.semesterCodeLabel.text=gridData[0].semesterName;
			popUpWin.programCourseKey=gridData[0].programCourseKey;
			popUpWin.semesterStartDatelabel.text =gridData[0].semesterStartDate;
			popUpWin.semesterEndDateLabel.text =gridData[0].semesterEndDate;
			popUpWin.processCode = gridData[0].processCode.toString();
			popUpWin.processName = gridData[0].processName.toString();
			popUpWin.activityCode = gridData[0].activityCode.toString();
			popUpWin.activityName = gridData[0].activityName.toString();
			popUpWin.activitySequence.text=gridData[0].activitySequence;
			popUpWin.sessionStartDate=sessionStartDate;
			popUpWin.sessionEndDate=sessionEndDate;
			popUpWin.refreshActivityMasterGrid=refreshGrid;
			popUpWin.setFocus();	
			PopUpManager.centerPopUp(popUpWin);            
			
		}
		
		/**
		 * @protected
		 * this method is called when the delete button is clicked
		 * this method will delete the selected records
		 * @param event a Mouse event object.
		 */
		protected function deleteButtonClickHandler(event:MouseEvent):void
		{
			var gridData:ArrayCollection=commonFunction.getSelectedRowData(activityMasterDetailGrid);
			selectedRecordArrColl=new ArrayCollection();
			
			for each(var o:Object in gridData){
				selectedRecordArrColl.addItem([o.programCourseKey, o.processCode,
				o.activityCode, o.semesterStartDate, o.semesterEndDate]);
			}
			Alert.show(commonFunction.getMessages('deleteConfirmMessage'), commonFunction.getMessages('confirm'), 3, this, deleteRecords,questionIcon);
					
		}
		
		/**
		 * @protected
		 * this method is called on Alert Box Close Event
		 * this method will delete the selected record.
		 * @param event a close event object.
		 */
		protected function deleteRecords(event:CloseEvent):void
		{
			if(event.detail == Alert.YES)
			{
				var param:Object=new Object();
				param["recordArrayColl"]=selectedRecordArrColl;
				param["sessionStartDate"]=sessionStartDate;
				param["sessionEndDate"]=sessionEndDate;
				param["selectedEntityId"]=selectedEntityId;
				Mask.show(commonFunction.getMessages('pleaseWait'));
				deleteActivityMasterDetailsHttpService.send(param);
				selectedRecordArrColl=null;
			} 
		}
		
		/**
		 * @protected
		 * this method will inactivate the selected record.
		 * @param event a close event object.
		 */
		protected function statusButtonClickHandler(event:MouseEvent):void
		{
			var gridData:ArrayCollection=commonFunction.getSelectedRowData(activityMasterDetailGrid);
			selectedRecordArrColl=new ArrayCollection();
			
			for each(var o:Object in gridData){
				selectedRecordArrColl.addItem([o.programCourseKey, o.processCode,
					o.activityCode, o.semesterStartDate, o.semesterEndDate, o.status]);
			}
			Alert.show(commonFunction.getMessages('statusChangeConfirmMessage'), commonFunction.getMessages('confirm'), 3, this, inactivateRecords,questionIcon);
		}
		
		/**
		 * @protected
		 * this method is called on Alert Box Close Event
		 * this method will inactivate the selected record.
		 * @param event a close event object.
		 */
		protected function inactivateRecords(event:CloseEvent):void
		{
			if(event.detail == Alert.YES)
			{
				var param:Object=new Object();
				param["recordArrayColl"]=selectedRecordArrColl;
				param["sessionStartDate"]=sessionStartDate;
				param["sessionEndDate"]=sessionEndDate;
				param["selectedEntityId"]=selectedEntityId;
				Mask.show(commonFunction.getMessages('pleaseWait'));
				changeActivityStatusHttpService.send(param);
			} 
		}
