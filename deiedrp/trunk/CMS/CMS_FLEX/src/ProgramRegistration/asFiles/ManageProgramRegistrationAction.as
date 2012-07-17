/**
 * @(#) ManageProgramRegistrationAction.as
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
import common.validations.CommonValidations;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.*;
			
[Embed(source="/images/warning.png")]private var warningIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;

public var registration_start_dateValid:DateValidator = new DateValidator();
public var registration_last_dateValid:DateValidator = new DateValidator();
public var addDrop_periodValid:StringValidator = new StringValidator();
public var enddate:DateValidator =new DateValidator();
private var entityArrColl:ArrayCollection=null;

public var validate:CommonValidations=new CommonValidations();

		 public var url_DNS:String ="";
		[Bindable] public var urlGetEntityList:String="";
		[Bindable] public var urlLoadProgramList:String = "";
		[Bindable] public var urlBranchList:String = "";
    	[Bindable] public var urlSemList:String = "";
     	[Bindable] public var urlSpecialList:String = "";
     	[Bindable] public var urlProgramDetails:String = "";
     	[Bindable] public var urlUpdateProgramDetails:String = "";
     	[Bindable] public var urlLoadSession:String = "";
     	[Bindable] public var urlCheckStatus:String = "";
		[Bindable] public var xmlDataLoadSession:XML;
		[Bindable] public var xmlDataProgramList:XML;
		[Bindable] private var xmldata_branchName:XML;
    	[Bindable] private var xmldata_semesterList:XML;
    	[Bindable] private var xmldata_specialList:XML;
    	[Bindable] private var xmlDataProgramDetails:XML;
    	[Bindable] private var updateMessage:XML;
    	[Bindable] private var statusMessage:XML;
		[Bindable] protected var statusField:String="";
		[Bindable]protected var sessionDate:String="";
		[Embed(source="/images/question.png")]private var questionIcon:Class;	
			public function initManageScreen():void{				
			url_DNS = commonFunction.getConstants('url');
			urlGetEntityList = url_DNS+"/manageprogramregistration/getEntityList.htm";
			urlLoadSession = url_DNS+"/programregistration/loadSession.htm";
    		urlLoadProgramList = url_DNS+"/manageprogramregistration/programList.htm";
    		urlBranchList = url_DNS+"/manageprogramregistration/branchList.htm";
    		urlSemList = url_DNS+"/manageprogramregistration/semesterList.htm";
    		urlSpecialList = url_DNS+"/manageprogramregistration/specializationList.htm";
			urlProgramDetails = url_DNS+"/manageprogramregistration/programDetails.htm";
    		urlUpdateProgramDetails = url_DNS+"/manageprogramregistration/updateProgramDetails.htm";
    		urlCheckStatus = url_DNS+"/manageprogramregistration/checkStatus.htm";
    		getCurrentSession();    		
			}
			
		public function getString(pass_key:String):String{
			return resourceManager.getString('Constants', pass_key);
		}
		
		public function getCurrentSession():void {
       	var params:Object = {};
     		 params["abc"] =new Date().getTime();//does not use of params with this send(), it is only used for affect the page contents according dbase
    	     httpLoadSession.send(params);
        }
		
		private function resultGetEntityHandler(event:ResultEvent):void{
        	var entityList:XML = event.result as XML;
        	entityArrColl=new ArrayCollection();
        	for each(var obj:Object in entityList.item){
        		entityArrColl.addItem({entityId:obj.entityId, entityName:obj.entityName});
        	}
        	entity_nameCB.dataProvider=entityArrColl;	
        }
        
        private function faultGetEntityHandler(event:FaultEvent):void{
        	Alert.show(commonFunction.getMessages('problemInService'), commonFunction.getMessages('failure'),4,null,null,errorIcon);
        }
		
		public function getProgramList():void {
       	var params:Object = {};
     		 params["abc"] =new Date().getTime();
			 params["selectedEntityId"]=entity_nameCB.selectedItem.entityId;
    	     httpLoadProgramList.send(params);
    	     
        }
	
		private function get_branch():void {
      		var params:Object = {};
      		params["programId"] = xmlDataProgramList.item.(@name==program_nameCB.selectedLabel).itemId;
			params["selectedEntityId"]=entity_nameCB.selectedItem.entityId;
        	httpXmlBranchList.send(params);
      }
      
      private function get_specialization():void {
      var params:Object = {};
      params["programId"] = xmlDataProgramList.item.(@name==program_nameCB.selectedLabel).itemId;
      params["branchId"] = xmldata_branchName.item.(@name==branch_nameCB.selectedLabel).itemId;
      params["selectedEntityId"]=entity_nameCB.selectedItem.entityId;
           httpXmlSpecialList.send(params);
     }
			
	private function get_semester():void {
      var params:Object = {};
      params["programId"] = xmlDataProgramList.item.(@name==program_nameCB.selectedLabel).itemId;
      params["branchId"] = xmldata_branchName.item.(@name==branch_nameCB.selectedLabel).itemId;
	  params["specializationId"] = xmldata_specialList.item.(@name==specializationCB.selectedLabel).itemId;
	  params["selectedEntityId"]=entity_nameCB.selectedItem.entityId;
        httpXmlSemList.send(params);
        
     }
     
     private function getProgramDetails():void {
      var params:Object = {};
      params["programId"] = xmlDataProgramList.item.(@name==program_nameCB.selectedLabel).itemId;
      params["branchId"] = xmldata_branchName.item.(@name==branch_nameCB.selectedLabel).itemId;
	  params["specializationId"] = xmldata_specialList.item.(@name==specializationCB.selectedLabel).itemId;
      params["semesterCode"] =  semester_nameCB.selectedItem.semesterCode;
	  params["selectedEntityId"]=entity_nameCB.selectedItem.entityId;
        httpProgramDetails.send(params);
     }
     
public function updateProgramDetails():void
{
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,updateProgramDetail,questionIcon);
}      

     
     private function updateProgramDetail(event:CloseEvent):void {
     	
     	if(event.detail==1)
	   {
      	var params:Object = {};
      	params["programCourseKey"] = xmlDataProgramDetails.programCourseKey.@code;
      	params["registrationStartDate"] = registration_start_date.text;
	  	params["registrationLastDate"] = registration_last_date.text;
      	params["addDropPeriod"] =  addDrop_period.text;
      	params["status"] =  statusField;
		params["selectedEntityId"]=entity_nameCB.selectedItem.entityId;
        httpUpdateProgramDetails.send(params);
    }
     }
     
     private function resultLoadSessionHandler(event:ResultEvent):void{
        xmlDataLoadSession = event.result as XML;        
        if(xmlDataLoadSession.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        sessionDate = xmlDataLoadSession.sessionDate;
        httpGetEntityList.send(new Date());
       }
		private function resultLoadProgramListHandler(event:ResultEvent):void{
        	xmlDataProgramList = event.result as XML;
        	 if(xmlDataProgramList.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}        		        				
        		if(xmldata_semesterList != null)
        		{           			
        			branch_nameCB.enabled = false;            			
        			get_branch();        			 			
        		}
      //  sessionDate = xmlDataLoadSession.sessionDate;
       }
       private function resultHandler_BranchList(event:ResultEvent):void{
        xmldata_branchName = event.result as XML;        
        if(xmldata_branchName.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
         program_nameCB_changeHandler();
    }
       private function resultHandler_SpecialList(event:ResultEvent):void{
        xmldata_specialList=event.result as XML;
        if(xmldata_specialList.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
     	branch_nameCB_changeHandler();
    }
       private function resultHandler_SemList(event:ResultEvent):void{
        xmldata_semesterList=event.result as XML;
        var semesterList:ArrayCollection= new ArrayCollection();
        if(xmldata_semesterList.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        		}        		 
				 
				 for each(var obj:Object in xmldata_semesterList.sem){
				 	semesterList.addItem({semesterName:obj.semesterName,semesterCode:obj.semesterCode});
				 }
				semester_nameCB.dataProvider = semesterList;
				semester_nameCB.labelField = "semesterName";
				semester_nameCB.enabled = true;
       specializationCB_changeHandler();
    }
      
      private function resultHandler_ProgramDetails(event:ResultEvent):void{
        	xmlDataProgramDetails = event.result as XML;
        	 if(xmlDataProgramDetails.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        		}
        	fillFields();
       }
       
       private function resultHandler_UpdateProgramDetails(event:ResultEvent):void{
       updateMessage = event.result as XML;
        if(updateMessage.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        		}
       if(updateMessage.message=='success'){
       	Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),
       	commonFunction.getConstants('success'),0,null,null,successIcon);
      
       	initManageScreen();
       	 
         }
        else if(updateMessage.message=='fail'){
       	Alert.show(commonFunction.getMessages('requestFailed'),
       	commonFunction.getConstants('fail'),0,null,null,errorIcon);
       }
        else if(String(updateMessage.message).search("updateError")>-1){
       	Alert.show(updateMessage.message,
       	commonFunction.getConstants('updateError'),0,null,null,errorIcon);
       }
       //resetButton_clickHandler();
    }
       
       private function resultHandler_CheckStatus(event:ResultEvent):void{
       		statusMessage = event.result as XML;
       		if(statusMessage.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
       		if(statusMessage.sessionDate=='ACT'){
       			updateProgramDetails();//call for updation
         	}
        	else if(statusMessage.sessionDate=='INA'){
       			Alert.show(commonFunction.getConstants('updateStatus'),
       			commonFunction.getConstants('error'),0,null,null,warningIcon);
       		}
       //resetButton_clickHandler();
    	}
    	
        private function faultLoadProgramListHandler(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'));
    	}
			
		private function fillFields():void{
			registration_start_date.data = DateField.stringToDate(xmlDataProgramDetails.programCourseKey.registrationStartDate,"YYYY-MM-DD")
        	registration_last_date.data = DateField.stringToDate(xmlDataProgramDetails.programCourseKey.registrationLastDate,"YYYY-MM-DD")
        	addDrop_period.text = xmlDataProgramDetails.programCourseKey.addDropPeriod;
        	semester_start_date.text = xmlDataProgramDetails.programCourseKey.semesterStartDate;
        	semester_end_date.text = xmlDataProgramDetails.programCourseKey.semesterEndDate;
			statusField = xmlDataProgramDetails.programCourseKey.status;
				if(statusField=="ACT"){
					statusCheckBox.selected=true; 
				}
				else{
					statusCheckBox.selected=false;
				}
			setDateRangeAndFocus();		
		}
			
			
			/**
			 * @protected
			 * this method is called on Program Name comboBox item change event.
			 * @param event a ListEvent event object.
			 */
			protected function program_nameCB_changeHandler():void
			{
				branch_nameCB.enabled=true;
				semester_nameCB.enabled=false;
				specializationCB.enabled=false;
				
				branch_nameCB.selectedIndex=-1;
				semester_nameCB.selectedIndex=-1;
				specializationCB.selectedIndex=-1;
				
				secondCanvas.visible=false;
				
			}

			/**
			 * @protected
			 * this method is called on Branch Name comboBox item change event.
			 * @param event a ListEvent event object.
			 */
			protected function branch_nameCB_changeHandler():void
			{
				specializationCB.enabled=true;
				semester_nameCB.enabled=false;
				
				specializationCB.selectedIndex=-1;
				semester_nameCB.selectedIndex=-1;
				
				secondCanvas.visible=false;
			}

			/**
			 * @protected
			 * this method is called on Specialization Name comboBox item change event.
			 * @param event a ListEvent event object.
			 */
			protected function specializationCB_changeHandler():void
			{
				semester_nameCB.enabled=true;
				semester_nameCB.selectedIndex=-1;
				
				secondCanvas.visible=false;
			}

			/**
			 * @protected
			 * this method is called on Semester Name comboBox item change event.
			 * @param event a ListEvent event object.
			 */
			protected function semester_nameCB_changeHandler():void
			{
				secondCanvas.visible=true;	
				saveButton.enabled=true;
				getProgramDetails();
			}
			
			/**
			 * @protected
			 * this method send the Form's data to server side to save.
			 * @param event a mouse event object.
			 */
			protected function saveButton_ClickHandler(event:MouseEvent):void
			{
				 if(validateForm())
				 {
				 	addDrop_period.errorString = "";
				 	if(statusCheckBox.selected == true){
						 statusField = "ACT"; 
						 var params:Object = {};
      					params["programCourseKey"] = xmlDataProgramDetails.programCourseKey.@code;
      		        	httpCheckStatus.send(params);
					 }
					 else{
						 statusField = "INA";
						 updateProgramDetails();
					 }

				 }  
			}
			
			/** This function calls on creation complete and It set's the selectable Renge of 
			 * Date field for process start date as from 1 month before to 1 month sfter from
			 * current date,and also calls a function drawFocousTo */
			public function setDateRangeAndFocus():void
			{
				var semesterStartDate:Date = DateField.stringToDate(semester_start_date.text,"YYYY-MM-DD");
				var semesterEndDate:Date = DateField.stringToDate(semester_end_date.text,"YYYY-MM-DD");
				registration_start_date.selectableRange = {rangeStart :semesterStartDate,rangeEnd :semesterEndDate};
				registration_last_date.selectableRange = {rangeStart :semesterStartDate,rangeEnd :semesterEndDate};
			}
			
			/**
 * @protected
 * this method is called to validate the form fields.
 */
