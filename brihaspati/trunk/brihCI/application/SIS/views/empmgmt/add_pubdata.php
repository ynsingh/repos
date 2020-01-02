<!--@name add_recmethddata.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Staff Publications Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <script>
            $(document).ready(function(){
		$("#conf1").hide();		
		$("#month1").hide();		
		$("#year1").hide();		
		$("#venue1").hide();		
		$("#jmoption1").hide();		
		$("#userrating1").hide();		
		$("#publisher1").hide();		
		$("#publang1").hide();		

		$('#prgtype').on('change',function(){
                        var pc= $('#prgtype').val();
			//alert(pc);
                        if(pc == "Journals"){
				$("#conf1").show();		
				$("#month1").show();		
				$("#year1").show();		
				$("#venue1").hide();		
				$("#jmoption1").show();		
				$("#userrating1").show();		
				$("#publisher1").hide();		
				$("#publang1").hide();		
                        }
                        if(pc == "Conference"){
				$("#conf1").show();		
				$("#month1").show();		
				$("#year1").show();		
				$("#venue1").show();		
				$("#jmoption1").hide();		
				$("#userrating1").hide();		
				$("#publisher1").hide();		
				$("#publang1").hide();		
                        }
                        if(pc == "Book"){
				$("#conf1").hide();		
				$("#month1").hide();		
				$("#year1").hide();		
				$("#venue1").hide();		
				$("#jmoption1").hide();		
				$("#userrating1").hide();		
				$("#publisher1").show();		
				$("#publang1").show();		
                        }
                        if(pc == "Chapter"){
				$("#conf1").show();		
				$("#month1").hide();		
				$("#year1").hide();		
				$("#venue1").hide();		
				$("#jmoption1").hide();		
				$("#userrating1").hide();		
				$("#publisher1").show();		
				$("#publang1").show();		
                        }
                        if(pc == "Articles"){
				$("#conf1").hide();		
				$("#month1").show();		
				$("#year1").show();		
				$("#venue1").hide();		
				$("#jmoption1").hide();		
				$("#userrating1").hide();		
				$("#publisher1").show();		
				$("#publang1").hide();		
                        }
                        if(pc == "Review"){
				$("#conf1").hide();		
				$("#month1").hide();		
				$("#year1").hide();		
				$("#venue1").hide();		
				$("#jmoption1").hide();		
				$("#userrating1").hide();		
				$("#publisher1").hide();		
				$("#publang1").hide();		
                        }
                        if(pc == "Notes"){
				$("#conf1").hide();		
				$("#month1").hide();		
				$("#year1").hide();		
				$("#venue1").hide();		
				$("#jmoption1").hide();		
				$("#userrating1").hide();		
				$("#publisher1").hide();		
				$("#publang1").hide();		
                        }
                        if(pc == "Monograph"){
				$("#conf1").hide();		
				$("#month1").hide();		
				$("#year1").hide();		
				$("#venue1").hide();		
				$("#jmoption1").hide();		
				$("#userrating1").hide();		
				$("#publisher1").hide();		
				$("#publang1").hide();		
                        }
                      /*  else{
				$("#conf1").hide();		
				$("#month1").hide();		
				$("#year1").hide();		
				$("#venue1").hide();		
				$("#jmoption1").hide();		
				$("#userrating1").hide();		
				$("#publisher1").hide();		
				$("#publang1").hide();		
                        }*/
                });

/*
                $('#todate,#fdate').datepicker({
                    dateFormat: 'yy/mm/dd',
                    autoclose:true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: 'c-70:c+20',
               
                }).on('changeDate', function (ev) {
                    $(this).datepicker('hide');
                });

		 $('#prgtype').on('change',function(){
                var recmthd = $(this).val();
               // alert("redioval===="+redioval);
                if(recmthd != 'Training'){
                    $('#dsubgrpid').prop('disabled',true);
                }
                else{
                    $('#dsubgrpid').prop('disabled',false);
                }
            });

*/
                  });
