
<g:form action="editQuestion" name="editQuestionForm" method="post" >
<g:if test="${editQuestionDetailsList}" > 
<g:if test="${editQuestionDetailsList.questionDetails.qnTypeId==1}" > 
   <br />
  <div style="width=100%" align="center">
   <div id="mchoiceQn" style="border-style:outset; border-width:5px; color:#ffffff;; background-color:#FFFFF0; width:72%" align="center">
    <h3 align="center"> Edit. </h3>
          <table width="100%" height="294" border="0" bgcolor="#FFFFF0" style="padding-left:10px" align="center">      
             <tr >				
                  <td  align="center" colspan="2">
                    <input type="hidden" value="${editQuestionDetailsList.questionDetails.id}" name="questionId" id="questionId" />
                     <textarea id="questTxt" name="questTxt" cols="40" rows="4"  style="width:95%" class="quiztext">${editQuestionDetailsList.questionDetails.qnText}</textarea>&nbsp;
                  </td>
                  <td colspan="2">
                     <g:if test="${editQuestionDetailsList.questionDetails.qnImage}" >
                             <img width="100" style="border:dotted; border-color:#999;" height="100" 
                              src="${hostname}/aell/uploads/quiz/crop/${editQuestionDetailsList.questionDetails.qnImage}"
                              name="editImgUpload4" id="editImgUpload4" width="60" height="60" onclick="imagePopUp(4)" 
                              style="cursor:pointer; border:dotted; border-color:#999" title="upload image"  />
                             <input type="hidden" name="editImgValue4" id="editImgValue4" />
                      </g:if>
                      <g:else>
                              <img src="${hostname}/aell/images/upload_question.jpg" name="editImgUpload4" width="60" 
                              height="60" id="editImgUpload4" onclick="imagePopUp(4)" 
                              style="cursor:pointer;" title="attach image" />
                             <input type="hidden" name="editImgValue4" id="editImgValue4" />
                      </g:else>
                  </td>
             </tr>
             <tr align="center">
                <td width="78%" colspan="3">&nbsp;</td>
                <td width="4%" align="center" style="color:#999" colspan="2">ANSWER</td>             
             </tr> 
             <input type="hidden" value="${editQuestionDetailsList.questionDetails.qnTypeId}" name="qnTypeId" id="qnTypeId" />
             <g:each status="ik" in="${editQuestionDetailsList.answers}" var="i">
             <tr align="center">
                 <td height="60" align="center" colspan="3">
                    <textarea  style="width:97%;" id="choices" name="choices" cols="45">${i.choices }</textarea>
                 </td>
                 <td align="center" colspan="3">
                      <g:if test="${editQuestionDetailsList.questionDetails.ansCorrect.toInteger() == i.id.toInteger()}">
                          <g:set var="check" value="checked"/>
                      </g:if>
                      <g:else>
                         <g:set var="check" value=""/>
                      </g:else>
                      <g:radio id="checkAns" checked="${check}" name="checkAns"  value="${i.choices }"/>
                      <input type="hidden" value="${i.id }" name="choiceId" id="choiceId" />
                </td>           
              </tr>  
              </g:each>
              <tr>
                   <td colspan="5" style="color:#999; padding-left:1.5%;">Hint</td>  
              </tr>
              <tr class="">                	
                <td colspan="2" align="left" >
                    <textarea name="hintText" style="font-style:italic;width:100%;padding-left:0%;" id="hintText" cols="45" >
                       <g:if test="${editQuestionDetailsList.hint}" >
                           ${editQuestionDetailsList.hint.hintText}
                       </g:if>
                    </textarea>
                 </td>
                 <td colspan="3">&nbsp;</td>
             </tr> 
             <tr>
                 <td colspan="3">&nbsp;</td>
                 <td colspan="3">&nbsp;</td>
             </tr>
         </table>
      <div align="center"><input type="submit" name="updateQuestion" id="updateQuestion" value="Edit & Publish" onclick="return validateEditQuiz();" >&nbsp;&nbsp;            
            <input type="button" value="Cancel" id="cancel" name="cancel" onclick="return closeEditQuestionForm();" />
      </div>
   </div>
  </div>
 </g:if>
 <g:elseif test="${editQuestionDetailsList.questionDetails.qnTypeId == 2}">
  <br />
    <div style="width=100%" align="center">
    <div style="border:dotted; background-color:#FFFFF0; width:72%" align="center">
      <br />
      <h2 align="center">Edit Image Question.</h2><br/>
       <table border="0" width="100%" bgcolor="#FFFFF0">      
         <tr >				
               <td  align="center" colspan="3">
                     <input type="hidden" value="${editQuestionDetailsList.questionDetails.id}" name="questionId" id="questionId" />
                     <textarea id="questTxt" name="questTxt" class="quiztext" cols="50" rows="4"  style="width:95%" class="quiztext">${editQuestionDetailsList.questionDetails.qnText}</textarea>&nbsp;
                </td>
                <td colspan="2">
                     <g:if test="${editQuestionDetailsList.questionDetails.qnImage}" >
                             <img width="100" style="border:dotted; border-color:#999;" height="100" 
                              src="${hostname}/aell/uploads/quiz/crop/${editQuestionDetailsList.questionDetails.qnImage}"
                              name="editImgUpload4" id="editImgUpload4" width="60" height="60" onclick="imagePopUp(4)" 
                              style="cursor:pointer; border:dotted; border-color:#999" title="upload image"  />
                             <input type="hidden" name="editImgValue4" id="editImgValue4" />
                      </g:if>
                      <g:else>
                              <img src="${hostname}/aell/images/upload_question.jpg" name="editImgUpload4" width="60" 
                              height="60" id="editImgUpload4" onclick="imagePopUp(4)" 
                              style="cursor:pointer;" title="attach image" />
                              <input type="hidden" name="editImgValue4" id="editImgValue4" />
                      </g:else>
                 </td>
         </tr>
         <tr align="center">
                <td width="78%" colspan="3">&nbsp;</td>
                <td width="4%" align="center" style="color:#999" colspan="2">ANSWER</td>             
         </tr>
         <tr>
           <g:each status="ik" in="${editQuestionDetailsList.answers}" var="i">
             <g:if test="${i.choices}" >
                <td width="23%" align="left" class="text"><img src="${hostname}/aell/uploads/quiz/crop/${i.choices}" name="img${ik }" id="img${ik }" width="100" height="100" onclick="imagePopUp(${ik })" style="cursor:pointer; border:dotted; border-color:#999" title="upload image"  />
                <input type="hidden" name="imgHidVal${ik }" id="imgHidVal${ik }" value="${i.choices}" />
                </td>
             </g:if>
           </g:each>
           <input type="hidden" value="${editQuestionDetailsList.questionDetails.qnTypeId}" name="qnTypeId" id="qnTypeId" />
        </tr>
        <tr>
           <g:each status="ik" in="${editQuestionDetailsList.answers}" var="i">
             <g:if test="${i.choices}" >
                 <g:if test="${editQuestionDetailsList.questionDetails.ansCorrect.toInteger() == i.id.toInteger()}">
                     <g:set var="check" value="checked"/>
                 </g:if>
                 <g:else>
                     <g:set var="check" value=""/>
                 </g:else>
              <td align="center">
                  <g:radio id="checkAns" checked="${check}" name="checkAns"  value="${i.choices }"/>
                  <input type="hidden" value="${i.id }" name="choiceId" id="choiceId" />
              </td>
             </g:if>
           </g:each>
        </tr>
        <tr>
             <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2" style="color:#999; padding-left:13px;">HINT</td>                   
        </tr>  
        <tr class="text">				
             <td height="38" colspan="4" style="padding-left:10px;">
                 <textarea name="hintText" style="font-style:italic" id="hintText" cols="40">
					<g:if test="${editQuestionDetailsList.hint}" >
                       ${editQuestionDetailsList.hint.hintText}
                    </g:if> 
                 </textarea>
             </td>
        </tr>
        <tr>
             <td colspan="4">&nbsp;</td>
        </tr>
     </table> 
     <div align="center"><input type="submit" name="updateImgQn" id="updateImgQn" value="Edit & Publish" onclick="return imgQuizValidate();" >&nbsp;&nbsp;
     <input type="button" value="Cancel" id="cancel" name="cancel" onclick="return closeEditQuestionForm();" />
     </div>
    </div>
  </div>  
 </g:elseif> 
 </g:if>
</g:form>