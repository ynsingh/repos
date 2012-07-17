/**
 * @(#) switchRuleAction.as
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

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.core.UIComponent;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

import switchrule.editPopUp;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var infoObject:Object={};

/**This function Set Focus on 1st Field Entity combobox
 * and call onloadRequests for loading input details
 * */
public function setFirstFocous(object:UIComponent):void
{
	object.setFocus();
	object.drawFocus(true);
	onloadRequests();
}

/**This function  send request on load for input details
 * */
public function onloadRequests():void
{
	infoObject["userId"] = new Date;
	
	label1Combo.selectedIndex=-1;
	label2Combo.selectedIndex =-1;
	
	ruleFormulaField.text="";
	
	getRule3Details.send(infoObject);
	getRule4Details.send(infoObject);
	getRule5Details.send(infoObject);
	getRule6Details.send(infoObject);
	getSetRuleRecords.send(infoObject);
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
	
	if(detailsRule3list.length==0){
		
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('alert'));
	}else{
		
		label3Combo.dataProvider = detailsRule3.role.description;
		
	}	
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
		
		label4Combo.dataProvider = detailsRule4.role.description;
		
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
		label5Combo.dataProvider = detailsRule5.role.description;
		
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
		label6Combo.dataProvider = detailsRule6.role.description;
		
}

/**
 * The function retrives the data about rules added for 
 * concerned university idS
 * and set this data to grid
 * */
[Bindable]
public var details: XML;
public var detailslist:ArrayCollection;
public function onSuccess(event:ResultEvent):void{
	
	details=event.result as XML;
	
	if(details.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	
	
	detailslist =new ArrayCollection();
	
	for each (var o:Object in details.Details){
		
		detailslist.addItem({select:false,ruleId:o.ruleId,ruleCodeOne:o.ruleCodeOne,ruleCodeTwo:o.ruleCodeTwo,
			ruleCodeThree:o.ruleCodeThree,ruleCodeFour:o.ruleCodeFour,ruleCodeFive:o.ruleCodeFive,
			ruleCodeSix:o.ruleCodeSix,ruleFormula:o.ruleFormula,ruleDescthree:o.ruleDescthree,
			ruleDescfour:o.ruleDescFour,ruleDescfive:o.ruleDescFive,ruleDescsix:o.ruleDescSix});
		
	}		
		ruleDetailGrid.dataProvider = detailslist;	
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);		
}


/**This Function Checks All basic validations required of all form fields
 * and send request for saving values
 * */
public function validateForm():void
{
var i:int=Validator.validateAll([label1ComboValidator,label2ComboValidator,label3ComboValidator]).length;
    if(i==0)
	{
		if(checkFormula(ruleFormulaField.text)){
			
			Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,successValidation,questionIcon);		
		}
		else{
			ruleFormulaField.errorString=commonFunction.getMessages('enterFormulainValidLetters');
          Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);

		}
	}
	else
	{
	Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),
	(commonFunction.getMessages('error')),0,null,null,errorIcon);	
	}	
}

public function successValidation(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
	
		infoObject["ruleCode1"] = label1XmlId.label1Data.(@name==label1Combo.selectedItem).@code;
		infoObject["ruleCode2"] = label1XmlId.label1Data.(@name==label2Combo.selectedItem).@code;
		infoObject["ruleCode3"] = detailsRule3.role.(description==label3Combo.selectedLabel).id;
		if(label4Combo.selectedLabel==null){
			
			infoObject["ruleCode4"] = "";
		}else{
			infoObject["ruleCode4"] = detailsRule4.role.(description==label4Combo.selectedLabel).id;
		}	
		if(label4Combo.selectedLabel==null){
			
			infoObject["ruleCode5"] = "";
		}else{
			infoObject["ruleCode5"] = detailsRule5.role.(description==label5Combo.selectedLabel).id;
		}
		if(label4Combo.selectedLabel==null){
			
			infoObject["ruleCode6"] = "";
		}else{
			infoObject["ruleCode6"] = detailsRule6.role.(description==label6Combo.selectedLabel).id;
		}
		if(ruleFormulaField.text==null){
			
			infoObject["ruleFormula"] = "";
		}else{
			infoObject["ruleFormula"] = ruleFormulaField.text;
		}
		infoObject["activity"] = "insert";
		
		setRuleDetails.send(infoObject);
		
	}
	
}


/** This function ask for conformation for continue to delete records
 * and calls appropriate function for handling choice
 * */
public function deleteFunction():void
{
  Alert.show(commonFunction.getMessages('conformForContinue'),
  commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteOrNot,questionIcon);
}

