/**
 * @(#) PrintReports.as
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
import common.commonFunction;

import flash.events.Event;
import flash.net.URLRequest;
import flash.net.navigateToURL;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.events.CloseEvent;
import mx.events.ListEvent;
import mx.managers.FocusManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
		
public var object:Object = new Object;
public var sessionList:ArrayCollection;
public var sessionXML:XML;
public var reportType:String;
public var reportCode:String;
public var reportDescription:String;
public var downloadUrl:URLRequest = new URLRequest();		
public var fileRequest:FileReference = new FileReference();
public var combinationList:ArrayCollection=new ArrayCollection();
public var entityXML:XML;
public var entityList:ArrayCollection = new ArrayCollection();	;
public var reportXML:XML;
public var reportList:ArrayCollection;
public var reportList1:ArrayCollection;
public var combinationXML:XML;		
public var columns:Array;
public var generateAuthority:String=null;
public var downloadAuthority:String=null;
public var generateAuthorityGen:String=null;
public var downloadAuthorityGen:String=null;		
[Bindable]public var genToolTip:String=null;	
[Bindable]public var dwnToolTip:String=null;	
[Bindable]public var printToolTip:String=null;
[Bindable]public var genBoolean:Boolean=false;
[Bindable]public var dwnBoolean:Boolean=false;
public var toolTipAction:String;
public function onCreationComplete():void{				
	object["date"] = new Date;									
	getUniversitySessions.send(object);
	Mask.show();								
}

/**
 * The method retrieves the list of university sessions
 * from the database
 **/ 
public function getUniversitySessionList(event:ResultEvent):void{
	Mask.close();
	sessionXML = event.result as XML;
		
	if(sessionXML.sessionConfirm == true){
       	Alert.show(resourceManager.getString('Messages','sessionInactive'));
      	var url:String="";
 		url=commonFunction.getConstants('navigateHome');
 		var urlRequest:URLRequest=new URLRequest(url);
 		urlRequest.method=URLRequestMethod.POST;
 		navigateToURL(urlRequest,"_self");
    }	
	sessionList = new ArrayCollection();	
	for each(var obj:Object in sessionXML.role){		
		var sessionStartDate:String = obj.id.toString();
		var year:String = sessionStartDate.substr(0,4);		
		var sessionEndDate:String = obj.description.toString();
		var endYear:String = sessionEndDate.substr(0,4);		
		sessionList.addItem({select:false,id:year,description:endYear});		
	}	
	fromYear.dataProvider = sessionList;
	fromYear.labelField = "id";	
}

/**
 * The method is executed on failure of 
 * a request
 **/ 	
public function onFailure(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('reportGenerated'),
	commonFunction.getMessages('success'),0,null,null,successIcon);
	Mask.close();	
}
		
/**
 * This method is executed on success of report generation
 * request
 **/ 	
