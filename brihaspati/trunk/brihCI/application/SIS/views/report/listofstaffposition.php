<!--@filename listofstaffposition.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) 
	@author Om Prakash (omprakashkgp@gmail.com)
        @author Neha Khullar (nehukhullar@gmail.com)
        @author Manorama Pal(palseema30@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html moznomarginboxes mozdisallowselectionprint>
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
                //document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' align='left' style='width:70%;height:100px;'>"+" <div style='width:70%;height:100%;'> " + printContents + "</div>"+"</body>";
                document.body.innerHTML = "<html><head><title></title></head><body style='width:100%;'><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' align='left'  style='width:100%;height:100px;' >"+" <div style='width:100%;height:100%;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
                
    
            /* var content = document.getElementById(printme).innerHTML;
                //alert("content=="+content);
                var mywindow = window.open('', 'Print', 'height="100%",width="100%"');

                 mywindow.document.write('<html><head><title>Print</title>');
                    mywindow.document.write('</head><body ><table width="100%" style="font-size:300%;">');
                    mywindow.document.write(content);
                mywindow.document.write('</table></body></html>');

                mywindow.document.close();
                mywindow.focus()
                 mywindow.print();
                mywindow.close();
                return true;*/
            }
            
            
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
                            url: "<?php echo base_url();?>sisindex.php/report/getuolist_sp",
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
                    if(uoid == ''){
                        $('#dept').prop('disabled',true);
                   
                    }
                    else{
                        $('#dept').prop('disabled',false);
                        $.ajax({
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
                           
               
            });
            function verify(){

                var x=document.getElementById("wtype").value;
                var y=document.getElementById("uoff").value;
                if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    alert("please select at least any two combination for search !!");
                    return false;
                };
                   

            }

        </script>   
             
    </head>
    <body>
          
        <?php $this->load->view('template/header'); ?>
        <form action="<?php echo site_url('report/listofstaffposition');?>" id="myForm" method="POST" class="form-inline">
        <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td>  Select Working Type
                    <select name="wtype" id="wtype" style="width:250px;"> 
			<?php if  (!empty($this->wtyp)){ ?>
                        <option value="<?php echo $this->wtyp; ?>" > <?php echo $this->wtyp; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>------- Select Working Type -------</option>
                          <?php  } ?>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                       
                    </select> 
                                    
                </td> 
                <td>   Select UO
                    <select name="uoff" id="uoff" style="width:270px;"> 
			<?php if  ((!empty($this->uolt))&&($this->uolt != 'All')){ ?>
                        <option value="<?php echo $this->uolt; ?>" > <?php echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$this->uolt)->name ." ( ". $this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$this->uolt)->code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>------ Select University officer -----</option>
                          <?php  } ?>
                     <!-- <option value="All" >All</option> -->
                    </select> 
                </td>
                <td> Select Department
                    <select name="dept" id="dept" style="width:250px;"> 
			<?php if ( (!empty($this->deptmt))&&($this->deptmt != 'All')){ ?>
                        <option value="<?php echo $this->deptmt; ?>" > <?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id' ,$this->deptmt)->dept_name ." ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id' ,$this->deptmt)->dept_code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>--------- Select Department -------</option>
                          <?php  } ?>
                      <!--<option value="All" >All</option> -->
                    </select> 
                </td>
                <td valign="bottom">
                    <input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
                </td>
            </tr>    
        </table>
        </form>
        <table width="100%"><tr style=" background-color: graytext;">
                
                <td>
                    <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')"  style='width:30px; height:30px;float:right;padding:2px; margin-right:30px;' title="Click for print" >  
                
                     <form action="<?php echo site_url('Pdfgen/lsf/'.str_replace(' ','_',$this->wtyp).'/'.$this->uolt.'/'.$this->deptmt) ?>">
                    <input type="submit" value="" style="width:30px; height:30px;float:right;padding:2px; margin-right:10px;background-image:url('<?php echo base_url(); ?>assets/sis/images/pdf.jpeg')" title="Click for pdf">     
                    </form>
                    <div style="margin-left:500px;"><b>List of Staff Position with Staff Name</b></div>

                </td>
           
	<!--<//?php
            echo "<td style=\"text-align:center;\" width=\"100%\">";
            echo "<b>List of Staff Position Details</b>";
            echo "</td>";
         ?> -->
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
    </tr></table>
   <div id="printme" align="left" style="width:100%;">        
    <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                   <!-- <th>Sr.No</th>-->
                    <th>SS</th> 
                    <th>P</th> 
                    <th>V </th> 
                    <th>Name of The Employee </th>
                    <th colspan=10>Designation</th>
                </tr>
            </thead>
            <tbody>
                <?php $count = 0; 
		$ouoid = 0;
		$odid = 0;
		$oschid = 0;
		$nop=0;

                $ossid=0;
                //initilise the grand total, Uo total, Dept total
                $gtotss=0;              $gtotp=0;               $gtotv=0;
                $uototalss=0;           $uototalp=0;            $uototalv=0;
                $depttotalss=0;         $depttotalp=0;          $depttotalv=0;

                $i=0; $ii=0;

                $type_tnt=$tnttype;
                $ddropdept=$seldept;
               if( count($records) ):  ?>
                    <?php foreach($records as $record){
      		 if($ouoid !=$record->sp_uo){               
			if($ii>0){
                                        echo "<tr>";
                                        echo "<td>". $depttotalss . "</td>";
                                        echo "<td>". $depttotalp . "</td>";
                                        echo "<td>". $depttotalv . "</td>";
                                        echo "<td colspan=10 >";
                                        echo " <b> Department Total  ";
                                        echo "</b></td>";
                                        echo "</tr>";
                                        $depttotalss=0;         $depttotalp=0;          $depttotalv=0; $ii=0;
                                }

                                if($i>1){
                                        echo "<tr>";
                                        echo "<td>". $uototalss. "</td>";
                                        echo "<td>". $uototalp."</td>";
                                        echo "<td>".$uototalv. "</td>";
                                        echo "<td colspan=10 >";
                                        echo " <b> UO CONTROL Total  ";
                                        echo "</b></td>";
                                        echo "</tr>";
                                        $uototalss=0;           $uototalp=0;            $uototalv=0;
                                }

			echo "<tr>";
			echo "<td colspan=\"10\" style=\"text-align:center;\">";
			echo " <b> UO CONTROL : ";
			echo "&nbsp;&nbsp;";
			echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->sp_uo)->name;
                        echo "&nbsp;&nbsp;"."( ".$this->lgnmodel->get_listspfic1('authorities','code','id' ,$record->sp_uo)->code." )";
			echo "</b></td>";
			echo "</tr>";
			$ouoid =$record->sp_uo;
		}
              //         	$this->deptlist = $this->sismodel->deptlist_sp($record->sp_uo,$type_tnt,$ddropdept);
                //        foreach($this->deptlist as $dept){
		if($odid !=$record->sp_dept){
				if($ii>0){
                                        echo "<tr>";
                                        echo "<td>". $depttotalss . "</td>";
                                        echo "<td>". $depttotalp . "</td>";
                                        echo "<td>". $depttotalv . "</td>";
                                        echo "<td colspan=10 >";
                                        echo " <b> Department Total  ";
                                        echo "</b></td>";
                                        echo "</tr>";
                                        $depttotalss=0;         $depttotalp=0;          $depttotalv=0;
                                }

                            echo "<tr><td colspan=10 align=left><b> Department : ";
                            echo "&nbsp;&nbsp;";
                            echo $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->sp_dept)->dept_code;
                            echo "<div style=\"text-align:center;\">";
                            echo "DEPT. OF ".strtoupper($this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->sp_dept)->dept_name);
                            $orgcode=$this->commodel->get_listspfic1('Department','dept_orgcode','dept_id',$record->sp_dept)->dept_orgcode;
                            echo "<br>".strtoupper($this->commodel->get_listspfic1('study_center','sc_name','org_code',$orgcode)->sc_name)."</br>"; 
                            echo "</div>";
                            echo "</b></td></tr>";
				$odid =$record->sp_dept;
		}
      		 if($oschid !=$record->sp_schemecode){               
			echo "<tr>";
			echo "<td colspan=10 style=\"text-align:center\">";
			echo " <b> Scheme : ";
			echo "&nbsp;&nbsp;";
			echo strtoupper($this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->sp_schemecode)->sd_name);
                        echo " ( ".$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$record->sp_schemecode)->sd_code ." )";
			echo "</b></td>";
			echo "</tr>";
			$oschid =$record->sp_schemecode;
		}
                            	//$this->postlist = $this->sismodel->postlist_sp($record->sp_uo,$record->sp_dept,$type_tnt,$record->sp_schemecode);
                           // echo "testing dept===".$record->sp_uo."dept====".$dept->sp_dept;
                    //        foreach($this->postlist as $post){
                                
                                echo "<tr><td colspan=10 align=left><b> Name of The Post : ";
                                echo  $this->commodel->get_listspfic1('designation','desig_name','desig_id', $record->sp_emppost)->desig_name; 
                                echo "</b></td></tr>";
                                echo "<tr>";
				$sss=$record->sp_sancstrenght;
                        $sp=$record->sp_position;
                        $sv=$record->sp_vacant;
                        echo "<td>". $sss ." </td>";
                        echo "<td>". $sp."</td>";
                        echo "<td colspan=10>". $sv ."</td>";
                        $gtotss=$gtotss+$sss;                   $gtotp=$gtotp+$sp;                      $gtotv=$gtotv+$sv;
                        $uototalss=$uototalss+$sss;             $uototalp=$uototalp+$sp;                $uototalv=$uototalv+$sv;
                        $depttotalss=$depttotalss+$sss;         $depttotalp=$depttotalp+$sp;            $depttotalv=$depttotalv+$sv;
                        $i++;   $ii++;
                        //        echo "<td> $record->sp_sancstrenght</td>";
                          //      echo "<td> $record->sp_position</td>";
                            //    echo "<td colspan=10> $record->sp_vacant</td>";
                                echo "</tr>";
                       
                                $this->emprec=$this->sismodel->emplist($record->sp_uo,$record->sp_dept,$record->sp_emppost,$record->sp_schemecode);      
                                foreach($this->emprec as $emp){
                                    echo "<tr>";
                                    echo "<td></td><td></td><td></td>";
                                    echo "<td>";
				echo anchor("report/viewfull_profile/{$emp->emp_id}",$emp->emp_name." ( "."PF No:".$emp->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
                                    echo "</td>";
                                    echo "<td>";
                                    //echo  $emp->emp_post;
                                   echo  $this->commodel->get_listspfic1('designation','desig_name','desig_id', $emp->emp_desig_code)->desig_name ;
                                    echo "</td>";
                                    echo "</tr>";
                          //      }
//                            }
                        }
                    ?>
                       
                <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" > No Records found...!</td>
                <?php endif;?>
		 <?php                           echo "<tr>";
                                        echo "<td>". $depttotalss . "</td>";
                                        echo "<td>". $depttotalp . "</td>";
                                        echo "<td>". $depttotalv . "</td>";
                                        echo "<td colspan=10 >";
                                        echo " <b> Department Total  ";
                                        echo "</b></td>";
                                        echo "</tr>";
                                        echo "<tr>";
                                        echo "<td>". $uototalss. "</td>";
                                        echo "<td>". $uototalp."</td>";
                                        echo "<td>".$uototalv. "</td>";
                                        echo "<td colspan=10 >";
                                        echo " <b> UO CONTROL Total  ";
                                        echo "</b></td>";
                                        echo "</tr>";
                                        echo "<tr>";
                                        echo "<td>". $gtotss. "</td>";
                                        echo "<td>". $gtotp."</td>";
                                        echo "<td>".$gtotv. "</td>";
                                        echo "<td colspan=10 >";
                                        echo " <b> Grand Total  ";
                                        echo "</b></td>";
                                        echo "</tr>";
        ?>

            </tbody>
        </table>
        
        </div><!------scroller div------>
        
        </div><!------print div------>
        <p> &nbsp; </p>

        <div>  <?php $this->load->view('template/footer');?></div>

    </body>
</html>
