<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View|Supplier </title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
        
      <table width="100%">
            <tr>
                <?php 
		 echo "<td align=\"left\"width=\"33%\">";
	         echo anchor('picosetup/vendor/', "Add Supplier", array('title' => 'Add Detail','class' =>'top_parent'));
                 echo "</td>";
		 ?>
                 <?php
		 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big>Supplier Details</big></b>";
                 echo "</td>";
                 echo "<td align=\"right\" width=\"33%\">";
                 $help_uri = site_url()."/help/helpdoc#ViewRoleDetail";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		 echo "</td>";
                 ?>
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
             </tr>
       </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
                <tr>
<thead><th>Sr.No</th><th> Firm </th><th>Address</th><th>Contact Details</th><th>Registration Details</th><th>Supplier Details</th><th>Blacklist</th>

<?php
$suname=$this->session->userdata['username'];
if((strcasecmp($suname,"admin"))==0)
{
echo "<th>Action</th>";
}
?>

</tr></thead>
<tbody>
   <?php
        $count =0;
        //foreach ($query->result() as $row)
        foreach ($result as $row)
        {  
         ?>
             <tr>
            <td><b> <?php echo ++$count; ?>.</b> <br>
            <br>
            <br>
            <br>
            <br>
            <br>
         
            </td> 
            
            <td> 
                  <b>Firm Name-:</b> <?php echo $row->vendor_companyname ?><br>
            		<b>Owner-:</b><?php echo $row->vendor_name ?>  <br>
            		<b>E-mail-:</b><?php echo $row->vendor_email ?><br>
            		<b>Website-:</b><?php echo $row->vendor_website ?><br><br><br>
      
            </td>
            
            <td> <b>Postal-:</b> <?php echo $row->vendor_address ?><br>
                 <b>Pincode-:</b><?php echo $row->vendor_pincode ?><br>
                 <b>HQ -:</b><?php echo $row->vendor_hqaddress ?><br> 
                 <b>Pincode-:</b><?php echo $row->vendor_hqpincode ?><br><br><br>
          
            </td>
            
            <td>   <b>Name-:</b> <?php echo $row->vendor_contact_person_name ?><br>
                  <b>Phone-:</b><?php echo $row->vendor_phone ?><br>
                 <b>Mobile-:</b><?php echo $row->vendor_mobile ?><br> 
                 <b>Fax-:</b><?php echo $row->vendor_fax ?><br>
                  <b>City-:</b><?php echo $row->vendor_city ?><br> 
                 <b>State-:</b><?php echo $row->vendor_state ?><br>
          
            </td>
            
            <td> <b>GST No-:</b> <?php echo $row->vendor_gstno ?><br>
                  <b>PAN No-:</b><?php echo $row->vendor_pan ?><br>
                 <b>Shop Act No-:</b><?php echo $row->vendor_shop_act_registration_no ?><br> 
                 <b>Excise No-:</b><?php echo $row->vendor_excise_registration_no ?><br>
                  <b>Bank Account No-:</b><?php echo $row->vendor_bank_account_no ?> <br><br>
                                   
                             </td>
            
            <td> <b>Type-:</b><?php echo $row->vendor_type ?> <br>
                 <b>Items Supply-:</b><?php echo $row->vendor_item_supply ?><br>
                 <b>Previous Supplies-:</b><?php echo $row->vendor_pre_order ?><br><br><br><br>
           
            </td>
            
            
           
            <td>  <b>Status-:</b><?php echo $row->vendor_blackliststatus ?> <br>
            		<?php if($row->vendor_blacklistdatefrom != 0000-00-00 && !empty($row->vendor_blacklistdatefrom)){
            			?>
            		<b>Date Start-:</b><?php echo $row->vendor_blacklistdatefrom ?>
            		<?php } ?>                                       <br>
            		<?php if($row->vendor_blacklistdateto != 0000-00-00 && !empty($row->vendor_blacklistdateto) ){
            			?>
            		<b>Date End-:</b><?php echo $row->vendor_blacklistdateto ?>
            		<?php } ?>                                       <br>
            		<?php if($row->vendor_blacklistdateto != 0000-00-00 && !empty($row->vendor_blacklistdatefrom)){
            			?>
            		<b>By-:</b><?php echo $row->vendor_blacklistby ?>
            		<?php } ?>                                       <br>
            		<br><br>
            </td>
           
	         
	        
            <?php  
		       $suname=$this->session->userdata['username'];
	          if((strcasecmp($suname,"admin"))==0)
	    	   {	
	    	   echo "<td>";
	    	
	    	   echo "&nbsp; ";
            		echo anchor('picosetup/editvendor/' . $row->vendor_id , "EDIT", array('title' => 'Details' , 'class' => 'red-link')) . "<br><br><br> ";
		
	    		echo "&nbsp; ";
            		echo anchor('picosetup/deletevendor/' . $row->vendor_id , "DELETE", array('title' => 'Details' , 'class' => 'red-link')) . " ";
		
          
            echo "</td>";
            echo "</tr>";
          
            }}
          ?>
          
</tbody>
</table>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





