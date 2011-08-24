<head>	
	<title>New Registration</title>
<g:javascript src="jquery.js"/>
<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'register/jquery-ui-1.8.10.custom.css')}" />
<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'general.css')}" />

<style>


  /*Style for error*/
  .errors{ font-weight: bold;
           font-family:Arial, Helvetica, sans-serif;
		   padding:0px 0px 8px; 0px;
	       font-size:12px;color:red}
		   
	/*Button Style*/
	.formbutton{
	
	display: block;
	float: left;
	clear: left;
	height: 24px;
	text-align: center;
	cursor: pointer;
	border: none;
	font-weight: bold;	
	width: 94px;
	color: white;
	background: transparent url(../images/bt_register.png) no-repeat 0 0;
	background:url(../bt_register.png) repeat-x left top;
	}	   
  
</style>
<g:javascript src="ui/jquery.ui.core.js"/>
<g:javascript src="ui/jquery.ui.widget.js"/>
<g:javascript src="ui/jquery.ui.tabs.js"/>
<script>
	$(function() {
	      $("#tabs" ).tabs();		 
		//Selecting the tab after form submission			
		  var action='${params.action}';
          if(action=='registerUniversity')
             $("#tabs" ).tabs("select",0)
          if(action=='registerInstitute')
             $("#tabs" ).tabs("select",1)
           if(action=='registerUser')
              $("#tabs" ).tabs("select",2)
         			
			$('#univ').click(function() { $('#errors').html('')  });
			$('#inst').click(function() { $('#errors').html('')  });
			$('#staff').click(function() { $('#errors').html('')  });
			var dflt=$('#usr_univ_id').val();
			getInstitutes(dflt);
	});
	

  

	</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#btnAdd').click(function() {
			var num		= $('.clonedInput').length;	// how many "duplicatable" input fields we currently have
			var newNum	= new Number(num + 1);		// the numeric ID of the new input field being added
			var newElem = $('#input' + num).clone().attr('id', 'input' + newNum);

			newElem.children(':first').attr('id', 'name' + newNum).attr('name', 'lmsname');
			$('#input' + num).after(newElem);
			$('#btnDel').attr('disabled','');
			if (newNum == 3)
				$('#btnAdd').attr('disabled','disabled');
		});

		$('#btnDel').click(function() {
			var num	= $('.clonedInput').length;	// how many "duplicatable" input fields we currently have
			$('#input' + num).remove();		// remove the last element
			$('#btnAdd').attr('disabled','');	// enable the "add" button

			// if only one element remains, disable the "remove" button
			if (num-1 == 1)
				$('#btnDel').attr('disabled','disabled');
		});

		$('#btnDel').attr('disabled','disabled');
	});
</script>


