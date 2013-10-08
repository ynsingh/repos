        import common.commonFunction;
        
        import mx.collections.ArrayCollection;
        import mx.controls.Alert;
        import mx.controls.DataGrid;
        import mx.controls.LinkButton;
        import mx.events.CloseEvent;
        import mx.events.ListEvent;
        import mx.managers.PopUpManager;
        import mx.rpc.events.FaultEvent;
        import mx.rpc.events.ResultEvent;
        
        import studentVerificationCheckList.enrollmentDetails;
        
        [Bindable]public var ss:LinkButton=new LinkButton();
        [Embed(source="/images/infoIcon.png")]private var infoIcon:Class;		
		[Embed(source="/images/error.png")]private var errorIcon:Class;
        [Embed(source="/images/success.png")]private var successIcon:Class;
        [Embed(source="/images/question1.png")]private var questionIcon:Class;
        public var singleStudentData:Object = {};
        public var inputs:Object=new Object();
        public var inputs2:Object=new Object();
        public var obj:Object = {};
        
        public var checked:String;
        [Bindable] protected var urlPrefix:String;        
        [Bindable] public var url:String;    
          
        public function showButton(event:ListEvent){                
        	var mode:String = studentCheckListGrid.dataProvider[event.rowIndex].admission_mode;
        	if((mode!="NEW")&&(mode!="SWT"))
        	{
//        	enrollButton.enabled=false;	
        	}
//        	else enrollButton.enabled=true;	
        }     
        public function open():void{
       	   
       	   if(studentCheckListGrid.selectedIndex<0)
			{
			    Alert.show(resourceManager.getString('Messages','noRecordSelected'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);	
			}
			else
			{	
       	var editWindow:enrollmentDetails =enrollmentDetails(PopUpManager.createPopUp(this,enrollmentDetails,true))
		
		editWindow.regNo=studentCheckListGrid.selectedItem.registrationNumber;
		editWindow.enrollNo=studentCheckListGrid.selectedItem.enrollment_number;
		editWindow.admissionMode=studentCheckListGrid.selectedItem.admission_mode;
		editWindow.height=this.parentDocument.loaderCanvas.height;
		PopUpManager.centerPopUp(editWindow); 			
		 }             	
       }
          
        /* Sending initial inputs to get student list */
       
        public function send_data(params:Object):void {        	
        	   urlPrefix=commonFunction.getConstants('url')+"/associatecoursewithinstructor/";
        	   url=commonFunction.getConstants('url');        	          	 
        	   httpXmlDataService.send(params);         
         }
        
        
        private function init():void{        	
           urlPrefix=commonFunction.getConstants('url')+"/associatecoursewithinstructor/";       
           url = commonFunction.getConstants('url');           
           obj["date"] = new Date().getTime(); 
           ss.enabled=false;          
           entityListHttpService.send(obj);                   	       	        
        }
        
        

		public function getRegistrationRollNo(row:Object,col:DataGridColumn):String
		{
			if(row.admission_mode.toString().toUpperCase()=="NEW")
			{
				return row.registrationNumber;
			}
			else
			{
			    return row.roll_number;			
			}
		}
		
		public function getName(row:Object,col:DataGridColumn):String
		{
			return row.first_name+" "+row.middle_name+" "+row.last_name;
		}
		
		public function getAdmissionMode(row:Object,col:DataGridColumn):String
		{
		
			if(row.admission_mode.toString().toUpperCase()=="NEW")
			{
				return resourceManager.getString('Constants','newAdmission');
			}
			else
			{
			    return resourceManager.getString('Constants','switchedAdmission');			
			}			
			
		}
		
		public function getRegistrationStatus(row:Object,col:DataGridColumn):String
		{
			if(row.status.toString().toUpperCase()=="V")
			{
				return resourceManager.getString('Constants','verified');
			}
		    else if(row.status.toString().toUpperCase()=="P")
		    {
		    	return resourceManager.getString('Constants','notVerified');
		    }
		    else if(row.status.toString().toUpperCase()=="N")
		    {
		    	return resourceManager.getString('Constants','notVerified');
		    }
		    else if(row.status.toString().toUpperCase()=="R")
		    {
		    	return resourceManager.getString('Constants','rejected');
		    }
		    else
		    {
		    	return resourceManager.getString('Constants','unknown');
		    }
		}   
	
		public function showStudentCheckList(studentCheckListGrid:DataGrid,xmlData:XML):void
		{
            if(studentCheckListGrid.selectedIndex<0)
			{
			    Alert.show(resourceManager.getString('Messages','noRecordSelected'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);	
			}
			else
			{									
					singleStudentData["registrationNumber"]=studentCheckListGrid.selectedItem.registrationNumber;
					singleStudentData["roll_number"]=studentCheckListGrid.selectedItem.roll_number;
					singleStudentData["admission_mode"]=studentCheckListGrid.selectedItem.admission_mode;
					singleStudentData["sequence_number"]=studentCheckListGrid.selectedItem.sequence_number;
					singleStudentData["enrollment_number"]=studentCheckListGrid.selectedItem.enrollment_number;
					singleStudentData["program_id"]=inputs["program_id"];
					var S:String=inputs["program_id"];
					getVerificationComp(singleStudentData);				
			}
		}
		
	/**
 * Method to fire the click event of the cancel button,remove UI from main window
 **/	
		public function closeScreen():void{
			
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
		
		
		public function showFullDetails(studentCheckListGrid:DataGrid):void
		{
			var rowIndex:int=studentCheckListGrid.selectedIndex;
			if(rowIndex<0)
			{
			Alert.show(resourceManager.getString('Messages','noRecordSelected'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);	
			}
			else
			{				
		
					var params:Object = {};
					params["registrationNumber"]=studentCheckListGrid.selectedItem.registrationNumber;
					params["roll_number"]=studentCheckListGrid.selectedItem.roll_number;
					params["admission_mode"]=studentCheckListGrid.selectedItem.admission_mode;
					params["sequence_number"]=studentCheckListGrid.selectedItem.sequence_number;
					params["program_id"]=inputs["program_id"];
					 params["semester_start_date"] = inputs["semester_start_date"];
     				 params["semester_end_date"] = inputs["semester_end_date"];
																		
			        getOptedCourse(params);
					getFullDetails(params);
				
			}
			
		}
		
		[Bindable]
    private var xmldata:XML;
    [Bindable]
    private var fullDetails:XML;
	[Bindable]
    private var courseDetails:XML;	
    	[Bindable]
    private var rejectDetails:XML;		
		
   		
		
	private function faultHandler(event:FaultEvent):void{
		//studentCheckListGrid.dataProvider = null;			
         Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
    }
   private function resultHandler(event:ResultEvent):void{  
   
        xmldata=event.result as XML;        
     if(xmldata.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}   	
        var studentExist:String = xmldata.Exception;
        if(studentExist == 'Y')
        {
        Alert.show(commonFunction.getMessages('noRecordFound'),commonFunction.getMessages('information'),0,null,null,infoIcon);
        studentCheckListGrid.dataProvider = null;
        }
       else{
        var i:int=0;       
             var a:ArrayCollection=new ArrayCollection();
        for each(var  s:Object in  xmldata.student){
        	var regRoll:String="";	
        	
        	if(s.admission_mode.toString().toUpperCase()=="NEW")
        	{
        		regRoll=s.registrationNumber;
			}
			else
			{
			    regRoll=s.roll_number;
        	}
        	var bol:Boolean=false;
        	if(s.status.toString().toUpperCase()=="V")
        	{
        		bol=false;
			}
			else
			{
			    bol=true;
        	}
        	a.addItem({registration_roll_number:regRoll,enrollment_number:s.enrollment_number,admission_mode:s.admission_mode,status:s.status,
        	student_name:s.student_name,father_name:s.father_name,date_of_birth:s.date_of_birth,program_name:s.program_name,
        	sequence_number:s.sequence_number,registrationNumber:s.registrationNumber,roll_number:s.roll_number,enabled:bol});
        }
        
        for each(var o:Object in a){
        	if(o.status.toString().toUpperCase() != "V"){
        		i++;
        	}
        }
        if(i==0){
        	Alert.show(commonFunction.getMessages('allStudentsForGivenProgramAreVerified'),commonFunction.getMessages('information'),0,null,null,infoIcon);
        }else{
        	Alert.show(commonFunction.getMessages('total')+" "+i+" "+commonFunction.getMessages('studentsAreUnverified'),commonFunction.getMessages('information'),0,null,null,infoIcon);
        }
        
         studentCheckListGrid.dataProvider=a;
         getRejectRecords();
       }         
      }
	
	
	public function getRejectRecords():void{
		
		inputs2["program_id"]=programList.program.(programName==programCombo.selectedLabel).programId;
        inputs2["entity_id"]= entityList.entity.(entityName == entityCombo.selectedLabel).entityId;
        inputs2["branch_id"]=branchList.branch.(branchName==branchCombo.selectedLabel).branchId;
        inputs2["specialization_id"]=specializationList.specialization.(specializationName==specializationCombo.selectedLabel).specializationId;
        inputs2["semester"]=semesterList.semester.(semesterName==semesterCombo.selectedLabel).semesterCode;
        inputs2["semester_start_date"]=semesterStartDateField.text;
        inputs2["semester_end_date"]=semesterEndDateField.text;
        inputs2["flag"]="R";         
        getRejectedStudents.send(inputs2);
	}
	
	
		
	public function rejectResultHandler(event:ResultEvent){
		
		rejectDetails= event.result as XML;
		 
     if(rejectDetails.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}   	
       
      
        var i:int=0;       
             var rr:ArrayCollection=new ArrayCollection();
        for each(var  s:Object in  rejectDetails.student){
        	var regRoll:String="";	
        	
        	if(s.admission_mode.toString().toUpperCase()=="NEW")
        	{
        		regRoll=s.registrationNumber;
			}
			else
			{
			    regRoll=s.roll_number;
        	}
        	var bol:Boolean=false;
       
        	rr.addItem({select:false,registration_roll_number:regRoll,enrollment_number:s.enrollment_number,admission_mode:s.admission_mode,status:s.status,
        	student_name:s.student_name,father_name:s.father_name,date_of_birth:s.date_of_birth,program_name:s.program_name,
        	sequence_number:s.sequence_number,registrationNumber:s.registrationNumber,roll_number:s.roll_number});
        }
        
        
       
        
         rejectionGrid.dataProvider=rr;
		
		
		
		
		
	}
		
		
	/* getting full details of student */
		
	private function getFullDetails(params:Object):void { 
         httpGetStudentFullDetails.send(params);
	}
		
	
   private function resultHandler_GetStudentFullDetails(event:ResultEvent):void{  
        fullDetails=event.result as XML;
         if(fullDetails.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}   	
 		showFullDetailsPopUp(fullDetails);
      }	
      
      private function showFullDetailsPopUp(fullDetails:XML):void {
      var studentDetailPopup:StudentDetailsScreen=StudentDetailsScreen(PopUpManager.createPopUp(this,StudentDetailsScreen,true));
                               
              studentDetailPopup.courseDetailsGrid.dataProvider=courseDetails.student;
               
                
                studentDetailPopup.height==this.parentDocument.loaderCanvas.height;
                studentDetailPopup.studentNameText.text= fullDetails.student.student_name;
                studentDetailPopup.fatherNameText.text= fullDetails.student.father_name;
                studentDetailPopup.motherNameText.text= fullDetails.student.mother_name;
                studentDetailPopup.enrolmentNoText.text= fullDetails.student.enrollment_number;
                studentDetailPopup.fromEntityText.text= fullDetails.student.old_entity_name;
                studentDetailPopup.toEntityText.text= fullDetails.student.entity_name;
                studentDetailPopup.fromProgramText.text= fullDetails.student.old_program_name;
                studentDetailPopup.toProgramText.text= fullDetails.student.program_name;
                studentDetailPopup.fromBranchText.text= fullDetails.student.old_branch_name;
                studentDetailPopup.toBranchText.text= fullDetails.student.branch_name;
                studentDetailPopup.fromSpecializationText.text= fullDetails.student.old_specialization;
                studentDetailPopup.toSpecializationText.text= fullDetails.student.new_specialization;
                studentDetailPopup.fromSemesterText.text= fullDetails.student.old_semester_code;
                studentDetailPopup.toSemesterText.text= fullDetails.student.semester_code;
//                studentDetailPopup.primaryEmailText.text= fullDetails.student.primary_email_id;
                studentDetailPopup.dobText.text= fullDetails.student.date_of_birth;
                studentDetailPopup.genderText.text= fullDetails.student.gender;
                studentDetailPopup.categoryText.text= fullDetails.student.category;
//                studentDetailPopup.secondaryEmailText.text= fullDetails.student.secondary_email_id;
                if(fullDetails.student.admission_mode.toString().toUpperCase()=="SWT")
                {
                	studentDetailPopup.admissionModeText.text=resourceManager.getString('Constants','switchedAdmission');
                    studentDetailPopup.registartionRollNoText.text= fullDetails.student.roll_number;
                }
                else
                {
                	studentDetailPopup.admissionModeText.text=resourceManager.getString('Constants','newAdmission');
                    studentDetailPopup.registartionRollNoText.text= fullDetails.student.registrationNumber;
                }
              //  Alert.show(fullDetails.student.status.toString());
                
                if(fullDetails.student.status.toString().toUpperCase()=="V")
                {
                	studentDetailPopup.registrationStatusText.text=resourceManager.getString('Constants','verified');
                }
                else if(fullDetails.student.status.toString().toUpperCase()=="P")
                {
                	studentDetailPopup.registrationStatusText.text=resourceManager.getString('Constants','notVerified');
                }
                else if(fullDetails.student.status.toString().toUpperCase()=="N")
                {
                	studentDetailPopup.registrationStatusText.text=resourceManager.getString('Constants','notVerified');
                }
                else
                {
                	studentDetailPopup.registrationStatusText.text=resourceManager.getString('Constants','unknown');
                }
                PopUpManager.centerPopUp(studentDetailPopup); 
      }
      
      /* Getting opted course list*/
      	private function getOptedCourse(params:Object):void {    
      	 httpGetOptedCourses.send(params);
	}
		

   private function resultHandler_GetCourseList(event:ResultEvent):void{
         courseDetails=event.result as XML;
          if(courseDetails.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}   	
      }	
      
     /* Getting verification components
     */ 
      
     [Bindable]	public var componentList:XML;
		
		  	private function getVerificationComp(params:Object):void {    
      
         httpGetVerificationComponents.send(params);
	}
		
	
   private function resultHandler_GetVerificationComponents(event:ResultEvent):void{
  
        componentList=event.result as XML;
         if(componentList.sessionConfirm == true)
    		    {
        		Alert.show(resourceManager.getString('Messages','sessionInactive'));
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}   	
       	var studentCheckListPopup:StudentCheckListScreen=StudentCheckListScreen(PopUpManager.createPopUp(this,StudentCheckListScreen,true));				
			studentCheckListPopup.parameters=singleStudentData;
			studentCheckListPopup.inputs = inputs;
			studentCheckListPopup.refreshFunction=semesterChange;
				studentCheckListPopup.checkListGrid.dataProvider=componentList.student;
				PopUpManager.centerPopUp(studentCheckListPopup);
      }	
   
   
   public function cancelReject():void{
   	
   Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,confirmCancelReject,questionIcon);		
  } 
   
  public function confirmCancelReject(event:CloseEvent):void{
									
			if(event.detail==1){
			var params:Object = {};
			var regRollNo:String="";
			var enrollNo:String = "";
			var modeOfAdmission:String = "";
			var arr:ArrayCollection= commonFunction.getSelectedRowData(rejectionGrid);
			
			for each(var obj:Object in arr){
				
				regRollNo=regRollNo+obj.registration_roll_number+"|";
				enrollNo=enrollNo+obj.enrollment_number+"|";
				modeOfAdmission=modeOfAdmission+obj.admission_mode+"|";
			}	
																															
			params["regNo"] = regRollNo;
			params["enrollNo"] = enrollNo;
		    params["modeOfAdmission"] = modeOfAdmission;
			cancelRejectStudent.send(params);
	}							
  }
   
   public function cancelRejectResultHandler(event:ResultEvent){
   	
   	var rejectStatus:XML = event.result as XML; 
 		var status:Number=Number(rejectStatus);		 		
 		if(status == 1)
 		{ 	
 			//Alert.show("Rejection of Records Cancelled Successfully");
 			rejectionGrid.dataProvider=null;
 			canRej.enabled=false;		
 			 semesterChange(); 			   			 
 		}
   }
      
   public function refreshGrid(params:Object):void {        	
    	   urlPrefix=commonFunction.getConstants('url')+"/associatecoursewithinstructor/";
    	   url=commonFunction.getConstants('url');        	      	   
    	   httpXmlDataService.send(params);         
   }   
      
      