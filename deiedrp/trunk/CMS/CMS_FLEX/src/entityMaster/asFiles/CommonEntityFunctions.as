/**
 * @(#) CourseEntityFunctions.as
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
package entityMaster.asFiles
{
	import common.Mask;
	
	import mx.controls.ComboBox;
	
	public class CommonEntityFunctions
	{
		
		[Bindable]
var entityTypeXml:XML;
		
		
		public function CommonEntityFunctions()
		{
		}


/**
function for getting entity type list
*/
private function getEntityTypeList(entityTypeCombo:ComboBox):void{
	var params:Object=new Object();
	params["userId"]=userId;
	Mask.show(resourceManager.getString('Constants','loading'));
		params["time"]=new Date().getTime();
	httpEntityType.send(params);
	httpLocation();
}

private function faultHandler_EntityType(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_EntityType(event:ResultEvent):void{
	  
   entityTypeXml=event.result as XML;
 
     if(entityTypeXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
   var entityTypeAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in entityTypeXml.entityTypeList){
   		entityTypeAC.addItem({entity_id:object.entity_id,entity_name:object.entity_name,level:object.level});
   	}
  
  entityTypeCombo.dataProvider=entityTypeXml.entityTypeList;
 entityTypeCombo.labelField="entity_name";
 Mask.close();
 
   }




	}
}