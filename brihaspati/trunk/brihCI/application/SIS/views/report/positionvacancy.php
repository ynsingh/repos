<!--@filename positionvacancy.php  @author Manorama Pal(palseema30@gmail.com) 
    @filename positionvacancy.php  @author Neha Khullar(nehukhullar@gmail.com)
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
       echo "<b>Vacancy Position</b>";
       echo "</td>";
       ?>
       
        </div>
        </td></tr></table>
        <div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>SS</th>
                    <th>P</th>
                    <th>V</th>
                    <th>Name of the Employee</th>
                    <th>Designation</th>
                    <th>DOR</th>
                   
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		$opid = 0;
                $poid1=0;$ss1=0;$sp1=0;$sv1=0;
                
               if( count($allpost) ):  ?>
                <?php foreach($allpost as $record){
              
                if($opid !=$record->sp_emppost){    
                    echo "<tr>";
                    echo "<td colspan=6 style=\"text-align:center;\">";
                    echo " <b> Post : ";
                    echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->sp_emppost)->desig_name;
                    echo "</b></td>";
                    echo "</tr>";
                   $poid=$record->sp_emppost;
                    }   
                $selectfield ="sp_uo, sp_dept,sp_schemecode,sp_sancstrenght , sp_position , sp_vacant";
                $whdata = array('sp_emppost' =>$record->sp_emppost);
                $whorder = "sp_uo  asc, sp_dept  asc";
                $alldata=$this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);
                foreach($alldata as $data){
                    echo "<tr>";
                    echo "<td colspan=6 style=\"text-align:center;\">";
                    echo " <b> UO CONTROL : ";
                    echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$data->sp_uo)->name;
                    echo " ( ".$this->lgnmodel->get_listspfic1('authorities','code','id' ,$data->sp_uo)->code." )";
                    echo "</b></td>";
                    echo "</tr>";
                    echo "<tr><td colspan=6 align=left><b> Department : ";
                    echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$data->sp_dept)->dept_name;
                    echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$data->sp_dept)->dept_code ." )";
                    echo "</b></td></tr>";
                    echo "<tr><td colspan=6 align=left><b> Scheme Name : ";
                    echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$data->sp_schemecode)->sd_name;
                    echo " ( ". $this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$data->sp_schemecode)->sd_code ." )";
                    echo "</b></td></tr>";
                    echo "<tr>";
                    echo "<td>". $data->sp_sancstrenght ." </td>";
                    echo "<td> $data->sp_position</td>";
                    echo "<td> $data->sp_vacant</td>";
                    echo "<td> </td>";
                    echo "<td> </td>";
                    echo "<td> </td>";
                    echo "</tr>";  
                     $opid1=$record->sp_emppost;
                    $selectfield ="emp_name,emp_post,emp_dor";
                    $whdata=array('emp_uocid' => $data->sp_uo,'emp_dept_code' =>$data->sp_dept,'emp_schemeid' =>$data->sp_schemecode,'emp_desig_code' =>$record->sp_emppost);
                    $whorder = "emp_name asc";
                    $this->dataemp = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
                    foreach($this->dataemp as $emp){
                        echo '<tr>';
                        echo "<td> </td>";
                        echo "<td> </td>";
                        echo "<td> </td>";
                        echo "<td>". $emp->emp_name ." </td>";
                        echo "<td>". $emp->emp_post ." </td>";
                        echo "<td>". $emp->emp_dor ." </td>";
                        echo '</tr>';
                    }//emp
                    $poid =$record->sp_emppost;
				$ss = $data->sp_sancstrenght;
				$sp = $data->sp_position;
				$sv = $data->sp_vacant;
				if ($poid == $poid1){
					$ss = $ss1 + $ss;
					$sp = $sp1 + $sp;
					$sv = $sv1 + $sv;
				}
				$poid1 = $poid;
				$ss1 = $ss;
				$sp1 = $sp;
				$sv1 = $sv;
                }// alldata
                
                 echo '<tr>';
                        echo "<td><b>".$ss."</b></td>";
                        echo "<td><b>".$sp."</b> </td>";
                        echo "<td><b>".$sv."</b></td>";
                        echo "<td><b>Total for  ". $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->sp_emppost)->desig_name."</b></td>";
                        echo "<td> </td>";
                        echo "<td> </td>";
                        echo '</tr>';
                             
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