</script> 
    </head>
    <body>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    if($this->roleid == 4){
                        echo anchor('empmgmt/viewempprofile', 'View Profile ', array('class' => 'top_parent'));
                    }
                    else{
                        echo anchor('report/performance_profile/publication/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Staff Publications Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php  }; ?>
                <?php if  (isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php }; ?>
            </div>
            </td></tr>
        </table>
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
                    $yearArray = range(1952,  $cyear);
?>
        <div> 
            <form id="myform" action="<?php echo site_url('empmgmt/add_pubdata/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
            <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
                <tr><thead><th  style="color:white;background-color:#0099CC; text-align:left; height:30px;" colspan=63">&nbsp;&nbsp; Add Staff Publications Details</th></thead></tr>
                <tr></tr><tr></tr>
		<tr>
			<td>Type Of Publication<font color='Red'>*</font></td>
                        <td><select id="prgtype" name="prgtype" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Type Of Publication ---------</option>
                        <option value="Journals">Research Papers published in Journals</option>
                        <option value="Conference">Research Papers presented  in seminar/Workshop/conference etc</option>
                        <option value="Book">Book</option>
                        <option value="Chapter">Book Chapter</option>
                        <option value="Articles">Popular Articles</option>
                        <option value="Review">Review Articles</option>
                        <option value="Notes">Research Notes/Research Short Notes </option>
                        <option value="Monograph">Monograph/Manual</option>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Title <font color='Red'></font></td>
		    <td>
                            <input type="text" name="prgtitle" id="prgtitle" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Authors <font color='Red'></font></td>
		    <td>
                            <input type="text" name="author" id="author" value="" size="40" >
                    </td>
                </tr>
                <tr>
                    <td>Author Type<font color='Red'></font></td>
		    <td>
			<select id="authtype" name="authtype" required style="width:350px;">
                        <option selected="selected" disabled selected>------------- Author Type ---------</option>
                        <option value="FA">First Author</option>
                        <option value="SA">Second Author</option>
                        <option value="Others">Others</option>
                    </select>
                    </td>
                </tr>
                <tr id="conf1">
                    <td>Name of Journal/Seminar/Workshop/Conference etc<font color='Red'></font></td>
		    <td>
                            <input type="text" name="conf" id="conf" value="" size="40" >
                    </td>
                </tr>
                <tr id="month1">
		<td> Month
                    </td>
		    <td>
                        <select name="month" id="month" style="width:300px;font-weight:bold;">
                            <option value="" disabled selected>--------Select Month ----------</option>
                            <?php
                                foreach ($formattedMonthArray as $month) {
                                    $selected = ($month == $cmonth) ? 'selected' : '';
                                    echo '<option  '.$selected.' value="'.$month.'">'.$month.'</option>';
                                }
                            ?>
                        </select>
                    </td>
                </tr>
                <tr id="year1">
                    <td> Year
                    </td>
		    <td>
                        <select name="year" id="year" style="width:300px;font-weight:bold;">
                            <option value="" disabled selected>--------Select Year ----------</option>
                            <?php
                                foreach ($yearArray as $year) {
                                // if you want to select a particular year
                                $selected = ($year == $cyear) ? 'selected' : '';
                                echo '<option '.$selected.' value="'.$year.'">'.$year.'</option>';
                                }
                            ?>
                        </select>
                    </td>
                        </tr>
		
                <tr>
                    <td>ISSN/ISBN/Volume/Issue/Page No./Session<font color='Red'></font></td>
		    <td>
                            <input type="text" name="issn" id="issn" value="" size="40" >
                    </td>
                </tr>
                <tr id="venue1">
                    <td>Venue<font color='Red'></font></td>
		    <td>
                            <input type="text" name="venue" id="venue" value="" size="40" >
                    </td>
                </tr>
		
		<tr id="jmoption1">
                <td>Journal Metric options<font color='Red'></font></td>
                <td><select name="jmoption" style="width:350px;" id="jmoption" >
		<option selected="selected" disabled selected>------------ Journal Metric options ---------</option>
                        <option value="Naas Rating">Naas Rating</option>
                        <option value="Impact Factor">Impact Factor</option>
                        <option value="Scopus Rank">Scopus Rank</option>
                </select>
                </td>
        	</tr>
                <tr id="userrating1">
                    <td>Get Rating input from user<font color='Red'></font></td>
		    <td>
                            <input type="text" name="userrating" id="userrating" value="" size="40" >
                    </td>
                </tr>
                <tr id="publisher1">
                    <td>Publisher<font color='Red'></font></td>
		    <td>
                            <input type="text" name="publisher" id="publisher" value="" size="40" >
                    </td>
                </tr>
		<tr>
                <td>Publication Level<font color='Red'></font></td>
                <td><select name="publevel" style="width:350px;" id="publevel" >
		<option selected="selected" disabled selected>------------ Publication Level ---------</option>
                        <option value="University">University</option>
                        <option value="State">State</option>
                        <option value="National">National</option>
                        <option value="International">International</option>
                        <option value=""></option>
                </select>
                </td>
        	</tr>
		<tr id="publang1">
                <td>Language <font color='Red'></font></td>
                <td><select name="publang" style="width:350px;" id="publang" >
		<option selected="selected" disabled selected>------------ Language  ---------</option>
                        <option value="Local">Local</option>
                        <option value="Vernacular">Vernacular</option>
                        <option value="English">English</option>
                        <option value="Others">Others</option>
                </select>
                </td>
        	</tr>
                <tr></tr><tr></tr>
                <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="3">
                    <button name="addpubdata" >Submit</button>
		    <!--input type="reset" name="Reset" value="Clear"/-->
			<button type="button" onclick="history.back();">Back</button>
                    </td>
                </tr>    
        
            </table>
            </form>
        </div>    
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>
</html>    
   
