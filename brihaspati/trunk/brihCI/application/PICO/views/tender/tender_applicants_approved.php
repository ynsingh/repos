<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<head>
<title>Tender|View</title>
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header');
     
      ?>
     
 


</head>
<body>
 
<script>
               $(document).ready(function(){
                            $("#offpayrowl").hide();
    		              
    		      $('.approve').on('change',function(){
                        var pt= $('.approve').val();
                      //  alert(pt);
                        if(pt != null){
                            $("#offpayrowl").show();
                            }
                        else{
                            $("#offpayrowl").hide();
                            }
               });
               
                      
               });

</script>


     <table width="100%">
     
       <tr>
                <?php 
		 echo "<td align=\"left\"width=\"33%\">";
		 if(empty($comingfrom) )
		 {
	         echo anchor('tender/tender_applied', "View Tenders Requests", array('title' => 'Tender Detail','class' =>'top_parent'));
                } echo "</td>";
		 ?>
                 <?php
		 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big> Tender </big></b>";
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
 
<form action="<?php echo site_url('tender/proposal');?>" method="POST" class="form-inline">

   <?php //print_r($tcresult);
		foreach($tcresult as $res){   
    ?>
          <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Tender Details:</h2>
                  
                 </td>           
            </tr>
            
            <tr>
                
             	
         
                <td><b>Tender ID:</b></td>
                <td><?php echo $res->tc_id ; ?>
                </td>
             
                <td><b>Tender Reference No:</b></td>
                <td><?php echo $res->tc_refno ; ?></td>
              
               
                 <td><b>Work Item Title:</b></td>
                <td><?php echo $res->tc_workitemtitle ; ?></td>
              
          
        
          
          
          </tr>    
         
                <td><b>Prepared By:</b></td>
                <td><?php echo $res->tc_tenderprepby ; ?></td>
              
         
        
                <td><b>Designation:</b></td>
                <td><?php echo $res->tc_prepbydesig  ; ?></td>
               
   
             
             
                <td><b>Date/Time:</b></td>
                <td><?php echo $res->tc_prepbydate ; ?></td>
               
               
              
         
         </tr>
          <tr>
                <td><b>Bid Submission Start Date:</b></td>
                <td colspan="2"><?php echo $res->tc_bidsubstartdate.' ' ;echo $res->tc_bidsubstartdatet ; ?></td>
         
       
             
          
               
                <td><b>Bid Submission End Date:</b></td>
                <td colspan="2"><?php echo $res->tc_bidsubenddate.' ' ; echo $res->tc_bidsubenddatet ; ?></td>
          </tr> 
          
         <?php  } ?>
         </table>
         <br> 
        <?php  if(empty($comingfrom) )
		 { ?>
           <table width="100%">
       <tr> 
       <td> <?php   echo anchor('tender/tender_applicants/' . $tcidlink, "Full List", array('title' => 'Complete List' , 'class' => 'red-link' ))." ";              		
           	 ?></td>    
      <td> 
         <?php   echo anchor('tender/tender_applicants_approved/' . $tcidlink , "Approved List", array('title' => 'Approve list' , 'class' => 'red-link' ))." ";              		
           	 ?> 
      </td>
       <td>   
      <?php   echo anchor('tender/tender_applicants_rejected/' . $tcidlink , "Reject List", array('title' => 'Reject list' , 'class' => 'red-link' ))." ";              		
           	 ?> 
      </td>      
       </tr>
       </table>
         <?php } ?>
         
         <br>
       
         
   <table class="TFtable">
            <tr> <td colspan="7">
               <h2 class="title">Supplier Details</h2>
                  
                 </td>           
            </tr>
    </table>
            
       <div class="scroller_sub_page">
        <table class="TFtable" >
                <tr>
 <?php if(empty($result)) { 
                                
                 echo "<th>NO Record Found.</th>";
                       }
                       else {  
                 
                    ?>
<thead><th>Sr.No</th><th> Firm </th><th>Prices</th><th>Other Info</th><th>Support Document</th>

<?php
$suname=$this->session->userdata['username'];
if((strcasecmp($suname,"admin"))==0)
{
echo "<th>Action</th>";
 } //admin check close..
 
 }//empty check close,..
