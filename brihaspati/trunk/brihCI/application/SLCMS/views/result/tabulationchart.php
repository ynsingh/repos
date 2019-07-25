<style type="text/css">
label{font-size:18px;}
tbody tr td{font-size:18px;}
thead tr th{color:white;font-weight:bold;font-size:18px;}
select{width:100%;font-size:17px;}
#text{background-color:#38B0DE;color:white;font-size:20px;font-weight:bold;opacity:1.5;height:30px;padding:5px;}
#form{ border:1px solid black;width:60%;}
</style>
 <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
<script>
	/*function getsemester(degree){
	        var degree = degree;
		//alert(sem);
                $.ajax({
                type: "POST",
		url: "<?php //echo base_url();?>slcmsindex.php/admin_studentresult/subject_get",
                data: {"program_branch" : degree},
                dataType:"html",
                success: function(data){
                	$('#program_branch').html(data.replace(/^"|"$/g, ''));
		}

            });
        }*/

	$(document).ready(function(){
        
       $('#program_branch').on('change',function(){
		var prgid = $(this).val();
		//console.log(prgid);
		//die();
		//alert(prgid);
		//console.log(2);
                //var semid = $('#semester').val();
               if(prgid == ''){
               $('#subject').prop('disabled',true);
               //alert("disabled");
          
           }
           else{
             
               $('#subject').prop('disabled',false);
               $.ajax({
                url: "<?php echo base_url();?>slcmsindex.php/admin_studentresult/branchlist",
                type: "POST",
                data: {"programname" : prgid},
                dataType:"html",

                success: function(data){
                	//alert(data);
                	$('#subject').html(data.replace(/^"|"$/g, ''));
		}
             });
           }
        });
    })
	function getacdyr(prgsemsubid){
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

        $(document).ready(function(){
            
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
<script>    
            
</script>

</head>
<body>


<div>
	<?php $this->load->view('template/header'); ?>
	<?php //$this->load->view('template/facultymenu'); ?>
</div>
	
<?php           
/*$unique = array();    
$unique2 = array();
foreach($facprgsublist as $prgdata):
  //  $prdata = $prgdata->sp_programid;
    //$prgsem = $prgdata->sp_semester;
   // $prgsem = $this->commodel->get_listspfic1('student_program','sp_semester','sp_programid',$prdata)->sp_semester;
    $unique[] = $prdata;
    $unique2[] = $prgsem;
endforeach; 
$unique1 = array_unique($unique); 
$unique3 = array_unique($unique2);*/

?>

<table width="100%">
            <tr>
		<?php
		  
                    //echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		?>
			 
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


	</br>
	<?//php print_r($degree);?>
<form name="tabulationchart" id="tabulation" action="<?php echo site_url('');?>" method="POST" class="form-inline">

        <table style="width:100%;" >
	
            <tr style="font-weight:bold; background-color:lightslategray;">

                <td align=left> <span style="color:white;">Select Degree :</span></br>
                    <select name="program_branch" id="program_branch">
			<option selected="true" disabled>Select degree</option>
			 <?php 
			 	
			 foreach($degree as $degree1){  

			  ?>	
                    <option value="<?php echo $degree1->prg_id; ?>"><?php echo $degree1->prg_name ?></option> 
 			<?php
 				}
 			?>		
		    </select>
                </td>
                <td align=left><span style="color:white;">Subject :</span></br>
			<select name="subject" id="subject" >
				<option  valur="" selected="true" disabled>subject</option>


		
		   	</select>		
                </td>
                <td align=left><span style="color:white;">Academic Year :</span></br>
                    <select name="acdyr" id="acdyr" onchange="getacdyr(this.value);" > 
                        <option selected="selected" disabled selected>Academic year</option>
		
                    </select>
                </td>
		<td align=left><span style="color:white;">Semester :</span></br>
                    <select name="semester" id="semester" onchange="getsemester(this.value)" > 
                        <option selected="selected" disabled selected>semester</option>
							<?php for ($x = 1; $x <9; $x++) { ?>

							<option> <?php echo $x ?> </option>
						<?php } ?>
                    </select>
                </td>

		<td valign=bottom><input type="submit" name="search" value="Search" style="font-size:17px;" /></td>
            </tr>
	
	

          <table>
  <tr>
  	<?php //////////////////////////////////////?>
   <th rowspan="4">MS NO</th><th>Enroll. NO.</th><th>Name of the Students</th><th colspan="2">Subject Name</th><th>Status(P/F)</th><th>Grade letter</th><th colspan="2">Subject name</th><th>Status(P/F)</th><th>Grade letter</th></tr>


   
  </tr>
  <tr>
    <td>January</td>
    <td>$100</td>
    <td rowspan="2">$50</td>x	
  </tr>
  <tr>
    <td>February</td>
    <td>$80</td>
  </tr>
</table>



      		</thead>
          </table>

    </body>   
  <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>