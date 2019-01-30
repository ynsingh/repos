 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Promotional Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){

                $('#DateofAGP,#DateofAL,#Datefrom,#Datesp,#Datesg').datepicker({
                    dateFormat: 'yy-mm-dd',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+20',
               
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });


		$("#slevel").hide();
                $("#dlevel").hide();
                $("#sgpay").hide();
         	$("#dagp").hide();
        
                 $('#paycomm').on('change',function(){
                        var pc= $('#paycomm').val();
                        if(pc == '6th'){
                                $("#sgpay").show();
                                $("#dagp").show();
                                $("#slevel").hide();
                                $("#dlevel").hide();
                        }
                        else{
                                $("#dlevel").show();
                                $("#slevel").show();
                                $("#dagp").hide();
                                $("#sgpay").hide();
                        }
                  });


         	$("#pcom").hide();
         	$("#dojp").hide();
         	$("#dosp").hide();
         	$("#dosg").hide();
 //        	$("#le").hide();

		$('#worktypeid').on('change',function(){
                        var wtid= $('#worktypeid').val();
                        if(wtid == "Teaching"){
		         	$("#pcom").show();
         			$("#dojp").hide();
		         	$("#dosp").hide();
         			$("#dosg").hide();
//		         	$("#le").hide();
                        }
                        else{
         			$("#pcom").hide();
                                $("#dagp").hide();
                                $("#sgpay").hide();
                                $("#slevel").hide();
                                $("#dlevel").hide();
		         	$("#dojp").show();
         			$("#dosp").show();
		         	$("#dosg").show();
  //       			$("#le").show();
                        }
                  });
           

            /************************select Designation on basis of Group*******************/
            $('#grpid').on('change',function(){
                var grp_id = $(this).val();
		var wrktype_id = $('#worktypeid').val();
		var wtg=grp_id+","+wrktype_id;
//		alert(wtg);
                if(grp_id == ''){
                    $('#desigid').prop('disabled',true);
                }
                else{
             
                    $('#desigid').prop('disabled',false);
                    $.ajax({
                      //  url: "<?php echo base_url();?>sisindex.php/staffmgmt/getdesiglist",
                        url: "<?php echo base_url();?>sisindex.php/jslist/getgwdesiglist",
                        type: "POST",
                       // data: {"group" : grp_id},
                        data: {"gwt" : wtg},
                        dataType:"html",
                        success:function(data){
//				alert("data==1="+data);
                            $('#desigid').html(data.replace(/^"|"$/g, ''));
                        },
                        error:function(data){
                            //alert("data in error part==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
	    });


	});
</script> 
    </head>
    <body>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    if($this->roleid == 4){
                        echo anchor('empmgmt/viewempprofile', 'View Profile ', array('class' => 'top_parent'));
                    }
                    else{
                        echo anchor('report/promotional_profile/'.$promotdata->spd_empid, 'View Promotional Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Promotional Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php  }; ?>
                <?php if  (isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php }; ?>
            </div>
            </td></tr>
        </table>
        <div>							    
<?php //		print_r($promotdata); ?>
            <form id="myform" action="<?php echo site_url('empmgmt/update_promotdata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="pid" value="<?php echo  $id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Edit Promotional Details</th></thead></tr>
                <tr></tr><tr></tr>
		<tr>
			<td>Working Type<font color='Red'>*</font></td>
                        <td colspan=2><select id="worktypeid" name="workingtype" required style="width:350px;">
<?php			if(!empty($promotdata->spd_wtype)) { ?>
			<option value="<?php echo $promotdata->spd_wtype;?>"><?php echo $promotdata->spd_wtype ; ?></option>
<?php 			}else{ ?>
                        <option selected="selected" disabled selected>------------- Working Type -------------</option>
<?php 			} ?>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                    </select>
                </td>

	<tr>
                <td>Group<font color='Red'>*</font></td>
                <td colspan=2><select name="group" style="width:350px;" id="grpid" required>
<?php                   if(!empty($promotdata->spd_group)) { ?>
                        <option value="<?php echo $promotdata->spd_group;?>"><?php echo $promotdata->spd_group ; ?></option>
<?php                   }else{ ?>
		<option selected="selected" disabled selected>------------ Select Group ---------</option>
<?php 			} ?>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                </select>
                </td>
        </tr>
        <tr>
                    <td>Designation<font color='Red'>*</font></td>
                        <td colspan=2><select name="designation" id="desigid"  style="width:350px;" required> 
<?php                   if(!empty($promotdata->spd_designation)) { ?>
                        <option value="<?php echo $promotdata->spd_designation;?>"><?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $promotdata->spd_designation)->desig_name ; ?></option>
<?php                   }else{ ?>
                            <option selected="selected" disabled selected>------- Select Designation ---------</option>
<?php 			} ?>
                        </select>
                    </td>
        </tr>
<?php		if($promotdata->spd_wtype == "Teaching"){ ?>
                <tr>
<!--	<tr id=pcom> -->
                        <td><label for="paycomm" class="control-label">Pay Commission:</label></td>
                        <td>
                            <select name="paycomm" id="paycomm" style="width:350px;">
<?php                   if(!empty($promotdata->spd_paycom)) { ?>
                        <option value="<?php echo $promotdata->spd_paycom;?>"><?php echo $promotdata->spd_paycom ; ?></option>
<?php                   }else{ ?>
                                <option value="" disabled selected >------Select ---------------</option>
<?php 			} ?>
                                <option value="6th">6th</option>
                                <option value="7th">7th</option>
                            </select>
                        </td>
        </tr>
                <tr>

<!--	 <tr id=slevel> -->
                <td>Academic Level<font color='Red'></font></td>
                <td colspan=2><select name="tlevel" style="width:350px;" id="lvel" >
<?php                   if(!empty($promotdata->spd_level)) { ?>
                        <option value="<?php echo $promotdata->spd_level;?>"><?php echo $promotdata->spd_level; ?></option>
<?php                   }else{ ?>
                <option selected="selected" disabled selected>------------ Select Level---------</option>
<?php 			} ?>
                        <option value="Level-10">Level-10</option>
                        <option value="Level-11">Level-11</option>
                        <option value="Level-12">Level-12</option>
			<option value="Level-13A">Level-13A</option>
                        <option value="Level-14">Level-14</option>
                        <option value="Level-15">Level-15</option>
                </select>
                </td>
        </tr>		
                <tr>
    <!--            <tr id="dlevel"> -->
                    <td>Date of Academic Level<font color='Red'></font></td>
                        <td colspan=2><input type="text" name="DateofAL" id="DateofAL" value="<?php echo $promotdata->spd_leveldate; ?>"  size="40" >
                    </td>
                </tr>
                <tr>
<!--	<tr id=sgpay>-->
	    <td>Academic Grade Pay<font color='Red'></font></td>
                    <td colspan=2><select name="payband" id="payband" style="width:350px;" > 
<?php                   if(!empty($promotdata->spd_agp)) { ?>
                        <option value="<?php echo $promotdata->spd_agp;?>"><?php echo $promotdata->spd_agp ; ?></option>
<?php                   }else{ ?>
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
<?php 			} ?>
                        <option value="AGP-6000">AGP-6000</option>
                        <option value="AGP-7000">AGP-7000</option>
                        <option value="AGP-8000">AGP-8000</option>
                        <option value="AGP-9000">AGP-9000</option>
                        <option value="AGP-10000">AGP-10000</option>
                        <option value="HGP">HGP</option>
                        <?php //foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php //echo $salgrddata->sgm_id; ?>"><?php //echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php //endforeach;?>
                       
                    </select>
                    </td>
                </tr>
                <tr>
               
              <!--  <tr id="dagp">-->
                    <td>Date of AGP<font color='Red'></font></td>
                        <td colspan=2><input type="text" name="DateofAGP" id="DateofAGP" value="<?php echo $promotdata->spd_agpdate; ?>"  size="40" >
                    </td>
                </tr>
<?php }else{ ?>
   <!--             <tr>
                <td>Level of Entry<font color='Red'></font></td>
                <td colspan=2><select name="ntlevel" style="width:350px;" id="lvel" >
<?php           //        if(!empty($promotdata->spd_level)) { ?>
                        <option value="<?php echo $promotdata->spd_level;?>"><?php echo $promotdata->spd_level ; ?></option>
<?php             //      }else{ ?>
                <option selected="selected" disabled selected>------------ Select Level---------</option>
<?php 		//	} ?>
                        <option value="Level-1">Level-1</option>
                        <option value="Level-2">Level-2</option>
                        <option value="Level-3">Level-3</option>
                        <option value="Level-4">Level-4</option>
                        <option value="Level-5">Level-5</option>
                </select>
                </td>
        </tr>
-->
                <tr>
<!--                <tr id=dojp> -->
                    <td>Date of Joing in the Post <font color='Red'></font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo $promotdata->spd_dojinpost; ?>"  size="40" >
                        </td>
                </tr>
                <tr>
               <!-- <tr id=dosg> -->
                    <td>Date of Selection Grade<font color='Red'></font></td>
                        <td><input type="text" name="Datesg" id="Datesg" value="<?php echo $promotdata->spd_selgradedate; ?>"  size="40" >
                        </td>
                </tr>
               <!-- <tr id=dosp> -->
                <tr>
                    <td>Date of Special Grade<font color='Red'></font></td>
                        <td><input type="text" name="Datesp" id="Datesp" value="<?php echo $promotdata->spd_specialgrddate; ?>"  size="40" >
                        </td>
                </tr>
<?php } ?>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                	    <button name="editpromotdata" >Submit</button>
		    <!--input type="reset" name="Reset" value="Clear"/-->
			<button type="button" onclick="history.back();">Back</button>
                    </td>
                </tr>    
            </table>
            </form>
        </div>    
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>
</html>    
   
