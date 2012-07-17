/**
 * @(#) LoginForm.as
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
import common.RoleSelector;
import common.commonFunction;

import flash.events.KeyboardEvent;
import flash.net.URLRequestMethod;
import flash.ui.Keyboard;
import flash.utils.Timer;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.DateField;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.rpc.http.HTTPService;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var params:Object={};
[Bindable] public var universityId:String;
[Bindable] public var userId:String;
[Bindable] public var userGroupId:String;
[Bindable] public var userGroupName:String;
[Bindable] public var urlPrefix:String;
[Bindable] public var userName:String;
[Bindable] public var universityName:String;
[Bindable] public var startDate:String;
[Bindable] public var endDate:String;

public var getSessionStartDate:HTTPService=new HTTPService();
public var getLoginInfoService:HTTPService=new HTTPService();
public var getRegistrationStartDate:HTTPService=new HTTPService();
var userInfoXml:XML;
public var selectRolectPopup:RoleSelector=new RoleSelector();


public function onCreationComplete():void{		
          getSessionStartDate.useProxy=false;
          getSessionStartDate.resultFormat="e4x";
          getSessionStartDate.method="POST";
          getSessionStartDate.addEventListener(ResultEvent.RESULT,onSuccessSessionStartDate);
          getSessionStartDate.addEventListener(FaultEvent.FAULT,loginInfoFaultHandler);
          getSessionStartDate.url=commonFunction.getConstants('urlCms')+'/login/getSessionStartDate.htm';   
		  getSessionStartDate.send(params);	
		  
		  	

		  

}
/**
 *On Tab key Event at Username Text Input
 */  
public function onTabUserName(event:KeyboardEvent):void{
	if(event.keyCode==Keyboard.TAB){
	passwordText.setFocus();

	}	
}


/**
 *On Tab key Event at Password Text Input
 */
public function onTabPassword(event:KeyboardEvent):void{
		if(event.keyCode==Keyboard.TAB){
	     applications.setFocus();	
	}
}
/**
 *On Tab key Event at Application ComboBox 
 */
public function onApplicationTab(event:KeyboardEvent):void{
	if(event.keyCode==Keyboard.TAB){
	loginButton.setFocus();	
	}
	
}

