
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>                                                    
<html>
<title>View admission Open</title>

  <head>
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   	<?php $this->load->view('template/header'); ?>
   	 <?php $this->load->view('template/menu');?>
</head>
<body>
<div style="margin-top:50px;"></div>
<p>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</p>

<center>
	<table width="100%">
            <tr><td>
                <div align="left">
                <?php  echo anchor('enterence/addadmissionopen/', "Add Admission Open", array('title' => 'Add Admission Open','class' =>'top_parent'));
                ?>
                 <?php
                 $help_uri = site_url()."/help/helpdoc#viewadmissionopen";
                 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
                 ?>
                <div  style="width:90%;margin-left:2%">
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
       </table></center></br>
<div class="scroller_sub_page">
<table>
<tr>
<div>

<table cellpadding="16" class="TFtable" >
<tr align="center">
<thead><th>Sr.No</th><th>Academic Year</th><th>Program Category</th><th>Program Name </th><th>Entrance Exam Fees </th> <th>Minimum Qualification </th><th>Entrance Exam Pattern</th><th>Entrance Exam Date</th><th>Start Date Of Online Application </th><th>Last Date Of Online Application</th><th>Last Late Of Application Received</th><th>Action</th></tr></thead>
<?php
        $count =0;
        if( count($this->result) ):
        foreach ($this->result as $row)
        {
         ?>
             <tr align="center">
            <td> <?php echo ++$count; ?> </td>
	    <td> <?php echo $row->admop_acadyear;?></td>
       	    <td> <?php 
			echo $row->admop_prgcat; ?>
	    </td>
	    <td> <?php 
			echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->admop_prgname_branch)->prg_name ;
			echo "(";
			echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->admop_prgname_branch)->prg_branch ;
			echo ")";
		?>
	    </td>
	    <td> <?php echo $row->admop_entexam_fees?></td>
            <td> <?php echo $row->admop_min_qual ?></td>
	    <td> <?php echo $row->admop_entexam_patt ?></td>
	    <td> <?php echo $row->admop_entexam_date ?></td>
            <td> <?php echo $row->admop_startdate ?></td>
	    <td> <?php echo $row->admop_lastdate ?></td>
  	    <td> <?php echo $row->admop_app_received ?></td>
            <td>

        <?php	echo anchor('enterence/editadmissionopen/' . $row->admop_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
       
            echo "</td>";
            echo "</tr>";
}
        
        else :
        echo "<tr>";
            echo "<td colspan= \"8\" align=\"center\"> No Records found...!</td>";
        echo "</tr>";
        endif;

        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";
           ?>

</div>
</tr>
</table>
</div><!------scroller div------>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



