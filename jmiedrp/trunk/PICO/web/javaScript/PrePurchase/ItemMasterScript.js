function getCapitalCategoryList(SourceListID, DestinationListID){
   var searchValue = document.getElementById(SourceListID).value;

   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getCapitalCategoryList.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();

        TypeArray = listText.split(",");
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(DestinationListID).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));
    }

}


function getSubCategoryList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;
   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getSubCategory1List.action?searchValue=" + searchValue,
            async:false
        }).responseText;

        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();

        TypeArray = listText.split(",");
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(DestinationListID).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));
    }

}


function getDepreciationMethodandPercentageList(SourceListID, DestinationListID, DestinationListID1){
   
    var searchValue = document.getElementById(SourceListID).value;
    var searchValue1 = searchValue;

   //this is for Depreciation value
   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getDepreciationMethodandPercentageList.action?searchValue="+searchValue,
            async:false
        }).responseText;

        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
       var str1;
       var str2;

        TypeArray = listText.split(",");
        document.getElementById(DestinationListID).options.length = 0;
                 

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");

var test6= new String(TypeArrayInfo[1]);
if(String(test6)=="S"){
    str1="Straight Line";
        str2="Written Down Value";

}
else{
      str1="Written Down Value";
      str2="Straight Line";

}
        document.getElementById(DestinationListID).options.add(new Option(str1, ""));

            document.getElementById(DestinationListID).options.add(new Option(str2, TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));
    }
    //this is for getting Percantage value
if (searchValue1 != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getPercentageList.action?searchValue=" + searchValue1,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
       //document.getElementById(DestinationListID1).options.length = 0;
        //document.getElementById(DestinationListID1).options.add(new Option("-- Please Select --", ""));
            
        TypeArray = listText.split(",");
//get percantage value from TypeArray
       for (i = 0; i < TypeArray.length; i++) {

            TypeArrayInfo = TypeArray[i].split("|");
          

            /* document.getElementById(DestinationListID1).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));*/
            //set TypeArrayInfo[1] value to Percantage textbox value 
          document.getElementById(DestinationListID1).value=""+TypeArrayInfo[1];
    
   }
       // document.getElementById(DestinationListID1).value=
    }

    else{
       document.getElementById(DestinationListID1).value=""
    }


}
