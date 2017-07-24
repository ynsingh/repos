<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name  Programcat.php 
  @author Raju kamal(kamalraju8@gmail.com)
 -->
<html>
<title>Add program Category</title>
 <head>    
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
 </head>
   <body>
     <table width="100%">
       <tr><td>
        <div style="margin-left:2%;" >
        <?php
           echo anchor('setup/viewprogramcat', 'Program Categroy List', array('class' => 'top_parent'));
           $help_uri = site_url()."/help/helpdoc#Category";
           echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
        ?>
        </font>
        </div>
        <div align="left" style="margin-left:2%;width:90%;">
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
    <tr>
    <div align="left" style="margin-left:30px">
        <form action="<?php echo site_url('setup/programcat');?>" method="POST" class="form-inline">
          <table style="margin-left:30px">
            <tr>
                <td><label for="procatname" class="control-label"> Program Category Name :</label></td>
                <td>
                <input type="text" name="procatname" class="form-control" size="40" /><br>
                </td>
                <td><?php echo form_error('procatname')?></td>
                <td> Example : Under Graduate , Post Graduate , Diploma  </td>
            </tr>
            <tr>
                <td>
                <label for="procatcode" class="control-label">Program Category Code :</label>
                </td>
                <td>
                    <input type="text" name="procatcode" size="40" class="form-control"/> <br>
                </td>
                <td><?php echo form_error('procatcode')?></td>
                <td>Example :01,02,03,04 </td>
            </tr>
            <tr>
                <td>
                    <label for="proshrtname" class="control-label">Program Category Short Name :</label>
                </td>
                <td>
                    <input type="text" name="proshrtname" size="40"  class="form-control"/> <br>
                </td>
                 <td><?php echo form_error('proshrtname')?></td>
                <td> Example: UG,PG,R etc  </td>
            </tr>
            <tr>
                <td>
                <label for="prodesc" class="control-label">Program Category Description :</label>
                </td>
                <td>
                    <input type="text" name="prodesc"  size="40"/> <br>
                </td>
                <td><?php echo form_error('prodesc')?></td>
                <td> Example :  </td>
            </tr>
            <tr><td></td>
                <td colspan="2">
                <button name="programcat" >Add Category program </button>
                <input type="reset" name="Reset" value="Clear"/>
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

                         
