<!--@name edituserrole.php  @author kishore kr Shukla(kishore.shukla@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
	<title>Edit User Role</title>
	<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
        <?php $this->load->view('template/header'); ?>
            <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php //$this->load->view('template/menu');?>
    </head>
    <body>
	<script>
        function goBack() {
        window.history.back();
        }
        </script>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
        <table width="100%"> 
            <tr>
         <?php
	 echo "<td align=\"center\" width=\"100%\">";
         echo "<b>Update User Role List Details</b>";
         echo "</td>";  
	?> 
	</tr>
</table> 
		 <table width="100%">
            	    <tr><td>
                       <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div  class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                       <div  class="isa_success"><?php echo $_SESSION['success'];?></div>
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
    <!--<table style="margin-left:50px;">
    <tr><td align="left"> Edit Campus Program seat</td></tr> 
    </table><br/>-->
     <table>
      <form action="<?php echo site_url('map/edituserrole/' .$id );?>" method="POST" class="form-inline">
           <tr>
           <td>Institute Name</td>
                <td>
                   <?php echo form_input($scid); ?>
                                          
               </td>
            </tr>
            <tr>
                <td>User Name</td>
                <td>
                 <?php echo form_input($username); ?>
                </td>
            </tr>
            <tr>
                <td>Department</td>
                <td>
                  <?php echo form_input($deptid); ?>
                </td>
                <td><?php echo form_error('deptid')?></td>
            </tr>
             <tr>
                <td>Role Name</td>
                 <td><select name="roleid" class="my_dropdown" style="width:100%;">
                  <option value="<?php echo $this->commodel->get_listspfic1('role','role_id', 'role_name',$roleid['value'])->role_id;?>" style="display:none"><?php echo $roleid['value'];?></option>
		<?php foreach($this->roleresult as $datas): ?>
                   <option value="<?php echo $datas->role_id; ?>"><?php echo $datas->role_name; ?></option>
                   <?php endforeach; ?>
                </td>
                <td><?php echo form_error('roleid')?></td>
               </td>
            </tr>
               <tr>
                <td>Usertype</td>
                <td>
                  <select name="usertype" style="width:100%;">
                   <option value="<?php echo $usertype['value'];?>" style="display:none"><?php echo $usertype['value'];?></option>
                    <!--<option value="" disabled selected>------Select User Type------</option>-->
                    <option value="Administartor" >Administartor</option>
                    <option value="Faculty">Faculty</option>
                    <option value="Staff" >Staff</option>
                    <option value="Student">Student</option>
                    <option value="Multy Tasking" >Multy Tasking</option>
                    </select> 
              </td> 
              </tr>
               <td></td>
                <td colspan="2">   
                <button name="edituserrole" >Update</button>
            <?php echo form_hidden('id', $id);?>
        </form>    
		 <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
                </td>
        </table>
    </body>
    <div align="center"><?php $this->load->view('template/footer');?></div>
</html>
