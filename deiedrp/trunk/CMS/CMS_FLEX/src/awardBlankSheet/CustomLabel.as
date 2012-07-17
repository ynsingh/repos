// ActionScript file
package awardBlankSheet{
    import mx.controls.Label;
    import mx.controls.advancedDataGridClasses.AdvancedDataGridListData;
    import mx.controls.dataGridClasses.DataGridListData;
    import mx.controls.listClasses.*;
    import mx.events.FlexEvent;
    import mx.utils.ObjectUtil;
   
       
 
    public class CustomLabel extends mx.controls.Label {
    	
    	 private var _listData:BaseListData;
 
        private const POSITIVE_COLOR:uint = 0x000000; // Black
        private const NEGATIVE_COLOR:uint = 0xFF0000; // Red
        private const GREEN_COLOR:uint= 0x06a620;//GREEN
       public var myfield :String="";
             
         public var issuestatus :String="";
         
         
        public function CustomLabel(){
//        
        addEventListener( FlexEvent.DATA_CHANGE,setcolor);
   
        }
         
 
 
	private function setcolor(e:FlexEvent):void {	


//Alert.show('Data field: ' + AdvancedDataGridListData(listData).dataField + '\n'+
//        'Contents:' + AdvancedDataGridListData(listData).label + '\n' +
//        'Row:\n' + ObjectUtil.toString(AdvancedDataGridListData(listData).item));
        var obj1:Object=AdvancedDataGridListData(listData).item;
        
    
       
        
//		var myListData:DataGridListData = 
//                    DataGridListData(listData);
//                    var mydg:AdvancedDataGrid =AdvancedDataGrid(AdvancedDataGridListData);
//                    
//                    text="row index: " + String(myListData.rowIndex) + 
//                    " column index: " + String(myListData.columnIndex);
                    


 		  var s1:String = obj1.Correction;
          var myarray:Array= s1.split("|");
        if (myarray.length>2){
          	for (var idx:int = 0 ;idx<myarray.length;idx++){
          		if(myarray[idx]==myfield){
          			issuestatus = String(myarray[idx+2])
           		
          			break;
         		}
           	}
          	        		 if (issuestatus=="PEN"){
			 setStyle("color",NEGATIVE_COLOR)
			             
			           } else if (issuestatus=="RES"){
			           	setStyle("color",GREEN_COLOR);
			           } else {
			           		setStyle("color",POSITIVE_COLOR);
			           }  	
           	
 }else {
 	setStyle("color",POSITIVE_COLOR); // if there is no change 
 }
 

 }
 
//  override public function get listData():BaseListData
//   {
//  return _listData;
//   }
//  override   public function set listData(value:BaseListData):void
//   {
//    _listData = value;
//
//       }   
   
//  private function createComplete(e:FlexEvent){
// 	     
//			         //  		setStyle("color",POSITIVE_COLOR);
//			           		
//			    
// } 
   
   
    
		
				


        // Override the set method for the data property.
        override public function set data(value:Object):void {
    
    
       if(value != null)  {
                    super.data = value;
               

    }

}

			
		
    }
		
		
}
