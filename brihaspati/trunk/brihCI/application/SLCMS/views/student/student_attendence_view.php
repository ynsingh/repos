<!--@name student_attendence_view.php  @author sumit saxena(sumitsesaxena@gmail.com)-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>  
	<title>Welcome to IGNTU :Attendence</title>  
       
          <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style>
            thead th{
               
                background-color: #DCDCDC;
              }
		.tag_color{
			color:red;
		}
		
       </style>
    </head>    
    <body>	
		 <?php $this->load->view('template/header'); ?>
          	<h2>Welcome <?= $this->session->userdata('username') ?>  </h2>
       		 <?php $this->load->view('template/facultymenu');?>
		<?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;width:90%;\">";
                    echo "<tr valign=\"top\">";
		    echo "<td>";
                    $help_uri = site_url()."/studenthome/student_attendence";
                    echo "<a href=$help_uri><b style=\"margin-left:8%;position:absolute;margin-top:1.6%\">Student Attendence</b></a>";
                    echo "</td>";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdocfaculty#StudentList";
                    echo "<a href=$help_uri><b style=\"float:right;margin-top:1.6%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                 ?>
         <!--<table style="margin-left:2%;width:100%"> 
          <tr><td> 
               <div  style="margin-left:2%; width:90%;" >
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
        </td></tr>  
        </table>  -->
        <br/>
      <!--  <a href="<?php echo site_url(); ?>/studentrecord/mypdf">pdf</a>-->
      <!-- <div class="panel panel-primary"> -->
            <table cellpadding="16" style="margin-left:2%;" class="TFtable">
            <thead >
            <tr align="center">
                <th>Sr. No.</th>
                <th>Student Name</th>
                <th>Study Center</th>
                <th>Department</th>
                <th>Academic Year</th>
                <th>Program</th>
                <th>Semester</th>
                <th>Subject</th> 
                <th>Paper</th>
		<th>Attendence</th>
		<th>Class Type</th>
		<th>Attendence Date</th>
            </tr>
	<?php $count=1;
	if( count($studatt) ):	
	    foreach($studatt as $row){
		//print_r($row);
            	echo "<tr align=\"center\">";
		echo "<td>".$count++ ."</td>";
                echo "<td>".$this->stuname = $this->commodel->get_listspfic1('student_master','sm_fname','sm_id',$row->satd_smid)->sm_fname."</td>";
		echo "<td>".$scname = $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$row->satd_scid)->sc_name."</td>";
		echo "<td>".$deptname = $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->satd_deptid)->dept_name."</td>";
		echo "<td>".$row->satd_acadyear."</td>";
		echo "<td>".$prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->satd_prgid)->prg_name."(".$branchname = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->satd_prgid)->prg_branch.")"."</td>";
		echo "<td>".$row->satd_sem."</td>";
		echo "<td>".$subname = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->satd_subid)->sub_name."</td>";
		echo "<td>".$papname = $this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$row->satd_papid)->subp_name."</td>";
		echo "<td>".$row->satd_astatus."</td>";
		echo "<td>".$row->satd_classtype."</td>";
		echo "<td>".$row->satd_adate."</td>";
	echo "</tr>";
		}
	?>
		<?php else : ?>
                <tr><td colspan= "12" align="center"> <b>No Records found...!</b></td></tr>
                <?php endif;?>
        </thead>
        <tbody>
         </tbody>
        </table> 
  <!-- </div>  -->     
    </body>  
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

