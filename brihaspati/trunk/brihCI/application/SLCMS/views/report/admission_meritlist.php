<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name admission_meritlist.php
  @author Sumit Saxena(sumitsesaxena@gmail.com)
 -->

<html>
<title>View Admission merit list</title>
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
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;margin-left:39%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>
        </table>
           <table cellpadding="16" style="margin-left:30px;" class="TFtable">
            <thead>
                <tr align="center"> 
                	<th>Sr No.</th>
               	 	<th>Application Number</th>
                	<th>Enterance Exam Name</th>
                	<th>Enterance Exam Roll No.</th>
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
                                echo "<td colspan=\"6\" align=\"center\"> No Records found...!</td>";
                        endif;

                ?>
		
            </tbody>
        </table>

 </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>

