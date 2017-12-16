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
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->


                   <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "<table width='100%'>"; 
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Faculty List Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdoc#FacultyList";
                    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>
             <div class="scroller_sub_page"> 
	    <table cellpadding="0" class="TFtable" >
            <thead>
                <tr>
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
        </table></div>

 </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

