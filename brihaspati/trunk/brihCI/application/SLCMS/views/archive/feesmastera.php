<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displayfees.php 
  @author Vijay (vijay.pal428@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name authoritya.php 
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
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
            echo "<b>Fees Master Archive Details</b>";
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
		<th>Sr.No</th>
		<th>Fees master Id</th>
		<th>Program Name</th>
		<th>Academic Year</th>
		<th>Semester</th>
		<th>Category</th>
		<th>Gender</th>
		<th>Head</th>
		<th>Amount</th>
		<th>From Date</th>
		<th>To Date</th>
		<th>Description</th>
		<th>Archiver Name</th>
		<th>Archive date</th>
                </tr>
            </thead>
            <tbody>
                    <?php $count =0?>
              	    <?php if( count($this->fmaresult) ): ?>
                    <?php foreach($this->fmaresult as $row){ ?>
                         <tr align="center">
			 <td> <?php echo ++$count; ?> </td>
			 <td> <?php echo $row->fma_fmid ?></td>
			 <?php  echo "<td>" . $this->common_model->get_listspfic1('program','prg_name','prg_id',$row->fma_programid)->prg_name. "</td>";?>
			 <td> <?php echo $row->fma_acadyear ?></td>
            		 <td> <?php echo $row->fma_semester ?></td>
                         <?php  echo "<td>" . $this->common_model->get_listspfic1('category','cat_name','cat_id',$row->fma_category)->cat_name . "</td>";?>
            		 <td> <?php echo $row->fma_gender ?></td>
           		 <td> <?php echo $row->fma_head ?></td>
            		 <td> <?php echo $row->fma_amount ?></td>
            		 <td> <?php echo $row->fma_frmdate ?></td>
            		 <td> <?php echo $row->fma_todate ?></td>
         		 <td> <?php echo $row->fma_desc ?></td>
            		 <td> <?php echo $row->creatorid ?></td>
            		 <td> <?php echo $row->createdate ?></td>

                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "10" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table></div>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

