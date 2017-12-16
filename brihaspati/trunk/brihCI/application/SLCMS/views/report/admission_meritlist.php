<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name admission_meritlist.php
  @author Sumit Saxena(sumitsesaxena@gmail.com)
 -->

<html>
<title>View Admission merit list</title>
    <head>    
         <?php $this->load->view('template/header'); ?>
	  <?php // $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body> 
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
   		<!--?php $help_uri = site_url()."/help/helpdoc#FacultyList";
                    echo "<table style=\" \" align=right>";
                    echo "<tr>";
                    echo "<td align=right>";
                    
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>
        </table-->
<table width="100%;">
            <tr>
<?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Admission Merit List Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdoc#FacultyList";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
echo "</tr>";
echo "</table>";
?>

           <table class="TFtable" >
            <thead>
                <tr align="center"> 
                	<th>Sr No.</th>
               	 	<th>Application Number</th>
                	<th>Entrance Exam Name</th>
                	<th>Entrance Exam Roll No.</th>
                	<th>Course Name</th>
                	<th>Branch Name</th>
			<th>Student Name</th>
			<th>Student Email-ID</th>
                	<th>Father Name</th>
                	<th>Marks</th>
                	<th>Admission Quota</th>
			<th>Category</th>
                	<th>Merit List No.</th>
			<th>Last Date of Admission</th>
                	<th>Admission Taken</th>
                 </tr>
		</thead>
		<tbody>
                <?php
			$count=1;
                         if( count($this->admission) ):
                                foreach($this->admission as $row){
                                        echo "<tr>";
                                        echo " <td align=\"center\">";
                                        echo $count++;
                                        echo "</td>";
					echo " <td align=\"center\">";
					echo $row->application_no;
                                        echo " </td>";
                                        echo " <td align=\"center\">";
                                        echo $row->entexamname;
                                        echo " </td>";
                                        echo " <td align=\"center\">";
                                        echo $row->entexamrollno;
                                        echo "</td>";
                                        echo " <td align=\"center\">";
                                        echo $row->course_name;
                                        echo "</td>";
                                        echo " <td align=\"center\">";
                                        echo $row->branchname;
                                        echo "</td>";
					echo " <td align=\"center\">";
                                       	echo $row->student_name;
                                        echo " </td>";
					echo " <td align=\"center\">";
                                        echo $row->student_email;
                                        echo " </td>";
					echo " <td align=\"center\">";
                                       	echo $row->father_name;
                                        echo " </td>";
					echo " <td align=\"center\">";
                                        echo $row->marks;
                                        echo " </td>";
					echo " <td align=\"center\">";
                                        echo $row->admission_quota;
                                        echo " </td>";
					echo " <td align=\"center\">";
                                        echo $row->category;
                                        echo " </td>";
					echo " <td align=\"center\">";
                                        echo $row->meritlist_no;
                                        echo " </td>";
					echo " <td align=\"center\">";
                                        echo $row->lastdate_admission;
                                        echo " </td>";
					echo " <td align=\"center\">";
                                        echo $row->admission_taken;
                                        echo " </td>";
                                        echo "</tr>";
                                };
                        else :
                                echo "<td colspan=\"16\" align=\"center\"> No Records found...!</td>";
                        endif;

                ?>
		
            </tbody>
        </table>
</br></br></br></br>
 </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

