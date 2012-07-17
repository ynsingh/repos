/**
 * @(#) Mask.as
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
package common
{
	import flash.display.Sprite;
	
	import mx.containers.Box;
	import mx.controls.ProgressBar;
	import mx.core.Application;
	import mx.managers.PopUpManager;
	
	public class Mask extends Box
	{
		
		private static var _mask:Mask;
		
		private var _message:String;
		
		public function Mask()
		{
			super();
		}
		
		 public static function show(message:String="Please Wait...", parent:Sprite=null):Mask{
		 	
		 	_mask = new Mask();
		 	_mask._message = message;
		 	PopUpManager.addPopUp(_mask, parent||Sprite(Application.application), true);
		 	PopUpManager.centerPopUp(_mask);
		 	_mask.setFocus();
		 	
		 	return _mask;	
		 }
		 
		 
		 public static function close():void {
			PopUpManager.removePopUp(_mask);
         	}
		 
		override protected function createChildren():void
		{
        	super.createChildren();
        	
			var pb:ProgressBar = new ProgressBar();
			pb.label = _message;
			pb.indeterminate = true;
			pb.labelPlacement= 'center';
			pb.setStyle('barColor', uint(0xAEAEAE));
			pb.height =50;
			pb.setStyle("fontSize",16);
			addChild(pb);				
		}
	}
}