<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name semrulea.php 
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
        <table style="margin-left:10px;">
            <tr colspan="2"><td>
            <div  style="margin-left:-06px;width:1793px;">

                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

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
        <table cellpadding="16" style="margin-left:30px;" class="TFtable" >
            <thead>
                <tr align="center">
		<th>Sr.No</th>
		<th>Semester Rule Master Id</th>
		<th>Program Name</th>
		<th>Branch</th>
		<th>Semester</th>
		<th>Minimum Credit</th>
		<th>Maximum Credit</th>
		<th>Semester CPI</th>
		<!--<th>Amount</th>
		<th>From Date</th>
		<th>To Date</th>
		<th>Description</th> -->
		<th>Archiver Name</th>
		<th>Archive date</th>
                </tr>
            </thead>
            <tbody>
                    <?php $count =0?>
              	    <?php if( count($this->sraresult) ): ?>
                    <?php foreach($this->sraresult as $row){ ?>
                         <tr align="center">
			 <td> <?php echo ++$count; ?> </td>
			 <td> <?php echo $row->semcra_semcrid ?></td>
			 <?php  echo "<td>" . $this->common_model->get_listspfic1('program','prg_name','prg_id',$row->semcra_prgid)->prg_name. "</td>";?>
			 <?php  echo "<td>" . $this->common_model->get_listspfic1('program','prg_branch','prg_id',$row->semcra_prgid)->prg_branch. "</td>";?>
            		 <td> <?php echo $row->semcra_semester ?></td>
            		 <td> <?php echo $row->semcra_mincredit?></td>
           		 <td> <?php echo $row->semcra_maxcredit ?></td>
            		 <td> <?php echo $row->semcra_semcpi ?></td>
            <!--		 <td> <?php echo $row->fma_frmdate ?></td>
            		 <td> <?php echo $row->fma_todate ?></td>
         		 <td> <?php echo $row->fma_desc ?></td>-->
            		 <td> <?php echo $row->creatorid ?></td>
            		 <td> <?php echo $row->createdate ?></td>

                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "10" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
    </body>
<p> &nbsp; </p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

