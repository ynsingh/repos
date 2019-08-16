<!--
    @author Manorama Pal(palseema30@gmail.com) pdf report, bug fix
    @author Akash Rathi(akash92y@gmail.com) html part  

-->

<html>
<body>    
<?php $this->load->view('template/pheader'); ?>
<!--    <img src="uploads/logo/logotanuvas.jpeg" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/>-->
<div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th align="left">Department Name</th>
                    <th>HOD Name</th>
                                      
                </tr>
            </thead>
            <tbody>
                <?php 
		$cid = 0;
                $did=0;
                $uoprid = 0;               
                if( count($allsc) ):  ?>
                    <?php foreach($allsc as $record){
			if($record->hl_uopid != $uoprid) {
				$uoprid = $record->hl_uopid;
				echo "<tr>";
	                        echo "<td colspan=2>";
				echo "<b>";
				echo $this->lgnmodel->get_listspfic1('authorities','name','priority',$record->hl_uopid)->name;
                                echo " ( ";
                                echo $this->lgnmodel->get_listspfic1('authorities','code','priority',$record->hl_uopid)->code;
                                echo " ) ";
				echo "</b>";
				echo "</td>";
				echo "</tr>";
			}
			echo "<tr>";
                        echo "<td>";
				echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->hl_deptid)->dept_name;
				echo " ( ";
				echo $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record->hl_deptid)->dept_code;
				echo " ) ";
			echo "</td>";
			echo "<td colspan=2>";
				if(!empty($record->hl_empcode)){
				$name=$this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$record->hl_empcode);
				if(!empty($name)){
					echo $name->emp_name;
				}
				}
				//echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_code',$record->hl_empcode)->emp_name;
				echo " ( ";
				echo $this->lgnmodel->get_listspfic1('edrpuser','username','id',$record->hl_userid)->username;
				echo " ) ";
                        echo "</td>";
                        echo "</tr>";
           /* 
                        if($cid !=$record->hl_scid){    
                            echo "<tr>";
                            echo "<td colspan=2 style=\"text-align:center;\">";
                            echo " <b> Campus Name : ";
                            echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->hl_scid)->sc_name;
                            echo " ( ".$this->commodel->get_listspfic1('study_center','sc_code','sc_id',$record->hl_scid)->sc_code." )";
                            echo "</b></td>";
                            echo "</tr>";
                            $cid=$record->hl_scid;
                        }
                        $hodlist=$this->sismodel->hoduser($record->hl_scid);
                        foreach($hodlist as $record2){
                        if($did !=$record2->hl_deptid){    
                            echo "<tr>";
                            echo "<td colspan=2 ><b>";
                            echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record2->hl_deptid)->dept_name;
                            echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record2->hl_deptid)->dept_code ." )";
                            echo "</b></td>";
                            echo "</tr>";
                        
                            $did=$record2->hl_deptid;
                        } 
                        echo "<tr>";
                        echo "<td > </td>";
                        echo "<td>";
                        $username=$this->lgnmodel->get_listspfic1('edrpuser','username','id',$record2->hl_userid)->username;
                        echo $username;
                        echo "</td>";
                        echo "</tr>";

                        }    
                       */          
                    ?>
                <?php }; ?>
            <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
            <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>   
      <?php //$this->load->view('template/footer'); ?>
<!-- <img src="uploads/logo/logo23.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/>-->
</body>
</html>
