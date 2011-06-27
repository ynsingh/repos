<meta name="layout" content="main" />
<g:javascript src="jquery.js"/>
<g:javascript src="ddaccordion.js"/>
<style>
.myform{
margin:0 auto;
width:375px;
height:260px;
float:left;
margin:20px;
padding-left:10px;
}

/* ----------- stylized ----------- */
#stylized{
border:solid 2px #b7ddf2;
background:#ebf4fb;
}
#stylized h1 {
font-size:14px;
font-weight:bold;
margin-bottom:0px;
}
#stylized p{
font-size:11px;
color:#666666;
margin-bottom:15px;
border-bottom:solid 1px #b7ddf2;
padding-bottom:0px;
}
#stylized label{
display:block;
font-weight:bold;
text-align:right;
padding-top:4px;
width:140px;
float:left;
}
#stylized .small{
color:#666666;
display:block;
font-size:11px;
font-weight:normal;
text-align:right;
width:140px;
}
#stylized input{
float:left;
font-size:12px;
padding:4px 2px;
border:solid 1px #aacfe4;
width:200px;
margin:2px 0 5px 10px;
}

/*Button Style*/
	.formbutton{	
	clear: left;
	height: 24px;
	text-align: center;
	cursor: pointer;
	border: none;
	font-weight: bold;	
	width: 94px;
	color: white;
	background: transparent url(../images/links/bt_register.png) no-repeat 0 0;
	background:url(../images/links/bt_register.png) repeat-x left top;
	}	
.mandatory
{
 font-weight: bold;
 font-size:15px;
 color:#F60001;
}
.alert{ font-weight: bold;
           font-family:Arial, Helvetica, sans-serif;
		   padding:0px 0px 8px; 0px;
	       font-size:12px;color:green}	
</style>
<g:javascript src="blockui.js"/>				
		<script>	
		   $(document).ready(function() {
			  var dflt_id=$('#lms').val();
			  getLmsInfo(dflt_id);
			});
					 
			 function getLmsInfo(sel_id) { 
			  $('#loader').show();
			  var lms_name=$('#lms :selected').text();	
			  $('#title').html("Enter Database Details for "+lms_name);
			   $.post(
				 "ajaxGetLmsInfo",{id:sel_id},
				 function(data) {
				     $('#loader').hide();		
					 if(data!='') {		  
				     var arr=data.split('|^@^|');
					
					 if(arr[0]!='') $('#host').val(arr[0]);
					 if(arr[1]!='') $('#port').val(arr[1]); 
					 if(arr[2]!='') $('#uname').val(arr[2]); 
					 if(arr[3]!='') $('#paswd').val(arr[3]); 
				     if(arr[4]!='') $('#dbname').val(arr[4]); 
					 }
					 else
					 {
					  $('#host').val('');$('#port').val('');
					  $('#uname').val('');$('#paswd').val('');$('#dbname').val('');
					 }				   
				 }
			   );
			 }
			
			
			function validateForm(thisform)
          {
             var host=$.trim($('#host').val());
			 var port=$.trim($('#port').val());
			 var uname=$.trim($('#uname').val());
			 var paswd=$.trim($('#paswd').val());
			 var dbname=$.trim($('#dbname').val());
			 
			  if(host=='' || port=='' || uname=='' || paswd=='' || dbname=='')
			  {
			    alert("Please fill in all the mandatory fields.");			
				return false;
			  }	
			 var lms_id=$('#lms').val();
			 $('#lmsid').val(lms_id);
			 thisform.submit();
			 return true;
          }
		  
		  function Redirect(formid)
		  {			 
			 //Showing the loading overlay using bolckui
			   $('#content').block({ 
					message: '<h4>Transferring data ...Please wait..</h4>', 
					css: { border: '3px solid #a00' } 
				}); 
			  var lmsid=$('#lms').val();
		      window.location.href='transform?lms_id='+lmsid;
		      return false;
		  }
 </script>
<!-- ##################################  Layout body starts here  ###########################################-->
	<div id="wrapper">
		<div id="head">
			<div class="innnerBanner">
			<g:isLoggedIn>
			<div class="loginLink">
			<span>
			<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>			
			<b>${session.UserId}</b> (<a href="${resource(dir:'/logout')}" class="logout">Logout</a>)
			</span>
			</div>
			</g:isLoggedIn>
			</div>		    
		</div>
		<br />
		<g:if test="${flash.message}">
		<div align="center" class="alert"><strong>${flash.message}</strong></div>
		</g:if>
		<br />
		<strong>LMS</strong> &nbsp;<g:select keys="${lmsList.lmsId}"  from="${lmsList.lmsName}" onchange="getLmsInfo(this.value)" name="lms"  id="lms" style="width:150px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="loader" style="display:none;"><image src='../ajax-loader.gif'></label>		
		<br />
	<div id="content"> 
<!-- Middle area starts here -->	
	    <g:menu/>
		 
		<div style="float: left; width: 790px;margin-right: 15px;">
		                    <div id="stylized" class="myform">
							   <g:form action="update" name="settings" id="settings">
									<h1 id="title"></h1>
									<p>&nbsp;</p>
									
									<label>Host Name <span class="mandatory">*</font></label>
									<input type="text" name="host" id="host" value="" />
									
									<label>Port Number <span class="mandatory">*</font></label>
									<input type="text" name="port" id="port" value="" />
									
									<label>User Name <span class="mandatory">*</font></label>
									<input type="text" name="uname" id="uname"value=""/>
									
									<label>Password <span class="mandatory">*</font></label>
									<input type="text" name="paswd" id="paswd" value=""/>
									
									<label>Database Name <span class="mandatory">*</font></label>
									<input type="text" name="dbname" id="dbname" value=""/>
									
									
									<input type="hidden" name="lmsid" id="lmsid" value=""/>								
									<div style="padding-left:150px">
									<button type="button" class="formbutton" onclick="return validateForm(document.settings)"> 
									Update</button>&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="formbutton" id="formbutton" 
									onclick="return Redirect(document.settings);">Transform</button>
									</div>					
								</g:form>
						 </div>	
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br />
<!-- Middle area ends here -->		
  </div> <!-- End of content div -->
</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->