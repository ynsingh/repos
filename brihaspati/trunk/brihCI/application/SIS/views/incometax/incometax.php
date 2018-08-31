<!--@name addannouncement.php
    @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Income Tax</title>

 <head>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <?php $this->load->view('template/header'); ?>
     
 </head>
 <script type="text/javascript">
             function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                //alert("printContents==="+printContents);
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body style='width:100%;'><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' style='width:100%;height:100px;'>"+" <div style='width:100%;height:100%;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
            }

//check max value
$(function () {
   $( "#numberBox,#numberBox1,#numberBox2,#numberBox3,#numberBox4,#numberBox5" ).change(function() {
      var max = parseInt($(this).attr('max'));
      var min = parseInt($(this).attr('min'));
      if ($(this).val() > max)
      {
          $(this).val(max);
      }
      else if ($(this).val() < min)
      {
          $(this).val(min);
      }       
    }); 
});

        </script>

 <body>

     <table width="100%">
        <tr colspan="2"><td>
                <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['error'])){?>
                    <div class="isa_error"><?php echo $_SESSION['error'];?></div>
                <?php
                };
               ?>
              </div>
             </td></tr>
        </table>
<div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
<tr> <td colspan="8"><img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  align="left" onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" ></td></tr>
        <table width="100%">
                <tr><td colspan="9">
                    <HR COLOR="#6699FF" SIZE="3">
<tr>
                    <td><b>Employee Name :</b></td>
                    <td><?php echo $record->emp_name ;?></td>
		    <td><b>Employee PF No : </b></td>
                    <td><?php echo $record->emp_code;?></td>
	            <td><b>Designation :</b></td>
                    <td>
		   <?php
                   echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name;
			?>
                        </td>
                </tr>
		<tr>
                <td><b>Email Id:</b></td>
                <td><?php   echo $record->emp_secndemail; ?></td>
		<td><b>Department :</b></td>
                <td>
		<?php
                echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
		?>
                    </td>
		<td><b>Pan No :</b></td>
                    <td><?php echo $record->emp_pan_no;?></td>
	<tr><td colspan="9">
                    <HR COLOR="#6699FF" SIZE="3">
</tr></td>

<table id="table" cellspacing="1" border="2" width="100%">
<form id="myform" action="<?php echo site_url('incometax/incometax');?>" method="POST" >
<tbody>
<tr>
	<td><B>SI No.</B></td>
	<td><B>Sections</B></td>
	<td><B>Particulars</B></td>
	<td><B>Amount (Rs.)</B></td>
						</tr>
