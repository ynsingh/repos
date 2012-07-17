// ActionScript file
import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.ComboBox;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import programMaster.ProgramMaster;

[Bindable]
var branchXML:XML;
[Bindable]
var specXML:XML;
[Bindable]
var semXML:XML;
var progMasterObj:ProgramMaster=new ProgramMaster();
var branchCB:ComboBox=new ComboBox();
var specCB:ComboBox=new ComboBox();
var activeCB:ComboBox=new ComboBox();



/**
 * Function for getting branch list
 */
public function httpBranch():void{
	var params:Object=new Object();
	params["userId"]=progMasterObj.userId;
		params["time"]=new Date().getTime();
	httpBranchList.send(params);
}
private function faultHandler_Branch(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_Branch(event:ResultEvent):void{
	  
   branchXML=event.result as XML;
 if(branchXML.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
  var branchAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in branchXML.branch){
   		branchAC.addItem({branchName:object.branchName,branchCode:object.branchCode});
   	}
  try{
  branchCombo.dataProvider=branchXML.branch;
  
  branchCB.dataProvider=branchXML.branch.branchName;
  }catch(e:Error){}
httpSpec();

   }
   
   
/**
 * Function for getting specialization list
 */
 private function httpSpec():void{
	var params:Object=new Object();
	params["userId"]=progMasterObj.userId;
		params["time"]=new Date().getTime();
	httpSpecList.send(params);
}
private function faultHandler_Spec(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_Spec(event:ResultEvent):void{
	
   specXML=event.result as XML;
 if(specXML.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
  var specAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in specXML.branch){
   		specAC.addItem({specName:object.specName,specCode:object.specCode});
   	}
   try{
  specializationCombo.dataProvider=specXML.branch;
  specCB.dataProvider=specXML.branch.branchName;
   }catch(e:Error){}
httpSemester();
   }
   
   
   
   /**
 * Function for getting semester list
 */
 private function httpSemester():void{
	var params:Object=new Object();
	params["userId"]=progMasterObj.userId;
		params["time"]=new Date().getTime();
	httpSemList.send(params);
}
private function faultHandler_Semester(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_Semester(event:ResultEvent):void{
	  
   semXML=event.result as XML;
    if(semXML.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
  var semAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in semXML.branch){
   		semAC.addItem({semName:object.semName,semCode:object.semCode});
   	}
   try{
  ActiveSemCombo.dataProvider=semXML.branch;
activeCB.dataProvider=semXML.branch.branchName;
 }catch(e:Error){}
   }