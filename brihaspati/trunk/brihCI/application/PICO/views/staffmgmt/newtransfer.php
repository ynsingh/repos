<!--@filename stafftransferlist.php  @author Manorama Pal(palseema30@gmail.com) -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">  
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
        <style>
            /*----- Tabs -----*/
.tabs {
	width:100%;
	display:inline-block;
}

/*----- Tab Links -----*/
/* Clearfix */
	.tab-links:after {
	display:block;
	clear:both;
	content:'';
}

.tab-links li {
	margin:0px 5px;
	float:left;
	list-style:none;
}

.tab-links a {
	padding:9px 15px;
	display:inline-block;
	border-radius:3px 3px 0px 0px;
	background:#7FB5DA;
	font-size:16px;
	font-weight:600;
	//color:#4c4c4c;
	transition:all linear 0.15s;
}

.tab-links a:hover {
	background:#a7cce5;
	text-decoration:none;
}

li.active a, li.active a:hover {
	background:#fff;
        //color:#4c4c4c;
}

/*----- Content of Tabs -----*/
.tab-content {
	//padding:15px;
	//border-radius:3px;
	//box-shadow:-1px 1px 1px rgba(0,0,0,0.15);
	//background:#fff;
}

.tab {
	display:none;
}

.tab.active {
	display:block;
}
        </style>
        <script>
         jQuery(document).ready(function() {
	jQuery('.tabs .tab-links a').on('click', function(e) {
		var currentAttrValue = jQuery(this).attr('href');
                alert("1=1=="+currentAttrValue);

		// Show/Hide Tabs
		jQuery('.tabs ' + currentAttrValue).show().siblings().hide();
                alert("1==2="+currentAttrValue);

		// Change/remove current tab to active
		jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
                alert("1=3=="+currentAttrValue);

		e.preventDefault();
	});
});   
        </script>
    </head>
    <body> 
            <?php $this->load->view('template/header'); ?>
        
        <div class="tabs">
	<ul class="tab-links">
		 <li class="active"><a href="<?php site_url('staffmgmt/stafftransfer','id="tab1"') ;?>">Single Transfer</a></li>
		<li><a href="<?php site_url('staffmgmt/stafftransfer','id="tab1"') ;?>">Single Promotion</a></li>
		<li><a href="<?php site_url('staffmgmt/stafftransfer','id="tab1"') ;?>">Mutual Transfer Order</a></li>
		<li><a href="<?php site_url('staffmgmt/stafftransfer','id="tab2"') ;?>">Mutiple Transfer in Single Order</a></li>
                <li><a href="<?php site_url('staffmgmt/stafftransfer','id="tab1"') ;?>">Work Order</a></li>
                <li><a href="<?php site_url('staffmgmt/stafftransfer','id="tab1"') ;?>">Transfer with Post and Budget</a></li>
              
	</ul>

	<div class="tab-content">
		<div id="tab1" class="tab active">
			 <table style="margin-left:0%;border:0 solid gray;width:100%;">
                   
                    <tr>
                        <td><label for="registrarname" style="font-size:15px;">Registrar Name<font color='Red'>*</font></label>
                           <div><input type="text" name="registrarname" class="keyup-characters" value="<?php echo isset($_POST["registrarname"]) ? $_POST["registrarname"] : ''; ?>" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required">
                           </div>    
                        </td>
                        <td><label for="designation" style="font-size:15px;">Designation<font color='Red'>*</font></label>
                            <div><select name="designation" required="required"> 
                                <option value="">---------------- Select Designation -------------</option>
                                <option value="Registrar">Registrar</option>
                                <option value="Registrar Incharge">Registrar Incharge</option>                     
                            </select></div>
                        </td>
                        <td><label for="usono" style="font-size:15px;">University Sanction Order No<font color='Red'>*</font></label>
                            <div><input type="text" name="usono"  value="<?php echo isset($_POST["usono"]) ? $_POST["usono"] : ''; ?>" size="40"  required="required">
                            </div>    
                        </td>
                    </tr>
                    <tr>
                        <td ><label for="rcno" style="font-size:15px;">RC No<font color='Red'>*</font></label>
                            <div><input type="text" name="rcno" value="<?php echo isset($_POST["rcno"]) ? $_POST["rcno"] : ''; ?>" size="40"   required="required"></div>
                        </td> 
                        <td><label for="referenceno" style="font-size:15px;">Reference No</font></label>
                            <div><input type="text" name="referenceno" value="<?php echo isset($_POST["referenceno"]) ? $_POST["referenceno"] : ''; ?>" size="38" ></div>
                        </td> 
                        <td></td>
                    </tr>
                    <tr></tr>
                    <tr><td colspan="5"><hr></td></tr>
                    <tr><td><font color='blue'> Employee Transfer from</font</td></tr>
                    <tr>
                        <td><label for="campus" style="font-size:15px;">Campus Name <font color='Red'>*</font></label>
                        <div> <select id="campfrom" style="width:350px;" name="campusfrom" required> 
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
                            <?php endforeach; ?>
                      
                            </select></div>
                        </td>
                        <td><label for="uocfrom" style="font-size:15px;">University Officer Control From<font color='Red'>*</font></label>
                            <!--<div><select name="uocontrol" style="width:300px;"id="uocid" required> 
                            <option selected="selected" disabled selected>---------------- University Officer Control ---------------</option> -->
                            <div><select name="uocfrom" id="uocid" required> 
                                <option value="">------- Select University Officer Control --------</option>
                                
                                <?php foreach($this->uoc as $ucodata): ?>	
                                    <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
                                <?php endforeach; ?>
                            </select></div>
                        </td>
                        <td><label for="department" style="font-size:15px;">Department From<font color='Red'>*</font></label>
                            <div><select required name="deptfrom"  id="scid"> 
                                <option selected="selected" disabled selected >----------------- Select Department --------------</option>
                            </select></div>
                        </td>
                    </tr> 
                    <tr>
                        <td><label for="schemecode" style="font-size:15px;">Scheme Name From<font color='Red'>*</font></label>
                            <div><select required name="schemfrom" id="schmid"> 
                            <option selected="selected" disabled selected>-------------- Select Scheme ------------------</option>
                        
                            </select><div>
                        </td>
                        
                        <td><label for="employeetype" style="font-size:15px;">Working Type From<font color='Red'>*</font></label>
                            <div><select name="employeetype" id="emptype" required="required"> 
                                <option value="">------------ Select Employee Type ---------------</option>
                                <option value="Teaching">Teaching</option>
                                <option value="Non Teaching">Non Teaching</option>                     
                            </select></div>
                        </td>
                                               
                   <!-- </tr>
                    <tr>-->
                        <td><label for="designation" style="font-size:15px;">Designation From<font color='Red'>*</font></label>
                            <div><select name="desigfrom" id="desigid" required> 
                                <option selected="selected" disabled selected>-------------- Select Designation -----------------</option>
                                <!--    <//?php foreach($this->desig as $desigdata): ?>	
                                <option value="<//?php echo $desigdata->desig_id; ?>"><//?php echo $desigdata->desig_name; ?></option> 
                                <//?php endforeach; ?>-->
                                </select></div>
                        </td>
                    </tr>
                    <tr>
                        
                        <td><label for="empname" style="font-size:15px;">Employee Name<font color='Red'>*</font></label>
                            <div><select name="empname" id="empnameid"> 
                                <option value="">--------- Select Employee Name --------------</option>
                               <!-- <//?php foreach($this->usrlist as $usrdata): ?>	
                                    <option value="<//?php echo $usrdata->emp_id; ?>"><//?php echo $usrdata->emp_name; ?></option> 
                                <//?php endforeach; ?> -->
                                                   
                            </select></div>
                        </td>
                         <td><label for="postfrom" style="font-size:15px;">Post From<font color='Red'>*</font></label>
                            <div><input type="text" name="postfrom" id="postfrom"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>
                         <td><label for="emppt" style="font-size:15px;">Employee Type<font color='Red'>*</font></label>
                             <div><input type="text" name="empptfrom" id="emppt"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                           <!-- <div><select id="emppt" name="empptfrom" required style="width:300px;"> 
                            <option selected="selected" disabled selected>-------- Select Employee Type ------</option>
                            <option value="Permanent">Permanent</option>
                            <option value="Temporary">Temporary</option>
                            </select><div> -->
                        </td>
                    </tr>
                    <tr></tr>
                    <tr><td colspan="5"><hr></td></tr>
                    <tr><td><font color='blue'> Employee Transfer To</font></td></tr>
                    <tr>
                        <td><label for="campus" style="font-size:15px;">Campus Name <font color='Red'>*</font></label>
                        <div> <select id="camp" style="width:350px;" name="campus" required> 
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>	
   				<option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option> 
                            <?php endforeach; ?>
                      
                            </select></div>
                        </td> 
                        <td><label for="uocontrol" style="font-size:15px;">University Officer Control To<font color='Red'>*</font></label>
                            <div><select name="uocontrolto" id="uocidto" required> 
                                <option value="">------- Select University Officer Control ---------</option>
                                
                                <?php foreach($this->uoc as $ucodata): ?>	
                                    <option value="<?php echo $ucodata->id; ?>"><?php echo $ucodata->name; ?></option> 
                                <?php endforeach; ?>
                            </select></div>
                        </td>
                        <td><label for="dept" style="font-size:15px;">Department To<font color='Red'>*</font></label>
                            <div><select required name="deptto" id="scidto"> 
                               <option value="">----------------- Select Department ------------</option>
                            </select></div>
                        </td>
                        
                    </tr>
                    <tr>
                        <td><label for="schemecode" style="font-size:15px;">Scheme Name To<font color='Red'>*</font></label>
                            <div><select required name="schemto" id="schmidto"> 
                            <option selected="selected" disabled selected>----------------- Select Scheme ------------------</option>
                        
                            </select><div>
                        </td>
                        <td><label for="ddo" style="font-size:15px;">Drawing and Disbursing Officer To<font color='Red'>*</font></label>
                            <div><select name="ddo" id="ddoid" required style="width:350px;"> 
                            <option selected="selected" disabled selected>--------- Drawing and Disbursing Officer-----</option>
                            </select></div>
                        </td>
                        <td><label for="payband" style="font-size:15px;">Pay Band To<font color='Red'>*</font></label>
                        <div><select name="payband" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------------------ Select Pay Band -------------</option>
                        <?php foreach($this->salgrd as $salgrddata): ?>	
                            <option value="<?php echo $salgrddata->sgm_id; ?>"><?php echo $salgrddata->sgm_name."(". $salgrddata->sgm_min."-".$salgrddata->sgm_max.")".$salgrddata->sgm_gradepay; ?>
                            </option> 
 			<?php endforeach; ?>
                       
                        </select></div>
                        </td>
                        
                    </tr>
                    <tr>
                        
                        <td><label for="employeetype" style="font-size:15px;">Working Type To<font color='Red'>*</font></label>
                            <div><select name="emptypeto" id="emptypeto" required="required"> 
                                <option value="">------------ Select Employee Type ---------------</option>
                                <option value="Teaching">Teaching</option>
                                <option value="Non Teaching">Non Teaching</option>                     
                            </select></div>
                        </td>
                        <td><label for="group" style="font-size:15px;">Group To<font color='Red'>*</font></label>
                        <div><select name="group" id="grpid" required style="width:300px;"> 
                        <option selected="selected" disabled selected>------------ Select Group ---------</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                        </select></div>
                        </td>
                        <td><label for="desigto" style="font-size:15px;">Designation To<font color='Red'>*</font></label>
                            <div><select required name="desigto" id="desigidto"> 
                               <option value="">--------------- Select Designation ---------------</option>
                              
                            </select></div>
                        </td>
                        
                        <!--<td><label for="postfrom" style="font-size:15px;">Post From<font color='Red'>*</font></label>
                            <div><input type="text" name="postfrom" id="postfrom"  readonly class="keyup-characters" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>
                        </td>-->
                    </tr>
                    <tr>
                        
                        <td><label for="postto" style="font-size:15px;">Post To<font color='Red'>*</font></label>
                            <div><select required name="postto" id="postto"> 
                               <option value="">--------------- Select Post ------------------------</option>
                            <!--<div><input type="text" name="postto" class="keyup-characters" value="<?php echo isset($_POST["postto"]) ? $_POST["postto"] : ''; ?>" size="40"  required pattern="[a-zA-Z0-9 ]+" required="required"></div>-->
                        </td>
                        <td><label for="emptype" style="font-size:15px;">Employee Type<font color='Red'>*</font></label>
                            <div><select id="vtypeid" name="vacanttype" required style="width:300px;"> 
                            <option selected="selected" disabled selected>-------- Select Employee Type ------</option>
                            <!--<option value="Permanent">Permanent</option>
                            <option value="Temporary">Temporary</option>-->
                            </select><div>
                        </td> 
                        <td><label for="dateofrelief" style="font-size:15px;">Date of Relieve</label>
                            <div><input type="text" name="dateofrelief" id="Dateofrelief"  value="<?php echo isset($_POST["dateofrelief"]) ? $_POST["dateofrelief"] : ''; ?>" size="40" />
                            </div>
                        </td>
                                               
                    </tr>
                    <tr>
                        <td><label for="expdoj" style="font-size:15px;">Expected Date of joining</label>
                            <div><input type="text" name="expdoj" id="expDateofjoin"  value="<?php echo isset($_POST["expDateofjoin"]) ? $_POST["expDateofjoin"] : ''; ?>" size="40" /></div>  
                        </td> 
                        <td><label for="subject" style="font-size:15px;">Subject<font color='Red'>*</font></label>
                            <div><textarea name="subject" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea><div>
                        </td>
                        <td><label for="ordercontent" style="font-size:15px;">Order Content<font color='Red'>*</font></label>
                            <div><textarea name="ordercontent" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea>
                            </div>
                        </td>
                                               
                    </tr>
                    <tr>
                        <td><label for="ttadetail" style="font-size:15px;">TTA Detail</label>
                            <div><textarea name="ttadetail" rows="5" cols="50"  placeholder="Enter text here...."></textarea></div>
                        </td>
                        <td><label for="emailsentto" style="font-size:15px;">Email Sent To</label>
                            <div><textarea name="emailsentto" rows="5" cols="50" required pattern="[a-zA-Z0-9 ]+" required="required" placeholder="Enter text here...."></textarea></div>
                        </td>
                    </tr> 
                    <tr style="background-color:#2a8fcf;text-align:left;height:40px;">
                        <td colspan="6">   
                            <button name="stafftransfer" >Submit</button>
                            <input type="reset" name="Reset" value="Clear"/>
                        </td>
           
                    </tr>
                </table>
                </div>  
		 <div id="" class="tab">
                    <table   style="font-size:13px;" > 
                        <tr><td align="left" ><b>Note : The file extension should be in csv. The format of Staff transfer order file is --</b>
                        </td></tr>
                        <tr><td align="left"><br/><b>Registrar Name &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;Designation &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; USO No &nbsp; RC No &nbsp; Reference No &nbsp;&nbsp;</b>
                          <br/> Dr. A B SINGH &nbsp; &nbsp;Registrar Incharge &nbsp;&nbsp;&nbsp;34567 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BC8776 &nbsp;&nbsp; C898000 &nbsp;&nbsp;
                            <br/><br/>
                            <tr><td align="left"><b>Employee Transfer To Details:</b> </td></tr>    
                            <tr><td align="left"><b>Employee PF No. &nbsp;&nbsp;&nbsp;&nbsp &nbsp;&nbsp;Campus Code &nbsp;&nbsp; Authority(UCO)Code &nbsp;&nbsp; Department Code &nbsp;&nbsp;  Scheme Code &nbsp;&nbsp; DDO Code &nbsp;&nbsp; AGP Code &nbsp;&nbsp; Working Type &nbsp;&nbsp; Group &nbsp;&nbsp;Designation Code&nbsp;&nbsp;Shown against Post Code&nbsp;&nbsp; Employee Type </b> <br/>
                                V1234&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CU001&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MVC-AGB
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;1007&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D149
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;16 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Teaching
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Permanent<br/>
                                <b>Date of relieve &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date of Joining&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Subject
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Order Content&nbsp; &nbsp;&nbsp; &nbsp;TTA Detail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email Sent To</b>
                                <br/>2018-05-30 00:00:00 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2018-06-30 00:00:00 &nbsp;&nbsp;&nbsp;TRANSFER
                                &AElig;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;order content&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TTA Detail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;palseema30@gmail.com
                            </td></tr>
                                                                                   
                            </td></tr>
           
                        </table>
                        <br/><table>
                        <?php echo form_open_multipart('upl/uploadtransferorder');?>
                            <tr>
                                <td align="left">
                                    <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                                    <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                                    <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" />
                                    <input type='submit' name='importdata' id="btnUpload"  value='upload'/></div>
                                </td> 
                                 
                            </tr>
                        </form>
                  </table> 
                </div>
