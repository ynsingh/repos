/**
 * @(#) RemarkAs.as
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
 import common.commonFunction;
 
 import mx.collections.ArrayCollection;
 import mx.controls.Alert;
 import mx.controls.DataGrid;
 import mx.events.CloseEvent;
 import mx.rpc.events.FaultEvent;
 import mx.rpc.events.ResultEvent;
 


var remarkArrCol:ArrayCollection;
var semesterXml:XML=new XML;
var semesterArrCol:ArrayCollection;

[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
 
 
 /**
  * Method to set data provider for Remark Combo on Creation Complete
 **/
 public function init():void{
 	remarkArrCol=new ArrayCollection;
 	for each(var obj:Object in remarkXml.remark){
 		remarkArrCol.addItem({rmName:obj.@name,rmDesc:obj});
 	}
 	remarkCB.dataProvider=remarkArrCol;
 }
 
 /**
  * Method to Change on Remark Combo Box
  **/
 public function OnRemarkCBChange():void{
 	remarkText.text=remarkCB.selectedItem.rmDesc;
 }
 
 /**
  * Method to click on Reset Button to Reset the Fields
  **/
 public function onClickReset():void{
 	rollnumberInput.text="";
 	remarkCB.selectedIndex=-1;
 	remarkText.text="";
 	entityName.text="";
 	programName.text="";
 	semesterArrCol.removeAll();
 	semesterGrid.dataProvider.referesh();
 	saveLink.enabled=false;
 	semesteMainCanvas.visible=false;
 	resetMangeGrid();
 }
 
 /**
  * Method to click on Ok Button to get the Student Semester Details
  **/
 public function onClickOk():void{
 	if(rollnumberInput.text==""){ 		
 		Alert.show(commonFunction.getMessages('enterRollNumber'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 		entityName.text="";
	 	programName.text="";
	 	semesterArrCol.removeAll();
	 	semesterGrid.dataProvider.referesh();
	 	saveLink.enabled=false;
	 	semesteMainCanvas.visible=false;
	 	resetMangeGrid(); 		
 	}
 	else if(remarkCB.selectedIndex==-1){ 		
 		Alert.show(commonFunction.getMessages('selectRemark'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 		entityName.text="";
	 	programName.text="";
	 	semesterArrCol.removeAll();
	 	semesterGrid.dataProvider.referesh();
	 	saveLink.enabled=false;
	 	semesteMainCanvas.visible=false; 	
	 	resetMangeGrid();	
 	}
 	else{
 		var obj:Object={};
 		obj['date']=new Date();
 		obj['rollNumber']=rollnumberInput.text;
 		getSemesterDeatil.send(obj); 		
 	}
 }
 
 /**
  * Failure Handler for Http services
  **/
 	public function faultHandler(event:FaultEvent):void{
 		Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
 	}
 	
 /**
  * Result Handler for getSemesterDeatil Service
  **/
 	public function resultHandlerSemesterDeatil(event:ResultEvent):void{
 		semesterXml=event.result as XML;
 		 if(semesterXml.sessionConfirm == true){
    		Alert.show(resourceManager.getString('Messages','sessionInactive'));
			var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
        }					 		
 		semesterArrCol=new ArrayCollection();
 		for each(var o:Object in semesterXml.semester){
 			semesterArrCol.addItem({select:false,semCod:o.semesterCode,semesterName:o.semesterName,semesterStartDate:o.semesterStartDate,
 			semesterEndDate:o.semesterEndDate,programName:o.programName,entityName:o.entityName,programCourseKey:o.programCourseKey});
 		}
 		if(semesterArrCol.length==0){
 			semesteMainCanvas.visible=false;
 			Alert.show(commonFunction.getMessages('remarkSemesterDetailNotFound'),commonFunction.getMessages('info'),4,null,null,infoIcon);
 			
 		}
 		else{
 			semesteMainCanvas.visible=true;
 			entityName.text=semesterArrCol.getItemAt(0).entityName;
 			programName.text=semesterArrCol.getItemAt(0).programName;
 			semesterGrid.dataProvider=semesterArrCol;
 		}
 	}
 	
 /**
  * Method to click on CAncel Button
  **/
 public function onClickCancel():void{
 	this.parent.removeChild(this);
 }
 
 /**
  * Method to Select Record on Semester Detail Grid
  **/
 public function onCheckBoxClick():void{
 	var selectedData:ArrayCollection=new ArrayCollection;
 	selectedData=commonFunction.getSelectedRowData(semesterGrid);
 	var param:Object={};
 	param['date']=new Date();
 	param['programCourseKey']=selectedData.getItemAt(0).programCourseKey;
 	param['semesterStartDate']=selectedData.getItemAt(0).semesterStartDate;
 	param['semesterEndDate']=selectedData.getItemAt(0).semesterEndDate; 	
 	param['rollNumber']=rollnumberInput.text;
 	getManageRemarkData.send(param);
 }
 
 /**
  * Result Handler for getSemesterDeatil Service
 **/
 	public function resultHandlerRemarkDetail(event:ResultEvent):void{
 		var remarkXml:XML=event.result as XML; 		
 		 if(remarkXml.sessionConfirm == true){
    		Alert.show(resourceManager.getString('Messages','sessionInactive'));
			var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
        }					 		
 		var remarkDataArrCol:ArrayCollection=new ArrayCollection();
 		for each(var o:Object in remarkXml.semester){
 			remarkDataArrCol.addItem({select:false,rollNumber:rollnumberInput.text,semesterStartDate:o.semesterStartDate,semesterEndDate:o.semesterEndDate
 			,programCourseKey:o.programCourseKey,programName:o.programName,branchName:o.branchName,spclName:o.specialization,semesterName:o.semesterName,remark:o.remark}); 			
 		}
 		mangeGridCanvas.visible=true;
 		manageRemarkLabel.visible=true;
 		manageGrid.dataProvider=remarkDataArrCol; 		
 	}

/** Method to Reset Manate Grid AND set visible off for mangeGridCanvas **/
 public function resetMangeGrid():void{
 	manageGrid.dataProvider=null;
 	manageGrid.dataProvider.refresh();
 	mangeGridCanvas.visible=false; 	
 	manageRemarkLabel.visible=false;
 }
 
 /** Method to click on Save Remark Link Button **/
 public function onClickSaveRemark():void{
 	Alert.show(commonFunction.getMessages('doYouWantContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,saveConfirm,questionIcon);
 }
 
 public function saveConfirm(event:CloseEvent){
 	if(event.detail==Alert.YES){
 		var param:Object={};
 		var data:ArrayCollection=new ArrayCollection;
 		data=commonFunction.getSelectedRowData(semesterGrid);
 		var param:Object={};
	 	param['date']=new Date();
	 	param['programCourseKey']=data.getItemAt(0).programCourseKey;
	 	param['semesterStartDate']=data.getItemAt(0).semesterStartDate;
	 	param['semesterEndDate']=data.getItemAt(0).semesterEndDate; 	
	 	param['rollNumber']=rollnumberInput.text;
	 	param['remark']=remarkText.text;
	 	saveRemarkData.send(param);
 	}
 }
 
 /**
  * Result Handler for getSemesterDeatil Service
 **/
 public function resultHandlerSaveRemarkDetail(event:ResultEvent):void{
 	var xml:XML=event.result as XML; 		
 	 if(xml.sessionConfirm == true){
		Alert.show(resourceManager.getString('Messages','sessionInactive'));
		var url:String="";
		url=commonFunction.getConstants('navigateHome');
		var urlRequest:URLRequest=new URLRequest(url);
		urlRequest.method=URLRequestMethod.POST;
		navigateToURL(urlRequest,"_self");
    }
    if(xml.resultdata=="success"){
    	Alert.show(commonFunction.getMessages('remarkSaveSuccess'),commonFunction.getMessages('success'),null,null,null,successIcon);
    	onCheckBoxClick();
    }	
    else{
    	Alert.show(commonFunction.getMessages('remarkSaveError'),commonFunction.getMessages('error'),null,null,null,errorIcon);
    	resetGrid(semesterGrid);
    	resetMangeGrid();
    }				 		 		
 }
 
 /**
  *Method to Reset Grid 
 **/
 public function resetGrid(grid:DataGrid){
 	for each(var o:Object in grid.dataProvider){
 		o.select=false;
 	}
 	grid.dataProvider.refresh();
 }
