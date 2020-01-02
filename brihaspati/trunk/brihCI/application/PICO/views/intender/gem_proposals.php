<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View|Gem-Proposals</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
     

<!-- <p id="demo"></p> -->

<script>
function myFunction() {
  var txt;
  var person = prompt("Observation:", "");
  if (person == null || person == "") {
    txt = "User cancelled the prompt.";
  } else {
    txt =person;
  }
   document.getElementById("demo").innerHTML = txt;
   window.location.href = "/intender/gem_audit/";
}
</script>

        <table width="100%">
            <tr><td>
           <?php   echo anchor('intender/gemview', 'Proposal Form<br> via GeM', array('title' => 'Purchase Approval via GeM')); ?>
                <?php
                 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big>Proposals </big></b>";
                 echo "</td>";              
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for help</b></a>";
		echo "</td>"
                 ?>
                <div  style="width:100%;">
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
             </td></tr>
        </table>  
   
        <div class="scroller_sub_page">
        <table class="TFtable" >
                <tr>
<thead><th>Sr.No</th><th>Proposal Details</th><th>Indenter Details</th><th>Item Details</th><th>Delivery details</th><th>Terms & Condition Details</th>

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
            
            <td> 
                  <b>Proposal Reference No-:</b> <?php echo $row->pp_gemrefno ?><br>
            	   <br>
            		<b>Vendor ID-:</b><?php echo $row->pp_vendorid ?><br>
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
                
                   <b>Total Cost-:</b><?php echo $row->pp_itemtotcost ?> 
                    <br>   <br>   <br>   <br>   <br>   <br>   
          
          		
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

           
            echo anchor('intender/gem_proposal_view/' . $row->pp_id , "View details", array('title' => 'Dismiss' , 'class' => 'red-link'))." ";
            echo "<br><br>";
             if($row->pp_budgetapprovedby=='') {
            echo anchor('intender/gem_budget_unit/' . $row->pp_id , "Budget Unit", array('title' => 'Dismiss' , 'class' => 'red-link'))." ";
            echo "<br><br>";                
                                          }
              if($row->pp_auditapproved=='') {
            echo anchor('intender/gem_audit/' . $row->pp_id , "Internal Audit", array('title' => 'Dismiss' , 'class' => 'red-link'))."";
            echo "<br><br>";
                                           }
              if($row->pp_expsanctionstatus=='') {                               
            echo anchor('intender/gem_expenditure/' . $row->pp_id , "Expenditure Sanctioning Authority", array('title' => 'Dismiss' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to Approved this Proposal--Order Proposal-- ?');"))." ";
            echo "<br><br>";
                                           }
               if($row->pp_orderstatus=='') {
           //echo anchor('intender/proposal_order/' . $row->pp_id , "Purchase order", array('title' => 'Dismiss' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Order Proposal-- ?');"))." ";
           }
           }  
	        }
          ?>
                  
</tbody>
</table>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





