// ActionScript file
/**
 * @(#) globalscript.as
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
                  
                  import mx.collections.ArrayCollection;
                  import mx.controls.Alert;
                  import mx.rpc.events.FaultEvent;
                  import mx.rpc.events.ResultEvent;
                  /* Viewing pages */
                  [Bindable] public var startAlert:uint;
                  [Bindable] public var endAlert:uint;
                  [Bindable] public var totalAlert:uint;
                  
                  [Bindable] public var myData:ArrayCollection = new ArrayCollection();
                  
                 public var orgData:ArrayCollection = new ArrayCollection();
				 public var ArrData:ArrayCollection = new ArrayCollection;
                  
    [Bindable] public var nav:ArrayCollection = new ArrayCollection();
    [Bindable] private var courseHeader:ArrayCollection = new ArrayCollection();
    [Bindable] public var courseDetails:ArrayCollection = new ArrayCollection();
    [Bindable] public var urlProgramCourseHeaderList:String = "";
    [Bindable] public var urlProgramCourseDetails:String = "";
    [Bindable] public var urlProgramHeaderInactive:String = "";
                 public var url_DNS:String ="";
                 
                  /* Records per page */
                  private var pageSize:uint = 0; 
				  private var intPages:uint = 0;
    			  private var CurrPage:uint = 0;
                  
                  /* Number of pages per view */
                  public var navSize:uint = 2;
                  
                  private var index:uint = 0;
                  private var navPage:uint = 1;
       
    [Bindable] public var xmldata_ProgramCourseHeaderDetail:XML;
     [Bindable] public var xmldata_ProgramCourseDetail:XML;
     [Bindable] public var xmldata_ProgramHeaderInactive:XML;
     
     
		[Embed(source="/images/error.png")]public var errorIcon:Class;
		[Embed(source="/images/success.png")]private var successIcon:Class;
		[Embed(source="/images/question.png")]private var questionIcon:Class;
     
     public function init_program_header():void{
			url_DNS = commonFunction.getConstants('url');
    		urlProgramCourseHeaderList = url_DNS+"/manageprogsetup/programCourseHeaderList.htm";
    		urlProgramCourseDetails = url_DNS+"/manageprogsetup/programCourseDetails.htm";
    		urlProgramHeaderInactive = url_DNS+"/manageprogsetup/changeStatusProgramHeader.htm";
    		get_ProgramCourseHeader_List();
		}
     

       private function resultProgramCourseDetailsHandler(event:ResultEvent):void{
        xmldata_ProgramCourseDetail=event.result as XML;
        if(xmldata_ProgramCourseDetail.sessionConfirm == true)
    		    {
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        xmldata_ProgramCourseHeaderDetail
        initCourseDetails();
      }
      private function resultProgramHeaderInactivteHandler(event:ResultEvent):void{
        xmldata_ProgramHeaderInactive=event.result as XML;
          if(xmldata_ProgramHeaderInactive.sessionConfirm == true)
    		    {
        		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
        		}
        var msg:String=null;
         if(xmldata_ProgramHeaderInactive.message=='success'){
          	msg = commonFunction.getConstants('status_msg_success');
        }
        else if(xmldata_ProgramHeaderInactive.message=='fail'){
       	msg=commonFunction.getConstants('status_msg_fail');
       }
        Alert.show(msg, commonFunction.getConstants('message'), Alert.OK, this, alertListener, successIcon);
     }
      private function alertListener(eventObj:CloseEvent):void {
                 if (eventObj.detail==Alert.OK) {
                    get_ProgramCourseHeader_List();//for reload the program course header
                }
            }
      
       private function faultProgramCourseHeaderHandler(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'),4,null,null,errorIcon);
    }
    
    public function inactivate_ProgramHeader(programcoursekeys:ArrayCollection):void {
    	     var params:Object = {};
     		 params["programcoursekeys"] =programcoursekeys;
    	     xmlProgramHeaderInactive.send(params);
        }
    
	public function get_ProgramCourseHeader_List():void {
    	var params:Object = {};
     	params["time"] =new Date();//does not use of params with this send(), it is only used for affect the page contents according dbase
    	xmlProgramCourseHeaderList.send(params);
    	Mask.show(commonFunction.getMessages('pleaseWait'));
    }
           
           public function get_ProgramCourseDetail_List(programCourseKey:String):void {
           //	Alert.show("inside get_ProgramCourseDetail_List");
           	var params:Object = {};
      		params["time"]= new Date();
      		params["programCourseKey"] =programCourseKey;
    	     xmlProgramCourseDetails.send(params);
        }
           
                  /* Test data */
                
                 private function initCourseDetails():void{
                 	courseDetails.removeAll();
                  	 for each(var s:Object in xmldata_ProgramCourseDetail.programCourseKey){
                  courseDetails.addItem({Select:false,courseCode:s.courseCode,courseName:s.courseName,
                  courseType:s.courseType,courseCategory:s.courseCategory,courseGroup:s.courseGroup,
                  courseOption:s.courseOption,courseAvailability:s.courseAvailability});
                  }
                }
//                  private function initApp():void
//                  {     
//                  	 // Convert XML to ArrayCollection
//                  	 courseHeader.removeAll();
//              for each(var s:Object in xmldata_ProgramCourseHeaderDetail.programCourseKey){
//                  courseHeader.addItem({Select:false,programName:s.programName,branchName:s.branchName,
//                  specializationName:s.specializationName,semesterCode:s.semesterCode,
//                  semesterStatus:s.semesterStatus});
//                    }
//                        orgData = courseHeader;
//                       ArrData = courseHeader;
                        
				      //  pageSize =(programCourseGrid.height/programCourseGrid.rowHeight)-1 ;        
				        //wow dynamic pazeSize !
				        
				     //   intPages = Math.ceil(orgData.length/pageSize);
						//let's calc the intPages
										
				     //   refreshDataProvider(index);
				        //refresh the data provider
				        
//				        if(intPages<=1){ //less then one page ?
//				            intPages = 1;
//				            createNavBar(1);
//				        }else{ //more pages !!!!
//				            createNavBar(intPages);    
//				        }
//
//                  }
//                  
                  /* Create pagination */
//                  private function createNavBar(pages:uint = 1,intSet:uint = 0):void{
//                        nav.removeAll();
//                        if( intSet > 1 ){
//                              nav.addItem({label:"<< First",data:0});
//                              
//                              var intLFive:int = intSet-navSize; // calculate start of last 5;
//                              
//                              nav.addItem({label:"< Previous",data:intLFive});
//                        }
//                              
//                      for( var x:uint = 0; x < navSize; x++){
//                            var pg:uint = x + intSet;
//                            nav.addItem({label: pg + 1,data: pg});
//							
//							var pgg:uint = pg+1;
//							if(pgg>=intPages){ //if you reach the last page stop adding linkselectors on the navbar
//								x=navSize; 
//							}
//                      }
//					  
//					  var lastpage:Number = 0;
//					  for( var y:uint = navSize; y <= pages-1;y = y + navSize ){ //lets calculate the lastpage button
//					  	if(y+5 > navSize){
//					  		lastpage = y;
//					  	}
//					  	
//					  }
//					  
//					  
//					  
//                      if( pg < pages - 1 ){
//                            nav.addItem({label:"Next >",data:pg + 1});
//                            nav.addItem({label:"Last >>",data:lastpage});
//                      }                              
//                    }
//                  
                  /* Refresh data per page groups */
//                  private function navigatePage(event:ItemClickEvent):void
//                  {
//                        refreshDataProvider(event.item.data);
//                        var lb:String = event.item.label.toString();
//                        if( lb.indexOf("<") > -1 || lb.indexOf(">") > -1 )
//                        {
//                              //createNavBar(Math.ceil(orgData.length/pageSize),event.item.data);
//                              createNavBar(Math.ceil(ArrData.length/pageSize),event.item.data);
//                        }
//                        
//                  }
//                  
//                  private function refreshDataProvider(start:uint):void{
//                  	
//                  	myData = new ArrayCollection( ArrData.source.slice((start * pageSize),(start * pageSize) + pageSize) );
//                  	
//               	    viewingAlertsLabel1.text = 'Page ' + (start+1).toString() + ' of ' + intPages.toString();
//              	    viewingAlertsLabel2.text = 'Total items ' + orgData.length.toString();
//                  }
//                                    
//                 
//                  private function viewingAlerts ():void {
//                        startAlert == ((navPage - 1) * pageSize + 1);
//                        endAlert == ((navPage - 1) * pageSize + pageSize);
//                        totalAlert == myData.length;
//                  }
                  