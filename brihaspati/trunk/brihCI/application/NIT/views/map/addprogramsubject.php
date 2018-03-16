<!--
    @name add_program_subject_paper.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo "<head>";
echo "<title>".'Program Subject Add List'."</title>";
    $this->load->view('template/header');
  
//    $this->load->view('template/menu');
?>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
<!--<link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">-->
    <style>
    .abc{
        width:330px;
    }
    </style>
<script>
 function getdegreename(branch){
		var branch = branch;
		//alert (branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/degreelist",
		
                data: {"subjecttype" : branch},
                dataType:"html",
                success: function(data){
                $('#degree').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }
function getsubj(combid){
           // var sem = sem;
	        var sem = $('#subsem_semester').val();
                var degree = $('#degree').val();
                var combid = sem+","+degree;
		//alert(combid);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/subList",
                data: {"sem_degree" : combid},
                dataType:"html",
                success: function(data){
                $('#subid').html(data.replace(/^"|"$/g, ''));
                }
             });
        }

</script>

<?php
echo "</head>";
echo "<body>";?>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
<?php
/*
    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Map";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo anchor('map/programsubject/', "Map Program with Subject", array('title' => 'Add Detail' , 'class' => 'top_parent'));
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\">";
    echo "Program with Subject";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
    echo"</br>";
*/
?>

<div>
<table style="width:100%">
<tr>
        <div> 
        <?php 
          echo "<td align=\"left\" width=\"33%\">";
          echo anchor('map/programsubject/', " Subject Paper List" ,array('title' => 'Subject List' , 'class' => 'top_parent'));
          echo "</td>";

          echo "<td align=\"center\" width=\"34%\">";
          echo "<b>Add Subject Paper Details</b>";
          echo "</td>";

          echo "<td align=\"right\" width=\"33%\">";
          $help_uri = site_url()."/help/helpdoc#MapProgramwithSubjectandPaper";
          echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;\">Click for Help</b></a>";
          echo"</td>";
          ?>
           </div>
</tr>
           </table>
           <table width="100%">
          <tr><td>
    <div>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
        <?php
        };
        if(isset($_SESSION['error']))
        {
        ?> <div  class="isa_success">"<?php echo $_SESSION['error'];?> </div>
        <?php
        }
        ?>
    </div>
    </td></tr>
</table>
<?php
    echo "<div>";
    //echo "<div style=\"margin-left:10px;\">";

/* Form */

    $subtype = array('Under Graduate'=>'Under Graduate','Post Graduate'=>'Post Graduate','Research'=>'Research','Diploma Course'=>'Diploma Course');    
    $acadyear = array();
    for($i = 2016; $i < date("Y")+10; $i++)
    {
        $j=$i+1;
        $temp =  $i. "-" .$j;
        $acadyear[$temp ] = $temp;
    }

    echo "<table>";
    echo form_open('map/addprogramsubject');
?>
	<tr>
                <td><?php echo form_label('Department Name','department'); ?> </td>
                <td>
                <select name="subsem_deptid" id="subsem_deptid" class="my_dropdown" style="width:100%;" >
                <option value="" disabled selected >------Select Department Name--------------</option>
                <?php foreach($dept as $dataspt): ?>
                <option value="<?php echo $dataspt->dept_id;?>"><?php echo $dataspt->dept_name; ?></option>
                <?php endforeach; ?>
           	</td>
		</tr>
	
		<tr ><td>
        	<label for="text">Program Category</label>
		</td><td>
			<select name="subjecttype" id="pcategory" style="width:100%;" onchange="getdegreename(this.value)">
			<option selected="true" disabled="disabled" style="font-size:18px;">------Program Category------</option>
				<?php 
				    foreach($pcategory as $row){
			        ?>
					<option value="<?php echo $row->prgcat_id;?>"><?php echo $row->prgcat_name;?>
				<?php  }?>
	  		</select>
		
		</td>
		</tr>


		<tr ><td>
        	<label for="text">Degree</label>
		</td><td>
			<select name="degree" id="degree" style="width:100%;">
			<option selected="true" disabled="disabled" style="font-size:18px;">------Degree------</option>
	  		</select>
		
		</td>
		</tr>

