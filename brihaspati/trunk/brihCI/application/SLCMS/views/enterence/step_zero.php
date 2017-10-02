<?php defined('BASEPATH') OR exit('No direct script access allowed');
/*
    @name Enterence.php  
    @author Sharad Singh(sharad23nov@yahoo.com)
    
    This class basically responsible for complete registration process of applicant in a program
*/

    defined('BASEPATH') OR exit('No direct script access allowed');
?>
<html>
<head>

    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/stylecal.css">
    <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
    <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

    <script>
        $(document).ready(function(){
    
            $('.keyup-email').keyup(function() {
                $('span.error-keyup-7').remove();
                var inputVal = $(this).val();
                var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                if(!emailReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-7"><font color="red">Invalid email format.</font></span>');
                }
            });
        
            $('.keyup-numeric').keyup(function() {
                $('span.error-keyup-1').hide();
                var inputVal = $(this).val();
                //var numericReg = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
                var numericReg = /^\d*[0-9](|\d*[0-9]|,\d*[0-9])?$/;
                if(!numericReg.test(inputVal)) {
                    $(this).after('<span class="error error-keyup-1"><font color="red">Numeric integer only.</font></span>');
                }
            });


        });
    </script> 
<?php
    $this->load->view('template/header'); ?>

<?php //echo $prg_name;
//echo $msg;
?>


<?php
    $msgflag;
    $prg_name;
    $programdata = $this->commodel->get_listrow('program','prg_id',$prg_name);
    $programname = $programdata->row()->prg_name;//."(".$programdata->row()->prg_branch.")";
    $programbranch = $programdata->row()->prg_branch;
    $programname = $programname."(".$programbranch.")";
    //print_r($programname);


?>

</head>
<body>
        <div align="center" style="margin-left:25%;width:50%;">

                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>

            <?php if(isset($_SESSION['message'])){?>
                    <div class="isa_success"><?php echo $_SESSION['message'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>

        </div>

<center>
    <form action="<?php echo site_url('Enterence/step_zero'); ?>" method="POST" >
    <table style="border:1px solid black; margin-top:50px;">

        <tr><td>
        <label for="text">Email Id :</label></td>
        </td><td>
<!--        <input type="text" name="applicantemail" placeholder="Enter your email id" class="keyup-email" value="<?php //echo isset($_POST["applicantemail"]) ? $_POST["applicantemail"] : ''; ?>" required="true" autofocus/> <br>-->
        <input type="text" name="applicantemail" placeholder="Enter your email id" class="keyup-email" value="<?php echo isset($_POST["applicantemail"]) ? $_POST["applicantemail"] : ''; ?>" required="true" autofocus/> <br>
        </td></tr>
    
        <tr><td>
        <label for="text">Mobile No :</label></td>
        </td><td>
        <input type="text" name="applicantmobile" placeholder="Enter your mobile no" class="keyup-numeric" value="<?php echo isset($_POST["applicantmobile"]) ? $_POST["applicantmobile"] : ''; ?>" required="true"/> <br>
        </td></tr>

        <tr><td>
        <label for="text">Date Of Birth :</label></td>
        <td>
        <input type="text" name="dateofbirth" value="<?php echo isset($_POST["dateofbirth"]) ? $_POST["dateofbirth"] : ''; ?>" id="dob" required="true"/>
        
            <script>
                $('#dob').datepicker({
                onSelect: function(value, ui) {
                    console.log(ui.selectedYear)
                    var today = new Date(), 
                    dob = new Date(value), 
                    age = 2017-ui.selectedYear;

//                $("#age").text(age);
                },
                //(set for show current month or current date)maxDate: '+0d',
                
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                    defaultDate: '1yr',
                    yearRange: 'c-37:c+30',
                });
            </script>
        </td>
        </tr>
        
        <tr><td>
        <label for="text">Program Name :</label></td>
        </td><td>
        <input type="text" name="applicantprogram" placeholder="Enter your program" value="<?php echo isset($_POST["applicantprogram"]) ? $_POST["applicantprogram"] : $programname; ?>" required="true" readonly/> <br>
        </td></tr>
        <tr><td align="center" colspan="2"><b><i>If you have already registered, Please fillup the code.</i></b></td></tr> 
        <tr><td>
        <label for="text">Verification Code :</label></td>
        </td><td>
        <input type="text" name="applicantvercode" placeholder="Enter verification code" class="keyup-numeric" value="<?php echo isset($_POST["applicantvercode"]) ? $_POST["applicantvercode"] : ''; ?>" maxlength="8"/> <br>
        </td></tr>
        <?php
            if($msgflag == 1){?>
            <tr><td align="center" colspan="2"><b><i>Forget Verification code?.</i></b>
            <input type="checkbox" name="accept" style="font-size:17px;" value="accept" id="termsChkbx">
            </td></tr>
        <?php }?>
        
        <tr>
        <td></td>
        <td>
        <button name="login1" >Submit</button>
        <input type="hidden" name="prg_name" value="<?php echo $prg_name;?>">
        </td></tr>
    
    </table>
    </form>

</center>
<?php
     $this->load->view('template/footer'); ?>

</body>
</html>



