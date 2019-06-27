<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name dept.php 
  @author Raju kamal (kamalraju8@gmail.com)
  kumar.abhay.4187@gmail.com =>repopluate
 @Modification : Om Prakash(omprakashkgp@gmail.com) Dec-2017
  -->
<html>
<head>
<title>Add Department</title>
</head>
	<?php $this->load->view('template/header'); ?> 
	
<body>

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

      	<table width="100%">
		<tr><td>  
  			<?php echo anchor('setup/dispdepartment','Department List',array('title'=>'View Detail','class' => 'top_parent'  )); 
		        echo "<td align=\"right\">";
		           $help_uri = site_url()."/help/helpdoc#Department";
           			echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
            		echo "</td>";
		?>
                <div align="left" style="margin-left:0%;width:90%;">
       
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
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
              	<tr>
         	<form action="<?php echo site_url('setup/dept');?>" method="POST" class="form-inline">
      		<table> 
		<tr><td>
                          
        		<label>University Name:</label></td><td>
    			<select name="orgprofile" style="width:100%;">
    			<option value=""disabled selected>--------------------Select university------------------</option>
			<?php foreach($this->uresult as $datas): ?>	
   				<option value="<?php echo $datas->org_code; ?>"><?php echo $datas->org_name; ?></option> 
 			<?php endforeach; ?>
   			</select>          
   			</td></tr><tr><td>    
 			<label>Campus Name:</label> </td><td>         
 			<select name="studycenter" style="width:100%;">
 			<option value=""disabled selected>----------------------Select campus-------------------</option>
                      
 			<?php foreach($this->scresult as $datas): ?>	
				<option value="<?php echo $datas->sc_code; ?>"><?php echo $datas->sc_name; ?></option>
			<?php endforeach; ?>
			</select>   
			</td></tr>
                         
                         <tr><td>
                        <label>Authorities Name:</label> </td><td>
                        <select name="authorities" style="width:100%;">
                        <option value=""disabled selected>-------------------Select authorities-----------------</option>
			                  <?php foreach($this->authresult as $datas): ?>
                                <option value="<?php echo $datas->id; ?>"><?php echo $datas-> name; ?></option>
                        <?php endforeach; ?>
                        </select>
                        </td></tr>


          		<tr>  
                                <td><label>School/Faculty Code:</label></td>
                                <td><input type="text"placeholder="School Code" name="dept_schoolcode"  size="43" value="<?php echo isset($_POST["dept_schoolcode"]) ? $_POST["dept_schoolcode"] : ''; ?>" /></td> 
                                 <td>Example: Sbs</td>
                            </tr>
                            <tr> 
                                <td><label>School/Faculty Name:</label></td>
                                <td><input type="text"placeholder="School Name"name="dept_schoolname"  size="43" value="<?php echo isset($_POST["dept_schoolname"]) ? $_POST["dept_schoolname"] : ''; ?>" /> </td>
                               <td>Example: School of basic science  </td>
                                            
                            </tr>
                            <tr>
                                <td><label>Department Code:</label></td>
                                <td><input type="text"placeholder="Department Code" size="43" name="dept_code" value="<?php echo isset($_POST["dept_code"]) ? $_POST["dept_code"] : ''; ?>" /> </td>
                                 <td>Example: Phy </td>          
                            </tr>
                            <tr>
                                <td><label>Department Name:</label></td>
                                <td><input type="text"placeholder="Department Name" name="dept_name"  size="43" value="<?php echo isset($_POST["dept_name"]) ? $_POST["dept_name"] : ''; ?>" /></td>
                               <td>Example:Physics Department </td>                               
                            </tr>
                            <tr>
                                <td><label>Department Email:</label></td>
                                <td><input type="text" placeholder="Department Mail" name="dept_mail"  size="43" value="<?php echo isset($_POST["dept_mail"]) ? $_POST["dept_mail"] : ''; ?>" /></td>
                               <td>Example:ee@iitk.ac.in </td>                               
                            </tr>
                            <tr>
                                <td><label>Department Nick Name:</label></td>
                                <td><input type="text"placeholder="Department Nick Name"name="dept_short" size="43" value="<?php echo isset($_POST["dept_short"]) ? $_POST["dept_short"] : ''; ?>"/> </td>
                                 <td>Example:Phy </td>
                                
                            </tr>
                            <tr>
                                <td><label>Department Description:</label></td>    
                                <td><input type="text"placeholder="Dapartment Description"name="dept_descripation"size="43" value="<?php echo isset($_POST["dept_descripation"]) ? $_POST["dept_descripation"] : ''; ?>" /> </td>   
                                 <td>Example:Department of Physics  </td>           
                            </tr>
			                    
                            <tr>
                          <td></td>
                          <td colspan="2">     
                         <button name="dept" >Add Department </button>
                          <!--<button name="reset" >Clear</button>-->
                             <?php echo form_close(); ?>
			 <input type="reset" name="Reset" value="Clear"/>
                                </td>
                            </tr>
                    </form>
            </tr>     
        </table> 
 <p><br></p>    
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

