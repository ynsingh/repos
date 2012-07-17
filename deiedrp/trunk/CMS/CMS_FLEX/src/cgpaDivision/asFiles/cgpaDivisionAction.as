/**
 * @(#) cgpaDivisionAction.as
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
import cgpaDivision.editPopupWindow;

import common.Mask;
import common.commonFunction;

import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.managers.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.*;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var infoObject=Object;


/**This function Set Focus on 1st Field Entity combobox
 * and call onload for loading input details
 * */
public function setFirstFocous(object:UIComponent):void
            {
            	object.setFocus();
                object.drawFocus(true);
                onLoad();
            }

/**This function  send request on load for input details
 * */
public function onLoad():void
{
divisionCombo.selectedIndex=-1;
minCgpaField.text="";
maxCgpaField.text="";

infoObject["userId"]=new Date;

getInputDetails.send(infoObject);

Mask.show(commonFunction.getMessages('pleaseWait'));

getSetData.send(infoObject);

	
}

/**
 * The function retrives the list of divisions for 
 * concerned university idSS
 * */
public var divisionXml:XML;
public var divisionList:ArrayCollection;
public function onDivisionSuccess(event:ResultEvent):void
{
	divisionXml=event.result as XML;
	
	if(divisionXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	
	divisionList =new ArrayCollection();
	
	for each (var o:Object in divisionXml.role){
		
		divisionList.addItem({id:o.id,description:o.description});
		
	}
	
	if(divisionList.length==0){
		
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('error'),0,null,null,errorIcon);
			
			divisionCombo.dataProvider=null;
	}else{
		
		divisionCombo.dataProvider = divisionXml.role.description;
		
	}	
	
	Mask.close();
	
}

/**
 * The function retrives the data about divisions added for 
 * concerned university idSS
 * and set this data to grid
 * */
public var detailsXml:XML;
public var detailList:ArrayCollection;

public function onSetGridSuccess(event:ResultEvent):void
{
	detailsXml=event.result as XML;
	
	if(detailsXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	
	detailList=new ArrayCollection();
	
	
	for each(var obj:Object in detailsXml.Details)
	{
	detailList.addItem({select:false,divisionId:obj.divisionId,division:obj.divisionDescription,mincgpa:obj.minCGPA,maxcgpa:obj.maxCGPA});	
	}
	  cgpaDivisionGrid.dataProvider=detailList;

}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();	
	
}

/**This Function Checks All basic validations required of all form fields
 * and send request for saving values
 * */
public function saveDetails():void
{
     	if(Validator.validateAll([divisionComboValidator,minCgpaFieldValidator,maxCgpaFieldValidator]).length==0)
        {
        	var v:Number= Number(maxCgpaField.text);
        	var z:Number=Number(minCgpaField.text);
        	if(v>z)
        	{
        	
        	Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,successValidation,questionIcon);
				
        	}       	
        	else
        	{
        	Alert.show(commonFunction.getMessages('maximumCGPACanNotBeEqualorLessThanMininmumCGPA'),commonFunction.getMessages('error'),0,null,null,errorIcon);	
        	}
	    }
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

public function successValidation(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){	
		
        		infoObject["divisionId"]=divisionXml.role.(description==divisionCombo.selectedLabel).id;
        		infoObject["minCGPA"]=minCgpaField.text;
        		infoObject["maxCGPA"]=maxCgpaField.text;
        		infoObject["activity"]="insert";
        		
        		Mask.show(commonFunction.getMessages('pleaseWait'));
        		
        		saveDetail.send(infoObject);       	
		 }
}	

/**
 * method executed on processing of the request of saving details successfully 
 * */
public function onSetSuccess(event:ResultEvent):void
{
	Alert.show(commonFunction.getMessages('savedSuccessfully'),
		commonFunction.getMessages('success'),0,null,null,successIcon);
		
		Mask.close();
		
		onLoad();
		
		
		
				  
}

/** This function ask for conformation for continue to delete records
 * and calls appropriate function for handling choice
 * */
public function deleteOrNot():void
	{	 
		 Alert.show(commonFunction.getMessages('conformForContinue'),
		 commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteRecords,questionIcon);	
                
	}

/**This function works according conformation results
 * and send request for delete records for yes
 * and back to previous for no
 * */
public function deleteRecords(event:CloseEvent):void
{
	if(event.detail==1)
	{
        var recordTypeList:ArrayCollection =new ArrayCollection();	
		
		for each (var obj:Object in cgpaDivisionGrid.dataProvider){
			if(obj.select==true){
				recordTypeList.addItem(obj.divisionId);		
			}
		}		
			
			infoObject["divisionRecords"]=recordTypeList;
			deleteDivisionRecords.send(infoObject);
		
	}
	if(event.detail==2)
	{
		var gridData:ArrayCollection=cgpaDivisionGrid.dataProvider as ArrayCollection;
            
            	for(var e:int=0;e<gridData.length;e++)
            	{
            	  var gridItem:Object=gridData.getItemAt(e);
            	if(gridItem.select==true)
            	{
            			gridItem.select=false;
            			gridData.setItemAt(gridItem,e);
            	cgpaDivisionGrid.dataProvider=gridData;
            	editbutton.enabled=false;
            	deletebutton.enabled=false;
            	}
            }
	}
	
	Mask.close();
}

/**
 * method executed on successfull deletion
 * */
public function onDeleteSuccess(event:ResultEvent)
{
	Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),
			commonFunction.getMessages('success'),4,this,null,successIcon);
			deletebutton.enabled=false;
			editbutton.enabled=false;
			
			Mask.close();
			
			onLoad();
			
			
		
}

/** This function opens an popup window with details for editing records
 * */
private function openEditWindow():void
{
	var gridData:ArrayCollection=commonFunction.getSelectedRowData(cgpaDivisionGrid);
	
	var gridItem:Object=gridData.getItemAt(0);
	if(gridItem.select==true)
	{
		var editWindow:editPopupWindow =editPopupWindow(PopUpManager.createPopUp(this,editPopupWindow,true))
		PopUpManager.centerPopUp(editWindow);                  	
		editWindow.changedmincgpa.text=gridItem.mincgpa;
		editWindow.changedmaxcgpa.text=gridItem.maxcgpa;
		editWindow.coursetypeLabel.text=gridItem.division;
		editWindow.divisionId=gridItem.divisionId;
		editWindow.buttonFunction=onLoad;
		editWindow.editB=editbutton;
		editWindow.deleteB=deletebutton;
		editWindow.setFocus();
	}
	
}

/**
 * This function removes this UI Pannel from main window
 * */
public function cancel():void
{
this.parentDocument.loaderCanvas.removeAllChildren();
}

