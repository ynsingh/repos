//ActionScript file

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
 *This file consist of the method definitions used in 
 *defining authority to an employee of the university.
 *The idea behind is to revoke the authority defined
 *on a menu item(link) as per the user group of the
 *of the concerned employee 
 *@author Ashish
 *@date 22 Jan 2011
 *@version 1.0
 **/

import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/success.png")]private var successIcon:Class;
import common.commonFunction;
import common.Mask;
[Embed(source="/images/error.png")]private var errorIcon:Class;



public function onCreationComplete():void{
	
		var infoObject:Object={};
		infoObject["userId"]=new Date;
		infoObject["counter"] = "one";
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		getusersfromuserinfo.send(infoObject);
	
}

/**
 * This method retrives the list of menu items(links)
 * associated to the group(role) of the concerned
 * employee.
 * */
public function getRoleItems():void{
	
	var i:int=Validator.validateAll([textvalidate]).length;
	
	
	if(i!=0){
		
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),
		commonFunction.getMessages('alert'),0,null,null,errorIcon);
		
	}else{
		
		var infoObject:Object={};
		infoObject["userId"]=new Date;
		infoObject["empCode"] = empId.text;
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		getemployeeitems.send(infoObject);
		
	}
	
}

[Bindable]
var details: XML;
var detailslist:ArrayCollection =new ArrayCollection();
public function onItemsSuccess(event:ResultEvent):void{
	
	itemscanvas.visible=true;
	
	details=event.result as XML;
	
	if(details.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	
	detailslist.removeAll();
		
		for each (var o:Object in details.role){
			
			detailslist.addItem({select:false,id:o.id,description:o.description,role_id:o.role_id,
				role_description:o.role_description,user_id:o.user_id});
			
		}
		
		if(detailslist.length==0){			
			Alert.show(commonFunction.getMessages('noRecordFound'),
			commonFunction.getMessages('alert'),4,null,null,errorIcon);
			itemscanvas.visible=false;
			
			Mask.close();
			
		}else{
		
		roleLabel.text=detailslist.getItemAt(0).role_description;	
		roleitemdata.dataProvider=detailslist;
		roleitemdata.labelField="description";
		
		}
		
		Mask.close();
	
}

[Bindable]
public var usersXml:XML;
[Bindable]
public var userList:ArrayCollection;
public function onUsersSuccess(event:ResultEvent):void{
	
	usersXml = event.result as XML;
	
	if(usersXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
	
	userList = new ArrayCollection();
	
	for each(var userObj:Object in usersXml.role){
		
		userList.addItem({id:userObj.id,description:userObj.description});
		
	}
	
	empId.selectedIndex=-1;
	
	empId.dataProvider = userList;
	empId.labelField = "description";
	
	Mask.close();
	
}

public function onItemsFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();
	
}

/**
 * This method is used to set the authority
 * on the selected menu item(link) for the
 * concerned user 
 **/
var itemsPopup:setAuthority;
public function setaddedauthority():void{
	
	var detailslist:ArrayCollection =commonFunction.getSelectedRowData(roleitemdata);
		
	if(detailslist.length==1){			
	
	itemsPopup=setAuthority(PopUpManager.createPopUp(this,setAuthority,true));
	
	PopUpManager.centerPopUp(itemsPopup);	
	
	itemsPopup.employeeId=empId.text;
	itemsPopup.empId = detailslist.getItemAt(0).user_id;
	itemsPopup.menuItemId = detailslist.getItemAt(0).id;
	itemsPopup.itemNameId.text = detailslist.getItemAt(0).description;
	
	itemsPopup.getRoleItems = getRoleItems;
	
	itemsPopup.linkbutton = linkbutton;
	
	}else if(detailslist.length==0){
		Alert.show(commonFunction.getMessages('selectARecord'),commonFunction.getMessages('error'));
	}else if(detailslist.length>1){
		Alert.show(commonFunction.getMessages('selectOnlyOneRecord'),commonFunction.getMessages('error'));
	}
	
}
