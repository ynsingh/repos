

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
       <tr colspan="2"><td>
        <td>
            <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
        </td>      
       <div>
       <?php
       echo "<td align=\"center\" width=\"100%\">";
       echo "<b>List of UO</b>";
       echo "</td>";
       ?>
                
      
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
        <div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>Sr. No.</th>
                    <th>University Officer</th>
                    <th>UO Name</th>
                                      
                </tr>
            </thead>
            <tbody>
                <?php 
		$cid = 0;
                $did=0;
                               
		$i=1;
                if( count($allsc) ):  ?>
                    <?php foreach($allsc as $record){


		
			echo "<tr>";
			echo "<td>";
				echo $i;
			echo "</td>";
                        echo "<td>";
				if (!empty($record->ul_authuoid)){
                               echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $record->ul_authuoid)->name; 
			       echo " ( ";
                               echo $this->lgnmodel->get_listspfic1('authorities','code','id',$record->ul_authuoid)->code;
                               echo " ) ";
                              echo " ( ";
                                     echo $this->lgnmodel->get_listspfic1('authorities','priority','id',$record->ul_authuoid)->priority;
                                      echo " ) ";
				}
			echo "</td>";
			echo "<td>";
				
//				$empname=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$record->ul_empcode)->emp_name;
				if (!empty($record->ul_empcode)){
				echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$record->ul_empcode)->emp_name;
				}
				if (!empty($record->ul_userid)){ 
				echo " ( ";
				echo $this->lgnmodel->get_listspfic1('edrpuser','username','id',$record->ul_userid)->username;
				echo " ) ";
				}
                        echo "</td>";
			$i++;
                        echo "</tr>";
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
