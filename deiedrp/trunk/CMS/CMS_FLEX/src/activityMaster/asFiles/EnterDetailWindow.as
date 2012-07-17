/**
 * @(#) EnterDetailsWindow.as
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
			import mx.events.ListEvent;
			import mx.managers.PopUpManager;
			import mx.rpc.events.FaultEvent;
			import mx.rpc.events.ResultEvent;
				
			[Bindable]public var urlPrefix:String;
			public var programCourseKey:String;
			public var refreshActivityMasterGrid:Function;
			public var programId:String;
			public var semesterCode:String;
			public var activitySequenceArrColl:ArrayCollection;
			protected var activitySequences:String="";
			
			protected var processXMLResult:XML;
			protected var activityXMLResult:XML;
			protected var semStartEndDateXMLResult:XML;
			protected var activitySequenceXMLResult:XML;
			protected var noOfActivityXMLResult:XML;
			protected var noOfActivity:int=0;
			[Bindable]public var sessionStartDate:String;
			[Bindable]public var sessionEndDate:String;
			public var selectedEntityId:String;
			
			[Embed(source="/images/error.png")]private var errorIcon:Class;
			[Embed(source="/images/success.png")]private var successIcon:Class;			
			[Embed(source="/images/question.png")]private var questionIcon:Class;

			protected function submitFormResultHandler(event:ResultEvent):void{
				var successResult:XML= event.result as XML;
				if(successResult.sessionConfirm == true){
        			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        			this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
        		}
				
				Alert.show(commonFunction.getMessages('recordSubmitSuccessMessage'), commonFunction.getMessages('success'),4, this, confirm, successIcon);
				refreshActivityMasterGrid.call();
				Mask.close();
			}
			
			protected function confirm(event:CloseEvent):void{
				if(event.detail == Alert.OK){
					Alert.show(commonFunction.getMessages('EnterMoreDetailMessage'), commonFunction.getConstants('confirm'), 3, this, resetForm,questionIcon);
				}
				Mask.close();
			}
			protected function resetForm(event:CloseEvent):void{
				if(event.detail == Alert.YES)
				{
					popupWindowInitializeHandler();
					processCB.selectedIndex=-1;
					activityCB.selectedIndex=-1;
					activitySequence.text="";
					activitySequence.errorString="";
					submitButton.enabled=false;
				}
				else{
					refreshActivityMasterGrid.call();
					popUpWindowClose();
				}
				Mask.close();
			}

			private function popUpWindowClose():void
        	{
				refreshActivityMasterGrid.call();
				PopUpManager.removePopUp(this);
				Mask.close();
        	}
            	
			/**
			 * @protected
			 * this method is called on initilize the popUp window.
			 */
        	protected function popupWindowInitializeHandler():void
        	{
        		urlPrefix=commonFunction.getConstants('url')+"/activitymaster/";
        		var param:Object={};
        		param["time"]=new Date();
				param["programCourseKey"]=programCourseKey;
				param["sessionStartDate"]=sessionStartDate;
				param["sessionEndDate"]=sessionEndDate;
				param["selectedEntityId"]=selectedEntityId;
				semesterStartEndDateHttpService.send(param);
				Mask.show(commonFunction.getMessages('pleaseWait'));
				processListHttpService.send(param);
        	}
        	
        	/**
			 * @protected
			 * this method is called when a result come from the server. 
			 * @param event a Result event object.
			 */	
        	protected function getNumberOfActivityResultHandler(event:ResultEvent):void{
        		noOfActivityXMLResult=event.result as XML;
        		if(noOfActivityXMLResult.sessionConfirm == true){
        			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        			this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        		
        		noOfActivity=int(noOfActivityXMLResult.message);
        		Mask.close();
        	}
			
			/**
			 * @protected
			 * this method is called when a result come from the server. 
			 * @param event a Result event object.
			 */		
        	protected function processListResultHandler(event:ResultEvent):void
        	{
        		processXMLResult=event.result as XML;
        		if(processXMLResult.sessionConfirm == true){
        			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        			this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        		
        		processCB.dataProvider=processXMLResult.process.@name;
        		Mask.close();
        	}
            	
			/**
			 * @protected
			 * this method is called when a result come from the server. 
			 * @param event a Result event object.
			 */	
			protected function activityListResultHandler(event:ResultEvent):void
        	{
        		activityXMLResult=event.result as XML;
        		if(activityXMLResult.sessionConfirm == true){
        			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        			this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        		
        		activityCB.dataProvider=activityXMLResult.activity.@name;
				activityCB.dataProvider.refresh();
        		Mask.close();
        	}
            	
			/**
			 * @protected
			 * this method is called when a result come from the server. 
			 * @param event a Result event object.
			 */	
        	protected function semesterStartEndDateResultHandler(event:ResultEvent):void
        	{
        		semStartEndDateXMLResult = event.result as XML;
        		if(semStartEndDateXMLResult.sessionConfirm == true){
        			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        			this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        		
        		var startDate:String = semStartEndDateXMLResult.semesterStartDate;
        		var endDate:String = semStartEndDateXMLResult.semesterEndDate;
        		
        		if(startDate=="" || endDate==""){
        			Alert.show(commonFunction.getMessages('semesterStartEndDateMissing'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		}
        		else{
        			semesterStartDateLabel.text = semStartEndDateXMLResult.semesterStartDate;
        			semesterEndDateLabel.text = semStartEndDateXMLResult.semesterEndDate;
        			processCB.enabled=true;	
        		}
        		
        		Mask.close();
        	}
				
			/**
			 * @protected
			 * this method is called when a result come from the server. 
			 * @param event a Result event object.
			 */	
        	protected function getExistingActivityResultHandler(event:ResultEvent):void
        	{
        		semStartEndDateXMLResult = event.result as XML;
        		if(semStartEndDateXMLResult.sessionConfirm == true){
        			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        			this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        		
        		Mask.close();
        	}
				
			/**
			 * @protected
			 * this method is called when a fault occur from the server. 
			 * @param event a Result event object.
			 */	
			protected function httpServiceFaultHandler(event:FaultEvent):void
			{
				Alert.show(commonFunction.getMessages('problemInService'), commonFunction.getMessages('error'),4,null,null,errorIcon);
				Mask.close();
			}
        	
        	/**
			 * @protected
			 * this method is called when a result come from the server. 
			 * @param event a Result event object.
			 */	
        	protected function existingActivitySequenceResultHandler(event:ResultEvent):void
        	{
        		activitySequences="";
        		activitySequenceXMLResult = event.result as XML;
        		if(activitySequenceXMLResult.sessionConfirm == true){
        			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        			this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        		
        		activitySequenceArrColl= new ArrayCollection();
        		for each (var o:Object in activitySequenceXMLResult.activity)
				{
					activitySequenceArrColl.addItem({activitySequence:o.activitySequence});
					activitySequences=o.activitySequence+","+activitySequences;
					
				}
				if(activitySequences==""){
					activitySequence.toolTip="Enter sequence Number";
				}
				else{
					activitySequence.toolTip=commonFunction.getMessages('alreadyUsedSequenceNumberMessage')+activitySequences;
				}	
				Mask.close();
        	}
				
			protected function processCBChangeHandler(event:ListEvent):void
        	{
        		var param:Object=new Object();
        		param["processCode"]=processXMLResult.process.(@name==processCB.selectedItem).processCode;
        		param["programCourseKey"]=programCourseKey;
				param["sessionStartDate"]=sessionStartDate;
				param["sessionEndDate"]=sessionEndDate;
				param["selectedEntityId"]=selectedEntityId;
        		activityListHttpService.send(param);
        		activityCB.enabled=true;
        		activitySequence.text="";
				activitySequence.errorString="";
        	}
            	
			/**
			 * @protected
			 * this method is called on Activity comboBox item change event.
			 * @param event a ListEvent event object.
			 */	
        	protected function activityCBChangeHandler(event:ListEvent):void
        	{
        		var param:Object=new Object();
    			submitButton.enabled=true;
        		param["programCourseKey"]=programCourseKey;
        		param["processCode"]=processXMLResult.process.(@name==processCB.selectedItem).processCode;
        		param["activityCode"]=activityXMLResult.activity.(@name==activityCB.selectedItem).activityCode;
				param["sessionStartDate"]=sessionStartDate;
				param["sessionEndDate"]=sessionEndDate;
				param["selectedEntityId"]=selectedEntityId;
				Mask.show(commonFunction.getMessages('pleaseWait'));
        		getExistingActivitySequenceHttpService.send(param);
//        		param["activityCode"]=activityXMLResult.activity.(@name==activityCB.selectedItem).activityCode;
        		getNumberOfActivityHttpService.send(param);
        	}
        	
            	
			/**
			 * @protected
			 * this method send the Form's data to server side to save.
			 * @param event a mouse event object.
			 */	
        	protected function submitButtonClickHandler(event:MouseEvent):void
			{
				if(validateForm()){
					var flag:Boolean=false;
					for each(var o:Object in activitySequenceArrColl){
						if(activitySequence.text==o.activitySequence.toString()){
							activitySequence.errorString=commonFunction.getMessages('alreadyUsedSequenceNumberMessage')+activitySequences;
							flag=true;
							break;
						}
					}
					
					if(int(activitySequence.text)>noOfActivity){
						activitySequence.errorString=commonFunction.getMessages('maxdSequenceNumberMessage')+noOfActivity;
						flag=true;
					}
					
					if(int(activitySequence.text)==0){
							activitySequence.errorString=commonFunction.getMessages('zeroIsNotAllowed');
							flag=true;
					}
					
					if(!flag){
						Alert.show(commonFunction.getMessages('areyousure'), commonFunction.getMessages('confirm'), 3, this, saveConfirm,questionIcon);
					}
					else{
						Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),4,null,null,errorIcon1);
					}
				}
			}
			
			private function saveConfirm(event:CloseEvent):void{
				if(event.detail==Alert.YES){
					var param:Object = new Object(); 
					param["programCourseKey"]=programCourseKey;
					param["semesterStartDate"]=semesterStartDateLabel.text
					param["semesterEndDate"]=semesterEndDateLabel.text
					param["process"]=processXMLResult.process.(@name==processCB.selectedItem).processCode;
					param["activity"]=activityXMLResult.activity.(@name==activityCB.selectedItem).activityCode;
					param["activitySequence"]=activitySequence.text;
					param["sessionStartDate"]=sessionStartDate;
					param["sessionEndDate"]=sessionEndDate;
					param["selectedEntityId"]=selectedEntityId;
					Mask.show(commonFunction.getMessages('pleaseWait'));
					formSubmitHttpService.send(param);
				}
			}
			
			/**
			 * @protected
			 * This function  close popup window on pressing ESC key 
			 * @param evt a Keyboard Event object.
			 */
			protected function initializeForEsc(evt:KeyboardEvent):void
			{
			    if (evt.keyCode == Keyboard.ESCAPE)
			    {
			    	PopUpManager.removePopUp(this);	
			    }
			}

			