<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name dispdepartment.php  
  @author Neha Khullar (nehukhullar@gmail.com)
 -->

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php // $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
                    <!--?php
                    echo "<table>";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#AuthorityArchive";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;margin-left:56%;position:absolute;\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?-->

<table width="100%">
            <tr colspan="2"><td>
            <?php
            echo "<td align=\"center\" width=\"100%\">";
            echo "<b>Department Archive Details</b>";
            echo "</td>";
             ?>
                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

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

<div class="scroller_sub_page">
        <table  class="TFtable" >
            <thead>
                <th>University Name</th>
                <th>Campus Name</th>
                <th>Authorities Name</th>
                <th>School/Faculty Code</th>
                <th>School/Faculty Name</th>
                <th>Department Code</th>
                <th>Department Name</th>
                <th>Department Nick Name</th>
                <th>Department Description</th>
                <th>Archiver Name</th>
                <th>Archive Date</th>
                </tr>
            </thead>
            <tbody>
                    <?php $count =0?>
                    <?php foreach($this->deptaresult as $row) 
			{ 
		        echo "<tr align=\"center\">";
                        echo "<td>" . $this->common_model->get_listspfic1('org_profile','org_name','org_code',$row->depta_orgcode)->org_name. "</td>";
                        echo "<td>" . $this->common_model->get_listspfic1('study_center','sc_name','sc_code',$row->depta_sccode)->sc_name . "</td>";
                        echo "<td>";
                        if(!empty($this->logmodel->get_listspfic1('authorities','name','id',$row->depta_uoid)->name)){
                        echo  $this->logmodel->get_listspfic1('authorities','name','id',$row->depta_uoid)->name;}
                        echo "</td>";
                        echo "<td>" . $row->depta_schoolcode. "</td>";
                        echo "<td>" . $row->depta_schoolname . "</td>";
                        echo "<td>" . $row->depta_code . "</td>";
                        echo "<td>" . $row->depta_name . "</td>";
                        echo "<td>" . $row->depta_short. "</td>";
                        echo "<td>" . $row->depta_description. "</td>";
                        echo "<td>" . $row->creatorid. "</td>";
                        echo "<td>" . $row->createdate. "</td>";

                        //echo "<td>" . anchor('setup/deletedept/' . $row->dept_id , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
                       // echo "</br>";
                        echo "</tr>";
			}
			//echo "</table>";
			?>	
            </tbody>
          </table></div>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                     
