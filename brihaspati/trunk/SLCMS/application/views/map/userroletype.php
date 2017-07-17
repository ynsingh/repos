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
                   url: "<?php echo base_url();?>index.php/map/getdeptlist",
                   type: "POST",
                   data: {"campusname" : sc_code},
                   dataType:"html",
                   success:function(data){
                      $('#scid').html(data);
                       
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
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?> 
        </br>    
        <table> 
       
            <tr colspan="2"><td>  
                <div style="margin-left:30px;">    
                <?php echo anchor('map/viewuserrole/', "Map User Role List ", array('title' => 'View Detail' , 'class' => 'top_parent'));?>
                </div>
                <div align="left" style="margin-left:30px;width:1700px;">
                 <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning">','</div>');?>
                  <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div style="margin-left:30px;"  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
         
            </div>
        </td></tr>  
        </table>  
        <br/>
        <table style="margin-left:50px;">
            
           <form action="<?php echo site_url('map/userroletype');?>" method="POST">
                         <tr><td> Choose your Campus: </td><td>
                         <select name="campus" id="camp" style="width:300px;">
                         <option value="">-------------Select Campus---------------</option>
                         <?php foreach($this->scresult as $datas): ?>
 		         <option value="<?php echo $datas->sc_id; ?>"><?php echo $datas->sc_name; ?></option>
                         <?php endforeach; ?>
                         </select>
                        </td></tr><tr><td>
                        Choose your Department: </td><td>
                        <select name="dept_name" id="scid" disabled="" style="width:300px;">
                        <option value="">select department</option>
                        </select>
                        </td></tr>
                        <tr><td> Select your Role: </td><td>
                        <select name="role_name"  style="width:300px;">
                        <option value="" disabled selected>-------------Select Role---------------</option>
                        <?php foreach($this->roleresult as $datas): ?>
                        <option value="<?php echo $datas->role_id; ?>"><?php echo $datas->role_name; ?></option>
                        <?php endforeach; ?>
                        </select>
                        </td></tr>
                       <tr>
                      <td>User Type :</td>
                 <td>
                    <select name="usertype" class="my_dropdown" style="width:300px;">
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
                        <select name="username" class="my_dropdown" style="width:300px;">
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
        </table>
    </body> 
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
