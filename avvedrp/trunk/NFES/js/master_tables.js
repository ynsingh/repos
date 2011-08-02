
function edit_general_master(name){
	var str=name.split("_");
	document.forms[0].id.value=document.getElementById("id_"+str[1]).value;
	document.forms[0].fld_value.value=document.getElementById("fld_value_"+str[1]).value;
	document.forms[0].active_yes_no.value=document.getElementById("active_yes_no_"+str[1]).value;
	document.forms[0].action.value="edit_general_master";
	document.forms[0].submit();
}

function save_edit_general_master(){
	if(document.forms[0].new_fld_value.value==""){
             document.forms[0].new_fld_value.style.backgroundColor = "#fca9ae";
	     alert("Value field is empty");
	}else{
	     document.forms[0].new_fld_value.style.backgroundColor = "white";
	     document.forms[0].action.value="save_edit_general_master";
	     document.forms[0].submit();
	}
}

function enable_save_button(){	
	document.forms[0].save.disabled=false;
}

function save_add_new_general_master(){
        if(document.forms[0].fld_value.value==""){
	             document.forms[0].fld_value.style.backgroundColor = "#fca9ae";
		     alert("Value field is empty");
		}else{
		     document.forms[0].fld_value.style.backgroundColor = "white";
		     document.forms[0].action.value="save_add_new_general_master";
		     document.forms[0].submit();
	}
}

function general_master_combo_change(arg){
	document.forms[0].category.value=arg;
	document.forms[0].tab_name.value="general_master";
	document.forms[0].action.value="show_general_master";
	document.forms[0].submit();
}

function general_master_button_change(){
	document.forms[0].tab_name.value="general_master";
	document.forms[0].action.value="add_new_general_master";
	document.forms[0].submit();
}

function back_general_master(){
	document.forms[0].tab_name.value="general_master";
	document.forms[0].action.value="show_general_master";
	document.forms[0].submit();
}

function edit_principal_investigator_master(name){
	var str=name.split("_");
	document.forms[0].pi_name.value=document.getElementById("pi_name_"+str[1]).value;
	document.forms[0].department.value=document.getElementById("department_"+str[1]).value;
	document.forms[0].designation.value=document.getElementById("designation_"+str[1]).value;
	document.forms[0].email.value=document.getElementById("email_"+str[1]).value;
	document.forms[0].address.value=document.getElementById("address_"+str[1]).value;
	document.forms[0].active.value=document.getElementById("active_"+str[1]).value;
	document.forms[0].id.value=document.getElementById("id_"+str[1]).value;
	document.forms[0].action.value="edit_principal_investigator_master";
	document.forms[0].submit();
}

function back_principal_investigator_master(){
	document.forms[0].tab_name.value="principal_investigator_master";
	document.forms[0].action.value="show_principal_investigator_master";
	document.forms[0].submit();
}

function save_edit_principal_investigator_master(){
	if(document.forms[0].pi_name.value==""){
             document.forms[0].pi_name.style.backgroundColor = "#fca9ae";
	     alert("Name field is empty");
	}else if(document.forms[0].designation.value==""){
	     document.forms[0].designation.style.backgroundColor = "#fca9ae";
	     alert("Designation field is empty");
	}else if(isEmail(document.forms[0].email)==false){		
		   document.forms[0].email.style.backgroundColor = "#fca9ae";
	           alert("Email is not valid.");
		}		
	else {
	     document.forms[0].pi_name.style.backgroundColor = "white";
	     document.forms[0].designation.style.backgroundColor = "white";
	     document.forms[0].action.value="save_edit_principal_investigator_master";
	     document.forms[0].submit();
	}	
}

function isEmail(field) {
	var check = true;	
	var str=field.value;		
	if(str!=""){
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if(! str.match(re)) {
		    check = false;
        	}
        }
        return check;
}

function add_new_principal_investigator_master(){
	document.forms[0].tab_name.value="principal_investigator_master";
	document.forms[0].action.value="add_new_principal_investigator_master";
	document.forms[0].submit();
}

