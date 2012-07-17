
import common.Mask;
import common.commonFunction;
import common.validations.CommonValidations;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.events.CloseEvent;
import mx.events.ListEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

import systemTableTwo.EditWindow;


[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

protected var validate:CommonValidations=new CommonValidations();

public var relatedGroupCode:String=new String();
[Bindable]public var componentDetails:ArrayCollection=new ArrayCollection();
	
public var params:Object={};
public var recordCount:int;
[Bindable]public var urlPrefix:String;

public var componentInfoXml:XML;

/** Pre Initilize Reservation Form (Getting List of Category) **/
public function initForm():void
{
	urlPrefix=commonFunction.getConstants('url')+"/systemTableTwo/";
	
	var groupArray:Array=new Array;
	for each(var obj:Object in groupDB.group)
	{
		groupArray.push(obj.@group_description.toString());
	}
	groupArray.sort();
	groupCB.dataProvider=groupArray;
    groupCB.selectedIndex=-1;
	groupCB.setFocus();
}

/** group change handler **/
protected function groupCBChangeHandler(event:ListEvent):void
{
	var max:int=int(groupDB.group.(@group_description==groupCB.selectedLabel).@max.toString());
	
	componentCodeTxtInput.maxChars=max;
	
	componentCodeValid.source=null;
	componentDescValid.source=null;
	firstFlagValid.source=null;
	secondFlagValid.source=null;
	thirdFlagValid.source=null;
	saveButton.enabled=true;
	relatedGroupCode=groupDB.group.(@group_description==groupCB.selectedLabel).@group_code;
	componentCodeTxtInput.text="";
	componentCodeTxtInput.errorString="";
	componentDescriptionTxtArea.text="";
	componentCodeTxtInput.errorString="";

	gridCanvas.visible=true;
	
	params["groupCode"]=relatedGroupCode;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getComponentDetailsService.send(params);
		
	if(groupDB.group.(@group_code==relatedGroupCode).verification_required.@allow=="yes")
	{
		verification.height = 24;
		verificationColumn.visible=true;
	}
	else
	{
		verification.height=0;
		verificationColumn.visible=false;
	}
		
	firstFlagTxtInput.text="0";
	
	if(groupDB.group.(@group_code==relatedGroupCode).dummy_flag1.@flag1_description=="")
	{
		first.height = 0;
		firstFlagDataGridColumn.headerText="";
		firstFlagLab.text="";
		firstFlagDataGridColumn.visible=false;
	}
	else
	{
		first.height=24;
		firstFlagDataGridColumn.visible=true;
		firstFlagDataGridColumn.headerText=groupDB.group.(@group_code==relatedGroupCode).dummy_flag1.@flag1_description;
		firstFlagLab.text=groupDB.group.(@group_code==relatedGroupCode).dummy_flag1.@flag1_description;

	}
	
	secondFlagTxtInput.text="";
	
	if(groupDB.group.(@group_code==relatedGroupCode).dummy_flag2.@flag2_description=="")
	{
		second.height = 0;
		secondFlagLab.text="";
		secondFlagDataGridColumn.headerText="";
		secondFlagDataGridColumn.visible=false;

	}
	else
	{
		second.height=24;
		secondFlagDataGridColumn.visible=true;
		secondFlagDataGridColumn.headerText=groupDB.group.(@group_code==relatedGroupCode).dummy_flag2.@flag2_description;
		secondFlagLab.text=groupDB.group.(@group_code==relatedGroupCode).dummy_flag2.@flag2_description;
	}
	
	thirdFlagLab.text="";
	
	if(groupDB.group.(@group_code==relatedGroupCode).dummy_flag3.@flag3_description=="")
	{
		third.height=0;
		thirdFlagTxtInput.text="";
		thirdFlagDataGridColumn.headerText="";
		thirdFlagDataGridColumn.visible=false;
	}
	else
	{
		third.height=24;
		thirdFlagDataGridColumn.visible=true;
		thirdFlagDataGridColumn.headerText=groupDB.group.(@group_code==relatedGroupCode).dummy_flag3.@flag3_description;
		thirdFlagLab.text=groupDB.group.(@group_code==relatedGroupCode).dummy_flag3.@flag3_description;
	}
}

/** get component details result handler **/
protected function componentInfoResultHandler(event:ResultEvent):void
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
    componentInfoXml=event.result as XML;
    componentDetails.removeAll();
	for each(var obj:Object in componentInfoXml.groupInfo)
	{
		componentDetails.addItem({select:false,groupCode:relatedGroupCode,componentCode:obj.componentCode, componentDescription:obj.componentDescription, active:obj.active, verificationRequired:obj.verificationRequired, dummyFlag1:obj.dummyFlag1, dummyFlag2:obj.dummyFlag2, dummyFlag3:obj.dummyFlag3});
	}
	sysTab2Grid.dataProvider=componentDetails;
	editButton.enabled=false;
	deleteButton.enabled=false;
}			
			
