<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displaysociety.php 
  @author Neha Khullar(nehukhullar@gmail.com)
 -->

<html>
<title>View Society</title>
<head>    
    <?php $this->load->view('template/header'); ?>
    
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
</head>
 <body>

<table width='100%'>
     <tr colspan="2"><td>
         <?php
            echo "<td align=\"left\" width=\"33%\">";
            echo anchor('setup/addsociety/', 'Add Society Master', array('class' => 'top_parent'));
            echo "</td>";
            ?>
            <?php
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Society Details</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
            $help_uri = site_url()."/help/helpdoc#ViewSocietyDetail";
//            echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
            echo "</td>";
         ?>

        <div align="left" style="margin-left:0%;width:90%;">

          <?php
//		print_r($this->session->userdata());
		 echo validation_errors('<div class="isa_warning">','</div>');?>
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
        </div> </td>
    </tr>
  </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
        <thead>
        <tr>
        <th>Sr. No</th>
        <th> Society Details</th> 
	<th> Society Contact </th>
        <th> Society Other Info </th>
	<th> Society Bank Detail </th>
        <th> Society Remark </th>
        <th> Action </th> 
        </thead>
        <tbody>
             <?php
                $count = 0;
                foreach ($this->result as $row)
                {
              ?>
                <tr>
                    <td><?php echo ++$count; ?> </td>
                    <td><?php echo "<b>Name :</b> ".$row->soc_sname ;
				echo "<br><b>Code : </b>".$row->soc_scode;
				echo "<br><b>Registartion No : </b>".$row->soc_regno;
				echo "<br><b>Registration Date :</b> ".$row->soc_regdate;
				
?></td>
                    <td><?php echo "<b>Address :</b> ".$row->soc_address ;
			echo "<br><b>Phone :</b> ".$row->soc_phone;
			echo "<br><b>Mobile :</b> ".$row->soc_mobile;
			echo "<br><b>Email : </b>".$row->soc_email;
?> </td>
                    <td><?php echo "<b>PAN NO :</b> ".$row->soc_pan;
				echo "<br><b>TAN No :</b> ".$row->soc_tan;
			echo "<br><b>GST No :</b> ".$row->soc_gst;
 ?> </td>
                    <td><?php  echo "<b>Bank Name :</b> ".$row->soc_bname;
				echo "<br><b>Account No. :</b> ".$row->soc_bacno;
				echo "<br><b>IFSC COde : </b>".$row->soc_bifsc;
				echo "<br><b>MICR Code :</b> ".$row->soc_bmicr;
				echo "<br><b>Branch :</b> ".$row->soc_bbranch;
				echo "<br><b>A/C Type :</b> ".$row->soc_bactype;
 ?> </td> 
		    <td><?php echo $row->soc_remarks ?> </td>
		   <!-- <td><?php //echo $row->soc_creatordate ?> </td> -->
                     <?php 
			$roleid=$this->session->userdata('id_role');
			if($roleid == 1){ ?>

		 <td> <?php  echo anchor("setup/delete_soc/{$row->soc_id}","Delete",array('title' => 'Delete' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?> &nbsp;&nbsp; 
<?php //echo anchor('setup/editsociety/' . $row->soc_id  , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?>
               </td>
                <?php } else {
                        echo "<td> </td>";
                }?>
               </tr>
          <?php } ?>
        </tbody>
    </table>
   </div><!------scroller div------>
  </body>
<p>&nbsp;</p>
 <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>


