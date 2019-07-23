<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View|Tender|Requests </title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>
        
      <table width="100%">
            <tr>
                <?php 
		 echo "<td align=\"left\"width=\"33%\">";
	        // echo //anchor('picosetup/vendor/', "", array('title' => 'Add Detail','class' =>'top_parent'));
                 echo "</td>";
		 ?>
                 <?php
		 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b><big>Tender Request Details</big></b>";
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
<thead><th>Sr.No</th><th> Tender Info </th><th>No. Of Applicants</th>

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
  
        $tcid='';
  
        foreach ($result as $row)
        {   
         $id=$row->tc_id;
    
		               $id=$row->tc_id;
		         		if($tcid!=$id)
		             
		             {
 							
		         ?>
		            <tr>
		            <td><b> <?php echo ++$count; ?>.</b> <br>
		            		         
		            </td> 
		            
		            <td> 
		                  <b>Tender Name-:</b> <?php echo $row->tc_workitemtitle ?><br>
		            		<b>Reference No-:</b><?php echo $row->tc_refno ?>  <br>
		            		<b>Prepared By-:</b><?php echo $row->tc_tenderprepby ?><br>
		            		<b>Tender ID-:</b><?php echo $row->tc_id ?><br><br><br>
		      
		            </td>
		            
		            <td> 
		                 <b>No. Of Applications-:</b> <?php 
		                //  $today=date('Y-m-d');
		                  $whdata = array('ta_tcid' => $id);
		                  echo $this->PICO_model->getnoofrows('tender_apply',$whdata); ?><br>
		            </td>
       
		            <?php  
				       $suname=$this->session->userdata['username'];
			          if((strcasecmp($suname,"admin"))==0)
			    	   {	
			    	   echo "<td>";
			    	
			    	   echo "&nbsp; ";
		            		echo anchor('tender/tender_applicants/' . $row->tc_id , "View Applications", array('title' => 'View Details' , 'class' => 'red-link')) . "<br><br><br> ";
				
		            echo "</td>";
		            echo "</tr>";
		             
		            }
		            $tcid=$id; 
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





