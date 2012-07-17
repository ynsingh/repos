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

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/success.png")]private var successIcon:Class;
import common.commonFunction;
import mx.states.RemoveChild;
import flash.utils.getTimer;
import programMaster.StartDate;
import common.Mask;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;


/**
 * This file consist of the method definitions used in 
 * creating a university
 * @author Ashish
 * @date 9 Feb 2011
 * @version 1.0
 * */
[Bindable]
public var universityList:XML;
public var universitylist:ArrayCollection
public function ongetUniversitySuccess(event:ResultEvent):void{
	
	universityList=event.result as XML;		
	
	universitylist =new ArrayCollection();
}

public function ongetUniversityFailure(event:FaultEvent):void
{	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
}



/**
 * @public
 * this method send the Form's data to server side to save.
 * @param event a mouse event object.
 */
public function createUniversity():void
{
	
	
	if(validateForm())
	{		
		
		var infoObject:Object={};		
		
		infoObject["userId"] = new Date;
		
			
			var duplicate:Boolean=false;
			var toLowerName:String;
			var toLowerNickName:String;
			var toLowerGetName:String;
			var toLowerGetNickName:String;
			
						
			
			for each(var obj:Object in universityList.role){
				
				toLowerName = universityName.text.toLowerCase();
				toLowerNickName = universityNickName.text.toLowerCase();
				toLowerGetName = obj.description.toLowerCase();
				toLowerGetNickName = obj.id.toLowerCase();						
				
				if((toLowerGetName==toLowerName)
					||(toLowerGetNickName==toLowerNickName)){
					
					duplicate=true;
					
				}
			}
					
			
			var dateDiff:int = commonFunction.calculateDays(new Date(sessionStartDate.text),
													new Date(sessionEndDate.text));													
			
			if(dateDiff<365){
				
				Alert.show(commonFunction.getMessages('diffsession'),
				commonFunction.getMessages('failure'),4,null,null,errorIcon);				
				sessionStartDate.errorString=commonFunction.getMessages('diffsession');
				sessionEndDate.errorString=commonFunction.getMessages('diffsession');
				
			}else if(duplicate){
				
				Alert.show(commonFunction.getMessages('duplicateUniversity'),
				commonFunction.getMessages('failure'),4,null,null,errorIcon);
				
			}else{
				
				Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);
				
				
				
			}		
	}
}

public function onOK(event:CloseEvent):void{
			
			if(event.detail==Alert.YES){				
				
				infoObject["nickName"] = universityNickName.text;
				infoObject["universityName"]=universityName.text;
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
				
				setUniversity.send(infoObject);
				
				Mask.show(commonFunction.getMessages('pleaseWait'));
				
			}
			
		}

public function onsetUniversitySuccess(event:ResultEvent):void{
	
	Mask.close();
	
	Alert.show(commonFunction.getMessages('successunivcreation'),
		commonFunction.getMessages('success'),4,this,onSuccess,successIcon);
		
	
}

public function onsetUniversityFailure(event:FaultEvent):void
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
		resetButtonClickHandler();
	}
	
	
}


