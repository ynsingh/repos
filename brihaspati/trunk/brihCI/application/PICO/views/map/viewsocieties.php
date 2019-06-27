<!--@filename viewsocieties.php  @author Vijay -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
            <?php $this->load->view('template/header'); ?>

        <table width="100%"><tr colspan="2">
        <?php
        echo "<td align=\"left\" width=\"33%\">";
        echo anchor('map/addsocieties', "Map Add Societies" ,array('title' => ' Add Detail ' , 'class' => 'top_parent'));
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Map Societies Details</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
        $help_uri = site_url()."/help/helpdoc#Societies";
        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
        echo "</td>";
        ?>
        <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['err_message'])) && (($_SESSION['err_message'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['err_message'];
                    echo "</div>";
                }
                ;?>
        </div>
      </tr>
        </table>
        <div class="scroller_sub_page">
        <table class="TFtable" >
             <thead>
                 <tr>
                 <th>Sr.No</th>
                 <th>Society Name</th>
                 <th>Society Head</th>
                 <th>Society Secretary</th>
                 <th>Society Treasurer</th>
                 <th>Society Members</th>
                 <th>Total Values</th>
                 <th>Action</th>
            </tr>
            </thead>
            <tbody>
<?php
                       $count =0;
                     if( count($this->result) ):
                     foreach($this->result as $row){ ?>
                     <tr>
                     <td> <?php echo ++$count; ?> </td>
	       	    <td><?php echo $this->sismodel->get_listspfic1('society_master_list','soc_sname','soc_id',$row->society_id)->soc_sname ?></td>
		    <td><?php echo $row->society_head ?> </td>
                    <td><?php echo $row->society_secretary ?> </td>
                    <td><?php echo $row->society_treasurer ?> </td>
                    <td><?php echo $row->society_members ?> </td>
		    <td><?php echo $row->society_totalvalues ?> </td>

                
                        <td> <?php //echo anchor ("map/editsocieties/{$row->id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
<td colspan= "10" align="center"> No Records found...!</td>
                <?php endif;?>
 </tbody>
        </table>
        </div><!------scroller div------>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
                                                                       




                               
