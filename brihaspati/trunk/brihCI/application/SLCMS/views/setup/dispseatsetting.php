<!--@name dispemailsetting.php
  @author Abhay(kumar.abhay.4187@gmail.com)
 -->
<html>
<title>Display Seat Setting</title>
<head>    
	<?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
</head>    
<body>  
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>  
<table width="100%">
                <tr><td>
                <div>
                <?php  
                 echo "<td align=\"left\" width=\"33%\">";
                 echo anchor('setup/seatsetting/', "Add Seat Setting", array('title' => 'Add Seat Setting Detail','class' =>'top_parent'));
                 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b>Seat Setting Details</b>";
                 echo "</td>";
                 echo "<td align=\"right\" width=\"33%\">";
                 $help_uri = site_url()."/help/helpdoc#ViewSeatSetting";
                 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                 echo "</td>";
                 ?>
                </div>
                <div>
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
       </table>
      
	        <?php
                echo "<table border=0 class=\"TFtable\">";
                echo "<thead><th>Sr. No.</th><th>University</th><th>Category</th><th>Pecentage(%)</th><th>Number of Seat</th><th>Action</th></tr></thead>";
		$count=0;
                    foreach ($this->srresult as $row)
                    {
                    echo "<tr>";
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




