<!--@name mapsubsem.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)  -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>Subject Semester Program with Department List</title>
    <head>    
       	<?php $this->load->view('template/header'); ?>
	<?php $this->load->view('template/menu');?> 
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script>
	function getbranchname(branch){
                var branch = branch;
                //alert(branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/setup2/branchlist",
                data: {"programname" : branch},
                dataType:"html",
                success: function(data){
                //alert(data);
                $('#subsem_prgid').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }
	</script>
    </head>    
    <body>
       
        <!--<//?php
           echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Map";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            
            echo anchor('map/viewscprgseat/', "View Study Center Program Seat ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Map Study Center Program Seat";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>-->
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
        <table width="100%"> 
        <div>    
            <?php echo form_error('<div class="isa_error">','</div>');?>
 
            <tr><td>  
                <div>    
                <?php echo anchor('map/subjectsemester/', "Subject Semester Program Department List ", array('title' => 'View Detail' , 'class' => 'top_parent'));
                echo "<td align=\"right\">";
		$help_uri = site_url()."/help/helpdoc#MapSubjectandSemester";
		echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
		?>
                </div>
                <div>
                 
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>

                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                <?php
                };
                ?>    
               
            </div>
        </td></tr>  
        </table>  
        <div>     
	<form action="<?php echo site_url('map/mapsubsem');?>" method="POST" class="form-inline">
        <table>
		<tr>
                <td>Department Name :</td>
                <td>
                <select name="subsem_deptid" id="subsem_deptid" class="my_dropdown" style="width:100%;" >
                <option value="" disabled selected >------Select Department Name--------------</option>
                <?php foreach($dept as $dataspt): ?>
                <option value="<?php echo $dataspt->dept_id ?>"><?php echo $dataspt->dept_name; ?></option>
                <?php endforeach; ?>
           	</td>
		</tr>

		<tr>
           	<td>Program Name :</td>
           	<td>
                <select name="programname" id="programname" class="my_dropdown" style="width:100%;" onchange="getbranchname(this.value)" >
                <option value="" disabled selected >------Select Program Name--------------</option>
                <?php foreach($prgresult as $dataspt): ?>
                <option value="<?php echo $dataspt->prg_name ?>"><?php echo $dataspt->prg_name; ?></option>
                <?php endforeach; ?>
           	</td>
		</tr>

        	<tr>
           	<td>Branch Name :</td>
           	<td>
                <select name="subsem_prgid" id="subsem_prgid" class="my_dropdown" style="width:100%;">
                <option value="" disabled selected >------Select Branch Name--------------</option>
           	</td>
		</tr>

		<tr>
                <td>Semester/Year :</td>
                <td>
                <select name="subsem_semester" id="subsem_semester" class="my_dropdown" style="width:100%;">
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
                <td>Subject Name :</td>
                <td>
                <select name="subsem_subid" id="subsem_subname" class="my_dropdown" style="width:100%;" >
                <option value="" disabled selected >------Select Subject Name--------------</option>
                <?php foreach($subres as $dataspt): ?>
                <option value="<?php echo $dataspt->sub_id ?>"><?php echo $dataspt->sub_name; ?></option>
                <?php endforeach; ?>
                </td>
                </tr>

            	<tr>
                <td> Subject Type:</td>
                <td>
                <select name="subsem_subtype"class="my_dropdown" style="width:100%;">
                <option value="" disabled selected>------Select Subject Type------</option>  
                <option value="Compulsory" class="dropdown-item">Compulsory</option>
                <option value="Elective" class="dropdown-item">Elective</option>
                </select>
                </td> 
		</tr>

            	<tr>
               	<td></td>
                <td colspan="2">   
                <button name="mapsubsem" >Submit</button>
                <button name="reset" >Clear</button>
                </td>
            	</tr>
        </form>    
        </table>
    </body> 
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
