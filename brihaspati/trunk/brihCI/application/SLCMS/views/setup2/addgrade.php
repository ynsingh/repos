<!--@name addgrade.php
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add grade</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     <?php //$this->load->view('template/menu');?>
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
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
     <table width="100%" >
            <tr>
                <div>
                <?php
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup2/grademaster/', "View Grade Master ", array('title' => 'View Grade Master' ,'class' =>'top_parent'));
                echo "</td>"; 

                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Add Grade Master Details</b>";
                echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#GradeMaster";
                echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";		
                echo "</td>";  

             ?>
               </tr>
           </table>
           <table width="100%">
           <tr><td>

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
    <div> 
    <form action="<?php echo site_url('setup2/addgrade');?>" method="POST" class="form-inline">
            <table><tr>
            <tr>
                <td><label for="gm_gradename" class="control-label">Grade Name:</label></td>
                <td>
                <input type="text" name="gm_gradename"  class="form-control" size="30" />
                </td>
                <td>
                   Example: A, B, C, D, E etc
                </td>
	    </tr>
	    <tr>
                <td><label for="gm_gradepoint" class="control-label">Grade Point:</label></td>
                <td>
                <input type="text" name="gm_gradepoint"  class="form-control" size="30" />
                </td>
                <td>
                   Example: 10, 8, 6, 4, 2 etc
                </td>
            </tr>
		<tr>
                <td>
                <label for="gm_short" class="control-label">Grade Short:</label>
                </td>
                <td>
                    <input type="text" name="gm_short" size="30"  class="form-control" /> 
                </td>
                <td>
			Example: Good, Fair, Poor etc.
                </td>
            </tr>

            <tr>
                <td>
                <label for="gm_desc" class="control-label">Grade Description:</label>
                </td>
                <td>
                    <input type="text" name="gm_desc" size="30"  class="form-control" /> 
                </td>
                <td>
                 
                </td>
            </tr>
            <tr>
                <td></td><td>
                <button name="addgrade" >Add Grade</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
           </table>
	</div>
          </form>
    </div>
    </tr>
    </table>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

   
