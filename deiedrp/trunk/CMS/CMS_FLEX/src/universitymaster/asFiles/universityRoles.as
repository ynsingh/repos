// ActionScript file

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
 *This file consist of the method definitions used for defining
 *authorities to different roles of the university
 *@author Ashish
 *@date 22 Jan 2011
 *@version 1.0
 **/

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/success.png")]private var successIcon:Class;
import common.commonFunction;
import common.Mask;
import mx.validators.Validator;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;


/**
 *This method retrives the list of roles 
 * defined in the university
 **/
public function getRoles():void{
	
	var userId:Object={};
	userId["userId"]=new Date;
	userId["counter"]="one";
	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
	unirole.send(userId);
}

[Bindable]
var roll:XML;
public function onSuccess(event:ResultEvent):void{
	
	roll=event.result as XML;
	
	if(roll.sessionConfirm == true){
		
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
//          	this.parentDocument.vStack.selectedIndex=0;
//          	this.parentDocument.loaderCanvas.removeAllChildren();
          	
          	 	var url:String="";
            	url=commonFunction.getConstants('navigateHome');
          		var urlRequest:URLRequest=new URLRequest(url);
           		urlRequest.method=URLRequestMethod.POST;
            	navigateToURL(urlRequest,"_self");
      }	
	
	rolecombobox.dataProvider =roll.role ;
	rolecombobox.labelField ="description";
	
	Mask.close();
	
}

private function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();
}


/**
 *This method retrives the list of menu items(links)
 * to be associated with different roles 
 * of the university
 **/
public function getMenus():void{
	
	applicationValidate.source = applicationCombo;
	applicationCombo.errorString = "";
	
	roleValidate.source = rolecombobox;
	rolecombobox.errorString = "";	
	
	if(Validator.validateAll([roleValidate,applicationValidate]).length==0){
		
		gridcanvas.visible=true;
	
	var userId:Object={};
	userId["userId"]=new Date;
	userId["roleId"]=rolecombobox.selectedItem.id;
	userId["applicationId"] = applicationCombo.selectedItem;	
	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
	menus.send(userId);
		
	}else{
		
		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),
		(commonFunction.getMessages('error')),0,null,null,errorIcon);	
		
	}
	
	
	
}


[Bindable]
var menusxml:XML;
private function onSuccessMenus(event:ResultEvent):void{
	
	menusxml=event.result as XML;
	
	if(menusxml.sessionConfirm == true){
		
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
//          	this.parentDocument.vStack.selectedIndex=0;
//          	this.parentDocument.loaderCanvas.removeAllChildren();
          	
          	 	var url:String="";
            	url=commonFunction.getConstants('navigateHome');
          		var urlRequest:URLRequest=new URLRequest(url);
           		urlRequest.method=URLRequestMethod.POST;
            	navigateToURL(urlRequest,"_self");
      }
	
	var menuslist:ArrayCollection =new ArrayCollection();
	
	
	for each (var obj:Object in menusxml.role){
		
		menuslist.addItem({select:false,id:obj.id,description:obj.description});
		
	}
	
	if(menuslist.length==0){
		
//		Alert.show(commonFunction.getMessages('noRecordFound'),
//			commonFunction.getMessages('alert'),4,null,null,errorIcon);
		gridcanvas.visible=false;
	}
		menuitemsdislpay.dataProvider=menuslist;
		menuitemsdislpay.labelField="description";
		
		Mask.close();
	
}

private function onFailureMenus(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();
}

public var menuslist:ArrayCollection;

/**
 *This method associates the menu items(links)
 * to different roles of the university
 **/
public function setRoleAuthorities():void{
	
		menuslist = new ArrayCollection();
	
	for each (var obj:Object in menuitemsdislpay.dataProvider){
		if(obj.select==true){
			menuslist.addItem(obj.id);		
		}
	}
	
	if(menuslist.length==0){
		
		Alert.show(commonFunction.getMessages('selectARecord'),
		commonFunction.getMessages('error'),0,null,null,errorIcon);
		
	}else {
		
		Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onValidation,questionIcon);		
	}
	
}

public function onValidation(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		
		var infoObject:Object={};
		infoObject["userId"]=new Date;
		infoObject["roleId"]=rolecombobox.selectedItem.id;
		infoObject["selectedMenus"] = menuslist;
		infoObject["applicationId"] = applicationCombo.selectedItem;
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		setmenus.send(infoObject);
		
	}	
}


public function onSuccesssetMenus(event:ResultEvent):void{	
	
	Alert.show(commonFunction.getMessages('successOnInsert'),
	commonFunction.getMessages('alert'),4,this,onOK,successIcon);	
	
	Mask.close();
	
}

private function onFailuresetMenus(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('failure'));
	
	Mask.close();
}

/**
 *Method to fire the click event of the button
 * ie. to reflect the changes made in the record
 * onto the application 
 **/
public function onOK(event:CloseEvent):void{
	if(event.detail==Alert.OK){		
			getMenus();	
			linkbutton.enabled=false;	
	}
	
	
}