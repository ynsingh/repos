/**
 * @(#) addEvaluationComponentAction.as
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

import flash.events.Event;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable] protected var urlPrefix:String;
[Bindable] protected var urlPrefix2:String;
[Bindable] public var evlistarray:ArrayCollection=new ArrayCollection();
[Bindable] public var idgrouparray:ArrayCollection=new ArrayCollection();
var e:CloseEvent;
public var evaluationComponentList:XML;
public var groupXML:XML;
public var ruleXML:XML;
[Bindable]public var parms :Object={};
public var programid:String;
public var semestercode:String;

public var rule:String;
public var group:String;
public var evaluation:String;

public var referenceFunction:Function;


/** this method close popup screen**/
public function closeScreen(event:CloseEvent):void
{
	PopUpManager.removePopUp(this);
	referenceFunction();
}

/** this method add program switch details **/
public function submitDetails(event:Event):void
{
	if(Validator.validateAll([evaluationIdValidator,ruleValidator,groupValidator,
	                 examDateValidator]).length==0)
	{
		parms["course"] = courseLabel.text ;
		parms["programid"]	= programid ;
		parms["evalid"] = evaluationComponentList.rec.(iddescription==evaluationIdCombo.selectedLabel).evaluationid;
		parms["idgrp"]  = groupXML.Details.(name==groupCombo.selectedLabel).id;
		parms["idrule"] = ruleXML.Details.(name==ruleCombo.selectedLabel).id ;
		parms["dspdateto"] = datetoDispalyMarks.selectedValue ;
		parms["dspdatefrom"]=datefromDispalyMarks.selectedValue;
		parms["ordmks"] = orderinMarkshet.value ;
		parms["mxmmrk"] = maximumMarks.value ;
		parms["exmdat"] = examDateField.selectedValue ;
		
		parms["semester"] = semestercode ;
		
		parms["action"]="I";
		
		getDuplicateService.send(parms);
		Mask.show("Please Wait");
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** this method update program switch details **/
public function updateDetails(event:Event):void
{
	if(Validator.validateAll([evaluationIdValidator,ruleValidator,groupValidator,
	                 examDateValidator]).length==0)
	{
		parms["evalid"] = evaluationComponentList.rec.(iddescription==evaluationIdCombo.selectedLabel).evaluationid;
		parms["idgrp"]  = groupXML.Details.(name==groupCombo.selectedLabel).id;
		parms["idrule"] = ruleXML.Details.(name==ruleCombo.selectedLabel).id ;
		parms["dspdateto"] = datetoDispalyMarks.selectedValue ;
		parms["dspdatefrom"]=datefromDispalyMarks.selectedValue;
		parms["ordmks"] = orderinMarkshet.value ;
		parms["mxmmrk"] = maximumMarks.value ;
		parms["exmdat"] = examDateField.selectedValue ;
		parms["programid"]	= programid ;
		
		parms["semester"] = semestercode ;
		parms["course"] = courseLabel.text ;

		parms["action"]="U";
		
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,doOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** get duplicate success handler **/
public function getDuplicateSuccess(event:ResultEvent):void
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
    if(int(event.result.count.toString())==0)
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,doOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** insert/update confirmation handler **/
public function doOrNot(event:CloseEvent):void
{	if(datetoDispalyMarks.monthsCombo.selectedIndex==-1 && datefromDispalyMarks.monthsCombo.selectedIndex==-1){
		if(event.detail==Alert.YES)
		{
			setCecDetailService.send(parms);
			Mask.show("Please Wait");
		}
	} 
	else if(commonFunction.validateDate(examDateField,datefromDispalyMarks,datetoDispalyMarks)){
		if(event.detail==Alert.YES)
		{
		setCecDetailService.send(parms);
		Mask.show("Please Wait");
		}
	}

}

/** insert/update details success handler **/
public function writececdetailSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'));
			var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    
 	if(event.result.result+""=="true")
	{
		if(parms["action"]=='I')
		{
			//Change by Devendra for Ask confirmation to add more component or not after save one component
			Alert.show(commonFunction.getMessages('addMoreConfirmForCEC'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,addMoreComponentConfirm,questionIcon);
		}
		else
		{
			Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,closeScreen,successIcon);
		}
	}
	else
	{
		Alert.show(commonFunction.getMessages('noRecordChanged'),commonFunction.getMessages('success'),0,null,closeScreen,successIcon);
	}
}

/** get evaluation id lis success handler **/
public function getevalidsSuccess(event:ResultEvent):void
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
    evaluationComponentList=event.result as XML;
	
	evaluationIdCombo.dataProvider=evaluationComponentList.rec.iddescription;
	evaluationIdCombo.selectedItem=evaluation;
	parms['groupCode']='EVLGRP';
	getComponentService.send(parms);
	Mask.show("Please Wait");
}

/** this method reset form **/
public function resetDetails():void
{
	evaluationIdCombo.selectedIndex=-1;
	groupCombo.selectedIndex=-1;
	ruleCombo.selectedIndex=-1;
	datetoDispalyMarks.dateCombo.selectedIndex=-1;
    datetoDispalyMarks.monthsCombo.selectedIndex=-1;
    datefromDispalyMarks.dateCombo.selectedIndex=-1;
    datefromDispalyMarks.monthsCombo.selectedIndex=-1;
    orderinMarkshet.value=0;
    maximumMarks.value=1;
    examDateField.dateCombo.selectedIndex=-1;
    examDateField.monthsCombo.selectedIndex=-1;
}

/** get component details success handler **/
public function getComponentSuccess(event:ResultEvent):void
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
    if(parms['groupCode']=='EVLGRP')
	{
		groupXML=event.result as XML;
		groupCombo.dataProvider=groupXML.Details.name;
		groupCombo.selectedItem=group;
		
		parms['groupCode']='CERULE';
		getComponentService.send(parms);
		
		Mask.show("Please Wait");
	}
	else if(parms['groupCode']=='CERULE')
	{
		ruleXML=event.result as XML;
		ruleCombo.dataProvider=ruleXML.Details.name;
		ruleCombo.selectedItem=rule;
		Mask.close();
	}
}

/** fault handler **/		
public function onFailure(event:FaultEvent):void
{
	Mask.close();
 	Alert.show("failure"+parms['groupCode']);
}

/** on creation complete of page **/
protected function applicationCreationHandler1():void
{
	urlPrefix=commonFunction.getConstants('url')+"/CourseEvaluationComponent/";
	urlPrefix2=commonFunction.getConstants('url')+"/courseMaster/";
	getEvaluationIdsService.send();
	Mask.show("Please Wait");
}

//---------------Add by Devendra--------------------
/** Method to  confirm to add more component or not
 *  after save one component
 **/
protected function addMoreComponentConfirm(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		resetDetails();
	}
	else{
		closeScreen(event);
	}
}
//---------------Devendra Ends----------------------------