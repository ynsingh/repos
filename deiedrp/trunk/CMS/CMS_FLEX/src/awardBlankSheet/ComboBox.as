

 
package awardBlankSheet {
	
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.ComboBox;


	public class ComboBox extends mx.controls.ComboBox
	{
		private var _input:String = "";
		private var _cancelClose:Boolean = false;
		
		override protected function keyDownHandler(event:KeyboardEvent):void {
			if(!event.altKey 
				&& !event.ctrlKey 
				&& (event.charCode >= 32 
				&& event.charCode <= 126)) {
				
				event.stopPropagation();
				
				if(!dataProvider && (dataProvider is Array || dataProvider is ArrayCollection))
					return;
				
				_input += String.fromCharCode(event.charCode);
				
				// Prevent close after change event (default behavior)
				_cancelClose = true;
				
				// Set the index to -1, so the currently selected field will be included in the search field
				dropdown.selectedIndex = Math.max(-1, dropdown.selectedIndex - 1);
				
				// Find the input string, if not found...
				if(!dropdown.findString(_input)) {
					
					// ...then try again with only the pressed key (default behavior)
					_input = String.fromCharCode(event.charCode);
					dropdown.findString(_input);
				}
				
				return;
			}
			super.keyDownHandler(event);
		}
		
		override public function close(trigger:Event=null):void {
			
			if(!_cancelClose) {
				super.close(trigger);
				// Clear the input string
				_input = "";
			}
			
			_cancelClose = false;
			
		}
		
	}
}
