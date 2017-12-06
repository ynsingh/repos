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

    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawFormChart);
    google.charts.setOnLoadCallback(drawFeeChart);
    google.charts.setOnLoadCallback(drawStatChart);

    function drawFormChart() {
        var data = google.visualization.arrayToDataTable([
                ['Dateline', 'Submit', { role: "style" } ],
<?php
                foreach ($chart_data1 as $data1) {
                    echo '["' . $data1->pdate . '",' . $data1->pval . ',"" ],';
}?>
        ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title:'Applicant Registration: <?php echo $min_date . '-' . $max_date; ?> ',
        vAxis: {title: 'No of Applicants'},
        hAxis: {title: 'Timeline',slantedText: true,slantedTextAngle: 45},

        //title: "Datewise Form Submission ",
        width: 800,
        height: 400,
        bar: {groupWidth: "35%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_material"));
      chart.draw(view, options);    
    }


    function drawFeeChart() {
        var data = google.visualization.arrayToDataTable([
            ["Fees", "", { role: "style" } ],
<?php 
            echo '["UnPaid",' .$feenotpaid.', "#b87333"], ["Paid",' .$feepaid.', "#b87333"]';
?>
        ]);
      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);
        
      var options = {
        title:'   Fee paid Vs unpaid ',
        //title: "Datewise Form Submission ",
        width: 430,
        height: 400,
        bar: {groupWidth: "25%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_material1"));
      chart.draw(view, options);    
    }

function drawStatChart() {
      var data = google.visualization.arrayToDataTable([
        ["Element", "", { role: "style" } ],
<?php
            //echo '["UnPaid",' .$feenotpaid.', "#b87333"], ["Paid",' .$feepaid.', "#b87333"],["Total Registered",'.$totalsubmitted.',"silver"],["Total Submitted",'.$totalregistered.',"gold"],';
            echo '["UnPaid",' .$feenotpaid.', "#b87333"], ["Paid",' .$feepaid.', "black"],["Total Submitted",'.$totalregistered.',"gold"],["Total Registered",'.$totalsubmitted.',"silver"]';
?>
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "STATS",
        width: 430,
        height: 365,
        bar: {groupWidth: "35%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.BarChart(document.getElementById("columnchart_material2"));
      chart.draw(view, options);

  }
    
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
                    <td>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Form Submit Report</b>  </div>
                            <div class="panel-body">
                            <div id="columnchart_material" style="width: 800px; height: 400px;"></div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Fees Submission</b> </div>
                            <div class="panel-body">
                            <div id="columnchart_material1" style="width: 430px; height: 400px;"></div>
                            </div>
                        </div>
                    </td>
            </tr>
            <tr>
                    <td valign=top>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0;">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Recent Applications</b> </div>
                            <div class="panel-body" style="overflow:scroll;width: 800px; height: 400px">
                            <?php //print_r($registeredapplicant);?>
                                <table class="TFtable" style="width: 800px; height: 400px;">
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

                    <td>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px "><b>Application Stats</b> </div>
                            <div class="panel-body">
                            <div id="columnchart_material2" style="width: 430px; height: 400px;"></div>
                            </div>
                        </div>
                    </td>   

            </tr>

            </table></center>

            </div><?php $this->load->view('template/footer'); ?></div>
        </body>
</html>

