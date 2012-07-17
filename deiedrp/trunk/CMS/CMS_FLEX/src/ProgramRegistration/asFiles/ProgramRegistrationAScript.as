/**
 * @(#) ProgramRegistrationAScript.as
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
import mx.controls.LinkButton;
import mx.controls.NumericStepper;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

[Bindable]public var t:LinkButton=new LinkButton();
[Bindable]public var registrationText:NumericStepper=new NumericStepper();
[Bindable]public var addDropText:NumericStepper=new NumericStepper();
[Bindable] private var programDataList:ArrayCollection = new ArrayCollection();
[Bindable]protected var sessionDate:String="";
public var url_DNS:String ="";
[Bindable] public var urlLoadSession:String = "";
[Bindable] public var xmlDataLoadSession:XML;
[Bindable] public var urlLoadProgramDetails:String ="";
[Bindable] public var xmlDataLoadProgramDetails:XML;
[Bindable] public var urlInsertProgramDetails:String = "";
[Bindable] public var xmlInsertionMessage:XML;

			
		public function init():void{
			url_DNS = commonFunction.getConstants('url');				
    		urlLoadSession = url_DNS+"/programregistration/loadSession.htm";
    		urlLoadProgramDetails = url_DNS+"/programregistration/programToRegister.htm";
    		urlInsertProgramDetails = url_DNS+"/programregistration/insertProgram.htm";
    		getCurrentSession();
    		t.enabled=false;
		}
			
			
		
		
		public function getCurrentSession():void {
       	var params:Object = {};
     		 params["abc"] =new Date().getTime();//does not use of params with this send(), it is only used for affect the page contents according dbase
    	    httpLoadSession.send(params);
        }
        
       	public function getProgramDetails():void {
       	var params:Object = {};
     		 params["abc"] ="anil";//does not use of params with this send(), it is only used for affect the page contents according dbase
    	     httpLoadProgramDetails.send(params);
        }
        
        private function resultLoadSessionHandler(event:ResultEvent):void{
        xmlDataLoadSession = event.result as XML;
        if(xmlDataLoadSession.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        		}
        sessionDate = xmlDataLoadSession.sessionDate;
        getProgramDetails();
       }
       
       
        private function resultLoadProgramDetailsHandler(event:ResultEvent):void{
        xmlDataLoadProgramDetails = event.result as XML;
         if(xmlDataLoadProgramDetails.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        	if(xmlDataLoadProgramDetails.programCourseKey.length()==0){
        			Alert.show("All Programs Has Been Registered",commonFunction.getMessages('info'),0,null,null,infoIcon);
        			this.parentDocument.loaderCanvas.removeAllChildren();
        			}		
		loadProgramRegistrationList();
       	}
   //Changes BY Mandeep    
       private function resultInsertProgramHandler(event:ResultEvent):void{
        xmlInsertionMessage = event.result as XML;
        if(xmlInsertionMessage.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        if(xmlInsertionMessage.message!="0"){
       	Alert.show(xmlInsertionMessage.message+" "+commonFunction.getMessages('successOnInsert'),
       	commonFunction.getConstants('success'),0,this,onInsertSuccess,successIcon);
   
       	}
		else if(xmlInsertionMessage.message=='0'){
       	Alert.show(commonFunction.getMessages('noRecordInserted'),
       	commonFunction.getConstants('fail'),0,null,null,errorIcon);
       }
       else if(String(xmlInsertionMessage.message).search("insertError")>-1){
        Alert.show(xmlInsertionMessage.message,
       	commonFunction.getConstants('insertError'),0,null,null,errorIcon);     	
       }
     }
     
     // fires on insert success added by ashish mohan
     public function onInsertSuccess(event:CloseEvent):void{
     	t.enabled=false;
       	getProgramDetails();
     }
             
        private function faultLoadSessionHandler(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'),0,null,null,errorIcon);
    }
		
		private function loadProgramRegistrationList():void{
      		programDataList.removeAll();
      

                 	programDataList.removeAll();
                 	 for each(var s:Object in xmlDataLoadProgramDetails.programCourseKey){
                  	 	//Alert.show(s.program.@name);
                  	var regno:int=int(commonFunction.getConstants('registrationPeriodDate'));
                  	var addno:int=int(commonFunction.getConstants('addDropPeriodDate'));	 	
                  	 	
                  programDataList.addItem({select:false,entityId:s.entity.entityId,entityName:s.entity.@name,
				  programName:s.program.@name,programId:s.program.programId, branchName:s.branch.@name,branchId:s.branch.branchId,
                  specializationName:s.specialization.@name,specializationId:s.specialization.specializationId,
                  semesterName:s.semester.@name,semesterCode:s.semester.semesterCode,semesterStartDate:s.semesterStartDate,
                  semesterEndDate:s.semesterEndDate,programCourseKey:s.@name,registrationPeriod:regno,addDropPeriod:addno});
                  }
                  programRegistrationDetailsGrid.dataProvider=programDataList;
                }	
	
		
	//on Click of Submit Button
	public function submitForm():void{
	if(validateScreen()){
	Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);
				}
	}
	
	//function added by ashish mohan
	 public function onOK(event:CloseEvent):void {
       	if(event.detail==Alert.YES){
		if(validateScreen())
		{

		            var entity:String = "";
					var prog:String = "";
     		 		var ssd:String = "";
     		 		var sed:String = "";
     		 		var regp:String = "";
     		 		var adp:String = "";

			for each(var obj:Object in programRegistrationDetailsGrid.dataProvider)
	        {
	        	  if(obj.select==true)
		        { 
		        	var params:Object = {};
					entity = entity+obj.entityId+"|";
     		 		prog = prog+obj.programCourseKey+"|";
     		 		ssd =ssd+ obj.semesterStartDate+"|";
     		 		sed =sed+ obj.semesterEndDate+"|";
     		 		regp =regp+ registrationText.value+"|";
     		 		adp =adp+ addDropText.value+"|";  

			    }
			    
			 }
					params["selectedEntityId"] = entity;
					params["programCourseKey"] = prog;
     		 		params["semesterStartDate"] = ssd;
     		 		params["semesterEndDate"] = sed;
     		 		params["registrationPeriod"] = regp;
     		 		params["addDropPeriod"] = adp;  
     		 		
     		 		httpInsertProgramDetails.send(params);
		}
      }
      else{
      getProgramDetails();
      }
 }
      
      	
	//validating user form
public function validateScreen():Boolean
{
	var b:Boolean=true;
	var selectedCount:int=0;
	var mentorErrorCount:int=0;
	var seatsErrorCount:int=0;
	var errorString:String="";
	for each(var obj:Object in programRegistrationDetailsGrid.dataProvider)
	{
		if(obj.select==true)
		{
			selectedCount++;
			registrationText.value = int(obj.registrationPeriod);
			addDropText.value = int(obj.addDropPeriod);
			registrationValidator.source = registrationText;
			addDropValidator.source = addDropText;
			if(Validator.validateAll([registrationValidator]).length!=0)
			{
				b=false;
				Alert.show(commonFunction.getMessages('pleaseFillRegistrationPeriod'),commonFunction.getMessages('error'),0,null,null,errorIcon);
			}
			else if(Validator.validateAll([addDropValidator]).length!=0)
			{
				b=false;
				Alert.show(commonFunction.getMessages('pleaseFillAddDropPeriod'),commonFunction.getMessages('error'),0,null,null,errorIcon);
			}

		}
	}
//	if(seatsErrorCount>0)
//	{
//		b=false;
//		//errorString=commonFunction.getMessages('pleasecheckEntriesinRed');
//		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
//	}
	
	
//	if(errorString!="")
//	{
//		Alert.show(errorString,commonFunction.getMessages('error'),0,null,null,errorIcon);
//	}
	return b;
}		
			

			