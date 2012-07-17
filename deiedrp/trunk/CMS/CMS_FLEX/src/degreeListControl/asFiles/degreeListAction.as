// ActionScript file
/**
 * @(#) degreeListAction.as
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
 
 /**
  * The file manages the inputs required for the
  * various reports to be generated from the interfae
  * @Date 22 Aug 2011
  * @author Ashish Yadav
  * @version 1.0
  **/ 
import common.Mask;

import mx.collections.ArrayCollection;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

var infoObject = Object;
/**
 * The method sends a request to get the list of
 * entities on page creation
 **/ 
public function onCreationComplete():void{
	
	infoObject["date"] = new Date;
	
	getEntities.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
}

public var entityXML:XML;
public var entityList:ArrayCollection;
/**
 * The method retrieves the list of entities
 * from the database
 **/ 
public function getEntityList(event:ResultEvent):void{
	
	Mask.close();
	
	entityXML = event.result as XML;
	
	if(entityXML.sessionConfirm == true)
    	{
           	Alert.show(resourceManager.getString('Messages','sessionInactive'));
          	var url:String="";
			 url=commonFunction.getConstants('navigateHome');
			 var urlRequest:URLRequest=new URLRequest(url);
			 urlRequest.method=URLRequestMethod.POST;
			 navigateToURL(urlRequest,"_self");
    }
	
	entityList = new ArrayCollection();
	
	for each(var obj:Object in entityXML.role){
		
		entityList.addItem({select:false,id:obj.id,description:obj.description});		
		
	}
	
	
	
	entityCombo.dataProvider = entityList;
	entityCombo.labelField="description";
	

	
}

/**
 * The method sends a request to get the list
 * of programs offered by the entity
 **/ 
public function onEntityChange():void{
	
	programCombo.enabled=true;
	fromYearCombo.selectedIndex=-1;
	fromYearCombo.enabled = false;
	toYearCombo.visible = false;
	
	infoObject["entityId"] = entityCombo.selectedItem.id;
	
	getEntityPrograms.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
}

public var programXML:XML;
public var programList:ArrayCollection;
/**
 * The method retrieves the list of programs
 * from the database offered by the seleceted entity
 **/ 
public function getEntityProgramList(event:ResultEvent):void{
	
	Mask.close();
	
	programXML = event.result as XML;
	
	if(programXML.sessionConfirm == true)
    	{
           	Alert.show(resourceManager.getString('Messages','sessionInactive'));
          	var url:String="";
			 url=commonFunction.getConstants('navigateHome');
			 var urlRequest:URLRequest=new URLRequest(url);
			 urlRequest.method=URLRequestMethod.POST;
			 navigateToURL(urlRequest,"_self");
    }
	
	programList = new ArrayCollection();	
	
	for each(var obj:Object in programXML.Details){
		
		programList.addItem({select:false,id:obj.programId,description:obj.advanceProgram,
			programCode:obj.courseCode,printType:obj.coursename});		
	}
	
	
	
	programCombo.dataProvider  = programList;
	programCombo.labelField = "description";
	
}

/**
 * The method sends a request to get the list
 * of university sessions from the database
 **/ 
public function onProgramChange():void{
	
	fromYearCombo.enabled=true;
	toYearCombo.enabled=true;
	toYearCombo.visible = false;
	
	infoObject["programId"] = programCombo.selectedItem.id;
	
	getUniversitySessions.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	
}
public var sessionXML:XML;
public var sessionList:ArrayCollection;
/**
 * The method retrieves the list of university sessions
 * from the database
 **/ 
public function getUniversitySessionList(event:ResultEvent):void{
	
	Mask.close();
	
	sessionXML = event.result as XML;
	
	if(sessionXML.sessionConfirm == true)
    	{
           	Alert.show(resourceManager.getString('Messages','sessionInactive'));
          	var url:String="";
			 url=commonFunction.getConstants('navigateHome');
			 var urlRequest:URLRequest=new URLRequest(url);
			 urlRequest.method=URLRequestMethod.POST;
			 navigateToURL(urlRequest,"_self");
    }
	
	sessionList = new ArrayCollection();
	
	for each(var obj:Object in sessionXML.role){
		
		var sessionStartDate:String = obj.id.toString();
		var year:String = sessionStartDate.substr(0,4);
		
		var sessionEndDate:String = obj.description.toString();
		var endYear:String = sessionEndDate.substr(0,4);
		
		sessionList.addItem({select:false,id:year,description:endYear});		
	}
	
	fromYearCombo.dataProvider = sessionList;
	fromYearCombo.labelField = "id";
	
	
	
	
	
}

/**
 * The method sets the year of to session
 * wrt the session selected from the from session
 * dropdown
 **/ 
public function onYearChange():void{
	
	for each(var obj:Object in sessionList){		
		if(fromYearCombo.selectedLabel == obj.id){			
			toYearCombo.text = obj.description;			
		}		
	}
	
	toYearCombo.visible = true;
}

/**
 * The method is executed on the click of
 * reset button
 **/ 
public function resetButtonClickHandler():void{
	
	entityCombo.selectedIndex = -1;
	programCombo.selectedIndex = -1;
	programCombo.enabled = false;
	fromYearCombo.selectedIndex = -1;
	fromYearCombo.enabled = false;
	toYearCombo.text = null;
	toYearCombo.visible = false;
	entityCombo.errorString = "";
	programCombo.errorString = "";
	fromYearCombo.errorString = "";
	
}
