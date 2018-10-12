<!-- @name editsocieties.php  @author Neha Khullar(nehukhullar@gmail.com)  -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        


 <!--link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css"-->
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>


</head>
    <body>

        <script>
        function goBack() {
        window.history.back();
        }
        </script>

        <table>
            <tr><td>
                <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div  class="isa_error">','</div>');?>
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
      <table>
      <form action="<?php echo site_url('map/editsocieties/' .$id);?>" method="POST" class="form-inline">
            <tr>
           <td>Societies Id</td>
                <td>
                   <?php echo form_input($id); ?>

               </td>
                 </td>
            </tr>
            <tr>
                <td>Society Head</td>
                <td>
                  <?php echo form_input($society_head); ?>
                </td>
                <td><?php echo form_error('society_head')?></td>
            </tr>
 
            <tr>
                <td>Society Secretary</td>
                <td>
                  <?php echo form_input($society_secetary); ?>
                </td>
                <td><?php echo form_error('society_secetary')?></td>
            </tr>

            <tr>
                <td>Society Treasurer</td>
                <td>
                  <?php echo form_input($society_treasurer); ?>
                </td>
                <td><?php echo form_error('society_treasurer')?></td>
            </tr>

            <tr>
                <td>Society Members</td>
                <td>
                  <?php echo form_input($society_members); ?>
                </td>
                <td><?php echo form_error('society_members')?></td>
            </tr>


		<tr>
                <td>Society Totalvalues</td>
                <td>
                  <?php echo form_input($society_totalvalues); ?>
                </td>
                <td><?php echo form_error('society_totalvalues')?></td>
            </tr>


                </td>
                <td><?php echo form_error('id')?></td>
               </td>
            </tr>
               <td></td>
                <td colspan="2">
                <button name="editsocieties" >Update</button>
            <?php echo form_hidden('id', $id);?>
        </form>
                 <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
                </td>
            </tr>
        </table>
    </body>
<p> &nbsp; </p>
    <div align="center"><?php $this->load->view('template/footer');?></div>
</html>
         
    


