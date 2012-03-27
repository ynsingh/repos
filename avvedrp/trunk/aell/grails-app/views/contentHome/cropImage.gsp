
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${grailsApplication.config.page_title}</title>
<g:javascript plugin="jquery" />
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.min.js')}"></script>
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.Jcrop.js')}"></script>
<link rel="stylesheet" href="${resource(dir:'css',file:'jquery.Jcrop.css')}" />
<script language="javascript">
function checkUploadName()
{
	if(document.getElementById('imgName').value=="")
	{
		alert('Please select an image.');
		return false;
	}   
	else {
	var img = document.getElementById('imgName');
    var width=img.clientWidth;
	var height = img.clientHeight;
	var div = document.getElementById("showCropImage");
    div.style.width = width+"px"; 
	var ext = document.getElementById('imgName').value;
  	ext = ext.substring(ext.length-3,ext.length);
  	ext = ext.toLowerCase();
  	if(ext == "jpg"||ext == "jpeg"||ext == "JPG"||ext == "png"|| ext == "gif")
		return true; 
  	else {
    		alert("Invalid file type, please choose an image file.");
    		return false; 
	   }
  
  		   
   }
   return true;
}
$(function() {
    $('#cropbox').Jcrop({
        aspectRatio: 1,
        aspectRatio: 1,
		onSelect: updateCoords
    });
});
function updateCoords(c) {
    $('#x1').val(c.x);
       $('#y1').val(c.y);
       $('#x2').val(c.x2);
       $('#y2').val(c.y2);
}
function checkCoords()
{
	if (parseInt($('#x2').val())) return true;
	alert('Please crop the image.');
	return false;
};
</script>
</head>

<body>
<div align="center">
    <h3 align="center">Upload Image</h3>
  <!--   <g:form action="uploadImage" name="imageUploadForm" method="post" > -->
    <g:uploadForm action="uploadImage" method="post" >
     <table border="0" width="70%" style="border-style:solid; border-width:thin; border-color:#999; padding-top:10px">
    	<tr>
    	         <td>
    	             <g:if test="${flash.message}">
	           	            <div class="message"><img src="${hostname}/aell/images/tick.gif" title="Success" height="20" width="20">${flash.message}</div>
                      </g:if>
                      <g:if test="${flash.error}">
    	       	            <div class="error"><img src="${hostname}/aell/images/wrong.gif" title="Failure" height="20" width="20">${flash.error}</div>
                      </g:if> 
                 </td>
    	</tr>   
    	<tr>
                 <td align="right"><b>Choose image : </b>
                 </td>
                 <td><input type="file" name="imgName" id="imgName" />
                 </td>
            
       </tr>
       <tr>
       		     <td>&nbsp;</td>
                 <td><input type="submit" name="uploadImg" id="uploadImg" value="Upload" onclick="return checkUploadName();"/></td>
       </tr>     
    </table>
    <br />
	<br />
	<div id="showCropImage" style="border-style:solid; width:${width}px; border-width:thin; border-color:#999; padding-top:10px; color:#999;" align="center">
      <input type="hidden" value="${number}" name="number" id="number" />
          <g:if test="${fileName}" >  
              <g:if test="${cropImage=='crop'}" >
                 <img src="${hostname}/aell/uploads/quiz/crop/${fileName}"  id="cropbox" height="100" width="100" style="border:dotted; border-color:#999;"/>
              </g:if>
              <g:else>
                <img src="${hostname}/aell/uploads/quiz/${fileName}"  id="cropbox"/>
              </g:else>
    		<input type="hidden" id="x1" name="x1" />
            <input type="hidden" id="y1" name="y1" />
            <input type="hidden" id="x2" name="x2" />
            <input type="hidden" id="y2" name="y2" />
            <input type="hidden" id="fileName" name="fileName" value="${fileName}" />
            <br /><br />        
          </g:if>
          <g:else>
             <br /><br /> Preview
           </g:else>
     </div><br />
     <g:if test="${fileName}" >
          <g:if test="${cropImage=='crop'}" >
              <input type="button" value="Done" name="done" id="done" onclick="javascript: window.opener.CallAlert('${filePath}','${number}','${fileName }'), self.close();" />
          </g:if>
          <g:else>
              <input type="submit" name="cropImg"  id="cropImg" value="Crop Image" onClick="return checkCoords();" />
          </g:else>
     </g:if>
     <g:else>
     <input type="submit" name="cropImg" disabled="disabled" id="cropImg" value="Crop Image" onClick="return checkCoords();" />
     </g:else>
 </g:uploadForm>
<!--</g:form> -->
</div>
</body>
</html>
