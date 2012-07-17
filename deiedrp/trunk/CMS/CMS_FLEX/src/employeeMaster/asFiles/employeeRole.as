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
 *This file consist of the method definitions used in assigning a role to 
 *an employee of the university
 *@author Ashish
 *@date 22 Jan 2011
 *@version 1.0
 * */


import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;
[Embed(source="/images/success.png")]private var successIcon:Class;
import common.commonFunction;
import common.Mask;
import mx.events.CloseEvent;
import employeeMaster.employeePopUp;
import employeeMaster.setEmployeeRole;
import mx.managers.PopUpManager;
[Embed(source="/images/error.png")]private var errorIcon:Class;
public var counter:String;
[Bindable]
public var length:int=0;
[Bindable]
public var cls:Function;


public function onCreationComplete():void{		
		
		var infoObject:Object={};
		infoObject["userId"]=new Date();

        empId.text="";
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
		getempdetails.send(infoObject);	
	
}

/**
 *This method is used to get the details of the employee 
 * whose id is been entered in the field provided on the 
 * interface.
* */
[Bindable]
public var gridData:ArrayCollection;
public function getEmployeeDetails():void{
	
	gridData=new ArrayCollection();
	for each(var object:Object in detailslist){
		
		if(object.employee_id==empId.selectedLabel){
			gridData.addItem({select:false,empname:object.empname,dob:object.dob,category:object.category,gender:object.gender,email_id:object.email_id,
								employee_id:object.employee_id});
		}
		
		empdetailsdislpay.dataProvider=gridData;
	}
	
	var i:int=Validator.validateAll([textvalidate]).length;
	
	
	if(i!=0){
		
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),
		commonFunction.getMessages('error'),0,null,null,errorIcon);
		
	}else{
		
		if(detailslist.length==0){
			
			Alert.show(commonFunction.getMessages('userCodeError'),
			commonFunction.getMessages('error'),0,null,null,errorIcon);
			
			empdetails.visible=false;
			empId.text="";
			
		}else{
				
				empdetails.visible=true;
	
				var infoObject:Object={};
				infoObject["userId"]=new Date();
				infoObject["counter"] = "two";	
				counter="two";
		
				Mask.show(commonFunction.getMessages('pleaseWait'));
		
				unirole.send(infoObject);				
				
			}		
		}
	
}
[Bindable]
var details: XML;
[Bindable]
var detailslist:ArrayCollection
public function onEmpSuccess(event:ResultEvent):void{
	
	details=event.result as XML;	
	
	if(details.sessionConfirm == true)
             {
             Alert.show(resourceManager.getString('Messages','sessionInactive'));
//          	this.parentDocument.vStack.selectedIndex=0;
//          	this.parentDocument.loaderCanvas.removeAllChildren();
          	
          	 	var url:String="";
            	url=commonFunction.getConstants('navigateHome');
          		var urlRequest:URLRequest=new URLRequest(url);
           		urlRequest.method=URLRequestMethod.POST;
            	navigateToURL(urlRequest,"_self");
            }
	
	detailslist=new ArrayCollection();
	var name:String;	
	
	for each (var o:Object in details.Details){		
		
		detailslist.addItem({empname:o.first_name,dob:o.dob,category:o.category,gender:o.gender,email_id:o.primary_email_id,
								employee_id:o.employee_id});
		
	}
	if(detailslist.length==0){
		
		Alert.show(commonFunction.getMessages('noemployee'),
		commonFunction.getMessages('error'),4,null,null,errorIcon);
		empdetails.visible=false;
		empId.text="";
	}
	
//	empdetailsdislpay.dataProvider=detailslist;
	empId.dataProvider = detailslist;
	empId.labelField = "employee_id";	
	empId.selectedIndex=-1;
	empId.setFocus();
	Mask.close();
	
}

private function onEmpFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();
	
}

/**
 *This method is used to get the list of pre-defined roles
 * in the university
 * */
var emprolePopup:setEmployeeRole;
public function getRoles():void{
	
	var detailslist:ArrayCollection =new ArrayCollection();
	
	for each (var o:Object in empdetailsdislpay.dataProvider){		
		if(o.select==true){			
			detailslist.addItem({select:false,empname:o.first_name,dob:o.dob,category:o.category,gender:o.gender,email_id:o.primary_email_id});			
		}
	}
		
	if(detailslist.length==0){		
		Alert.show(commonFunction.getMessages('selectARecord'),commonFunction.getMessages('error'),0,null,null,errorIcon);		
	}else{	
	
	var userId:Object={};
	userId["userId"]=new Date();
	userId["employeeCode"] = empId.text;
	userId["counter"]="three";
	
	counter="three";
	
	hii();
	
//	Mask.show(commonFunction.getMessages('pleaseWait'));
	
//	unirole.send(userId);	
	
	}
}

public function hii():void{
	
	emprolePopup=setEmployeeRole(PopUpManager.createPopUp(this,setEmployeeRole,true));	
	emprolePopup.empName.text = gridData.getItemAt(0).empname;
	emprolePopup.employeeId=empId.text;
	emprolePopup.mailId=gridData.getItemAt(0).email_id;
	emprolePopup.empId = gridData.getItemAt(0).employee_id;
	emprolePopup.canvas = empdetails;
	emprolePopup.linkbutton = linkbutton;
	emprolePopup.employeecode = empId.text;
	
	
	emprolePopup.roleList = roleArray.length;
//	emprolePopup.employeeRoleList = length;
//	emprolePopup.roleCombo.dataProvider =roll.role ;
//	emprolePopup.roleCombo.labelField ="description";
//	emprolePopup.rolls=roll;
	
	PopUpManager.centerPopUp(emprolePopup);
	emprolePopup.setFocus();
	Mask.close();
}




[Bindable]
public var roll:XML;
[Bindable]
public var roleList:XML;
[Bindable]
public var roleArray:ArrayCollection;
[Bindable]
public var employeeRoleArray:ArrayCollection;
private function onSuccess(event:ResultEvent):void{	
	
	if(counter=="two"){		
		
		roleList = event.result as XML;
		
		if(roleList.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
//          	this.parentDocument.vStack.selectedIndex=0;
//          	this.parentDocument.loaderCanvas.removeAllChildren();
          	
          	 	var url:String="";
            	url=commonFunction.getConstants('navigateHome');
          		var urlRequest:URLRequest=new URLRequest(url);
           		urlRequest.method=URLRequestMethod.POST;
            	navigateToURL(urlRequest,"_self");
            }		
		
		roleArray=new ArrayCollection();
		for each (var o:Object in roleList.role){			
			roleArray.addItem({id:o.id,description:o.description});			
		}
		Mask.close();	
	}else if(counter=="three"){
		roll=new XML;
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
		
		employeeRoleArray=new ArrayCollection();
		
		for each (var obj:Object in roll.role){			
			employeeRoleArray.addItem({id:obj.id,description:obj.description});	
		}	
				
		length=int(employeeRoleArray.length);	
	
//		hii(length);	
	}
	
	
	
}

private function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();
}
