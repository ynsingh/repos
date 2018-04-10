
<!--@filename positionsummary.php  @author Manorama Pal(palseema30@gmail.com) 
    @filename positionsummary.php  @author Neha Khullar(nehukhullar@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
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
        </script>     
        </head>
    <body>
     <?php $this->load->view('template/header'); ?>
  <form action="<?php echo site_url('report/positionsummary');?>" id="myForm" method="POST" class="form-inline">
         <table width="100%" border="0">
            <tr style="font-weight:bold;">
                <td>  Select Type
                    <select name="wtype" id="wtype">
                     <!-- <option value="" disabled selected>--------Select Working Type-------</option> -->
                      <option value="All">All</option>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>

                    </select>

                </td>
                <td><input type="submit" name="filter" id="crits" value="Search" /></td>
            </tr>
        </table>
        
<table width="100%">
       <tr colspan="2"><td>
        <td>
            <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
        </td>       
       <div>
       <?php
       echo "<td align=\"center\" width=\"100%\">";
       echo "<b>".$this->wtype." Position-Summary</b>";
       echo "</td>";
       ?>
       
        </div>
        </td></tr></table>
         <div id="printme" align="left" style="width:100%;">
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Name of the Post</th>
                    <th>Sanctioned Strength</th>
                    <th>Position</th>
                    <th>Vacancy</th>
                   
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
		$totalss= 0;
                $totalsp= 0;
		$totalsv= 0;
		$rc= array();
		$poid1=0;$ss1=0;$sp1=0;$sv1=0;
                if( count($records) ):  ?>
                	<?php foreach($records  as $record) { 
				$poid =$record->sp_emppost;
				$ss = $record->sp_sancstrenght;
				$sp = $record->sp_position;
				$sv = $record->sp_vacant;
				if ($poid == $poid1){
					$ss = $ss1 + $ss;
					$sp = $sp1 + $sp;
					$sv = $sv1 + $sv;
				}
			
				$rc[$poid] =array('poid' => $poid,'ss'=> $ss,'sp'=> $sp,'sv' => $sv); 
				$poid1 = $poid;
				$ss1 = $ss;
				$sp1 = $sp;
				$sv1 = $sv;
                	}; 

			foreach($rc  as $rc1) {
                            echo "<tr><td>";
                            echo $serial_no++;;
                            echo "</td><td>";
                            echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$rc1['poid'])->desig_name;
                            echo "</td><td>";
                            echo $rc1['ss'];
                            echo "</td><td>";
                            echo $rc1['sp'];
                            echo "</td><td>";
                            echo $rc1['sv'];
                            echo "</td></tr>";
                        
                            $totalss = $totalss+$rc1['ss'];
                            $totalsp = $totalsp+$rc1['sp'];
                            $totalsv = $totalsv+$rc1['sv'];

			}
                        
                 else : ?> 
                    <td colspan= "13" align="center"> No Records found...!</td> 
                <?php endif;?>
               
                    <tr>
                        <td></td>
                        <td><b>Grand Total:</b></td>
                        <td><b><?php echo $totalss;?></b></td>
                        <td><b><?php echo $totalsp;?></b></td>
                        <td><b><?php echo $totalsv;?></b></td>
                    </tr>
                </tbody>
        </table>
        </div><!------scroller div------>
        </div><!------print div------>
	<p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
  
    </body>
</html>

