<!-------------------------------------------------------
    -- @name student_checklist.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
//if (isset($this->session->userdata['sm_id'])) {
//$id = ($this->session->userdata['sm_id']);
//$firstname = ($this->session->userdata['sm_fname']);
//$applino = ($this->session->userdata['sm_applicationno']);
?><!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Student crieteria</title>
		<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<!--<link rel="icon" href="<?php //echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png" >-->	
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<style type="text/css">
label{font-size:18px;}
input[type='text']{font-size:17px;width:120%;height:30px;}
input[type='button']{font-size:16px;}
#step2{border:1px solid black;width:70%;}
#firsttb tr td {padding:10px 0px 10px 0px;}
#secondtb tr td {padding:10px 0px 10px 20px;}
input[type="radio"] {
  visibility: hidden;
}
label {
  cursor: pointer;
}
input[type="radio"] + label:before {
  border: 1px solid #333;
  content: "\00a0";
  display: inline-block;
  font: 16px/1em sans-serif;
  height: 16px;
  margin: 0 .25em 0 0;
  padding: 0;
  vertical-align: top;
  width: 16px;
}
input[type="radio"]:checked + label:before {
  background: #fff;
  color: black;
  content: "\2713";
  text-align: center;
}
input[type="radio"]:checked + label:after {
  font-weight: bold;
}

input[type="radio"]:focus + label::before {
    outline: rgb(59, 153, 252) auto 5px;
}
</style>

</head>
<body>


<div>
	<div id="body">
	<?php $this->load->view('template/header2'); ?>
	<div class="welcome"><h2>Welcome : <?php echo $email?></h2></div>

	<?php $this->load->view('student/stuStepshead');?>
	
	<?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
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

	<center>
</br>

<form action='' method='POST'>
	<table style="width:100%;font-size:22px;" border=1>
		<table style="width:100%;font-size:22px;" border=1 id='firsttb'>
		<tr>
			<td style="text-align:center;"><b>List of Original/Duplicate certificates deposited during <?php //prg name?> Admission, SLCMS</b></td>
		</tr>
		<tr>
			<td style="padding:10px 0px 10px 20px;">Name of Student (BLOCK LETTERS):&nbsp;&nbsp;<?php echo strtoupper($name); ?></td>
		</tr>
		<tr>
			<td style="text-align:center;"><b>List of Collected Items (Please tick √ the appropriate box. Write any remarks next to the box)</b></td>
		</tr>
		</table>
		
		<table style="width:100%;font-size:22px;" border=1 id='secondtb'>
			<tr>
				<!--<td>Sr.No.</td>-->
				<td colspan=2></td>
				<td><b>Original</b></td>
				<td><b>Duplicate</b></td>
				<td><b>Remarks(If Any)</b></td>
			</tr>
			<tr>
				<td>1</td>
				<td>JEE (Main) Score Card
				<input type="hidden" name="stu_check1" style=" " value="JEE (Main) Score Card" readonly>
				</td>
				<td>
   					 <input type="radio" id="c1" name="cb1o" value='Original' class="example">
      					 <label for="c1"> </label>
				</td>
				<td>
   					 <input type="radio" id="c2" name="cb1d" value='Duplicate' class="example">
      					 <label for="c2"> </label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark1' autofocus style="width:80%;"></textarea></td>
						
			</tr>
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', true);  
});
</script>-->
			<tr>
				<td>2</td>
				<td>JEE (Main) Admit Card<input type="hidden" name="stu_check2" style=" " value="JEE (Main) Admit Card" readonly></td>
				<td>
   					 <input type="radio" id="c3" name="cb2" value='Original' class="example">
      					 <label for="c3"></label>
				</td>
				<td>
   					 <input type="radio" id="c4" name="cb2" value='Duplicate' class="example">
      					 <label for="c4"></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark2' autofocus style="width:80%;"></textarea></td>
						
			</tr>
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', true);  
});
</script>-->
			<tr>
				<td>3</td>
				<td>Date of birth proof (10 th standard/ matriculation or equivalent
certificate or marks sheet)<input type="hidden" name="stu_check3" style=" " value="DOB proof" readonly></td>
				<td>
   					 <input type="radio" id="c5" name="cb3" value='Original' class="example">
      					 <label for="c5"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c6" name="cb3" value='Duplicate' class="example">
      					 <label for="c6"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark3' autofocus style="width:80%;"></textarea></td>
						
			</tr>
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->

			</tr>

			<tr>
				<td>4</td>
				<td>10 th standard/ matriculation or equivalent certificate or marks sheet
				<input type="hidden" name="stu_check4" value="10th marksheet" readonly></td>
				<td>
   					 <input type="radio" id="c7" name="cb4" value='Original' class="example">
      					 <label for="c7"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c8" name="cb4" value='Duplicate' class="example">
      					 <label for="c8"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark4' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->

			<tr>
				<td>5</td>
				<td>Mark Sheet & Pass Certificate of the Qualifying Exam
				<input type="hidden" name="stu_check5" value="Mark Sheet & Pass Certificate of the Qualifying Exam" readonly></td>
				<td>
   					 <input type="radio" id="c9" name="cb5" value='Original' class="example">
      					 <label for="c9"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c10" name="cb5" value='Duplicate' class="example">
      					 <label for="c10"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark5' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->
	
			<tr>
				<td>6</td>
				<td>School Certificate/Transfer certificate from the institute last<input type="hidden" name="stu_check6" value="School Certificate/Transfer certificate" readonly>
