/**
 * @(#) nameCorrectionAction.as
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
import mx.events.*;
import mx.managers.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

import nameCorrection.CorrectionWindow;

[Bindable]public var details: XML;
[Bindable]public var branchDetails: XML;
[Bindable]public var specialiationDetails: XML;
[Bindable]public var semesterDetails: XML;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question1.png")]private var questionIcon:Class;

[Bindable] public var urlPrefix1:String = "";
[Bindable] public var urlPrefix2:String = "";
public var params:Object={};
   
/**
 *Method fires on load of screen
 * it set url to variables and send request for fetching programs
 **/    	
public function setDateRangeAndFocus():void{
		
		
		urlPrefix1=commonFunction.getConstants('url')+"/prestagingData/";
		urlPrefix2=commonFunction.getConstants('url')+"/namecorrection/";
		Mask.show(commonFunction.getMessages('pleaseWait'));
		getProgramList.send(new Date);
		programCombo.setFocus();
}

/**This function Validate that all mandatory fields required has filled or not
 * and on validation pass calls a function showProcessGrid*/          
public function validationforOk():void
{
	programCombovalidator.source=programCombo;
	
	if(Validator.validateAll([programCombovalidator]).length!=0)
		{
		Alert.show(commonFunction.getMessages('firstSelectallMandatoryFields'),commonFunction.getMessages('error'),0,null,null,errorIcon);		
	    }
	
	else{
		showProcessGrid();		
	    }
}

/**
 * This function send request to fetch grid records 
 * and make grid canvas visible true
 * */
public function showProcessGrid():void 
{
   	var show:String=" ";
	 var param:Object=new Object();
	param["programId"]=programCombo.selectedItem.id;
	if(branchCombo.selectedIndex==-1){
			
			param["branchId"]= "b";						
		}else{	
			
			var s:String=branchCombo.selectedItem.id;
			param["branchId"]= s+"%";			
		}
	
	if(specializationCombo.selectedIndex==-1){
			
			param["specializationId"] = "c";						
		}else{	
			
			var s:String= specializationCombo.selectedItem.id;
			param["specializationId"] = s+"%";			
		}
	processGridCanvas.visible=true;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getErrorRecords.send(param);
}

/**
 *Method fires on success of fetching grid records
 * it set data through an array collection to grid
 * set appropriate labels
 **/
[Bindable] public var gridxml:XML;
public var gridData:ArrayCollection;
public function getErrorRecordsSuccess(event:ResultEvent):void{
	
	gridxml=event.result as XML;
	gridData =new ArrayCollection;
	for each(var o:Object in gridxml.Details){
		
		var sName:String=o.studentfname+" "+o.studentmname+" "+o.studentlname;
		var sts:String="";
		if(o.verified=="0"){
			sts="Not verified";
		}else{
			sts="verified";
		}
		gridData.addItem({select:false,studentId:o.studentId,verified:o.verified,registrationNo:o.registrationNo,studentfname:o.studentfname,
		studentmname:o.studentmname,studentlname:o.studentlname,fatherfname:o.fatherfname,fathermname:o.fathermname,
        fatherlname:o.fatherlname,motherfname:o.motherfname,mothermname:o.mothermname,motherlname:o.motherlname,studentName:sName,status:sts});
	}
	
	var detail:String="Program: ";
	detail=detail+programCombo.selectedLabel;
	if(branchCombo.selectedIndex!=-1){
		detail=detail+"\tBranch: "+branchCombo.selectedLabel;
	}
	if(specializationCombo.selectedIndex!=-1){
		detail=detail+"\tSpecialization: "+specializationCombo.selectedLabel;
	}
	gridLabel.text=detail;
	errorRecordGrid.dataProvider=gridData;
	Mask.close();
	}

/**
 *Method firs on click of edit button
 * it opens correction window  
 **/
