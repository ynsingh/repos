<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name academicprofile.php @author Manorama Pal(palseema30@gmail.com) -->
<html>
<title>Academic Profile</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin:0;  /* this affects the margin in the printer settings */
            }
        </style>
<?php $current="academic"; ?>

        <script>
      
            function printDiv(printme) {
                var printContents = document.getElementById(printme).innerHTML; 
                var originalContents = document.body.innerHTML;      
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logo1.png' alt='logo' align='left' style='width:70%;height:100px;'>"+" <div style='width:70%;height:100%;'> " + printContents + "</div>"+"</body>";
                // document.body.style.fontSize = "x-small";
                //document.body.style. = "x-small";
                window.print();  
                // document.body.style.fontSize = "initial";
                document.body.innerHTML = originalContents;
            }
		 function goBack() {
        		window.history.go(-2);
	        }

        </script>

    </head>
    <body>
    <table style="width:100%;">
        <tr>
            <td>
                <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
                
            </td> 
	 <td align=right>
        <a href="#" onclick="goBack()"><img src='<?php echo base_url(); ?>uploads/icons/back1.png' title="Back"></a>
	</td>
        </tr>
    </table>        
    <div id="printme">   
      
        <table style="width:100%;" border=0>
            <div align="left">
            
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
 
            <tr><td>
		<?php
        include  'ptab.php';
?>
		</td>
		
  <!--              <td valign="top" width=170>

                    <?php //include 'profiletab.php'; ?>
	   
                </td>-->
                <?php 
//                $roleid=$this->session->userdata('id_role');
  //              if($roleid == 5){
