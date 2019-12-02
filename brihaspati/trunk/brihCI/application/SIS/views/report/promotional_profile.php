<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>View Promotional Profile</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
<?php $current="promotional"; ?>
        <script>
      
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logo1.png' alt='logo' align='left' style='width:70%;height:100px;'>"+" <div style='width:70%;height:100%;'> " + printContents + "</div>"+"</body>";
                // document.body.style.fontSize = "x-small";
                //document.body.style. = "x-small";
                window.print();  
                // document.body.style.fontSize = "initial";
                document.body.innerHTML = originalContents;
            }
		 function goBack() {
        		window.history.go(-2);
	        }

        </script>

    </head>
    <body>
    <table style="width:100%;">
        <tr>
            <td>
                <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
                
            </td> 
	 <td align=right>
        <a href="#" onclick="goBack()"><img src='<?php echo base_url(); ?>uploads/icons/back1.png' title="Back"></a>
	</td>
        </tr>
    </table>        
    <div id="printme">   
      
<table style="width:100%;" border=0>
    <div align="left">
            
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
                  
        </div>
 
<tr><td>
<?php
        include  'ptab.php';
?>
                </td>

<td valign="top">		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
			<tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Promotional Particulars</b></td>
                            <td align="right">
                                <?php 
				$uname=$this->session->userdata('username');
				$rest = substr($uname, -21);

                                if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
					echo anchor("empmgmt/add_promotionaldata/{$emp_id}"," Add ",array('title' => ' Add Promotional Data' , 'class' => 'red-link'));
				}
				?>
				
                            </td>  
                        <tr>
		</table>
		<table class="TFtable" align="center">
                        <?php if( !empty($promotionaldata) ):  ?>
                    <thead>
                        <tr>
                            <th>Working Type</th>
                            <th>Group/Grade</th>
                            <th>Designation</th>
                            <th>Pay Commission</th>
                            <th>Academic Grade Pay/Level </th>
                            <th>Date of Academic Grade Pay/Level</th>
                            <th>Selection Grade Date</th>
                            <th>Special Grade Date </th>
			    <th>Support Doc </th>
			    <th colspan=2> </th>
                        </tr>
                    </thead>
                    <tbody>
                        
                            <?php foreach($promotionaldata as $record){;
//print_r($record);
//die;
?>
			<?php
				echo "<tr>";
				echo "<td>";
					echo  $record->spd_wtype;
				echo "</td>";
				echo "<td>";
					echo  $record->spd_group;
					if(!empty($record->spd_grade)){
						echo " / ";
						echo  $record->spd_grade;
					}
				echo "</td>";
				echo "<td>";
					$desig=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $record->spd_designation)->desig_name;
					echo $desig;
				echo "</td>";
				echo "<td>";

					echo  $record->spd_paycom;
				echo "</td>";
				echo "<td>";

					echo  $record->spd_agp;
					echo  $record->spd_level;
				echo "</td>";
				echo "<td>";
					if(!empty((int)$record->spd_agpdate)){
					echo  $record->spd_agpdate;
					echo "&nbsp;&nbsp;";
					echo  $record->spd_agpsession;
					}
					if(!empty((int)$record->spd_leveldate)){	
					echo  $record->spd_leveldate;
					echo "&nbsp;&nbsp;";
					echo  $record->spd_levelsession;
					}
					if(!empty((int)$record->spd_dojinpost)){	
					echo  $record->spd_dojinpost;
					echo "&nbsp;&nbsp;";
					echo  $record->spd_dojinsession;
					}
				echo "</td>";
				echo "<td>";
					if(!empty((int)$record->spd_selgradedate)){	
					echo  $record->spd_selgradedate;
					}

				echo "</td>";
				echo "<td>";

					if(!empty((int)$record->spd_specialgrddate)){	
					echo  $record->spd_specialgrddate;
					}
				echo "</td>";
					?>
        		    	<td>
				<?php if(!empty($record->spd_filename)):;?>
		                <a href="<?php echo base_url().'uploads/SIS/Promotional_Details/'.$record->spd_filename ; ?>"
                               target="_blank" type="application/octet-stream" download="<?php echo $record->spd_filename ?>">Download the pdf</a>
		            <?php endif;?>
			       </td>
<!--
				echo "<td>";

				echo "</td>";
                                <td>
                                    <?php 
                                //    $date1 = new DateTime($record->empsd_dojoin);
                                  //  $date2 = new DateTime($record->empsd_dorelev);
                                   // $diff = $date1->diff($date2);
                                   // echo "<b>&nbsp;&nbsp;".$diff->y . "&nbsp;&nbsp;&nbsp; " . $diff->m."&nbsp;&nbsp;&nbsp; ".$diff->d." ";
				?>
                                </td>
-->
                                <td>
                                <?php 
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
						echo anchor("empmgmt/edit_promotdata/{$record->spd_id}","Edit",array('title' => ' Edit Promotional Data' , 'class' => 'red-link'));
				}
				if($roleid == 1){
						echo " <br><br> ";
						echo anchor("empmgmt/delete_promotprofile/{$record->spd_id}","Delete",array('title' => ' Delete Promotional Data' , 'class' => 'red-link'));
				}
				?>
                                </td> 
                            </tr>
                        <?php }; ?>
                        <?php else : ?>
                            <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?>
                    </tbody>    
		</table>
		<?php //include 'other_profile_perticulars.php'; ?>
		<table style="width:100%;">
        	<tr>
        	<td align=right>
		<a href="#" onclick="goBack()"><img src='<?php echo base_url(); ?>uploads/icons/back1.png' title="Back"></a>
        	</td>
        	</tr>
    		</table>
	<br>
</td>
</tr>


</table>
       
   </div>      
 <p> &nbsp; </p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>

