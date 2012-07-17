package systemTableTwo.asFiles
{
	import mx.collections.IViewCursor;
	import mx.controls.DataGrid;
	import mx.controls.dataGridClasses.DataGridColumn;

	public class ExportSelectedRow
	{

		public static function exportSelectedRowData(dg:DataGrid, selectedRowIndex:Number):Array
		{
			var gridRowData:Array=new Array();
			var columns:Array = dg.columns;
			var columnCount:int = columns.length;
			var column:DataGridColumn;
			var dataProvider:Object = dg.dataProvider;
			var dp:Object = null;
		
			var cursor:IViewCursor = dataProvider.createCursor ();

			
			//loop through rows
			var currentRowIndex:int=0;
			while (!cursor.afterLast)
			{
				var obj:Object = null;
				obj = cursor.current;
								
				if(currentRowIndex==selectedRowIndex)
				{
						//loop through all columns for the row
						for(var k:int = 0; k < columnCount; k++)
						{
							column = columns[k];
		
							gridRowData.push(column.itemToLabel(obj));

						}
						
				}
				currentRowIndex++;
				cursor.moveNext ();
			}
			
			//set references to null:
			dataProvider = null;
			columns = null;
			column = null;

			
			return (gridRowData);
		}
		
		
		public static function exportSelectedRowHeader(dg:DataGrid, selectedRowIndex:Number):Array
		{
			var gridRowHeader:Array=new Array();
			var columns:Array = dg.columns;
			var columnCount:int = columns.length;
			var column:DataGridColumn;
			var headerGenerated:Boolean = false;
			var dataProvider:Object = dg.dataProvider;
			var dp:Object = null;
			
			
			var cursor:IViewCursor = dataProvider.createCursor();
			
			//loop through rows
			var currentRowIndex:int=0;
			while (!cursor.afterLast)
			{
				var obj:Object = null;
				obj = cursor.current;
								
				if(currentRowIndex==selectedRowIndex)
				{
						//loop through all columns for the row
						for(var k:int = 0; k < columnCount; k++)
						{
							column = columns[k];
		
							if (!headerGenerated)
							{
								gridRowHeader.push(column.headerText);
								
							}
						}
					headerGenerated = true;	
				}

				currentRowIndex++;
				cursor.moveNext ();
			}
			
			//set references to null:
			dataProvider = null;
			columns = null;
			column = null;

			
			return (gridRowHeader);
		}
		
		
		public static function validateEmptyRow(dg:DataGrid):Boolean
			{
			var data:Array = new Array();
			var flag:Boolean=false;
			var columns:Array = dg.columns;
			var columnCount:int = columns.length;
			var column:DataGridColumn;
			var dataProvider:Object = dg.dataProvider;
			var rowCount:int = dataProvider.length;
			var dp:Object = null;
		
			var cursor:IViewCursor = dataProvider.createCursor();
			
			//loop through rows
			while (!cursor.afterLast)
			{
				cursor.moveNext();
			}	
			cursor.movePrevious();
			var obj:Object = null;
			obj = cursor.current;
				
				//loop through all columns for the row
				for(var k:int = 0; k < columnCount; k++)
				{
					column = columns[k];
					data[k]= column.itemToLabel(obj);
				}
				
			if(data[0]=="" || data[3]=="" || data[4]=="" || data[5]=="")
			{
				flag=true;
			}
			else{
				flag=false;
			}	
		
			//set references to null:
			dataProvider = null;
			columns = null;
			column = null;
		
			return flag;
		}	
	}

}
