/**
 * @(#) externalExaminarCourseAction.as
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

import externalExaminarCourse.editPopupWindow;

import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.formatters.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/warning.png")]private var warningIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;	

public var infoObject:Object={};
public var startDate:Date;
public var endDate:Date;
public var bl:Boolean=true;
public var bool:Boolean=true;
/**
 *This function Set Focus on 1st Field course code combobox
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
	
	infoObject["userId"]=new Date();
	
	Mask.show(commonFunction.getMessages('pleaseWait'));	
	
	getcoursesList.send(infoObject);	
		
	externalExaminarCourseGrid.dataProvider=null;	
	editButton.enabled=false;
	deletebutton.enabled=false;
	firstDate.selectedDate=null;
		secondDate.selectedDate=null;
		thirdDate.selectedDate=null;
		timeInHour.value=0;
		timeInMinute.value=0;
		secondTimeInHour.value=0;
		secondTimeInMinute.value=0;
		thirdTimeInHour.value=0;
		thirdTimeInMinute.value=0;
		secondHourLabel.visible=false;
		secondMinLabel.visible=false;
		secondTimeInHour.visible=false;
		secondTimeInMinute.visible=false;
		thirdHourLabel.visible=false;
		thirdMinLabel.visible=false;
		thirdTimeInHour.visible=false;
		thirdTimeInMinute.visible=false;
		
}


/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	Mask.close();
}

/**
 * The function retrives the list of programs for the 
 * concerned university id
 * */
[Bindable]
public var CoursesDetails: XML;
public var CoursesDetailsList:ArrayCollection;
public function onCoursesSuccess(event:ResultEvent):void{
	
	CoursesDetails=event.result as XML;	
	
	if(CoursesDetails.sessionConfirm == true)
             {
         Alert.show(resourceManager.getString('Messages','sessionInactive'));
			 var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
            }	
	
	CoursesDetailsList =new ArrayCollection();
	
	
	for each (var o:Object in CoursesDetails.Details){
		
		CoursesDetailsList.addItem({id:o.component_id, description:o.component_description,
		sessionStartDate:o.employee_id, sessionEndDate:o.application});
	}

	startDate=DateField.stringToDate(CoursesDetailsList.getItemAt(0).sessionStartDate.toString(),"YYYY-MM-DD");
	endDate=DateField.stringToDate(CoursesDetailsList.getItemAt(0).sessionEndDate.toString(),"YYYY-MM-DD");
	
	
	courseCodeCombo.dataProvider = CoursesDetailsList;
	firstDate.selectableRange={rangeStart :startDate,rangeEnd :endDate};
	secondDate.selectableRange={rangeStart :startDate,rangeEnd :endDate};
	thirdDate.selectableRange={rangeStart :startDate,rangeEnd :endDate};
	Mask.close();	
}



/**
 * The function retrives the list of programs for the 
 * concerned university id
 * */
[Bindable]
public var ExternalExaminerDetails: XML;
public var ExternalExaminerDetailsList:ArrayCollection;
public function onExaminerSuccess(event:ResultEvent):void{
	
	ExternalExaminerDetails=event.result as XML;
	
	if(ExternalExaminerDetails.sessionConfirm == true)
             {
          Alert.show(resourceManager.getString('Messages','sessionInactive'));
			 var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
            }	
	
	ExternalExaminerDetailsList =new ArrayCollection();
	
	for each (var o:Object in ExternalExaminerDetails.role){
		
		ExternalExaminerDetailsList.addItem({id:o.id,description:o.description});
		
	}
	
	externalExaminarCombo.dataProvider = ExternalExaminerDetailsList;
	
	Mask.close();	
}

/**
 * The function fires on mou course code change
 * enabled external examinar combo send requests 
 * */
public function courseCodeChange():void
{
	externalExaminarCombo.enabled=true;
	
	infoObject["courseCode"]=courseCodeCombo.selectedItem.id;
	
	getCourseRecords.send(infoObject);	
	
	getexaminerList.send(infoObject);
	editButton.enabled=false;
	deletebutton.enabled=false;
	
}

/**
 * The function retrives the list of programs for the 
 * concerned university id
 * */
[Bindable]
public var recordDetails: XML;
public var recordDetailsList:ArrayCollection;
public function onRecordsSuccess(event:ResultEvent):void{
	
	recordDetails=event.result as XML;
	
	if(recordDetails.sessionConfirm == true)
             {
         Alert.show(resourceManager.getString('Messages','sessionInactive'));
			 var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
            }	
	
	recordDetailsList =new ArrayCollection();
	
	for each (var o:Object in recordDetails.Details){
		
		recordDetailsList.addItem({select:false,courseCode:o.courseCode,courseName:o.courseName,
		externalExaminerId:o.externalExaminerId,externalExaminerName:o.externalExaminerName,
		firstDate:o.firstDate,secondDate:o.secondDate,thirdDate:o.thirdDate,time:o.time,secondTime:o.secondTime,thirdTime:o.thirdTime});
		}
	
	externalExaminarCourseGrid.dataProvider = recordDetailsList;
	
	Mask.close();	
}

