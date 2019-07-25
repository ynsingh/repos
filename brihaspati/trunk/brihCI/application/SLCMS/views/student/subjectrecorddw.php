<!--@filename stu_semmarksdw.pdf  @author sumit saxena(sumitsesaxena@gmail.com) -->

<html>
    <head>
        <title>Welcome </title>

	
<style>
      #watermark { position: fixed; bottom: 0px; top: 10%;left:25%;right: 0px; width: 300px; height: 300px; opacity: .3; }
    </style>
    </head>

    <body>
	<!--<div id="watermark"><img src="assets/images/logogray.jpg" height="100%" width="100%"></div>-->

	
		<img src='<?php echo base_url("uploads/logo/logo2.jpg");?>'  style="width:100%;height:70px;">
<!--	<img src='<?php echo base_url("uploads/logo/niitsikkim.png");?>'  style="width:100%;height:90px;">-->
	<table style="width:100%;">
<tr><th>
	<center>Student Marks</center>
</th></tr></table>
    <!--     <div style="">-->

	<table border=1 style="width:100%;">
	   <tr> <?php //echo $this->sfeeid;?>
               <th style="text-align:justify;background-color:#dbdbdb;color:black;font-size:18px;">Student Marks details</th>
            </tr>
	</table>
	<table  style="width:100%;" border=1>
            <thead style="text-align:justify;background-color:#dbdbdb;color:black;font-size:18px;">
            <tr align="left">
                <th>Program Name(Branch)</th>
                <th>Semester</th>
                <th>Academic Year</th>
                <th>Subject List</th>
            </tr>
        
        </thead>
        <tbody>
 <?php
            foreach($subres as $row){
		//$prgid=$this->commodel->get_listspfic1('student_program','sp_programid','sp_smid',$row->sp_smid)->sp_programid;
		//$sub1 = $this->commodel->get_listspfic1('student_program','sp_subid1','sp_smid',$row->sp_smid)->sp_subid1;
                echo "<tr align=\"center\">";
                /*echo "<td>" . $row->sfee_spid ."</td>";*/
                echo "<td align=left>";
                echo $this->commodel->get_listspfic1('program','prg_name','prg_id',$row->sp_programid)->prg_name ;
                echo " ( ";
                echo $this->commodel->get_listspfic1('program','prg_branch','prg_id',$row->sp_programid)->prg_branch ;
                echo " ) ";
                echo "</td>";
                echo "<td  valign=top style='text-align:left;'>" . $row->sp_semester ."</td>";
		echo "<td valign=top style='text-align:left;'>" . $row->sp_acadyear ."</td>";
		echo "<td valign=top style='text-align:left;'>" . $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid1)->sub_name ;
		if($row->sp_subid2 != ""){
		echo ", ";  
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid2)->sub_name;
		}
		if($row->sp_subid3 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid3)->sub_name;
		}
		if($row->sp_subid4 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid4)->sub_name;
		}
		if($row->sp_subid5 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid5)->sub_name;
		}
		if($row->sp_subid6 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid6)->sub_name;
		}
		if($row->sp_subid7 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid7)->sub_name;
		}
		if($row->sp_subid8 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid8)->sub_name;
		}
		if($row->sp_subid9 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid9)->sub_name;
		}
		if($row->sp_subid10 != ""){
		echo ", "; 
		echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$row->sp_subid10)->sub_name;
		}

		echo "</td>";
                
                echo "</tr>";
                }
        ?>
         </tbody>
        </table>

</body>  
</html>    
