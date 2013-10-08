//<!--* @Date :26/11/2012
//* Version 1.0
//    Author :Upasana Kulshrestha 
//* Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
//* All Rights Reserved.
//*
//* Redistribution and use in source and binary forms, with or
//* without modification, are permitted provided that the following
//* conditions are met:
//*
//* Redistributions of source code must retain the above copyright
//* notice, this list of conditions and the following disclaimer.
//*
//* Redistribution in binary form must reproducuce the above copyright
//* notice, this list of conditions and the following disclaimer in
//* the documentation and/or other materials provided with the
//* distribution.
//*
//*
//* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
//* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
//* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
//* DISCLAIMED. IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
//* FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
//* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
//* OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
//* BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
//* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
//* OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
//* EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//*
//* Contributors: Members of EdRP, Dayalbagh Educational Institute
//*/
//// -->


import common.Mask;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

public var url_DNS:String ="";
[Bindable] public var xmldata_AdminInstituteDetail:XML;
[Bindable] public var urlGetInstituteAdminDetail:String = "";
[Bindable] public var urlUpdateInstituteAdminDetail:String = "";
[Bindable] public var urlInsertIntoUniversityUser:String = "";
[Bindable] private var instituteAdminGridData:ArrayCollection = new ArrayCollection();

public var successDetails: XML;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

public var parmInst:Object = new Object ;

public function initialize_form():void{
	url_DNS = commonFunction.getConstants('url');
	urlGetInstituteAdminDetail = url_DNS+"/registrationForInstAndAdmin/getInstituteAdminDetail.htm";
	urlUpdateInstituteAdminDetail=url_DNS+"/registrationForInstAndAdmin/updateStatusInstitute.htm"
	urlInsertIntoUniversityUser=url_DNS+"/registrationForInstAndAdmin/insertIntoUniversityUser.htm"
	getInstAdminDetail();
}	

public function getInstAdminDetail():void{
	var params:Object = {};
    params["time"] =new Date();//does not use of params with this send(), it is only used for affect the page contents according dbase
    xmlAdminInstituteList.send(params);
    Mask.show(commonFunction.getMessages('pleaseWait'));
}

protected function resultInstituteAdminDetail(event:ResultEvent):void{
	xmldata_AdminInstituteDetail=event.result as XML;
		if(parmInst["buttonClicked"]=="DEL"){
			Alert.show("Record Deleted successfully");
		}
		else if(parmInst["buttonClicked"]=="REJ"){
			Alert.show("Record Rejected successfully");
		}	
		Mask.close();
 		if(xmldata_AdminInstituteDetail.sessionConfirm == true){
			Alert.show(resourceManager.getString('Messages','sessionInactive'));
			var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
		}
   		instituteAdminGridData.removeAll();
 		for each(var s:Object in xmldata_AdminInstituteDetail.details){
 			var apr:Boolean=false;
		 	var rej:Boolean=false;
		 	var del:Boolean=false;
		 	var statusDetails:String;
		 	
		 	if(String(s.requestStatus)=="PND"){
		 		statusDetails="Pending";
          		apr=true;
          		rej=true;
          	}
          	if(String(s.requestStatus)=="EXP"){
          		statusDetails="Expired";
          		del=true;
          	}
          	if(String(s.requestStatus)=="REJ"){
          		statusDetails="Rejected";
          		del=true;
          	}
          	if(String(s.requestStatus)=="APR"){
          		statusDetails="Approved";
          	}
          	if(String(s.requestStatus)=="WTG"){
          		statusDetails="Waiting";
          	}
          	
        	instituteAdminGridData.addItem({instituteName:s.instituteName,address:s.address,
          	city:s.city,state:s.state,statusDetails:statusDetails,
          	country:s.country,name:s.name,adminEmail:s.adminEmail,requestStatus:s.requestStatus,instituteNickName:s.instituteNickName,
          	apr:apr,rej:rej,del:del
          	});
          	
        }
        requestgrid.dataProvider=instituteAdminGridData;
}
public  function processRequest(event:Event):void{
	
		parmInst["instituteName"]=requestgrid.selectedItem.instituteName;
		parmInst["address"]=requestgrid.selectedItem.address; 
		parmInst["city"]=requestgrid.selectedItem.city;
		parmInst["state"]=requestgrid.selectedItem.state;
		parmInst["country"]=requestgrid.selectedItem.country;
		parmInst["name"]=requestgrid.selectedItem.name;
		parmInst["adminEmail"]=requestgrid.selectedItem.adminEmail;
		parmInst["instituteNickName"]=requestgrid.selectedItem.instituteNickName;
		
		if (event.currentTarget.id == "approved"){
			Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onConfirm,questionIcon);
		}
		if(event.currentTarget.id == "reject"){
			parmInst["requestStatus"]="REJ";
			parmInst["buttonClicked"]="REJ";
			Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onConfirmRequest,questionIcon);
				
		}
		if(event.currentTarget.id == "delete1"){
			parmInst["requestStatus"]="DEL";
			parmInst["buttonClicked"]="DEL";
			Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onConfirmRequest,questionIcon);
			//updateStatusInstitute.send(parmInst);
		}
	
}
public function faultInstituteAdminDetail(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
}

public function resultIntoUniversityUser(event:ResultEvent):void{
	successDetails=event.result as XML;
	Mask.close();
	if(successDetails.Details.list_values=="duplicateunivesity"){
		
		Alert.show(commonFunction.getMessages('duplicateInstitute'),
               commonFunction.getMessages('failure'),0,null,null,errorIcon); 
		
	}else if(String(successDetails.Details.list_values).search("false")>-1){
               
        Alert.show(commonFunction.getMessages('errorInsert')+"\n"+String(successDetails.Details.list_values),
               commonFunction.getMessages('error'),0,null,null,errorIcon);                        
 	}     
	else if(successDetails.Details.list_values=="duplicateuserinfo"){
	   
	   	Alert.show("Admin details already exists ",
	           commonFunction.getMessages('failure'),0,null,null,errorIcon);  
	   
	}
	else{
	    Alert.show("Institute Created successfully.",
	           (commonFunction.getMessages('saved')),0,null,OnSuccess,successIcon);
	}
}

public function OnSuccess(event:CloseEvent):void{
	var params:Object = {};
	params["time"] =new Date();//does not use of params with this send(), it is only used for affect the page contents according dbase
    xmlAdminInstituteList.send(params);
    Mask.show(commonFunction.getMessages('pleaseWait'));
}

public function onConfirm(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		parmInst["requestStatus"]="APR";
		insertIntoUniversityUser.send(parmInst);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

public function onConfirmRequest(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		updateStatusInstitute.send(parmInst);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}
public function Cancel():void{
	this.parentDocument.loaderCanvas.removeAllChildren();
}
