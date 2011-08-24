<head>	
	<title>New Registration</title>
	<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'general.css')}" />
	<g:javascript src="jquery.js"/>
	<style>
		.myform{
		margin:0 auto;
		width:375px;
		float:middle;
		margin:20px;
		padding-left:0px;
		}
		
		/* ----------- stylized ----------- */
		#stylized{
		border:solid 2px #b7ddf2;
		background:#ebf4fb;
		}
		
		#stylized label{
		display:block;
		font-weight:bold;
		text-align:left;
		padding-top:1px;
		width:60px;
		float:middle;
		}			
</style>
<script>
function validate(thisform)
{
  var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;   /* For Email Address*/
  if( $.trim($('#email').val())=='')
  {
    alert("Please enter your Username");
	return false;
  }
   if(reg.test($.trim($('#email').val())) == false)
    {alert("Please enter a valid EmailId for Username");
	return false;
  }
   thisform.submit();
}  
</script>
</head>
<body>
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>    
					<div id="menus_wrapper">

					<div id="sec_menu" style="height:40px;">					
					<li style="padding:10px 0px 0px 400px;" align="center">&nbsp;
					</li>
					</ul>					
					</div>
					</div>
	    	</div>
	<div id="content" align="center"> <!-- Start of content div -->
	<div align="center">
	<br /><br />
	<H3>FORGOT PASSWORD</H3>
	<div align="center">${flash.message}</div>	 
            <g:form action="sendNewPassword" name="forgotpassword">
                <div id="stylized" class="myform">	
						<table>		
						<tr>
						<td>&nbsp;</td>			
						</tr>			  
						<tr>
						<td><strong>Please type your User Name below.</strong></td>			
						</tr>
						<tr>		
						<td><input class="text_field" id="email" name="email" type="text" width="50"/></td>
						</tr>
						<tr>						
						<td><input type="button" value="Submit" onClick="validate(document.forgotpassword)"/></td>
						</tr>						
						</table>
					<br >
				   </div>	 
              </g:form> 
		</div>	  
        </div> <!-- End of content div -->
 <br /> <br />
</div>
	 <div id="footer">
          <div style="padding:8px 30px 20px 0px" align="right"><h4><font color="white">Developed by Amrita University under ERP, NME ICT, MHRD</font></h4></div>
</div>

</body>