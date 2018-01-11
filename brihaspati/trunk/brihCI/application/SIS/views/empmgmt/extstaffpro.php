<!--@name extstaffpro.php  
    @author Rekha Pal(rekha20july@gmail.com)
    @author Manorama Pal(palseema30@gmail.com)
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
<title>Staff Performance Detail</title>
   <head>
        <?php $this->load->view('template/header'); ?>
        <!--<?php
            /*if($this->roleid != 4){
                $this->load->view('template/menu');
            } */   
        ?>-->
       <!-- <?php if($this->roleid != 4){  ?>
    <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
     <?php }; ?>-->
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
  
  </head>
   <body>
    <table width="100%">
   <!-- <table style="padding: 8px 8px 8px 20px;">-->
     <tr>
         <?php
            echo "<td align=\"left\" width=\"33%\">";
            /*if($this->roleid != 4){
                echo anchor('staffmgmt/employeelist', 'View Employee List ', array('class' => 'top_parent'));
            }*/
            echo "</td>";
            
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Add Performance Details</b>";
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
          <?php
          };
          ?>
        <?php if  (isset($_SESSION['err_message'])){?>
             <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

        <?php
        };
        ?>
 </div>
</td></tr>
 </table>
   <div> 
    <form id="myform" action="<?php echo site_url('empmgmt/extstaffpro/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
   <?php //echo "id===temp=".$this->emp_id ?> 
    <!--echo $this->emp_id-->
 <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
   <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
        <tr><thead><th  style="background-color: gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp;A) Awards and Medals</th></thead></tr>
        <tr></tr><tr></tr>
        
        <tr>
            <td>Number of Medals at National</td>
            <td>Number of Medals at International</td>
            <td>Number of Medals at University</td>
        </tr>
        <tr>
            <td><input type="text" name="national"  class="form-control" size="30" value="<?php echo isset($_POST["national"]) ? $_POST["national"] : ''; ?>" /></td>
            <td><input type="text" name="international"  class="form-control" size="30" /></td>
            <td><input type="text" name="university"  class="form-control" size="30" /></td>
        </tr>
        <tr></tr><tr></tr>
        <tr><thead><th  style="background-color:gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp;B) Publications</th></thead></tr> 
        <tr></tr><tr></tr>    
        <tr>
            <td>Number of Research at National</b></td>
            <td>Number of Research at International</b></td>  
        </tr>
        <tr>
            <td><input type="text" name="research_national"  class="form-control" size="30" /></td>
            <td><input type="text" name="research_international"  class="form-control" size="30" /></td>
        </tr>
        <tr>
            <td>Number of Popular at National</b></td>
            <td>Number of Popular at International</b></td>
        </tr>
        <tr>
            <td><input type="text" name="popular_national"  class="form-control" size="30" /></td>
            <td><input type="text" name="popular_international"  class="form-control" size="30" /></td>
        </tr>
        <tr>
            <td>Number of Presentation at National</b></td>
            <td>Number of Presentation at International</b></td>
        </tr>
        <tr>
            <td><input type="text" name="presentation_national"  class="form-control" size="30" /></td>
            <td><input type="text" name="presentation_international"  class="form-control" size="30" /></td>
        </tr>
        <tr></tr><tr></tr>        
        <tr><thead><th  style="background-color: gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp;C) Project Handled</th></thead></tr>
        <tr></tr><tr></tr> 
        <tr>
            <td>Number of Project Handled</td>
            <td>Fund Outlay</td>
        </tr>
        <tr>
            <td><input type="text" name="noofproject"  class="form-control" size="30" /></td>
            <td><input type="text" name="fund"  class="form-control" size="30" /></td>
        </tr> 
        <tr></tr><tr></tr> 
        <tr><thead><th  style="background-color:gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp;D) Training attended (Seminar / Symposium / Workshop etc.) </th></thead></tr>
        <tr></tr><tr></tr>
        <tr>
            <td>Number of Training Attended at National</b></td>
            <td>Number of Training Attended at International</b></td>
        </tr>
        <tr>
            <td><input type="text" name="training_attend_national"  class="form-control" size="30" /></td>
            <td><input type="text" name="training_attend_international"  class="form-control" size="30" /></td>
        </tr>
        <tr></tr><tr></tr> 
       <tr><thead><th  style="background-color:gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp;E) Training Conducted (Seminar / Symposium / Workshop etc.) </th></thead></tr>         
        <tr></tr><tr></tr> 
        <tr>
            <td>Number of Trainings Conducted at National</b></td>
            <td>Number of Trainings Conducted at International</b></td>
        </tr>
        <tr>
            <td><input type="text" name="training_conducted_national"  class="form-control" size="30" /></td>
            <td><input type="text" name="training_conducted_international"  class="form-control" size="30" /></td>
        </tr>
        <tr></tr><tr></tr> 
        <tr><thead><th  style="background-color:gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp;F) Students Guided</th></thead></tr>
        <tr></tr><tr></tr> 
        <tr>
            <td>Number of Students at MVSc</td>
            <td>Number of Students in PhD</td>
            <td>Number of Other Students</td>
        </tr>
        <tr>
            <td><input type="text" name="mvsc"  class="form-control" size="30" /></td>
            <td><input type="text" name="phd"  class="form-control" size="30" /></td>
            <td><input type="text" name="others"  class="form-control" size="30" /></td>
        </tr>
        <tr></tr><tr></tr> 
        <tr><thead><th  style="background-color:gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp;G) Guest Lecture Delivered</th></thead></tr>
        <tr></tr><tr></tr>    
        <tr>
            <td>Number of Guest lecture delivered</b></td>
        </tr>
        <tr>
            <td><input type="text" name="guestlect"  class="form-control" size="30" /></td>
        </tr>
        <tr></tr><tr></tr> 
	<tr><thead><th  style="background-color:gray; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp;H) Attachment Performance File</th></thead></tr>
        <tr></tr><tr></tr>     
        <tr>
            <td><label for="spd_per_filename">Upload Attachment:</label></td>
            <td><input type='file' name='userfile' size='20' style="font-size:15px;"/>
            </td>
        </tr>
        <tr></tr><tr></tr> 
        <tr style="background-color:gray; text-align:left; height:30px;">
            <td colspan="3">
                <button name="extstaffpro" >Submit</button>
                <button name="clear" >Clear</button>
            </td>
        </tr>
    </table>
    </form>
     <p> &nbsp; </p>
    </div>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>


