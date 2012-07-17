/**
 * @(#) ManageEditWindow.as
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
			
	protected var activitySequenceXMLResult:XML;
	protected var activitySequenceArrColl:ArrayCollection;
	protected var activitySequences:String="";
	protected var noOfActivityXMLResult:XML;
	protected var noOfActivity:int=0;
	
	public var programCourseKey:String;
	public var refreshActivityMasterGrid:Function;
	public var programId:String;
	public var semesterCode:String;
	public var processCode:String;
	public var processName:String;
	public var activityCode:String;
	public var activityName:String;
	public var sessionStartDate:String;
	public var sessionEndDate:String;
	public var selectedEntityId:String;
	private var existingData:String;

	[Embed(source="/images/error.png")]private var errorIcon:Class;
	[Embed(source="/images/success.png")]private var successIcon:Class;
	[Embed(source="/images/question.png")]private var questionIcon:Class;
			
	[Bindable]public var urlPrefix:String;
			
		protected function updateFormResultHandler(event:ResultEvent):void{
			var resultData:XML=event.result as XML;
			if(resultData.sessionConfirm == true){
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        	}
			
			Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),4,null,null,successIcon);
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
			
			activitySequence.toolTip=commonFunction.getMessages('alreadyUsedSequenceNumberMessage')+activitySequences;
			Mask.close();
    	}
			
		/**
		 * @protected
		 * this method is called when a fault occur from the server. 
		 * @param event a Result event object.
		 */	
		protected function httpServiceFaultHandler(event:FaultEvent):void
		{
			Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
			Mask.close();
		}
		
		/**
		 * @protected
		 * this method is called on initilize the popUp window.
		 */
		protected function popupWindowInitializeHandler():void
		{
			urlPrefix=commonFunction.getConstants('url')+"/activitymaster/";
			processLabel.text=processName;
			activityLabel.text=activityName;
			existingData = activitySequence.text;
			var param:Object=new Object();
			param["programCourseKey"]=programCourseKey;
    		param["processCode"]=processCode;
    		param["activityCode"]=activityCode;
			param["sessionStartDate"]=sessionStartDate;
			param["sessionEndDate"]=sessionEndDate;
			param["selectedEntityId"]=selectedEntityId;
    		getExistingActivitySequenceHttpService.send(param);
    		param["processCode"]=processCode;
    		Mask.show(commonFunction.getMessages('pleaseWait'));
    		getNumberOfActivityHttpService.send(param);
		}
		
		/**
		 * @protected
		 * this method send the Form's data to server side to save.
		 * @param event a mouse event object.
		 */	
		protected function updateButtonClickHandler(event:MouseEvent):void
		{
			if(existingData!=activitySequence.text){
				if(validateForm()){
					var flag:Boolean=false;
					for each(var o:Object in activitySequenceArrColl){
						if(activitySequence.text==o.activitySequence.toString()){
								//Alert.show("Sequence Number Allready Used");
								activitySequence.errorString=commonFunction.getMessages('alreadyUsedSequenceNumberMessage')+activitySequences;
								flag=true;
								break;
						}
					}
					
					if(int(activitySequence.text)==0){
						activitySequence.errorString=commonFunction.getMessages('zeroIsNotAllowed');
						flag=true;
					}
					
					else if(int(activitySequence.text)>noOfActivity){
						activitySequence.errorString=commonFunction.getMessages('maxdSequenceNumberMessage')+noOfActivity;
						flag=true;
					}
					
					
					if(!flag){
						Alert.show(commonFunction.getMessages('areyousure'), commonFunction.getMessages('confirm'), 3, this, updateConfirm,questionIcon);
					}
					else{
						Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),4,null,null,errorIcon1);
					}
				
				}
				else{
					Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),4,null,null,errorIcon1);
				}
			}
			else{
				Alert.show(commonFunction.getMessages('noChangeInActivitySequence'),commonFunction.getMessages('error'),4,null,null,errorIcon1);
			}
		}
		
		private function updateConfirm(event:CloseEvent):void{
			if(event.detail==Alert.YES){
				var param:Object = new Object(); 
				param["programCourseKey"]=programCourseKey;
				param["semesterStartDate"]=semesterStartDatelabel.text;
				param["semesterEndDate"]=semesterEndDateLabel.text
				param["processCode"]=processCode;
				param["activityCode"]=activityCode;
				param["activitySequence"]=activitySequence.text;
				param["sessionStartDate"]=sessionStartDate;
				param["sessionEndDate"]=sessionEndDate;
				param["selectedEntityId"]=selectedEntityId;
				Mask.show(commonFunction.getMessages('pleaseWait'));
				formUpdateHttpService.send(param);
				refreshActivityMasterGrid.call();
				popUpWindowClose();
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
		
		protected function activitySequenceChangeHandler():void{
			updateButton.enabled=true;
		}