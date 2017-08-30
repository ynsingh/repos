<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name listfac.php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
  @author Malvika Upadhyay (malvikaupadhyay644@gmail.com)

 -->
<html>
<title>View Faculty list</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
		    <?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#FacultyList";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;margin-left:34%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

        <!--table style="margin-left:10px;">
            <tr colspan="2"><td-->
             <!--?php
                 echo anchor('setup/faclist/', "Add Faculty list ",array('title' => 'faculty list Detail ' , 'class' => 'top_parent'));
                 $help_uri = site_url()."/help/helpdoc#ViewFacultylistwithHead";
                 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:71%\">Click for Help</b></a>";
                ?>
	<table-->
	    <table cellpadding="16" style="margin-left:30px;" class="TFtable" >
            <thead>
                <tr align="center">
                <th>Faculty Name</th>
                <th>Email Id</th>
                <th>Mobile</th>
                <th>Campus Name</th>
                <th>Department Name</th>
                <!-- <th></th>-->
                </tr>
                <?php
                        if( count($this->tresult) ):
                                foreach($this->tresult as $row){
   
                                        echo "<tr>";
					echo "<td align=\"center\">";
					echo $this->logmodel->get_listspfic1('userprofile','firstname','userid',$row->userid)->firstname;
					echo "&nbsp; ";
					echo $this->logmodel->get_listspfic1('userprofile','lastname','userid',$row->userid)->lastname;
					echo "</td>";
					echo " <td align=\"center\">";
					echo $this->logmodel->get_listspfic1('edrpuser','username','id',$row->userid)->username;
					echo " </td>";
					echo " <td align=\"center\"> ";
					echo $this->logmodel->get_listspfic1('userprofile','mobile','userid',$row->userid)->mobile;
					echo "</td>";
					echo " <td align=\"center\">";
					echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$row->scid)->sc_name;
					echo "</td>";
					echo " <td align=\"center\">";
				        echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->deptid)->dept_name;
					echo "</td>";
                                        echo "</tr>";
                                };
                        else :
                                echo "<td colspan=\"6\" align=\"center\"> No Records found...!</td>";
                        endif;

                ?>
            </thead>
        </table>
    </body>
</html>