attended</td>
				<td>
   					 <input type="radio" id="c11" name="cb6" value='Original' class="example">
      					 <label for="c11"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c12" name="cb6" value='Duplicate' class="example">
      					 <label for="c12"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark6' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->	

			<tr>
				<td>7</td>
				<td>Migration Certificate<input type="hidden" name="stu_check7" value="Migration Certificate" readonly></td>
				<td>
   					 <input type="radio" id="c13" name="cb7" value='Original' class="example">
      					 <label for="c13"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c14" name="cb7" value='Duplicate' class="example">
      					 <label for="c14"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark7' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->

			<tr>
				<td>8</td>
				<td>Character and Conduct certificate from the institute last attended
				<input type="hidden" name="stu_check8" value="Character and Conduct certificate" readonly></td>
				<td>
   					 <input type="radio" id="c15" name="cb8" value='Original' class="example">
      					 <label for="c15"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c16" name="cb8" value='Duplicate' class="example">
      					 <label for="c16"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark8' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>	-->

			<tr>
				<td>9</td>
				<td>Gap Certificate (applicable for candidates who have passed the</br>
qualifying exam in years prior to the current academic year)
				<input type="hidden" name="stu_check9" value="Gap Certificate" readonly></td>
				<td>
   					 <input type="radio" id="c17" name="cb9" value='Original' class="example">
      					 <label for="c17"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c18" name="cb9" value='Duplicate' class="example">
      					 <label for="c18"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark9' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->


			<tr>
				<td>10</td>
				<td>Community certificate in the form prescribed by Govt. of India and</br>
Issued by the competent authority in case of SC/ST candidates
				<input type="hidden" name="stu_check10" value="Community certificate in case of SC/ST candidates" readonly></td>
				<td>
   					 <input type="radio" id="c19" name="cb10" value='Original' class="example">
      					 <label for="c19"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c20" name="cb10" value='Duplicate' class="example">
      					 <label for="c20"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark10' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->	


			<tr>
				<td>11</td>
				<td>Community certificate in the case of OBC candidates from a</br>
competent authority indicating the status regarding creamy layer
				<input type="hidden" name="stu_check11" value="Community certificate in case of OBC candidates" readonly></td>
				<td>
   					 <input type="radio" id="c21" name="cb11" value='Original' class="example">
      					 <label for="c21"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c22" name="cb11" value='Duplicate' class="example">
      					 <label for="c22"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark11' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>	-->
			<tr>
				<td>12</td>
				<td>Certificate for persons with disabilities (PwD)
					<input type="hidden" name="stu_check12" value="Certificate for persons with disabilities (PwD)" readonly></td>
				<td>
   					 <input type="radio" id="c23" name="cb12" value='Original' class="example">
      					 <label for="c23"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c24" name="cb12" value='Duplicate' class="example">
      					 <label for="c24"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark12' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>	-->

			<tr>
				<td>13</td>
				<td>Medical Certificate [format given in Annexure 8 of JoSAA
business rules]
				<input type="hidden" name="stu_check13" value="Medical Certificate" readonly></td>
				<td>
   					 <input type="radio" id="c25" name="cb13" value='Original' class="example">
      					 <label for="c25"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c26" name="cb13" value='Duplicate' class="example">
      					 <label for="c26"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark13' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>	-->

			<tr>
				<td>14</td>
				<td>Family Annual Income Proof and Affidavit declaration+
				<input type="hidden" name="stu_check14" value="Family Annual Income Proof and Affidavit declaration+" readonly></td>
				<td>
   					 <input type="radio" id="c27" name="cb14" value='Original' class="example">
      					 <label for="c27"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c28" name="cb14" value='Duplicate' class="example">
      					 <label for="c28"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark14' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>	-->

			
		<tr>
				<td>15</td>
				<td>Three recent passport size photographs not older than six month
				<input type="hidden" name="stu_check15" value="Three recent passport size photographs" readonly></td>
				<td>
   					 <input type="radio" id="c29" name="cb15" value='Original' class="example">
      					 <label for="c29"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c30" name="cb15" value='Duplicate' class="example">
      					 <label for="c30"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark15' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>	-->
	
		<tr>
				<td>16</td>
				<td>Class XII performance check [format given in Annexure 7 (b) of
JoSAA business rules]
				<input type="hidden" name="stu_check16" value="Class XII performance check" readonly></td>
				<td>
   					 <input type="radio" id="c31" name="cb16" value='Original' class="example">
      					 <label for="c31"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c32" name="cb16" value='Duplicate' class="example">
      					 <label for="c32"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark16' autofocus style="width:80%;"></textarea></td>
						
			</tr>	