/** fault handler **/
protected function faultHandler(event:FaultEvent):void
{
	Alert.show(commonFunction.getMessages('requestFailed'));
	Mask.close();
}			
			
/** save button click handler **/
private function saveButtonClickHandler():void
{
	var check:int=0;
				
	if(!validateForm(groupDB,groupCB.selectedLabel))
    {	
    	check++;	
    }
            	
    if(firstFlagDataGridColumn.visible==true)
	{
		if(Validator.validateAll([firstFlagValid]).length>0)
		{
	        check++;
	    }
	    else
	    {
    	     firstFlagTxtInput.errorString="";
    	}
    }
				
	if(secondFlagDataGridColumn.visible==true)
	{
		if(Validator.validateAll([secondFlagValid]).length>0)
		{
	        check++;
	    }
	    else
	    {
	        secondFlagTxtInput.errorString="";
	    }
	}
        		
    if(thirdFlagDataGridColumn.visible==true)
	{
		if(Validator.validateAll([thirdFlagValid]).length>0)
		{
	        check++;
	    }
	    else
	    {
	        thirdFlagTxtInput.errorString="";
	    }
	}			

	if(check==0)
	{
    	params["groupCode"]=relatedGroupCode;
		params["componentCode"]=componentCodeTxtInput.text;
		params['time']=new Date();
		Mask.show(commonFunction.getMessages('pleaseWait'));
		duplicateCheckService.send(params);
    }
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** duplicate component check result handler **/
protected function duplicateCheckResultHandler(event:ResultEvent):void
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
    var c:int=int(event.result.count);
    var bool:Boolean=true;
	if(c==0)
	{
//		for each(var obj:Object in componentDetails)
//		{
//			if(obj.componentDescription.toString().toLowerCase()==componentDescriptionTxtArea.text.toLowerCase())
//			{
//				bool=false;
//				componentDescriptionTxtArea.errorString=commonFunction.getMessages('duplicateEntry');
//			}
//		}	
	}
	else
	{
		bool=false;
		componentCodeTxtInput.errorString=commonFunction.getMessages('duplicateEntry');
	}
	
	if(bool)
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('conform'), 3, this, insertOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}
			
/** onInsert yes no Confirm handler **/
public function insertOrNot(event:CloseEvent):void
{
    if(event.detail == 1)
    {
    	params["componentDescription"]=componentDescriptionTxtArea.text;
		params["active"]=activeRGroup.selectedValue;
		params["dummyFlag1"]=firstFlagTxtInput.text;
		if((firstFlagTxtInput.text=="")||(firstFlagTxtInput.text==null))
		{
			params["dummyFlag1"]='0';
		}
		params["dummyFlag2"]=secondFlagTxtInput.text;
		params["dummyFlag3"]=thirdFlagTxtInput.text;
		
	    if(verificationColumn.visible==true)
		{
			var verificationRequired:int=0;
			if(verificationChBox.selected)
			{
				verificationRequired=1
			}
			params["verificationRequired"]=verificationRequired;
	    }
	    Mask.show(commonFunction.getMessages('pleaseWait'));
		setComponentDetailsService.send(params);
    }
	else
	{
		getDetails();
	}
}			
			
