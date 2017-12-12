<!--@name addexamtype.php
    @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Exam</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     <?php $this->load->view('template/menu');?>
 </head>
 <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
 <table width="100%">
            <tr>
                <?php 
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup2/examtype/', "View Exam Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));
                echo "</td>";

                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Add Exam Details</b>";
                echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewExamDetail";
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
    <form action="<?php echo site_url('setup2/addexamtype');?>" method="POST" class="form-inline">
    <table>
            <tr>
                <td><label for="exty_name" class="control-label">Exam Type:</label></td>
                <td>
                <input type="text" name="exty_name"  class="form-control" size="30" />
                </td>
                <td>
                   Example: Internal Exam, External Exam, Practical, Viva-Voce
                </td>

            </tr>
            <tr>
                <td>
                <label for="exty_desc" class="control-label">Exam Description:</label>
                </td>
                <td>
                    <input type="text" name="exty_desc" size="30"  class="form-control" /> 
                </td>
                <td>
                    Example : Exam, University Exam, Practical Exam, Oral Exam
                </td>
            </tr>
            <tr>
                <td></td><td>
                <button name="addexamtype" >Add Exam</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
                 </table>
    </form>
</div>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

