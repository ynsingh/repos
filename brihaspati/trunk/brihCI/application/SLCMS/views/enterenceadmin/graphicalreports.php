<!--@filename graphicalreports.php  @author sharad singh(sharad23nov@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>IGNTU Admission Dashboard</title>
        <head>
          
            <style>
                .panel-primary{
                   // margin-left:20px;
                    //margin-right:5px;
                    width:600px;
                    height:600px;
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
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/jsapi" ></script>
        <script type="text/javascript">

            google.load("visualization", "1.1", {packages: ['bar','timeline']});
            //google.load("visualization", "1.1", {packages: ['timeline']});
            google.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                [{label:'Timeline',type:'string'},{type:'number',label:'Submit'}],
<?php

foreach ($chart_data1 as $data1) { 

//$dates = new Date($data1->pdate);
    echo '[' . $data1->pdate . ',' . $data1->pval .  '],';
  //  echo '[' . $dates . ',' . $data1->pval .  '],';
} 
  
  
?>
                ]);
                //alert(data.Timeline);
                var options = {
                    chart: { 
                        title: '',
                        subtitle:'Applicant Registration: <?php echo $min_date . '-' . $max_date; ?> '
/*                          hAxis: {
                            format: 'MMyyyy',
                            gridlines: {count: 15}
                        },
                            vAxis: {
                            gridlines: {color: 'none'},
                            minValue: 0
          }*/

                    }
                };
 
                var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

 
                chart.draw(data, options);
            }
        </script>

       
        </head>

        <body>
        
            <div>
                <?php $this->load->view('template/header'); ?>
                <h3>Welcome <?= $this->session->userdata('username') ?></h3>
                 <?php $this->load->view('template/menu');?>


            </div><br/>
<?php
/*foreach ($chart_data1 as $data) {
    echo '[' . $data->pdate . ',' . $data->pval .  '],';
}
*/
?>         <center>
           <table>
            <tr>
                    <td>
                        <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                            <div class="panel-heading" style="padding:8px; background-color:#0099cc;height:20px ">Form Submit Report (last 10 days report) </div>
                            <div class="panel-body">
                            <div id="columnchart_material" style="width: 600px; height: 570px;"></div>
                            </div>
                        </div>

                    </td>

                </tr>
            </table></center>

            </div><?php $this->load->view('template/footer'); ?></div>
        </body>
</html>

