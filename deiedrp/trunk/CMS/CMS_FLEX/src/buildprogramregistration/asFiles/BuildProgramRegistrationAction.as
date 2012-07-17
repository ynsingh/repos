/**
 * @(#) ProgramRegistrationAction.as
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

import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;
			
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

			[Bindable]protected var sessionDate:String="";
			 public var url_DNS:String ="";
			[Bindable] public var urlLoadSession:String = "";
			[Bindable] public var xmlDataLoadSession:XML;
			
			[Bindable] public var urlInsertProgramReg:String = "";
			[Bindable] public var xmlInsertionMessage:XML;
			
			[Bindable] public var urlCheckStatus:String = "";
			
			public function init():void{
			url_DNS = commonFunction.getConstants('url');
    		urlLoadSession = url_DNS+"/programregistration/loadSession.htm";
    		urlInsertProgramReg = url_DNS+"/programregistration/insertProgramReg.htm";
    		urlCheckStatus=url_DNS+"/programregistration/checkStatus.htm"
    		getCurrentSession();
			}
			
		public function getString(pass_key:String):String{
			return resourceManager.getString('Constants', pass_key);
		}
		
		
		public function getCurrentSession():void {
       	var params:Object = {};
     		 params["time"] =new Date();//does not use of params with this send(), it is only used for affect the page contents according dbase
    	     xmlLoadSession.send(params);
        }
        
        public function insertProgramRegistration():void {
       	var params:Object = {};
     		 params["time"] =new Date();//does not use of params with this send(), it is only used for affect the page contents according dbase
     		 params["regperiod"] = registrationPeriod.text;
    	     params["adddropperiod"] = addDropPeriod.text;
    	     params["flag"]="BuildYearEnd";
    	     xmlInsertProgramReg.send(params);
    	     Mask.show(commonFunction.getMessages('pleaseWait'));
        }
        
        private function resultLoadSessionHandler(event:ResultEvent):void{
        xmlDataLoadSession = event.result as XML;
        sessionDate = xmlDataLoadSession.sessionDate;
       }
       
       private function resultInsertProgramRegHandler(event:ResultEvent):void{
       		xmlInsertionMessage = event.result as XML;
       		Mask.close();
        	if(xmlInsertionMessage.message==1){
         		Alert.show("" +commonFunction.getMessages('recordBuildSuccess')+ 
         			"\nSession: "+sessionDate+
         			"\nTotal records found = "+xmlInsertionMessage.totalRecords+
       				"\nRecords inserted = "+xmlInsertionMessage.recInserted+
       				"\nRecords inserted in log file = "+xmlInsertionMessage.recInLogs+
       				"\n\nSession : "+ (int(sessionDate.substr(0,4))+1)+"-"+(int(sessionDate.substr(5,7))+1)+
       				"\nTotal records found = "+xmlInsertionMessage.nextSessionTotalRecords+
       				"\nRecords inserted = "+xmlInsertionMessage.nextSessionRecInserted+
       				"\nRecords inserted in log file = "+xmlInsertionMessage.nextSessionRecInLogs,
       				commonFunction.getMessages('info'),0,null,null,infoIcon);
       		}
       		else if(xmlInsertionMessage.message==0){
       			Alert.show("" +commonFunction.getMessages('recordBuildSuccess')+ 
       				"\nSession: "+sessionDate+
       				"\nTotal records found = "+xmlInsertionMessage.totalRecords+
       				"\nRecords inserted = "+xmlInsertionMessage.recInserted+
       				"\nRecords inserted in log file = "+xmlInsertionMessage.recInLogs+
       				"\n\nSession : "+ (int(sessionDate.substr(0,4))+1)+"-"+(int(sessionDate.substr(5,7))+1)+
       				"\nTotal records found = "+xmlInsertionMessage.nextSessionTotalRecords+
       				"\nRecords inserted = "+xmlInsertionMessage.nextSessionRecInserted+
       				"\nRecords inserted in log file = "+xmlInsertionMessage.nextSessionRecInLogs,
       				commonFunction.getMessages('info'),0,null,null,infoIcon);
       		}
        	else if(xmlInsertionMessage.message==-1){
       			Alert.show(commonFunction.getConstants('insertFailed'),
       			commonFunction.getConstants('fail'),0,null,null,errorIcon);
       		}
       }
       
       private function faultLoadSessionHandler(event:FaultEvent):void{
         Mask.close();
         Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'));
    }
			
			/**
			 * @protected
			 * this method is called to validate the form fields.
			 */
			public function validateForm():Boolean
			{
				var flag:int=0;

				if(Validator.validateAll([registrationPeriodValid, addDropPeriodValid]).length!=0){
					Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'), commonFunction.getMessages('info'),4,null, null, errorIcon);
					flag++;		
				}
				if(Number(registrationPeriod.text)>99){
					registrationPeriod.errorString=commonFunction.getMessages("lessthen99NotAllowed");
					flag++;
				}
				if(Number(addDropPeriod.text)>99){
					addDropPeriod.errorString=commonFunction.getMessages("lessthen99NotAllowed");
					flag++;
				}
				
				return (flag>0?false:true);
			}
			
						
			/**
			 * @protected
			 * this method send the Form's data to server side to save.
			 * @param event a mouse event object.
			 */
			protected function saveButtonClickHandler(event:MouseEvent):void
			{
				 if(validateForm())
				 { 
					Alert.show(commonFunction.getMessages('areyousure'), commonFunction.getMessages('confirm'), 3, this, insertRecords,questionIcon);	
				 }  
			}
			
			/**
		 * @protected
		 * this method is called on Alert Box Close Event
		 * this method will inactivate the selected record.
		 * @param event a close event object.
		 */
		protected function insertRecords(event:CloseEvent):void
		{
			if(event.detail == Alert.YES)
			{
				checkSequenceAndStatus.send(new Date());
				
			}
		}
			
			/**
			 * @protected
			 * to close the form.
			 * @param event a mouse event object.
			 */
			protected function cancelButtonClickHandler(event:MouseEvent):void
			{
				this.removeAllChildren();
				document.visible=false;
			}
				
			/**
 			 * Success Handler for checkSequenceAndStatus Service  
 			 * @Param Event(ResultEvent)
 			**/
 			public function resultStatusHandler(event:ResultEvent):void{
				 var data:XML=event.result as XML;
				 var message:String=data.resultdata;
				 if(data.sessionConfirm == true){
	        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
					var url:String="";
					url=commonFunction.getConstants('navigateHome');
					var urlRequest:URLRequest=new URLRequest(url);
					urlRequest.method=URLRequestMethod.POST;
					navigateToURL(urlRequest,"_self");
        		}
        		if(message=="run"){
        			insertProgramRegistration();
        		}
        		else if(message=="buildPrevious"){
        			Alert.show(resourceManager.getString("Messages","firstBuild",["Build Next Session"]), commonFunction.getMessages('info'),4,null, null, infoIcon);
        		}
        		else if(message=="allreadyBuild"){
        			Alert.show(resourceManager.getString("Messages","allreadyBuild",["Program Registration",sessionDate]), commonFunction.getMessages('info'),4,null, null, infoIcon);
        		}
        		else if(message=="noSequence"){
        			Alert.show(resourceManager.getString("Messages","NOProcessCode",["YEPCOD","1"]),commonFunction.getMessages('info'),4,null,null,infoIcon);
        		}
        		else if(message=="NOProcessCode"){
        			Alert.show(resourceManager.getString("Messages","NOProcessCode",["YEPCOD","2"]),commonFunction.getMessages('info'),4,null,null,infoIcon);
        		}
        		else{
        			Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
        		}
			}				