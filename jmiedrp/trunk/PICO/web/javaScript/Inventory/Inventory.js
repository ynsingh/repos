/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function makeDisableInReturnIssuedItem(){
    if (document.FrmReturnIssuedItems.radSelect[0].checked) {
       document.FrmReturnIssuedItems.action="SaveReturnIssuedItemsAction.action?radSelectvalue=IssueNo";
    }
    if(document.FrmReturnIssuedItems.radSelect[1].checked){
        document.FrmReturnIssuedItems.action="SaveReturnIssuedItemsAction.action?radSelectvalue=ItemSerialNo";
    }
}


function makeDisable(eimempId,eimauthId,eimsupId){

    var eimempId1=document.getElementById(eimempId)
    var eimauthId1=document.getElementById(eimauthId)
    var eimsupId1=document.getElementById(eimsupId)


    if (document.frmIssueItems.radSelect[0].checked) {


        eimauthId1.disabled=false
        eimempId1.disabled=true
        eimsupId1.disabled=true
        eimempId1.selectedIndex = 0;
        eimsupId1.selectedIndex = 0;


    }
    else if(document.frmIssueItems.radSelect[1].checked){


        eimauthId1.disabled=true
        eimempId1.disabled=false
        eimsupId1.disabled=true
        eimauthId1.selectedIndex=0;
        eimsupId1.selectedIndex=0;


    }
    else if(document.frmIssueItems.radSelect[2].checked){


        eimauthId1.disabled=true
        eimempId1.disabled=true
        eimsupId1.disabled=false
        eimauthId1.selectedIndex=0;
        eimempId1.selectedIndex=0;

    }

}


function selectall()
 {
 var list = document.getElementById("right");
 for (var i = 1; i < list.options.length; i++)
   {
    alert(list.options[i].value)
    list.options[i].selected = true;
   }
 }

 function getEmailAddressForCommittee(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getEmailAddressForCommittee.action?searchValue=" + searchValue,
            async:false
        }).responseText;

        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}

function getEmailAddressForEmployee(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getEmailAddressForEmployee.action?searchValue=" + searchValue,
            async:false
        }).responseText;

        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}


function getEmailAddressForSupplier(SourceListID, DestinationListID){
    var searchValue = document.getElementById(SourceListID).value;

    if (searchValue != "") {
        var msg = $.ajax({
            url:"/pico/ajax/getEmailAddressForSupplier.action?searchValue=" + searchValue,
            async:false
        }).responseText;
//        alert("Supplier is: "+msg);
        document.getElementById(DestinationListID).setAttribute("value",msg);
        }
}