<script>
        function validateUniversity(thisform)
        {                     
 var errorstr = '';
                      var msgstr = "Sorry, we cannot complete your request.\nKindly provide us the missing or incorrect information enclosed below.\n\n";
                      var i=0;
                      var flag=0;
                      var fcs_fld=new Array();
                      var illegalChars = /\W/; /* For User name validtion*/
                      var illegalChars1 = /[\W_]/;  /* For Password validtion*/
                      var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;   /* For Email Address*/


                      if ($.trim($('#univ_username').val())=='') { errorstr += "* Please enter Username.\n";  fcs_fld[i]='univ_username'; i++;}
                      if ($.trim($('#univ_username').val())!='')
                      {
                       if(reg.test($.trim($('#univ_username').val())) == false) { errorstr += "* Please provide valid Email Address for Username.\n";   fcs_fld[i]='univ_username'; i++;}
                      }
                      if ($.trim($('#univ_paswd').val())=='')  { errorstr += "* Please enter Password.\n";  fcs_fld[i]='univ_paswd'; i++;}
                      if ($.trim($('#univ_paswd').val())!='')
                      {
                         if ($.trim($('#univ_paswd').val()).length < 6) { errorstr += "* Password should contain atleast 6 characters.\n";   fcs_fld[i]='univ_paswd'; i++;}
                      }
                      if ($.trim($('#univ_paswd').val()).length >= 6 && $.trim($('#univ_paswd').val())!='') {
                      if ($.trim($('#univ_conpaswd').val())=='') { errorstr += "* Please Confirm Password.\n";   fcs_fld[i]='univ_conpaswd'; i++;}
                      }
                      if ($.trim($('#univ_conpaswd').val())!='' ) {
                      if ($.trim($('#univ_paswd').val()) != $.trim($('#univ_conpaswd').val())) { errorstr += "- Password Mismatch.\n";  fcs_fld[i]='univ_conpaswd'; i++;}
                      }
                      if ($.trim($('#univ_name').val())=='') { errorstr += "* Please enter University name.\n";  fcs_fld[i]='univ_name'; i++;}
                      if ($.trim($('#univ_address').val())=='') { errorstr += "* Please enter Unversity address.\n";  fcs_fld[i]='univ_address'; i++;}
                      if ($.trim($('#univ_captcha').val())=='') { errorstr += "* Please enter the Captcha code.\n";  fcs_fld[i]='univ_captcha'; i++;}

              if (errorstr != '')
              {
                      msgstr = msgstr + errorstr;
                      alert(msgstr);
                      $('#'+fcs_fld[0]+'').focus();$('#'+fcs_fld[0]+'').select();
                      return false;
              }
              else
              {
                      thisform.submit();
                      return true;
              }             
        }
        function validateInstitute(thisform)
        {
                    var errorstr = '';
                      var msgstr = "Sorry, we cannot complete your request.\nKindly provide us the missing or incorrect information enclosed below.\n\n";
                      var i=0;
                      var flag=0;
                      var fcs_fld=new Array();
                      var illegalChars = /\W/; /* For User name validtion*/
                      var illegalChars1 = /[\W_]/;  /* For Password validtion*/
                      var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;   /* For Email Address*/


                      if ($.trim($('#inst_username').val())=='') { errorstr += "* Please enter Username.\n";  fcs_fld[i]='inst_username'; i++;}
                      if ($.trim($('#inst_username').val())!='')
                      {
                       if(reg.test($.trim($('#inst_username').val())) == false) { errorstr += "* Please provide valid Email Address for Username.\n";   fcs_fld[i]='inst_username'; i++;}
                      }
                      if ($.trim($('#inst_paswd').val())=='')  { errorstr += "* Please enter Password.\n";  fcs_fld[i]='inst_paswd'; i++;}
                      if ($.trim($('#inst_paswd').val())!='')
                      {
                         if ($.trim($('#inst_paswd').val()).length < 6) { errorstr += "* Password should contain atleast 6 characters.\n";   fcs_fld[i]='inst_paswd'; i++;}
                      }
                      if ($.trim($('#inst_paswd').val()).length >= 6 && $.trim($('#inst_paswd').val())!='') {
                      if ($.trim($('#inst_conpaswd').val())=='') { errorstr += "* Please Confirm Password.\n";   fcs_fld[i]='inst_conpaswd'; i++;}
                      }
                      if ($.trim($('#inst_conpaswd').val())!='' ) {
                      if ($.trim($('#inst_paswd').val()) != $.trim($('#inst_conpaswd').val())) { errorstr += "- Password Mismatch.\n";  fcs_fld[i]='inst_conpaswd'; i++;}
                      }

                      if ($.trim($('#inst_univ_id').val())=='') { errorstr += "* Please Select a  University name.\n";  fcs_fld[i]='univ_name'; i++;}
                      if ($.trim($('#inst_name').val())=='') { errorstr += "* Please enter Institute name.\n";  fcs_fld[i]='inst_name'; i++;}
                      if ($.trim($('#inst_address').val())=='') { errorstr += "* Please enter Institute address.\n";  fcs_fld[i]='inst_address'; i++;}
                      if ($.trim($('#name1').val())=='') { errorstr += "* Please enter a LMS name.\n";  fcs_fld[i]='name1'; i++;}
                      if ($.trim($('#inst_captcha').val())=='') { errorstr += "* Please enter the Captcha code.\n";  fcs_fld[i]='inst_captcha'; i++;}

              if (errorstr != '')
              {
                      msgstr = msgstr + errorstr;
                      alert(msgstr);
                      $('#'+fcs_fld[0]+'').focus();$('#'+fcs_fld[0]+'').select();
                      return false;
              }
              else
              {
                      thisform.submit();
                      return true;
              }
                     
        }

         function Redirect()
        {
          window.location="../login/auth";
        }
