
<!--@filename professorlist.php  @author Manorama Pal(palseema30@gmail.com) 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
        <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
        <table width="100%"><tr colspan="2"><td>
            <?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b> List of Professors - ( Seniority on the basis of date of appt. as Prof )</b>";
                    echo "</td>";
            ?>
       
      
        </td></tr></table>
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    
                    <th>Sr.No</th>
                    <th>Employee Name</th>
                    <th>DOR</th>
                    <th>Discipline</th>
                    <th>Department</th>
                    <th>Date of joining <br/> as Prof.</th>
                    <th>Total service as Prof. <br/> as on (<?php echo date("Y/m/d");?>)<br/>YY/MM/DD</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
                    
                    if( count($emplist) ):  ?>
                        <?php    echo "<tr>";
                            echo "<td colspan=7>";
                            echo " <b> Designation : PROFESSOR";
                            echo "</b></td>";
                            echo "</tr>";
                         foreach($emplist as $record){
                            echo "<tr>";
                            echo "<td>".$serial_no++."</td>";
                            echo "<td>". $record-> emp_name." </td>";
                            echo "<td> $record->emp_dor</td>";
                            echo "<td>";
                            if(!empty($record->emp_specialisationid)){
                                echo  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$record->emp_specialisationid)->sub_name;
                                echo  "</td>";
                            }
                            echo "<td>";
                            echo  $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
                            echo  "</td>";
                            echo "<td> $record->emp_doj</td>";
                            $date1 = new DateTime($record->emp_doj);
                            $date2 = new DateTime();
                            $diff = $date1->diff($date2);
                            echo "<td> ".$diff->y . "&nbsp;&nbsp;&nbsp;&nbsp;" . $diff->m." &nbsp;&nbsp;&nbsp;&nbsp; ".$diff->d."</td>";
                            echo "</tr>"
                    
                        ?>
               
                        <?php }; ?>
                    <?php else : ?>
            <td colspan= "13" align="center"> No Records found...!</td>
                    <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>
        <div align="center">  <?php $this->load->view('template/footer');?></div>

    </body>
</html>


