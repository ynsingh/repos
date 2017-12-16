<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name authoritya.php 
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
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
            echo "<b>Authority Archive Details</b>";
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
		<th>Authority Name(Authority Id)</th>
		<th>Authority Type</th>
		<th>User Name</th>
		<th>From Date</th>
		<th>Till Date</th>
	        <th>Archiver Name</th>
		<th>Archive date</th> 
                </tr>
            </thead>
	    <tbody>

                    <?php $count =0?>
              	    <?php if( count($this->authresult) ): ?>
                    <?php foreach($this->authresult as $row){ ?>
                         <tr align="center">
			 <td> <?php echo ++$count; ?> </td>
<?php  echo "<td>" . $this->logmodel->get_listspfic1('authorities','name','id',$row->authority_id)->name;
			echo " ( ". $row->id ." ) ";
			echo "</td>";?>
			 <td> <?php echo $row->authority_type ?></td>
			<?php  echo "<td>";
			echo $this->logmodel->get_listspfic1('userprofile','firstname','userid',$row->user_id)->firstname ;
			echo " ";
			echo $this->logmodel->get_listspfic1('userprofile','lastname','userid',$row->user_id)->lastname ;
			 echo "</td>";?>
            		 <td> <?php echo $row->map_date ?></td>
           		 <td> <?php echo $row->till_date ?></td>
                         <td> <?php echo $row->creatorid ?></td>
                         <td> <?php echo $row->createdate ?></td>

                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "16" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table></div>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

