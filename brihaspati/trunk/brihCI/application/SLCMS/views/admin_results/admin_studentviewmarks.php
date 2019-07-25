<!-------------------------------------------------------
    -- @name admin_studentviewmarks.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>SLCMS:Student fees detail</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
       
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;height:30px;background-color:white;}


tbody tr td{font-size:18px;}
thead tr th{color:white;font-weight:bold;font-size:18px;}
select{width:100%;font-size:17px;}

#text{background-color:#38B0DE;color:white;font-size:20px;font-weight:bold;opacity:1.5;height:30px;padding:5px;}
#form{ border:1px solid black;width:60%;}
</style>
<script>
	function getsemester(sem){
	        var sem = sem;
		//alert(sem);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/admin_studentresult/semester_get",
                data: {"program_branch" : sem},
                dataType:"html",
                success: function(data){
                	$('#semester').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	function getsubject(combid){
		var prgid = $('#program_branch').val();
                var semid = $('#semester').val();
                var combid = prgid+","+semid;
		//alert(combid);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/admin_studentresult/subject_get",
                data: {"semprg" : combid},
                dataType:"html",
                success: function(data){
                	$('#subjectname').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	function getpaper(prgsemsubid){
	        var prgid = $('#program_branch').val();
                var semid = $('#semester').val();
		var subid = $('#subjectname').val();
                var prgsemsubid = prgid+","+semid+","+subid;
		//alert(prgsemsubid);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/admin_studentresult/papers_get",
                data: {"prg_sem_sub" : prgsemsubid},
                dataType:"html",
                success: function(data){
                	$('#papername').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	</script>
<script>   
 $(document).ready(function(){
            $('.keyup-numeric').keyup(function() {
                $('span.error-keyup-1').hide();
                var inputVal = $(this).val();
                var numericReg = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
                if(!numericReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-1"><font color="red">Numeric integer only.</font></span>');
                }
            });
            });
</script>

</head>
<body>


<div>
	<?php $this->load->view('template/header'); ?>
	
	<?php //$this->load->view('template/facultymenu'); ?>
</div>	
		<table width="100%">
            	<tr>
		<?php
		    echo "<td align=\"left\" width=\"33%\">";
			echo anchor('admin_studentresult/studentmarks/', "Add Student Marks" ,array('title' => 'Add Student Marks ' , 'class' => 'top_parent'));
                    echo "</td>";

                    echo "<td align=\"center\" width=\"34%\">";
			echo "<b>View Student Marks</b>";
                    
                    echo "</td>";

                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdocfaculty#StudentList";
                    //echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		?>
			 <span style="color:black;float:right;font-weight:bold;">Academic Year:&nbsp;&nbsp <?php echo $academicyear;?></span>
		<?php
                    echo "</td>";
		 echo "</tr>";echo "</table>";
                    ?>
</br>
	<?php 
	    if(!empty($_SESSION['success'])){	
		if(isset($_SESSION['success'])){?>
         <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?> 	
<?php /*          
$unique = array();    
$unique2 = array();
foreach($facprgsublist as $prgdata):
    $prdata = $prgdata->pstp_prgid;
    //$prgsem = $prgdata->pstp_sem;
$prgsem = $this->commodel->get_listspfic1('program_subject_teacher','pstp_sem','pstp_prgid',$prdata)->pstp_sem;
    $unique[] = $prdata;
    $unique2[] = $prgsem;
    
    
endforeach; 
$unique1 = array_unique($unique); 
$unique3 = array_unique($unique2);
 $etype;*/
?>


<form action="<?php echo site_url('admin_studentresult/studentsviewmarks');?>" method="POST" class="form-inline">


 <table style="width:100%;" >
	
            <tr style="font-weight:bold; background-color:lightslategray;">

                <td align=left> <span style="color:white;">Select Program :</span></br>
                    <select name="program_branch" id="program_branch" onchange="getsemester(this.value);">
			<option selected="true" disabled>Select Program</option>
			 <?php foreach($facprgsublist as $prgdata): ?>	
                            <option value="<?php echo $prgdata->sp_programid; ?>">
				<?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgdata->sp_programid)->prg_name."&nbsp;"."(".
                                $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgdata->sp_programid)->prg_branch.")" ;?></option> 
 			<?php endforeach; ?>
		    </select>
                </td>
                <td align=left><span style="color:white;">Semester :</span></br>
			<select name="semester" id="semester" onchange="getsubject(this.value);">
				<option selected="true" disabled>semester</option>
				<option value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>	
				<option value='5'>5</option>
				<option value='6'>6</option>
				<option value='7'>7</option>
				<option value='8'>8</option>	
		   	</select>		
                </td>
                <td align=left><span style="color:white;">Subject Name :</span></br>
                    <select name="subjectname" id="subjectname" onchange="getpaper(this.value);"> 
                        <option selected="selected" disabled selected>subject name</option>
			<?php 
			foreach($subject as $datas){
				$sub_id = $datas->smr_subid;
				$subname = $this->commodel->get_listspfic1('subject','sub_name','sub_id',$sub_id)->sub_name;

			?>
				 <option value="<?php echo $datas->smr_subid;?>"><?php echo $subname;?></option>
			<?php }?>
                    </select>
                </td>
		<td align=left><span style="color:white;">Subject Paper Name :</span></br>
                    <select name="papername" id="papername" onchange="" > 
                        <option selected="selected" disabled selected>subject paper name</option>
			<?php 
			foreach($subject as $datas){
				$sub_id = $datas->smr_papid;
				$papername = $this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$sub_id)->subp_name;

			?>
				 <option value="<?php echo $datas->smr_papid;?>"><?php echo $papername;?></option>
			<?php }?>	
                    </select>
                </td>
		<td width = "20%" align=left>
    <span style="color:white;" >Exam Type: &nbsp;</span>
            <select name="examtype">
		<option selected="selected" disabled selected>Select Exam Type</option>
            <?php
                foreach($exmtype as $exm_type): ?>
                    <option value="<?php echo $exm_type->exty_id; ?>"><?php
                    echo $exm_type->exty_name;?>
                    </option>
            <?php endforeach; ?>
            </select>
        </td>

		<td valign=bottom><input type="submit" name="search" value="Search" style="font-size:17px;" /></td>
            </tr>
	
	

        </table>  
<br>

<?php //print_r($studentdetail);?>
<?php
if(! empty($studentdetail))
{
    foreach($studentdetail as $studdetail)
    {
        $stid = $studdetail->smr_smid;
    }
}
?>

<table  class="TFtable">
<thead>    
	<tr>
		<b><th>Sr.No</th><th>Student Name </th><th>Roll No</th><th>Exam Type</th><th>Max Marks</th><th>Marks</th><th>Grade</th></b>
	</tr>
</thead>
    
     <?php $count=0; 
        $i=0;
        $smid=array();
     if(! empty($studentdetail)){
     foreach($studentdetail as $record){ ?>  
        <tr align="center">
        <td><?php echo ++$count; ?></td>
        <td><?php
                            $fname=$this->commodel->get_listspfic1('student_master','sm_fname ','sm_id',$record->smr_smid)->sm_fname;
                            $mname=$this->commodel->get_listspfic1('student_master','sm_mname ','sm_id',$record->smr_smid)->sm_mname;
                            $lname=$this->commodel->get_listspfic1('student_master','sm_lname ','sm_id',$record->smr_smid)->sm_lname;
                            echo $fname." " .$mname." " .$lname;
                            echo "<input type=\"hidden\" name=\"stumid$i\" value=$record->smr_smid/>"; 
                            ;?>
        </td>
        <td><?php 
                            
                            $Rfield=array('senex_rollno');

                            $Rstdata=array(
                                'senex_smid' =>$record->smr_smid,
                                'senex_prgid' =>$record->smr_prgid,
                            );
                            $stud_rollno = $this->commodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata);
                            //print_r($stud_rollno);
                            if(!empty($stud_rollno[0]->senex_rollno)){
                                echo $stud_rollno[0]->senex_rollno;
                            }
                            else{
                                 $Rstdata2=array(
                                'senex_smid' =>$record->smr_smid,
                                'senex_prgid' =>$record->smr_prgid,
                            );
                            $stud_rollno2 = $this->commodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata2);
                            //print_r(sizeof($stud_rollno));
                            if(!empty($stud_rollno2[0]->senex_rollno)){
                                echo $stud_rollno2[0]->senex_rollno;
                            }

                            }
                      ?>
                        
        </td>
                    <?php 
                    //$sub_pap = explode('#',$subpap);
                    //$studsub = $sub_pap[0];
                    //$studpap = $sub_pap[1];
                    /*$acadyear;
                    $prgsem;
                    $subpap;
                    $prgname;
                    $etype;
                    $studfield = array('smr_marks','smr_grade','smr_mmmarks');
                    $studwhere = array( 'smr_smid' => $record->smr_smid,
                                        'smr_prgid' => $prgname,
                                        'smr_subid' => $studsub,
                                        'smr_papid' =>$studpap,
                                        'smr_sem' => $prgsem,
                                        'smr_extyid' => $etype,
                    );
                    $studsubpap = $this->commodel->get_listspficemore('student_marks',$studfield,$studwhere);                 
                    if(!empty($studsubpap))
                    {*/
                    ?>
	 <td>
                    
                    <?php //echo "1";echo $studsubpap[0]->smr_mmmarks; 
			echo $this->commodel->get_listspfic1('examtype','exty_name ','exty_id',$record->smr_extyid)->exty_name;
		?>
        </td>	
        <td>
                    
                    <?php echo $record->smr_mmmarks;//echo "1";echo $studsubpap[0]->smr_mmmarks; 
                    ?>
        </td>


        <td>
                    <?php echo $record->smr_marks;//echo $studsubpap[0]->smr_marks; ?>
        </td>
        <td>
                    <?php echo $record->smr_grade;//$studsubpap[0]->smr_grade; 
                    ?>
        </td>
                    <?php //} else {?>
                   <!-- <td>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>-->
                    <?php //}?>


    </tr>
    <?php  $i++;}
    }
        //echo $prg_id;
        ?>
            
   <!-- <tr align="center">
    <td colspan="7">
        <input type="hidden" name="studsize" value="<?php //echo $i;?>">
        <input type="hidden" name="subid" value="<?php //echo $prgdata->pstp_subid;?>">
        <input type="hidden" name="papid" value="<?php //echo $prgdata->pstp_papid;?>">
        
    </td>
    </tr>-->

</thead>

</table>
</form>

</br>

<!--</center>-->

<?php $this->load->view('template/footer'); ?>

</body>
</html>
