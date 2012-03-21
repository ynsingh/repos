<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="getUserDetails" class="com.erp.nfes.GetRecordValue" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<%
String lc = "";
try{     
     lc=(String) session.getAttribute("language");    
     ml.init(lc);  
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}

%>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">

<HEAD profile=http://gmpg.org/xfn/11>
	<title>Institution Transfer</title>
	<link href="../css/oiostyles.css" type="text/css" rel="stylesheet">
	<link href="../css/jquery.datepick.css" rel="stylesheet" type="text/css" />
	
	<script language="javascript" src="../js/jquery-1.4.2.min.js"></script>
	<script language="JavaScript" src="../js/jquery.datepick.js"></script>
	<script language="javascript" src="../js/clinicaldoc_common.js"></script>
	<script language="javascript">
	$(document).ready(function(){
		$('#relieve_date').datepick({maxDate:'',dateFormat: 'dd-mm-yy',closeAtTop: false, showStatus: true,  showOn: 'both', buttonImageOnly: true, buttonImage: '../images/calendar.gif'});
		$('#joining_date').datepick({maxDate:'',dateFormat: 'dd-mm-yy',closeAtTop: false, showStatus: true,  showOn: 'both', buttonImageOnly: true, buttonImage: '../images/calendar.gif'});
		});
	
	function showInstitutions(selectedUniversity){
		clearDropDown('faculty_name');
		var url = '../GetAjaxResponse?action=SHOW_INSTITUTION&universityId='+selectedUniversity;
        if (window.ActiveXObject){
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }else if (window.XMLHttpRequest){
            httpRequest = new XMLHttpRequest();
        }        
        httpRequest.open("GET", url, true);
        httpRequest.onreadystatechange = function() {
        	processRequest("institution"); 
        	copyDropDownObject("institution","new_institution")
        	} ;
        httpRequest.send(null);             		
	}	
	
	function showDepartments(selectedInstitution) {	
		clearDropDown('faculty_name');	
	 	var url = '../GetAjaxResponse?action=SHOW_DEPARTMENTS&institutionId='+selectedInstitution;
        if (window.ActiveXObject){
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }else if (window.XMLHttpRequest){
            httpRequest = new XMLHttpRequest();
        }        
        httpRequest.open("GET", url, true);
        httpRequest.onreadystatechange = function() {
        	processRequest("department"); 
        	} ;
        httpRequest.send(null);  
	}	
	function showNewDepartments(selectedNewInstitution) {
		 	var url = '../GetAjaxResponse?action=SHOW_DEPARTMENTS&institutionId='+selectedNewInstitution
	        if (window.ActiveXObject){
	            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	        }else if (window.XMLHttpRequest){
	            httpRequest = new XMLHttpRequest();
	        }        
	        httpRequest.open("GET", url, true);
	        httpRequest.onreadystatechange = function() {
	        	processRequest("new_department"); 
	        	} ;
	        httpRequest.send(null);  
	}
	function showFaculties(Selected_Institution_DeptId ){
	 	var url = '../GetAjaxResponse?action=SHOW_FACULTIES&institutionDeId='+Selected_Institution_DeptId;
        if (window.ActiveXObject){
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }else if (window.XMLHttpRequest){
            httpRequest = new XMLHttpRequest();
        }        
        httpRequest.open("GET", url, true);
        httpRequest.onreadystatechange = function() {processRequest("faculty_name"); } ;
        httpRequest.send(null); 	
	}
	
	function showFacultyDetails(SelectedUserId){
		var url = '../GetAjaxResponse?action=SHOW_FACULTY_DEAILS&userId='+SelectedUserId;
        if (window.ActiveXObject){
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }else if (window.XMLHttpRequest){
            httpRequest = new XMLHttpRequest();
        }        
        httpRequest.open("GET", url, true);
        httpRequest.onreadystatechange = function() {showFacultyData(); } ;
        httpRequest.send(null); 	
	}

	function showFacultyData(){
		if (httpRequest.readyState == 4){
            if(httpRequest.status == 200) {
                var facultyDetXML = httpRequest.responseXML.getElementsByTagName("reponseXML")[0];
                var facultyDets="";
                if(facultyDetXML.childNodes[0]){
	               facultyDets = facultyDetXML.childNodes[0].nodeValue;             
					var faculty_Dets= facultyDets.split("~",2);
					if(faculty_Dets[1]=="null"){
   						document.getElementById('joined_date').value="";
					}else{
						document.getElementById('joined_date').value=faculty_Dets[1];
					}
					var optid="optdsg_"+faculty_Dets[0];
					document.getElementById(optid).defaultSelected = true;
                }                                            	
           }else{
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText);
            }         		
          }  
	}
	
	function processRequest(ctrlname){
		clearDropDown(ctrlname);
		if (httpRequest.readyState == 4){
            if(httpRequest.status == 200) {
                var institutionXML = httpRequest.responseXML.getElementsByTagName("reponseXML")[0];
                var institutions="";
                if(institutionXML.childNodes[0]){
	               institutions = institutionXML.childNodes[0].nodeValue;             
                }                                
                var institution_array=institutions.split("|");                
                for(var i=0;i<institution_array.length-1;i++){
                	var inst_options= institution_array[i].split("~",2);
					var optn = document.createElement("OPTION");
					optn.text = inst_options[1];
					optn.value = inst_options[0];
					document.getElementById(ctrlname).options.add(optn);
                }            
           }else{
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText);
            }         		
          }  
	}
	
	function copyDropDownObject(sourceObject,destinationobject){
	    var dest=document.getElementById(destinationobject);
    	for(var ind=dest.length-1;ind>=0;ind--){
			dest.remove(ind);
		}
		var source=document.getElementById(sourceObject);
		for(var ind=0;ind<=source.length-1;ind++){
			var optn = document.createElement("OPTION");
			optn.text = source.options[ind].text;
			optn.value =source.options[ind].value;
			document.getElementById(destinationobject).options.add(optn);
		}
	}
	function reloadURL() {
       // window.location.reload(true);
        window.location.href="InstitutionTransfer.jsp";
    }
    
    function checkdependancy(ctrlname,dependent_control,messaseTxt){
    	if(document.getElementById(dependent_control).value==""){
	        eval('document.forms[0].'+ctrlname+'.focus');
    		alert('Please Select '+messaseTxt);    	
    		return(false);
    	}else{
	    	return(true);
    	}
    }
    
    function saveTranfer(){
     var university=document.forms[0].university.value;
     var institution=document.forms[0].institution.value;
     var department=document.forms[0].department.value;
     var faculty_name=document.forms[0].faculty_name.value;
     var relieve_date=document.forms[0].relieve_date.value;
     var new_institution=document.forms[0].new_institution.value;
     var new_department=document.forms[0].new_department.value;
     var joining_date=document.forms[0].joining_date.value;
     var designation=document.forms[0].designation.value;
     var joined_date=document.forms[0].joined_date.value;
     if(university=="" || institution=="" || department=="" || faculty_name=="" || relieve_date=="" || new_institution=="" || new_department=="" || joining_date=="" || designation==""){
     	alert("Please Fill Mandatory Fields");
     }else if (institution==new_institution){
     	alert("Please select the new institution to be transfered.");
     }else if(compareDatesfunc(joining_date,relieve_date)==-1){
     	alert("Joining Date should be greater than or same as relieving date");
     }else if(compareDatesfunc(relieve_date,joined_date)==-1){
     	alert("Relieving Date should be greater than or same as Joined Date");     	
     }
     else{
	     document.forms[0].submit();
     }
    }
    function clearDropDown(ctrlname){
		 var myselect=document.getElementById(ctrlname);
	     for(var ind=myselect.length-1;ind>=0;ind--){
			myselect.remove(ind);
		 }    
		var optn = document.createElement("OPTION");
		optn.text = "-Select-";
		optn.value = "";
		document.getElementById(ctrlname).options.add(optn);	 
    }
	</script>
