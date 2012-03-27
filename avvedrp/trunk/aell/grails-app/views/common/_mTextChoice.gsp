<g:form action="addQuestion" name="addQuizFrom" method="post" enctype="multipart/form-data">
   <input type="hidden" name="ansType" id="ansType" value="Text"> 
   <input type="hidden" name="qnTypeId" id="qnTypeId" value="1"> 
   <input type="hidden" name="ansDisplayOrder" id="ansDisplayOrder" value="Sequential"> 
   
        <h3>Add Question</h3><br>
        <table width="100%" border="0" id="dataTable">    
            <tbody>
              <tr align="center">
                  <td height="124" colspan="4" align="left" style="padding-left:10px;">
                    <textarea style="width:80%" onclick="clearcontent('qnText')" class="quiztext" onmouseout="fillcontent('qnText')" id="qnText" name="qnText" cols="45" rows="5">Question text</textarea>&nbsp;
                    <img src="${hostname}/aell/images/attach_image.jpg" name="imgUpload0" id="imgUpload0" width="60" height="60" onclick="imagePopUp(0)" style="cursor:pointer;" title="attach image">
                     <input type="hidden" name="imgValue0" id="imgValue0" />
                </td>
            </tr>
        
        
       
            <tr>
                
                <input type="hidden" name="ansVal" id="ansVal">
                <input type="hidden" name="idAssign" id="idAssign"> 
                
                <td width="90%">&nbsp;</td>
                <td width="6%" style="color:#999" align="left">Answer|</td>
                <td width="72%" style="color:#999" align="center">Delete</td>
            </tr>
        </tbody></table>
        <br>
        <div align="left" style="padding-left:18px;">
            <a onclick="addChoice('dataTable');" title="add choice" style="cursor:pointer"><img src="${hostname}/aell/images/add.gif" width="16" height="16" style="cursor:pointer">&nbsp;</a>
        </div>
        <br>
        
        <div align="left" style="padding-left:18px;">
            <textarea onclick="clearcontent('hintText')" style="font-style:italic;" onmouseout="fillcontent('hintText')" name="hintText" id="hintText" cols="45">Hint</textarea>
            <input type="hidden" name="hintType" id="hintType" value="Text">
        </div>
        <br>
        <div align="center">
            <input type="submit" name="addNewQn" id="addNewQn" value="&nbsp;Add &amp; Publish&nbsp;" onclick="return validateAddQuiz(this);">&nbsp;&nbsp;
            <input type="button" value="&nbsp;Cancel&nbsp;" id="cancel" name="cancel" onclick="hideAddDiv();">
        </div>
   </g:form>