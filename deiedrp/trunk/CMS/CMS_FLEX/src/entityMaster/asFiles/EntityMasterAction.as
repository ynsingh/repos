/**
 * @(#) EntityMasterAction.as
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
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
public var userId:String;
public var entityType:String;
public var parentEntity:String;
public var entityState:String;
public var location:String;
public var refrenceFunction:Function;
public var resultXml:XML;


 
public function validateEntityMasterScreen():Boolean
{
	var bool:Boolean=true;
	
	addressValidator.source=address.addressLine1;
	cityValidator.source=address.cityField;
	stateValidator.source=address.stateField;
	entityTypeValidator.source=entityTypeCombo;
	parentEntityValidator.source=parentEntityCombo;
	entityLocationValidator.source=locationCombo;
	
	var i:int=Validator.validateAll([entityTypeValidator,parentEntityValidator,entityLocationValidator,
	              entityCodeValidator,entityNameValidator,addressValidator,stateValidator,cityValidator,
	                  address.pinValidator,phoneValidator,faxValidator]).length;
	
	if(i>0)
	{
		bool=false;
	}
		
	return bool;
}

public function submitForm():void
{
	
	if(validateEntityMasterScreen())
	{
		Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);
	}
	else
	{
		Alert.show(resourceManager.getString('Messages','pleasecheckEntriesinRed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	}
}

public function onOK(event:CloseEvent):void{
			
			if(event.detail==Alert.YES){
				var inputvalues:Object=new Object();				
				inputvalues["parentId"]=parentXml.parent.(entity_name==parentEntityCombo.selectedLabel).entity_id;
				inputvalues["time"]=new Date().getTime();
				httpChildList.send(inputvalues);			
			}
}
/* adding new entity */
 private function httpAddNewEntity(params:Object):void{
	params["time"]=new Date().getTime();
	httpAddEntity.send(params);
}

