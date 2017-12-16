<!--@name addentranceexamfees.php
    @author Neha Khullar (nehukhullar@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Entrance Exam Fees</title>

 <head>
     <?php $this->load->view('template/header'); ?>
     <?php //$this->load->view('template/menu');?>
 </head>
 <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
 <table width="100%">
            <tr>
                <div>
                <?php 
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup/viewentranceexamfees/', "View Entrance Exam Fees Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));
                echo "</td>";

                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Add Entrance Exam Fees Details</b>";
                echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
                //$help_uri = site_url()."/help/helpdoc#Authority";
                //echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
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
    <form action="<?php echo site_url('setup/addentranceexamfees');?>" method="POST" class="form-inline">
    <table>
            <tr>
                     <tr>
                        <td>Fees Name :</td>
                        <td><input type="text"placeholder="fees" name="fees"  size="30" /></td>
                        <?php //echo form_error('fm_amount')?></td>
                        </tr>
       
                       <tr><td> Category: </td><td>
                        <select name="category" style="width:100%;">
                        <option value=""disabled selected>----Select Category----</option>
                        <?php foreach($this->catresult as $datas): ?>
                        <option value="<?php echo $datas->cat_id;?>"><?php echo $datas->cat_name; ?></option>
                        <?php endforeach; ?>
                        </select>



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
                        <td>Amount:</td>
                        <td><input type="text"placeholder="Amount" name="amount"  size="30" /></td>
                        <?php //echo form_error('fm_amount')?></td>
                        </tr>
                <tr>
                <td></td><td>
                <button name="addentranceexamfees" >Add Fees</button>
                <button name="reset" >Clear</button>
                </td>
           </tr>
       </tr>
    </table>
  </form>
</div>
</body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>

