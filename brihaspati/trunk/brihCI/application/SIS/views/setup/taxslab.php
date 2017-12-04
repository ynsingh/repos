<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name taxslab.php 
  @author Rekha Devi Pal(rekha20july@gmail.com)
 -->


<html>
<title>taxslab</title>
 <head>    
	<?php $this->load->view('template/header'); ?>
	<!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
	<?php $this->load->view('template/menu');?>
 </head>    
   <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
     <table width="100%"> 
       <tr><td>
       	<?php
           echo anchor('setup/displaytaxslab', 'Tax Slab Master', array('class' => 'top_parent'));
           echo "<td align=\"right\">";
	   $help_uri = site_url()."/help/helpdoc#Scheme";
	   echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
           echo "</td>";
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
    <tr>  
    <div>
        <form action="<?php echo site_url('setup/taxslab');?>" method="POST" class="form-inline">
          <table>

            <?php
                        echo "<td>";
                        echo form_label('Financial Year', 'tsmfy');
                        echo "</td>";
                        echo "<td>";
                        echo "<select name=\"tsmfy\" class=\"my_dropdown\" style=\"width:100%;\">";
                        echo "<option value= disabled selected >------Financial Year------</option>";
                        for($i = 2016; $i < date("Y")+10; $i++){
                        $j=$i+1;
                        echo '<option value="'.$i.' - '.$j.'">'.$i.' - '.$j.'</option>';
                        }
                        echo " </select>";
                        ?>

            <tr>  
                <td><label for="tsmname" class="control-label"> Tax Slab Name :</label></td>
                <td>
                <input type="text" name="tsmname" class="form-control" size="40" /><br>
                </td>
 		<td><?php echo form_error('tsmname')?></td> 
            </tr>
            <tr> 
                <td>    
                <label for="tsmstartvalue" class="control-label">Tax Slab Start Value :</label>
                </td>
                <td>
                    <input type="text" name="tsmstartvalue" size="40" class="form-control"/> <br>
                </td>
 		<td><?php echo form_error('tsmstartvalue')?></td>
            </tr>
            <tr>
                <td>   
                    <label for="tsmendvalue" class="control-label">Tax Slab End Value :</label>
                </td>
                <td>
                    <input type="text" name="tsmendvalue" size="40"  class="form-control"/> <br>
                </td>
		 <td><?php echo form_error('tsmendvalue')?></td>
            </tr>


                <tr>
                        <td>Tax Slab Type:</td>
                        <td>
                        <select name="tsmtype" id="" class="my_dropdown" style="width:100%;">
                        <option value="" disabled selected >------Select Type------</option>
                        <option value="Normal" class="dropdown-item">Normal</option>
                        <option value="Senior Citizen" class="dropdown-item">Senior Citizen</option>
                        </select>
                        </td></tr>
                <tr>
                        <td>Tax Slab Gender:</td>
                        <td>
                        <select name="tsmgender" id="" class="my_dropdown" style="width:100%;">
                        <option value="" disabled selected >------Select Gender------</option>
                        <option value="All" class="dropdown-item">All</option>
                        <option value="Male" class="dropdown-item">Male</option>
                        <option value="Female" class="dropdown-item">Female</option>
                        <option value="Transgender" class="dropdown-item">Transgender</option>
                        </select>
                        </td></tr>


            <tr>
                <td>   
                <label for="tsmpercent" class="control-label">Tax Slab Percent :</label>
                </td>
                <td>
                    <input type="text" name="tsmpercent"  size="40"/> <br>
                </td>
 		<td><?php echo form_error('tsmpercent')?></td>
            </tr>



            <tr><td></td>
                <td colspan="2">   
                <button name="taxslab" >Tax Slab Master </button>
		<input type="reset" name="Reset" value="Clear"/>
                </td>
            </tr>
           </table>
        </form>
       <p><br></p>
      </div> 
     </tr>     
  </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
    
