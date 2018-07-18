<!--@filename positionvacancy.php  @author Manorama Pal(palseema30@gmail.com) 
    @filename positionvacancy.php  @author Neha Khullar(nehukhullar@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
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
                
                /****************************************** start post********************************/
                $('#wtype').on('change',function(){
                    var workt = $(this).val();
                   // alert("post====="+workt);
                    if(workt == ''){
                        $('#post').prop('disabled',true);
                   
                    }
                    else{
                        $('#post').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getpostlist_sp",
                            type: "POST",
                            data: {"worktype" : workt},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#post').html(data.replace(/^"|"$/g, ''));
                                                 
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
            });  
            
            function verify(){
                var x=document.getElementById("wtype").value;
                var y=document.getElementById("post").value;
                if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    alert("please select at least any two combination for search !!");
                    return false;
                };
                   

            }

        </script>     
       
    
    </head>
    <body>
        <?php $this->load->view('template/header'); ?> 
        <form action="<?php echo site_url('report/positionvacancy');?>" id="myForm" method="POST" class="form-inline">
          <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td>  Select Working Type
                    <select name="wtype" id="wtype"> 
			<?php if  (!empty($this->wtyp)){ ?>
                        <option value="<?php echo $this->wtyp; ?>" > <?php echo $this->wtyp; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>------- Select Working Type -------</option>
                          <?php  } ?>

                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                       
                    </select> 
                                    
                <!--</td> 
                <td> -->  Select Post
                    <select name="post" id="post"> 
			<?php if  ((!empty($this->desigm))&&($this->desigm != 'All')){ ?>
                        <option value="<?php echo $this->desigm; ?>" > <?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$this->desigm)->desig_name ." ( ". $this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$this->desigm)->desig_code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>----------- Select Post------</option>
                         <?php  } ?>

                     <!-- <option value="All" >All</option> -->
                    </select> 
               <!-- </td>
                <td>-->
                    <input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
                </td>
            </tr>    
        </table>  
        
    <table width="100%">
       <tr style=" background-color: graytext;">
        <td>
            <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
            <div style="margin-left:500px;"><b>Vacancy Position</b></div>
        </td>       
      <!-- <div>
       <//?php
       echo "<td align=\"center\" width=\"100%\">";
       echo "<b>Vacancy Position</b>";
       echo "</td>";
       ?>
       
        </div> -->
        </tr></table>
        <div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>SS</th>
                    <th>P</th>
                    <th>V</th>
                    <th>Name of the Employee</th>
                    <th>Designation</th>
                    <th>DOR</th>
                   
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		$opid = 0;
                $poid1=0;$ss1=0;$sp1=0;$sv1=0;
                
               if( count($allpost) ):  ?>
                <?php foreach($allpost as $record){
              
                if($opid !=$record->sp_emppost){    
                    echo "<tr>";
                    echo "<td colspan=6 style=\"text-align:center;\">";
                    echo " <b> Post : ";
                    echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->sp_emppost)->desig_name;
                    echo "</b></td>";
                    echo "</tr>";
                   $poid=$record->sp_emppost;
                    }   
                $selectfield ="sp_uo, sp_dept,sp_schemecode,sp_sancstrenght , sp_position , sp_vacant";
                $whdata = array('sp_emppost' =>$record->sp_emppost);
                $whorder = "sp_uo  asc, sp_dept  asc";
		
		$rlid=$this->session->userdata('id_role');
                if ($rlid == 5){
                        $usrid=$this->session->userdata('id_user');
			$deptid = '';
                	$whdatad = array('userid' => $usrid,'roleid' => $rlid);
        	        $resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
	                foreach($resu as $rw){
                        	$deptid=$rw->deptid;
                	}
                        $whdata['sp_dept'] = $deptid;
                        //array_push($whdata,'sp_dept' => $deptid);
                }
                if ($rlid == 10){
                        $usrname=$this->session->userdata('username');
			if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')){
                        }else{
                        	$uoid=$this->lgnmodel->get_listspfic1('authorities','id','authority_email',$usrname)->id;
                        	$whdata['sp_uo'] = $uoid;
                	}
		}


                $alldata=$this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);
                foreach($alldata as $data){
                    echo "<tr>";
                    echo "<td colspan=6 style=\"text-align:center;\">";
                    echo " <b> UO CONTROL : ";
                    echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$data->sp_uo)->name;
                    echo " ( ".$this->lgnmodel->get_listspfic1('authorities','code','id' ,$data->sp_uo)->code." )";
                    echo "</b></td>";
                    echo "</tr>";
                    echo "<tr><td colspan=6 align=left><b> Department : ";
                    echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$data->sp_dept)->dept_name;
                    echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$data->sp_dept)->dept_code ." )";
                    echo "</b></td></tr>";
                    echo "<tr><td colspan=6 align=left><b> Scheme Name : ";
                    echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$data->sp_schemecode)->sd_name;
                    echo " ( ". $this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$data->sp_schemecode)->sd_code ." )";
                    echo "</b></td></tr>";
                    echo "<tr>";
                    echo "<td>". $data->sp_sancstrenght ." </td>";
                    echo "<td> $data->sp_position</td>";
                    echo "<td> $data->sp_vacant</td>";
                    echo "<td> </td>";
                    echo "<td> </td>";
                    echo "<td> </td>";
                    echo "</tr>";  
                    $opid1=$record->sp_emppost;
                    //$selectfield ="emp_name,emp_post,emp_dor";
                    //$whdata=array('emp_uocid' => $data->sp_uo,'emp_dept_code' =>$data->sp_dept,'emp_schemeid' =>$data->sp_schemecode,'emp_desig_code' =>$record->sp_emppost);
                    $selectfield ="emp_id,emp_code,emp_name,emp_desig_code,emp_post,emp_dor";
		    $emppost1=$this->commodel->get_listspfic1('designation','desig_name','desig_id', $record->sp_emppost)->desig_name;
                    $whdata=array('emp_uocid' => $data->sp_uo,'emp_dept_code' =>$data->sp_dept,'emp_schemeid' =>$data->sp_schemecode,'emp_post' =>$emppost1);
                    $whorder = "emp_name asc";
                    $this->dataemp = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
                    foreach($this->dataemp as $emp){
                        echo '<tr>';
                        echo "<td> </td>";
                        echo "<td> </td>";
                        echo "<td> </td>";
                        echo "<td>";
			echo anchor("report/viewfull_profile/{$emp->emp_id}",$emp->emp_name." ( "."PF No:".$emp->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
//			. $emp->emp_name .
			echo " </td>";
                        //echo "<td>". $emp->emp_post ." </td>";
                        echo "<td>". $this->commodel->get_listspfic1('designation','desig_name','desig_id', $emp->emp_desig_code)->desig_name ." </td>";
                        echo "<td>". $emp->emp_dor ." </td>";
                        echo '</tr>';
                    }//emp
                    $poid =$record->sp_emppost;
				$ss = $data->sp_sancstrenght;
				$sp = $data->sp_position;
				$sv = $data->sp_vacant;
				if ($poid == $poid1){
					$ss = $ss1 + $ss;
					$sp = $sp1 + $sp;
					$sv = $sv1 + $sv;
				}
				$poid1 = $poid;
				$ss1 = $ss;
				$sp1 = $sp;
				$sv1 = $sv;
                }// alldata
                
                 echo '<tr>';
                        echo "<td><b>".$ss."</b></td>";
                        echo "<td><b>".$sp."</b> </td>";
                        echo "<td><b>".$sv."</b></td>";
                        echo "<td><b>Total for  ". $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->sp_emppost)->desig_name."</b></td>";
                        echo "<td> </td>";
                        echo "<td> </td>";
                        echo '</tr>';
                             
                ?>
               
                       
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>  
        </div><!------print div------>
        </form>
        <p> &nbsp; </p> 
        <div align="center">  <?php $this->load->view('template/footer');?></div>
     </body>
</html>

