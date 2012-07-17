/*
The contents of this file are subject to the Mozilla Public License
Version 1.1 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at
http://www.mozilla.org/MPL/

Software distributed under the License is distributed on an "AS IS"
basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
License for the specific language governing rights and limitations
under the License.

The Original Code is: www.iwobanas.com code samples.

The Initial Developer of the Original Code is Iwo Banas.
Portions created by the Initial Developer are Copyright (C) 2009
the Initial Developer. All Rights Reserved.

Contributor(s):
*/
package common.Components.com.iwobanas.controls.dataGridClasses.filterEditors
{
	import common.Components.com.iwobanas.controls.dataGridClasses.MDataGridColumn;
	import common.Components.com.iwobanas.effects.SlideDown;
	import common.Components.com.iwobanas.effects.SlideUp;
	
	import flash.events.Event;
	
	import mx.containers.Box;
	import mx.events.FlexMouseEvent;
	import mx.managers.PopUpManager;

	/**
	 * The length of the transition when the drop-down closes, in milliseconds.
	 * @default 250
	 */
	[Style(name="closeDuration", type="Number", format="Time", inherit="no")]
	
	/**
	 * The length of the transition when the drop-down opens, in milliseconds.
	 * @default 250
	 */
	[Style(name="openDuration", type="Number", format="Time", inherit="no")]

	/**
	 * The DropDownFilterEditorBase defines base class for MDataGrid column filter editors 
	 * displayed as a drop-down.
	 * 
	 * <p>NOTE: As IColumnFilterEditor editor will be modified this class will be changed too.
	 * There is a plan to separate FilterEditorBase class implementing general filter editing 
	 * functionality and create DropDownFilterEditor class using Decorator design patter.</p>
	 * 
	 * @see com.iwobanas.controls.dataGridClasses.filterEditors.IColumnFilterEditor
	 */
	public class DropDownFilterEditorBase extends Box implements IColumnFilterEditor
	{
		/**
		 * Constructor.
		 */
		public function DropDownFilterEditorBase()
		{
			super();
			
			addEventListener(FlexMouseEvent.MOUSE_DOWN_OUTSIDE, mouseDownOutsideHandler);
			addEventListener(Event.DEACTIVATE, deactivateHandler);
			
		}
		
		/**
		 * Template method called when <code>column</code> property is changed.
		 * This function should be overridden by subclasses to initialize them
		 * based on column value.
		 */
		protected function columnChanged():void
		{
			// subclass may override this
		}
		
		/**
		 * @private
		 * Override stylesInitialized() function to initialize effects.
		 */
		override public function stylesInitialized():void
		{
			super.stylesInitialized();
			initializeEffects();
		}

		/**
		 * Initialize effects applied to the dropdown when it opens/closes.
		 */
		protected function initializeEffects():void
		{
			var closeDuration:Number = getStyle("closeDuration");
			var openDuration:Number = getStyle("openDuration");
			
			var slideDown:SlideDown = new SlideDown();
			slideDown.duration = openDuration;
			setStyle("creationCompleteEffect", slideDown);
			
			var slideUp:SlideUp = new SlideUp();
			slideUp.duration = closeDuration;
			setStyle("removedEffect", slideUp);
		}
		
		/**
		 * MDataGrid column related to this filter editor.
		 */
		[Bindable]
		public function get column():MDataGridColumn
		{
			return _column;
		}
		
		/**
		 * @private
		 */
		public function set column(value:MDataGridColumn):void
		{
			_column = value;
			_column.filterEditorInstance = this;
			columnChanged();
		}
		/**
		 * @private
		 * Storage variable for <code>column</code> property.
		 */
		protected var _column:MDataGridColumn;
		
		/**
		 * Close filter editor and null filterEditorInstance column property. 
		 */
		public function closeEditor():void
		{
			PopUpManager.removePopUp(this);
			column.filterEditorInstance = null;
		}
		
		/**
		 * @private
		 * Mouse down outside event handler.
		 * Close dropdown on mouse down outside.
		 */
		protected function mouseDownOutsideHandler(event:FlexMouseEvent):void
		{
			closeEditor();
		}
		
		/**
		 * @private
		 * Deactivate event handler.
		 * Close dropdown when window looses focus.
		 */
		protected function deactivateHandler(event:Event):void
		{
			closeEditor();
		}
	}
}