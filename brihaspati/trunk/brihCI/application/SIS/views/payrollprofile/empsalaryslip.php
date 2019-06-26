
<!--@filename viewempsalaryslip.php  @author Manorama Pal(palseema30@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/style.css">
	<style type="text/css" media="print">
       
        </style>
        <script type="text/javascript">
            function verify(){
                var year=document.getElementById("year").value;
                var month=document.getElementById("month").value;
                if((year == " " && month ==" " )){
                    alert("please select month and year for load the salary !!");
                    return false;
                };


            }     
	</script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%">
           <tr colspan="8">
               
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b></b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#ViewProfile";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
		   // echo "</tr>";
		   // echo "</table>";
                ?>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>
            </tr>
        </table>
        <table width="100%" border="0">
            <?php
            //set the month array
                $cmonth= date('M');
                   
                $formattedMonthArray = array(
                   "1" => "Jan", "2" => "Feb", "3" => "Mar", "4" => "Apr",
                    "5" => "May", "6" => "Jun", "7" => "Jul", "8" => "Aug",
                    "9" => "Sep", "10" => "Oct", "11" => "Nov", "12" => "Dec",
                );
                  
                    // set start and end year range
                $cyear= date("Y");
                $yearArray = range(2015,  $cyear);
                 
            ?>
            <form action="<?php echo site_url('payrollprofile/empsalslip');?>" method="POST" enctype="multipart/form-data"> 
                <tr style="font-weight:bold;" >
                    <td>Select Month 
                        <select name="month" id="month" style="width:300px;"> 
                            <?php if($month!=$cmonth && (!empty($month))):;?>
                            <option value="<?php echo $month;?>"><?php echo $month;?></option>
                            <?php else:;?>
                            <option value="<?php echo $month;?>"><?php echo $month;?></option>
                            <?php endif;?>
                            <?php
                                foreach ($formattedMonthArray as $month) {
                                    echo '<option  value="'.$month.'">'.$month.'</option>';
                                }
                            ?>
                       
                        </select> 
                   <!--</td>   
                    <td> --> Select Year
                        
                        
                        <select name="year" id="year" style="width:300px;" > 
                            <?php if($year!=$cyear):;?>
                            <option value="<?php echo $year;?>"><?php echo $year;?></option>
                            <?php else:;?>
                            <option value="<?php echo $year;?>"><?php echo $year;?></option>
                            <?php endif;?>
                            <?php
                                foreach ($yearArray as $year) {
                                    echo '<option value="'.$year.'">'.$year.'</option>';
                                }
                            ?>          
                        </select> 
                    <!--</td>
                    <td> -->                      
                    <input type="submit" name="salpro" id="salpro" value="Load" onclick="verify();"/> 
                    </td>
                </tr>    
            </form>
        </table>    
        <p> &nbsp; </p>   
        </div><?php $this->load->view('template/footer'); ?></div>
    </body>
</html>