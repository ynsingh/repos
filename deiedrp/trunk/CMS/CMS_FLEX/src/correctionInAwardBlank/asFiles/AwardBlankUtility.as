/**
 * @(#) AwardBlankUtility.as
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

public function getbest(myArray:Array,rule:String):int{
		myArray.sort(Array.NUMERIC);
		
		var maxValue1:int =myArray.pop();
		var maxValue2:int =myArray.pop();
		var maxValue3:int =myArray.pop();
		var maxValue4:int =myArray.pop();
		var maxValue5:int =myArray.pop();
		
		var max:int =0;
		if(rule == "BT1" ){
			 max=maxValue1 ;
		} else if(rule == "BT2" ){
		   max=maxValue1+maxValue2;	
		} else if(rule == "BT3" ){
		   max=maxValue1+maxValue2+maxValue3;	
		} else if(rule == "BT4" ){
		   max=maxValue1+maxValue2+maxValue3+maxValue4;	
		} else if(rule == "BT5" ){
		   max=maxValue1+maxValue2+maxValue3+maxValue4+maxValue5;	
		} else{
			max=maxValue1+maxValue2;
		}
		
	
		return	max;
	}


	public function getTotal(row:Object,col:AdvancedDataGridColumn):String
	{
		var myArray:Array=new Array();
		var tempArray:Array=new Array();
		var rule:String = "";
		var myArray:Array=new Array();
				var total:int=0;
		
	    for each(var obj2:Object in componentAC){ //loop 03
	       
			var groupName:String=obj2.group;
			if(tempArray.indexOf(groupName)<0){	 //loop 02
		
		
			   	var arr:Object=new Object();
			   	
			   	var i:int=0;
			   	rule = obj2.rule;
			   	for each(var obj3:Object in componentAC){ // start loop 01
					if(groupName==obj3.group){
						if(obj3.idType=='MK'){
							arr[i++]=obj3.evaluationId;
			   			}
			   		}
			   	}// end for loop 01
			   	tempArray.splice(0);
			   	myArray.splice(0) ;
				tempArray.push(groupName);
				myArray.push(arr);
	//		}  	
	//	}  
			
		//var total:int=0;
		for each(var ac:Object in myArray){			
			var arr1:Array=new Array();	
			for each(var o:String in ac){
				arr1.push(int(row[o]));
			}
			
			
			
			total=total+getbest(arr1,rule);
		}
		}//loop 02
	    }//loop 03
		if(display_type=='I'){
		row["totalInternal"]=total;
		}else if(display_type=='E'){
			row["totalExternal"]=total;
		}else{
			row["totalMarks"]=total;
		}
		return	total+"";
	}
	
public function getInWords(row:Object,col:AdvancedDataGridColumn):String {
	var num:String=row[col.dataField.slice(0,col.dataField.length-2)];
	if(num==null){
		return "";
	}
	else{
		return commonFunction.convertNumberToWord(int(num));
	}
}

	public function calculategrade(marks:int):String{
	
	var wtotalmarks:int = new int(); 
	var lcut :int = new int();
	var ucut:int = new int();
	
	wtotalmarks =marks ;
			
	for each (var obj:Object in gradeLimitAC ){ 
		lcut = obj.marksfrom ;
		ucut = 	obj.marksto ;
	if (wtotalmarks>=lcut && wtotalmarks <= ucut ) {
	return (obj.grades);
	}
	}
	return null ;
	}
	
public function isANumber(__str:String):Boolean { 
		    return !isNaN(Number(__str)); 
}