function save_add_new_principal_investigator_master(){
	if(document.forms[0].pi_name.value==""){
	     document.forms[0].pi_name.style.backgroundColor = "#fca9ae";
	     alert("Name field is empty");
	}else if(document.forms[0].designation.value==""){
	     document.forms[0].designation.style.backgroundColor = "#fca9ae";
	     alert("Designation field is empty");
	}else if(isEmail(document.forms[0].email)==false){		
		   document.forms[0].email.style.backgroundColor = "#fca9ae";
	           alert("Email is not valid.");
	}else {
	     document.forms[0].pi_name.style.backgroundColor = "white";
	     document.forms[0].designation.style.backgroundColor = "white";
	     document.forms[0].action.value="save_add_new_principal_investigator_master";
	     document.forms[0].submit();
	}	
}

function courses_taught_master_combo_change(arg){
	document.forms[0].faculty_name.value=arg;
	document.forms[0].tab_name.value="courses_taught_master";
	document.forms[0].action.value="show_courses_taught_master";
	document.forms[0].submit();
}

function edit_courses_taught_master(name){
	var str=name.split("_");
	document.forms[0].id.value=document.getElementById("id_"+str[1]).value;
	document.forms[0].academic_term.value=document.getElementById("academic_term_"+str[1]).value;
	document.forms[0].class_name.value=document.getElementById("class_name_"+str[1]).value;
	document.forms[0].course_name.value=document.getElementById("course_name_"+str[1]).value;
	document.forms[0].students_registered.value=document.getElementById("students_registered_"+str[1]).value;
	document.forms[0].percent_of_pass.value=document.getElementById("percent_of_pass_"+str[1]).value;
	document.forms[0].active.value=document.getElementById("active_"+str[1]).value;
	document.forms[0].action.value="edit_courses_taught_master";
	document.forms[0].submit();
}

function back_courses_taught_master(){
	document.forms[0].tab_name.value="courses_taught_master";
	document.forms[0].action.value="show_courses_taught_master";
	document.forms[0].submit();
}

function save_edit_courses_taught_master(){
	if(document.forms[0].class_name.value==""){
	     document.forms[0].class_name.style.backgroundColor = "#fca9ae";
	     alert("Class Name field is empty");
	}else if(document.forms[0].course_name.value==""){
	     document.forms[0].course_name.style.backgroundColor = "#fca9ae";
	     alert("Course Name field is empty");
	}else if(document.forms[0].students_registered.value==""){
	     document.forms[0].students_registered.style.backgroundColor = "#fca9ae";
	     alert("Students Registered field is empty");
	}else if(document.forms[0].percent_of_pass.value==""){
	     document.forms[0].percent_of_pass.style.backgroundColor = "#fca9ae";
	     alert("Percent of Pass field is empty");
	}else if(!validatePercentage(document.forms[0].percent_of_pass)){
	     alert("Please enter Proper Percentage");
	}else {
	     document.forms[0].class_name.style.backgroundColor = "white";
	     document.forms[0].course_name.style.backgroundColor = "white";
	     document.forms[0].students_registered.style.backgroundColor = "white";
	     document.forms[0].percent_of_pass.style.backgroundColor = "white";	
	     document.forms[0].action.value="save_edit_courses_taught_master";
	     document.forms[0].submit();
	}	
}
function validatePercentage(field) {
	var check = true;		
	if((isNaN(field.value))||(eval(field.value)<=0)||(eval(field.value)>100))
    	{
	    	check = false;
    	}
        return check;
}

function courses_taught_master_button_change(){
	document.forms[0].tab_name.value="courses_taught_master";
	document.forms[0].action.value="add_new_courses_taught_master";
	document.forms[0].submit();
}

