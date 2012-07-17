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
	import common.Components.com.iwobanas.core.ISearchable;
	import common.Components.com.iwobanas.utils.WildcardUtils;
	
	import flash.events.Event;
	
	import mx.controls.TextArea;

	/**
	 * WARNING: This component is not working yet!
	 * It was created to test if ISearchable interface can be applied to component different than DataGrid
	 */
	public class SearchableTextArea extends TextArea implements ISearchable
	{
		public function SearchableTextArea()
		{
			super();
		}
		
		public function get searchExpression():RegExp
		{
			return _searchExpression;
		}
		protected var _searchExpression:RegExp;
		
		public function get searchString():String
		{
			return _searchString;
		}
		protected var _searchString:String;
		
		public function get found():Boolean
		{
			return _found;
		}
		protected var _found:Boolean;
		
		protected function setFound(value:Boolean):void
		{
			if (value != _found)
			{
				_found = value;
				dispatchEvent(new Event("searchResultChanged"));
			}
		}
		
		public function find(wildcard:String, caseInsensitive:Boolean = true):Boolean
		{
			if (!wildcard)
			{
				_searchString = null;
				_searchExpression = null;
				dispatchEvent(new Event("searchParamsChanged"));
				setFound(false);
				return false;
			}
			_searchString = wildcard;
			_searchExpression = WildcardUtils.wildcardToRegExp(wildcard , caseInsensitive ? "ig":"g" );
			return findText();
		}
		
		public function findNext():Boolean
		{
			return findText();
		}
		
		public function findPrevious():Boolean
		{
			return false;
		}
		
		protected function findText():Boolean
		{
			htmlText = text.replace(_searchExpression, "<b>$&</b>");
			return true;
			/* var result:Object = _searchExpression.exec(text);
			if (result)
			{
				selectionBeginIndex = result.index;
				selectionEndIndex = _searchExpression.lastIndex;
				return true;
			}
			return false; */
		}
		
	}
}