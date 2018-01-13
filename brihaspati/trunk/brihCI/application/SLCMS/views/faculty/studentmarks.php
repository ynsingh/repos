<!-- @author Sharad Singh(sharad23nov@gmail.com) -->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IGNTU:Student fees detail</title>
	<link rel="shortcut icon" href="<?php echo base_url('assets/images'); ?>/index.jpg">
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/message.css">
	<!--<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/studentNavbar.css">--->
	 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   

        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

<style type="text/css">
label{font-size:18px;}

tbody tr td{font-size:18px;}
thead tr th{color:white;font-weight:bold;font-size:18px;}

#text{background-color:#38B0DE;color:white;font-size:20px;font-weight:bold;opacity:1.5;height:30px;padding:5px;}
#form{ border:1px solid black;width:60%;}
</style>
<script>
	function getsemester(sem){
	        var sem = sem;
		//alert(sem);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/semester_get",
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
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/subject_get",
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
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/paper_get",
                data: {"prg_sem_sub" : prgsemsubid},
                dataType:"html",
                success: function(data){
                	$('#papername').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	</script>
<script>    $(document).ready(function(){
            
            $('.keyup-numeric').keyup(function() {
                var m11 = $('#m1').val();
                $('span.error-keyup-1').hide();
                var inputVal = $(this).val();
                var numericReg = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
                if (m11 == '')
                    $(this).after('<td><span class="error error-keyup-1"><font color="red">Please fill Max Marks first.</font></span></td>');   
                    //$('#m1')
                if(!numericReg.test(inputVal)) {
                    $(this).after('<td><span class="error error-keyup-1"><font color="red">Numeric integer only.</font></span></td>');
                }
                if(parseInt(inputVal) > parseInt(m11))
                {
                    $(this).after('<td><span class="error error-keyup-1"><font color="red">Marks should be less than Max Marks</font></span></td>');
                    
                }
            });
            
            $('.keyup-characters').keyup(function() {
                $('span.error-keyup-2').remove();
                var inputVal = $(this).val();
                var frm = $("frm").val();
                //alert (frm);
                var characterReg = /^\s*[a-zA-Z]+\s*$/;
                if(!characterReg.test(inputVal)) {
                    $(this).after('<td><span class="error error-keyup-2"><font color="red">Only Alphabate..</font></span></td>');
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
	
<?php           
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
/*if(!empty($subpap))
{
$sub_pap = explode('#',$subpap);
$sub = $sub_pap[0];
$pap = $sub_pap[1];
}*/

?>

<table width="100%">
            <tr>
		<?php
		    echo "<td align=\"left\" width=\"33%\">";
			echo anchor('faculty/studentviewmarks/', "View Student Marks" ,array('title' => 'Student Marks ' , 'class' => 'top_parent'));
                    echo "</td>";

                    echo "<td align=\"center\" width=\"34%\">";
			echo "<b>Marks Submission</b>";
                    
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


<!--<table style="width:100%;">
<tr><td align=left width="200">
<?php echo anchor('faculty/studentviewmarks/', "View Student Marks" ,array('title' => 'Student Marks ' , 'class' => 'top_parent'));?>
</td>
</tr>
</table>-->
	</br>
<form name="marksupload" id="marksupload" action="<?php echo site_url('Faculty/studentmarks');?>" method="POST" class="form-inline">
<!--<table class="TFtable">
<tr style="font-weight:bold; background-color:lightslategray;">
</table>
<table class="TFtable">
    <tr align="center" style="font-weight:bold; background-color:lightslategray;">
        <td width = "30%">Select Program :
            <select name="programname">
            --<select name="programname"  onchange="this.form.submit();">--
            <?php
                foreach ($unique1 as $key=> $value)
                {
                    echo $prg_id = $value;
            ?>
                    <option value= "<?php echo $prg_id;?>">
            <?php   echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prg_id)->prg_name."&nbsp;"."(". $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prg_id)->prg_branch.")";?>
                    </option>
<?php
                } 
?>
            </select>
        </td>
            
        <td width="10%">Semester :
            <select name="semester"> 
            <?php
                foreach ($unique3 as $key=> $value)
                {
                    $prg_sem = $value;
            ?>
                <option value= "<?php echo $prg_sem;?>">
                    <?php echo $prg_sem;?>
                </option>
            <?php }
            ?>
            </select>
        </td>

        <td  width="30%">Subject(Paper) :
            <select name="subjectpaper">
            <?php
                foreach($facprgsublist as $prgdata): ?>
                    <option value="<?php echo $prgdata->pstp_subid."#".$prgdata->pstp_papid; ?>"><?php
                     echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$prgdata->pstp_subid)->sub_name."(" .$this->commodel->get_listspfic1('subject_paper','subp_name','subp_id',$prgdata->pstp_papid)->subp_name.")";?>
                    </option>
            <?php endforeach; ?>
            </select>
        </td>
        <td  width="30%">
            <input type="submit" name="search" value="Search" />
            <input type="hidden" name="prgname" value="<?php //echo $selprg_name;?>">
        </td>
         </tr>
</table>       -------->
        <table style="width:100%;" >
	
            <tr style="font-weight:bold; background-color:lightslategray;">

                <td align=left> <span style="color:white;">Select Program :</span></br>
                    <select name="program_branch" id="program_branch" onchange="getsemester(this.value);" required>
			<option selected="true" disabled>Select Program</option>
			 <?php foreach($facprgsublist as $prgdata): ?>	
                            <option value="<?php echo $prgdata->pstp_prgid; ?>">
				<?php echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgdata->pstp_prgid)->prg_name."&nbsp;"."(".
                                $this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgdata->pstp_prgid)->prg_branch.")" ;?></option> 
 			<?php endforeach; ?>
		
		    </select>
                </td>
                <td align=left><span style="color:white;">Semester :</span></br>
			<select name="semester" id="semester" onchange="getsubject(this.value);" required>
				<option selected="true" disabled>semester</option>
		
		   	</select>		
                </td>
                <td align=left><span style="color:white;">Subject Name :</span></br>
                    <select name="subjectname" id="subjectname" onchange="getpaper(this.value);" required> 
                        <option selected="selected" disabled selected>subject name</option>
		
                    </select>
                </td>
		<td align=left><span style="color:white;">Subject Paper Name :</span></br>
                    <select name="papername" id="papername" onchange="" required> 
                        <option selected="selected" disabled selected>subject paper name</option>
		
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
        $stid = $studdetail->sp_smid;
    }
}
?>
<?php
if(! empty($studentdetail))
{
?>
<?php if($this->mst == 0)
{
?>
<table class="TFtable">
<tr align="center" style="font-weight:bold; background-color:lightslategray;">
<td width = "20%">
    Exam Type: &nbsp;
            <select name="examtype" required>
		<option selected="selected" disabled selected>Selecet Exam Type</option>
            <?php
                foreach($exmtype as $exm_type): ?>
                    <option value="<?php echo $exm_type->exty_id; ?>"><?php
                    echo $exm_type->exty_name;?>
                    </option>
            <?php endforeach; ?>
            </select>    
</td>

<td>
    Max Marks :<?php //echo "<input type=\"text\" name=\"mmarks\" value=\"\" />";?>&nbsp;
<input type="text" name="mmarks"  id="m1" size="5" class="keyup-numeric" value="<?php echo isset($_POST["mmarks"]) ? $_POST["mmarks"] : ''; ?>"/>
</td>
<!--<td>
    Academic Year :&nbsp;<?php //echo $acadyear;?>
</td>-->

</tr>
</table>

<!--<table style="margin-left:30px;" class="TFtable">-->
<table  class="TFtable">
<thead>    
	<tr>
		<b><th>Sr.No</th><th>Student Name </th><th>Roll No</th><th>Marks</th><th>Grade</th></b>
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
                            $fname=$this->commodel->get_listspfic1('student_master','sm_fname ','sm_id',$record->sp_smid)->sm_fname;
                            $mname=$this->commodel->get_listspfic1('student_master','sm_mname ','sm_id',$record->sp_smid)->sm_mname;
                            $lname=$this->commodel->get_listspfic1('student_master','sm_lname ','sm_id',$record->sp_smid)->sm_lname;
                            echo $fname." " .$mname." " .$lname;
                            echo "<input type=\"hidden\" name=\"stumid$i\" value=$record->sp_smid>"; 
                            ;?>
        </td>
        <td><?php 
                            
                            $Rfield=array('senex_rollno');

                            $Rstdata=array(
                                'senex_smid' =>$record->sp_smid,
                                'senex_prgid' =>$prgid,
                            );
                            $stud_rollno = $this->commodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata);
                            //print_r($stud_rollno);
                            if(!empty($stud_rollno[0]->senex_rollno)){
                                echo $stud_rollno[0]->senex_rollno;
                            }
                            else{
                                 $Rstdata2=array(
                                'senex_smid' =>$record->sp_smid,
                                'senex_prgid' =>$prgname,
                            );
                            $stud_rollno2 = $this->commodel->get_listspficemore('student_entry_exit',$Rfield,$Rstdata2);
                            //print_r(sizeof($stud_rollno));
                            if(!empty($stud_rollno2[0]->senex_rollno)){
                                echo $stud_rollno2[0]->senex_rollno;
                            }

                            }
                      ?>
                        
        </td>
      <!--  <td>
            <?php //echo "100";?>
        </td>-->
        <td>
            <?php
            ?>
            <input type="text" name="marks<?php echo $i;?>"  id="m2" size="5" class="keyup-numeric" value="<?php echo isset($_POST["marks".$i]) ? $_POST["marks".$i] : '0'; ?>" />
            <!--<input type="text" name="marks<?php //echo $i;?>"  $id="m2" size="5" class="keyup-numeric" value="" required="required"/>-->

        </td>
        <td>
            <?php
            ?>
            <input type="text" name="grade<?php echo $i;?>"  size="5" maxlength ="1" class="keyup-characters" value="<?php echo isset($_POST["grade".$i]) ? $_POST["grade".$i] : ''; ?>"/>
            <!--<input type="text" name="grade<?php //echo $i;?>"  size="5" maxlength ="1" class="keyup-characters" value="" required="required"/>-->

        </td>


    </tr>
    <?php  $i++;}
    }
        ?>
            
    <tr align="center">
    <td colspan="7">
        <!--<input type="submit" name="Submit" value="Marks Upload" />-->
        <input type="submit" name="Submit" value="Marks Upload" />
        <input type="hidden" name="studsize" value="<?php echo $i;?>">
        <input type="hidden" name="subid" value="<?php echo  $subid;?>">
        <input type="hidden" name="papid" value="<?php echo $papid;?>">
        <input type="hidden" name="mst" value="<?php echo $this->mst;?>">
        <input type="hidden" name="prgid1" value="<?php echo $prgid;?>">
        <input type="hidden" name="semester1" value="<?php echo $prgsem;?>">
    </td>
    </tr>

</thead>

</table>
<?php
}

}
else
{ 
    //$this->session->set_flashdata("textmsg", "Please select Program along with Semester and subject to view the student");
    //echo "Please select Program along with Semester and subject to view the student";
}?>
    <!--<div style="margin-left:30px;" class="isa_success"><?php //echo $_SESSION['textmsg'];?></div>-->
</form>

</br>

</center>

<?php $this->load->view('template/footer'); ?>

</body>
</html>
