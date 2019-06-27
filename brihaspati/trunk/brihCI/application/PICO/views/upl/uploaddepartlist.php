
<!--@filename uploaddepartllist.php  @author Manorama Pal(palseema30@gmail.com)-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome </title>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#btnUpload").on('click',function(){
                    var allowedFiles = [".csv", "text/csv"];
                    var fileUpload = $("#fileUpload");
                    var lblError = $("#lblError");
                    var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+(" + allowedFiles.join('|') + ")$");
                    if (!regex.test(fileUpload.val().toLowerCase())) {
                        lblError.html("Please upload files having extensions: <b>" + allowedFiles.join(', ') + "</b> only.");
                        return false;
                    }
                    lblError.html('');
                    return true;
                });
            });
        </script>	
    </head>
    <body>

	<?php $this->load->view('template/header'); ?>
       
        <div>
        <table width="100%;">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>Upload Department List</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#UploadDepartmentList";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";

                ;?>
            </tr>
        </table>
	<div>
	<?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['error'];
                    echo "</div>";
                }
            ;?>
        </div>
         <table   style="font-size:13px;" >
            <tr><td align="left" colspan=6><b>Note : The file extension should be in csv. The format of Department list file is </b>
                </td>
            </tr>
            <tr><td><b>University Code</b> </td><td> <b> &nbsp;Campus Code</b> </td><td><b>&nbsp;Authority Code</b></td><td> <b>&nbsp;Department Code</b> </td><td> <b>&nbsp;Department Name</b> </td><td> <b>&nbsp;Department Nick Name</b>
		</td>
		</tr>
		<tr><td>&nbsp;CU001</td><td> &nbsp;CU002</td><td> &nbsp;DEAN-FBS </td><td> &nbsp;MVC-BTIC</td><td> &nbsp;Bioinformatics Centre&nbsp;&nbsp;</td><td> &nbsp;&nbsp;&nbsp;&nbsp;BTIS </td></tr>

		<?php echo form_open_multipart('upl/uploaddepartlist');?>
             <tr>
                    <td align="left" colspan="6">
                        <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                        <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                        <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" />
                        <input type='submit' name="uploaddepartlist" id="btnUpload"  value='upload'/></div>
                    </td>

                </tr>

            <?php echo form_close();?>
        </table>
        <p> &nbsp; </p>
        <?php $this->load->view('template/footer'); ?>
    </body>
</html>