public function openCorrectionWindow():void{
	
	var selectedRecord:ArrayCollection=commonFunction.getSelectedRowData(errorRecordGrid);
    
	if(selectedRecord.length==1)
    {
           var editWindow:CorrectionWindow =CorrectionWindow(PopUpManager.createPopUp(this,CorrectionWindow,true))
		   
		   editWindow.programLabel.text=programCombo.selectedLabel;
		   editWindow.branchLabel.text=branchCombo.selectedLabel;
		   editWindow.specializationLabel.text=specializationCombo.selectedLabel;
		   editWindow.registrationLabel.text=selectedRecord.getItemAt(0).registrationNo;
		   editWindow.stuId=selectedRecord.getItemAt(0).studentId;
		   editWindow.studentFirstName.text=selectedRecord.getItemAt(0).studentfname;
		   editWindow.studentMiddleName.text=selectedRecord.getItemAt(0).studentmname;
		   editWindow.studentLastName.text=selectedRecord.getItemAt(0).studentlname;
   		   editWindow.fatherFirstName.text=selectedRecord.getItemAt(0).fatherfname;
		   editWindow.fatherMiddleName.text=selectedRecord.getItemAt(0).fathermname;
		   editWindow.fatherLastName.text=selectedRecord.getItemAt(0).fatherlname;
		   editWindow.motherFirstName.text=selectedRecord.getItemAt(0).motherfname;
		   editWindow.motherMiddleName.text=selectedRecord.getItemAt(0).mothermname;
		   editWindow.motherLastName.text=selectedRecord.getItemAt(0).motherlname;
		   editWindow.buttonFunction=showProcessGrid;
		   editWindow.ed=getButton;
	   	   PopUpManager.centerPopUp(editWindow);	  
    }
}



/**
 * get Program List Success Handler
 **/
public function getProgramSuccess(event:ResultEvent):void{
	
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    details=event.result as XML;
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in details.Details){	
		detailslist.addItem({id:o.id,name:o.name});	
	}
	programCombo.dataProvider = detailslist;
	programCombo.labelField="name";	
    programCombo.setFocus();
    Mask.close();
}




/**
 *  Program change Handler
 **/
public function programChange(event:ListEvent):void
{
	 branchCombo.enabled=true;
	 errorRecordGrid.dataProvider=null;
	 gridLabel.text="";
	 specializationCombo.selectedIndex=-1;
     params["programId"] = programCombo.selectedItem.id;
     Mask.show(commonFunction.getMessages('pleaseWait'));
	 getBranchList.send(params);
}

/**
 *  get Branch List Success Handler
 **/
public function getBranchSuccess(event:ResultEvent):void{
	

	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    branchDetails=event.result as XML;
	
    var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in branchDetails.Details){
		
		detailslist.addItem({id:o.id,name:o.name});
		
	}
	branchCombo.dataProvider = detailslist;
	branchCombo.labelField="name";
	branchCombo.selectedIndex=-1;
	branchCombo.enabled=true;
	Mask.close();
}

/**
 * Branch change Handler 
 **/
protected function branchChange(event:ListEvent):void
{
	errorRecordGrid.dataProvider=null;
	gridLabel.text="";
	var infoObject:Object = {};
	
	infoObject["programId"] = programCombo.selectedItem.id;
	infoObject["branchId"] = branchCombo.selectedItem.id;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getspecializationList.send(infoObject);
}

/**
 *  get Specialization List Success Handler
 **/
public function getSpecializationSuccess(event:ResultEvent):void
{
	
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    specialiationDetails=event.result as XML;
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in specialiationDetails.Details){
		
		detailslist.addItem({id:o.id,name:o.name});
		
	}
	specializationCombo.dataProvider = detailslist;
	specializationCombo.labelField="name";
	specializationCombo.selectedIndex=-1;
	specializationCombo.enabled=true;  
	Mask.close();  
}

public function specizlizationChange(event:ListEvent):void{
	errorRecordGrid.dataProvider=null;
	gridLabel.text="";
}
			
/**
* method executed on request failure 
* */
public function onFailure(event:FaultEvent):void{
	Mask.close();			
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	}
			

/**
 *This Funtion firs on click on cancel button 
 * it removes this interface from main page 
 **/
public function cancelFunction():void
{
   this.parentDocument.loaderCanvas.removeAllChildren();
}
