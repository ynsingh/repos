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
