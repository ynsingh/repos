/**
 * @(#) EnrollmentLogin.as
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

import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import studentEnrollment.StudentEnrollment;
[Embed(source="/images/error.png")]private var errorIcon:Class;

[Bindable]var loginResult:XML;

[Bindable]public var userName:String=""

private function Login():void
{
	var params:Object=new Object();
	userName=userNameText.text;
    params["user_name"]=userNameText.text; 
    params["password"]=passwordText.text;
    Mask.show(resourceManager.getString('Messages','pleaseWait'));
    httpLogin.send(params);
}

private function faultHandler(event:FaultEvent):void
{
	var faultEvent : FaultEvent = FaultEvent( event );
	Alert.show(resourceManager.getString('Messages','loginFailed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	Mask.close();
}

private function resultHandler(event:ResultEvent):void
{
	Mask.close();
	loginResult=event.result as XML;
	var enrollmentForm:StudentEnrollment=new StudentEnrollment();
	enrollmentForm.registrationNo=userName;
	enrollmentForm.studentId=loginResult.login.student_id+"";
	this.parentDocument.loaderCanvas2.addChild(enrollmentForm);
	this.parentDocument.loaderCanvas2.removeChildAt(0);
}