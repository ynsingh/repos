<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name display program subject paper.php 
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
    @author Neha Khullar (nehukhullar@gmail.com)

 -->


    <html>
    <head>
    <title>Programme Subject Paper Archive</title>    
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
            echo "<b>Program Subject Paper Archive Details</b>";
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
		<!--<th>Subject Paper Id</th>-->
		<th>Program Category</th>
		<th>Department</th>
		<th>Program Name</th>
		<th>Academic Year</th>
		<th>Subject Name</th>
		<!--<th>Paper Number</th>-->
		<th>Paper Name</th>
		<th>Paper Code</th>
		<th>Paper Short Name</th>
		<th>Paper Description</th>
		<th>Archiver Name</th>
		<th>Archive Date</th>
                </tr>
            </thead>
            <tbody>
                    <?php $count =0?>
              	    <?php if( count($prgsubaresult) ): ?>
                    <?php foreach($prgsubaresult as $row){ ?>
                         <tr align="center">
			 <td> <?php echo ++$count; ?> </td>
			<!-- <td> <?php echo $row->subpa_subpid ?></td>-->
			 <td> <?php echo $row->subpa_prgcat ?></td>
            		 <td> <?php echo $this->common_model->get_listspfic1('Department','dept_name ','dept_id',$row->subpa_dept)->dept_name; ?></td>
                         <?php  echo "<td>" . $this->common_model->get_listspfic1('program','prg_name','prg_id',$row->subpa_degree)->prg_name ." ( ".$this->common_model->get_listspfic1('program','prg_branch','prg_id',$row->subpa_degree)->prg_branch ." )</td>";?>
           		 <td> <?php echo $row->subpa_acadyear ?></td>
			 <?php  echo "<td>" . $this->common_model->get_listspfic1('subject','sub_name','sub_id',$row->subpa_sub_id)->sub_name. "</td>";?>
            		 
             <td> <?php echo $row->subpa_name ?></td>
             <!--<td> <?php //echo $row->subpa_paperno ?></td>-->
         	 <td> <?php echo $row->subpa_code ?></td>
         	 <td> <?php echo $row->subpa_short ?></td>
        	 <td> <?php echo $row->subpa_desp ?></td>
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

