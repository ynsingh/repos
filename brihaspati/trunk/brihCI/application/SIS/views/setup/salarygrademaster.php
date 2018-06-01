<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name salarygrademaster.php
  @author Rekha Devi Pal(rekha20july@gmail.com)
  @author: Om Prakash (omprakashkgp@gmail.com), Dec-2017 Modification
 -->

<html>
<title>Salary Grade Master</title>
 <head>    
	<?php $this->load->view('template/header'); ?>
	
 </head>    
   <body>

     <table width="100%"> 
       <tr><td>
       	<?php
           echo anchor('setup/displaysalarygrademaster', 'Salary Grade List', array('class' => 'top_parent'));
	  // $help_uri = site_url()."/help/helpdoc#Scheme";
	   //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:73%\">Click for Help</b></a>";
       	?>
        <div>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
        <?php
    	 };
       	?>
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
        <?php
        };
	?>  
      </div>
    </td>     
    </tr>   
    </table>  
        <form action="<?php echo site_url('setup/salarygrademaster');?>" method="POST" class="form-inline">
          <table>
            <tr>  
                <td><label for="sgmname" class="control-label">Salary Grade Name :</label></td>
                <td>
                <input type="text" name="sgmname"  size="40" value="<?php echo isset($_POST["sgmname"]) ? $_POST["sgmname"] : ''; ?>"  class="form-control"  placeholder="Salary Grade Name.."/><br>
                </td>
 		<!--<td><?php //echo form_error('sgmname')?></td>--> 
            </tr>
            <tr> 
                <td>    
                <label for="sgmmax" class="control-label">Salary Grade Max :</label>
                </td>
                <td>
                    <input type="text" name="sgmmax" size="40"  value="<?php echo isset($_POST["sgmmax"]) ? $_POST["sgmmax"] : ''; ?>" class="form-control" placeholder="Salary Grade Max.."/> <br>
                </td>
 		<!--<td><?php //echo form_error('sgmmax')?></td>-->
            </tr>
            <tr>
                <td>   
                    <label for="sgmmin" class="control-label">Salary Grade Min :</label>
                </td>
                <td>
                    <input type="text" name="sgmmin" size="40"  value="<?php echo isset($_POST["sgmmin"]) ? $_POST["sgmmin"] : ''; ?>" class="form-control" placeholder="Salary Grade Min.."/> <br>
                </td>
		 <!--<td><?php //echo form_error('sgmmin')?></td>-->
            </tr>
            <tr>
                <td>   
                <label for="sgmgradepay" class="control-label">Salary Grade Pay :</label>
                </td>
                <td>
                    <input type="text" name="sgmgradepay"  size="40" value="<?php echo isset($_POST["sgmgradepay"]) ? $_POST["sgmgradepay"] : ''; ?>" placeholder="Salary Grade Pay .."/> <br>
                </td>
 		<!--<td><?php //echo form_error('sgmgradepay')?></td>-->
            </tr>
	 <tr>
                <td> Salary Level<font color='Red'>*</font></td>
                <td><select name="sgmlevel" style="width:410px;" id="sgmlevel">           
                <option selected="selected" disabled selected>------ Select Level------</option>
                        <option value="Level-1">Level-1</option>
                        <option value="Level-2">Level-2</option>
                        <option value="Level-3">Level-3</option>
                        <option value="Level-4">Level-4</option>
                        <option value="Level-5">Level-5</option>
                        <option value="Level-6">Level-6</option>
                        <option value="Level-7">Level-7</option>
                        <option value="Level-8">Level-8</option>
                        <option value="Level-9">Level-9</option>
                        <option value="Level-10">Level-10</option>
                        <option value="Level-11">Level-11</option>
                        <option value="Level-12">Level-12</option>
                        <option value="Level-13">Level-13</option>
                        <option value="Level-13A">Level-13A</option>
                        <option value="Level-14">Level-14</option>
                        <option value="Level-15">Level-15</option>
                        <option value="Level-16">Level-16</option>
                        <option value="Level-17">Level-17</option>
                        <option value="Level-18">Level-18</option>
                </select>
                </td>
                 </tr>
                <tr><td></td>
                <td colspan="2">
                <button name="salarygrademaster" >Add Salary Grade </button>
                <input type="reset" name="Reset" value="Clear"/>
                </td>
            </tr>
           </table>
        </form>
     </tr>
 <p><br></p>
  </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
