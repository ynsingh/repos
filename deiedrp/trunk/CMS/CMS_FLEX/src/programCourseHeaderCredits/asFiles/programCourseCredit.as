/**
 * @(#) ProgramCourseCredits.as
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
  import mx.managers.PopUpManager;
  
  public var url_DNS:String ="";
  public var programCourseKey:String=null;
  
  [Embed(source="/images/error.png")]public var errorIcon:Class;
  
  [Bindable] public var urlProgramCourseHeaderList:String = "";
  
  [Bindable] public var xmldata_ProgramCourseHeaderDetail:XML;
  
  [Bindable] private var courseHeadergird:ArrayCollection = new ArrayCollection();
	public var topParentFun:Function;
  
  public function getString(pass_key:String):String{
		return commonFunction.getConstants(pass_key);
  }
  
  public function init_program_header():void{
	url_DNS = commonFunction.getConstants('url');
	urlProgramCourseHeaderList = url_DNS+"/programCourseHeaderCredit/programCourseHeaderList.htm";

	get_ProgramCourseHeader_List();
  }
  
  public function doSomething():void{
	get_ProgramCourseHeader_List();
  }
  
  public function get_ProgramCourseHeader_List():void {
    	var params:Object = {};
     	params["time"] =new Date();//does not use of params with this send(), it is only used for affect the page contents according dbase
    	xmlProgramCourseHeaderList.send(params);
    	Mask.show(commonFunction.getMessages('pleaseWait'));
  }
  private function resultProgramCourseHeaderHandler(event:ResultEvent):void{
        		xmldata_ProgramCourseHeaderDetail=event.result as XML;
        		Mask.close();
         		if(xmldata_ProgramCourseHeaderDetail.sessionConfirm == true){
        			Alert.show(resourceManager.getString('Messages','sessionInactive'));
					var url:String="";
					url=commonFunction.getConstants('navigateHome');
					var urlRequest:URLRequest=new URLRequest(url);
					urlRequest.method=URLRequestMethod.POST;
					navigateToURL(urlRequest,"_self");
        		}
       			courseHeadergird.removeAll();
         		for each(var s:Object in xmldata_ProgramCourseHeaderDetail.programCourseKey){
                	courseHeadergird.addItem({Select:false,programName:s.programName,branchName:s.branchName,
                  	specializationName:s.specializationName,semesterCode:s.semesterCode,
                  	semesterStatus:s.semesterStatus,maxCredits:s.maxCredit,minCredits:s.minCredit,maxLecCredits:s.maxLecCredit,
                  	minLecCredits:s.minLecCredit
                  	});
                }
                programCourseHeaderGrid.dataProvider=courseHeadergird;
  }
  
  protected function editButton_ClickHandler(event:MouseEvent):void
  {	
				
	        	var gridData:ArrayCollection=programCourseHeaderGrid.dataProvider as ArrayCollection;
            	var colc:int=gridData.length;
            	var x:int=0;
            	
            	var selectedRowData:Array=new Array();
            	
            	for(var i:int=0;i<colc;i++)
            	{
            	 	var gridItem:Object=gridData.getItemAt(i);
            		if(gridItem.Select==true)
            		{
            			x=x+1;
            			selectedRowData[0]=gridItem.programName;
            			selectedRowData[1]=gridItem.branchName;
            			selectedRowData[2]=gridItem.specializationName;
            			selectedRowData[3]=gridItem.semesterCode;
            			selectedRowData[4]=gridItem.maxCredits;
            			selectedRowData[5]=gridItem.minCredits;
            			selectedRowData[6]=gridItem.maxLecCredits;
            			selectedRowData[7]=gridItem.minLecCredits;
            			
            			programCourseKey=xmldata_ProgramCourseHeaderDetail.programCourseKey.
            			(programName==gridItem.programName).(branchName==gridItem.branchName).
            			(specializationName==gridItem.specializationName).(semesterCode==gridItem.semesterCode).@name;
               		}
            	}
            	
            	if(x==0)
            	{
            		Alert.show(commonFunction.getConstants('select_for_updation'),"error",0,null,null,errorIcon);
            	}
            	else if(x==1)
            	{
            		var proCouEditWindowObj:editProgramCourseCredit=editProgramCourseCredit(PopUpManager.createPopUp(this,editProgramCourseCredit,true));
	            	proCouEditWindowObj.program_name_lab.text=selectedRowData[0];
					proCouEditWindowObj.branch_name_lab.text=selectedRowData[1];
					proCouEditWindowObj.specialization_lab.text=selectedRowData[2];
					proCouEditWindowObj.semester_lab.text=selectedRowData[3];
					proCouEditWindowObj.maxCredits.text=selectedRowData[4];
					proCouEditWindowObj.minCredits.text=selectedRowData[5];
					proCouEditWindowObj.maxLectureCredits.text=selectedRowData[6];
					proCouEditWindowObj.minLectureCredits.text=selectedRowData[7];
					proCouEditWindowObj.parentFun =doSomething;
					proCouEditWindowObj.programCourseKey= programCourseKey;
					PopUpManager.centerPopUp(proCouEditWindowObj);
	           	}
            	else
            	{
            		Alert.show(commonFunction.getConstants('select_only_one')+" "+x+" "+commonFunction.getConstants('records'),
            		commonFunction.getConstants('error'),4,null,null,errorIcon);
            	}
     }
    protected function cancelButton_clickHandler(event:MouseEvent):void
	{
		document.visible=false;
	}
	
	private function faultPCHCredit(event:FaultEvent):void{
		mx.controls.Alert.show(event.fault.message,getString('errorInResult'),4,null,null,errorIcon);
	}		
    
