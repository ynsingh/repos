// ActionScript file

import mx.collections.ArrayCollection;
import flash.net.navigateToURL;
import mx.controls.Alert;
import common.commonFunction;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

//[Bindable] public var universityId:String;
//[Bindable] public var userId:String;
//[Bindable] public var userGroupId:String;
//[Bindable] public var userGroupName:String;
//[Bindable] public var userName:String;
//[Bindable] public var universityName:String;
//[Bindable] public var startDate:String;
//[Bindable] public var endDate:String;
//public var getLoginInfoService:HTTPService=new HTTPService();
//public var params:Object={};

var a:int=150;
var count:int=0;

public function testing():void{
	var i:int; 
	params["userName"]="ups001";
	params["password"]="upasana";
	params["application"]="CMS";
	getLoginInfoService.useProxy=false;
	getLoginInfoService.resultFormat="e4x";
	getLoginInfoService.method="POST";
	getLoginInfoService.addEventListener(ResultEvent.RESULT,testingResultHandler);
	getLoginInfoService.addEventListener(FaultEvent.FAULT,testingFaultHandler);
	getLoginInfoService.url=commonFunction.getConstants('urlCms')+'/login/getLoginDetails.htm';   
	getLoginInfoService.send(params);
}
public function testingResultHandler(event:ResultEvent):void
{
	count++;
	var userInfoXml:XML=event.result as XML;
	
	var values:ArrayCollection=new ArrayCollection();
	for each(var obj:Object in userInfoXml.loginInfo)
	{
		values.addItem({userId:obj.userId,universityId:obj.universityId,userGroupId:obj.userGroupId,
		userGroupName:obj.userGroupName,userName:obj.userName, universityName:obj.universityName});
	}
	if(values.length>0)
	{
		userId=values[0].userId;
		userName=values[0].userName;
		universityId=values[0].universityId;
		universityName=values[0].universityName;
		startDate=values[0].startDate;
		endDate=values[0].endDate;
		
		var application="CMS";
		userName=values[0].userName;
		userGroupId=values[0].userGroupId;
		userGroupName=values[0].userGroupName;
		userId=values[0].userId;
		universityId=values[0].universityId;
		universityName=obj.universityName;
		
 		var url:String="";
		url=commonFunction.getConstants('navigateUrlCms')+'#userGroupId='+userGroupId+'&userGroupName='+userGroupName+'&userName='+userName+'&application='+application+'&university='+universityName
		var urlRequest:URLRequest=new URLRequest(url);
		urlRequest.method=URLRequestMethod.POST;
		navigateToURL( urlRequest,"_blank");
		if(count<a){
			testing();
		}
	}
	else
	{
		Alert.show((commonFunction.getMessages('invalidLoginDetails')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
}

//login fault handler
public function testingFaultHandler(event:FaultEvent):void
{
	Alert.show(event.message+"");
}
