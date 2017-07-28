<!--@name addsemrule.php
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Semester Rule</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
     <?php $this->load->view('template/menu');?>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
<script>
	function getbranchname(branch){
                var branch = branch;
                //alert(branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>hrmindex.php/setup2/branchlist",
                data: {"programname" : branch},
                dataType:"html",
                success: function(data){
                //alert(data);
                $('#semcr_prgid').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }
</script>

 </head>    
 <body> 
<!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
		echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/displayrole/', "View Role Detail ", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>-->

     <table width="100%">
            <tr><td>
                <div align="left" style="margin-left:0.2%;">
                <?php echo anchor('setup2/semesterrules/', "View Semester Rule ", array('title' => 'View Semester Rule' ,'class' =>'top_parent'));?>
                <?php
                 $help_uri = site_url()."/help/helpdoc#";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:65%\">Click for Help</b></a>";
                 ?>
                <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
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
 
    <tr>
    <div style="margin-left:0.6%;">
    <form action="<?php echo site_url('setup2/addsemrule');?>" method="POST" class="form-inline">
		<table style="margin-left:0.6%;">
		<tr>
           	<td>Program Name :</td>
           	<td>
                <select name="programname" id="programname" class="my_dropdown" style="width:300px;" onchange="getbranchname(this.value)" >
                <option value="" disabled  selected >------Select Program Name--------------</option>
                <?php foreach($this->prgresult as $dataspt): ?>
                <option value="<?php echo $dataspt->prg_name ?>"><?php echo $dataspt->prg_name; ?></option>
                <?php endforeach; ?>
           </td>
       </tr>
        <tr>
           <td>Branch Name :</td>
           <td>
                <select name="semcr_prgid" id="semcr_prgid" class="my_dropdown" style="width:300px;">
                <option value="" disabled  selected >------Select Branch Name--------------</option>
           </td>
        </tr>

		<tr>
                        <td>Semester :</td>
                        <td>
                        <select name="semcr_semester" id="" class="my_dropdown" style="width:100%;">
                        <option value="" disabled selected >------Select Semester------</option>
                        <option value="1" class="dropdown-item">1</option>
                        <option value="2" class="dropdown-item">2</option>
                        <option value="3" class="dropdown-item">3</option>
                        <option value="4" class="dropdown-item">4</option>
                        <option value="5" class="dropdown-item">5</option>
                        <option value="6" class="dropdown-item">6</option>
                        <option value="7" class="dropdown-item">7</option>
                        <option value="8" class="dropdown-item">8</option>
                        </select>
                        </td></tr>
	    <tr>

                <td><label for="semcr_mincredit" class="control-label">Semester Minimum Credit:</label></td>
                <td>
                <input type="text" name="semcr_mincredit"  class="form-control" size="33" /><br>
                </td>
                <td>
                    <?php //echo form_error('semcr_mincredit')?>
                </td>
                <td>
                   Example:any positive number 
                </td>
	    </tr>
		<tr>
                <td><label for="semcr_maxcredit" class="control-label">Semester Maximum Credit:</label></td>
                <td>
                <input type="text" name="semcr_maxcredit"  class="form-control" size="33" /><br>
                </td>
                <td>
                    <?php //echo form_error('semcr_maxcredit')?>
                </td>
                <td>
                   Example:any positive number    
                </td>
            </tr>
		<tr>
                <td>
                <label for="semcr_semcpi" class="control-label">Semester CPI:</label>
                </td>
                <td>
                    <input type="text" name="semcr_semcpi" size="33"  class="form-control" /> <br>
                </td>
                <td>
                    <?php //echo form_error('semcr_semcpi ')?>
                </td>
                <td>
			Example: any desimal value
                </td>
            </tr>

            <tr>
                <td></td><td>
                <button name="addsemrule" >Add Semester Rule</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
           </table>
    </form>
    </div>
    </tr>
    </table>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

   