<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->
			<tr>
				<td>17</td>
				<td>Provisional Seat allotment letter
				<input type="hidden" name="stu_check17" value="Provisional Seat allotment letter" readonly></td>
				<td>
   					 <input type="radio" id="c33" name="cb17" value='Original' class="example">
      					 <label for="c33"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c34" name="cb17" value='Duplicate' class="example">
      					 <label for="c34"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark17' autofocus style="width:80%;"></textarea></td>
						
			</tr>	

<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->

			<tr>
				<td>18</td>
				<td>Photo identity card
				<input type="hidden" name="stu_check18" value="Photo identity card" readonly></td>
				<td>
   					 <input type="radio" id="c35" name="cb18" value='Original' class="example">
      					 <label for="c35"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c36" name="cb18" value='Duplicate' class="example">
      					 <label for="c36"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark18' autofocus style="width:80%;"></textarea></td>
						
			</tr>	

<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->

			<tr>
				<td>19</td>
				<td>Registration-cum-locked choices for seat allotment
				<input type="hidden" name="stu_check19" value="Registration-cum-locked choices for seat allotment" readonly></td>
				<td>
   					 <input type="radio" id="c37" name="cb19" value='Original' class="example">
      					 <label for="c37"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c38" name="cb19" value='Duplicate' class="example">
      					 <label for="c38"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark19' autofocus style="width:80%;"></textarea></td>
						
			</tr>	

<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->

			<tr>
				<td>20</td>
				<td>Document Verification-cum-Seat Acceptance Letter
				<input type="hidden" name="stu_check20" value="Document Verification-cum-Seat Acceptance Letter" readonly></td>
				<td>
   					 <input type="radio" id="c39" name="cb20" value='Original' class="example">
      					 <label for="c39"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c40" name="cb20" value='Duplicate' class="example">
      					 <label for="c40"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark20' autofocus style="width:80%;"></textarea></td>
						
			</tr>	

<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->

		
			<tr>
				<td>21</td>
				<td>Proof of fee payment to JoSAA
				<input type="hidden" name="stu_check21" value="Proof of fee payment to JoSAA" readonly></td>
				<td>
   					 <input type="radio" id="c41" name="cb21" value='Original' class="example">
      					 <label for="c41"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c42" name="cb21" value='Duplicate' class="example">
      					 <label for="c42"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark21' autofocus style="width:80%;"></textarea></td>
						
			</tr>	

<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->

			<tr>
				<td>22</td>
				<td>Any other item<input type="hidden" name="stu_check22" value="Any other item" readonly></td>
				<td>
   					 <input type="radio" id="c43" name="cb22" value='Original' class="example">
      					 <label for="c43"></label></label>
				</td>
				<td>
   					 <input type="radio" id="c44" name="cb22" value='Duplicate' class="example">
      					 <label for="c44"></label></label>
				</td>
				
				<td><textarea rows="1" cols="10" name='stu_remark22' autofocus style="width:80%;"></textarea></td>
						
			</tr>	

<!--<script>
$('input.example').on('change', function() {
    $('input.example').not(this).prop('checked', false);  
});
</script>-->


		</table>
	</table>
	</br>	

	<table style="width:15%;">
		<tr><td>
			<input type="submit" name="stu_doclist" value="Submit" style="width:100%;height:40px;font-size:22px;">
		</td></tr>
	</table>	
</form>	

</div>
<?php //$thisPage2="studentCrieteria2"; 
$this->load->view('template/footer'); ?>
	<!--<p class="footer">Page rendered in <strong>{elapsed_time}</strong> seconds. <?php echo  (ENVIRONMENT === 'development') ?  'CodeIgniter Version <strong>' . CI_VERSION . '</strong>' : '' ?></p>-->
</div>

<?php  //} //else {  header("location:student/studentForm"); }
//else{header("location:".base_url()."Student/Step0");}?>
</body>
</html>

