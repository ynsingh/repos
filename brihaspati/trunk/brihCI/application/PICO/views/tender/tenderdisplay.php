<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View|Tenders </title>
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
        <table class="TFtable" >
                <tr>
<thead><th>Sr.No</th><th>Tender Details</th><th>Work Details</th><th>Fees Details</th><th>Critical Dates</th><th>NIT DOC</th><th>BID Opener Details</th>

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
   // print_r($result);
    $tcid='';
        foreach ($result as $row)
        { 
        $id=$row->tc_id;
        
        if($tcid!=$id)
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
                  <b>Reference No-:</b> <?php echo $row->tc_refno ?><br>
            		<b>Type-:</b><?php echo $row->tc_tentype  ?>  <br>
            		<b>Contract Type-:</b><?php echo $row->tc_contractform ?><br>
            		<b>Category-:</b><?php echo $row->tc_category  ?><br><br><br>
      
            </td>
            
            <td>
                 <?php if(!empty($row->tc_workitemtitle)){
            			?>
            
                 <b>Work Item Tittle-:</b> <?php echo $row->tc_workitemtitle ?><br>
                 <b>Work Description-:</b><?php echo $row->tc_workdesc ?><br>
                 <b>Work Category-:</b><?php echo $row->tc_prodcatid ?><br> 
                 <b>Sub Category-:</b><?php echo $row->tc_prodsubcat ?><br><br><br>
                 
                 	<?php } ?>  
          
            </td>
            
            <td> 
                 <?php if(!empty($row->tc_tenderfees)){
            			?>
            
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
                 <?php if(!empty($row->tc_publishingdate && $row->tc_publishingdate != 1000-01-01)){
            			?>
                
                 <b>Publishing-:</b> <?php echo $row->tc_publishingdate ?><br>
                 <b>DOC Sale Start-:</b><?php echo $row->tc_docsalestartdate ?><br>
                 <b>Clarification Start-:</b><?php echo $row->tc_seekclailstartdate ?><br> 
                 <b>Bid Submit Start-:</b><?php echo $row->tc_bidsubstartdate ?><br>
                 <b>Bid Submit End-:</b><?php echo $row->tc_bidsubenddate ?><br>
                 <b>Bid Opening-:</b><?php echo $row->tc_bidopeningdate ?> <br><br>
                 
                 	<?php } ?>  
                                   
            </td>
            
            <td> 
                 	<?php if(!empty($row->tc_nitdocfilename)){
            			?>
            
            	  <b>File Name-:</b><?php echo $row->tc_nitdocfilename ?> <br>
                 <b>File Size-:</b><?php $a=$row->tc_nitdocfilesize/1024;
                                        
                                         echo round($a,2) ?>KB<br>
                 <b>File Type-:</b><?php echo $row->tc_nitdoctype ?><br><br><br><br>
                 
                 	<?php } ?>  
           
            </td>
            
            
           
            <td>  
            
                 
            		<?php if(!empty($row->tbos_boname )){
            			?>
            		<b>Name-:</b><?php echo $row->tbos_boname  ?> <br>		
            		<b>Designation-:</b><?php echo $row->tbos_bodesig ?> <br>
            	   <b>Email-:</b><?php echo $row->tbos_boemail ?> <br>
            	   <br><br><br>
       	         <?php } ?>  
            </td>
           
	         
	        
            <?php  
		       $suname=$this->session->userdata['username'];
	          if((strcasecmp($suname,"admin"))==0)
	    	   {	
	    	    echo "<td>";
	    	    
	    	    if($row->tc_workitemtitle ==NULL  )                            //empty($row->tc_workitemdetails))
	    	   
	    	    {
	    	    echo "&nbsp; ";
            		echo anchor('tender/step2/' . $row->tc_id , "Complete", array('title' => 'Work Item Details' , 'class' => 'red-link'));
             }
             elseif($row->tc_tenderfees ==NULL || $row->tc_tenderfees == 0)
              {
              echo "&nbsp; ";
            		echo anchor('tender/step3/' . $row->tc_id , "Complete", array('title' => 'Fees Details' , 'class' => 'red-link')); 	
                           	
              	
              }
              elseif($row->tc_publishingdate == 1000-01-01 || $row->tc_publishingdate == null)
              {
              echo "&nbsp; ";
            		echo anchor('tender/step4/' . $row->tc_id , "Complete", array('title' => 'Critical Dates Details' , 'class' => 'red-link')); 	
                           	
              	
              }
	    	    elseif(empty($row->tc_nitdocfilename))
             {
             echo "&nbsp; ";
          	      echo anchor('tender/step5/' . $row->tc_id , "Complete", array('title' => 'NIT Documents' , 'class' => 'red-link')); 	
                           	
              	
              }
             elseif(empty($row->tbos_boname))
             {
             echo "&nbsp; ";
          	      echo anchor('tender/step6/' . $row->tc_id , "Complete", array('title' => 'Bid Opener Selection' , 'class' => 'red-link')); 	
                           	
              	
              }
            elseif($row->tc_approvedstatus== 'Approved') {  echo "Approved" ; 
             echo "<br>";
             echo "BY:<b>".$row->tc_approvedbyname ;
             echo "</b><br><br>";
            echo "&nbsp; ";
            		echo anchor('tender/tenderdismiss/' . $row->tc_id , "Dismiss", array('title' => 'Dismiss' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Remove Approved Status-- ?');"))." ";
            
            
            }  
              
              
	    	   else
	    	   {
	    	   echo "&nbsp; ";
            		echo anchor('tender/tenderedit/' . $row->tc_id , "EDIT", array('title' => 'EDIT Details' , 'class' => 'red-link')) . "<br><br>";
            echo "&nbsp; ";
            		echo anchor('tender/tenderview/' . $row->tc_id , "VIEW", array('title' => 'VIEW Details' , 'class' => 'red-link')) . "<br><br>";
            
            echo "&nbsp; ";
               if($row->tc_approvedstatus== null ||  $row->tc_approvedstatus== 'Dismissed')               
               {echo anchor('tender/tenderapprove/' . $row->tc_id , "Approve", array('title' => 'Approve' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Approve This Tender-- ?');"))." ";              		
           		}
           		
            }	
            	
            echo "<br><br>";
                        
            
		      echo "&nbsp; ";
            echo anchor('tender/tenderdelete/' . $row->tc_id , "DELETE", array('title' => 'Delete ' , 'class' => 'red-link' , 'onclick' => "return confirm('Are you sure you want to --Delete This Tender-- ?');" ) )." ";
                      
          
         
            echo "</td></tr>";
             
          }$tcid=$id;
            }}
          ?>
          
</tbody>
</table>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





