<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>View Addional Assignment Profile</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
<?php $current="addional"; ?>

        <script>
      
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' align='left' style='width:70%;height:100px;'>"+" <div style='width:70%;height:100%;'> " + printContents + "</div>"+"</body>";
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
 
<tr>
	<?php
        include  'ptab.php';
?>
<td valign="top">		
		<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                        <tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Addional Assignment Particulars</b></td>
                            <td align="right">
                                <?php
					$uname=$this->session->userdata('username');
                                	$rest = substr($uname, -21);
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                      echo anchor("empmgmt/add_addionalassigndata/{$emp_id}"," Add ",array('title' => ' Add Addional Assignment Data' , 'class' => 'red-link'));
                                }
                                ?>

                            </td>
                        <tr>
                </table>
                <table class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Assignment Name</th>
                            <th>Assignment Place</th>
			    <th>From - To Date </th>
                            <th >Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <?php 
		//	print_r($addionaldata);
			if( !empty($addionaldata) ):  
                             	foreach($addionaldata as $record){
					echo "<tr>";
	                                echo "<td>";
        	                        if (!empty($record->aa_asigname)){
						if(substr($record->aa_asigname, 0, 7) === "Others,"){
							$rstr=substr($record->aa_asigname, 7, strlen($record->aa_asigname));
							echo $rstr;
						}else{
							echo $record->aa_asigname;
						}
					}
                                	echo "</td>";
                                	echo "<td>";
                                 	if (!empty($record->aa_place))
						echo $record->aa_place;
                                	echo "</td>";
                                	echo "<td>";
					if(!empty($record->aa_asigperiodfrom)){
						echo $record->aa_asigperiodfrom ." - ".$record->aa_asigperiodto;
//						echo date('d-m-Y',strtotime($record->aa_asigperiodfrom)) ." - ". date('d-m-Y',strtotime($record->aa_asigperiodto));
					}
                                	echo "</td>";
                                	echo "<td>";
					if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
//                                              echo anchor("empmgmt/edit_addionalassigndata/{$record->aa_id}","Edit",array('title' => ' Edit Addional Assignment Data' , 'class' => 'red-link'));
//						echo "<br><br>";
						echo anchor("empmgmt/delete_addionalassignprofile/{$record->aa_id}", "Delete",array('title' => ' Delete addional assignment Data' , 'class' => 'red-link'));
                                	}
                                	echo "</td>";
                            		echo "</tr>";
                         	} 
                         else : ?>
                         	<td colspan= "13" align="center"> No Records found...!</td>
                  <?php endif;?>
                    </tbody>
                </table>
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

