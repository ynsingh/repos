<!--@name subjectrecord.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
         <!-- <h1>Welcome <?//= $this->session->userdata('username') ?>  </h1>-->
          <?php //$this->load->view('template/stumenu'); ?>
		<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
	
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
<?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdocstudent#SubjectRecord";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;margin-left:39%;position:absolute;margin-top:-0%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>
         <table style="width:100%">
          <tr><td>
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
        </td></tr>
        </table>
        <br/>
      <!-- <div class="panel panel-primary"> -->
            <table cellpadding="16" style="margin-left:" class="TFtable">
            <thead >
            <tr align="center">
                <th>Program Name(Branch)</th>
                <th>Semester</th>
                <th>Academic Year</th>
                <th>Subject List</th>
                <th>Action</th>
            </tr>
        <?php
            foreach($this->result as $row){
                echo "<tr align=\"center\">";
                /*echo "<td>" . $row->sfee_spid ."</td>";*/
                echo "<td>";
                echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->sp_programid)->prg_name ;
                echo " ( ";
                echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->sp_programid)->prg_branch ;
                echo " ) ";
                echo "</td>";
                echo "<td>" . $row->sp_semester ."</td>";
		echo "<td>" . $row->sp_acadyear ."</td>";
		echo "<td>" . $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid1)->sub_name ;
		if($row->sp_subid2 != ""){
		echo ", ";  
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid2)->sub_name;
		}
		if($row->sp_subid3 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid3)->sub_name;
		}
		if($row->sp_subid4 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid4)->sub_name;
		}
		if($row->sp_subid5 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid5)->sub_name;
		}
		if($row->sp_subid6 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid6)->sub_name;
		}
		if($row->sp_subid7 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid7)->sub_name;
		}
		if($row->sp_subid8 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid8)->sub_name;
		}
		if($row->sp_subid9 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid9)->sub_name;
		}
		if($row->sp_subid10 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid10)->sub_name;
		}
//		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid2)->sub_name;
//		echo $row->sp_subid3 .','. $row->sp_subid4 .','. $row->sp_subid5 .','. $row->sp_subid6 .','. $row->sp_subid7 .','. $row->sp_subid8 .','. $row->sp_subid9 .','. $row->sp_subid10 ."</td>";
                echo "<td>" . "<a > </a>" ."</td>";
                echo "</tr>";
                }
        ?>
        </thead>
        <tbody>
         </tbody>
        </table>
  <!-- </div>  -->
 </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


