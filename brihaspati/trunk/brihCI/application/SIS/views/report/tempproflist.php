<!--
    @author Manorama Pal(palseema30@gmail.com) pdf report, bug fix
    @author Akash Rathi(akash92y@gmail.com) html part  

-->

<html>

<img src="uploads/logo/logotanuvas.jpeg" alt="logo" style= " width:100%;height:80px; margin-bottom:15px; " > <br/>
<div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    
                    <th valign="top">Sr.No</th>
                    <th valign="top">Employee Name</th>
                    <th valign="top">DOR</th>
                    <th valign="top">Discipline</th>
                    <th valign="top">Department</th>
                    <th valign="top">Date of Joining <br/> as Prof.</th>
                    <th valign="top">Total Service as Prof. <br/> as on (<?php echo date("Y/m/d");?>)<br/>YY/MM/DD</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;
                    
                    if( count($emplist) ):  ?>
                        <?php    echo "<tr>";
                            echo "<td colspan=7>";
                            echo " <b> Designation : ";
			if(!empty($this->desig))
                                if($this->desig != 'ALL'){
				echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$this->desig)->desig_name ;
                                }
                                else{
                                   echo  $this->desig;    
                                }
                            echo "</b></td>";
                            echo "</tr>";
                         foreach($emplist as $record){
                            echo "<tr>";
                            echo "<td>".$serial_no++."</td>";
                            echo "<td>";
				echo anchor("report/viewfull_profile/{$record->emp_id}",$record->emp_name." ( "."PF No:".$record->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));
	//			. $record-> emp_name.
			    echo "</td>";
                            echo "<td> ".implode('-', array_reverse(explode('-', $record->emp_dor)))."</td>";
                            echo "<td>";
                            if(!empty($record->emp_specialisationid)){
                                echo  $this->commodel->get_listspfic1('subject','sub_name','sub_id',$record->emp_specialisationid)->sub_name;
                                echo  "</td>";
                            }
                            echo "<td>";
                            echo  $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name;
                            echo  "</td>";
                            echo "<td>". implode('-', array_reverse(explode('-', $record->emp_doj)))."</td>";
                            $date1 = new DateTime($record->emp_doj);
                            $date2 = new DateTime();
                            $diff = $date1->diff($date2);
                            echo "<td> ".$diff->y . "&nbsp;&nbsp;&nbsp;&nbsp;" . $diff->m." &nbsp;&nbsp;&nbsp;&nbsp; ".$diff->d."</td>";
                            echo "</tr>"
                    
                        ?>
               
                        <?php }; ?>
                    <?php else : ?>
            <td colspan= "13" align="center"> No Records found...!</td>
                    <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>
        
 <img src="uploads/logo/logo23.png" alt="logo" style= " width:100%;height:30px; margin-top:30px; " > <br/>
</html>
