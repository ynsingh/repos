

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
  // var s=getSession().getAttribute("userid").toString();
   //alert("hellloo");
 //alert(s);
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


function getDepartmentList(SourceListID, DestinationListID,SUBINSITUTEID){
//alert('Welcome to my Web Site!');
    var searchValue = document.getElementById(SourceListID).value;
    var SIM_ID = document.getElementById(SUBINSITUTEID).value;

    if (searchValue != "") {

        var msg = $.ajax({
           // url:"/pico/ajax/getDepartmentList.action?searchValue=" + searchValue,
           //  url:"/pico/ajax/getAllocatedAmount.action?searchValue=" + searchValue +"&searchValue2="+DM_ID,
            url:"/pico/ajax/getDepartmentList.action?searchValue=" + searchValue +"&searchValue2="+SIM_ID,
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

function SetInstitutionDependenetLists_UserProfile(SourceListID, DestinationListID){
    //Set Sub Institutions List
    getSubinstitutionList(SourceListID, DestinationListID);
    //Set Departments to Blank List
    //alert('hii');
    document.getElementById("SaveUserProfile_erpmur_departmentmaster_dmId").options.length = 0;
    document.getElementById("SaveUserProfile_erpmur_departmentmaster_dmId").options.add(new Option("-- Please Select --", ""));
    //Reset User Role List
    getInstitutionUserRoleList(SourceListID, "SaveUserProfile_erpmur_institutionuserroles_iurId")
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


function getUOP(SourceListID, DestinationListID){

    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getUOP.action?searchValue=" + searchValue,
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
            url:"/pico/ajax/getItemsforInsitute.action?searchValue=" + searchValue,
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
   // alert(searchValue);
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

function getitemList(DestinationListID){


        var msg = $.ajax({
            url:"/pico/ajax/getitemList.action",
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






