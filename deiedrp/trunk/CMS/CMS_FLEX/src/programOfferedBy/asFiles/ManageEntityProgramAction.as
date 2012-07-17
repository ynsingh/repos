/**
 * @(#) ManageEntityProgramAction.as
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
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable] public var urlEntityList:String = "";
[Bindable] public var urlProgramList:String = "";
[Bindable] public var urlEntityDetails:String = "";
[Bindable] public var urlDeleteProgramOfferedBy:String = "";
[Bindable] public var dataEntityList:ArrayCollection;

public var url_DNS:String ="";
private var msg:String=null;
private var msgHeader:String=null;
public var image:Class;

[Bindable] public var xmldataEntityList:XML;
[Bindable] public var xmldataProgramList:XML;
[Bindable] public var xmlDataEntityDetailList:XML;
[Bindable] public var xmldataDeleteProgramOfferedBy:XML;

[Bindable]public var recordsToDelete:ArrayCollection=new ArrayCollection();
[Bindable]public var entityDetailListAC:ArrayCollection=new ArrayCollection();
[Bindable]public var employeeListAC:XMLList;//:ArrayCollection = new ArrayCollection();

public function getString(pass_key:String):String{
		return commonFunction.getConstants(pass_key);
		}

public function initProgramOfferedBy():void{
			url_DNS = commonFunction.getConstants('url');
    		urlEntityList = url_DNS+"/manageprogramofferedby/manageEntityList.htm";
    		urlProgramList = url_DNS+"/manageprogramofferedby/getProgramList.htm";
    		urlEntityDetails = url_DNS+"/manageprogramofferedby/getDetailsOfEntity.htm";
    		getEntityList();
}

public function getEntityList():void {
       	var params:Object = {};
     		 params["abc"] =new Date;//does not use of params with this send(), it is only used for affect the page contents according dbase
    	     xmlEntityList.send(params);
        }
        
        //getting details of entity
        public function getProgramList():void {
        	
        	parentEntityLabel.visible = true;
        	parentEntity.visible = true;
        	
        	parentEntity.text = offeredByCombo.selectedItem.parentEntityId;
        	
       		var params:Object = {};
       		 params["entityId"] = offeredByCombo.selectedItem.entityId;
    	     xmlProgramList.send(params);
        }
        //getting details of entity
        public function getEntityDetails():void {
       		var params:Object = {};
       		 params["entityId"] = offeredByCombo.selectedItem.entityId;
    	     params["programId"] = xmldataProgramList.entity.(@name==programCombo.selectedLabel).entityId;
    	     xmlEntityDetails.send(params);
        }
        
        public function deleteProgramOfferedBy(event:CloseEvent):void{
        	urlDeleteProgramOfferedBy = commonFunction.getConstants('url')+"/manageprogramofferedby/deleteProgramOfferedBy.htm";
				if(event.detail == Alert.YES)
				{
					var params:Object = {};
        			params["entityId"] = offeredByCombo.selectedItem.entityId;
        			params["entityData"] = recordsToDelete;
    	     		xmlDeleteProgramOfferedBy.send(params)
				} 
         }
    //changes by Mandeep    
        private function resultDeleteProgramOfferedBy(event:ResultEvent):void{
        xmldataDeleteProgramOfferedBy = event.result as XML;
         if(xmldataDeleteProgramOfferedBy.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        if(xmldataDeleteProgramOfferedBy.message=='success'){
          	msg=commonFunction.getConstants('deletion_successful'); 
          	image = successIcon;
          	msgHeader = commonFunction.getMessages('success');      	
       }
        else if(String(xmldataDeleteProgramOfferedBy.message).search("deleteError")>-1){
       	msg=xmldataDeleteProgramOfferedBy.message;
       	image = errorIcon;
       	msgHeader = commonFunction.getMessages('errorOnpobDeletion');       
       }
       Alert.show(msg,msgHeader,0,null,alertListener,image);
         
     }
      private function alertListener(eventObj:CloseEvent):void {
                  if (eventObj.detail==Alert.OK) {
                      	getEntityDetails();
                      	
                      	 editButton.enabled= false;
          				 deleteButton.enabled = false;
                 }
            }
        
        private function resultEntityDetailsHandler(event:ResultEvent):void{
        		xmlDataEntityDetailList = event.result as XML;
        		  if(xmlDataEntityDetailList.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        		}
       	        //for employee list
        		employeeListAC = xmlDataEntityDetailList.empList;
               //for program branch specialization
        		entityDetailListAC.removeAll();
                for each(var s:Object in xmlDataEntityDetailList.program){
                  entityDetailListAC.addItem({select:false,programName:s.@name,programId:s.programId,
                  branchName:s.branch.@name,branchId:s.branch.branchId,
                  specializationName:s.specialization.@name,specializationId:s.specialization.specializationId,
                  mentor:s.mentor,seats:s.seats});
                  }
           	entityProgramPanel.visible=true;
           	entityProgramGrid.dataProvider=entityDetailListAC;     
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
      
      private function resultProgramListHandler(event:ResultEvent):void{
        xmldataProgramList = event.result as XML;
        if(xmldataProgramList.sessionConfirm == true)
        {
        	Alert.show(resourceManager.getString('Messages','sessionInactive'));
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
        }
        xmldataEntityList
         changeEntity();
      }
      private function faultEntityListHandler(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'));
    }
      
	public function showDetails():void
	{
		if(Validator.validateAll([entityValidator]).length==0)
		{
			getEntityDetails();
		}
		else
		{
			Alert.show(resourceManager.getString('Messages','pleasecheckEntriesinRed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
		}
	}

public function editDetails():void
{
	var selectedRow:ArrayCollection=commonFunction.getSelectedRowData(entityProgramGrid);
	var c:int=selectedRow.length;
	
	var editPopup:EditEntityProgram=EditEntityProgram(PopUpManager.createPopUp(this,EditEntityProgram,true));
	
	editPopup.entityId = offeredByCombo.selectedItem.entityId;
	editPopup.programId = selectedRow[0].programId;
	editPopup.branchId = selectedRow[0].branchId;
	editPopup.specializationId = selectedRow[0].specializationId;
	editPopup.programLabel.text=selectedRow[0].programName.toString();
	editPopup.branchLabel.text=selectedRow[0].branchName.toString();
	editPopup.specializationLabel.text=selectedRow[0].specializationName;
	editPopup.offeredByLabel.text=offeredByCombo.selectedLabel;
	editPopup.desiredEmployee = selectedRow[0].mentor.toString();
	editPopup.seatsText.text=selectedRow[0].seats.toString();
	editPopup.parentFun=getEntityDetails;
	editPopup.employeeListAC = employeeListAC;
	editPopup.editButton = editButton;
	editPopup.deleteButton = deleteButton;
	
	PopUpManager.centerPopUp(editPopup);	
}

public function deleteDetails():void
{
	var selectedRow:ArrayCollection=commonFunction.getSelectedRowData(entityProgramGrid);
	//var s:String="";
			var entityDetails:Array=new Array();
			var i:int = 0;
			var cl:int=0;
		for each(var obj:Object in selectedRow)
		{
			entityDetails[i] = new Array();
				cl=0;
			entityDetails[i][cl] = obj.programId;
		        cl++;
		    entityDetails[i][cl] = obj.branchId;
		        cl++;
		    entityDetails[i][cl] = obj.specializationId;
		        cl++;
	        entityDetails[i][cl] = ";";
		        i++;
		}
			recordsToDelete.removeAll();
		 for(var k:int = 0; k < entityDetails.length; k++){
					recordsToDelete.addItem(entityDetails[k]);
					trace("entity details="+entityDetails[k]);
				}
				Alert.show(commonFunction.getMessages('areyousure'), 
				commonFunction.getConstants('confirm'), 3, this, deleteProgramOfferedBy, questionIcon);
}

public function changeEntity():void
{
	programCombo.enabled=true;
	programCombo.selectedIndex=-1;
	entityDetailListAC.removeAll();
	entityProgramPanel.visible=false;
}