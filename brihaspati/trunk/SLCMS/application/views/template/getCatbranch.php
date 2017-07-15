
<?php
$name = $_GET['catbranch'];
if($name != " ")
{		
         $list = $this->Common_model->get_listspfic2('admissionmeritlist','id','branchname','course_name',$name,'branchname');
	 foreach($list as $datas): ?>   
      		  <option  id='getbranch' value="<?php echo $datas->id;?>"><?php echo $datas->branchname; ?></option>
<?php    endforeach;
 } ?>   