/**
 * The function fires on external examinar change
 * enabled save button
 * */
public function externalExaminarChnage():void{
	savebutton.enabled=true;
	externalExaminarCombo.toolTip=externalExaminarCombo.selectedLabel;
}

/**
 * The function fires on second date change
 * enabled save button
 * */
public function visibleSecondTime():void{
	if(firstDate.selectedDate!=null){
	secondHourLabel.visible=true;
	secondMinLabel.visible=true;
	secondTimeInHour.visible=true;
	secondTimeInMinute.visible=true;}
	else{
		secondDate.text="";
		Alert.show(commonFunction.getMessages('selectFirstDate'),commonFunction.getMessages('info'),4,null,null,infoIcon);
	}
}

/**
 * The function fires on third date change
 * enabled save button
 * */
public function visibleThirdTime():void{
	if(secondDate.selectedDate!=null){
	thirdHourLabel.visible=true;
	thirdMinLabel.visible=true;
	thirdTimeInHour.visible=true;
	thirdTimeInMinute.visible=true;}
	else{
	thirdDate.text="";
	Alert.show(commonFunction.getMessages('selectSecondDate'),commonFunction.getMessages('info'),4,null,null,infoIcon);
	}
}


//to check both second time and date is selected 
public function checkSecondDate():Boolean{
	if(secondDate.selectedDate!=null && (secondTimeInHour.value==0 && secondTimeInMinute.value==0)){
 		Alert.show("Select Second Time",commonFunction.getMessages('info'),4,null,null,infoIcon);
 		bool=false;
 	}
 
 	else if(secondDate.selectedDate!=null && (secondTimeInHour.value>0 || secondTimeInMinute.value>0)){
		bool=true;
 	}
	return bool;
}

//to check both third time and date is selected
public function checkThirdDate():Boolean{
	if(thirdDate.selectedDate!=null && (thirdTimeInHour.value==0 && thirdTimeInMinute.value==0)){
 		Alert.show("Select Third Time",commonFunction.getMessages('info'),4,null,null,infoIcon);
 		bl=false;
 	}
 	else if(thirdDate.selectedDate!=null && (thirdTimeInHour.value>0 || thirdTimeInMinute.value>0)){
 		bl=true;
 	}
	return bl;
}

/**
 * Method to fire on the click event of the save button
 * check validations
 * send requests for fecthing details
 **/
public function saveDetails():void{
	if((courseCodeCombo.selectedIndex!=-1)&&(externalExaminarCombo.selectedIndex!=-1)&&(firstDate.selectedDate!=null)&&(timeInHour.value>0 || timeInMinute.value>0))
    {
    	checkSecondDate();	
        checkThirdDate();
        if(bl==true && bool==true)
        {
        	Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onValidationSuccess,questionIcon);	
		}		  
	}
	else
	{
		Alert.show(commonFunction.getMessages('firstSelectallMandatoryFields'),
		commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}


/**
 * Method to fire on the click event 
 * of the yes button of confirmation of save detail
 * send requests for fecthing details
 **/
public function onValidationSuccess(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		infoObject["examinerCode"]=externalExaminarCombo.selectedItem.id;
		
		infoObject["firstDate"]=dateFormatter.format(firstDate.text);
		infoObject["secondDate"]=dateFormatter.format(secondDate.text);
		infoObject["thirdDate"]=dateFormatter.format(thirdDate.text);
		infoObject["time"]=timeInHour.value+":"+timeInMinute.value;
		infoObject["secondTime"]=secondTimeInHour.value+":"+secondTimeInMinute.value;
		infoObject["thirdTime"]=thirdTimeInHour.value+":"+thirdTimeInMinute.value;	
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		setDetails.send(infoObject); 
		
	}
	
}	

/**
 * Method fire on click of update asks for conformation for continuing with same course
 **/
public function askToAddMore(event:CloseEvent):void
{
 Alert.show(commonFunction.getMessages('doYouWantToContinueWithSameCourse'),
		commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,addMoreExaminar,questionIcon);		  
}

/** method to confirm conituation with same course
 * */
public function addMoreExaminar(event:CloseEvent):void
{
	if(event.detail==1)
	{
		Mask.close();
		externalExaminarCombo.selectedIndex=-1;	
		firstDate.selectedDate=null;
		secondDate.selectedDate=null;
		thirdDate.selectedDate=null;
		timeInHour.value=0;
		timeInMinute.value=0;
		secondTimeInHour.value=0;
		secondTimeInMinute.value=0;
		thirdTimeInHour.value=0;
		thirdTimeInMinute.value=0;
		secondHourLabel.visible=false;
		secondMinLabel.visible=false;
		secondTimeInHour.visible=false;
		secondTimeInMinute.visible=false;
		thirdHourLabel.visible=false;
		thirdMinLabel.visible=false;
		thirdTimeInHour.visible=false;
		thirdTimeInMinute.visible=false;
		

	}else if(event.detail==2){
		
		Mask.close();
		onLoad();
		
	}
}