</script>



<script>
 var realvalues = [];
 var textvalues = [];
 function getInstitutes(sel_id) { 
  $('#loader').show();
   $.post(
     "ajaxGetInstitutes",{id:sel_id},
     function(data) {
	  $('#loader').hide();
      $('#updateInst').html(data);
     }
   );
 }
 function getLms(sel_id) {
    realvalues = [];
   $('#showbox').html('');
   $.post(
     "ajaxGetLms",{id:sel_id},
     function(data) {
      $('#updateLms').html(data);
     }
   );
 }
 </script>

 <script>
 function call()
 {
	    realvalues = [];
        textvalues = [];
        $('#showbox').html('');
	$('#lmsname :selected').each(function(i, selected) {
		realvalues[i] = $(selected).val();
		textvalues[i] = $(selected).text();
		//alert($(selected).val());
		//alert($(selected).text());
	});

	//var apndval='-------------------- LMS Details ------------------<br /><br />';
     
         var apndval='';
	 for (x=0; x<realvalues.length; x++)
		{	
		   apndval+="<tr><td width='150'><strong>UserId for "+textvalues[x]+"</strong></td><td class='name'><input type='text' id='lms"+realvalues[x]+"' name='lms"+realvalues[x]+"'/></td></tr>";
		}
	$('#showbox').html(apndval);
 }

 function validateUser(thisform)
  {
	   
	        var errorstr = '';
                      var msgstr = "Sorry, we cannot complete your request.\nKindly provide us the missing or incorrect information enclosed below.\n\n";
                      var i=0;
                      var flag=0;
                      var fcs_fld=new Array();
                      var illegalChars = /\W/; /* For User name validtion*/
                      var illegalChars1 = /[\W_]/;  /* For Password validtion*/
                      var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;   /* For Email Address*/


                      if ($.trim($('#usr_username').val())=='') { errorstr += "* Please enter Username.\n";  fcs_fld[i]='usr_username'; i++;}
                      if ($.trim($('#usr_username').val())!='')
                      {
                       if(reg.test($.trim($('#usr_username').val())) == false) { errorstr += "* Please provide valid Email Address for Username.\n";   fcs_fld[i]='usr_username'; i++;}
                      }
                      if ($.trim($('#usr_paswd').val())=='')  { errorstr += "* Please enter Password.\n";  fcs_fld[i]='usr_paswd'; i++;}
                      if ($.trim($('#usr_paswd').val())!='')
                      {
                         if ($.trim($('#usr_paswd').val()).length < 6) { errorstr += "* Password should contain atleast 6 characters.\n";   fcs_fld[i]='usr_paswd'; i++;}
                      }
                      if ($.trim($('#usr_paswd').val()).length >= 6 && $.trim($('#usr_paswd').val())!='') {
                      if ($.trim($('#usr_conpaswd').val())=='') { errorstr += "* Please Confirm Password.\n";   fcs_fld[i]='usr_conpaswd'; i++;}
                      }

                      if ($.trim($('#usr_conpaswd').val())!='' ) {
                      if ($.trim($('#usr_paswd').val()) != $.trim($('#usr_conpaswd').val())) { errorstr += "- Password Mismatch.\n";  fcs_fld[i]='usr_conpaswd'; i++;}
                      }

                      if ($.trim($('#usr_univ_id').val())=='') { errorstr += "* Please Select a  University.\n";  fcs_fld[i]='usr_univ_id'; i++;}
                      if ($.trim($('#usr_inst_id').val())=='') { errorstr += "* Please Select a  Institute.\n";  fcs_fld[i]='usr_inst_id'; i++;}
                       if ($('#lmsname').val()==null) { errorstr += "* Please Select a  LMS.\n";  fcs_fld[i]='lmsname'; i++;}
                      if ($.trim($('#usr_captcha').val())=='') { errorstr += "* Please enter the Captcha code.\n";  fcs_fld[i]='usr_captcha'; i++;}
					  
					
					 
					  for (j=0; j<(realvalues.length); j++)
						{						
						   var field='lms'+realvalues[j];
						  if($.trim($('#'+field+'').val())=='')
						  {
						     alert("Please enter your userId for all the selected LMS");
							 $('#'+field+'').focus();
							 return false;
						  }						  
                        }
					
              if (errorstr != '')
              {
                      msgstr = msgstr + errorstr;
                      alert(msgstr);
                      $('#'+fcs_fld[0]+'').focus();$('#'+fcs_fld[0]+'').select();
                      return false;
              }
              else
              {
                        var uservalues = [];
						for (x=0; x<realvalues.length; x++)
						{
						 var element='lms'+realvalues[x];
						 var usrname=$('#'+element+'').val();
						 uservalues[x]=realvalues[x]+'@'+usrname;
						 //alert(uservalues[x]);
						}
						$('#usernames').val(uservalues);
						thisform.submit();
                                                return true;
              }

      
  }
 </script>

