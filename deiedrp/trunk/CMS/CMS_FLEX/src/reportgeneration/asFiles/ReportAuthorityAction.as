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
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Bindable] public static var headerSelect:Boolean=false;
public var param:Object={};
 public var reportListXml:XML;
 public var reportList:ArrayCollection;

/**
 *This file consist of the method definitions used for defining
 *authorities 
 *@author Mandeep Sodhi
 *@date 30 Mar 2012
 *@version 1.0
 **/
/**
 * On Creation Complete Of the Interface
 */ 
 public function onCreationComplete():void{
 		var userId:Object={};
	userId["userId"]=new Date;
	userId["counter"]="one";
 	unirole.send(userId);
 }
 /**
 * On Success Of the University Role service
 */ 
 public function onSuccess(event:ResultEvent){
 	var roleXml:XML=event.result as XML;
 	roleCombo.dataProvider=roleXml.role;
 	roleCombo.labelField="description";
 }
 
 /**
 * this Function Runs on the failure of Any Request
 */ 
 private function onFailure(event:FaultEvent){
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
}
 /**
 * This method runs on the Click Of OK button
 */ 
 public function onOk():void{
	gridCanvas.visible=true;
 	param["userRoleId"]=roleCombo.selectedItem.id;
 	reportDetails.send(param);
 
 }
 /**
 * This method runs on the success of the ReportDetail servicde
 * Returns All the Reports and thier Details
 */ 
 public function onReportDetailsSuccess(event:ResultEvent){
 		if(event.result.sessionConfirm == true){
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
	}
 	reportListXml=event.result as XML;
 	reportList=new ArrayCollection();
 	for each(var obj:Object in reportListXml.report){
 		var genBool:String=obj.generateAuthority;
 		var dwnBool:String=obj.downloadAuthority;
 		var genFlag:Boolean=false;
 		var dwnFlag:Boolean=false;
 		if(genBool=='Y'){
 			genFlag=true;
 		}
 		if(dwnBool=='Y'){
 			dwnFlag=true;
 		}
 		
 		reportList.addItem({reportName:obj.reportCode+"-"+obj.reportDescription,reportCode:obj.reportCode,genAuthority:genFlag,dwnAuthority:dwnFlag});
 	}
 	reportGrid.dataProvider=reportList;	
 }
 /**
 * This method runs on the Click Of Assign Authority button
 */  
 public function onAssignAuthority():void{
  Alert.show(commonFunction.getMessages('conformForContinue'),
  commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,assignOrNot,questionIcon);
 }
 
  /**
 * This method runs When the User Click On the Alert
 */ 
 public function assignOrNot(event:CloseEvent):void{
 	if(event.detail==1){
 	 	var arry:ArrayCollection=new ArrayCollection();
 	arry=reportGrid.dataProvider as ArrayCollection;
 	param["reportCode"]="";
 	param["genAuthority"]="";
 	param["dwnAuthority"]="";
 	param["deleteReportCode"]="";
 	for each(var obj:Object in arry){
 		if(obj.genAuthority==true || obj.dwnAuthority==true){
             param["reportCode"]+=obj.reportCode+"|";
             var dwn:String="N"
             var gen:String="N"
             if(obj.genAuthority==true){
             	gen="Y";
             }
             if(obj.dwnAuthority==true){
             	dwn="Y";
             }
             param["genAuthority"]+=gen+"|";
             param["dwnAuthority"]+=dwn+"|";
 		}
 		if(obj.genAuthority==false && obj.dwnAuthority==false){
 			param["deleteReportCode"]+=obj.reportCode+"|";
 			
 		}	
 	}
 	setReportAuthority.send(param);	
 	}
 }
 
 /**
 * This method runs on the success of Assiging Report Authority 
 */  
 public function onSetReportAuthoritySuccess(event:ResultEvent):void{
 	 		if(event.result.sessionConfirm == true){
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
	}
 	var result:XML=event.result as XML;
 	if(result.Details.list_values=="success"){
 		Alert.show(commonFunction.getMessages('authorityUpdated'),
	commonFunction.getMessages('alert'),4,this,onAssignedAuth,successIcon);
 }
 else{
 	Alert.show(result.Details.list_values,commonFunction.getMessages('error'),4,this,onAssignedAuth,errorIcon);
 }
 }

 /**
 * This method refresh the Grid After Clicking on the ok button of the Alert 
 */  
public function onAssignedAuth(event:CloseEvent):void{
	reportDetails.send(param);
	
}
