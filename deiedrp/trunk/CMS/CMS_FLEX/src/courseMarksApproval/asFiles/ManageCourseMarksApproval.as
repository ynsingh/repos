/**
 * @(#) ManageCourseMarksApprovalAction.as
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
 
import common.Mask;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Bindable]public var urlPrefix:String;
[Bindable]var ApprovalDataXml:XML;
[Bindable]var entityXml:XML;
[Bindable]var entityCol:ArrayCollection;
[Bindable]var ApprovalDataCol:ArrayCollection;
[Bindable]var selectedData:ArrayCollection;
public var flag:Boolean=false;

[Embed(source="/images/error.png")] private var errorIcon:Class;
[Embed(source="/images/success.png")] private var successIcon:Class;
[Embed(source="/images/reset.png")] private var resetIcon:Class;
[Embed(source="/images/question.png")] private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")] private var infoIcon:Class;


/** on Creation Complete Event
 *This Method initialize url and send request for getting approved record
**/
public function init():void
{
	urlPrefix=commonFunction.getConstants('url')+"/marksApproval/";
	getEntityList.send(new Date());
	Mask.show(commonFunction.getMessages('pleaseWait'));
}

/** Failure Handler **/
public function onFailure(event:FaultEvent):void{
	Mask.close();
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
}

/** Success handler of getApprovedRecords service **/
public function httpGetApprovedSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'));
			var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
		}
		
		ApprovalDataXml=event.result as XML;
		ApprovalDataCol=new ArrayCollection;
		var approver:String="";			
		for each(var obj:Object in ApprovalDataXml.approvalInfo){
			var sequenceNo:String=obj.sequenceNo;
			switch(obj.sequenceNo.toString()){
				case "1":{
					approver="Primary";
					break;
				}
				case "2":{
					approver="Secondary";
					break;
				}
				case "3":{
					approver="Tertiary";
					break;
				}
				case "4":{
					approver="Other";
					break;
				}
				default :{
					approver="";
					sequenceNo="null";
					break;
				}
			}
			if(entityCombo.selectedLabel=='All'){
	   			ApprovalDataCol.addItem({select:false,entityCode:obj.entityCode,entityName:obj.entityName,
	   			programCourseKey:obj.programCourseKey,courseCode:obj.courseCode,employeeCode:obj.employeeCode,approvalOrder:obj.approvalOrder,seqNo:sequenceNo,sequence:approver,
	   			programName:obj.programCode,branchName:obj.branchCode,spclName:obj.specializationCode,semesterName:obj.semesterCode,evaluationId:obj.evaluationId,displayType:obj.displayType});
	 		 }
	 		 else{
	 		 	if(obj.entityName.toString()==entityCombo.selectedLabel.toString()){
	 		 		ApprovalDataCol.addItem({select:false,entityCode:obj.entityCode,entityName:obj.entityName,
	   				programCourseKey:obj.programCourseKey,courseCode:obj.courseCode,employeeCode:obj.employeeCode,approvalOrder:obj.approvalOrder,seqNo:sequenceNo,sequence:approver,
	   				programName:obj.programCode,branchName:obj.branchCode,spclName:obj.specializationCode,semesterName:obj.semesterCode,evaluationId:obj.evaluationId,displayType:obj.displayType});
	 		 	}
	 		 }
   		}
   		approvalGrid.dataProvider=ApprovalDataCol;
	}
 	catch(e:Error){}
}

/** getEntityList service success handler
 * method to set data provider entity Combo
 **/
protected function httpEntitySuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	    	Alert.show(resourceManager.getString('Messages','sessionInactive'));
			var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    entityXml=event.result as XML;
    entityCol=new ArrayCollection;
   entityCol.addItem({entityCode:"All",entityName:"All"});
   for each(var o:Object in entityXml.Details){
   	entityCol.addItem({entityCode:o.entityCode,entityName:o.entityName});
   }
    entityCombo.dataProvider=entityCol;  
}

/** On Change of entity combo 
 * This method set dataprovider of grid according to selected entity
**/
public function onEntityChange():void{
	var obj:Object={};
	obj["entity"]=entityCombo.selectedItem.entityCode;
	obj["date"]=new Date();
	getApprovedRecords.send(obj);
   Mask.show(commonFunction.getMessages('pleaseWait'));
}

/** Click on Delete link
 * This method ask confirmation for delete selected records
**/
public function onDelete():void{
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteOrNot,questionIcon);
}

/** on click of delete confirmation **/
public function deleteOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		selectedData=new ArrayCollection;
		selectedData=commonFunction.getSelectedRowData(approvalGrid);
		var param:Object={};        
		param['programCourseKey']="";
		param['courseCode']="";
		param['approvalOrder']="";
		param['employee']="";
		param['entity']="";
		param['sequenceNo']="";
		param['display']="";
		for each(var obj:Object in selectedData){
			param['programCourseKey']+=obj.programCourseKey+"|";
			param['courseCode']+=obj.courseCode+"|";
			param['approvalOrder']+=obj.approvalOrder+"|";
			param['employee']+=obj.employeeCode+"|";
			param['entity']+=obj.entityCode+"|";
			param['sequenceNo']+=obj.seqNo+"|";	
			param['display']+=obj.evaluationId+"|";				
		}		
		param["time"]=new Date();
		deleteApprovalDetails.send(param);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

/** delete approval details succes handler **/
public function deleteApprovalDetailsSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'));
			var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    
    if(int(event.result.count)>0)
	{
		Alert.show(event.result.count+" "+commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
	}
	approvalGrid.dataProvider=null;
	approvalGrid.dataProvider.refresh();
	deleteLink.enabled=false;
	entityCombo.selectedIndex=-1;
	flag=false;
}