</head>

<body class="bodystyle">
<form id="institutionTransfer" action="../InstitutionTransfer" method="post"  >
<div  class="listdiv" align="center">
<div style="text-align: center; font-size:14px;font-weight: bold;height:8px;"> <%=ml.getValue("institution_transfer")%> </div>
<BR>
<% request.getParameter("Saved");
	if(request.getParameter("Saved")!=null){
	%>		
	<div id="divmsg" class="message">			
		<%=ml.getValue("saved_sccessfully")%>		
	</div>				
	<br>
	<%
}
%>
<table class="search_field_div" align="center" width="98%">  
<tr>
	<td width="15%"><label class="labeltext"><%=ml.getValue("university")%>:</label><label class="mandatory">*</label></td>
	<td width="34%">
		<select name="university" id="university" style="WIDTH:250px" ONCHANGE="showInstitutions(this.options[this.selectedIndex].value)">
		<option value="">-Select-</option>		
		<%
			Connection conn=null;
			PreparedStatement pst_university=null;
			try{
				conn=db.getMysqlConnection();
				if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){
					pst_university = conn.prepareStatement("SELECT id,NAME  FROM university_master WHERE id="+getUserDetails.getUniversityID(request.getUserPrincipal().getName()) +" and active_yes_no=1 ORDER BY NAME");
				}else{
					pst_university = conn.prepareStatement("SELECT id,NAME  FROM university_master WHERE active_yes_no=1 ORDER BY NAME");
				}	
			    ResultSet rs_university=pst_university.executeQuery();
			    while(rs_university.next()){
			    %>
			    <option value='<%=rs_university.getString("id")%>'><%=rs_university.getString("name")%></option>
			    <%
			    }
			}catch(Exception e){
	    		 e.printStackTrace();
			}
			conn.close();			
		%>
	</select>	
	</td>
	<td width="15%"><label class="labeltext"><%=ml.getValue("institution")%>:</label><label class="mandatory">*</label></td>
	<td width="34%">
		<select name="institution" id="institution" style="WIDTH:250px"  ONCLICK="checkdependancy('institution','university','University')" ONCHANGE="showDepartments(this.options[this.selectedIndex].value)">
		<option value="">-Select-</option>
		</select>
		<%
		%>
	</td>