/**
 * Method to fire the click event of the edit button
 **/
private function openEditWindow():void
{
	var selectedRecord:ArrayCollection=commonFunction.getSelectedRowData(externalExaminarCourseGrid);
    
	if(selectedRecord.length==1)
    {	
           var editWindow:editPopupWindow =editPopupWindow(PopUpManager.createPopUp(this,editPopupWindow,true))
		   editWindow.courseCode.text=selectedRecord.getItemAt(0).courseCode;
		   editWindow.externalExaminer.text=selectedRecord.getItemAt(0).externalExaminerName;
		   editWindow.externalExaminerId.text=selectedRecord.getItemAt(0).externalExaminerId;
		   
		   editWindow.firstDate.selectableRange={rangeStart :startDate,rangeEnd :endDate};
		   editWindow.secondDate.selectableRange={rangeStart :startDate,rangeEnd :endDate};
		   editWindow.thirdDate.selectableRange={rangeStart :startDate,rangeEnd :endDate};
		   editWindow.firstDate.text=selectedRecord.getItemAt(0).firstDate;
		   editWindow.secondDate.text=selectedRecord.getItemAt(0).secondDate;
		   editWindow.thirdDate.text=selectedRecord.getItemAt(0).thirdDate;
		  
		   var fragmentedTime:Array=(selectedRecord.getItemAt(0).time).split(":",2);
		   editWindow.timeInHour.value=parseInt(fragmentedTime[0].toString());
		   editWindow.timeInMinute.value=parseInt(fragmentedTime[1].toString());
		   
		   var secondFragmentedTime:Array=(selectedRecord.getItemAt(0).secondTime).split(":",2);
		   editWindow.secondTimeInHour.value=parseInt(secondFragmentedTime[0].toString());
		   editWindow.secondTimeInMinute.value=parseInt(secondFragmentedTime[1].toString());
		   
		   var thirdFragmentedTime:Array=(selectedRecord.getItemAt(0).thirdTime).split(":",2);
		   editWindow.thirdTimeInHour.value=parseInt(thirdFragmentedTime[0].toString());
		   editWindow.thirdTimeInMinute.value=parseInt(thirdFragmentedTime[1].toString());
		   
	   	   editWindow.refreshFunction=courseCodeChange;
		   PopUpManager.centerPopUp(editWindow);	  
    }
}		

/**
 * Method to fire on the click event of the delete button
 **/
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
		
		for each (var obj:Object in externalExaminarCourseGrid.dataProvider){
			if(obj.select==true){
				recordTypeList.addItem(obj.externalExaminerId);		
			}
		}		
		
		
		infoObject["examinerCourseRecords"] = recordTypeList;
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		deleteExaminerCourseRecords.send(infoObject);        	     
	}
	if(event.detail==2)
	{
		var gridData:ArrayCollection=externalExaminarCourseGrid.dataProvider as ArrayCollection;
            
            	for(var e:int=0;e<gridData.length;e++)
            	{
            	  var gridItem:Object=gridData.getItemAt(e);
            	if(gridItem.select==true)
            	{
            			gridItem.select=false;
            			gridData.setItemAt(gridItem,e);
            	externalExaminarCourseGrid.dataProvider=gridData;
            	}
            }
            deletebutton.enabled=false;
	}
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
			 var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
            }
	
	if(successDetails.Details.list_values=="success"){
		
		courseCodeChange();
		
		Alert.show(commonFunction.getMessages('savedSuccessfully'),
			resourceManager.getString('Messages','success'),0,null,askToAddMore,successIcon);
		
		
		
		
	}else{
		Alert.show((resourceManager.getString('Messages','requestFailed')),
		(resourceManager.getString('Messages','failure')),0,null,null,null);	
	}	
	
	Mask.close();		
}


/**
 * method executed on processing of the request
 * */
[Bindable]
public var deleteSuccessDetails: XML;
public function onDeleteSuccess(event:ResultEvent):void{
	
	deleteSuccessDetails=event.result as XML;
	
	if(deleteSuccessDetails.sessionConfirm == true)
             {
          	 Alert.show(resourceManager.getString('Messages','sessionInactive'));
			 var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");

            }
	
	if(deleteSuccessDetails.Details.list_values=="success"){
		
		Alert.show((resourceManager.getString('Messages','recordsDeletedSuccessfully')),(resourceManager.getString('Messages','success')),0,null,null,successIcon);
		
		courseCodeChange();
		
		deletebutton.enabled=false;		
		
	}else{
		Alert.show((resourceManager.getString('Messages','requestFailed')),
		(resourceManager.getString('Messages','failure')),0,null,null,null);	
	}	
	
	Mask.close();		
}	

/**
 * Method to fire the click event of the cancel button,remove UI from main window
 **/
public function cancel():void
{
this.parentDocument.loaderCanvas.removeAllChildren();
}

