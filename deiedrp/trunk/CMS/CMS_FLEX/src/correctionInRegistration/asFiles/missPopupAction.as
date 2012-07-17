/**
 * @(#) missPopupAction.as
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
public var buttonFunction:Function;
public var gender:String;
[Bindable]public var genderr:String="";
[Bindable]
public var categoryDetails: XML;
public var categorylist:ArrayCollection;

[Bindable]public var category:String="";
[Bindable]public var catId:String="";
[Bindable]public var reg:String="";
[Bindable]public var reason:String=""; 
[Bindable]public var roll:String="";
[Bindable]public var prgId:String="";
[Bindable]public var regNo:String="";
[Bindable]public var enrNo:String="";
[Bindable]public var stuId:String="";
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
public var param:Object={};
[Bindable]public var urlPrefix1:String;
[Bindable]public var urlPrefix2:String;	
[Bindable]
public var SMDetails: XML;
[Bindable]
public var TSMDetails: XML;

			protected function titlewindowInitializeHandler():void{
				urlPrefix1=resourceManager.getString('Constants','url')+"/correctionInRegistration/";
	            urlPrefix2=resourceManager.getString('Constants','url')+"/updatePrestaging/";
				
				param["userId"] = new Date;
				getCategoryDetails.send(param);
				
				param["enrollNo"] =enrNo;
				getSMDetails.send(param);
				param["studentId"] =stuId;
			    getTSMDetails.send(param);
//				genderCombo.selectedItem=genders.gender.(@code==genderr).@name+"";
				
			}
			
			public function onSMSuccess(event:ResultEvent):void{
				SMDetails=event.result as XML;
				//Alert.show(event.result as XML);
				var gendName:String="";
			    studentFirstName.text=SMDetails.Details.studentfname;
			    studentmiddleName.text=SMDetails.Details.studentmname;
				studentLastName.text=SMDetails.Details.studentlname;
				fatherFirstName.text=SMDetails.Details.fatherfname;
				fatherMiddleName.text=SMDetails.Details.fathermname;
				fatherLastName.text=SMDetails.Details.fatherlname;
				if(SMDetails.Details.gender=="M"){
					gendName="Male";
				}else{
					gendName="Female";
				}
				genderLabel.text=gendName;
				categoryLabel.text=SMDetails.Details.category;
				dateofBirthLabel.text=SMDetails.Details.dob;	
			}
			
			public function onTSMSuccess(event:ResultEvent):void{
				TSMDetails =event.result as XML;
				//Alert.show(event.result as XML);
				
			    studentFirstNameEdit.text=TSMDetails.Details.studentfname;
			    studentmiddleNameEdit.text=TSMDetails.Details.studentmname;
				studentLastNameEdit.text=TSMDetails.Details.studentlname;
				fatherFirstNameEdit.text=TSMDetails.Details.fatherfname;
				fatherMiddleNameEdit.text=TSMDetails.Details.fathermname;
				fatherLastNameEdit.text=TSMDetails.Details.fatherlname;
			
				genderCombo.selectedItem=genders.gender.(@code==SMDetails.Details.gender).@name+"";
		
				categoryCombo.selectedItem=TSMDetails.Details.category;
				dateofBirthFieldEdit.text=TSMDetails.Details.dob;	
				
			}
			
			/**
			 * method executed on request failure 
			 * */
			public function onFailure(event:FaultEvent):void{
				
			//	Alert.show(commonFunction.getMessages('requestFailed'),
			//	commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
			}
			
			
			
			public function closepopup():void{
				PopUpManager.removePopUp(this);
			}
						

/**
 * The function retrives the list of categories for the 
 * concerned university id
 * */

public function onCategoryDetailsSuccess(event:ResultEvent):void{
	
	categoryDetails=event.result as XML;
	
	categorylist =new ArrayCollection();
	
	for each (var o:Object in categoryDetails.role){
		
		categorylist.addItem({id:o.id,description:o.description});
		
	}
	
	if(categorylist.length==0){
		
		Alert.show(resourceManager.getString('Messages','noRecordFound'),
			resourceManager.getString('Messages','alert'));
	}else{
		
		categoryCombo.dataProvider = categoryDetails.role.description;
		//categoryCombo.selectedItem = categoryDetails.role.(id==category).description+"";
	}	
}
			
	public function updateValues():void{
		
		if(Validator.validateAll([studentFirstNameValid,fatherFirstNameValid,motherFirstNameValid]).length==0){
			
//		param["regRollNo"] =regrollNo.text;
//		param["enrollNo"] =enrollNo.text;
//		param["studentfname"] =studentFirstName.text;
//		param["studentmname"] =studentmiddleName.text;
//		param["studentlname"] =studentLastName.text;
//		param["fatherfname"] =fatherFirstName.text;
//		param["fathermname"] =fatherMiddleName.text;
//		param["fatherlname"] =fatherLastName.text;
//		param["motherfname"] =motherFirstName.text;
//		param["mothermname"] =motherMiddleName.text;
//		param["motherlname"] =motherLastName.text;
//		param["primaryMail"] =primarymailtext.text;
//		param["dob"] =dateofBirthField.text;
//		param["gender"] =genders.gender.(@name==genderCombo.selectedLabel).@code;
//		param["category"] =categoryDetails.role.(description==categoryCombo.selectedLabel).id;
//		param["reasoncode"] =reasonCode.text;
//		param["description"] =description.text;
		
		updateRecords.send(param);			
									  
		}else{
			Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),
	(commonFunction.getMessages('error')),0,null,null,errorIcon);	
		}
	}
	
		public var updateDetails:XML;
		public function onUpdateSuccess(event:ResultEvent):void{
		

	updateDetails=event.result as XML;
	
	if(updateDetails.Details.list_values=="success"){
	
	Alert.show((resourceManager.getString('Messages','recordUpdatedSuccessfully')),commonFunction.getMessages('success'),
		4,null,onUpdate,successIcon);
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}		
		
		
}
			
public function onUpdate(event:CloseEvent):void{
				
				PopUpManager.removePopUp(this);
				buttonFunction.call();
				
			}