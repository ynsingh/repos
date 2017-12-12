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
        </head>

        <body>
        
            <div>
                <?php $this->load->view('template/header'); ?>
               
                 <?php $this->load->view('template/menu');?>

		<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
            </div>
         <center>
           <table width="100%">
            <tr>
                    <td valign="top">
                        <div class="panel panel-primary" style="background-color: #D0D0D0;">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Exam Center Stats</b> </div>
                            <div class="panel-body" style="max-height:350px; overflow:auto;">
                            <table class="TFtable">
                                    <thead >
                                    <tr align="left" valign=top>
                                        <th><b>Entrance Exam Center (Center Code)</b></th>
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
                                                 $eecname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$row->eec_id)->eec_name;
                                                 $eeccode = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_code','eec_id',$row->eec_id)->eec_code;
                                                echo $eecname ." ( " .$eeccode ." ) ";
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

                <td valign=top>
                        <div class="panel panel-primary" style="margin-left:10px;background-color: #D0D0D0; max-height:350px; overflow:auto; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Course Stats</b> </div>
                            <div class="panel-body" style="max-height:350px; overflow:auto;">
                            <table class="TFtable" >
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
                                             $prg_name = $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->admop_prgname_branch)->prg_name;
                                             $prg_branch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->admop_prgname_branch)->prg_branch;
                                            echo $prg_name . " ( " . $prg_branch . " ) ";
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
                        <div class="panel panel-primary" style="margin-top:10px;background-color: #D0D0D0;">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Exam Center & Course Stats</b> </div>
                            <div class="panel-body" style="max-height:350px; overflow:scroll;">
                            <table class="TFtable" >
                                    <tr align="left" valign=top>
                                        <th><b>Entrance Exam Center(Center Code)/Course(Branch)</b></th>
                                        <th><b>Submitted</b></th>
                                        <th><b>Paid</b></th>
                                        <th><b>Unpaid</b></th>
                                    </tr>
                                    <?php
                                        foreach($centerid as $row)
                                        {
                                            $centername = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$row->eec_id)->eec_name;
                                            $centercode = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_code','eec_id',$row->eec_id)->eec_code;
                                    ?>
                                        
                                           <tr><td><b><?php echo $centername ." ( ". $centercode ." ) ";?></b></td><td></td><td></td><td></td></tr> 
                                            <?php
                        
                                                $center_id = $row->eec_id;
                                                foreach($allprogramid as $row1)
                                                {
                                                ?>
                                                <?php
                                                    $data = array('ca_centername' => $center_id,'ca_prgid' => $row1->admop_prgname_branch);
                                                ?><tr>
                                                <td><?php 
                                                    $prgname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$row1->admop_prgname_branch)->prg_name;                    
                                                    $prgbranch = $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row1->admop_prgname_branch)->prg_branch;                    
                                                    echo $prgname ." ( " .$prgbranch . " ) "; ?> </td>
                                                <?php  
                                                    $studid = $this->commodel->get_listspficemore('admissionstudent_centerallocation','ca_asmid',$data); ?>
                                                    <td><?php echo sizeof($studid);?></td>
<?php                                              
                                                    $noofpaid = 0;
                                                    foreach($studid as $row2)
                                                    {
                                                        $studregid = $row2->ca_asmid;
                                                        $feestat = $this->commodel->get_listrow('admissionstudent_enterencestep','admission_masterid',$studregid);
                                                        //print_r($feestat);
                                                        $data1 = $feestat->result();
                                                        $feepay = 0;
                                                        if(sizeof($data1) > 0)
                                                        {   
                                                            $feepay = $feestat->row()->step4_status;
                                                        }                 
                                                        if($feepay == "1")
                                                            $noofpaid = $noofpaid + 1;
                                                        }           ?>
                                                    <td><?php echo $noofpaid;?></td>
                                                    <td><?php echo sizeof($studid) - $noofpaid;?></td>
                                                    </tr>
                                                    <?php
                                                    }
                                                ?>
                                                
                                     <?php  }
                                     ?>

                            </table>
                            </div>
                        </div>
                    </td>

		</tr>
            </table></center>

            </div><?php $this->load->view('template/footer'); ?></div>
        </body>
</html>

