<!--@name dispemailsetting.php
  @author Abhay(kumar.abhay.4187@gmail.com)
 -->
<html>
<head>    
	<?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu');?>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">    
</head>    
<body>  
<center>   
<table width="70%">
            <tr><td>
                <div>
                <?php  echo anchor('setup/seatsetting/', "Add seat Setting", array('title' => 'Add Seat Setting Detail','class' =>'top_parent'));
                ?>
                 <?php
                 $help_uri = site_url()."/help/helpdoc#ViewSeatSetting";
                 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
                 ?></div>
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
             </td></tr>
       </table></center>

		<?php
		echo"<div>";
                echo "<table border=0 cellpadding=16  class=\"TFtable\">";
                echo "<thead><tr><th>Sr. No.</th><th>University</th><th>Category</th><th>Pecentage(%)</th><th>Number of Seat</th><th>Action</th></tr></thead>";
		$count=0;
                    foreach ($this->srresult as $row)
                    {
                    echo "<tr align='center'>";
	            echo "<td>" . ++$count. "</td>";
		    echo "<td>" . $this->common_model->get_listspfic1('org_profile','org_name','org_code',$row->org_code)->org_name. "</td>";
                    echo "<td>" . $this->common_model->get_listspfic1('category','cat_name','cat_id',$row->category_id)->cat_name."</td>";
                    echo "<td>" . $row->percentage . "</td>";
                    echo "<td>" . $row->noofseat . "</td>";
		    echo "<td>";
                    //echo  anchor('setup/delete_eseat/' . $row->id , "Delete", array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
		    echo "&nbsp;";
		    echo  anchor('setup/editseatsetting/' . $row->id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
		    echo "</td>";
                    echo "</tr>";
                    }
                ?>
    	</table>
        </div>
    </body>   
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>




