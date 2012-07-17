// ActionScript file

/*
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
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

/**
 *This file consist of the method definitions used
 *to reset user passwords using the application
 *@author Ashish
 *@date 22 July 2011
 *@version 1.0
 * */


import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Embed(source="/images/success.png")]private var successIcon:Class;
import common.commonFunction;
import common.Mask;
import mx.events.CloseEvent;
import employeeMaster.employeePopUp;
import employeeMaster.setEmployeeRole;
import mx.managers.PopUpManager;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var confirmIcon:Class;
public var counter:String;
[Bindable]
public var length:int=0;
[Bindable]
public var cls:Function;

/**
 * The method fetches the details of the user
 * on the basis on the inputs provided
 * */
public function onCreationComplete():void{
	
	linkbutton.enabled = false;		
		
		var infoObject:Object={};
		infoObject["userId"]=new Date();
		textvalidate.source=userType;
		var i:int=Validator.validateAll([textvalidate]).length;
	

								
								if(!checkBoxId.selected){
									
									if((empId.text=="") ||(i!=0)){
										Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),
										commonFunction.getMessages('error'),0,null,null,errorIcon);
										
										empId.errorString = commonFunction.getConstants('errorStringusername');
										
									}else{										
										
										infoObject["userName"] = empId.text;
										infoObject["userType"] = userTypeXml.id.(@name==userType.selectedLabel).@code;
										infoObject["counter"] = "one";
										infoObject["dateofbirth"] = "";	
										
										Mask.show(commonFunction.getMessages('pleaseWait'));		
										getApplicationUsers.send(infoObject);
										
									}		
								}else{
									
									if(i!=0){
		
										Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),
										commonFunction.getMessages('error'),0,null,null,errorIcon);
		
									}else{
										
										infoObject["userType"] = userTypeXml.id.(@name==userType.selectedLabel).@code;
									if(name1.text!=null){
										infoObject["name"] = name1.text+"%";								
																			
									}else{
										
										infoObject["name"] = "%";
										
									}
									if(dateofbirth.text!=null){
										
										infoObject["dateofbirth"] = dateFormatter.format(dateofbirth.text);										
																			
									}else{
										
										infoObject["dateofbirth"] = "";
									}
									if(fathername.text!=null){
										
										infoObject["fathername"] = fathername.text+"%";									
																		
									}else{
										
										infoObject["fathername"] = "%";	
									}
									
										infoObject["counter"] = "two";
										
										Mask.show(commonFunction.getMessages('pleaseWait'));		
										getApplicationUsers.send(infoObject);
										
									}									
									
								}
						}


			public function ddd():void{
					if(empId.text!=""){
					empId.errorString="";
				}
			}
			
			/**
			 * method shows the details of the users in the grid
			 * */
			[Bindable] public var userDetails:XML;
			[Bindable] public var userDetailsList:ArrayCollection;
			public function onUsersSuccess(event:ResultEvent):void{
				
				userDetails=event.result as XML;
				
				Mask.close();				
				
				if(userDetails.sessionConfirm == true)
            	 {
           			 Alert.show(resourceManager.getString('Messages','sessionInactive'));
          			 this.parentDocument.vStack.selectedIndex=0;
         			 this.parentDocument.loaderCanvas.removeAllChildren();
         			 
           	     }else{
           	     	userDetailsList=new ArrayCollection();
           	     	
           	     	for each (var o:Object in userDetails.Details){		
		
					userDetailsList.addItem({select:false,employeeId:o.employee_id,empname:o.first_name,dob:o.dob,category:o.category,gender:o.gender,email_id:o.primary_email_id,
								employee_id:o.employee_id});
		
					}
					
					if(userDetailsList.length==0){
						
						Alert.show(commonFunction.getMessages('noRecordFound'),
						commonFunction.getMessages('error'),0,null,null,errorIcon);					
						
					}else{
						
						empdetails.visible=true;
						empdetailsdislpay.dataProvider=userDetailsList;
						
					}
           	     	
           	     	
           	     }				
			}
			
			/**
			 * The method asks the user to continue or not
			 **/
			public function getApplicationUserDetails():void{				
								
				Alert.show(commonFunction.getMessages('conformForContinue'),
				commonFunction.getMessages('confirm'),3,this,onYes,confirmIcon);
								
			}
			
			
			/**
			 * The method sends a request on the server to update the password
			 * of the concerned user
			 * */
			public function onYes(event:CloseEvent):void{
				
				var gridData:ArrayCollection = commonFunction.getSelectedRowData(empdetailsdislpay);
				
				
				
				if(event.detail==Alert.YES){
					
					var infoObject:Object={};
					infoObject["userId"]=new Date();
				
					infoObject["employeeCode"] = gridData.getItemAt(0).employeeId;
					infoObject["userType"] = userTypeXml.id.(@name==userType.selectedLabel).@code;
					infoObject["emailId"] = gridData.getItemAt(0).email_id;
				
					Mask.show(commonFunction.getMessages('pleaseWait'));				
				
					getempdetails.send(infoObject);	
					
				} 
				
			} 

