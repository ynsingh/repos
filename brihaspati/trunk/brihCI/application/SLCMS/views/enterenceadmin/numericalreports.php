<!--filename graphicalreports.php  @author sharad singh(sharad23nov@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>IGNTU Admission Dashboard</title>
        <head>
         <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
            <style>
                .panel-primary{
                   // margin-left:20px;
                    //margin-right:5px;
                    //width:600px;
                    //height:400px;
                    background-color: #D0D0D0;

                }
                #Table{

                }
                #Table td{

                    padding: 8px 8px 8px 50px;
                    font-size:15px;
                }

                    .table2 {
                    font-family: arial, sans-serif;
                    // border-collapse: collapse;
                    width: 100%;
                    //overflow: scroll;
                }
                .table2 td{
                    // border: 2px solid #dddddd;
                    text-align: left;
                    padding: 8px;
                    // padding: 8px 8px 8px 50px;
                }

                tr:nth-child(even) {
                    //background-color: #dddddd;
                // padding: 8px 8px 8px 50px;
                }
            </style>



        <meta charset="UTF-8"/>
        <title></title>
        <!-- Load Google chart api -->

    <script type="text/javascript" src="<?php echo base_url();?>assets/js/loader.js"></script>
    <script type="text/javascript">

    
    </script>
       
        </head>

        <body>
        
            <div>
                <?php $this->load->view('template/header'); ?>
               
                 <?php $this->load->view('template/menu');?>

<p>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
</p>	
            </div></br></br></br><br>
         <center>
           <table style="margin-top:5px;">
            <tr>
    <!--                <td valign=top>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Recent Applications</b> </div>
                            <div class="panel-body">
                            <?php //print_r($registeredapplicant);?>
                                <table class="TFtable" style="width: 700px; height: 350px;>
                                    <thead >
                                    <tr align="left" valign=top>
                                        <th><b>Applicant Name</b></th>
                                        <th><b>Type</b></th>
                                        <th><b>Status</b></th>
                                        <th><b>Time</b></th>
                                    </tr>
                                    </thead>
                                <?php $i=0;  foreach($registeredapplicant as $row){ ?>
                                <tr align="left">
<?php //echo $this->cmodel->get_listspfic1('program','prg_category ','prg_id',$row->pstp_prgid)->prg_category;?>
                                <?php $fname = $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$row->admission_masterid)->asm_fname; 
                                      $mname = $this->commodel->get_listspfic1('admissionstudent_master','asm_mname','asm_id',$row->admission_masterid)->asm_mname; 
                                      $lname = $this->commodel->get_listspfic1('admissionstudent_master','asm_lname','asm_id',$row->admission_masterid)->asm_lname;
                                if(empty($mname))
                                    $fullname = $fname." ".$lname;
                                else
                                    $fullname = $fname." ".$mname." ".$lname;
                                ?>
                                <td><?php echo $fullname;?></td>
                                <td>Application-2017</td>
                                <?php $row->step5_status;
                                    if($row->step5_status == 1):
                                ?>
                                        <td>Paid</td>
                                    <?php else: ?>
                                        <td>Unpaid</td>
                                     <?php endif;?>
                                <td><?php echo $row->step1_date;?></td>
                                </tr>
                                <?php } ?>

                                </table>
                            </div>
                        </div>
                    </td>
