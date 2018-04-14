<!--@name disciplinewiselist.php 
  @author Om Prakash(omprakashkgp@gmail.com)
  @author Manorama Pal(palseema30@gmail.com)
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="http://172.26.82.20/~guest/brihCI/assets/css/tablestyle.css"> 
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
                document.body.innerHTML = "<html><head><title></title></head><body><img src='<?php echo base_url(); ?>uploads/logo/logotanuvas.jpeg' alt='logo' style='width:100%;height:100px;' >"+" <div style='width:100%;height:100px;'>  " + printContents + "</div>"+"</body>";
                window.print();     
                document.body.innerHTML = originalContents;
            }
        </script>     
       
    </head>
    <body>

    <?php $this->load->view('template/header'); ?>
	<form action="<?php echo site_url('report/disciplinewiselist');?>" id="myForm" method="POST" class="form-inline">
 	<table width="100%" border="0">
            <tr style="font-weight:bold;">
               <td>  Select Campus
                    <select name="camp" id="camp" style="width:400px;">
                      <option value="" disabled selected>-------- Select Campus name------</option>
                      <?php
                      foreach( $this->sc as $row ){
 ?>
                      <option value="<?php echo $row->sc_id; ?>" > <?php echo $row->sc_name ." ( ".$row->sc_code ." )"; ?></option>
<?php }?>
                    </select>
                </td>

                 <td><div> Select Subject
                    <select name="subj" id="subj" style="width:400px;">
                      <option selected="selected" disabled selected>-------------Select Subject name-----------</option>
			 <?php
                      foreach( $this->sub as $row ){
 ?>
                      <option value="<?php echo $row->sub_id; ?>" > <?php echo $row->sub_name ." ( ".$row->sub_code ." )"; ?></option>
<?php }?>

                    </select>

                </td>


                <td><input type="submit" name="filter" id="crits" value="Search" /></td>
		<td>
            <img src='<?php echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
        </td>
            </tr>
        </table>
</form>
    <table width="100%">
    
       <tr>
<!--	<td>
            <img src='<?php //echo base_url(); ?>uploads/logo/print1.png' alt='print'  onclick="javascript:printDiv('printme')" style='width:30px;height:30px;' title="Click for print" >  
        </td>        -->
      <!-- <div> -->
	<?php
       // echo "<td align=\"center\" width=\"100%\">";
//        echo "<b>Discipline Wise List Details</b>";
//	if((!empty($this->camp)) && (!empty($this->subj))){
//	echo  " ( ".$this->commodel->get_listspfic1('study_center','sc_name','sc_id' ,$this->camp)->sc_name."  -  ".$this->commodel->get_listspfic1('subject','sub_name','sub_id' ,$this->subj)->sub_name." ) ";
//	}
  //      echo "</td>";
       // echo "<td>";
            ?>
        <div>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
         <?php echo form_error('<div class="isa_error">','</div>');?>
          <?php if(isset($_SESSION['success'])){?>
              <div class="isa_success"><?php echo $_SESSION['success'];?></div>
          <?php
          };
          ?>
        <?php if  (isset($_SESSION['err_message'])){?>
	     <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
	<?php
	};
	?>
	</div>
     </td></tr>
 </table>
        <div id="printme" align="left" style="width:100%;"> 
	 <table width="100%">

       <tr colspan="2">
	<?php
        echo "<td align=\"center\" width=\"100%\">";
        echo "<b>Discipline Wise List Details</b>";
        if((!empty($this->camp)) && (!empty($this->subj))){
        echo  " ( ".$this->commodel->get_listspfic1('study_center','sc_name','sc_id' ,$this->camp)->sc_name."  -  ".$this->commodel->get_listspfic1('subject','sub_name','sub_id' ,$this->subj)->sub_name." ) ";
        }
        echo "</td>";
?>
</tr>
 </table>

        <div class="scroller_sub_page">
        <table class="TFtable" 
            <thead>
                <tr>
		<th> Sr.No </th>
		<th> Name </th>
		<th> Designation </th>
		<th> Department Name </th>
	</thead>
	<tbody>
	<?php  $count = 0;
		$sbid=0;
	 if(count($this->result)) {
		foreach ($this->result as $row) {
		   if($sbid!=$row->emp_specialisationid){
	             ?>
                     <tr>
			<?php echo "<td colspan=4><b>Major subject :";
			echo $this->commodel->get_listspfic1('subject','sub_name','sub_id' ,$row->emp_specialisationid)->sub_name; 
			echo "</td>";
			echo "</tr>";
			$sbid = $row->emp_specialisationid; 
			$count = 0;
		       } ?>
		     <tr>
			 <td><?php echo ++$count; ?> </td>
                         <td><?php echo $row->emp_name; ?> </td>
			 <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$row->emp_desig_code)->desig_name; ?> </td>
			 <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$row->emp_dept_code)->dept_name; ?> 
			( <?php echo $this->commodel->get_listspfic1('Department','dept_code','dept_id',$row->emp_dept_code)->dept_code; ?> ) </td>
		     </tr>
	<?php  }; 
	}else{
  	?>  
        <tr><td colspan= "13" align="center"> No Records found...!</td></tr>
       <?php }?>
	</tbody>
        </table>
        </div><!------scroller div------> 
        </div><!------print div------>
        
  <p> &nbsp; </p>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>
</html>

 
