
<!--@filename desigemployeelist.php  @author Manorama Pal(palseema30@gmail.com) 
    @filename desigemployeelist.php  @author Neha Khullar(nehukhullar@gmail.com)
-->

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
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo'  style='width:100%;height:100px;'>"+" <div style='width:100%;height:100%;'>  " + printContents + "</div>"+"</body>";
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
       echo "<b>Designation Wise Teaching Staff List Details</b>";
       echo "</td>";
       ?>
       </div>
        </td></tr></table>
        <div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>Sr.No</th>
                   
                    <th>Employee Name</th>
                
                    <th>Department</th>
                   
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		$ouoid = 0;
		$odid = 0;
		
               if( count($records) ):  ?>
                    <?php foreach($records as $record){
//                      
			if($odid !=$record->emp_desig_code){
                        echo "<tr><td colspan=4 align=left><b> Designation : ";
			echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name;
			echo " ( ". $this->commodel->get_listspfic1('designation','desig_code','desig_id',$record->emp_desig_code)->desig_code ." )";
                        echo "</b></td></tr>";
			$odid = $record->emp_desig_code;
			$serial_no = 1;
                        }
			echo "<tr>";
			echo "<td>". $serial_no++ ." </td>";
			echo "<td> $record->emp_name</td>";
			echo "<td> ";
                        echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
			echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->emp_dept_code)->dept_code ." )";
                        echo "</td>";
?>
                        </tr>
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