public function validateForm():Boolean
{
	var flag:Boolean=true;
	
	registration_start_dateValid.source = registration_start_date;
	registration_start_dateValid.property = "text";
	registration_start_dateValid.required=true;
	registration_start_dateValid.inputFormat="yyyy-mm-dd";
	registration_start_dateValid.allowedFormatChars="-";
	
	registration_last_dateValid.source = registration_last_date;
	registration_last_dateValid.property = "text";
	registration_last_dateValid.required=true;
	registration_last_dateValid.inputFormat="yyyy-mm-dd";
	registration_last_dateValid.allowedFormatChars="-";
	
	addDrop_periodValid .source = addDrop_period;
	addDrop_periodValid.property = "text";
	addDrop_periodValid.required=true;
		
	if(Validator.validateAll([registration_start_dateValid, registration_last_dateValid, addDrop_periodValid]).length!=0){
		Alert.show(commonFunction.getMessages('validateMessage'));
		flag=false;		
	}
	
	if(validate.datechecker(registration_start_date, registration_last_date)){
		registration_start_date.errorString=commonFunction.getMessages('startLessEndDate');
		flag=false;		
	}
	else{
		registration_start_date.errorString="";
	}
	
	if(Number(addDrop_period.text)>99){
		addDrop_period.errorString=commonFunction.getMessages('addDropValidation');
		flag=false;
	}
	
	return flag;
}

			/**
			 * @protected
			 * to close the form.
			 * @param event a mouse event object.
			 */
			protected function cancelButton_ClickHandler(event:MouseEvent):void
			{
				this.removeAllChildren();
				document.visible=false;
			}			