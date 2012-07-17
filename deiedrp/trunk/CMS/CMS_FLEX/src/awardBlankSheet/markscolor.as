// ActionScript file
package awardBlankSheet{
    import mx.controls.Label;
    import mx.controls.listClasses.*;
 
    public class markscolor extends Label {
 
        private const POSITIVE_COLOR:uint = 0x000000; // Black
        private const NEGATIVE_COLOR:uint = 0xFF0000; // Red
        public var datafield :String;
        public var requestedmarks :Number=0;
        public var remarks :String="";
        //public var Correction :String;
           
        
        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
            super.updateDisplayList(unscaledWidth, unscaledHeight);
            
            var marks:Number = parseFloat(data[datafield]) ;
            var myarray:Array;
            //var s1:String = parentDocument.awardSheetGrid.selectedItem[Correction].toString(); 
            var s1:String = data.Correction;
           myarray= s1.split("|");
           if (myarray.length>2){
           	for (var idx:int = 0 ;idx<myarray.length;idx++){
           		if(myarray[idx]==datafield){
           			requestedmarks=parseFloat(String(myarray[idx-1]));
           			remarks =String(myarray[idx+1]);
           			break;
           		}
           	}
           }
            if(requestedmarks>0){
            
              setStyle("color", (marks != requestedmarks) ? NEGATIVE_COLOR : POSITIVE_COLOR);
            } 
            
          
 
            /* Set the font color based on the item price. */
        
           
        }
        
        
}
}
