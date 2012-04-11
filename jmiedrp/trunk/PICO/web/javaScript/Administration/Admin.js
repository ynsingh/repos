

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

function getDepartmentForAdminList(SourceListID, DestinationListID){

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


function getItemRateListItem(SourceListID1, SourceListID2, DestinationListID2 ) { //}, InstitutionRole){
    var searchValue = document.getElementById(SourceListID1).value;
    var searchValue2 = document.getElementById(SourceListID2).value;
    
    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getItemRateListItem.action?searchValue=" + searchValue +"&searchValue2="+searchValue2,
            async:false
        }).responseText;
        alert(msg);
        var listText = unescape(msg);
        var TypeArray = new Array();
        var TypeArrayInfo = new Array();
        TypeArray = listText.split(",");
        document.getElementById(DestinationListID2).options.length = 0;
        document.getElementById(DestinationListID2).options.add(new Option("    Supplier                         Prices Vaid From      Valid Upto     Approved Rates    " , ""));
        for (i = 0; i < TypeArray.length; i++) {
            TypeArrayInfo = TypeArray[i].split("|");
            document.getElementById(DestinationListID2).options.add(new Option(TypeArrayInfo[1], TypeArrayInfo[0]));
        }
    }
    else{
        document.getElementById(DestinationListID2).options.length = 0;
        document.getElementById(DestinationListID2).options.add(new Option("-- Please Select --", ""));
    }
}




function  getRateForItem(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;
    alert(searchValue);
    if (searchValue != "") {
    var msg = $.ajax({
            url:"/pico/ajax/getRateForItem.action?searchValue=" + searchValue,
            async:false
        }).responseText;
      
        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}

function showItemRateDetails(SourceListID, DestinationId1, DestinationId2, DestinationId3, DestinationId4, DestinationId5)
{
    var searchValue = document.getElementById(SourceListID).value;
    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getItemRateForItemRateId.action?searchValue=" + searchValue,
            async:false
        }).responseText;


    var listText = unescape(msg);
    var TypeArray = new Array();
    var TypeArrayInfo = new Array();
    TypeArray = listText.split("|");

    document.getElementById(DestinationId1).setAttribute("value",Math.round(parseFloat(TypeArray[0])));
    document.getElementById(DestinationId2).setAttribute("value",TypeArray[1]);
    document.getElementById(DestinationId3).setAttribute("value",TypeArray[2]);
    document.getElementById(DestinationId4).setAttribute("value",TypeArray[3]);
    document.getElementById(DestinationId5).setAttribute("value",Math.round(parseFloat(TypeArray[0])));

    }
}
/*function getRateandUOP(SourceListID, DestinationListID1, DestinationListID2) {
          getUOP(SourceListID, DestinationListID1);
          //getItemRateForItem(SourceListID, DestinationListID2);
          getItemRateListItem(SourceListID, DestinationListID2);

}
*/
function getItemRateAndUOP(SourceListID, SourceListID2, DestinationListID1, DestinationListID2) {
          getUOP(SourceListID, DestinationListID1);
          //getItemRateForItem(SourceListID, DestinationListID2);          
          getItemRateListItem(SourceListID, SourceListID2, DestinationListID2);
   
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
function getitemList(DestinationListID){


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


function getaddressforSupplierList(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getaddressforSupplier.action?searchValue=" + searchValue,
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


