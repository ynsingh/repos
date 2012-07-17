/**
 * @(#) UniversityReservationAction.as
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

import universityReservation.EditUniversityReservation;


[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var params:Object={};
public var recordCount:int;
[Bindable]public var urlPrefix:String;
public var total:Number=0;

/** Pre Initilize Reservation Form (Getting List of Category) **/
public function initForm():void
{
	urlPrefix=commonFunction.getConstants('url')+"/universityReservation/";
	getDetails();
	params['time']=new Date();
	getCategoryService.send(params);
}

/** Reset Reservation Form **/
public function resetForm():void
{
	categoryCombo.selectedIndex=-1;
	percentageText.text="";
	categoryCombo.errorString="";
	percentageText.errorString="";
}

/** Validating Reservation Form for user Input **/
public function validateUniversitySeatReservationScreen():Boolean
{
	var i:int=Validator.validateAll([categoryValidator,percentageValidator]).length;
	if(i==0)
	{
	    return true;
	}
	else
	{
		return false;
	}
}

/** Submit Reservation Form **/
public function submitForm():void
{
	if(validateUniversitySeatReservationScreen())
	{
		var gt:Number=total+Number(percentageText.text);
		if(gt<=100)
		{
			params["categoryCode"]=categoryCombo.selectedItem.id;
			params["percentage"]=percentageText.text;
			params['time']=new Date();
			duplicateCheckService.send(params);
			Mask.show(commonFunction.getMessages('pleaseWait'));
		}
		else
		{
			Alert.show('Total sum of percentage of all categories must be less than or equal to 100',commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** Edit Resrvation Details **/
public function editDetails():void
{
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(reservationDetailsGrid);
	
	var editWindow:EditUniversityReservation=EditUniversityReservation(PopUpManager.createPopUp(this,EditUniversityReservation,true));
	editWindow.categoryId=selectedValues.getItemAt(0).categoryCode;
	editWindow.categoryLabel.text=selectedValues.getItemAt(0).categoryName;  
	editWindow.percentageText.text=selectedValues.getItemAt(0).percentage;
	editWindow.percentageText.setFocus();
	editWindow.referenceFunction=getDetails;
	editWindow.urlPrefix=urlPrefix;
	editWindow.total=total-Number(selectedValues.getItemAt(0).percentage);
	PopUpManager.centerPopUp(editWindow);	   
}

/** Delete Reservation Details **/
public function deleteDetails():void
{
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteOrNot,questionIcon);
}

/** Delete confirmation handler function **/
public function deleteOrNot(event:CloseEvent):void
{
	if(event.detail==1)
	{
		var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(reservationDetailsGrid);
		var categoryCode:String="";
		
		for each(var obj:Object in selectedValues)
		{
			categoryCode+=obj.categoryCode+"|";	
		}
		params["categoryCode"]=categoryCode;
		params['time']=new Date();
		
		deleteDetailsService.send(params);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	}
	else
	{
		getDetails();
	}
}

/** getting List of Category Success Result Handler **/
public function getCategoryResultHandler(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    var categoryXML:XML=event.result as XML;
	var categoryList:ArrayCollection=new ArrayCollection();
	for each(var obj:Object in categoryXML.category)
	{
		categoryList.addItem({id:obj.id,name:obj.name});
	}
	categoryCombo.dataProvider=categoryList;
	categoryCombo.labelField="name";
}

/** Fault Handler **/
public function faultHandler(event:FaultEvent):void
{
	Alert.show(commonFunction.getMessages('requestFailed'));
	Mask.close();
}

/** Checking for Duplicate Record Success Result Handler **/
public function duplicateCheckResultHandler(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    var checkXml:XML=event.result as XML;
	if(checkXml.check=="false")
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,saveOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		percentageText.text="";
		categoryCombo.errorString=commonFunction.getMessages('duplicateEntry');
		getDetails();
	}
}

/** Insert confirmation Handler function **/
public function saveOrNot(event:CloseEvent):void
{
	if(event.detail==1)
	{
		setReservationDetailsService.send(params);
	}
}

/** Inserting Resrvation Details Success Result Handler **/
public function setReservationDetailsResultHandler(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    Alert.show(commonFunction.getMessages('successOnInsert'),commonFunction.getMessages('success'),0,null,null,successIcon);
	getDetails();
	resetForm();
}

/** Getting List of Reservation Details **/
public function getDetails():void
{
	editButton.enabled=false;
	deleteButton.enabled=false;
	params['time']=new Date();
	getReservationDetailsService.send(params);
	Mask.show(commonFunction.getMessages('pleaseWait'));
}

/** Getting Reservation Details Success Result Handler **/
public function getReservationDetailsResultHandler(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    var reservatinDetailsXml:XML=event.result as XML;
	var reservatinDetailsList:ArrayCollection=new ArrayCollection();
	total=0;
	for each(var obj:Object in reservatinDetailsXml.reservation)
	{
		total=total+int(obj.percentage.toString());
		reservatinDetailsList.addItem({select:false,categoryCode:obj.categoryCode, categoryName:obj.categoryName,percentage:obj.percentage});
	}
	recordCount=reservatinDetailsList.length;
	reservationDetailsGrid.dataProvider=reservatinDetailsList;
}

/** Deleting Reservation Details Success Result Handler **/ 
public function deleteDetailsResultHandler(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
	getDetails();
}