/**This function works according conformation results
 * and send request for delete records for yes
 * and back to previous for no
 * */
public function deleteOrNot(event:CloseEvent):void
{
	if(event.detail==1)
	{
		var recordTypeList:ArrayCollection =new ArrayCollection();	
		
		for each (var obj:Object in ruleDetailGrid.dataProvider){
			if(obj.select==true){
				recordTypeList.addItem(obj.ruleId);		
			}
		}		
		
		infoObject["switchRuleRecords"] = recordTypeList;
		
		deleteSwitchRuleRecords.send(infoObject);		
	    editbutton.enabled=false;
		deletebutton.enabled=false;
	}
	if(event.detail==2)
	{
		var gridData:ArrayCollection=ruleDetailGrid.dataProvider as ArrayCollection;
	
		for(var e:int=0;e<gridData.length;e++)
		{
			var gridItem:Object=gridData.getItemAt(e);
			if(gridItem.select==true){
				gridItem.select=false;
				gridData.setItemAt(gridItem,e);
				ruleDetailGrid.dataProvider=gridData;
			    editbutton.enabled=false;
				deletebutton.enabled=false;
			}
		}
	}
}

/** This function opens an popup window with details for editing records
 * */
public function openEditPopUp():void
{
	var gridData:ArrayCollection=commonFunction.getSelectedRowData(ruleDetailGrid);
	var editWindow:editPopUp=editPopUp(PopUpManager.createPopUp(this,editPopUp,false))	
	
	editWindow.ruleCodeOne = gridData.getItemAt(0).ruleCodeOne;
	editWindow.ruleCodeTwo = gridData.getItemAt(0).ruleCodeTwo;
	editWindow.ruleCodeThree = gridData.getItemAt(0).ruleCodeThree;
	editWindow.ruleCodeFour = gridData.getItemAt(0).ruleCodeFour;
	editWindow.ruleCodeFive = gridData.getItemAt(0).ruleCodeFive;
	editWindow.ruleCodeSix = gridData.getItemAt(0).ruleCodeSix;
	editWindow.ruleFormula = gridData.getItemAt(0).ruleFormula;
	editWindow.ruleIdText.text= gridData.getItemAt(0).ruleId;
	editWindow.ruleId = gridData.getItemAt(0).ruleId;
	editWindow.editRuleFormulaField.text = gridData.getItemAt(0).ruleFormula;
	editWindow.buttonFunction = onSetSuccess;
	editWindow.editButton = editbutton;
	editWindow.deleteButton = deletebutton;
	editWindow.check=checkFormula;
	PopUpManager.centerPopUp(editWindow);  
	editWindow.setFocus();
		
}

/**
 * method executed on processing of the request
 * */
[Bindable]
public var successDetails: XML;
public function onSetSuccess(event:ResultEvent):void{
	
	successDetails=event.result as XML;
	
	if(successDetails.Details.list_values=="success"){
		ruleFormulaField.errorString="";
		Alert.show((commonFunction.getMessages('savedSuccessfully')),
		(commonFunction.getMessages('success')),0,null,null,successIcon);
		
		onloadRequests();
		
	}else{
		Alert.show(commonFunction.getMessages('requestFailed'),
		commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
	}			
}

/**
 * method executed on processing of the request
 * */
[Bindable]
public var successDeleteDetails: XML;
public function onSetDeleteSuccess(event:ResultEvent):void{
	
	successDeleteDetails=event.result as XML;
	
	if(successDeleteDetails.Details.list_values=="success"){
		ruleFormulaField.errorString="";
		Alert.show((commonFunction.getMessages('recordsDeletedSuccessfully')),
		(commonFunction.getMessages('success')),0,null,null,successIcon);
		
		onloadRequests();
		
	}else{
		Alert.show(commonFunction.getMessages('requestFailed'),
		commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
	}			
}

public function checkFormula(rule:String):Boolean
{   
   //var rule:String=ruleFormulaField.text;

   for(var g:int=0;g<rule.length;g++)
   {
     if (!((rule.charCodeAt(g) > 64) && (rule.charCodeAt(g) < 91))) //not between A to Z
     {
      if (!((rule.charCodeAt(g) > 96) && (rule.charCodeAt(g) < 123))) //not between a to z
      {
       if (!((rule.charCodeAt(g)  >47) && (rule.charCodeAt(g) < 58)) &&//b/w 0 to 9
          (rule.charCodeAt(g) != 43)) //not +
        {                
		           return false;
        } 
      }
     }
   }
return true;
}


/**
 * This function removes this UI Pannel from main window
 * */
public function cancelFunction():void
{
this.parentDocument.loaderCanvas.removeAllChildren();
	
}
