

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">  
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
          <script>
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                //alert("printContents==="+printContents);
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body style='width:100%;' ><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' style='width:100%;height:100px;' >"+" <div style='width:100%;height:100px;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
            }
        </script>     
       
    </head>
    <body>
    <?php $this->load->view('template/header'); ?>
    <table width="100%">
       <tr><td>
                                <?php
                                $uname=$this->session->userdata('username');
                  //              $rest = substr($uname, -21);
		//		if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
				if($uname == "admin"){
				echo anchor("upl/uploaddocumentlist","Upload Support Document ",array('title' => 'Upload Support Document' , 'class' => 'red-link'));
				}
                                ?>
        </td>      
       <div>
       <?php
       echo "<td align=\"center\" >";
       echo "<b>List of Uploaded Documents</b>";
       echo "</td>";
       ?>
        <td>
            <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
        </td>      
                
     </tr></table>
	<table width="100%"><tr><td width="100%"> 
        <div>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
         <?php echo form_error('<div class="isa_error">','</div>');?>
          <?php if(isset($_SESSION['success'])){?>
              <div class="isa_success"><?php echo $_SESSION['success'];?></div>
          <?php
          };
          ?>
        <?php if  (isset($_SESSION['err_message'])){?>
             <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
        <?php
        };
        ?>
        </div>

        </td></tr></table>
	<table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                        <tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>View Uploaded Document List</b></td>
                            <td align="right">
                            </td>
                        <tr>
                </table>

        <div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>Sr. No.</th>
                    <th>Profile Name</th>
                    <th>Sub Profile  Name</th>
                    <th>Category Name </th>
                    <th>Employee PF No.</th>
                    <th>File  Name</th>
                                      
                </tr>
            </thead>
            <tbody>
                <?php 
		$cid = 0;
                $did=0;
                $uoid=0;   
		$i=1;
                if( count($record) ):  ?>
                    <?php foreach($record as $rec){
						echo "<tr>";
						echo "<td>";
						echo $i;
						echo "</td>";
                        			echo "<td>";
						echo str_replace('_',' ',$rec->ud_proflname);
						echo "</td>";
                        			echo "<td>";
						echo str_replace('_',' ',$rec->ud_subproflnme);
						echo "</td>";
                        			echo "<td>";
						echo str_replace('_',' ',$rec->ud_degreename);
						echo "</td>";
                        			echo "<td>";
						echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$rec->ud_pfno)->emp_name;
						echo " ( ";
						echo $rec->ud_pfno;
						echo " ) ";
						echo "</td>";
                        			echo "<td>";
						$tmp = explode('.', $rec->ud_filename);
						$file_ext = end($tmp);
		
				//		if((strcasecmp($file_ext,"pdf" )) == 0){
							if(!empty($rec->ud_filename)){ ?>
                            				<a href="<?php echo base_url().$rec->ud_filelocation.'/'.$rec->ud_filename ; ?>"
                               			target="_blank" type="application/octet-stream" download="<?php echo $rec->ud_filename ?>"><?php echo str_replace('_',' ',$rec->ud_filename) ?></a>
                       				 <?php }
				//		}
						/*
						if((strcasecmp($file_ext,"png" )) == 0){
							if(!empty($rec->ud_filename)){ ?>
                            				<a href="<?php echo base_url().$rec->ud_filelocation.'/'.$rec->ud_filename ; ?>"
                               			target="_blank" type="image/png" download="<?php echo $rec->ud_filename ?>"><?php echo str_replace('_',' ',$rec->ud_filename) ?></a>
                       				 <?php }
						}
						*/
//						echo str_replace('_',' ',$rec->ud_filename);
						echo "</td>";
			//	$usrnme=$this->lgnmodel->get_listspfic1('edrpuser','username','id',$record->ul_userid)->username;
                    ?>
                <?php }; ?>
            <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
            <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>   
        </div><!------print div------>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>
