/**
 * @(#) evaluationComponentAction.as
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

import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

private var courseDetailGridDataProvider:ArrayCollection = new ArrayCollection();

public var infoObject:Object={};
public var eComp:String;

/**
 *This function Set Focus on 1st Field Program combobox
 **/
public function setFirstFocous(object:UIComponent):void{
	object.setFocus();
    object.drawFocus(true);
    onLoad();
	}

/**
 * The function send request on load for getting input values
 * */           
public function onLoad():void{
	editbutton.enabled=false;
	deletebutton.enabled =false;
	
	evaluationIdCombo.selectedIndex=-1;
	idTypeCombo.selectedIndex=-1;
	displayTypeCombo.selectedIndex=-1;
	evaluationIdDescription.text="";
	
	infoObject["userId"] = new Date;
	getsetRecords.send(infoObject);
	infoObject["evaluationComponent"] = "components";
	eComp = "components";
	

Mask.show(commonFunction.getMessages('pleaseWait'));
	
	getInputDetails.send(infoObject);
	
	
}

/**
 * The function retrives the list of programs for the 
 * concerned university id
 * */
[Bindable]
public var details: XML;
public var detailsList:ArrayCollection;
public function onSuccess(event:ResultEvent):void{
	
	details=event.result as XML;	
	
	detailsList =new ArrayCollection();
	
	for each (var o:Object in details.role){
		
		detailsList.addItem({id:o.id,description:o.description});
		
	}
	
	if(detailsList.length==0){
		
		evaluationIdCombo.dataProvider=null;
		
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('alert'),0,null,null,errorIcon);
	}else{
		
		if(eComp=="components"){
			
			evaluationIdCombo.dataProvider = details.role;
			evaluationIdCombo.labelField="id";
			
		}
		else if(eComp=="idtypes"){			
			
			idTypeCombo.dataProvider = details.role;
			idTypeCombo.labelField = "description";
			
		}
		else if(eComp=="displayType"){
			
			displayTypeCombo.dataProvider = details.role;
			displayTypeCombo.labelField = "description";
		}
	}
	
	Mask.close();
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
}

/**
 * The function fires on evaluation id change
 * and  send requests for inputs
 * */
public function evaluationIdChange(event:ListEvent):void
{
 evaluationIdDescription.text=evaluationIdCombo.selectedItem.description;
 
 infoObject["evaluationComponent"] = "idtypes";
 eComp = "idtypes";
 
 Mask.show(commonFunction.getMessages('pleaseWait'));
 getInputDetails.send(infoObject);
 
}

/**
 * The function fires on id Type change
 * and  send requests for inputs
 * */
public function idTypeChange(event:ListEvent):void{
	
	infoObject["evaluationComponent"] = "displayType";
	 eComp = "displayType";
	 
	 infoObject["evaluationComponentId"] = evaluationIdCombo.selectedItem.id;
	 
	 Mask.show(commonFunction.getMessages('pleaseWait'));
	 
	 getInputDetails.send(infoObject);
	
}

/**
 * The function get retrived values for grid  
 * and set it to grid data provider
 * */
[Bindable]
public var recordDetails: XML;
public var recordDetailsList:ArrayCollection;
public function onGetRecordsSuccess(event:ResultEvent):void{
	
	recordDetails=event.result as XML;
	
	if(recordDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }		
	
	recordDetailsList =new ArrayCollection();
	
	for each (var o:Object in recordDetails.Details){
		
	   recordDetailsList.addItem({select:false,evaluationId:o.evaluationId,idCode:o.idCode,
	   idDescription:o.idDescription,displayCode:o.displayCode,displayDescription:o.displayDescription});
		
	}
	
	evaluationComponentGrid.dataProvider = recordDetailsList;
	
	Mask.close();	

}

/**
 * Method to fire on the click event of the save button
 * and check validations
 **/
public function checkValidations():void
{
	var i:int=Validator.validateAll([evaluationIdComboValidator,idTypeComboValidator,displayTypeComboValidator]).length;
	if(i==0)
	{
		
		Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onValidationSuccess,questionIcon);		     
   }
    else
   {
 	  Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),
 	  commonFunction.getMessages('error'),0,null,null,errorIcon);
   }
}

