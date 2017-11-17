<!--filename graphicalreports.php  @author sharad singh(sharad23nov@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>IGNTU Admission Dashboard</title>
        <head>
          
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
                ["Dateline", "Submit", { role: "style" } ],
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
        //title: "Datewise Form Submission ",
        width: 430,
        height: 365,
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
        title:'<center>   Fee paid Vs unpaid </center>',
        //title: "Datewise Form Submission ",
        width: 330,
        height: 365,
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
            //echo '["UnPaid",' .$feenotpaid.', "#b87333"], ["Paid",' .$feepaid.', "#b87333"],["Total Submitted",'.$totalsubmitted.',"silver"],["Total Registered",150,"gold"],';
            echo '["UnPaid",' .$feenotpaid.', "#b87333"], ["Paid",' .$feepaid.', "#b87333"],["Total Submitted",'.$totalsubmitted.',"silver"],["Total Registered",'.$totalregistered.',"gold"],';
?>

    /*    ["Copper", 8.94, "#b87333"],
        ["Silver", 10.49, "silver"],
        ["Gold", 19.30, "gold"],
        ["Platinum", 21.45, "color: #e5e4e2"]
    */
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "Various Statistics",
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
                <h3>Welcome <?= $this->session->userdata('username') ?></h3>
                 <?php $this->load->view('template/menu');?>


            </div></br></br></br><br>
         <center>
           <table style="margin-top:50px;">
            <tr>
                    <td>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px ">Form Submit Report (last 10 days report) </div>
                            <div class="panel-body">
                            <div id="columnchart_material" style="width: 430px; height: 400px;"></div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px ">Fees Submission </div>
                            <div class="panel-body">
                            <div id="columnchart_material1" style="width: 330px; height: 400px;"></div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px ">Application Stats </div>
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

