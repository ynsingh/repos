<!DOCTYPE html>
<html>
    <head>
        <title>UploadDocument</title>
	<?php $this->load->view('template/header'); ?>
	 <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

        <script>
		$(document).ready(function(){
			$( "#asignother" ).hide();
			$('#profileid').on('change',function(){
		                var worktype = $(this).val();
//              		  alert(worktype);
		                if(worktype == ''){
                    			$('#grouppost').prop('disabled',true);                  
                		}
                		else {
                    			$('#subprfid').prop('disabled',false);
                    			$.ajax({                      
						url: "<?php echo base_url();?>sisindex.php/upl/getpcatlist",
			                        type: "POST",
                        			data: {"groupp" : worktype},
			                        dataType:"html",
                        			success:function(data){
			  //                          alert("data==1="+data);
                            				$('#subprfid').html(data.replace(/^"|"$/g, ''));
                        			},
                        			error:function(data){
			                            	alert("data in error==="+data);
                            				alert("error occur..!!");
                        			}
                    			});
                		}
            		}); 
			 /************************ closer Sub profile Category******************************************************************/
			 
           /************************select Category Name on basis of Sub profile Category*******************/
		        $('#subprfid').on('change',function(){
                		var grp_id = $(this).val();
				var profile_id = $('#profileid').val();
				var wtg=grp_id+","+profile_id;
//		alert(wtg);
                		if(grp_id == ''){
                    			$('#catgid').prop('disabled',true);
                		}
                		else{
             
                    			$('#catgid').prop('disabled',false);
                    			$.ajax({
                      //  url: "<?php echo base_url();?>sisindex.php/upl/getctglist",
                        			url: "<?php echo base_url();?>sisindex.php/upl/getctglist",
                        			type: "POST",
                        			data: {"groupp" : grp_id},
                       // data: {"groupp" : wtg},
                        			dataType:"html",
                        			success:function(data){
//				alert("data==1="+data);
                            				$('#catgid').html(data.replace(/^"|"$/g, ''));
                        			},
                        			error:function(data){
                            				alert("data in error part==="+data);
                            				alert("error occur..!!");
                 
                        			}
                                            
                    			});
                		}
	    		});
	
                        $('#catgid').on('change',function(){
                                var recmthd = $(this).val();
                                if(recmthd == 'Other'){
                                        $( "#asignother" ).show();
                                }
                                else{
                                        $( "#asignother" ).hide();
                                }
                        });
			
			$('#profileid').on('change',function(){
				var pnme = $(this).val();
				if(pnme == 'Technical_Qualification'){
					$( "#asignother" ).show();
					$( "#ctnm" ).hide();
				}
                                else{
                                        $( "#asignother" ).hide();
					$( "#ctnm" ).show();
                                }
                        });
	//		$('#pfno').on('change',function(){
          //                      var pfno = $(this).val();
	//			
	//		});
	

	});
</script>   
    </head>
    <body>
 

 <!--?php
                    echo "<table align=\"right\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#UploadDocumentList";
                    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b style=\"font-size:17px;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?-->
	<div>
	<table width="100%;">
            <tr>
 <?php
		    echo "<td align=\"left\" width=\"33%\" style=\"font-size:16px\">";
			echo anchor("upl/viewuploaddocument","View Uploaded Support Document ",array('title' => 'View Uploaded Support Document' , 'class' => 'red-link'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>Upload Document List</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#UploadDocument";
					
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
echo "</tr>";
	echo "</table>";
?>

<br/></br>
	</div>
	            <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php if(isset($_SESSION['success'])){
			if(!empty($_SESSION['success'])){
		?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
		}
                if(isset($_SESSION['err_message'])){
			if(!empty($_SESSION['err_message'])){
		?>
                <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
		}
               ?>
              </div>
         </td></tr>
    </table>							
	<form action="<?php echo site_url('upl/uploaddocumentlist');?>" method="POST" enctype="multipart/form-data">
	<table border=0>
		<tr align="left">
			<td><label>Profile Name :  <font color=red>*</font> </label></td>
			<td>
				<select class="my_dropdown" id="profileid" name="profilename" style="width:300px;" required> 
					<option selected="selected" disabled selected>--Select profile name--</option>
					<option value="Basic_Profile">Basic Profile</option>
					<option value="Academic_Qualification">Academic Qualification</option>
					<option value="Technical_Qualification">Technical Qualification</option>
<!--					<option value="Promotional_Details">Promotional Details</option> -->
				</select>
			</td>
		</tr>
		<tr align="left">
			<td ><label>Sub Profile category:   </label></td>
			<td>
				<select class="my_dropdown" id="subprfid" name="subprofile" style="width:300px;"> 
					<option selected="selected" disabled selected>--Select subprofile category--</option>
					
				</select>
			</td>
		</tr>
		<tr align="left">
			<td>
			<label>Category Name :   </label></td>
			<td id="ctnm">
				<select class="my_dropdown" id="catgid" name="categoryname" style="width:300px;"> 
					<option selected="selected" disabled selected>--Select category name--</option>
									</select>
			</td>
			<td><input type="text" name="asignother" id="asignother" class="keyup-characters" value="<?php echo isset($_POST["asignother"]) ? $_POST["asignother"] : ''; ?>" placeholder="Write name here." size="35" >
                </td>
		</tr>
		<tr align="left">
                <td><label for="role_name" class="control-label">PF No:<font color=red>*</font></label></td>
                <td>
                <input type="text" name="pfno" id="pfno" class="form-control" size="35" required/><br>
                </td>
            </tr>
		<tr align="left">
			<td>
			<label>Select File:  <font color=red>*</font> </label></td>
			<td>
			<input type='file'  id="fileUpload" name='userfile' size='20' accept=".pdf, .png" required />
			</td>
		</tr>
	</table>
	 <table   style="font-size:15px;" >
            <tr><td><b>Note : The file extension should be in png or pdf and file size 8 MB. </b>
            </td>
            </tr>

  </table>
	<table>	
			<tr><td><table style="font-size:30px;"></td></tr>
            <tr>
			
                <td align="left"></td><td>
                <button name="adddocumentlist" >Submit</button>
				 <!--input type="reset" name="Reset" value="Clear"/-->
                <button name="reset" >Clear</button>
                </td>
           </tr>
		 </table>
           </table>
		  	

	</form>
	
 <p><br></p>
    </div>
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>