public function onSuccess(event:ResultEvent):void{
	try{
		Mask.close();
		if(event.result.sessionConfirm == true){
 			Alert.show(resourceManager.getString('Messages','sessionInactive'));
	      	var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
		}
	}catch(e:Error){}
 
	 var status:String=(event.result.message+"");
	 if(status.substr(0,4)=="true"){
	 	if(status.length>4){
	 		Alert.show(status.substr(5,status.length-5),commonFunction.getMessages('success'),0,null,null,successIcon);
	 	}
	 	else{
			Alert.show(commonFunction.getMessages('reportGenerated'),commonFunction.getMessages('success'),0,null,null,successIcon);
	 	}
	}
	else{
		if(status.length>5){
	 		Alert.show(status.substr(6,status.length-6),commonFunction.getMessages('error'),0,null,null,errorIcon);
	 	}
	 	else{
			Alert.show(commonFunction.getMessages('failToGenerate'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	 	}		       
	 }		
}
		
/**
 * The method sets the year of to session
 * with respect to the session selected from the from session
 * dropdown
 **/ 
public function onYearChange():void{
	for each(var obj:Object in sessionList){		
		if(fromYear.selectedLabel == obj.id){			
			toYear.text = obj.description;			
		}		
	}
	toYear.visible = true;		
	object["fromSession"] = sessionXML.role.(id.toString().substr(0,4)==fromYear.selectedLabel).id;
	object["toSession"] = sessionXML.role.(description.toString().substr(0,4)==toYear.text).description;		
	/*
	 * request to get the reports-details on change of the session
	 */				 	
 	getReportsStatus.send(object);				 
}

/**
 * The method is executed on success of the report details 
 * request
 **/ 		
protected function onSuccessReportDetails(event:ResultEvent):void{
	if(event.result.sessionConfirm == true){
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
	}
	
	reportXML = new XML();
	reportXML = event.result as XML;	
	reportList = new ArrayCollection();
	reportList1 = new ArrayCollection();
				
	for each(var obj:Object in reportXML.report){		
//		var limit:int=obj.reportCode;
//		if(limit<=16){
			reportList.addItem({select:false,reportCode:obj.reportCode,reportDescription:obj.reportCode+"-"+obj.reportDescription
							,reportType:obj.reportTypeCode,reportTypeDescription:obj.reportTypeDescription
							,generateAuthority:obj.generateAuthority,downloadAuthority:obj.downloadAuthority});
//		}
//		//Report Name in Next Tile List
//		else{
//			nextButton.enabled=true;
//			reportList1.addItem({select:false,reportCode:obj.reportCode,reportDescription:obj.reportCode+"-"+obj.reportDescription
//							,reportType:obj.reportTypeCode,reportTypeDescription:obj.reportTypeDescription
//							,generateAuthority:obj.generateAuthority,downloadAuthority:obj.downloadAuthority});	
//		}				
	}							
	reportTileList.dataProvider = reportList;
	reportTileList2.dataProvider=reportList1;
	rbGroup.selectedValue = null;		
	canvasId.visible = true;
	gridCanvasId.visible = false;
	entityComboBox.visible = false;
	backButton2.visible=false;
	entityLabel.visible = false;
	reportLabel.visible = false;
	reportLabelId.visible = false;
	backButton.visible = false;			
}	
 /**
 * The method is executed on click of report radio button 
 **/     
private function showAlert(event:ListEvent):void{  
 	try{  
 		this.verticalScrollPosition=0;
	  	gridCanvasId.visible = false;
		entityComboBox.visible = false;
		entityLabel.visible = false;
		backButton2.visible=false;
	  	reportType = event.currentTarget.selectedItem.reportType;
	  	reportCode = event.currentTarget.selectedItem.reportCode;
	  	reportDescription = event.currentTarget.selectedItem.reportDescription;
	  	reportLabelId.text = reportDescription;
	  	object["reportType"] = reportType;
		object["reportCode"] = reportCode;
		object["reportDescription"] = reportDescription;
		generateAuthority=event.currentTarget.selectedItem.generateAuthority;
      	downloadAuthority=event.currentTarget.selectedItem.downloadAuthority;

      	/*
      	* Enabling and Disabling generate,download and print button according to the authority and changing tool tip
      	*/
      	
      		if(generateAuthority=="Y"){
      			genBoolean=true;
      			genToolTip=commonFunction.getConstants('generateReport');
      		}
      		else{
      			genToolTip=commonFunction.getConstants('canNotGenerate');
      		}
      		if(downloadAuthority=="Y"){
      			dwnBoolean=true;
      			dwnToolTip=commonFunction.getConstants('downloadreport');
      			printToolTip=commonFunction.getConstants('printReport');
      		}
      		else{
      			dwnToolTip=commonFunction.getConstants('canNotDownload');
      			printToolTip=commonFunction.getConstants('canNotPrint');
      		}
      		if(reportType=="RGN"){
      			generalReportsList.send(new Date);
      		
      		}
      		if(reportType=="RVR"){
      			getReportDetailsWithoutEntity.send(object);
      		}
	  	/*
	  	 *to get the list of entities offered by the university
	  	 */
  		getEntities.send(object);  	
		//Alert.show(event.currentTarget.selectedItem.reportType+" is the button Id");      					
  	}catch(e:Error){      		
      		Alert.show(e.message);      		
  	}		
}
/*
* This method is gets all the General Type Reports
*/
public function onGeneralSuccess(event:ResultEvent):void{	
	var generalXml:XML=event.result as XML;
		if(generalXml.sessionConfirm == true){
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
	}	
	else{
		getCombinationsList(event);
			for each(var obj:Object in generalXml.report){	
		
			generateAuthorityGen=obj.generateAuthority;
			downloadAuthorityGen=obj.downloadAuthority;
			
    var genToolTip:String="";
    var dwnToolTip:String="";
    var printToolTip:String="";
    var genBoolean:Boolean=false;
    var dwnBoolean:Boolean=false;
   	var bool:Boolean=false;
	var bool1:Boolean=false;

			if(generateAuthorityGen=="Y"){
      			genBoolean=true;
      			bool=true;
      			genToolTip=commonFunction.getConstants('generateReport');
      		}
      		else{
      			bool=false;
      			genToolTip=commonFunction.getConstants('canNotGenerate');
      		}
      		if(downloadAuthorityGen=="Y"){
      			dwnBoolean=true;
      			bool1=true;
      			dwnToolTip=commonFunction.getConstants('downloadreport');
      			printToolTip=commonFunction.getConstants('printReport');
      		}
      		else{
      			bool1=false;
      			dwnToolTip=commonFunction.getConstants('canNotDownload');
      			printToolTip=commonFunction.getConstants('canNotPrint');
      		}
      		
      		combinationList.addItem({select:false,reportCode:obj.reportCode,reportName:obj.reportDescription,reportType:obj.reportTypeCode,generateAuthorityGen:obj.generateAuthority
		,downloadAuthorityGen:obj.downloadAuthority,genToolTip:genToolTip,printToolTip:printToolTip,dwnToolTip:dwnToolTip,genBoolean:genBoolean,dwnBoolean:dwnBoolean});	
   }
   reportsDetails.dataProvider = combinationList;
	}
}
				
 /**
  * The method retrieves the list of entities
  * from the database
 **/ 
public function getEntityList(event:ResultEvent):void{		
	Mask.close();
	entityXML = event.result as XML;		
	if(entityXML.sessionConfirm == true){
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
	}	
					
	/*
	* In the case of REN no entity combo will come
	*/					
	if(reportType=="REN"){	
		getCombinationsList(event);
		for each(var obj:Object in entityXML.entity){														
			combinationList.addItem({select:false,id:obj.entityId,description:obj.entityName});
			entityList.addItem({select:false,id:obj.entityId,description:obj.entityName,entityCode:obj.entityCode});
		}													
		reportsDetails.dataProvider = combinationList;							
		gridCanvasId.visible = true;
		backButton.visible = true;																					
	}
	/*
	 * Report Semester-Session Wise :No entity combo will come
	 */ 
	else if(reportType=="RSS"){	
		getCombinationsList(event);	
		combinationList.addItemAt({semesterName:"Even Semesters"},0);
		combinationList.addItemAt({semesterName:"Odd Semesters"},1);
		reportsDetails.dataProvider = combinationList;					
		gridCanvasId.visible = true;
		backButton.visible = true;
	}
	else if(reportType=="RGN"||reportType=="RVR"){
		gridCanvasId.visible = true;
		backButton.visible = true;
	}	
	else{
		for each(var obj1:Object in entityXML.entity){	
			entityList.addItem({select:false,id:obj1.entityId,description:obj1.entityName,entityCode:obj1.entityCode});	
		}
		entityLabel.visible = true;
		entityComboBox.visible = true;
		backButton2.visible=true;	
	}	
																																								
	entityComboBox.dataProvider = entityList;
	entityComboBox.labelField="description";								
	canvasId.visible = false;
	reportLabel.visible = true;
	reportLabelId.visible = true;	
}

/**
  * This method fires on entity change
 **/		
protected function onEntityChange():void{	
	backButton2.visible=false;
	object["entityId"] = entityComboBox.selectedItem.id;
	object["entityCode"] = entityComboBox.selectedItem.entityCode;
	object["reportType"] = reportType;
	object["reportCode"] = reportCode;
	object["reportDescription"] = reportDescription;		
	/*
	 * get the combinations for the selected entity for the session
	 */
	getCombinations.send(object);	
}


//ADDed By Mandeep Sodhi for Verification OF Report
public function reportDetailsWithoutEntitySuccess(event:ResultEvent):void{
		combinationXML = event.result as XML;		
	combinationList = new ArrayCollection();		

				getCombinationsList(event);				
		for each(var obj:Object in combinationXML.report){
			if(reportType=="RVR"){

		combinationList.addItem({select:false,companyName:obj.companyName,reqDate:obj.reqDate,reportGenerated:obj.reportGenerated,
								printStatus:obj.printStatus});
			}

		}
		reportsDetails.dataProvider = combinationList;					
		gridCanvasId.visible = true;
		backButton.visible = true;

}
/**
  * This method calls on the success of the 
  * combination list request
 **/				
public function getCombinationsList(event:ResultEvent):void{
	if(event.result.sessionConfirm == true){
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
	}
	//Alert.show(event.result as XML);
	combinationXML = event.result as XML;		
	combinationList = new ArrayCollection();
	columns = new Array();
	
	//			Alert.show(combinationXML+"combination");
	var entityNameColumn:DataGridColumn = new DataGridColumn();
	var programNameColumn:DataGridColumn = new DataGridColumn();
	var branchNameColumn:DataGridColumn = new DataGridColumn();
	var specializationColumn:DataGridColumn = new DataGridColumn();
	var semesterColumn:DataGridColumn = new DataGridColumn();
	var semesterStartDateColumn:DataGridColumn = new DataGridColumn();
	var semesterEndDateColumn:DataGridColumn = new DataGridColumn();
	var reportNameColumn:DataGridColumn=new DataGridColumn();
	var reportCodeColumn:DataGridColumn=new DataGridColumn();
	//Added By Mandeep
	var companyNameColumn:DataGridColumn=new DataGridColumn();
	var requestedDateColumn:DataGridColumn=new DataGridColumn();
	
	//**************************											
	entityNameColumn.editable = false;
	entityNameColumn.headerText = commonFunction.getConstants('entityName');
	entityNameColumn.width = 250;
	entityNameColumn.dataField = 'description';
	entityNameColumn.draggable = false;
	entityNameColumn.setStyle("textAlign","center");
	entityNameColumn.setStyle("color","#000000");	
	
	programNameColumn.editable = false;
	programNameColumn.headerText = commonFunction.getConstants('programName');
	programNameColumn.width = 200;
	programNameColumn.dataField = 'programName';
	programNameColumn.draggable = false;
	programNameColumn.setStyle("textAlign","center");
	programNameColumn.setStyle("color","#000000");
	
	branchNameColumn.editable = false;
	branchNameColumn.headerText = commonFunction.getConstants('branchName');
	branchNameColumn.width = 150;
	branchNameColumn.dataField = 'branchName';
	branchNameColumn.draggable = false;
	branchNameColumn.setStyle("textAlign","center");
	branchNameColumn.setStyle("color","#000000");
			
	specializationColumn.editable = false;
	specializationColumn.headerText = commonFunction.getConstants('specializationName');
	specializationColumn.width = 150;
	specializationColumn.dataField = 'specializationName';
	specializationColumn.draggable = false;
	specializationColumn.setStyle("textAlign","center");
	specializationColumn.setStyle("color","#000000");
	
	semesterColumn.editable = false;
	semesterColumn.headerText = commonFunction.getConstants('semesterName');
	semesterColumn.width = 110;
	semesterColumn.dataField = 'semesterName';
	semesterColumn.draggable = false;
	semesterColumn.setStyle("textAlign","center");
	semesterColumn.setStyle("color","#000000");
			
	semesterStartDateColumn.editable = false;
	semesterStartDateColumn.headerText = commonFunction.getConstants('semesterStartDate');
	semesterStartDateColumn.width = 130;
	semesterStartDateColumn.dataField = 'semesterStartDate';
	semesterStartDateColumn.draggable = false;
	semesterStartDateColumn.setStyle("textAlign","center");
	semesterStartDateColumn.setStyle("color","#000000");
			
	semesterEndDateColumn.editable = false;
	semesterEndDateColumn.headerText = commonFunction.getConstants('semesterEndDate');
	semesterEndDateColumn.width = 130;
	semesterEndDateColumn.dataField = 'semesterEndDate';
	semesterEndDateColumn.draggable = false;
	semesterEndDateColumn.setStyle("textAlign","center");
	semesterEndDateColumn.setStyle("color","#000000");
	
    reportNameColumn.editable = false;
	reportNameColumn.headerText = commonFunction.getConstants('reportName');
	reportNameColumn.width = 130;
	reportNameColumn.dataField = 'reportName';
	reportNameColumn.draggable = false;
	reportNameColumn.setStyle("textAlign","center");
	reportNameColumn.setStyle("color","#000000");
	
	reportCodeColumn.editable = false;
	reportCodeColumn.headerText = commonFunction.getConstants('reportCode');
	reportCodeColumn.width = 130;
	reportCodeColumn.dataField = 'reportCode';
	reportCodeColumn.draggable = false;
	reportCodeColumn.setStyle("textAlign","center");
	reportCodeColumn.setStyle("color","#000000");
	
	//Added BY Mandeep For Result Verification Report
	companyNameColumn.editable=false;
	companyNameColumn.headerText=commonFunction.getConstants('companyName');
	companyNameColumn.width = 130;
	companyNameColumn.dataField = 'companyName';
	companyNameColumn.draggable = false;
	companyNameColumn.setStyle("textAlign","center");
	companyNameColumn.setStyle("color","#000000");
	
	requestedDateColumn.editable=false;
	requestedDateColumn.headerText=commonFunction.getConstants('requestedDate');
	requestedDateColumn.width = 130;
	requestedDateColumn.dataField = 'reqDate';
	requestedDateColumn.draggable = false;
	requestedDateColumn.setStyle("textAlign","center");
	requestedDateColumn.setStyle("color","#000000");	
	
	
	
	//**************************				
	/*
	 * reports @ program course key wise
	 */
	if(reportType=="RCL"){						
		gridCanvasId.width=1058;				
		columns.push(programNameColumn);
		columns.push(branchNameColumn);
		columns.push(specializationColumn);
		columns.push(semesterColumn);
		columns.push(semesterStartDateColumn);
		columns.push(semesterEndDateColumn);					
	}
			
	/*
	 * report @ program semester type wise
	 */
	if(reportType=="EPS"){		
		gridCanvasId.width=580;					
		columns.push(programNameColumn);
		columns.push(semesterColumn);		
	}
	/*
	 * report @ program wise
	 */
	if(reportType=="REP"){		
		gridCanvasId.width=470;					
		columns.push(programNameColumn);		
	}
	/*
	 * report @ program semester & semester type wise
	 */
	if(reportType=="PSS"){			
		gridCanvasId.width=860;		
		columns.push(programNameColumn);
		columns.push(semesterColumn);
		columns.push(semesterStartDateColumn);
		columns.push(semesterEndDateColumn);
		columns.push(reportNameColumn);			
	}
			
	/*
	 * report @ program branch specialization wise
	 */
	if(reportType=="PBS"){				
		gridCanvasId.width=690;							
		columns.push(programNameColumn);
		columns.push(branchNameColumn);
		columns.push(specializationColumn);				
	}
			
	/*
	 * report @ semester type wise
	 */
	if(reportType=="RES"){				
		gridCanvasId.width=290;												
		columns.push(semesterColumn);				
	}
			
	/*
	 * report @ entity wise
	 */
	if(reportType=="REN"){				
		gridCanvasId.width=500;										
		columns.push(entityNameColumn);	
		columns.push(semesterColumn);			
	}
	
	if(reportType=="RSS"){
		gridCanvasId.width=290;												
		columns.push(semesterColumn);			
	}
	if(reportType=="RGN"){
		gridCanvasId.width=500;	
		columns.push(reportCodeColumn);											
		columns.push(reportNameColumn);	
	}
	if(reportType=="RVR"){						
		gridCanvasId.width=500;					
		columns.push(companyNameColumn);
		columns.push(requestedDateColumn);					
	}		
	columns.push(reportsDetails.columns.pop());				
	reportsDetails.columns = columns;						
						
	for each(var obj:Object in combinationXML.report){
				
		/*
	 	 * reports @ program course key wise
	 	 */
		if(reportType=="RCL"){			
			combinationList.addItem({select:false,programId:obj.programId,programName:obj.programName,programCode:obj.programCode,
								branchId:obj.branchId,branchName:obj.branchName,specializationId:obj.specializationId,
								specializationName:obj.specializationName,semesterCode:obj.semesterCode,
								semesterName:obj.semesterName,semesterStartDate:obj.semesterStartDate,
								semesterEndDate:obj.semesterEndDate,reportGenerated:obj.reportGenerated,
								printStatus:obj.printStatus});			
		}
				
		/*
	 	 * reports @ program semester type wise
	 	 */
		if(reportType=="EPS"){			
			combinationList.addItem({select:false,programId:obj.programId,programName:obj.programName,
				programCode:obj.programCode,semesterName:obj.semesterName,reportGenerated:obj.reportGenerated,
				printStatus:obj.printStatus});			
		}
				
		/*
	 	* report @ program wise
	 	*/
		if(reportType=="REP"){			
			combinationList.addItem({select:false,programId:obj.programId,programName:obj.programName,
				programCode:obj.programCode,semesterName:obj.semesterName,reportGenerated:obj.reportGenerated,
				printStatus:obj.printStatus});			
		}
				
		/*
	 	 * report @ program semester & semester type wise
	 	 */	
	 	 if(reportType=="PSS"){	
 	 		var semesterSequence =obj.semesterSequence;
			var finalSemesterCode:String =obj.finalSemCode;
			var pssReportName:String=null;
			if(int(reportCode)==17){
				pssReportName = "Semester Wise";
			}
			else{
				if(finalSemesterCode == "" || finalSemesterCode == "N"){
					if(semesterSequence % 2 == 0){
						pssReportName= "RESULT CARD";			
					} 
					else if(semesterSequence % 2 != 0){
						pssReportName = "PROGRESS CARD";
					}
				}
				else if(finalSemesterCode == "F"){
					pssReportName= "FINAL RESULT CARD";
				}	
			}
			combinationList.addItem({select:false,programId:obj.programId,programName:obj.programName,programCode:obj.programCode,
								semesterCode:obj.semesterCode,semesterName:obj.semesterName,semesterStartDate:obj.semesterStartDate,
								semesterEndDate:obj.semesterEndDate,reportGenerated:obj.reportGenerated,
								printStatus:obj.printStatus,reportName:pssReportName,semesterSeq:obj.semesterSequence});			
		}
				
		/*
	 	 * report @ program branch specialization wise
	 	 */	
	 	 if(reportType=="PBS"){			
			combinationList.addItem({select:false,programId:obj.programId,programName:obj.programName,programCode:obj.programCode,
								branchId:obj.branchId,branchName:obj.branchName,specializationId:obj.specializationId,
								specializationName:obj.specializationName,reportGenerated:obj.reportGenerated,
								printStatus:obj.printStatus});			
		}		
	} //end for loop of combination xml
			
	/*
	 * report @ semester type wise
	 */	
	if(reportType=="RES"){		
		combinationList.addItemAt({semesterName:"Even Semesters"},0);
		combinationList.addItemAt({semesterName:"Odd Semesters"},1);		
	}
	


								
	reportsDetails.dataProvider = combinationList;
	gridCanvasId.visible = true;
	backButton.visible = true;
	gene.visible = false;
	down.visible = false;
	pri.visible = false;								
}
public function refreshObject():void{
	object["programName"] = null;
	object["programId"] = null;
	object["programCode"] =  null;
	object["branchName"] = null;
	object["branchId"] = null;
	object["specializationName"] = null;
	object["specializationId"] = null;
	object["semesterName"] = null;
	object["semesterCode"] = null;		
	object["semesterStartDate"] = null;
	object["semesterEndDate"] = null;
	object["semesterSequence"] = null;
	object["semesterType"] = null;
	object["printType"] = null;
	object["report"] = null;
	object["pssReportName"]= null;
	object["semesterWise"] = null;
	//Added By Mandeep
	 object["companyName"]=null;
	 object["reqDate"]=null;
}

/**
  * This method calls on the click of any button(generate, download, print)
 **/
 		
public function onButtonClick(event:Event,genReportCode:String):void{
	toolTipAction = event.currentTarget.toolTip;
	if(entityComboBox.selectedIndex!=-1){
		object["entityName"] = entityComboBox.selectedItem.description;
	}					
	object["reportActivity"] = event.currentTarget.toolTip;				
					
 	// reports @ program course key wise	 
	if(reportType=="RCL"){
		refreshObject();						
		object["programName"] = reportsDetails.selectedItem.programName; 
		object["programId"] = reportsDetails.selectedItem.programId;
		object["programCode"] =  reportsDetails.selectedItem.programCode;
		object["branchName"] = reportsDetails.selectedItem.branchName;
		object["branchId"] = reportsDetails.selectedItem.branchId;
		object["specializationName"] = reportsDetails.selectedItem.specializationName;
		object["specializationId"] = reportsDetails.selectedItem.specializationId;
		object["semesterName"] = reportsDetails.selectedItem.semesterName;
		object["semesterCode"] = reportsDetails.selectedItem.semesterCode;			
		object["semesterStartDate"] = reportsDetails.selectedItem.semesterStartDate;
		object["semesterEndDate"] = reportsDetails.selectedItem.semesterEndDate;
		var semesterSeq:String=reportsDetails.selectedItem.semesterCode;
		object["semesterSequence"] =semesterSeq.substr(2,1);	
		//Alert.show(semesterSeq.substr(2,1));													
	}						
	 // reports @ program semester type wise		 
	if(reportType=="EPS"){
		refreshObject();			
		object["programName"] = reportsDetails.selectedItem.programName; 
		object["programId"] = reportsDetails.selectedItem.programId;
		object["programCode"] =  reportsDetails.selectedItem.programCode;
		object["semesterType"] = reportsDetails.selectedItem.semesterName;														
	}
		
	if(reportType=="REP"){	
		refreshObject();		
		object["programName"] = reportsDetails.selectedItem.programName; 
		object["programId"] = reportsDetails.selectedItem.programId;
		object["programCode"] =  reportsDetails.selectedItem.programCode;
		object["printType"] = reportsDetails.selectedItem.semesterName;												
	}
	/*
	 * report @ program semester & semester type wise
	 */	
	if(reportType=="PSS"){	
		refreshObject();
		object["programName"] = reportsDetails.selectedItem.programName; 
		object["programId"] = reportsDetails.selectedItem.programId;
		object["programCode"] =  reportsDetails.selectedItem.programCode;
		object["semesterName"] = reportsDetails.selectedItem.semesterName;
		object["semesterCode"] = reportsDetails.selectedItem.semesterCode;
		object["semesterStartDate"] = reportsDetails.selectedItem.semesterStartDate;
		object["semesterEndDate"] = reportsDetails.selectedItem.semesterEndDate;
		object["report"] = "progress";
		object["semesterSequence"] =reportsDetails.selectedItem.semesterSeq;
		object["pssReportName"]=reportsDetails.selectedItem.reportName;
									
	}
		
	/*
	 * report @ program branch specialization wise
	 */	
	if(reportType=="PBS"){	
		refreshObject();		
		object["programName"] = reportsDetails.selectedItem.programName; 
		object["programId"] = reportsDetails.selectedItem.programId;
		object["programCode"] =  reportsDetails.selectedItem.programCode;		
		object["branchName"] = reportsDetails.selectedItem.branchName;
		object["branchId"] = reportsDetails.selectedItem.branchId;
		object["specializationName"] = reportsDetails.selectedItem.specializationName;
		object["specializationId"] = reportsDetails.selectedItem.specializationId;									
	}
		
	/*
	 * report @ semester type wise
	 */	
	if(reportType=="RES"){	
		refreshObject();		
		object["semesterType"] = reportsDetails.selectedItem.semesterName;			
		/*
		 * add reports here
		 */ 			
	}	
	if(reportType=="REN"){
		refreshObject();	
		object["entityName"] = reportsDetails.selectedItem.description; 
		object["entityId"] = reportsDetails.selectedItem.id;														
	}
	
	/*
	 *report @ semester Session Wise 
	 */ 
	 if(reportType=="RSS"){
	 	refreshObject();
	 	object["semesterWise"] = reportsDetails.selectedIndex;	 		 	
	 }
	 /*
	 *report @ semester Session Wise 
	 */ 
	 if(reportType=="RGN"){
	 	refreshObject();
	 	object["reportCode"] = reportsDetails.selectedItem.reportCode;
	 	object["reportDescription"] = reportsDetails.selectedItem.reportName;
//	 	Alert.show(object["reportCode"]+'');
	 	
	 }
	 
	 /*
	 *Verification Report-Added by Mandeep
	 */
	 if(reportType=="RVR"){
	 refreshObject();
	 object["companyName"]=reportsDetails.selectedItem.companyName;
	 object["reqDate"]=reportsDetails.selectedItem.reqDate;
//	 Alert.show(object["reportCode"]+''+object["companyName"]+":"+object["reqDate"]);
	 	
	 }
	 
	if(event.currentTarget.toolTip==commonFunction.getConstants('generateReport')){
		Mask.show();	
		// Result Report			 
		if(reportCode=="1"){				
			reportRequest.url = commonFunction.getConstants('url')+"/resultReport/PrintResultReport.htm";				
		}
		/*
		 * Enrollment Report
		 */
		if(reportCode=="2"){					
			reportRequest.url = commonFunction.getConstants('url')+"/enrollmentReport/getEnrollmentDataForReportGeneration.htm";					
		}
		/*
		 * Unsatisfactoey performance
		 */
		if(reportCode=="3"){					
			reportRequest.url = commonFunction.getConstants('url')+"/performance/unsatisfactoryPerformance.htm";					
		}
		/*
		 * Temporary Registration Chart
		 */
		if(reportCode=="4"){					
			reportRequest.url = commonFunction.getConstants('url')+"/studentCheckList/directToExtendedPdf.htm";					
		}
		/*
		 * Final Registration Chart
		 */
		if(reportCode=="5"){					
			reportRequest.url = commonFunction.getConstants('url')+"/studentCheckList/directPdf.htm";					
		}
		// Consolidated Chart			
		if(reportCode=="6"){
		//reportRequest.url = commonFunction.getConstants('url')+"/report/getReportPath.htm";
		reportRequest.url = commonFunction.getConstants('url')+"/consolidatedChart/getConsolidatedChartData.htm";					
		}
			
		// Degree List Report			 
		if(reportCode=="7"){				
			reportRequest.url = commonFunction.getConstants('url')+"/degreeList/generateDegreeList.htm";				
		}
		/*
		 * Progress Card Report
		 */
		if(reportCode=="8"){
			reportRequest.url = commonFunction.getConstants('url')+"/progresscard/handleRequestInternal.htm";
						
		}
		/*
		 * Registration Statistics Report
		 */ 
		 if(reportCode=="9"){
		 	reportRequest.url = commonFunction.getConstants('url')+"/consolidatedChart/generateRegistrationStatisticsReport.htm"
		 }
		/*
		 * Result Statistics Report
		 */
		if(reportCode=="10"){			
			reportRequest.url = commonFunction.getConstants('url')+"/consolidatedChart/getResultStatisticsData.htm";				
		}
		/*
		* Final Semester Result Statistic
		*/	
		if(reportCode=="11"){
			reportRequest.url = commonFunction.getConstants('url')+"/finalsemesterresultstatistics/generatePDF.htm";
		}	
		/*
		* Final Semester Result Statistic Category wise
		*/	
		if(reportCode=="15"){
			reportRequest.url = commonFunction.getConstants('url')+"/finalSemesterResultStatisticsCategoryWise/generatePDF.htm";
		}
		/*
		* Medal List Report
		*/
		if(genReportCode=="16"){			
			reportRequest.url = commonFunction.getConstants('url')+"/resultReport/MedalListReport.htm";
		}	
		/*
		* Semester wise Marks Report
		*/
		if(reportCode=="17"){
			object["report"] = "semesterwise";
//			Alert.show("inside button click"+object["report"]);
			reportRequest.url = commonFunction.getConstants('url')+"/progresscard/handleRequestInternal.htm";
		}
		/*
		* Grade Report Consolidated
		*/
		if(reportCode=="18"){			
			reportRequest.url = commonFunction.getConstants('url')+"/consolidatedMarksheet/getConsolidatedMarkSheetData.htm";					
		}
		/*
		* Merit List CP
		*/
		if(reportCode=="19"){			
			reportRequest.url = commonFunction.getConstants('url')+"/performance/meritListBasedOnCP.htm	";					
		}
		/*
		* Subject category wise student merit list
		*/
		if(reportCode=="20"){			
			reportRequest.url = commonFunction.getConstants('url')+"/subjectcategorywisestudentlist/generatePDF.htm";					
		}
		/*
		* Program course key wise Final student List
		*/
		if(reportCode=="21"){			
			reportRequest.url = commonFunction.getConstants('url')+"/finalstudentlist/generateFinalStudentReport.htm";					
		}				
		
		/*
		*Delay In AwarD Blank Marks-Entity-Semester Wise
		*@author Mandeep Sodhi
		*/
		if(reportCode=="22"){			
			reportRequest.url = commonFunction.getConstants('url')+"/delayInDisplay/coursesMarksReleasedByDean.htm";					
		}

		/*
		* entity-sem type wise Component List whose marks modified
		* @author Ashish Mohan 
		*/
		if(reportCode=="23"){			
			reportRequest.url = commonFunction.getConstants('url')+"/finalstudentlist/modifiedMarksWithComponentReport.htm";					

		}
		
		/*
		* general report RGN (university_wise){list of courses that are unreleased by dean}
		* @author Ashish Mohan 
		*/
		if(genReportCode=="24"){	
					
			reportRequest.url = commonFunction.getConstants('url')+"/performance/setUnapprovedCourseList.htm";					
		}
		
		/*
		* List Of Courses Whose Marks Released By Dean
		*@author Mandeep Sodhi
		*/
		if(reportCode=="25"){
			reportRequest.url = commonFunction.getConstants('url')+"/delayInDisplay/coursesMarksReleasedByDean.htm";
		}
		/*
		* Result Verification Report
		*/
		if(reportCode=="26"){
			reportRequest.url=commonFunction.getConstants('url')+"/resultReport/VerificationOfResultReport.htm";
		}
		
		/*
		* Entity-Program course key-sem date wise (RCL)
		* Report to show Collation Differences of Remedial and External Award Blank
		* @author Ashish Mohan 
		*/
		if(reportCode=="27"){			
			reportRequest.url = commonFunction.getConstants('url')+"/performance/collationDifferences.htm";					
		}
		
		reportRequest.send(object);			
	}
					
	if(event.currentTarget.toolTip==commonFunction.getConstants('downloadreport')){			
		Mask.show();		
		downloadRequest.send(object);
	}
	
	if(event.currentTarget.toolTip==commonFunction.getConstants('printReport')){			
		Mask.show();
		object["print"] = "yes";		
		downloadRequest.send(object);
	}
	//Alert.show(event.currentTarget.toolTip+'');
}
/**
 * The method is executed on success of the download report 
 * request
 **/ 		
public function onDownloadRequestSuccess(event:ResultEvent):void{
	Mask.close();
	if(event.result.sessionConfirm == true){
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
	}
	var pathXML:XML = event.result as XML;		
	var path:String = pathXML.Path;		
	if(path.substr(0,5)=="false"){
		Alert.show(path.substr(6,path.length-6),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else{
		if(toolTipAction==commonFunction.getConstants('printReport')){			
			object["reportPath"] = path;
			Alert.show("Do You want to Print the Report ?",
				commonFunction.getMessages('success'),(Alert.YES|Alert.NO),null,printFile,successIcon,1);	
		}
		else{
			if(int(reportCode)==8){
		//		Alert.show(path.substr(0,path.length-1));
				downloadUrl.url = commonFunction.getConstants('url')+"/"+path.substr(0,path.length-1)+".zip";
			}
			else if(int(reportCode)==7){
				downloadUrl.url = commonFunction.getConstants('url')+"/"+path+reportDescription+".doc";	
			}
			else if(int(reportCode)==21){				
				downloadUrl.url = commonFunction.getConstants('url')+"/"+path+reportDescription+".doc";				
			}
			else{
				downloadUrl.url = commonFunction.getConstants('url')+"/"+path+reportDescription+".pdf";	
			}			
			Alert.show(commonFunction.getMessages('downloadConfirmation'),
					commonFunction.getMessages('success'),(Alert.YES|Alert.NO),null,downloadFile,successIcon,1);
						
		}	
	}
							
}	

public function downloadFile(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		fileRequest.download(downloadUrl);		
	}	
}

public function printFile(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		Mask.show();
		printReport.send(object);
//		navigateToURL(downloadUrl);		
	}	
}

/**
 * The method is executed on success of the print report 
 * request
 **/ 		
public function onPrintReportSuccess(event:ResultEvent):void{
	Mask.close();
	try{
		if(event.result.sessionConfirm == true){
 			Alert.show(resourceManager.getString('Messages','sessionInactive'));
	      	var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
		}
	}catch(e:Error){}
 
	 var status:String=(event.result.message+"");
	 if(status.substr(0,4)=="true"){
	 	if(status.length>4){
	 		Alert.show(status.substr(5,status.length-5),commonFunction.getMessages('success'),0,null,null,successIcon);
	 	}
	 	else{
			//Alert.show(commonFunction.getMessages('reportGenerated'),commonFunction.getMessages('success'),0,null,null,successIcon);
	 	}
	}
	else{
		if(status.length>5){
	 		Alert.show(status.substr(6,status.length-6),commonFunction.getMessages('error'),0,null,null,errorIcon);
	 	}
	 	else{
			Alert.show(commonFunction.getMessages('failToPrint'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	 	}		       
	 }		
}
/**
 * The method is executed on the click of the back button 
 **/ 	
	
private function onClickBack():void{	
	gridCanvasId.visible=false;
	reportLabel.visible = false;
	reportLabelId.visible = false;
	entityLabel.visible = false;
	entityComboBox.visible = false;
	entityList.removeAll();
	entityList.removeAll();
	backButton.visible = false;
	backButton2.visible=false;	
	gene.visible = false;
	down.visible = false;
	pri.visible = false;	
	canvasId.visible = true;	
}

/**
 * The method is executed on the click of the next button 
 **/ 	
private function onClickNext():void{
	reportTileList2.visible=true;
	previosButton.enabled=true;
}

/**
 * The method is executed on the click of the previous button 
 **/
private function onPrevClick():void{
	previosButton.enabled=false;
	reportTileList2.visible=false;
	reportTileList.visible=true;
}
		
