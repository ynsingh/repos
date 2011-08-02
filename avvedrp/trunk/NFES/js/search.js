

function getResultFields(opt) 
{     //alert("1");	
      var selected = new Array();
      var index = 0;
      for (var intLoop=0; intLoop < opt.length; intLoop++) {
         if (opt[intLoop].selected) {
            index = selected.length;
            selected[index] = new Object;
            selected[index].value = opt[intLoop].value;
            selected[index].index = intLoop;
         }
      }
      
	var sel = selected;            
	var strSel = "";
        for (var item in sel)       
        strSel += sel[item].value + "\n";
        document.searchForm.result_field.value=strSel;        
}

function addtoSearchCondition(search_fld,search_op,search_value,search_value2)
{
getResultFields(document.searchForm.listfld);
//if(document.searchForm.searchConditions.value==""){
	document.searchForm.searchConditions.value= search_fld + " " + search_op + " " + search_value ;
/*}
else{
	document.searchForm.searchConditions.value= document.searchForm.searchConditions.value + "\n" +search_fld + " " + search_op + " " + search_value ;
}*/

if(search_value2!=""){
	document.searchForm.searchConditions.value=document.searchForm.searchConditions.value + " and " + search_value2;
}
}

function search1(){
	var search_condition=document.searchForm.searchConditions.value;
	var tmparr=search_condition.split(".");
	var table_name ="";
	var criteria="";
	var search_fld_value;
	var qry_string;
	
	table_name = tmparr[0].replace('_items','_values');	
	criteria=tmparr[1];
	if (criteria.search("equals")>0){
		search_fld_value=criteria.split("equals");
		qry_string=search_fld_value[0]+"='"+search_fld_value[1]+"'"; 	
	}
	if (criteria.search("start_with")>0){
		search_fld_value=criteria.split("start_with");
		qry_string=search_fld_value[0]+"'%"+search_fld_value[1]+"'"; 	
	}
	if (criteria.search("end_with")>0){
		search_fld_value=criteria.split("end_with");
		qry_string=search_fld_value[0]+"'"+search_fld_value[1]+"%'"; 	
	}
	if (criteria.search("includes")>0){
		search_fld_value=criteria.split("includes");
		qry_string=search_fld_value[0]+"'%"+search_fld_value[1]+"%'"; 	
	}
	qry_string="select idf from " + table_name + " where "+ qry_string 
	qry_string="SELECT user_full_name , username FROM users WHERE id IN(" + qry_string + ")"
	alert(qry_string);
	
}

function reset(){
	alert("Reseting...");
}

function search(){	
	addtoSearchCondition(document.searchForm.searchfld.value,document.searchForm.searchop.value,document.searchForm.searchvalue.value,document.searchForm.searchvalue2.value);
	if (document.searchForm.searchConditions.value==""){
		alert("Please enter a search condition");
	}
	else{		
		getResultFields(document.searchForm.listfld);
		document.forms[0].action.value="search";
		document.forms[0].submit();
		
	}
}	


function showHideSearchResults(tab_id){
if( document.getElementById(tab_id).style.display=='none' ){
   document.getElementById(tab_id).style.display = 'block';
 }else{
   document.getElementById(tab_id).style.display = 'none';
 }

}

function showSearchCondition(srchcondition){
document.searchForm.searchConditions.value=srchcondition;
}

function showdetals(UserId,documentid,entity_type){
//alert("showdetals Fucntion");
var entity_id=UserId;
var document_Id=documentid;	  	
formname="staff_profile_"+ entity_type +"_v0.xml";
window.open("./jsp/showdetailedformforapprove.jsp?entityId="+entity_id+"&documentId="+document_Id+"&formname="+formname+"&entitytype="+entity_type ,"DetailForm","height="+screen.height +", width="+screen.width+", scrollbars=yes,directories=no,location=no,menubar=no,resizable=no,status=yes,toolbar=no,modal=yes");
}