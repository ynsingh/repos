<g:form action="addQuestion" name="editMTextForm" method="post" enctype="multipart/form-data">
   <input type="hidden" name="ansType" id="ansType"> 
   <input type="hidden" name="qnTypeId" id="qnTypeId"> 
   <input type="hidden" name="ansDisplayOrder" id="ansDisplayOrder" value="Sequential"> 
   <input type="hidden" name="ansVal" id="ansVal">
   <input type="hidden" name="idAssign" id="idAssign">
   <input type="hidden" name="addNewQn" id="addNewQn" value="Edit">
   <input type="hidden" name="mTextQnId" id="mTextQnId"> 
        <h3 style="color:black;">Edit Question</h3><br>
        <table width="100%" border="0" id="dataTable">    
            <tbody>
              <tr align="center">
                  <td height="124" colspan="4" align="left" style="padding-left:10px;">
                    <textarea style="width:80%" onclick="" class="quiztext" id="qnText" name="qnText" cols="45" rows="3"></textarea>&nbsp;
                    <img src="${hostname}/aell/images/attach_image.jpg" name="imgUpload0" id="eimgUpload0" width="60" height="60" onclick="imagePopUp(0)" style="cursor:pointer;" title="attach image">
                     <input type="hidden" name="imgValue0" id="eimgValue0" />
                </td>
            </tr>
            <tr id="mQnControls">
                <td width="90%">&nbsp;</td>
                <td width="6%" style="color:#999" align="left">Answer|</td>
                <td width="72%" style="color:#999" align="center">Delete</td>
            </tr>
        </tbody>
        </table>
        <br>
        <div id="mChoiceAdd" align="left" style="padding-left:18px;">
            <img src="${hostname}/aell/images/add.gif" width="16" height="16" style="cursor:pointer" onclick="mChoiceAdd('mTextEdit');">
        </div>
        <br>
        
        <div align="left" style="padding-left:18px;">
            <textarea onclick="" style="font-style:italic;" onmouseout="" name="hintText" id="hintText" cols="45"></textarea>
            <input type="hidden" name="hintType" id="hintType" value="Text">
        </div>
        <br>
        <div align="center">
            <input type="submit" id="editMTxtQn" value="&nbsp;&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;" onclick="return mTextvalidate();">&nbsp;&nbsp;
            <input type="button" value="&nbsp;Cancel&nbsp;" onclick="mTextHide();">
        </div>
   </g:form>