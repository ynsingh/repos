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
	
	import mx.core.IUIComponent;
	
	/**
	 * The IColumnFilterEditor defines interface of all column filters editors.
	 * Column filter editors are used to modify MDataGrid column filters.
	 * 
	 * <p>NOTE: This interface is not yet mature and there is the great chance 
	 * that it will be changed in the near future.</p>
	 * 
	 * @see com.iwobanas.controls.dataGridClasses.filters.ColumnFilterBase
	 */
	public interface IColumnFilterEditor extends IUIComponent
	{
		/**
		 * Column related to this filter.
		 */
		function get column():MDataGridColumn;
		
		/**
		 * Set MDataGrid column related to this filter.
		 * Implementation of this setter should also update MDataGridColumn 
		 * <code>editorInstance</code> property. 
		 */
		function set column(value:MDataGridColumn):void;
		
		/**
		 * Stop editing filter for the given column.
		 * Implementation of this column should also set MDataGridColumn 
		 * <code>editorInstance</code> property to null.
		 */
		function closeEditor():void;
		
	}
}