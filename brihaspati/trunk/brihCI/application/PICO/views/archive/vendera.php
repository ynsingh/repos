<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>

<table width="100%">
   <tr colspan="2"><td>
            <div>
	<?php
            echo "<td align=\"center\" width=\"100%\">";
            echo "<b>Vender Archive Details</b>";
            echo "</td>";
    	?>
             <?php echo validation_errors('<div class="isa_warning>','</div>');?>
              <?php echo form_error('<div class="isa_error">','</div>');?>
               <?php if(isset($_SESSION['success'])){?>
                    <div  class="isa_success"><?php echo $_SESSION['success'];?></div>
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
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                <th>Vender Details</th>
                <th>Vender Address</th>
                <th>Contact Details</th>
                <th>Other Info.</th>
                <th>Order Details</th>
                <th>Status</th>
                <th>Archiver Details</th>
              <!--  <th>Department Name</th>
                <th>Department Description</th>
                <th>Archiver Name</th>
                <th>Archive date</th>-->
                </tr>
	    </thead>
<!--	archive_id	vendor_archive_id	vendor_archive_userid	vendor_archive_companyname	vendor_archive_name	
vendor_archive_address	vendor_archive_pincode	vendor_archive_hqaddress	vendor_archive_hqpincode vendor_archive_email	vendor_archive_website	
vendor_archive_contact_person_name	vendor_archive_phone	vendor_archive_mobile	vendor_archive_fax	vendor_archive_city	vendor_archive_state
vendor_archive_gstno	vendor_archive_pan	vendor_archive_shop_act_registration_no	vendor_archive_excise_registration_no	vendor_archive_bank_account_no
vendor_archive_type	vendor_archive_pre_order	vendor_archive_item_supply	
vendor_archive_blackliststatus	vendor_archive_blacklistdatefrom	vendor_archive_blacklistdateto	vendor_archive_blacklistby
vendor_archive_updatedby	vendor_archive_updatedate -->
            <tbody>
                    <?php $count =0?>
                    <?php foreach($va as $row) 
			{ 
		        echo "<tr>";
                     //   echo "<td>" . $this->common_model->get_listspfic1('org_profile','org_name','org_code',$row->depta_orgcode)->org_name. "</td>";
                       // echo "<td>" ;
		//	if(!empty($row->depta_sccode)){
			//	$res= $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$row->depta_sccode);
			//	if(!empty($res)){
			//echo	$res->sc_name;
			//	}
		//	}
		//	echo "</td>";
                  //      echo "<td>". $this->logmodel->get_listspfic1('authorities','name','id',$row->depta_uoid)->name . "</td>";
			echo "<td>" . $row->vendor_archive_id ."</br>";
			echo $row->vendor_archive_userid."</br>";
			echo $row->vendor_archive_companyname."</br>";
			echo $row->vendor_archive_name."</br>";
			echo "</td>";
                        echo "<td>" . $row->vendor_archive_address. "</br>";
			echo $row->vendor_archive_pincode."</br>";
			echo $row->vendor_archive_hqaddress ."</br>";
			echo $row->vendor_archive_hqpincode ."</br>";
			echo $row->vendor_archive_email ."</br>";
			echo $row->vendor_archive_website ."</br>";
			echo "</td>";
                        echo "<td>" . $row->vendor_archive_contact_person_name. "</br>";
			echo $row->vendor_archive_phone ."</br>";
			echo $row->vendor_archive_mobile ."</br>";
			echo $row->vendor_archive_fax  ."</br>";
			echo $row->vendor_archive_city ."</br>";
			echo $row->vendor_archive_state ."</br>";
			echo "</td>";
                        echo "<td>" . $row->vendor_archive_gstno . "</br>";
			echo $row->vendor_archive_pan ."</br>";
			echo $row->vendor_archive_shop_act_registration_no ."</br>";
			echo $row->vendor_archive_excise_registration_no ."</br>";
			echo $row->vendor_archive_bank_account_no ."</br>";
			echo "</td>";
                        echo "<td>" . $row->vendor_archive_type . "</br>";
			echo $row->vendor_archive_pre_order ."</br>";
			echo $row->vendor_archive_item_supply ."</br>";
			echo "</td>";
                        echo "<td>" . $row->vendor_archive_blackliststatus . "</br>";
			echo $row->vendor_archive_blacklistdatefrom ."</br>";
			echo $row->vendor_archive_blacklistdateto ."</br>";
			echo $row->vendor_archive_blacklistby ."</br>";
			echo "</td>";
                        echo "<td>" . $row->vendor_archive_updatedby. "</br>";
			echo $row->vendor_archive_updatedate ."</br>";
			echo "</td>";
//                        echo "<td>" . $row->creatorid. "</td>";
  //                      echo "<td>" . $row->createdate. "</td>";

                        //echo "<td>" . anchor('setup/deletedept/' . $row->dept_id , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
                       // echo "</br>";
                        echo "</tr>";
			}
		//	echo "</table>";
			?>	
            </tbody>
		</div>
          </table>
    </body>
 <p> &nbsp; </p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                     
 


