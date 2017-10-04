<!--@name addexamtype.php
    @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Exam</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
     <?php $this->load->view('template/menu');?>
 </head>
 <body>
 <table width="100%">
            <tr><td>
                <div align="left">
                <?php echo anchor('setup2/examtype/', "View Exam Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <div  style="width:100%;">
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
<tr>
    <div>
    <form action="<?php echo site_url('setup2/addexamtype');?>" method="POST" class="form-inline">
            <table style="margin-left:1%;">
            <tr>
                <td><label for="exty_name" class="control-label">Exam Type:</label></td>
                <td>
                <input type="text" name="exty_name"  class="form-control" size="30" /><br>
                </td>
                <td>
                    <?php echo form_error('exty_name')?>
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
                    <input type="text" name="exty_desc" size="30"  class="form-control" /> <br>
                </td>
                <td>
                    <?php echo form_error('exty_desc')?>
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
    </tr>
    </table>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

