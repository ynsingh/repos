<!--@name editsubjectteacher.php
    @author Om Prakash(omprakashkgp@gmail.com)
 -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
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
       <table  width="100%">
          <tr>
	 <?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Subject and Paper with Teacher Details</b>";
                    echo "</td>";
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
    <table>
        <form action="<?php echo site_url('map/editsubjectteacher/' . $pstp_id);?>" method="POST" class="form-inline">
            <tr>
                <td> Institute Name </td>
                <td>
                    <?php echo form_input($campusname); ?>
                </td>
            </tr>
            <tr>
                <td> Department Name </td>
                <td>
                    <?php echo form_input($deptname); ?>

                </td>
            </tr>
            <tr>
                <td>Academic Year :</td>
                <td>
		<?php echo form_input($academicyear); ?>
                </td>
            </tr>
            <tr>
                <td> Program Name </td>
                <td>
                 <?php echo form_input($programname); ?>
                </td>
            </tr>
            <tr>
                <td> Branch Name </td>
                <td>
                 <?php echo form_input($branchname); ?>
                </td>
            </tr>
            <tr>
                <td>Semester :</td>
                <td>
                 <?php echo form_input($semester); ?>
                </td>
            </tr>
            <tr>
                <td>Subject Name</td>
                <td>
                 <?php echo form_input($subjectname); ?>
                </td>
            </tr>
            <tr>
                <td>Paper Name</td>
                <td>
                 <?php echo form_input($papername); ?>
                </td>
            </tr>
            <tr>
                <td>Teacher Name</td>
                <td> 
                    <select name="teachername" id="" class="my_dropdown" style="width:100%">
                    <option value="<?php echo $teachername['value'];?>" style="display:none"><?php echo $teachername['value'];?></option>
                    <?php foreach($this->tresult as $dataspt): ?>
                        <?php if ((($dataspt->roleid)==2)&&(($this->commodel->get_listspfic1('user_role_type', 'deptid', 'userid', $dataspt->userid)->deptid)== $this->commodel->get_listspfic1('Department', 'dept_id', 'dept_name', ($deptname['value']))->dept_id)) { ?>
                        <option value="<?php echo $this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $dataspt->userid)->firstname ?>"><?php echo $this->loginmodel->get_listspfic1('userprofile', 'firstname', 'userid', $dataspt->userid)->firstname; ?>  <?php echo $this->loginmodel->get_listspfic1('userprofile', 'lastname', 'userid', $dataspt->userid)->lastname; ?> </option>
                        <?php } ?>
                        <?php endforeach; ?>
                </td>
            </tr>
	  <tr><td></td>
	   <td>
		<button name "submit" >Update</button>
		<?php echo form_hidden( 'pstp_id', $pstp_id );?>
	</form>
		<?php echo "<button onclick=\"goBack()\" >Back</button>";?>
	   </td>	
	  </tr>
	
     </table>
   </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
