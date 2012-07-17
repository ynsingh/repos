/**
 * @(#) AwardBlankAction.as
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

	import awardBlankSheet.AdvancedGridOperations;
	import awardBlankSheet.ApprovedRequest;
	
	import common.Mask;
	import common.commonFunction;
	
	import flash.events.MouseEvent;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.collections.ArrayCollection;
	import mx.controls.AdvancedDataGrid;
	import mx.controls.Alert;
	import mx.controls.LinkButton;
	import mx.controls.advancedDataGridClasses.AdvancedDataGridColumn;
	import mx.events.CloseEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	import mx.validators.Validator;
	
	[Bindable] private var statusXml:XML;
	[Bindable] private var pendingXml:XML;
	[Bindable]	var gridData:ArrayCollection ;
	public var entityId:String=null;
	public var programId:String=null;
	public var entityType:String=null;
	public var branchId:String=null;
	public var specializationId:String=null;
	public var semesterId:String=null;
	public var courseCode:String=null;
	public var courseName:String=null;
	public var sessionStartDate:String=null;
	public var sessionEndDate:String=null;
	public var resultSystem:String=null;
	public var entityName:String=null;
	public var programName:String=null;
	public var branchName:String=null;
	public var specializationName:String=null;
	public var semesterName:String=null;
	public var entityTypeName:String=null;
	public var pendingList:PendingRequest;
	public var approvedList:ApprovedRequest;
	public var rejectedList:RejectedRequest;
	public var msg:String;
	
	[Embed(source="/images/success.png")]private var successIcon:Class;	
	[Embed(source="/images/error.png")]private var errorIcon:Class;
	[Embed(source="/images/warning.png")]private var warningIcon:Class;
	[Embed(source="/images/question.png")]private var questionIcon:Class;
	[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
	
	public var semesterStartDate:String;
	public var semesterEndDate:String;
	public var prevousEmployeeId:String;
	public var employeeName:String;
	//public var gradelimit:String;  // Arush 
	[Bindable]public var pendingAC:ArrayCollection;
	[Bindable]public var approvedAC:ArrayCollection;
	[Bindable]public var rejectedAC:ArrayCollection;

	/**
	 * function for setting value of button
	 */
	public function setButtonPressed(status:String):void{
		buttonPressed=status;
	}
	
	/**
	 * function for setting values for variables based on selections
	 */
	public function setVariables():void{
		if(buttonPressed=='SW'){ // show button
			Mask.show(commonFunction.getMessages('pleaseWait'));
			getEvaluationComponents();			
		}
		else if(buttonPressed=='PL'){// pending list
			var selectedPendingListArrCol:ArrayCollection=AdvancedGridOperations.getSelectedRowData(pendingList.pendingListGrid);
			if(selectedPendingListArrCol.length>0){
				entityId=selectedPendingListArrCol.getItemAt(0).entityId;
				entityType=selectedPendingListArrCol.getItemAt(0).entityType;
				programId=selectedPendingListArrCol.getItemAt(0).programId;
				branchId=selectedPendingListArrCol.getItemAt(0).branchId;
				specializationId=selectedPendingListArrCol.getItemAt(0).specializationId;
				semesterId=selectedPendingListArrCol.getItemAt(0).semesterCode;
				courseCode=selectedPendingListArrCol.getItemAt(0).courseCode;
				semesterStartDate=selectedPendingListArrCol.getItemAt(0).startDate;
				semesterEndDate=selectedPendingListArrCol.getItemAt(0).endDate;
				entityName=selectedPendingListArrCol.getItemAt(0).entityName;
				entityTypeName=selectedPendingListArrCol.getItemAt(0).entityTypeName;
				programName=selectedPendingListArrCol.getItemAt(0).programName;
				branchName=selectedPendingListArrCol.getItemAt(0).branchName;
				specializationName=selectedPendingListArrCol.getItemAt(0).specializationName;
				semesterName=selectedPendingListArrCol.getItemAt(0).semesterName;
				courseName=selectedPendingListArrCol.getItemAt(0).courseName;
				resultSystem=selectedPendingListArrCol.getItemAt(0).resultSystem;
				programCourseKey=selectedPendingListArrCol.getItemAt(0).programCourseKey;
				displayType = selectedPendingListArrCol.getItemAt(0).displayType;
				prevousEmployeeId = selectedPendingListArrCol.getItemAt(0).employeeId;
				showFile();
			}else{
				Alert.show(commonFunction.getMessages('pleaseSelectAtleastOneRecord'),commonFunction.getMessages('info'),4,null,null,errorIcon);
			}
		}
		else if(buttonPressed=='AP'){ // approved list
			var selectedApprovedListArrCol:ArrayCollection=AdvancedGridOperations.getSelectedRowData(approvedList.approvedListGrid);
			if(selectedApprovedListArrCol.length>0){
				entityId=selectedApprovedListArrCol.getItemAt(0).entityId;
				entityType=selectedApprovedListArrCol.getItemAt(0).entityType;
				programId=selectedApprovedListArrCol.getItemAt(0).programId;
				branchId=selectedApprovedListArrCol.getItemAt(0).branchId;
				specializationId=selectedApprovedListArrCol.getItemAt(0).specializationId;
				semesterId=selectedApprovedListArrCol.getItemAt(0).semesterCode;
				courseCode=selectedApprovedListArrCol.getItemAt(0).courseCode;
				semesterStartDate=selectedApprovedListArrCol.getItemAt(0).startDate;
				semesterEndDate=selectedApprovedListArrCol.getItemAt(0).endDate;
				entityName=selectedApprovedListArrCol.getItemAt(0).entityName;
				entityTypeName=selectedApprovedListArrCol.getItemAt(0).entityTypeName;
				programName=selectedApprovedListArrCol.getItemAt(0).programName;
				branchName=selectedApprovedListArrCol.getItemAt(0).branchName;
				specializationName=selectedApprovedListArrCol.getItemAt(0).specializationName;
				semesterName=selectedApprovedListArrCol.getItemAt(0).semesterName;
				courseName=selectedApprovedListArrCol.getItemAt(0).courseName;
				resultSystem=selectedApprovedListArrCol.getItemAt(0).resultSystem;
				programCourseKey=selectedApprovedListArrCol.getItemAt(0).programCourseKey;
				displayType = selectedApprovedListArrCol.getItemAt(0).displayType;
				prevousEmployeeId = selectedApprovedListArrCol.getItemAt(0).employeeId;
			    showFile();
			}else{
				Alert.show(commonFunction.getMessages('pleaseSelectAtleastOneRecord'),commonFunction.getMessages('info'),4,null,null,errorIcon);
			}
		}
		else if(buttonPressed=='RJ'){ // reject list
			var selectedRejectListArrCol:ArrayCollection=AdvancedGridOperations.getSelectedRowData(rejectedList.rejectedListGrid);
			if(selectedRejectListArrCol.length>0){
				entityId=selectedRejectListArrCol.getItemAt(0).entityId;
				entityType=selectedRejectListArrCol.getItemAt(0).entityType;
				programId=selectedRejectListArrCol.getItemAt(0).programId;
				branchId=selectedRejectListArrCol.getItemAt(0).branchId;
				specializationId=selectedRejectListArrCol.getItemAt(0).specializationId;
				semesterId=selectedRejectListArrCol.getItemAt(0).semesterCode;
				courseCode=selectedRejectListArrCol.getItemAt(0).courseCode;
				semesterStartDate=selectedRejectListArrCol.getItemAt(0).startDate;
				semesterEndDate=selectedRejectListArrCol.getItemAt(0).endDate;
				entityName=selectedRejectListArrCol.getItemAt(0).entityName;
				entityTypeName=selectedRejectListArrCol.getItemAt(0).entityTypeName;
				programName=selectedRejectListArrCol.getItemAt(0).programName;
				branchName=selectedRejectListArrCol.getItemAt(0).branchName;
				specializationName=selectedRejectListArrCol.getItemAt(0).specializationName;
				semesterName=selectedRejectListArrCol.getItemAt(0).semesterName;
				courseName=selectedRejectListArrCol.getItemAt(0).courseName;
				resultSystem=selectedRejectListArrCol.getItemAt(0).resultSystem;
				programCourseKey=selectedRejectListArrCol.getItemAt(0).programCourseKey;
				displayType = selectedRejectListArrCol.getItemAt(0).displayType;
				prevousEmployeeId = selectedRejectListArrCol.getItemAt(0).employeeId;						    
			    showFile();		
			}else{
				Alert.show(commonFunction.getMessages('pleaseSelectAtleastOneRecord'),commonFunction.getMessages('info'),4,null,null,errorIcon);
			}
		}
	}
	
	private function showFile():void{
		var params:Object =new Object();
		params["entityName"]=entityName;
		params["entityId"]=entityId;
		params["courseCode"]=courseCode;
	 	params["courseName"]=courseName;	 	
	 	params["startDate"]=semesterStartDate;
		params["endDate"]=semesterEndDate;
		params["employeeCode"]=employeeCode; 
		params["previousStatus"]='SUB';
		params["displayType"]=displayType;
		params["programName"]=programName;
		params["branchName"]=branchName;
		params["specializationName"]=specializationName;
		params["semesterName"]=semesterName;
		params["previousEmployeeId"]=prevousEmployeeId;
		params["programCourseKey"]=programCourseKey;
		Mask.show(commonFunction.getMessages('pleaseWait'));
		httpShowApprovedRequests.send(params); // http request will open the PDF file of selected request
	}
	
	private function showFileResultHandler(event:ResultEvent):void{
		var resultXML:XML=event.result as XML;
		
		if(resultXML.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
		
		Mask.close();
		var fileName:String=resultXML.exception.exceptionstring;
			navigateToURL(new URLRequest(commonFunction.getConstants('url')+"/"+fileName));		
	}
	

	/**
	 * function for enabling/disabling buttons according to status
	 */
	private function resultHandlerStatus(event:ResultEvent):void{
		statusXml=event.result as XML;
		
		if(statusXml.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
		
	   	var status:String=statusXml.exception.exceptionstring;
		switch(status+""){
			case 'WDW': submitForApprovalButton.enabled=true;
		  				saveButton.enabled=true;	
		  				gradelimitButton.enabled=true ;
		  				mkcorrectionButton.enabled=true ;
		  				withdrawButton.enabled=false;
		  				break;
		  	case 'SUB': withdrawButton.enabled=true;
		  				submitForApprovalButton.enabled=false;
		  				saveButton.enabled=false;
		  				gradelimitButton.enabled=false ;
		  				mkcorrectionButton.enabled=false ;
		  				break;	
		  	case 'APR': withdrawButton.enabled=false;
		  				submitForApprovalButton.enabled=false;
		  				mkcorrectionButton.enabled=false ;
		  				saveButton.enabled=false;
		  				gradelimitButton.enabled=false ;
		  				break;	  				
		  	default:   submitForApprovalButton.enabled=true;
		  				saveButton.enabled=true;
		  				withdrawButton.enabled=false;
		  				gradelimitButton.enabled=true ;
		  				mkcorrectionButton.enabled=true ;
		  				break;
		  }
		  Mask.close();
	}
 
 	
	 /**
	 * function for getting list of pending requests
	 */
	 private function resultHandlerPendingList(event:ResultEvent):void{
	 	pendingXml=event.result as XML;
	 	
	 	if(pendingXml.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
	 	
	 	pendingAC=new ArrayCollection();
		var flag:Boolean=true;
		var displayTypeDescription:String;
	   	for each (var object:Object in pendingXml.root){
	   		if(object.displayType=="I"){
	   			displayTypeDescription="Internal";
	   		}
	   		else if(object.displayType=="E"){
	   			displayTypeDescription="External";
	   		}
	   		else if(object.displayType=="R"){
	   			displayTypeDescription="Remedial";
	   		}
	   		
	   		pendingAC.addItem({select:false,courseName:object.courseName,courseCode:object.courseCode,
	   		entityId:object.entityId,entityType:object.entityType,programId:object.programId,
	   		branchId:object.branchId,specializationId:object.specializationId,semesterCode:object.semesterCode,
	   		startDate:object.startDate,endDate:object.endDate, entityName:object.entityName, entityTypeName:object.entityTypeName,
	   		programName:object.programName, branchName:object.branchName, specializationName:object.specializationName, 
	   		semesterName:object.semesterName,resultSystem:object.resultSystem, programCourseKey:object.programCourseKey, 
	   		displayType:object.displayType, displayTypeDescription:displayTypeDescription, 
	   		employeeId:object.employeeId, employeeName:object.employeeName});
	   	}
	   	
	   	if(pendingAC.length>0)
	   	{
	   		var showPendingButton:LinkButton = new LinkButton();
	   		showPendingButton.label=commonFunction.getConstants('pendingAwardBlank'); 
	   		showPendingButton.addEventListener(MouseEvent.CLICK,showPendingList);
	   		vbox.addChild(showPendingButton);
	   		courselistCanvas.y=vbox.height+60;
	   	}
	 } 
	 
	 
	 /**
	 * function for getting list of pending requests
	 */
	 private function resultHandlerApprovedList(event:ResultEvent):void{
	 	pendingXml=event.result as XML;
	 	
	 	if(pendingXml.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
	 	
	 	approvedAC=new ArrayCollection();
	   	var flag:Boolean=true;
	   	var displayTypeDescription:String;
	   	
	   	for each (var object:Object in pendingXml.root){
	   		if(object.displayType=="I"){
	   			displayTypeDescription="Internal";
	   		}
	   		else if(object.displayType=="E"){
	   			displayTypeDescription="External";
	   		}
	   		else if(object.displayType=="R"){
	   			displayTypeDescription="Remedial";
	   		}
	   		
	   		approvedAC.addItem({select:false,courseName:object.courseName,courseCode:object.courseCode,
	   		entityId:object.entityId,entityType:object.entityType,programId:object.programId,
	   		branchId:object.branchId,specializationId:object.specializationId,semesterCode:object.semesterCode,
	   		startDate:object.startDate,endDate:object.endDate, entityName:object.entityName, entityTypeName:object.entityTypeName,
	   		programName:object.programName, branchName:object.branchName, specializationName:object.specializationName, 
	   		semesterName:object.semesterName,resultSystem:object.resultSystem, programCourseKey:object.programCourseKey,
	   		displayType:object.displayType,	displayTypeDescription:displayTypeDescription, 
	   		employeeId:object.employeeId, employeeName:object.employeeName});
	   	}
	   	
	   	if(approvedAC.length>0)
	   	{
	   		var showApprovedButton:LinkButton = new LinkButton();
	   		showApprovedButton.label=commonFunction.getConstants('approvedAwardBlank'); 
	   		showApprovedButton.addEventListener(MouseEvent.CLICK,showApprovedList);
	   		vbox.addChild(showApprovedButton);
	   		courselistCanvas.y=vbox.height+60;
	   	}
	 }
	 
	 /**
	 * function for getting list of pending requests
	 */
	 private function resultHandler_RejectedList(event:ResultEvent):void{
	 	pendingXml=null;
	 	pendingXml=event.result as XML;
	 	
	 	if(pendingXml.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
	 		 	
	   	rejectedAC=new ArrayCollection();
		var flag:Boolean=true;
		var displayTypeDescription:String;
	   	
	   	for each (var object:Object in pendingXml.root){
	   		if(object.displayType=="I"){
	   			displayTypeDescription="Internal";
	   		}
	   		else if(object.displayType=="E"){
	   			displayTypeDescription="External";
	   		}
	   		else if(object.displayType=="R"){
	   			displayTypeDescription="Remedial";
	   		}	   	
	   	
	   		rejectedAC.addItem({select:false,courseName:object.courseName,courseCode:object.courseCode,
	   		entityId:object.entityId,entityType:object.entityType,programId:object.programId,
	   		branchId:object.branchId,specializationId:object.specializationId,semesterCode:object.semesterCode,
	   		startDate:object.startDate,endDate:object.endDate, entityName:object.entityName, entityTypeName:object.entityTypeName,
	   		programName:object.programName, branchName:object.branchName, specializationName:object.specializationName, 
	   		semesterName:object.semesterName,resultSystem:object.resultSystem,reason:object.reason,
	   		displayType:object.displayType,	displayTypeDescription:displayTypeDescription,
	   		employeeId:object.employeeId, employeeName:object.employeeName,programCourseKey:object.programCourseKey});
	   	}
	   	
	   	if(rejectedAC.length>0)
	   	{
	   		var showRejectedButton:LinkButton = new LinkButton();
	   		showRejectedButton.label=commonFunction.getConstants('rejectedAwardBlank'); 
	   		showRejectedButton.addEventListener(MouseEvent.CLICK,showRejectedList);
	   		vbox.addChild(showRejectedButton);	  
	   		courselistCanvas.y=vbox.height+60;
	   	}
	 }  
 
 
    private function checkGradeField():Boolean{
    	var bool:Boolean=true;
    	for each(var obj:Object in awardSheetGrid.dataProvider as ArrayCollection){
    		if(obj.grade=="" || obj.grade==null){
    			bool=false;
    		}
    	}
    	return bool;
    }  
    
    //  Arush Validation ; Check Grades enetered thru excel. 
     private function validategrade(){
      for each(var obj:Object in awardSheetGrid.dataProvider as ArrayCollection){
     if (obj.grade!= "" || obj.grade!=null){
     	
     		
     	
     	Alert.show("check grade value "+gradeXML.grade);
   
     	
     	
     }
     }
     }
 	
 	private function submitConfirm():void{
		Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),3,null,submitSheet,questionIcon); 	
	}
 	
	 /**
	 * function for submitting award sheet for approval
	 */
	private function submitSheet(event:CloseEvent):void{
		
		var totmrk :int;
		if(event.detail==Alert.YES){
			gridData = new ArrayCollection;
			if (requestpending()){
			Alert.show("Request for marks correction is pending from Roll Number: "+msg,commonFunction.getMessages('info'),4,null,null,infoIcon)
			return;
			}
			
		//var gridData:ArrayCollection=commonFunction.getAdvancedDataGridRowData(awardSheetGrid);
		gridData = commonFunction.getAdvancedDataGridRowData(awardSheetGrid);
			if(gridData.length>0){
				var s:String="";
				for each(var o:Object in gridData){
					
						if (gradelimit=='1'){
					if(displayType.toUpperCase()=="I"){
		    						totmrk=o["totalInternal"];
		    						}
		    						else if(displayType.toUpperCase()=="E"){
		    							totmrk=o["totalExternal"];
		    						} else{
		    							totmrk=o["totalMarks"];
		    						}
		    						
		    						var wgrade:String = calculategrade(totmrk);
		    						if (wgrade==null){
		    						Alert.show("Grade does not exists in grade limit for marks = "+ totmrk,commonFunction.getMessages('info'),4,null,null,infoIcon);	
		    						return;
		    						}
				 }else{
				 	wgrade = o["grade"] ;
				 }
					
					for(var v:String in o){

			    			if((v!="rollNumber")&&(v!="studentName")&&(v!="totalMarks")&&(v!=o[v]+"idType")&&
			    	//		(v!="externalGrade")&&(v!="internalGrade")&&(v!="totalInternal")&&(v!="totalExternal")&&(v!="grade")){ //Arush Check Grade
			    				(v!="externalGrade")&&(v!="internalGrade")&&(v!="totalInternal")&&(v!="totalExternal" &&(v!="Correction"))){
			    			// Changes by arush ; Validate each component marks
			    			
			    			if (v!="grade"){  // loop v! = grade
			    						    			
			    				var idmaxmarks:Number  = new Number(componentXml.component.(evaluationId==v).maximumMarks);
			    				var mrkchg:String="N";
			    				var componentMarks:Number ;
			    				
			    				if (isANumber(o[v])){
			    					 componentMarks  = new Number(o[v]);
			    				}else{
			    					componentMarks = 0;
			    				}
			    			  	
			    			  	
			    			  		var prvmarks:String=getpreviousmarks(o["rollNumber"],v);
			    			  	//var prvmarks:String = o[v+"b"];
			    			  	if(prvmarks!=o[v] && prvmarks !="Z") 
			    			  	{
			    			  		mrkchg = "C";
			    			  	}else{
			    			  			mrkchg = "N";
			    			  	}			
			    				if (componentMarks > idmaxmarks)
			    				{
									Alert.show(commonFunction.getMessages('EnteredmarksmorethanMaximumMark')+ " for Roll Number: "+ 
									o["rollNumber"] + " and component: " + v
									,commonFunction.getMessages('error'),4,null,null,errorIcon);
			    					return;
			    		  				}
			    		  				if (v==''){
			    		  					v='ZZZ' // dummy number
			    		  				}
			    		  				
			    		  		
		    				 				
		    				    				
 			if(displayType=='I'){
		    				
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalInternal"]+"|"+wgrade+"|"+prvmarks+"|"+mrkchg+";";
						}else if(displayType=='E'){
		    			//	s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o[v]+"|"+wgrade+";";
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalExternal"]+"|"+wgrade+"|"+prvmarks+"|"+mrkchg+";";
						}else {
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalMarks"]+"|"+wgrade+"|"+prvmarks+"|"+mrkchg+";";
						}
		    			
						
		    		
		    				
			    		 	}// end of loop  If Grade Limit to be used
			    		  	
			    		  		if (v=="grade" ){     // loop v== Grade
			    		  		var invalidgrade:Boolean =true;	
			    		  		var s1:XML;
			    		  		var s2:String;
							  	for each (s1 in  gradeXML.gradeList.grade){
							  		
							  		s2=s1;
							  		
			    		  			if (o[v] ==s2){
			    		  			invalidgrade = 	false ;
			    		  			break;
			    		  			} 
			    		 }       // end  loop v== Grade
			    		  			
			    			if (invalidgrade==true){   // invalid grade true
			    			Alert.show(commonFunction.getMessages('invalidgrade')+ " for Roll Number: "+ 
									o["rollNumber"] + " and Grade : " + o[v]
									,commonFunction.getMessages('error'),4,null,null,errorIcon);
									return;	
			    			}  // end of loop invalid grade true
			    			}  // end  of  loop v! = grade
			    			
			    				// arush changes end here
			    	
			    		 			
			    			
			    		  		}
			    		
			    			
						
					}	
				}
					    		
				var params:Object=new Object();
			 	params["courseCode"]=courseCode;
				params["startDate"]=semesterStartDate;
				params["endDate"]=semesterEndDate;
			    params["programCourseKey"]=programCourseKey;
			    params["entityId"]=entityId;
			    params["employeeCode"]=employeeCode;  
			    params["status"]='SUB';
				params["displayType"]=displayType;
				params["data"]=s;
				httpSaveSheet2.send(params);
				if(checkGradeField()){
					Mask.show(commonFunction.getMessages('pleaseWait'));
			    	httpSubmitApprovalRequest.send(params);	
				}
				else{
					Alert.show(commonFunction.getMessages('fillGrade'),commonFunction.getMessages('error'),4,null,null,errorIcon);
				}
			}
			else{
				Alert.show(commonFunction.getMessages('noStudentFound'),commonFunction.getMessages('info'),4,null,null,infoIcon);
			}
		}		    
	 }
	 
	 private function resultHandlerSave2(event:ResultEvent):void{
	 	
	 }
 
	 /**
	  * request handler for submit button
	  */
	 private function resultHandlerSubmit(event:ResultEvent):void{
	 	var result:XML=event.result as XML;
	 	
	 	if(result.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
	 	
		if(result.exception.exceptionstring == 'E'){
	   		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else{
	  		saveButton.enabled=false;   
	   		submitForApprovalButton.enabled=false;
	   		var params:Object=new Object();
			params["courseCode"]=courseCode;
			params["startDate"]=semesterStartDate;
			params["endDate"]=semesterEndDate;
		    params["programCourseKey"]=programCourseKey;
		    params["entityId"]=entityId;
		    params["employeeCode"]=employeeCode;
			params["displayType"]=displayType; 
		    httpStatus.send(params);
		    submitForApprovalRequest(awardSheetGrid);
	   	}
	 }
	 
	 /**
     * function for generating pdf on click of SubmitForApproval Button
     */
     public function submitForApprovalRequest(awardSheetGrid:AdvancedDataGrid):void{
         var headers:String="";
         var data:String="1"+";";
         var i:int=0;
         var count:int=1;
         for each(var obj:Object in awardSheetGrid.dataProvider){
            for each(var a:AdvancedDataGridColumn in awardSheetGrid.columns){
                if(i==0){
                    headers=headers+a.headerText+'|';
                }
               
                if(obj[a.dataField]==null){
//                    data=data+"-"+";";
                    data=data+commonFunction.convertNumberToWord(obj[a.dataField.substr(0,a.dataField.length-2)])+";";
                }else if(obj[a.dataField]==""){
                    data=data+"-"+";";
                }
                else{
                    data=data+obj[a.dataField]+";";
                }
            }
            count++;
            data=data+String(count)+";";
            i++;
         }

	 	var params:Object=new Object();
	 	params["entity"]=entityId;
		params["program"]=programId;
		params["branch"]=branchId;
		params["specialization"]=specializationId;
		params["semester"]=semesterId;
		params["courseCode"]=courseCode;
	 	params["courseName"]= courseName;
	 	params["output"]="PDF";
	 	params["header"]=headers;
	 	params["data"]=data;	 	
	 	params["programCourseKey"]=programCourseKey;
		params["employeeCode"]=employeeCode; 
		params["displayType"]=displayType;
		params["entityName"]=entityName;
		params["entityTypeName"]=entityTypeName;
		params["programName"]=programName;
		params["branchName"]=branchName;
		params["specializationName"]=specializationName;
		params["semesterName"]=semesterName;
		params["sessionStartDate"]=sessionStartDate;
		params["sessionEndDate"]=sessionEndDate;
		params["employeeName"]=employeeName;		
		httpApproveRequestPdf.send(params); // request is for generate the PDF on the submit button	 	
	 }
	 
	private function resultHandlerApproveRequestPdf(event:ResultEvent):void{
		 Mask.close();
		 for each(var obj:Object in courseListGrid.dataProvider as ArrayCollection){
			obj.select=false;
		}
		courseListGrid.dataProvider.refresh();
		awardSheetGrid.dataProvider.refresh();
		awardSheetCanvas.visible=false;
		 Alert.show(commonFunction.getMessages('approvedSuccessfullyPdf'),commonFunction.getMessages('success'),4,null,null,successIcon);
	} 
  
	 /**
	 * function for generating pdf on click of approve button
	 */
	 public function approveRequest():void{
	 	var selectedPendingListArrCol:ArrayCollection=AdvancedGridOperations.getSelectedRowData(pendingList.pendingListGrid);
		entityId=selectedPendingListArrCol.getItemAt(0).entityId;
		entityType=selectedPendingListArrCol.getItemAt(0).entityType;
		programId=selectedPendingListArrCol.getItemAt(0).programId;
		branchId=selectedPendingListArrCol.getItemAt(0).branchId;
		specializationId=selectedPendingListArrCol.getItemAt(0).specializationId;
		semesterId=selectedPendingListArrCol.getItemAt(0).semesterCode;
		courseCode=selectedPendingListArrCol.getItemAt(0).courseCode;
		semesterStartDate=selectedPendingListArrCol.getItemAt(0).startDate;
		semesterEndDate=selectedPendingListArrCol.getItemAt(0).endDate;
		entityName=selectedPendingListArrCol.getItemAt(0).entityName;
		entityTypeName=selectedPendingListArrCol.getItemAt(0).entityTypeName;
		programName=selectedPendingListArrCol.getItemAt(0).programName;
		branchName=selectedPendingListArrCol.getItemAt(0).branchName;
		specializationName=selectedPendingListArrCol.getItemAt(0).specializationName;
		semesterName=selectedPendingListArrCol.getItemAt(0).semesterName;
		courseName=selectedPendingListArrCol.getItemAt(0).courseName;
		resultSystem=selectedPendingListArrCol.getItemAt(0).resultSystem;
		programCourseKey=selectedPendingListArrCol.getItemAt(0).programCourseKey;
		displayType = selectedPendingListArrCol.getItemAt(0).displayType;
		prevousEmployeeId = selectedPendingListArrCol.getItemAt(0).employeeId;
	 	
	 	var params:Object=new Object();
	 	params["entity"]=entityId;
	 	params["programCourseKey"]=programCourseKey;
	 	params["courseCode"]=courseCode;
	 	params["startDate"]=semesterStartDate;
		params["endDate"]=semesterEndDate;
		params["employeeCode"]=employeeCode;
		params["status"]="APR";
		params["previousStatus"]='SUB';
		params["displayType"]=displayType;
		params["requestSender"]= prevousEmployeeId;
		Mask.show(commonFunction.getMessages('pleaseWait'));
		httpApproveRequest.send(params);	 	
	 }
 
 
	 private function resultHandlerApproveRequest(event:ResultEvent):void{
		 var serviceResult:XML=event.result as XML;
		 
		 if(serviceResult.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		 }
		 
			 Alert.show(commonFunction.getMessages('approvedSuccessfully'),(commonFunction.getMessages('success')),4,null,null,successIcon);

			 var params:Object=new Object();
			 params["employeeCode"]=employeeCode;
			 params["displayType"]=displayType;
			 params["time"]=new Date().getTime();
			 vbox.removeAllChildren();
			 httpPendingRequests.send(params);
			 httpApprovedRequests.send(params);			 
			 PopUpManager.removePopUp(pendingList);
			 pendingAC.refresh();			 
			 Mask.close();
	}
	
	
	public var rejectionPopup:RejectionReason;
	
	public function rejectFrame():void
	{
		rejectionPopup=RejectionReason(PopUpManager.createPopUp(this,RejectionReason,true));
		rejectionPopup.refFuncForReject=rejectRequest;
		PopUpManager.centerPopUp(rejectionPopup);
	} 
 
	/**
	 * Function for rejecting award blank
	 */
	public function rejectRequest():void{
		//reject current and all lower level requests
		var selectedPendingListArrCol:ArrayCollection=AdvancedGridOperations.getSelectedRowData(pendingList.pendingListGrid);
		entityId=selectedPendingListArrCol.getItemAt(0).entityId;
		entityType=selectedPendingListArrCol.getItemAt(0).entityType;
		programId=selectedPendingListArrCol.getItemAt(0).programId;
		branchId=selectedPendingListArrCol.getItemAt(0).branchId;
		specializationId=selectedPendingListArrCol.getItemAt(0).specializationId;
		semesterId=selectedPendingListArrCol.getItemAt(0).semesterCode;
		courseCode=selectedPendingListArrCol.getItemAt(0).courseCode;
		semesterStartDate=selectedPendingListArrCol.getItemAt(0).startDate;
		semesterEndDate=selectedPendingListArrCol.getItemAt(0).endDate;
		entityName=selectedPendingListArrCol.getItemAt(0).entityName;
		entityTypeName=selectedPendingListArrCol.getItemAt(0).entityTypeName;
		programName=selectedPendingListArrCol.getItemAt(0).programName;
		branchName=selectedPendingListArrCol.getItemAt(0).branchName;
		specializationName=selectedPendingListArrCol.getItemAt(0).specializationName;
		semesterName=selectedPendingListArrCol.getItemAt(0).semesterName;
		courseName=selectedPendingListArrCol.getItemAt(0).courseName;
		resultSystem=selectedPendingListArrCol.getItemAt(0).resultSystem;
		programCourseKey=selectedPendingListArrCol.getItemAt(0).programCourseKey;
		displayType = selectedPendingListArrCol.getItemAt(0).displayType;
		prevousEmployeeId = selectedPendingListArrCol.getItemAt(0).employeeId;
		
		if(Validator.validateAll([rejectionPopup.reasonValidator]).length==0){
			var params:Object=new Object();
		 	params["entity"]=entityId;
			params["status"]="REJ";
		 	params["programCourseKey"]=programCourseKey;
		 	params["startDate"]=semesterStartDate;
			params["endDate"]=semesterEndDate;
			params["employeeCode"]=employeeCode; 
			params["courseCode"]=courseCode;
			params["displayType"]=displayType;
			params["reason"]=rejectionPopup.reasonText.text;
	 		Mask.show(commonFunction.getMessages('pleaseWait'));
			httpRejectRequest.send(params);
		}
		else{
			rejectionPopup.reasonText.errorString="";
		}
		
	}

	private function resultHandlerRejectRequest(event:ResultEvent):void{   
		
		 if(event.result.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		 }
		
		var params:Object=new Object();
	 	params["employeeCode"]=employeeCode;
	 	params["displayType"]=displayType;
	 	params["time"]=new Date().getTime();
	 	vbox.removeAllChildren();
		httpPendingRequests.send(params);
		httpApprovedRequests.send(params);
		PopUpManager.removePopUp(pendingList);
		PopUpManager.removePopUp(rejectionPopup);
		pendingAC.refresh();
		Mask.close();
	   	Alert.show(commonFunction.getMessages('recordRejectedSuccessfully'),(commonFunction.getMessages('success')),4,null,null,successIcon);   
	 }
	
	private function withdrawConfirm():void{
		Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),3,null,withdrawSheet,questionIcon);
	}
	
	/**
	 * Function for withdrawing award blank previously submitted
	 */
	public function withdrawSheet(event:CloseEvent):void{
		if(event.detail==Alert.YES){
			var params:Object=new Object();
		 	params["courseCode"]=courseCode;
			params["startDate"]=semesterStartDate;
			params["endDate"]=semesterEndDate;
		    params["programCourseKey"]=programCourseKey;
		    params["entityId"]=entityId;
		    params["employeeCode"]=employeeCode;  
		    params["status"]='WDW';
		    params["displayType"]=displayType;
		    Mask.show(commonFunction.getMessages('pleaseWait'));
		    httpWithdrawRequest.send(params);
		}    
	}
	
	
    /**
     * request handler for withdraw button
     */
	private function resultHandlerWithdraw(event:ResultEvent):void{
		var result:XML=event.result as XML;
		
		 if(result.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		 }
		
		if(result.exception.exceptionstring == 'E'){
	   		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else{
			//result.exception.exceptionstring (this string contains no of record updated)
	  		withdrawButton.enabled=false; 
	   		Alert.show(commonFunction.getMessages('withdrawsuccessfully') ,commonFunction.getMessages('success'),4,null,null,successIcon);
	   		var params:Object=new Object();
			params["courseCode"]=courseCode;
//			params["startDate"]=semesterStartDate;
//			params["endDate"]=semesterEndDate;
		    params["programCourseKey"]=programCourseKey;
		    params["entityId"]=entityId;
//		    params["employeeCode"]=employeeCode;
			params["displayType"]=displayType; 
//		    httpStatus.send(params);
//		    
//
//		getAprStatus.send(params);
			getApprovalOrder.send(params);
		
		
	   	}
	   	Mask.close();
	}

	/**
	 * Functions for calculating total
	 */
		public function getbest(myArray:Array,rule:String):int{
		myArray.sort(Array.NUMERIC);
		
		var maxValue1:int =myArray.pop();
		var maxValue2:int =myArray.pop();
		var maxValue3:int =myArray.pop();
		var maxValue4:int =myArray.pop();
		var maxValue5:int =myArray.pop();
		
		var max:int =0;
		if(rule == "BT1" ){
			 max=maxValue1 ;
		} else if(rule == "BT2" ){
		   max=maxValue1+maxValue2;	
		} else if(rule == "BT3" ){
		   max=maxValue1+maxValue2+maxValue3;	
		} else if(rule == "BT4" ){
		   max=maxValue1+maxValue2+maxValue3+maxValue4;	
		} else if(rule == "BT5" ){
		   max=maxValue1+maxValue2+maxValue3+maxValue4+maxValue5;	
		} else{
			max=maxValue1+maxValue2;
		}
		
	
		return	max;
	}

	public function getTotal(row:Object,col:AdvancedDataGridColumn):String
	{
		var myArray:Array=new Array();
		var tempArray:Array=new Array();
		var rule:String = "";
		var myArray:Array=new Array();
				var total:int=0;
		
	    for each(var obj2:Object in componentAC){ //loop 03
	       
			var groupName:String=obj2.group;
			if(tempArray.indexOf(groupName)<0){	 //loop 02
		
		
			   	var arr:Object=new Object();
			   	
			   	var i:int=0;
			   	rule = obj2.rule;
			   	for each(var obj3:Object in componentAC){ // start loop 01
					if(groupName==obj3.group){
						if(obj3.idType=='MK'){
							arr[i++]=obj3.evaluationId;
			   			}
			   		}
			   	}// end for loop 01
			   	tempArray.splice(0);
			   	myArray.splice(0) ;
				tempArray.push(groupName);
				myArray.push(arr);
	//		}  	
	//	}  
			
		//var total:int=0;
		for each(var ac:Object in myArray){			
			var arr1:Array=new Array();	
			for each(var o:String in ac){
				if(isANumber(row[o])){
				arr1.push(int(row[o]));	
				}else{
					arr1.push("0");
				}
				
			}
			
			
			
			total=total+getbest(arr1,rule);
		}
		}//loop 02
	    }//loop 03
		if(displayType=='I'){
		row["totalInternal"]=total;
		}else if(displayType=='E'){
			row["totalExternal"]=total;
		}else{
			row["totalMarks"]=total;
		}
		return	total+"";
	}
	
	
	public function requestpending():Boolean{
		for each(var Obj:Object in abcbk){
			var base:String =Obj.Correction ;
			msg=Obj.rollNumber;
			
			var start:Number =base.indexOf("PEN", start+3);
			if (start != -1){
				return true;
			}
		
		}
		
		return false ;
		
		
	}