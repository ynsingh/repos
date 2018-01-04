
<!--@filename hodlist.php  @author Manorama Pal(palseema30@gmail.com) 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
        <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
        <table width="100%"><tr colspan="2"><td>
            <?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>List of HOD</b>";
                    echo "</td>";
            ?>
       
      
        </td></tr></table>
        <div class="scroller_sub_page">
            <table class="TFtable" >
                <thead>
                <tr>
                    <th>Department Name</th>
                    <th>HOD Name</th>
                                      
                </tr>
            </thead>
            <tbody>
                <?php 
		$cid = 0;
                               
                if( count($allsc) ):  ?>
                    <?php foreach($allsc as $record){
              
                        if($cid !=$record->scid){    
                            echo "<tr>";
                            echo "<td colspan=2 style=\"text-align:center;\">";
                            echo " <b> Campus Name : ";
                            echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->scid)->sc_name;
                            echo " ( ".$this->commodel->get_listspfic1('study_center','sc_code','sc_id',$record->scid)->sc_code." )";
                            echo "</b></td>";
                            echo "</tr>";
                            $cid=$record->scid;
                        }
                        $hodlist=$this->sismodel->hoduser($record->scid);
                        foreach($hodlist as $record2){
                            echo "<tr>";
                            echo "<td>";
                            echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record2->deptid)->dept_name;
                            echo " ( ". $this->commodel->get_listspfic1('Department','dept_code','dept_id',$record2->deptid)->dept_code ." )";
                            echo "</td>";
                            echo "<td>";
                            $username=$this->lgnmodel->get_listspfic1('edrpuser','username','id',$record2->userid)->username;
                            $firstname=$this->lgnmodel->get_listspfic1('userprofile','firstname','userid',$record2->userid)->firstname;
                            $lastname=$this->lgnmodel->get_listspfic1('userprofile','lastname','userid',$record2->userid)->lastname;
                            if(!empty($firstname)){
                                echo $firstname ." " .$lastname;
                            }
                            else{
                                echo $username;
                            }
                            echo "</td>";
                            echo "</tr>";
                        }    
                                 
                    ?>
                <?php }; ?>
            <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
            <?php endif;?>
            </tbody>
        </table>
        </div><!------scroller div------>
        <div align="center">  <?php $this->load->view('template/footer');?></div>

    </body>
</html>
