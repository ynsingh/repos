
<!--@filename subprg_list.php @author Manorama Pal(palseema30@gmail.com)
-->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>
        <title>Welcome to IGNTU</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
       
    </head>
    <body>
        <div>
         <?php $this->load->view('template/header'); ?>
        <!--<h3>Welcome <?//= $this->session->userdata('username') ?></h3>-->
        <?php //$this->load->view('template/facultymenu');?>
        </div>
       <!-- <div>-->
<table width="100%">
            <tr>
<?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";

                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Subject List</b>";
                    echo "</td>";

                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdocfaculty#SubjectListwithProgram";
                    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
            ?>
</tr>
</table>
<div class="scroller_sub_page">
        <table class="TFtable">
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Category</th>
                    <th>Department</th>
                    <th>Program</th>
                    <th>Academic Year</th>
                    <th>Semester</th>
                    <th>Subject</th>
                    <th>Paper</th>
                </tr>
            </thead>
            <tbody>
                <?php if(count($this->cdetail)): 
                $count=$this->uri->segment(3, 0);   
                foreach($this->cdetail as $row){ ?>
                <tr> 
                        <td><?php echo ++$count; ?></td> 
                        <td><?php echo $this->cmodel->get_listspfic1('program','prg_category ','prg_id',$row->pstp_prgid)->prg_category;?></td>
                        <td><?php 
                            $prgdeptid=$this->cmodel->get_listspfic1('program','prg_deptid','prg_id',$row->pstp_prgid)->prg_deptid;
                            //echo $this->cmodel->get_listspfic1('program','prg_deptid','prg_id',$row->pstp_prgid)->prg_deptid;
                            echo $this->cmodel->get_listspfic1('Department','dept_name','dept_id',$prgdeptid)->dept_name;
                            ?>
                        </td>
                        <td><?php echo $this->cmodel->get_listspfic1('program','prg_name ','prg_id',$row->pstp_prgid)->prg_name ;?></td>
                        <td><?php echo $row->pstp_acadyear;?></td>
                        <td><?php echo $row->pstp_sem;?></td>
                        <td><?php echo $this->cmodel->get_listspfic1('subject','sub_name','sub_id',$row->pstp_subid)->sub_name;?></td>
                        <td><?php echo $this->cmodel->get_listspfic1('subject_paper','subp_name','subp_id',$row->pstp_papid)->subp_name;?></td>
                    </tr>
                <?php };?>
                <?php else : ?>
                <tr><td colspan= "7" align="center"> No Records found...!</td></tr>
                <?php endif;?>

            </tbody>  
        </table>  
	</div><!------scroller div------>
        <?= $this->pagination->create_links();?>
        <div><?php $this->load->view('template/footer'); ?></div>    
    </body> 
</html>