public function onValidationSuccess(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		
		infoObject["evaluationComponentId"] = evaluationIdCombo.selectedItem.id;
		infoObject["typeId"] = idTypeCombo.selectedItem.id;
		infoObject["displayId"] = displayTypeCombo.selectedItem.id;
		infoObject["activity"] = "insert";
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		setRecords.send(infoObject);
		
	}	 
	
}
       	
/**
 * Method to fire on the click event of the delete button
* and ask for conformation
 * */
public function deleteOrNot():void
{   		
 Alert.show(commonFunction.getMessages('conformForContinue'),
 commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteRecords,questionIcon);
 
}

/**
 * Method to confirm deletion of record
 **/
public function deleteRecords(event:CloseEvent):void
{
	if(event.detail==1)
	{

		var recordTypeList:ArrayCollection =new ArrayCollection();	
		
		for each (var obj:Object in evaluationComponentGrid.dataProvider){
			if(obj.select==true){
				recordTypeList.addItem(obj.evaluationId);		
			}
		}		
		
		infoObject["evaluationRecords"] = recordTypeList;
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		deleteEvaluationRecords.send(infoObject);        	     
               
 }
 
 if(event.detail==2)
	{
		var gridData:ArrayCollection=evaluationComponentGrid.dataProvider as ArrayCollection;
            	
            	for(var e:int=0;e<gridData.length;e++)
            	{
            	  var gridItem:Object=gridData.getItemAt(e);
            	if(gridItem.select==true)
            	   {
					gridItem.select=false;
					gridData.setItemAt(gridItem,e);
					evaluationComponentGrid.dataProvider=gridData;
            	   }
            	}
	 editbutton.enabled=false,deletebutton.enabled=false;
	 }
 }      


/**
 * Method to fire the click event of the edit button
 * opens edit window
 **/
private function openEditWindow():void
{
        var gridData:ArrayCollection=commonFunction.getSelectedRowData(evaluationComponentGrid);      
     	
           var editWindow:editPopupWindow =editPopupWindow(PopUpManager.createPopUp(this,editPopupWindow,true))
           
		   editWindow.evalId = gridData.getItemAt(0).evaluationId; 
    	   editWindow.programLabel.text=gridData.getItemAt(0).evaluationId;
    	   editWindow.idType=gridData.getItemAt(0).idCode;
		   editWindow.idDescription = gridData.getItemAt(0).idDescription;
		   editWindow.displayType=gridData.getItemAt(0).displayCode;
		   editWindow.displayDescription = gridData.getItemAt(0).displayDescription;
		   editWindow.buttonFunction = onLoad;
		   editWindow.editButton = editbutton;
		   editWindow.deleteButton = deletebutton;
		   
		   
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
	
	if(successDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	
	Mask.close();
	
	if(successDetails.Details.list_values=="success"){
		
		Alert.show(commonFunction.getMessages('savedSuccessfully'),
		commonFunction.getMessages('success'),0,null,null,successIcon);
		
		onLoad();
		
		
	}else{
		Alert.show(commonFunction.getMessages('requestFailed'),
		commonFunction.getMessages('failure'),0,null,null,errorIcon);
		
		Mask.close();	
	}	
	
			
}		

/**
 * method executed on processing of the request
 * */
[Bindable]
public var successDetails1: XML;
public function onSetSuccess1(event:ResultEvent):void{
	
	successDetails1=event.result as XML;
	
	if(successDetails1.sessionConfirm == true){
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          	this.parentDocument.vStack.selectedIndex=0;
          	this.parentDocument.loaderCanvas.removeAllChildren();
            }
	
				Mask.close();
	
				if(successDetails1.Details.list_values=="success"){
		
				Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),
				commonFunction.getMessages('success'),0,null,null,successIcon);
		
				onLoad();	
		
	}else{
		Alert.show(commonFunction.getMessages('requestFailed'),
		commonFunction.getMessages('failure'),0,null,null,errorIcon);
		
		Mask.close();	
	}	
	
			
}	

/**
 * Method to fire the click event of the cancel button,remove UI from main window
 **/		
public function cancelFunction():void{
this.parentDocument.loaderCanvas.removeAllChildren();
	}						
								