function save_add_new_courses_taught_master(){
	if(document.forms[0].class_name.value==""){
		     document.forms[0].class_name.style.backgroundColor = "#fca9ae";
		     alert("Class Name field is empty");
		}else if(document.forms[0].course_name.value==""){
		     document.forms[0].course_name.style.backgroundColor = "#fca9ae";
		     alert("Course Name field is empty");
		}else if(document.forms[0].students_registered.value==""){
		     document.forms[0].students_registered.style.backgroundColor = "#fca9ae";
		     alert("Students Registered field is empty");
		}else if(document.forms[0].percent_of_pass.value==""){
		     document.forms[0].percent_of_pass.style.backgroundColor = "#fca9ae";
		     alert("Percent of Pass field is empty");
		}else if(!validatePercentage(document.forms[0].percent_of_pass)){
		     alert("Please enter Proper Percentage");
		}else {
		     document.forms[0].class_name.style.backgroundColor = "white";
		     document.forms[0].course_name.style.backgroundColor = "white";
		     document.forms[0].students_registered.style.backgroundColor = "white";
		     document.forms[0].percent_of_pass.style.backgroundColor = "white";	
		     document.forms[0].action.value="save_add_new_courses_taught_master";
		     document.forms[0].submit();
	}	
}

function edit_university_master(name){
	var str=name.split("_");
	document.forms[0].id.value=document.getElementById("id_"+str[1]).value;
	document.forms[0].university_name.value=document.getElementById("university_name_"+str[1]).value;
	document.forms[0].short_name.value=document.getElementById("short_name_"+str[1]).value;
	document.forms[0].address.value=document.getElementById("address_"+str[1]).value;
	document.forms[0].active.value=document.getElementById("active_"+str[1]).value;
	document.forms[0].action.value="edit_university_master";
	document.forms[0].submit();
}

function back_university_master(){
	document.forms[0].tab_name.value="university_master";
	document.forms[0].action.value="show_university_master";
	document.forms[0].submit();
}

function save_edit_university_master(){
	if(document.forms[0].university_name.value==""){
	     document.forms[0].university_name.style.backgroundColor = "#fca9ae";
	     alert("University Name field is empty");
	}else if(document.forms[0].short_name.value==""){
	     document.forms[0].short_name.style.backgroundColor = "#fca9ae";
	     alert("Short Name field is empty");
	}else if(document.forms[0].address.value==""){
	     document.forms[0].address.style.backgroundColor = "#fca9ae";
	     alert("Address field is empty");
	}else {
	     document.forms[0].university_name.style.backgroundColor = "white";
	     document.forms[0].short_name.style.backgroundColor = "white";
	     document.forms[0].address.style.backgroundColor = "white";
	     document.forms[0].action.value="save_edit_university_master";
	     document.forms[0].submit();
	}	
}

function add_new_university_master(){
	document.forms[0].tab_name.value="university_master";
	document.forms[0].action.value="add_new_university_master";
	document.forms[0].submit();
}

function save_add_new_university_master(){
	if(document.forms[0].university_name.value==""){
	     document.forms[0].university_name.style.backgroundColor = "#fca9ae";
	     alert("University Name field is empty");
	}else if(document.forms[0].short_name.value==""){
	     document.forms[0].short_name.style.backgroundColor = "#fca9ae";
	     alert("Short Name field is empty");
	}else if(document.forms[0].address.value==""){
	     document.forms[0].address.style.backgroundColor = "#fca9ae";
	     alert("Address field is empty");
	}else {
	     document.forms[0].university_name.style.backgroundColor = "white";
	     document.forms[0].short_name.style.backgroundColor = "white";
	     document.forms[0].address.style.backgroundColor = "white";
	     document.forms[0].action.value="save_add_new_university_master";
	     document.forms[0].submit();
	}	
}

function edit_institution_master(name){
	var str=name.split("_");
	document.forms[0].id.value=document.getElementById("id_"+str[1]).value;
	document.forms[0].institution_name.value=document.getElementById("institution_name_"+str[1]).value;
	document.forms[0].short_name.value=document.getElementById("short_name_"+str[1]).value;
	document.forms[0].address.value=document.getElementById("address_"+str[1]).value;
	document.forms[0].location.value=document.getElementById("location_"+str[1]).value;
	document.forms[0].university_id.value=document.getElementById("university_id_"+str[1]).value;
	document.forms[0].active.value=document.getElementById("active_"+str[1]).value;
	document.forms[0].action.value="edit_institution_master";
	document.forms[0].submit();
}

