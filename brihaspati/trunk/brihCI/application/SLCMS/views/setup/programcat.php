<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  Programcat.php 
  @author Raju kamal(kamalraju8@gmail.com)
 -->
<html>
<title>Add program Category</title>
 <head>    
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
 </head>
   <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>


     <table width="100%">
       <tr>
        <div>
        <?php 
           echo "<td align=\"left\" width=\"33%\">";
           echo anchor('setup/viewprogramcat', 'Program Categroy List', array('class' => 'top_parent'));
           echo "</td>";

           echo "<td align=\"center\" width=\"34%\">";
           echo "<b>Add Program Category Details</b>";
           echo "</td>";

           echo "<td align=\"right\" width=\"33%\">";
           $help_uri = site_url()."/help/helpdoc#ProgramCategory";
           echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
           echo "</td>";
        ?>
        </div>
       </tr>
           </table>
           <table width="100%">
           <tr><td>
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
    <div>
        <form action="<?php echo site_url('setup/programcat');?>" method="POST" class="form-inline">
        <table>
            <tr>
                <td><label for="procatname" class="control-label"> Program Category Name :</label></td>
                <td>
                <input type="text" name="procatname" class="form-control" size="40" />
                </td>
                <td> Example : Under Graduate , Post Graduate , Diploma  </td>
            </tr>
            <tr>
                <td>
                <label for="procatcode" class="control-label">Program Category Code :</label>
                </td>
                <td>
                    <input type="text" name="procatcode" size="40" class="form-control"/> 
                </td>
                <td>Example :01,02,03,04 </td>
            </tr>
            <tr>
                <td>
                    <label for="proshrtname" class="control-label">Program Category Short Name :</label>
                </td>
                <td>
                    <input type="text" name="proshrtname" size="40"  class="form-control"/> 
                </td>
                <td> Example: UG,PG,R etc  </td>
            </tr>
            <tr>
                <td>
                <label for="prodesc" class="control-label">Program Category Description :</label>
                </td>
                <td>
                    <input type="text" name="prodesc"  size="40"/> 
                </td>
                <td> Example :  </td>
            </tr>
            <tr><td></td>
                <td>
                <button name="programcat" >Add Category program </button>
                <input type="reset" name="Reset" value="Clear"/>
                </td>
            </tr>
           </table>
          </form>
       </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>

                         
