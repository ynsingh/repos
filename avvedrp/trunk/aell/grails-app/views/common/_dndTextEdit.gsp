<g:form url="[controller:'quiz',action:'cfillblanks']" name="addQuizFrom" method="post">
	<input type="hidden" name="isEdit" id="isEdit" value="0">
	<input type="hidden" name="qnId" id="qnId" value="0">
     <input type="hidden" name="ansType" id="ansType" value="dnd"> 
	<div id="addFibQuestion" align="center" width="40%">
			<div id="addDndDiv" class="quizTable" align="center">
				<input type="hidden" name="qnTypeId" id="qnTypeId" value="4">
                <h3 id="qnHeader" style="color: black;">Add Drag and Drop Question</h3>
                <br>
                <table width="100%" border="0">
                    <tbody>
                        <tr>
                            <td height="124" colspan="4" align="left"
                                style="padding-left: 10px;"><textarea class="fibdndquiztext" id="dndqnText" name="qnText" 
                                style="width: 580px;height: 86px; ">Enclose text that is to [appear] as a blank within square brackets. Example the [blank]</textarea>
                            </td>
                        </tr>
                        <tr><td>
                            <div align="center" style="padding-left: 18px;">
                                <textarea style="font-style: italic;" onmouseout="" name="hintText" id="dndtHintText" cols="45">Hint</textarea>
                                <input type="hidden" name="hintType" id="hintType" value="Text">
                           </div>
                           </td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <br>
                <div align="center">
                    <input type="submit" name="addNewQn" id="addNewQn" value="&nbsp;Create Question&nbsp;" 
                        onclick="return validateFibDndQuiz('dndqnText');">&nbsp;&nbsp; <input
                        type="button" class="cancelBtn" value="&nbsp;Cancel&nbsp;"
                        id="cancel" name="cancel" onclick='closeDivs("addDND");'>
                </div>
                <br>
				</div>
	</div>
</g:form>