function back_institution_master(){
	document.forms[0].tab_name.value="institution_master";
	document.forms[0].action.value="show_institution_master";
	document.forms[0].submit();
}

function save_edit_institution_master(){
	if(document.forms[0].institution_name.value==""){
	     document.forms[0].institution_name.style.backgroundColor = "#fca9ae";
	     alert("Institution Name field is empty");
	}else if(document.forms[0].short_name.value==""){
	     document.forms[0].short_name.style.backgroundColor = "#fca9ae";
	     alert("Short Name field is empty");
	}else if(document.forms[0].address.value==""){
	     document.forms[0].address.style.backgroundColor = "#fca9ae";
	     alert("Address field is empty");
	}else if(document.forms[0].location.value==""){
	     document.forms[0].location.style.backgroundColor = "#fca9ae";
	     alert("Location field is empty");
	}else {
	     document.forms[0].institution_name.style.backgroundColor = "white";
	     document.forms[0].short_name.style.backgroundColor = "white";
	     document.forms[0].address.style.backgroundColor = "white";
	     document.forms[0].location.style.backgroundColor = "white";
	     document.forms[0].action.value="save_edit_institution_master";
	     document.forms[0].submit();
	}	
}

function add_new_institution_master(){
	document.forms[0].tab_name.value="institution_master";
	document.forms[0].action.value="add_new_institution_master";
	document.forms[0].submit();
}

function save_add_new_institution_master(){
	if(document.forms[0].institution_name.value==""){
	     document.forms[0].institution_name.style.backgroundColor = "#fca9ae";
	     alert("Institution Name field is empty");
	}else if(document.forms[0].short_name.value==""){
	     document.forms[0].short_name.style.backgroundColor = "#fca9ae";
	     alert("Short Name field is empty");
	}else if(document.forms[0].address.value==""){
	     document.forms[0].address.style.backgroundColor = "#fca9ae";
	     alert("Address field is empty");
	}else if(document.forms[0].location.value==""){
	     document.forms[0].location.style.backgroundColor = "#fca9ae";
	     alert("Location field is empty");
	}else {
	     document.forms[0].institution_name.style.backgroundColor = "white";
	     document.forms[0].short_name.style.backgroundColor = "white";
	     document.forms[0].address.style.backgroundColor = "white";
	     document.forms[0].location.style.backgroundColor = "white";
	     document.forms[0].action.value="save_add_new_institution_master";
	     document.forms[0].submit();
	}	
}

function institution_department_master_combo_change(value){
	document.forms[0].institution_id.value=value;
	document.forms[0].institution_name.value=document.forms[0].institution_combo.options[document.forms[0].institution_combo.selectedIndex].text;
	document.forms[0].tab_name.value="institution_department_master";
	document.forms[0].action.value="show_institution_department_master";
	document.forms[0].submit();
}

function edit_institution_department_master(name){
	var str=name.split("_");
	document.forms[0].id.value=document.getElementById("id_"+str[1]).value;
	document.forms[0].department_name.value=document.getElementById("department_name_"+str[1]).value;
	document.forms[0].department_id.value=document.getElementById("department_id_"+str[1]).value;
	document.forms[0].active.value=document.getElementById("active_"+str[1]).value;
	document.forms[0].action.value="edit_institution_department_master";
	document.forms[0].submit();
}

function back_institution_department_master(){
       document.forms[0].action.value="show_institution_department_master";
       document.forms[0].submit();
}

function save_edit_institution_department_master(){
	document.forms[0].action.value="save_edit_institution_department_master";
	document.forms[0].submit();	
}

function add_new_institution_department_master_button_click(){
	document.forms[0].action.value="add_new_institution_department_master";
	document.forms[0].submit();	
}

function save_add_new_institution_department_master(){
	document.forms[0].action.value="save_add_new_institution_department_master";
	document.forms[0].submit();	
}
