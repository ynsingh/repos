/**
 * @(#) CommonValidations.as
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
package common.validations
{
	import mx.controls.DateField;
	
	public class CommonValidations
	{
		public function CommonValidations()
		{
		}
		
		//Method to check first date is not less than the secon date
		public function datechecker(firstDate:DateField, secondDate:DateField ):Boolean 
		{
			if (firstDate.selectedDate>= secondDate.selectedDate) {
				return true;
			} else {
				return false;
			}
			
		}
		
		public function universitydatechecker(firstDate:DateField, secondDate:DateField ):Boolean 
				{			
					if ((int(secondDate.text.substring(6,10)))-(int(firstDate.text.substr(6,10)))>=1) {
						return false;
					} else {
						return true;
					}
					
				}

		
		 //Method to Check passed String is Blank or Not
		 public function validatateField(txtInput:String):Boolean
		 {
		 	if(txtInput=="")
		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}
		 }
		 
		 //Method to Check first parameter must be grater then second parameter.
		 public function isGreater(firstText:String, secondText:String):Boolean
		 {
		 	var firstnum:Number=Number(firstText);
		 	var secondnum:Number=Number(secondText);
		 	
		 	if(firstnum < secondnum)
		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}
		 }
		 
		 //Method to Check first parameter must be grater then second parameter.
		 public function PhoneNumberLength(phonenumber:String):Boolean
		 {
		 	if(phonenumber.length>20 || phonenumber.length<10)

		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}
		 }


	}
}