<?php /*echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Category','subjecttype');
    echo "</td><td>";
    echo form_dropdown('subjecttype',$subtype,'','class="abc"');
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Degree','degree');
    echo "</td><td>";
    echo form_dropdown('degree',$program,'','class="abc"');
    //echo "</td><td>";
    //echo form_error('subjectcode');
    //echo"</td><td>"; echo "Optional";
    echo "</td></tr>";
    echo "</p>";*/
    ?>
    <tr>
                <td>
                <?php echo form_label('Semester/Year','semester'); ?> </td>
                <td>
                <select name="subsem_semester" id="subsem_semester" style="width:100%;"  onchange="getsubj(this.value)">
                <option value="" disabled selected >------Select Semester------</option>
                <option value="1" class="dropdown-item">1</option>
                <option value="2" class="dropdown-item">2</option>
                <option value="3" class="dropdown-item">3</option>
                <option value="4" class="dropdown-item">4</option>
                <option value="5" class="dropdown-item">5</option>
                <option value="6" class="dropdown-item">6</option>
                <option value="7" class="dropdown-item">7</option>
                <option value="8" class="dropdown-item">8</option>
                </select>
		</td>
		</tr>

 <tr>
                <td>
                <?php echo form_label('Subject','subject'); ?> </td>
                <td>
                <select name="subjectname" id="subid" style="width:100%;">
                <option value="" disabled selected >------Select Subject------</option>
                	<option value="" class="dropdown-item"></option>
                </select>
		</td>
		</tr>
<?php
/*
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Branch', 'prgbranch');
    echo "</td><td>";
    echo form_input($prgbranch);
    echo "</td><td>";
     echo"</td><td>";
    echo "Example : Physics, Computer Science & Engineering  ";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Name', 'subjectname');
    echo "</td><td>";
    echo form_dropdown('subjectname', $subject,'','class="abc"');
    echo "</td></tr>";
    echo "</p>"; */
   ?>
  

   <tr>
       <td>  <?php echo form_label('Subject Type','subtype'); ?></td>
       <td>
           <select name="subsem_subtype" class="my_dropdown" style="width:100%;">
           <option value="" disabled selected>------Select Subject Type------</option>  
           <option value="Compulsory" class="dropdown-item">Compulsory</option>
           <option value="Elective" class="dropdown-item">Elective</option>
           </select>
      </td> 
   </tr>

   <?php
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Academic Year','acadyear');
    echo "</td><td>";
    echo form_dropdown('acadyear',$acadyear,'','class="abc"');
    echo "</td>";
    echo"</td><td>"; //echo "Optional";
    echo "</tr>";
    echo "</p>";
        
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper No','subjectno');
    echo "</td><td>";
    echo form_input($subjectno,'class="text"');    
    echo "</td><td>";
    echo"</td><td>"; echo "Example : 1, Numeric values only ";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Name','papername');
    echo "</td><td>";
    echo form_input($papername,'class="text"');        
    echo "</td><td>";
    echo"</td><td>"; echo "Example : Physics";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Code','subjectcode');
    echo "</td><td>";
    echo form_input($subjectcode,'class="text"');
    echo "</td><td>";
    echo"</td><td>"; echo "Example : Phy01";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Short Name','subjectshrname');
    echo "</td><td>";
    echo form_input($subjectshrname,'class="text"');
    //echo "</td><td>";
    //echo"</td><td>"; echo "Optional";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Paper Description','subjectdesc');
    echo "</td><td>";
    echo form_input($subjectdesc,'class="text"');
    //echo "</td><td>";
    //echo"</td><td>"; echo "Optional";
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo "</td><td>";
    echo form_submit('submit', 'Submit');
    echo form_reset('reset','Clear');
    //echo form_button('submit', 'Submit');
    echo"</td></tr>";
    echo "</p>";

    echo form_close();
    echo "</table>";
/* Form */
    echo"</body>";
    echo "<div align=\"center\">";  
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>
