/**
 * @(#) EodControlAS.as
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
	import common.validations.CommonValidations;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.events.CloseEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.validators.Validator;
			
	[Embed(source="/images/error.png")]public var errorIcon:Class;
	[Embed(source="/images/success.png")]private var successIcon:Class;
	[Embed(source="/images/question.png")]private var questionIcon:Class;
	[Embed(source="/images/warning.png")]private var warningIcon:Class;
	
	[Bindable]public var urlPrefix:String;
	private var eodControlDetailsArrCol:ArrayCollection;
	private var stepFrequencyXML:XML;
	private var methodToRunXML:XML;
	private var selectedRecordArrColl:ArrayCollection;
	private var buildDayArrCol:ArrayCollection;
	
	/**
	 * @private
	 * this method is called on module creation complete.
	 */
	private function moduleCreationCompleteHandler():void{
		urlPrefix=commonFunction.getConstants('url')+"/eodcontrol/";
		methodRemark.text="";
		var param:Object=new Object();
		param["time"] = new Date();
		getEodControlDetails.send(param);
	}
			
	/**
	 * @private
	 * this method is called when a result come from the server. 
	 * @param event a Result event object.
	 */
	private function getEodControlDetailsResultHandler(event:ResultEvent):void{
		eodControlGrid.dataProvider=null;
		var eodControlDetailsXML:XML = event.result as XML;
		if(eodControlDetailsXML.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}
    	
		if(eodControlDetailsXML.message=="norecordFound"){
//			Alert.show(commonFunction.getMessages('noRecordFound'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else{
			eodControlDetailsArrCol = new ArrayCollection();
		
			for each (var o:Object in eodControlDetailsXML.eodControlDetail)
			{
				eodControlDetailsArrCol.addItem({select:false,phase:o.phase,dependentPhase:o.dependentPhase, step:o.step,
				stepFrequencyCode:o.stepFrequencyCode, stepFrequencyDescription:o.stepFrequencyDescription, periodActiveFrom:o.periodActiveFrom, 
				periodActiveTo:o.periodActiveTo, methodToRunCode:o.methodToRunCode, methodToRunDescription:o.methodToRunDescription, 
				status:o.status, day:o.buildDay, mmdd:o.mmdd, flag:o.flag});
			}
			
			eodControlGrid.dataProvider=eodControlDetailsArrCol;
			editButton.enabled=false;
			statusButton.enabled=false;
			deleteButton.enabled=false;	
		}
				
		var param:Object = new Object();
		param["time"]= new Date();
		getStepFrequencyHttpService.send(param);				
	}
			
	/**
	 * @private
	 * this method is called on add save button click event. 
	 */
	private function saveButtonClickHandler():void{
		if(validateInput()){
			var param:Object = new Object();
			param["phase"]=phaseNS.value;
			param["dependentPhase"]=dependentPhaseNS.value;
			param["step"]=stepNS.value;
			param["stepFrequencyCode"]=stepFrequencyXML.frequency.(stepFrequencyDescription==stepFrequencyCB.selectedLabel).stepFrequencyCode;
			param["periodActiveFrom"]=dateFormatter.format(periodActiveFromDF.text);
			param["periodActiveTo"]=dateFormatter.format(peridActiveToTF.text);
			param["methodToRunCode"]=methodToRunXML.methods.(methodToRunDescription==methodToRunCB.selectedLabel).methodToRunCode;
			param["methodRemark"]=methodRemark.text;
			var frequencyCode:String = stepFrequencyXML.frequency.(stepFrequencyDescription==stepFrequencyCB.selectedLabel).stepFrequencyCode;
			if(frequencyCode=="DLY"){
				param["info"]="daily";						
			}
			else if(frequencyCode=="WKY"){
				param["info"]="week";
				for each(var obj:Object in buildDayArrCol){
					if(obj.description==buildTimeCB.selectedLabel){
						param["buildTime"]=obj.code;
						break;		
					}
				}									
			}
			else if(frequencyCode=="MTH"){
				param["info"]="month";
				for each(var obj:Object in buildDayArrCol){
					if(obj.description==buildTimeCB.selectedLabel){
						param["buildTime"]=obj.code;
						break;		
					}
				}
			}
			else if(frequencyCode=="YRL"){
				param["info"]="mmdd";
				param["buildTime"]=mmdd.selectedValue	
			}
			else{
				param["info"]="";
			}
			saveDetailsHttpService.send(param);	
		}
		else{
			Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
		}				
	}
			
	/**
	 * @private
	 * this method is used to validate the form inputs.
	 */
	private function validateInput():Boolean{
		var validate:CommonValidations=new CommonValidations();
		var count:int=0;
		var validateArray:Array = new Array();
		var frequencyCode:String = stepFrequencyXML.frequency.(stepFrequencyDescription==stepFrequencyCB.selectedLabel).stepFrequencyCode;
			   
		if(frequencyCode!="" && methodToRunCB.selectedIndex!=-1){
			if(frequencyCode=="WKY" || frequencyCode=="MTH"){
				validateArray.push(phaseNSValidator);
				validateArray.push(stepNSValidator);
				validateArray.push(stepFrequencyCBValidator);
				validateArray.push(periodActiveFromDFValidator);
				validateArray.push(periodActiveToDFValidator);
				validateArray.push(methodToRunValidator);
				validateArray.push(buildTimeValidator);
			}
			else if(frequencyCode=="YRL"){
				validateArray.push(phaseNSValidator);
				validateArray.push(stepNSValidator);
				validateArray.push(stepFrequencyCBValidator);
				validateArray.push(periodActiveFromDFValidator);
				validateArray.push(periodActiveToDFValidator);
				validateArray.push(methodToRunValidator);
				validateArray.push(mmddValidator);
			}	
		}
		else{
			validateArray.push(phaseNSValidator);
			validateArray.push(stepNSValidator);
			validateArray.push(stepFrequencyCBValidator);
			validateArray.push(periodActiveFromDFValidator);
			validateArray.push(periodActiveToDFValidator);
			validateArray.push(methodToRunValidator);
		}
		
		
		
		if(Validator.validateAll(validateArray).length>0){
          	count++;  	
        }
        
        if(dateValueChecker(peridActiveToTF.text)){
        	if(validate.datechecker(periodActiveFromDF, periodActiveToDF)){
	        	periodActiveFromDF.errorString=commonFunction.getMessages('startLessEndDate');
	        	errorLabel.text="X";
	        	errorLabel.setStyle("color", "red");
	        	errorLabel.errorString=commonFunction.getMessages('startLessEndDate');
	        	count++;
	        }
	        else{
	        	periodActiveFromDF.errorString="";
	        	errorLabel.text="";
	        }
        }
        else{
        	count++;
        }
	        if(count>0){
	        	return false;
	        }
	        else{
	        	return true;	
	        }
	}
	
	public function dateValueChecker(input:String):Boolean{
		var validformat:String="/^\d{2}\/\d{2}\/\d{4}$/"; //Basic check for format validity
		var dateArray:Array = input.split("/",3);
		var yearfield:int = dateArray.pop();
		var dayfield:int = dateArray.pop();
		var monthfield:int = dateArray.pop(); 
		var dayobj:Date = new Date(yearfield, monthfield-1, dayfield)
		var flag:Boolean=false;
		
		if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)){
			flag=false;
		}
		else{
			flag=true;
		}
		return flag;
	}
					
	/**
	 * @private
	 * this method is called when a result come from the server. 
	 * @param event a Result event object.
	 */
	private function saveDetailsHttpServiceResultHandler(event:ResultEvent):void{
		var eodControlDetailsXML:XML = event.result as XML;
		if(eodControlDetailsXML.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}
		
		if(eodControlDetailsXML.message=="success"){
			Alert.show(commonFunction.getMessages('savedSuccessfully'), commonFunction.getMessages('success'),4,this,confirm,successIcon);
			moduleCreationCompleteHandler();				
		}
		else if(eodControlDetailsXML.message=="duplicateData"){
			Alert.show(commonFunction.getMessages('recordAlreadyExist'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else if(eodControlDetailsXML.message=="updateStatus"){
			Alert.show(commonFunction.getMessages('inactiveActiveRecord'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else{
			Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		
	}
			
	private function confirm(event:CloseEvent):void{
		if(event.detail == Alert.OK){
			Alert.show(commonFunction.getMessages('insertMoreStepConfirm'), commonFunction.getConstants('confirm'), 3, this, resetForm2,questionIcon);
		}
		Mask.close();
	}
	
	private function resetForm2(event:CloseEvent):void{
		if(event.detail==Alert.YES){
			stepNS.value=0;
			stepFrequencyCB.selectedIndex=-1;
			periodActiveFromDF.text="";
			periodActiveToDF.text="";
			methodToRunCB.selectedIndex=-1;	
		}
		else{
			resetForm();
		}
	}
	
	/**
	 * @private
	 * this method is called when a result come from the server. 
	 * @param event a Result event object.
	 */
	private function getStepFrequencyHttpServiceResultHandler(event:ResultEvent):void{
		stepFrequencyXML =  event.result as XML;
		if(stepFrequencyXML.message=="norecordFound"){
			Alert.show(commonFunction.getMessages('noRecordFound'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else{
			stepFrequencyCB.dataProvider=stepFrequencyXML.frequency.stepFrequencyDescription;
		}
		var param:Object =new Object();
		param["time"] = new Date();				
		getMethodsToRunHttpService.send(param);
	}
			
	/**
	 * @private
	 * this method is called when a result come from the server. 
	 * @param event a Result event object.
	 */
	private function getMethodsToRunHttpServiceResultHandler(event:ResultEvent):void{
		methodToRunXML =  event.result as XML;
		if(methodToRunXML.message=="norecordFound"){
			Alert.show(commonFunction.getMessages('noRecordFound'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else{
			methodToRunCB.dataProvider=methodToRunXML.methods.methodToRunDescription;
		}
	}
			
	/**
	 * @private
	 * this method is called on add edit button click event. 
	 */
	private function editButtonClickHandler():void{
		
		var gridData:ArrayCollection=commonFunction.getSelectedRowData(eodControlGrid);
		var popUpWin:EditWindowEodControl=EditWindowEodControl(PopUpManager.createPopUp(this, EditWindowEodControl, true));
		
		popUpWin.gridRecords=gridData;
		popUpWin.phaseLabel.text = gridData[0].phase;
		popUpWin.dependentPhaseNS.value = gridData[0].dependentPhase;
		popUpWin.stepLabel.text=gridData[0].step;
		popUpWin.stepFrequencyXML = stepFrequencyXML;
		popUpWin.periodActiveFromDF.text = gridData[0].periodActiveFrom;
		popUpWin.periodActiveToDF.text = gridData[0].periodActiveTo;
		popUpWin.methodToRunLabel.text = gridData[0].methodToRunDescription;
		popUpWin.gridRefreshFunction = 	moduleCreationCompleteHandler;
		popUpWin.selectedDay = gridData[0].day;
		popUpWin.selectedMMDD = gridData[0].mmdd;
		popUpWin.flag = gridData[0].flag;
		popUpWin.setFocus();	
		PopUpManager.centerPopUp(popUpWin);  
	}
	
	private function statusButtonClickHandler():void{
		Alert.show(commonFunction.getMessages('statusChangeConfirmMessage'), commonFunction.getMessages('confirm'), 3, this, inactivateRecords,questionIcon);
	}
	
	/**
	 * @private
	 * this method is called on Alert Box Close Event
	 * this method will inactivate the selected record.
	 * @param event a close event object.
	 */
	private function inactivateRecords(event:CloseEvent):void
	{
		if(event.detail == Alert.YES)
		{
			var gridData:ArrayCollection=commonFunction.getSelectedRowData(eodControlGrid);
			selectedRecordArrColl=new ArrayCollection();
			
			for each(var o:Object in gridData){
				var status:String="";
				if(o.status=="Inactive"){
					status="INA";
				}else{
					status="ACT";
				}
				selectedRecordArrColl.addItem([o.phase, o.step, o.methodToRunCode, status]);
			}
			
			var param:Object=new Object();
			param["time"]=new Date();
			param["recordArrayColl"]=selectedRecordArrColl;
			changeEodControlStatusHttpService.send(param);
		} 
	}
	
	/**
	 * @private
	 * this method is called when a result come from the server. 
	 * @param event a Result event object.
	 */
	private function changeEodControlStatusResultHandler(event:ResultEvent):void{
		var resultData:XML=event.result as XML;
		if(resultData.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}
    	
    	if(resultData.message=="success"){
    		Alert.show(commonFunction.getMessages('statusChangeMessage'), commonFunction.getMessages('success'),4,null,null,successIcon);
    		moduleCreationCompleteHandler();
    	}
    	else if(resultData.message=="makeInactive"){
    		Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),4,null,null,errorIcon);
    	}
    	else{
    		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
    	}					
	}
		
	/**
	 * @private
	 * this method is called on add save button click event. 
	 */
	private function deleteButtonClickHandler():void{
		Alert.show(commonFunction.getMessages('deleteConfirmMessage'), commonFunction.getMessages('confirm'), 3, this, deleteRecords,questionIcon);				
	}
		
		/**
	 * @private
	 * this method is called on Alert Box Close Event
	 * this method will delete the selected record.
	 * @param event a close event object.
	 */
	private function deleteRecords(event:CloseEvent):void
	{
		if(event.detail == Alert.YES)
		{
			var gridData:ArrayCollection=commonFunction.getSelectedRowData(eodControlGrid);
			selectedRecordArrColl=new ArrayCollection();
			
			for each(var o:Object in gridData){
				var status:String="";
				if(o.status=="Inactive"){
					status="INA";
				}else{
					status="ACT";
				}
				selectedRecordArrColl.addItem([o.phase, o.step, o.methodToRunCode,status]);
			}
			
			var param:Object=new Object();
			param["time"] =  new Date();
			param["recordArrayColl"]=selectedRecordArrColl;				
			deleteEodControlDetailsHttpService.send(param);
		} 
	}
	
	/**
	 * @private
	 * this method is called when a result come from the server. 
	 * @param event a Result event object.
	 */
	private function deleteEodControlDetailsHttpServiceResultHandler(event:ResultEvent):void{
		var resultData:XML=event.result as XML;
		if(resultData.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}
    	
    	if(resultData.message=="success"){
    		Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'), commonFunction.getMessages('success'),4,null,null,successIcon);
    		moduleCreationCompleteHandler();
    	}
    	else{
    		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
    	}	
	}
	
	private function stepFrequencyChangeHandler():void{
		var frequencyCode:String = stepFrequencyXML.frequency.(stepFrequencyDescription==stepFrequencyCB.selectedLabel).stepFrequencyCode;
						
		if(frequencyCode=="DLY"){
			buildTimelabel.visible=false;
			buildTimeCB.visible=false;	
			mmdd.visible=false;
		}
		
		else if(frequencyCode=="WKY"){
			buildTimelabel.visible=true;
			buildTimeCB.visible=true;
			mmdd.visible=false;
			buildDayArrCol=new ArrayCollection();
			buildDayArrCol.addItem({description:"Monday",code:"MON"});
			buildDayArrCol.addItem({description:"Tuesday",code:"TUE"});
			buildDayArrCol.addItem({description:"Wednesday",code:"WED"});
			buildDayArrCol.addItem({description:"Thursday",code:"THU"});
			buildDayArrCol.addItem({description:"Friday",code:"FRI"});
			buildDayArrCol.addItem({description:"Saturday",code:"SAT"});
			buildDayArrCol.addItem({description:"Sunday",code:"SUN"});
			buildDayArrCol.addItem({description:"Any Day",code:"ANY"});
			
			buildTimelabel.text=commonFunction.getConstants('buildDay');
			buildTimeCB.dataProvider=buildDayArrCol;
			buildTimeCB.labelField="description";										
		}
		else if(frequencyCode=="MTH"){
			buildTimelabel.visible=true;
			buildTimeCB.visible=true;
			mmdd.visible=false;
			buildDayArrCol=new ArrayCollection();
			buildDayArrCol.addItem({description:"Start of Month",code:"S"});
			buildDayArrCol.addItem({description:"End of Month",code:"E"});
								
			buildTimelabel.text=commonFunction.getConstants('buildTime');
			buildTimeCB.dataProvider=buildDayArrCol;
			buildTimeCB.labelField="description";
		}
		else if(frequencyCode=="YRL"){
			buildTimelabel.visible=true;
			buildTimeCB.visible=false;
			mmdd.monthsCombo.selectedIndex=-1;
			mmdd.dateCombo.selectedIndex=-1;
			mmdd.visible=true;
			buildTimelabel.text=commonFunction.getConstants('selectBuildDate');					
		}
		else{
			
		}
	}
	
	/**
	 * @private
	 * this method is called on add reset button click event. 
	 */
	private function resetForm():void{
		phaseNS.value=0;
		dependentPhaseNS.value=0;
		stepNS.value=0;
		stepFrequencyCB.selectedIndex=-1;
		periodActiveFromDF.text="";
		periodActiveToDF.text="";
		methodToRunCB.selectedIndex=-1;						
	}
	
	/**
	 * @private
	 * this method is called when a failure come from the server. 
	 * @param event a fault event object.
	 */	
	private function httpServiceFaultHandler(event:FaultEvent):void{
		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
		