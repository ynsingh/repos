<html>
    <head>   
   <title> View Department</title> 
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
         
       </head>    
    <body>
        <?php
           echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
           echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
           echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo anchor('setup/dept/', "Add Department", array('title' => 'Add Detail' , 'class' => 'top_parent')) ." ";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "View Department";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>
        <table> 
            <tr colspan="2"><td>    
                <div  style="margin-left:30px;width:1200px;">
                <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning>','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
               	echo $this->session->flashdata('flash_data');
		echo $this->session->flashdata('error');
                ?>
            	</div>
        </td></tr>   
        <tr>
            <div align="left" style="margin-left:30px;">
  
                <?php
                    echo "<table border=0 cellpadding=13 style=\"padding: 8px 8px 8px 20px;\">";
                    echo "<thead><tr align=\"left\"><th>University code</th><th> Campus Code</th><th>School Code</th><th>School Name</th><th>Department Code</th><th>Department Name</th><th>Department Nick</th><th>Department Description</th><th>Action</th><th></th></tr></thead>";
                    foreach ($this->deptresult as $row)
                    {
                        echo "<tr>";
                        echo "<td>" . $row->dept_orgcode. "</td>";
			echo "<td>" . $row->dept_sccode . "</td>";
                        echo "<td>" . $row->dept_schoolcode. "</td>";
                        echo "<td>" . $row->dept_schoolname . "</td>";
                        echo "<td>" . $row->dept_code . "</td>";
                        echo "<td>" . $row->dept_name . "</td>";
                        echo "<td>" . $row->dept_short. "</td>";
                        echo "<td>" . $row->dept_description. "</td>";
                        
                        echo "<td>" . anchor('setup/deletedept/' . $row->dept_id , "Delete", array('title' => 'Delete Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
                        echo "<td>" . anchor('setup/editdepartment/' . $row->dept_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
                        echo "</br>";
                        echo "</tr>";
                    }
                    echo "</table>";
        
                ?>
            </div>
        </tr>
    </table>    
    </body>   
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
