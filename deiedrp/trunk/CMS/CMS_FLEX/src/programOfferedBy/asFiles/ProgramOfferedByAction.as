/**
 * @(#) ProgramOfferedByAction.as
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
import mx.controls.NumericStepper;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;
import mx.events.CloseEvent;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]public var seatsTempText:NumericStepper=new NumericStepper();
[Bindable] private var dataList:ArrayCollection;
[Bindable]public var selectedRecords:ArrayCollection=new ArrayCollection();
[Bindable]public var existingEntityDetailListAC:ArrayCollection=new ArrayCollection();
[Bindable] public var urlEntityList:String = "";
[Bindable] public var urlProgramBranchSpeclList:String = "";
[Bindable] public var urlInsertProgramOfferedBy:String = "";
[Bindable] public var urlExistingEntityDetails:String = "";


 public var url_DNS:String ="";
                 
 [Bindable] public var xmldataEntityList:XML;
 [Bindable] public var xmldataProgramBranchSpeclList:XML;
 [Bindable] public var xmlInsertionMessage:XML;
 [Bindable] public var xmlDataExistingEntityDetailList:XML;
 [Bindable] public var urlProgramTypeDetails:XML; 
 [Bindable] public var dataEntityList:ArrayCollection;

     public function getString(pass_key:String):String{
		return resourceManager.getString('Constants', pass_key);
		}

     public function initProgramOfferedBy():void{
			url_DNS = commonFunction.getConstants('url');
    		urlEntityList = url_DNS+"/programofferedby/entityList.htm";
    		urlProgramBranchSpeclList = url_DNS+"/programofferedby/programBranchSpecializationList.htm";
    		urlInsertProgramOfferedBy = url_DNS+"/programofferedby/insertProgramOfferedBy.htm";
    		urlExistingEntityDetails = url_DNS+"/programofferedby/existingEntityDetails.htm";
    		getEntityList();
		}

 public function getEntityList():void {
       	var params:Object = {};       	
     		 params["abc"] =new Date().getTime();//does not use of params with this send(), it is only used for affect the page contents according dbase
    	     xmlEntityList.send(params);
        }
        
        public function getProgramBranchSpeclList():void {
       	var params:Object = {};
     		 params["entityId"] = offeredByCombo.selectedItem.entityId;//does not use of params with this send(), it is only used for affect the page contents according dbase
    	     xmlProgramBranchSpeclList.send(params);
        }
        
       public function onOK(event:CloseEvent):void {
       	if(event.detail==Alert.YES){
      	var params:Object = {};       		
      		 params["entityId"] = offeredByCombo.selectedItem.entityId;
     		 params["entityData"] = selectedRecords;
        xmlInsertProgramOfferedBy.send(params);}       	
        else{
        getExistingEntityDetails();
       	getProgramBranchSpeclList();
       	}
      }
       //getting existing details of entity
        public function getExistingEntityDetails():void {
   	    	var params:Object = {};
     		 params["entityId"] = offeredByCombo.selectedItem.entityId;
    	     xmlExistingEntityDetails.send(params);
        }
        
        private function resultEntityListHandler(event:ResultEvent):void{        	
        xmldataEntityList = event.result as XML;        
        if(xmldataEntityList.sessionConfirm == true)
        {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        }else{
        	
        	dataEntityList = new ArrayCollection();
        	
        	for each(var entityObject:Object in xmldataEntityList.entity){
        		
        		dataEntityList.addItem({entityName:entityObject.@name,entityId:entityObject.entityId,parentEntityId:entityObject.parentEntityId});
        		
        	}
        	
        	offeredByCombo.dataProvider = dataEntityList;
        	offeredByCombo.labelField = "entityName";
        	
        }
       }
      
      [Bindable]public var employees:ArrayCollection;
       private function resultProgramBranchSpeclListHandler(event:ResultEvent):void{
        xmldataProgramBranchSpeclList = event.result as XML;        
         if(xmldataProgramBranchSpeclList.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        		}
        		employees = new ArrayCollection();
        		for each(var obj:Object in xmldataProgramBranchSpeclList.employee){
        			
        			employees.addItem({mentor:obj.@name+"["+obj.employeeCode+"]",employeeCode:obj.employeeCode});       			
        			
        		}
        		
        		parentEntityLabel.visible = true;
        		parentEntityName.visible = true;        		
        		
        		parentEntityName.text = offeredByCombo.selectedItem.parentEntityId;
        		
        		       
        setEntity();
        loadProgramBranchSpeclList();
        getExistingEntityDetails();//getting program, branch, specialization associated with selected entity
      }
      
      private function resultInsertProgramOfferedByHandler(event:ResultEvent):void{
        xmlInsertionMessage = event.result as XML;
         if(xmlInsertionMessage.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        		}
         
        if(xmlInsertionMessage.message=='exist'){
         	Alert.show(commonFunction.getConstants('alreadyExist'),
       	commonFunction.getConstants('exist'),0,null,null,errorIcon);
       }
       else if(xmlInsertionMessage.message=='success'){
       	Alert.show(commonFunction.getConstants('insertSuccessful'),
       	commonFunction.getConstants('success'),0,null,null,successIcon);
       	
       	getExistingEntityDetails();
       	getProgramBranchSpeclList();
        }
        else if(xmlInsertionMessage.message=='fail'){
       	Alert.show(commonFunction.getConstants('insertFailed'),
       	commonFunction.getConstants('fail'),0,null,null,errorIcon);
       }
     }
      
      private function resultExistingEntityDetailsHandler(event:ResultEvent):void{
        xmlDataExistingEntityDetailList = event.result as XML;
         if(xmlDataExistingEntityDetailList.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        		}
        existingEntityDetailListAC.removeAll();
                for each(var s:Object in xmlDataExistingEntityDetailList.program){
                  existingEntityDetailListAC.addItem({programName:s.@name,
                  branchName:s.branch,specializationName:s.specialization,
                  mentor:s.mentor,programType:s.programType,seats:s.seats});
                  }
                  existingProgramDetailsGrid.dataProvider=existingEntityDetailListAC;
        }
      
       private function faultEntityListHandler(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'));
    }

    
       private function loadProgramBranchSpeclList():void{
                 	 //dataList.removeAll();
					 dataList= new ArrayCollection();//Add by Devendra
                  	 for each(var s:Object in xmldataProgramBranchSpeclList.program){
						dataList.addItem({select:false,programName:s.@name,programId:s.programId,
						branchName:s.branch.@name,branchId:s.branch.branchId,
						specializationName:s.specialization.@name,specializationId:s.specialization.specializationId});
					}
         //------------------Add by Devendra for enabled search field-----------------
                  if(dataList.length!=0){           	 
       				 programSearch.text="";
       				 programSearch.enabled=true;
                  }
                  else{
                  	 programSearch.text=commonFunction.getConstants('prog_name');
       				 programSearch.enabled=false;
                  }
         //-------------Devendra ends------------------------------
                }
//validating user form
public function validateScreen():Boolean
{
	var b:Boolean=true;
	var selectedCount:int=0;
	var mentorErrorCount:int=0;
	var programTypeErrorCount:int = 0;
	var seatsErrorCount:int=0;
	var errorString:String="";
	for each(var obj:Object in programDetailsGrid.dataProvider)
	{
		if(obj.select==true)
		{
			selectedCount++;
			seatsTempText.value=obj.seats;
			if(Validator.validateAll([seatsValidator]).length>0)
			{
				seatsErrorCount++;
			}
			if((obj.mentor=="")||(obj.mentor==null))
			{
				mentorErrorCount++;
			}
			if((obj.programType=="")|| obj.programType == null)
			{
				programTypeErrorCount++;
			}
		}
	}
	if(selectedCount==0)
	{
		b=false;
		errorString=resourceManager.getString('Messages','noRecordSelected');
	}
	
	if(seatsErrorCount>0)
	{
		b=false;
		errorString+=resourceManager.getString('Messages','invalidSeats')+"\n";
	}
	
	if(mentorErrorCount>0)
	{
		b=false;
		errorString+=resourceManager.getString('Messages','invalidMentor')+"\n";
	}
	if(programTypeErrorCount>0)
	{
		b = false;
		errorString += resourceManager.getString('Messages', 'errorProgramType')+"\n";
	}
	if(errorString!="")
	{
		Alert.show(errorString,resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	}
	return b;
}


//on Click of Submit Button
public function submitForm():void
{
	if(Validator.validateAll([offeredByValidator]).length==0)
	{
		if(validateScreen())
		{
			var entityDetails:Array=new Array();
			var i:int = 0;
			var cl:int=0;
			for each(var obj:Object in programDetailsGrid.dataProvider)
	        {
	        	cl=0;
		        if(obj.select==true)
		        { 
		        entityDetails[i] = new Array();
		        entityDetails[i][cl] = obj.programId;
		        cl++;
		        entityDetails[i][cl] = obj.branchId;
		        cl++;
		        entityDetails[i][cl] = obj.specializationId;
		        cl++;
		        var empCode:String = obj.mentor.toString();
		        // entityDetails[i][cl] = empCode.substring(empCode.length-7,empCode.length-1);
		         entityDetails[i][cl] = empCode.substring(empCode.indexOf("[")+1,empCode.indexOf("]"));
		     //    Alert.show(empCode.substring(empCode.indexOf("[")+1,empCode.indexOf("]")));
		        cl++;
		        entityDetails[i][cl] = xmldataProgramBranchSpeclList.programType.(@name == obj.programType).programCode;
		        cl++;
		        entityDetails[i][cl] = obj.seats;
		        cl++;
		        entityDetails[i][cl] = ";";
		        i++;
			    }
			    
			 }
			 for(var k:int = 0; k < entityDetails.length; k++){
					selectedRecords.addItem(entityDetails[k]);
					trace("entity details="+entityDetails[k]);
				}
			//calling for insertion
			Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);	
		}
	}
	else
	{
		Alert.show(resourceManager.getString('Messages','pleasecheckEntriesinRed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	}
}



//On Change of offered by combobox value
public function setEntity():void
{
	programDetailsPanel.visible=true;
	selectedRecords.removeAll();
	programDetailsGrid.selectedIndex=-1;
}