<!--@name contact.php
  @author Rekha Devi Pal(rekha20july@gmail.com)
 -->
<html>
<title>Add Contact Us</title>
<head>           
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</head>    
<body>
 <?php $this->load->view('template/header'); ?>
         
	<?php $this->load->view('template/menu');?>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>

        <!--?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            echo anchor('setup/dispemailsetting/', "View Email Setting", array('title' => 'Add Detail' ,'class' =>'top_parent')) . " ";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo " Add Email Setting";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        
        ?-->

              <table width="100%">
             <tr><td>
                <?php echo anchor('setup/displaycontact/', "View Contact Us" ,array('title' => 'Contact Us Detail ' , 'class' => 'top_parent'));?>
                 <?php
                  echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Contact Us";
                 //echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
                 ?>
                <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

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
                <form action="<?php echo site_url('setup/contact');?>" method="POST" class="form-inline"></div>
                    <table>
                        <tr>  
                            <td><label for="ascu_name" class="control-label">Name:</label></td>
                            <td><input type="text" name="ascu_name" size="50"  class="form-control" /> <br></td>
                        </tr>
                        <tr>
                            <td><label for="ascu_emailid" class="control-label">Email Id:</label></td>
                            <td><input type="text" name="ascu_emailid"size="50"  class="form-control"/> <br></td>
                        </tr>
                        <tr>
                            <td><label for="ascu_phoneno" class="control-label">Mobile No:</label></td>
                            <td><input type="text" name="ascu_phoneno"  size="50" /> <br></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="2">   
                            <button name="contact" >Submit</button>
                            <button name="reset" >Clear</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
        </tr>     
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
  
