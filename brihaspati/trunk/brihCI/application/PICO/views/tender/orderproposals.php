<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<head>
<title>Proposals|L1|Order</title>
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
                            $("#offpayrowl").show();}
                        else{
                            $("#offpayrowl").hide();}
               });
               
                      
               });

</script>
  <table width="100%">
            <tr><td>
                <?php // echo anchor('pico', "View Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>"
                 ?>
                <div  style="width:100%;">
                <?php echo validation_errors('<div class="isa_warning">','');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?>
                <?php
                };
               ?>
              
             </td></tr>
        </table>



<form action="<?php echo site_url('tender/order');?>" method="POST" class="form-inline" enctype="multipart/form-data">


<h2 class="title">Order Proposals</h2>

  
          <table class="TFtable">
            <tr>
              
                <td><label for="bd_tt" class="control-label">Ready for PO:</label></td>
                <td>
                <select class="approve" name="id"  style="width:317px ;">
				          <option selected="" disabled="">----Select Proposal ID----</option>
                            <?php
                                foreach ($dept as $roow){
                            ?>
                                    <option value="<?php echo $roow->pp_id ?>"><?php echo 'Proposal ID='.$roow->pp_id.',Tender ID='.$roow->pp_tcid.' & Reference No.='.$roow->pp_tenrefno ?></option> 
                                <?php
                                }
                                ?>
				    </select>
                
                
                 <br></td>
           
         
                   
               
		    <td id="offpayrowl"  >
				
					<button name="push" style="float:left; ">--Submit--</button>
				
					</td>
					</tr>     
 			 
 			 </table>    
</form>           
        <table class="TFtable" >
                <tr>
<thead><th>Sr.No</th><th>Proposal Order Details</th><th>Proposal Details</th><th>Indenter Details</th><th>Item Details</th><th>Budget Details</th><th>Delivery details</th><th>Terms & Condition Details</th>

<?php
$suname=$this->session->userdata['username'];
if((strcasecmp($suname,"admin"))==0)
{
echo '<th>Links<th>';
} 
?>

</tr></thead>
<tbody>
   <?php
        $count =0;
   //print_r($result);
    $tcid='';
        foreach ($result as $row)
        { 
        
       
        
       
      
        
         
         ?>
         
             <tr>
            <td><b> <?php echo ++$count; ?>.</b> <br>
            <br>
            <br>
            <br>
            <br>
            <br><br> 
         
            </td> 
            <td>   <b>Proposal Order No-:</b> <?php echo $row->po_no ?><br>
            		<b>Order Date-:</b><?php echo $row->po_date  ?>  <br>
            		<b>Created by-:</b><?php echo $row->po_createdby ?><br>
            		<b></b><?php   ?><br><br><br><br>
            </td>
            <td> 
                  <b>Proposal Reference No-:</b> <?php echo $row->pp_gemrefno ?><br>
            		<b>Tender Reference No-:</b><?php echo $row->pp_tenrefno  ?>  <br>
            		<b>Vendor -:</b><?php echo $row->pp_vendorid ?><br>
            		<b></b><?php   ?><br><br><br><br>
      
            </td>
            
            <td>
         
            
                 <b>Indent Name-:</b> <?php echo $row->pp_indentername ?><br>
                 <b>Indent E-mail-:</b><?php echo $row->pp_indenteremail ?><br>
                 <b>PF No-:</b><?php echo $row->pp_indenterid ?><br> 
                 <?php $m=$this->common_model->get_listspfic1('Department','dept_name','dept_id',$row->pp_deptid)->dept_name;     ?>
                 <b>Department-:</b><?php echo $m ?><br>
                 <b>Department No-:</b><?php echo $row->pp_deptindentno ?><br>
                 <b>Indent Date-:</b><?php echo $row->pp_indentdate ?><br><br> 
                 
                
          
            </td>
            
            <td> 
             
                   <?php $n=$this->PICO_model->get_listspfic1('material_type','mt_name','mt_id',$row->pp_materialtypeid)->mt_name;    ?>
                   <b>Type Of Material-:</b> <?php echo $n ?><br>
                   <?php $o=$this->PICO_model->get_listspfic1('tender_create','tc_workitemtitle','tc_id',$row->pp_tcid)->tc_workitemtitle;    ?>
                   <b>Work item-:</b><?php echo $o?><br>
                   <?php $p=$this->PICO_model->get_listspfic1('tender_create','tc_workdesc','tc_id',$row->pp_tcid)->tc_workdesc;    ?>
                   <b>Work description-:</b><?php echo $p?><br>  
                   <?php $q=$this->PICO_model->get_listspfic1('tender_create','tc_quantity','tc_id',$row->pp_tcid)->tc_quantity;    ?>                 
                   <b>Quantity-:</b><?php echo $q ?><br> 
                   <?php $r=$this->PICO_model->get_listspfic1('tender_apply','ta_baseprice','ta_id',$row->pp_taid)->ta_baseprice;    ?>         
                   <b>Price-:</b><?php echo $r ?><br>
                   <?php $s=$this->PICO_model->get_listspfic1('tender_apply','ta_gsttax','ta_id',$row->pp_taid)->ta_gsttax;    ?>         
                   <b>GST-:</b><?php echo $s ?><br>
                   <b>Total-:</b><?php echo $row->pp_itemtotcost ?> 
                    <br>
          
          		
            </td>
            
            <td>
             
                 <b>Budget Project No.-:</b> <?php echo $row->pp_budgetprojno ?><br>
                 <b>Budget info-:</b><?php echo $row->pp_budgethead ?><br>
                 <b>Budget amount-:</b><?php echo $row->pp_budgetamt ?><br> 
                 <br> <br> <br> <br> 
                 
                
                                   
            </td>
            
            <td> 
               
            	  <b>Delivery Period-:</b><?php echo $row->pp_deliveryperiod ?> <br>
                 <b>Date From-:</b><?php echo $row->pp_deliveryperiodfrom;?><br>
                 <b>Date To-:</b><?php echo $row->pp_deliveryperiodto ?><br><br><br><br><br> 
                 
                 
            </td>
            
            
           
            <td>  
            
                 
            
            		<b>Warranty-:</b><?php echo $row->pp_warranty  ?> <br>		
            		<b>Guarantee:</b><?php echo $row->pp_guarantee ?> <br>
            	   <b>Pay terms-:</b><?php echo $row->pp_payterm ?> <br>
            	   <br><br><br>
       	       <br> 
            </td>
           
            <?php  
		       $suname=$this->session->userdata['username'];
	          if((strcasecmp($suname,"admin"))==0)
	    	   {	
     	    	   echo "<td>";

           
            echo "<br>";
            echo anchor('tender/order_view/' . $row->pp_id , "View details", array('title' => 'Dismiss' , 'class' => 'red-link'))." ";
            
            echo "<br><br>";
            
            
                                                     }  
	         
	        
         }
          ?>
          
</tbody>
</table>



</body>
<br>
<br>
<p>&nbsp;</p>
  <div align="center"> <?php $this->load->view('template/footer');?>
</html>
