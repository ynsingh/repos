/*
*Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
* All Rights Reserved.
*
* Redistribution and use in source and binary forms, with or
* without modification, are permitted provided that the following
* conditions are met:
*
* Redistributions of source code must retain the above copyright
* notice, this  list of conditions and the following disclaimer.
*
* Redistribution in binary form must reproduce the above copyright
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


/**
 * This file consist of the method definitions used in 
 * managing a university
 * @author Ashish
 * @date 9 Feb 2011
 * @version 1.0
 * */


import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.DateField;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

public var infoObject:Object={};
public var universityname:String;

[Embed(source="/images/success.png")]private var successIcon:Class;
import common.commonFunction;
import flash.events.MouseEvent;
import mx.binding.utils.BindingUtils;
import common.Mask;
import mx.events.CloseEvent;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;




/**
 * @public
 * this method will retrieve the list of universities added
 * and will also show there details for managing on selecting
 * a university from the drop down.
 */
public function onCreationComplete():void{	
	
	infoObject["userId"] = new Date;	
	infoObject["flag"] = "two";
	
	universityName.setFocus();	
	
	getUniversity.send(infoObject);
	
}
[Bindable]
public var universityList:XML;
public var universitylist:ArrayCollection;
public var universities:ArrayCollection;
public function ongetUniversitySuccess(event:ResultEvent):void{
	
	universityList=event.result as XML;	
	
	universitylist = new ArrayCollection();	
	universities = new ArrayCollection();
	
	
	if(infoObject["flag"] == "two"){
		
		for each (var obj:Object in universityList.role){
		if(String(obj.description)==loginUniversity)
			{universitylist.addItem({universityCode:obj.id,universityName:obj.description});}
		
	}	

	if(universitylist.length==0){
		
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('alert'),4,null,null,errorIcon);
	}else{
		
		universitySearchCB.dataProvider=universitylist;
		universitySearchCB.labelField="universityName";
		
	}	
	infoObject["flag"] = "one";		
	
	getUniversity.send(infoObject);
		
	}else if(infoObject["flag"] == "one"){
		
		for each (var obj1:Object in universityList.role){			
		
		universities.addItem({universityCode:obj1.id,universityName:obj1.description});
		
	}	
		
	}
	
	
	
}

public function ongetUniversityFailure(event:FaultEvent):void
{	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
}

/**
 * @public
 * this method is called on edit button click event.
 * on method call a edit form will open.
 * @param event a mouse event object.
 */
 [Bindable]
public var titleDetails:String;
public function editButtonClickHandler(event:MouseEvent):void
{
	if(event.currentTarget.id=="deleteButton"){
		
		titleDetails="Delete Mode";
		
		universityName.editable=false;
		universityNickName.editable=false;
		sessionStartDate.enabled=false;
		sessionEndDate.enabled=false;
		address.editable=false;
		city.editable=false;
		stateCB.enabled=false;
		pincode.editable=false;
		phoneNumber.editable=false;
		otherPhone.editable=false;
		faxNumber.editable=false;
		countryCB.editable = false;
		
		updateButton.visible=false;
		resetButton.visible=false;
		activeButton.visible=true;
		Alert.show("Not Authorised To Delete Your University",commonFunction.getMessages('error'),4,null,null,errorIcon);
		
	}else if(event.currentTarget.id=="editButton"){
		
		titleDetails="Edit Mode";
		
		universityName.editable=true;
		universityNickName.editable=true;
		sessionStartDate.enabled=true;
		sessionEndDate.enabled=true;
		address.editable=true;
		city.editable=true;
		stateCB.enabled=true;
		pincode.editable=true;
		phoneNumber.editable=true;
		otherPhone.editable=true;
		faxNumber.editable=true;
		countryCB.editable = true;
		
		updateButton.visible=true;
		resetButton.visible=true;
		activeButton.visible=false;		
		
		infoObject["userId"] = new Date;	
	
		infoObject["universityCode"] = universitySearchCB.selectedItem.universityCode;	
		
		getUniversityDetails.send(infoObject);	
		
	}
	
	
}



[Bindable] public var start:String="";
[Bindable] public var universityDetailsList:XML;
public function ongetDetailsSuccess(event:ResultEvent):void{
	
	universityDetailsList=event.result as XML;
	
	var universityDetailslist:ArrayCollection = new ArrayCollection();	
	
	for each (var obj:Object in universityDetailsList.Details){
		
		universityDetailslist.addItem({universityCode:obj.universityCode,universityName:obj.universityName,
			sessionStartDate:obj.sessionStartDate,sessionEndDate:obj.sessionEndDate,universityAddress:obj.universityAddress,
			universityCity:obj.universityCity,universityState:obj.universityState,universityPhoneNumber:obj.universityPhoneNumber,
			otherPhoneNumber:obj.otherPhoneNumber,faxNumber:obj.faxNumber,pinCode:obj.pinCode,
			status:obj.status,nickName:obj.nickName,countryName:obj.countryName});
		
	}
	
	editWindow.visible=true;
	
	start=universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).sessionStartDate;
	var end:String=universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).sessionEndDate;
	
	if(universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).status=="1"){
		
		currentStatus.text = "Active";
		
	}else{
		
		currentStatus.text = "Inactive";
		
	}
	
	universityName.text = universitySearchCB.selectedLabel;
	universityNickName.text=universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).nickName;
	sessionStartDate.data =DateField.stringToDate(start,"YYYY/MM/DD");
	sessionEndDate.data = DateField.stringToDate(end,"YYYY/MM/DD");
	address.text = universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).universityAddress;
	city.text = universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).universityCity;
	stateCB.text = universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).universityState.toString();
	/*
	*add karna hia kal backend mein as well as over here
	*/
	countryCB.text = universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).countryName;
	pincode.text = universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).pinCode;
	phoneNumber.text = universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).universityPhoneNumber;
	otherPhone.text = universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).otherPhoneNumber;
	faxNumber.text = universityDetailsList.Details.(universityName==universitySearchCB.selectedLabel).faxNumber;
	
}

