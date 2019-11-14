<!--@filename employeelist.php  @author Manorama Pal(palseema30@gmail.com) 
    modification in gui - 16 OCT 2017 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to Staff Management</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
<script>

//                            url: "<?php echo base_url();?>sisindex.php/report/getuolist"
$(document).ready(function(){
		/****************************************** start of uofficer********************************/
                $('#wtype').on('change',function(){
                    var workt = $(this).val();
                    //alert(sc_code);
                    if(workt == ''){
                        $('#uoff').prop('disabled',true);
                   
                    }
                    else{
                        $('#uoff').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getspuolist",
                            type: "POST",
                            data: {"worktype" : workt},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#uoff').html(data.replace(/^"|"$/g, ''));
                                                 
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of uofficer********************************/

		/****************************************** start of deptarment********************************/
                $('#uoff').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    //alert(sc_code);
                    var wrktypeuo = wtcode+","+uoid;
                    if(wtcode == ''){
                        $('#dept').prop('disabled',true);
                   
                    }
                    else{
                        $('#dept').prop('disabled',false);
                        $.ajax({
                           // url: "<?php echo base_url();?>sisindex.php/report/getdeptlist",
                            url: "<?php echo base_url();?>sisindex.php/report/getdeptlist_sp",
                            type: "POST",
                            data: {"worktypeuo" : wrktypeuo},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#dept').html(data.replace(/^"|"$/g, ' '));
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of department********************************/
	/****************************************** start of designation*******************************/
                $('#dept').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    var dept = $('#dept').val();
                    //alert(sc_code);
                    var wtuodept = wtcode+","+uoid+","+dept;
                    // alert(wtuodept);
                    if(dept == ''){
                        $('#post').prop('disabled',true);
                   
                    }
                    else{
                        $('#post').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getuodeptpostlist_sp",
                          //  url: "<?php echo base_url();?>sisindex.php/jslist/getwdesiglist",
                            type: "POST",
                            data: {"wtuodept" : wtuodept},
                          //  data: {"wtype" : wtcode},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#post').html(data.replace(/^"|"$/g, ' '));
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 

                /****************************************** start post********************************/
               // $('#wtype').on('change',function(){
                $('#dept').on('change',function(){
			var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    var dept = $('#dept').val();
                    //alert(sc_code);
                    var wtuodept = wtcode+","+uoid+","+dept;
                   // var workt = $(this).val();
                   //alert("post====="+workt);
                    if(dept == ''){
                        $('#desig').prop('disabled',true);
                    }
                    else{
                        $('#desig').prop('disabled',false);
                        $.ajax({
                           // url: "<?php echo base_url();?>sisindex.php/report/getuodeptpostlist_sp",
                            url: "<?php echo base_url();?>sisindex.php/jslist/getwdesiglist",
                            type: "POST",
			  //  data: {"wtuodept" : wtuodept},
                            data: {"wtype" : wtcode},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#desig').html(data.replace(/^"|"$/g, ' '));
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
		var y=document.getElementById("uoff").value;
		var z=document.getElementById("strin").value;
		if(((x == 'null') && (y == 'null') && (z == 'null')) || ((x == '') && (y == '') && (z == ''))){
                //if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    alert("Please use at least one option either select or type string for search !!");
                    return false;
                };


            }
</script>
    <body>
            <?php $this->load->view('template/header'); ?>
           
        <table width="100%"><tr colspan="2">
        <?php 
        echo "<td align=\"left\" width=\"33%\">";
	$roleid=$this->session->userdata('id_role');
	$uname=$this->session->userdata('username');
        if(($roleid == 1)||($uname == 'rsection@tanuvas.org.in')){
		echo anchor('staffmgmt/staffprofile/', "Add Profile" ,array('title' => 'Add staff profile ' , 'class' => 'top_parent'));
	}
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Staff Profile Details</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	$help_uri = site_url()."/help/helpdoc#ViewEmployeeList";
        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
        echo "</td>";
        ?>
        <div>     
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
                  
        </div>
</tr>
        </table>
<form action="<?php echo site_url('staffmgmt/employeelist');?>" id="myForm" method="POST" class="form-inline">
          <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td>  Select Working Type<br>
                    <select name="wtype" id="wtype" style="width:200px;">
				<?php if  (!empty($this->wtyp)){ ?>
                        <option value="<?php echo $this->wtyp; ?>" > <?php echo $this->wtyp; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>-- Select Working Type --</option>
                          <?php  } ?>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                    </select>
		 </td>
               <td>  Select UO<br>
                    <select name="uoff" id="uoff" style="width:200px;">
                      <option value="" disabled selected>-- Select University officer--</option>
                    </select>
                </td>
                <td>  Select Department<br>
                    <select name="dept" id="dept" style="width:200px;">
                      <option value="" disabled selected>-- Select Department --</option>
                    </select>
                </td>
                <td>  Select Designation<br>
                    <select name="desig" id="desig" style="width:200px;">
                      <option value="" disabled selected>-- Select Designation --</option>
                    </select>
                </td>

		<?php
//		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp";
		?>
                <!--</td>-->
                <td>  Select Shown Against Post<br>
                    <select name="post" id="post" style="width:200px;">
			<?php if  ((!empty($this->desigm))&&($this->desigm != 'All')){ ?>
                        <option value="<?php echo $this->desigm ?>" > <?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$this->desigm)->desig_name ." ( ". $this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$this->desigm)->desig_code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value="" >-- Select Post --</option>
			 <?php  } ?>
                     <!-- <option value="All" >All</option> -->
                    </select>
                </td>
		<td>
		 Search String<br>
                          <input type="text" name="strin" id="strin" style="width:100" placeholder="Enter String" value="<?php echo isset($_POST["emp_name"]) ? $_POST["dept_name"] :  ''; ?>">

                    <input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
                </td>
            </tr>
        </table><br>
<?php 
	$hdeptid=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid;	
?>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th></th>
                    <th>Employee Name</th>
                    <th>Campus Detail</th>
                    <th>Designation Detail</th>
                    <th>Contact No<br/>Aadhaar No</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($records) ): 
		 ?>
                    <?php foreach($records as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <?php //$img=$record->emp_code;?>
                            <?php if(!empty($record->emp_photoname)):?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$record->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php else :?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php endif;?>
			    <td><?php echo anchor("report/viewfull_profile/{$record->emp_id}",$record->emp_name,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
                                    echo "<br/> ( "."PF No:".$record->emp_code." )";
                                    echo "<br/>".$record->emp_email;
                            ?>
                            </td>
                            <td><?php
                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
                                    if(!empty($record->emp_schemeid)){
                                    $schm=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name;
					$schmcode=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$record->emp_schemeid)->sd_code;
                                    }
                                    else{
                                     $schm='';   
					$schmcode='';
                                    }
                                    if(!empty($record->emp_specialisationid)){
                                        $sub=$this->commodel->get_listspfic1('subject','sub_name','sub_id',$record->emp_specialisationid)->sub_name;
                                    }
                                    else{
                                        $sub="";
                                    } 
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "
                                            ."<b>scheme-: </b>".$schm . "( " .$schmcode." )"."<br/>"."<b>subject-: </b>".$sub;
                            ?></td>
                            <td>
                                <?php 
				if(!empty($record->emp_desig_code)){
					echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name; 
				}

				$cdate = date('Y-m-d');
                                $headflag="false";
                                $hwdata = array('hl_empcode' =>$record->emp_code, 'hl_dateto' =>'0000-00-00 00:00:00' );
                                $headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);
                                if(($headflag)||($record->emp_head == "HEAD")){
                                        echo " ( <font color=Red> Head </font>)";
                                }

                                $uoflag="false";
                                $uhwdata = array('ul_empcode' =>$record->emp_code, 'ul_dateto ' =>'0000-00-00 00:00:00' );
                                $uoflag=$this->sismodel->isduplicatemore("uo_list",$uhwdata);
				if(($uoflag)||($record->emp_head == "UO")){
                                                echo " & UO";
                                 }

                                 echo "<br/><b>Shown Against Post-:</b>".$record->emp_post;
		
			
				?>
                            </td>
                            <td>
                                <?php $phone=$record->emp_phone;
                                      $adhaar=$record->emp_aadhaar_no;
                                      echo $phone."<br/>".$this->sismodel->mask($adhaar,null,strlen($adhaar)-4); 
                                ?>  
                            </td>
                            <td> <?php 
				$uname = $this->session->userdata('username');
                                $rest = substr($uname, -21);
				$hempid='';
		        	if($roleid == 5){
		                        $deptuocid=$this->commodel->get_listspfic1('Department','dept_uoid','dept_id',$hdeptid)->dept_uoid;
					if(!empty($this->sismodel->get_listspfic1('hod_list','hl_empcode','hl_userid',$this->session->userdata('id_user')))){
	                			$hempcode=$this->sismodel->get_listspfic1('hod_list','hl_empcode','hl_userid',$this->session->userdata('id_user'))->hl_empcode;
//						echo "hodcode ".$hempcode;
			                	$hempid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$hempcode)->emp_id;
//						echo " hodid ".$hempid; 
					}
        			}

				$suocid='';
				if(($uname == 'deancppmoffice@tanuvas.org.in')||($uname == 'deanffsoffice@tanuvas.org.in')){
		                        //$suocid=$this->commodel->get_listspfic1('Department','dept_uoid','dept_id',$hdeptid)->dept_uoid;
		                        $suocid=$deptuocid;
                		}
					//echo "Top".$record->emp_dept_code."em".$record->emp_id."hm".$hempid."idus".$this->session->userdata('id_user');

				 $flagffs=false;
				 $flagcppm=false;
				 $flagro=false;
				 $flaguooff =false;
				 $flaghod=false;
				if(($suocid == $record->emp_uocid) && ($this->session->userdata('username') == 'deanffsoffice@tanuvas.org.in')&&(!(in_array($record->emp_id, $uoempid)))){
					$flagffs=true;
				}
				if(($suocid == $record->emp_uocid) && ($this->session->userdata('username') == 'deancppmoffice@tanuvas.org.in')&&(!(in_array($record->emp_id, $uoempid)))){
					$flagcppm=true;
				}
				if(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($record->emp_id, $uoempid))){
					$flagro=true;
				}
				if(($rest == 'office@tanuvas.org.in') && (in_array($record->emp_id, $hodempid))&&(!(in_array($record->emp_id, $uoempid)))&&($deptuocid == $record->emp_uocid)){
					$flaguooff =true;
				}
				if(($roleid == 5)&&($hdeptid == $record->emp_dept_code)&&($record->emp_id != $hempid)&&(!(in_array($record->emp_id, $uoempid)))){
					$flaghod=true;
				}
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
					if(($headflag)||($record->emp_head == "HEAD")||($uoflag)||($record->emp_head == "UO")){
//						echo "I".$headflag."==".$record->emp_head."==".$uoflag."==".$record->emp_head."I";
						if($roleid == 1){
							echo anchor("staffmgmt/editempprofile/{$record->emp_id}","View/Edit",array('title' => 'View/Edit Details' , 'class' => 'red-link')); 
							echo "<br>";
							echo "<br>";
						}
						if(($flagffs)||($flagcppm)){
							if(($headflag)||($record->emp_head == "HEAD")){
								echo anchor("staffmgmt/editempprofile/{$record->emp_id}","View/Edit",array('title' => 'View/Edit Details' , 'class' => 'red-link'));
	                                                        echo "<br>";
        	                                                echo "<br>";
							}
						}
                                	}
					else{
						echo anchor("staffmgmt/editempprofile/{$record->emp_id}","View/Edit",array('title' => 'View/Edit Details' , 'class' => 'red-link')); 
						echo "<br>";
						echo "<br>";
					}
					if(($record->emp_worktype === "Teaching")&&($roleid == 1)){
						if(($headflag)||($record->emp_head == "HEAD")){
        	                        		echo anchor("staffmgmt/removehead/{$record->emp_id}","Remove Head",array('title' => 'Remove Head' , 'class' => 'red-link'));
						}else{
        	                               		echo anchor("staffmgmt/addhead/{$record->emp_id}","Add Head",array('title' => 'Add Head' , 'class' => 'red-link'));
						}
					}
				//	echo "<br>";
                              //        echo anchor("staffmgmt/changepf/{$record->emp_id}","Change PF",array('title' => 'Change Temp PF Number' , 'class' => 'red-link'));
				}
				?></td>
                        </tr>
                    <?php };
			if(($this->wtyp == '') ||( $this->wtyp == 'Teaching')){
			if(!empty($records1)){
			?>
			
			<?php 
				$opdfcde='';
				foreach($records1 as $record){ 
					$pfcde=$record->emp_code;
				if($opdfcde != $pfcde){
			?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <?php //$img=$record->emp_code;?>
                            <?php if(!empty($record->emp_photoname)):?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$record->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php else :?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php endif;?>
                            <td><?php echo anchor("report/viewfull_profile/{$record->emp_id}",$record->emp_name,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
                                echo "<br/> ( "."PF No:".$record->emp_code." )"; 
                                echo "<br/>".$record->emp_email;
				$opdfcde = $pfcde;
                            ?></td>
                             <td><?php
                                    $sc=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name;
                                    $uo=$this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name;
                                    $dept=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
                                    if(!empty($record->emp_schemeid)){
                                    $schm=$this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name;
					$schmcode=$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$record->emp_schemeid)->sd_code;
                                    }
                                    else{
                                     $schm='';   
					$schmcode='';
                                    }
                                    if(!empty($record->emp_specialisationid)){
                                        $sub=$this->commodel->get_listspfic1('subject','sub_name','sub_id',$record->emp_specialisationid)->sub_name;
                                    }
                                    else{
                                        $sub="";
                                    } 
                                    echo "<b>campus-: </b>".$sc."<br/> "."<b>uo-: </b>".$uo."<br/> "."<b>dept-: </b>".$dept."<br/> "
                                            ."<b>scheme-: </b>".$schm . "( " .$schmcode." )"."<br/>"."<b>subject-: </b>".$sub;
                            ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name;
                                $cdate = date('Y-m-d');
                                $headflag="false";
                                $hwdata = array('hl_empcode' =>$record->emp_code, 'hl_dateto' =>'0000-00-00 00:00:00');
                                $headflag=$this->sismodel->isduplicatemore("hod_list",$hwdata);

			//	 echo "empcode ".$record->emp_code." and flag ". $headflag;
				if(($headflag)||($record->emp_head == "HEAD")){
                                        echo " ( <font color=Red> Head </font>)";
                                }
                                       echo "<br/><b>Shown Against Post-:</b>".$record->emp_post; 

                                ?></td>
                            <td>
                                <?php $phone=$record->emp_phone;
                                      $adhaar=$record->emp_aadhaar_no;
                                      //echo "<b> Contact no-:</b>".$phone."<br/>"."<b> Aadhaar No-: </b>".$adhaar;
                                      echo $phone."<br/>".$this->sismodel->mask($adhaar,null,strlen($adhaar)-4);
                                ?>  
                            </td>
                            <td> <?php
                          //    $roleid=$this->session->userdata('id_role');
                        //        if(($roleid == 1)||(($roleid == 5)&&($hdeptid == $record->emp_dept_code ))){
                                        echo anchor("staffmgmt/editempprofile/{$record->emp_id}","View/Edit",array('title' => 'View/Edit Details' , 'class' => 'red-link'));
                          //      }
                                ?></td>
                        </tr>
			<?php
			}//record dup end
			 }//end of foreach
		}//empty end
	} //wtype null or teaching end
			?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
		</tbody>
        </table>
        </div><!------scroller div------>
		<br><br>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   