private function faultHandler_AddNewEntity(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
    
    
private function resultHandler_AddNewEntity(event:ResultEvent):void{
	resultXml=event.result as XML;
	if(resultXml.sessionConfirm == true)
    {
          Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
    }
            
	
			else if(String(resultXml.exception.exceptionstring).search("ErrSysVal")>-1){
			  	callAlert("ErrSysVal");
                   }
            else if(String(resultXml.exception.exceptionstring).search("duplicateentitycode")>-1){
             	callAlert("duplicateentitycode");
			}
			else if(String(resultXml.exception.exceptionstring).search("error")>-1){
             	callAlert("errorInsert");
			}
			else{
            	   
			Alert.show(resourceManager.getString('Messages','successOnInsert'),resourceManager.getString('Messages','success'),0,null,null,successIcon);
			
			resetForm();
            }
            
   }
   
  // function added by ashish mohan to call  custom alert 
   private function callAlert(msg:String):void
 {

 mx.controls.Alert.yesLabel = "Details"
  mx.controls.Alert.buttonWidth=80;
 mx.controls.Alert.show(resourceManager.getString('Messages',msg),resourceManager.getString('Messages','error'),(Alert.YES|Alert.CANCEL), this,showDetailError, errorIcon);
 }
 
 // function added by ashish mohan to show details of error
   public function showDetailError(event:CloseEvent):void{
  	if(event.detail==Alert.YES){
  	Alert.show(String(resultXml.exception.exceptionstring),commonFunction.getMessages('errorDetail'),0,null,null,infoIcon);}
   	resetForm();
   }
   
  
            



/* updating existing entity*/
private function httpUpdateExistingEntity(params:Object):void{
			params["time"]=new Date().getTime();
	httpUpdateEntity.send(params);
}
private function faultHandler_UpdateEntity(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_UpdateEntity(event:ResultEvent):void{
	resultXml=event.result as XML;
	
	 if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	if(String(resultXml.exception.exceptionstring).search("Error")>-1){
   		   			callAlert("cannotUpdate");

   	}else if(String(resultXml.exception.exceptionstring).search("duplicateentitycode")>-1){
           callAlert("duplicateentitycode");

   	}else if(String(resultXml.exception.exceptionstring).search("0")>-1){
   	Alert.show(resourceManager.getString('Messages','recordNotUpdate'),resourceManager.getString('Messages','error'),4,this,closePopUp,errorIcon);
   	}
   	else{
Alert.show(resourceManager.getString('Messages','recordUpdatedSuccessfully'),resourceManager.getString('Messages','success'),4,this,closePopUp,successIcon);
				
   	}
   }




/* for manage screen*/
public function updateClick():void
{
	if(validateEntityMasterScreen())
	{
		var inputvalues:Object=new Object();
		inputvalues["entityId"]=entityId.text;
		inputvalues["entityType"]=entityTypeXml.entityTypeList.(entity_name==entityTypeCombo.selectedLabel).entity_id;
		inputvalues["city"]=address.cityField.selectedLabel ;
		inputvalues["state"]=address.stateField.selectedLabel;
		inputvalues["phone"]=phoneNoText.text;
		inputvalues["fax"]=faxText.text;
		inputvalues["knownby"]=knowByText.text;
		inputvalues["parentId"]=parentXml.parent.(entity_name==parentEntityCombo.selectedLabel).entity_id;
		inputvalues["level"]=levelText.text;
		inputvalues["entityCode"]=entityCodeText.text;
		inputvalues["pincode"]=address.pinField.text;
		inputvalues["address"]=address.addressLine1.text;
		inputvalues["location"]=locationXml.parent.(entity_name==locationCombo.selectedLabel).entity_id;
		inputvalues["name"]=entityNameText.text;
		inputvalues["userId"]=userId;
		httpUpdateExistingEntity(inputvalues);
			}
	else
	{
		Alert.show(resourceManager.getString('Messages','pleasecheckEntriesinRed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	}
}

/* for manage screen*/
public function deleteClick():void
{
	var inputvalues:Object=new Object();
		inputvalues["entityId"]=entityId.text;
		httpDeleteExistingEntity(inputvalues);
}




/* delete existing entity*/
private function httpDeleteExistingEntity(params:Object):void{
			params["time"]=new Date().getTime();
	httpDeleteEntity.send(params);
}
private function faultHandler_DeleteEntity(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
  
private function resultHandler_DeleteEntity(event:ResultEvent):void{
	resultXml=event.result as XML;
	
	 if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	if(String(resultXml.exception.exceptionstring).search("ForiegnKeyConstraint")>-1){
   		   			callAlert("dependDeleteError");
   	}else if(String(resultXml.exception.exceptionstring).search("ErrorInDelete")>-1){
   		   			callAlert("cannotDelete");
   		}
   		else if(String(resultXml.exception.exceptionstring).search("ErrorInChildQuery")>-1){
   		   			callAlert("noChild");}
   		   			else if(String(resultXml.exception.exceptionstring).search("EntityHasChild")>-1){
   		   			Alert.show(resourceManager.getString('Messages','hasChild',[String(resultXml.exception.exceptionstring).substring(14,15)]),resourceManager.getString('Messages','error'),4,this,closePopUp,errorIcon);}
				
   		   			else if(String(resultXml.exception.exceptionstring).search("0")>-1){
   		   			Alert.show(resourceManager.getString('Messages','recordNotDelete'),resourceManager.getString('Messages','error'),4,this,closePopUp,errorIcon);}
				
				
				else{
	
Alert.show(resourceManager.getString('Messages','recordsDeletedSuccessfully'),resourceManager.getString('Messages','success'),4,this,closePopUp,successIcon);
				}

   }

public function resetForm():void
{
	addressValidator.source=null;
	stateValidator.source=null;
	cityValidator.source=null;
	entityTypeValidator.source=null;
	parentEntityValidator.source=null;
	entityLocationValidator.source=null;
	
	entityTypeCombo.selectedIndex=-1;
	entityCodeText.text="";
	entityNameText.text="";
	address.addressLine1.text="";
	address.cityField.selectedIndex=-1;
	address.stateField.selectedIndex=-1;
	address.pinField.text="";
	faxText.text="";
	knowByText.text="";
	levelText.text="";
	locationCombo.selectedIndex=-1;
	parentEntityCombo.selectedIndex=-1;
	phoneNoText.text="";
	
	entityTypeCombo.errorString="";
	entityCodeText.errorString="";
	entityNameText.errorString="";
	address.addressLine1.errorString="";
	address.cityField.errorString="";
	address.stateField.errorString="";
	address.pinField.errorString="";
	faxText.errorString="";
	knowByText.errorString="";
	locationCombo.errorString="";
	parentEntityCombo.errorString="";
	phoneNoText.errorString="";
	
//    Alert.show(resourceManager.getString('Messages','allFieldsCleared'),resourceManager.getString('Messages','reset'),0,null,null,resetIcon);
}

[Bindable]
var entityTypeXml:XML;
[Bindable]
var parentXml:XML;
[Bindable]
var locationXml:XML;
[Bindable]
var childXml:XML;

/**
function for getting entity type list
*/
public function getEntityTypeList():void{
	
	address.stateField.selectedItem=entityState;
	
	var params:Object=new Object();
	params["userId"]=userId;
		params["time"]=new Date().getTime();
	httpEntityType.send(params);
	
}

private function faultHandler_EntityType(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_EntityType(event:ResultEvent):void{
	  
   entityTypeXml=event.result as XML;
  if(entityTypeXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
   
   httpLocation();
   
   var entityTypeAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in entityTypeXml.entityTypeList){
   		entityTypeAC.addItem({entity_id:object.entity_id,entity_name:object.entity_name,level:object.level});
   	}
  
  entityTypeCombo.dataProvider=entityTypeXml.entityTypeList.entity_name;
 entityTypeCombo.selectedItem=entityType;
 if(entityType!=null)
 {
 	entityTypeCombo.enabled=false; 	
     httpParent();
 }
 }

/**
 * function for getting list of possible parents for selected entity
 */
private function httpParent():void{
	levelText.text=entityTypeXml.entityTypeList.(entity_name==entityTypeCombo.selectedLabel).level;
	var params:Object=new Object();
	params["userId"]=userId;
	params["level"]=levelText.text;
		params["time"]=new Date().getTime();
	httpParentList.send(params);
}
private function faultHandler_ParentList(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_ParentList(event:ResultEvent):void{
	  
   parentXml=event.result as XML;
    if(parentXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
  var parentAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in parentXml.parent){
   		parentAC.addItem({entity_id:object.entity_id,entity_name:object.entity_name});
   	}
  
  parentEntityCombo.dataProvider=parentXml.parent.entity_name;
parentEntityCombo.selectedItem=parentEntity;
 
   }

/**
 * Function for getting location list
 */
 private function httpLocation():void{
	var params:Object=new Object();
	params["userId"]=userId;
		params["time"]=new Date().getTime();
	httpLocationList.send(params);
}
private function faultHandler_LocationList(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_LocationList(event:ResultEvent):void{
	  
   locationXml=event.result as XML;
   if(locationXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
  var locationAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in locationXml.parent){
   		locationAC.addItem({entity_id:object.entity_id,entity_name:object.entity_name});
   	}
  
  locationCombo.dataProvider=locationXml.parent.entity_name;
// locationCombo.labelField="entity_name";
locationCombo.selectedItem=location;
   }
   
   
  public function closePopUp(event:CloseEvent):void
		{
			PopUpManager.removePopUp(this);	
			if(event.detail==Alert.OK){
				refrenceFunction.call();	
			}		
			  	
		}
		
private function resultHandler_ChildList(event:ResultEvent):void{
	 
   childXml=event.result as XML;
    if(childXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
  var childAC:Array=new Array();
	var flag:Boolean=true;
   	for each (var object:Object in childXml.parent){
   		childAC.push(object.entity_name.toString().toLowerCase());
   	}
   	
   	
   	if(childAC.indexOf(entityNameText.text.toLowerCase())<0){
   		var inputvalues:Object=new Object();
   		inputvalues["entityType"]=entityTypeXml.entityTypeList.(entity_name==entityTypeCombo.selectedLabel).entity_id
		inputvalues["city"]=address.cityField.selectedLabel
		inputvalues["state"]=address.stateField.selectedLabel
		inputvalues["phone"]=phoneNoText.text
		inputvalues["fax"]=faxText.text
		inputvalues["knownby"]=knowByText.text
		inputvalues["parentId"]=parentXml.parent.(entity_name==parentEntityCombo.selectedLabel).entity_id
		inputvalues["level"]=levelText.text
		inputvalues["entityCode"]=entityCodeText.text
		inputvalues["pincode"]=address.pinField.text
		inputvalues["address"]=address.addressLine1.text
		inputvalues["location"]=locationXml.parent.(entity_name==locationCombo.selectedLabel).entity_id
		inputvalues["name"]=entityNameText.text
		inputvalues["userId"]=userId;
		
		httpAddNewEntity(inputvalues);
   	}else{
   		   mx.controls.Alert.show(resourceManager.getString('Messages','duplicateEntry'),resourceManager.getString('Messages','error'));
   	}
   	
  

}
   
public function onKeyDown(event:KeyboardEvent):void
{
	if(updateButton.visible||deleteButton.visible)
   	{
   	   if(event.keyCode==Keyboard.ESCAPE)
   	   {
   	   	   PopUpManager.removePopUp(this);
   	   }
   	}
}
   
public function onCancelClick():void
{
   	if(updateButton.visible||deleteButton.visible)
   	{
   	   PopUpManager.removePopUp(this);
   	}
   	else
   	{
   		this.parentDocument.loaderCanvas.removeAllChildren();
   	}
}