//On click of login button
public function Login():void
{
	if(Validator.validateAll([userNameValidator,passwordValidator,appValidator]).length==0)
	{
		params["userName"]=userNameText.text;
		params["password"]=passwordText.text;
         
         if(applications.selectedItem=='UPM'){ 
         params["application"]=applications.selectedItem;
          getLoginInfoService.useProxy=false;
          getLoginInfoService.resultFormat="e4x";
          getLoginInfoService.method="POST";
          getLoginInfoService.addEventListener(ResultEvent.RESULT,loginInfoResultHandler);
          getLoginInfoService.addEventListener(FaultEvent.FAULT,loginInfoFaultHandler);
         //getLoginInfoService.url='http://localhost:8080/ESTABLISHMENT/login/getLoginDetails.htm';  
          getLoginInfoService.url=commonFunction.getConstants('urlUpm')+'/login/getLoginDetails.htm'; 
		  getLoginInfoService.send(params);
		  
         }
         	else if(applications.selectedItem=='CMS'){
         params["application"]=applications.selectedItem;		
          getLoginInfoService.useProxy=false;
          getLoginInfoService.resultFormat="e4x";
          getLoginInfoService.method="POST";
          getLoginInfoService.addEventListener(ResultEvent.RESULT,loginInfoResultHandler);
          getLoginInfoService.addEventListener(FaultEvent.FAULT,loginInfoFaultHandler);
          //getLoginInfoService.url='http://localhost:8080/CMS/login/getLoginDetails.htm';
           getLoginInfoService.url=commonFunction.getConstants('urlCms')+'/login/getLoginDetails.htm';   
		  getLoginInfoService.send(params);
		}

	else if(applications.selectedItem=='ADM'){
        params["application"]=applications.selectedItem;		
          getLoginInfoService.useProxy=false;
          getLoginInfoService.resultFormat="e4x";
          getLoginInfoService.method="POST";
         getLoginInfoService.addEventListener(ResultEvent.RESULT,loginInfoResultHandler);
          getLoginInfoService.addEventListener(FaultEvent.FAULT,loginInfoFaultHandler);
          //getLoginInfoService.url='http://localhost:8081/AdmissionSystem/login/getLoginDetails.htm';
          getLoginInfoService.url=commonFunction.getConstants('urlCms')+'/login/getLoginDetails.htm';   
		  getLoginInfoService.send(params);
		}
		else{
			getLoginInfoService.url='file:///E:/Current%20Flex%20Side%20Workspace/login_page/bin-debug/login_page.html';
		}
	}
	else{
		Alert.show((commonFunction.getMessages('pleaseEnterAllLoginDetails')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}

}


//login result handler
public function loginInfoResultHandler(event:ResultEvent):void
{
	userInfoXml=event.result as XML;
	
	var values:ArrayCollection=new ArrayCollection();
	for each(var obj:Object in userInfoXml.loginInfo)
	{
		values.addItem({userId:obj.userId,universityId:obj.universityId,userGroupId:obj.userGroupId,
		userGroupName:obj.userGroupName,userName:obj.userName, universityName:obj.universityName});
	}
	if(values.length>0)
	{
		userId=values[0].userId;
		userName=values[0].userName;
		universityId=values[0].universityId;
		universityName=values[0].universityName;
		startDate=values[0].startDate;
		endDate=values[0].endDate;
		userNameText.text="";
		passwordText.text="";
		


		if(values.length==1)
		{
			var application=applications.selectedItem;
			userName=values[0].userName;
			userGroupId=values[0].userGroupId;
			userGroupName=values[0].userGroupName;
			userId=values[0].userId;
			universityId=values[0].universityId;
			universityName=obj.universityName;
			
 
			var url:String=""
			if(applications.selectedItem=='UPM'){
			
			   url=commonFunction.getConstants('navigateUrlUpm')+'#userGroupId='+userGroupId+'&userGroupName='+userGroupName+'&userName='+userName+'&application='+application
			}
			else if(applications.selectedItem=='CMS'){
		
				url=commonFunction.getConstants('navigateUrlCms')+'#userGroupId='+userGroupId+'&userGroupName='+userGroupName+'&userName='+userName+'&application='+application+'&university='+universityName
			}
else if(applications.selectedItem=='ADM'){
				url=commonFunction.getConstants('navigateUrlAdm')+'?userGroupId='+userGroupId+'&userGroupName='+userGroupName+'&userId='+userId+'&userName='+userName+'&urlHome='+commonFunction.getConstants('navigateHome')+'&universityId='+universityId+'&application='+application
			}
			else{
				url=commonFunction.getConstants('navigateHome');
			}
			applications.selectedIndex=-1;
			var urlRequest:URLRequest=new URLRequest(url);
			
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
	
		}
		else
		{
			selectRolectPopup=RoleSelector(PopUpManager.createPopUp(this,RoleSelector,true));
			selectRolectPopup.roleCombo.dataProvider=userInfoXml.loginInfo.userGroupName;
			selectRolectPopup.roleCombo.selectedIndex=-1;
			selectRolectPopup.dataXml=userInfoXml;
			selectRolectPopup.application=applications.selectedItem.toString();
			selectRolectPopup.universityId=universityId;
			selectRolectPopup.universityName=universityName;
			selectRolectPopup.userId=userId;
			selectRolectPopup.userName=userName;			
			PopUpManager.centerPopUp(selectRolectPopup);
		}
	}
	else
	{
		Alert.show((commonFunction.getMessages('invalidLoginDetails')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
}



//login fault handler
public function loginInfoFaultHandler(event:FaultEvent):void
{
	Alert.show(event.message+"");
}

//On click of Apply online hyperlink on the main cms page
public function LoginAdmission():void{	
	var url:String=""
	url=commonFunction.getConstants('navigateUrlAdm')+'?flag=apply&urlHome='+commonFunction.getConstants('navigateHome')   
	var urlRequest:URLRequest=new URLRequest(url);	
	urlRequest.method=URLRequestMethod.POST;
	navigateToURL(urlRequest,"_self");      					     
}

public static const millisecondsPerDay:int = 1000 * 60 * 60 * 24;
public var regDays:Number=0;
public var regExtendDays:Number=0;
public 	var systemFullDate:Date=new Date();
//function for enrollment link button enabled and disabled
public function onSuccessSessionStartDate(event:ResultEvent):void{
	      getRegistrationStartDate.useProxy=false;
          getRegistrationStartDate.resultFormat="e4x";
          getRegistrationStartDate.method="POST";
          getRegistrationStartDate.addEventListener(ResultEvent.RESULT,onSuccessRegStartDate);
          getRegistrationStartDate.addEventListener(FaultEvent.FAULT,loginInfoFaultHandler);
          getRegistrationStartDate.url=commonFunction.getConstants('urlCms')+'/login/getRegistrationStartDate.htm';   
		  getRegistrationStartDate.send(params);
	
	var  bool:Boolean=false;
	var result:XML=event.result as XML;
	var fullSessionDate:String=result.sessionStartDates;
	var tempEnrollBefore:Number=Number(result.enrollmentPeriod);//Enrollment days before Session Start
	var enrollExtendDays:Number=Number(result.enrollExtDays)+1;
	                    regDays=Number(result.regDays);
	              regExtendDays=Number(result.regExtDays)+1;
   var tempDate:Date=DateField.stringToDate(fullSessionDate,"YYYY-MM-DD");
	
	
	
	var EnrollmentStartDate:Date=new Date(tempDate.getTime() -(tempEnrollBefore* millisecondsPerDay));
	var EnrollmentEndDate:Date=new Date(tempDate.getTime() +(enrollExtendDays*millisecondsPerDay));
	var EnrollmentPeriod:Date=new Date(EnrollmentEndDate.getTime()-(EnrollmentStartDate.getTime()));
	var enrollmentPeriod:Number=EnrollmentPeriod.getDate();
	var bool:Boolean=false;
	var fixedDate:Date=null;
	var fixedMonth:Number=null;
	if(systemFullDate>EnrollmentStartDate && systemFullDate<EnrollmentEndDate){
		bool=true;	
	}
	if(bool==true){
     enrollmentLink.visible=true;	

	}
	else{
	enrollmentLink.visible=false;	
	}		
		
}
//function for registration link button enabled and disabled
public function onSuccessRegStartDate(event:ResultEvent):void{
	var bool1:Boolean=false;
	var result:XML=event.result as XML;
	var semesterDate:String="";
	var fullSemesterDate:Date=null;
	var semDateArry:ArrayCollection=new ArrayCollection();
	for each(var o:Object in result.show){
		 semesterDate=o.RegDate;
		
		fullSemesterDate=DateField.stringToDate(semesterDate,"YYYY-MM-DD");
		 semDateArry.addItem({semDate:fullSemesterDate});
  	 	var RegistrationStartDate:Date=new Date(fullSemesterDate.getTime() -(regDays* millisecondsPerDay));
		var RegistrationEndDate:Date=new Date(fullSemesterDate.getTime() +(regExtendDays*millisecondsPerDay));
		
		if(systemFullDate>=RegistrationStartDate &&systemFullDate<RegistrationEndDate)
		{
		  bool1=true;	
		  	
		}
	}
	if(bool1==true){
	registrationLink.visible=true;	
	}	
	else{
		registrationLink.visible=false;
	}
	   	   
	   
}

/**
 *On enter key Event at Login Button 
 * added by ashish mohan
 */
public function onLogin(event:KeyboardEvent):void{
	if(event.keyCode==13){
	Login();	
	}
}