/** insert component result handler **/
protected function setDetailsResultHandler(event:ResultEvent):void
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
    
    Alert.show(commonFunction.getMessages('successOnInsert'),commonFunction.getMessages('success'),0,null,onOk,successIcon);
	componentCodeTxtInput.text="";
	componentDescriptionTxtArea.text="";
	firstFlagTxtInput.text="0";
	secondFlagTxtInput.text="";
	thirdFlagTxtInput.text="";
	componentCodeTxtInput.errorString="";
	componentDescriptionTxtArea.errorString="";
	firstFlagTxtInput.errorString="";
	secondFlagTxtInput.errorString="";
	thirdFlagTxtInput.errorString="";
}
			
/** cancel button click handler **/
private function cancelButtonClickHandler():void
{
	this.removeAllChildren();
	document.visible=false;
}
			
/** edit button click handler **/
protected function editButtonClickHandler():void
{	 
	var selectedRecords:ArrayCollection=commonFunction.getSelectedRowData(sysTab2Grid);
	var verificationRequired:Boolean=false;
		if(selectedRecords[0].verificationRequired==1)
		{
			verificationRequired=true;
		}
		var editWindow:EditWindow =EditWindow(PopUpManager.createPopUp(this, EditWindow, true));

		editWindow.firstFlagLab.text = firstFlagLab.text;
		editWindow.secondFlagLab.text = secondFlagLab.text;
		editWindow.thirdFlagLab.text = thirdFlagLab.text;
		editWindow.verificationChBox.selected=verificationRequired;
		editWindow.verificationEnabled=verificationColumn.visible;
		editWindow.firstFlagTxtInput.text=selectedRecords[0].dummyFlag1;
		editWindow.secondFlagTxtInput.text=selectedRecords[0].dummyFlag2;
		editWindow.thirdFlagTxtInput.text=selectedRecords[0].dummyFlag3;
		editWindow.activeRGroup.selectedValue=selectedRecords[0].active;
		editWindow.componentCodeTxtInput.text=selectedRecords[0].componentCode;
		editWindow.componentDescriptionTxtArea.text=selectedRecords[0].componentDescription;
		editWindow.groupCode = relatedGroupCode;
		editWindow.groupDescription = groupCB.selectedLabel;
		editWindow.parentFunction =getDetails;
		PopUpManager.centerPopUp(editWindow);
}
			
/** delete button click handler **/
protected function deleteButtonClickHandler():void
{
	var selectedRecords:ArrayCollection=commonFunction.getSelectedRowData(sysTab2Grid);
	if(selectedRecords.length>0)
	{
		var selectedGroupCode:String="";
		var selectedComponentCode:String="";
		for each(var obj:Object in selectedRecords)
		{
			selectedComponentCode+=obj.componentCode+"|";
			selectedGroupCode+=obj.groupCode+"|";
		}
		params["selectedComponentCode"]=selectedComponentCode;
		params["selectedGroupCode"]=selectedGroupCode;
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,confirmDelete,questionIcon);
	}			
}

/** onDelete yes no confirm handler **/
public function confirmDelete(event:CloseEvent):void
{
	if(event.detail==1)
	{
		params['time']=new Date();
		Mask.show(commonFunction.getMessages('pleaseWait'));
		deleteComponentService.send(params);
	}
	else
	{
		getDetails();
	}
}

/** delete component result handler **/
protected function deleteComponentResultHandler(event:ResultEvent):void
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
 	if(event.result.count>=0)
 	{
    	Alert.show(event.result.count+" "+commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,onOk,successIcon);
	}
}

/** ok button click handler **/
public function onOk(event:CloseEvent):void
{
	getDetails();
}

/** getting component details **/
protected function getDetails():void
{
	params['time']=new Date();
	params["groupCode"]=relatedGroupCode;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getComponentDetailsService.send(params);
}

public function showStatus(row:Object,col:DataGridColumn):String
{
	if(row.active.toString()=="1")
	{
		return "Active";
	}
	else
	{
		return "Inactive";
	}
}