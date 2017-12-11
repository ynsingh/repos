<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name fees.php 
  @author Vijay (vijay.pal428@gmail.com)
  @author Manorama pal (palseema30@gmail.com) // add program branch in dropdown
 -->


<html>
<title>Add Fees</title>
<head>
<title>Fees</title>
  <div id="body">
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu'); ?>
</div>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
 <table width= "100%"> 
            <tr><td>
                <div>
                <?php echo anchor('setup/displayfees',' Program Fees with Head List',array('title'=>'View Detail','class' => 'top_parent'  )); 
                echo "<td align=\"right\">";
                echo "</td>";
                echo "<td>";
		$help_uri = site_url()."/help/helpdoc#ProgramFees";
		echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;\">Click for Help</b></a>";
                echo "</td>";
		?>
     </div>
                <div>
		<?php 
		     echo validation_errors('<div class="isa_warning">','</div>');
                     echo form_error('<div  class="isa_error">','</div>');

                     if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                 <?php
                     };
		     if(isset($_SESSION['err_message'])){?>
                    	<div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
 
                 <?php
                    };
                 ?>
                </div>
            </td></tr>
</table>
 <div>
     <form action="<?php echo site_url('setup/fees');?>" method="POST" class="form-inline">    
                <table>
                	<tr><td> Program Name: </td><td>
			<select name="program" style="width:100%;">
                        <option value=""disabled selected>---------Select program ---------</option>
                        <?php foreach($this->prgresult as $datas): ?>
                        <option value="<?php echo $datas->prg_name; ?>"><?php echo $datas->prg_name; ?></option>
                        <!-- <option value="<?php echo $datas->prg_id; ?>"><?php echo $datas->prg_name."(".$this->common_model->get_listspfic1('program','prg_branch','prg_id',$datas->prg_id)->prg_branch.")"; ?></option> -->
                        <?php endforeach; ?>
                        </select>
                        </td></tr>
			<?php
			echo "<td>";
	                echo form_label('Academic Year', 'acadyear');
        	        echo "</td>";
                	echo "<td>";
               		echo "<select name=\"acadyear\" class=\"my_dropdown\" style=\"width:100%;\">";
                	//echo "<option value=$acdy>$acdy</option>";
               		echo "<option value= disabled selected >------Select Academic year------</option>";
	                for($i = 2016; $i < date("Y")+10; $i++){
        	        $j=$i+1;
	                echo '<option value="'.$i.' - '.$j.'">'.$i.' - '.$j.'</option>';
                        }
        	        echo " </select>";
			?>
			<tr>
			<td>Semester :</td>
                	<td>
                	<select name="semester" id="" class="my_dropdown" style="width:100%;">
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

			<tr><td> Category: </td><td>
                        <select name="category" style="width:100%;">
                        <option value=""disabled selected>----Select Category----</option>
			<?php foreach($this->catresult as $datas): ?>
                        <option value="<?php echo $datas->cat_id;?>"><?php echo $datas->cat_name; ?></option>
			<?php endforeach; ?>
                        </select>
                        </td></tr>
			<tr>
                        <td>Gender :</td>
                        <td>
                        <select name="gender" id="" class="my_dropdown" style="width:100%;">
                        <option value="" disabled selected >------Select Gender------</option>
                        <option value="All" class="dropdown-item">All</option>
                        <option value="Male" class="dropdown-item">Male</option>
                        <option value="Female" class="dropdown-item">Female</option>
                        <option value="Transgender" class="dropdown-item">Transgender</option>
                        </select>
                        </td></tr>

			<tr>
                        <td><label>Head:</label></td>
			<td><input type="text"placeholder="Head" name="head"  size="30" /></td>
                        <?php echo form_error('fm_head')?></td>
			</tr>
			<tr>
                        <td><label>Amount:</label></td>
			<td><input type="text"placeholder="Amount" name="amount"  size="30" /></td>
                        <?php echo form_error('fm_amount')?></td>
                        </tr>
                        <tr>
                        <td><label>Description:</label></td>
			<td><input type="text"placeholder="Description" name="description"  size="30" /></td>
                        <?php echo form_error('fm_description')?></td>
                        </tr>
                        <!--!tr>
                        <td><label>From Date:</label></td>
			<td><input type="text"placeholder="From Date" name="frmdate"  size="27" /></td>
                                </td>
			</tr-->

			<!--tr>
                        <td><label>To Date:</label></td>
			<td><input type="text"placeholder="To Date" name="todate"  size="27" /></td>
                        </tr-->

			
                        <tr>
                        <td></td>
                        <td colspan="2">
                        <button name="fees">Add Fees </button>
			<button name="clear">Clear</button>
                        </td>
                        </tr>
                    </form>
                  </div>
        </table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</head>
</html>

