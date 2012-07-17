/**
 * @(#) EditWindowEodControlAS.as
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
		import common.Mask;
		import common.commonFunction;
		
		import mx.collections.ArrayCollection;
		import mx.controls.Alert;
		import mx.events.CloseEvent;
		import mx.managers.PopUpManager;
		import mx.rpc.events.FaultEvent;
		import mx.rpc.events.ResultEvent;
			
			[Embed(source="/images/error.png")]private var errorIcon:Class;
			[Embed(source="/images/success.png")]private var successIcon:Class;
			[Embed(source="/images/question.png")]private var questionIcon:Class;
			
			[Bindable]public var urlPrefix:String;
			public var gridRecords:ArrayCollection;
			public var stepFrequencyXML:XML;
			public var gridRefreshFunction:Function;
			public var selectedDay:String;
			public var selectedMMDD:String;
			public var flag:String;
			private var dayXML:XML=<root>
										<item>
											<day code="MON" value="Monday"></day>
										</item>
										<item>
											<day code="TUE" value="Tuesday"></day>
										</item>
										<item>
											<day code="WED" value="Wednesday"></day>
										</item>
										<item>
											<day code="THU" value="Thursday"></day>
										</item>
										<item>
											<day code="FRI" value="Friday"></day>
										</item>
										<item>
											<day code="SAT" value="Saturday"></day>
										</item>
										<item>
											<day code="SUN" value="Sunday"></day>
										</item>
										<item>
											<day code="ANY" value="Any Day"></day>
										</item>
									</root>;
			
			private var flagXML:XML=<root>
										<item>
											<startPoint code="S" value="Start of Month"></startPoint>
										</item>
										<item>
											<startPoint code="E" value="End of Month"></startPoint>
										</item>
									</root>;
									
			
		
		/**
		 * @protected
		 * this method is called on initilize the popUp window.
		 */
		private function popupWindowCreationCompleteHandler():void
		{
			urlPrefix=commonFunction.getConstants('url')+"/eodcontrol/";
			stepFrequencyCB.dataProvider = stepFrequencyXML.frequency.stepFrequencyDescription;
			stepFrequencyCB.selectedItem = gridRecords.getItemAt(0).stepFrequencyDescription;
			
			if(gridRecords.getItemAt(0).stepFrequencyCode=="DLY"){
				buildTimeCB.visible=false;
				mmdd.visible=false;
				buildTimeLabel.text="";
			}
			else if(gridRecords.getItemAt(0).stepFrequencyCode=="WKY"){
				buildTimeCB.dataProvider = dayXML.item.day.@value;
				buildTimeCB.selectedItem = dayXML.item.day.(@code==selectedDay).@value.toString();
				buildTimeCB.visible=true;
				mmdd.visible=false;
				buildTimeLabel.text=commonFunction.getConstants('buildDay');
			}
			else if(gridRecords.getItemAt(0).stepFrequencyCode=="MTH"){
				buildTimeCB.dataProvider = flagXML.item.startPoint.@value;
				buildTimeCB.selectedItem = flagXML.item.startPoint.(@code==flag).@value+"";
				buildTimeCB.visible=true;
				mmdd.visible=false;
				buildTimeLabel.text=commonFunction.getConstants('buildTime');
			}
			else if(gridRecords.getItemAt(0).stepFrequencyCode=="YRL"){
				buildTimeCB.visible=false;
				mmdd.visible=true;
				mmdd.monthsCombo.selectedIndex= int(selectedMMDD.substring(0,2))-1;
				mmdd.selectedDate=int(selectedMMDD.substring(3,5));
				buildTimeLabel.text=commonFunction.getConstants('selectBuildDate');
			}
		}
		
		private function updateButtonClickHandler():void{
			Alert.show(commonFunction.getMessages('updateConfirmMessage'), commonFunction.getMessages('success'),3,this,updateConfirmFunction,successIcon);
		}
		
		private function updateConfirmFunction(event:CloseEvent):void{
			if(event.detail==Alert.YES){
				var param:Object = new Object();
				param["time"] = new Date();
				param["phase"] = gridRecords.getItemAt(0).phase;
				param["dependentPhase"] = dependentPhaseNS.value;
				param["step"] = gridRecords.getItemAt(0).step;
				param["stepFrequencyCode"] = stepFrequencyXML.frequency.(stepFrequencyDescription==stepFrequencyCB.selectedLabel).stepFrequencyCode;
				param["periodActiveFrom"] = dateFormatter.format(periodActiveFromDF.text);
				param["periodActiveTo"] = dateFormatter.format(periodActiveToDF.text);
				param["methodToRunCode"] = gridRecords.getItemAt(0).methodToRunCode;
				
				if(gridRecords.getItemAt(0).status=="Inactive"){
					param["status"] = "INA";	
				}
				else{
					param["status"] = "ACT";
				}
				var frequencyCode:String = stepFrequencyXML.frequency.(stepFrequencyDescription==stepFrequencyCB.selectedLabel).stepFrequencyCode;
				if(frequencyCode=="WKY"){
					param["buildDay"]=dayXML.item.day.(@value==buildTimeCB.selectedLabel).@code+"";
				}
				else if(frequencyCode=="MTH"){
					param["flag"]=flagXML.item.startPoint.(@value==buildTimeCB.selectedLabel).@code+"";
				}
				else if(frequencyCode=="YRL"){
					param["mmdd"]=mmdd.selectedValue;
				}
				updateEodControlHttpService.send(param);	
			}				
		}
		
		private function updateEodControlHttpServiceResultHandler(event:ResultEvent):void{
			var resultData:XML = event.result as XML;
			if(resultData.message=="success"){
				Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'), commonFunction.getMessages('success'),4,null,null,successIcon);
				popUpWindowClose();
			}
			else if(resultData.message=="nothingUpdated"){
				Alert.show(commonFunction.getMessages('recordNotUpdate'),commonFunction.getMessages('error'),4,null,null,errorIcon);
			}
			else{
				Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
			}
		}	
			
		/**
		 * @protected
		 * This function  close popup window on pressing ESC key 
		 * @param evt a Keyboard Event object.
		 */
		private function initializeForEsc(evt:KeyboardEvent):void
		{
		    if (evt.keyCode == Keyboard.ESCAPE)
		    {
		    	PopUpManager.removePopUp(this);	
		    }
		}
		
		private function popUpWindowClose():void
		{
			gridRefreshFunction.call();
			PopUpManager.removePopUp(this);	
			Mask.close();		
		}
		
		private function httpServiceFaultHandler(event:FaultEvent):void{
			Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);			
		}
		
