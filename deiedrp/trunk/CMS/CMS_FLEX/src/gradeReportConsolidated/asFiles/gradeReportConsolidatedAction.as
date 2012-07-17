/**
 * @(#) gradeReportConsolidatedAction.as
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
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]public var urlPrefix1:String;
public var yearDataProvider:Array=new Array;
public var infoObject:Object = {};
[Bindable] public var entityDetails: XML;
[Bindable] public var programDetails: XML;
[Bindable] public var branchDetails: XML;
[Bindable] public var specializationDetails: XML;
/**This function Set Focus on 1st Field Entity combobox
 * and send request on load for input details
 * */
public function setFirstFocous(object:UIComponent):void {
	
	object.setFocus();
    object.drawFocus(true);
	var endMonth:Date=new Date();
	var currentYear:int=int(endMonth.getFullYear());
	for(var year:int=currentYear; currentYear>=2000; currentYear--){
	yearDataProvider.push(year);
	year--;
	}
	sessionStartDateCB.dataProvider=yearDataProvider;
	urlPrefix1=resourceManager.getString('Constants','url')+"/consolidatedMarksheet/";
	Mask.show(commonFunction.getMessages('pleaseWait'));
    getEntityList.send(new Date);
   }
 
/** This Method Retrieves entity values and set as input in combobox
 * */
public function getEntitySuccess(event:ResultEvent):void{

	entityDetails=event.result as XML;
	
	if(entityDetails.sessionConfirm == true){
		
       Alert.show(resourceManager.getString('Messages','sessionInactive'));
       this.parentDocument.vStack.selectedIndex=0;
       this.parentDocument.loaderCanvas.removeAllChildren();
     }
     
	 var detailslist:ArrayCollection =new ArrayCollection();
	
	 for each (var o:Object in entityDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
	}
	
	if(detailslist.length==0){
		Alert.show(commonFunction.getMessages('noRecordFound'),
			commonFunction.getMessages('alert'));
	}else{
		entityCombo.dataProvider = entityDetails.role.description;
	}
	Mask.close();	
}

/**
 * method executed on entity change
 * and send request for fetching program input values
 * and enable branch combo box
 * */
protected function entityChange():void{
	
	infoObject["entityId"] = entityDetails.role.(description==entityCombo.selectedLabel).id;
	programCombo.enabled=true;
	specilizationCombo.selectedIndex=-1;	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getProgramList.send(infoObject);
				
}	

/** This Method Retrieves program values and set as input in combo box
 * */
public function getProgramSuccess(event:ResultEvent):void{
	
	programDetails=event.result as XML;
	
	if(programDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in programDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
	}
	
	if(detailslist.length==0){	
		Alert.show(commonFunction.getMessages('noRecordFound'),
			commonFunction.getMessages('alert'));
	}else{
		programCombo.dataProvider = programDetails.role.description;
	}	
	Mask.close();
}

/**
 * method executed on program change
 * and enable branch combo field and send request for sending branch input values
 * */
 public function programChange(event:ListEvent):void{
			 
	branchCombo.enabled=true;
	specilizationCombo.selectedIndex=-1;		 
	infoObject["entityId"] = entityDetails.role.(description==entityCombo.selectedLabel).id;
	infoObject["programId"] = programDetails.role.(description==programCombo.selectedLabel).id;
	branchCombo.selectedIndex=-1;
			 
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getBranchList.send(infoObject); 
			
	}


/** This Method Retrieves branch values and set as input in combo box
 * */			
public function getBranchSuccess(event:ResultEvent):void{
	
	branchDetails=event.result as XML;
	
	if(branchDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }	
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in branchDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}
	
	if(detailslist.length==0){
		
		Alert.show(commonFunction.getMessages('noRecordFound'),
			commonFunction.getMessages('alert'));
	}else{
		
		branchCombo.dataProvider = branchDetails.role.description;
	}
	
	Mask.close();	
}


/**
 * method executed on branch change
 * and enable specialization combo field and send request for fetching specialization input values
 * */
protected function branchChange(event:ListEvent):void{
	
	specilizationCombo.enabled=true;
	
	infoObject["entityId"] = entityDetails.role.(description==entityCombo.selectedLabel).id;
	infoObject["programId"] = programDetails.role.(description==programCombo.selectedLabel).id;
	infoObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
	specilizationCombo.selectedIndex=-1;

	Mask.show(commonFunction.getMessages('pleaseWait'));
	
	getSpecializationList.send(infoObject);
	
	}

/** This Method Retrieves specialization values and set as input in combo box
 * */	
