<!--@name savingmaster.php
@author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Add Saving Master</title>

 <head>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
     <?php $this->load->view('template/header'); ?>
 </head>
<script>
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
                <?php echo anchor('setup4/dispsavingmaster', "View Saving Master", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <!--?php
                 $help_uri = site_url()."/help/helpdoc#Role";
                 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:73%\">Click for Help</b></a>";
                 ?-->
                 </div>
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
<table>
<form id="myform" action="<?php echo site_url('setup4/savingmaster');?>" method="POST" >
<?php
echo "<tr>";
        echo "<td>";
        echo form_label('Financial Year', 'sm_fyear');
        echo "</td>";
        echo "<td>";
        echo "<select name=\"sm_fyear\" class=\"my_dropdown\" style=\"width:200%;\">";
	echo "<option id=\"dropdown\"></option>";
        for($i = 2016; $i < date("Y")+10; $i++){
        $j=$i+1;
        echo '<option value="'.$i.'-'.$j.'">'.$i.'-'.$j.'</option>';
        }
        echo " </select>";
        echo "</td>";
        echo "</tr>";
        ?>
<script>
function getCurrentFinancialYear() {
  var fiscalyear = "";
  var today = new Date();
  if ((today.getMonth() + 1) <= 3) {
    fiscalyear = (today.getFullYear() - 1) + "-" + today.getFullYear()
  } else {
    fiscalyear = today.getFullYear() + "-" + (today.getFullYear() + 1)
  }
  return fiscalyear
}

document.getElementById("dropdown").innerHTML=getCurrentFinancialYear();
</script>
<table id="table" cellspacing="1" border="2" width="100%">
<tbody>
<tr>
        <td><B>SI No.</B></td>
        <td><B>Sections</B></td>
        <td><B>Particulars</B></td>
        <td><B>Amount (Rs.)</B></td>
                                                </tr>
<td>1.</td>
<td>U/S 80 C</td>
<td>PPF, ULIP, NSC, LIC, Tuition Fee, Others, Repayment of HBA
(other than from IITK)(maximum limit up to Rs. 1,50,000/-).</td>
<td><input type='text' id="numberBox"  min="0" max="150000" name="sm_80C" value="150000"/></td>
</tr>
<tr>
<td>2.</td>
<td>U/S 80CCD(1-B)</td>
<td>An additional deduction up to Rs. 50,000/- in respect of amount paid in the NPS. </td>
<td><input type='text' id="numberBox1" min="0" max="50000" name="sm_80CCD" value="50000" /></td>
</tr>
<tr>
<td>3.</td>
<td>U/S 80 D</td>
<td>Medical Insurance Premium (Max. Limit Rs. 25,000/-) (Rs. 30,000/- in case the person insured is a senior citizen) and Rs. 30,000 in respect of medical insurance premium for parents.</td>
<td><input type='text' id="numberBox2" min="0" max="25000" name="sm_80D" value="25000" /></td>
</tr>
<tr>
<td>4.</td>
<td>U/S 80 DD</td>
<td>Medical treatment of Handicapped dependents raised to Rs. 75,000/- (Rs. 1,25,000/- for severe disability).</td>
<td><input type='text' id="numberBox3" min="0" max="125000" name="sm_80DD" value="125000" /></td>
</tr>
<tr>
<td>5.</td>
<td>U/S 80 E</td>
<td>Interest on a loan taken for higher education (no limit).</td>
<td><input type='text' name="sm_80E" /></td>
</tr>
<tr>
<td>6.</td>
<td>U/S 80 G</td>
<td>Donation should be made only to specified Fund (Prime Minister’s National Relief Fund, Chief Minister’s Relief Fund or Lt. Governor’s Relief Fund etc). <b>Rebate for any other donation should be claimed directly.</b></td>
<td><input type='text' name="sm_80G" /></td>
</tr>
<tr>
<td>7.</td>
<td>U/S 80 GGA</td>
<td>Donations to specified institutions / associations for Research or for Rural Development.</td>
<td><input type='text' name="sm_80GGA" /></td>
</tr>
<tr>
<td>8.</td>
<td>U/S 80 U</td>
<td>Physical Disability (max. Rs.75,000/- for disability and Rs. 1,25,000/- for severe disability).</td>
<td><input type='text' id="numberBox4" min="75000" max="125000" name="sm_80U" value="75000" /></td>
</tr>
<tr>
<td>9.</td>
<td>U/S 24 B </td>
<td>Interest on Housing Loan (other than from IIT Kanpur) up to Rs. 2,00,000/- (max. limit).</td>
<td><input type='text' id="numberBox5" min="0" max="200000" name="sm_24B" value="200000"/></td>
<tr>
<td>10.</td>
<td>Other</td>
<td>Any Other Verified Income</td>
<td><input type='text'  name="sm_other" ></td>
</tr>
</tbody></table>
<br><br>
<tr>
      <td></td>
      <td colspan="20">
      <button name="savingmaster" >Submit</button>
      <button name="reset" >Clear</button>
                            </td>
                        </tr>
</table>
 <?php $this->load->view('template/footer'); ?>
</div></form>
</body></html>
               