<!--
		<div id="tab2" class="tab">
			<p>Tab #2 content goes here!</p>
			<p>Donec pulvinar neque sed semper lacinia. Curabitur lacinia ullamcorper nibh; quis imperdiet velit eleifend ac. Donec blandit mauris eget aliquet lacinia! Donec pulvinar massa interdum risus ornare mollis. In hac habitasse platea dictumst. Ut euismod tempus hendrerit. Morbi ut adipiscing nisi. Etiam rutrum sodales gravida! Aliquam tellus orci, iaculis vel.</p>
		</div>

		<div id="tab3" class="tab">
			<p>Tab #3 content goes here!</p>
			<p>Donec pulvinar neque sed semper lacinia. Curabitur lacinia ullamcorper nibh; quis imperdiet velit eleifend ac. Donec blandit mauris eget aliquet lacinia! Donec pulvinar massa interdum ri.</p>
		</div>

		<div id="tab4" class="tab">
			<p>Tab #4 content goes here!</p>
			<p>Donec pulvinar neque sed semper lacinia. Curabitur lacinia ullamcorper nibh; quis imperdiet velit eleifend ac. Donec blandit mauris eget aliquet lacinia! Donec pulvinar massa interdum risus ornare mollis. In hac habitasse platea dictumst. Ut euismod tempus hendrerit. Morbi ut adipiscing nisi. Etiam rutrum sodales gravida! Aliquam tellus orci, iaculis vel.</p>
		</div> -->
	</div>
</div>
    </body>        
     <p> &nbsp; </p>   
        <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>        