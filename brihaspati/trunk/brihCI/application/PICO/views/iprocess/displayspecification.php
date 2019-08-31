 
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Committee Selection
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com) 
 */
?>

<html>
<title>SPECIFICATIONS  | DETAILS</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
      <table width="100%">
            <tr>
                <?php 
                    echo "<td align=\"left\"width=\"33%\">";
                    echo anchor('iprocess/specification/', "Add Specifications", array('title' => 'Specifications','class' =>'top_parent'));
                    echo "</td>";
                  ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Intender Specifications Details</b>";
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
              <thead>
                  <th>Sr.No</th>
                  <th>Enquiry No.</th>
                  <th>Item Details</th>
					<th>Intender Details.</th>
				</thead>
            </tr>
<tbody>
   <?php
        $count =0;
        
        foreach ($specs as $row)
        {  
         ?>
          <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->enquiry_no ?></td>
			<td> 
                  <b>Item Name:- </b><?php echo $row->item_name ?><br>
               
                  <b>Item Quantity:- </b><?php echo $row->item_quantity ?><br>
                  
                  
                  
			
				  
            
				<td><b>Name:- </b><?php echo $row->name ?><br>
                
                  <b>Department Id:- </b><?php echo $row->dept_id ?><br>
                  
                  <b>Designation Id:- </b><?php echo $row->desig_id ?><br>
				  
				  <b>Email-Id:- </b><?php echo $row->email ?><br>
                
                  <b>Phone:- </b><?php echo $row->phone ?><br>
				  <?php } ?>
			</td>
                  
                  
            
                 
          

      <td>
       
</tbody>
</table>
<br><br>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



