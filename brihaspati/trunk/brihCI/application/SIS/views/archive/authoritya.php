<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name authoritya.php 
  @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<html>
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
                    $help_uri = site_url()."/help/helpdoc#AuthorityArchive";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;margin-left:35%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>

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
		<th>Authority Name(Authority Id)</th>
		<th>Authority Type</th>
		<th>User Name</th>
		<th>From Date</th>
		<th>Till Date</th>
		<!--<th>Archiver Name</th>
		<th>Archive date</th> -->
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

                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "16" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

