
<!--@name edit_servicedata.php  @author Manorama Pal(palseema30@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Service Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
                $('#DateofAGP,#Datefrom,#Dateto').datepicker({
                    dateFormat: 'yy/mm/dd',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+20',
               
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });

/********uoc on the basis of campus*****************************************************************************/          
            /*
            In future this code may be replace when either campusid added in the 
            autority or authority added in campus.*/
            $('#camp').on('change',function(){
                var sc_code = $(this).val();
                //alert(sc_code);
                if(sc_code == ''){
                    $('#uocid').prop('disabled',true);
                   
                }
                else{
                    $('#uocid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/empmgmt/getuoclist",
                        type: "POST",
                        data: {"campusname" : sc_code},
                        dataType:"html",
                        success:function(data){
                            //alert("data==1="+data);
                            $('#uocid').html(data.replace(/^"|"$/g, ''));
                                                 
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                    });
                }
            }); 
            /*****end of uoc***************************************************************************/
            /************************select department on basis of uoc and campus*******************/           
             $('#uocid').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocid').val();
                var combid = sc_code+","+uoc_id;
               //alert("combid=="+combid);
                if(uoc_id == ''){
                    $('#scid').prop('disabled',true);
                }
                else{
             
                    $('#scid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/empmgmt/getnewdeptlist",
                        type: "POST",
                        data: {"campuoc" : combid},
                        dataType:"html",
                        success:function(data){
                            
                            $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
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
                        
                        echo anchor('report/viewfull_profile/'.$servicedata->empsd_empid, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Edit Service Details</b>";
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
            <form id="myform" action="<?php echo site_url('empmgmt/update_servicedata/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<?php echo  $id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Service Details</th></thead></tr>
                <tr></tr><tr></tr>
                <tr>
                    <td>Campus Name <font color='Red'>*</font></td>
                        <td><select id="camp" style="width:350px;" name="campus" required> 
                            <?php if(!empty($servicedata->empsd_campuscode)):;?>       
                            <option value="<?php echo $servicedata->empsd_campuscode;?>"><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$servicedata->empsd_campuscode)->sc_name;?></option>    
                            <?php else:?>
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php endif;?>
                            <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
                            <?php endforeach; ?>
                      
                        </select>
                    </td>
                </tr>
<tr>
                    <td>University Officer Control<font color='Red'>*</font></td>
                        <td><select id="uocid" style="width:350px;" name="uocontrol" required>
                            <?php if(!empty($servicedata->empsd_ucoid)):;?>
                            <option value="<?php echo $servicedata->empsd_ucoid;?>"><?php echo $this->lgnmodel->get_listspfic1('authorities','name','id',$servicedata->empsd_ucoid)->name;?></option>
                            <?php else:?>
			<option value="">Select University Officer Control</option>
			<?php endif;?>
                        </select>
                    </td>
                </tr>
<tr>
                    <td>Department<font color='Red'>*</font></td>
                        <td><select id="scid" style="width:350px;" name="department" required>
                            <?php if(!empty($servicedata->empsd_deptid)):;?>
                            <option value="<?php echo $servicedata->empsd_deptid;?>"><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$servicedata->empsd_deptid)->dept_name;?></option>
                            <?php else:?>
			  <option value="">Select Department</option>
                        <?php endif;?>

                        </select>
                    </td>
                </tr>
 
		<tr>
		    <td>Designation<font color='Red'>*</font></td>
                        <td><select name="designation" id="desigid" required style="width:350px;"> 
                            <?php if(!empty($servicedata->empsd_desigcode)):;?> 
                            <option value="<?php echo $servicedata->empsd_desigcode;?>"><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_code',$servicedata->empsd_desigcode)->desig_name;?></option>    
                            <?php else:?>
                            <option selected="selected" disabled selected>------- Select Designation ---------</option>
                            <?php endif;?>
                            <?php foreach($this->desig as $desigdata): ?>	
                                <option value="<?php echo $desigdata->desig_code; ?>"><?php echo $desigdata->desig_name; ?></option> 
                            <?php endforeach; ?>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Pay Band<font color='Red'>*</font></td>
                    <td><select name="payband" required style="width:350px;">
                        <?php if(!empty($servicedata->empsd_pbid)):;?>
                        <option value="<?php echo $servicedata->empsd_pbid;?>">
                            <?php
                            $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$servicedata->empsd_pbid)->sgm_name;
                            $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$servicedata->empsd_pbid)->sgm_max;
                            $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$servicedata->empsd_pbid)->sgm_min;
                            $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$servicedata->empsd_pbid)->sgm_gradepay;
                            ;?>
                            <?php echo $payband."(".$pay_min."-".$pay_max.")".$gardepay;?></option>
                        <?php else:?>
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <?php endif;?>
                        <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php endforeach; ?>
                       
                    </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Grade Pay<font color='Red'></font></td>
                        <td><input type="text" name="gradepay" id="gradepay" value="<?php echo $servicedata->empsd_gradepay;?>"  size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Date of AGP<font color='Red'></font></td>
                        <td><input type="text" name="DateofAGP" id="DateofAGP" value="<?php echo $servicedata->empsd_pbdate;?>"  size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Date From<font color='Red'>*</font></td>
                        <td><input type="text" name="Datefrom" id="Datefrom" value="<?php echo $servicedata->empsd_dojoin; ?>"  size="40" required="required">
                    </td>
                </tr>
                <tr>
                    <td>Date To<font color='Red'></font></td>
                        <td><input type="text" name="Dateto" id="Dateto" value="<?php echo $servicedata->empsd_dorelev; ?>"  size="40" >
                    </td>   
                </tr>
                <tr></tr><tr></tr>
                <tr style="background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="editservdata" >Update</button>
                    <!--<button  onclick="goBack();">Back</button> -->
                    </td>
                </tr>    
        
            </table>
            </form>
        </div>    
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>
</html>    
    