-->
                    <td>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Exam Center Stats</b> </div>
                            <div class="panel-body">
                            <table class="TFtable" style="width: 430px; max-height: 350px;">
                                    <thead >
                                    <tr align="left" valign=top>
                                        <th><b>Enterance Exam Center</b></th>
                                        <th><b>Submitted</b></th>
                                        <th><b>Paid</b></th>
                                        <th><b>Unpaid</b></th>
                                    </tr>
                                    </thead>
                                    <?php 
                                        foreach($centerid as $row)
                                        {   
                                    ?>
                                        <tr >
                                            <td>            
                                            <?php 
                                                echo $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$row->eec_id)->eec_name;
                                            $center_id = $row->eec_id;?>
                                            </td>
                                            <?php
                                                $whdata = array('ca_centername' => $center_id); 
                                                $totalsubmitted =  $this->commodel->getnoofrows('admissionstudent_centerallocation',$whdata);
                                            ?>
                                            <td>
                                            <?php echo $totalsubmitted; ?>
                                            </td>
                                            <?php
                                                $selectdata=array('ca_asmid'); 
                                                $getsmids = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$selectdata,$whdata);
                                                $noofpaid = 0;
                                                foreach($getsmids as $row1)
                                                {
                                                    $paidsmid = $row1->ca_asmid;
                                                    $feepaidstat = $this->commodel->get_listrow('admissionstudent_enterencestep','admission_masterid',$paidsmid);
                                                    $data2 = $feepaidstat->result();
                                                    $feepay = 0;
                                                    if(sizeof($data2) > 0)
                                                    {
                                                        $feepay = $feepaidstat->row()->step4_status;
                                                    }
                                                    if($feepay == "1")
                                                        $noofpaid = $noofpaid + 1;
                                                }
                                                ?>
                                            <td><?php echo $noofpaid;?></td>
                                            <td><?php echo $totalsubmitted - $noofpaid;?> </td>
                    
                                        </tr>    
                                    <?php        
                                        }
                                    ?>
                            </table>        
                            </div>
                        </div>
                    </td>   

  <!--          </tr>
            <tr> -->
                <td valign=top>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Course Stats</b> </div>
                            <div class="panel-body">
                            <table class="TFtable">
                                    <thead >
                                    <tr align="left" valign=top>
                                        <th><b>Branch for Graduate Courses</b></th>
                                        <th><b>Submitted</b></th>
                                        <th><b>Paid</b></th>
                                        <th><b>Unpaid</b></th>
                                    </tr>
                                    </thead>
                                    <?php
                                        foreach($allprogramid as $row)
                                        {
                                        ?>
                                        <tr><td>
                                        <?php
                                            echo $prg_name = $this->commodel->get_listrow('program','prg_id',$row->admop_prgname_branch)->row()->prg_name;
                                         ?></td>
                                            <td>                                         
                                            <?php $whdata = array('asreg_program' => $row->admop_prgname_branch);
                                            echo $programreg = $this->commodel->getnoofrows('admissionstudent_registration',$whdata);?></td>
                                            <?php
                                            $studidinprg = $this->commodel->get_listspficarry('admissionstudent_registration','asreg_id','asreg_program',$row->admop_prgname_branch);
                                            $noofpaid = 0;        
                                            foreach($studidinprg as $row1)
                                            {
                                                $studregid = $row1->asreg_id;
                                                $feestat = $this->commodel->get_listrow('admissionstudent_enterencestep','registration_id',$studregid);
                                                $data1 = $feestat->result();
                                                $feepay = 0;
                                                if(sizeof($data1) > 0)
                                                {
                                                    $feepay = $feestat->row()->step4_status;
                                                }                    
                                                if($feepay == "1")
                                                    $noofpaid = $noofpaid + 1;
                                            }?>

                                            <td><?php echo $noofpaid;?></td>
                                            <?php $unpaid = $programreg - $noofpaid;?>
                                            <td><?php echo $unpaid;?></td>
                                        </tr>         
                                        <?php
                                        } ?>
                                        
                            </table>
                            </div>
                        </div>
                </td>

            </tr>
		<tr>
			<td colspan=2>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Exam Center & Course Stats</b> </div>
                            <div class="panel-body">
                            <table class="TFtable" style="height: 350px;">
                                    <thead >
                                    <tr align="left" valign=top>
                                        <th><b>Enterance Exam Center/Course</b></th>
                                        <th><b>Submitted</b></th>
                                        <th><b>Paid</b></th>
                                        <th><b>Unpaid</b></th>
                                    </tr>
                                    </thead>
                            </table>
                            </div>
                        </div>
                    </td>

		</tr>
            </table></center>

            </div><?php $this->load->view('template/footer'); ?></div>
        </body>
</html>

