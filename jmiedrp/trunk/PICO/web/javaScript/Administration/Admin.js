

function ClearInstitutionFields(){
    document.forms[0].action = "ClearInstitution.action";
    document.forms[0].submit();
    return false;
}

function ClearSubInstitutionFields(){
    document.forms[0].action = "ClearSubInstitution.action";
    document.forms[0].submit();
    return false;
}

function ClearDepartmentFields(){
    document.forms[0].action = "ClearDepartment.action";
    document.forms[0].submit();
    return false;
}

function ClearIssueItemsFields(){
     document.forms[0].action = "ClearIssueItems.action";
    document.forms[0].submit();
    return false;
}
function getSubinstitutionAndgetSupplierList(SourceListID, SubInstitutionList, SupplierList){
   var searchValue = document.getElementById(SourceListID).value;
 
   var searchValue1=searchValue;
   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getSubinstitutionList.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");
        document.getElementById(SubInstitutionList).options.length = 0;
        document.getElementById(SubInstitutionList).options.add(new Option("-- Please Select --", ""));

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(SubInstitutionList).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(SubInstitutionList).options.length = 0;
        document.getElementById(SubInstitutionList).options.add(new Option("-- Please Select --", ""));
    }

    //This part retrieves Employees of the Institution
 if (searchValue1 != "") {

        var msg = $.ajax({
            url:"/pico/ajax/getSupplierList.action?searchValue="+searchValue1,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");

        document.getElementById(SupplierList).options.length = 0;
        document.getElementById(SupplierList).options.add(new Option("-- Please Select --", ""));

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(SupplierList).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{

        document.getElementById(SupplierList).options.length = 0;
        document.getElementById(SupplierList).options.add(new Option("-- Please Select --", ""));
    }

}
function getSubinstitutionList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;
    
   if (searchValue != "") {      
        var msg = $.ajax({
            url:"/pico/ajax/getSubinstitutionList.action?searchValue=" + searchValue,
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

function getEmployeeList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getEmployeeList.action?searchValue=" + searchValue,
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

function getAllSubinstitutionList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;
   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getSubinstitutionList.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("All Colleges/Faculties/Schools",0))

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(DestinationListID).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else {
                document.getElementById(DestinationListID).options.length = 0;
                document.getElementById(DestinationListID).options.add(new Option("All Colleges/Faculties/Schools",0));
    }

}
function getItemListfromsubcategory(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

   if (searchValue != "") {

        var msg = $.ajax({
          url:"/pico/ajax/getItemListTOS.action?searchValue=" +searchValue,
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

function getAllDepartmentList(SourceListID, DestinationListID){

    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getDepartmentList.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();

        TypeArray = listText.split(",");
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("All Departments",0));

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(DestinationListID).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("All Departments",0));
    }
}

function getDepartmentList(SourceListID, DestinationListID){

    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {

        var msg = $.ajax({
            url:"/pico/ajax/getDepartmentList.action?searchValue=" + searchValue,
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
        document.getElementById(DestinationListID).options.add(new Option("All Departments"));
    }
}

function getDepartmentForAdminList(SourceListID, DestinationListID) {

    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {

        var msg = $.ajax({
            url:"/pico/ajax/getDepartmentForAdminList.action?searchValue=" + searchValue,
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


function getSubinstitutionAndEmployeeList(SourceListID, SubInstitutionList, EmployeeList){
   var searchValue = document.getElementById(SourceListID).value;
   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getSubinstitutionList.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");
        document.getElementById(SubInstitutionList).options.length = 0;
        document.getElementById(SubInstitutionList).options.add(new Option("-- Please Select --", ""));

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(SubInstitutionList).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(SubInstitutionList).options.length = 0;
        document.getElementById(SubInstitutionList).options.add(new Option("-- Please Select --", ""));
    }

    //This part retrieves Employees of the Institution
 if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getEmployeeList.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");
        document.getElementById(EmployeeList).options.length = 0;
        document.getElementById(EmployeeList).options.add(new Option("-- Please Select --", ""));

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(EmployeeList).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(EmployeeList).options.length = 0;
        document.getElementById(EmployeeList).options.add(new Option("-- Please Select --", ""));
    }

}


function getDepartmentList(SourceListID, DestinationListID){

    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {

        var msg = $.ajax({
            url:"/pico/ajax/getDepartmentList.action?searchValue=" + searchValue,
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

function getBudgetAfterValidation(DestinationListID){


        var msg = $.ajax({
            url:"/pico/ajax/getBudgetAfterValidation.action",
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
function getForwardedToUserAfterValidation(DestinationListID){
        var msg = $.ajax({
            url:"/pico/ajax/getForwardedToUserAfterValidation.action",
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
function getInstitutionUserRoleList(SourceListID, DestinationListID){

    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getInstitutionUserRoleList.action?searchValue=" + searchValue,
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
function SetInstitutionDependenetLists(SourceListID, DestinationListID, InstitutionList) {       
       getSubinstitutionList(SourceListID, DestinationListID);       
//       getInstitutionUserRoleList(SourceListID, "SaveUserProfile_erpmur_institutionuserroles_iurId")
       getInstitutionUserRoleList(SourceListID, InstitutionList);
}

function SetInstitutionList(DestinationListID) {
        var msg = $.ajax({
            url:"/pico/ajax/getInstitutions.action",
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
function RetrieveSQ(UserName, DOB, SecretQuestion){
   var UName = document.getElementById(UserName).value;
   var DateofBirth = document.getElementById(DOB).value;

    if (UName == "" || DateofBirth == "")
        document.getElementById(SecretQuestion).setAttribute("value","Please provide values for the above fields");
    else
    {
        var msg = $.ajax({
            url:"/pico/ajax/getSecretQuestion.action?searchValue=" + UName +"&searchValue2="+DateofBirth,
            async:false
        }).responseText;
        document.getElementById(SecretQuestion).setAttribute("value",msg);
    }
}
//The function fetches the descriptionb of the Generic Role specified in GURID
 function showRoleRemarks (GURID, RemarksField) {
 var genericUserRoleId = document.getElementById(GURID).value;
        var msg = $.ajax({
            url:"/pico/ajax/getGURDescription.action?searchValue=" + genericUserRoleId ,
            async:false
        }).responseText;
    document.getElementById(RemarksField).setAttribute("value",msg);
}

function getProgramList(SourceListID, DestinationListID, InstitutionRole){
    var searchValue = document.getElementById(SourceListID).value;
    var IR = document.getElementById(InstitutionRole).value;
    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getProgramList.action?searchValue=" + searchValue +"&InstitutionRole="+IR,
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
function getUOP(SourceListID, DestinationListID1){

    var searchValue = document.getElementById(SourceListID).value;
    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getUOP.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        document.getElementById(DestinationListID1).setAttribute("value",msg);
        }
}

function getItemRateList(Item, Currency, Quantity, Rates, SelectedItemRate, TotalCost, Mode){ //RateOfNonAvailableItem, Mode) { //}, InstitutionRole){
    var searchValue = document.getElementById(Item).value;
    var searchValue2 = document.getElementById(Currency).value;
    var searchValue3 = document.getElementById(Quantity).value;
    var mode = document.getElementById(Mode).value;
    var totalCost = document.getElementById(TotalCost).value;
    var searchString = "http://www.google.co.in/search?hl=en&tbm=prc&as_qdr=y";
      
    if (searchValue != "") {
        //Find Item Name
        var itemName = $.ajax({url:"/pico/ajax/getItemName.action?searchValue=" + searchValue,
                        async:false
                        }).responseText;
        searchString = searchString + "&as_q=" + itemName;

        var msg = $.ajax({
            url:"/pico/ajax/getItemRateListItem.action?searchValue=" + searchValue +"&searchValue2="+searchValue2+"&searchValue3="+Math.round(searchValue3),
            async:false
        }).responseText;

        if(msg == "No rate is approved for the item. Please add on your own, if required" || msg == "") {
            if (mode == 'POMode')
                alert("Warning: The rates of the item have expired. Consider revising the rates or remove the Item from the PO");
            else if(totalCost == 0) {
                alert("Rates for this item are not approved. Pl. proceed to add the total approximate cost for the quantities required.");
                window.open(searchString,"_blank","fullscreen = no, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes, copyhistory=yes, width=400, height=400");
                document.getElementById(SelectedItemRate).setAttribute("value", "Self Estimate");
                }
        }
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");
        document.getElementById(Rates).options.length = 0;
        document.getElementById(Rates).options.add(new Option("    Supplier                         Prices Vaid From      Valid Upto     Approved Rates    " , ""));
        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(Rates).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(Rates).options.length = 0;
        document.getElementById(Rates).options.add(new Option("-- Please Select --", ""));
    } 
}




function  getRateForItem(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;
//    alert(searchValue);
    if (searchValue != "") {
    var msg = $.ajax({
            url:"/pico/ajax/getRateForItem.action?searchValue=" + searchValue,
            async:false
        }).responseText;
      
        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}

function showItemRateDetails(ItemRateID, ItemRate, ValidFromDate, ValidFromTo, Currency, minOrderQty,
                            maxOrderQty, Quantity, TotalCost, TaxNarration, TaxValue, TotalCostIncludingTaxes) {

    var searchValue = document.getElementById(ItemRateID).value;
    var varItemRateID= document.getElementById(ItemRateID).value.toString();

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getItemRateForItemRateId.action?searchValue=" + searchValue,
            async:false
        }).responseText;

    var listText = unescape(msg);
    var TypeArray = new Array();
    TypeArray = listText.split("|");
    
    document.getElementById(ItemRate).setAttribute("value",Math.round(parseFloat(TypeArray[0])));
    document.getElementById(ValidFromDate).setAttribute("value",TypeArray[1]);
    document.getElementById(ValidFromTo).setAttribute("value",TypeArray[2]);
    document.getElementById(Currency).setAttribute("value",TypeArray[3]);
    document.getElementById(minOrderQty).setAttribute("value",TypeArray[4]);
    document.getElementById(maxOrderQty).setAttribute("value",TypeArray[5]);
    
    if(varItemRateID!= "") {
    var msg = $.ajax({
        url:"/pico/ajax/getTaxNarration.action?searchValue=" + varItemRateID,
            async:false
        }).responseText;        

        document.getElementById(TaxNarration).value = msg;

        var msg = $.ajax({
            url:"/pico/ajax/getTaxValue.action?searchValue=" + varItemRateID + "&searchValue2=" + TypeArray[0].toString() + "&searchValue3=" + document.getElementById(Quantity).value ,
            async:false
        }).responseText;         

    document.getElementById(TaxValue).value = msg;


    }

    document.getElementById(TotalCost).setAttribute("value",Math.round(parseFloat(TypeArray[0]))*document.getElementById(Quantity).value);
    document.getElementById(TotalCostIncludingTaxes).setAttribute("value",(Math.round(parseFloat(TypeArray[0]))*document.getElementById(Quantity).value).valueOf()
                                                                            + parseFloat(document.getElementById(TaxValue).value));
    }
}


function updateTotalItemCost(Quantity, ItemRate, TotalCost) {

    //var Qty = document.getElementById(Quantity).value;
    var Rate = document.getElementById(ItemRate).value;

    document.getElementById(TotalCost).setAttribute("value",Rate);
}



function getItemRateAndUOP(Item, Currency, Quantity, Rates, UOP, SelectedItemRate, TotalCost, Mode) {
//          document.getElementById(TotalCost).setAttribute("value",document.getElementById(Quantity).value*document.getElementById(SelectedItemRate).value);
          getUOP(Item, UOP);
          getItemRateList(Item, Currency, Quantity, Rates, SelectedItemRate, TotalCost, Mode);
}

function getSuppliersName(SourceListID, DestinationListID) {
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getSuppliersName.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}

function getAllocatedAmount(SourceListID, DestinationListID, DepartmentID){
    var searchValue = document.getElementById(SourceListID).value;
    var DM_ID = document.getElementById(DepartmentID).value;
    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getAllocatedAmount.action?searchValue=" + searchValue +"&searchValue2="+DM_ID,
            async:false
        }).responseText;
       // alert("DH = " + searchValue + "DEPT=" + DM_ID + msg);
        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}
function getItemforInsituteList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;
    //alert(searchValue);
    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getItemforInsituteList.action?searchValue=" + searchValue,
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

function getsupplierforInsituteList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;
    
    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getsupplierforInsitute.action?searchValue=" + searchValue,
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

function getpoterms(SourceListID, DestinationListID){

    var searchValue = document.getElementById(SourceListID).value;
    //alert(searchValue);

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getpoterms.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        document.getElementById(DestinationListID).value =msg;
                //alert(msg);
        }
}
function getStateList(SourceListID, DestinationListID){
   var searchValue = document.getElementById(SourceListID).value.toString();

   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getStateList.action?searchValue=" + searchValue,
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

function getCountryList(DestinationListID){
        var msg = $.ajax({
            url:"/pico/ajax/getCountryList.action",
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

function getsupplieraftervalidation(DestinationListID){
        var msg = $.ajax({
            url:"/pico/ajax/getsupplieraftervalidation.action",
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

function getitemList(DestinationListID, SupplierId){
    var varSupplierId = document.getElementById(SupplierId).value;
//    alert();
        var msg = $.ajax({
            url:"/pico/ajax/getitemList.action?searchValue=" + varSupplierId,
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
function getIssueSerialList(SourceListID,DestinationListID){

      var searchValue = document.getElementById(SourceListID);

   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getIssueSerialList.action?searchValue=" + searchValue,
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
}

function getIssueMasterList(SourceListID,DestinationListID){
//             alert ("Source and Destination Committees cannot be same.");

      var searchValue = document.getElementById(SourceListID);
//             alert (""+searchValue);

   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getIssueMasterList.action?searchValue=" + searchValue,
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
}
function getitemList_2(DestinationListID){


        var msg = $.ajax({
            url:"/pico/ajax/getitemList.action",
            //url:"/pico/ajax/getitemListforPO.action",
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

function getInsituteaftervalidation(DestinationListID){
        var msg = $.ajax({
            url:"/pico/ajax/getInsituteaftervalidation.action",
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

function getDepartmentAfterValidation(DestinationListID){

        var msg = $.ajax({
            url:"/pico/ajax/getDepartmentAfterValidation.action",
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

function getCurrencyAfterValidation(DestinationListID){

    var msg = $.ajax({
            url:"/pico/ajax/getCurrencyAfterValidation.action",
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

function getWarrantyaftervalidation(DestinationListID){


        var msg = $.ajax({
            url:"/pico/ajax/getWarrantyaftervalidation.action",
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

function getEmployeeEmail(SourceListID, DestinationListID){

    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {

        var msg = $.ajax({

            url:"/pico/ajax/getEmployeeEmail.action?searchValue=" + searchValue,

            async:false

        }).responseText;

        document.getElementById(DestinationListID).setAttribute("value",msg);

        }

}


function getAddressForSupplier(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getAddressForSupplier.action?searchValue=" + searchValue,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split("?");
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));
        for (i = 0; i < TypeArray.length-1; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(DestinationListID).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));
    }
}

function zoomin(){
if(parent.parent.document.body.style.zoom!=0)
parent.parent.document.body.style.zoom*=1.2;
else
parent.parent.document.body.style.zoom=1.2;
}
function zoomout(){
if(parent.parent.document.body.style.zoom!=0)
parent.parent.document.body.style.zoom*=0.833;
else
parent.parent.document.body.style.zoom=0.833;
}
function zoomrest(){
if(parent.parent.document.body.style.zoom!=0)
parent.parent.document.body.style.zoom*=0;
else
parent.parent.document.body.style.zoom=0;
}

 function  compareCommittees(SourceListID, DestinationListID){
     var scommittee = document.getElementById(SourceListID).value.toString();     
     var dcommittee = document.getElementById(DestinationListID).value.toString();
     if(scommittee == dcommittee) {
             alert ("Source and Destination Committees canot be same.");
             event.returnValue=false;
     }
     else
         event.returnValue=true;
  }

 function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57) )
            return false;
    return true;
 }


function getWorkFlowStage(workFlowID, indentId, workFlowStage, workFlowAction,
                        sourceCommitteeID,sourceCommitteeName,
                        destinationCommitteeID, destinationCommitteeName,
                        committeeConvener, EditButton) {
    //Retrieves Current Workflow Stage
    findCurrentWFTStage(workFlowID, indentId, workFlowStage, sourceCommitteeID,
                        sourceCommitteeName, destinationCommitteeID,
                        destinationCommitteeName,committeeConvener );

//For Current WorkFlow Stage Retrieve the Permitted Actions
    findStageWorkFlowActions(workFlowID, document.getElementById(workFlowStage).value.toString(), workFlowAction);


}

function findCurrentWFTStage(workFlowID, indentId, workFlowStage, 
                sourceCommitteeID, sourceCommitteeName,
                destinationCommitteeID, destinationCommitteeName,
                committeeConvener) {
    var varWorkFlowID = document.getElementById(workFlowID).value.toString();
    var varIndentId = document.getElementById(indentId).value.toString();

    if (varWorkFlowID != "") {
       var msg = $.ajax({
            url:"/pico/ajax/findCurrentWFTStage.action?searchValue=" + varWorkFlowID +"&searchValue2="+varIndentId ,
            async:false
        }).responseText;

        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split("|");

        document.getElementById(workFlowStage).setAttribute("value",TypeArray[0]);

        TypeArrayInfo = TypeArray[1].split(",");
        document.getElementById(sourceCommitteeID).setAttribute("value",TypeArrayInfo[0]);
        document.getElementById(sourceCommitteeName).setAttribute("value",TypeArrayInfo[1])

        TypeArrayInfo = TypeArray[2].split(",");
        document.getElementById(destinationCommitteeID).setAttribute("value",TypeArrayInfo[0]);
        document.getElementById(destinationCommitteeName).setAttribute("value",TypeArrayInfo[1]);

        document.getElementById(committeeConvener).setAttribute("value",TypeArray[3]);
}
}

function findStageWorkFlowActions(workFlowID, workFlowStage, workFlowAction) {
    var varWorkFlowID = document.getElementById(workFlowID).value.toString();
    var varIndentId = workFlowStage;
    var EditEnable = false;

    if (varWorkFlowID != "") {
       var msg = $.ajax({
            url:"/pico/ajax/findStageWorkFlowActions.action?searchValue=" + varWorkFlowID +"&searchValue2="+varIndentId ,
            async:false
        }).responseText;
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");
        document.getElementById(workFlowAction).options.length = 0;
        document.getElementById(workFlowAction).options.add(new Option("-- Please Select --", ""));
        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(workFlowAction).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(workFlowAction).options.length = 0;
        document.getElementById(workFlowAction).options.add(new Option("-- Please Select --", ""));
    }
}

function UpdateIndentCost(Qty, UnitRate, TotalCost) {
        document.getElementById(TotalCost).setAttribute("value",document.getElementById(Qty).value*document.getElementById(UnitRate).value);
}

function getIndentList(FromDate, ToDate, indentList, currency) {
    var varFromDate= document.getElementById(FromDate).value.toString();
    var varToDate = document.getElementById(ToDate).value.toString();
    var varCurrency =  document.getElementById(currency).value.toString();

    if (varFromDate.toString().length  == 0)
        varFromDate = '01-01-1900';
    if (varToDate.toString().length == 0 )
        varToDate  = date.getDate() + "-" + date.getMonth() + date.getFullYear();

    var msg = $.ajax({
        url:"/pico/ajax/getApprovedIndentList.action?searchValue=" + varFromDate +"&searchValue2="+varToDate+"&searchValue3="+varCurrency,
            async:false
        }).responseText;

    alert(msg);

    var listText = unescape(msg);
    var TypeArray = new Array();
    var TypeArrayInfo = new Array();
    TypeArray = listText.split(",");
    document.getElementById(indentList).options.length = 0;
    document.getElementById(indentList).options.add(new Option("-- Please Select --", ""));
    for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(indentList).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
}


function getIndentItems(indentNumber, indentItemList) {
    var varIndentNumber= document.getElementById(indentNumber).value.toString();

    if(varIndentNumber != "") {
    var msg = $.ajax({
        url:"/pico/ajax/getIndentItems.action?searchValue=" + varIndentNumber,
            async:false
        }).responseText;
    }
    else
        alert("Please select an Indent");

}

function submittoPO(URL) {
    var varURL= document.getElementById(URL).value.toString();
    alert(varURL);
    window.open("","", "_blank","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=400, height=400");
}

function showClause(genericTerm,clauseText) {
    var varGenericTerm= document.getElementById(genericTerm).value.toString();
    
    if(varGenericTerm != "") {
    var msg = $.ajax({
        url:"/pico/ajax/getClause.action?searchValue=" + varGenericTerm,
            async:false
        }).responseText;                 
         document.getElementById(clauseText).value = msg;

    }

}

function checkQtyDistribution(poNumber, item, Qty, locationId) {

    var varpoNumber= document.getElementById(poNumber).value.toString();
    var varItem = document.getElementById(item).value.toString();
    var varQty = document.getElementById(Qty).value.toString();
    var varlocationId = document.getElementById(locationId ).value.toString();
  
    var msg = $.ajax({
        url:"/pico/ajax/findMaxDistributableQty.action?searchValue=" + varpoNumber + "&searchValue2=" + varItem  + "&searchValue3=" + varlocationId,
            async:false
        }).responseText;

    if (parseFloat(varQty) > parseFloat(msg)) {
        
        alert("Cannot transfer more than " + parseFloat(msg) + " items to a location now") ;
        document.getElementById(Qty).value = parseFloat(msg);
    }
    return;
}

function getProgramBySubmoduleList(SourceListID, DestinationListID){

    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {

        var msg = $.ajax({

            url:"/pico/ajax/getProgramBySubmoduleList.action?searchValue=" + searchValue,
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


function getItemListTOS(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

   if (searchValue != "") {

        var msg = $.ajax({
          url:"/pico/ajax/getItemListTOS.action?searchValue=" + searchValue,
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

function getEmployeeList(SourceListID, DestinationListID)
{

     var searchValue = document.getElementById(SourceListID).value;
          alert("varSearchId1"+searchValue);

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getEmployeeList.action?searchValue=" + searchValue,
            async:false
        }).responseText;
         //alert("varSearchId"+msg);

        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");
                 //alert("TypeArray"+TypeArray);

        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));

        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            // alert("TypeArrayInfo"+TypeArrayInfo);
            document.getElementById(DestinationListID).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(DestinationListID).options.length = 0;
        document.getElementById(DestinationListID).options.add(new Option("-- Please Select --", ""));
    }

}
function getEmployeeListByDmID(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getEmployeeListByDmID.action?searchValue=" + searchValue,
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




function getIssueNoByEmpId(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getIssueNoByEmpId.action?searchValue=" + searchValue,
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


function getIssueNoByComId(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

//alert(searchValue);
    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getIssueNoByComId.action?searchValue=" + searchValue,
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


function EnableDisablePO_Challan(InvoiceType,PurchaseOrderNo,ChallanNo,showPOreport){


    var myselect=document.getElementById(InvoiceType)
    var myselect1=document.getElementById(PurchaseOrderNo)
    var myselect2=document.getElementById(ChallanNo)
    var myselect3=document.getElementById(showPOreport)

  if (myselect.options[0].selected==true){

  myselect1.disabled=false
  myselect2.disabled=false
    }

 if (myselect.options[1].selected==true){
  myselect1.disabled=true
  myselect3.disabled=true
  myselect1.selectedIndex = 0;
  myselect2.disabled=false
    }

if (myselect.options[2].selected==true){
  myselect1.disabled=false
  myselect3.disabled=false
  myselect2.disabled=true
  myselect2.selectedIndex = 0;
    }
}



function getPOList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;
    
   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getPOList.action?searchValue=" + searchValue,
            async:false
        }).responseText;
      //  alert (searchValue || msg);
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


function getChallanList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getChallanList.action?searchValue=" + searchValue,
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

function getProgramListForSubModule(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getProgramListForSubModule.action?searchValue=" + searchValue,
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


function getParentCategoryList(SourceListID, DestinationListID){
   var searchValue = document.getElementById(SourceListID).value;
   
   if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getParentCategoryList.action?searchValue=" + searchValue,
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

function getTenderName(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getTenderName.action?searchValue=" + searchValue,
            async:false
        }).responseText;
//        alert("Supplier is: "+msg);
        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}


function getStockInHand(SourceListID, DestinationListID,IndentId){
    var searchValue = document.getElementById(SourceListID).value;
    var indentId = document.getElementById(IndentId).value;


    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getStockInHand.action?searchValue=" + searchValue + "&searchValue2=" + indentId,
            async:false
        }).responseText;

        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}
