<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name dept.php 
  @author Raju kamal (kamalraju8@gmail.com)
 -->
<html>
<head>
<title>Add Department</title>
</head>
  <div id="body">
	<?php $this->load->view('template/header'); ?> 
	<h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    	<?php $this->load->view('template/menu'); ?>
</div>
<!-- <//?php 
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            echo anchor('setup/dispdepartment/',"View Department ",array('title' => 'View Detail' ,'class' =>'top_parent')) . " ";
            echo "<span style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            echo "<span style='padding: 8px 8px 8px 20px;'>";
            echo " Add Department ";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
         
	?>-->
      <table>  
	        <font size=4pt> 
  	        <div style="margin-left:20px; width:200px;">
  	        <?php echo anchor('setup/dispdepartment','Department List',array('title'=>'View Detail','class' => 'top_parent'  )); ?>
               </font>
 
	   <tr colspan="2"><td>    
                <div align="left" style="margin-left:55px;width:1700px;">
       
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
               
                </div>
            </td></tr>  
              <tr>
         <form action="<?php echo site_url('setup/dept');?>" method="POST" class="form-inline">
      		<table style="margin-left:55px;"> 

		<tr><td>
                          
        		Choose your University:</td><td>
    			<select name="orgprofile">
    			<option value=""disabled selected>--------------Select university------------</option>
			  <?php foreach($this->uresult as $datas): ?>	
   			<option value="<?php echo $datas->org_code; ?>"><?php echo $datas->org_name; ?></option> 
                        <!-- <option value="<?php echo $datas->org_name; ?>"><?php echo $datas->org_name; ?></option>--> 
 		<?php endforeach; ?>
   			</select>          
   			</td></tr><tr><td>    
 			Choose your Campus: </td><td>         
 			<select name="studycenter">
 			<option value=""disabled selected>-------------Select campus---------------</option>
                      
			  <?php foreach($this->scresult as $datas): ?>	
			<option value="<?php echo $datas->sc_code; ?>"><?php echo $datas->sc_name; ?></option>
                     <!-- <option value="<?php echo $datas->sc_name; ?>"><?php echo $datas->sc_name; ?></option>-->
			<?php endforeach; ?>
			</select>   
			</td></tr>                       
          		<tr>  
                                <td><label>School Code:</label></td>
                                <td><input type="text"placeholder="School Code" name="dept_schoolcode"  size="27" /></td> 
                                <td><?php echo form_error('dept_schoolcode')?></td>
                                 <td>Example: Sbs</td>
                            </tr>
                            <tr> 
                                <td><label>School Name:</label></td>
                                <td><input type="text"placeholder="School Name"name="dept_schoolname"  size="27"  /> </td>
                                <td><?php echo form_error('dept_schoolname')?></td> 
                               <td>Example: School of basic science  </td>
                                            
                            </tr>
                            <tr>
                                <td><label>Department Code:</label></td>
                                <td><input type="text"placeholder="Department Code"size="27" name="dept_code" /> </td>
                                <td><?php echo form_error('dept_code')?></td>
                                 <td>Example: Phy </td>          
                            </tr>
                            <tr>
                                <td><label>Department Name:</label></td>
                                <td><input type="text"placeholder="Department Name" name="dept_name"  size="27"  /></td>
                                <td><?php echo form_error('dept_name')?></td> 
                               <td>Example:Physics Department </td>                               
                
                            </tr>
                            <tr>
                                <td><label>Department Nick Name:</label></td>
                                <td><input type="text"placeholder="Department Nick Name"name="dept_short" size="27"  /> </td>
                                <td><?php echo form_error('dept_short')?></td>
                                 <td>Example:Phy </td>
                                
                            </tr>
                            <tr>
                                <td><label>Department Description:</label></td>    
                                <td><input type="text"placeholder="Dapartment Description"name="dept_descripation"size="27"/> </td>   
                                 <td><?php echo form_error('dept_descripation')?></td>
                                 <td>Example:Department of Physics  </td>           
                            </tr>
			                    
                            <tr>
                          
                          <td colspan="2" style="margin-left:30px;">     
	         
                         <button name="dept" style="margin-left:175px;">Add Department </button>
                          <button name="reset" >Clear</button>
                             <?php echo form_close(); ?>
                                </td>
                            </tr>
                        </table>
                    </form>
                  </div> 
            </tr>     
        </table>     

</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

