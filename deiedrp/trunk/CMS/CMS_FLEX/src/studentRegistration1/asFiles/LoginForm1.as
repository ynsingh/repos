// ActionScript file



import common.Mask;

import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import studentRegistration.StudentRegistration;




[Embed(source="/images/error.png")]private var errorIcon:Class;
        
var registrationForm:StudentRegistration=new StudentRegistration();

[Bindable]
var loginResult:XML;
var student_id="";

    private function Login():void {   
    	
    	var params:Object=new Object();
    	params["user_name"]=userNameText.text; 
    
      Mask.show(resourceManager.getString('Messages','pleaseWait'));
         httpLogin.send(params);
	}
		
	private function faultHandler(event:FaultEvent):void{
//var errorMessage:ErrorMessage = event.message as ErrorMessage;
//mx.controls.Alert.show(errorMessage.rootCause.toString());
//mx.controls.Alert.show(errorMessage.rootCause.myName);

var faultEvent : FaultEvent = FaultEvent( event );
 Alert.show(resourceManager.getString('Messages','loginFailed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
Mask.close();
    }
   private function resultHandler(event:ResultEvent):void{
   Mask.close();
  loginResult=event.result as XML;
 // Alert.show(loginResult);
  student_id=loginResult.login.student_id;
  
  var params:Object=new Object();
  params["student_id"]=student_id;
  params["roll_number"]=userNameText.text;
	registrationForm.user_name=userNameText.text;
	registrationForm.parameter=params;
	registrationForm.forCancel="inner";
		
	this.parentDocument.loaderCanvas.addChild(registrationForm);
	this.parentDocument.loaderCanvas.removeChildAt(0);
	//this.parentDocument.loaderCanvas.removeAllChildren();
//  this.parentDocument.loaderCanvas2.addChild(registrationForm);
//	this.parentDocument.loaderCanvas2.removeChildAt(0);
      }