
<!--@filename positionsummary.php  @author Manorama Pal(palseema30@gmail.com) 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        
        <table width="100%"><tr colspan="2"><td>
            <?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Position-Summary</b>";
                    echo "</td>";
            ?>
       
      
        </td></tr></table>
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Name of the Post</th>
                    <th>Sanctioned strength</th>
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
        <div align="center">  <?php $this->load->view('template/footer');?></div>

    </body>
</html>