public function getSpecializationSuccess(event:ResultEvent):void{
	
	specializationDetails=event.result as XML;
	
	if(specializationDetails.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in specializationDetails.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}
	
	if(detailslist.length==0){
		
		Alert.show(commonFunction.getMessages('noRecordFound'),
			commonFunction.getMessages('alert'));
	}else{
		
		specilizationCombo.dataProvider = specializationDetails.role.description;
	}	
	
	Mask.close();
}

/**
 * method executed on specialziation change,it enables session combo
 * */
protected function specializationChange():void{
				
	sessionStartDateCB.enabled=true;			
	}

/**
 * method executed on sesion start date change
 * it sets corresponding end date in label
 * */
protected function sessionStartCBChangeHandler():void{
				
	var sessionEnd:Number = new Number(sessionStartDateCB.selectedLabel)+1;
	SessionEndLab.text=sessionEnd.toString();				
	}
 
/**
 * method executed on click of generate report 
 * and send request with details for generating report
 * */
public function getReoprt():void
{
		entityValid.source=entityCombo;
		programValid.source=programCombo;
		branchValid.source=branchCombo;
		specializationValid.source=specilizationCombo;
		sessionValid.source=sessionStartDateCB;
	  	if(Validator.validateAll([entityValid,programValid,branchValid,specializationValid,sessionValid]).length==0)
       	 {
			
			var infoObject:Object = {};
			
			infoObject["entityId"] = entityDetails.role.(description==entityCombo.selectedLabel).id;
			infoObject["address"] = entityDetails.role.(description==entityCombo.selectedLabel).address;
            infoObject["city"] = entityDetails.role.(description==entityCombo.selectedLabel).city;
            infoObject["state"] = entityDetails.role.(description==entityCombo.selectedLabel).state;
            infoObject["pinCode"] = entityDetails.role.(description==entityCombo.selectedLabel).pinCode;
            infoObject["fax"] = entityDetails.role.(description==entityCombo.selectedLabel).fax;
            infoObject["phoneNumber"] = entityDetails.role.(description==entityCombo.selectedLabel).phoneNumber;
			
			infoObject["programId"] = programDetails.role.(description==programCombo.selectedLabel).id;
			infoObject["branchId"] = branchDetails.role.(description==branchCombo.selectedLabel).id;
			infoObject["specializationId"] = specializationDetails.role.(description==specilizationCombo.selectedLabel).id;
		    
		    infoObject["entityName"] = entityCombo.selectedLabel;
			infoObject["programName"] =programCombo.selectedLabel;
			infoObject["branchName"] = branchCombo.selectedLabel;
			infoObject["specializationName"] =specilizationCombo.selectedLabel;
			infoObject["passFromDate"] = sessionStartDateCB.selectedLabel;
			infoObject["passToDate"] =SessionEndLab.text;

			Mask.show(commonFunction.getMessages('pleaseWait'));
			
			getMarkSheets.send(infoObject);		

       	 }
     else
     {
       Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);     	
     }
}

public function onMarkSheetSuccess(event:ResultEvent):void{
	
	Alert.show(commonFunction.getMessages('markSheetGeneratedSuccessfully'),
	commonFunction.getMessages('success'),0,null,null,successIcon);
	Mask.close();
}

public function resetForm():void{
entityValid.source=null;
programValid.source=null;
branchValid.source=null;
specializationValid.source=null;
sessionValid.source=null;
sessionStartDateCB.errorString="";
specilizationCombo.errorString="";
branchCombo.errorString="";
programCombo.errorString="";
entityCombo.errorString="";	
sessionStartDateCB.selectedIndex=-1;
sessionStartDateCB.enabled=false;
SessionEndLab.text="";
specilizationCombo.selectedIndex=-1;
specilizationCombo.enabled=false;
branchCombo.selectedIndex=-1;
branchCombo.enabled=false;
programCombo.selectedIndex=-1;
programCombo.enabled=false;
entityCombo.selectedIndex=-1;
	
}

/**
 * method executed on generate marksheet request failure 
 * */
public function onMarkSheetFailure(event:FaultEvent):void{
	
	Mask.close();
	var em:String=String(event.message);
	if(em.search("ExceptionConverter: java.io.IOException: The document has no pages")>-1){
		Alert.show("Enrollment Reports Generated Successfully.",commonFunction.getMessages('success'),0,null,null,successIcon);
		resetForm();
	}else
	{
		Alert.show("Error in pdf generation.",commonFunction.getMessages('error'),0,null,null,errorIcon);
	}	
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();	
}

/**
 * This function removes this UI Pannel from main window
 * */		
public function cancelFunction():void{
this.parentDocument.loaderCanvas.removeAllChildren();
}								