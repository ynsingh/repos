<?php defined ('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name addsocieties.php 
  @author Vijay
 -->
<html>
<title>Add Societies</title>
<head>
<title>Societies</title>       
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

<div id="body">
        <?php $this->load->view('template/header'); ?>
         
</div>
<table width="100%">
                <tr colspan="2"><td>
                <?php echo anchor('map/societies',' Map User with Societies List',array('title'=>'View Detail','class' => 'top_parent'  ));
                //$help_uri = site_url()."/help/helpdoc#ProgramFees";
                //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:72%\">Click for Help</b></a>";
                ?>
                <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
 	
                </div>
            </td></tr>
</table>
     <form action="<?php echo site_url('map/addsocieties');?>" method="POST" class="form-inline">
                <table>

<!--<tr>
                <td><label for="soc_id" class="control-label">Society Id: <font color='Red'>*</font></label></td>
                <td>
                <input type="text" name="soc_id"  class="form-control" size="30" value="<?php echo isset($_POST["soc_id"]) ? $_POST["soc_id"] : ''; ?>" /><br>
                </td>-->


                        <td> Society Name: </td><td>
                        <select name="society_name" class="my_dropdown" style="width:100%;">
                        <option value=""disabled selected>---------Select societies ---------</option>
                        <?php foreach($this->socresult as $datas): ?>
                        <option value="<?php echo $datas->soc_id;?>"><?php echo $datas->soc_name; ?></option>
                        <?php endforeach; ?>
                        </select>
                        </td></tr>
                                                

  		<tr>
                <td><label for="society_head" class="control-label">Society Head: <font color='Red'>*</font></label></td>
                <td>
                <input type="text" name="society_head"  class="form-control" size="30" value="<?php echo isset($_POST["society_head"]) ? $_POST["society_head"] : ''; ?>" /><br>
                </td>
		</tr>

		<tr>
                <td><label for="society_secretary" class="control-label">Society Secretary: <font color='Red'>*</font></label></td>
                <td>
                <input type="text" name="society_secretary"  class="form-control" size="30" value="<?php echo isset($_POST["society_secretary"]) ? $_POST["society_secretary"] : ''; ?>" /><br>
                </td>
		</tr>


		<tr>
                <td><label for="society_treasurer" class="control-label">Society Treasurer :</label></td>
                <td>
                <input type="text" name="society_treasurer"  class="form-control" size="30" value="<?php echo isset($_POST["society_treasurer"]) ? $_POST["society_treasurer"] : ''; ?>" /><br>
                </td>
                </tr>


		<tr>
                <td><label for="society_members" class="control-label">Society Members: </label></td>
                <td>
                <input type="text" name="society_members"  class="form-control" size="30" value="<?php echo isset($_POST["society_members"]) ? $_POST["society_members"] : ''; ?>" /><br>
                </td>
		</tr>


		<tr>
                <td><label for="society_totalvalues" class="control-label">Society Totalvalues: </label></td>
                <td>
                <input type="text" name="society_totalvalues"  class="form-control" size="30" value="<?php echo isset($_POST["society_totalvalues"]) ? $_POST["society_totalvalues"] : ''; ?>" /><br>
                </td>
		</tr>

                        <tr>
                        <td></td>
                        <td colspan="2">
                        <button name="addsocieties">Add Societies </button>
                        <input type="reset" name="Reset" value="Clear"/>
                        </td></tr>
                    </form>
                  </div>
            </tr>
        </table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</head>
</html>