?>
<th>
L1
</th>
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
            
            <td> <b>Supplier ID-:</b> <?php echo $row->ta_vendorid ;
                  //  <!-- this need to be inside the controller -->                    
                    $a=$row->ta_vendorid ;                       
			           $whdata = array('vendor_id'=>$a,  );
					     $field='vendor_companyname,vendor_name,vendor_phone ,vendor_address,vendor_website' ;
					     $data=$this->PICO_model->get_orderlistspficemore('vendor',$field,$whdata,'');  
					     $whd = array('ta_tcid' => $row->ta_tcid,'ta_status'=>'Approved');
					     $minamt=$this->PICO_model->get_minvalue('tender_apply','ta_totalprice',$whd);
					     //$minamtsize=($minamt);//size of array if size is 1 then select 
					     $small=0; 
					     foreach ($minamt as $rmin){ 
					     $small=$rmin->ta_totalprice ;
					     }
 
					     //echo $small; for details of t id
					     //print_r($whd);
					     //print_r($minamt);
					     
                    foreach ($data as $r)
                    {
                   
             ?><br>
                                                                                 
            
                  <b>Firm Name-:</b> <?php echo $r->vendor_companyname ?><br>
            		<b>Owner-:</b><?php echo $r->vendor_name ?>  <br>
            		<b>Address-:</b><?php echo $r->vendor_address ?><br>
            		<b>Contact No-:</b><?php echo $r->vendor_phone ?><br>
            		<b>Website-:</b><?php echo $r->vendor_website; } ?>
                       
            </td>
            
            <td> <b>Base Price-:</b> <?php echo $row->ta_baseprice ?><br>
                 <b>GST-:</b><?php echo $row->ta_gsttax ?><br>
                 <b>Total-:</b><?php echo $row->ta_totalprice ?><br> 
                  <br><br><br>
          
            </td>
             <td> <b>Warranty-:</b> <?php echo $row->ta_warranty ?><br>
                 <b>Payment-:</b><?php echo $row->ta_payment ?><br>
                 <b>Delivery(Days)-:</b><?php echo $row->ta_delivery ?><br> 
                 <b>Validity(Days)-:</b><?php echo $row->ta_validity ?><br> 
                 
                            <br><br>
          
            </td>
            <td> 
             <?php if(!empty($row->ta_updoc1)){ ?>
                   <b>Document 1-:</b>  
              <button onclick="location.href='<?php echo base_url();?><?php echo $row->ta_updoc1 ;?>'">View</button>
                <br>
                     <?php  } ?>
             <?php if(!empty($row->ta_updoc2)){ ?>     
                   <b>Document 2-:</b>
              <button onclick="location.href='<?php echo base_url();?><?php echo $row->ta_updoc2 ;?>'">View</button>
                <br>
                    <?php  } ?>
                            
             <?php if(!empty($row->ta_updoc3)){ ?>     
                   <b>Document 3-:</b>
              <button onclick="location.href='<?php echo base_url();?><?php echo $row->ta_updoc3 ;?>'">View</button>
                <br>
                    <?php  } ?>
            <?php if(!empty($row->ta_updoc4)){ ?>     
                   <b>Document 4-:</b>
              <button onclick="location.href='<?php echo base_url();?><?php echo $row->ta_updoc4 ;?>'">View</button>
                <br>
                    <?php  } ?>
            <?php if(!empty($row->ta_updoc5)){ ?>     
                   <b>Document 5-:</b>
              <button onclick="location.href='<?php echo base_url();?><?php echo $row->ta_updoc5 ;?>'">View</button>
                <br>
                    <?php  } ?>        
            </td>
            
	         
	        
            <?php  
		       $suname=$this->session->userdata['username'];
	          if((strcasecmp($suname,"admin"))==0)
	    	   {	
     	    	   echo "<td>";

            if($row->ta_status== 'Approved') {  echo "Approved" ; 
            echo "<br>";
            echo "BY:<b>".$row->ta_approvedby ;
            echo "</b><br><br>";
            echo "&nbsp; ";
            		echo anchor('tender/vendor_dismiss/' . $row->ta_id , "Dismiss", array('title' => 'Dismiss' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Remove Approved Status-- ?');"))." ";
            
            
                                                     }  
              
              
	    	   else
	    	   {
	    	   
            echo "&nbsp; ";
               if($row->ta_status== null ||  $row->ta_status== 'Dismissed')               
               {echo anchor('tender/vendor_approve/' . $row->ta_id , "Approve", array('title' => 'Approve' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Approve This Supplier-- ?');"))." ";              		
           		}
           		
            }	
            
            
            if($row->ta_status== 'Rejected') {  echo "Rejected" ; 
            echo "<br>";
            echo "BY:<b>".$row->ta_approvedby ;
            echo "</b><br><br>";
            echo "&nbsp; ";
            		echo anchor('tender/vendor_approve/' . $row->ta_id , "Re-approve", array('title' => 'Dismiss' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Re-add Approved Status-- ?');"))." ";
            
            
                                                     }  
              
              
	    	   else
	    	   {
	    	   
            echo "&nbsp; ";
               if($row->ta_status== null ||  $row->ta_status== 'Re-approve')               
               {echo anchor('tender/vendor_reject/' . $row->ta_id , "Reject", array('title' => 'Approve' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Reject This Supplier-- ?');"))." ";              		
           		}
           		
            }	

	    	  
		
            echo "</td>";
          
            ?>
           
            <td> 
            <?php 
            if($row->ta_totalprice==$small) {
            ?>
             <input type="radio"  checked="true" name="aradio" value="<?php echo $row->ta_id ?>" />ID:<?php echo $row->ta_id ?> 
                     <?php
          
            } else {   ?>
             <input type="radio"   name="aradio" value="<?php echo $row->ta_id ?>" />ID:<?php echo $row->ta_id ?> 
           <?php    }
                      ?>
          
            </td>            
            </tr>
            
            <?php
          
            }}
          ?>
          
</tbody>
</table>
<br>
<?php //if(empty($comingfrom) ) //to show proposal form when cs is also coming but hide the other above options
		 { ?>
<table class="TFtable" style="border-color:transparent;">
<?php //<tr id="offpayrowl" >?>
<tr>
<td><b>Remarks</b><br><br><br> </td>
<td>
<input type="hidden" name="creator" value="<?php echo $suname ?>">
<textarea class="approve" style="width:100%;height:200px;" name="comment" >


</textarea>

 </td>
</tr>
<tr id="offpayrowl">

<td colspan="2">
<button name="push" style="float:right;">Go For Purchase Proposal Form</button>
</td>
</tr>

</table>
<?php } ?>
</form>
</div><!------scroller div------>
     <br> <br>
         
         
   
   
              

</div>
</div>
</body>
<br>
<br>
<p>&nbsp;</p>
  <div align="center"> <?php $this->load->view('template/footer');?>
</html>
