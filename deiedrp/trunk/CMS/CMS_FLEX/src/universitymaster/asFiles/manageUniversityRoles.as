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
 * This file consist of the method definitions used in 
 * managing the menu items(links) associated to 
 * the role of the university.
 * @author Ashish
 * @date 22 Jan 2011
 * @version 1.0
 **/

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/success.png")]private var successIcon:Class;
import common.commonFunction;
import common.Mask;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;



/**
 * This method is used to get the list of
 * roles defined for the university.
 **/
public function getRoles():void{
	
	var infoObject:Object={};
	infoObject["userId"]=new Date;
	infoObject["counter"]="two";
	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
	unirole.send(infoObject);
}


[Bindable]
var roll:XML;
private function onSuccess(event:ResultEvent):void{
	
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
 * This method is used to get the list of
 * menu items used in the application 
 * defined for the university.
 **/
public function getMenus():void{
	
	applicationValidate.source = applicationCombo;
	applicationCombo.errorString = "";
	
	roleValidate.source = rolecombobox;
	rolecombobox.errorString = "";
	
	if(Validator.validateAll([roleValidate,applicationValidate]).length==0){
	
	gridcanvas.visible=true;
	
	var infoObject:Object={};
	infoObject["userId"]=new Date;
	infoObject["roleId"]=rolecombobox.selectedItem.id;	
	infoObject["applicationId"] = applicationCombo.selectedItem;	
	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
	menus.send(infoObject);
	
	}else{
	
		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),
		(commonFunction.getMessages('error')),0,null,null,errorIcon);		
		
	}
	
}


[Bindable]
var menuas:XML;
private function onSuccessMenus(event:ResultEvent):void{
	
	menuas=event.result as XML;
	
	if(menuas.sessionConfirm == true){
		
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
	
	
	for each (var o:Object in menuas.role){
		
		menuslist.addItem({select:false,id:o.id,description:o.description});
		
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
 * This method is used to delete 
 * the list of selected items 
 * for the concerned role.
 **/
public function deleteRoleAuthorities():void{
	
	menuslist =	new ArrayCollection();	
	
	for each (var o:Object in menuitemsdislpay.dataProvider){
		if(o.select==true){
			menuslist.addItem(o.id);		
		}
	}
	
	if(menuslist.length==0){		
		Alert.show(commonFunction.getMessages('selectARecord'),
		commonFunction.getMessages('error'),0,null,null,errorIcon);
	}else{
		
		Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onValidation,questionIcon);	
	}
}

public function onValidation(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		
		var infoObject:Object={};
	infoObject["userId"]=new Date;
	infoObject["roleId"]=rolecombobox.selectedItem.id;
	infoObject["deleteMenus"] = menuslist;
	infoObject["applicationId"] = applicationCombo.selectedItem;
	
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
	setmenus.send(infoObject);
		
	}
	
	
	
}

public function onSuccesssetMenus(event:ResultEvent):void{
	
	Mask.close();	
	
	Alert.show(commonFunction.getMessages('deletedauthority')
	,commonFunction.getMessages('alert'),4,this,onOK,successIcon);	
	
}

private function onFailuresetMenus(event:FaultEvent):void{
	
	Mask.close();
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
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