
<div id="dndTable" class="quizTable" align="center" style="width:800px;color:black;">
<g:form url="[controller:'quiz',action:'cMatchQnAns']"
		name="addMatchFrom" method="post">
		<input type="hidden" name="dndEdit" id="dndEdit" value="0"/>
		<input type="hidden" name="qnIds" id="qnIds" />
		<input type="hidden" name="noChoices" id="noChoices" value=""/>
		<h3 id="qnHeader">Add Match the Following Question</h3>
		<br>

        <table width="100%">    
            <tbody>
	            <tr align="center">
	                <td height="50px" colspan="4" align="left" style="padding-left:10px;">
	                	<textarea style="width:100%;height:50px" id="Q" name="Q">Question text</textarea>&nbsp;
	                	
                </td>
            </tr>
            <tr>
                <td width="90%">&nbsp;</td>
                <td width="6%" style="color:#999" align="left"></td>
                <td width="72%" style="color:#999" align="left">Delete</td>
            </tr>
       	</tbody>
		</table>
        <br>
	<table id="dndChoice" width="100%" border="0" >    
					<tr id="dndrow1">
						<td>Option1:&nbsp;&nbsp;<textarea style="margin-left:5px;width:65%;height:20px;" id="option1" class="dndOption" name="option1"></textarea>	</td>
						<td>Answer1:&nbsp;&nbsp;<textarea style="margin-left:5px;width:65%;height:20px;" id="answer1" class="dndAnswer" name="answer1"></textarea>	</td>
						<td align="center"><img width="20px" height="20px" src="/aell/images/delete.png" onclick="return delchoice(1)" alt="delete choice"></td>
					</tr>
					<tr id="dndrow2">
						<td>Option2:&nbsp;&nbsp;<textarea style="margin-left:5px;width:65%;height:20px;" id="option2" class="dndOption" name="option2"></textarea>	</td>
						<td>Answer2:&nbsp;&nbsp;<textarea style="margin-left:5px;width:65%;height:20px;" id="answer2" class="dndAnswer" name="answer2"></textarea>	</td>
						<td align="center"><img width="20px" height="20px" src="/aell/images/delete.png" onclick="return delchoice(2)" alt="delete choice"></td>
					</tr>
										
	</table>
<br>
        <div align="left" style="padding-left:18px;">
            <a onclick="dndAddChoice('dndChoice');" title="add choice" style="cursor:pointer"><img src="${hostname}/aell/images/add.gif" width="16" height="16" style="cursor:pointer">&nbsp;</a>
        </div>
        <br>
        
        <div align="left" style="padding-left:18px;">
            <textarea name="H" id="H" cols="45">Hint</textarea>
        </div>
        <br>
        <div align="center">
        <input type="submit" name="addNewQn" id="addNewQn" value="&nbsp;Add Question&nbsp;" onclick="return dndValidate(this);">&nbsp;&nbsp;
            <input type="button" value="&nbsp;Cancel&nbsp;" id="cancel" name="cancel" onclick='closeDivs("addDND");'>
        </div>
	 </g:form>

    </div>
