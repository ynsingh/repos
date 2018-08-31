<!--@name approve.php
	@author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Approve Leave Request</title>
<head> 
     <?php $this->load->view('template/header'); ?>
	  <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
	  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery.datetimepicker.css"/>
</head>

<body><!--<?php print_r($lrmain);?>-->
       <table width="100%">
           <tr><td>
             <div>
                  <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                  <?php echo form_error('<div class="isa_error">','</div>');?></div>
                  <?php if(isset($_SESSION['success'])){?>
                  <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                  <?php
                  };
                  ?>
             </div>
        		</td></tr>
        </table>
		  <table width="100%">
		  <tr>
				<td><?php echo anchor('setup4/pendingincomereq/', "Pending Saving Master", array('title' => 'Pending Saving Master' ,'class' =>'top_parent'));?></td>
		  </tr>
		  </table><br>
        <table>
			<?php
        	 echo form_open('setup4/approve/' . $usm_id);
        	 
           echo "<td>";
           echo form_hidden('usm_id', $usm_id);
           echo"<td>";
           echo form_submit('submit', 'Approve');
           echo " ";
           echo form_close();
			  echo "</td>";
           echo "</tr>";
			  ?>
</table>
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                                                                                                                        
