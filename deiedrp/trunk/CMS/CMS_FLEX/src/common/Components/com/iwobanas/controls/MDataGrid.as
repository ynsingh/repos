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
package common.Components.com.iwobanas.controls
{
	import common.Components.com.iwobanas.controls.dataGridClasses.MDataGridColumn;
	import common.Components.com.iwobanas.controls.dataGridClasses.MDataGridEvent;
	import common.Components.com.iwobanas.core.ISearchable;
	import common.Components.com.iwobanas.utils.WildcardUtils;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.collections.CursorBookmark;
	import mx.collections.ICollectionView;
	import mx.collections.IList;
	import mx.collections.IViewCursor;
	import mx.collections.ListCollectionView;
	import mx.collections.XMLListCollection;
	import mx.controls.DataGrid;
	import mx.controls.dataGridClasses.DataGridColumn;
	
	/**
	 * Dispatched when the <code>originalCollection</code> property changes.
	 * 
	 * @eventType com.iwobanas.controls.dataGridClasses.MDataGridEvent.ORIGINAL_COLLECTION_CHANGE
	 */
	[Event(name="originalCollectionChange",type="com.iwobanas.controls.dataGridClasses.MDataGridEvent")]

	/**
	 * This class extends standard mx.controls.DataGrid by adding following features:
	 * <ul>
	 * <li>Searching across all columns with option to find next / previous item</li>
	 * <li>Filtering data using column header drop downs</li>
	 * </ul>
	 */ 
	public class MDataGrid extends DataGrid implements ISearchable
	{
		
		/**
		 * @copy com.iwobanas.core.ISearchable#found
		 */
		[Bindable("searchResultChanged")]
		public function get found():Boolean
		{
			return _found;
		}
		/**
		 * @private
		 * Storage for current found value.
		 */
		protected var _found:Boolean;
		
		/**
		 * @private
		 * Set <code>found</code> value and dispatch "searchResultChanged" event if needed.
		 */
		protected function setFound(value:Boolean):void
		{
			if (value != _found)
			{
				_found = value;
				dispatchEvent(new Event("searchResultChanged"));
			}
		}
		
		
		/**
		 * @copy com.iwobanas.core.ISearchable#searchString
		 */
		[Bindable("searchParamsChanged")]
		public function get searchString():String
		{
			return _searchString;
		}
		/**
		 * @private
		 * Storage for current searchString value.
		 */
		protected var _searchString:String;


		/**
		 * @copy com.iwobanas.core.ISearchable#searchExpression
		 */
		[Bindable("searchParamsChanged")]
		public function get searchExpression():RegExp
		{
			return _searchExpression;
		}
		
		/**
		 * @private
		 * Storage for current searchExpression value.
		 */
		protected var _searchExpression:RegExp;
		
		
		/**
		 * Find item matching given wildcard and assign first match to selectedItem.
		 * 
		 * <p>Unlike standard findSting() function this functions searches labels of all visible columns.
		 * It also supports wildcards containing <code>"?"</code> or <code>"*"</code> characters 
		 * interpreted as any character or any character sequence respectively.</p>
		 * 
		 * <p>The search starts at <code>selectedIndex</code> location and if match is find stops immediately.
		 * If it reaches the end of the data provider it starts over from the beginning. 
		 * If you need to navigate between matches user <code>findNext() / findPrevious()</code> functions.</p>
		 * 
		 * @param wildcard text to search for
		 * @param caseInsensitive flag indicating whether search should be case insensitive
		 * @return <code>true</code> if text was fond or <code>false</code> if not
		 * 
		 * @see com.iwobanas.core.ISearchable
		 */
		public function find(wildcard:String, caseInsensitive:Boolean = true):Boolean
		{
			if (!wildcard)
			{
				_searchString = null;
				_searchExpression = null;
				dispatchEvent(new Event("searchParamsChanged"));
				setFound(false);
				updateList();
				return false;
			}
			_searchString = wildcard;
			_searchExpression = WildcardUtils.wildcardToRegExp(wildcard, caseInsensitive ? "ig":"g");
			dispatchEvent(new Event("searchParamsChanged"));
			return findItem();
		}
		
		/**
		 * @copy com.iwobanas.core.ISearchable#findNext()
		 */
		public function findNext():Boolean
		{
			return findItem(true, true);
		}
		
		/**
		 * @copy com.iwobanas.core.ISearchable#findPrevious()
		 */
		public function findPrevious():Boolean
		{
			return findItem(false, true);
		}
		
		/**
		 * @private
		 * Iterate through data provider and check if it matches search condition by calling <code>matchItem()</code>.
		 * If matching item is found set <code>selectedIndex</code> to point to this item and scroll the content 
		 * so that selected item can be seen.
		 * 
		 * @param forward determines if items should be searched forward (from top to bottom) or backward.
		 * @param skip determines if search should start at <code>selectedIndex</code> or one item after/before.
		 */
		protected function findItem(forward:Boolean = true, skip:Boolean = false):Boolean
		{
			var cursor:IViewCursor = collection.createCursor();
			var itemFound:Boolean = false;
			
			if (selectedIndex > 0)
			{
				cursor.seek(CursorBookmark.FIRST, selectedIndex);
			}
			else
			{
				cursor.seek(CursorBookmark.FIRST);
			}
			
			if (skip)
			{
				if (forward)
				{
					cursor.moveNext();
				}
				else
				{
					cursor.movePrevious()
				}
			}
			
			// iterate through collection, note that "i" is not current index
			for (var i:int = 0; i < collection.length; i++)
			{
				if (matchItem(cursor.current))
				{
					itemFound = true;
					break;
				}
				
				if (forward)
				{
					cursor.moveNext();
				}
				else
				{
					cursor.movePrevious();
				}
				
				if (cursor.afterLast)
				{
					cursor.seek(CursorBookmark.FIRST);
				}
				if (cursor.beforeFirst)
				{
					cursor.seek(CursorBookmark.LAST);
				}
			}
			
			if (itemFound)
			{
				selectedItem = cursor.current;
				
				//scrollToIndex(selectedIndex); scrolls the content so that selected item is always the first item
				// we wanted selected item to be at the bottom if we scroll downward so scrollToIndex is not used.
				if (selectedIndex < verticalScrollPosition)
				{
					verticalScrollPosition = selectedIndex;
				}
				if (selectedIndex > verticalScrollPosition + rowCount - lockedRowCount - 2) // 1 for header + 1 for last row
				{
					verticalScrollPosition = selectedIndex - rowCount + lockedRowCount + 2;
				}
				
			}
			setFound(itemFound);
			
			return itemFound;
		}
		
		/**
		 * @private
		 * Mathes search parameters against item.
		 * 
		 * @param item item to be matched
		 * @return <code>true</code> if item matches search parameters, <code>false</code> otherwise.
		 */
		protected function matchItem(item:Object):Boolean
		{
			for each (var column:DataGridColumn in columns)
			{
				if (column.visible && _searchExpression && _searchExpression.test(column.itemToLabel(item)))
				{
					_searchExpression.lastIndex = 0;
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Flag indicating if data provider should be copied.
		 * 
		 * If <code>true</code> shallow copy of data provider is created
		 * and filters applied to data provider doesn't affect data provider.
		 * 
		 * If <code>false</code> filters are applied to original data provider.
		 * 
		 * NOTE: Changing this value when <code>dataProvider</code> is set 
		 * will take effect after next call to <code>dataProvider</code> setter.
		 */
		public var copyDataProvider:Boolean = true;
		
		/**
		 * If <code>copyDataProvider</code> is set this variable will store 
		 * original data provider converted to ICollectionView if needed while
		 * <code>dataProvider</code> will store a copy of data provider.
		 * 
		 * If <code>copyDataProvider</code> is <code>false</code> this will equal 
		 * to <code>dataProvider</code>.
		 */
		[Bindable("originalCollectionChange")]
		public var originalCollection:ICollectionView;
		
		
    	[Inspectable(category="Data", defaultValue="undefined")]
    	/**
    	 * @private
    	 * Override dataProvider setter to create a copy of data provider if needed.
    	 */
		override public function set dataProvider(value:Object):void
		{
			originalCollection = dataProviderToCollection(value);
			dispatchEvent(new MDataGridEvent(MDataGridEvent.ORIGINAL_COLLECTION_CHANGE));
			
			if (copyDataProvider)
			{
				var copy:ICollectionView = copyCollection(originalCollection);
				
				//TODO: Copy sort
				//TODO: Registet listeners for collection change events 
				super.dataProvider = copy;
			}
			else
			{
				super.dataProvider = originalCollection;
			}
		}
		
		/**
		 * @private
		 * Flag indicating that column filters have changed and data provider needs to be refreshed.
		 */
		protected var columnFiltersChanged:Boolean = false;
		
		/**
		 * Mark datagrid so that data provider will be refreshed
		 * (new filters values will take effect) on next call commitProperties().
		 * 
		 * This function may eventually be called by <code>"filteChange"</code> 
		 * and <code>"filterValueChange"</code> event handlers but for now 
		 * it is directly called by ColumnFilterBase.
		 */
		public function invalidateColumnFilters():void
		{
			columnFiltersChanged = true;
			invalidateProperties();
		}
		
		/**
		 * @private
		 * Array of filter functions of active column filters.
		 */
		protected var columnFilterFunctions:Array;
		
		/**
		 * @private
		 * Examine column filters and assign filter functions 
		 * of active filters to <code>columnFilterFunctions</code>.
		 */
		protected function updateColumnFilterFunctions():void
		{
			var cff:Array = [];
			for each (var column:DataGridColumn in columns)
			{
				if (column is MDataGridColumn)
				{
					var mc:MDataGridColumn = MDataGridColumn(column);
					if (mc.filter && mc.filter.isActive)
					{
						cff.push(mc.filter.filterFunction);
					}
				}
			}
			columnFilterFunctions = cff;
		}
		
		/**
		 * @private
		 * Filter function to be passed to data provider.
		 * This function is a logical AND of all functions in <code>columnFilterFunctions</code>
		 * i.e. if any of the functions in <code>columnFilterFunctions</code> return false
		 * this function will return false and item will be filtered out.
		 */
		protected function collectionFilterFunction(obj:Object):Boolean
		{
			//TODO: handle original collection filter
			for each (var cff:Function in columnFilterFunctions)
			{
				if (!cff(obj))
				{
					return false;
				}
			} 
			return true;
		}
		
		/**
		 * @private
		 */ 
		override protected function commitProperties():void
		{
			if (columnFiltersChanged)
			{
				columnFiltersChanged = false;
				updateColumnFilterFunctions();
				collection.filterFunction = collectionFilterFunction;
				collection.refresh();
			}
			super.commitProperties();
		}
		
		/**
		 * @private
		 * Convert Object to ICollectionView following the algorithm used by ListBase dataProvider setter.
		 * 
		 * This code is borrowed from ListBase dataProvider setter implementation.
		 */
		protected function dataProviderToCollection(value:Object):ICollectionView
		{
			var result:ICollectionView;
			if (value is Array)
        	{
            	result = new ArrayCollection(value as Array);
        	}
	        else if (value is ICollectionView)
	        {
	            result = ICollectionView(value);
	        }
	        else if (value is IList)
	        {
	            result = new ListCollectionView(IList(value));
	        }
	        else if (value is XMLList)
	        {
	            result = new XMLListCollection(value as XMLList);
	        }
	        else if (value is XML)
	        {
	            var xl:XMLList = new XMLList();
	            xl += value;
	            result = new XMLListCollection(xl);
	        }
	        else
	        {
	            // convert it to an array containing this one item
	            var tmp:Array = [];
	            if (value != null)
	                tmp.push(value);
	            result = new ArrayCollection(tmp);
	        }
	        return result;
		}
		
		/**
		 * @private
		 * Crate shallow copy of a collection.
		 * 
		 * This function is used to create a copy of data provider when <code>copyDataProvider</code> flag is set.
		 */
		protected function copyCollection(value:ICollectionView):ICollectionView
		{
			var copy:ICollectionView;
			if (originalCollection is ArrayCollection)
	        {
	        	copy = new ArrayCollection(ArrayCollection(originalCollection).source);
	        }
	        else if (originalCollection is XMLListCollection)
	        {
	        	copy = new XMLListCollection(XMLListCollection(originalCollection).source);
	        }
	        else if (originalCollection is ListCollectionView)
	        {
	        	copy = new ListCollectionView(ListCollectionView(originalCollection).list);
	        }
	        else
	        {
				var dp:Array = new Array();
				for each (var item:Object in originalCollection)
				{
					dp.push(item);
				}
				copy = new ArrayCollection(dp);
	        }
	        return copy;
		}
	}
}