
<!--@filename desigemployeelist.php  @author Manorama Pal(palseema30@gmail.com) 
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
                    echo "<b>Designation Wise Teaching Staff List Details</b>";
                    echo "</td>";
            ?>
       
      
        </td></tr></table>
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
        <div align="center">  <?php $this->load->view('template/footer');?></div>

    </body>
</html>

