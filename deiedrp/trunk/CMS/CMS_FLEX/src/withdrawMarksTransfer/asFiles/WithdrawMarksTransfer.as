// ActionScript file
import common.commonFunction;

import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

private var param:Object = new Object;
private var programCourseKey:String = new String;
private var sessionStartDate:String = new String;
private var sessionEndDate:String = new String;
private var semesterStartDate:String = new String;
private var semesterEndDate:String = new String;
private var rollNumber:String = new String;
private var semesterCode:String = new String;
private var registrationPeriod:String = new String;

[Bindable]public var detailsXML:XML;
[Bindable]public var sessionXML:XML;
[Bindable]public var datesXML:XML;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/warning.png")]private var warningIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

public function getStudentDetails():void{
	sessionCanvas.visible=false;
	if(rollText.length==0){
		Alert.show(commonFunction.getMessages('rollNumberEnter'),commonFunction.getMessages('info'),4,null,null,infoIcon);
	}else{
		rollNumber=rollText.text
		param['rollNumber']=rollNumber;
		getDetails.send(param);
	}
}

public function resultGetDetails(event:ResultEvent):void{
	detailsXML = event.result as XML;
	populateStudentDetails(detailsXML);
	if(studentName.length==0||dob.length==0||gender.length==0||category.length==0||entityText.length==0
		||programCourseKey.length==0){
			Alert.show(commonFunction.getMessages('detailsNotExists') + ' ' + rollNumber,commonFunction.getMessages('info'),4,null,null,infoIcon);
		}else{
			detailsCanvas.visible=true;
		}
}

private function populateStudentDetails(detailsXML:XML):void{
	semesterCode=detailsXML.StudentDetail.semesterCode;
	enrollmentNumberText.text=detailsXML.StudentDetail.enrollmentNumber;
	studentName.text=detailsXML.StudentDetail.studentName;
	fatherName.text=detailsXML.StudentDetail.fatherName;
	dob.text=detailsXML.StudentDetail.dob;
	gender.text=detailsXML.StudentDetail.genderName;
	category.text=detailsXML.StudentDetail.categoryName;
	entityText.text=detailsXML.StudentDetail.entityName;
	programText.text=detailsXML.StudentDetail.programName;
	branchText.text=detailsXML.StudentDetail.branchName;
	specializationText.text=detailsXML.StudentDetail.specializationName;
	attemptText.text=detailsXML.StudentDetail.attemptNumber;
	programCourseKey=detailsXML.StudentDetail.programCourseKey;
	withdrawalSession.text=detailsXML.StudentDetail.withdrawalSession;
	withdrawalSemester.text=detailsXML.StudentDetail.semesterName;
	withdrawalStartDate.text=detailsXML.StudentDetail.withdrawalSemesterStartDate;
	withdrawalEndDate.text=detailsXML.StudentDetail.withdrawalSemesterEndDate;
}

public function checkSessionDetails():void{
	param['entityId']=detailsXML.StudentDetail.entityId;
	param['programCourseKey']=detailsXML.StudentDetail.programCourseKey;
	getRegisteringSession.send(param);
}

public function resultGetRegisteringSession(event:ResultEvent):void{
	sessionXML=event.result as XML;
	sessionLabel.text=sessionXML.DateDetails.registeringSession;
	startDateLabel.text=sessionXML.DateDetails.semesterStartDate;
	endDateLabel.text=sessionXML.DateDetails.semesterEndDate;
	sessionStartDate=sessionXML.DateDetails.sessionStartDate;
	sessionEndDate=sessionXML.DateDetails.sessionEndDate;
	semesterStartDate=sessionXML.DateDetails.startDate;
	semesterEndDate=sessionXML.DateDetails.endDate;
	if(semesterStartDate.length==0 && semesterEndDate.length==0){
		param['entityId']=detailsXML.StudentDetail.entityId;
		param['semesterCode']=semesterCode;
		param['programId']=detailsXML.StudentDetail.programId;
		param['branchId']=detailsXML.StudentDetail.branchId;
		param['specializationId']=detailsXML.StudentDetail.specializationId;
		getDates.send(param);
	}else{
		sessionCanvas.visible=true;
	}
}

public function resultGetRegistrationDates(event:ResultEvent):void{
	datesXML=event.result as XML;
	registrationPeriod=commonFunction.getConstants('from') + ' ' + datesXML.DateDetails.startDate + ' ' + commonFunction.getConstants('to') + ' ' + datesXML.DateDetails.endDate;
	Alert.show(commonFunction.getMessages('registrationNotAllowed')+'\n'+commonFunction.getMessages('validPeriod')+'\n'+registrationPeriod,commonFunction.getMessages('info'),4,null,null,infoIcon);
	detailsCanvas.visible=false;
}

public function enableRegistration():void{
	param['entityId']=detailsXML.StudentDetail.entityId;
	param['programId']=detailsXML.StudentDetail.programId;
	param['branchId']=detailsXML.StudentDetail.branchId;
	param['specializationId']=detailsXML.StudentDetail.specializationId;
	param['semesterCode']=detailsXML.StudentDetail.semesterCode;
	param['semesterStartDate']=semesterStartDate;
	param['semesterEndDate']=semesterEndDate;
	param['sessionStartDate']=sessionStartDate;
	param['sessionEndDate']=sessionEndDate;
	param['programCourseKey']=detailsXML.StudentDetail.programCourseKey;
	param['rollNumber']=rollNumber;
	param['attemptNo']=detailsXML.StudentDetail.attemptNumber;
	onSubmit.send(param);
}

public function resultOnSubmit(event:ResultEvent):void{
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }
    if(result.exception.exceptionstring == 'E'){
	   	Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}else{
	   	Alert.show(commonFunction.getMessages('regEnableSuccess') ,commonFunction.getMessages('success'),4,null,null,successIcon);
	   	detailsCanvas.visible=false;
	   	sessionCanvas.visible=false;
	   	rollText.text='';
	}
}

public function onFailure(event:FaultEvent):void{
	Alert.show('Failure');
}
