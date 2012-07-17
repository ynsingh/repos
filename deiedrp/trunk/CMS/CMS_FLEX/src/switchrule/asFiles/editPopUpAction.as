/**
 * @(#) editPopUpAction.as
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
import mx.controls.Alert;
import mx.managers.PopUpManager;
[Embed(source="/images/success.png")]private var successIcon:Class;
import common.commonFunction;
			[Embed(source="/images/error.png")]private var errorIcon:Class;
			[Embed(source="/images/question.png")]private var questionIcon:Class;
			import mx.collections.ArrayCollection;
			import mx.events.CloseEvent;
			import mx.events.FlexEvent;
			import mx.rpc.events.FaultEvent;
			import mx.rpc.events.ResultEvent;
			import mx.controls.Button;
			
			public var ruleCodeOne:String;
			public var ruleCodeTwo:String;
			public var ruleCodeThree:String;
			public var ruleCodeFour:String;
			public var ruleCodeFive:String;
			public var ruleCodeSix:String;
			public var ruleFormula:String;
			public var infoObject:Object={};
			public var ruleId:String;
			public var buttonFunction:Function;
			public var editButton:Button;
			public var deleteButton:Button;
			public var check:Function;
			/**
 			* The function send request for fetching values   
			* on load of popup window
 			* */
			protected function titlewindowInitializeHandler(event:FlexEvent):void
			{
				infoObject["userId"] = new Date;
				
				getRule3Details.send(infoObject);
				getRule4Details.send(infoObject);
				getRule5Details.send(infoObject);
				getRule6Details.send(infoObject);				
				
				editRule1Combo.selectedItem = label1XmlId.label1Data.(@code==ruleCodeOne).@name+"";
				
				editRule2Combo.selectedItem = label1XmlId.label1Data.(@code==ruleCodeTwo).@name+"";
				
			}
			
			 /**
 			 * The function retrives the list of options for rule3 for 
			 * concerned university idSS
			 * */
			[Bindable]
			public var detailsRule3: XML;
			public var detailsRule3list:ArrayCollection;
			public function onRule3Success(event:ResultEvent):void{
				
				detailsRule3=event.result as XML;
				
				if(detailsRule3.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
				
				detailsRule3list =new ArrayCollection();
				
				for each (var o:Object in detailsRule3.role){
					
					detailsRule3list.addItem({id:o.id,description:o.description});
					
				}			
					
					editRule3Combo.dataProvider = detailsRule3.role.description;
					editRule3Combo.selectedItem = detailsRule3.role.(id==ruleCodeThree).description+"";
			}
			
			 /**
 			 * The function retrives the list of options for rule4 for 
			 * concerned university idSS
			 * */
			[Bindable]
			public var detailsRule4: XML;
			public var detailsRule4list:ArrayCollection;
			public function onRule4Success(event:ResultEvent):void{
				
				detailsRule4=event.result as XML;
				
				if(detailsRule4.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
				
				
				detailsRule4list =new ArrayCollection();
				
				for each (var o:Object in detailsRule4.role){
					
					detailsRule4list.addItem({id:o.id,description:o.description});
					
				}
				
					editRule4Combo.dataProvider = detailsRule4.role.description;
					editRule4Combo.selectedItem = detailsRule4.role.(id==ruleCodeFour).description+"";

			}
			
			/**
 			* The function retrives the list of options for rule5 for 
 			* concerned university idSS
 			* */
			[Bindable]
			public var detailsRule5: XML;
			public var detailsRule5list:ArrayCollection;
			public function onRule5Success(event:ResultEvent):void{
				
				detailsRule5=event.result as XML;
				
				if(detailsRule5.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
				
				
				detailsRule5list =new ArrayCollection();
				
				for each (var o:Object in detailsRule5.role){
					
					detailsRule5list.addItem({id:o.id,description:o.description});
					
				}
					editRule5Combo.dataProvider = detailsRule5.role.description;
					editRule5Combo.selectedItem = detailsRule5.role.(id==ruleCodeFive).description+"";

			}
			
			/**
 			* The function retrives the list of options for rule6 for 
 			* concerned university idSS
 			* */
			[Bindable]
			public var detailsRule6: XML;
			public var detailsRule6list:ArrayCollection;
			public function onRule6Success(event:ResultEvent):void{
				
				detailsRule6=event.result as XML;
				
				if(detailsRule6.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
				
				
				detailsRule6list =new ArrayCollection();
				
				for each (var o:Object in detailsRule6.role){
					
					detailsRule6list.addItem({id:o.id,description:o.description});
					
				}					
					editRule6Combo.dataProvider = detailsRule6.role.description;
					editRule6Combo.selectedItem = detailsRule6.role.(id==ruleCodeSix).description+"";
			}
			
			/**
			 * method executed on request failure 
			 * */
			public function onFailure(event:FaultEvent):void{
				
				Alert.show(commonFunction.getMessages('requestFailed'),
				commonFunction.getMessages('failure'),0,null,null,errorIcon);	
		
			}
			
			/**
			 * method executed on processing of the request
			 * */
			[Bindable]
			public var successDetails: XML;
			public function onSetSuccess(event:ResultEvent):void{
				
				successDetails=event.result as XML;
				
				if(successDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
				
				if(successDetails.Details.list_values=="success"){
										
					closePopUp();
					buttonFunction(event);
					editButton.enabled=false;
					deleteButton.enabled = false;
					
					
				}else{
					Alert.show(commonFunction.getMessages('requestFailed'),
					commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
				}			
			}
			
			
			/** This Function fires on click of update button
			 * and send request for saving changes
            * */
			public function saveChanges():void
			{
				if(check(editRuleFormulaField.text)){
					
					Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,successValidation,questionIcon);			
				
				}
				else{
			editRuleFormulaField.errorString=commonFunction.getMessages('enterFormulainValidLetters');
          Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);

				}
			}


public function successValidation(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
	
		infoObject["ruleId"] = ruleId;
				infoObject["ruleCode1"] = label1XmlId.label1Data.(@name==editRule1Combo.selectedItem).@code;
				
				infoObject["ruleCode2"] = label1XmlId.label1Data.(@name==editRule2Combo.selectedItem).@code;
				infoObject["ruleCode3"] = detailsRule3.role.(description==editRule3Combo.selectedLabel).id;
				if(editRule4Combo.selectedLabel==null){
					
					infoObject["ruleCode4"] = "";
				}else{
					infoObject["ruleCode4"] = detailsRule4.role.(description==editRule4Combo.selectedLabel).id;
				}	
				if(editRule5Combo.selectedLabel==null){
					
					infoObject["ruleCode5"] = "";
				}else{
					infoObject["ruleCode5"] = detailsRule5.role.(description==editRule5Combo.selectedLabel).id;
				}
				if(editRule6Combo.selectedLabel==null){
					
					infoObject["ruleCode6"] = "";
				}else{
					infoObject["ruleCode6"] = detailsRule6.role.(description==editRule6Combo.selectedLabel).id;
				}
				if(editRuleFormulaField.text==null){
					
					infoObject["ruleFormula"] = "";
				}else{
					infoObject["ruleFormula"] = editRuleFormulaField.text;
				}
				infoObject["activity"] = "update";
				
				setRuleDetails.send(infoObject);				
		
	}
	
}


/** executed on click of cancel button and closes pop up
 * */
public function cancelFunction():void
{
	closePopUp();
}

/** executed on click of close button and closes pop up
 * */
public function closePopUp():void
{
	PopUpManager.removePopUp(this);
}



/** executed on pressing Esc key down and closes pop up
 * */
private function initializeForEsc(evt:KeyboardEvent):void
{
	if (evt.keyCode == Keyboard.ESCAPE)
	{
		closePopUp();
	}
}