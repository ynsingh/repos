 <!--@author Manorama Pal(palseema30@gmail.com) pdf report
    @author Akash Rathi(akash92y@gmail.com) html part  

-->
<html>
    
    <head>
        
        <style>
            
            .TFtable {border:0px solid black;}
            
            tr { 
                border: solid;
                border-width: 1px 0;
            }
            </style>
        
    </head>
    
    <body >

	<?php $this->load->view('template/pheader'); ?>
 <!--   <img src="uploads/logo/logo1.png" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/>         -->
        <div class="scroller_sub_page">
    
        <table width="100 %" border=1 frame=void rules=rows>

            <thead>
                <tr>
                    <th>Name of the Post</th>
		    <th>Emp Type</th>
                    <th>Sanctioned Strength</th>
                    <th>Position</th>
                    <th>Vacancy</th>
                </tr>
            </thead>
            <tbody>
                <?php 
		$serial_no = 1;
		$ouoid = 0;
		$odid = 0;
		$ossid=0;
		//initilise the grand total, Uo total, Dept total
		$gtotss=0;		$gtotp=0;		$gtotv=0;
		$uototalss=0;		$uototalp=0;		$uototalv=0;
		$depttotalss=0;		$depttotalp=0;		$depttotalv=0;

		$i=0; $ii=0;

               if( count($records) ):  
		?>
                    <?php foreach($records as $record){
//                        print_r($record);
			if($ouoid !=$record->sp_uo){
				 if($ii>0){
                                        echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> Department Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $depttotalss . "</td>";
                                        echo "<td>". $depttotalp . "</td>";
                                        echo "<td>". $depttotalv . "</td>";
                                        echo "</tr>";
                                        $depttotalss=0;         $depttotalp=0;          $depttotalv=0; $ii=0;
                                }

				if($i>1){
					echo "<tr>";
                                	echo "<td colspan=2 style=\"text-align:center;\">";
	                                echo " <b> UO CONTROL Total : ";
        	                        echo "</b></td>";
					echo "<td>". $uototalss. "</td>";
					echo "<td>". $uototalp."</td>";
					echo "<td>".$uototalv. "</td>";
	                                echo "</tr>";
					$uototalss=0;           $uototalp=0;            $uototalv=0;
				}
				echo "<tr>";
				echo "<td colspan=5 style=\"text-align:center;\">";
				echo " <b> UO CONTROL : ";
				echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->sp_uo)->name;
				echo "</b></td>";
				echo "</tr>";
				$ouoid=$record->sp_uo;
			}
			if($odid !=$record->sp_dept){
				if($ii>0){
					echo "<tr>";
	                                echo "<td colspan=2 style=\"text-align:center;\">";
        	                        echo " <b> Department Total : ";
                	                echo "</b></td>";
                        	        echo "<td>". $depttotalss . "</td>";
                                	echo "<td>". $depttotalp . "</td>";
	                                echo "<td>". $depttotalv . "</td>";
        	                        echo "</tr>";
					$depttotalss=0;         $depttotalp=0;          $depttotalv=0;
				}
        	                echo "<tr><td colspan=5 align=left><b> Department : ";
                	        echo "&nbsp;&nbsp;";
				echo $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->sp_dept)->dept_code;
				echo "<div style=\"text-align:center;\">";
        	                echo "DEPT. OF ".strtoupper($this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->sp_dept)->dept_name);
                	        echo "</b></td></tr>";
				$odid = $record->sp_dept;
				$serial_no = 1;
                        }
			if($ossid !=$record->sp_schemecode){
        	                echo "<tr><td colspan=5 align=left><b> Scheme : ";
                	        echo "&nbsp;&nbsp;";
                       // echo "<div style=\"text-align:center;\">";
                       		echo strtoupper($this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->sp_schemecode)->sd_name);
                        	echo " ( ".$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$record->sp_schemecode)->sd_code ." )";
                        	echo "</b></td></tr>";
                        	$ossid = $record->sp_schemecode;
                        	$serial_no = 1;
                        }
			echo "<tr>";
                        echo "<td>";
                        echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $record->sp_emppost)->desig_name;
                        echo "</td>";

			//echo "<td>". $serial_no++ ." </td>";
			//echo "<td>".$record->sp_grppost ." </td>";
			$sss=$record->sp_sancstrenght;
			$sp=$record->sp_position;
			$sv=$record->sp_vacant;
			echo "<td>".$record->sp_type." </td>";
			echo "<td>". $sss ." </td>";
			echo "<td>". $sp."</td>";
			echo "<td>". $sv ."</td>";
			$gtotss=$gtotss+$sss;              	$gtotp=$gtotp+$sp;               	$gtotv=$gtotv+$sv;
        	        $uototalss=$uototalss+$sss;           	$uototalp=$uototalp+$sp;            	$uototalv=$uototalv+$sv;
	                $depttotalss=$depttotalss+$sss;         $depttotalp=$depttotalp+$sp;          	$depttotalv=$depttotalv+$sv;
			$i++;	$ii++;
?>
                        </tr>
                    <?php }; ?>

                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
	<?php				echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> Department Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $depttotalss . "</td>";
                                        echo "<td>". $depttotalp . "</td>";
                                        echo "<td>". $depttotalv . "</td>";
                                        echo "</tr>";
					echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> UO CONTROL Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $uototalss. "</td>";
                                        echo "<td>". $uototalp."</td>";
                                        echo "<td>".$uototalv. "</td>";
                                        echo "</tr>";
					echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> Grand Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $gtotss. "</td>";
                                        echo "<td>". $gtotp."</td>";
                                        echo "<td>".$gtotv. "</td>";
                                        echo "</tr>";
	?>

                </tbody>
        </table>
	<?php $this->load->view('template/footer'); ?>
<!--    <img src="uploads/logo/logo1.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/> --> 
</div>
    </body>
</html>
