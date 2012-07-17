package ProgramCourse.GridDataExporterService
{
	import mx.collections.IViewCursor;
	import mx.controls.Alert;
	import mx.controls.DataGrid;
	import mx.controls.dataGridClasses.DataGridColumn;
	
	
	public class DataGridDataExporter
	{

		public static function exportGridData(dg:DataGrid, csvSeparator:String="\t", lineSeparator:String="\n"):Array
		{
			
			var data:String = "";
			var header:String = "";
			var headerGenerated:Boolean = false;
			var columns:Array = dg.columns;
			var columnCount:int = columns.length;
			var column:DataGridColumn;
			var dataProvider:Object = dg.dataProvider;
			var rowCount:int = dataProvider.length;
			var dp:Object = null;
		var courseData:Array=new Array();
			var cursor:IViewCursor = dataProvider.createCursor();
			var row:int = 0;
			//loop through rows
			while (!cursor.afterLast)
			{
				var obj:Object = null;
				obj = cursor.current;
				courseData[row] = new Array();

				//loop through all columns for the row
				for(var col:int = 0; col < columnCount; col++)
				{
					column = columns[col];
					courseData[row][col]=column.itemToLabel(obj);
				}
				headerGenerated = true;
				row++;
				cursor.moveNext ();
			}//end of while loop
			
			//set references to null:
			dataProvider = null;
			columns = null;
			column = null;
			return (courseData);
		}
		    
		public static function checkDuplicateCourseCode(dg:DataGrid,code:String):Boolean{
			var bool:Boolean=true;
			var courseData:Array=DataGridDataExporter.exportGridData(dg);
			var courseCodeArray:Array = new Array();  
			
			for each(var obj:Object in dg.dataProvider){
				courseCodeArray.push(obj.course_code);
			}
			
			for(var i:int=0; i<courseCodeArray.length;i++){
				if(courseCodeArray[i]==code){
					bool=false;
					break;
				}
			}
			return bool;
		}
		
		public static function validateCourseCode(dg:DataGrid,code:String):Boolean{
			var bool:Boolean=true;
			var courseCodeArray:Array = new Array();  
			
			for each(var obj:Object in dg.dataProvider){
				courseCodeArray.push(obj.course_code);
			}
			
			for(var i:int=0; i<courseCodeArray.length;i++){
				for(var j:int=i+1;j<courseCodeArray.length;j++){
					if(courseCodeArray[i]==courseCodeArray[j]){
						bool=false;
						break;
					}
				}
			}
			return bool;
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
			
			if(data[0]=="" || data[2]=="" || data[3]=="" || data[4]=="" || data[5]=="" || data[6]=="")
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