<!-- ##################################  Layout body starts here  ###########################################-->
	</head>

<body>
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>    
					<div id="menus_wrapper">

					<div id="sec_menu" style="height:40px;">					
					<li style="padding:10px 0px 0px 400px;" align="center">
					<font color="#FFFFFF" size="3" family="Verdana,Arial,sans-serif"><strong>REGISTER NOW!</strong></font></li>
					</ul>					
					</div>
					</div>
	    	</div>
	<div id="content"> <!-- Start of content div -->                     
                        
          
		  
<div align="center" style="padding:1px 0px 0px 275px;width:400px;">
<g:if test="${flash.message}">
	<div class="errors" id="errors"  align="center">${flash.message}</div>
</g:if>
<div id="tabs">
	<ul>
		<li><a href="#tabs-1"  id="univ"><strong>UNIVERSITY</strong></a></li>
		<li><a href="#tabs-2"  id="inst"><strong>INSTITUTE</strong></a></li>
		<li><a href="#tabs-3"  id="staff"><strong>STAFF / STUDENT</strong></a></li>
	</ul>
	<br />

	
	<div id="tabs-1">	
			<g:form action="registerUniversity" name="univ_register">
				<table width="359" height="201">
			<tbody>
			
			<tr>
			<td width="165" valign='top'><strong>Username:</strong><font color="red">*</font></td>
			<td width="182" valign='top'>
			<input type="text" name='univ_username' id='univ_username' value="${params.univ_username}" size="28"/>
			</td>
			</tr>
			
			<tr>
			<td valign='top'><strong>Password:</strong><font color="red">*</font></td>
			<td valign='top'>
			<input type="password" name='univ_paswd'  id='univ_paswd' value="" size="28"/>
			</td>
			</tr>
			
			<tr>
			<td valign='top'><strong>Confirm Password:</strong><font color="red">*</font></td>
			<td valign='top'>
			<input type="password" name='univ_conpaswd' id='univ_conpaswd' value="" size="28"/>
			</td>
			</tr>
			
			<tr>
			<td valign='top'><strong>University Name:</strong><font color="red">*</font> </td>
			<td valign='top'>
			<input type="text" name='univ_name' id='univ_name' value="${params.univ_name}" size="28"/>
			</td>
			</tr>
			
			<tr>
			<td valign='top'><strong>University Address:</strong><font color="red">*</font> </td>
			<td valign='top'>
			<textarea name='univ_address' id='univ_address' rows="3" cols="25" cols="25">${params.univ_address}</textarea>
			</td>
			</tr>
			
			<tr>
			<td valign='top'><strong>Enter Code: </strong><font color="red">*</font></td>
			<td valign='top'>
			<table>
			<tr><td height="66" valign="top">
			<input type="text" name="univ_captcha" id="univ_captcha" size="10"/></td>
			<td  valign="top"><img src="${createLink(controller:'captcha', action:'index')}" align="absmiddle"/></td>
			</tr>
			</table>
			
			</td>
			</tr>
			
			
			<tr>
			<td valign='top' class='name'>
		<a href="${createLink(action:'index',controller:'login')}" title="Back to Login Page"><strong>&lt;&lt;&nbsp;Back</strong></a></td>
			<td valign='top' class='name'>
			<input type='button' value='Register' onclick="return validateUniversity(document.univ_register)"/>
			</td>
			</tr>
			</tbody>
			</table>
			</form>

	</div> <!-- END OF TAB 1 -->
	
	
	<div id="tabs-2">
			<form action="/StudentActivityVisualization/register/registerInstitute" method="post" name="inst_register" id="inst_register" >
				<table width="360" height="201">
				<tbody>
				
				<tr>
				<td width="163" valign='top'><strong>Username:</strong><font color="red">*</font></td>
				<td width="185" valign='top'>
				<input type="text" name='inst_username' id='inst_username' value="${params.inst_username}" size="28"/>
				</td>
				</tr>
				
				<tr>
				<td valign='top'><strong>Password:</strong><font color="red">*</font></td>
				<td valign='top'>
				<input type="password" name='inst_paswd'  id='inst_paswd' value="" size="28"/>
				</td>
				</tr>
				
				<tr>
				<td valign='top'><strong>Confirm Password:</strong><font color="red">*</font></td>
				<td valign='top'>
				<input type="password" name='inst_conpaswd' id='inst_conpaswd' value="" size="28"/>
				</td>
				</tr>
				
				<tr>
				<td valign='top'><strong>University Name:</strong><font color="red">*</font> </td>
				<td valign='top'>
				<g:select optionKey="id" optionValue="univ_name" from="${University.list()}" id="inst_univ_id" name="inst_univ_id"  noSelection="['':'-------------- select -------------']"  value="${univ_name}"  style="width:183px;"  ></g:select>
				</td>
				</tr>
				
				<tr>
				<td valign='top'><strong>Institute Name:</strong><font color="red">*</font> </td>
				<td valign='top'>
				<input type="text" name='inst_name' id='inst_name' value="${params.inst_name}" size="28"/>
				</td>
				</tr>
				
				<tr>
				<td valign='top'><strong>Institute Address:</strong><font color="red">*</font> </td>
				<td valign='top'>
				<textarea name='inst_address' id='inst_address' rows="3" cols="25">${params.inst_address}</textarea>
				</td>
				</tr>
				
				<tr>
				<td valign='top' class='name'><strong>LMS Name :</strong><font color="red">*</font> </td>
				<td valign='top' class='name'>
				<div id="input1" style="margin-bottom:4px;" class="clonedInput" >
				<input type="text" name="lmsname" id="name1" size="28" value="" />
				</div>
				<div>
				<input type="button" id="btnAdd" value="Add" />
				<input type="button" id="btnDel" value="Remove" />
				</div>
				</td>
				</tr>
				
				<tr>
				<td valign='top'><strong>Enter Code:</strong> <font color="red">*</font></td>
				<td valign='top'>
				<table>
				<tr><td height="66" valign="top">
				<input type="text" name="inst_captcha" id="inst_captcha" size="10"/></td>
				<td valign="top"><img src="${createLink(controller:'captcha', action:'index')}" align="absmiddle"/></td>
				</tr>
				</table>
				</td>
				</tr>
				
				
				<tr>
				<td valign='top' class='name'>
		<a href="${createLink(action:'index',controller:'login')}" title="Back to Login Page"><strong>&lt;&lt;&nbsp;Back</strong></a></td>
				<td valign='top' class='name'>
				<input type='button' value='Register'  onclick="return validateInstitute(document.inst_register)"/>
				</td>
				</tr>
				</tbody>

				</table>
				</form>
	</div><!-- END OF TAB 2 -->
	
	
	<div id="tabs-3">
				<form action="/StudentActivityVisualization/register/registerUser" method="post" name="usr_register" id="usr_register" >
		<table width="397" height="201">
		<tbody>
		
		<tr>
		<td width="169" valign='top'><strong>Username:</strong><font color="red">*</font></td>
		<td width="216" valign='top'>
		<input type="text" name='usr_username' id='usr_username' value="${params.usr_username}" size="28"/>						</td>
		</tr>
		
		<tr>
		<td valign='top'><strong>Password:</strong><font color="red">*</font></td>
		<td valign='top'>
		<input type="password" name='usr_paswd'  id='usr_paswd' value="" size="28"/>						</td>
		</tr>
		
		<tr>
		<td valign='top'><strong>Confirm Password:</strong><font color="red">*</font></td>
		<td valign='top'>
		<input type="password" name='usr_conpaswd' id='usr_conpaswd' value="" size="28"/>						</td>
		</tr>
		
		<tr>
		<td valign='top'><strong>University Name:</strong><font color="red">*</font> </td>
		<td valign='top'>
		<g:select   optionKey="id" optionValue="univ_name" from="${University.list()}" id="usr_univ_id" name="usr_univ_id"  noSelection="['':'-------------- select -------------']"  onchange="getInstitutes(this.value)" style="width:183px;" >
		</g:select>
		<label id="loader" style="display:none;"><image src='../images/ajax-loader.gif'></label>
		</td>
		
		</tr>
		
		<tr>
		<td valign='top'><strong>Institute Name:</strong><font color="red">*</font> </td>
		<td valign='top' id="updateInst">
		<select style="width:183px;"><option>-------------- select -------------</option></select>
		</td>
		</tr>
		
		<tr>
		<td valign='top'><strong>Select LMS:</strong><font color="red">*</font> </td>
		<td valign='top'  id="updateLms">
		<select style="width:183px;">
		<option>-------------- select -------------</option>
		</select></td>
		</tr>
		
		<tr>
		<td colspan="2">
		<table id="showbox">
		<!-- LMS User name Boxes will be displayed here -->
		</table>
		</td>
		</tr>
		
		<tr>
		<td valign='top'><strong>Enter Code: </strong><font color="red">*</font></td>
		<td valign='top'>
		<table>
		<tr><td height="66" valign="top">
		<input type="text" name="usr_captcha" id="usr_captcha" size="10"/></td>
		<td valign="top"><img src="${createLink(controller:'captcha', action:'index')}" align="absmiddle"/></td>
		</tr>
		</table></td>
		</tr>
		
		
		
		
		<tr>
		<td valign='top' class='name'>
		<a href="${createLink(action:'index',controller:'login')}" title="Back to Login Page"><strong>&lt;&lt;&nbsp;Back</strong></a></td>
		<td valign='top' class='name'>
		<input type='hidden' name="usernames" id="usernames"/>
		<input type='button' value='Register'  onclick="return validateUser(document.usr_register)"/>						</td>
		</tr>
		</tbody>
		</table>
		</g:form>

	</div><!-- END OF TAB 3 -->
</div>

</div>


                          
 </div> <!-- End of content div -->
 <br /> <br />
</div>
	 <div id="footer">
          <div style="padding:8px 30px 20px 0px" align="right"><h4><font color="white">Developed by Amrita University under ERP, NME ICT, MHRD</font></h4></div>
</div>

</body>