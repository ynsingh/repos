<!--@name viewsc.php
  @author Rekha Devi Pal(rekha20july@gmail.com)
 -->
<html>
<title>View Study Center</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        <?php // $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table> -->
<style>
    .light{
    background-color:#99B89D;
    padding-left:20px
    
}
</style>
<table width="100%">
            <tr>
            <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup/sc/', "Add Study Center " ,array('title' => ' Add study center Configuration Detail ' , 'class' => 'top_parent'));                   
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Study Center Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdoc#ViewStudyCenter";
                    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		    echo "</td>";
            ?>
      </tr>
 </table>
	    <table width="100%">
            <tr><td>
            <div>
                <?php echo validation_errors('<div class="isa_warning>','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                <div  class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>
            </div>
           </td></tr>
        </table>
        <div class="scroller_sub_page">
        <table class="TFtable">
               <thead>
                        <tr>
              <th>Sr. No.</th>          
             <th>Campus Name</th>
			<th>Address</th>
			<th>Phone</th>
			<th>Fax</th>
			<th>Status</th>
			<th>Date</th>
			<th>Website</th>
			<th>Incharge</th>
                        <th>Action</th>
                        </tr>
            </thead>
            <tbody>
                <?php 
        $scid = 0;
        if(count($result) ): 
                  foreach($result as $row){ 
            $orgid = $this->common_model->get_listspfic1('org_profile','org_id','org_code',$row->org_code)->org_id;
            if($scid !=$orgid){
            ?>
                <tr>
                    <td class = "light"; style="text-align:center; font-weight: bold;" colspan=10;>
                  <!--  <b>Institute Name :</b> -->
                                <?php
                                echo strtoupper($this->common_model->get_listspfic1('org_profile','org_name','org_id',$orgid)->org_name);
                                ?></td>
                </tr>
                        <?php $scid =$orgid;
            $serial_no = 1;
            }?>
              <?php //if( count($this->result) ): ?>
                    <?php 
					//$pre="k";
					//foreach($this->result as $row){ ?>
                    <!--    <tr>
						<td> -->
                        <?php
                     //   $this->uresult = $this->common_model-> get_listspfic1('org_profile','org_name','org_code',$row->org_code);
                      //  $new1= $this->uresult->org_name;
						// $new1=strtoupper($new1);
						 //if(!($new1==$pre)){
							// echo "<br>";
							 
							// echo " <td  style=\"text-align:center; font-weight: bold;\"colspan=\"8\"> $new1";
							 //echo "<br>";
						// }
						// $pre=$new1;?>
                        <!--</td></tr> -->
						 <tr>
						<td><?php echo $serial_no++; ?></td>
                        <td style="text-align: left;"><?php echo $row->sc_name .", (". $row->sc_code .", ". $row->sc_nickname .") "?></td>
                        <td style="text-align: left;"><?php echo $row->sc_address . "<br>".$this->common_model->get_listspfic1('cities','name','id',$row->sc_city)->name.", "
                         . $row->sc_district .", ". $this->common_model->get_listspfic1('states','name','id',$row->sc_state)->name. ", ". $this->common_model->get_listspfic1('countries','name','id',$row->sc_country)->name;?>
                      	<?php echo "<br>". $row->sc_pincode?></td>
                        <td><?php echo $row->sc_phone?></td>
                        <td><?php echo $row->sc_fax?></td>
                        <td><?php echo $row->sc_status?></td>
                        <td><?php echo $row->sc_startdate . "<br><br>". $row->sc_closedate?></td>
                        <td><?php echo $row->sc_website?></td>
                        <td><?php echo $row->sc_incharge ." &nbsp;(Mob-&nbsp;". $row->sc_mobile.")"?></td>

                          
 <td> <?php // echo anchor ('setup/deletesc/' . $row->sc_id , "Delete",array('title' => 'Delete' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
                            &nbsp;<?php  echo anchor ('setup/editsc/' . $row->sc_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); ?></td>
                        </tr>
                    <?php } ?>
                <?php else : ?>
                    <td colspan= "10" align="center"> No Records found...!</td>
                <?php endif;?>
<?php //}}?>
            </tbody>
        </table></div>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>