//		$hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; 
//		$hempcode=$this->sismodel->get_listspfic1('hod_list','hl_empcode','hl_userid',$this->session->userdata('id_user'))->hl_empcode; 
//		$hempid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_code',$hempcode)->emp_id; 
  //              }
                
               // $hdept=$this->sismodel->get_listspfic1('user_role_type','deptid','userid',$this->session->userdata('id_user'))->deptid; 
                //$roleid=$this->session->userdata('id_role');
                ?>
                <td valign="top">		
                    <table style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                        <tr style="color:white;background:none repeat scroll 0 0 #0099CC;width:100%;">
                            <td align=left colspan=4><b>Academic Qualification</b></td>
                            <td align="right">
                                <?php
                                $uname=$this->session->userdata('username');
				$rest = substr($uname, -21);
                               // if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code))||($roleid == 4)){
        //                        if(($roleid == 1)||(($roleid == 5)&&($hdept == $data->emp_dept_code)&&($emp_id != $hempid)&&(!(in_array($emp_id, $uoempid))))||(($this->session->userdata('username') == 'ro@tanuvas.org.in') && (in_array($emp_id, $uoempid)))||(($rest == 'office@tanuvas.org.in') && (in_array($emp_id, $hodempid)))){
				if(($roleid == 1)||($flagffs)||($flagcppm)||($flagro)||($flaguooff)||($flaghod)){
                                    echo anchor("empmgmt/add_academicqualification/{$emp_id}"," Add ",array('title' => ' Add Academic Qualification Data' , 'class' => 'red-link')); 
                                    echo "<span>/</span>" ;
                                    echo anchor("empmgmt/edit_academicprofile/{$emp_id}","Edit ",array('title' => ' Edit Academic Qualification Data' , 'class' => 'red-link'));
                                }
                                ?>

                            </td>
                        <tr>
                    </table>
                    <div class="scroller_sub_page">
                       
                    <table  width="100%" class="TFtable">
                        <thead>
                            <tr>
                                <th>Academic</th>
                                <th>Board/University</th>
                                <th>Result</th>
                                <th>Year of Passing</th>
                                <th>Certificate Type</th>
                                <th>Discipline</th>
                                <th></th>
                            </tr>    
                        </thead>
                        <tbody>
                               
                                
                                    <tr><td colspan="7"><b><span style="color:#0099CC;">School Education</span></b></td></tr>
                                    <?php if( !empty($schooledu) ):  ?>
                                    <?php foreach($schooledu as $record){; ?>
                                        
                                        <tr>
                                            <?php $str= substr($record->saq_dgree,0,1);
                                            
                                            if($str != 'B' && $record->saq_dgree != 'PhD' && $record->saq_dgree != 'PDF' && $record->saq_dgree != 'P.G.Diploma' && $record->saq_dgree != 'NA'): ?>
                                            <td><b><?php $record->saq_dgree;
                                                    if($record->saq_dgree == '8th_std'){
                                                        echo "8<sup>th</sup> std";
                                                    }
                                                    if($record->saq_dgree == '10th_std'){
                                                        echo "10<sup>th</sup>std/SSLC";
                                                    }
                                                     if($record->saq_dgree == 'Ten_Plus_Two'){
                                                        echo "Ten Plus Two/12<sup>th</sup>";
                                                    }
                                            ;?></b></td>
                                            <td><?php echo $record->saq_board_univ;?></td>
                                            <td><?php echo $record->saq_result;?></td>
                                            <td><?php echo $record->saq_yopass;?></td>
                                            <td><?php echo $record->saq_certtype;?></td>
                                            <td><?php echo $record->saq_discipline;?></td>
                                            <td>
                                           <?php
                                                $selectfield = 'ud_filename';
                                                $whdata = array ('ud_filename LIKE' =>$data->emp_code.'_'.$record->saq_dgree."%") ;
                                                $schfd['schfile']= $this->sismodel->get_orderlistspficemore('uploaddocuments',$selectfield,$whdata,'');
                                                if(!empty($schfd['schfile'])){
                                                $file_path = 'uploads/SIS/School_Education/'.$schfd['schfile'][0]->ud_filename;
                                               // echo $file_path ;
                                                    if (file_exists($file_path)){
                                                 //  echo "true";     
                                              ?>  
                                                    <a href="<?php echo base_url().$file_path; ?>"
                                                    accesskey=""target="_blank" type="application/octet-stream" download >View</a>
                                                <?php    }
                                                } 
                                             ?>
                                            </td>    
                                            <?php endif;?>
                                        </tr>
                                    <?php }; ?>
                                <?php else : ?>
                                    <td colspan= "13" align="center"> No Records found...!</td>
                                <?php endif;?>        
                            </tbody>
                       
                            <tbody>
                               
                                
                                    <tr><td colspan="7"><b><span style="color:#0099CC;">P. G. Diploma</span></b></td></tr>
                                    <?php if( !empty($diploma) ):  ?>
                                    <?php foreach($diploma as $record){; ?>
                                        
                                        <tr>
                                            <td><b><?php echo $record->saq_dgree
                                                    
                                                    ;?></b></td>
                                            <td><?php echo $record->saq_board_univ;?></td>
                                            <td><?php echo $record->saq_result;?></td>
                                            <td><?php echo $record->saq_yopass;?></td>
                                            <td><?php echo $record->saq_certtype;?></td>
                                            <td><?php echo $record->saq_discipline;?></td>
                                            <td>
                                                <?php
                                                $selectfield = 'ud_filename';
                                                $whdata = array ('ud_filename LIKE' =>$data->emp_code.'_Postgraduate_PG_Diploma'."%") ;
                                                $dipfd['dipfile']= $this->sismodel->get_orderlistspficemore('uploaddocuments',$selectfield,$whdata,'');
                                                if(!empty($dipfd['dipfile'])){
                                                $file_path1 = 'uploads/SIS/Academic_Qualification/'.$dipfd['dipfile'][0]->ud_filename;
                                               // echo $file_path1 ;
                                                    if (file_exists($file_path1)){
                                            ?>            
                                                    <a href="<?php echo base_url().$file_path1; ?>"
                                                        accesskey=""target="_blank" type="application/octet-stream" download >View</a>
                                                <?php    }
                                                } 
                                             ?>
                                            </td>    
                                        </tr>
                                    <?php }; ?>
                                <?php else : ?>
                                    <td colspan= "7" align="center"> No Records found...!</td>
                                <?php endif;?>        
                            </tbody>
                       
                            <tbody>
                                
                                                               
                                <tr><td colspan="7"><b><span style="color:#0099CC;">Under Graduate (UG)</span></b></td></tr>
                                 <?php if( !empty($ugraduate) ):  ?>
                                    <?php foreach($ugraduate as $record){; ?>
                                        
                                        <tr>
                                        
                                            <td><b><?php echo $record->saq_dgree;?></b></td>
                                            <td><?php echo $record->saq_board_univ;?></td>
                                            <td><?php echo $record->saq_result;?></td>
                                            <td><?php echo $record->saq_yopass;?></td>
                                            <td><?php echo $record->saq_certtype;?></td>
                                            <td><?php echo $record->saq_discipline;?></td>
                                            <td>
                                            <?php
                                                $selectfield = 'ud_filename';
                                                $whdata = array ('ud_filename LIKE' =>$data->emp_code.'_Undergraduate_'.$record->saq_dgree."%") ;
                                                $ungfd['ungfile']= $this->sismodel->get_orderlistspficemore('uploaddocuments',$selectfield,$whdata,'');
                                                if(!empty($ungfd['ungfile'])){
                                                $file_path2 = 'uploads/SIS/Academic_Qualification/'.$ungfd['ungfile'][0]->ud_filename;
                                                //echo $file_path2 ;
                                                    if (file_exists($file_path2)){
                                            ?>            
                                                    <a href="<?php echo base_url().$file_path2 ; ?>"
                                                        accesskey=""target="_blank" type="application/octet-stream" download >View</a>
                                                <?php    }
                                                } 
                                             ?>
                                            </td>    
                                        </tr>
                                    <?php }; ?>
                                <?php else : ?>
                                    <td colspan= "7" align="center"> No Records found...!</td>
                                <?php endif;?>        
                            </tbody>
                          
                            <tbody>
                                
                                
                                    <tr><td colspan="7"><b><span style="color:#0099CC;">Post Graduate (PG)</span></b></td></tr>
                                    <?php if( !empty($masters) ):  ?>
                                    <?php foreach($masters as $record){; ?>
                                        
                                        <tr>
                                            
                                            <td><b><?php echo $record->saq_dgree;?></b></td>
                                            <td><?php echo $record->saq_board_univ;?></td>
                                            <td><?php echo $record->saq_result;?></td>
                                            <td><?php echo $record->saq_yopass;?></td>
                                            <td><?php echo $record->saq_certtype;?></td>
                                            <td><?php echo $record->saq_discipline;?></td>
                                            <td>
                                            <?php
                                                $selectfield = 'ud_filename';
                                                if($record->saq_dgree == 'MPhil'){
                                                $whdata = array ('ud_filename LIKE' =>$data->emp_code.'_'.$record->saq_dgree.'_'."%") ;
                                                }
                                                else{
                                                $whdata = array ('ud_filename LIKE' =>$data->emp_code.'_Postgraduate_'.$record->saq_dgree."%") ;
                                                }
                                                $pgfd['pgfile']= $this->sismodel->get_orderlistspficemore('uploaddocuments',$selectfield,$whdata,'');
                                                if(!empty($pgfd['pgfile'])){
                                                $file_path3 = 'uploads/SIS/Academic_Qualification/'.$pgfd['pgfile'][0]->ud_filename;
                                               // echo $file_path3 ;
                                                    if (file_exists($file_path3)){
                                            ?>            
                                                    <a href="<?php echo base_url().$file_path3 ; ?>"
                                                        accesskey=""target="_blank" type="application/octet-stream" download >View</a>
                                                <?php    }
                                                
                                                } 
                                             ?>
                                            </td>    
                                        </tr>
                                    <?php }; ?>
                                <?php else : ?>
                                    <td colspan= "7" align="center"> No Records found...!</td>
                                <?php endif;?>        
                            </tbody>
                            <tbody>
                                
                                
                                    <tr><td colspan="7"><b><span style="color:#0099CC;">Doctoral Programmes</span></b></td></tr>
                                    <?php if( !empty($doctrate) ):  ?>
                                        <?php foreach($doctrate as $record){; ?>
                                        
                                        <tr>
                                            <?php $str= $record->saq_dgree;
                                            if($str != 'Plus Two'){ ?>
                                            <td><b><?php echo $record->saq_dgree;?></b></td>
                                            <td><?php echo $record->saq_board_univ;?></td>
                                            <td><?php echo $record->saq_result;?></td>
                                            <td><?php echo $record->saq_yopass;?></td>
                                            <td><?php echo $record->saq_certtype;?></td>
                                            <td><?php echo $record->saq_discipline;?></td>
                                            <?php };?>
                                             <td>
                                           <?php
                                                $selectfield = 'ud_filename';
                                                if($record->saq_dgree == 'PhD' ||$record->saq_dgree == 'PDF' ){
                                                $whdata = array ('ud_filename LIKE' =>$data->emp_code.'_'.$record->saq_dgree.'_'."%") ;
                                                }
                                                $docfd['docfile']= $this->sismodel->get_orderlistspficemore('uploaddocuments',$selectfield,$whdata,'');
                                                if(!empty($docfd['docfile'])){
                                                $file_path4 = 'uploads/SIS/Academic_Qualification/'.$docfd['docfile'][0]->ud_filename;
                                                //echo $file_path4 ;
                                                    if (file_exists($file_path4)){
                                            ?>            
                                                   <a href="<?php echo base_url().$file_path4 ; ?>"
                                                        accesskey=""target="_blank" type="application/octet-stream" download >View</a>
                                                <?php    }
                                                } 
                                             ?> 
                                            </td>     
                                        </tr>
                                    <?php }; ?>
                                <?php else : ?>
                                    <td colspan= "7" align="center"> No Records found...!</td>
                                <?php endif;?>        
                            </tbody>
                        </table>    
                   
        
        </div> <!--div scrollbar-->
       
                    
                    <table style="width:100%;">
                        <tr>
                        <td align=right>
                        <a href="#" onclick="goBack()"><img src='<?php echo base_url(); ?>uploads/icons/back1.png' title="Back"></a>
                        </td>
                        </tr>
                    </table>
                    <br> 
        </td>
        </tr>
       </table> 
                    
    <p> &nbsp; </p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</body>
</html>

