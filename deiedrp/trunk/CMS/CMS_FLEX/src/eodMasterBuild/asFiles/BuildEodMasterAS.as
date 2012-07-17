/**
 * @(#) BuildEodMasterAS.as
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
// ActionScript file
import common.commonFunction;
import common.validations.CommonValidations;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;
			
			[Embed(source="/images/error.png")]public var errorIcon:Class;
			[Embed(source="/images/success.png")]private var successIcon:Class;
			[Embed(source="/images/question.png")]private var questionIcon:Class;
			[Embed(source="/images/warning.png")]private var warningIcon:Class;
			
			[Bindable]public var urlPrefix:String;
			private var eodControlDetailsArrCol:ArrayCollection;
			private var stepFrequencyXML:XML;
			private var methodToRunXML:XML;
			private var selectedRecordArrColl:ArrayCollection;
			
			/**
			 * @private
			 * this method is called on module creation complete.
			 */
			private function moduleCreationCompleteHandler():void{
				urlPrefix=commonFunction.getConstants('url')+"/eodcontrol/";				
			}
			
			/**
			 * @private
			 * this method is called on add save button click event. 
			 */
			private function buildButtonClickHandler():void{
				if(validateInput()){
					var param:Object = new Object();
					param["eodDate"]=dateFormatter.format(eodDateDF.text);
					buildEodMasterHttpService.send(param);	
				}
				else{
					Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
				}				
			}
			
			/**
			 * @private
			 * this method is used to validate the form inputs.
			 */
			private function validateInput():Boolean{
				var validate:CommonValidations=new CommonValidations();
				var count:int=0;
				if(Validator.validateAll([eodDateDFValidator]).length>0){
	            	return false;
	            }
	            else{
	            	return true;	
	            }
			}
					
			/**
			 * @private
			 * this method is called when a result come from the server. 
			 * @param event a Result event object.
			 */
			private function buildDetailsHttpServiceResultHandler(event:ResultEvent):void{
				var eodControlDetailsXML:XML = event.result as XML;
				if(eodControlDetailsXML.sessionConfirm == true){
	        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
	        		this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
	        	}
				
				if(eodControlDetailsXML.message=="incompleteStatus"){
					Alert.show(commonFunction.getMessages('lastDateRecordStatus'),commonFunction.getMessages('info'),4,null,null,warningIcon);
				}
				else if(eodControlDetailsXML.message=="noRecordsFoundInSelectedDate"){
					Alert.show(commonFunction.getMessages('noRecordFoundInDate'),commonFunction.getMessages('info'),4,null,null,warningIcon);
				}
				else if(eodControlDetailsXML.message=="success"){
					Alert.show(commonFunction.getMessages('recordBuildSuccess'),commonFunction.getMessages('success'),4,null,null,successIcon);
				}
				else if(eodControlDetailsXML.message=="noRecordBuild"){
					Alert.show(commonFunction.getMessages('nothingBuild'),commonFunction.getMessages('info'),4,null,null,warningIcon);
				}
				else{
					Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
				}				
			}
						
			/**
			 * @private
			 * this method is called when a failure come from the server. 
			 * @param event a fault event object.
			 */	
			private function httpServiceFaultHandler(event:FaultEvent):void{
				Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
			}
		