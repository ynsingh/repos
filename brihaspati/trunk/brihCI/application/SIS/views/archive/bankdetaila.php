<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name bankdetails.php
  @author Abhay Kumar (kumar.abhay.4187@gmail.com)
 -->
<html>
    <head>
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
		    <?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#AuthorityArchive";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;margin-left:35%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

        <table style="margin-left:10px;">
            <tr colspan="2"><td>
            <div  style="margin-left:-06px;width:1793px;">

                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

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
        <table cellpadding="16" style="margin-left:30px;" class="TFtable" >
            <thead>
                <tr align="center">
				 <table cellpadding="16" style="margin-left:30px;" class="TFtable" >
            <thead>
                <tr align="center">
		<th>Sr.No</th>
		
		<th>Bank Name</th>
		<th>Bank Address</th>
		<th>Bank Branch</th>
		<th>Account No</th>
		<th>Account Name</th>
		<th>Account Type</th>
		<th>IFSC Code</th>
		<th>PAN No</th>
		<th>TAN No</th>
		<th>GST No</th>
		
                </tr>
            </thead>
	    <tbody>        
                   
					 <tbody>
	<?php $count = 0;
	if( count($this->result) ) {
	      foreach ($this->result as $row)
	      {
	 ?>    
	   <tr align="center">
	        	<td><?php echo ++$count; ?> </td>
	        	<td><?php echo $row->bpa_bank_name ; ?> </td>
			<td><?php echo $row->bpa_bank_address ;?> </td>
			<td><?php echo $row->bpa_branch_name ;?></td>
	        	
			<td><?php echo  $row->bpa_account_number ;?> </td>
			<td><?php echo  $row->bpa_account_name ?> </td>
			<td><?php echo  $row->bpa_account_type ;?> </td>
			<td><?php echo  $row->bpa_ifsc_code ;?> </td>
			<td><?php echo  $row->bpa_pan_number ;  ?> </td>
			<td><?php echo  $row->bpa_tan_number  ;?> </td>	
			<td><?php echo  $row->bpa_gst_number ;  ?> </td>
			
	   </tr>
	   <?php } 
	   }else{
  	   ?>  
           <tr><td colspan= "12" align="center"> No Records found...!</td></tr>
           <?php }?> 
     </tbody>
		
            </tbody>
        </table>
    </body>	
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

				
