<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displaycategory.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->
<html>
<head>    
    <?php $this->load->view('template/header'); ?>
    <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
    <?php $this->load->view('template/menu');?>
    <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">
</head>    
<body>

        <?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo anchor('setup/category/', "Add Category", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "View Category";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>

      <table>
            <tr colspan="2"><td>
                <div style="margin-left:30px;width:1700px;">
                    <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

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
  <table>
   <tr>  
     <div align="left" style="margin-left:30px">
		<?php
    
                    echo "<table style=\"padding: 8px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";

                    echo "<table border=0 cellpadding=13 style=\"padding: 8px 8px 8px 20px;\">";
                    echo "<thead><tr align=\"left\"><th>Sr. No</th><th> Category Name</th><th>Category Code</th><th>Category Short Name</th><th>Category Discription </th><th>Action</th><th></th></tr></thead>";
                    
		    $count = 0;
 
		    foreach ($this->result as $row)
                    {
                    ?>    
			<tr>
                        <td><?php echo ++$count; ?> </td>
                        <td><?php echo $row->cat_name ?> </td>
                        <td><?php echo $row->cat_code ?> </td>
                        <td><?php echo $row->cat_short ?></td>
                        <td><?php echo $row->cat_desc ?> </td>
                 <?php  echo "<td>" . anchor('setup/deletecategory/' . $row->cat_id , "Delete", array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this category record... ')")) . " ";
                        echo "<td>" . anchor('setup/editcategory/' . $row->cat_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
                        echo "</br>";
                        echo "</tr>";
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
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