</tr>
<?php if(isset($empmaster)){
//print_r($empmaster );
//foreach( $empmaster as $row){
//echo $row;
?>
<tr>
<td>1.</td>
<td>U/S 80 C</td>
<td>PPF, ULIP, NSC, LIC, Tuition Fee, Others, Repayment of HBA 
(other than from IITK)(maximum limit up to Rs. 1,50,000/-).</td>
<td><input type='text' id="numberBox" min="0" max="150000" name="usm_80C" value="<?php echo  $empmaster->usm_80C; ?>"/></td>
</tr>
<tr>
<td>2.</td>
<td>U/S 80CCD(1-B)</td>
<td>An additional deduction up to Rs. 50,000/- in respect of amount paid in the NPS. </td>
<td><input type='text' id="numberBox1" min="0" max="50000" name="usm_80CCD" value="<?php echo  $empmaster->usm_80CCD; ?>"/></td>
</tr>
<tr>
<td>3.</td>
<td>U/S 80 D</td>
<td>Medical Insurance Premium (Max. Limit Rs. 25,000/-) (Rs. 30,000/- in case the person insured is a senior citizen) and Rs. 30,000 in respect of medical insurance premium for parents.</td>
<td><input type='text'id="numberBox2"  min="0" max="30000" name="usm_80D" value="<?php echo  $empmaster->usm_80D; ?>"/></td>
</tr>
<tr>
<td>4.</td>
<td>U/S 80 DD</td>
<td>Medical treatment of Handicapped dependents raised to Rs. 75,000/- (Rs. 1,25,000/- for severe disability).</td>
<td><input type='text' id="numberBox3" min="0" max="125000" name="usm_80DD" value="<?php echo  $empmaster->usm_80DD; ?>"/></td>
</tr>
<tr>
<td>5.</td>
<td>U/S 80 E</td>
<td>Interest on a loan taken for higher education (no limit).</td>
<td><input type='text' name="usm_80E" value="<?php echo  $empmaster->usm_80E; ?>" /></td>
</tr>
<tr>
<td>6.</td>
<td>U/S 80 G</td>
<td>Donation should be made only to specified Fund (Prime Minister’s National Relief Fund, Chief Minister’s Relief Fund or Lt. Governor’s Relief Fund etc). <b>Rebate for any other donation should be claimed directly.</b></td>
<td><input type='text'  name="usm_80G" value="<?php echo  $empmaster->usm_80G; ?>"/></td>
</tr>
<tr>
<td>7.</td>
<td>U/S 80 GGA</td>
<td>Donations to specified institutions / associations for Research or for Rural Development.</td>
<td><input type='text'  min="75000" max="125000" name="usm_80GGA" value="<?php echo  $empmaster->usm_80GGA; ?>"/></td>
</tr>
<tr>
<td>8.</td>
<td>U/S 80 U</td>
<td>Physical Disability (max. Rs.75,000/- for disability and Rs. 1,25,000/- for severe disability).</td>
<td><input type='text' min="0" max="150000" id="numberBox4" name="usm_80U" value="<?php echo  $empmaster->usm_80U; ?>"/></td>
</tr>
<tr>
<td>9.</td>
<td>U/S 24 B </td>
<td>Interest on Housing Loan (other than frrm IIT Kanpur) up to Rs. 2,00,000/- (max. limit).</td>
<td><input type='text' id="numberBox5" min="0" max="200000" name="usm_24B" value="<?php echo  $empmaster->usm_24B; ?>"/></td>
</tr>

<?php }else{?>
<tr>
<td>1.</td>
<td>U/S 80 C</td>
<td>PPF, ULIP, NSC, LIC, Tuition Fee, Others, Repayment of HBA 
(other than from IITK)(maximum limit up to Rs. 1,50,000/-).</td>
<td><input type='text' id="numberBox"  min="0" max="150000" name="usm_80C" /></td>
</tr>
<tr>
<td>2.</td>
<td>U/S 80CCD(1-B)</td>
<td>An additional deduction up to Rs. 50,000/- in respect of amount paid in the NPS. </td>
<td><input type='text' id="numberBox1" min="0" max="50000" name="usm_80CCD" /></td>
</tr>
<tr>
<td>3.</td>
<td>U/S 80 D</td>
<td>Medical Insurance Premium (Max. Limit Rs. 25,000/-) (Rs. 30,000/- in case the person insured is a senior citizen) and Rs. 30,000 in respect of medical insurance premium for parents.</td>
<td><input type='text' id="numberBox2" min="0" max="30000" name="usm_80D" /></td>
</tr>
<tr>
<td>4.</td>
<td>U/S 80 DD</td>
<td>Medical treatment of Handicapped dependents raised to Rs. 75,000/- (Rs. 1,25,000/- for severe disability).</td>
<td><input type='text' id="numberBox3" min="0" max="125000" name="usm_80DD" /></td>
</tr>
<tr>
<td>5.</td>
<td>U/S 80 E</td>
<td>Interest on a loan taken for higher education (no limit).</td>
<td><input type='text' name="usm_80E" /></td>
</tr>
<tr>
<td>6.</td>
<td>U/S 80 G</td>
<td>Donation should be made only to specified Fund (Prime Minister’s National Relief Fund, Chief Minister’s Relief Fund or Lt. Governor’s Relief Fund etc). <b>Rebate for any other donation should be claimed directly.</b></td>
<td><input type='text' name="usm_80G" /></td>
</tr>
<tr>
<td>7.</td>
<td>U/S 80 GGA</td>
<td>Donations to specified institutions / associations for Research or for Rural Development.</td>
<td><input type='text' name="usm_80GGA" /></td>
</tr>
<tr>
<td>8.</td>
<td>U/S 80 U</td>
<td>Physical Disability (max. Rs.75,000/- for disability and Rs. 1,25,000/- for severe disability).</td>
<td><input type='text' id="numberBox4" min="75000" max="125000" name="usm_80U" /></td>
</tr>
<tr>
<td>9.</td>
<td>U/S 24 B </td>
<td>Interest on Housing Loan (other than from IIT Kanpur) up to Rs. 2,00,000/- (max. limit).</td>
<td><input type='text' id="numberBox5" min="0" max="200000" name="usm_24B"/></td>
</tr>

</tbody>
<?php } ?>
</table>

<p align="justify"><font size="3">Note:<b>*Mandatory with photocopy of PAN card (duly self-certified) if not submitted earlier.</b><br><br>
<b>** Verified Income Certificate to be attached.</b></font>

<br><br>
<p align="justify"><font size="3">I hereby declare that the particulars given above are correct and complete. I may be allowed appropriate tax rebate while calculating my tax liability of FY 2017-18.<br><br>
The self-attested documentary proof for claiming the benefits of various savings/investments already made is/are attached herewith and for the savings/investments which are likely to be made, will be submitted before 30th November 2017, failing which tax may be recovered by nullifying the savings at applicable rates.</p></font>
<tr>
      <td></td>
      <td colspan="2">
      <button name="incometax" >Submit</button>
      <button name="reset" >Clear</button>
                            </td>
                        </tr>

</table>
 <?php $this->load->view('template/footer'); ?>
<!--/div-->
</div>
