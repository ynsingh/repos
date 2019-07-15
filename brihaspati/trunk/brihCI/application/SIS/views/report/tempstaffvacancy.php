<!--
    @author Manorama Pal(palseema30@gmail.com) pdf report
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
    
    <body>
        
<!--     <img src="uploads/logo/logotanuvas.jpeg" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/>    -->
<?php $this->load->view('template/pheader'); ?>

    <div class="scroller_sub_page">
        <table class="TFtable" width="100 %" border=1 frame=void rules=rows>
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Depart/Scheme Name</th>
                    <th>Sanctioned Strength</th>
                    <th>Position</th>
                    <th>Vacancy</th>
                    <th>Remark</th>
                </tr>
            </thead>
            <tbody>
                <?php 
		
		$serial_no = 1;
		$ouoid = 0;
		$opid = 0;
		//initilise the grand total, Uo total, Post total
		$gtotss=0;              $gtotp=0;               $gtotv=0;
                $uototalss=0;           $uototalp=0;            $uototalv=0;
                $posttotalss=0;         $posttotalp=0;          $posttotalv=0;

                $i=0; $ii=0;	

               if( count($records) ):  ?>
                    <?php foreach($records as $record){
//                        print_r($record);
			if($opid !=$record->sp_emppost){
				if($ii>0){
                                        echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> UO CONTROL Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $uototalss. "</td>";
                                        echo "<td>". $uototalp."</td>";
                                        echo "<td>".$uototalv. "</td>";
                                        echo "<td></td>";
                                        echo "</tr>";
                                        $uototalss=0;           $uototalp=0;            $uototalv=0; $ii=0;
                                }
				if($i>0){
                                        echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> Post Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $posttotalss . "</td>";
                                        echo "<td>". $posttotalp . "</td>";
                                        echo "<td>". $posttotalv . "</td>";
                                        echo "<td></td>";
                                        echo "</tr>";
                                        $posttotalss=0;         $posttotalp=0;          $posttotalv=0; $ouoid=0;  
                                }

                        echo "<tr><td colspan=6 align=left><b> Name of the Post : ";
			//echo $record->sp_grppost;
                        echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->sp_emppost)->desig_name;
//			echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->sp_dept)->dept_name;
//			echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->sp_dept)->dept_code ." )";
                        echo "</b></td></tr>";
			$opid = $record->sp_emppost;
                        }
			if($ouoid !=$record->sp_uo){
				if($ii>0){
                                        echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> UO CONTROL Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $uototalss . "</td>";
                                        echo "<td>". $uototalp . "</td>";
                                        echo "<td>". $uototalv . "</td>";
                                        echo "<td></td>";
                                        echo "</tr>";
                                        $uototalss=0;         $uototalp=0;          $uototalv=0;
                                }

			echo "<tr>";
			echo "<td colspan=6 style=\"text-align:center;\">";
			echo " <b> UO CONTROL : ";
			echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->sp_uo)->name;
			echo " ( ".$this->lgnmodel->get_listspfic1('authorities','code','id' ,$record->sp_uo)->code." )";
			echo "</b></td>";
			echo "</tr>";
			$ouoid=$record->sp_uo;
			$serial_no = 1;
			}
			echo "<tr>";
			echo "<td>". $serial_no++ ." </td>";
                        echo "<td> <b>".$this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->sp_dept)->dept_name ;
			echo "(".$this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->sp_dept)->dept_code.")"."</b>" ;
			echo "<br>";
			echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->sp_schemecode)->sd_name ;
			echo "(".$this->sismodel->get_listspfic1('scheme_department','sd_code','sd_id',$record->sp_schemecode)->sd_code .")";
			echo "</td>";
			$sss=$record->sp_sancstrenght;
                        $sp=$record->sp_position;
                        $sv=$record->sp_vacant;
			echo "<td>". $sss ." </td>";
			echo "<td>". $sp."</td>";
			echo "<td>". $sv."</td>";
			echo "<td>". $record->sp_remarks."</td>";
			$gtotss=$gtotss+$sss;                   $gtotp=$gtotp+$sp;                      $gtotv=$gtotv+$sv;
                        $uototalss=$uototalss+$sss;             $uototalp=$uototalp+$sp;                $uototalv=$uototalv+$sv;
                        $posttotalss=$posttotalss+$sss;         $posttotalp=$posttotalp+$sp;            $posttotalv=$posttotalv+$sv;
                        $i++;   $ii++;
?>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
		<?php                           echo "</tr>";
                                        echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> UO CONTROL Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $uototalss. "</td>";
                                        echo "<td>". $uototalp."</td>";
                                        echo "<td>".$uototalv. "</td>";
                                        echo "<td></td>";
                                        echo "</tr>";
		                           echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> Post Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $posttotalss . "</td>";
                                        echo "<td>". $posttotalp . "</td>";
                                        echo "<td>". $posttotalv . "</td>";
                                        echo "<td></td>";
                                        echo "</tr>";
					 echo "<tr>";
                                        echo "<td colspan=2 style=\"text-align:center;\">";
                                        echo " <b> Grand Total : ";
                                        echo "</b></td>";
                                        echo "<td>". $gtotss. "</td>";
                                        echo "<td>". $gtotp."</td>";
                                        echo "<td>".$gtotv. "</td>";
                                        echo "<td></td>";
                                        echo "</tr>";
        ?>
                </tbody>
        </table>
</div>
    <?php $this->load->view('template/footer'); ?>

<!--    <img src="uploads/logo/logo23.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/>-->
</body>
    </html>
