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
<table> 
		<font size=4pt>
		<div style="margin-left -10px; width:190px;">
		<?php echo anchor('setup/seatsetting/', "Add seat Setting", array('title' => 'Add Detail' , 'class' => 'top_parent')) . " "; ?>
		</font>
		<tr colspan="2"><td>    
		<div  style="margin-left:30px;width:1600px;">
                <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning>','</div>');
                if(isset($_SESSION['success'])){
			echo "<div style=\"margin-left:30px;\" class=\"isa_success\">";
		        echo $_SESSION['success'];
			echo "</div>";
                }
		if(isset($_SESSION['err_message'])){
			echo "<div class=\"isa_error\">";
		        echo $_SESSION['err_message'];
			echo "</div>";
                }
                ?>
		</div>
		</td></tr>   
		</table>
		<?php
		echo"<div style=\"margin-left:30px;\">";
                echo "<table border=0 cellpadding=16  class=\"TFtable\">";
		echo "</td>";
                echo "<thead><tr align=\"\"><th>Sr. No.</th><th>University</th><th>Category</th><th>Pecentage(%)</th><th>Number of Seat</th><th>Action</th><th></th></tr></thead>";
				
		$count=0;
                    foreach ($this->srresult as $row)
                    {
	
 
		   ?>
					<td> <?php //echo ++$count; ?> </td>
	<?php
		
                    echo "<tr align='center'>";
				echo "<td>" . ++$count. "</td>";
				echo "<td>" . $this->common_model->get_listspfic1('org_profile','org_name','org_code',$row->org_code)->org_name. "</td>";
                    echo "<td>" . $this->common_model->get_listspfic1('category','cat_name','cat_id',$row->category_id)->cat_name."</td>";
                    echo "<td>" . $row->percentage . "</td>";
                    echo "<td>" . $row->noofseat . "</td>";
					echo "<td>";
                    echo  anchor('setup/delete_eseat/' . $row->id , "Delete", array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
					echo "&nbsp;";
					echo  anchor('setup/editseatsetting/' . $row->id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
                    echo "</tr>";
					echo "</td>";
                    }
					
                    echo "</table>";
					echo "</td>";
                    echo "</tr>";
                    echo "</table>";
        
                ?>
				
            </div>
        </tr>

    </table>
		
    </body>   
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>




