<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name semrulea.php 
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
  @author Neha Khullar (nehukhullar@gmail.com)
 -->

       <html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php //$this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
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
            echo "<b>Semester Rule Archive Details</b>";
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
        <table class="TFtable" >
            <thead>
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
		<th>Archive Date</th>
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
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

