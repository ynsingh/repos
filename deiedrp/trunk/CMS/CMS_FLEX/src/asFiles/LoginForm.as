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
import common.SettingPanel;
import common.commonFunction;

import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;


[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var params:Object={};
[Bindable] public var universityId:String;
[Bindable] public var userId:String;
[Bindable] public var userGroupId:String;
[Bindable] public var userGroupName:String;
[Bindable]public var urlPrefix:String;
[Bindable] public var userName:String;
[Bindable] public var universityName:String;
[Bindable] public var startDate:String;
[Bindable] public var endDate:String;


var userInfoXml:XML;

           import mx.managers.BrowserManager;
        import mx.managers.IBrowserManager;
        import mx.utils.URLUtil

        import flash.net.navigateToURL;
        import common.Mask;
        import flash.sampler.getSize;
        import flash.net.URLLoader;
        import flash.display.Loader;
        import flash.utils.ByteArray;
        import common.validations.CommonValidations;
        private var bm:IBrowserManager;
        [Bindable]
        private var userGrpId:String;
        [Bindable]
        private var userGroup:String; 
        [Bindable]
        private var uName:String; 
        [Bindable]
		public var application:String;//Change private to public By Devendra
          

        private function init(e:Event):void {
        	vStack.selectedIndex=2;
            bm = BrowserManager.getInstance();                
            bm.init("", "Welcome!");
            
            userNameLabel.visible=true;

            /* The following code will parse a URL that passes firstName and lastName as
               query string parameters after the "#" sign; */
            var o:Object = URLUtil.stringToObject(bm.fragment, "&");                
            userGrpId = o.userGroupId;
            userGroup = o.userGroupName;
             userName=o.userName;
             application=o.application;
             universityName=o.university;
            universityId=o.universityId;
            getMenues(userGrpId,userGroup,application);   
            Mask.show("Please Wait");          
        }
//getting menues
public function getMenues(roleId:String,roleName:String,application:String):void
{	
	var param:Object=new Object();
	param["userGroupId"]=roleId;
	param["userGroupName"]=roleName;
	param["application"]=application;
	menuHttpService.send(param);
}

public function setLogo():void{

	logoImage.load(commonFunction.getConstants('url')+'/UniversityLogos/'+universityId+'.png');	

}

//login fault handler
public function loginInfoFaultHandler(event:FaultEvent):void
{
	Alert.show(event.message+"");
}

//logout result handler
public function logoutResultHandler(event:ResultEvent):void
{
	logout();
}

//logout result handler
public function logoutFaultHandler(event:FaultEvent):void
{
	logout();
}

//On click of logout button
public function logout():void
{
	var url:String="";
	    url=commonFunction.getConstants('navigateHome');
	   var urlRequest:URLRequest=new URLRequest(url);
	   urlRequest.method=URLRequestMethod.POST;
	    navigateToURL(urlRequest,"_self");
	userInfoXml=new XML;
	userNameLabel.visible=false;
	loaderCanvas.removeAllChildren();
	userNameText.setFocus();
}

//on click of setting button
public function goToSettings():void
{
	loaderCanvas.removeAllChildren();
	loaderCanvas.addChild(new SettingPanel);
}