/**
 * The method gets the response from the server and displays
 * on the front end
 * */			
var successXml:XML;
private function onSuccess(event:ResultEvent):void{
	
	successXml=event.result as XML;				
	
				if(successXml.sessionConfirm == true)
            	 {
           			 Alert.show(resourceManager.getString('Messages','sessionInactive'));
          			 this.parentDocument.vStack.selectedIndex=0;
         			 this.parentDocument.loaderCanvas.removeAllChildren();
           	     }
           	     
           	     if(successXml.Details.list_values=="failure"){
           	     	
           	     	Alert.show(commonFunction.getMessages('requestFailed'),
					commonFunction.getMessages('failure'),0,null,null,errorIcon);
           	     	
           	     }else{
           	     	
           	     	Alert.show("Password reset successfully,generated password is "+successXml.Details.list_values,
					commonFunction.getMessages('success'),0,this,onReset,successIcon);           	     	
           	     }
	
	
	
			Mask.close();
}

/**
 * The function to be called as and when required
 * in order to clear the fields on the interface
 * */
private function onReset():void{
	
					empId.text="";
					userType.selectedIndex = -1;
					name1.text = "";
					dateofbirth.data = null;
					fathername.text = "";
					empdetails.visible=false;
					linkbutton.enabled = false;
					userType.errorString = "";
					empId.errorString = "";
	
}

/**
 * The method to be called when the request is not processed on the server
 * */
private function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();
}

/**
 * The conditions to be worked out on change of the check box
 * */
public function checkBoxChange():void{
	
	if(checkBoxId.selected==true){
		
		
		name1.text = "";
		fathername.text = "";
		dateofbirth.data = null;
		empId.text="";
		empId.errorString="";
		empId.editable=false;
		detailsCanvas.y = 93;
		okButton.y = detailsCanvas.height+20+detailsCanvas.y;
		cancelButton.y = detailsCanvas.height+20+detailsCanvas.y;
		resetButton.y = detailsCanvas.height+20+detailsCanvas.y;
		detailsCanvas.visible=true;	
		empdetails.visible=false;
		linkbutton.enabled = false;
		usernameid.text=resourceManager.getString('Constants','userName');
		this.getChildAt(0).height=335;
		
	
	}else{
		
		detailsCanvas.y = 5;
		usernameid.text=resourceManager.getString('Constants','userName')+"*";
		empId.editable=true;
		okButton.y = okButton.y-(detailsCanvas.height+15);
		cancelButton.y = cancelButton.y-(detailsCanvas.height+15);
		resetButton.y = resetButton.y-(detailsCanvas.height+15);
		detailsCanvas.visible=false;
		empdetails.visible=false;
		name1.text = "";
		fathername.text = "";
		dateofbirth.data = null;
		linkbutton.enabled = false;
		this.getChildAt(0).height=195;		
		
	}
	
}

/**
 * The method makes the grid invisible 
 * */
public function onComboChange():void{
	
	empdetails.visible=false;
	
	if(userType.selectedLabel == "Employee"){
		
		fathernamelabel.visible = false;
		fathername.visible = false;		
		
	}else{
		
		fathernamelabel.visible =true;
		fathername.visible = true;
		
	}
	
}
