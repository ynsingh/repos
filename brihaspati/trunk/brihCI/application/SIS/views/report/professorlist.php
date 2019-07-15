<!--@filename professorlist.php  @author Manorama Pal(palseema30@gmail.com) 
    @filename professorlist.php  @author Neha Khullar(nehukhullar@gmail.com) 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">

<script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
<script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <style type="text/css" media="print">
        @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
          <script>
             function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                //alert("printContents==="+printContents);
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body style='width:100%;' ><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' style='width:100%;height:100px;' >"+" <div style='width:100%;height:100px;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
            }

	$(document).ready(function(){
		$('#wtype').on('change',function(){
                    var workt = $(this).val();
                    if(workt == ''){
                        $('#desig').prop('disabled',true);
                   
                    }
                    else{
                        $('#desig').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getdesiglist",
                            type: "POST",
                            data: {"worktype" : workt},
                            dataType:"html",
                            success:function(data){
                           // alert("data==1="+data);
                                $('#desig').html(data.replace(/^"|"$/g, ''));
                                                 
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                });
		var today = new Date();
            
            $('#Dateofservcalc,#Dateofappoint,#Dateofagp').datepicker({
                dateFormat: 'yy/mm/dd',
                autoclose:true,
                changeMonth: true,
                changeYear: true,
                yearRange: 'c-70:c',
                endDate: "today",
                maxDate: today
            }).on('changeDate', function (ev) {
                $(this).datepicker('hide');
        });

	});



//	});
	function verify(){
                    var w=document.getElementById("desig").value;
                    var x=document.getElementById("wtype").value;
                    var y=document.getElementById("Dateofservcalc").value;
                    var z=document.getElementById("Dateofappoint").value;
                    var v=document.getElementById("Dateofagp").value;
                    if((x == 'null' && w == 'null') || (x == '' && w == '')||(w == 'null')||(x == 'null')){
                        alert("please select first two and date combination for search !!");
                        return false;
                    };
                    
            }
    </script>     
        
    </head>
    <body>
    <?php $this->load->view('template/header'); ?>    
<form action="<?php echo site_url('report/professorlist');?>" id="myForm" method="POST" class="form-inline">
         <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td> Select Type
                    <select name="wtype" id="wtype" style="width:150px;">
			<?php if  (!empty($this->wtyp)){ ?>
                        <option value="<?php echo $this->wtyp; ?>" > <?php echo $this->wtyp; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>- Select Working Type --</option>
                          <?php  } ?>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                    </select>
                </td>
                <td> Designation
                    <select name="desig" id="desig" style="width:150px;">
			 <?php if  ((!empty($this->desig))&&($this->desig != 'All')){ ?>
                        <option value="<?php echo $this->desig; ?>" > <?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$this->desig)->desig_name ." ( ". $this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$this->desig)->desig_code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>- Select Designation-</option>
                         <?php  } ?>

                    </select>
                </td>
		<td>Service Calculation Date
		<input type="text" name="dateofservecalc" value="" id="Dateofservcalc"  size="15" >
		</td>		
		<td>Appointment Date
		<input type="text" name="dateofappoint" value="" id="Dateofappoint"  size="15" >
		</td>		
		<td>AGP Date
		<input type="text" name="dateofagp" value="" id="Dateofagp"  size="15" >
		</td>		
		<td valign="bottom">
                    <input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
                </td>
		<!--<td valign="bottom">
            <img src='<?php //echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
        </td> -->
       
	   </tr>
	</table>
</form>
                   
        

<!--
    <table width="100%">
       <tr>
        <td>
            <img src='<?php //echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
        </td>       
        </tr></table>
-->
     
        <div id="printme" align="left" style="width:100%;">
	<table width="100%">
            
	<tr style=" background-color: graytext;">
         <td>
            <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px; height:30px;float:right;padding:2px; margin-right:30px;' title="Click for print" >   
            <form action="<?php echo site_url('Pdfgen/proflist/'.str_replace(' ','_',$this->wtyp).'/'.$this->desig) ?>">
            <input type="submit" value="" style="width:30px; height:30px;float:right;padding:2px; margin-right:10px;background-image:url('<?php echo base_url(); ?>assets/sis/images/pdf.jpeg')" title="Click for pdf">     
            </form>
            <div style="margin-left:500px;"><b><?php echo  "List of ";
                    if(!empty($this->desig))
                       echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$this->desig)->desig_name;
                    
                    echo " - ( Seniority on the basis of date of appt. as Prof )";
                    ; ?>
            </b> </div>        
         </td>  
            
       <?php /*
       echo "<td align=\"center\" width=\"100%\">";
       echo "<b> List of ";

	if(!empty($this->desig))
		echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$this->desig)->desig_name;
	echo " - ( Seniority on the basis of date of appt. as Prof )</b>";
       echo "</td>";*/
       ?>

        </tr>
        </table>
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    
                    <th>Sr.No</th>
                    <th>Employee Name</th>
                    <th>DOR</th>
                    <th>Discipline</th>
                    <th>Department</th>
                    <th>Date of Joining <br/> as Prof.</th>
                    <th>Total Service as Prof. <br/> as on (<?php echo date("Y/m/d");?>)<br/>YY/MM/DD</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
                    
                    if( count($emplist) ):  ?>
                        <?php    echo "<tr>";
                            echo "<td colspan=7>";
                            echo " <b> Designation : ";
			if(!empty($this->desig))
				echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$this->desig)->desig_name ;
                            echo "</b></td>";
                            echo "</tr>";
                         foreach($emplist as $record){
                            echo "<tr>";
                            echo "<td>".$serial_no++."</td>";
                            echo "<td>";
				echo anchor("report/viewfull_profile/{$record->emp_id}",$record->emp_name." ( "."PF No:".$record->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
	//			. $record-> emp_name.
			    echo "</td>";
                            echo "<td> ".implode('-', array_reverse(explode('-', $record->emp_dor)))."</td>";
                            echo "<td>";
                            if(!empty($record->emp_specialisationid)){
                                echo  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$record->emp_specialisationid)->sub_name;
                                echo  "</td>";
                            }
                            echo "<td>";
                            echo  $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
                            echo  "</td>";
                            echo "<td>". implode('-', array_reverse(explode('-', $record->emp_doj)))."</td>";
                            $date1 = new DateTime($record->emp_doj);
                            $date2 = new DateTime();
                            $diff = $date1->diff($date2);
                            echo "<td> ".$diff->y . "&nbsp;&nbsp;&nbsp;&nbsp;" . $diff->m." &nbsp;&nbsp;&nbsp;&nbsp; ".$diff->d."</td>";
                            echo "</tr>"
                    
                        ?>
               
                        <?php }; ?>
                    <?php else : ?>
            <td colspan= "13" align="center"> No Records found...!</td>
                    <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>   
        </div><!------print div------>
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>
</html>


