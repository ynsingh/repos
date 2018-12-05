<!--@filename employeelist.php  @author Manorama Pal(palseema30@gmail.com) 
    modification in gui - 16 OCT 2017 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>

    <body>
            <?php $this->load->view('template/header'); ?>
           
        <table width="100%"><tr>
        <?php 
        echo "<td align=\"left\" width=\"33%\">";
	$roleid=$this->session->userdata('id_role');
	$uname=$this->session->userdata('username');
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Backup Details</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	$help_uri = site_url()."/help/helpdoc#";
        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
        echo "</td>";
        ?>
	</tr></table>
        <div>     
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
        </div>
        <div class="scroller_sub_page">
	<table> 
	<tr>
<td>
<?php  echo anchor('backups/dbbackup', "Database Backup" ,array('title' => 'Database Backup' , 'class' => 'top_parent')); ?>
</td>
<td>
&nbsp;&nbsp;
</td>
<td>
<?php  echo anchor('backups/get_site_backup', "Site Backup" ,array('title' => 'Site Backup' , 'class' => 'top_parent')); ?>
</td>
</tr>
	</table>
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>File name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
              <?php $serial_no = 1;$i=0;
               if( count($record) ):
//			print_r($record); 
                     foreach($record as $key => $rec){ 
//				print_r( $rec);
				if(is_array($rec)):
				foreach($rec as $item){
			?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
				<td> <?php echo $item;      ?> </td>
			    <td>

				 <a href="<?php echo base_url().'backups/'.$key.$item ; ?>"
                               target="_blank" type="application/octet-stream" download="<?php echo $item ;?>">Download </a>

			    </td>
			</tr>
			<?php 
				}
			endif;
			} 
                 else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
		</tbody>
        </table>
        </div><!------scroller div------>
		<br><br>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   