</tr>
<tr>
	<td width="15%"><label class="labeltext"><%=ml.getValue("department")%>:</label><label class="mandatory">*</label></td>
	<td width="34%">
		<select name="department" id="department" style="WIDTH:250px"  ONCLICK="checkdependancy('department','institution','Institution')" ONCHANGE="showFaculties(this.options[this.selectedIndex].value)">
		<option value="">-Select-</option>
		</select>
		<%
		%>
	</td>	
	<td width="15%"><label class="labeltext"><%=ml.getValue("faculty_name")%>:</label><label class="mandatory">*</label></td>
	<td width="34%">
		<select name="faculty_name" id="faculty_name" style="WIDTH:250px" ONCLICK="checkdependancy('faculty_name','department','Department')" ONCHANGE="showFacultyDetails(this.options[this.selectedIndex].value)" >
		<option value="">-Select-</option>
		</select>
		<%
		%>
	</td>
</tr>
<tr>
	<td width="15%"><label class="labeltext"><%=ml.getValue("joined_date")%>:</label></td>	
	<td><input type="text" id="joined_date" name="joined_date" value="" readonly="readonly" size="10" /></td>		
	<td width="15%"><label class="labeltext"><%=ml.getValue("relieve_date")%>:</label><label class="mandatory">*</label></td>	
	<td><input type="text" id="relieve_date" name="relieve_date" value="" readonly="readonly" size="10" /></td>		
</tr>
<tr>	
	<td width="15%"><label class="labeltext"><%=ml.getValue("transfer_to")%>:<label class="mandatory">*</label></td>
	<td width="34%">
		<select name="new_institution" id="new_institution" style="WIDTH:250px" ONCLICK="checkdependancy('new_institution','university','University')" ONCHANGE="showNewDepartments(this.options[this.selectedIndex].value)">
		<option value="">-Select-</option>
		</select>
		<%
		%>
	</td>		
	<td width="15%"><label class="labeltext"><%=ml.getValue("new")%>&nbsp;<%=ml.getValue("department")%>:</label><label class="mandatory">*</label></td>
	<td width="34%">
		<select name="new_department" id="new_department" ONCLICK="checkdependancy('new_department','new_institution','New Institution')" style="WIDTH:250px">
		<option value="">-Select-</option>
		</select>
		<%
		%>
	</td>	
</tr>
<tr>	
	<td width="15%"><label class="labeltext"><%=ml.getValue("joining_date")%>:</label><label class="mandatory">*</label></td>	
	<td><input type="text" id="joining_date" name="joining_date" value="" readonly="readonly" size="10" /></td>	
	<td width="15%"><label class="labeltext"><%=ml.getValue("designation")%>:</label><label class="mandatory">*</label></td>
	<td width="34%">
		<select name="designation" id="designation" style="WIDTH:250px" >
		<option value="null">-Select-</option>		
		<%
			try{
				conn=db.getMysqlConnection();
				PreparedStatement pst = conn.prepareStatement("SELECT id,fld_value FROM `general_master` WHERE category='Designation' AND  active_yes_no=1  ORDER BY fld_value");
			    ResultSet rs_university=pst.executeQuery();
			    while(rs_university.next()){
			    	String optid="optdsg_"+rs_university.getString("fld_value");
			    %>
			    <option id='<%=optid%>' value='<%=rs_university.getString("id")%>'><%=rs_university.getString("fld_value")%></option>
			    <%
			    }
			}catch(Exception e){
	    		 e.printStackTrace();
			}
			conn.close();			
		%>
	</select>	
	</td>
</tr>	
<tr>
	<td></td>
	<td ><input type="button"  value="<%=ml.getValue("save")%>" onclick="saveTranfer();" />
	<input type="button"  value="<%=ml.getValue("clear")%>" onclick="reloadURL();" />
	</td>
</tr>
</table>
<br>

</div>
</form>
</body>
</html>