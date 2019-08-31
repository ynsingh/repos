 
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Item List
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */
?>

<html>
<title>Item List | Display</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script>
	$(document).ready(function(){

		$("#btnUpload").on('click',function(){
                var emptype= $('#emppfno').val(); 
//                alert("emptyy==="+emppfno);
                if( emptype === null || emptype === ''){
                    alert("Please Type Employee PF No..!!");
                    return false;
                } 
            });

	  });
	</script>
  </head>
 <body>
      <table width="100%">
            <tr>
                <?php 
                    echo "<td align=\"left\"width=\"33%\">";
                    echo anchor('itemaction/itemreturndetails/', "Returned Item Details ", array('title' => 'Item Issue Form','class' =>'top_parent'));
                    echo "</td>";
                  ?>
                 <?php
                   echo "<td align=\"center\" width=\"34%\">";
                   echo "<b>Item Issued Details</b>";
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
	<form name= "frm" action="<?php echo site_url('itemaction/returnitem');?>" method="POST" enctype="multipart/form-data">
	<table  width="100%" class="TFtable">

                <tr><thead><th style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="4">&nbsp;&nbsp;</th></thead></tr>
                        <tr style="font-weight:bold;">
                    <td><label for="emppfno" style="font-size:15px;font-weight:bold;"><font>Employee PF No</font> <font color='Red'>*</font></label>
		    <div><input type="text" name="emppfno" id="emppfno" value="" placeholder="Employee PF No..."  required>   </div>
                    </td>
                        <td>
			<button name="addsalaryhead" id="btnUpload">Submit</button>
                    </td>
                    <!--    <td>
                                <input type="text" id="error" value="" style="text-decoration:none;border:0; font-size:25px;font-weight:bold;color:red; word-break: break-all;width:400px;" readonly>
                        </td> -->
                </tr>
		</table>
	</form>
      <table class="TFtable" >
		<tr>
<thead><th>Sr.No</th>
  <th>Issue Details</th><th> Item Details</th><th>Receiver Details</th><th>Action</th></tr></thead>
  <tbody>
   <?php
        $count =0;
	if(!empty($result)){	
        foreach($result as $row)
        {  
         ?>
          <tr>
            <td> <?php echo ++$count; ?> </td> 
	    <td> <?php 
		echo "PF No. :".$row->ii_staffpfno."</br>";
		echo "Name :".$row->ii_staffname."</br>";
		echo "Department :".$row->ii_dept."</br>";
		echo "Date :".$row->ii_creatordate;
		 ?></td>
	    <td> <?php 
            echo "Type :".$this->picomodel->get_listspfic1('material_type','mt_name','mt_id',$row->ii_mtid)->mt_name."</br>"; 
		echo "Name :".$row->ii_name."</br>";
//		echo "Price :".$row->item_price."</br>";
		echo "Qty :".$row->ii_qty."</br>";
		echo "Desc :".$row->ii_desc;

		 ?></td>
            <td> <?php echo $row->ii_receivername; ?></td>

      <td>
	<?php  
		
      if(($row->ii_qty - $row->ii_irqty) > 0){
            
            echo "&nbsp; ";
            echo anchor('itemaction/returnitemtype/'.$row->ii_id , "Return", array('title' => 'Return' , 'class' => 'red-link')) . " ";
         //   echo "<br>";
         //   echo anchor('itemaction/edititemdetails/' . $row->item_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
            
        }
            echo "</td>";
          echo "</tr>";
          
	}//end of foreach
	}else{
		echo "<tr><td colspan=5>";
		echo "<b> No item issued with this PF Number</b>";
		echo "</td></tr>";

	}

          ?>  
  </tbody>
</table>
<br><br>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



