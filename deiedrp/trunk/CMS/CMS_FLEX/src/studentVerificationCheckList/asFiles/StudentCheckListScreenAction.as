        import common.commonFunction;
        
        import mx.controls.Alert;
        import mx.events.CloseEvent;
        import mx.events.DataGridEvent;
        import mx.managers.PopUpManager;
        import mx.rpc.events.FaultEvent;
        import mx.rpc.events.ResultEvent;
        
        import studentVerificationCheckList.StudentCheckList;
		[Embed(source="/images/error.png")]private var errorIcon:Class;
        [Embed(source="/images/success.png")]private var successIcon:Class;
        [Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
        public var changedIndexes:Array=new Array();
        public var parameters:Object=new Object();
        public var inputs:Object = new Object();
		public var refreshFunction:Function;
		
		[Bindable]public var url:String;
		var abc:StudentCheckList=new StudentCheckList();
		
		public function initializeUrl():void
		{
			url = commonFunction.getConstants('url');
		}		
		public function closeMe():void
		{
			
			PopUpManager.removePopUp(this);	
			refreshFunction();  	
		}
		
		public function getVerificationStatus(row:Object,col:DataGridColumn):String
		{
			if(row.verified==1)
			{
				return resourceManager.getString('Constants','verified');
			}
		    else if(row.verified==0)
			{
				return resourceManager.getString('Constants','notVerified');
			}
		    else
		    {
		    	row.verified=0;
		    	return resourceManager.getString('Constants','notVerified');
		    }
		}
		
		public function submitCheckListData():void
		{
			var gridData:Array=new Array;
			for each(var o:Object in checkListGrid.dataProvider)
			{
				var temp:Array=new Array();
				temp.push(o.component_code);
				temp.push(o.component_description);
				temp.push(o.verified);
				temp.push(o.notes);
				gridData.push(o);
			}
			
			changedIndexes.sort(Array.NUMERIC);
			var rowCount:int=changedIndexes.length;
			var s:String="";//temporary
			
			var componentCode:String="|";
			var compName:String="|";
			var verified:String="|";
			var notes:String="|";
			if(rowCount>0)
			{
			    for(var i:int=0;i<rowCount;i++)
			    {
			    	var rowData:Object=gridData[changedIndexes.pop()];
			
					componentCode+=rowData.component_code+"|";
				
					compName+=rowData.component_name+"|";	
				
					verified+=rowData.verified+"|";
				
				if(rowData.notes==""){
					rowData.notes="modified";
				}
					notes+=rowData.notes+"|";
			    }
			    
			    var param:Object={};
			    param["component"]=componentCode;
			    param["verified"]=verified;
			    param["notes"]=notes;
			    param["total"]=rowCount;
			    param["registrationNumber"]=parameters["registrationNumber"];
			    param["enrollment_number"]= parameters["enrollment_number"];
			    
			    if(parameters["admission_mode"].toString().toUpperCase()=="SWT"){
			    	
			    param["registrationNumber"]=parameters["roll_number"];
			    }
			    else{			    
			    	param["registrationNumber"]=parameters["registrationNumber"];			  
			    }
			    param["roll_number"]=parameters["roll_number"];
			    param["sequence_number"]=parameters["sequence_number"];
			    param["admission_mode"]=parameters["admission_mode"];
			    			   
			    	SubmitVerificationComp(param);
			    		    			   
			}
			else
			{
				Alert.show(resourceManager.getString('Messages','noRecordChanged'),resourceManager.getString('Constants','error'),0,null,null,errorIcon);
			}
		}
		
		public function setChangedIndex(event:DataGridEvent):void
		{
			var i:int=changedIndexes.indexOf(event.rowIndex);
			if(i<0)
			{
			changedIndexes.push(event.rowIndex);
			}
		}
		
		/*
		Submitting changed data to server
		*/
		
		  	private function SubmitVerificationComp(params:Object):void {    
      
         httpSubmitVerificationComponents.send(params);
	}
		
	private function faultHandler_SubmitVerificationComponents(event:FaultEvent):void{
        Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
    }
   private function resultHandler_SubmitVerificationComponents(event:ResultEvent):void{  
   	   	  	
   Alert.show(commonFunction.getMessages('submittedSuccessfully'),resourceManager.getString('Constants','success'),4,this,closed,successIcon);  

//     closeMe();
      }	
      
      public function closed(event:CloseEvent):void{
      	
      	closeMe();
      }
    		
		
		