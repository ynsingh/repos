import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

import programMaster.BranchWithSpecialization;
import programMaster.ProgramMaster;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

var pm:ProgramMaster=new ProgramMaster();
var bws:BranchWithSpecialization=new BranchWithSpecialization();

 public var progId:String="";
 public var branchId:String="";
  public var specId:String="";

[Bindable]
public var branchSpecXml:XML;
public var programIdForBranchList:String;

var bsAC:ArrayCollection;
[Bindable]var branchXML:XML;
[Bindable]var specXML:XML;
[Bindable]var semXML:XML;

	public function changeProgram():void{
		programCodeText.text="";
		programCodeText.errorString="";
		//edited by nikita to reset the error display
		branchCombo.errorString='';
		specializationCombo.errorString='';
		ActiveSemCombo.errorString='';
		//Nikita editing ended
		programCodeText.text=pm.codeXML.branch.(branchName==programNameComboBox.selectedLabel).branchCode;
		httpBranchSpecList();
		branchCombo.enabled=true;
		specializationCombo.enabled=true;
		ActiveSemCombo.enabled=true;
		addButton.enabled=true;
		branchCombo.selectedIndex=-1;
		specializationCombo.selectedIndex=-1;
		ActiveSemCombo.selectedIndex=-1;
	}

	public function showDetails():void{
		if(!(programNameComboBox.selectedIndex==-1)){
			programNameComboBox.errorString="";	
//	    	branchSpecializationPanel.visible=true;
		    httpBranchSpecList();
		    branchCombo.enabled=true;
		    specializationCombo.enabled=true;
		    ActiveSemCombo.enabled=true;
		    addButton.enabled=true;
		    deleteButton.enabled=false;
		}
		else{
			programNameComboBox.errorString=commonFunction.getMessages('noRecordSelected');
			Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
    	}
    }

	public function deleteDetails():void{
		var selectedValues:ArrayCollection=	commonFunction.getSelectedRowData(branchSpecializationGrid);
		for each(var obj:Object in  selectedValues){
			progId+=obj.programId+"|";
			branchId+=obj.branchCode+"|";
			specId+=obj.specCode+"|";
		}
		Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onDelete,questionIcon);
	}

	public function onDelete(event:CloseEvent):void{
		if(event.detail==Alert.YES){
			var params:Object=new Object();
			params["progId"]=progId;
			params["branchId"]=branchId;
			params["specId"]=specId;
			
			deleteBranch(params);
		}
	}

	public function setSpecialization():void{
//		var noneStatus:String=commonFunction.getConstants('none');
//		if(specializationCombo.selectedItem.branchCode==noneStatus){
//			ActiveSemCombo.selectedIndex=-1;
//			ActiveSemCombo.enabled=false;
//		}
//		else{
//			ActiveSemCombo.enabled=true;
//			ActiveSemCombo.selectedIndex=0;
//		}
	}

	public function addBranch():void{
		if(Validator.validateAll([branchValidator,specializationValidator]).length==0){
			if( !(((branchCombo.selectedIndex<0)||(specializationCombo.selectedIndex<0))
		    		||((branchCombo.selectedItem.branchCode==commonFunction.getConstants('none')) 
		    		&& (specializationCombo.selectedItem.branchCode==commonFunction.getConstants('none')) )) )
			{
				var c:int=0;
				for each (var obj:Object in bsAC){
					if(obj.branchCode.toString() == branchCombo.selectedItem.branchCode.toString() && obj.specCode.toString()==specializationCombo.selectedItem.branchCode.toString()){
						c++;
					}
				}
			
				if(c==0){
					Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);	
				}
				else{
					Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				}
			}
			else{
				Alert.show(commonFunction.getMessages('invalidEntry'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	        }
		}
		else{
			Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
	}

	public function addStartDate():void{
		Alert.show(commonFunction.getMessages('successOnInsert'),commonFunction.getMessages('success'),0,null,null,successIcon);
	}

	private function loadCombo():void{
		pm.httpProgramCode.url=commonFunction.getConstants('url')+ "/programmaster/methodprogList.htm";
		pm.httpProgramCodeList();
		pm.codeListCb=programNameComboBox;
		httpBranch();	
	}

	public function onOK(event:CloseEvent):void{
		if(event.detail==Alert.YES){				
			var inputObj:Object=new Object();
			inputObj["userId"]=pm.userId;
			inputObj["programId"]=pm.codeXML.branch.(branchName==programNameComboBox.selectedLabel).programId;
			inputObj["programCode"]=programCodeText.text;
			inputObj["branchCode"]=branchCombo.selectedItem.branchCode;
			inputObj["specCode"]=specializationCombo.selectedItem.branchCode;
			if(specializationCombo.selectedItem.branchCode==commonFunction.getConstants('none')){
				inputObj["activeSem"]=";";
			}
			else{
				inputObj["activeSem"]=ActiveSemCombo.selectedItem.branchCode;	
			}
			//	Alert.show("not duplicate"+c);
			addNewBranch(inputObj);
		}
	}


	/**
	 * Function for getting program code list
	 */
	public function httpBranchSpecList():void{
		var params:Object=new Object();
		params["userId"]=pm.userId;
	//	if(programNameComboBox.selectedIndex>0){
		params["programId"]=pm.codeXML.branch.(branchName==programNameComboBox.selectedLabel).programId;
	//	}
	//	if(programCodeText.text != ""){
	//		params["criteria"]="code";
	//	params["programCode"]=programCodeText.text
	//	}else{
			params["criteria"]="not code";
	//	}
			params["time"]=new Date().getTime();
		httpBranchSpec.send(params);
	}

	private function faultHandler_BranchSpec(event:FaultEvent):void{
		Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
	}
    
	private function resultHandler_BranchSpec(event:ResultEvent):void{
		branchSpecXml=event.result as XML;  
   		if(branchSpecXml.sessionConfirm == true){
            Alert.show(commonFunction.getMessages('sessionInactive'));
          	this.parentDocument.vStack.selectedIndex=0;
          	this.parentDocument.loaderCanvas.removeAllChildren();
        }
          
 		bsAC=new ArrayCollection();
		var flag:Boolean=true;
   		for each (var object:Object in branchSpecXml.bs){
   			bsAC.addItem({select:false,programId:object.programId,programCode:object.programCode,programName:object.programName,
   			branchName:object.branchName,specName:object.specName,activeSemester:object.activeSemester,
   			branchCode:object.branchCode,specCode:object.specCode});
   			
   			programIdForBranchList=object.programId;
   		}
		branchSpecializationGrid.dataProvider=bsAC;
   }

	

	/**
	 * Function for getting branch list
	 */
	public function httpBranch():void{
		var params:Object=new Object();
		params["userId"]=pm.userId;
		params["time"]=new Date().getTime();
		httpBranchList.send(params);
	}
	private function faultHandler_Branch(event:FaultEvent):void{
    	Alert.show(event.fault.message,commonFunction.getMessages('error'));
    }
    
	private function resultHandler_Branch(event:ResultEvent):void{
		branchXML=event.result as XML;
		if(branchXML.sessionConfirm == true){
	    	Alert.show(commonFunction.getMessages('sessionInactive'));
	        this.parentDocument.vStack.selectedIndex=0;
	        this.parentDocument.loaderCanvas.removeAllChildren();
	    }
		branchCombo.dataProvider=branchXML.branch;
		httpSpec();
	}
   
   
	/**
	 * Function for getting specialization list
	 */
	 private function httpSpec():void{
		var params:Object=new Object();
		params["userId"]=pm.userId;
		params["time"]=new Date().getTime();
		httpSpecList.send(params);
	}

	private function faultHandler_Spec(event:FaultEvent):void{
		Alert.show(event.fault.message,commonFunction.getMessages('error'));
	}

	private function resultHandler_Spec(event:ResultEvent):void{
		specXML=event.result as XML;
		if(specXML.sessionConfirm == true){
	    	Alert.show(commonFunction.getMessages('sessionInactive'));
	        this.parentDocument.vStack.selectedIndex=0;
	        this.parentDocument.loaderCanvas.removeAllChildren();
	    }  
	  	specializationCombo.dataProvider=specXML.branch;
	  	httpSemester();
	}
   
	/**
	 * Function for getting semester list
	 */
	private function httpSemester():void{
		var params:Object=new Object();
		params["userId"]=pm.userId;
			params["time"]=new Date().getTime();
		httpSemList.send(params);
	}

	private function faultHandler_Semester(event:FaultEvent):void{
		Alert.show(event.fault.message,commonFunction.getMessages('error'));
	}
    
	private function resultHandler_Semester(event:ResultEvent):void{
		semXML=event.result as XML;
  		if(semXML.sessionConfirm == true){
        	Alert.show(commonFunction.getMessages('sessionInactive'));
            this.parentDocument.vStack.selectedIndex=0;
            this.parentDocument.loaderCanvas.removeAllChildren();
        }  
		ActiveSemCombo.dataProvider=semXML.branch;
	}

	private function addNewBranch(params:Object):void{
		params["time"]=new Date().getTime();
		httpAddNewBranch.send(params);
	}

	private function faultHandler_NewBranch(event:FaultEvent):void{
		Alert.show(event.fault.message,commonFunction.getMessages('error'));
	}
    
	private function resultHandler_NewBranch(event:ResultEvent):void{
		var resultXML:XML=event.result as XML;
		if(resultXML.sessionConfirm == true){
	    	Alert.show(commonFunction.getMessages('sessionInactive'));
        	this.parentDocument.vStack.selectedIndex=0;
        	this.parentDocument.loaderCanvas.removeAllChildren();
    	}  
	
		if(resultXML.exception.exceptionstring=="fail"){
			Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'));
		}else{
			Alert.show(commonFunction.getMessages('savedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
			branchCombo.selectedIndex=-1;
			specializationCombo.selectedIndex=-1;
			ActiveSemCombo.selectedIndex=-1;
			ActiveSemCombo.errorString="";
			deleteButton.enabled=false;
			showDetails();
		}  
	}

	/*
	* function to delete branch specialization details
	*/
	private function deleteBranch(params:Object):void{
		params["time"]=new Date().getTime();
	 	httpDeleteBranch.send(params);
	}

	private function faultHandler_DeleteBranch(event:FaultEvent):void{
		Alert.show(event.fault.message,commonFunction.getMessages('error'));
	}

	private function resultHandler_DeleteBranch(event:ResultEvent):void{
		progId=new String;
		branchId=new String;
		specId=new String;
		var resultXml:XML=event.result as XML;
		if(resultXml.sessionConfirm == true){
	    	Alert.show(commonFunction.getMessages('sessionInactive'));
	        this.parentDocument.vStack.selectedIndex=0;
	        this.parentDocument.loaderCanvas.removeAllChildren();
	    } 
	    
	    if(resultXml.exception.exceptionstring=="ie"){
			Alert.show(commonFunction.getMessages('dependencyDeleteError'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}else{
	    	Alert.show(commonFunction.getMessages('recordsDeletedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);  
			showDetails();
		}
	}