public var dateDiff:int; 

/**
 * @public
 * this method is called on update button click event.
 * this method is used to update the data.
 * @param event a mouse event object.
 */
public function updateButtonClickHandler(event:MouseEvent):void
{
	if(validateForm())
	{
		var infoObject:Object={};
		
		
		var duplicate:Boolean=false;
		var toLowerName:String;
		var toLowerNickName:String;
		var toLowerGetName:String;
		var toLowerGetNickName:String;
		
		
		for each(var obj:Object in universities){	
			
				toLowerName = universityName.text.toLowerCase();
				toLowerNickName = universityNickName.text.toLowerCase();
				toLowerGetName = obj.universityName.toLowerCase();
				toLowerGetNickName = obj.universityCode.toLowerCase();			
				
				if((universitySearchCB.selectedLabel!=universityName.text)){
				
				if(toLowerGetName==toLowerName){					
					duplicate=true;					
				}
			}else if((universityDetailsList.Details.
				(universityName==universitySearchCB.selectedLabel)
				.nickName!=universityNickName.text)){					
					if(toLowerGetNickName==toLowerNickName){					
					duplicate=true;				
				}				
			}
		}
		
		dateDiff = commonFunction.calculateDays(new Date(sessionStartDate.text),
													new Date(sessionEndDate.text));
													
			if(dateDiff<365){
				
				Alert.show(commonFunction.getMessages('diffsession'),
				commonFunction.getMessages('failure'),4,null,null,errorIcon);				
				sessionStartDate.errorString=commonFunction.getMessages('diffsession');
				sessionEndDate.errorString=commonFunction.getMessages('diffsession');
				
			}else if(duplicate){
				
				Alert.show(commonFunction.getMessages('duplicateUniversity'),
				commonFunction.getMessages('success'),4,null,null,errorIcon);
				
			}else{
				
				Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);

		
	}
	}
	
}

public function onOK(event:CloseEvent):void{
			
			if(event.detail==Alert.YES){				
				
				infoObject["userId"] = new Date;
		infoObject["nickName"] = universityNickName.text;
		infoObject["universityName"]=universityName.text;
		
//		Alert.show(""+int(sessionStartDate.text.substring(6,10))+ " " + int(start.substring(0,4))+" " + dateDiff );
		
		if(int(sessionStartDate.text.substring(6,10))>int(start.substring(0,4))&&(dateDiff>=365)){
			
//			Alert.show("in insert");
								
			infoObject["flag"] = "insert"; 			
		}else{
			
//			Alert.show("in update");
			
			infoObject["flag"] = "update";
		}
		infoObject["sessionStartDate"]=dateFormatter.format(sessionStartDate.text);
		infoObject["sessionEndDate"]=dateFormatter.format(sessionEndDate.text);
		infoObject["address"]=address.text;
		infoObject["city"]=city.text;
		infoObject["state"]=stateCB.text;
		infoObject["country"] = countryCB.text;
		infoObject["pinCode"]=pincode.text;
		infoObject["phoneNumber"]=phoneNumber.text;
		infoObject["otherPhoneNumber"]=otherPhone.text;
		infoObject["faxNumber"]=faxNumber.text;
		infoObject["universityCode"]=universitySearchCB.selectedItem.universityCode;	
		
		updateUniversity.send(infoObject);
		
				Mask.show(commonFunction.getMessages('pleaseWait'));
				
			}
			
		}

public function onupdateUniversitySuccess(event:ResultEvent):void{
	
	Mask.close();
	
	
	Alert.show(commonFunction.getMessages('successunivupdate'),
		commonFunction.getMessages('success'),4,this,onSuccess,successIcon);
	
	
}

public function onupdateUniversityFailure(event:FaultEvent):void
{
	
	Mask.close();
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
}

/**
 * @public
 * this method is called on activate button click event.
 * this method is used to activate or inactivate the records.
 * @param event a mouse event object.
 */
public function activeButtonClickHandler(event:MouseEvent):void
{

	//Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onDelete,questionIcon);
		
		
	
}

public function onDelete(event:CloseEvent):void{

if(event.detail==Alert.YES){	
	
	var oldStartDateField:DateField=new DateField;
		
		
		oldStartDateField.data =dateFormatter.format(DateField.stringToDate(start,"YYYY/MM/DD"));			
		
		infoObject["userId"] = new Date	;	
		infoObject["universityCode"]=universitySearchCB.selectedItem.universityCode;
		infoObject["sessionStartDate"] = oldStartDateField.data; 
		
		updatestatusUniversity.send(infoObject);
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		}
	
}

public var updatedDetails:XML;
public function onupdateUniversityStatusSuccess(event:ResultEvent):void{
	
	updatedDetails = event.result as XML;
	
	Mask.close();
	
	if(updatedDetails.Details.list_values=="success"){
		
		Alert.show(commonFunction.getMessages('successunivstatusupdate'),
		commonFunction.getMessages('success'),4,this,onSuccess,successIcon);
		
	}else{
		
		Alert.show(commonFunction.getMessages('errorOndeletion'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
		
	}	
}

public function onupdateUniversityStatusFailure(event:FaultEvent):void
{
	
	Mask.close();
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
}


/**
 *Method to fire the click event of the button
 * ie. to reset the page
 **/
public function onSuccess(event:CloseEvent):void{
	if(event.detail==Alert.OK){	
		this.parentDocument.loaderCanvas.removeAllChildren();
	}	
}

