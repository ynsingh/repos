<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Apply|Tenders|List </title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
        
      <table width="100%">
            <tr>
                <?php 
		 echo "<td align=\"left\"width=\"33%\">";
	         echo anchor('tender/tenderform', "Add Tender", array('title' => 'Add Detail','class' =>'top_parent'));
                 echo "</td>";
		 ?>
                 <?php
		 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big>Tenders </big></b>";
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
 <form action="<?php echo site_url('tender/tenderapply/');?>" method="POST" class="form-inline"> 
        <table class="TFtable" >
                <tr>
<thead><th>Sr.No</th><th> List Of Tender</th><th>Fees Details</th><th>Important Dates</th>

<?php

{
echo "<th>Action</th>";
}
?>

</tr></thead>
<tbody>
   <?php
        $count =0;
     
        $tcid='';
       
        foreach ($result as $row)
        { 
        $id=$row->tc_id;
       
        
        if($tcid!=$id && $row->tc_approvedstatus == 'Approved')
        {
             $p = date('Y-m-d');
             $p=date('Y-m-d', strtotime($p));
 
             $Begin = date('Y-m-d', strtotime("$row->tc_bidsubstartdate"));
             $End = date('Y-m-d', strtotime("$row->tc_bidsubenddate"));

             if (($p > $Begin) && ($p < $End)){
        	   
           
         
         ?>
         
         <tr>
            <td><b> <?php echo ++$count; ?>.</b> <br><br></td> 
            
            <td> 
                  <b>Reference No-:</b> <?php echo $row->tc_refno ?><br>
            </td>
            <td> 
                 <?php if(!empty($row->tc_tenderfees))
                 {                                      ?>
            
                   <b>Tender Fee-:</b> <?php echo $row->tc_tenderfees ?><br>
                   <b>Processing Fee-:</b><?php echo $row->tc_processingfees ?><br>
                   <b>Surcharge-:</b><?php echo $row->tc_surcharge ?><br> 
                   <b>EMD Fee-:</b><?php echo $row->tc_emdfeesmode.'(' ?> 
                    <?php 
                    if($row->tc_emdfeesmode == 'fixed' )
                    echo $row->tc_emdamount ;
                    elseif($row->tc_emdfeesmode == 'percentage' )
                    echo $row->tc_emdpercentage ;
                    else
                    { echo '' ; }                    
                      echo ' )' ;
                    
                    ?><br>
                      <b>EMD Exemption Allowed-:</b><?php echo $row->tc_emdexemption ?><br> 
                      <br>
          
          			<?php } ?>  
            </td>
            
            <td>
                 <?php if(!empty($row->tc_publishingdate && $row->tc_publishingdate != 0000-00-00)){
            			?>
                
                
                 <b>Bid Submit Start-:</b><?php echo $row->tc_bidsubstartdate ?><br>
                 <b>Bid Submit End-:</b><?php echo $row->tc_bidsubenddate ?><br>
                 <b>Bid Opening-:</b><?php echo $row->tc_bidopeningdate ?> <br><br>
                 
                 	<?php } ?>  
                                   
            </td>
         <?php
	    	   {	
	    	   echo "<td>";
	    	   echo "&nbsp; ";
            		echo anchor('tender/tenderapply/' . $row->tc_id ,"APPLY", array('title' => 'APPLY'        , 'class' => 'red-link')) . "<br><br>";
            echo "&nbsp; ";
            		echo anchor('tender/tenderview/'  . $row->tc_id ,"VIEW" , array('title' => 'VIEW Details' , 'class' => 'red-link')) . "<br><br>";
            echo "&nbsp; ";
            		echo anchor('tender/question/'  ,"QUERY", array('title' => 'Any Questions' , 'class' => 'red-link')) . "";
            
      
            echo "</td></tr>";
             
            }$tcid=$id;
            }  }}
          ?>
          
</tbody>
</table>
</form>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





