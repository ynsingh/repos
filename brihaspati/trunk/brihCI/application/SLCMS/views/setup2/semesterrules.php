<!--@name semester rule.php
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Semester Rule</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
   <?php //$this->load->view('template/menu');?>
  </head>
 <body>
    <!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
        echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/role/', "Add Role", array('title' => 'Add Detail'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>-->
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->

      <table width="100%">
            <tr><td>
                <div>
	     <?php
                echo "<td align=\"left\" width=\"33%\">";   
                echo anchor('setup2/addsemrule/', "Add Semester Rule", array('title' => 'Add Semseter Rule','class' =>'top_parent'));
                echo "</td>";
                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Semester Rule Details</b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#ViewSemesterRule";
		echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                  ?>
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
          </div>   
             </td></tr>
       </table>
<div class="scroller_sub_page">
<table>
<table cellpadding="0" class="TFtable" >
<thead><th>Sr.No</th><th>Program Name</th><th>Branch</th><th>Semester</th><th>Minimum Credit</th><th>Maximum Credit</th><th>Semester CPI</th><th>Action</th></tr></thead>

   <?php
        $count =0;
        if( count($result) ):
        foreach ($result as $row)
        {  
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo  $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->semcr_prgid)->prg_name ?></td>
            <td> <?php echo $sub1 = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->semcr_prgid)->prg_branch  ?></td>
            <td> <?php echo $row->semcr_semester ?></td>
            <td> <?php echo $row->semcr_mincredit ?></td>
            <td> <?php echo $row->semcr_maxcredit ?></td>
            <td> <?php echo $row->semcr_semcpi ?></td>
	    <td>
	<?php  
		//if($row->gm_id > 6){
//	    		echo anchor('setup2/deletesemrule/' . $row->semcr_id , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
	    		echo "&nbsp; ";
			echo anchor('setup2/editsemrule/' . $row->semcr_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
	//	}
            echo "</td>";
            echo "</tr>";
          
        }
        else :
        echo "<tr>";
            echo "<td colspan= \"8\" align=\"center\"> No Records found...!</td>";
        echo "</tr>";
        endif;

           ?>

</div>
</table>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>





