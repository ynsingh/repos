<!--@name userroletype.php  @author kishore kr shukla(kishore.shukla@gmail.com)  -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
         <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
<script>
        $(document).ready(function(){
        
       $('#camp').on('change',function(){
           var sc_code = $(this).val();
           if(sc_code == ''){
               $('#scid').prop('disabled',true);
          
           }
           else{
             
               $('#scid').prop('disabled',false);
               $.ajax({
                   url: "<?php echo base_url();?>slcmsindex.php/map/getdeptlist",
                   type: "POST",
                   data: {"campusname" : sc_code},
                   dataType:"html",
                   success:function(data){
                      $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                   },
                   error:function(data){
                       alert("error occur..!!");
                 
                   }
               });
           }
       }); 
    });
</script>  
    </head>    
    <body>
       <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?> 
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
        <table width="100%"> 
            <tr><td>  
                <div>    
                <?php echo anchor('map/viewuserrole/', "Map User Role List ", array('title' => 'View Detail' , 'class' => 'top_parent'));
                echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#EmailSetting";
                 echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
            	?>
                </div>
                <div>
                 <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                  <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
            </div>
        </td></tr>  
        </table>         
 <div>
        <table>
           <form action="<?php echo site_url('map/userroletype');?>" method="POST">
                         <tr><td> Choose your Campus: </td><td>
                         <select name="campus" id="camp" style="width:150%;">
                         <option value="">-------------Select Campus---------------</option>
                         <?php foreach($this->scresult as $datas): ?>
 		         <option value="<?php echo $datas->sc_id; ?>"><?php echo $datas->sc_name; ?></option>
                         <?php endforeach; ?>
                         </select>
                        </td></tr><tr><td>
                        Choose your Department: </td><td>
                        <select name="dept_name" id="scid" disabled="" style="width:150%;">
                        <option value="">select department</option>
                        </select>
                        </td></tr>
                        <tr><td> Select your Role: </td><td>
                        <select name="role_name"  style="width:150%;">
                        <option value="" disabled selected>-------------Select Role---------------</option>
                        <?php foreach($this->roleresult as $datas): ?>
                        <option value="<?php echo $datas->role_id; ?>"><?php echo $datas->role_name; ?></option>
                        <?php endforeach; ?>
                        </select>
                        </td></tr>
                       <tr>
                      <td>User Type :</td>
                 <td>
                    <select name="usertype" class="my_dropdown" style="width:150%;">
                    <option value="" disabled selected>------Select User Type------</option>  
                    <option value="Administrator" class="dropdown-item">Administrator</option>
                    <option value="Faculty" class="dropdown-item">Faculty</option>
                    <option value="Staff" class="dropdown-item">Staff</option>
                    <option value="Student" class="dropdown-item">Student</option>
                    <option value="Multi Tasking Staff" class="dropdown-item" >Multi Tasking Staff</option>
                    </select>
                </td> 
            </tr>
             <tr><td> Select Username: </td><td>
                        <select name="username" class="my_dropdown" style="width:150%;">
                        <option value="" disabled selected>-------------Select Username---------------</option>
                          <?php foreach($this->loginuser as $datas): ?>
                        <option value="<?php echo $datas->id; ?>"><?php echo $datas->username; ?></option>
                        <?php endforeach; ?>
                        </select>
                     </td></tr>
             <tr>
                <td></td>
                <td colspan="2">   
                <button name="userroletype">Submit</button>
                <button name="reset">Clear</button>
                </td>
             </tr>
        </form>    
        </table></div>
    </body> 
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
