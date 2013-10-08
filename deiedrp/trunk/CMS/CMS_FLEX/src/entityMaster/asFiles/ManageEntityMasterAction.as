/**
 * @(#) ManageEntityMasterAction.as
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

import entityMaster.EntityMaster;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.DataGrid;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]public var enData:ArrayCollection=new ArrayCollection();
public var entityMasterObj:EntityMaster=new EntityMaster();
public var chosenEntityLevel:String;

public function onChangeEntityType():void
{
	getData();
	entityDetails.visible=true;
}

public function getData():void
{
	enData.removeAll();
	for each(var obj:Object in em.entityMaster)
	{
		if(obj.entity_type==entityTypeComboManage.selectedItem)
		{
			enData.addItem({select:false,entity_type:obj.entity_type,entity_code:obj.entity_code,entity_head:obj.entity_head,entity_name:obj.entity_name,parent_entity:obj.parent_entity,address1:obj.address1,address2:obj.address2,city:obj.city,state:obj.state,pin:obj.pin,phone:obj.phone,fax:obj.fax,location:obj.location,level:obj.level,knownBy:obj.knownBy});
		}
	}
}

public function showDetails(event:Event):void
{
	
	var selectedData:ArrayCollection=getSelectedData(entityDetailsGrid);
	
	var editWindow:EntityMaster=EntityMaster(PopUpManager.createPopUp(this,EntityMaster,true));
	    
	   setFields(selectedData,editWindow);
	    
	    editWindow.showCloseButton=true;
	    editWindow.resetButton.visible=false;
	    editWindow.submitButton.visible=false;
	    
//	    editWindow.entityTypeCombo.enabled=false;
	        
	    if(event.currentTarget.label=="Edit")
	    {
	    	editWindow.updateButton.visible=true;
	        editWindow.deleteButton.visible=false;
	    }
	    else
	    {
	    	editWindow.entityCodeText.editable=false;
	        editWindow.entityNameText.editable=false;
	        editWindow.parentEntityCombo.enabled=false;
	        editWindow.address.addressLine1.editable=false;
	        editWindow.address.stateField.enabled=false;
	        editWindow.address.cityField.enabled=false;
	        editWindow.address.pinField.editable=false;
	        editWindow.phoneNoText.editable=false;
	        editWindow.faxText.editable=false;
	        editWindow.locationCombo.enabled=false;
	        editWindow.knowByText.editable=false;
	        
	    	editWindow.updateButton.visible=false;
	    	editWindow.deleteButton.visible=true;
	    }
	    editWindow.parentEntityCombo.setFocus();
	    
	    PopUpManager.centerPopUp(editWindow);
}

public function getSelectedData(dataGrid:DataGrid):ArrayCollection
{
	var selectedData:ArrayCollection=new ArrayCollection();
	for each(var object:Object in dataGrid.dataProvider as ArrayCollection)
	{		
		if(object.select==true)
		{
			selectedData.addItem({entityId:object.entityId,entityName:object.entityName,
   		entityAddress:object.entityAddress,entityCity:object.entityCity,entityState:object.entityState,
   		entityPhone:object.entityPhone,fax:object.fax,parentEntityId:object.parentEntityId,parentEntity:object.parentEntity,
   		entityCode:object.entityCode,locationId:object.locationId,location:object.location,knownBy:object.knownBy,
   		pinCode:object.pinCode});
        }
	}
	return selectedData;
}

public function setFields(dataArray:ArrayCollection,editWindow:EntityMaster):void
{
	editWindow.entityId.text=dataArray.getItemAt(0).entityId;
	editWindow.entityType=entityTypeComboManage.selectedLabel;
	editWindow.entityCodeText.text=dataArray.getItemAt(0).entityCode;
	editWindow.entityNameText.text=dataArray.getItemAt(0).entityName;
	editWindow.parentEntity=dataArray.getItemAt(0).parentEntity;	
	editWindow.address.addressLine1.text=dataArray.getItemAt(0).entityAddress;
	editWindow.entityState=dataArray.getItemAt(0).entityState.toString();
	editWindow.address.city=dataArray.getItemAt(0).entityCity;
	editWindow.address.pinField.text=dataArray.getItemAt(0).pinCode;
	editWindow.phoneNoText.text=dataArray.getItemAt(0).entityPhone;
	editWindow.faxText.text=dataArray.getItemAt(0).fax;
	editWindow.location=dataArray.getItemAt(0).location;
	editWindow.knowByText.text=dataArray.getItemAt(0).knownBy;
	editWindow.refrenceFunction=httpPopulateEntityGrid;	    
}










[Bindable]
var manageEntityTypeXml:XML;
[Bindable]
var parentXml:XML;
[Bindable]
var locationXml:XML;
[Bindable]
var entityXml:XML;


/**
function for getting entity type list
*/
private function getEntityTypeList():void{
	var params:Object=new Object();
	params["userId"]=entityMasterObj.userId;
		params["time"]=new Date().getTime();
	httpEntityType.send(params);
//	httpLocation();
}

private function faultHandler_EntityType(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_EntityType(event:ResultEvent):void{
	  
   manageEntityTypeXml=event.result as XML;
  if(manageEntityTypeXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
   
   var entityTypeAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in manageEntityTypeXml.entityTypeList){
   		entityTypeAC.addItem({entity_id:object.entity_id,entity_name:object.entity_name,level:object.level});
   	}
  
  entityTypeComboManage.dataProvider=manageEntityTypeXml.entityTypeList;
 entityTypeComboManage.labelField="entity_name";
   }

/*
populating grid according to search condiion
*/

public function httpPopulateEntityGrid():void{
	var params:Object=new Object();
	params["entityType"]=chosenEntityLevel;
	params["userId"]=entityMasterObj.userId;
		params["time"]=new Date().getTime();
	httpPopulateEntity.send(params);
}
private function faultHandler_PopulateEntity(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error')+"  here");
    }
private function resultHandler_PopulateEntity(event:ResultEvent):void{
	  
   entityXml=event.result as XML;
    if(entityXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
    var entityAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in entityXml.entity){
   		entityAC.addItem({select:false,entityId:object.entityId,entityName:object.entityName,entityAddress:object.entityAddress,
   		entityCity:object.entityCity,entityState:object.entityState,entityPhone:object.entityPhone,
   		fax:object.fax,parentEntityId:object.parentEntityId,parentEntity:object.parentEntity,
   		entityCode:object.entityCode,locationId:object.locationId,location:object.location,knownBy:object.knownBy,
   		pinCode:object.pinCode});
   	
   	}   
   entityDetailsGrid.dataProvider=entityAC; 
 
	deleteButton.enabled=false;
	editButton.enabled=false;
   }


private function getLevel():void{
	chosenEntityLevel=entityTypeComboManage.selectedItem.entity_id;
	httpPopulateEntityGrid();
}










